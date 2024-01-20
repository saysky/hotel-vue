package com.liuyanzhao.hotel.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liuyanzhao.common.core.domain.entity.SysUser;
import com.liuyanzhao.hotel.domain.common.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 账单对象 t_order_bill
 * 
 * @author saysky
 * @date 2024-01-17
 */
@Data
@TableName("t_order_bill")
public class OrderBill extends CommonEntity
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 订单id */
    private Long orderId;

    /** 金额 */
    private BigDecimal money;

    /** 类型 */
    private String type;

    @TableField(exist = false)
    private Order order;

    @TableField(exist = false)
    private SysUser user;
}
