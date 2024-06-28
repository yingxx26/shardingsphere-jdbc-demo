package com.courage.shardingsphere.jdbc.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.courage.shardingsphere.jdbc.common.result.ResponseEntity;
import com.courage.shardingsphere.jdbc.domain.po.TEntOrder;
import com.courage.shardingsphere.jdbc.domain.po.TestBigsql;
import com.courage.shardingsphere.jdbc.service.OrderService;
import com.courage.shardingsphere.jdbc.service.UserService;
import com.courage.shardingsphere.jdbc.service.sharding.PreciseShardingTableAlgorithm;
import com.courage.shardingsphere.jdbc.service.sharding.RangeShardingTableAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping("/test")
    @ApiOperation("test")
    public ResponseEntity test() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/ds_0?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&useTimezone=true&allowPublicKeyRetrieval=true");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");
        dataSourceMap.put("ds0", dataSource1);


        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("test_bigsql","ds0.test_bigsql${1..3}");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id",
                new PreciseShardingTableAlgorithm(), new RangeShardingTableAlgorithm()));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 省略配置order_item表规则...
        // ...

        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());

        Connection connection = dataSource.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("select * from test_bigsql where id between  4 and 20");
        //prepareStatement.setString(1, "2342");
        prepareStatement.execute();
        ResultSet result = prepareStatement.getResultSet();
        if (result.next()) {
            log.info("标准分片查询数据x_openid:{}", result.getString(1));
        }
        connection.close();
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
        for(long i=1;i<10;i++){
            orderService.save(i);
        }
        return ResponseEntity.successResult(null);
    }


    @GetMapping("/queryUsersPage")//没用插件
    @ApiOperation("queryUsersPage")
    public ResponseEntity queryUsersPage() {

        Page<TestBigsql> userPage = new Page<>(4,2);
        List<TestBigsql> testBigsqls = userService.selectPageVo(userPage);
        return ResponseEntity.successResult(testBigsqls);
    }


    @GetMapping("/queryUsersGroup")//没用插件
    @ApiOperation("queryUsersGroup")
    public ResponseEntity queryUsersGroup() {
       userService.selectgroupVo();
        return ResponseEntity.successResult("");
    }
}
