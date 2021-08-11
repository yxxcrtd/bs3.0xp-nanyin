import request from '@/utils/request'

export function getPlatformList(data) {
  return request({
    url: '/sso/auth/getPlatformList',
    method: 'post',
    data
  })
}

export function getRedirect(params) {
  return request({
    url: '/sso/redirect',
    method: 'post',
    params
  })
}
