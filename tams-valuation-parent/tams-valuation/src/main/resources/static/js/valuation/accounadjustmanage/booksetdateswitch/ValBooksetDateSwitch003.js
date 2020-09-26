$(function() {
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
    	ajax: {
            url: basePath + 'valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch003/findList',
            type: 'POST',
            data: function() {
                return {booksetId : ID_DATA};
            }
        },
        loadonce: true,
        searchOnInit: true,
        page: false,
        height: 400,
        cellMinWidth : 100,
        checkbox: false,
        cols:[
            {field:'booksetId', title: '账套代码',sortable : false}
            ,{field:'booksetName', title: '账套名称',sortable : false}
            ,{field:'booksetDate', title: '当前日期 ', width: 450, edit: true, type: 'date',formatter: 'yyyy-MM-dd', sortable : false}   
        ],
        editCell: function(value, data, field, cell, column){
        	$('#booksetDate').val("");
        	return true;
    	}
    });
    
    function saveOrSubmit(operateType) {
		var rowArr = table.getAllRows();
		for(var i=0; i<rowArr.length; i++){
			if(rowArr[i].booksetDate == undefined || $.trim(rowArr[i].booksetDate) == ''){
				Joyin.error(JoyinUtil.getMessage("当前日期不能为空!"));
				return;
			}
			// 表格date框架控件必须用数值初始化
			if(!isNaN(Number(rowArr[i].booksetDate))) {
	            var date = new Date(parseInt(rowArr[i].booksetDate));
	            rowArr[i].booksetDate = date.format('yyyy-MM-dd');
	        }
		}
		
		var object = {selectedItem: rowArr, operateType:operateType};
        if (operateType === 'submit') {
        	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch003/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                	WorkFlow.doSubmitList(basePath + 'valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch003/doUpdate', object, "ValBooksetDateSwitch003_btn_submit", "ValBooksetDateSwitch002");
                });
        	});
        }
	}

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#booksetDate').change(function() {
        	for (var i = 0; i < table.getAllRows().length; i ++) {
        		table.renderRow(i, {booksetDate: $('#booksetDate').val()});
            }
        });
        
        $('#ValBooksetDateSwitch003_btn_submit').click(function(){
        	saveOrSubmit('submit');
        });
        
    });
}());