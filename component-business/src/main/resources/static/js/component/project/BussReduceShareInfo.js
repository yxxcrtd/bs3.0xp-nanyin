/**
 * 减持信息组件JS
 * @auth zhangnian
 */
(function(){
    var getElementById = function(id) {
        return $('#' + entityName_BussReduceShareInfo + '_' + id);
    };
    //初始化
        /**
         * 定义数据表格
         */
    var cols = [
             {field:'reduceDate', width: 300, title: 'BussReduceShareInfo.reduceDate',edit:true,type:'date'}
            ,{field:'reducePer', width: 300, title: 'BussReduceShareInfo.reducePer',edit:true,type:'number'}
            ,{field:'remark',  width: 300,title: 'BussReduceShareInfo.remark',edit:true}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
        ]
    var table =$("#bussReduceShareInfoTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/project/BussReduceShareInfo/initInfo'),
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
        param.entityName = entityName_BussReduceShareInfo;
        param.pageModel = pageModel;
        return param;
    }
    
    // 公开接口
    window.BussReduceShareInfo = {
            getAllRows:getAllRows
    };
}());