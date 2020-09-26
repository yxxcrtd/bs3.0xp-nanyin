$(function () {
	//当前是否包含流程
	var workflowIdNow = $("#workflowId").val();
	
	//定义表格列数据
	var tableCols = [
	      		   {field:'subjectNo', title: '科目号', edit:true, editOption:{id: 'subjectNo', type:'select', url:JoyinUtil.getRealPath('/valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getAllSubjectNo'),showField :'id'}},
	               {field:'subjectName', title: '科目名称'},
	               {field:'detailSubjectNo', title: '科目尾号', edit:true, editOption:{type: 'textbox'}},
	               {field:'detailSubjectName', title: '科目尾号名称', edit:true, editOption:{type: 'textbox'}},
	               {field:'dAmt', title: '借方金额', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.00'}},
	               {field:'cAmt', title: '贷方金额', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.00'}},
	               {field:'ccy', title: '币种', edit:true, editOption:{id: 'ccy', type:'select', result: CCY_DATA}},
	               {field:'bdHappenAmt', title: '本币借方金额', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.00'}},
	               {field:'bcHappenAmt', title: '本币贷方金额', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.00'}},
	               {field:'happenNumber', title: '数量', edit: true, align: 'right', editOption:{type: 'money', pattern: '#.0000'}},
	               {field:'branch', title: '分支序号', edit:true, editOption:{type: 'textbox'}},
	               {field:'acctCode', title: '账户代码', edit:true, editOption:{type: 'textbox'}},
//	               {field:'branch', title: '分支序号', edit: true, visible:true, editOption:{id:'branch', type:'select', url: JoyinUtil.getRealPath('/valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getBranch'), parentIds: 'booksetId,finprodType,finprodId,subjectNo', showField: 'id'}},
//	               {field:'acctCode', title: '账户代码', edit: true, visible:true, editOption:{id:'acctCode', type:'select', url: JoyinUtil.getRealPath('/valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getAcctId'), parentIds: 'booksetId', showField: 'id'}},
	               {field:'baseSubjectNature', title: '基础科目属性' , visible:false},
	               {field:'status', title: '状态' , visible:false},
	               {field:'genDetsubFlag', title: '是否生成四级科目号' , visible:false},
	               {field:'busiDetailType', title: '资产代码对应的业务明细类型' , visible:false}
	               ];
	
	var isDisabledVal = false;//退回修改时通过页面是否disable来加载表格是否可以编辑
	if($("#cashId").prop("disabled") == true){
		isDisabledVal = true;
	}
	
	//无流程信息，代表是新建页面，不是我的待办中点击弹出的页面
	if(workflowIdNow==null || workflowIdNow.length==0 || !isDisabledVal){
		tableCols.push(
		          {field:'operation', fixed:'right',width : 120, title : '<i id="ValManualVoucher003_btn_add" class="iconfont icon-found" style="color:green"></i>',
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
            url : basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getValManualVoucher003AddDtoList',
            type: 'POST',
            data: {workflowId: workflowIdNow}
        },
        editCell: function(value, data, field, cell, column) {
            /** modify by dongjun on 20181211 start **/
            setTimeout(function() {
                
                if (!(field == 'subjectNo' || field == "ccy")) {
                    return;
                }
                if(!value || value.trim() == ''){
                    return;
                }
                //框架js中joyin-table-lay.js v2.1.3中，
                //下拉框中注释（cacheData[field + 'Val'] = ids.join(','); //缓存中添加值），直接改为cacheData[field] = ids.join(','); // 修改field值
                // 取得科目联动处理数据
                //var object = {booksetId: $("#booksetId").val(), subjectNo: data.subjectNo, busiId: $("#busiId").val(), finprodType: $("#finprodType").val(), ccy: data.ccyVal};
                var object = {booksetId: $("#booksetId").val(), subjectNo: data.subjectNo, busiId: $("#busiId").val(), finprodType: $("#finprodType").val(), ccy: data.ccy};
                Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getSubjectNo', object, function(result){
                    if (!result.data) {
                        return;
                    }
                    var rstdata = result.data;
                    data.subjectName = rstdata.subjectName;
//                    data.subjectName = rstdata.subjectName;
                    data.baseSubjectNature = rstdata.baseSubjectNature;
                    data.acctCode = rstdata.acctCode;//modify by dongjun on 20181210
                    // 资产代码对应的业务明细类型
                    data.busiDetailType = $("#busiId_hidden").val();
                    if ("Y" == rstdata.genDetsubFlag && $("#busiId").val() != "") {
                        // 期限品种 不为空
                        if('Y' == rstdata.termTypeFlag){
                            // 科目尾号 = 资产代码对应的期限品种
                            data.detailSubjectNo = rstdata.termType;
                            // 尾号名称= 资产代码对应的期限品种对应的数据字典名称
                            data.detailSubjectName = rstdata.termTypeFmt;
//                    }
//                    else if (result.busiId) {
//                        data.detailSubjectNo = result.cashType2;
//                        data.detailSubjectName = result.cashType2Name;
                        }
                        else if($("#finprodType").val() == FINPROD_TYPE_F35) {
                            /** add by yanya on 20200212**/
                            data.detailSubjectNo = $("#busiId").val();
                            data.detailSubjectName = $("#busiId").val();
                        }
                        else{
                            data.detailSubjectNo = $("#busiId").val();
                            //data.detailSubjectName = $("#busiId").data("id");
                            /** modify by yanya on 20191031 增加退回修改流程赋值**/
                            data.detailSubjectName = getBusiSubjectNameVal();
                        }
                        data.genDetsubFlag = 'Y';
                    } else {
                        data.genDetsubFlag = 'N';
                    }
//                if (data.baseSubjectNature == QTYSK_BJ || data.baseSubjectNature == QTYSK_LX) {
////                    $('[data-field="branch"]').show();
//                    table.showColumns([{field:'branch', visible: true}]);
//                } else {
////                    $('[data-field="branch"]').hide();
//                    table.showColumns([{field:'branch', visible: false}]);
//                }
                    // 刷新表格
                    table.renderRow(data.LAY_TABLE_INDEX, data);
//                    var allrows = table.getAllRows();
//                    table.reload({data : allrows});
                    table.events();
                });
            }, 10);
        },
        /** modify by dongjun on 20181211 end **/
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
        object.booksetId = $("#booksetId").val();
        object.busiId = $("#busiId").val();
        object.finprodType = $("#finprodType").val();
        if (data.genDetsubFlag && data.genDetsubFlag == "Y") {
            if (!data.detailSubjectNo || data.detailSubjectNo == "") {
                Joyin.confirm(JoyinUtil.getMessage("MANUAL_VOUCHER_C_001",JoyinUtil.getLabel("ValManualVoucher.detailSubjectNo")), function(){
                    Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/doCheck', object, function(result){
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
                });
            } else {
                Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/doCheck', object, function(result){
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
        } else {
            Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/doCheck', object, function(result){
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
     * 获取资产代码联想下拉框隐藏值，同时增加修改流程中未初始化联想框判定
     */
    var getBusiHiddenVal = function(){
    	var currentBusiIdHidden = $("#busiId_hidden").val();
        if((!currentBusiIdHidden || currentBusiIdHidden == null || currentBusiIdHidden == undefined || currentBusiIdHidden == "undefined")
        		&& (BUSI_DETAIL_TYPE && BUSI_DETAIL_TYPE != null && BUSI_DETAIL_TYPE != undefined)){
        	currentBusiIdHidden = BUSI_DETAIL_TYPE;
        }
        return currentBusiIdHidden;
    }
    
    /**
     * 获取资产代码联想下拉框文本值即资产名称
     */
    var getBusiSubjectNameVal = function(){
    	var currentSubjectName = $("#busiId").data("id");
        if((!currentSubjectName || currentSubjectName == null || currentSubjectName == undefined || currentSubjectName == "undefined")
        		&& (BUSI_NAME && BUSI_NAME != null && BUSI_NAME != undefined)){
        	currentSubjectName = BUSI_NAME;
        }
        return currentSubjectName;
    }
    
	/**
	 * 点击添加按钮触发事件
	 */
	table.bind('click',"#ValManualVoucher003_btn_add",function(){
	    var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/doAdd', object, function(result){
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
    $("#ValManualVoucher003_btn_submit").click(function(){
    	// 增加check，输入框资产代码必须是下拉框中的值
		var dataKeyArr = [];
		$("#busiId").parent().find("tr").each(function(){
			dataKeyArr.push($(this).attr("data-key"));
		});// 列表中的值
		var alt = $("#busiId").attr("alt");// 当前输入的值
		if(dataKeyArr.indexOf(alt) == -1){ // 输入不存在的值
			//修改操作中增加是否为初始值判定 add by yanya on 20191030
			if(BUSI_ID && BUSI_ID != null && BUSI_ID != undefined && BUSI_ID == $("#busiId").val()){
			}
			else{
				$("#busiId").val("");
			}
		}
    	
        var object = $.serializeToObject('#submitForm');
        //流程ID add by yanya on 20191030
        var workflowIdNow = $("#workflowId").val();
        if(workflowIdNow != null || workflowIdNow.length > 0){
        	object.workflowId = workflowIdNow;
        }
        //资产名称
        object.busiName = getBusiSubjectNameVal();
        // 业务明细类型
        object.busiDetailType = getBusiHiddenVal();
        // 初始化 业务明细代码 = 资产代码
//        object.busiDetailId = $("#busiId").val();
        object.pageModel = pageModel;
        object.listAddDto = table.getAllRows();
        var allrows = table.getAllRows();
        var flg = true;
        //科目尾号判断
        if($.isArray(allrows)) {
            $.each(allrows, function(i, d) {
                if (d.genDetsubFlag && d.genDetsubFlag == "Y") {
                    if (!d.detailSubjectNo || d.detailSubjectNo == "") {
                        flg = false;
                    }
                }
            });
        }
        
        //启动流程提交
		object.operateType = 'submit';
        
    	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/check', object, function(result) {
        	WorkFlow.doSubmitList(
        			basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/doSave', 
        			object, 
        			"ValManualVoucher003_btn_submit",
        			null,
            		function (data) {
        				var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
        			});
    	});

        
        
        
/*        if (!false) {
            Joyin.confirm(JoyinUtil.getMessage("MANUAL_VOUCHER_C_001",JoyinUtil.getLabel("ValManualVoucher.detailSubjectNo")), function(){
                Joyin.ajaxJson('doSubmit', object, function(result){
                    if(result.errorList.length==0){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
                });
            });
        } else {
            Joyin.ajaxJson('doSubmit', object, function(result){
                if(result.errorList.length==0){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }
            });
        }*/
    });
    
    //页面全部加载完毕后
    $(document).ready(function(){
    	// 审核页面加载时
    	// 业务类型为债券时，显示投资目的
        if ($("#finprodType").val() == 'F01'){
        	$("#invAimDiv").show();
        }
        else{
        	$("#invAimDiv").hide();
        }
    	
        // 业务类型变更时清空资产代码
        $("#finprodType").change(function() {
            $("#busiId").val("");
            // TODO:业务类型选择债券和计划类资产时，分支序号列显示
//            if ($("#finprodType").val() == 'F01' || $("#finprodType").val() == 'F17') {
//                $('[data-field="branch"]').show();
//                table.showColumns([{field:'acctCode', visible: true}]);
//            } else {
//                $('[data-field="branch"]').hide();
            	// modify by lixiaoyong 很多场景都要用存款类科目， 暂时不控制账户列隐藏   on 20180909 
//                tabl.showColumns([{field:'acctCode', visible: false}]);
//            }
            // 刷新表格
//            table.reload(table.options);
            // 业务类型为债券时，显示投资目的
            if ($("#finprodType").val() == 'F01'){
            	$("#invAimDiv").show();
            }
            else{
            	$("#invAimDiv").hide();
            }
        });
        // 账套变更时清空数据
        $("#booksetId").change(function() {
            if($.trim($('#booksetId').val())){
                var object = $.serializeToObject('#submitForm');
                Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getBookDate', object, function(result) {
                    if(result.data){
                        $("#bookDate").val(result.data);
                    }
                })
            }
            else{
                $("#bookDate").val("");
            }
            $("#finprodType").empty();
            $("#busiId").val("");
            $("#cashId").val("");
            $("#invAim").val("");
            $("#invAimDiv").hide();
            /*var allrows = table.getAllRows();
            if($.isArray(allrows)) {
                $.each(allrows, function(i, d) {
                    table.removeRow(d);
                });
                for (var i=0; i<allrows.length;i++) {
                    table.removeRow(allrows[i]);
                }
            }*/
            table.clear();
        });
        
        $("#busiId").change(function() {
        	var object = $.serializeToObject('#submitForm');
        	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getInvAim', object, function(result){
        		var data = result.data;
        		if(data && data.busiDetailType == 'F01'){
        			$("#invAim").val(result.invAim);
        		}
        	});
        });
    });
});