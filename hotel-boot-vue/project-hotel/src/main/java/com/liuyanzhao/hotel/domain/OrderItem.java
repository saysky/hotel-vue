package com.liuyanzhao.hotel.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liuyanzhao.hotel.domain.common.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 订单明细对象 t_order_item
 *
 * @author saysky
 * @date 2024-01-15
 */
@Data
@TableName("t_order_item")
public class OrderItem extends CommonEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 房间ID
     */
    private Long roomId;

    /**
     * 房间名称
     */
    private String roomTitle;

    /**
     * 房间号
     */
    private String roomNo;

    /**
     * 房间缩略图
     */
    private String roomThumbnail;

    /**
     * 房间价格
     */
    private BigDecimal roomPrice;

    /**
     * 是否已评价
     */
    private Integer hasComment;

}
