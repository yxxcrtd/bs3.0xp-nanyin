$(function() {
	
	function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        var tableData = table.getAllRows();
        object.ptlInvestStrategyPoolDetailList = tableData;
    	if(object.evalIndex){
    		object.evalIndex = object.evalIndex.join(",");
    	}
    	Joyin.ajaxJson(basePath + 'invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/save', object, "PtlInvestStrategyPool002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/save', object, "PtlInvestStrategyPool002_btn_submit");
                }
            });
        });
    
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
    	ajax: {
    		url: JoyinUtil.getRealPath('/invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/queryInvestPoolDetail'),
    		type: 'POST',
    		data: function() {
    			return {workflowId : workflowId,strategyId : $("#strategyId").val()};   
    		}
    	},
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: true,
        height : 200,
        cols:[
            {field:'type2', title: 'PtlInvestStrategyPool001.type2',edit:true,  width:200,sortable:false, 
            	editOption:{type:'select',multiple:true, id: 'type2',formatoption:true,childrenIds:"type3", url: JoyinUtil.getRealPath('/invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/queryMulType2')}
            }//资产二级分类
           ,{field:'type3', title: 'PtlInvestStrategyPool001.type3',edit:true, width:200, sortable:false, 
            	editOption:{type:'select',multiple:true, parentIds : 'type2',id: 'type3',formatoption:true, url: JoyinUtil.getRealPath('/invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/queryMulType3')}
           }//资产三级分类
           ,{field:'type4', title: 'PtlInvestStrategyPool001.type4',edit:true,  width:200,sortable:false, 
            	editOption:{type:'select',multiple:true, parentIds : 'type3',id: 'type4',formatoption:true, url: JoyinUtil.getRealPath('/invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/queryMulType4')}
           }//资产四级分类
           ,{field:'type5', title: 'PtlInvestStrategyPool001.type5',edit:true,  width:200,sortable:false, 
            	editOption:{type:'select',multiple:true, parentIds : 'type4',id: 'type5',formatoption:true, url: JoyinUtil.getRealPath('/invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/queryMulType5')}
           }//资产五级分类
           ,{field:'ratio', title: 'PtlInvestStrategyPool001.ratio', width:150,edit:true,sortable:false}//比例
           ,{field:'batchId', title: 'PtlInvestStrategyPool001.batchId', width:150,edit:false,sortable:false}//策略明细编号
           ,{field:'operation',width:80,multiple:true,title: 'SysCommon.operation', type:'action', buttons: [ 'delt']}
       ],
        beforeEditCell : function(preVal, field, rowData){
            return true;
        },
        editCell : function(value, data, field, cell, column){
        	if(field === 'type2'){
        	    var rowData = {};
        	    rowData.type3='';
        	    rowData.type4='';
        	    rowData.type5='';
        	    //更新表格
        	    table.renderRow(data.LAY_TABLE_INDEX, rowData);
        	}
        	else if(field === 'type3'){
        	    var rowData = {};
        	    rowData.type4='';
        	    rowData.type5='';
        	    rowData.type3hidd = value;
        	    
        	    //更新表格
        	    table.renderRow(data.LAY_TABLE_INDEX, rowData);
        	}
        	else if(field === 'type4'){
        	    var rowData = {};
        	    rowData.type5='';
        	    //更新表格
        	    table.renderRow(data.LAY_TABLE_INDEX, rowData);
        	}
//        	column.editOption.result.updateChosenArr({item: ['01','02','03'],
//                disabled: false});
        },
        done : function(table, allData){
        	
        }
    });
	var init = function(){
    	
    }
	/**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	table.removeRow(data);
    });
	$(function(){
    	/**
         * 画面加载完成
         */
    	init();
        // 绑定按钮事件
    	$('#PtlInvestStrategyPool002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlInvestStrategyPool002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
}());