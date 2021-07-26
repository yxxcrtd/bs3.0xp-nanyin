$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var object = $.serializeToObject('#searchForm');
        object.searchDate = $('#searchDate').val();
        if($('#importType').val()){
           object.importType = $('#importType').val();
         }
        return object;
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
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("RskOutValtblImportHis002.reference"),
                JoyinUtil.getRealPath("invest/risk/outsvaltableimporthis/RskOutValtblImportHis002/init"),
                "99%", "99%", data/*,
                    function(){table.reload();}*/);
        },
        cols:[
            {field:'fileName',sortable: false, width:300 , title: 'RskOutValtblImportHis001.fileName'}
            ,{field:'filePath',sortable: false, width:200 , title: 'RskOutValtblImportHis001.filePath'}
            ,{field:'startTimeStr', width:150 , title: 'RskOutValtblImportHis001.startTime',sortField:"startTime"}
            ,{field:'endTimeStr', width:150, title: 'RskOutValtblImportHis001.endTime',sortField:"endTime"}
            ,{field:'importTypeStr', width:100 ,title: 'RskOutValtblImportHis001.importType',sortField:"importType",render:function(data,type, row, meta){
                if(row.importType == 'T')
                    return  '<a  href="javascript:;" style="cursor: pointer;"><span style="color: green">'+data+'</span></a>';
                else if(row.importType == 'R')
                    return  '<a  href="javascript:;"  style="cursor: pointer;"><span style="color: orange">'+data+'</span></a>';
                else if(row.importType == 'F')
                    return  '<a  href="javascript:;" style="cursor: pointer;"><span style="color: red">'+data+'</span></a>';
            }}
            ,{field:'errorCodeStr',sortable: false, width:400 ,title: 'RskOutValtblImportHis001.errorCode',sortField:"errorCode",render:function(data,type, row, meta){
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
            ,{field: 'operation', title: 'SysCommon.operation', type: 'action',width:200 ,
                render: function (data, type, row, meta) {//科目映射，运行
                    var buttons = '<a title="科目映射" class="btn layui-btn assetCode" style="font-size:12px;color:white">科目映射</a>';
                    if (row.runFlag == 'Y'){
                        buttons += '<a title="运行" class="btn layui-btn run" style="margin-left:5px;font-size:12px;color:white">运行</a>';
                    }
                    return buttons;
                }
            }
        ]
    });

    /**
     * 绑定科目映射参数事件
     */
    table.bind('click', '.assetCode', function(data, index) {
        var newTab = {
            pageId: "RskValAssetCode001",
            title: "估值表资产科目映射",
            url: "/invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/init?id="+data.assetSubMapId+"&type='T'",
            id:"RISK_MANAGEMENT_001_005_002",
            icon:""
        };
        top.tab.tabDelete(newTab.id);
        top.tab.tabAdd(newTab);
    });

    /**
     * 绑定编辑关联指标参数事件
     */
    table.bind('click', '.run', function(data, index) {
        Joyin.ajaxJson(basePath + 'invest/risk/outsvaltableimporthis/RskOutValtblImportHis001/run',
            data,function(result){
                var resultMsg = result.data.resultMsg;
                var resultCode = result.data.resultCode;
                if (resultCode == 'T'){
                    Joyin.alert(resultMsg);
                    table.load();
                }else{
                    Joyin.error(resultMsg,function(){
                        //点击保存后关闭窗口
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        table.load();
                    });
                }
          });
    });

    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        //查询
        $('#RskOutValtblImportHis001_btn_search').click(function() {
            if ($('#searchDate').val() == '' || $('#searchDate').val() == null){
                Joyin.error("请选择查询日期");
                return;
            }
            table.load();
        });
        $('#RskOutValtblImportHis001_btn_export').click(function() {
            exportExcel();
        });
        $("#RskOutValtblImportHis001_btn_reset").on('click', function(){
            document.location.reload();
        });
    });
}());