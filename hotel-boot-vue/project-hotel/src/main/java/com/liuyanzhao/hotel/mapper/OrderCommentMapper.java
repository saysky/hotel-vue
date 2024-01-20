package com.liuyanzhao.hotel.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyanzhao.hotel.domain.OrderComment;

/**
 * 订单评价Mapper接口
 * 
 * @author saysky
 * @date 2024-01-15
 */
public interface OrderCommentMapper extends BaseMapper<OrderComment>
{
    /**
     * 查询订单评价
     * 
     * @param id 订单评价主键
     * @return 订单评价
     */
    OrderComment selectOrderCommentById(Long id);

    /**
     * 查询订单评价列表
     * 
     * @param orderComment 订单评价
     * @return 订单评价集合
     */
    List<OrderComment> selectOrderCommentList(OrderComment orderComment);

    /**
     * 新增订单评价
     * 
     * @param orderComment 订单评价
     * @return 结果
     */
    int insertOrderComment(OrderComment orderComment);

    /**
     * 修改订单评价
     * 
     * @param orderComment 订单评价
     * @return 结果
     */
    int updateOrderComment(OrderComment orderComment);

    /**
     * 删除订单评价
     * 
     * @param id 订单评价主键
     * @return 结果
     */
    int deleteOrderCommentById(Long id);

    /**
     * 批量删除订单评价
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteOrderCommentByIds(Long[] ids);
}
