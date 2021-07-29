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
            { title: "压缩包编号", field: 'datZipCode',width:200,sortable:false},
            { title: "报送类型", field: 'datZipTypeStr',width:100, type: 'datetime',sortable:false},
            { title: "报送明细类型", field: 'datZipDatTypeStr',width:150,sortable:false},
            { title: "业务日期", field: 'submitTime',width:100, type: 'datetime',sortable:false},
            { title: "DAT明细数", field: 'pibDetailCnt',width:100, type: 'datetime',sortable:false},
            { title: "报送状态", field: 'zipSendStatusStr',width:100,sortable:false},
            { title: '反馈状态',field:'returnZipSendStatusStr',width:100,sortable:false},
            { title: '反馈消息',field:'returnMsg',width:150,sortable:false},
            { title: '压缩包放置路径',field:'datZipPath',width:150,sortable:false},
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
        var height = '600px';
        var btns = {};
        var keyList = [];
        var dialogIndex = -1;
        var path = basePath + 'svreport/pbc/PbcSendZipDatFile/preView';
        if(data.status=='03'){
            Joyin.error("删除状态数据不支持预览");
            return;
        }

        var param={reportuuid:data.reportuuid,
            termid:data.termid,reporttype:data.reporttype}

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
       });
}());