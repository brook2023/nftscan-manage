import request from '@/utils/request'
// 查询api平台用户列表
export function listApiUser(query) {
  return request({
    url: '/system/apiUser/list',
    method: 'get',
    params: query
  })
}

// 查询api平台用户详细
export function getApiUser(id) {
  return request({
    url: '/system/apiUser/' + id,
    method: 'get'
  })
}

// 新增api平台用户
export function addApiUser(data) {
  return request({
    url: '/system/apiUser',
    method: 'post',
    data: data
  })
}

// 修改api平台用户
export function updateApiUser(data) {
  return request({
    url: '/system/apiUser',
    method: 'put',
    data: data
  })
}

// 删除api平台用户
export function delApiUser(id) {
  return request({
    url: '/system/apiUser/' + id,
    method: 'delete'
  })
}
