package com.liuyanzhao.hotel.controller.portal;

import com.liuyanzhao.common.annotation.Log;
import com.liuyanzhao.common.core.controller.BaseController;
import com.liuyanzhao.common.core.domain.AjaxResult;
import com.liuyanzhao.common.core.page.TableDataInfo;
import com.liuyanzhao.common.enums.BusinessType;
import com.liuyanzhao.hotel.domain.RoomCategory;
import com.liuyanzhao.hotel.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客房类型Controller
 * 
 * @author saysky
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/hotel/portal/roomCategory")
public class PortalRoomCategoryController extends BaseController
{
    @Autowired
    private RoomCategoryService roomCategoryService;

    /**
     * 查询客房类型列表
     */
    @GetMapping("/list")
    public TableDataInfo list(RoomCategory roomCategory)
    {
        startPage();
        List<RoomCategory> list = roomCategoryService.selectRoomCategoryList(roomCategory);
        return getDataTable(list);
    }

}
