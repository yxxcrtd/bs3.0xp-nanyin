/**
 * 认购信息组件JS
 * @auth zhangnian
 */
(function(){
    var getElementById = function(id) {
        return $('#' + entityName_BussSubscriptionInfo + '_' + id);
    };
    //初始化
        /**
         * 定义数据表格
         */
    var cols = [
            {field:'institutionName', width: 200, title: 'BussSubscriptionInfo.institutionName',edit:true}  
            ,{field:'subscribAmout', width: 200, title: 'BussSubscriptionInfo.subscribAmout',edit:true,type:'number'}
            ,{field:'subscribDate',  width: 200,title: 'BussSubscriptionInfo.subscribDate',edit:true,type:'date'}
            ,{field:'subscribRate', width: 200, title: 'BussSubscriptionInfo.subscribRate',edit:true,type:'number'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
        ]
    var table =$("#bussSubscriptionInfoTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/project/BussSubscriptionInfo/initInfo'),
            type: 'POST',
            data: getPreviewParams()
        },
        cols:cols,
        searchOnInit: true,
        loadonce:true,
        checkbox:false,
        height:290,
        page: false,
        addbtn:true
    });
        
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    var getAllRows = function(){
            return table.getAllRows();
    }
    
    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        var param = {}
        param.finprodId = $("#finprodId").val();
        param.workflowId = $("#workflowId").val();
        param.entityName = entityName_BussSubscriptionInfo;
        param.pageModel = pageModel;
        return param;
    }
    
    // 公开接口
    window.BussSubscriptionInfo = {
            getAllRows:getAllRows
    };
}());