$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			assetType:$('#assetcode').val(),
		};
	}
	// 定义数据表格
	var table=$("#dataTable").joyintable({
        ajax: {
            url: '/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain001/searchPage',
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
                basePath + "model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    seqno: data.seqno
                });
        },
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols:[	
				{ field: 'seqno',visible:false},
				{ title: "资产类别", field: 'assetTypeStr',sortField:'ASSET_TYPE',align:'center'},
				{ title: "债券类型", field: 'assetDetailTypeStr',sortField:'ASSET_DETAIL_TYPE',align:'center'},
				{ title: "是否含有抵质押物", field: 'isContainPledgeStr',sortField:'IS_CONTAIN_PLEDGE',align:'center'},
				{ title: "LGD%", field: 'lgd',sortField:'LGD',type:'number',align:'center'},
				{ field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit'],align:'center'}
          ]
	})
	  table.bind('click','.edit', function(row,index) {
            row.pageModel = PAGE_MODEL_EDIT;
//            data.operateType = "edit";
               Joyin.dialog("LGD违约损失率修改",
                      "/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002/init",
                      "90%", "100%", row,function(){
                  table.reload();
              });
        });
	// 查询按钮
	$('#LossGivenDefaultMaintain001_btn_search').click(function(){
		table.load();
	})
//	/**
//     * 新建
//     */
//	$('#Specialassets_btn_add').click(function() {
//        	openNew();
//        });
//    var openNew = function(){
//        Joyin.dialog(JoyinUtil.getLabel("Specialassets002"),
//        		JoyinUtil.getRealPath("/model/impairment/assetratingallocation/specialassets/Specialassets002/init"),
//                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
//                );
//    };
//    $('#Specialassets_btn_del').click(function() {
//    	delRows();
//    });
//    var delRows = function () {
//    	 if(table!=null && table!=''){
//             var selArr = table.getAllCheckedRows();
//         }
//         if (!selArr || selArr.length == 0) {
//             Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
//             return;
//         }
//         var object = {selectedItem: selArr, operateType:"delete"};
//             Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                 WorkFlow.doDeleteList('/model/impairment/assetratingallocation/specialassets/Specialassets001/delete', object, "Specialassets001_btn_delete", "Specialassets002", function(){
//                     table.reload();//删除事件请求及回调函数
//                 });
//             });
//        
//    };
	// 导出按钮
	$('#LossGivenDefaultMaintain_btn_chbexport').click(function(){
		 var paramObj = getSearchParams();//请求参数json格式
	        Joyin.exportExcel('/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain001/export', paramObj);//导出（共通）
	});
	 /**
     * 导出
     * flag:0 全部导出，选中导出
     */
	
    function init(){
    	table.load();
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