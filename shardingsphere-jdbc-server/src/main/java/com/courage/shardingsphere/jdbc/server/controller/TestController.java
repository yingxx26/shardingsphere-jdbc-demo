package com.courage.shardingsphere.jdbc.server.controller;

import com.courage.shardingsphere.jdbc.common.result.ResponseEntity;
import com.courage.shardingsphere.jdbc.domain.po.TEntOrder;
import com.courage.shardingsphere.jdbc.domain.po.TestBigsql;
import com.courage.shardingsphere.jdbc.service.OrderService;
import com.courage.shardingsphere.jdbc.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/hello")
@Slf4j
public class TestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


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

    @GetMapping("/queryOrders")
    @ApiOperation("queryOrders")
    public ResponseEntity queryOrders() {
        List<TEntOrder> orderMap = orderService.queryOrders();
        return ResponseEntity.successResult(orderMap);
    }


    @GetMapping("/save")
    @ApiOperation("save")
    public ResponseEntity save() {
        orderService.save();
        return ResponseEntity.successResult(null);
    }


    @GetMapping("/queryUsers")//没用插件
    @ApiOperation("queryUsers")
    public ResponseEntity queryUsers() {
        List<TestBigsql> orderMap = orderService.queryUsers();
        return ResponseEntity.successResult(orderMap);
    }

    @GetMapping("/saveuser")
    @ApiOperation("saveuser")
    public ResponseEntity saveuser() {
        orderService.saveUser();
        return ResponseEntity.successResult(null);
    }

    @GetMapping("/queryUsersPage")//没用插件
    @ApiOperation("queryUsersPage")
    public ResponseEntity queryUsersPage() {

            PageHelper.startPage(4,2);
            List<TestBigsql> itemsList =   userService.selectPageVo();;
            PageInfo<TestBigsql> pageInfo = new PageInfo<>(itemsList);
         return ResponseEntity.successResult(pageInfo);
    }

}
