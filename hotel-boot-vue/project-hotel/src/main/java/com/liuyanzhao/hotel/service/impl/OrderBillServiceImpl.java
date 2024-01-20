package com.liuyanzhao.hotel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyanzhao.common.core.domain.entity.SysUser;
import com.liuyanzhao.common.utils.DateUtils;
import com.liuyanzhao.hotel.domain.Order;
import com.liuyanzhao.hotel.domain.RoomCategory;
import com.liuyanzhao.hotel.service.ISysUserService;
import com.liuyanzhao.hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuyanzhao.hotel.mapper.OrderBillMapper;
import com.liuyanzhao.hotel.domain.OrderBill;
import com.liuyanzhao.hotel.service.OrderBillService;

/**
 * 账单Service业务层处理
 *
 * @author saysky
 * @date 2024-01-17
 */
@Service
public class OrderBillServiceImpl extends ServiceImpl<OrderBillMapper, OrderBill> implements OrderBillService {
    @Autowired
    private OrderBillMapper orderBillMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ISysUserService userService;

    /**
     * 查询账单
     *
     * @param id 账单主键
     * @return 账单
     */
    @Override
    public OrderBill selectOrderBillById(Long id) {
        OrderBill orderBill = orderBillMapper.selectOrderBillById(id);
        if (orderBill == null) {
            return null;
        }
        orderBill.setOrder(orderService.getById(orderBill.getOrderId()));
        orderBill.setUser(userService.selectUserById(orderBill.getUserId()));
        return orderBill;
    }

    /**
     * 查询账单列表
     *
     * @param orderBill 账单
     * @return 账单
     */
    @Override
    public List<OrderBill> selectOrderBillList(OrderBill orderBill) {
        List<OrderBill> orderBills = orderBillMapper.selectOrderBillList(orderBill);
        Map<Long, Order> orderMap = new HashMap<>();
        Map<Long, SysUser> userMap = new HashMap<>();
        for (OrderBill item : orderBills) {
            if (orderMap.containsKey(item.getOrderId())) {
                item.setOrder(orderMap.get(item.getOrderId()));
            } else {
                Order order = orderService.getById(item.getOrderId());
                item.setOrder(order);
                orderMap.put(item.getOrderId(), order);
            }
            if (userMap.containsKey(item.getUserId())) {
                item.setUser(userMap.get(item.getUserId()));
            } else {
                SysUser user = userService.selectUserById(item.getUserId());
                item.setUser(user);
                userMap.put(item.getUserId(), user);
            }

        }


        return orderBills;
    }

    /**
     * 新增账单
     *
     * @param orderBill 账单
     * @return 结果
     */
    @Override
    public int insertOrderBill(OrderBill orderBill) {
        orderBill.setCreateTime(DateUtils.getNowDate());
        return orderBillMapper.insertOrderBill(orderBill);
    }

    /**
     * 修改账单
     *
     * @param orderBill 账单
     * @return 结果
     */
    @Override
    public int updateOrderBill(OrderBill orderBill) {
        return orderBillMapper.updateOrderBill(orderBill);
    }

    /**
     * 批量删除账单
     *
     * @param ids 需要删除的账单主键
     * @return 结果
     */
    @Override
    public int deleteOrderBillByIds(Long[] ids) {
        return orderBillMapper.deleteOrderBillByIds(ids);
    }

    /**
     * 删除账单信息
     *
     * @param id 账单主键
     * @return 结果
     */
    @Override
    public int deleteOrderBillById(Long id) {
        return orderBillMapper.deleteOrderBillById(id);
    }
}
