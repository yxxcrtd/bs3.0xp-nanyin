$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			finprodIds:$('#assetcode').val()==null?null:$('#assetcode').val().join(','),
		};
	}
	// 定义数据表格
	var table=$("#dataTable").joyintable({
        ajax: {
            url: '/model/impairment/assetratingallocation/specialassets/Specialassets001/searchPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick: function (table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "model/impairment/assetratingallocation/specialassets/Specialassets002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    seqno: data.seqno
                });
        },
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols:[	
				{ field: 'seqno',visible:false},
				{ title: "资产代码", field: 'finprodId',sortField:'FINPROD_ID'},
				{ title: "资产名称", field: 'finprodName',sortField:'FINPROD_NAME'},
				{ title: "评级机构", field: 'gradeOrgIdStr',sortField:'GRADE_ORG_ID'},
				{ title: "评级类型", field: 'oTypeStr',sortField:'O_TYPE'},
				{ field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']}
          ]
	})
	  table.bind('click','.edit', function(row,index) {
            row.pageModel = PAGE_MODEL_EDIT;
//            data.operateType = "edit";
               Joyin.dialog("特殊资产评级配置修改",
                      "/model/impairment/assetratingallocation/specialassets/Specialassets002/init",
                      "90%", "100%", row,function(){
                  table.reload();
              });
        });
	// 查询按钮
	$('#Specialassets001_btn_search').click(function(){
		table.load();
	})
	/**
     * 新建
     */
	$('#Specialassets_btn_add').click(function() {
        	openNew();
        });
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("Specialassets002"),
        		JoyinUtil.getRealPath("/model/impairment/assetratingallocation/specialassets/Specialassets002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
                );
    };
    $('#Specialassets_btn_del').click(function() {
    	delRows();
    });
    var delRows = function () {
    	 if(table!=null && table!=''){
             var selArr = table.getAllCheckedRows();
         }
         if (!selArr || selArr.length == 0) {
             Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
             return;
         }
         var object = {selectedItem: selArr, operateType:"delete"};
             Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                 WorkFlow.doDeleteList('/model/impairment/assetratingallocation/specialassets/Specialassets001/delete', object, "Specialassets001_btn_delete", "Specialassets002", function(){
                     table.reload();//删除事件请求及回调函数
                 });
             });
        
    };
	// 导出按钮
	$('#Specialassets_btn_chbexport').click(function(){
//		var expArr=table.getAllCheckedRows();
//		if(expArr != null && expArr.length >0){
//			Joyin.confirm('确认导出选中信息？',function(){
//				exportExcel(1);
//			})
//		}else{
//			Joyin.confirm('确认导出查询结果？',function(){
//				exportExcel(0);
//			})
//		}
		 var paramObj = getSearchParams();//请求参数json格式
	        Joyin.exportExcel('/model/impairment/assetratingallocation/specialassets/Specialassets001/export', paramObj);//导出（共通）
	});
	 /**
     * 导出
     * flag:0 全部导出，选中导出
     */
//    var exportExcel = function(flag) {
//		if(flag == 0){
//	    	 var param = getSearchParams();
//	         Joyin.exportExcel("export",param);
//		}else{
//			var param = {};
//			var expArr=table.getAllCheckedRows();
//			var keyList = '';
//			layui.each(expArr,function(index,data){
//				if(index == expArr.length-1){
//					keyList = keyList + data.registSeqno+'';
//				}else{
//					keyList = keyList + data.registSeqno+',';
//				}
//			})
//			param.keyList = keyList;
//			Joyin.exportExcel("export",param);
//		}
//        
//    };
    
    
	
    function init(){
        if(isMain=='01'){
            setTimeout(function() {
            	if($('#sendStatus').val()=='02'||$('#sendStatus').val()=='03'){
          		   $('#submitTime').val(searchDataStart+' - '+searchDataEnd);
          	   }else{
          		   $('#pbcSubmitDeadline').val(searchDataStart+' - '+searchDataEnd);
          	   }
                table.load();
            }, 500);
        }
    };
    init();
	
})