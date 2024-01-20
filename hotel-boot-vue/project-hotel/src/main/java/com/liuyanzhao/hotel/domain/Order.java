package com.liuyanzhao.hotel.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.liuyanzhao.common.core.domain.entity.SysUser;
import com.liuyanzhao.hotel.domain.common.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 订单对象 t_order
 *
 * @author saysky
 * @date 2024-01-15
 */
@Data
@TableName("t_order")
public class Order extends CommonEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 入住日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 退房日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 天数
     */
    private Integer dayNum;

    /**
     * 订单状态 0待支付 1待接单 2预定成功 -1已取消   -2支付超时，-3商家未接单
     */
    private Integer status;


    /**
     * 是否已评价
     */
    private Integer hasComment;

    @TableField(exist = false)
    private SysUser user;


    @TableField(exist = false)
    private List<Long> roomIds;


    @TableField(exist = false)
    private List<OrderItem> orderItemList;




}
