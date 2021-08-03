(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            futuresType: $('#futuresType').val(),
            futuresContractId: $('#futuresContractId').val(),
            futuresCompany: $('#futuresCompany').val(),
            effDateStart: $('#effDate').startDate(),
            effDateEnd: $('#effDate').endDate()
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
            {field:'futuresTypeStr', title: 'FuturesDepositPercent001.futuresType',width : 250}//期货类型
            ,{field:'futuresContractIdStr',title: 'FuturesDepositPercent001.futuresContractId',width:250}//合约标的
            ,{field:'futuresCompany', title: 'FuturesDepositPercent001.futuresCompany',width:150,visible:false}//期货公司
            ,{field:'futuresCompanyName', title: 'FuturesDepositPercent001.futuresCompany',width:250}//期货公司
            ,{field:'effDate', title: 'FuturesDepositPercent001.effDate',width:250,type:'date'}//生效日
            ,{field:'depositPercent', title: 'FuturesDepositPercent002.depositPercent', width : 250,type: 'number'}//保证金比例
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.effDate = new Date(data.effDate).format('yyyy-MM-dd');
        Joyin.ajaxJson(basePath + 'invest/basicdata/futuresdepositpercent/FuturesDepositPercent002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("FuturesDepositPercent002.edit"),
                basePath + "invest/basicdata/futuresdepositpercent/FuturesDepositPercent002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                    futuresType:data.futuresType,futuresContractId:data.futuresContractId,futuresCompany:data.futuresCompany,effDate:data.effDate},function() {
                    table.reload();
                });
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        var object = {selectedItem: [data], operateType: "delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/futuresdepositpercent/FuturesDepositPercent001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/futuresdepositpercent/FuturesDepositPercent001/delete', object, "FuturesDepositPercent001_btn_delete", "FuturesDepositPercent002",function() {
                    table.reload();
                });
            });
        });
    });


    /**
     * 绑定导出事件
     */
    var exportExcel = function () {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };


    //点击按钮事件
    $(function() {
        //查询
        $('#FuturesDepositPercent001_btn_search').click(function() {
            table.load();
        });

        $('#FuturesDepositPercent001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FuturesDepositPercent002"),
                basePath + "invest/basicdata/futuresdepositpercent/FuturesDepositPercent002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW});
        });

        $('#FuturesDepositPercent001_btn_export').click(function () {
            exportExcel();
        });

        $('#FuturesDepositPercent001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete",pageModel:'02'};
            Joyin.ajaxJson(basePath + 'invest/basicdata/futuresdepositpercent/FuturesDepositPercent001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/futuresdepositpercent/FuturesDepositPercent001/delete', object, "FuturesDepositPercent001_btn_delete", "FuturesDepositPercent002",function() {
                        table.reload();
                    });
                });
            });
        });

    });



}());
