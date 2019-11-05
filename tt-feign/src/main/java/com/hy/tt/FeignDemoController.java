package com.hy.tt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther thy
 * @date 2019/11/5
 */
@Controller
@RequestMapping("/demo")
public class FeignDemoController {

    @Autowired
    FeignClientProxy feignClientProxy;

    @RequestMapping(value = "/feign")
    @ResponseBody
    public String testFeign(@RequestParam(name = "name") String name) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData<DemoResp> respResponseBody = feignClientProxy.invoke(name);
        return "调用结果:" + objectMapper.writeValueAsString(respResponseBody);

    }


    @RequestMapping(value = "/hello")
    @ResponseBody
    public ResponseData<DemoResp> helloFeign(@RequestParam(name = "name") String name)  {
        DemoResp demoResp = new DemoResp();
        demoResp.setAge("22");
        demoResp.setName("name");

        ResponseData responseData = new ResponseData();
        responseData.setCode("10000").setData(demoResp).setDesc("helloyyyyyy");
        return responseData;
    }

}
