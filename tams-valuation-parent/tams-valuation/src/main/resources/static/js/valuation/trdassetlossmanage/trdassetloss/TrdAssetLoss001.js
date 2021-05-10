/**
 * 资产减值管理查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
        var object = {
            finprodId: $('#finprodId').val(),
            vDate: $('#queryDate').startDate(),
            mDate: $('#queryDate').endDate(),
            stage: $('#stage').val()
        };
        if(object.finprodId){
            object.finprodId = object.finprodId.join(',');
        }
        return object;
    };

    // 导入按钮点击事件
    $('#TrdAssetLoss001_btn_import').click(function() {
        Joyin.uploadFile({pageId:'TrdAssetLoss004CommonMTM'},function(fileName) {
            Joyin.ajax(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004/checkImportData', {fileName: fileName}, function(checkResult) {
                if (checkResult) {
                    setTimeout( function () {
                        if(checkResult.data.errorMsg){
                            Joyin.error(checkResult.data.errorMsg);
                            return;
                        }
                        //Joyin.confirm(checkResult.data.confirmMsg,function() {
                        var result = {};
                        result.tempList = checkResult.data.correctData;
                        Joyin.dialog(JoyinUtil.getLabel("TrdAssetLoss002"),
                            JoyinUtil.getRealPath("/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004/init"),
                            "98%", "95%", result,
                            function () {
                                table.reload();
                            });
                        //});
                    },1000);
                }
            });
        },function() {
            table.reload();
        });
    });
    // 中债ECL导入按钮点击事件
    $('#TrdAssetLoss001_btn_import_ecl').click(function() {
        Joyin.uploadFile({pageId:'TrdAssetLoss004EclMTM'},function(fileName) {
            Joyin.ajax(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004/checkImportDataEcl', {fileName: fileName}, function(checkResult) {
                if (checkResult) {
                    setTimeout( function () {
                        if(checkResult.data.errorMsg){
                            Joyin.error(checkResult.data.errorMsg);
                            return;
                        }
                        //Joyin.confirm(checkResult.data.confirmMsg,function() {
                        var result = {};
                        result.tempEclList = checkResult.data.correctEclData;
                        Joyin.dialog(JoyinUtil.getLabel("TrdAssetLoss002"),
                            JoyinUtil.getRealPath("/valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004/init"),
                            "98%", "95%", result,
                            function () {
                                table.reload();
                            });
                        //});
                    },1000);
                }
            });
        },function() {
            table.reload();
        });
    });
    /**
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('TrdAssetLoss002'), basePath +'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002/init', '98%', '95%',function(){table.reload();});
    }
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return searchform();
            }
        },
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdAssetLoss002.refrence"),
                JoyinUtil.getRealPath("valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss002/init"),
                "98%", "95%", data,
                function(){table.reload();});
        },
        cols:[
            {field: 'valDate', title: '减值行情日', type: 'date', width: '10%', sortField: "valDate"}
            , {field: 'finprodId', title: '资产代码', width: '10%', sortField: "finprodId"}
            , {field: 'finprodAbbr', title: '产品名称', width: '10%', sortField: "finprodAbbr"}
            , {field: 'lossTestType', title: '减值测算方式', width: '10%', sortField: "lossTestType",visible: false}
            , {field: 'lossTestTypeStr', title: '减值测算方式', width: '10%', sortField: "lossTestType"}
            , {field: 'overDays', title: '逾期天数', width: '10%', sortField: "valDate"}
            , {field: 'residualMaturity', title: '剩余期限', width: '10%', sortField: "valDate",visible: false}
            , {field: 'vDate', title: '资产起息日', type: 'date', width: '10%', sortField: "vDate"}
            , {field: 'mDate', title: '资产到期日', type: 'date', width: '10%', sortField: "mDate"}
            , {field: 'assetExerciseDate', title: '资产行权日', type: 'date', width: '10%', sortField: "valDate"}
            , {field: 'strDate', title: '初始确认起始日', type: 'date', width: '10%', sortField: "strDate"}
            , {field: 'endDate', title: '初始确认截止日', type: 'date', width: '10%', sortField: "endDate"}
            , {field: 'peroid', title: '待偿期（年）', width: '10%', sortField: "peroid"}
            , {field: 'stage', title: '减值阶段', width: '10%', sortField: "stage",visible: false}
            , {field: 'stageStr', title: '减值阶段', width: '10%', sortField: "stage"}
            , {field: 'lossRatio', title: '减值比例%', width: '10%', sortField: "lossRatio"}
            , {field: 'pdValue', title: 'PD违约概率%', width: '10%', sortField: "pdValue"}
            , {field: 'lgdValue', title: 'LGD违约损失率%', width: '10%', sortField: "lgdValue"}
            , {field: 'eclVaule', title: 'ECL预期信用损失比例%', width: '10%', sortField: "eclVaule"}
            , {field: 'tradeId', title: '交易编号', width: '10%', sortField: "tradeId", visible: false}
        ],
    });
    /**
     * 导出
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = searchform();
            Joyin.exportExcel(basePath+"valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss001/export", searchform());
        } else {
            Joyin.exportExcel(basePath+"valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss001/export", {
                datas : datas
            });
        }
    };
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        //走check会校验dto，所以抽出一个新的editCheck方法
        Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/checkEdit', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrInvestBondInfo002.edit"),
                JoyinUtil.getRealPath("invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/init"),
                "98%", "95%", data,
                function(){table.reload();});
        });
    });

    /**
     * 组合代码链接
     */
    table.bind('click','.portfolioIdLink', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                portfolioId:row.portfolioId});//主键组合代码
    });
    /**
     * 资产代码链接
     */
    table.bind('click','.finprodIdLink', function(row) {
        //在新建界面进行显示单行的详细信息
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath
            + "invest/asset/bond/FinBondTargetAsset002/init",
            "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                finprodId:row.finprodId,  //金融产品代码
                //valDate:data.valDate,//估值日
                //finprodMarketId:data.finprodMarketId//市场代码
            });
    });
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#TrdAssetLoss001_btn_search').click(function() {
            table.load();
            $('#TrdAssetLoss001_btn_close').show();
            $('#TrdAssetLoss001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#TrdAssetLoss001_btn_export').click(function() {
            exportExcel();
        });

        /**
         * 新建
         */
        $('#TrdAssetLoss001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 撤销
         */
        $('#TrdAssetLoss001_btn_revoke').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }

            var checkObj = {};
            checkObj.tradeId = selArr[0].tradeId;
            checkObj.finprodId = selArr[0].finprodId;
            checkObj.valDate = selArr[0].valDate;
            checkObj.invalidDate = selArr[0].invalidDate;

            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss001/checkRevoke', checkObj, function (result) {
                Joyin.prompt("撤销原因", 0, function (result) {
                    result = result.replace(/\s+/g, "");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doRevokeList(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss001/doRevoke', checkObj, "TrdAssetLoss001_btn_revoke", "TrdAssetLoss002");
                });
            });
        });
    });
}());