$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	fileName: $.trim($('#fileName').val()),
        	fileTimeBegin: $.trim($('#fileTime').startDate()),
        	fileTimeEnd: $.trim($('#fileTime').endDate()),
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
        checkbox: true,
		cellMinWidth : 100,
		cols: [
		   {field:'fileId', title: '序号',  width:320, sort: true},
		   {field:'fileName', title: '文件名称', width:320, sort: true},
		   {field:'fileTypeFt', title: '文件类型', width:320, sort: true,sortField:'fileType'},
		   {field:'filePath', title: '文件路径',  width:180, sort: true, visible:false},
		   {field:'fileTime', title: '文件日期',  width:180, sort: true},
		   {field:'', title: '备注',  sort: true},
		   {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
               buttons: [
                   {text: '&#xe640;',clas: 'delt',title: '删除'}]
           }
		],
	});
	
	/**
	 * 事件绑定
	 */
	var tableBind = function () {
	
	/**
     * 绑定事件：文件上传 - 新建
     */
    $("#DataFileUpload001_btn_upload").click(function(event){
        Joyin.dialog(JoyinUtil.getLabel("新建文件上传（ "+ "<span style='color:red'> * </span>" + "必填）"),
                   basePath + "valuation/fileupload/DataFileUpload002/init",
                   "50%", "85%", {pageModel : PAGE_MODEL_NEW,finprodId: "",operateType:"new"},function(){
                       table.reload();
                       
           });
    	});
    
    
    /**
     * 绑定事件：文件上传 - 删除
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {};
        object.fileId = data.fileId;
        var message = "是否删除" + data.fileName + "？";
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function(){
        	Joyin.ajaxJson(basePath + "valuation/fileupload/DataFileUpload001/delete", object, function(result) {
                table.reload();
            });
       });
   });
    
	/** 事件绑定: 文件上传-批量删除 */
	$('#DataFileUpload001_btn_delete').click(function() {
		var object = {};
		var rowArr = table.getAllCheckedRows();// 所有选中行数据
		if(rowArr == null || rowArr.length == 0 ){
			layer.msg("请至少选择1条记录进行删除。");
		}else{
			var keyStr = "";
    		for(var i=0; i<rowArr.length; i++){
    			keyStr = keyStr + rowArr[i].fileId + ",";
    		}
			object.fileId = keyStr; 
	    	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function(){
	           	Joyin.ajaxJson(basePath + "valuation/fileupload/DataFileUpload001/delete", object, function(result) {
	           		table.reload();
	           	});
           });
		}
	});
    
	}
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#DataFileUpload001_btn_search').click(function() {
            table.load();
        });

        //新建、修改、删除事件绑定
        tableBind();
    });
	
});



