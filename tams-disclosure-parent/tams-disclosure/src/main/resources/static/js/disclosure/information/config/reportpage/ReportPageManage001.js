/**
 * 
 */
$(function() {
	 /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var options=$("#reportName option:selected");
    	var reportNote = $('#reportNote').val();
    	if (reportNote) {
    		reportNote = reportNote;
    	}    	
    	return{
            //模板名称
    		reportName: options.text(),
    		//模板说明
    		reportNote: reportNote,
    		//报表类别
    		reportCategory: $.trim($('#reportCategory').val()),
  		
        }
    };
   
   
    /**
     * 定义数据表格
     */
    var table=$("#reportModel").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        sortable:false,
        loadonce:true,
        cols:[
            {field:'reportName', width:340, title: '报表名称'},
            {field:'templatePath',visible: false,  title: '模板路径'},
            {field:'templateId', width:340, title: '模板英文名'},
            {field:'templateName', width:340, title: '模板中文名'},
            {field:'repId', visible: false,  title: '报表ID'},
            {field:'reportCategory', visible: false, title: '报表类别', sort: true},
            {field:'reportCategoryName', title: '报表类别',width:200, sort: true}
            ,{field:'updateUser', width:200, title: '制作人'}
            ,{field:'reportType', title: '状态',sort: true,visible:false}
            ,{field:'reportTypeName', width:140, title: '状态', align: 'center'
                ,render: function (data, type, row, meta) {
                	if(row.reportType == 'Y') {
                		return '<span class="layui-badge-rim layui-bg-blue">'+row.reportTypeName+'</span>';
                	}
                	else if(row.reportType == 'N') {
                		return '<span class="layui-badge-rim layui-bg-gray">'+row.reportTypeName+'</span>';
                	}
                	else {
                		return '';
                	}
                }
            },
            {field:'operation',width:20, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
        ]
    });
    /**
     * 编辑页面
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
            Joyin.dialog(JoyinUtil.getLabel('报表编辑'), '/disclosure/information/reportpage/ReportPageManage002/init',
                    "90%", "95%", {pageModel:PAGE_MODEL_EDIT,templatePath:data.templatePath,templateId:data.templateId,templateName:data.templateName,repId:data.repId,reportName:data.reportName,reportType:data.reportType,reportStyle:data.reportCategory},function() {
                    	table.reload();
                    });
    });
    
    /**
     * 删除页面
     */
    table.bind('click', '.delt', function(data, index) {
        data.operateType = "delete";
            Joyin.confirm("确认删除该报表吗？", function(){
            	Joyin.ajaxJson('deleteReport', {repId:data.repId}, function(result) {
 				   table.reload();
 			   });
        });
    });
    // 配置页面[新建]
    var addNew = function(){
    	//var modelName =     	
    	Joyin.dialog(JoyinUtil.getLabel('报表新建'), '/disclosure/information/reportpage/ReportPageManage002/init', "85%", "90%",null,
    			function(result) {
			   table.reload();
    	}
        );
    	
    };
    
    /*var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };*/
	//导出   选中的数据 
    var exportExcel = function() {
		if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    
    function effective(flag){
    	var select = table.getAllCheckedRows();
    	var selectData = [];
    	for(var i in select){
    		selectData.push(select[i].repId)
    	}
    	var message = "请选择需要停用的报表";
		var confirmMessage = "确认停用已选择的报表吗？"
    	if(flag=='Y'){
    		message = "请选择需要启用的报表"
    		confirmMessage = "确认启用已选择的报表吗？"
    	}
    	if(selectData.length==0){
    		Joyin.alert(message, 7,null, null);
    	}else{
    		Joyin.confirm(confirmMessage, function(){
    			 Joyin.ajaxJson('effitive', {flag:flag,selectData:selectData}, function(result) {
    				   table.reload();
    			   });
            });
    	}
    };
    
    $(function() {
        // 绑定按钮事件
        $('#ReportPageManage001_btn_search').click(function() {
        	table.load();
        });
        $('#reportPagel001_btn_export').click(function() {
    		exportExcel();	
        });
        
        $('#reportPagel001_btn_design').click(function() {
        	addNew();
        });
        $('#reportPagel001_btn_effective').click(function() {
        	effective('Y')
        });
        $('#reportPagel001_btn_neffective').click(function() {
    		effective('N')
        });
    });

});