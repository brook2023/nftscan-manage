import request from '@/utils/request'

// 查询tradeContract列表
export function listTradeContract(query) {
  return request({
    url: '/system/tradeContract/list',
    method: 'get',
    params: query
  })
}

// 查询tradeContract详细
export function getTradeContract(contract) {
  return request({
    url: '/system/tradeContract/' + contract,
    method: 'get'
  })
}

// 新增tradeContract
export function addTradeContract(data) {
  return request({
    url: '/system/tradeContract',
    method: 'post',
    data: data
  })
}

// 修改tradeContract
export function updateTradeContract(data) {
  return request({
    url: '/system/tradeContract',
    method: 'put',
    data: data
  })
}

// 删除tradeContract
export function delTradeContract(contract) {
  return request({
    url: '/system/tradeContract/' + contract,
    method: 'delete'
  })
}
