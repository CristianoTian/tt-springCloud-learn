package com.hy.tt.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @auther thy
 * @date 2019/7/31
 */
@Data
@Accessors(chain = true)
public class People {

    @ApiModelProperty(value = "名字")
    private String  name;
    @ApiModelProperty(value = "地址")
    private String  address;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "性别")
    private Integer sex;
}
