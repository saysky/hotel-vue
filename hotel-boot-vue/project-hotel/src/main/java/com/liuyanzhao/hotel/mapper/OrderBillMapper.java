package com.liuyanzhao.hotel.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyanzhao.hotel.domain.OrderBill;

/**
 * 账单Mapper接口
 * 
 * @author saysky
 * @date 2024-01-17
 */
public interface OrderBillMapper extends BaseMapper<OrderBill>
{
    /**
     * 查询账单
     * 
     * @param id 账单主键
     * @return 账单
     */
    OrderBill selectOrderBillById(Long id);

    /**
     * 查询账单列表
     * 
     * @param orderBill 账单
     * @return 账单集合
     */
    List<OrderBill> selectOrderBillList(OrderBill orderBill);

    /**
     * 新增账单
     * 
     * @param orderBill 账单
     * @return 结果
     */
    int insertOrderBill(OrderBill orderBill);

    /**
     * 修改账单
     * 
     * @param orderBill 账单
     * @return 结果
     */
    int updateOrderBill(OrderBill orderBill);

    /**
     * 删除账单
     * 
     * @param id 账单主键
     * @return 结果
     */
    int deleteOrderBillById(Long id);

    /**
     * 批量删除账单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteOrderBillByIds(Long[] ids);
}
