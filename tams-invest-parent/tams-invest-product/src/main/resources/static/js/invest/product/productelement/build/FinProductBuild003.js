(function(){
	
	/**
     * 定义数据表格
     */
    var table=$("#FinProductBuild003_dataTable").joyintable({ 
    	ajax: {
            url: JoyinUtil.getRealPath('invest/product/productelement/build/FinProductBuild003/queryForList'),
            type: 'POST',
            data: function() {
                return {finprodIdStr : finprodIdStr, workflowId : workflowId};
            }
        },
        searchOnInit : true,
        page: false,
        loadonce: true,
        rowno: true,
        cols:[
			{field:'finprodMarketId', title: 'FinProductBuild003.table.finprodMarketId', width:150}
			,{field:'finprodAbbr', title: 'FinProductBuild003.table.prodAbbr', width:150}
			,{field:'issueStatusStr', sortField: "issueStatus",title: 'FinProductBuild003.table.issueStatus', width:150}
			,{field:'newIssueStatus', title: 'FinProductBuild003.table.newIssueStatus', width:150, edit : true, editOption:{type:'select',result: ISSUE_STATUS}}
			,{field:'issueStatusRemark', title: 'FinProductBuild003.table.issueStatusRemark', width:200, edit : true}
			,{field:'issueStatus', visible : false}
			,{field:'finprodId', visible : false}
        ],
        editCell : function(value, data, field, cell, column){
            if(field === 'newIssueStatus'){
                var rowData = {};
                rowData.newIssueStatus = value;
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
            }
        }
    });
    
    var newIssueStatusChange = function(){
    	var newIssueStatus = $('#FinProductBuild003_newIssueStatus').val();
    	_.each(issueStatusList, function(v) {
    		if(v.id === newIssueStatus) {
    			newIssueStatus = v.text;
    		}
    	});
    	
    	var rowDataList = table.getAllRows();
    	$.each(rowDataList, function(id, value){
    		if (value.LAY_CHECKED){
    			value.newIssueStatus = newIssueStatus;
        		table.renderRow(value.LAY_TABLE_INDEX, value);
    		}
    	});
    };
    
    var issueStatusRemarkChange = function(){
    	var rowDataList = table.getAllRows();
    	$.each(rowDataList, function(id, value){
    		if (value.LAY_CHECKED){
    			value.issueStatusRemark = $('#FinProductBuild003_issueStatusRemark').val();
    			table.renderRow(value.LAY_TABLE_INDEX, value);
    		}
    		
    	});
    };
    
    /**
	 * 保存或提交按钮执行事件
	 */
	var onSaveOrSubmitAction = function(operateType){
		var object = $.serializeToObject('#issueStatusForm', true);
		
		if (object.selectedItem){
			$.each(object.selectedItem, function(id, value){
				if (value.newIssueStatus){
					_.each(issueStatusList, function(v) {
			    		if(v.text === value.newIssueStatus) {
			    			value.newIssueStatus = v.id;
			    		}
			    	});
				}
			})
		}
		
		object.operateType = operateType;
        object.pageModel = pageModel;
        
        Joyin.ajaxJson(basePath + 'invest/product/productelement/build/FinProductBuild003/check',object, function(result) {
        	Joyin.confirm(result.data, function() {
        		if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productelement/build/FinProductBuild003/save', object, "FinProductBuild003_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/productelement/build/FinProductBuild003/save', object, "FinProductBuild003_btn_submit");
                }
        	});
        })
	}
    
	$(function() {
        // 新发行状态下拉框联动处理
        $('#FinProductBuild003_newIssueStatus').bind('change',newIssueStatusChange);
        
        // 状态备注文本框联动处理
        $('#FinProductBuild003_issueStatusRemark').bind('change',issueStatusRemarkChange);
        
        // 保存按钮执行事件
        $('#FinProductBuild003_btn_save').click(function(){
        	onSaveOrSubmitAction('save');
        });
        // 提交按钮执行事件
        $('#FinProductBuild003_btn_submit').click(function(){
        	onSaveOrSubmitAction('submit');
        })
	});
}());