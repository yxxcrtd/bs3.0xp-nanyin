/**
 * 外汇掉期委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
        var data = $.serializeToObject('#searchForm');
        if($('#portfolioId').val()){
            data.portfolioId = $('#portfolioId').val().join(",");
        }
        return data;
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrWtFxswap002'), basePath +'invest/entrust/etrwtfxswap/EtrWtFxswap002/init', '98%', '95%',function(){table.reload();});
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
            Joyin.dialog(JoyinUtil.getLabel("EtrWtFxswap002.refrence"),
                    JoyinUtil.getRealPath("invest/entrust/etrwtfxswap/EtrWtFxswap002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[
             {field:'busEntrustStatus', title: 'EtrWtFxswap001.entrustStatus', width:'8%',sortField:"entrustStatus"}
            ,{field:'portfolioId', title: 'EtrWtFxswap001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'entrustId',visible:false}
            ,{field:'portfolioName', title: 'EtrWtFxswap001.portfolioName', width:'8%',sortField:"portfolioName"}
            ,{field:'busTradeType', title: 'EtrWtFxswap001.tradeType', width:'8%',sortField:"tradeType"}
            ,{field:'busCcyPair', title: 'EtrWtFxswap001.ccyPair', width:'8%',sortField:"ccyPair"}
            ,{field:'busTradePs', title: 'EtrWtFxswap001.tradePs', width:'8%',sortField:"tradePs"}
            ,{field:'vdate', title: 'EtrWtFxswap001.vdate', width:'10%',type:'date',sortField:"vdate"}
            ,{field:'mdate', title: 'EtrWtFxswap001.mdate', width:'10%',type:'date',sortField:"mdate"}
            ,{field:'busTermType', title: 'EtrWtFxswap001.termType', width:'8%',sortField:"termType"}
            ,{field:'proximalDeliveryDate', title: 'EtrWtFxswap001.proximalDeliveryDate', width:'10%',sortField:"proximalDeliveryDate"}
            ,{field:'remoteDeliveryDate', title: 'EtrWtFxswap001.remoteDeliveryDate', width:'10%',sortField:"remoteDeliveryDate"}
            ,{field:'swapsPoint', title: 'EtrWtFxswap001.swapsPoint', width:'8%',sortField:"swapsPoint"}
            ,{field:'busTradeCcy', title: 'EtrWtFxswap001.tradeCcy', width:'8%',sortField:"tradeCcy"}
            ,{field:'tradeAmt',type:'number', title: 'EtrWtFxswap001.tradeAmt', width:'10%',sortField:"tradeAmt"}
            ,{field:'busContraCcy', title: 'EtrWtFxswap001.contraCcy', width:'8%',sortField:"contraCcy"}
            ,{field:'completeAmt',type:'number', title: 'EtrWtFxswap001.completeAmt', width:'8%',sortField:"completeAmt"}
            ,{field:'tradePercent', title: 'EtrWtFxswap001.tradePercent', render : function(data, type, row, meta){
                return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+(row.tradePercent*100).toFixed(2)+"%'><span class='layui-progress-text'>"+(row.tradePercent*100).toFixed(2)+"%</span></div></div>";
            },width:'8%',sortField:"tradePercent"}
            ,{field:'remark', title: 'EtrWtFxswap001.remark', width:'8%',sortField:"REMARK"}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrwtfxswap/EtrWtFxswap001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrWtFxswap002.edit"),
                    JoyinUtil.getRealPath("invest/entrust/etrwtfxswap/EtrWtFxswap002/init"),
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
        $('#EtrWtFxswap001_btn_search').click(function() {
            table.load();
            $('#EtrWtFxswap001_btn_close').show();
            $('#EtrWtFxswap001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrWtFxswap001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrWtFxswap001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrWtFxswap001_btn_close').click(function() {
            var selArr;
            if (table != null && table != '') {
                selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
       });
        /**
         * 撤销
         */
        $('#EtrWtFxswap001_btn_revoke').click(function() {
            var selArr;
            if (table != null && table != '') {
                selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap001/recall', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 绑定删除事件
         */
        table.bind('click', '.delt', function(data, index) {
            var object = {selectedItem: [data], operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                   Joyin.ajaxJson(basePath + 'invest/entrust/etrwtfxswap/EtrWtFxswap001/delete', object, function() {
                            table.reload();
                   });
                });
            });
        });
        
    });
}());