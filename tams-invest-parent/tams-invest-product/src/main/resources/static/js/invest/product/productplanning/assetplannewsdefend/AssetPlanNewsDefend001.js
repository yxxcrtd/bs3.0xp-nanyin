(function () {
  /**
   * 获取查询参数
   */
  var getSearchParams = function() {
    var param = $.serializeToObject('#searchForm');
    return param;
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
    checkbox: false,
    cols:[
      {field:'assetPlanCode',visible:false}
      ,{field:'assetTypeStr', title: '资产大类',width:200,sortField:'assetType'}
      /*,{field:'prdPlanSerisName', title: '产品规划系列名称',width:200}*/
      ,{field:'vdate', title: '规划开始日',type:'date',width:200}
      ,{field:'edate', title: '规划结束日',type:'date',width:200}
      ,{field:'planInitAmt', title: '规划初始值（万元）',type:'number',width:200}
      ,{field:'planChangeAmt', title: '规划变动值（万元）',type:'number',width:200}
      ,{field:'planTargetAmt', title: '规划目标值（万元）',type:'number',width:200}
      ,{field:'remark', title: 'SysCommon.remark',width:200}
      ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
    ]
  });

  /**
   * 绑定编辑事件
   */
  table.bind('click', '.edit', function(data, index) {
    data.pageModel = PAGE_MODEL_EDIT;
    Joyin.dialog(JoyinUtil.getLabel("AssetPlanNewsDefend001.edit"),
      basePath + "invest/product/productplanning/assetplannewsdefend/AssetPlanNewsDefend002/init",
      "95%", "90%", {pageModel:PAGE_MODEL_EDIT,assetPlanCode:data.assetPlanCode},function() {
        table.reload();
      });
  });

  /**
   * 绑定删除事件
   */
  table.bind('click', '.delt', function(data, index) {
    var object = {assetPlanCode: data.assetPlanCode,assetType:data.assetType};
    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
      WorkFlow.doDeleteList(basePath + 'invest/product/productplanning/assetplannewsdefend/AssetPlanNewsDefend001/delete', object, "AssetPlanNewsDefend001_btn_delete", "AssetPlanNewsDefend002",function() {
        table.reload();
      });
    });
  });

  //新建
  $('#AssetPlanNewsDefend001_btn_add').click(function() {
    Joyin.dialog(JoyinUtil.getLabel("AssetPlanNewsDefend001.add"),
      basePath + "invest/product/productplanning/assetplannewsdefend/AssetPlanNewsDefend002/init",
      "98%", "95%", {pageModel:PAGE_MODEL_NEW});
  });

  /**
   * 以excel形式导出数据
   */
  var exportExcel = function() {
    var paramObj = getSearchParams();
    Joyin.exportExcel('export', paramObj);
  };


  $(function () {
    $("#AssetPlanNewsDefend001_btn_search").click(function () {
      table.load();
    });
    $('#AssetPlanNewsDefend001_btn_export').click(function () {
      exportExcel();
    });
  });
}());