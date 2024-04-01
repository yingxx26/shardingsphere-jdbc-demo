package com.courage.shardingsphere.jdbc.server.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class MybatisPageConfig {

    /**
     * 可能存在多个连接工厂，是允许这么注入的
     */
    @Resource
    private List<SqlSessionFactory> sqlSessionFactoryList;

   /* @PostConstruct
    public void initConfig(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        // 设置数据源方言，使用mysql
        properties.setProperty("helperDialect","mysql");

        pageInterceptor.setProperties(properties);
        sqlSessionFactoryList.forEach(factory ->factory.getConfiguration().addInterceptor(pageInterceptor));
    }*/
}