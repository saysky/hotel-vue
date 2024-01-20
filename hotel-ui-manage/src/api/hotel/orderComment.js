import request from '@/utils/request'

// 查询订单评价列表
export function listOrderComment(query) {
  return request({
    url: '/hotel/orderComment/list',
    method: 'get',
    params: query
  })
}

// 查询订单评价详细
export function getOrderComment(id) {
  return request({
    url: '/hotel/orderComment/' + id,
    method: 'get'
  })
}

// 新增订单评价
export function addOrderComment(data) {
  return request({
    url: '/hotel/orderComment',
    method: 'post',
    data: data
  })
}

// 修改订单评价
export function updateOrderComment(data) {
  return request({
    url: '/hotel/orderComment',
    method: 'put',
    data: data
  })
}

// 删除订单评价
export function delOrderComment(id) {
  return request({
    url: '/hotel/orderComment/' + id,
    method: 'delete'
  })
}
