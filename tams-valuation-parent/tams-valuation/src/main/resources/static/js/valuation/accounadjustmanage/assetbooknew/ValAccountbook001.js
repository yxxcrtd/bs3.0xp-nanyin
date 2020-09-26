$(function () {
  var table=$("#dataTable1").joyintable({
  data:[
    {"ztmc":"M2模板","kmdm":"1002","kmmc":"银行存款","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"100201","kmmc":"活期存款","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"10020101","kmmc":"活期存款","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"10020102","kmmc":"券商保证金","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"1003","kmmc":"存拆放款项","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"1002","kmmc":"银行存款","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"1002","kmmc":"银行存款","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"1002","kmmc":"银行存款","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"1002","kmmc":"银行存款","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"},
    {"ztmc":"M2模板","kmdm":"1002","kmmc":"银行存款","kmlb":"资产类","yefx":"借方","hsxm":"核算项目","sfhssl":"是","hsbz":"人民币","beizhu":"&nbsp;"}
  ],
  searchOnInit: true,
  cellMinWidth:100 ,
  cols:[
        {field:'ztmc', title:'账套', width:120, sort:true},
        {field:'kmdm', title:'科目代码', width:100, sort:true},
        {field:'kmmc', title:'科目名称', width:200, sort:true},
        {field:'kmlb', title:'科目类别', width:90, sort:true},
        {field:'yefx', title:'余额方向', width:90, align:'center', sort:true},
        {field:'hsxm', title:'核算项目', minWidth:160, sort: true},
        {field:'sfhssl', title:'是否核算数量',width:120, align:'center', sort: true},
        {field:'hsbz', title:'核算币种', minWidth:150, sort:true},
        {field:'beizhu', title:'备注', minWidth:200, sort:true},
        {fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: ['edit','delt']}
      ]
  });
});

$(function () {
  var table=$("#dataTable2").joyintable({
  data:[
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"银转证","jiedai":"借","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"证转银","jiedai":"贷","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"银转证","jiedai":"借","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"证转银","jiedai":"贷","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"银转证","jiedai":"借","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"证转银","jiedai":"贷","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"银转证","jiedai":"借","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"证转银","jiedai":"贷","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"银转证","jiedai":"借","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"},
      {"ztmc":"M2模板","xtjy":"银证转账","kjsj":"证转银","jiedai":"贷","kmh":"1002","kmmc":"银行存款","zhi":"YH001","sfhssl":"是","beizhu":"&nbsp;"}
  ],
  searchOnInit: true,
  cellMinWidth:100 ,
  cols:[
          {field:'ztmc', title:'账套', width:120, sort:true},
          {field:'xtjy', title:'系统交易', width:150, sort:true},
          {field:'kjsj', title:'会计事件', width:200, sort:true},
          {field:'jiedai', title:'借贷', width:90, sort:true},
          {field:'kmh', title:'科目号', width:100, sort:true},
          {field:'kmmc', title:'科目名称', width:200, sort:true},
          {field:'zhi', title:'值', width:90, align:'right', sort:true},
          {field:'sfhssl', title:'是否核算数量',width:120, align:'center', sort: true},
          {field:'beizhu', title:'备注', minWidth:200, sort:true},
          //{fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: ['edit','delt']}
    ]
  });
});
  
$(function () {
  //新建账套
  $('#ValAccountbook001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建账套"),
      basePath + "valuation/accounadjustmanage/assetbooknew/ValAccountbook002/init",
      "600px", "520px", {pageModel:PAGE_MODEL_NEW});
  });
  //新建账套分组
  $('#ValAccountbook001_btn_subgroup').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建账套组"),
      basePath + "valuation/accounadjustmanage/assetbooknew/ValAccountbook003/init",
      "380px", "420px", {pageModel:PAGE_MODEL_NEW});
  });
  //新建科目
  $('#ValAccountbook001_btn_subject').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建科目"),
      basePath + "valuation/accounadjustmanage/assetbooknew/ValAccountbook005/init",
      "700px", "540px", {pageModel:PAGE_MODEL_NEW});
  });
 //新建会计分录
  $('#ValAccountbook001_btn_record').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建会计分录"),
      basePath + "valuation/accounadjustmanage/assetbooknew/ValAccountbook007/init",
      "600px", "450px", {pageModel:PAGE_MODEL_NEW});
  });
 //新建会计分录配置
  $('#ValAccountbook001_btn_record_configure').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("新建会计分录配置"),
      basePath + "valuation/accounadjustmanage/assetbooknew/ValAccountbook008/init",
      "600px", "360px", {pageModel:PAGE_MODEL_NEW});
  });
  
  //按钮
  $.fn.joyintable.defaults = {
    buttons: {
      
      edit: {
        text: '&#xe642;',
        clas: 'edit'
      },
      delt: {
        text: '&#xe640;',
        clas: 'delt'
      }
    }
  };

});