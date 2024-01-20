import request from '@/utils/request'

// 查询房间列表
export function listRoom(query) {
  return request({
    url: '/hotel/portal/room/list',
    method: 'get',
    params: query
  })
}

// 查询房间详细
export function getRoom(id) {
  return request({
    url: '/hotel/portal/room/' + id,
    method: 'get'
  })
}
