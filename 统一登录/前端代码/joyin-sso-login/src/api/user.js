import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/sso/auth/ssoLogin',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/joyin-sso-login/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/joyin-sso-login/user/logout',
    method: 'post'
  })
}
