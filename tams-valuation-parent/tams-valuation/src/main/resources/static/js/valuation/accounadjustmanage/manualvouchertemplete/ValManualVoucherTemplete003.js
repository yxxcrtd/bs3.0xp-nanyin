$(function () {
	//当前是否包含流程
	var workflowIdNow = $("#workflowId").val();
	var tmpCode = $("#tmpCode").val();

	//定义表格列数据
	var tableCols = [
	      		   {field:'subjectNo', title: '科目号', edit:true, editOption:{id: 'subjectNo', type:'select', url:JoyinUtil.getRealPath('/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/getAllSubjectNo'),showField :'id'}},
	               {field:'subjectName', title: '科目名称'},
	               {field:'dAmt', title: '借方金额', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.00'}},
	               {field:'cAmt', title: '贷方金额', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.00'}},
	               {field:'ccy', title: '币种', edit:true, editOption:{id: 'ccy', type:'select', result: CCY_DATA}},
	               {field:'happenNumber', title: '数量', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.0000'}}
	               ];

	var isDisabledVal = false;//退回修改时通过页面是否disable来加载表格是否可以编辑
	if($("#subjectNo").prop("disabled") == true){
		isDisabledVal = true;
	}

	//无流程信息，代表是新建页面，不是我的待办中点击弹出的页面
	if(workflowIdNow==null || workflowIdNow.length==0 || !isDisabledVal){
		tableCols.push(
		          {field:'operation', fixed:'right',width : 120, title : '<i id="ValManualVoucherTemplete003_btn_add" class="iconfont icon-found" style="color:green"></i>',
	                   align : 'center',type : 'action', buttons : [ {text: '<i class="iconfont icon-submit"></i>', clas: 'check', title: '确认'}, 'edit', 'delt' ]}
		);
	}

    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
        searchOnInit: true,
        checkbox: false,
        height: 300,
        rowno: true,
        loading: false,
        page: false,
        ajax: {
            url : basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/getValManualVoucherTemplete003AddDtoList',
            type: 'POST',
            data: {workflowId: workflowIdNow,tmpCode:tmpCode}
        },
        editCell: function(value, data, field, cell, column) {
            setTimeout(function() {
                if (!(field == 'subjectNo' || field == "ccy")) {
                    return;
                }
                if(!value || value.trim() == ''){
                    return;
                }
                var object = {subjectNo: data.subjectNo, finprodType: $("#finprodType").val(), ccy: data.ccy};
                Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/getSubjectNo', object, function(result){
                if (!result.data) {
                    return;
                }
                var rstdata = result.data;
                data.subjectName = rstdata.subjectName;
                // 刷新表格
                table.renderRow(data.LAY_TABLE_INDEX, data);
                table.events();
            });
            }, 10);
        },
		cols: tableCols,
		loadonce:true
	});

	/**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            if(!data.seqno){
                table.removeRow(data);
                layui.layer.alert("删除成功");
                table.events();
            }
        });

    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        // 改变行数据状态
        data.status = 'N';
        // 刷新表格,状态改为可编辑
        var tr = $('tr[data-index="' + index + '"]');
        // 刷新表格,状态改为不可编辑
        tr.find('th[data-field="subjectNo"]').find('.layui-table-cell').attr('disabled','');
        tr.find('[data-field="operation"]').find('.layui-table-cell').find('.check').show();
        tr.find('[data-field="operation"]').find('.layui-table-cell').find('.edit').hide();
    });

    /**
     * 绑定确认事件
     */
    table.bind('click', '.check', function(data, index) {
        var object = data;
        object.tmpName = $("#tmpName").val();
        object.recordType = $("#recordType").val();
        object.remark = $("#remark").val();
        object.busiType = $("#busiType").val();
        if(data.subjectNo || data.subjectNo!='' ){
            Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/doCheck', object, function(result){
                if(result.errorList.length==0){
                    // 改变行数据状态
                    data.status = 'Y';
                    var tr = $('tr[data-index="' + index + '"]');
                    // 刷新表格,状态改为不可编辑
                    tr.find('th[data-field="subjectNo"]').find('.layui-table-cell').attr('disabled','disabled');
                    tr.find('[data-field="operation"]').find('.layui-table-cell').find('.check').hide();
                    tr.find('[data-field="operation"]').find('.layui-table-cell').find('.edit').show();
                }
            });
        }
    });

	/**
	 * 点击添加按钮触发事件
	 */
	table.bind('click',"#ValManualVoucherTemplete003_btn_add",function(){
	    var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/doAdd', object, function(result){
            if(result.errorList.length==0){
                result.data.status = 'N';
                table.addRow(result.data);
                table.events();
            }
        });
	});

	/**
     * 点击提交按钮触发事件
     */
    $("#ValManualVoucherTemplete003_btn_submit").click(function(){
        var object = $.serializeToObject('#submitForm');
        var workflowIdNow = $("#workflowId").val();
        if(workflowIdNow != null || workflowIdNow.length > 0){
        	object.workflowId = workflowIdNow;
        }
        object.pageModel = pageModel;
        object.listAddDto = table.getAllRows();
        var allrows = table.getAllRows();
        var flg = true;

        //启动流程提交
		object.operateType = 'submit';

    	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/check', object, function(result) {
        	WorkFlow.doSubmitList(
        			basePath + 'valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/doSave',
        			object,
        			"ValManualVoucherTemplete003_btn_submit",
        			null,
            		function (data) {
        				var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
        			});
    	});
    });

    //页面全部加载完毕后
    $(document).ready(function(){
    	// 审核页面加载时
    });
});