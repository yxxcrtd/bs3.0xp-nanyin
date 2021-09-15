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
            ,{field:'finprodMarketId',sortField:"finprodMarketId",title:'资产代码'}
            ,{field:'finprodName', sortField:"finprodMarketId" , title: '资产名称'}
            ,{field:'bondRiskType',  sortField:"bondRiskType" , title: '风险五级分类'}
            ,{field:'updateTimeStr',  sortField:"updateTime" , title: '更新日期'}
        ]
    });

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