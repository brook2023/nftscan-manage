import request from '@/utils/request'

// 查询userContract列表
export function listContract(query) {
  return request({
    url: '/system/contract/list',
    method: 'get',
    params: query
  })
}

// 查询userContract详细
export function getContract(id) {
  return request({
    url: '/system/contract/' + id,
    method: 'get'
  })
}

// 新增userContract
export function addContract(data) {
  return request({
    url: '/system/contract',
    method: 'post',
    data: data
  })
}

// 修改userContract
export function updateContract(data) {
  return request({
    url: '/system/contract',
    method: 'put',
    data: data
  })
}

// 删除userContract
export function delContract(id) {
  return request({
    url: '/system/contract/' + id,
    method: 'delete'
  })
}
