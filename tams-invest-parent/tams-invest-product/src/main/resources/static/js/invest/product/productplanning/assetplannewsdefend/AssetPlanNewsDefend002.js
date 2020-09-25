(function () {

  /**
   * 页面加载初始化
   */
  var options = {
    page: false,
    loadonce:true,
    ajax: {
      url: JoyinUtil.getRealPath('invest/product/productplanning/assetplannewsdefend/AssetPlanNewsDefend002/getPlans'),
      type: 'POST',
      data: function() {
        return {
          vdate: $('#vdate').val(),
          edate:$('#edate').val(),
          benchDate:$('#benchDate').val(),
          assetTargetScale:$('#assetTargetScale').val(),
          pageType:pageModel,
          assetPlanCode:$('#assetPlanCode').val(),
          isInit:isInit,
          workflowId:workflowId
        };
      }
    },
    checkbox: false,
    searchOnInit: true,
    resize:true,
    cols:[
      {field:'assetTypeStr', title: '资产大类', sortable:false,width:200}
      ,{field:'planInitAmt', title: '规划初始值（万元）',type:'number', sortable:false,digit:6,width:200,edit:true}
      ,{field:'planTargetAmt', title: '规划目标值（万元）',type:'number', sortable:false,digit:6,width:200}
      ,{field:'planChangeAmt', title: '规划变动值（万元）', type:'number', sortable:false, digit:6,width:200}
    ]
  };

  /**
   * 绑定表格事件
   */
  var table = $("#dataTable").joyintable(options);

  $('#vdate').change(function () {
    var vdate = $('#vdate').val();
    if(vdate <= sysDate){
      $('#vdate').val("");
      Joyin.error("规划开始日需大于平台日！");
      return;
    }
    //规划结束日值为规划开始日所在年的年末（自然日）
    var edate = new Date(vdate).getFullYear() + "-12-31";
    $('#edate').val(edate);
  });
  $('#edate').change(function () {
    var vdate = $('#vdate').val();
    var edate = $('#edate').val();
    if(edate < vdate){
      $('#edate').val("");
      Joyin.error("规划结束日不能小于规划开始日！");
      return;
    }
  });

  function tableLoadCheck() {
    var vdate = $('#vdate').val();
    var edate = $('#edate').val();
    var benchDate = $('#benchDate').val();
    var assetTargetScale = $('#assetTargetScale').val();
    if(vdate != null && vdate.length > 0
      && edate != null && edate.length > 0
      && benchDate != null && benchDate.length > 0
      && assetTargetScale != null && assetTargetScale.length > 0){
      table.reload();
    }
  }

  $('#vdate').change(function () {
    isInit = "NO";
    table.clear();
    tableLoadCheck();
  });
  $('#edate').change(function () {
    isInit = "NO";
    table.clear();
    tableLoadCheck();
  });
  $('#benchDate').change(function () {
    isInit = "NO";
    table.clear();
    tableLoadCheck();
  });
  $('#assetTargetScale').change(function () {
    isInit = "NO";
    table.clear();
    tableLoadCheck();
  });

  function saveAction(){
    saveOrSubmitAction('save');
  }

  function submitAction(){
    saveOrSubmitAction('submit');
  }

  function saveOrSubmitAction(operateType){
    var object = $.serializeToObject('#AssetPlanNewsDefend002_Form', false);
    var assetPlanNewsDefendList = table.options.allData;
    object.operateType = pageModel;
    object.assetPlanNewsDefendList = assetPlanNewsDefendList;
    Joyin.ajaxJson(basePath + "invest/product/productplanning/assetplannewsdefend/AssetPlanNewsDefend002/check",object,function (result) {
      Joyin.confirm(result.data,function () {
        if(operateType === 'save'){
          WorkFlow.doSaveList(basePath + "invest/product/productplanning/assetplannewsdefend/AssetPlanNewsDefend002/save",object,"AssetPlanNewsDefend002_btn_save");
        }else {
          WorkFlow.doSubmitList(basePath + "invest/product/productplanning/assetplannewsdefend/AssetPlanNewsDefend002/save",object,"AssetPlanNewsDefend002_btn_submit");
        }
      })
    });
  }

  var init = function(){
    if(pageModel==PAGE_MODEL_EDIT){
      $('#vdate').attr('disabled',true);
      $('#edate').attr("disabled",true);
      $('#benchDate').attr('disabled',true);
    }
  };

  $(function () {
    init();
    //保存
    $('#AssetPlanNewsDefend002_btn_save').click(saveAction);
    //提交
    $('#AssetPlanNewsDefend002_btn_submit').click(submitAction);
  });
}());