$(function () {
  var table=$("#dataTable").joyintable({
    data:[
      {"tjrq":"2018/05/28","cpdm":"HBd1023","kmh":"1002","kmmc":"银行存款","bizhong":"人民币(CNY)","y-jdfx1":"借方","y-qcye":"200,000.01","y-jffsye":"44,100,000.00","y-dffsye":"41,400,000.00","y-jdfx2":"借方","y-qmye":"0.00","huilv":"1.00000000","b-jdfx1":"借方","b-qcye":"200,000.01","b-jffsye":"44,100,000.00","b-dffsye":"41,400,000.00","b-jdfx2":"借方","b-qmye":"0.00",},
      {"tjrq":"2018/05/28","cpdm":"HBd1023","kmh":"1002","kmmc":"银行存款","bizhong":"综合本位币(人民币)","y-jdfx1":"&nbsp;","y-qcye":"&nbsp;","y-jffsye":"&nbsp;","y-dffsye":"&nbsp;","y-jdfx2":"&nbsp;","y-qmye":"&nbsp;","huilv":"&nbsp;","b-jdfx1":"借方","b-qcye":"200,000.01","b-jffsye":"44,100,000.00","b-dffsye":"41,400,000.00","b-jdfx2":"借方","b-qmye":"0.00",} 
    ],
    searchOnInit: true,
    cellMinWidth:100 ,
    cols:[
      [
        {field:'tjrq', title:'统计日期', width:100, sort:true, rowspan:2}, //rowspan 即纵向跨越的单元格数
        {field:'cpdm', title:'产品代码', width:150, sort:true, rowspan:2},
        {field:'kmh', title:'科目号', width:80, sort:true, rowspan:2},
        {field:'kmmc', title:'科目名称', width:180, sort:true, rowspan:2},
        {field:'bizhong', title:'币种', width:150, sort:true, rowspan:2}, 
        {align:'center', title:'原币种金额', colspan:6,}, //colspan 即横跨的单元格数，这种情况下不用设置field和width
        {field:'huilv', title:'汇率', width:120, sort:true, rowspan:2},
        {align:'center', title:'本位币金额', colspan:6,}
      ],
      [
         {field:'y-jdfx1', title:'借或贷', width:70, sort:true},
         {field:'y-qcye', title:'期初余额', width:160, sort:true},
         {field:'y-jffsye', title:'本期发生金额(借方)', width:160, sort:true},
         {field:'y-dffsye', title:'本期发生金额(贷方)', width:160, sort:true},
         {field:'y-jdfx2', title:'借或贷', width:70, sort:true},
         {field:'y-qmye', title:'期末余额', width:160, sort:true},
         {field:'b-jdfx1', title:'借或贷', width:70, sort:true},
         {field:'b-qcye', title:'期初余额', width:160, sort:true},
         {field:'b-jffsye', title:'本期发生金额(借方)', width:160, sort:true},
         {field:'b-dffsye', title:'本期发生金额(贷方)', width:160, sort:true},
         {field:'b-jdfx2', title:'借或贷', width:70, sort:true},
         {field:'b-qmye', title:'期末余额', width:160, sort:true},
       ]
    ]
  });
  
  //新建
  $('#ValBalanceInquiry001_btn_add').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("ValBalanceBuild002"),
      basePath + "valuation/accounadjustmanage/balanceinquiry/ValBalanceInquiry002/init",
      "1000px", "360px", {pageModel:PAGE_MODEL_NEW});
  });

});