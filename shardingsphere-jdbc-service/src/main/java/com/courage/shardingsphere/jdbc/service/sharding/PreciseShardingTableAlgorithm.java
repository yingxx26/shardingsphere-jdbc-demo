package com.courage.shardingsphere.jdbc.service.sharding;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;


/**
 * 自定义实现 精准分片算法（PreciseShardingAlgorithm）接口
 * 数据表table的精准分片
 * @author Peng zhizhong
 * @version 1.0
 * fileName PreciseShardingTableAlgorithm
 * createTime 2020/5/11  19:21
 */
public class PreciseShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * 注释键 PreciseShardingDBAlgorithm
     * @param tableNames
     * @param shardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> tableNames,
                             PreciseShardingValue<Long> shardingValue) {
        for (String key : tableNames) {
            if (key.endsWith(String.valueOf(shardingValue.getValue() % tableNames.size()))) {
                System.out.println("key"+key);
                return key;
            }
        }
        throw new UnsupportedOperationException();
    }

}