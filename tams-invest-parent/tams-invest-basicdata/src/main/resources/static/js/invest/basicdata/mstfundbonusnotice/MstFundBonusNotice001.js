(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            fundId:$('#fundId').val(),
            sdelDate:$('#delDate').startDate(),
            edelDate:$('#delDate').endDate(),
            sdevidentDate:$('#devidentDate').startDate(),
            edevidentDate:$('#devidentDate').endDate()
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
             {field:'fundId', title: 'MstFundBonusNotice001.fundId', sortField:'fund_Id',width : 250}//资产代码
            ,{field:'regDate', title: 'MstFundBonusNotice001.regDate', sortField:'reg_Date',width:250,type:'date'}//权益登记日
            ,{field:'delDate', title: 'MstFundBonusNotice001.delDate',sortField:'del_Date',width:250,type:'date'}//除息登记日
            ,{field:'devidentDate', title: 'MstFundBonusNotice001.devidentDate',sortField:'devident_Date',width:250,type:'date'}//派息日
            ,{field:'unitBonusUnitValue', title: 'MstFundBonusNotice001.unitBonusUnitValue',sortField:'unit_Bonus_Unit_Value', width : 250}//单位分红净值
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var regDate = new Date(data.regDate);
        regDate= regDate.format('yyyy-MM-dd');
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstFundBonusNotice002.edit"),
                basePath + "invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                    fundId:data.fundId,
                    regDate:regDate},function() {
                    table.reload();
                });
        });
    });


    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",fundId:data.fundId};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice001/delete', object, "MstFundBonusNotice001_btn_delete", "MstFundBonusNotice002",function() {
                    table.reload();
                });
            });
        });
    });




    //导出
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    //点击按钮事件
    $(function() {
        //查询
        $('#MstFundBonusNotice001_btn_search').click(function() {
            table.load();
        });

        $('#MstFundBonusNotice001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstFundBonusNotice002"),
                basePath + "invest/basicdata/mstfundbonusnotice/MstFundBonusNotice002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW});
        });


        $('#MstFundBonusNotice001_btn_delete').click(function() {
             if(table!=null && table!=''){
                 var selArr = table.getAllCheckedRows();
             }
             if (!selArr || selArr.length == 0) {
                 Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                 return;
             }
             var object = {selectedItem: selArr, operateType:"delete"};
             Joyin.ajaxJson(basePath + 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice001/check', object, function(result) {
                 Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                     WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstfundbonusnotice/MstFundBonusNotice001/delete', object, "MstFundBonusNotice001_btn_delete", "MstFundBonusNotice002",function() {
                          table.reload();
                     });
                 });
             });
         });




        //导出
        $('#MstFundBonusNotice001_btn_export').click(function() {
            exportExcel();
        });
//        //重置
//        $('#MstFundBonusNotice001_btn_reset').click(function(){
//            $("#fundId").val('');
//            $("#delDate").val('');
//            $("#devidentDate").val('');
//            table.clear();
//        });
    });


}());
