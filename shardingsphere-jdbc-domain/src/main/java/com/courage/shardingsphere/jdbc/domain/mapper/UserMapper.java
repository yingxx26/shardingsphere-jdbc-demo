package com.courage.shardingsphere.jdbc.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.courage.shardingsphere.jdbc.domain.po.TestBigsql;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper   {

     @Select("SELECT * FROM test_bigsql ")
    List<TestBigsql> selectPageVo(Page<TestBigsql> page);

    @Select("SELECT count(*) FROM test_bigsql group by id")
    void selectgroupVo();
}
