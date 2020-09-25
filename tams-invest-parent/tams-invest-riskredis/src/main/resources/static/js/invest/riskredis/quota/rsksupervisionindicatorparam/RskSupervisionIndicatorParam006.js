/**
 * 规则信息新建
 */
$(function() {
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	/**
    	 * 获取列表参数
    	 */
    	var object = $.serializeToObject('#submitForm', true);
    	if(table!=null && table!=''){
            var selArr = table.getAllRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error("表格不能为空");
            return;
        }
        
        object.selectedItem = table.getAllRows();
        for (var i = 0; i < object.selectedItem.length; i++){
    		object.selectedItem[i].paraName = $("#paraName").val();
    		object.selectedItem[i].paraCode = $("#paraCode").val();
    	}
        object.pageId = "RskSupervisionIndicatorParam006";
        object.pageModel = pageModel;
        object.workflowId = $("#workflowId").val();
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam006/check', object, function(result) {
            Joyin.confirm(result.data, function() {
            	if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam006/save', object, "RskSupervisionIndicatorParam006_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam006/save', object, "RskSupervisionIndicatorParam006_btn_submit");
                }
            });
        });
    }
    /**
     * 定义数据表格
     */
    var addbtn = (pageModel == PAGE_MODEL_NEW) || ($("#wfl_operType").val() == 'NEW');
	var options = {
		page: true,
        ajax : {
            url : JoyinUtil.getRealPath('invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam006/findPage'),
            type : 'POST',
            data : function() {
                return {
                	paraCode : $('#paraCode').val(),
                    workflowId : $('#workflowId').val()
                };
            }
        },
        addbtn:true,
        searchOnInit: true,
        checkbox:false,
        height:250,
        editCell: function (value, row, field, cell, column) {
			if(!value||value==''){
				return;
			}
			if (column.field == 'institutionCode') { 
				if (msInstitutionCode != "ALL" && value != InstitutionCode) {
					JoyinUtil.showMessage(JoyinUtil.getMessage("只能选择本机构"), 'error', function() {
              			table.deleteRow(row.LAY_TABLE_INDEX);
        			});
        			return;
				}
				var selArr = table.getAllRows();
            	if(selArr && selArr.length>0){
            		for(var i=0;i<selArr.length;i++){
            			var validDate = new Date(selArr[i].validDate);
            			var institutionCode = selArr[i].institutionCode; 
            			var validDate2 = new Date(row.validDate);
                		if(institutionCode==value && validDate.format("yyyy-MM-dd")==validDate2.format("yyyy-MM-dd")&&i!=row.LAY_TABLE_INDEX){
                			JoyinUtil.showMessage(JoyinUtil.getMessage('RSKRDS_W_0034',"相同机构下生效日期"), 'error', function() {
                      			table.deleteRow(row.LAY_TABLE_INDEX);
                			});
                			return;
                		}
            		}
            	}
			}
            if (column.field == 'validDate'||column.field == 'notifyDate') {
            	/*var sysDate = new Date(SYS_DATE);
            	sysDate = sysDate.format('yyyy-mm-dd');*/
            	if(column.field == 'notifyDate'&&value!=""&&value<=SYS_DATE){
            		JoyinUtil.showMessage(JoyinUtil.getMessage('RSKRDS_W_0068'), 'error', function() {
              			table.deleteRow(row.LAY_TABLE_INDEX);
              		});
          			return;
            	}
            	else if(column.field == 'validDate'&&value<SYS_DATE){
            		if(value<SYS_DATE){
                		JoyinUtil.showMessage(JoyinUtil.getMessage('RSKRDS_W_0068'), 'error', function() {
                  			table.deleteRow(row.LAY_TABLE_INDEX);
                  		});
              			return;
                	}
            	}
            	var selArr = table.getAllRows();
            	if(selArr && selArr.length>0){
            		for(var i=0;i<selArr.length;i++){
            			var validDate = new Date(selArr[i].validDate);
            			var institutionCode = selArr[i].institutionCode; 
            			var institutionCode2 = row.institutionCode;
                		if(institutionCode==institutionCode2 && validDate.format("yyyy-MM-dd")==value&&i!=row.LAY_TABLE_INDEX){
                			JoyinUtil.showMessage(JoyinUtil.getMessage('RSKRDS_W_0034',"相同机构下生效日期"), 'error', function() {
                      			table.deleteRow(row.LAY_TABLE_INDEX);
                			});
                			return;
                		}
            		}
            	}
            } 
        },
        beforeEditCell : function(preVal, field,data){ 
      	if((field == 'content'||field == 'validDate')&&data.updateFlag =="02"){
    		return false;
    	}
      	if(field == 'institutionCode'){
          if(isEnabled == '01'){
        	  if (!data.institutionCode||data.institutionCode==''){ 
        		 return true; 
        	  } 
        	  if (msInstitutionCode == "ALL") {
        		return true;
	  		  } 
  		  }else{
  		  	return false;
  		  }
          return false;
      	} 
      	if(field == 'notifyDate'){
        	var selArr = table.getAllRows();
        	var maxValDate; 
        	if(selArr&&selArr.length>1){
        		for(var i=0;i<selArr.length;i++){
        			for(var j=i+1;j<selArr.length;j++){
        				var validDate1 = new Date(selArr[i].validDate);
        				var validDate2 = new Date(selArr[j].validDate);
        				if(validDate1.format("yyyy-MM-dd")<validDate2.format("yyyy-MM-dd")){
        					maxValDate = validDate2.format("yyyy-MM-dd");
        				}else{
        					maxValDate = validDate1.format("yyyy-MM-dd");
        				}
        			}
        		}
        	}else if(selArr.length==1){
        		var validDate = new Date(selArr[0].validDate);
        		maxValDate = validDate.format("yyyy-MM-dd");
        	}
        	var validDate = new Date(data.validDate);
        	if(validDate.format("yyyy-MM-dd")!=maxValDate){
        		return false;
        	}
        }
       	return true;
       	},
    	cols:[
			  //{field:'updateFlag' ,visible: false}  
    	     {field:'content' ,width:"150",title: 'RskSupervisionIndicatorParam006.content', edit:true,type:'abc'
             }//参数值
             ,{field:'validDate' ,width:"100",title: 'RskSupervisionIndicatorParam006.validDate', edit:true,type:'date'
             }//有效日期
             ,{field:'notifyDate',width:"100",title: 'RskSupervisionIndicatorParam006.notifyDate', edit:true,type:'date'
             }//连接符 
             ,{field:'institutionCode' ,width:"150",title: 'RskSupervisionIndicatorParam006.institutionName', 
            	 visible:isEnabled=='01'?true:false,
	        	 edit:true,sortable:false, 
				 editOption:{type:'select',showField:'text',id:'institutionCode',formatoption:true,initValue:InstitutionCode,
			     url:JoyinUtil.getRealPath('/invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam003/getInstitutionId')} 
             }//机构名称
             ,{field:'operation',title: 'SysCommon.operation', type:'action',
            	 render:function(data,type,row,meta){
            		 if(row&&row.updateFlag =="02"){
            			 return '';
            		 }else{
            			 return '<i class="btn layui-icon delt delt" title="删除">&#xe640;</i>';
            		 }
            	 }
             }
         ],
         
    };
	
	/*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    var init = function(){
	
   };

    $(function() {
    	init();
        // 按钮事件
        $('#RskSupervisionIndicatorParam006_btn_save').click(function() {
        	
            saveOrSubmit('save');
        });
        $('#RskSupervisionIndicatorParam006_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());