import request from '@/utils/request'

// 查询岗位列表
export function listPlatform(query) {
  return request({
    url: '/system/platform/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getPlatform(platformId) {
  return request({
    url: '/system/platform/' + platformId,
    method: 'get'
  })
}

// 新增岗位
export function addPlatform(data) {
  return request({
    url: '/system/platform',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updatePlatform(data) {
  return request({
    url: '/system/platform',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delPlatform(platformId) {
  return request({
    url: '/system/platform/' + platformId,
    method: 'delete'
  })
}
