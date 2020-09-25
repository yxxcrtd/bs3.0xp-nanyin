$(function() {
    
    /**
     * 定义表头
     */
    var cols = [
                {field:'windtypeone',  title: 'EtrStandardBond003.windtypeone',edit: true,
                	editOption: {
	                    type: 'select', id: 'windtypeoneSelect', multiple: false, searchable: true,
	                    result: WINDTYPEONE,childrenIds: 'windtypetwoSelect'
                	},sortable: false
                },
                {field:'windtypetwo',  title: 'EtrStandardBond003.windtypetwo',edit: true,
                	editOption: {
	                    type: 'select', id: 'windtypetwoSelect', multiple: false, searchable: true,
	                    result: WINDTYPETWO,parentIds: 'windtypeoneSelect',
	                    /*url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select")*/
                	},sortable: false
                },
                {field:'pledgePri',  title: 'EtrStandardBond003.pledgePri',edit: true,
                	editOption: {
	                    type: 'select', id: 'pledgePriSelect', multiple: false, searchable: true,
	                    result: PLEDGEPRI
                	},sortable: false
                },
                {field:'configId',visible:false},
                {field: 'operation', width: 150, title: 'SysCommon.operation', type: 'action', buttons: ['delt'],
                    render: function (data, type, row, meta) {
                        if (row.isPayed == 'Y')
                            return "";
                        else
                            return '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
                    }}
            ];
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
    	ajax: {
    		url: JoyinUtil.getRealPath('/invest/entrust/etrstandardbond/EtrStandardBond003/selectEtrSecPriDetailList'),
    		type: 'POST',//请求类型
    		dataType: 'json',
    		data: {}
    	},
        cols:cols,
        searchOnInit: true,
        loadonce:true,
        checkbox:false,
        height:180,
        page:false,
        addbtn:true,
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	table.removeRow(data);
    });
    
    function saveOrSubmit(operateType) {
        if(table!=null && table!=''){
            var selArr = table.getAllRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0057","分类数据"));
            return;
        }
        var periodsArr = new  Array();
        for(var i=0;i<selArr.length;i++){
        	var  windtypeone = selArr[i].windtypeone ;//获取期次值
        	if(windtypeone == '' || windtypeone == null || windtypeone == undefined){
        		Joyin.error("第"+(i+1)+"行一级分类必填");
        		return;
        	}
        	periodsArr.push(selArr[i].windtypeone);
        }
        
        var object = $.serializeToObject('#submitForm', true);
//        	object.pageModel = pageModel;
        	object.operateType = operateType;
        	object.detailList = table.getAllRows();
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrstandardbond/EtrStandardBond003/check', object, function (result) {
        		Joyin.confirm(result.data, function () {
        			if (operateType === 'save') {
        				WorkFlow.doSaveList(basePath + 'invest/entrust/etrstandardbond/EtrStandardBond003/save', object, "EtrStandardBond003_btn_save");
	                } else {
	                    WorkFlow.doSubmitList(basePath + 'invest/entrust/etrstandardbond/EtrStandardBond003/save', object, "EtrStandardBond003_btn_submit");
	                }
            });
        });  
        
        
        
	};
    
    $(function(){
        $('#EtrStandardBond003_btn_save').click(function() {
            saveOrSubmit('submit');
        });
        $('#EtrStandardBond003_btn_submit').click(function() {
        	saveOrSubmit('submit');
        });
    });
});
