/**
 * 利率互换委托查询
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){ 
    	var data =$.serializeToObject('#searchForm'); 
    	if($('#busiPs').val()){
    		data.busiPs = $('#busiPs').val().join(",");
        }
        if($('#dealMode').val()){
        	data.dealMode = $('#dealMode').val().join(",");
        }
        if($('#portfolioId').val()){
        	data.portfolioId = $('#portfolioId').val().join(",");
        }
        if($('#entrustStatus').val()){
        	data.entrustStatus = $('#entrustStatus').val().join(",");
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
        Joyin.dialog(JoyinUtil.getLabel('EtrIRS002'), basePath +'invest/entrust/etrirs/EtrIRS002/init', '98%', '95%',function(){table.reload();});
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
            Joyin.dialog(JoyinUtil.getLabel("EtrIRS.dblclick"),
                    JoyinUtil.getRealPath("invest/entrust/etrirs/EtrIRS002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[{field:'entrustStatusStr', title: 'EtrIRS.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'invManagerId', title: 'EtrIRS.invManagerId', width:'10%',sortField:"invManagerId"}
            ,{field:'portfolioId', title: 'EtrIRS.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrIRS.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'dueDate', title: 'EtrIRS.dueDate', width:'10%',sortField:"dueDate"}
            ,{field:'termTypeStr', title: 'EtrIRS.termType', width:'10%',sortField:"termType"}
            ,{field:'ccyStr', title: 'EtrIRS.ccy', width:'10%',sortField:"ccy"}
            ,{field:'entrustAmt' , digit:'6', title: 'EtrIRS.entrustAmt', width:'10%',sortField:"entrustAmt"}
            ,{field:'busiPsStr', title: 'EtrIRS.busiPs', width:'10%',sortField:"busiPs"}
            ,{field:'dealModeStr', title: 'EtrIRS.dealMode', width:'10%',sortField:"dealMode"}
            ,{field:'matureYield',type:'number', digit:'4', title: 'EtrIRS.matureYield', width:'10%',sortField:"matureYield"}
            ,{field:'vdateStr', title: 'EtrIRS.vdate', width:'10%',sortField:"vdate"}
            ,{field:'mdateStr', title: 'EtrIRS.mdate', width:'10%',sortField:"mdate"}
            ,{field:'finishAmt',type:'number', title: 'EtrIRS.finishAmt', width:'10%',sortField:"finishAmt"}
            ,{field:'transactioncom',type:'number', title: 'EtrIRS.tradeFinishAmt', width:'10%',sortField:"transactioncom" ,render : function(data, type, row, meta){
            	return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text'>"+row.transactioncom+"%</span></div></div>";
            	}
            }
            ,{field:'remark', title: 'EtrIRS.remark', width:'10%',sortable:false}
            ,{field:'entrustId',  visible:false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit','delt'] , width:'10%'}
            ],
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrirs/EtrIRS001/export", searchform());
    };

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.valueDate = new Date(data.valueDate).format();
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrIRS.edit"),
                    basePath + "invest/entrust/etrirs/EtrIRS002/init",
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
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS001/delete', object,function(result){
                    table.reload();
                });
            });
        });
    });
    
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrIRS001_btn_search').click(function() {
            table.load();
            $('#EtrIRS001_btn_close').show();
            $('#EtrIRS001_btn_revoke').show();
        });
        /**
         * 导出按钮
         */
        $('#EtrIRS001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建
         */
        $('#EtrIRS001_btn_add').click(function() {
            showDialog();
        });
        /**
         * 关闭委托
         */
        $('#EtrIRS001_btn_close').click(function() {
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
                Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤回
         */
        $('#EtrIRS001_btn_revoke').click(function() {
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
                Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032",JoyinUtil.getLabel("ETR_REVERT")), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrirs/EtrIRS001/recall', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        });
    });
}());