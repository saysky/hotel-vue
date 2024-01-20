import request from '@/utils/request'

// 查询账单列表
export function listOrderBill(query) {
  return request({
    url: '/hotel/orderBill/list',
    method: 'get',
    params: query
  })
}

// 查询账单详细
export function getOrderBill(id) {
  return request({
    url: '/hotel/orderBill/' + id,
    method: 'get'
  })
}

// 新增账单
export function addOrderBill(data) {
  return request({
    url: '/hotel/orderBill',
    method: 'post',
    data: data
  })
}

// 修改账单
export function updateOrderBill(data) {
  return request({
    url: '/hotel/orderBill',
    method: 'put',
    data: data
  })
}

// 删除账单
export function delOrderBill(id) {
  return request({
    url: '/hotel/orderBill/' + id,
    method: 'delete'
  })
}
