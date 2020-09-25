$(function() {
    /**
     * 获取查询参数
     */
  var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        if(param.strategyId){
            param.strategyId = param.strategyId.join(",");
        }
        if(param.type2){
            param.type2 = param.type2.join(",");
        }
        if(param.useStatus){
            param.useStatus = param.useStatus.join(",");
        }
        return param;
  };

    /**
     * 定义数据表格
     */
  var table=$("#dataTable").joyintable({ 
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        }
        ,tree:true
        ,checkbox: false
        ,searchOnInit:false
        ,lazyload:true
        ,treeId:'assetTypeId'//树形id字段名称
        ,treeUpId:'pAssetTypeId'//树形父id字段名称
        ,treeShowName:'strategyName'//以树形式显示的字段
        /**
         * 定义表格名称
         */
        ,cols:[
            {field:'useStatusStr', width:220, title: 'PlanningStrategyPool001.useStatus'}//使用状态
            ,{field:'strategyId', width:240, title: 'PlanningStrategyPool001.strategyId'}//策略代码
            ,{field:'strategyName', width:240, title: 'PlanningStrategyPool001.strategyName'}//策略名称
            ,{field:'assetTypeId', title: '树形结构代码',visible:false}
            ,{field:'type2Str', width:220, title: 'PlanningStrategyPool001.type2',sortable:false}//资产大类
            ,{field:'ratio', width:220, title: 'PlanningStrategyPool001.ratio'}//比例
            ,{field:'operation', title: 'SysCommon.operation', type:'action',
            render: function (data, type, row, meta) {
                if (row.level != 1){
                    return "";
                }
                else{
                    var str = '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>      ';
                    str = str + '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
                    return str;
                }
            }}//编辑和删除事件
        ]
  });
    
    /**
     * 绑定删除事件
     */
  table.bind('click', '.delt', function(data, index) {
        if(data.strategyId){
             var object = {strategyId: data.strategyId, operateType:"delete"};
                Joyin.ajaxJson(basePath + 'invest/product/productplanning/planningstrategypool/PlanningStrategyPool001/deleteCheck', object, function(result) {//数据验证
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'invest/product/productplanning/planningstrategypool/PlanningStrategyPool001/delete', object, 
                        		"PlanningStrategyPool001_btn_delete", "PlanningStrategyPool002",function() {
                            table.reload();
                        });
                    });
                });
        }
  });
    /**
     * 绑定修改事件
     */
  table.bind('click', '.edit', function(data, index) {
        if(data.strategyId){
            Joyin.dialog(JoyinUtil.getLabel("PlanningStrategyPool002"),
                    basePath+ "invest/product/productplanning/planningstrategypool/PlanningStrategyPool002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_EDIT,strategyId:data.strategyId},function(){
                            table.reload();
                        });
        }
  });
    /**
     * 画面加载完成
     */
  $(function() {
        // 绑定查询按钮
        $('#PlanningStrategyPool001_btn_search').click(function() {
            table.load();//加载表格
        });
        //绑定到处按钮
        $('#PlanningStrategyPool001_btn_export').click(function() {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        });
        //绑定新建按钮
        $('#PlanningStrategyPool001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PlanningStrategyPool002"),
                    basePath+ "invest/product/productplanning/planningstrategypool/PlanningStrategyPool002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_NEW},function(){
                            table.reload();
                        });
        });
  });
}());