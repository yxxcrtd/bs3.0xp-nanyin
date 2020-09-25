/**
 * 期权委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
//        return $.serializeToObject('#searchForm');
        var assetType11 = $('#assetType11').val()?$('#assetType11').val().join(","):"";
        var assetType10 = $('#assetType10').val()?$('#assetType10').val().join(","):"";
        var portfolioId = $('#portfolioId').val()?$('#portfolioId').val().join(","):"";
        var exerciseType =$('#exerciseType').val();
        var ps = $('#ps').val();
        var entrustStatus = $('#entrustStatus').val()?$('#entrustStatus').val():"";
       return {
            portfolioId:portfolioId
            ,assetType10:assetType10
            ,exerciseType:exerciseType
            ,ps:ps
            ,assetType11:assetType11
            ,entrustStatus:entrustStatus
            ,entrustDate:$('#entrustDate').val()
        }
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrOption002'), basePath +'invest/entrust/etroption/EtrOption002/init', '98%', '95%',function(){table.reload();});
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
            Joyin.dialog(JoyinUtil.getLabel("EtrOption002.dblclick"),
                    JoyinUtil.getRealPath("invest/entrust/etroption/EtrOption002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[{field:'entrustStatusStr', title: 'EtrOption001.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustStatus', visible:false}
            ,{field:'invManagerId', title: 'EtrOption001.invManagerId', width:'10%',sortField:"invManagerId"}
            ,{field:'portfolioName', title: 'EtrOption001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'psStr', title: 'EtrOption001.ps', width:'10%',sortField:"ps"}
            ,{field:'ps', visible:false}
            ,{field:'exerciseTypeStr', title: 'EtrOption002.exerciseType', width:'10%',sortField:"exerciseType"}
            ,{field:'assetType10Str', title: 'EtrOption001.contractSubMatter', width:'10%',sortField:"assetType10Str"}
            ,{field:'assetType10', visible:false}
            ,{field:'assetType11Str', title: 'EtrOption001.contractType', width:'10%',sortField:"assetType11"}
            ,{field:'assetType11', visible:false}
            ,{field:'entrustNum',type:'number', title: 'EtrOption001.entrustNum', width:'10%',sortField:"entrustNum"}
            ,{field:'termDays',type:'number', title: 'EtrOption001.term', width:'10%',sortField:"termDays"}
            ,{field:'vdateStr', title: 'EtrOption001.vdate', width:'10%',sortField:"vdate"}
            ,{field:'mdateStr', title: 'EtrOption001.mdate', width:'10%',sortField:"mdate"}
            ,{field:'remark', title: 'EtrOption001.remark', width:'10%',sortable:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etroption/EtrOption001/export", searchform());
    };


    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.valueDate = new Date(data.valueDate).format();
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        Joyin.ajaxJson(basePath + 'invest/entrust/etroption/EtrOption001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrOption002.edit"),
                    basePath + "invest/entrust/etroption/EtrOption002/init",
                    "98%", "95%",data,
                    function(){table.reload();});
        });
    });
    // /**
    //  * 组合代码链接
    //  */
    // table.bind('click','.portfolioIdLink', function(row) {
    //     Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
    //             basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
    //             "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
    //             portfolioId:row.portfolioId});//主键组合代码
    // });
//    /**
//     * 绑定删除事件
//     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {selectedItem: [data], operateType:"delete"};
//        Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo001/check', object, function(result) {
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo001/delete', object,function(result){
//                    table.reload();
//                });
//            });
//        });
//    });
    
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrOption001_btn_search').click(function() {
            table.load();
            $('#EtrOption001_btn_close').show();
            $('#EtrOption001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrOption001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrOption001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrOption001_btn_close').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }
//            else if(selArr && selArr.length>1){
//                 Joyin.error(JoyinUtil.getMessage("ETR_COM_X"));
//                 return;
//             } 
            else {
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etroption/EtrOption001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etroption/EtrOption001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤销
         */
        $('#EtrOption001_btn_revoke').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }
//            else if(selArr && selArr.length>1){
//                 Joyin.error(JoyinUtil.getMessage("ETR_COM_X"));
//                 return;
//             } 
            else {
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo001/recall', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
    });
}());