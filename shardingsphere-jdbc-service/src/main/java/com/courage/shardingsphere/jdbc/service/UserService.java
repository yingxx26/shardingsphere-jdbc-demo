package com.courage.shardingsphere.jdbc.service;

import com.courage.shardingsphere.jdbc.domain.po.TestBigsql;

import java.util.List;


public interface  UserService   {

      List<TestBigsql> selectPageVo( );
}