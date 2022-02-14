<template>
  <div class="app-container">
    <div style="width: 100%;">
    <el-form ref="form" :model="form" >
      <el-row class="FormClass">
        <el-col :span="6">
          <el-form-item label="系统名称">
            <el-input v-model="form.platformname" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="状态">
            <el-select v-model="form.status" placeholder="请选择状态" clearable>
              <el-option label="不可用" value="0" />
              <el-option label="可用" value="1" />
            </el-select>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="8" > -->
          <el-button size="small" type="primary"
          style="position: absolute;
                  top: 0;
                  right: 0;"
          @click="pageSsoPlatform" class="onSearch el-button-init" :loading="listLoading" icon="el-icon-search">查询</el-button>
        <!-- </el-col> -->
      </el-row>

    </el-form>

    <el-card :style="contentStyleObj">
      <div slot="header">
        <!-- <span> -->
          <el-button class="el-button-init" type="primary" size="mini" @click="add('new')">+新增</el-button>
        <!-- </span> -->
      </div>
      <div ref="tableCot">
        <el-table
          v-loading="listLoading"
          :data="dataSource"
          element-loading-text="Loading"
          border
          stripe
          style="width: 100%"
          size="mini"
          fit
          highlight-current-row
        >
          <el-table-column prop="platformcode" label="系统编号" min-width="13%">
          </el-table-column>
          <el-table-column prop="platformname" label="系统名称" min-width="16%">
          </el-table-column>
          <el-table-column prop="platformurl" label="系统地址" min-width="43%">
          </el-table-column>
          <el-table-column prop="syncFlag" label="是否实时同步" min-width="9%">
            <template slot-scope="scope">
              <span v-if="scope.row.syncFlag == 0">否</span>
              <span v-if="scope.row.syncFlag == 1">是</span>
            </template>
          </el-table-column>
          <!-- <el-table-column prop="mduser" label="维护人员" >
          </el-table-column>
          <el-table-column prop="mdtime" label="维护时间" >
          </el-table-column> -->
          <el-table-column prop="status" label="状态" min-width="9%">
            <template v-slot="scoped">
              <!-- <el-switch v-model="scoped.row.status"
              active-value="1"
              inactive-value="0"
              active-color="#13ce66"></el-switch> -->
              <el-switch
              class="switchStyle"
              v-model="scoped.row.status"
              :active-value="1"
              :inactive-value="0"
              active-text="可用"
              inactive-text="不可用"
              active-color="green"
              inactive-color="#B9B9B9"
              @change="changeSwitch(scoped.row)"/>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" min-width="11%">
            <template slot-scope="scope">
              <el-button @click="handleEdit(scope.row,'edit')"  type="text" size="small">编辑</el-button>
              <el-button type="text" size="small" @click="handleDel(scope.row)">删除</el-button>
              <el-button type="text" size="small" @click="handleAuto(scope.row)">授权</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="paginationBlock">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[20, 30, 50, 100]"
          :page-size="20"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </el-card>


    <el-dialog
      :title="this.handType == 'new' ? '新增' : '编辑'"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      width="50%"
      :before-close="handleClose">
      <div>
        <el-form ref="addForm" :model="addForm" :rules="rules" label-width="120px" class="FormClass" style="">
          <el-row class="input-half">
            <el-col :span="12" required>
              <el-form-item label="系统编号" prop="platformcode">
                <el-input v-model="addForm.platformcode" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="系统名称" prop="platformname">
                <el-input v-model="addForm.platformname" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="input-half">
            <el-col :span="12">
              <el-form-item label="身份标志" prop="cookieKey">
                <el-input v-model="addForm.cookieKey" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="权重" prop="weight">
                <el-input v-model="addForm.weight" placeholder="值变小时排序相应靠前" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="input-large">
            <el-col :span="24">
              <el-form-item label="应用地址" prop="platformHost">
                <el-input v-model="addForm.platformHost"  placeholder="例如：http://127.0.0.1:8080/ContextPath" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="input-large">
            <el-col :span="24">
              <el-form-item label="应用首页" prop="platformIndex">
                <el-input v-model="addForm.platformIndex"  placeholder="例如：/index.html" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row class="input-switchLabel">
            <el-col>
              <el-form-item label="实时同步" prop="syncFlag">
                <el-switch
                  class="switchStyle"
                  v-model="addForm.syncFlag"
                  :active-value="1"
                  :inactive-value="0"
                  active-text="是"
                  inactive-text="否"
                  active-color="green"
                  inactive-color="#B9B9B9"
                />           
              </el-form-item>
              <el-col>
                <span class="item-label-extra">开启:统一身份认证平台维护用户机构部门信息会实时同步到当前系统。</span>
              </el-col>
            </el-col>
          </el-row>
          <el-row>
            <el-col style="margin-top:30px">
              <el-form-item label="描述" prop="describe">
                <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="addForm.describe" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;clearForm()" size="mini">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading = "submitLoading" size="mini">提 交</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="授权"
      :visible.sync="dialogAuth"
      width="30%"
      :before-close="handleAuthClose"
      class="dialogClass">
      <el-input class="app-container-input" v-model="filterText" @input="filterTextChange" placeholder="请输入名称" size="mini">
        <el-button slot="append" icon="el-icon-search" @click="listDeptAndUser"></el-button>
      </el-input>
      <div class="tree-container" style ="height:250px;overflow:auto;">
        <el-tree
          :data="dialogAuthData"
          show-checkbox
          ref="tree"
          node-key="name"
          :default-expand-all="false"
          :default-checked-keys="defaultShowKeys"
          @check-change="handleNodeClick"
          :props="defaultProps">
        </el-tree>
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogAuth = false" size="mini">取 消</el-button>
        <el-button type="primary" @click="submitAuth"  :loading = "submitLoading" size="mini">提 交</el-button>
      </span>
    </el-dialog>

  </div>
  </div>


