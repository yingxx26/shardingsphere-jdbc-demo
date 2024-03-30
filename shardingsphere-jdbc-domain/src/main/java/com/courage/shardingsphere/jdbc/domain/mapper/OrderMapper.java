package com.courage.shardingsphere.jdbc.domain.mapper;

import com.courage.shardingsphere.jdbc.domain.po.TEntOrder;
import com.courage.shardingsphere.jdbc.domain.po.TEntOrderDetail;
import com.courage.shardingsphere.jdbc.domain.po.TEntOrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {

    TEntOrder getOrderById(Long id);

    //保存基本信息
    void saveOrder(TEntOrder entOrder);

    //保存详情
    void saveOrderDetail(TEntOrderDetail orderDetail);

    //订单条目
    void saveOrderItem(TEntOrderItem entOrderItem);

    Map<String,Object> queryOrder(Long orderId);

    List<TEntOrder> queryOrders( Map<String,Object> map);
}
