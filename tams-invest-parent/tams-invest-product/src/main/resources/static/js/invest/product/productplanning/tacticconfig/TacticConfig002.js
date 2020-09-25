(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#TacticConfig002_form');
    	return param;
    };
    
    /**
     * 定义数据表格
     */
    var corTable=$("#corTable").joyintable({ 
    	ajax: {
            url: JoyinUtil.getRealPath('/invest/product/productplanning/tacticconfig/TacticConfig002/getTacticConfigOptDetailList'),
            type: 'POST',
            data: function() {
                return {
                	tacticConfigCode : $("#tacticConfigCode").val(),
                	workflowId : workflowId
                };
            }
        },
        
        searchOnInit: true,
        loadonce: true,
        page: false,
    	
		cols: [
	        {field:'assetType', title: 'TacticConfig001.assetType', width:'10%',sortable: false,
	        	edit:true,
	        	editOption:{type:'select',result:ASSET_TYPE}}
            	//editOption:{type:'select', url: JoyinUtil.getRealPath('/invest/product/productplanning/tacticconfig/TacticConfig002/selectAllAssetType')}}
	        ,{field:'theoConfigRate', title: 'TacticConfig001.theoConfigRate', width:'10%',sortable: false,
	        	edit:true,
	        	type:'number',
	        	digit:6
	        }
			,{field:'samleAverRate', title: 'TacticConfig001.samleAverRate', width:'10%',sortable: false,
				edit:true,
	        	type:'number',
	        	digit:6
			}
			,{field:'vix', title: 'TacticConfig001.vix', width:'10%',sortable: false,
				edit:true,
	        	type:'number',
	        	digit:6
			}
			,{field:'corRate', title: 'TacticConfig001.corRate', width:'10%',sortable: false,
				edit:true
			}
			,{field:'corScene', title: 'TacticConfig001.corScene', width:'10%',sortable: false,
				edit:true,
            	editOption:{type:'select',result:COR_SCENE}
			}
			,{field:'corRatio', title: 'TacticConfig001.corRatio', width:'10%',sortable: false,
				edit:true,
	        	type:'number',
	        	digit:0
			}
			,{field:'corView', title: 'TacticConfig001.corView', width:'10%',sortable: false,edit:true}
	        //,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
	    ],
	    beforeEditCell: function(preVal, field, rowData){
	    	if(field == 'corScene' || field == 'assetType'){
	    		return false;
	    	}
	    	return true;
	    },
	    editCell : function(value, data, field, cell, column){
	    	if(field == 'corRate'){
	    		var rowData = {};
	    		if(isNaN(value)){
	    			rowData.corScene = '';
	    			corTable.renderRow(data.LAY_TABLE_INDEX, rowData);
	    			Joyin.error("请输入数字");
	    			return true;
	    		}/*else if(value != Math.round(value)){
	    			rowData.corScene = '';
	    			rowData.corRate = '';
	    			corTable.renderRow(data.LAY_TABLE_INDEX, rowData);
	    			Joyin.error("请输入整数");
	    			return true;
	    		}*/
	    		
	    		if(!value){
	    			rowData.corScene = '';
	    		}else if(value < -20){
	    			rowData.corScene = COR_SCENE_1;
	    		}else if(value >= -20 && value < -10){
	    			rowData.corScene = COR_SCENE_2;
	    		}else if (value >= -10 && value < -5){
	    			rowData.corScene = COR_SCENE_3;
	    		}else if (value >= -5 && value < 5){
	    			rowData.corScene = COR_SCENE_4;
	    		}else if (value >= 5 && value < 10){
	    			rowData.corScene = COR_SCENE_5;
	    		}else if (value >= 10 && value < 20){
	    			rowData.corScene = COR_SCENE_6;
	    		}else if (value >= 20){
	    			rowData.corScene = COR_SCENE_7;
	    		}
	    		corTable.renderRow(data.LAY_TABLE_INDEX, rowData);
	    		//修正后比例联动
	    		var rowData2 ={};
	    		var corRatioTotal = 0;
	    		var corRatio = 0;
	    		var corRatioSum = 0;
	    		if(data.corRate && value){
	    			corRatio = data.corRate + value;
	    		}
	    		var allRows = corTable.getAllRows();
	    		$.each(allRows,function(index,obj){
	    			if(obj.corRate && obj.theoConfigRate){
	    				corRatioTotal = Number(corRatioTotal) + Number(obj.corRate) + Number(obj.theoConfigRate);
	    			}
	    		});
	    		$.each(allRows,function(index,obj){
	    			if(obj.corRate && obj.theoConfigRate){
	    				rowData2.corRatio = Math.round((((Number(obj.corRate) + Number(obj.theoConfigRate))/corRatioTotal)*100));
	    				//最后一列用倒减法确保总和为100
	    				if(index == (allRows.length - 1)){
	    					rowData2.corRatio = 100 - corRatioSum;
	    				}
	    				corRatioSum = Number(corRatioSum) + Math.round((((Number(obj.corRate) + Number(obj.theoConfigRate))/corRatioTotal)*100));
	    				corTable.renderRow(obj.LAY_TABLE_INDEX, rowData2);
	    			}
	    		});
	    	}
	    	//理论配置比例
	    	else if(field == 'theoConfigRate'){
	    		//修正后占比
	    		var corRatioTotal = 0;
	    		var corRatio = 0;
	    		var rowData = {};
	    		var corRatioSum = 0;
	    		if(data.corRate && value){
	    			corRatio = data.corRate + value;
	    		}
	    		var allRows = corTable.getAllRows();
	    		$.each(allRows,function(index,obj){
	    			if(obj.corRate && obj.theoConfigRate){
	    				corRatioTotal = Number(corRatioTotal) + Number(obj.corRate) + Number(obj.theoConfigRate);
	    			}
	    		});
	    		$.each(allRows,function(index,obj){
	    			if(obj.corRate && obj.theoConfigRate){
	    				rowData.corRatio = Math.round((((Number(obj.corRate) + Number(obj.theoConfigRate))/corRatioTotal)*100));
	    				//最后一列用倒减法确保总和为100
	    				if(index == (allRows.length - 1)){
	    					rowData.corRatio = 100 - corRatioSum;
	    				}
	    				corRatioSum = Number(corRatioSum) + Math.round((((Number(obj.corRate) + Number(obj.theoConfigRate))/corRatioTotal)*100));
	    				corTable.renderRow(obj.LAY_TABLE_INDEX, rowData);
	    			}
	    		});
	        }
	    }
        
        
	});
    
    /**
	 * 绑定删除事件
	 */
    corTable.bind('click', '.delt', function(data, index) {
    	corTable.removeRow(data);
	});
	
    /**
     * 新建
     */
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("TacticConfig002.new"),
                JoyinUtil.getRealPath("invest/product/productplanning/tacticconfig/TacticConfig002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
    /**
     * 根据修正期限起始日带出修正期限结束日
     */
    $("#vdate").change(function(){
    	var vdate = $("#vdate").val();
    	if(vdate){
    		var edateYear = Date.ofDate(vdate).format('yyyy');
    		var edate = edateYear + "-12-31";
    		$("#edate").val(edate);
    	}
    });
    
    /**
     * 保存或提交
     */
    var onSaveOrSubmitAction = function(operateType){
		var object = $.serializeToObject('#TacticConfig002_form',true);
		var tableAllData = corTable.getAllRows();
		if (!tableAllData || tableAllData.length == 0) {
            Joyin.error("战术配置列表不能为空");
            return;
        }
		object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/tacticconfig/TacticConfig002/check',object, function(result) {
    		Joyin.confirm(result.data, function() {
                	if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/product/productplanning/tacticconfig/TacticConfig002/save', object, "TacticConfig002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/product/productplanning/tacticconfig/TacticConfig002/save', object, "TacticConfig002_btn_submit");
                    }
                })
            })
	}
    
    var init = function(){
    	if(pageModel == PAGE_MODEL_EDIT){
    		$('#vdate').prop('disabled', true);
    		$('#edate').prop('disabled', true);
    	}
    }
    
	$(function(){
		init();
		//新建
		$('#TacticConfig001_btn_add').click(function() {
        	openNew();
        });
		// 提交按钮执行事件
		$('#TacticConfig002_btn_submit').click(function(){
			onSaveOrSubmitAction('submit');
		});
		// 保存按钮执行事件
		$('#TacticConfig002_btn_save').click(function(){
			onSaveOrSubmitAction('save');
		});
	});
}());