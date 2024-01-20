import request from '@/utils/request'

// 查询订单评价列表
export function listOrderComment(query) {
  return request({
    url: '/hotel/portal/orderComment/list',
    method: 'get',
    params: query
  })
}

// 新增订单评价
export function addOrderComment(data) {
  return request({
    url: '/hotel/portal/orderComment',
    method: 'post',
    data: data
  })
}
