$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tmpCode: $.trim($('#tmpCode').val()),
            tmpName: $('#tmpName').val(),
            busiType: $('#busiType').val(),
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
            Joyin.dialog(JoyinUtil.getLabel("查看"),
                    JoyinUtil.getRealPath("/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,tmpCode:data.tmpCode},
                    function(){
                        //table.reload();
                        });
        },
		cols: [
		   {field:'tmpCode', title: '模板编号',width:180},
           {field:'tmpName', title: '模板名称',width:140},
           {field:'subjectNo', title: '科目号',width:160},
           {field:'subjectName', title: '科目名称',width:320},
           {field:'cdFlagFmt', title: '借贷', sortField:"cdFlag", width:80},
           {field:'cdFlag', visible:false, title: '借贷'},          
           {field:'ccyFmt', title: '币种', sortField:"ccy",width:140},
           {field:'ccy', title: '币种',visible:false},
           {field:'oHappenAmt', type:"money", title: '金额',width:200},
           {field:'happenNumber', type:"number", title: '数量',width:100},
           {field:'busiTypeFmt', title: '业务类型', sortField:"busiType", width:250},
           {field:'busiType', title: '业务类型',visible:false},
           {field:'remark', title: '摘要',width:120},
           {field:'createUser', title: '制作人',width:120},
           {field:'operation', title:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操 作', type:'action', fixed: 'right',width:120,
           		       buttons:['edit', 'delt']},
		],
//		loadonce:true
	});
	
	/**
     * 绑定编辑事件w
     */
    table.bind('click', '.edit', function(data,index) {
        data.operateType="edit";
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("修改手工凭证模板（<i class='iconfont must' style='display:inline'>*</i>必填）"), basePath
                + "valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/init",
                "95%",
                "90%",{pageModel:PAGE_MODEL_EDIT, tmpCode:data.tmpCode,operateType:"edit"},
                function(){
                    table.reload();
                }
            );
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
               Joyin.ajaxJson(
                    basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001/delete',
                    object,
                    function(result) {
                        table.reload();
                    }
                );
            });
        });
    });
	
	var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
            var  isShowfl=$("#isShowfl").next().hasClass('layui-form-onswitch') ? 'yes' : 'no';
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        }
        else{
            Joyin.exportExcel('export', {
                datas : datas,
                isShowfl: isShowfl
            });
        }
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValManualVoucherTemplete001_btn_search').click(function() {
            var data=$.serializeToObject('#searchForm');
            table.load();
            if($("#isShowfl").next().hasClass('layui-form-onswitch')){
                table.showColumns([
                        {field:'subjectNo', visible: true},
                        {field:'subjectName', visible: true},
                        {field:'cdFlagFmt', visible: true},
                        {field:'ccyFmt', visible: true},
                        {field:'oHappenAmt', visible: true},
                        {field:'happenNumber', visible: true}
                    ]);
            }
            else{
                table.showColumns([
                    {field:'subjectNo', visible: false},
                    {field:'subjectName', visible: false},
                    {field:'cdFlagFmt', visible: false},
                    {field:'ccyFmt', visible: false},
                    {field:'oHappenAmt', visible: false},
                    {field:'happenNumber', visible: false}
                ]);
            }
            $('#ValManualVoucherTemplete001_btn_delete').show();
        });
        // 绑定新建事件
        $('#ValManualVoucherTemplete001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("新建手工凭证模板（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                basePath + "valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW,operateType:"new"},function(){
                    table.reload();
            });
        });
        // 绑定删除事件
        $('#ValManualVoucherTemplete001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001/check', object, function(result) {
            	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                   Joyin.ajaxJson(
                    		basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001/delete',
                    		object,
                    		function(result) {
                    			table.reload();
                    		}
                    	);
                });
            });
        });
        // 绑定导出事件
        $('#ValManualVoucherTemplete001_btn_export').click(function() {
            exportExcel();
        });
    });
	
});