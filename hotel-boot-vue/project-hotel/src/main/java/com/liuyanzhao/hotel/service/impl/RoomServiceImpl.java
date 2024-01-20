package com.liuyanzhao.hotel.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyanzhao.common.core.domain.entity.SysUser;
import com.liuyanzhao.common.exception.ServiceException;
import com.liuyanzhao.common.utils.DateUtils;
import com.liuyanzhao.common.utils.StringUtil;
import com.liuyanzhao.hotel.domain.OrderComment;
import com.liuyanzhao.hotel.domain.RoomCategory;
import com.liuyanzhao.hotel.service.OrderCommentService;
import com.liuyanzhao.hotel.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liuyanzhao.hotel.mapper.RoomMapper;
import com.liuyanzhao.hotel.domain.Room;
import com.liuyanzhao.hotel.service.RoomService;

/**
 * 房间Service业务层处理
 *
 * @author saysky
 * @date 2024-01-15
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomCategoryService roomCategoryService;

    @Autowired
    private OrderCommentService orderCommentService;

    /**
     * 查询房间
     *
     * @param id 房间主键
     * @return 房间
     */
    @Override
    public Room selectRoomById(Long id) {
        return roomMapper.selectRoomById(id);
    }

    /**
     * 查询房间列表
     *
     * @param room 房间
     * @return 房间
     */
    @Override
    public List<Room> selectRoomList(Room room) {
        Map<String, Object> params = room.getParams();
        if (params != null) {
            String beginOrderTime = (String) params.get("beginOrderTime");
            String endOrderTime = (String) params.get("endOrderTime");
            if (StringUtil.isNoneEmpty(beginOrderTime) && StringUtil.isNoneEmpty(endOrderTime)) {
                int dayNums = DateUtils.differentDaysByMillisecond(DateUtils.parseDate(beginOrderTime), DateUtils.parseDate(endOrderTime));
                if (dayNums < 1 || dayNums > 30) {
                    throw new ServiceException("预定日期或天数不合法");
                }

                List<String> dateList = DateUtils.getBetweenDates(beginOrderTime, endOrderTime);
                params.put("excludeDateList", dateList);
            }
        }
        List<Room> rooms = roomMapper.selectRoomList(room);
        Map<Long, RoomCategory> roomCategoryMap = new HashMap<>();
        for (Room item : rooms) {
            if (roomCategoryMap.containsKey(item.getCategoryId())) {
                item.setRoomCategory(roomCategoryMap.get(item.getCategoryId()));
            } else {
                RoomCategory roomCategory = roomCategoryService.getById(item.getCategoryId());
                item.setRoomCategory(roomCategory);
                roomCategoryMap.put(item.getCategoryId(), roomCategory);
            }
        }
        return rooms;
    }

    /**
     * 新增房间
     *
     * @param room 房间
     * @return 结果
     */
    @Override
    public int insertRoom(Room room) {
        String summary = HtmlUtil.cleanHtmlTag(room.getContent());
        if (summary.length() > 50) {
            summary = summary.substring(0, 50);
        }
        room.setSummary(summary);
        room.setCreateTime(DateUtils.getNowDate());
        return roomMapper.insertRoom(room);
    }

    /**
     * 修改房间
     *
     * @param room 房间
     * @return 结果
     */
    @Override
    public int updateRoom(Room room) {
        String summary = HtmlUtil.cleanHtmlTag(room.getContent());
        if (summary.length() > 50) {
            summary = summary.substring(0, 50);
        }
        room.setSummary(summary);
        return roomMapper.updateRoom(room);
    }

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的房间主键
     * @return 结果
     */
    @Override
    public int deleteRoomByIds(Long[] ids) {
        return roomMapper.deleteRoomByIds(ids);
    }

    /**
     * 删除房间信息
     *
     * @param id 房间主键
     * @return 结果
     */
    @Override
    public int deleteRoomById(Long id) {
        return roomMapper.deleteRoomById(id);
    }


    @Override
    public void reComputeStarLevel(Long roomId) {
        LambdaQueryWrapper<OrderComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderComment::getRoomId, roomId);
        List<OrderComment> orderComments = orderCommentService.list(queryWrapper);
        int sum = 0;
        for (OrderComment orderComment : orderComments) {
            sum += orderComment.getStarLevel();
        }
        double starLevel = sum * 1.0 / orderComments.size();
        DecimalFormat df = new DecimalFormat("0.0");
        Room room = new Room();
        room.setId(roomId);
        room.setStarLevel(Double.parseDouble(df.format(starLevel)));
        roomMapper.updateById(room);
    }
}
