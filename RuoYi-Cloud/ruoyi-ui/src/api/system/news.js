import request from '@/utils/request'

// 查询news列表
export function listNews(query) {
  return request({
    url: '/system/news/list',
    method: 'get',
    params: query
  })
}

// 查询news详细
export function getNews(id) {
  return request({
    url: '/system/news/' + id,
    method: 'get'
  })
}

// 新增news
export function addNews(data) {
  return request({
    url: '/system/news',
    method: 'post',
    data: data
  })
}

// 修改news
export function updateNews(data) {
  return request({
    url: '/system/news',
    method: 'put',
    data: data
  })
}

// 删除news
export function delNews(id) {
  return request({
    url: '/system/news/' + id,
    method: 'delete'
  })
}
