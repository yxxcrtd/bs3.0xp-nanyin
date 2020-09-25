var id;
$(function () {
    id = $("#idhid").val();
});
var ReportConfirmManage003 = function() {
    //上传文件信息
    var ReportConfirmManage003_uploadFileInfo = [];
    return {
        /**
         * 文件上传后记录文件信息
         */
        onUploaded : function(res) {
            if(res && res.length > 0){
                Joyin.ajax('uploadFile', {
                    fileNo: res[0].fileNo,
                    fileName: res[0].fileName,// "\\home\\tamsapp\\upload\\2018\\11\\10\\交易模块图_20181110170637330.xlsx"
                    fileNameOnly:res[0].srcName,//交易模块图.xlsx
                    execDate: $('#executeDate').val(),
                    finprodId: $('#finprodId').val(),
                    finprodAbbr: $('#finprodAbbr').val(),
                    valBalFlag: $('#valBalFlag').val(),
                    id:sessionStorage.ReportConfirmManage001_rowid// 表REP_REVEAL.主键
                }, function(result) {
                    if("200" ==  result.statuCode){
                        sessionStorage.removeItem("ReportConfirmManage001_rowid");// 清除web存储
                    }
                    //EXCELIMP_E_0020
                    window.location.reload();
//                    if(!table.table){
//                        table.load();
//                    }else{
//                        table.reload();
//                    } 
//                    table.load();
                });
            }else{
                
            }
        }      
    };
}();