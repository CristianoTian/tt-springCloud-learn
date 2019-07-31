package com.hy.tt.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.tt.dao.People;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @auther thy
 * @date 2019/7/31
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("test swagger body")
    @PostMapping(value="/get", produces="application/json")
    public String  get(@RequestBody People people){
        People people1 = new People();
        people1.setAddress(people.getAddress()).setAge(people.getAge()).setName(people.getName()).setSex(people.getSex());
        return JSONObject.toJSONString(  people1.toString());
    }


    @ApiOperation("test swagger param")
    @ApiImplicitParams({
            @ApiImplicitParam(name= "name",value = "名字" ,dataType = "String" ,paramType = "query" ,required = true),
            @ApiImplicitParam(name= "age",value = "年龄" ,dataType = "int" ,paramType = "query" ),
    })
    @PostMapping(value="/find", produces="application/json")
    public String find(@RequestParam("name") String name , @RequestParam(value = "age",required = false) Integer age){
        People people1 = new People();
        people1.setAge(age).setName(name);
        return JSONObject.toJSONString(  people1.toString());
    }


    @ApiOperation("test swagger path")
    @ApiImplicitParams({
            @ApiImplicitParam(name= "id",value = "id" ,dataType = "Integer"  ,paramType = "path" , required = true),
    })
    @GetMapping(value="testGet/{id}", produces="application/json")
    public String testGet(@PathVariable Integer id){
        People people1 = new People();
        people1.setAge(id).setName("ttt");
        return JSONObject.toJSONString(  people1.toString());
    }
}
