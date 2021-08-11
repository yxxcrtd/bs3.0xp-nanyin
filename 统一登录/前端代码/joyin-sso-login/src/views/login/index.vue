<template>
  <div class="joyin-ulogin joyin-loginbg0">
    <div class="joyin-ulogin-top"><img src="../../assets/public/images/joyin-logo.png"></div>
      <div class="joyin-ulogin-main">
        <div class="joyin-ulogin-left">
          <img src="../../assets/public/images/joyin-pic.png">
        </div>
        <div class="joyin-ulogin-right">
          <div class="joyin-ulogin-box">
            <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="layui-form login-form" label-position="left">
            <div class="joyin-ulogin-header">
              <div class="joyin-ulogin-logo">
                <img src="../../assets/public/images/fp-logo.png">
                <h3>统一身份认证</h3>
              </div>
              <div class="joyin-ulogin-tips">
                <p class="joyin-hide">上午好，欢迎您使用系统！</p>
                <p class="joyin-show">{{ loginErrorMsg }}</p>
              </div>
            </div>
            <div class="joyin-ulogin-body">
              <!-- <div class="joyin-form-item">
                <label class="joyin-ulogin-icon jyicon-user" for="login-username"></label>
                <input type="text" name="username" id="login-username" lay-verify="required" placeholder="用户名或手机号" class="joyin-input">
              </div> -->
              <el-form-item
                class="joyin-form-item"
                prop="username">
                <span class="joyin-ulogin-icon jyicon-user"
                ></span>
                <el-input
                  ref="username"
                  class="joyin-input"
                  v-model="loginForm.username"
                  placeholder="用户名或手机号"
                  name="username"
                  type="text"
                  tabindex="1"
                  auto-complete="on"
                />
              </el-form-item>
            <el-form-item
              class="joyin-form-item"
              prop="password">
              <span class="joyin-ulogin-icon jyicon-pass"
              ></span>
              <el-input
                id="login-password"
                :key="passwordType"
                ref="password"
                class="joyin-input"
                v-model="loginForm.password"
                :type="passwordType"
                placeholder="密码"
                name="password"
                tabindex="2"
                auto-complete="on"
                show-password
                @keyup.enter.native="handleLogin"
              />
<!--              <span class="show-pwd" @click="showPwd">
                <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
              </span>-->
            </el-form-item>
              <div class="joyin-form-item">
                <el-button :loading="loading" class="joyin-btn joyin-btn-fluid" lay-filter="login-submit" type="primary" @click.native.prevent="handleLogin">登 录</el-button>
              </div>
            </div>
            </el-form>
          </div>
        </div>
      </div>
    <div class="joyin-ulogin-footer">
      <p>2021~2025 &copy; <a href="http://wwww.joyintech.com" target="_blank">兆尹科技</a>版权所有. All Rights Reserved.</p>
      <p>技术支持：<a href="http://www.joyintech.com/contact/index.html" target="_blank">安徽兆尹信息科技股份有限公司</a></p>
    </div>
  </div>
</template>

<script>
import {setToken} from '@/utils/auth'
import {Encrypt, Decrypt} from '@/utils/encryption'
export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', message: '请输入用户名或手机号' }],
        password: [{ required: true, trigger: 'blur', message: '请输入密码'}]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      loginErrorMsg: '',
      loginErrorTimeout: null
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          let { userName, password} = this.loginForm;
          // this.loginForm.username = Encrypt(userName);
          this.loginForm.password = Encrypt(password);
          this.$store.dispatch('user/login', this.loginForm).then((res) => {
            this.loading = false
            if (res.code !== 'success') {
              this.loginErrorMsg = res.message || 'Error'
              this.loginErrorTimeout && clearTimeout(this.loginErrorTimeout);
              this.loginErrorTimeout = setTimeout(e => {
                this.loginErrorMsg = '';
              },5000)
              return
            }
            // this.$store.commit('user/SET_TOKEN', userName)
            // setToken(userName)
            this.$router.push({ path: '/dashboard' })

          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.svg-container {
  position: absolute;
  top: 0px;
  left: 10px;
  z-index: 11;
}
.show-pwd {
  position: absolute;
  right: 10px;
  // top: 7px;
  font-size: 16px;
  color: $dark_gray;
  cursor: pointer;
  user-select: none;
  z-index: 2;
}
</style>
