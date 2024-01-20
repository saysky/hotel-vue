package com.liuyanzhao.hotel.controller.portal;

import com.liuyanzhao.common.core.controller.BaseController;
import com.liuyanzhao.common.core.domain.AjaxResult;
import com.liuyanzhao.common.core.page.TableDataInfo;
import com.liuyanzhao.hotel.domain.Room;
import com.liuyanzhao.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房间Controller
 * 
 * @author saysky
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/hotel/portal/room")
public class PortalRoomController extends BaseController
{
    @Autowired
    private RoomService roomService;

    /**
     * 查询房间列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Room room)
    {
        startPage();
        List<Room> list = roomService.selectRoomList(room);
        return getDataTable(list);
    }


    /**
     * 获取房间详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(roomService.selectRoomById(id));
    }
}
