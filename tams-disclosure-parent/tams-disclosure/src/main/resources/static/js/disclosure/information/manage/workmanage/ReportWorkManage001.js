'$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            jobName: $.trim($('#jobName').val()), 
            jobId:$.trim($('#jobId').val())
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        minWidth:100,
		checkbox: true,
		cols: [ 
		        {field:'jobId', title: '作业编号',sort: true},
		        {field:'jobName', title: '作业名称',sort: true},
		        {field:'loopDateDetail', title: '作业执行规则',visible:true},
		        /*{field:'startDate', title: '开始执行时间',sort: true,formatter : 
                    function(cellvalue, options, rowObject){ 
                    if(cellvalue == null){
                        return '';
                    }else{
                        return new Date(cellvalue).format("yyyy-MM-dd HH:mm");
                    }
                }},
		        {field:'endDate', title: '结束执行时间',sort: true,visible:true,formatter : 
                    function(cellvalue, options, rowObject){ 
                    if(cellvalue == null){
                        return '';
                    }else{
                        return new Date(cellvalue).format("yyyy-MM-dd HH:mm");
                    }
                }},*/
		        {field:'execDate', title: '上次执行时间',sort: true,visible:true,formatter : 
                    function(cellvalue, options, rowObject){ 
                    if(cellvalue == null){
                        return '';
                    }else{
                        return new Date(cellvalue).format("yyyy-MM-dd HH:mm");
                    }
                }},
		        {field:'preExecDate', title: '预计执行时间',sort: true,visible:true,sortField:"jobId"},
		        {field:'operation',width:20, title: 'SysCommon.operation', type:'action', 
//		        	buttons: ['edit',{
//		                clas: 'iconfont icon-startup_fill',
//		                title: '加入执行队列'
//		           }]
		        	render : function(data, type, row, meta){
		        		 var html = "";
		        		 if(purviewElements.indexOf('_edit')!=-1){
		        			 html += "<i class='iconfont icon-edit edit' title ='编辑'></i>";
		        		 }
		        		 if(purviewElements.indexOf('_manual')!=-1){
		        			 html +="<i class = 'iconfont icon-startup manual' title='加入执行队列'></i>";
		        		 }
		        		 return html;
		        	}
		        }
//                {field:'operation', title: '操作',fixed : 'right',
//                    render : function(data, type, row, meta){
//                    	var str = '<a id="jumpTab" href=\'javascript:redirectUrl2("'+1+'","'+row.jobId+'")\'><i title="修改" style="color:#666;" class="edit iconfont icon-edit"></i></a>      ';
//                    	str +=  '<a id="jumpTab" href=\'javascript:redirectUrl2("'+2+'","'+row.jobId+'")\'><i title="立即执行" class="iconfont icon-startup_fill"></i></a>';
//                        return str;
//                    	//return '<a id="jumpTab" href=\'javascript:redirectUrl2("'+row.jobId+'")\'><i class="iconfont icon-edit"></i></a>';
//                    } 
//                }
		]
	});

	/**
     * 编辑页面
     */
    table.bind('click', '.edit', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("编辑"),
                JoyinUtil.getRealPath("/disclosure/information/manage/workmanage/ReportWorkManage002/init"),
                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,jobId:data.jobId},
                function(data){
                    table.reload();
                }); 
    });
    /**
     * 立即执行
     */
    table.bind('click', '.manual', function(data, index) {
        Joyin.confirm("请确认是否立即执行？", function(){
            var execDate = $("#execDate").val();
            if(execDate == null || execDate == ''){
                Joyin.alert('执行日期不能为空');
            }else {
                Joyin.ajaxJson('runJobNow', {jobId:data.jobId,execDate:execDate}, function(result) {
                    table.reload();
                });
            }
        });
    
    });
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
    /**
     * 跳转页面2
     */
    var redirectUrl2 = function(flag,param){
    	if("1" == flag){
    		Joyin.dialog(JoyinUtil.getLabel("编辑"),
    	            JoyinUtil.getRealPath("/disclosure/information/manage/workmanage/ReportWorkManage002/init"),
    	            "90%", "90%", {pageModel:PAGE_MODEL_EDIT,jobId:param},
    	            function(data){
    	            	table.reload();
    	            });  
    	}
    	else{
    		Joyin.confirm("请确认是否立即执行？", function(){
    			var execDate = $("#execDate").val();
    			if(execDate == null || execDate == ''){
    				Joyin.alert('执行日期不能为空');
    			}else {
    				Joyin.ajaxJson('runJobNow', {jobId:param,execDate:execDate}, function(result) {
    					table.reload();
    				});
    			}
            });
    	}
        
    }
    window.redirectUrl2 = redirectUrl2;
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ReportWorkManage001_btn_search').click(function() {
            table.load();
        });
        $('#ReportWorkManage001_btn_export').click(function() {
//            exportExcel();
        });
        $('#ReportWorkManage001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("新建"),
                    JoyinUtil.getRealPath("/disclosure/information/manage/workmanage/ReportWorkManage002/init"),
                    "70%", "90%", {pageModel:PAGE_MODEL_NEW},
                    function(data){
                    	table.reload();
                    }); 
        });
        
        /**
         * 删除
         */
        $('#ReportWorkManage001_btn_delete').click(function() {
        	
        	var object = {};
        	var rowArr = table.getAllCheckedRows();// 所有选中行数据
        	if(rowArr == null || rowArr.length == 0){
        		layer.msg("请选择一条记录！");   		
        	}/*else if(rowArr != null && rowArr.length>1){
        		
    			layer.msg("请选择一条记录！");
    		}*/else{		
        	    object.selectedItem = rowArr;
    	    	object.operateType = "delete";
    		   	Joyin.ajaxJson(basePath + 'disclosure/information/manage/workmanage/ReportWorkManage001/check', object, function(result) {
    		           Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	    		           	Joyin.ajaxJson(basePath + 'disclosure/information/manage/workmanage/ReportWorkManage002/delete', object, function(result) {
	    		           		table.reload();
	    		           	});
    		           });
    	       });
        	}
        });
        
        
        /**
         * 导出
         */
        $('#ReportWorkManage001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 刷新作业规则
         */
      /*  $('#ReportWorkManage001_btn_refresh').click(function() {
		   	Joyin.ajaxJson(basePath + 'disclosure/information/manage/workmanage/ReportWorkManage001/refreshCheck', null, function(result) {
		           Joyin.confirm(JoyinUtil.getMessage("INFOREFRESH_E_0001",result.data), function(){
 		           	Joyin.ajaxJson(basePath + 'disclosure/information/manage/workmanage/ReportWorkManage001/refreshJobRule', null, function(result) {
 		           		table.reload();
 		           	});
		           });
	       });
        });*/
    });     
	
});



