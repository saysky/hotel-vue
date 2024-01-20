package com.liuyanzhao.hotel.controller.portal;

import com.liuyanzhao.common.annotation.Log;
import com.liuyanzhao.common.core.controller.BaseController;
import com.liuyanzhao.common.core.domain.AjaxResult;
import com.liuyanzhao.common.core.page.TableDataInfo;
import com.liuyanzhao.common.enums.BusinessType;
import com.liuyanzhao.hotel.domain.OrderComment;
import com.liuyanzhao.hotel.service.OrderCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单评价Controller
 *
 * @author saysky
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/hotel/portal/orderComment")
public class PortalOrderCommentController extends BaseController {
    @Autowired
    private OrderCommentService orderCommentService;

    /**
     * 查询订单评价列表
     */
    @PreAuthorize("@ss.hasPermi('hotel:orderComment:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderComment orderComment) {
        startPage();
        List<OrderComment> list = orderCommentService.selectOrderCommentList(orderComment);
        return getDataTable(list);
    }


    /**
     * 新增订单评价
     */
    @PreAuthorize("@ss.hasPermi('hotel:orderComment:add')")
    @Log(title = "订单评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderComment orderComment) {
        return toAjax(orderCommentService.insertOrderComment(orderComment));
    }
}
