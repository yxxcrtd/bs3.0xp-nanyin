
/**
 * excel导入共通处理js
 */
//定义表格
var getSearchParams = function() {
    return {
        errorData: $("#errorData").val()
    }
};

/**
 * 定义数据表格
 */
var table=$("#dateTable").joyintable({
    ajax: {
        url: 'searchList',
        type: 'POST',
        data: function() {
            return getSearchParams();
        }
    },
    checkbox: false,
    cols: [
       {field:'errorLineNum', title: '警告/错误行号',width:200},
       {field:'errorType', width:200, title: '警告/错误', align: 'center'
           ,render: function (data, type, row, meta) {
               if(data == '错误') {
                   return '<span class="layui-badge-rim layui-bg-red">错误</span>';
               }
               return '<span class="layui-badge-rim layui-bg-orange">警告</span>';
           }
       },
       {field:'errorMessage', title: '警告/错误信息'}
    ],
    loadonce:true,
    page:false
});
table.load();


/**
 * 导出数据
 */
var exportExcel = function() {
    Joyin.exportExcel('export', {
        datas : $("#datas").val(),
        exportType:$("#exportType").val()
    });
};


/**模版类型下拉选项change事件**/
var gradeChange=function(){
	$("#exportbtn").hide();
	$("#datas").val("");
	$("#exportType").val("");
	
	$("#showTable").hide();
	$("#errorData").val("");
}





var FileUpload001 = function() {
    //上传文件信息
    var FileUpload001_uploadFileInfo = [];
    return {
        // 文件上传
        onUploaded : function(res) {
        	gradeChange();
            if(res && res.length > 0){
                Joyin.ajax('uploadFile', {
                	fileName: res[0].fileName,
                	modelType: $('#modelType').val()
                }, function(result) {
                	if(result.data.rstCode == 'N'){
                		$("#showTable").show();
                        var data = JSON.stringify(result.data.checkList);
                        $("#errorData").val(data);
                        table.reload();  
                	}else if(result.data.rstCode == 'Y'){
                		$("#datas").val(result.data.batchSeqno);
                		$("#exportType").val(result.data.modelType);
                		$("#exportbtn").show();
                    }else{
                        window.location.reload();
                    }
                });
            }
        }      
    };
}();

$(function() {
	$('#Transformation001_btn_export').click(function() {
        exportExcel();
    });
	
	$('#modelType').change(function(){
        gradeChange();
    });
});
