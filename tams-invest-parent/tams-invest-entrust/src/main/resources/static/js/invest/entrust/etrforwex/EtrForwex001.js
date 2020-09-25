/**
 * 外汇远期投资委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
        var data = $.serializeToObject('#searchForm');
        //组合代码 （多选）
        if($('#portfolioId').val()){
            data.portfolioId = $('#portfolioId').val().join(",");
        }
        // 委托日期区间 
        if($('#entrustDate').val()){
            data.startDate = $('#entrustDate').startDate();
            data.endDate = $('#entrustDate').endDate();
        }
        return data;
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrForwex002'), basePath +'invest/entrust/etrforwex/EtrForwex002/init', '98%', '95%',function(){table.reload();});
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
            Joyin.dialog(JoyinUtil.getLabel("EtrForwex002.view"),
                    JoyinUtil.getRealPath("invest/entrust/etrforwex/EtrForwex002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[
            {field:'entrustStatus',visible:false}
            ,{field:'entrustStatusStr', title: 'EtrForwex001.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustId',visible:false}
            ,{field:'portfolioId', title: 'EtrForwex001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrForwex001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'excDealTypeStr', title: 'EtrForwex001.dealType', width:'10%',sortField:"excDealType"}
            ,{field:'curPairStr', title: 'EtrForwex001.curPair', width:'10%',sortField:"curPair"}
            ,{field:'ps', visible:false}
            ,{field:'psStr', title: 'EtrForwex001.ps', width:'10%',sortField:'ps'}
            ,{field:'vdate', title: 'EtrForwex001.vdate', width:'10%',type:'date',sortField:"vdate"}
            ,{field:'mdate', title: 'EtrForwex001.mdate', width:'10%',type:'date',sortField:"mdate"}
            ,{field:'termTypeStr', title: 'EtrForwex001.term', width:'10%',sortField:"termType"}
            ,{field:'settleDate', title: 'EtrForwex002.settleDate', width:'10%',type:'date',sortField:"settleVdate"}
            ,{field:'price',type:'number', title: 'EtrForwex001.price', width:'10%',sortField:'price'}
            ,{field:'ccy',visible:false}
            ,{field:'ccyStr', title: 'EtrForwex001.ccy', width:'10%',sortField:"ccy"}
            ,{field:'entrustAmt',type:'number', title: 'EtrForwex001.entrustAmt', width:'10%',sortField:"entrustAmt"}
            ,{field:'corCcyStr', title: 'EtrForwex002.corCcy', width:'10%',sortField:"corCcy"}
            ,{field:'expCorAmt',type:'number', title: 'EtrForwex002.expCorAmt', width:'10%',sortField:"expCorAmt"}
            ,{field:'finishAmt',type:'number', title: 'EtrForwex001.finishAmt', width:'10%',sortField:"finishAmt"}
            ,{field:'transactioncom', title: 'EtrForwex001.transactioncom',render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text'>"+row.transactioncom+"%</span></div></div>";
            }, width:'10%',sortable:false}
            ,{field:'remark',type:'text', title: 'EtrForwex001.remark', width:'10%',sortField:"remark"}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrforwex/EtrForwex001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrforwex/EtrForwex001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrForwex002.edit"),
                    JoyinUtil.getRealPath("invest/entrust/etrforwex/EtrForwex002/init"),
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
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrForwex001_btn_search').click(function() {
            table.load();
            $('#EtrForwex001_btn_close').show();
            $('#EtrForwex001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrForwex001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrForwex001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrForwex001_btn_close').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrforwex/EtrForwex001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrforwex/EtrForwex001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤销
         */
        $('#EtrForwex001_btn_revoke').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrforwex/EtrForwex001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrforwex/EtrForwex001/recall', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
    });
}());