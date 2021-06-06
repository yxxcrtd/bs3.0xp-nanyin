/**
 * Created by Administrator on 2021/5/10 0014.
 */
var ChbFeedbackFileManagement001 = function() {
    var laydate = layui.laydate;
    laydate.render({
        elem: '#reportSendDate'
        ,type: 'datetime'
        ,format: 'yyyy-MM-dd HH:mm:ss'
    });
    laydate.render({
        elem: '#reportAcceptDate'
        ,type: 'datetime'
        ,format: 'yyyy-MM-dd HH:mm:ss'
    });

    return{
        getPageParams : function() {
            var obj = $.serializeToObject('#searchForm');
            return obj;
        },
        table : $('#ChbFeedbackFileTable').joyintable({
            ajax: {
                url: 'searchPage',
                type: 'POST',
                data: function() {
                    return ChbFeedbackFileManagement001.getPageParams();
                }
            },
            checkbox : true,
            cols: [
                { title: "报文编码", field: 'rtnRepMesNo',sortField:'RTN_REP_MES_NO',width:150},
                { title: "报文编码", field: 'sendRepMesNo', visible:false},
                { title: "报送发送时间", field: 'reportSendDate',type: 'datetime',sortField:'REPORT_SEND_DATE',width:90},
                { title: "报送接收时间", field: 'reportAcceptDate',type: 'datetime',sortField:'REPORT_ACCEPT_DATE',width:90},
                { title: "业务组类型", visible:false, field: 'businessGroupTypeName',sortField:'BUSINESS_GROUP_TYPE',width:110},
                { title: "报送类型", field: 'reportEtrType', visible:false},
                { title: "报送类型", field: 'reportEtrTypeName',sortField:'REPORT_ETR_TYPE',width:130},
                { title: "文件ID", field: 'fileId',sortField:'FILE_ID',width:160},
                { title: "文件名称", field: 'fileName',sortField:'FILE_NAME',width:160},
                { title: "文件路径", field: 'filePath',sortable:false,width:150},
                { field: 'handleStatus',visible:false},
                { title: "处理状态", field: 'handleStatusName',sortField:'HANDLE_STATUS',width:100},
                { title: "是否迁移", field: 'moveFlagName',sortField:'MOVE_FLAG',width:100},
                { title: "迁移目录", field: 'movePath',sortable:false,width:150},
                { title: "反馈信息",field: 'feedbackMsg', width:170, visible:false},
                //{field:'operation', title: '操作', type:'action', buttons: ['edit']}
            ]
        }),
        //点击打开明细页面
        openMatchPage : function(){
            if(ChbFeedbackFileManagement001.table!=null && ChbFeedbackFileManagement001.table!=''){
                var selArr = ChbFeedbackFileManagement001.table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0 || selArr.length > 1) {
                Joyin.error("为了保证数据准确性，请选择一条文件记录进行处理。");
                return;
            }
            if(selArr[0].handleStatus == '02'){
                Joyin.error("请选择未处理的记录！");
                return;
            }
            Joyin.dialog(JoyinUtil.getLabel("中债反馈文件匹配"),
                basePath + "svreport/system/chbfeedbackfilemanagement/ChbFeedbackFileManagement002/init",
                "95%", "95%", {		//pageModel:PAGE_MODEL_REFERENCE,
                    repSeqNo:$('#repSeqNo').val(),       //报送流水号
                    rtnRepMesNo:selArr[0].rtnRepMesNo,       //报文编码
                    filePath:selArr[0].filePath,        //文件路径
                    fileName:selArr[0].fileName,        //文件名
                    reportEtrType:selArr[0].reportEtrType,       //报文指令类型
                },function(){
                    ChbFeedbackFileManagement001.table.reload();
                });
        },
        backup:function(){
            if(ChbFeedbackFileManagement001.table!=null && ChbFeedbackFileManagement001.table!=''){
                var selArr = ChbFeedbackFileManagement001.table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0 || selArr.length > 1) {
                Joyin.error("为了保证数据准确性，请选择一条文件记录进行处理。");
                return;
            }
            if(selArr[0].handleStatus == '02'){
                Joyin.error("请选择未处理的记录！");
                return;
            }
            Joyin.ajaxJson('backup', selArr[0], function(result) {

            });
        }
    }
}();

$(document).ready(function() {
 // 查询按钮点击事件
    $('#ChbFeedbackFileManagement001_btn_search').click(function() {
        if($('#searchForm').joyinvalid()) {
            ChbFeedbackFileManagement001.table.load();
        }
    });
    $('#ChbFeedbackFileManagement001_btn_match').click(ChbFeedbackFileManagement001.openMatchPage);
    $('#ChbFeedbackFileManagement001_btn_backup').click(ChbFeedbackFileManagement001.backup);
    if(SEARCH_ON_INIT == 'Y'){
        if($('#searchForm').joyinvalid()) {
            ChbFeedbackFileManagement001.table.load();
        }
    }
});
