package com.courage.shardingsphere.jdbc.service.sharding;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 自定义实现 范围分片算法（RangeShardingAlgorithm）接口
 * 数据表 table 的范围分片
 * @author Peng zhizhong
 * @version 1.0
 * fileName RangeShardingTableAlgorithm
 * createTime 2020/5/11  19:21
 */
public class RangeShardingTableAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(final Collection<String> tableNames,
                                         final RangeShardingValue<Integer> shardingValue) {
        Set<String> result = new LinkedHashSet<>();
        // 如果between  2000000 and 7000000
        //if (Range.closed(2000000, 7000000).encloses(shardingValue.getValueRange())) {
            for (String each : tableNames) {
               // if (each.endsWith("0")) {
                    result.add(each);
              //  }
           }
        /*} else {
            throw new UnsupportedOperationException();
        }*/
        return result;
    }

}