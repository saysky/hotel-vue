package com.liuyanzhao.hotel.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyanzhao.hotel.domain.Record;
import org.apache.ibatis.annotations.Param;

/**
 * 预定记录Mapper接口
 * 
 * @author saysky
 * @date 2024-01-18
 */
public interface RecordMapper extends BaseMapper<Record>
{
    /**
     * 查询预定记录
     * 
     * @param id 预定记录主键
     * @return 预定记录
     */
    Record selectRecordById(Long id);

    /**
     * 查询预定记录列表
     * 
     * @param record 预定记录
     * @return 预定记录集合
     */
    List<Record> selectRecordList(Record record);

    /**
     * 新增预定记录
     * 
     * @param record 预定记录
     * @return 结果
     */
    int insertRecord(Record record);

    /**
     * 修改预定记录
     * 
     * @param record 预定记录
     * @return 结果
     */
    int updateRecord(Record record);

    /**
     * 删除预定记录
     * 
     * @param id 预定记录主键
     * @return 结果
     */
    int deleteRecordById(Long id);

    /**
     * 批量删除预定记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRecordByIds(Long[] ids);

    /**
     * 根据房间ID和日期列表查询预定
     *
     * @param roomId
     * @param dateList
     * @return
     */
    List<Record> findByRoomIdAndRecordDate(@Param("roomId") Long roomId,
                                           @Param("list") List<String> dateList);

}
