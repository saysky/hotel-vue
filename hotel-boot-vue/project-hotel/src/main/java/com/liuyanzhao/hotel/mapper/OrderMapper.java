package com.liuyanzhao.hotel.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyanzhao.hotel.domain.Order;

/**
 * 订单Mapper接口
 * 
 * @author saysky
 * @date 2024-01-15
 */
public interface OrderMapper extends BaseMapper<Order>
{
    /**
     * 查询订单
     * 
     * @param id 订单主键
     * @return 订单
     */
    Order selectOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param order 订单
     * @return 订单集合
     */
    List<Order> selectOrderList(Order order);

    /**
     * 新增订单
     * 
     * @param order 订单
     * @return 结果
     */
    int insertOrder(Order order);

    /**
     * 修改订单
     * 
     * @param order 订单
     * @return 结果
     */
    int updateOrder(Order order);

    /**
     * 删除订单
     * 
     * @param id 订单主键
     * @return 结果
     */
    int deleteOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteOrderByIds(Long[] ids);

    /**
     * 获取最新的数据
     * @return
     */
    Order getMaxId();


    /**
     * 获取超时未支付的订单， 5分钟
     * @return
     */
    List<Order> getPayOverTimeOrders();

    /**
     * 获取超时未接单的订单，30分钟
     * @return
     */
    List<Order> getAuditOverTimeOrders();
}
