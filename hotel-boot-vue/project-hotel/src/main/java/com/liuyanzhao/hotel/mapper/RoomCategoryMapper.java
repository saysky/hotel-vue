package com.liuyanzhao.hotel.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyanzhao.hotel.domain.RoomCategory;

/**
 * 客房类型Mapper接口
 * 
 * @author saysky
 * @date 2024-01-15
 */
public interface RoomCategoryMapper extends BaseMapper<RoomCategory>
{
    /**
     * 查询客房类型
     * 
     * @param id 客房类型主键
     * @return 客房类型
     */
    RoomCategory selectRoomCategoryById(Long id);

    /**
     * 查询客房类型列表
     * 
     * @param roomCategory 客房类型
     * @return 客房类型集合
     */
    List<RoomCategory> selectRoomCategoryList(RoomCategory roomCategory);

    /**
     * 新增客房类型
     * 
     * @param roomCategory 客房类型
     * @return 结果
     */
    int insertRoomCategory(RoomCategory roomCategory);

    /**
     * 修改客房类型
     * 
     * @param roomCategory 客房类型
     * @return 结果
     */
    int updateRoomCategory(RoomCategory roomCategory);

    /**
     * 删除客房类型
     * 
     * @param id 客房类型主键
     * @return 结果
     */
    int deleteRoomCategoryById(Long id);

    /**
     * 批量删除客房类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRoomCategoryByIds(Long[] ids);
}
