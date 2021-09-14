/**
 * 风险授权管理
 */


$(function() {
    /**
     * 获取查询参数
     */
	var getSearchParams = function() {
		var assetsTypetemp = $('#assetsType').val();
        var assetsType= null;
        if(assetsTypetemp != null)
        	assetsType=assetsTypetemp.join(",");
        var finprodNametemp = $("#finprodName").val();
        var finprodName = null;
        if(finprodNametemp != null)
        	finprodName=finprodNametemp.join(",");
        var bondRiskTypetemp = $("#bondRiskType").val();
        var bondRiskType = null;
        if(bondRiskTypetemp != null)
        	bondRiskType=bondRiskTypetemp.join(",");

        return {
        	bondRiskType: bondRiskType,
        	assetsType: assetsType,
        	finprodName: finprodName
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
             {field:'assetsType',sortField:"assetsType",title:'资产类型'}
            ,{field:'finprodMarketId',sortField:"FINPROD_MARKET_ID",title:'资产代码'}
            ,{field:'finprodName', sortField:"FINPROD_MARKET_ID" , title: '资产名称'}
            ,{field:'bondRiskType',  sortField:"BOND_RISK_TYPE" , title: '风险五级分类'}
            ,{field:'updateTimeStr',  sortField:"UPDATE_TIME" , title: '更新日期'}
        ]
    });
//    /**
//     * 绑定编辑事件
//     */
//    table.bind('click', '.edit', function(data, index) {
//        data.pageModel = PAGE_MODEL_EDIT;
//        Joyin.ajaxJson(basePath + 'invest/risk/rskauthoritymanage/RskAuthorityManage002/check', data, function(result) {
//        		Joyin.dialog(JoyinUtil.getLabel("RskAuthorityManage002.edit"),
//                        basePath + "invest/risk/rskauthoritymanage/RskAuthorityManage002/init",
//                        "95%", "90%",data,function() {
//                                            table.reload();
//                                        });
//
//        });
//        /*Joyin.ajaxJson(basePath + 'asset/depo/FinDepositTarget001/editCheck', data, function(result) {//请求路径，请求参数回调函数
//            Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002.editTitle"),
//                    basePath + "asset/depo/FinDepositTarget002/init",//打开新界面
//                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
//                                    finprodId:data.finprodId,  //金融产品代码
//                                    valDate:data.valDate,//估值日
//                                    finprodMarketId:data.finprodMarketId//市场代码
//                                    },function(){
//                                        table.reload();//回调函数，表格刷新
//                                    });
//        })*/
//    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/risk/rskauthoritymanage/RskAuthorityManage001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/rskauthoritymanage/RskAuthorityManage001/delete', object,
                        "RskAuthorityManage001_btn_delete", "RskAuthorityManage002",function() {
                    table.reload();
                });
            });
        });
    });


    // 导入按钮点击事件
    $('#RiskLevelImport_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
            var options = {};
            options.pageId = "RiskLevelImport001";
            Joyin.uploadFile(options,function(fileName) {
                Joyin.ajax(basePath + 'invest/risk/risklevel/RiskLevelImport001/check', {fileName: fileName}, function() {
                    Joyin.ajax(basePath + 'invest/risk/risklevel/RiskLevelImport001/importFile', {fileName: fileName});
                });
            },function() {
                table.reload();
            });
        });
    });


    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RiskLevelImport001_btn_search').click(function() {
            table.load();
        });
        $('#RiskLevelImport001_btn_export').click(function() {
            exportExcel();
        });

        $('#RiskLevelImport_btn_delete').click(function() {
             if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("请至少选中一条数据进行删除！"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function() {
                Joyin.ajaxJson(basePath + 'invest/risk/risklevel/RiskLevelImport001/delete', object, function(result) {
                    table.reload();// 表格删除行
                });
            }, function() {
            })
        });

    });
}());