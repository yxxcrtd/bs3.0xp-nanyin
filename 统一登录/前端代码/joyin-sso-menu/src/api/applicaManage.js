import request from '@/utils/request'

export function pageSsoPlatform(params) {
  return request({
    url: '/sso/ssoplatform/pageSsoPlatform',
    method: 'post',
    params
  })
}

export function insert(params) {
    return request({
      url: '/sso/ssoplatform/insert',
      method: 'post',
      params
    })
}

export function deleteDate(params) {
    return request({
      url: '/sso/ssoplatform/delete',
      method: 'post',
      params
    })
}
