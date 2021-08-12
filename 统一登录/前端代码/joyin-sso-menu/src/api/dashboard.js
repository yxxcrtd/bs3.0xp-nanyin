import request from '@/utils/request'

export function getPlatformList() {
  return request({
    url: '/sso/getPlatformList',
    method: 'post'
  })
}

export function getRedirect(params) {
  return request({
    url: '/sso/redirect',
    method: 'post',
    params
  })
}
