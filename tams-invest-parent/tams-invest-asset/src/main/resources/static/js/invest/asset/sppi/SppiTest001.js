/**
 * SPPI测试查询
 * @Author: Jiaoxujin
 * @Date: 2020-10-12
 */
$(function() {
    var layerIndex = null;

    // 测试用
    var randomPageId = function() {
        let pageIds = [
            'SppiTemplateBondPwc',
            'SppiTemplateBondDtt',
            'SppiTemplateBondKpmg',
            'SppiTemplateNotBondPwc',
            'SppiTemplateNotBondDtt',
            'SppiTemplateNotBondKpmg'
        ];
        let index = Math.round(Math.random() * 5);
        //return pageIds[index];
        return '';
    }

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        layerIndex = layer.load();      //开启loading条
        var param = $.serializeToObject('#searchForm');
        if(param.assetCodes){
            param.assetCodes = param.assetCodes.join(",");
        }

        return {
            assetCodes: param.assetCodes,
            assetType: param.assetType,
            testStatus: param.testStatus
        }
    };

    /**
     * 定义数据表格
     */
    var table = $("#assetListTable").joyintable({
        ajax: {
            url: 'findOnePage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            // 测试中、已退回也不让看？已退回，请到个人待办事项中查看
        	if(data.dataSource == '中债'){
        		data.pageModel = PAGE_MODEL_REFERENCE;
        		Joyin.dialog(JoyinUtil.getLabel("SppiTest003.title"),
	                    JoyinUtil.getRealPath("invest/asset/sppi/SppiTest003/init"),"95%","90%", data);
        	}else{
	            if (data.sppiTestId) {
	                if (data.testStatus && data.testStatus === '1'){
                        Joyin.warn("测试状态为测试中的数据无法查看详情！");
                        return;
                    }
	                let paramObj = {
	                    sppiPageId: randomPageId(),
	                    pageModel: PAGE_MODEL_REFERENCE,
	                    sppiTestId: data.sppiTestId,
	                    assetType: data.assetType,
	                    finprodId: data.finprodId,
	                    finprodName: data.finprodName,
	                    sppiTmpType: data.sppiTmpType,
	                    sppiTmpNo: data.sppiTmpNo
	                };
	                let winTitleSource = (paramObj.sppiPageId ? paramObj.sppiPageId : data.sppiPageId) + ".view";
	
	                Joyin.dialog(JoyinUtil.getLabel(winTitleSource),
	                    JoyinUtil.getRealPath("invest/asset/sppi/SppiTest002/init"),"95%","90%", paramObj);
	            }
	            else {
	                Joyin.warn(JoyinUtil.getMessage("FIN_ASSET_SPPI_001"));
	            }
        	}
        },
        cols:[
            {field:'sppiTestId', width:150, sortField:"sppiTestId", title: 'SppiTest001.sppiTestId',visible: false}, //SPPI测试ID
            {field:'assetType', width:150, sortField:"assetType", title: 'SppiTest001.assetType',visible: false}, //资产类型代码
            {field:'testStatus', width:150, sortField:"testStatus", title: 'SppiTest001.testStatus',visible: false}, //SPPI测试状态代码
            {field:'finprodId', width:150, sortField:"finprodId", title: 'SppiTest001.finprodId'}, //资产代码
            {field:'finprodName', width:250, sortField:"finprodName", title: 'SppiTest001.finprodName'}, //资产名称
            {field:'assetTypeName', width:150, sortField:"assetType", title: 'SppiTest001.assetTypeName'}, //资产类型
            {field:'testStatusName', width:150, sortField:"testStatus", title: 'SppiTest001.testStatusName'}, //SPPI测试状态
            {field:'testStatus', width:150, sortField:"testStatus", title: 'SppiTest001.testStatusName',visible: false}, //SPPI测试状态
            {field:'dataSource', width:150, sortField:"dataSource", title: 'SppiTest001.dataSource'}, //数据来源
            {field:'updateTimeStr',width:150, sortField:"notInitUpdateTime", title: 'SppiTest001.updateTimeStr',type:'date'}, //更新时间
            {field:'updateUserName', sortField:"updateUserName", title: 'SppiTest001.updateUserName'} //更新人
        ],
        done: function(table, allData){
            //数据加载完成后关闭loading条
            if(layerIndex != null){
                layer.close(layerIndex);
                layerIndex = null;
            }
        }
    });

    /**
     * 导出查询列表
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        var expArr = table.getAllCheckedRows();

        if(expArr && expArr.length > 0){
            paramObj.selectedRows = JSON.stringify(expArr);
        }

        Joyin.exportExcel('export', paramObj, function () {
            if(layerIndex != null){
                layer.close(layerIndex);
                layerIndex = null;
            }
        });
    };
    
    
    // 导入按钮点击事件
    $('#SppiTest001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
        	var options = {};
    		options.pageId = "SppiTest001_2";
            Joyin.uploadFile(options,function(fileName) {
                Joyin.ajax(basePath + 'invest/asset/sppi/SppiTest001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                    Joyin.ajax(basePath + 'invest/asset/sppi/SppiTest001/importFile', {fileName: fileName});
                });
            },function() {
                table.reload();
            });
        });
    });

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#SppiTest001_btn_search').click(function() {
            table.load();
        });

        $('#SppiTest001_btn_reset').click(function() {
            window.location.reload();
        });

        $('#SppiTest001_btn_export').click(function() {
            exportExcel();
        });

        $('#SppiTest001_btn_sppiTest').click(function() {
            // 获取选中的行
            var allCheckedRows = table.getAllCheckedRows();
            if (_.isArray(allCheckedRows) && allCheckedRows.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            else if (_.isArray(allCheckedRows) && allCheckedRows.length == 1) {
                let row = allCheckedRows[0];
                let paramObj = {
                    sppiPageId: randomPageId(),
                    pageModel: PAGE_MODEL_NEW,
                    assetType: row.assetType,
                    finprodId: row.finprodId,
                    finprodName: row.finprodName
                };
                let winTitleSource = (paramObj.sppiPageId ? paramObj.sppiPageId : row.sppiPageId) + ".create";

                if (SPPI_TESTING === row.testStatus) {
                    Joyin.error(JoyinUtil.getMessage("FIN_ASSET_SPPI_002"));
                    return;
                }
                else {
                    //如果此债券资产存在有效交易且SPPI测试结果为“测试通过”或“测试不通过”则提示“资产存在有效交易时，不允许进行SPPI测试！”。
                    if (SPPI_PASSED === row.testStatus || SPPI_FAILD === row.testStatus) {
                        Joyin.ajax(basePath + 'invest/asset/sppi/SppiTest001/queryValidTradeCount',
                            {finprodId: row.finprodId},function(result) {
                            if(result && result.statuCode == 200){
                                if (result.message) {
                                    //Joyin.alert(result.message);
                                    return;
                                }

                                Joyin.dialog(JoyinUtil.getLabel(winTitleSource),
                                    JoyinUtil.getRealPath("invest/asset/sppi/SppiTest002/init"),"95%","90%", paramObj);
                            }
                        });
                    }
                    else {
                        Joyin.dialog(JoyinUtil.getLabel(winTitleSource),
                            JoyinUtil.getRealPath("invest/asset/sppi/SppiTest002/init"),"95%","90%", paramObj);
                    }
                }
            }
            else {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            }
        });
    });
}());