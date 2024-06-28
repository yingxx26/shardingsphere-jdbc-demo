package com.courage.shardingsphere.jdbc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.courage.shardingsphere.jdbc.domain.po.TestBigsql;

import java.util.List;


public interface  UserService  /*extends IService<TestBigsql> */{

      List<TestBigsql> selectPageVo( Page page );

     void selectgroupVo();
}