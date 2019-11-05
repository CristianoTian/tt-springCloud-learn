package com.hy.tt;

/**
 * @auther thy
 * @date 2019/11/5
 */
public class ResponseData<T> {

    private String code;

    private String desc;

    private T data;

    public String getCode() {
        return code;
    }

    public ResponseData<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ResponseData<T> setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseData<T> setData(T data) {
        this.data = data;
        return this;
    }
}
