package com.hy.tt.config;

import java.lang.annotation.*;


/**
 * 参数注解,用于json对象解析
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JsonArg {

    /**
     * json属性名称
     * @return
     */
    String value();

    /**
     * 是否必须
     * @return
     */
    boolean required() default true;
}
