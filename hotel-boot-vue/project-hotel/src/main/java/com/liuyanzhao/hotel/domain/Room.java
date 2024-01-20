package com.liuyanzhao.hotel.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liuyanzhao.hotel.domain.common.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 房间对象 t_room
 * 
 * @author saysky
 * @date 2024-01-15
 */
@Data
@TableName("t_room")
public class Room extends CommonEntity
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 客房分类 */
    private Long categoryId;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;
    private String summary;

    /** 房间号 */
    private String roomNo;

    /** 价格 */
    private BigDecimal price;

    /** 缩略图 */
    private String thumbnail;

    /** 轮播图 */
    private String slideUrl;

    /** 1已上架,0已下架 */
    private Integer status;

    private Double starLevel;

    @TableField(exist = false)
    private RoomCategory roomCategory;

}
