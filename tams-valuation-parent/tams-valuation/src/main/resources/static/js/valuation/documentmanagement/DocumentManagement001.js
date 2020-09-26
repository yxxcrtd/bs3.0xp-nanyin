$(function() {
	/**
     * 导出
     */
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
     * 跳转页面
     */
/*    var redirectUrl = function(param){    	 
        var data = {seqNo : param}
        Joyin.exportExcel('downloadFile', data);
       // window.location.href = JoyinUtil.getRealPath("/valuation/documentmanagement/DocumentManagement001/downloadFile?"+param);
    }*/

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	fileName : $('#fileName').val(),
        	fileType : $('#fileType').val(),
        	createUser : $('#createUser').val(),
        	uploadTimeBegin: $('#uploadTime').startDate(),
        	uploadTimeEnd: $('#uploadTime').endDate()        	
        }       
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'findPage',
            type : 'POST',
            data : function() {           
                return getSearchParams();
            }
        },
        cols : [ {field : 'fileId', title : '流水号', sort : true,width:200,visible:false}, 
                {field : 'fileName', title : '文档名称', sort : true,width:240}, 
                {field : 'fileTypeFmt', title : '文档类型', sort : true,width:240,sortField:"fileType"}, 
                {field : 'createUser',title : '上传人', sort : true,width:200}, 
                {field : 'createDept',title : '所属部门',sort : true ,width:200},
                {field : 'uploadTime',title : '上传时间',sort : true ,width:200},        
                {field:'operation', title: '操作',width:80,fixed : 'right',width:200,
                    render : function(data, type, row, meta){                    
                        return '<a id="jumpTab" href=\'javascript:redirectUrl("'+row.fileId+'")\'><i class="iconfont icon-download"></i></a>';
                    },
                }       
               ]
    });    
    // 绑定新建事件
    $('#DocumentManagement001_btn_add').click(function() {
        Joyin.dialog(JoyinUtil.getLabel("新建上传文档（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                    basePath + "valuation/documentmanagement/DocumentManagement002/init",
                    "100%", "100%", {pageModel:PAGE_MODEL_NEW},function(){
                        table.reload();
                    });
    });
    
    /**
     * 导出
     */
    $('#DocumentManagement001_btn_export').click(function() {
        exportExcel();
    });
    
    /**
     * 查询
     */
    $('#DocumentManagement001_btn_search').click(function() {
        table.load();
    });
        
  })
  
    /**
     * 跳转页面
     */
    var redirectUrl = function(param){
        var data = {fileId : param}
        Joyin.exportExcel('downloadFile', data);
//        table.load();
//        window.location.href = JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003/downloadFile?"+param);
    }
  
  