$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            booksetId: $.trim($('#booksetId').val()),
            busiId: $('#busiId').val(),     
            finprodType: $('#finprodType').val(),
            bookDateBegin: $('#bookDate').startDate(),
            bookDateEnd: $('#bookDate').endDate(),
            tradeId: $('#tradeId').val(),
            bookType: $('#bookType').val(),
            bookId: $('#bookId').val(),
            isShowfl : $("#isShowfl").next().hasClass('layui-form-onswitch') ? 'yes' : 'no'
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
		dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看凭证编号："+data.bookId),
                    JoyinUtil.getRealPath("/valuation/accounadjustmanage/manualvoucher/ValManualVoucher002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,tradeId:data.tradeId},
                    function(){
                        //table.reload();
                        });
        },
		cols: [
//		   {field:'seqno', title: '流水号', visible:false},
		   {field:'bookDate', title: '记账日期',width:120},
//           {field:'happenDate', title: '生成日期',width:120},
           {field:'booksetId', title: '账套代码',width:140},
           {field:'booksetName', title: '账套名称',width:160},         
           {field:'bookId',title: '凭证编号',width:160},
           {field:'subjectNo', title: '科目号',width:160},
           {field:'subjectName', title: '科目名称',width:380},
           {field:'detailSubjectNo', title: '科目尾号',width:120},
           {field:'detailSubjectName', title: '科目尾号名称',width:120},
           {field:'cdFlagFmt', title: '借贷', sortField:"cdFlag", width:80},
           {field:'cdFlag', visible:false, title: '借贷'},          
           {field:'ccyFmt', title: '原币币种', sortField:"ccy",width:140},
           {field:'ccy', title: '原币币种',visible:false},
           {field:'happenAmt', type:"money", title: '原币余额',width:200},
           {field:'happenNumber', type:"number", title: '数量',width:100},           
           //{field:'bccyFmt', title: '记账币种', sortField:"bCcy",width:140},
           {field:'bCcy', title: '记账币种',width:140},          
           {field:'bHappenAmt', type:"money", title: '本币金额',width:200},           
           {field:'tableIdFmt', title: '业务类别', sortField:"tableId", width:250},
           {field:'busiId', title: '资产/负债代码',width:320},
           {field:'busiName', title: '资产/负债名称',width:180},
           {field:'tradeId',title: '业务编号',width:200},
           {field:'pTradeId',title: '原始业务编号',width:120},
           {field:'offsetFlagFmt',title: '冲销标识', sortField:"offsetFlag", width:100},
           {field:'offsetFlag', title: '冲销标识',visible:false,width:100},
           {field:'bookRemark', title: '摘要',width:120},
           {field:'createUser', title: '制作人',width:120},
           {field:'booksetDate', title: '账套日期',visible:false,width:100},
//           {fixed : 'right', title : '操作', align : 'center',type : 'action', buttons : [ 'edit' ] }
		],
//		loadonce:true
	});
	
	/**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("修改手工凭证（<i class='iconfont must' style='display:inline'>*</i>必填）"), basePath
                + "valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/init",
                "95%",
                "90%",
                {seqno:data.seqno},    
                function(){
                    table.reload();
                    pageModel : PAGE_MODEL_EDIT
                }
          );
    });
	
	var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        }
        else{
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValManualVoucher001_btn_search').click(function() {
            var data=$.serializeToObject('#searchForm');
            table.load();
            if($("#isShowfl").next().hasClass('layui-form-onswitch')){
                table.showColumns([
                        {field:'subjectNo', visible: true},
                        {field:'subjectName', visible: true},
                        {field:'detailSubjectNo', visible: true},
                        {field:'detailSubjectName', visible: true},
                        {field:'cdFlagFmt', visible: true},
                        {field:'ccyFmt', visible: true},
                        {field:'happenAmt', visible: true},
                        {field:'happenNumber', visible: true}
                    ]);
            }
            else{
                table.showColumns([
                    {field:'subjectNo', visible: false},
                    {field:'subjectName', visible: false},
                    {field:'detailSubjectNo', visible: false},
                    {field:'detailSubjectName', visible: false},
                    {field:'cdFlagFmt', visible: false},
                    {field:'ccyFmt', visible: false},
                    {field:'happenAmt', visible: false},
                    {field:'happenNumber', visible: false}
                ]);
            }
            $('#ValManualVoucher001_btn_delete').show();
        });
        // 绑定新建事件
        $('#ValManualVoucher001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("新建手工凭证（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                        basePath + "valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/init",
                        "95%", "90%", {pageModel:PAGE_MODEL_NEW},function(){
                            table.reload();
                        });
        });
        // 绑定删除事件
        $('#ValManualVoucher001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("MANUAL_VOUCHER_E_009"));
                return;
            }
            if (selArr[0].bookDate != selArr[0].booksetDate) {
                Joyin.error(JoyinUtil.getMessage("MANUAL_VOUCHER_E_011"));
                return;
            }
            
            // 启动删除流程
            //var object = {selectedItem: selArr[0], operateType:"delete"};
            var object = selArr[0];
            object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher001/check', object, function(result) {
            	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(
                    		basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher001/delete', 
                    		object, 
                    		"ValManualVoucher001_btn_delete", 
                    		"ValManualVoucher002",
                    		function() {
                    			table.reload();
                    		}
                    	);
                });
            });
            
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                Joyin.ajaxJson('delete', selArr[0], function(result){
//                    table.reload();
//                });
//            });
        });
        // 绑定导出事件
        $('#ValManualVoucher001_btn_export').click(function() {
            exportExcel();
        });
    });
	
});