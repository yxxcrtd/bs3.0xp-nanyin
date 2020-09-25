/**
 * 组合自动关联指标新建
 */
$(function() {
	var searchOnInit = $("#workflowId").val()!=null&&$("#workflowId").val()!='';
	var search = true;
	var sel;
	 /*var init = function(){
		 table.load();
	    	var wfl_operType = $("#wfl_operType").val();
	    	if(wfl_operType == null){
    			$('#indName').attr("disabled",true);
        		$('#indType').attr("disabled",true);
        		$('#supType').attr("disabled",true);
        		$('#RskPortAutoRelatePoint003_btn_search').attr("disabled",true);
    		}
	    	
	    };*/
    //var editable = pageModel != PAGE_MODEL_REFERENCE;
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	/**
    	 * 获取列表参数
    	 */
    	var object = $.serializeToObject('#submitForm', false);
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        object.selectedItem = selArr;
        object.pageId = "RskPortAutoRelatePoint003";
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rskportautorelatepoint/RskPortAutoRelatePoint003/check', object, function(result) {
            Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rskportautorelatepoint/RskPortAutoRelatePoint003/save', object, "RskPortAutoRelatePoint003_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rskportautorelatepoint/RskPortAutoRelatePoint003/save', object, "RskPortAutoRelatePoint003_btn_submit");
                }
            });
        });
    }
    /**
     * 获取查询参数
     */
	var getSearchParams = function() {
		var workflowId = ''
		if(search){
			return {workflowId : $('#workflowId').val()}
    	}else{
    		return {
            	ruleId: $('#ruleId').val(),
            	indName: $('#indName').val(),
            	indType: $('#indType').val(),
            	supType: $('#supType').val(),
            }
    	}
    	
        
    };
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/riskredis/quota/rskportautorelatepoint/RskPortAutoRelatePoint003/findPage'),
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox:pageModel!=PAGE_MODEL_REFERENCE,
        searchOnInit:searchOnInit,
        cols:[
             {field:'ruleId', visible: false}
            ,{field:'indId', visible: false}
            ,{field:'supType', visible: false}
            ,{field:'indName', sortField:"IND_NAME", width:320 , title: 'RskPortAutoRelatePoint003.indName'}
            ,{field:'indTypeStr',sortField:"IND_TYPE" ,width:180 , title: 'RskPortAutoRelatePoint003.indType'}
            ,{field:'supTypeStr', width:120 , sortField:"SUP_TYPE", title: 'RskPortAutoRelatePoint003.supType'}
            ,{field:'warnCond', width:120 , sortField:"WARN_COND",title: 'RskPortAutoRelatePoint003.warnCond',render: RskQuotaCommon.transferWarnFoul}
            ,{field:'approveCond', sortField:"APPROVE_COND",title: 'RskPortAutoRelatePoint003.approveCond',render: RskQuotaCommon.transferWarnFoul}
            ,{field:'foulCond', sortField:"FOUL_COND",title: 'RskPortAutoRelatePoint003.foulCond',render: RskQuotaCommon.transferWarnFoul}
        ],
        done: function() {
        	//如果是代办中第一次打开时
        	if(search){
        		debugger;
        		sel = table.getAllRows();
        		for(var j=0;j<sel.length;j++){
        			table.checkRow(sel[j].LAY_TABLE_INDEX);
				 }
        	}else{
        		debugger;
        		var rows = table.getAllRows();
        		if(rows&&rows.length>0&&sel&&sel.length>0){
       			 for(var i=0;i<sel.length;i++){
       				 for(var j=0;j<rows.length;j++){
       					 if(sel[i].indId==rows[j].indId){
       						 table.checkRow(rows[j].LAY_TABLE_INDEX);
           				 }
       				 }
       				 
       			 }
       		 }
        	}
        }
    });   

    $(function() {
    	//init();
        // 按钮事件
        $('#RskPortAutoRelatePoint003_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#RskPortAutoRelatePoint003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#RskPortAutoRelatePoint003_btn_search').click(function() {
        	search = false;
        	
        	table.load();
        });
    });
}());