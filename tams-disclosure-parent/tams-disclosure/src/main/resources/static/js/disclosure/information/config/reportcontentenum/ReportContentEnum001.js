$(function () {
//	左侧列表主键：指标Id -- 单击时使用
	var s_id = null;
	var indexName = null;
	var allLoadFalg = false;// 右侧表格是否加载过
	
	/**
	 * 左侧 - 动态获取：指标Id -- 单击时使用
	 */
	var getSchemeId = function() {
        return {
        	id: s_id,
        	indexName:indexName
        }
    };
	
    /**
     * 左侧 - 获取查询参数
     */
    var getSearchKey = function() {
        return {
        	searchKey: $('#searchKey').val()
        }
    };
    
    var getTypeAssetsParams = function() {
    	return {
            indexId: s_id, 
            indexName:indexName
        }
    }
    
	/**
     * 导出
     */
    var exportExcel2 = function() {
    	
    	if(getSchemeId().id!=null){
    		 if (typeAssetsTable != null && typeAssetsTable != '') {
    	            var selArr = typeAssetsTable.getAllCheckedRows();
    	            var datas = JSON.stringify(selArr);
    	        }
    	        if (!selArr || selArr.length == 0) {
    	            var paramObj = getTypeAssetsParams();
    	            Joyin.exportExcel('export', paramObj);
    	        } else {
    	            Joyin.exportExcel('export', {
    	                datas : datas
    	            });
    	        }
    	}else{
    		Joyin.alert("请选择左侧的指标库",2);
    	}
       
    };

    
   var c_id=null;
//    =========================  报表指标设置（左侧列表）   =======================
    /**
     * 设置表格：左侧指标列表    【点击左边的目录，右边表格会出现对应的数据；】
     */
	var indexIdTable=$("#indexIdTable").joyintable({
        ajax: {
            url: 'findIndexId',
            type: 'POST',
            data: function() {
                return getSearchKey();
            }
        },        
		searchOnInit: false,
		cellMinWidth:100 ,
		click:function(table, data){
			//点击时刷新右侧表格
			if(!allLoadFalg){
				s_id = data.id;
				indexName=data.indexName;
				document.getElementById("span").innerText ="指标编号:" +data.id + "    指标名称:" +data.indexName;
				allLoadTable();
			}else{
				s_id = data.id;
				indexName=data.indexName;
				document.getElementById("span").innerText ="指标编号:" +data.id + "    指标名称:" +data.indexName;
				reloadTable(data.id);
			}
			
			
		   //c_id = data.id;
//			----------------  添加操作图标和绑定事件  ------------------
	      var index=data.LAY_TABLE_INDEX;
		      var code=data.indexId;
			    var tt=$('#indexIdTable').next().children(".layui-table-box").find(".layui-table-body").eq(0);
		         tt.find("tr").each(function(i){
		      	    $(this).find('td[data-field="indexName"]').each(function(){  // 遍历 tr 的各个 td
			        	     var _text=$(this).text();
		      	    	if(index==i){
				        	   $(this).children("div").nextAll().remove();
				        	   //update by yantianyuan 二级法人按钮权限改造 start
				        	   var html = "<div style='float:right'>";
				        	   if(purviewElements.indexOf('_edit')!=-1){
				        		   html +="<i class='iconfont icon-edit edit scheme' ></i>";
				        	   }
				        	   if(purviewElements.indexOf('_delete')!=-1){
				        		   html +="  <i class='iconfont icon-delete delete scheme' ></i>";
				        	   }
				        	   html +="</div>";
				        	   $(this).children("div").after(html);
//				        	   $(this).children("div").after("<div style='float:right'><i class='iconfont icon-edit scheme' ></i>" +
//				        			  /* "  <i class='iconfont icon-copy scheme' ></i>" +*/
//				        			   "  <i class='iconfont icon-delete scheme' ></i>" );
//		//		        	   		"  <i class='iconfont icon-circle-correct' ></i></div>");
				        	   //update by yantianyuan 二级法人按钮权限改造 end
			        	     }else{
					           $(this).children("div").nextAll().remove();
		      	         }
		      	    });
	        }); 
		    $(".scheme.edit.icon-edit").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	Joyin.dialog(JoyinUtil.getLabel("修改指标名称（ "+ "<span style='color:red'> * </span>" + "必填）"),
		                basePath + "disclosure/information/config/reportcontentenum/ReportContentEnum002/init",
		                "60%", "70%", {pageModel:PAGE_MODEL_EDIT, id: data.id, operateType: "edit"},function(){
		                	$("#typeAssets-title").click();//右侧 tab 还原
		                	indexIdTable.reload();//加载左侧指标列表
		                	s_id = null;// 重置选择记录
		                	//allClearTable();// 清空右侧表格
		        });
		    });
		    $(".scheme.icon-copy").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	Joyin.dialog(JoyinUtil.getLabel("复制指标名称（ "+ "<span style='color:red'> * </span>" + "必填）"),
        	            basePath + "disclosure/information/config/reportcontentenum/ReportContentEnum002/init",
        	            "60%", "55%", {pageModel:PAGE_MODEL_EDIT, id: data.id , operateType: "copy"},function(){
        	            	$("#typeAssets-title").click();//右侧 tab 还原
        	            	indexIdTable.reload();//加载左侧指标列表
        	            	s_id = null;// 重置选择记录
        	            	//allClearTable();// 清空右侧表格
        	    });
		    });
		    $(".scheme.delete.icon-delete").click(function (event){
		    	event.stopPropagation();// 阻止事件冒泡
		    	var object = {};
		    	object.id = data.id;
		    	object.operateType = "delete";
        	    Joyin.ajaxJson(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum001/check', object, function(result) {
        	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
        	        	WorkFlow.doSaveList(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum001/deleteIndex', object, "", "ReportContentEnum001",function() {
        	        		$("#typeAssets-title").click();//右侧 tab 还原	
        	        		indexIdTable.reload();//加载左侧指标列表
        	            		s_id = null;// 重置选择记录
        	            		//allClearTable();// 清空右侧表格
        	              });
        	        });
        	    });
		    });
		    
		},
        checkbox:false,
		cols: [
			{field:'indexName', title: '指标名称', sortable: false,},
			{field:'id', title: '指标id', sortable: false,visible:false}
		],
		done:function(){
			$('#indexIdTable').next().find("thead").remove();// 删除表头
			
			//选择报表指标管理默认打开第一个指标库并展示详情
			var schArr = $('#indexIdTable').next().find("td");
			if( schArr != undefined && schArr.length > 0 ){
				schArr[0].click();// 默认选中第一个指标
			}
			
		}
	  });
	 console.log(c_id);
	var indexBind = function() {
    	/** 事件绑定: 指标名称-新建 */
    	$("#ReportContentEnum001_btn_add_index").click(function(event){
    		event.stopPropagation();// 阻止事件冒泡
            Joyin.dialog(JoyinUtil.getLabel("新建指标名称（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "disclosure/information/config/reportcontentenum/ReportContentEnum002/init",
                "60%", "70%", {pageModel:PAGE_MODEL_NEW},function(){                
                	indexIdTable.reload();//加载左侧指标列表
                	s_id = null;// 重置选择记录               	
                })
        });
    	
    	/** 带图标的输入框绑定事件:searchKey */
    	$(".iconinput").click(function(){
    		indexIdTable.reload();//加载左侧名称列表
    		s_id = null;// 重置选择记录
    	});	
    	
    	
	}
//指标         右表
	var typeAssetsTable=$("#typeAssets-table").joyintable({
	      ajax: {
	    	url: 'findIndexIdDetail',
	        type: 'POST',
	        data: function() {
	            return getTypeAssetsParams();
	        }
	    },
		searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
		cols: [
            {field:'id', width:260, title: '主键',visible:false},
            {field:'indexName', width:260, title: '指标名称',visible:false},
            {field:'indexFile', width:260, title: '文件名称',visible:false},
			{field:'indexDateStr', width:100, title: '生效日期',sort: true,sortField:"indexDate"},
			{field:'indexValue', width:260, title: '指标值'
				,render: function (data, type, row, meta) {
                    if(row.indexType=='02'){
                    	return '<a href="'+JoyinUtil.getRealPath('/framework/download/' + row.indexFile)+ '">' + data + '</a>';
                    }else{
                    	return '<span >' + data + '</a>';
                    }
                }
			},
			/*{field:'auditStatus', width:150, title: '审核状态', align: 'center'
                ,render: function (data, type, row, meta) {
                	if(data == '已审核') {
                		return '<span class="layui-badge-rim layui-bg-green">已审核</span>';
                	}
            		return '<span class="layui-badge-rim layui-bg-gray">未审核</span>';
                }
            },*/
			{field:'indexDetail', width:260, title: '适用范围说明', align: 'left'},
			{field:'createUser', width:100, title: '制作人'},
			{field:'updateTime', title: '修改时间',visible:false},
			{field:'updateTimeFmt', width:150, title: '修改时间'},
	        {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
//	        	buttons: [
//	                {text: '&#xe642;',clas: 'edit',title: '编辑'},
//	              /*  {clas: 'iconfont icon-copy',title: '复制'},*/
//	                {text: '&#xe640;',clas: 'delt',title: '删除'}]
				render: function (data, type, row, meta) {
					var html = "";
		            if(purviewElements.indexOf('_edit')!=-1){
		                html +='<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
		            }
		            if(purviewElements.indexOf('_delete')!=-1){
		                html +='<i class="btn layui-icon delete" title="删除">&#xe640;</i>';
		            }
		            return html;
		        } 

	        }
		],	
	  });
	
	var typeAssetsBind = function() {
		/**
		 * 绑定事件：自定义指标报表管理 - 新建
		 */
		$("#ReportContentEnum003_btn_add").click(function(event){	
			if(getSchemeId().id!=null){
				Joyin.dialog(JoyinUtil.getLabel("新建自定义指标报表管理（ "+ "<span style='color:red'> * </span>" + "必填）"),
			               basePath + "disclosure/information/config/reportcontentenum/ReportContentEnum003/init",
			               "60%", "80%", {pageModel:PAGE_MODEL_EDIT, operateType: "add",priId:s_id,indexName:indexName},function(){
			            	   typeAssetsTable.reload();
			       });
			}else{
				Joyin.alert("请选择左侧的指标库",2);
			}
					
		});
		
    	/** 事件绑定: 自定义指标报表管理配置-导出 */
       /* $('#ReportContentEnum003_export').click(function() {
        	
        	if(getSchemeId().id!=null){
        		var paramObj = {};
    			var rowArr = typeAssetsTable.getAllCheckedRows();// 所有选中行数据
    			if(rowArr == null || rowArr.length == 0){ // 按条件导出
    				paramObj = getTypeAssetsParams();
    			}else{// 按选中导出
    				var keyStr = "";
    				for(var i=0; i<rowArr.length; i++){ 
    					keyStr = keyStr + rowArr[i].id + ",";
    				}
    				paramObj.id = keyStr;
    			}
    	        Joyin.exportExcel('exportTypeAssets', paramObj);
        	}else{
        		Joyin.alert("请选择左侧的指标库",2);
        	}
        });
*/		
		/**
		 * 绑定事件：自定义指标报表管理 - 编辑
		 */
		typeAssetsTable.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑自定义指标报表管理（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "disclosure/information/config/reportcontentenum/ReportContentEnum003/init",
		               "70%", "80%", {pageModel:PAGE_MODEL_EDIT, id: data.id, operateType: "edit" ,priId:data.priId},function(){
		            	   typeAssetsTable.reload();
		       });
	   });
		
		/**
		 * 绑定事件：自定义指标报表管理 - 删除
		 */
		typeAssetsTable.bind('click', '.delete', function(data, index) {
			var object = {};
	    	object.id = data.id;
	    	object.operateType = "delete";
//		   	Joyin.ajaxJson(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum001/check', object, function(result) {
	           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
		           	Joyin.ajaxJson(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum001/deleteindexDetial', object, function(result) {
		           		typeAssetsTable.reload();
		           	});
		           });
