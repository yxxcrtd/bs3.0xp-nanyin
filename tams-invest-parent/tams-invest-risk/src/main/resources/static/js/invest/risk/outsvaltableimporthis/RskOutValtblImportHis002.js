$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        debugger;
        return {
            fileName: $("#fileName").val(),
            valdate: $("#valdate").val(),
            portfolioName:$("#portfolioName").val()
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'selectTraceByKey',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        checkbox: false,
        cols:[
            {field:'fileName',sortable: false, width:300 , title: 'RskOutValtblImportHis001.fileName'}
            ,{field:'filePath',sortable: false, width:200 , title: 'RskOutValtblImportHis001.filePath'}
            ,{field:'startTimeStr', width:200 , title: 'RskOutValtblImportHis001.startTime',sortField:"startTime"}
            ,{field:'endTimeStr', width:200, title: 'RskOutValtblImportHis001.endTime',sortField:"endTime"}
            ,{field:'importTypeStr', width:100 ,title: 'RskOutValtblImportHis001.importType',sortField:"importType",render:function(data,type, row, meta){
                if(row.importType == 'T')
                    return  '<a  href="javascript:;" style="cursor: pointer;"><span style="color: green">'+data+'</span></a>';
                else if(row.importType == 'R')
                    return  '<a  href="javascript:;"  style="cursor: pointer;"><span style="color: orange">'+data+'</span></a>';
                else if(row.importType == 'F')
                    return  '<a  href="javascript:;" style="cursor: pointer;"><span style="color: red">'+data+'</span></a>';
            }}
            ,{field:'errorCodeStr',sortable: false, title: 'RskOutValtblImportHis001.errorCode',sortField:"errorCode",render:function(data,type, row, meta){
                    return  '<a  href="javascript:;" style="cursor: pointer;"><span style="color: red">'+data+'</span></a>';
            }}
            ,{field:'valdate',  visible : false}
            ,{field:'valdateStr',  visible : false}
            ,{field:'impdate',  visible : false}
            ,{field:'portfolioId',  visible : false}
            ,{field:'portfolioName',  visible : false}
            ,{field:'runFlag',  visible : false}
            ,{field:'assetSubMapId',  visible : false}
            ,{field:'importType',  visible : false}
        ] ,
        loadonce:true
    });

}());