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

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <!-- <span> -->
          <el-button class="el-button-init" type="primary" size="mini" @click="add('new')">+新增</el-button>
        <!-- </span> -->
      </div>
      <div class="text item" ref="tableCot">
        <el-table
          v-loading="listLoading"
          :data="dataSource"
          element-loading-text="Loading"
          border
          stripe
          style="width: 100%"
          :height="Height"
          size="mini"
          fit
          highlight-current-row
        >
          <el-table-column prop="platformcode" label="系统编号" width="200px">
          </el-table-column>
          <el-table-column prop="platformname" label="系统名称" >
          </el-table-column>
          <el-table-column prop="platformurl" label="系统地址" >
          </el-table-column>
          <!-- <el-table-column prop="mduser" label="维护人员" >
          </el-table-column>
          <el-table-column prop="mdtime" label="维护时间" >
          </el-table-column> -->
          <el-table-column prop="status" label="状态" width="100px"> 
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
          <el-table-column fixed="right" label="操作" width="200px">
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
          <el-row>
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
          <el-row>
            <el-col :span="12">
              <el-form-item label="系统地址" prop="platformurl">
                <el-input v-model="addForm.platformurl" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="权重" prop="weight">
                <el-input v-model="addForm.weight" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="身份标志" prop="cookieKey">
                <el-input v-model="addForm.cookieKey" />
              </el-form-item>
            </el-col>
            
          </el-row>
          <el-row>
            <el-col >
              <el-form-item label="描述" prop="describe">
                <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" v-model="addForm.describe" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
        <el-button type="primary" @click="onSubmit" :loading = "submitLoading" size="mini">提 交</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="提示"
      :visible.sync="dialogAuth"
      width="30%"
      :before-close="handleAuthClose">
      <el-tree
        :data="dialogAuthData"
        show-checkbox
        ref="tree"
        node-key="name"
        :default-expanded-keys="defaultShowKeys"
        :default-checked-keys="defaultShowKeys"
        @check-change="handleNodeClick"
        :props="defaultProps">
      </el-tree>

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
      dataSource: [],
      listLoading: false,
      dialogVisible: false,
      dialogAuth: false,
      value5: 100,
      Height:300,
      dialogAuthData: [],
      defaultProps: {
        children: 'children',
        label: 'name'
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
        platformurl: [
          { required: true, message: '请填写系统地址', trigger: 'blur' }
        ],
        weight: [
          { required: true, message: '请填写权重', trigger: 'blur' }
        ],
        cookieKey: [
          { required: true, message: '请填写身份标志', trigger: 'blur' }
        ],
        // describe: [
        //   { required: true, message: '请填写描述', trigger: 'blur' }
        // ],
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
    const that = this
    window.onresize = () => {
      return (() => {
        let heightStyle = window.screen.height
        that.Height = heightStyle - 310
      })()
    }
  },
  created() {
    let that = this
    let heightStyle = window.screen.height
    that.Height = heightStyle - 310

    this.pageSsoPlatform()
  },

  methods: {
    handleNodeClick(data, checked, indeterminate) {
      if(!data.children){
        if(checked){
          this.defaultNodeKeys.push(data.name)
        }else{
          this.defaultNodeKeys.splice(this.defaultNodeKeys.indexOf(data.name),1);
        }
      }
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
    },

    handleAuthClose(done){
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
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
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
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
      axios.post('sso/ssoplatform/delete/'+value.id).then((res) =>{
        if(res.data.code == 'success'){
          this.$message.success(res.data.message)
          this.pageSsoPlatform()
        }else{
          this.$message.error(res.data.message)
        }
      })
    },
    handleEdit(value, handType){
      this.dialogVisible = true
      this.handType = handType
      axios.post('sso/ssoplatform/selectSsoPlatfromById/'+value.id).then((res) =>{
        if(res.data.code == 'success'){
          this.addForm = {
            platformcode: res.data.data.platformcode,
            platformname: res.data.data.platformname,
            platformurl: res.data.data.platformurl,
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
        platformurl: '',
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
              platformurl: this.addForm.platformurl,
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
              platformurl: this.addForm.platformurl,
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
