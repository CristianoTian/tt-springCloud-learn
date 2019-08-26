package com.hy.tt.serTwo.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.tt.dao.People;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.*;

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

    public static void main(String[] args) {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
//            System.out.println("召唤神龙");
//        });
//
//        for (int i = 0; i <7 ; i++) {
//            new Thread(()->{
//                System.out.println("收集第" + Thread.currentThread().getName() + "颗");
//                try {
//                    cyclicBarrier.await();
//                    System.out.println("给我888个亿");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (BrokenBarrierException e) {
//                    e.printStackTrace();
//                }
//            },String.valueOf(i+1)).start();
//        }

        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        //模拟实际业务逻辑
                        Thread.sleep((long) (Math.random() * 10000));
                        // 访问完后，释放
                        semp.release();
                    } catch (InterruptedException e) {
                    }
                }
            };
            exec.execute(run);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println(semp.getQueueLength());



        // 退出线程池
        exec.shutdown();

    }
}