</template>

<script>
// import { pageSsoPlatform } from '@/api/applicaManage'
import axios from "axios";
axios.defaults.withCredentials=true;

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      contentStyleObj: {
        height: '',
        'overflow-y':'scroll',
        padding: '0 0 100px 0',
      },
      filterText: '',
      dataSource: [],
      listLoading: false,
      dialogVisible: false,
      dialogAuth: false,
      value5: 100,
      // Height:300,
      dialogAuthData: [],
      defaultProps: {
        children: 'children',
        label: 'realname'
      },
      defaultShowKeys: [],
      defaultNodeKeys: [],

      form: {
        platformname: '',
        status: '',
      },
      addForm: {
        platformcode: '',
        platformname: '',
        platformurl: '',
        weight: '',
        cookieKey: '',
        describe: ''
      },

      rules: {
        platformcode: [
          { required: true, message: '请填写系统编号', trigger: 'blur' }
        ],
        platformname: [
          { required: true, message: '请填写系统名称', trigger: 'blur' }
        ],
        // platformurl: [
        //   { required: true, message: '请填写系统地址', trigger: 'blur' }
        // ],
        weight: [
          { required: true, message: '请填写权重', trigger: 'blur' }
        ],
        cookieKey: [
          { required: true, message: '请填写身份标志', trigger: 'blur' }
        ],
        platformHost: [
          { required: true, message: '请填写平台主机', trigger: 'blur' }
        ],
        platformIndex: [
          { required: true, message: '请填写主页地址', trigger: 'blur' }
        ],
      },
      // page: {
      //   pageNum: 1,
      //   pageSize: 20,
      // },
      pageNum: 1,
      pageSize: 20,

      total: 0,
      currentPage: 1,
      submitLoading: false,
      handType: 'new',
      id: "",
      platformid: ''

      // activeName: '关闭'

    }
  },
  computed: {
    // activeName() {
    //   return this.form.cloudStore === '1' ? '开启' : '关闭'
    // }
  },
  mounted () {
    // const that = this
    // window.onresize = () => {
    //   return (() => {
    //     let heightStyle = window.screen.height
    //     that.Height = heightStyle - 310
    //   })()
    // }
  },
  created() {
    // let that = this
    // let heightStyle = window.screen.height
    // that.Height = heightStyle - 310
    this.getHight()
    window.addEventListener('resize', this.getHight);
    this.pageSsoPlatform()
    this.listDeptAndUser()
    console.log('浏览器高度',document.body.clientWidth)
    //window.innerHeight
  },
  
  destroyed() {
    window.removeEventListener('resize', this.getHight)
  },

  methods: {
    getHight(){
      this.contentStyleObj.height = window.innerHeight  + 'px'
    },
    handleNodeClick(data, checked, indeterminate) {
      if(!data.children){
        if(checked){
          this.defaultNodeKeys.push(data.name)
        }else{
          this.defaultNodeKeys.splice(this.defaultNodeKeys.indexOf(data.name),1);
        }
      }
    },

    filterTextChange(val){
      this.filterText = val
    },

    listDeptAndUser(){
      axios.post('sso/ssouserplatform/listDeptAndUser',{
        realname: this.filterText
      }).then((res) =>{
        if(res.data.code == 'success'){
          this.dialogAuthData = res.data.data
        }
      })
    },

    handleAuto(val){
      this.dialogAuth = true
      this.platformid = val.id
      this.defaultNodeKeys = []

      axios.post('sso/ssouserplatform/listDeptAndUser',{
        name: ''
      }).then((res) =>{
        if(res.data.code == 'success'){
          this.dialogAuthData = res.data.data
          axios.post('sso/ssouserplatform/selectSsoUserListPlatFormById/'+val.id).then((resC) =>{
            this.$nextTick(() =>{
              this.defaultShowKeys = resC.data.data
              this.defaultNodeKeys = resC.data.data
            })
          })

        }
      })
    },

    submitAuth(){
      let arr = []
      let set = new Set
      this.defaultNodeKeys.forEach(element =>
        arr = [...set.add(element)]
      )
      let params = {
        userids: arr,
        platformid: this.platformid
      }

      axios.post('sso/ssouserplatform/grantAuthorizationForPlatForm',params).then((res) =>{
        if(res.data.code == 'success'){
          this.$message.success(res.data.message)
          this.clearFormAuth()
        }else{
          this.$message.error(res.data.message)
        }
      })
      this.filterText = '';
    },

    handleAuthClose(done){
      done();
      this.filterText = '';
    },

    changeSwitch(status){
      axios.post('sso/ssoplatform/updatestatus',{
        status: status.status,
        id: status.id
      }).then((res) =>{
        this.$message.success(res.data.message)
        this.pageSsoPlatform()
      })

    },
    add(){
      this.dialogVisible = true
    },
    handleClose(done){
      done();
      this.$refs['addForm'].resetFields();
      this.clearForm()
    },
     handleSizeChange(val) {
      this.pageSize = val
      this.pageSsoPlatform()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.pageSsoPlatform()
    },

    pageSsoPlatform() {
      this.listLoading = true
      let params = {
        platformname: this.form.platformname,
        status: this.form.status,
        // pageNum: this.pageNum,
        // pageSize: this.pageSize,
      }
      // params = {
      //   ...params,
      //   ...this.page
      // }
      axios.post('sso/ssoplatform/pageSsoPlatform?pageNum='+ this.pageNum + "&pageSize=" + this.pageSize,{
        ...params,
        page: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then((res) =>{
        if(res.data.code == 'success'){
          this.dataSource = res.data.data.list.sort((a, b) =>{
            return a.id -b.id
          })
          this.total = res.data.data.total
          this.listLoading = false
        }else{
          this.listLoading = false
        }
      })
    },
    handleDel(value){
      this.$confirm('确认删除？')
        .then(_ => {
          axios.post('sso/ssoplatform/delete/'+value.id).then((res) =>{
            if(res.data.code == 'success'){
              this.$message.success(res.data.message)
              this.pageSsoPlatform()
            }else{
              this.$message.error(res.data.message)
            }
          })
        })
        .catch(_ => {});
    },
    handleEdit(value, handType){
      this.dialogVisible = true
      this.handType = handType
      axios.post('sso/ssoplatform/selectSsoPlatfromById/'+value.id).then((res) =>{
        if(res.data.code == 'success'){
          this.addForm = {
            platformcode: res.data.data.platformcode,
            platformname: res.data.data.platformname,
            platformHost: res.data.data.platformHost,
            platformIndex: res.data.data.platformIndex,
            syncFlag: res.data.data.syncFlag,
            weight: res.data.data.weight,
            cookieKey: res.data.data.cookiekey,
            describe: res.data.data.describe,
          }
          this.id = res.data.data.id
        }else{
          this.$message.error(res.data.message)
        }
      })

    },

    clearFormAuth(){
      this.dialogAuth = false,
      this.defaultShowKeys = []
    },


    clearForm(){
      this.dialogVisible = false
      this.handType = 'new'
      this.addForm = {
        platformcode: '',
        platformname: '',
        platformHost: '',
        platformIndex: '',
        syncFlag: '',
        cookieKey: '',
        describe: ''
      }
      this.id = ''
    },
    onSubmit(){
      this.$refs.addForm.validate(valid => {
        if(valid){
          this.submitLoading = true
          if(this.handType == 'new'){
            const params = {
              platformcode: this.addForm.platformcode, 
              platformname: this.addForm.platformname,
              platformHost: this.addForm.platformHost,
              platformIndex: this.addForm.platformIndex,
              syncFlag: this.addForm.syncFlag,
              weight: this.addForm.weight,
              cookiekey: this.addForm.cookieKey,
              describe: this.addForm.describe,
            }
            axios.post('sso/ssoplatform/insert',params).then((res) =>{
              if(res.data.code == 'success'){
                this.submitLoading = false
                this.clearForm()
                this.pageSsoPlatform()
              }else{
                this.submitLoading = false
                this.$message.error(res.data.message)
              }
            })
          }else if(this.handType == 'edit'){
            const params = {
              platformcode: this.addForm.platformcode,
              platformname: this.addForm.platformname,
              platformHost: this.addForm.platformHost,
              platformIndex: this.addForm.platformIndex,
              syncFlag: this.addForm.syncFlag,
              weight: this.addForm.weight,
              cookiekey: this.addForm.cookieKey,
              describe: this.addForm.describe,
              id: this.id
            }
            axios.post('sso/ssoplatform/update',params).then((res) =>{
              if(res.data.code == 'success'){
                this.submitLoading = false
                this.clearForm()
                this.pageSsoPlatform()
              }else{
                this.submitLoading = false
                this.$message.error(res.data.message)
              }
            })
          }
        }else {
          return false
        }
      })


    },
  }
}
</script>

<style scoped>
.FormClass{
  font-size: 14px;
  position: relative;
  margin: 20px 20px 0 !important;
}
</style>
