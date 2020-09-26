$(function () {
  var table=$("#dataTable").joyintable({
    data:[
      {"xfzt":"<span class='unaffirm'>未下发</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='unaffirm'>未下发</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='unaffirm'>未下发</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='unaffirm'>未下发</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='unaffirm'>未下发</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='unaffirm'>未下发</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='unaffirm'>未下发</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='unaffirm'>未下发</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='wrong'>下发失败</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='wrong'>下发失败</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='wrong'>下发失败</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
      {"xfzt":"<span class='affirm'>下发成功</span>","gzrq":"2017/12/25","jzlsh":"JZ0000000000001","zhangtao":"账套名称","cpdm":"CMHB001","cpjc":"货币型产品开放式001","zcdm":"ZC0000001","zcmc":"资产名称A","kmh":"1001","kmmc":"科目名称A","jdfx":"借","bizhong":"人民币","jfje":"200,004,729.03","dfje":"&nbsp;","shuliang":"1","gzfx":"市值法","gzje":"200,004,729.03","ywbm":"YW0000001","ywlb":"基金-优先股",},
    ],
    searchOnInit: true,
    cellMinWidth:100 ,
    cols:[
          {field:'xfzt', title:'下发状态', width:90, sort:true, fixed:'left'},
          {field:'gzrq', title:'估值日期', width:120, sort:true},
          {field:'jzlsh', title:'记账流水号', width:150, sort:true},
          {field:'zhangtao', title:'账套', width:120, sort:true},
          {field:'cpdm', title:'产品代码', width:150, sort:true},
          {field:'cpjc', title:'产品简称', width:240, sort:true},
          {field:'zcdm', title:'资产代码', width:150, sort:true},
          {field:'zcmc', title:'资产名称', width:240, sort:true},
          {field:'kmh', title:'科目号', width:150, sort:true},
          {field:'kmmc', title:'科目名称', width:240, sort:true},
          {field:'jdfx', title:'借贷方向', width:240, sort:true},
          {field:'bizhong', title:'币种', width:150, sort:true},
          {field:'jfje', title:'借方金额', width:150, sort:true},
          {field:'dfje', title:'贷方金额', width:150, sort:true},
          {field:'shuliang', title:'数量', width:150, sort:true},
          {field:'gzfx', title:'估值方向', width:150, sort:true},
          {field:'gzje', title:'估值金额', width:150, sort:true},
          {field:'ywbm', title:'业务编码', width:150, sort:true},
          {field:'ywlb', title:'业务类别', width:150, sort:true},
          //{fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: ['edit','delt']}
        ]
  });
  
  //查看详情
  //$('.ValIssued001_detail').click(function() {
      //Joyin.dialog(JoyinUtil.getLabel("ValIssued002"),
      //basePath + "valuation/valuationmanage/valuaissued/ValIssued002/init",
      //"96%", "90%", {pageModel:PAGE_MODEL_NEW});
  //});

});