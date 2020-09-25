/**
 * 拆借委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
//        return $.serializeToObject('#searchForm');
        if($('#type6').val()){
            var type6 = $('#type6').val().join(",");
        }
        if($('#ccy').val()){
            var ccy = $('#ccy').val();
        }
        if($('#portfolioId').val()){
            var portfolioId = $('#portfolioId').val().join(",");
        }
//        if($('#ps').val()){
//            var ps = $('#ps').val().join(",");
//        }
        var ps = $('#ps').val();
        if($('#entrustStatus').val()){
            var entrustStatus = $('#entrustStatus').val().join(",");
        }
       return {
            portfolioId:portfolioId
            ,type6:type6
            ,ccy:ccy
            ,ps:ps
            ,entrustStatus:entrustStatus
            ,entrustDate:$('#entrustDate').val()
        }
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrLendSpecInfo002'), basePath +'invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/init', '98%', '95%',function(){table.reload();});
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
            Joyin.dialog(JoyinUtil.getLabel("EtrLendSpecInfo002.dblclick"),
                    JoyinUtil.getRealPath("invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[{field:'entrustStatusStr', title: 'EtrLendSpecInfo001.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustStatus', visible:false}
            ,{field:'portfolioId', title: 'EtrLendSpecInfo001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrLendSpecInfo001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'type6Str', title: 'EtrLendSpecInfo001.type6', width:'10%',sortField:"assetType1"}
            //新增投资经理  yzg 20190904 start
            ,{field:'invManagerId', title: 'EtrLendSpecInfo001.invManagerId', width:'10%',sortField:"invManagerId"}
            //新增投资经理  yzg 20190904 end
            ,{field:'type6', visible:false}
            ,{field:'psStr', title: 'EtrLendSpecInfo001.ps', width:'10%',sortField:"ps"}
            ,{field:'ps', visible:false}
            ,{field:'termDays',type:'number', title: 'EtrLendSpecInfo001.term', width:'10%',sortField:"termDays"}
            ,{field:'price',type:'number', title: 'EtrLendSpecInfo001.price', width:'10%',type:'number',sortField:"price"}
            ,{field:'ccyStr', title: 'EtrLendSpecInfo001.ccy', width:'10%',sortField:"ccy"}
            ,{field:'ccy', visible:false}
            ,{field:'vdateStr', title: 'EtrLendSpecInfo001.vdate', width:'10%',sortField:"vdate"}
            ,{field:'mdateStr', title: 'EtrLendSpecInfo001.mdate', width:'10%',sortField:"mdate"}
            ,{field:'entrustAmt',type:'number', title: 'EtrLendSpecInfo001.entrustAmt', width:'10%',sortField:"entrustAmt"}
            ,{field:'finishAmt',type:'number', title: 'EtrLendSpecInfo001.finishAmt', width:'10%',sortField:"finishAmt"}
            ,{field:'transactioncom', title: 'EtrLendSpecInfo001.transactioncom',render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+(row.finishAmt/row.entrustAmt*100).toFixed(2)+"%'><span class='layui-progress-text'>"+(row.finishAmt/row.entrustAmt*100).toFixed(2)+"%</span></div></div>";
            }, width:'10%',sortable:false}
            ,{field:'remark', title: 'EtrLendSpecInfo001.remark', width:'10%',sortable:false}
            ,{field:'entrustId',  visible:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrlendspecinfo/EtrLendSpecInfo001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.valueDate = new Date(data.valueDate).format();
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrLendSpecInfo002.edit"),
                    basePath + "invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/init",
                    "98%", "95%",data,
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
        $('#EtrLendSpecInfo001_btn_search').click(function() {
            table.load();
            $('#EtrLendSpecInfo001_btn_close').show();
            $('#EtrLendSpecInfo001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrLendSpecInfo001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrLendSpecInfo001_btn_add').click(function() {
            showDialog();
        });
        
        //add by gaopeng 20191231 导入按钮点击事件 start
        $('#EtrLendSpecInfo001_btn_import').click(function() {
            Joyin.uploadFile({pageId : 'EtrLendSpecInfo003MTM'}, function(fileName) {
                Joyin.ajax(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo003/checkImportData', {fileName: fileName}, function(checkResult) {
                    setTimeout( function () {
                        if(checkResult.data.errorMsg){
                            Joyin.error(checkResult.data.errorMsg);
                            return;
                        }
                        Joyin.confirm(checkResult.data.confirmMsg,function() {
                            var result = {};
                            result.tempList = checkResult.data.correctData;
                            Joyin.dialog(JoyinUtil.getLabel("EtrLendSpecInfo003"),
                                JoyinUtil.getRealPath("/invest/entrust/etrlendspecinfo/EtrLendSpecInfo003/init"),
                                "98%", "95%", result,
                                function(){table.reload();});
                        });
                    },1000);
                });
            },function() {
                table.reload();
            });
        });
      //add by gaopeng 20191231 导入按钮点击事件 end
        
        /**
         * 关闭委托
         */
        $('#EtrLendSpecInfo001_btn_close').click(function() {
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
                Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrlendspecinfo/EtrLendSpecInfo001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤销
         */
        $('#EtrLendSpecInfo001_btn_revoke').click(function() {
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