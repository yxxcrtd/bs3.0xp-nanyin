$(function() {
    
  function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.workflowId = workflowId;
        var tableData = table.getAllRows();
        object.planningStrategyPoolDetailList = tableData;
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/planningstrategypool/PlanningStrategyPool002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productplanning/planningstrategypool/PlanningStrategyPool002/save', object, 
                    		"PlanningStrategyPool002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/productplanning/planningstrategypool/PlanningStrategyPool002/save', object, 
                    		"PlanningStrategyPool002_btn_submit");
                }
            });
        });
    
  };
    /**
     * 定义数据表格
     */
  var table = $("#dataTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/invest/product/productplanning/planningstrategypool/PlanningStrategyPool002/queryPlanningStrategyPoolDetail'),
            type: 'POST',
            data: function() {
                return {workflowId : workflowId,strategyId : $("#strategyId").val()};   
            }
        },
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: true,
        height : 200,
        cols:[
            {field:'type2', title: 'PlanningStrategyPool001.type2', width:400,edit:true, sortable: false, editOption:{type:'select',id:'type2',formatoption:true,result: ASSET_TYPE}}
           ,{field:'ratio', title: 'PlanningStrategyPool001.ratio', width:400,edit:true,type:'number',digit:6,sortable:false}//比例
           ,{field:'operation',width:80,title: 'SysCommon.operation', type:'action', buttons: [ 'delt']}
       ],
  });
    
    /**
     * 绑定删除事件
     */
  table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    $(function(){
        // 绑定按钮事件
        $('#PlanningStrategyPool002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PlanningStrategyPool002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
  })
}());