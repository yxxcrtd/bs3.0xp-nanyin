

/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            execDate: $('#executeDate').val(),
            finprodId: $('#finprodId').val(),
            finprodAbbr: $('#finprodAbbr').val(),
            valBalFlag: $('#valBalFlag').val(),
            valDate: $('#valDate').val()
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
        checkbox: false,
        cellMinWidth : 100,
        cols: [ 
               {field:'finprodId', title: '产品代码',width:150, sort: true,fixed : 'left'},
               {field:'fileId', title: 'uuid',visible:false,width:150, sort: true,fixed : 'left'},
               {field:'valBalFlag', title: '估值余额标志',width:150, sort: true,
                   render: function (data, type, row, meta) {
                   if(row.valBalFlag=='01'){
                       return '估值表';
                   }else if(row.valBalFlag=='02'){
                       return '余额表';
                   }
               }},
               {field:'valDate',  title: '估值日期',width:80, sort:true,visible:false}, 
               {field:'valDateS', sortField:'valDate',title : '估值日期',width:120,sort : true},
               {field:'fileName', title: '文件名称',width:260, sort: true},
               {field:'filePath', title: '文件路径',width:320, sort:true},
               {field:'uploadDate',  title: '上传日期',width:80, sort:true,visible:false},                          
               {field:'uploadDateS', sortField:'uploadDate',title: '上传日期',width:120,sort: true,align:"right"},
               {field:'operation', title: '下载',width:60,fixed : 'right',
                   render : function(data, type, row, meta){
                       console.log(row);
                       return '<a id="jumpTab" href=\'javascript:redirectUrl("'+row.fileId+'")\'><i class="iconfont icon-download"></i></a>';
                   }
                   
               },
               {field:'operations', title: '删除',width:60,fixed : 'right',
                   render : function(data, type, row, meta){
                       console.log(row);
                       return '<a id="jumpTab" href=\'javascript:redirectUrls("'+row.fileId+'")\'><i class="iconfont icon-delete"></i></a>';
                   }
               }
        ],
    });
    
    table.load();

var ValNetValueConfirm003 = function() {
    //上传文件信息
    var ValNetValueConfirm003_uploadFileInfo = [];
    return {
        /**
         * 文件上传后记录文件信息
         */
        onUploaded : function(res) {
            if(res && res.length > 0){
                Joyin.ajax('uploadFile', {
                	fileName: res[0].fileName,
                	fileNameOnly:res[0].srcName,
                	execDate: $('#executeDate').val(),
                	finprodId: $('#finprodId').val(),
                	finprodAbbr: $('#finprodAbbr').val(),
                	valBalFlag: $('#valBalFlag').val(),
                	valDate: $('#valDate').val()
                }, function(result) {
                	//EXCELIMP_E_0020
                	window.location.reload();
//                    if(!table.table){
//                        table.load();
//                    }else{
//                        table.reload();
//                    } 
                    table.load();
                });
            }
        }      
    };
}();

    $(function() {
//        
//        if(!table.table){
//            table.load();
//        }else{
//            table.reload();
//        } 
        // 重置
        $("#ValNetValueConfirm003_btn_reset").on('click', function(){
            document.location.reload();
            if(!table.table){
                table.load();
            }else{
                table.reload();
            }
        });
    });
    /**
     * 下载
     */
    var redirectUrl = function(param){
        var data = {fileId : param}
        Joyin.exportExcel('downloadFile', data);
//        window.location.href = JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003/downloadFile?"+param);
    }
    /**
     * 删除
     */
    var redirectUrls = function(param){
        Joyin.ajax('deleteFile', {fileId : param},function(result) {
            window.location.reload();
        });
//        window.location.href = JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003/downloadFile?"+param);
    }