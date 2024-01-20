package com.liuyanzhao.hotel.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.liuyanzhao.hotel.domain.common.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 客房类型对象 t_room_category
 * 
 * @author saysky
 * @date 2024-01-15
 */
@Data
@TableName("t_room_category")
public class RoomCategory extends CommonEntity
{
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 分类名称 */
    private String name;

}
