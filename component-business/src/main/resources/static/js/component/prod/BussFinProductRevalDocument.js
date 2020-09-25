/**
 * 开放计划共通JS
 * @auth zhengyan
 */
(function(){

	// 快速录入标志
	var quickFlag = false;
	
    var options = {
        searchOnInit: true,
        rowno: true,
        checkbox: false,
        addbtn: true,
        height : 200,
        loadonce: true,
        page:false,
        cols:[
            {field:'fileType', width: 200, title: 'BussFinProductRevalDocumentDto.fileType',edit:true,editOption:{type:'select',childrenIds:'fileName',id:"fileType", result:FILE_TYPE_FG}}
            ,{field:'fileName', width: 200, title: 'BussFinProductRevalDocumentDto.fileName', edit:true,editOption:{type:'select',parentIds:'fileType',id:"fileName",url:JoyinUtil.getRealPath('component/prod/BussFinProductRevalDocument/queryMstFileByType')}}
            ,{field:'remark', width: 200, title: 'BussFinProductRevalDocumentDto.remark', edit:true}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ],
        editCell : function(value, data, field, cell, column){
        	if (field === "fileType"){
        		data.fileName = '';
    			table.renderRow(data.LAY_TABLE_INDEX, data);
        	}
        }
    };

	options.ajax = {
         url: JoyinUtil.getRealPath('/component/prod/BussFinProductRevalDocument/getDocumentList'),
         type: 'POST',
         data: function() {
             return {
                 finprodId : revalDocFinprodId,
                 workflowId : workflowId,
                 entityName : revalEntityName,
                 quickFlag : quickFlag
             };
         }
     };

    /**
     * 定义数据表格
     */
    var table = $('#' + revalEntityName + '_revalDocument').joyintable(options);

    /**
	 * 绑定删除事件
	 */
    table.bind('click', '.delt', function(data, index) {
    	table.deleteRow(index);
	});
    
    function check() {
    	var rows = table.getAllRows();
    	var fileTyps = rows.map(function(i) {return i.fileType;});
    	if(fileTyps.length > _.uniq(fileTyps).length) {
    		JoyinUtil.showMessage(JoyinUtil.getMessage('FIN_PROD_E_0020'), 'error');
    		return false;
    	}
    	return true;
    }

    function getAllRows(){
        if(table != ''){
            return table.getAllRows();
        }
    }
    
    function revalDocInitAction(input_finprodId){
    	revalDocFinprodId = input_finprodId;
    	quickFlag = true;
    	table.table ? table.reload() : table.load();
    }
    
    // 公开接口
    window.BussFinProductRevalDocument = {
        check: check,
        getAllRows:getAllRows,
        revalDocInitAction:revalDocInitAction
    };
}());