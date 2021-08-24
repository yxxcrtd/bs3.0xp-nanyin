$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId: $.trim($('#finprodId').val()),
            assetId: $.trim($('#assetId').val()),
            impairDateBegin: $("#impairDate").startDate(),
            impairDateEnd: $("#impairDate").endDate(),
            impairStage: $.trim($('#impairStage').val()),
            dataSource: $.trim($('#dataSource').val()),
        }
    };

    /**
     * 减值管理
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: false,
        cellMinWidth:100 ,
        checkbox:true,
        cols: [
            {field:'seqNo', sortField: "seqNo",width:120, title: '序列号', visible:false},
            {field:'portfolioId', sortField: "portfolioId",width:120, title: '组合代码', visible:false},
            {field:'decDateFmt', sortField: "decDate",width:120, title: '减值日'},
            {field:'decDate',width:120, title: '减值日', visible:false},
            /*{field:'bussNo',  width:200, title: '业务编号'},*/
            {field:'prodId',  width:200, title: '产品代码'},
            {field:'prodName',  width:200, title: '产品名称'},
            {field:'assetId',  width:200, title: '资产代码'},
            {field:'assetName',  width:200, title: '资产名称'},
            {field:'assetType',  width:200, title: '资产类型', visible:false},
            {field:'assetTypeFmt', sortField: "assetType",  width:200, title: '资产类型'},
/*            {field:'psFmt', sortField: "ps",width:120, title: '交易方向'},
            {field:'ps',  width:120, title: '交易方向', visible:false},*/
/*            {field:'tradeDateFmt', sortField: "tradeDate",width:120, title: '交易日期'},
            {field:'tradeDate',width:120, title: '交易日期', visible:false},*/
            {field:'prinOverdueDay', type:"number", digit:2, width:140, title: '本金逾期天数'},
            {field:'intOverdueDay', type:"number", digit:2, width:140, title: '利息逾期天数'},
            {field:'purchaseTimeGradeFmt', sortField: "purchaseTimeGrade",  width:200, title: '买入时点评级'},
            {field:'purchaseTimeGrade',  width:200, title: '买入时点评级', visible:false},
            {field:'currentTimeGradeFmt', sortField: "currentTimeGrade",  width:200, title: '当前评级结果'},
            {field:'currentTimeGrade',  width:200, title: '当前评级结果', visible:false},
            {field:'preProspectivePd', type:"number", digit:6, width:140, title: '前瞻前PD违约概率%', visible:false},
            {field:'preProspectivePdFmt', sortField: "preProspectivePd", width:140, title: '前瞻前PD违约概率%'},
            {field:'stageFmt', sortField: "stage",width:120, title: '减值阶段'},
            {field:'stage',  width:120, title: '减值阶段', visible:false},
            {field:'prospectivePd', type:"number", digit:6, width:140, title: '前瞻后PD违约概率%', visible:false},
            {field:'prospectivePdFmt', sortField: "prospectivePd", width:140, title: '前瞻后PD违约概率%'},
            {field:'lgd', type:"number", digit:6, width:140, title: 'LGD违约损失率%', visible:false},
            {field:'lgdFmt', sortField: "lgd", width:140, title: 'LGD违约损失率%'},
            {field:'ead', type:"number", digit:4, width:140, title: 'EAD违约风险暴露'},
            {field:'preProspectiveEcl', type:"number", digit:4, width:140, title: '前瞻前ECL预期信用损失'},
            {field:'prospectiveEcl', type:"number", digit:4, width:140, title: '前瞻后ECL预期信用损失'},
            {field:'dataSourceFmt', sortField: "dataSource",width:120, title: '数据来源'},
            {field:'dataSource',  width:120, title: '数据来源', visible:false},
            {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
                buttons: ['edit', 'delt']
            }
        ],
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看减值管理信息"),
                basePath + "model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE,  seqNo: data.seqNo, operateType: "view"},function(){
                });
        }
    });

    /**
     * 事件绑定：减值管理信息
     */
    var init = function() {
        /** 事件绑定: 减值管理信息-查询 */
        $("#AssetImpairmentKpmgManagement001_btn_search").click(function(event){
            table.load();//重载表格 - 参数设置配置
        });

        /** 事件绑定: 减值管理信息-导出 */
        $('#AssetImpairmentKpmgManagement001_btn_export').click(function() {
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
        });


        /** 事件绑定: 减值管理信息-删除 */
        $('#AssetImpairmentKpmgManagement001_btn_delete').click(function() {
            var object = {};
            var rowArr = table.getAllCheckedRows();// 所有选中行数据
            if(rowArr == null || rowArr.length == 0 || rowArr.length >1){
                layer.msg("请选择1条记录进行删除。");
            }else{
                object.configId = rowArr[0].configId;
                object.isDefault = rowArr[0].isDefault;
                object.seqNo = rowArr[0].seqNo;
                object.operateType = "delete";
                Joyin.ajaxJson(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001/check', object, function(result) {
                    if (result.data || result.messageType === 'info') {
                        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                            WorkFlow.doDeleteList(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001/delete', object, "AssetImpairmentKpmgManagement001_btn_delete", "AssetImpairmentKpmgManagement002",function() {
                                table.reload();
                            });
                        });
                    } else {
                        WorkFlow.doDeleteList(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001/delete', object, "AssetImpairmentKpmgManagement001_btn_delete", "AssetImpairmentKpmgManagement002",function() {
                            table.reload();
                        });
                    }
                });
            }
        });

        /**
         * 绑定事件：减值管理信息- 新建
         */
        $("#AssetImpairmentKpmgManagement001_btn_add").click(function(event){
            Joyin.dialog(JoyinUtil.getLabel("新建资产减值（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_NEW,  operateType: "new"},function(){
                    table.reload();
                });
        });

        /**
         * 绑定事件：减值管理信息 - 编辑
         */
        table.bind('click', '.edit', function(data, index) {
            data.operateType = 'edit';
            Joyin.ajaxJson(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001/check', data, function(result) {
                if (result.data || result.messageType === 'info') {
                    Joyin.dialog(JoyinUtil.getLabel("编辑资产减值（ "+ "<span style='color:red'> * </span>" + "必填）"),
                        basePath + "model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqNo: data.seqNo, operateType: "edit"},function(){
                            table.reload();
                        });
                } else {
                    Joyin.dialog(JoyinUtil.getLabel("编辑资产减值（ "+ "<span style='color:red'> * </span>" + "必填）"),
                        basePath + "model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqNo: data.seqNo, operateType: "edit"},function(){
                            table.reload();
                        });
                }
            });
        });

        /**
         * 绑定事件：减值管理信息 - 删除
         */
        table.bind('click', '.delt', function(data, index) {
            var object = {};
            object.seqNo = data.seqNo;
            object.decDate = data.decDate;
            object.operateType = "delete";
                Joyin.ajaxJson(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001/check', object, function(result) {
                    if (result.data || result.messageType === 'info') {
                        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                            WorkFlow.doDeleteList(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001/delete', object, "AssetImpairmentKpmgManagement001_btn_delete", "AssetImpairmentKpmgManagement002",function() {
                                table.reload();
                            });
                        });
                    } else {
                        WorkFlow.doDeleteList(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement001/delete', object, "AssetImpairmentKpmgManagement001_btn_delete", "AssetImpairmentKpmgManagement002",function() {
                            table.reload();
                        });
                    }
                });

        });
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        init();
    });
	
});



