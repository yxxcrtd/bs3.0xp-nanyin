$(function(){
	// 获取查询参数
	var getSearchParams = function(){
		return {
			stockBordsttus:$('#stockBordsttus').val(),
			finprodNames:$('#finprodName').val()==null?null:$('#finprodName').val().join(','),
		};
	}
	// 定义数据表格
	var table=$("#dataTable").joyintable({
        ajax: {
            url: 'searchPage',
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
                basePath + "valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    seqno: data.seqno
                });
        },
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols:[	
				{ field: 'seqno',visible:false},
				{ title: "资产代码", field: 'finprodId',sortField:'FINPROD_ID',align:'center',width:180 },
				{ title: "资产名称", field: 'finprodName',sortField:'FINPROD_NAME',align:'center',width:200},
				{ title: "标的股票代码", field: 'stockId',sortField:'STOCK_ID',align:'center',width:150},
				{ title: "标的公司上市状态", field: 'stockBordsttusStr',sortField:'STOCK_BORDSTTUS',align:'center',width:150},
				{ title: "可比公司", field: 'compOrgname',sortField:'COMP_ORG',align:'center',width:200},
				{ title: "可比公司股票代码", field: 'compStockid',sortField:'COMP_STOCKID',align:'center',width:150},
				{ title: "市场乘数方法", field: 'marketMultiStr',sortField:'MARKET_MULTI',align:'center',width:200},
				/** modify by yanya on 20210824 根据新需求去除：可比公司市净率、可比公司市盈率**/
				//{ title: "可比公司市净率", field: 'compPB',sortable:false,type:'number',align:'center',width:160},
				//{ title: "可比公司市盈率", field: 'compPE',sortable:false,type:'number',align:'center',width:160},
				{ title: "标的公司每股收益", field: 'finprodProit',sortField:'FINPROD_PROIT',type:'number',align:'center',width:180},
				{ title: "标的公司每股净资产", field: 'finprodNetasset',sortField:'FINPROD_NETASSET',type:'number',align:'center',width:200},
				{ title: "连续股息率", field: 'smoothsection',sortField:'SMOOTHSECTION',type:'number',align:'center',width:100},
				{ title: "二叉树步数", field: 'bintreeStep',sortField:'BINTREE_STEP',align:'center',width:100},
				{ title: "无风险利率曲线名称", field: 'curveCodeStr',sortField:'CURVE_CODE',align:'center',width:200},
				{ field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit'],align:'center'}
          ]
	})
	  table.bind('click','.edit', function(row,index) {
            row.pageModel = PAGE_MODEL_EDIT;
//            data.operateType = "edit";
               Joyin.dialog("二叉树模型参数配置修改",
                      "/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002/init",
                      "90%", "100%", row,function(){
                  table.reload();
              });
        });
	// 查询按钮
	$('#ModBintreeConfig001_btn_search').click(function(){
		table.load();
	})
	/**
	 * 新建
	 */
	$('#ModBintreeConfig001_btn_add').click(function() {
		Joyin.dialog("二叉树模型参数配置新建",
			"/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002/init",
			"90%", "100%", {pageModel:PAGE_MODEL_NEW},function(){
				table.reload();
			});
	});

	/**
	 *删除
	 */
	$('#ModBintreeConfig001_btn_delete').click(function() {
		if( table != null && table != ''){
			var selArr = table.getAllCheckedRows();
		}
		if (!selArr || selArr.length == 0) {
			Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
			return;
		}
		var object = {selectedItem: selArr, operateType:"delete"};
		//modify by yany on 20210823 删除检查
		Joyin.ajaxJson(basePath + 'valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig001/check', object, function(result) {
			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
				WorkFlow.doDeleteList('/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig001/delete', object, "ModBintreeConfig001_btn_delete", "ModBintreeConfig002", function () {
					table.reload();//删除事件请求及回调函数
				});
			});
		});
	});
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
	$('#ModBintreeConfig_btn_chbexport').click(function(){
		 var paramObj = getSearchParams();//请求参数json格式
	        Joyin.exportExcel('/model/impairment/lossgivendefaultmanage/ModBintreeConfig/ModBintreeConfig001/export', paramObj);//导出（共通）
	});
	 /**
     * 导出
     * flag:0 全部导出，选中导出
     */
	
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