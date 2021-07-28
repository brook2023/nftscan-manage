import request from '@/utils/request'

// 查询热点推荐列表
export function listHot(query) {
  return request({
    url: '/system/hot/list',
    method: 'get',
    params: query
  })
}

// 查询热点推荐详细
export function getHot(id) {
  return request({
    url: '/system/hot/' + id,
    method: 'get'
  })
}

// 新增热点推荐
export function addHot(data) {
  return request({
    url: '/system/hot',
    method: 'post',
    data: data
  })
}

// 修改热点推荐
export function updateHot(data) {
  return request({
    url: '/system/hot',
    method: 'put',
    data: data
  })
}

// 删除热点推荐
export function delHot(id) {
  return request({
    url: '/system/hot/' + id,
    method: 'delete'
  })
}
