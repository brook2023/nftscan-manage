import request from '@/utils/request'

// 查询NFTSCAN意见反馈列表
export function listFeedback(query) {
  return request({
    url: '/system/feedback/list',
    method: 'get',
    params: query
  })
}

// 查询NFTSCAN意见反馈详细
export function getFeedback(id) {
  return request({
    url: '/system/feedback/' + id,
    method: 'get'
  })
}

// 新增NFTSCAN意见反馈
export function addFeedback(data) {
  return request({
    url: '/system/feedback',
    method: 'post',
    data: data
  })
}

// 修改NFTSCAN意见反馈
export function updateFeedback(data) {
  return request({
    url: '/system/feedback',
    method: 'put',
    data: data
  })
}

// 删除NFTSCAN意见反馈
export function delFeedback(id) {
  return request({
    url: '/system/feedback/' + id,
    method: 'delete'
  })
}
