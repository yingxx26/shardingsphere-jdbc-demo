package com.courage.shardingsphere.jdbc.server.controller;

import com.courage.shardingsphere.jdbc.common.result.ResponseEntity;
import com.courage.shardingsphere.jdbc.domain.po.TEntOrder;
import com.courage.shardingsphere.jdbc.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/hello")
@Slf4j
public class TestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/test")
    @ApiOperation("test")
    public ResponseEntity test() {
        redisTemplate.opsForValue().set("hello", "myulife");
        return ResponseEntity.successResult("mylife");
    }

    @GetMapping("/queryOrder")
    @ApiOperation("queryOrder")
    public ResponseEntity queryOrder(String orderId) {
        Map<String, Object> orderMap = orderService.queryOrder(Long.valueOf(orderId));
        return ResponseEntity.successResult(orderMap);
    }

    @GetMapping("/queryOrderByEntId")
    @ApiOperation("queryOrderByEntId")
    public ResponseEntity queryOrderByEntId(String entId) {
        Map<String, Object> orderMap = orderService.queryOrderByEntId(Long.valueOf(entId));
        return ResponseEntity.successResult(orderMap);
    }

    @GetMapping("/queryOrders")
    @ApiOperation("queryOrders")
    public ResponseEntity queryOrders() {
        List<TEntOrder> list= orderService.queryOrders();
        return ResponseEntity.successResult(list);
    }


    @GetMapping("/save")
    @ApiOperation("save")
    public ResponseEntity save() {
        for(long i=1;i<2000;i++){
            orderService.save(i);
        }
        return ResponseEntity.successResult(null);
    }

    public static void main(String[] args) {

        List<Long> list =new ArrayList<>();
        list.add(585785164345749511l);
        list.add(585785164346216451l);
        list.add(585785164823797769l);
        list.add(585785164824395781l);
        list.add(585785166824767499l);
        list.add(585785167050825729l);
        list.add(585785167051296781l);


        list.add(585785164347441163l);
        list.add(585785164825026561l);
        list.add(585785164825440269l);
        list.add(585785165223317507l);
        list.add(585785166825463815l);

        list.add(585785165224222727l);
        list.add(585785165224820747l);
        list.add(585785165589229577l);

        list.add(585785164105797637l);
        list.add(585785165590093825l);
        list.add(585785165590659077l);


        List<Long> sortedNumbers1 = list.stream().sorted().collect(Collectors.toList());
        System.out.println("按照自然顺序排序后的列表：" + sortedNumbers1);
    // -1+1+3+4  +4s
        //11  16  19
        List<Long> result  =new ArrayList<>();
        result.add(list.get(5));
        result.add(list.get(6));
        result.add(list.get(7));
        System.out.println(" ：" + result);


    }
}
