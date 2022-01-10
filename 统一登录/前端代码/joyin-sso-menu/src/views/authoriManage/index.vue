<template>
  <div class="app-container">
    <el-aside width="200px" style="background: #fff; position: fixed;
  top: 0;
  bottom: 0;">
      <el-input class="app-container-input" v-model="filterText" @input="filterTextChange" placeholder="请输入名称" size="mini">
        <el-button slot="append" icon="el-icon-search" @click="listDeptAndUser"></el-button>
      </el-input>
      <el-tree
        ref="tree2"
        :data="treeData"
        :props="defaultProps"
        node-key="id"
        :filter-node-method="filterNode"
        class="filter-tree"
        default-expand-all
        highlight-current
        @node-click="handleNodeClick"
        
      />
    </el-aside>
    <div style="clear: both"></div>
    <el-container style="margin-left: 200px;">
      <el-header>
      <el-form ref="form" :model="form" class="p16">
        <el-row class="FormClass">
          <el-col :span="6">
            <el-form-item label="系统编码">
              <el-input v-model="form.platformcode" size="small" clearable/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="系统名称">
              <el-input v-model="form.platformname" size="small" clearable/>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="8" > -->
          <!-- </el-col> -->
            <el-button type="primary" size="small" 
            style="position: absolute;
                  top: 0;
                  right: 0;"
            @click="pageSsoPlatform" class="onSearch el-button-init" :loading="listLoading" icon="el-icon-search">查询</el-button>

        </el-row>

          
      </el-form>
      </el-header>
      <el-main class="p0">
      <el-card :style="contentStyleObj">
        <div slot="header" class="clearfix">
          <!-- <span></span> -->
          <el-button type="primary" size="mini" class="el-button-init" @click="grantAuthorization">授权</el-button>
        </div>
        <div class="text item">
          <el-table
            v-loading="listLoading"
            :data="dataSource"
            ref="tableRef"
            element-loading-text="Loading"
            border
            stripe
            style="width: 100%"
            size="mini"
            fit
            highlight-current-row
            @selection-change="selectionChange"
          >
          <el-table-column type="selection" width="50"  align="center"
            fixed="left"
            :resizable="false"
          />
            <el-table-column prop="platformcode" label="系统编码" width="200px">
            </el-table-column>
            <el-table-column prop="platformname" label="系统名称">
            </el-table-column>
            <el-table-column prop="platformurl" label="访问地址" >
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
      </el-main>
    </el-container>
    

  </div>
</template>

<script>
import axios from "axios";
axios.defaults.withCredentials=true;
export default {

  data() {
    return {
      contentStyleObj: {
        height: '',
        'overflow-y':'scroll',
        padding: '0 0 100px 0',
      },
      filterText: '',
      listLoading: false,
      dataSource: [],
      form: {
        platformname: '',
        platformcode: '',
        // status: '',
      },
      pageNum: 1,
      pageSize: 20,
      total: 0,
      currentPage: 1,
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'realname'
      },
      dataId: '',
      platformids: []
    }
  },
  watch: {
    
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
    this.listDeptAndUser()
    // this.pageSsoPlatform()
  },

  destroyed() {
    window.removeEventListener('resize', this.getHight)
  },

  methods: {
    getHight(){
      this.contentStyleObj.height = window.innerHeight  + 'px'
    },
    filterTextChange(val){
      this.filterText = val
    },
    listDeptAndUser(){
      axios.post('sso/ssouserplatform/listDeptAndUser',{
        realname: this.filterText
      }).then((res) =>{
        if(res.data.code == 'success'){
          this.treeData = res.data.data
        }
      })
    },
    selectionChange(val){
      this.platformids = []
      
      val.forEach(item =>{
        this.platformids.push(item.platformid)
      })
    },
    //授权
    grantAuthorization(){
      let params = {
        userid: this.dataId,
        platformids: this.platformids
      }
      
      axios.post('sso/ssouserplatform/grantAuthorizationForUser',params).then((res) =>{
        if(res.data.code == 'success'){
          this.$message.success(res.data.message)
          
        }else{
          this.$message.success(res.data.message)
          this.pageSsoPlatform()
        }
      })
    },

    handleNodeClick(data) {
      if(data.type == "3" && !data.children){
        this.dataId = data.name
        this.pageSsoPlatform()
      }else{
        this.dataId = ""
        this.pageSsoPlatform()
      }
    },

    // 设置多选是否可选择
    // selectable(row) {
    //   if (row.userid) {
    //     return false
    //   } else {
    //     return true
    //   }
    // },
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
      if(this.dataId != ""){
        let params = {
          platformname: this.form.platformname,
          platformcode: this.form.platformcode,
          userid: this.dataId,
        }
        axios.post('sso/ssouserplatform/pageSsoPlatform?pageNum='+ this.pageNum + "&pageSize=" + this.pageSize,{
          ...params,
          page: {
            pageNum: this.pageNum,
            pageSize: this.pageSize,
          }
        }).then((res) =>{
          if(res.data.code == 'success'){
            this.dataSource = res.data.data.list
            //   .sort((a, b) =>{
            //   return a.id -b.id
            // })

            this.$nextTick(() =>{
              this.dataSource.forEach((item, index) =>{
                if(item.userid){
                  this.$refs.tableRef.toggleRowSelection(this.dataSource[index], true)
                }
              })
            })

            this.total = res.data.data.total
            this.listLoading = false
          }else{
            this.listLoading = false
          }
        })
      }else{
        this.dataSource = []
        this.listLoading = false
      }
      
    },
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },


  }
}
</script>
<style lang="scss">
.app-container{
  display: flex;
  height: 100%;
}
.container-left {
  float: left;
  width: 200px;
  height: 100%;
}
.container-right {
  float: left;
  width: calc(100% - 200px);
  height: 100%;
  padding-left: 10px;
}
.container-right .el-tree{
    height: 100% !important;
  }
</style>

