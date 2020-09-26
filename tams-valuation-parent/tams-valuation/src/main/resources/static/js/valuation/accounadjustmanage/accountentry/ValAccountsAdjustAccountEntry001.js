var ValAccountsAdjustAccountEntry001 = function(){
	
	var PAGE_MODEL_NEW = "0";//页面模式-新建
    var PAGE_MODEL_EDIT = "1";//页面模式-编辑
    var PAGE_MODEL_COPY = "3";//页面模式-复制
    var PAGE_MODEL_ADD = "4";//页面模式-添加子节点
    var treeTable;
    
    return{
     	init:function(){
	        treeTable = $("#treeTable").joyintable({
			   ajax: {
		            url: 'findAccountEntry',
		            type: 'get',
		            data: function() {
		                return {
		     	           booksetId: $('#booksetId').val(),
		    	           nodeId: $('#tableId').val()
		    	       };
		            }
		        }
		        ,tree:true
		        ,checkbox: false
		        ,searchOnInit:true
		        ,treeId:'sceneNodeId'//树形id字段名称
		        ,treeUpId:'fsceneNodeid'//树形父id字段名称
		        ,treeShowName:'sceneNodeId'//以树形式显示的字段
	            ,dblclick:function(table, data) {
	                Joyin.dialog(JoyinUtil.getLabel("查看会计分录详情："+data.sceneNodeId),
	                        JoyinUtil.getRealPath("/valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry004/init"),
	                        "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,booksetId:data.booksetId,sceneNodeId:data.sceneNodeId,},
	                        function(){});
	            }
		        ,cols:[
		               {field:'sceneNodeId', edit:false,width:'200', title: '分支节点'},
		               {field:'dictId', edit:false,width:'140', title: '影响记账元素'},
		               {field:'tableName', edit:false,width:'260', title: '影响记账元素名称'},
		               {field:'nodeValue', edit:false,width:'120', title: '元素值'},
		               {field:'dictName', edit:false,width:'260', title: '元素值说明'},
		               {field:'isEntry', edit:false,width:'120', title: '是否包含分录'
		            	   ,render: function (data, type, row, meta) {
		            		   if (row.disCbxCol == '2') {
		            			   return '';
		            		   }
		                    	if(data == false) {
		                    		return '<span class="layui-badge-rim layui-bg-blue">否</span>';
		                    	}else{
		                    		return '<span class="layui-badge-rim layui-bg-blue">是</span>';
		                    	}
		                    } 	               
		               }
//		               ,{field:'operation',title: '操作', width:'120', align:'center', type:'action',
//		            	   render : function(data, type, row, meta){
//		            		   var html = "";
//				        	 if(purviewElements.indexOf('_manual')!=-1){
//				        		 html += "<i class='iconfont icon-copy' title ='复制'></i>";
//				        	 }
//				        	 if(purviewElements.indexOf('_batch')!=-1){
//				        		 html += "<i class='iconfont icon-hotfill' title ='添加子节点'></i>";
//				        	 }
//				        	 if(purviewElements.indexOf('_edit')!=-1){
//				        		 html += "<i class='btn layui-icon  edit' title ='编辑'>&#xe642;</i>";
//				        	 }
//				        	 if(purviewElements.indexOf('_delete')!=-1){
//				        		html +="<i class = 'btn layui-icon delete' title='删除'>&#xe640;</i>";
//				        	}
//				        	 return html;
//		            	   }
//
//
//		            	}
		        ]
		    });
		    
//		    $.fn.joyintable.defaults = {
//			    // 按钮设置
//			    buttons: {
//				  copy: {
//					text: '<i class="iconfont icon-fuzhi"></i>',
//					clas: 'copy',
//					title:'复制'
//				  },
//				  addchild: {
//						text: '<i class="iconfont icon-fuzhi1"></i>',
//						clas: 'addchild',
//						title:'添加子节点'
//					  },
//				  edit: {
//					text: '&#xe642;',
//					clas: 'edit',
//					title:'编辑'
//				  },
//				  delt: {
//					text: '&#xe640;',
//					clas: 'delt',
//					title:'删除'
//				  }
//			    }
////		    	render : function(data, type, row, meta){
////		        	 var html = "";
////		        	 if(purviewElements.indexOf('_manual')!=-1){
////		        		 html += "<i class='iconfont icon-fuzhi manual' title ='复制'></i>";
////		        	 }
////		        	 if(purviewElements.indexOf('_batch')!=-1){
////		        		 html += "<i class='iconfont icon-fuzhi1 batch' title ='添加子节点'></i>";
////		        	 }
////		        	 if(purviewElements.indexOf('_edit')!=-1){
////		        		 html += "<i class='btn layui-icon  edit' title ='编辑'>&#xe642;</i>";
////		        	 }
////		        	 if(purviewElements.indexOf('_delete')!=-1){
////		        		html +="<i class = 'btn layui-icon delete' title='删除'>&#xe640;</i>";
////		        	}
////		        	 return html;
////		        }
//		   }
		   
		   
		   /**
		     * 绑定编辑事件
		     */
		   treeTable.bind('click', '.edit', function(data, index) {
			   data.pageModel = PAGE_MODEL_EDIT;
	    	   Joyin.dialog(JoyinUtil.getLabel("会计分录修改"),
	    	 		      basePath + "valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry003/init",
	    	 		      "80%", "90%", data);
		    });
		   	   
		   /**
		     * 绑定删除事件
		     */
		   treeTable.bind('click', '.delete', function(data, index) {
		        data.operateType = "delete";
		        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry001/deleteCheck', data, function(result) {
		        	Joyin.ajaxJson(basePath +"valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry001/deleteEntry",data,function(){
	            		treeTable.reload();
	                });
		        });
		    });
		   
		   /**
		     * 绑定复制事件
		     */
		   treeTable.bind('click', '.icon-copy', function(data, index) {
			   data.pageModel = PAGE_MODEL_COPY;
	    	   Joyin.dialog(JoyinUtil.getLabel("会计分录复制"),
	    	 		      basePath + "valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry003/init",
	    	 		      "80%", "90%", data);
		    });
		   
		   /**
		     * 绑定添加子节点事件
		     * */
		   treeTable.bind('click', '.icon-hotfill', function(data, index) {
			   data.pageModel = PAGE_MODEL_ADD;
	    	   Joyin.dialog(JoyinUtil.getLabel("会计分录添加子节点"),
	    	 		      basePath + "valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry003/init",
	    	 		      "80%", "90%", data);
		    });
        },
	    reloadTree:function(){
	    	treeTable.load();
	    },
	    exportAccountEntry:function(){
            var paramObj = {booksetId:$("#booksetId").val(), nodeId:$("#tableId").val()};
            paramObj.level = -1;
            Joyin.ajax("exportCheck", paramObj, function(result) {
                 Joyin.exportExcel('export', paramObj);
            })
        },
	    editAccountEntry:function(data,optype){
    	   data.pageModel = optype;
    	   var titlestr = "";
    	   if(optype == PAGE_MODEL_COPY){
    		   titlestr = "会计分录复制";
    	   }else if(optype == PAGE_MODEL_ADD){
    		   titlestr = "会计分录添加子节点";
    	   }else if(optype == PAGE_MODEL_EDIT){
    		   titlestr = "会计分录修改";
    	   }else if(optype == PAGE_MODEL_NEW){
    		   titlestr = "会计分录新建";
    	   }
    	   Joyin.dialog(JoyinUtil.getLabel(titlestr),
    	 		      basePath + "valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry003/init",
    	 		      "80%", "90%", data);
    	}
    }
    
}();


