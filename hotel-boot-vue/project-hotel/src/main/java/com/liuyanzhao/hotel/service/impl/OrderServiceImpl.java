package com.liuyanzhao.hotel.service.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyanzhao.common.core.domain.entity.SysUser;
import com.liuyanzhao.common.exception.ServiceException;
import com.liuyanzhao.common.utils.DateUtils;
import com.liuyanzhao.hotel.domain.*;
import com.liuyanzhao.hotel.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuyanzhao.hotel.mapper.OrderMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service业务层处理
 *
 * @author saysky
 * @date 2024-01-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private OrderBillService billService;

    @Autowired
    private RecordService recordService;

    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public Order selectOrderById(Long id) {
        Order order = orderMapper.selectOrderById(id);
        if (order == null) {
            return null;
        }
        order.setUser(userService.selectUserById(order.getUserId()));

        LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderItem::getOrderId, order.getId());
        List<OrderItem> orderItems = orderItemService.list(queryWrapper);
        order.setOrderItemList(orderItems);
        return order;
    }

    /**
     * 查询订单列表
     *
     * @param order 订单
     * @return 订单
     */
    @Override
    public List<Order> selectOrderList(Order order) {

        List<Order> orders = orderMapper.selectOrderList(order);
        Map<Long, SysUser> sysUserMap = new HashMap<>();
        for (Order item : orders) {
            if (sysUserMap.containsKey(item.getUserId())) {
                item.setUser(sysUserMap.get(item.getUserId()));
            } else {
                SysUser sysUser = userService.selectUserById(item.getUserId());
                item.setUser(sysUser);
                sysUserMap.put(item.getUserId(), sysUser);
            }
        }
        return orders;
    }

    /**
     * 新增订单
     *
     * @param order 订单
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOrder(Order order) {
        // 查询日期列表
        List<String> dateList = DateUtils.getBetweenDates(order.getStartDate(), order.getEndDate());
        if (CollectionUtils.isEmpty(order.getRoomIds())) {
            throw new ServiceException("请选择客房");
        }
        int dayNums = DateUtils.differentDaysByMillisecond(order.getEndDate(), order.getStartDate());
        if (dayNums < 1 || dayNums > 30) {
            throw new ServiceException("预定日期或天数不合法");
        }
        // 订单明细
        BigDecimal totalAmount = new BigDecimal(0);
        List<OrderItem> orderItemList = new ArrayList<>();
        List<Long> roomIds = order.getRoomIds().stream().distinct().collect(Collectors.toList());
        for (Long roomId : roomIds) {
            Room room = roomService.getById(roomId);
            if (room == null) {
                throw new ServiceException("客房不存在");
            }

            List<Record> recordList = recordService.findByRoomIdAndRecordDate(roomId, dateList);
            if (recordList.size() > 0) {
                throw new ServiceException("房间" + room.getTitle() + "已被预定，请重新选择房间或日期");
            }

            totalAmount = totalAmount.add(room.getPrice());
            OrderItem orderItem = new OrderItem();
            orderItem.setRoomId(roomId);
            orderItem.setRoomNo(room.getRoomNo());
            orderItem.setRoomThumbnail(room.getThumbnail());
            orderItem.setCreateTime(new Date());
            orderItem.setRoomPrice(room.getPrice());
            orderItem.setRoomTitle(room.getTitle());
            orderItem.setHasComment(0);
            orderItemList.add(orderItem);
        }
        // 订单信息
        order.setOrderNo("DD" + this.generateId());
        order.setDayNum(dayNums);
        order.setTotalAmount(totalAmount.multiply(BigDecimal.valueOf(order.getDayNum())));
        order.setStatus(0);
        order.setCreateTime(DateUtils.getNowDate());
        order.setHasComment(0);

        orderMapper.insertOrder(order);

        // 订单明细入库
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(order.getId());
        }
        orderItemService.saveBatch(orderItemList);

        // 新增预定记录
        OrderItem temp = new OrderItem();
        temp.setOrderId(order.getId());
        orderItemList = orderItemService.selectOrderItemList(temp);
        for (OrderItem orderItem : orderItemList) {
            for (String recordDate : dateList) {
                Record record = new Record();
                record.setRoomId(orderItem.getRoomId());
                record.setRecordDate(recordDate);
                record.setCreateTime(new Date());
                record.setOrderId(order.getId());
                recordService.insertRecord(record);
            }
        }
        return 1;
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */
    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(Long[] ids) {
        return orderMapper.deleteOrderByIds(ids);
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id) {
        return orderMapper.deleteOrderById(id);
    }

    @Override
    public void updateHasComment(Long id) {
        LambdaQueryWrapper<OrderItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderItem::getOrderId, id);
        List<OrderItem> orderItemList = orderItemService.list(queryWrapper);
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getHasComment() == 0) {
                return;
            }
        }
        Order order = new Order();
        order.setId(id);
        order.setHasComment(1);
        orderMapper.updateById(order);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payOrder(Order order) {
        Order dbOrder = this.getById(order.getId());
        if (dbOrder == null) {
            throw new ServiceException("订单不存在");
        }
        if (dbOrder.getStatus() != 0) {
            throw new ServiceException("订单状态不正确");
        }

        order.setStatus(1); //1待接单
        order.setPayTime(new Date());
        orderMapper.updateOrder(order);
        // 新增账单
        OrderBill orderBill = new OrderBill();
        orderBill.setOrderId(dbOrder.getId());
        orderBill.setUserId(dbOrder.getUserId());
        orderBill.setMoney(dbOrder.getTotalAmount());
        orderBill.setType("pay");
        orderBill.setCreateTime(new Date());
        billService.insertOrderBill(orderBill);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditOrder(Order order) {
        Order dbOrder = this.getById(order.getId());
        if (dbOrder == null) {
            throw new ServiceException("订单不存在");
        }
        if (dbOrder.getStatus() != 1) {
            throw new ServiceException("订单状态不正确");
        }

        order.setStatus(2); //2审核通过，预定成功
        orderMapper.updateOrder(order);
    }

    /**
     * 生成主键: 年月日+6位自增长码
     * 20240103000001
     *
     * @return
     */
    private Long generateId() {
        String idPrefix = DateUtils.dateTimeNow("yyyyMMdd");
        Order maxOrder = orderMapper.getMaxId();
        // 第一个订单
        if (maxOrder == null) {
            return Long.parseLong(idPrefix + "000001");
        }
        // 最新的订单是今天的
        if (maxOrder.getOrderNo().startsWith("DD" + idPrefix)) {
            Long maxId = Long.valueOf(maxOrder.getOrderNo()
                    .replace("DD", ""));
            return maxId + 1;
        }

        // 最新订单是之前的
        return Long.parseLong(idPrefix + "000001");
    }
}
