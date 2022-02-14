<template>
  <div>
    <div class="joyin-unifybg"></div>
    <div class="joyin-unify-main">
      <div class="joyin-unify-header">
        <div class=""><img src="../../assets/public/images/joyin-logo.png"></div>
        <div class="layui-nav layui-userinfo">
          <div class="layui-nav-item">
            <el-dropdown class="avatar-container"
                         trigger="click">
              <div class="avatar-wrapper">
                <img src="../../assets/public/images/avatar-lady.png"
                     class="user-avatar layui-nav-img">
                <span style="color: #fff">{{realname}}</span>
                <i style="color: #fff"
                   class="el-icon-caret-bottom" />
              </div>
              <el-dropdown-menu slot="dropdown"
                                class="user-dropdown">
                <!-- <el-dropdown-item>
                <span style="display:block;">密码修改</span>
              </el-dropdown-item> -->
                <el-dropdown-item @click.native="logout">
                  <span style="display:block;">退出</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>

      <div class="joyin-unify-body">
        <div class="joyin-unify-new-list"
             :class="platList.length>3?'twoLine':'oneLine'">
          <div class="joyin-unify-item-box"
               v-for="(item,index) in allData"
               :key='index'>
            <div class="joyin-unify-item"
                 @click="See(obj.id, obj.cookieKey)"
                 v-for="(obj,key) in item.row"
                 :key="key"
                 :style="obj.platformName?'':'visibility: hidden;'">
              <div class="top-box">
                <h4>{{ obj.platformName }}</h4>
                <p>{{obj.describe}}</p>
                <p>立即进入</p>
              </div>
              <div class="joyin-unify-hd">
                <img v-bind:src="obj.img">
              </div>
              <p>{{obj.platformName}}</p>
            </div>
          </div>
          <!-- <ul> -->
          <!-- <li class="curr-this" >
            <a href="javascript:void(0);" @click="See()">
              <div class="joyin-unify-hd">
                <img src="../../assets/public/images/fp-logo.png">
                <span>统一身份认证</span>
              </div>
              <div class="joyin-unify-bd">
                <h4>统一身份认证</h4>
                <p>描述简介描述简介描述简介描述简介描述简介描述简介描述简介描述简介描述简介描述简介</p>
                <p>立即进入</p>
              </div>
            </a>
          </li> -->
          <!-- <li v-for="(item, index) in platList"
                :key="index"
                class="plat-list-item">
              <a v-if="item.id"
                 href="javascript:void(0);"
                 @click="See(item.id, item.cookieKey)">
                <div class="joyin-unify-hd"> -->
          <!-- <img src="../../assets/public/images/fp-logo.png"> -->
          <!-- <img v-bind:src="imgUrl[index].src">
                </div>
                <span>{{ item.platformName }}</span>
                <div class="joyin-unify-bd">
                  <h4>{{ item.platformName }}</h4>
                  <p>{{item.describe}}</p>
                  <p>立即进入</p>
                </div>
              </a>
              <a v-else="!item.id"
                 class="item-empty"></a>
            </li>
          </ul> -->

        </div>
        <div class="joyin-sltsys">
          <img src="../../assets/public/images/system-select-pic.png">
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import Cookies from 'js-cookie'
import axios from 'axios'
import { getPlatformList } from '@/api/dashboard'
axios.defaults.withCredentials = true
// axios.defaults.baseURL= window.BASE_URL
export default {
  name: 'Dashboard',
  components: {},
  data() {
    return {
      isDraging: false,
      list: [],
      imgUrl: [
        {
          src: require('@/assets/public/images/fp-logo.png'),
        },
        {
          src: require('@/assets/public/images/Ip-logo.png'),
        },
        {
          src: require('@/assets/public/images/dsp-logo.png'),
        },
        {
          src: require('@/assets/public/images/fp-logo.png'),
        },
        {
          src: require('@/assets/public/images/va-logo.png'),
        },
        {
          src: require('@/assets/public/images/Id-logo.png'),
        },
      ],
      permitdetail: {
        add: { name: '新增' },
        edit: { name: '编辑' },
        delete: { name: '删除' },
        export: { name: '导出' },
      },
      itemData: {},
      modalType: '',
      modalTypeName: '',
      loading: false,
      isShowUpdateModal: false,
      platList: [],
      userName: '',
      realname: '',
      allData: [],
    }
  },
  computed: {},
  created() {
    this.getPlatformList()
  },
  mounted() {
    // this.$store.dispatch("data/getHelpFlag")
  },
  methods: {
    async logout() {
      axios.post('sso/auth/ssoLoginOut').then((res) => {
        if (res.data.code == 'success') {
          this.$router.push('/login')
        }
      })
    },

    getPlatformList() {
      this.userName = Cookies.get('username')
      this.realname = Cookies.get('realname')
      getPlatformList({ userName: this.userName }).then((res) => {
        let sortList = res.data.sort(
          (a, b) => Number(a.weight) - Number(b.weight)
        )
        let remain = 6 - sortList.length
        if (sortList.length === 0) {
          return
        }
        let emptyObj = {}
        if (remain > 0) {
          this.platList = [...sortList, ...new Array(remain).fill(emptyObj)]
        } else {
          let v = 4 - parseInt(Math.abs(remain) % 4)
          this.platList = [...sortList, ...new Array(v).fill(emptyObj)]
        }
        this.returnData()
      })
    },
    See(id, cookieKey) {
      if (id && cookieKey) {
        // var userName = Cookies.get(cookieKey)
        // console.log(userName)
        // window.open('http://192.168.10.108:1666/dsp' + '/sso/redirect?id='+ id);
        // console.log(window.WINDOW_BASE_URL,id)
        // window.location.href = 'http://192.168.90.208:1666/dsp' + '/sso/redirect?id='+ id
        // window.location.href = axios.get('sso/redirect?id='+ id).then()
        // window.open(

        axios.post('/joyinsso/sso/auth/redirect?id=' + id).then((res) => {
          if (res.data.code == '10007' || res.data.code == '10006') {
            this.$alert(res.data.message, '提示', {
              confirmButtonText: '确定',
              callback: (action) => {
                axios.post('sso/auth/ssoLoginOut').then((resitem) => {
                  //  if(resitem.data.code == 'success'){
                  this.$router.push('/login')
                  //  }
                })
              },
            })
          } else {
            window.location.href = res.data.data
          }

          // this.platList = res.data.data
        })

        // window.location.href = '/dsp/sso/auth/redirect?id='+ id;
        // window.location.href = axios.get('sso/auth/redirect?id='+ id).then((res) =>{
        //   console.log(res)
        //   if(res.data.code == '10007' || res.data.code == '10006'){
        //     this.$alert(res.data.message, '提示', {
        //       confirmButtonText: '确定',
        //       callback: action => {
        //         axios.post('sso/auth/ssoLoginOut').then((resitem) =>{
        //           //  if(resitem.data.code == 'success'){
        //             this.$router.push('/login')
        //           //  }
        //         })
        //       }
        //     });
        //   }
        // })
        // )
      } else {
        this.$router.push({ path: '/applicaManage' })
      }
    },
    returnData() {
      this.platList = this.platList.map((item, index) => {
        return { ...item, img: this.imgUrl[index].src }
      })
      if (this.platList.length > 3) {
        if (this.platList.length === 4) {
          const a = this.platList.length
          const b = a / 2
          for (var i = 0; i < b; i++) {
            const obj = { row: this.platList.slice(i * 2, i * 2 + 2) }
            this.allData.push(obj)
          }
        } else if (this.platList.length === 5) {
          this.platList.push({
            platformName: '',
            img: '',
          })
          this.returnData()
        } else {
          const a = this.platList.length
          const b = a / 3
          for (var i = 0; i < b; i++) {
            const obj = { row: this.platList.slice(i * 3, i * 3 + 3) }
            this.allData.push(obj)
          }
        }
      } else {
        const obj = { row: this.platList }
        this.allData.push(obj)
      }
    },
  },
}
</script>

