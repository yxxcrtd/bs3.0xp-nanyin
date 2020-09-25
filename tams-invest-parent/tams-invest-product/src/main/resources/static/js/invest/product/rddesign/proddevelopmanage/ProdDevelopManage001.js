(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	param.startQueryDate = $('#queryDate').startDate();
    	param.endQueryDate = $('#queryDate').endDate();
    	return param;
    };
    // 表格的列
    var tabCols = [
        {field:'seqNo', visible:false}
        ,{field:'fileNo', visible:false}//附件
        ,{field:'generateTimeStr', title: 'ProdDevelopManage001.generateTime', width:200, sortField:'generateTimeStr'}//生成日
        ,{field:'prdDesignId', title: 'ProdDevelopManage001.prdDesignId', width:200,sortField:'prdDesignId'}//产品设计系列编号
		,{field:'prdDesignName', title: 'ProdDevelopManage001.prdDesignName', width:200,sortField:'prdDesignName'}//产品设计系列名称
		,{field:'feasibilityResultStr', title: 'ProdDevelopManage001.feasibilityResult', width:200,sortField:'feasibilityResult'}//产品可行性分析结果
        ,{field:'prdRiskLevelStr', title: 'ProdDevelopManage001.prdRiskLevel', width:200,sortField:'prdRiskLevel'}//产品风险等级评定结果
        ,{field:'prdDesignResult', title: 'ProdDevelopManage001.prdDesignResult', width:200,sortable:false}//产品要素设计结果
        ,{field:'prdLoadResult', title: 'ProdDevelopManage001.prdLoadResult', width:200,sortable:false}//产品压力测试结果
        ,{field:'prdMockResult', title: 'ProdDevelopManage001.prdMockResult', width:200,sortable:false}//模拟试算结果
        ,{field:'schemeResulrStr', title: 'ProdDevelopManage001.schemeResulr', width:200,sortField:'schemeResulr'}//是否通过
		,{field:'updateTimeStr', title: 'ProdDevelopManage001.updateTime', width:200,sortField:'updateTimeStr'}//更新时间
        ,{field:'operation', title: 'SysCommon.operation', type:'action',width : 120, buttons: ['edit','delt',{ text : '&#xe601;', clas : 'download', title : '下载' }]}
    ];
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
    	
		cols: tabCols,
	});
    
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/proddevelopmanage/ProdDevelopManage001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("Productelementdes002.edit"),
                    basePath + "invest/product/rddesign/proddevelopmanage/ProdDevelopManage002/init",
                    "98%", "95%", {seqNo : data.seqNo, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
            
        });
    });
    
    /**
     * 绑定删除事件
     */
   table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete", seqNo : data.seqNo,generateTimeStr : data.generateTimeStr,prdDesignId : data.prdDesignName,prdDesignName : data.prdDesignName};
    	data.operateType = 'delete';
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/proddevelopmanage/ProdDevelopManage001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/rddesign/proddevelopmanage/ProdDevelopManage001/delete', object, "ProdDevelopManage001_btn_delete", "ProdDevelopManage002");
            });
        });
    });
   
   /**
    * 下载附件
    */
   table.bind('click', '.download', function(data, index) {
       if (!data.fileNo || data.fileNo == null) {
           Joyin.error("未上传附件，不能下载！");
           return;
       }
       parm = {};
       var url = JoyinUtil.getRealPath('/framework/download/' + data.fileNo);
       Joyin.ajax(url, parm, function downloadFileByForm() {
           Joyin.exportExcel('/framework/download/' + data.fileNo, parm);
       },
       function errorDownload(){
           Joyin.error("文件未找到！");
       }
       );
   });
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    
    /**
     * 新建
     */
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("Productelementdes002"),
                JoyinUtil.getRealPath("invest/product/rddesign/proddevelopmanage/ProdDevelopManage002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();})
    }
    
	$(function() {
        $('#ProdDevelopManage001_btn_search').click(function() { 
            table.load();
        });
        $('#ProdDevelopManage001_btn_export').click(function() {
        	exportExcel();
        });
        $('#ProdDevelopManage001_btn_new').click(function() {
        	openNew();
        });
	});
}());