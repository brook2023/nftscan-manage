import request from '@/utils/request'

// 查询apiCount列表
export function listApiCount(query) {
  return request({
    url: '/system/apiCount/list',
    method: 'get',
    params: query
  })
}

// 查询apiCount详细
export function getApiCount(id) {
  return request({
    url: '/system/apiCount/' + id,
    method: 'get'
  })
}

// 新增apiCount
export function addApiCount(data) {
  return request({
    url: '/system/apiCount',
    method: 'post',
    data: data
  })
}

// 修改apiCount
export function updateApiCount(data) {
  return request({
    url: '/system/apiCount',
    method: 'put',
    data: data
  })
}

// 删除apiCount
export function delApiCount(id) {
  return request({
    url: '/system/apiCount/' + id,
    method: 'delete'
  })
}
