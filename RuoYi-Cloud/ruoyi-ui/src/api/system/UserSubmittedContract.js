import request from '@/utils/request'

// 查询NFT项目信息提交列表
export function listUserSubmittedContract(query) {
  return request({
    url: '/system/UserSubmittedContract/list',
    method: 'get',
    params: query
  })
}

// 查询NFT项目信息提交详细
export function getUserSubmittedContract(id) {
  return request({
    url: '/system/UserSubmittedContract/' + id,
    method: 'get'
  })
}

// 新增NFT项目信息提交
export function addUserSubmittedContract(data) {
  return request({
    url: '/system/UserSubmittedContract',
    method: 'post',
    data: data
  })
}

// 修改NFT项目信息提交
export function updateUserSubmittedContract(data) {
  return request({
    url: '/system/UserSubmittedContract',
    method: 'put',
    data: data
  })
}

// 删除NFT项目信息提交
export function delUserSubmittedContract(id) {
  return request({
    url: '/system/UserSubmittedContract/' + id,
    method: 'delete'
  })
}
