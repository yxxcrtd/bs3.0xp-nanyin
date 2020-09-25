/**
 * 外汇即期委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
        if($('#excDealType').val()){
            var excDealType = $('#excDealType').val();
        }
        if($('#curPair').val()){
            var curPair = $('#curPair').val();
        }
        if($('#portfolioId').val()){
            var portfolioId = $('#portfolioId').val().join(",");
        }
        var ps = $('#ps').val();
        if($('#entrustStatus').val()){
            var entrustStatus = $('#entrustStatus').val().join(",");
        }
       return {
            portfolioId:portfolioId
            ,excDealType:excDealType
            ,curPair:curPair
            ,ps:ps
            ,entrustStatus:entrustStatus
            ,entrustDate:$('#entrustDate').val()
        }
    };
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrWtSpotex002'), basePath +'invest/entrust/etrwtspotex/EtrWtSpotex002/init', '98%', '95%',function(){table.reload();});
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
            Joyin.dialog(JoyinUtil.getLabel("EtrWtSpotex.dblclick"),
                    JoyinUtil.getRealPath("invest/entrust/etrwtspotex/EtrWtSpotex002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[{field:'entrustStatusStr', title: 'EtrWtSpotex.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustStatus', visible:false}
            ,{field:'portfolioId', title: 'EtrWtSpotex.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrWtSpotex.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'excDealTypeStr', title: 'EtrWtSpotex.excDealType', width:'10%',sortField:"excDealType"}
            ,{field:'excDealType', visible:false}
            ,{field:'curPairName', title: 'EtrWtSpotex.curPair', width:'10%',sortField:"curPair"}
            ,{field:'curPair', visible:false}
            ,{field:'psStr', title: 'EtrWtSpotex.ps', width:'10%',sortField:"ps"}
            ,{field:'ps', visible:false}
            ,{field:'vdateStr', title: 'EtrWtSpotex.vdate', width:'10%',sortField:"vdate"}
            ,{field:'mdateStr', title: 'EtrWtSpotex.mdate', width:'10%',sortField:"mdate"}
            ,{field:'settleSpreedStr', title: 'EtrWtSpotex.settleSpreed', width:'10%',sortField:"settleSpreed"}
            ,{field:'accountSpeed', visible:false}
            ,{field:'price', title: 'EtrWtSpotex.price', width:'10%',sortField:"price"}
            ,{field:'ccyName', title: 'EtrWtSpotex.ccy', width:'10%',sortField:"ccy"}
            ,{field:'ccy', visible:false}
            ,{field:'entrustAmt',type:'money', title: 'EtrWtSpotex.entrustAmt', width:'10%',sortField:"entrustAmt"}
            ,{field:'corCcyName', title: 'EtrWtSpotex.corCcy', width:'10%',sortField:"corCcy"}
            ,{field:'corCcy', visible:false}
            ,{field:'expCorAmt',type:'money', title: 'EtrWtSpotex.expCorAmt', width:'10%',sortField:"expCorAmt"}
            ,{field:'finishAmt',type:'number', title: 'EtrWtSpotex.finishAmt', width:'10%',sortField:"finishAmt"}
            ,{field:'tradeFinishAmt', title: 'EtrWtSpotex.tradeFinishAmt',render : function(data, type, row, meta){
                    return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.tradeFinishAmt+"%'><span class='layui-progress-text'>"+row.tradeFinishAmt+"%</span></div></div>";
                }, width:'10%',sortable:false}
            ,{field:'remark', title: 'EtrWtSpotex.remark', width:'10%',sortable:false}
            ,{field:'entrustId',  visible:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrwtspotex/EtrWtSpotex001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.valueDate = new Date(data.valueDate).format();
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrWtSpotex.edit"),
                    basePath + "invest/entrust/etrwtspotex/EtrWtSpotex002/init",
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
    
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrWtSpotex001_btn_search').click(function() {
            table.load();
            $('#EtrWtSpotex001_btn_close').show();
            $('#EtrWtSpotex001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrWtSpotex001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrWtSpotex001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrWtSpotex001_btn_close').click(function() {
            var selArr;
            if (table != null && table != '') {
                selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }
            else {
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤回
         */
        $('#EtrWtSpotex001_btn_revoke').click(function() {
            var selArr;
            if (table != null && table != '') {
                selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }
            else {
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032",JoyinUtil.getLabel("ETR_REVERT")), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrwtspotex/EtrWtSpotex001/recall', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
    });
}());