package com.liuyanzhao.hotel.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyanzhao.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuyanzhao.hotel.mapper.RoomCategoryMapper;
import com.liuyanzhao.hotel.domain.RoomCategory;
import com.liuyanzhao.hotel.service.RoomCategoryService;

/**
 * 客房类型Service业务层处理
 * 
 * @author saysky
 * @date 2024-01-15
 */
@Service
public class RoomCategoryServiceImpl extends ServiceImpl<RoomCategoryMapper, RoomCategory> implements RoomCategoryService
{
    @Autowired
    private RoomCategoryMapper roomCategoryMapper;

    /**
     * 查询客房类型
     * 
     * @param id 客房类型主键
     * @return 客房类型
     */
    @Override
    public RoomCategory selectRoomCategoryById(Long id)
    {
        return roomCategoryMapper.selectRoomCategoryById(id);
    }

    /**
     * 查询客房类型列表
     * 
     * @param roomCategory 客房类型
     * @return 客房类型
     */
    @Override
    public List<RoomCategory> selectRoomCategoryList(RoomCategory roomCategory)
    {
        return roomCategoryMapper.selectRoomCategoryList(roomCategory);
    }

    /**
     * 新增客房类型
     * 
     * @param roomCategory 客房类型
     * @return 结果
     */
    @Override
    public int insertRoomCategory(RoomCategory roomCategory)
    {
        roomCategory.setCreateTime(DateUtils.getNowDate());
        return roomCategoryMapper.insertRoomCategory(roomCategory);
    }

    /**
     * 修改客房类型
     * 
     * @param roomCategory 客房类型
     * @return 结果
     */
    @Override
    public int updateRoomCategory(RoomCategory roomCategory)
    {
        return roomCategoryMapper.updateRoomCategory(roomCategory);
    }

    /**
     * 批量删除客房类型
     * 
     * @param ids 需要删除的客房类型主键
     * @return 结果
     */
    @Override
    public int deleteRoomCategoryByIds(Long[] ids)
    {
        return roomCategoryMapper.deleteRoomCategoryByIds(ids);
    }

    /**
     * 删除客房类型信息
     * 
     * @param id 客房类型主键
     * @return 结果
     */
    @Override
    public int deleteRoomCategoryById(Long id)
    {
        return roomCategoryMapper.deleteRoomCategoryById(id);
    }
}
