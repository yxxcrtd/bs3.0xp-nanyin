$(function() {
	
	function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        var tableData = table.getAllRows();
        object.selectedItem = tableData;
        object.seqno = seqno;
    	if(object.evalIndex){
    		object.evalIndex = object.evalIndex.join(",");
    	}
    	Joyin.ajaxJson(basePath + 'invest/portfolio/trdpbrelation/TrdPbRelation002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                WorkFlow.doSubmitList(basePath + 'invest/portfolio/trdpbrelation/TrdPbRelation002/save', object, "TrdPbRelation002_btn_submit");
            });
        });
    
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
    	ajax: {
    		url: JoyinUtil.getRealPath('/invest/portfolio/trdpbrelation/TrdPbRelation002/getTrdPbRelation'),
    		type: 'POST',
    		data: function() {
    			return {seqno : seqno};   
    		}
    	},
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: true,
        height : 300,
        cols:[
            {field:'factorType', title: 'TrdPbRelation001.factorType',sortable:false, edit:true, width:120, 
            	editOption:{type:'select', id: 'factorType',formatoption:true,childrenIds:"prdPortfolioId",result: FACTOR_TYPE}}//要是类型
           ,{field:'prdPortfolioId', title: 'TrdPbRelation001.prdPortfolioId',edit:true, width:200, sortable:false, 
            	editOption:{type:'select',showField:'id', parentIds : 'factorType',id: 'prdPortfolioId',formatoption:true, url: JoyinUtil.getRealPath('/invest/portfolio/trdpbrelation/TrdPbRelation002/queryPrdPortfolioId')}
           }
           ,{field:'prdPortfolioName', title: 'TrdPbRelation001.prdPortfolioName', width:250,edit:false,sortable:false}//产品/子组合名称
           ,{field:'pbId', title: 'TrdPbRelation001.pbId', width:150,edit:true,sortable:false}//PB编号
           ,{field:'operation',width:80,multiple:true,title: 'SysCommon.operation', type:'action', buttons: [ 'delt']}
       ],
        beforeEditCell : function(preVal, field, rowData){
            return true;
        },
        editCell : function(value, data, field, cell, column){
        	if(field === 'factorType'){
        	    var rowData = {};
        	    rowData.prdPortfolioId='';
        	    rowData.prdPortfolioName='';
        	    //更新表格
        	    table.renderRow(data.LAY_TABLE_INDEX, rowData);
        	}
        	else if(field === 'prdPortfolioId'){
        	    var rowData = {};
        	    rowData.prdPortfolioName='';     
        	    if(data.factorType != '' && value != ''){
                    var obj = {'factorType':data.factorType,'prdPortfolioId':value,'search':value};
                    Joyin.ajax(JoyinUtil.getRealPath( "/invest/portfolio/trdpbrelation/TrdPbRelation002/queryPrdPortfolioName"),obj,function(result){
                    	rowData.prdPortfolioName=result.text;   
                	    //更新表格
                	    table.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
                }else{
            	    //更新表格
            	    table.renderRow(data.LAY_TABLE_INDEX, rowData);
                }   	    
        	}
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
        $('#TrdPbRelation002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
}());