package com.liuyanzhao.hotel.common.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuyanzhao.hotel.domain.Order;
import com.liuyanzhao.hotel.domain.OrderBill;
import com.liuyanzhao.hotel.domain.Record;
import com.liuyanzhao.hotel.mapper.OrderMapper;
import com.liuyanzhao.hotel.service.OrderBillService;
import com.liuyanzhao.hotel.service.RecordService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 言曌
 * @since 2024/1/5 11:00
 */
@Component
public class OrderSchedule {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderBillService billService;

    @Autowired
    private RecordService recordService;

    /**
     * 更新支付超时的订单
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void updatePayOverTime() {
        List<Order> orders = orderMapper.getPayOverTimeOrders();
        for (Order order : orders) {
            order.setStatus(-2);
            orderMapper.updateById(order);

            // 预定失败，删除预定记录
            LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Record::getOrderId, order.getId());
            recordService.remove(queryWrapper);
        }
    }


    /**
     * 更新接单超时的订单
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void updateAuditOverTime() {
        List<Order> orders = orderMapper.getAuditOverTimeOrders();
        for (Order order : orders) {
            order.setStatus(-3);
            orderMapper.updateById(order);

            // 新增账单-退款
            OrderBill orderBill = new OrderBill();
            orderBill.setOrderId(order.getId());
            orderBill.setUserId(order.getUserId());
            orderBill.setMoney(order.getTotalAmount());
            orderBill.setType("refund");
            orderBill.setCreateTime(new Date());
            billService.insertOrderBill(orderBill);

            // 预定失败，删除预定记录
            LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Record::getOrderId, order.getId());
            recordService.remove(queryWrapper);
        }
    }
}
