/**
 * RepEntrustedApproval001 委托
 */
var rsk = function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var eventId = $('#eventId').val();
        return {
        	eventId : eventId
            
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("RepEntrustedApproval002"),
                basePath + "invest/riskredis/quota/entrustedapproval/RepEntrustedApproval002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW,
            	eventId: data.eventId});
        },
        cols:[
            {field:'eventId',title:'RepEntrustedApproval.eventId',width:200}
            ]
    });
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RepEntrustedApproval001_btn_search').click(function() {
            table.load();
        });
    });
}();