$(function () {	
	    ValAccountsAdjustAccountEntry001.init();
	    
	    // 绑定查询按钮事件
	    $('#ValAccountsAdjustAccountEntry001_btn_search').click(function() {
	    	var booksetId=$.trim($('#booksetId').val());
	    	 if(booksetId==''||booksetId==null){
	    	       Joyin.alert("请输入所属账套", 2);
	    	    }
	    	 else{
	          	ValAccountsAdjustAccountEntry001.reloadTree(); 
	    	 }
	 
	    	
	    });
	    // 绑定重置按钮事件
	    $('#ValAccountsAdjustAccountEntry001_btn_reset').click(function() {
	    	if($('#booksetId').val() != null){
	    		$('#booksetId').empty();
	    	}
	    	if($('#tableId').val() != null){
	    		$('#tableId').empty();
	    	}
	    	ValAccountsAdjustAccountEntry001.reloadTree();
	    });
	    
	    //绑定新建按钮事件
	    $('#ValAccountsAdjustAccountEntry001_btn_add').click(function() {
	    	ValAccountsAdjustAccountEntry001.editAccountEntry({booksetId:$('#booksetId').val()},PAGE_MODEL_NEW);
	    });	    
	    
	    $('#booksetId').change(function(){
	    	 var booksetId=$.trim($('#booksetId').val());
	         if(booksetId==''||booksetId==null){
	             return;
	         }
	      });
	    
	    //绑定导出按钮事件
	    $('#ValAccountsAdjustAccountEntry001_btn_export').click(function() {
	    	ValAccountsAdjustAccountEntry001.exportAccountEntry();
	    });
});