/**
 * 风险事件关联指标参数
 */
(function() {
	
	/**
	 * 定义数据表格
	 */
	var dataTable = $('#dataTable_relate_param').joyintable({
		searchOnInit : true,
		loadonce : true,
		rowno : true,
		page : false,
		checkbox : false,
		addbtn : true,
		height : function() {
			var offset = $("#dataTable_relate_param").offset().top + 50;
			return 'full-' + (offset + $('#RskEvent003_btn').height());
		}(),
		cols : [{
			field : 'paraCode',
			width : '30%',
			title : 'RskEvent003.paraName',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : IND_PARAM
			}
		},{
			field : 'paraGranularity',
			width : '10%',
			title : 'RskEvent003.paraGranularity',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : PARA_GRAIN
			}
		}, {
			field : 'relType',
			width : '10%',
			title : 'RskEvent003.relType',
			edit : true,
			editOption : {
				type : 'select',
				result : SUP_CONNECTOR
			}
		},{
			field : 'relItem',
			width : '10%',
			title : 'RskEvent003.relItem',
			sortable : false,
			edit : true,
			editOption : {
				type : 'select',
				result : EVENT_REL_ITEM
			}
		},{
			field : 'customVal',
			title : 'RskEvent002.customVal',
			width : '10%',
			type : 'number',
			sortable : false,
			edit : true
		},{
			field : 'remark',
			title : 'RskEvent002.remark',
			width : '18.8%',
			sortable : false,
			edit : true
		},
		{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
		],
		editCell : function(value, data, field, cell, column){
			var rows = dataTable.getAllRows();
			var paraCodeArray;
			if (field === "paraCode"){
				paraCodeArray= _.filter(rows,function(i) {
					return i.paraCode==value&&data.paraGranularity==i.paraGranularity
				});
			}
			if (field === "paraGranularity"){
				paraCodeArray= _.filter(rows,function(i) {
					return i.paraGranularity==value&&data.paraCode==i.paraCode
				});
			}
			if(typeof(paraCodeArray) != "undefined"&&paraCodeArray.length>1) {
				JoyinUtil.showMessage(JoyinUtil.getMessage('RSKRDS_W_0034',JoyinUtil.getLabel("RskEvent003.paraNameAndGran")), 'error', function() {
					dataTable.deleteRow(data.LAY_TABLE_INDEX);
				});
				return;
			}
		},
		done : function(table, allData) {
		}
	});
	
	/**
     * 绑定删除事件
     */
	dataTable.bind('click', '.delt', function(data, index) {
		dataTable.deleteRow(index);
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
		params.operateType = operateType;
		params.pageModel = pageModel;
		params.paramList = dataTable.getAllRows();
		//check每一行中数据是否 必填写
		var checkTable=true;
		var message;
		$.each(params.paramList,function (i,item) {
			if(!item.paraCode||!item.paraGranularity||!item.relType||!item.relItem){
				checkTable=false;
				message=JoyinUtil.getMessage('RSKRDS_W_0067');
				return checkTable;
			}
			if(item.relItem=='custom'&&!item.customVal){
				checkTable=false;
				message=JoyinUtil.getMessage('RSKRDS_W_0078');
				return checkTable;
			}
		});
		if(!checkTable){
			Joyin.error(message)
			return;
		}
		$.each(params.paramList,function (i,item) {
			item.eventCode=$("#eventCode").val();
		});
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rskevent/RskEvent003/check',params, function(result) {
    		Joyin.confirm(result.data, function() {
                	if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rskevent/RskEvent003/save', params, "RskEvent003_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rskevent/RskEvent003/save', params, "RskEvent003_btn_submit");
                    }
                });
            });
	};
	
	var init = function(){
		var list = [];
		if(entity && entity.paramList) {
			list = entity.paramList;
		}
		$.each(list,function(i,v){
			dataTable.layOption.data.push(v);
		})
	}
	
	
	/*
	 * 
	 * 画面加载完成
	 */
	$(function() {
		
		init();
		
		// 保存按钮执行事件
		$('#RskEvent003_btn_save').click(function(){
			onSaveOrSubmitAction('save');
		});
		// 提交按钮执行事件
		$('#RskEvent003_btn_submit').click(function(){
			onSaveOrSubmitAction('submit');
		});
		
    });

}());