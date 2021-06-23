import request from '@/utils/request'

// 查询岗位列表
export function listTag(query) {
  return request({
    url: '/system/tag/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getTag(tagId) {
  return request({
    url: '/system/tag/' + tagId,
    method: 'get'
  })
}

// 新增岗位
export function addTag(data) {
  return request({
    url: '/system/tag',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updateTag(data) {
  return request({
    url: '/system/tag',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delTag(tagId) {
  return request({
    url: '/system/tag/' + tagId,
    method: 'delete'
  })
}
