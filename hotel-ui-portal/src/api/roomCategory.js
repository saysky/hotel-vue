import request from '@/utils/request'

// 查询客房类型列表
export function listRoomCategory(query) {
  return request({
    url: '/hotel/portal/roomCategory/list',
    method: 'get',
    params: query
  })
}
// 查询客房类型详细
export function getRoomCategory(id) {
  return request({
    url: '/hotel/portal/roomCategory/' + id,
    method: 'get'
  })
}
