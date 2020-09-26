$(function () {
	//当前是否包含流程
	var workflowIdNow = $("#workflowId").val();
	
	var buttons = [{text: '&#xe614;',ignore:true,clas: 'config',title: '费率调整'}, { text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
	var buttons1 = [{ text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
	
    /**
     * 定义数据表格
     */
    var table_cpfy=$("#dataTable_cpfy").joyintable({
        ajax: {
            url: basePath + "valuation/finncproductbuild/FinNCProductBuild001/queryFeeList",
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),noCashType2:'C04',workflowId:workflowIdNow
           }
        },
        searchOnInit: true,
        checkbox: false,
        height: 165,
        addbtn: true,
        cols: [
               {field:'cashId', title: '费用uuid',width:120, visible:false},
    	       {field:'cashType2', title: '费用类型',width:210, edit:true, editOption:{id: 'cashType2', type:'select', result: CASH_TYPE_2}},
               {field:'yield', title: '费率(%)',width:200, edit: true, align: 'right', type:'number', digit:2},
    	       {field:'baseType', title: '计算基数', width:200, edit:true, editOption:{id: 'baseType', type:'select', result: BASE_TYPE}},
    	       {field:'baseDateType', title: '计费日期类型',width:200, title: '计费日期类型', edit:true, editOption:{id: 'baseDateType', type:'select', result: BASE_DATE_TYPE}},
               {field:'basis', title: '计算基础',width:200, edit:true, editOption:{id: 'basis', type:'select', result: BASIS}},
               {field:'effDate', title: '首次计提日',width:220, edit:true, type:'date'},
               {field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
        ],
        loadonce:true,
        page:false
    });
    
    /**
     * 定义数据表格
     */
    var table_yjbj=$("#dataTable_yjbj").joyintable({
        ajax: {
            url: basePath + "valuation/finncproductbuild/FinNCProductBuild001/queryfloatFeeList2",
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),cashType2:'C04',workflowId:workflowIdNow
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 150,
        addbtn: true,
        cols: [
           {field:'basicLevel', title: '业绩层级',width:250, edit: true, type:'number', digit:0},
           {field:'baseRuleId', title: '业绩基准',width:250, edit:true, editOption:{type: 'textbox'}},
           {field:'coefficient',  type:"money",title: '系数',width:250, edit: true, type:'number'},
           {field:'spread',  type:"money",title: '利差',width:240, edit: true,  type:'number'},
           {field:'bankRatio',  type:"money",title: '银行收取比例（%）',width:250, edit: true,  type:'number', digit:0},
           {field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons1}
        ],
        loadonce:true,
        page:false
    });
	
    /**
     * 定义数据表格
     */
    var table_jtzq=$("#dataTable_jtzq").joyintable({
        ajax: {
           url: basePath + "valuation/finncproductbuild/FinNCProductBuild001/queryfloatFeeList1",
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),cashType2:'C04',workflowId:workflowIdNow
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 150,
        addbtn: true,
        cols: [
           {field:'jtStrDate', title: '计提周期开始日',width:420, edit:true, type:'date'},
           {field:'jtEndDate', title: '计提周期结束日',width:420, edit:true, type:'date'},
           {field:'jtDate', title: '计提日',width:410, edit:true, type:'date'},
           {field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons1}
        ],
        loadonce:true,
        page:false
    });
    
    /**
     * 定义数据表格
     */
    var table_zjzh=$("#dataTable_zjzh").joyintable({
        ajax: {
            url : basePath + "valuation/finncproductbuild/FinNCProductBuild001/getFinprodAcct",
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),workflowId:workflowIdNow
           }
        },
        searchOnInit: true,
        checkbox: false,
        height: 150,
        addbtn: true,
        cols: [
               {field:'acctNo', title:'网点账号', visible:false},
               {field:'acctName', title:'资金账户名称', edit:true, editOption:{id: 'acctName', type:'select', url:JoyinUtil.getRealPath('/valuation/finncproductbuild/FinNCProductBuild001/getAcctName'),showField :'id'}},
               {field:'acctType', title:'资金账户类型', edit:true, editOption:{id: 'acctType', type:'select', result: ACCT_TYPE}},
               {field:'busiAcctNo', title: '资金账户账号'},
               {field:'ccy', title: '币种', edit:true, editOption:{id: 'ccy', type:'select', result: CCY}},
               {field:'acctName1', title: '中文名'},
               {field:'bankDep', title: '开户行'},
               {field:'inOutBank', title: '行内行外', edit:true, editOption:{id: 'inOutBank', type:'select', result: IN_OUT_BANK}},
               {field:'bankDepType', title: '开户行类型'},
               {field:'corrNo', title: '开户行联行行号'},
               {field:'largePayno', title: '大额支付号'},
               {field:'swiftCode', title: 'Swift Code'},
               {field:'remark', title: '备注'},
               {field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons1}
        ],
            editCell: function(value, data, field, cell, column) {
            setTimeout(function() {
                // 取得联动处理数据
                Joyin.ajax(basePath + 'valuation/finncproductbuild/FinNCProductBuild001/getFinprodAcct', {acctName: value}, function(result){
                    if (result.length == 0) {
                        return;
                    }
                    var rstdata = result[0];
                    data.acctName = rstdata.acctName;
                    data.acctType = rstdata.acctType;
                    data.acctNo = rstdata.acctNo;
                    data.busiAcctNo = rstdata.busiAcctNo;
                    data.ccy = rstdata.ccy;
                    data.acctName1 = rstdata.acctName;
                    data.bankDep = rstdata.bankDep;
                    data.inOutBank = rstdata.inOutBank;
                    data.bankDepType = rstdata.bankDepType;
                    data.corrNo = rstdata.corrNo;
                    data.largePayno = rstdata.largePayno;
                    data.swiftCode = rstdata.swiftCode;
                    data.remark = rstdata.remark;
                    // 刷新表格
                    table_zjzh.renderRow(data.LAY_TABLE_INDEX, data);
                    table_zjzh.events();
                });
            }, 10);
        },
        loadonce:true,
        page:false
    });
    
    /**
     * 定义数据表格
     */
//    var table_zqzh=$("#dataTable_zqzh").joyintable({
//        ajax: {
//            url: basePath + "valuation/finncproductbuild/FinNCProductBuild001/getSecAccount",
//            type: 'POST',
//            data: {finprodId: $("#finprodId").val()
//           }
//        },
//        searchOnInit: true,
//        checkbox: false,
//        height: 200,
//        addbtn: true,
//        cols: [
//               {field:'finprodId', title:'产品代码',width:200, visible:false},
//               {field:'acctId', title:'证券账户账号',width:200},
//               {field:'acctCode', title:'证券账户名称',width:200},
//               {field:'brokerName', title: '托管机构',width:200},
//               {field:'acctDesc', title: '交易场所',width:200},
//               {field:'acctNo', title: '资金账户账号',width:120},
//               {field:'acctName', title:'资金账户名称',width:120},
//               {field:'ccy', title: '币种'},
//               {field:'remark', title: '备注'},
//               {field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons1}
//        ],
//        loadonce:true,
//        page:false
//    });
    
	/**
	 * 修改/新建
	 */
	var saveOrSubmit = function(operateType) {
		
		var object = $.serializeToObject('#submitForm');
		object.pageModel = pageModel;
		object.operateType = operateType;
		object.listValProductInfoAcctDto_ZJ = table_zjzh.getAllRows();
		object.listValProductInfoFeeDto_GD = table_cpfy.getAllRows();
		object.listValProductInfoFeeDto_FD1 = table_jtzq.getAllRows();
		object.listValProductInfoFeeDto_FD2 = table_yjbj.getAllRows();
		
		Joyin.ajaxJson(basePath + 'valuation/finncproductbuild/FinNCProductBuild002/check', object, function(result) {
		    Joyin.confirm(result.data, function() {
		    	if(operateType === 'save'){
				    WorkFlow.doSaveList(basePath + 'valuation/finncproductbuild/FinNCProductBuild002/save', 
								object, "FinNCProductBuild002_btn_save");
				}else{// submit
					WorkFlow.doSubmitList(basePath + 'valuation/finncproductbuild/FinNCProductBuild002/save', 
							object, "FinNCProductBuild002_btn_submit");
				}
		    });
		});
	};
	
	
	/**
	 * 根据操作类型：显示按钮等
	 */
    var initShow = function () {
    	$("#basis1").textSelect('setValue','A/365');
    	var finprodId = $('#finprodId').val();
    	var actRule = $('#actRule').val();
    	var firCalculaDate = $('#firCalculaDate').val();
    	if((finprodId != null && finprodId != "") && (actRule == null || actRule == "") && (firCalculaDate == null || firCalculaDate == "")){
    		$("#isAccrFloManageFee").textSelect('setValue','N');
    	}else{
    		$("#isAccrFloManageFee").textSelect('setValue','Y');
    	}
        var operateType = $('#operateType').val();
        if(operateType === 'add') {
        }else if(operateType === 'edit') {
        	$("#finprodId").attr("disabled", "disabled");
        	$("#profitType").attr("disabled", "disabled");
        	$("#finprodType").attr("disabled", "disabled");
//        	$("#actRule").attr("disabled", "disabled");
        	$("#isAccrFloManageFee").attr("disabled", "disabled");
        }
        
    	tableShow();//部分字段根据产品类型显示
    	
    	termShow();//产品期限
    	
    	fdglfShow();//是否计提浮动管理费
    };
    
    /**
     * 绑定联动
     */
    var linkBind=function() {
    	//参数显示根据产品类型联动
        $("#profitType").change(function(){
        	tableShow();
        });
        //
        $("#vdate").change(function(){
        	termShow();
        });
        
        $("#mdate").change(function(){
        	termShow();
        });
        
        $("#isAccrFloManageFee").change(function(){
        	fdglfShow();
        });
    };
	
    /**
     * 参数显示根据产品类型联动
     */
    var tableShow = function () {
    	var profitType = $('#profitType').val();
    	if(profitType == '01'){
			$("#c_unitvalueDec").show();
			$("#c_unitvalueMode").show();
			$("#c_milionProfitDec").hide();
			$("#c_milionProfitDecMode").hide();
			$("#c_yearYieldDec").hide();
			$("#c_yearYieldDecMode").hide();
    	}else if(profitType == '03'){
			$("#c_unitvalueDec").hide();
			$("#c_unitvalueMode").hide();
			$("#c_milionProfitDec").show();
			$("#c_milionProfitDecMode").show();
			$("#c_yearYieldDec").show();
			$("#c_yearYieldDecMode").show();
    	}
    };
    
    /**
     * 产品期限 = 【到期日-起息日+1】
     */
    var termShow = function () {
    	var vdate = $('#vdate').val();
    	var mdate = $('#mdate').val();
    	if(vdate == "" || mdate == ""){
    		
    	}else {
    	    var startDate = Date.parse(vdate.replace('/-/g','/'));  
    	    var endDate = Date.parse(mdate.replace('/-/g','/'));
    	    var diffDate = (endDate-startDate)+1*24*60*60*1000;  
    	    var termDate = diffDate/(1*24*60*60*1000);  
    	    $("#term").val(termDate).trigger("change");
    	}
    };
    
    /**
     * 是否计提浮动管理费
     */
    var fdglfShow = function () {
    	var isAccrFloManageFee = $('#isAccrFloManageFee').val();
    	if(isAccrFloManageFee == "N"){
    		//隐藏浮动费用窗口
        	$("#div_dataTable_fdglf").hide();
    	}else {
    	    //显示浮动费用窗口
    	    $("#div_dataTable_fdglf").show();
    	}
    };
    
    
	
    
    //页面全部加载完毕后
    $(function() {
        // 绑定按钮事件
        $('#FinNCProductBuild002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        
        $('#FinNCProductBuild002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

    	/**
         * 绑定固定费用删除事件
         */
    	table_cpfy.bind('click', '.delt', function(data, index) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            	table_cpfy.removeRow(data);
                layui.layer.alert("删除成功");
                table_cpfy.events();
            });
        });
    	
    	/**
         * 绑定浮动费用删除事件1
         */
    	table_yjbj.bind('click', '.delt', function(data, index) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            	table_yjbj.removeRow(data);
                layui.layer.alert("删除成功");
                table_yjbj.events();
            });
        });
    	
    	/**
         * 绑定浮动费用删除事件2
         */
    	table_jtzq.bind('click', '.delt', function(data, index) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            	table_jtzq.removeRow(data);
                layui.layer.alert("删除成功");
                table_jtzq.events();
            });
        });
    	
    	/**
         * 绑定资金账户删除事件
         */
    	table_zjzh.bind('click', '.delt', function(data, index) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            	table_zjzh.removeRow(data);
                layui.layer.alert("删除成功");
                table_zjzh.events();
            });
        });
    	
    	/**
         * 绑定证券账户删除事件
         */
//    	table_zqzh.bind('click', '.delt', function(data, index) {
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//            	table_zqzh.removeRow(data);
//                layui.layer.alert("删除成功");
//                table_zqzh.events();
//            });
//        });
    	
    	/**
         * 费率调整
         */
    	table_cpfy.bind('click', '.config', function(data, index) {
    		var listdto=[data];
    		var finprodId = $('#finprodId').val();
    		var finprodType = $('#finprodType').val();
    		Joyin.dialog(JoyinUtil.getLabel("费率调整"),
            JoyinUtil.getRealPath("valuation/finncproductbuild/FinNCProductBuild003/init"),
            "80%", "80%", {finprodId:finprodId, finprodType:finprodType, cashType2:data.cashType2, basis:data.basis, baseType:data.baseType, baseDateType:data.baseDateType},
            function(){
            });
        });

        //根据操作类型：显示按钮等
        initShow();
        
        //绑定联动
        linkBind();
        
    });
});