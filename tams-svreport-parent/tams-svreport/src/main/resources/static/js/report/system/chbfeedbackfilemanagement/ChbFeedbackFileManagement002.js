/**
 * Created by Administrator on 2021/5/10 0014.
 */
var ChbFeedbackFileManagement002 = function() {
    return{
        table : $('#ChbFeedbackFileTable').joyintable({
            data:entity.detailList,
            checkbox : false,
            searchOnInit:true,
            isMulti:true,
            cols: [[
                {title: '反馈文件信息', field: 'fileInfo', align : "center", colspan: 3, sortable : false},
                {title: '登记表信息', field: 'regInfo', align : "center", colspan: 6, sortable : false}
            ],[
                { title: "报送流水号", field: 'fileRegSeqno',sortable:false},
                { title: "报送类型", field: 'fileRepTypeName',sortable:false},
                { title: "反馈结果", field: 'fileFeedbackResultName',sortable:false},
                { title: "报送流水号", field: 'regSeqno',sortable:false},
                { title: "唯一标识", field: 'keyCode',sortable:false},
                { title: "报送状态", field: 'regRepStatusName',sortable:false},
                { title: "反馈状态", field: 'regFeedbackStatusName',sortable:false},
                { title: "数据日期", field: 'regDataDate',type: 'datetime',sortable:false},
                { title: "数据状态", field: 'regDataStatusName',sortable:false},
                { field: 'feedbackMsg', visible:false}
            ]
            ]
        }),
        //进行文件处理
        fileHandle : function(){
            var paramObj = entity;
            Joyin.ajaxJson('fileHandle', paramObj, function(result) {
                if (result.statuCode == "200") {
                    JoyinUtil.showMessage(result.data,"info");
                }else {
                    JoyinUtil.showMessage(result.message,"error");
                }
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            });
        }
    }
}();

$(document).ready(function() {
    if(entity.matchedFlag == MATCHED_SUCCESS){
        $('#ChbFeedbackFileManagement002_btn_handle').toggle(true);
        $('#ChbFeedbackFileManagement002_btn_handle').click(ChbFeedbackFileManagement002.fileHandle);
    }
});
