/**
 * 
 */
var ReportPageManage003 = function(global) {
	 
	var screenTypeTran = {
			singleValueDate:"01",
			singleValueNumber:"02",
			singleValueText:"03"
	}
    /**
     * 获取选择查询参数
     */
    function getSelectData(){
    	var object = $.serializeToObject('#submitForm');
    	// 启用状态   	
    	if(!object.reportType){
        	object.reportType = 'Y';
		}
    	var tableData = table.getAllRows();
    	// layui-form-checkbox
    	$(".layui-table-fixed tbody .layui-unselect.layui-form-checkbox").each(function(i){                
           // 遍历 checkbox.
    		console.log("第"+ i);
 		   if($(this).is('.layui-form-checked')){
 			  console.log("第"+ i + "已选择");
 		   };
    	});
    	object.repScreenParam = tableData;
    	return object;
    };
    
    /**
     * 定义数据表格
     */
    var table=$("#reportModel").joyintable({
    	height:200,
    	page:false,
    	checkbox :false,
    	loadonce : true,
        cols:[
            {field:'screenIndex',sortable:false,visible: false, title: '参数序号'},
            {field:'screenId',sortable:false,visible: false, title: '主键Id'},
            {field:'repId',sortable:false,visible: false, title: '报表Id'},
            {field:'screenType',sortable:false,  title: '条件类型',render: function (data, type, row, meta) {
	            	if(data == '01') {
	            		return '<span style="disabled:true;">日期</span>';
	            	}else if(data == '02'){
	            		return '<span style="disabled:true;">文本</span>';
	            	}else{
	            		return '<span style="disabled:true;">参数</span>';
	            	}
	            }
            },
            {field:'screenKey',sortable:false, title: '条件Id'},
            {field:'screenName',sortable:false, title: '查询条件'},
            {field:'screenValue',sortable:false, title: '字典代码', edit:true,editOption:{type:'select',result:ADMINISTRATIVE_TYPE1,initValue:"1"}
            }
            ,
            {field:'screenStatus', sortable:false, title: '是否启用',edit:true,editOption:{type:'select',result:effective,initValue:"01"}, render: function (data, type, row, meta) {
            	if(row.screenStatus == 'Y') {
            		return '<span style="disabled:true;" class="layui-badge-rim layui-bg-blue">启用</span>';
            	}else{
            		return '<span style="disabled:true;" class="layui-badge-rim layui-bg-blue">禁用</span>';
            	}
            }
            }
        ],
    });
    /**
     * 报表模板改变时；
     */
    function modelNameChange(param){
    	table.load();
    	table.clear(null);
    	var modelName = $('#modelName').val();
    	// var uriStr = getUri(modelName);
//    	var uriStr = param.temLoad +"/"+ param.code;
    	var r = {uri:param.code,folder:param.temLoad};//参数：模板代码和模板子路径
//		var control = getReportInputs(r);
		Joyin.ajax('getReportInputs', r,function(result) {
			var control = result.data;
			var screenType = "02";
	    	$.each(control, function(i,c){
	    		var screenName = "产品名称";
	    		switch(c.type){
	    		case "singleValueDate":
	    			screenType = "01";
	    		  break;
	    		case "singleValueText":
	    			screenType = "03";
	    		  break;
	    		default:
	    			screenType = "02";
	    		}
	    		
	    		if(c.id =='endDate'){
	    			screenName = '结束日期';
	    		}else if(c.id =='startDate'){
	    			screenName = '开始日期';
	    		}
	    		
				if(c.id != 'portname'&&c.id != 'rep_id'){
					table.addRow({screenIndex:i + 1,screenType:screenType,screenKey:c.id,screenName:screenName,screenStatus:'Y'});
				}
			});
		});
    };
    // checkbox样式调整;
    function checkClassSet(){
    	var tableData = table.getAllRows();
    	$(".layui-table-fixed tbody .layui-unselect.layui-form-checkbox").each(function(i){                
            // 遍历 checkbox.
    		if(tableData[i].screenStatus == 'Y'){
    			this.classList.add("layui-form-checked");
    		}
     	});
    	$(".layui-table-fixed tbody tr").each(function(i){                
            // 遍历 checkbox.
    		if(tableData[i].screenStatus == 'Y'){
    			this.classList.add("layui-form-checked");
    		}
     	});
    	
    }
    /**
     * uri获取
     * @param modelName
     * @returns
     */
    function getUri(modelName){
    	if(modelName == "01"){
    		return "/POC/BalanceReport";
    	}else{
    		return "/POC/PositionInIndustry";
    	}
    };
    /**
     * 预览
     */
    function loadView(){
    	 var templateId = $("#templateId").val();
    	 if(!templateId){
    		 Joyin.alert("请选择模板");
    		 return;
    	 }
    	 Joyin.dialog(JoyinUtil.getLabel('预览'), basePath + 'disclosure/information/reveal/detailshow/RepDetailsShow002/init',
    			 	'95%', '95%', null,function() {
                 });

    };
    /**
     * 选择模板
     * @returns
     */
    function modelSelect(){
   	 
   	 Joyin.dialog(JoyinUtil.getLabel('模板选择'), basePath + 'disclosure/information/config/reportmodelmanage/ReportModelManage001/init',
   			 	'90%', '90%', {isModelSelect:"yes"},function(result) {
   			 		var param =result;
   			 	modelSelectParam(top.modelSelectParam);
                });

   };
    function modelSelectParam(param){
    	var dataParam = param[0].name;
    	$('#templateName').val(dataParam);
    	$('#reportName').val(dataParam);
    	$('#templatePath').val(param[0].temLoad);
    	$('#templateId').val(param[0].code);
    	modelNameChange(param[0]);
    };
    /**
     * 保存页面配置
     * @returns
     */
    function save(){
    	// 是否输入报表名称
    	if(!$('#templateName').val()){
    		Joyin.alert("请输入模板名称", 7,null, null);
    		return;
    	}
    	if(!$('#reportName').val()){
    		Joyin.alert("请输入报表名称", 7,null, null);
    		return;
    	}
    	if(!$('#reportStyle').val()){
    		Joyin.alert("请输入报表类别", 7,null, null);
    		return;
    	}
    	   Joyin.ajaxJson('saveCheck', {reportName:$('#reportName').val(),repId:$('#repId').val()}, function(result) {
    		   var param = getSelectData();
    		   param.reportCategory = $('#reportStyle').val();
    		   if (result && result.statuCode == "200"){
    			   Joyin.ajaxJson('save', param, function(result) {
    				   Joyin.closeTopWindow();
    			   });
    		   }
       });
    }
    /**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			repId : $.trim($('#repId').val())
		}
	};
	var settedRule = $("#settedRule")
			.joyintable(
					{
						ajax : {
							url : basePath
									+ 'disclosure/information/reportpage/ReportPageManage002/findRulePage',
							type : 'POST',
							data : function() {
								return getSearchParams();
							}
						},
						checkbox : false,
						searchOnInit : true,
						loadonce : true,
						page : false,
						cols : [ {
							field : 'ruleType1Name',
							title : '字段值',
							fixed : true
						}, {
							field : 'ruleType2Name',
							title : '比较条件',
							fixed : true
						}, {
							field : 'ruleType3Name',
							title : '值类型',
							fixed : true
						}, {
							field : 'ruleType4Name',
							title : '值',
							fixed : true
						}, {
							field : 'operation',
							title : 'SysCommon.operation',
							type : 'action',
							buttons : [ 'delt' ]
						}, {
							field : 'ruleType1',
							visible : false
						}, {
							field : 'ruleType2',
							visible : false
						}, {
							field : 'ruleType3',
							visible : false
						}, {
							field : 'ruleType4',
							visible : false
						} ]
					});
	settedRule.bind('click', '.delt', function(data, index) {
		settedRule.removeRow(data);
	});
	/**
	 * 规则增加事件
	 * 
	 * @returns
	 */
	function addrule() {
		var rule = new Object;
		if ($('#ruleType1').select2('data')[0]) {
			rule.ruleType1 = $('#ruleType1').select2('data')[0].id;
			rule.ruleType1Name = $('#ruleType1').select2('data')[0].text;
		} else {
			Joyin.alert("请选择字段值", 7);
			return;
		}

		if ($('#ruleType2').select2('data')[0]) {
			rule.ruleType2 = $('#ruleType2').select2('data')[0].id;
			rule.ruleType2Name = $('#ruleType2').select2('data')[0].text;
		} else {
			Joyin.alert("请选择比较条件", 7);
			return;
		}
		if ($('#ruleType3').select2('data')[0]) {
			rule.ruleType3 = $('#ruleType3').select2('data')[0].id;
			rule.ruleType3Name = $('#ruleType3').select2('data')[0].text;
		} else {
			Joyin.alert("请选择值类型", 7);
			return;
		}
		if (rule.ruleType1 == 'PROFIT_TYPE') {
			if ($('#profitType').select2('data')[0]) {
				rule.ruleType4 = $('#profitType').select2('data')[0].id;
				rule.ruleType4Name = $('#profitType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择收益类型", 7);
				return;
			}

		} else if (rule.ruleType1 == 'RAISE_TYPE') {
			if ($('#raiseType').select2('data')[0]) {
				rule.ruleType4 = $('#raiseType').select2('data')[0].id;
				rule.ruleType4Name = $('#raiseType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择募集方式", 7);
				return;
			}
		} else if (rule.ruleType1 == 'ISSUE_MODE') {
			if ($('#issueMode').select2('data')[0]) {
				rule.ruleType4 = $('#issueMode').select2('data')[0].id;
				rule.ruleType4Name = $('#issueMode').select2('data')[0].text;
			} else {
				Joyin.alert("请选择发行方式", 7);
				return;
			}
		} else if (rule.ruleType1 == 'ISSUE_STATUS') {
			if ($('#issueStauts').select2('data')[0]) {
				rule.ruleType4 = $('#issueStauts').select2('data')[0].id;
				rule.ruleType4Name = $('#issueStauts').select2('data')[0].text;
			} else {
				Joyin.alert("请选择发行状态", 7);
				return;
			}
		} else if (rule.ruleType1 == 'OPEN_MODE') {
			if ($('#openMode').select2('data')[0]) {
				rule.ruleType4 = $('#openMode').select2('data')[0].id;
				rule.ruleType4Name = $('#openMode').select2('data')[0].text;
			} else {
				Joyin.alert("请选择开放模式", 7);
				return;
			}

		} else if (rule.ruleType1 == 'IS_INDEPE_ACC') {
			if ($('#isIndepeAcc').select2('data')[0]) {
				rule.ruleType4 = $('#isIndepeAcc').select2('data')[0].id;
				rule.ruleType4Name = $('#isIndepeAcc').select2('data')[0].text;
			} else {
				Joyin.alert("请选择是否", 7);
				return;
			}

		} else if (rule.ruleType1 == 'PROD_TYPE') {
			if ($('#prodType').select2('data')[0]) {
				rule.ruleType4 = $('#prodType').select2('data')[0].id;
				rule.ruleType4Name = $('#prodType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择产品类型", 7);
				return;
			}

		} else if (rule.ruleType1 == 'FINA_TYPE') {
			if ($('#finaType').select2('data')[0]) {
				rule.ruleType4 = $('#finaType').select2('data')[0].id;
				rule.ruleType4Name = $('#finaType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择理财类型", 7);
				return;
			}

		} else if (rule.ruleType1 == 'PROD_TERM_TYPE') {
			if ($('#prodTermType').select2('data')[0]) {
				rule.ruleType4 = $('#prodTermType').select2('data')[0].id;
				rule.ruleType4Name = $('#prodTermType').select2('data')[0].text;
			} else {
				Joyin.alert("请选择产品期限类型", 7);
				return;
			}

		} else {
			if (rule.ruleType3 == '03') {
				// 平台日
				// rule.ruleType4 = rule.ruleType3;
				// rule.ruleType4Name = rule.ruleType3Name;
			} else {
				if ($('#chooseDate').val()) {
					rule.ruleType4 = $('#chooseDate').val();
					rule.ruleType4Name = $('#chooseDate').val();
				} else {
					Joyin.alert("请选择日期", 7);
					return;
				}

			}
		}
		var obj = settedRule.getAllRows();
		var index = 0;
		for ( var i in obj) {
			if (obj[i].ruleType1 == rule.ruleType1
					&& obj[i].ruleType2 == rule.ruleType2
					&& obj[i].ruleType3 == rule.ruleType3
					&& obj[i].ruleType4 == rule.ruleType4) {
				index++;
			}
		}
		if (index == 0) {
			settedRule.addRow(rule);
		} else {
			Joyin.alert("规则已存在，请重新设置！", 7);
			return;
		}
	}
	function ruleType1Change() {
		var ruleType1 = $('#ruleType1').select2('data')[0].id;
		var ruleType3;
		if ($('#ruleType3').val()) {
			ruleType3 = $('#ruleType3').select2('data')[0].id;
		}
		if (ruleType1 == 'PROFIT_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').show();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'RAISE_TYPE') {
			$('#raiseTypeSelect').show();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'ISSUE_MODE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').show();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'ISSUE_STATUS') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').show();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'OPEN_MODE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').show();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'IS_INDEPE_ACC') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').show();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'PROD_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').show();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'FINA_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').show();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'PROD_TERM_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').show();
		} else {
			if (ruleType3 == '03' || !$('#ruleType3').val()) {
				$('#raiseTypeSelect').hide();
				$('#chooseDateSelect').hide();
				$('#profitTypeSelect').hide();
				$('#issueModeSelect').hide();
				$('#issueStautsSelect').hide();
				$('#openModeSelect').hide();
				$('#isIndepeAccSelect').hide();
				$('#prodTypeSelect').hide();
				$('#finaTypeSelect').hide();
				$('#prodTermTypeSelect').hide();
			} else {
				$('#raiseTypeSelect').hide();
				$('#chooseDateSelect').show();
				$('#profitTypeSelect').hide();
				$('#issueModeSelect').hide();
				$('#issueStautsSelect').hide();
				$('#openModeSelect').hide();
				$('#isIndepeAccSelect').hide();
				$('#prodTypeSelect').hide();
				$('#finaTypeSelect').hide();
				$('#prodTermTypeSelect').hide();
			}
		}

	}
	function ruleType3Change() {
		var ruleType1 = $('#ruleType1').select2('data')[0].id;
		var ruleType3;
		if ($('#ruleType3').val()) {
			ruleType3 = $('#ruleType3').select2('data')[0].id;
		}
		if (ruleType1 == 'PROFIT_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').show();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'RAISE_TYPE') {
			$('#raiseTypeSelect').show();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'ISSUE_MODE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').show();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'ISSUE_STATUS') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').show();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'OPEN_MODE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').show();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'IS_INDEPE_ACC') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').show();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'PROD_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').show();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'FINA_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').show();
			$('#prodTermTypeSelect').hide();
		} else if (ruleType1 == 'PROD_TERM_TYPE') {
			$('#raiseTypeSelect').hide();
			$('#chooseDateSelect').hide();
			$('#profitTypeSelect').hide();
			$('#issueModeSelect').hide();
			$('#issueStautsSelect').hide();
			$('#openModeSelect').hide();
			$('#isIndepeAccSelect').hide();
			$('#prodTypeSelect').hide();
			$('#finaTypeSelect').hide();
			$('#prodTermTypeSelect').show();
		} else {
			if (ruleType3 == '03' || !$('#ruleType3').val()) {
				$('#raiseTypeSelect').hide();
				$('#chooseDateSelect').hide();
				$('#profitTypeSelect').hide();
				$('#issueModeSelect').hide();
				$('#issueStautsSelect').hide();
				$('#openModeSelect').hide();
				$('#isIndepeAccSelect').hide();
				$('#prodTypeSelect').hide();
				$('#finaTypeSelect').hide();
				$('#prodTermTypeSelect').hide();
			} else {
				$('#raiseTypeSelect').hide();
				$('#chooseDateSelect').show();
				$('#profitTypeSelect').hide();
				$('#issueModeSelect').hide();
				$('#issueStautsSelect').hide();
				$('#openModeSelect').hide();
				$('#isIndepeAccSelect').hide();
				$('#prodTypeSelect').hide();
				$('#finaTypeSelect').hide();
				$('#prodTermTypeSelect').hide();
			}
		}
	}
	/**
	 * 预览产品列表
	 * @returns
	 */
	function previewProdSelect() {
		var object = $.serializeToObject('#submitForm', true);
		object.pageModel = pageModel;
		object.onlyView = '01';
		var settedRule = object.settedRule;
		Joyin
				.dialog(
						JoyinUtil.getLabel('预览产品列表'),
						basePath
								+ 'disclosure/information/manage/workmanage/ReportWorkManage003/init',
						'90%', '90%', {
							settedRule : JSON.stringify(settedRule),
							onlyView : '01',
							object : object
						}, function(result) {
							var param = result;
							//addProd(top.addProd);
							//getSelectData(top.getSelectData);
						})
	}
    $(function() {
    	$('#raiseTypeSelect').hide();
		$('#chooseDateSelect').hide();
		$('#profitTypeSelect').hide();
		$('#issueModeSelect').hide();
		$('#issueStautsSelect').hide();
		$('#openModeSelect').hide();
		$('#isIndepeAccSelect').hide();
		$('#prodTypeSelect').hide();
		$('#finaTypeSelect').hide();
		$('#prodTermTypeSelect').hide();
		//规则增加事件
		$('#ReportPageManage002_btn_add').click(addrule);
		//规则1改变事件
		$('#ruleType1').change(ruleType1Change);
		//规则3改变事件
		$('#ruleType3').change(ruleType3Change);
		//预览产品列表点击事件
		$('#ReportPageManage002_btn_previewProdList').click(previewProdSelect);
    	// 模板名称不可改
         $('#templateName').attr("disabled","true");
    	for(var i in RepScreenList){
    		table.load();
    		table.addRow({
    			screenIndex:RepScreenList[i].screenIndex,
    			screenId:RepScreenList[i].screenId,
    			repId:RepScreenList[i].repId,
    			screenType:RepScreenList[i].screenType,
    			screenKey:RepScreenList[i].screenKey,
    			screenName:RepScreenList[i].screenName,
    			screenValue:RepScreenList[i].screenValue,
    			screenStatus:RepScreenList[i].screenStatus
    			});
    		}
        // 绑定按钮事件
        $('#reportPage003_btn_save').click(save);
        // $('#templateName').change(modelNameChange);
        $('#reportPage003_btn_load').click(loadView);
        $('#reportModel001_select').click(modelSelect);
        $('#effcitiveCheckbox').attr('checked',true);
        $('#effcitiveCheckbox').attr('checked','checked');
        $('#effcitiveCheckbox').prop('checked',true);
        if($('#reportType').val() != "N"){
        $('.layui-unselect.layui-form-switch').each(function(i){                
    			this.classList.add("layui-form-onswitch");
    			});
        $('.layui-unselect.layui-form-switch em').each(function(i){   
        	var textVar = this;  
			this.innerText = "启用";
			});
        }              
    });   
    global.getSelectData = getSelectData;
}(this);