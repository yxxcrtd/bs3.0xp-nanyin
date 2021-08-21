$(function() {
  //跳转的页码
    var pageCount = 1;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //报送类型
            datZipType : $('#datZipType').val()==null?null:$('#datZipType').val().join(","),
            //发送状态
            zipSendStatus : $('#zipSendStatus').val()==null?null:$('#zipSendStatus').val().join(","),
            //反馈状态
            returnZipSendStatus: $('#returnZipSendStatus').val()==null?null:$('#returnZipSendStatus').val().join(","),
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
        cols:[
            { title: "压缩包名称", field: 'datZipCode',width:200,sortable:false},
            { title: '压缩包放置路径',field:'datZipPath',width:150,sortable:false},
            { title: "报送类型", field: 'datZipTypeStr',width:100, type: 'datetime',sortable:false},
            { title: "报送明细类型", field: 'datZipDatTypeStr',width:150,sortable:false},
            { title: 'zip包状态',field:'datZipStatusStr',width:150,sortable:false},
            { title: "数据业务日期", field: 'statusVdate',width:100, type: 'datetime',sortable:false},
            { title: "DAT明细数", field: 'pibDetailCnt',width:100, sortable:false},
            { title: "报送日期", field: 'submitTime',width:100, type: 'datetime',sortable:false},
            { title: "报送状态", field: 'zipSendStatusStr',width:100,sortable:false},
            { title: '反馈状态',field:'returnZipSendStatusStr',width:100,sortable:false},
             {
                 title : '操作',
                     field : 'operation',
                 type : 'action',
                 width : 150,
                 buttons : [
                 {
                     text : '&#xe615;',
                     clas : 'batch',
                     title : '预览'
                 } ]
             }
        ]
    });

    table.bind('click', '.batch', function(data, index) {
        var height = '500px';
        var btns = {};
        var keyList = [];
        var dialogIndex = -1;
        var path = basePath + 'pbc/pbcsendzipdatfile/PbcSendZipDatFile/preView';
        if(data.status=='03'){
            Joyin.error("删除状态数据不支持预览");
            return;
        }

        var param={reportuuid:data.reportuuid,
            reporttype:data.datZipDatType,datZipCode:data.datZipCode}

        Joyin.ajax('preView', param, function(result) {
            if ("fail" == result){
                Joyin.error("所选数据不存在，请重新查询！");
                table.load();
            } else {
                dialogIndex = Joyin.dialog('预览', path, '1200px', height, param, function (params) {
                }, btns);
            }
        });

    });
       $(function(){
           // 绑定按钮事件
           $('#PbcSendZipDatFile_btn_search').click(function() {
               table.load();
           });
           /**
            * 重置
            */
       });
    $('#PbcSendZipDatFile_btn_reset').click(function() {
        $('#datZipType').val('');
        $('#zipSendStatus').val('');
        $('#returnZipSendStatus').val('');
        table.load();
    });
    // 导入资管产品信息事件
    $('#PbcSendZipDatFile_btn_prdImport').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
           Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + 'pbc/pbcsendzipdatfile/PbcSendZipDatFile/checkBaseInfoSubDat', {fileName: fileName}, function() {
                    Joyin.ajax(basePath + 'pbc/pbcsendzipdatfile/PbcSendZipDatFile/importBaseInfoSubDat', {fileName: fileName});
                });
            },function() {
                table.reload();
            });
        });
    });
    // 导入统计模板数据
    $('#PbcSendZipDatFile_btn_staticTemplateImport').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + 'pbc/pbcsendzipdatfile/PbcSendZipDatFile/checkMonthReport', {fileName: fileName}, function() {
                    Joyin.ajax(basePath + 'pbc/pbcsendzipdatfile/PbcSendZipDatFile/importMonthReport', {fileName: fileName});
                });
            },function() {
                table.reload();
            });
        });
    });
    //下载zip文件
    // 导出按钮
    $('#PbcSendZipDatFile_btn_sendFileDownLoad').click(function(){
        var selArr=table.getAllCheckedRows();
        Joyin.confirm("是否确认生成报送文件？", function() {
            var arr=new Array();
            for(var i in selArr){
                arr.push(selArr[i].datZipCode);
            }
            var data = JSON.stringify(arr);
            var param = {result:data};
            Joyin.ajaxSync(basePath + 'pbc/pbcsendzipdatfile/PbcSendZipDatFile/createZipFile', param, function(result) {
                var msg = result.message;
                /*if(msg != null && msg != ''){
                    Joyin.warn(msg);
                }*/
                if (result.data.length< 1) {
                    layer.alert('当前没有待报送的数据，请确认');
                } else {
                    param.result =result.data;
                    Joyin.exportExcel(basePath + 'pbc/pbcsendzipdatfile/PbcSendZipDatFile/download', param);
                }

            });
        });
    })


}());