$(function () {
  var table=$("#dataTable1").joyintable({
    data:[
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84  ","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84  ","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"未找到文件","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"未找到文件","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='wrong'>已对账</span>","dzjg":"对账不一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='wrong'>已对账</span>","dzjg":"对账不一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      
    ],
    searchOnInit: true,
    cellMinWidth:100 ,
    cols:[
          {field:'dzzt', title:'对账状态', width:90, sort:true},
          {field:'dzjg', title:'对账结果', width:120, sort:true},
          {field:'cpdm', title:'产品代码', minWidth:150, sort:true},
          {field:'cpjc', title:'产品简称', minWidth:240, sort:true},
          {field:'gzrq', title:'估值日期', width:120, align:'center', sort:true},
          {field:'cpfe', title:'产品份额', minWidth:160, align:'right', sort: true},
          {field:'cpzjz', title:'产品总净值',minWidth:200, align:'right', sort: true},
          {field:'shouyilv', title:'单位净值/万份收益/利率', minWidth:100, align:'right', sort:true},
          {field:'beizhu', title:'备注', minWidth:140,},
          {fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: ['adjust','cancel']}
        ]
  });
});
$(function () {
  var table=$("#dataTable2").joyintable({
    data:[
      {"cszt":"<span class='unaffirm'>未发送</span>","dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='unaffirm'>未发送</span>","dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84  ","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='unaffirm'>未发送</span>","dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='unaffirm'>未发送</span>","dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84  ","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='unaffirm'>未发送</span>","dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='unaffirm'>未发送</span>","dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='wrong'>发送失败</span>","dzzt":"<span class='wrong'>对账失败</span>","dzjg":"对账不一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='wrong'>发送失败</span>","dzzt":"<span class='wrong'>对账失败</span>","dzjg":"对账不一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","gzrq":"2017/07/06","cpfe":"0.00","cpzjz":"-122,739.84","shouyilv":"1.000000","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"},
      {"cszt":"<span class='affirm'>已发送</span>","dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","gzrq":"2017/12/25","cpfe":"0.00","cpzjz":"0.00","shouyilv":"&nbsp;","beizhu":"<a class='ValReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>"}, 
    ],
    searchOnInit: true,
    cellMinWidth:100 ,
    cols:[
        {field:'cszt', title:'发送状态', width:90, sort:true},
        {field:'dzzt', title:'对账状态', width:90, sort:true},
        {field:'dzjg', title:'对账结果', minWidth:120, sort:true},
        {field:'cpdm', title:'产品代码', minWidth:150, sort:true},
        {field:'cpjc', title:'产品简称', minWidth:240, sort:true},
        {field:'gzrq', title:'估值日期', width:120, align:'center', sort:true},
        {field:'cpfe', title:'产品份额', minWidth:160, align:'right', sort: true},
        {field:'cpzjz', title:'产品总净值', minWidth:200, align:'right', sort: true},
        {field:'shouyilv', title:'单位净值/万份收益率', minWidth:200, align:'right', sort:true},
        {field:'beizhu', title:'备注', width:140,},
        {fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: ['btnok','cancel']}
    ]
  });
});

$(function () {
  //查看详情
  $('.ValReconciliation001_gzdzmx').click(function() {
    Joyin.dialog(JoyinUtil.getLabel("ValReconciliation002"),
    basePath + "valuation/valuationmanage/valuareconcy/ValReconciliation002/init",
    "96%", "90%", {pageModel:PAGE_MODEL_NEW});
  });
  //按钮
  $.fn.joyintable.defaults = {
    // 按钮设置
    buttons: {
      adjust: {
        text: '<i class="iconfont icon-edit" title="调整"></i>',
        clas: 'adjust'
      },
      cancel: {
        text: '<i class="iconfont icon-close-current" title="取消"></i>',
        clas: 'cancel'
      },
      btnok: {
          text: '<i class="iconfont icon-submit" title="确认对账"></i>',
          clas: 'btnok'
      },
    }
  };
});