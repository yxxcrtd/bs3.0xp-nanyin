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
            ,{field:'updateUser', width:200, title: '当前处理人'}
            ,{field:'reportType', title: '状态',sort: true,visible:false}
            ,{field:'reportTypeName', width:140, title: '状态', align: 'center'}
            ,{field:'remark', width:140, title: '备注说明', align: 'center'},
            // {field:'operation',width:20, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
            {field:'operation',width:60, title: 'SysCommon.operation', type:'action',
                render : function(data, type, row, meta){
                    var html = "";
                    if(purviewElements.indexOf('_edit')!=-1){
                        html += "<i class='iconfont icon-edit edit' title ='编辑'></i>";
                    }
                    if(purviewElements.indexOf('_manual')!=-1){
                        html +="<i class = 'iconfont icon-delete delt' title='删除'></i>";
                    }
                    html +="<i class='iconfont icon-search view'></i>";
                    return html;
                }
            }
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

    table.bind('click', '.view', function(data, index) {
        loadView(data)
    });

    /**
     * 预览
     */
    function loadView(data){
        var templateId = data.templateId;
        if(!templateId){
            Joyin.alert("请选择模板");
            return;
        }
        Joyin.dialog(JoyinUtil.getLabel('预览'), basePath + 'disclosure/information/reveal/detailshow/RepDetailsShow002/init',
            '1220px', '90%', {repId:data.repId,repName:data.reportName,uri:data.templateId},function() {
            });

    };

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
    
    function effective(workflowType){
    	var selRows = table.getAllCheckedRows();
        var message = "请选择一条记录进行停用";
        var confirmMessage = "确认停用已选择的报表吗？"
        if(workflowType==='01'){
            message = "请选择一条记录进行启用"
            confirmMessage = "确认启用已选择的报表吗？"
        }
        if(selRows != null && selRows.length == 1){
            if(workflowType==='01'){
                if(!(selRows[0].reportType===undefined||selRows[0].reportType===null||selRows[0].reportType==='04')){
                    Joyin.error("只能对状态为初始化/禁用审核通过/禁用生效的模板进行启用");
                    return;
                }
            }else{
                if(!(selRows[0].reportType===undefined||selRows[0].reportType===null||selRows[0].reportType==='02')){
                    Joyin.error("只能对状态为初始化/启用审核通过/启用生效的模板进行禁用");
                    return;
                }
            }

            Joyin.confirm(confirmMessage, function(){
                Joyin.dialog("信披模板启用禁用",basePath+"disclosure/information/reportpage/ReportPageManage003/init","1220px","70%",
                    {repId:selRows[0].repId,workflowType:workflowType},function () {
                            table.reload();
                })
            });
        } else {
            Joyin.error(message);
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
        	effective('01')
        });
        $('#reportPagel001_btn_neffective').click(function() {
    		effective('02')
        });
    });

});