package com.courage.shardingsphere.jdbc.service;

import com.courage.shardingsphere.jdbc.domain.mapper.UserMapper;
import com.courage.shardingsphere.jdbc.domain.po.TestBigsql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<TestBigsql> selectPageVo( ) {
        return userMapper.selectPageVo();
    }
}
