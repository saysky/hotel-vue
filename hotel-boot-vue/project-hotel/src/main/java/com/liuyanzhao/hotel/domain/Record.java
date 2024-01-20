package com.liuyanzhao.hotel.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.liuyanzhao.hotel.domain.common.CommonEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 预定记录对象 t_record
 *
 * @author saysky
 * @date 2024-01-18
 */
@Data
@TableName("t_record")
public class Record extends CommonEntity {
    private static final long serialVersionUID = 1L;


    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 房间ID
     */
    private Long roomId;

    /**
     * 日期
     */
    private String recordDate;

    private Long orderId;

}
