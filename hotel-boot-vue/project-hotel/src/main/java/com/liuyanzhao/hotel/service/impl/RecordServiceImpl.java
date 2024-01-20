package com.liuyanzhao.hotel.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyanzhao.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuyanzhao.hotel.mapper.RecordMapper;
import com.liuyanzhao.hotel.domain.Record;
import com.liuyanzhao.hotel.service.RecordService;

/**
 * 预定记录Service业务层处理
 * 
 * @author saysky
 * @date 2024-01-18
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService
{
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 查询预定记录
     * 
     * @param id 预定记录主键
     * @return 预定记录
     */
    @Override
    public Record selectRecordById(Long id)
    {
        return recordMapper.selectRecordById(id);
    }

    /**
     * 查询预定记录列表
     * 
     * @param record 预定记录
     * @return 预定记录
     */
    @Override
    public List<Record> selectRecordList(Record record)
    {
        return recordMapper.selectRecordList(record);
    }

    /**
     * 新增预定记录
     * 
     * @param record 预定记录
     * @return 结果
     */
    @Override
    public int insertRecord(Record record)
    {
        record.setCreateTime(DateUtils.getNowDate());
        return recordMapper.insertRecord(record);
    }

    /**
     * 修改预定记录
     * 
     * @param record 预定记录
     * @return 结果
     */
    @Override
    public int updateRecord(Record record)
    {
        return recordMapper.updateRecord(record);
    }

    /**
     * 批量删除预定记录
     * 
     * @param ids 需要删除的预定记录主键
     * @return 结果
     */
    @Override
    public int deleteRecordByIds(Long[] ids)
    {
        return recordMapper.deleteRecordByIds(ids);
    }

    /**
     * 删除预定记录信息
     * 
     * @param id 预定记录主键
     * @return 结果
     */
    @Override
    public int deleteRecordById(Long id)
    {
        return recordMapper.deleteRecordById(id);
    }


    @Override
    public List<Record> findByRoomIdAndRecordDate(Long postId, List<String> dateList) {
        return recordMapper.findByRoomIdAndRecordDate(postId, dateList);
    }

}
