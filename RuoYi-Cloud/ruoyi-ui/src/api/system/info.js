import request from '@/utils/request'

// 查询岗位列表
export function listInfo(query) {
  return request({
    url: '/system/info/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getInfo(infoId) {
  return request({
    url: '/system/info/' + infoId,
    method: 'get'
  })
}

// 新增岗位
export function addInfo(data) {
  return request({
    url: '/system/info',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updateInfo(data) {
  return request({
    url: '/system/info',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delInfo(infoId) {
  return request({
    url: '/system/info/' + infoId,
    method: 'delete'
  })
}
