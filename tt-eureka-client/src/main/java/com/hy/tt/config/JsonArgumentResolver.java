package com.hy.tt.config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @auther thy
 * @date 2019/8/10
 */
@Configuration
public class JsonArgumentResolver implements HandlerMethodArgumentResolver, BeanPostProcessor, WebMvcConfigurer {

    /**
     * 用于缓存body ,不是每次解析参数都读取解析body
     */
    private static final String JSONREQUESTBODY = "JOSN_REQUEST_BODY";

    private String JSON_PATH_SEPARATOR = "/";

    private String VALUE_SEPARATOR = "\\.";

    private ObjectMapper om;

    private JsonFactory jsonFactory;

    public JsonArgumentResolver(){
        this.om = new ObjectMapper();
        this.om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.jsonFactory = new JsonFactory(this.om);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonArgumentResolver());
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(JsonArg.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Object value = null;
        //读取request解析成JsonNode
        JsonNode rootNode = this.getRequestBody(nativeWebRequest);
        //根据JsonArg的参数解析node的元素,对应相应的类型返回
        if(rootNode != null){
            JsonArg parameterAnnotation = methodParameter.getParameterAnnotation(JsonArg.class);
            value = this.caculateValue(rootNode, parameterAnnotation, methodParameter);
            this.processRequired(value, parameterAnnotation);
        }
        return value;
    }

    /**
     * 处理{@code required}属性
     *
     * @param value                 要返回的参数值
     * @param annotation            参数注解
     */
    private void processRequired(Object value, JsonArg annotation) {
        if (null==value && annotation.required()) {
//            AssertUtil.throwException(ErrorCode.PARAM_ERROR.getErrorCode(),
//                    "请求解析失败：未找到" + annotation.value() + "参数值");
        }
    }

    /**
     * 解析获取请求体
     * @param webRequest
     * @return
     */
    private JsonNode getRequestBody(NativeWebRequest webRequest){
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        JsonNode rootNode = (JsonNode) nativeRequest.getAttribute(JSONREQUESTBODY);

        if(rootNode == null){
            try {
                JsonNode jsonNode = this.om.readTree(nativeRequest.getReader());
                nativeRequest.setAttribute(JSONREQUESTBODY,jsonNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rootNode;
    }

    private Object caculateValue(JsonNode rootNode, JsonArg annotation, MethodParameter parameter) {
        Object obj = null;

        try {
            JsonNode targetNode = rootNode.at(this.caculateJsonPath(annotation));
            if (!targetNode.isMissingNode()) {
                Class<?> parameterType = parameter.getParameterType();
                if (List.class.isAssignableFrom(parameterType)) {
                    obj = this.caculateValueOfList(targetNode, parameter);
                }else{
                    try{
                        JsonParser parser = this.jsonFactory.createParser(targetNode.toString());
                        obj = parser.readValuesAs(parameterType);
                    }catch (IOException e){

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 特别处理List
     *
     * @param node                 节点
     * @param parameter            参数类型
     */
    private Object caculateValueOfList(JsonNode node, MethodParameter parameter) throws IOException{
        ParameterizedType pType = (ParameterizedType) parameter.getGenericParameterType();
        return this.om.readValue(node.toString(),
                om.getTypeFactory().constructCollectionType(List.class, (Class)pType.getActualTypeArguments()[0]));
    }

    /**
     * 计算JsonPath
     *
     * @param annotation            注解
     * @return                      jsonpath
     */
    private String caculateJsonPath(JsonArg annotation) {
        return JSON_PATH_SEPARATOR + String.join(JSON_PATH_SEPARATOR, annotation.value().split(VALUE_SEPARATOR));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // swagger参数列表忽略带JsonArg注解的参数
        if (bean.getClass().isAssignableFrom(Docket.class)) {
            ((Docket)bean).ignoredParameterTypes(JsonArg.class);
        }
        return bean;
    }
}
