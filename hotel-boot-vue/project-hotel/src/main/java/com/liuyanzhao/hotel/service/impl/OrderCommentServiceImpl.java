package com.liuyanzhao.hotel.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyanzhao.common.exception.ServiceException;
import com.liuyanzhao.common.utils.DateUtils;
import com.liuyanzhao.hotel.domain.OrderItem;
import com.liuyanzhao.hotel.service.OrderItemService;
import com.liuyanzhao.hotel.service.OrderService;
import com.liuyanzhao.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuyanzhao.hotel.mapper.OrderCommentMapper;
import com.liuyanzhao.hotel.domain.OrderComment;
import com.liuyanzhao.hotel.service.OrderCommentService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单评价Service业务层处理
 *
 * @author saysky
 * @date 2024-01-15
 */
@Service
public class OrderCommentServiceImpl extends ServiceImpl<OrderCommentMapper, OrderComment> implements OrderCommentService {
    @Autowired
    private OrderCommentMapper orderCommentMapper;

    @Autowired
    private RoomService roomService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 查询订单评价
     *
     * @param id 订单评价主键
     * @return 订单评价
     */
    @Override
    public OrderComment selectOrderCommentById(Long id) {
        return orderCommentMapper.selectOrderCommentById(id);
    }

    /**
     * 查询订单评价列表
     *
     * @param orderComment 订单评价
     * @return 订单评价
     */
    @Override
    public List<OrderComment> selectOrderCommentList(OrderComment orderComment) {
        List<OrderComment> orderComments = orderCommentMapper.selectOrderCommentList(orderComment);
        for (OrderComment comment : orderComments) {
            comment.setOrder(orderService.getById(comment.getOrderId()));
        }
        return orderComments;
    }

    /**
     * 新增订单评价
     *
     * @param orderComment 订单评价
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrderComment(OrderComment orderComment) {
        int starLevel = orderComment.getStarLevel();
        if (starLevel > 5 || starLevel < 1) {
            throw new ServiceException("评价星级不合法");
        }
        OrderItem orderItem = orderItemService.getById(orderComment.getOrderItemId());
        if (orderItem == null) {
            throw new ServiceException("订单项不存在");
        }
        orderComment.setRoomId(orderItem.getRoomId());
        orderComment.setCreateTime(DateUtils.getNowDate());
        orderCommentMapper.insertOrderComment(orderComment);

        // 重算评分
        roomService.reComputeStarLevel(orderItem.getRoomId());

        // 更新明细已评论
        orderItem.setHasComment(1);
        orderItemService.updateById(orderItem);

        // 更新订单是否已评论
        orderService.updateHasComment(orderItem.getOrderId());
        return 1;
    }

    /**
     * 修改订单评价
     *
     * @param orderComment 订单评价
     * @return 结果
     */
    @Override
    public int updateOrderComment(OrderComment orderComment) {
        return orderCommentMapper.updateOrderComment(orderComment);
    }

    /**
     * 批量删除订单评价
     *
     * @param ids 需要删除的订单评价主键
     * @return 结果
     */
    @Override
    public int deleteOrderCommentByIds(Long[] ids) {
        return orderCommentMapper.deleteOrderCommentByIds(ids);
    }

    /**
     * 删除订单评价信息
     *
     * @param id 订单评价主键
     * @return 结果
     */
    @Override
    public int deleteOrderCommentById(Long id) {
        return orderCommentMapper.deleteOrderCommentById(id);
    }
}
