/**
 * excel导入共通处理js
 */

//定义表格
var table;

var ValExcelImpHandle = function() {
    //上传文件信息
    var ValExcelImpHandle_uploadFileInfo = [];
    return {
        /**
         * 文件上传后记录文件信息
         */
        onUploaded : function(res) {
            if(res && res.length > 0){
                Joyin.ajax('uploadFile', res[0], function(result){
                    if(result.status=='E'){
                        ValExcelImpHandle_uploadFileInfo.push(result);
                    }
                });
            }else{
                
            }
        },

        /**
         * 保存事件
         */
        save : function() {
            var param = {};
            param.impFileInfoList = ValExcelImpHandle_uploadFileInfo;
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function(){
                Joyin.ajaxJson(basePath + "valuation/system/excelimphandle/ValExcelImpHandle001/save", param, function(data) {
                    // 无有效上传文件的情况，报错，否则展示导入结果列表
                    if (data && data.impResultMsg) {
                        //弹出框弹出报错信息，点击确定刷新列表
                        layer.alert(data.impResultMsg,{icon: 2,closeBtn: 0 },
                                function (index) {
                                    if(table){
                                        table.reload({data: []});
                                    }
                                    layer.close(index);
                                }
                        );
                    } else if (data && data.resultList && data.resultList.length > 0) {
                        if(table){
                            table.reload({data: data.resultList});
                        }else{
                            table=$("#dataTable").joyintable({
                                data : data.resultList,
                                checkbox: false,
                                searchOnInit:true,
                                cols:[
                                    {field:'impFileName',  title: '导入文件名'},
                                    {field:'impTargetTableName',  title: '导入数据表'},
                                    {field:'importFlag',  title: '导入结果'},
                                    {field:'impResultMsg', title: '处理详情'}
                                ]
                            });
                        }
                        // 清空上传文件信息
                        ValExcelImpHandle_uploadFileInfo = [];
                    }
                });
            });
            
        }
    };
}();

$(function() {
    // 保存
    $("#ValVoucherQuery001_btn_save").on('click', ValExcelImpHandle.save);
    $("#ValVoucherQuery001_btn_reset").on('click', function(){
        document.location.reload();
    });
});