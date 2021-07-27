$(function () {
    //当前是否包含流程
	var workflowIdNow = $("#workflowId").val()
	var quickInput=false;
	var busiDetailTypeFlag=true;
	/*add by sunshuaishuai 20210303 start*/
    var getSearchParams = function() {
        //画面元素显示控制
        pageControl();
        return {
            //当前是否包含流程
             workflowId: $("#workflowId").val(),
             seqno: $("#seqno").val(),
             tmpCode: $("#tmpCode").val()
        }
    };

    var pageControl = function() {
        if(OPT_TYPE=='modify' || OPT_TYPE=='copy'){
            $("#ValManualVoucher003_tmpCode_div").hide();
            $("#ValManualVoucher003_btn_savetmp").hide();
        }
    }
    /*add by sunshuaishuai 20210303 end*/

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
            data: function() {
                    return getSearchParams();
                   }
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
                    var object = {booksetId: $("#booksetId").val(), subjectNo: data.subjectNo, busiId: $("#busiId").val(), finprodType: $("#finprodType").val(), ccy: data.ccy};
                    Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getSubjectNo', object, function(result){
                        if (!result.data) {
                            return;
                        }
                        var rstdata = result.data;
                        data.subjectName = rstdata.subjectName;
                        data.baseSubjectNature = rstdata.baseSubjectNature;
                        data.acctCode = rstdata.acctCode;
                        data.acctName = rstdata.acctName;
                        // 资产代码对应的业务明细类型
                        data.busiDetailType = $("#busiId_hidden").val();
                        if ("Y" == rstdata.genDetsubFlag && $("#busiId").val() != "") {
                            // 期限品种 不为空
                            if('Y' == rstdata.termTypeFlag){
                                // 科目尾号 = 资产代码对应的期限品种
                                data.detailSubjectNo = rstdata.termType;
                                // 尾号名称= 资产代码对应的期限品种对应的数据字典名称
                                data.detailSubjectName = rstdata.termTypeFmt;
                            }
                            //modify by sunshuaishuai 20210312 start
                            else if((data.acctName && data.acctName!="") || (rstdata.subjectNo=='10020101' || rstdata.subjectNo=='10020201'
                                   || rstdata.subjectNo=='10210101' || rstdata.subjectNo=='10310101' || rstdata.subjectNo=='10310201'
                                   || rstdata.subjectNo=='10310301') && $("#finprodType").val()!="F20"){
                                data.detailSubjectNo =  data.acctCode;
                                data.detailSubjectName = data.acctName;
                            } //modify by sunshuaishuai 20210312 end
                            else{
                                data.detailSubjectNo = $("#busiId").val();
                                data.detailSubjectName = getBusiSubjectNameVal();
                            }
                            if($("#finprodType").val() == FINPROD_TYPE_F35) {
                                data.detailSubjectNo = $("#busiId").val();
                                data.detailSubjectName = $("#busiId").val();
                            }
                            data.genDetsubFlag = 'Y';
                        } else {
                            data.genDetsubFlag = 'N';
                        }
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
                //modify by sunshuaishuai 修改时，删除没有效果 20210305
                table.removeRow(data);
                layui.layer.alert("删除成功");
                table.events();
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
        //add by wujie on 20201116 start
        object.invAim = $("#invAim").val();
        object.profitType = $("#profitType").val();
        //add by wujie on 20201116 end
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
    	var currentSubjectName = document.getElementById('busiId').getAttribute("data-id");
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
            if(OPT_TYPE=='modify' || OPT_TYPE=='copy' && busiDetailTypeFlag==true){

            }else{
                // 增加check，输入框资产代码必须是下拉框中的值
                 var dataKeyArr = [];
                $("#busiId").parent().find("tr").each(function(){
                    dataKeyArr.push($(this).attr("data-key"));
                });// 列表中的值
                var alt = $("#busiId").attr("alt");// 当前输入的值
                if(dataKeyArr.indexOf(alt) == -1){ // 输入不存在的值
                    if(BUSI_ID && BUSI_ID != null && BUSI_ID != undefined && BUSI_ID == $("#busiId").val()){
                    }
                    else{
                        $("#busiId").val("");
                    }
                }
           }


        var object = $.serializeToObject('#submitForm');
        var workflowIdNow = $("#workflowId").val();
        if(workflowIdNow != null || workflowIdNow.length > 0){
        	object.workflowId = workflowIdNow;
        }
        //资产名称
        object.busiName = getBusiSubjectNameVal();
        // 业务明细类型
        object.busiDetailType = getBusiHiddenVal();
        // 初始化 业务明细代码 = 资产代码
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
         quickInput=false;//modify by sunshuaishuai 20210306 快录标志
    });

    /**
     * add by sunshuaishuai 20210301 保存为模板功能  快录功能 start
     */
    $("#ValManualVoucher003_btn_savetmp").click(function(){

        var object = $.serializeToObject('#submitForm');
        // 初始化 业务明细代码 = 资产代码
        object.pageModel = pageModel;
        object.listAddDto = table.getAllRows();

        if (table != null && table != '') {
            var selArr = table.getAllRows();
            var datas = JSON.stringify(selArr);
        }
        var objParams= JSON.stringify(object);

        Joyin.dialog(JoyinUtil.getLabel("新建手工凭证模板（<i class='iconfont must' style='display:inline'>*</i>必填）"),
            basePath + "valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete003/init",
            "95%", "90%", {pageModel:PAGE_MODEL_NEW,operateType:"save",objParams:objParams,datas:datas},function(){

            });
    });

    // 快录按钮执行事件
    $('#ValManualVoucher003_btn_quickInput').click(function(){
          prodIdQuickInputAction();
    });

    /**
     * 手工凭证模板代码快录
     */
    var prodIdQuickInputAction = function(){
        tmpCode = $('#tmpCode').val();
        booksetId=$('#booksetId').val();
        quickInput = true;

        Joyin.ajax(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/checkTmpCode', {tmpCode:tmpCode,booksetId:booksetId}, function(result) {
            if(result.errorList.length==0){
                initPageInfoAction(tmpCode);
            }else{
                Joyin.error(JoyinUtil.getMessage("MANUAL_VOUCHER_E_013"));
                return;
            }
        });
    }

     /**
      * 画面数据加载
      */
    var initPageInfoAction=function(tmpCode){
        Joyin.ajax(basePath+'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/quickInput', {tmpCode:tmpCode}, function(result){
            if (result){
                bookRemark=result.remark;
                finprodType=result.busiType;
                recordType=result.recordType;
                $("#bookRemark").val(bookRemark).trigger('change');
                $("#finprodType").textSelect('setValue', false);
                $("#finprodType").val(finprodType).trigger('change');
                $("#recordType").val(recordType).trigger('change');
            }
        });
        table.reload();
    }
    /*add by sunshuaishuai 20210301 end */
    
    //页面全部加载完毕后
    $(document).ready(function(){
    	// 审核页面加载时
    	// 业务类型为债券时，显示投资目的
        if ($("#finprodType").val() == 'F01'){
        	$("#invAimDiv").show();
        }
        //add by wujie on 20201112 start
        else if ($("#finprodType").val() == 'F17' && $("#invAim").val() ){
        	$("#invAimDiv").show();
        }
        //add by wujie on 20201112 end
        else{
        	$("#invAimDiv").hide();
        }
    	
        // 业务类型变更时清空资产代码
        $("#finprodType").change(function() {
            $("#busiId").val("");
            // 业务类型为债券时，显示投资目的
            if ($("#finprodType").val() == 'F01'){
            	$("#invAimDiv").show();
            }
            //add by wujie on 20201112 start
            else if ($("#finprodType").val() == 'F17' && $("#profitType").val() == '02'){
            	$("#invAimDiv").show();
            }
            //add by wujie on 20201112 end
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
            //新建和复制时，变更账套代码  清空资产代码，分录中的科目尾号，科目尾号名称，账户代码
            $("#busiId").val("");
            //$("#cashId").val("");
            $('#invAim').val("").trigger("change");
            $("#invAimDiv").hide();

             var selArr = table.getAllRows();
             for (var i = 0; i < selArr.length; i++) {
                var data = table.getRow(i);
               data.detailSubjectNo = "";
               data.detailSubjectName = "";
               data.acctCode="";
                 // 刷新表格
                table.renderRow(data.LAY_TABLE_INDEX, data);
                table.events();
             }
            $("#profitType").val("")//add by wujie on 20201112

        });
        
        
        $("#busiId").change(function() {
            busiDetailTypeFlag=false;
            /*add by sunshuaishuai 快录或资产代码变更时：输入资产代码带出科目尾号和名称 20210303 start*/
            if(quickInput==true ||  $.trim($("#busiId").val())!=""){
                   var selArr = table.getAllRows();
                   var datas = JSON.stringify(selArr);
                   //var object = {booksetId: $("#booksetId").val(), busiId: $("#busiId").val()};
                   Joyin.ajax(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getDetailSubjectNo', {booksetId: $("#booksetId").val(),busiId: $("#busiId").val(),finprodType:$("#finprodType").val(),datas:datas}, function(result){
                        if (!result) {
                           return;
                       }
                       for (var i = 0; i < result.data.length; i++) {
                           var rstdata=result.data[i];
                           var data=table.getRow(i);
                           data.subjectName = rstdata.subjectName;
                           data.baseSubjectNature = rstdata.baseSubjectNature;
                           data.acctCode = rstdata.acctCode;
                           data.acctName = rstdata.acctName;
                           // 资产代码对应的业务明细类型
                           data.busiDetailType = $("#busiId_hidden").val();
                           if ("Y" == rstdata.genDetsubFlag && $("#busiId").val() != "") {
                               // 期限品种 不为空
                               if('Y' == rstdata.termTypeFlag){
                                   // 科目尾号 = 资产代码对应的期限品种
                                   data.detailSubjectNo = rstdata.termType;
                                   // 尾号名称= 资产代码对应的期限品种对应的数据字典名称
                                   data.detailSubjectName = rstdata.termTypeFmt;
                               }else if((data.acctName && data.acctName!="") || (rstdata.subjectNo=='10020101' || rstdata.subjectNo=='10020201'
                                        || rstdata.subjectNo=='10210101' || rstdata.subjectNo=='10310101' || rstdata.subjectNo=='10310201'
                                        || rstdata.subjectNo=='10310301') && $("#finprodType").val()!="F20"){
                                    data.detailSubjectNo =  data.acctCode;
                                    data.detailSubjectName = data.acctName;
                                }
                               else{
                                   data.detailSubjectNo = $("#busiId").val();
                                   data.detailSubjectName = getBusiSubjectNameVal();
                               }
                               if($("#finprodType").val() == FINPROD_TYPE_F35) {
                                   data.detailSubjectNo = $("#busiId").val();
                                   data.detailSubjectName = $("#busiId").val();
                               }
                               data.genDetsubFlag = 'Y';
                           } else {
                               data.genDetsubFlag = 'N';
                           }
                            // 刷新表格
                           table.renderRow(i, data);
                           table.events();
                       }
                    });

            }
            /*add by sunshuaishuai 20210303 end*/
        	var object = $.serializeToObject('#submitForm');
        	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher003/getInvAim', object, function(result){
        		$("#profitType").val("");//add by wujie on 20201111
        		var data = result.data;
        		if(data && data.busiDetailType == 'F01'){
        			$('#invAim').val(data.invAim).trigger("change");
        		}
        		//add by wujie on 20201110 start
        		else if(data && data.busiDetailType == 'F17' && data.profitType == '02'){
        			$("#invAimDiv").show();
        			$('#invAim').val(data.invAim).trigger("change");
        			$("#profitType").val(data.profitType);
        		}
        		else{
        			if ($("#finprodType").val() == 'F01'){
            			$("#invAimDiv").show();
            		}else{
            			$('#invAim').val("").trigger("change");
            			$("#invAimDiv").hide();
            		}
        		}
        		//add by wujie on 20201110 end
        	});
        });

    });
});