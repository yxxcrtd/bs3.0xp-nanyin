(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            shareId: $('#sharesId').val(),
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
            {field:'sharesId', title: 'MstShareBonusNotice001.sharesId', sortField:'shares_Id',width : 200}//资产代码
            ,{field:'regDate', title: 'MstShareBonusNotice001.regDate',sortField:'reg_Date', width:200,type:'date'}//权益登记日
            ,{field:'delDate', title: 'MstShareBonusNotice001.delDate',sortField:'del_Date',width:150,type:'date'}//除息登记日
            ,{field:'devidentDate', title: 'MstShareBonusNotice001.devidentDate',sortField:'devident_Date',width:200,type:'date'}//派息日
            ,{field:'redshareListingDate', title: 'MstShareBonusNotice001.redShareListingDate',sortField:'redshare_Listing_Date', width:200,type:'date'}//红股上市日
            ,{field:'perDevidentAmt', title: 'MstShareBonusNotice001.perDevidentAmt', sortField:'per_Devident_Amt',width : 200}//每股送转
            ,{field:'perDevidentCash', title: 'MstShareBonusNotice001.perDevidentCash', sortField:'per_Devident_Cash',width : 200}//每股派息
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
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstShareBonusNotice002.edit"),
                basePath + "invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                    sharesId:data.sharesId,
                    regDate:regDate},function() {
                    table.reload();
                });
        });
    });


    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",sharesId:data.sharesId};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice001/delete', object, "MstShareBonusNotice001_btn_delete", "MstShareBonusNotice002",function() {
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
        $('#MstShareBonusNotice001_btn_search').click(function() {
                table.load();
        });

        //新建
        $('#MstShareBonusNotice001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstShareBonusNotice002"),
                basePath + "invest/basicdata/mstsharebonusnotice/MstShareBonusNotice002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW});
        });


        //删除
        $('#MstShareBonusNotice001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
           Joyin.ajaxJson(basePath + 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstsharebonusnotice/MstShareBonusNotice001/delete', object, "MstShareBonusNotice001_btn_delete", "MstShareBonusNotice002",function() {
                        table.reload();
                    });
                });
            });
        });



        //导出
        $('#MstShareBonusNotice001_btn_export').click(function() {
            exportExcel();
        });
//        //重置
//        $('#MstShareBonusNotice001_btn_reset').click(function(){
//            $("#sharesId").val('');
//            $("#delDate").val('');
//            $("#devidentDate").val('');
//            table.clear();
//        });
    });



}());
