
/**
 * excel导入共通处理js
 */
//定义表格
//var table;
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
    //searchOnInit: true,
    checkbox: false,
    cols: [
       {field:'errorLineNum', title: '警告/错误行号',width:200},
       //{field:'errorType', title: '警告/错误',width:200},
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

var FileUpload001 = function() {
    //上传文件信息
    var FileUpload001_uploadFileInfo = [];
    return {
        // 文件上传
        onUploaded : function(res) {        	
            if(res && res.length > 0){
                Joyin.ajax('uploadFile', {
                	fileName: res[0].fileName,
                	modelType: $('#modelType').val()
                }, function(result) {
                	//EXCELIMP_E_0020
                    //window.location.reload();
//                    if(result.data){
//                        //alert(JSON.stringify(result.data));
//                        Joyin.dialog(JoyinUtil.getLabel("模板校验查看"),
//                                JoyinUtil.getRealPath("/valuation/datatransplant/TemplateCheck001/init"),
//                                "60%", "90%", {pageModel:PAGE_MODEL_REFERENCE, data:JSON.stringify(result.data)},
//                                function(){
//                                    window.location.reload();
//                                    });
//                    }
//                    window.location.reload();
                    
                    if(result.data){
                        $("#showTable").show();
                        var data = JSON.stringify(result.data);
                        $("#errorData").val(data);
                        table.reload();  
                        $('#modelType').val("").trigger("change");
                    }else{
                        window.location.reload();
                    }
                    
                    
                });
            }
        }      
    };
}();

$(function() {

});