<style lang="scss">
@media screen and (min-width: 1366px) {
  .joyin-unify-new-list {
    width: 1360px;
    margin: 0 auto;
  }
  .oneLine {
    margin-top: 160px;
    .joyin-unify-item {
      width: 280px;
      height: 400px;
      padding-top: 110px;
      box-sizing: border-box;
      margin-bottom: 60px;
    }
  }
  .twoLine {
    margin-top: 60px;
    .joyin-unify-item {
      width: 280px;
      height: 240px;
      padding-top: 30px;
      box-sizing: border-box;
      margin-bottom: 60px;
    }
  }
}
@media screen and (max-width: 1366px) {
  .joyin-unify-new-list {
    width: 1200px;
    margin: 0 auto;
  }
  .oneLine {
    margin-top: 120px;
    .joyin-unify-item {
      width: 240px;
      height: 320px;
      padding-top: 80px;
      box-sizing: border-box;
      margin-bottom: 30px;
    }
  }
  .twoLine {
    margin-top: 40px;
    .joyin-unify-item {
      width: 240px;
      height: 200px;
      padding-top: 40px;
      box-sizing: border-box;
      margin-bottom: 30px;
    }
  }
  .joyin-sltsys {
    padding-top: 0 !important;
  }
}
.joyin-unify-item-box {
  width: 100%;
  display: flex;
  align-content: center;
  justify-content: center;
  box-sizing: border-box;
  .joyin-unify-item {
    border-radius: 5px;
    margin-right: 110px;
    position: relative;
    background: linear-gradient(
      180deg,
      rgba(0, 0, 0, 0.2),
      rgba(255, 255, 255, 0.2),
      rgba(255, 255, 255, 0.4)
    );
    box-shadow: 0 1px 8px rgba(134, 106, 106, 0.3);
    cursor: pointer;
    p {
      font-size: 24px;
      color: #fff;
      height: auto;
      line-height: 1;
      text-align: center;
    }
  }
  .top-box {
    width: 100%;
    height: 100%;
    position: absolute;
    z-index: 10;
    color: #fff;
    text-align: left;
    background: rgba(43, 88, 244, 0.45);
    top: 0;
    left: 0;
    font-size: 14px;
    display: none;
    p {
      font-size: 14px;
      margin-top: 10px;
    }
  }
  .joyin-unify-item:last-child {
    margin-right: 0;
  }
  .joyin-unify-item:hover {
    .top-box {
      display: block;
    }
  }
}
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background 0.3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 14px;
        }
      }
    }
  }
}
.user-dropdown {
  top: 50px !important;
}
.dashboard {
  padding: 20px;
  .el-card {
    width: 240px;
    height: 180px;
    // display: inline-flex;
    margin: 0 20px 20px 0;
    cursor: pointer;
  }
  .el-card:hover {
    transform: scale(1.1, 1.1);
  }
  .group-list {
    text-align: center;
    display: flex;
    flex-direction: column;
    flex-flow: wrap;
    justify-content: center;
  }
  .header {
    display: flex;
    & > div {
      margin-left: 10px;
      width: 100%;
    }
    .title {
      padding-top: 4px;
      font-size: 14px;
      font-weight: bold;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      width: 100%;
    }
    .desc {
      color: #999;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
      width: 100%;
    }
  }
  .content {
    margin-top: 20px;
    color: #888;
    font-size: 13px;
    overflow: hidden;
    white-space: nowrap;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
    overflow: hidden;
    text-align: right;
    white-space: nowrap;
    .el-link {
      padding: 0 0 0 7px !important;
    }
  }

  .image {
    width: 50px;
    height: 50px;
    display: inline-block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: '';
  }

  .clearfix:after {
    clear: both;
  }
}
</style>
