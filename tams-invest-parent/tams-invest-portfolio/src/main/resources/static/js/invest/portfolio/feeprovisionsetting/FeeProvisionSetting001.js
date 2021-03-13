
(function() {

    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        param.positionStartDate = $('#FeeProvisionSetting001_positionDate').startDate();
        param.positionEndDate = $('#FeeProvisionSetting001_positionDate').endDate();
        return param;
    }

    var table = $('#dataTable')
        .joyintable(
            {
                ajax : {
                    url : 'selectForPage',
                    type : 'POST',
                    data : function() {
                        return getSearchParams();
                    }
                },
                // searchOnInit :REM_TYPE && REM_TYPE == 'R1S6',
                dblclick : function(table, data) {
                    if (!data.tradeId || data.tradeId === ''){
                        data.confirmFlag = 'confirm'
                    }
                    data.seqNo = data.seqNo;
                    data.pageModel = PAGE_MODEL_REFERENCE;
                    Joyin.dialog(JoyinUtil.getLabel("FeeProvisionSetting002.reference"),
                        JoyinUtil.getRealPath("invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/init"),
                        "95%", "95%", data, function() {
                            table.reload();
                        });
                },

                cols : [
                    { field : 'dealStatusStr', title : 'FeeProvisionSetting001.dealStatus', width : 110, sortField:'dealStatus'},
                    { field : 'secAcctId', title : 'FeeProvisionSetting001.secAcctId', width : 110, sortField:'secAcctId'},
                    { field : 'feeTypeStr', title : 'FeeProvisionSetting001.feeType', width : 100, sortField:'feeType'},
                    { field : 'positionStartDate', title : 'FeeProvisionSetting001.positionStartDate', width : 80, type:'date', sortField:'positionStartDate'},
                    { field : 'positionEndDate', title : 'FeeProvisionSetting001.positionEndDate', width : 80, type:'date', sortField:'positionEndDate'},
                    // { field : 'actPositionEndDate', title : 'FeeProvisionSetting001.actPositionEndDate', width : 130, type:'date', sortField:'actPositionEndDate'},
                    { field : 'dealStatus', visible : false},
                    { field : 'feeType', visible : false },
                    { field : 'seqNo', visible : false }
                ]
            });

    /**
     * 新建
     */
    var openNew = function() {
        Joyin.dialog(JoyinUtil.getLabel("FeeProvisionSetting002"),
            basePath + "invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/init",
            "95%", "95%",{pageModel : PAGE_MODEL_NEW, confirmFlag : 'new'},function(){table.reload();}
        );
    }

    /**
     * 导出
     */
   /* var exportExcel = function() {
        Joyin.exportExcel('export', getSearchParams());
    };*/
    
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 撤销
     */
    var revoke = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
            if( selArr != null &&  selArr.length > 0 ){
                if (selArr.length != 1){
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }
                var data = selArr[0];
                data.operateType = 'revoke';
                data.pageModel = PAGE_MODEL_REFERENCE;
                Joyin.ajaxJson(basePath + 'invest/portfolio/feeprovisionsetting/FeeProvisionSetting001/check', data, function(result) {
                    Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                        result = result.replace(/\s+/g,"");
                        if (!result) {
                            result = '无';
                        }
                        data.revokeReason = result;
                        WorkFlow.doRevokeList(basePath + 'invest/portfolio/feeprovisionsetting/FeeProvisionSetting001/doRevoke', data, "FeeProvisionSetting001_btn_revoke", "FeeProvisionSetting002");
                    });
                });
            }else{
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
    }

    /**
     * 修改
     */
    var modify = function() {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
            if( selArr != null &&  selArr.length > 0 ){
                if (selArr.length != 1){
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }
                var data = selArr[0];
                data.operateType = 'modify';
                data.pageModel = PAGE_MODEL_EDIT;
                Joyin.ajaxJson(basePath + 'invest/portfolio/feeprovisionsetting/FeeProvisionSetting001/checkUpdate', data, function(result) {
                    Joyin.dialog(JoyinUtil.getLabel("FeeProvisionSetting002.edit"),
                        basePath + "invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/init",
                        "95%", "95%", data, function () {
                            table.reload();
                        })
                })

            }else{
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
    }


    $(function() {
        $('#FeeProvisionSetting001_btn_search').click(function() {
            table.load();
        });
        $('#FeeProvisionSetting001_btn_export').click(function() {
            exportExcel();
        });
        $('#FeeProvisionSetting001_btn_add').click(function() {
            openNew();
        });
        $('#FeeProvisionSetting001_btn_revoke').click(function() {
            revoke();
        });
        $('#FeeProvisionSetting001_btn_modify').click(function() {
            modify();
        });
    });
}());