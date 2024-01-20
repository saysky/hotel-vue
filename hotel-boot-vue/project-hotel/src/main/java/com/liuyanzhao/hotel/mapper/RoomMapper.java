package com.liuyanzhao.hotel.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyanzhao.hotel.domain.Room;

/**
 * 房间Mapper接口
 * 
 * @author saysky
 * @date 2024-01-15
 */
public interface RoomMapper extends BaseMapper<Room>
{
    /**
     * 查询房间
     * 
     * @param id 房间主键
     * @return 房间
     */
    Room selectRoomById(Long id);

    /**
     * 查询房间列表
     * 
     * @param room 房间
     * @return 房间集合
     */
    List<Room> selectRoomList(Room room);

    /**
     * 新增房间
     * 
     * @param room 房间
     * @return 结果
     */
    int insertRoom(Room room);

    /**
     * 修改房间
     * 
     * @param room 房间
     * @return 结果
     */
    int updateRoom(Room room);

    /**
     * 删除房间
     * 
     * @param id 房间主键
     * @return 结果
     */
    int deleteRoomById(Long id);

    /**
     * 批量删除房间
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRoomByIds(Long[] ids);
}
