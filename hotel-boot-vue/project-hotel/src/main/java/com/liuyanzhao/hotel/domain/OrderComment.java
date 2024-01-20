package com.liuyanzhao.hotel.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liuyanzhao.hotel.domain.common.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 订单评价对象 t_order_comment
 * 
 * @author saysky
 * @date 2024-01-15
 */
@Data
@TableName("t_order_comment")
public class OrderComment extends CommonEntity
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 内容 */
    private String content;

    /** 订单ID */
    private Long orderId;

    private Long userId;

    /** 订单明细ID */
    private Long orderItemId;

    /** 星级 */
    private Integer starLevel;

    private Long roomId;

    @TableField(exist = false)
    private Order order;

}
