import request from '@/utils/request'

// 查询订单列表
export function listOrder(query) {
  return request({
    url: '/hotel/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getOrder(id) {
  return request({
    url: '/hotel/order/' + id,
    method: 'get'
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/hotel/order',
    method: 'post',
    data: data
  })
}

// 修改订单
export function updateOrder(data) {
  return request({
    url: '/hotel/order',
    method: 'put',
    data: data
  })
}

// 删除订单
export function delOrder(id) {
  return request({
    url: '/hotel/order/' + id,
    method: 'delete'
  })
}


// 审核
export function auditOrder(data) {
  return request({
    url: '/hotel/order/audit',
    method: 'post',
    data: data
  })
}
// 取消
export function cancelOrder(data) {
  return request({
    url: '/hotel/order/cancel',
    method: 'post',
    data: data
  })
}
// 支付
export function payOrder(data) {
  return request({
    url: '/hotel/order/pay',
    method: 'post',
    data: data
  })
}