//		       });
	   });
		
		/**批量删除**/
        $('#ReportContentEnum003_btn_delete').click(function() {
        	var object = {};
        	var rowArr = typeAssetsTable.getAllCheckedRows();// 所有选中行数据
        	if(rowArr == null || rowArr.length == 0){
        		layer.msg("请至少选中一个数据！");
        	}else{
        		var keyStr = "";
        		for(var i=0; i<rowArr.length; i++){
        			keyStr = keyStr + rowArr[i].id + ",";
        		}
        		object.id = keyStr;
    	    	object.operateType = "delete";
//    		   	Joyin.ajaxJson(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum001/check', object, function(result) {
		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
   		           	Joyin.ajaxJson(basePath + 'disclosure/information/config/reportcontentenum/ReportContentEnum001/deleteindexDetial', object, function(result) {
   		           		typeAssetsTable.reload();
   		           	});
		           });
//    	       });
        	}
        });
        
        /**
         * 导出
         */
        $('#ReportContentEnum003_btn_export').click(function() {
            exportExcel2();
        });
		
	}
		
	/**
	 * 加载右侧所有表格
	 */
	var allLoadTable = function(){
    	typeAssetsTable.load();//加载表格 -
    	allLoadFalg = true;
	}
	
	/**
	 * 右侧表格重载
	 */
	var reloadTable = function(id){
		typeAssetsTable.reload();//重载表格 	
	//	typeAssetsTable.layOption.where.id = id;		
	}
	
    /**
     * 页面加载完后执行
     */
    $(function() {
    	// 选项卡点击事件
        layui.element.on('tab(tabContainer)', function(obj) {
        	loadTable(obj.index);  // 加载当前表格数据
        });         
          indexIdTable.load();//加载左侧指标列表
        
          indexBind();
  //        typeAssetsTable.load();
          typeAssetsBind();
    });    
});

//添加
/*window.onload=function(){     
var oBtn=document.getElementById("btn1"); 
var arr=[]; 
var oUl=document.getElementById("ul1"); 
var aLi=oUl.getElementsByTagName("li"); 
var i;     var bAsc=true;  
oBtn.onclick=function(){
for(i=0;i<aLi.length;i++){ 
	arr[i]=aLi[i];       }  
    arr.sort(function(li1,li2){         
    if(bAsc){           
    return parseInt(li1.innerHTML)-parseInt(li2.innerHTML);  
    }else{
    	return parseInt(li2.innerHTML)-parseInt(li1.innerHTML); 
    	}       
    });       
    for(i=0;i<arr.length;i++){ 
       oUl.appendChild(arr[i]); //appendChild实际上分两步  1.先移除父级 2.添加到新的父级 （添加到原本的父级就相当于把当前元素拿到队列的末尾）       }       if(bAsc){         bAsc=false;       } //==>bAsc=!bAsc;  //简化写法       else{         bAsc=true;       }     };   }
}
}
}*/
