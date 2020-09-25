/**
 * 风险事件新建
 * 
 * @auth wangzhen
 */
(function() {
	var jqModal = $('#submitForm');
	var count=0;
	
	var divPzInfo=$('#groupTable');
	var varSt='$'+'{';
	var varEt='}';
	$('#btn-tzz-add').click(function(){
		 if(pageModel == PAGE_MODEL_REFERENCE){
			 return;
		 }
    	count++;
    	var divStr=navHtmlBag();
    	divPzInfo.append(divStr);
    	var table = $("#dataTable_character_item"+count).joyintable(options);
    	table.bind('click', '.delt', function(data, index) {
    		table.deleteRow(index);
        });
    	//$(".manager_fh").attr('data-result', SUP_CONNECTOR).textSelect();
    	//$(".manager_rr").attr('data-result', JSON.stringify(INDICATOR_PARAM)).textSelect();
    	$(".btn-detail-del").layButton();

    });
	
	$('#groupTable').on('click','.btn-detail-del',function() {
		var jqBtn = $(this);
		var jqDivId = jqBtn.closest('div').attr('id');
		$("#"+jqDivId).remove();
		count--;
	});
	
	//新增时
	function  navHtmlBag(){
		var divStr='';
		//<table class="layui-table" id="dataTable_character_item"></table>
		divStr+='<div id="table_character_item'+count+'" class="pro-module-item">';
		divStr+='<table class="layui-table" name="dataTable_character_item" id="dataTable_character_item'+count+'"></table>';
		divStr+='<a class="layui-btn btn-delete btn-detail-del" style="padding:0px"></a>';
		divStr+='</div>';
        /*divStr+='<div class="input-inline select-ind-fh manager_fh_fh">';
        divStr+='<select type="text" id="manager_fh" name="manager_fh" formatoption="false" class="input-select  manager_fh"  placeholder="请输入内容"></select>'; 
        divStr+='</div>';
    	divStr+='<div class="input-inline select-ind-rr">';
        divStr+='<select type="text" id="manager_rr" name="manager_rr" formatoption="false" class="input-select   manager_rr" placeholder="请输入内容" ></select>';  
        divStr+='</div>';
    	divStr+='<a class="layui-btn btn-delete btn-detail-del" style="padding:0px"></a>';
    	divStr+='</div>';*/
    	return divStr;
	}	
	function changeFunction(obj){
		var list = [];
		list = obj[0];
		$.each(list,function(i,v){
			dataTable_character_item.layOption.data.push(v);
		})
		if(obj.length>0){
			count=0;
			for(var i=1;i<obj.length;i++){
				count++;
		    	var divStr=navHtmlBag();
		    	divPzInfo.append(divStr);
		    	var table = $("#dataTable_character_item"+count).joyintable(options);
		    	list = obj[i];
				$.each(list,function(i,v){
					table.layOption.data.push(v);
				})
		    	deleteRowtable(table);
		    	$(".btn-detail-del").layButton();
			}
		}
	}
	/**
	 *TISHKBS-639
	 *	风控事项管理，当特征值列表有三个的时候，在第二个列表点击删除会删除第三个列表中的数据
	 */
	
	function deleteRowtable(table){
		table.bind('click', '.delt', function(data, index) {
			table.deleteRow(index);
		});
	}
	
	var buttons = ['delt'];
	/**
     * 定义数据表格
     */
    var addbtn = (pageModel == PAGE_MODEL_NEW) || ($("#wfl_operType").val() == 'NEW');
	var options = {
			searchOnInit : true,
			loadonce : true,
			rowno : true,
			page : false,
			checkbox : false,
			height:200,
			addbtn : true,
        cols : [{field: 'groupId',visible: false
        }, {
        	field : 'itemCode',
			width : 160,
			title : 'RskEvent002.itemCode',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : RISK_EVENT_CHARA_TYPE
			}
		}, {
			field : 'itemAnoName',
			width : 130,
			title : 'RskEvent002.itemAnoName',
			edit : true,
			sortable : false
		},{
			field : 'matchType',
			width : 130,
			title : 'RskEvent002.matchType',
			sortable : false,
			edit : true,
			editOption : {
				showField:'text',
				type : 'select',
				result : SUP_OPERATOR
			}
		},{
			field : 'itemValue',
			width : 190,
			title : 'RskEvent002.itemValue',
			edit : true,
			sortable : false
		}, {
			field : 'offsetVal',
			width : 130,
			title : 'RskEvent002.offsetVal',
			edit : true,
			type: 'number',
			digit: 0,
			sortable : false
		},{
			field : 'offsetType',
			width : 170,
			title : 'RskEvent002.offsetType',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : SUP_CONNECTOR
			}
		},{
			field : 'offsetUnit',
			width : 190,
			title : 'RskEvent002.offsetUnit',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : RISK_EVENT_CHARA_OFFSET_UNIT
			}
		},
		{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
		],
         
    };
	/**
	 * 定义数据表格
	 */
	var dataTable_character_item = $('#dataTable_character_item').joyintable({
		searchOnInit : true,
		loadonce : true,
		rowno : true,
		page : false,
		checkbox : false,
		addbtn : true,
		height : 200,
		cols : [{
			field : 'itemCode',
			width : 160,
			title : 'RskEvent002.itemCode',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : RISK_EVENT_CHARA_TYPE
			}
		}, {
			field : 'itemAnoName',
			width : 130,
			title : 'RskEvent002.itemAnoName',
			edit : true,
			sortable : false
		},{
			field : 'matchType',
			width : 130,
			title : 'RskEvent002.matchType',
			sortable : false,
			edit : true,
			editOption : {
				showField:'text',
				type : 'select',
				result : SUP_OPERATOR
			}
		},{
			field : 'itemValue',
			width : 190,
			title : 'RskEvent002.itemValue',
			edit : true,
			sortable : false
		}, {
			field : 'offsetVal',
			width : 130,
			title : 'RskEvent002.offsetVal',
			edit : true,
			type: 'number',
			digit: 0,
			sortable : false
		},{
			field : 'offsetType',
			width : 170,
			title : 'RskEvent002.offsetType',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : SUP_CONNECTOR
			}
		},{
			field : 'offsetUnit',
			width : 190,
			title : 'RskEvent002.offsetUnit',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : RISK_EVENT_CHARA_OFFSET_UNIT
			}
		},
		{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
		],
		done : function(table, allData) {
		}
	});
	
	/**
     * 绑定删除事件
     */
	dataTable_character_item.bind('click', '.delt', function(data, index) {
		dataTable_character_item.deleteRow(index);
    });
	
	/**
	 * 获取页面参数
	 */
	var getParams = function() {
		var params = $.serializeToObject('#submitForm');
		params.remark = $('#remark').val();
		return params;
	}
	
	/**
	 * 保存或提交按钮执行事件
	 */
	var onSaveOrSubmitAction = function(operateType){
		var params = getParams();
		if($("#isCalcIndicator")[0].checked){
			params.isCalcIndicator = 'Y';
		}else{
			params.isCalcIndicator = 'N';
		}
		params.operateType = operateType;
		params.pageModel = pageModel;
		/*if(!dataTable_character_item.getAllRows()||dataTable_character_item.getAllRows().length == 0){
		Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0066'));
        return;
		}*/
		var selList = [];
		selList[0]=dataTable_character_item.getAllRows();
		if(count>0){
			for(var i=1;i<=count;i++){
				var tab = $("#dataTable_character_item"+i).data("joyintable");
				/*if(!tab.getAllRows()||tab.getAllRows().length == 0){
					Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0066'));
			        return;
					}*/
				selList[i]=tab.getAllRows();
			}
		}
		
		/*$('#table_character_item').find("[name=dataTable_character_item]").each(function(index){
			if(index!=0){
				if($(this).val()==""){
					flag=false;
					return false;
				}
			}
			selList[index] = $(this).getAllRows();
			//exprStr+= $(this).val()+varSt+jqModal.find("[name=manager_rr]:eq("+index+")").val()+varEt;
			//expeStrChs+= $(this).val()+jqModal.find("[name=manager_rr]:eq("+index+")").find("option:selected").text();
		 });*/
		params.paramGroup = selList;
		//params.paramList = dataTable_character_item.getAllRows();
		/*if(!dataTable_character_item.getAllRows()||dataTable_character_item.getAllRows().length == 0){
			Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0066'));
            return;
		}*/
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rskevent/RskEvent002/check',params, function(result) {
    		Joyin.confirm(result.data, function() {
                	if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rskevent/RskEvent002/save', params, "RskEvent002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rskevent/RskEvent002/save', params, "RskEvent002_btn_submit");
                    }
                });
            });
	};
	
	var init = function(){
		if(pageModel!=PAGE_MODEL_NEW){
			$("#eventCode").attr("disabled","disabled");
		}
		if(entity && entity.paramGroup){
			changeFunction(entity.paramGroup);
		}
		
	}
	
	
	/*
	 * 
	 * 画面加载完成
	 */
	$(function() {
		
		init();
		
		// 保存按钮执行事件
		$('#RskEvent002_btn_save').click(function(){
			onSaveOrSubmitAction('save');
		});
		// 提交按钮执行事件
		$('#RskEvent002_btn_submit').click(function(){
			onSaveOrSubmitAction('submit');
		});
		
    });

}());