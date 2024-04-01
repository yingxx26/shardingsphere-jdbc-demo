package com.courage.shardingsphere.jdbc.domain.mapper;

import com.courage.shardingsphere.jdbc.domain.po.TestBigsql;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("SELECT * FROM test_bigsql")
    List<TestBigsql> selectPageVo();
}
