import request from '@/utils/request'

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

// 新增订单
export function payOrder(data) {
  return request({
    url: '/hotel/order/pay',
    method: 'post',
    data: data
  })
}
