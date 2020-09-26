$(function () {
  var table=$("#dataTable").joyintable({
    data:[
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"&nbsp;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='unaffirm'>未对账</span>","dzjg":"未找到文件","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='wrong'>已对账</span>","dzjg":"对账不一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='wrong'>已对账</span>","dzjg":"对账不一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致;","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"CMHB001","cpjc":"货币型产品开放式001","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/12/25","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      {"dzzt":"<span class='affirm'>已对账</span>","dzjg":"对账一致","cpdm":"A130F0003","cpjc":"惠益13号2期同业","ztmb":"账套模版A","zhangtao":"某某账套A","gzrq":"2017/07/06","beizhu":"<a class='ValBalanceReconciliation001_dzmx' href='javascript:;'>对账明细</a>&nbsp;<a target='_blank' href='javascript:;'>文件下载</a>",},
      
    ],
    searchOnInit: true,
    cellMinWidth:100 ,
    cols:[
          {field:'dzzt', title:'对账状态', width:90, sort:true, fixed:'left'},
          {field:'dzjg', title:'对账结果', width:120, sort:true},
          {field:'cpdm', title:'产品代码', width:100, sort:true},
          {field:'cpjc', title:'产品简称', minWidth:160, sort:true},
          {field:'ztmb', title:'账套模板', width:150, sort:true},
          {field:'zhangtao', title:'账套', width:90, sort:true},
          {field:'gzrq', title:'估值日期', width:120, align:'center', sort:true},
          {field:'beizhu', title:'备注', minWidth:140,},
          {fixed:'right', width:90, title:'操作', align:'center', type:'action', buttons: ['adjust','cancel']}
        ]
  });
  
  //按钮
  $.fn.joyintable.defaults = {
    // 按钮设置
    buttons: {
      adjust: {
        text: '<i class="iconfont icon-edit"></i>',
        clas: 'adjust'
      },
      cancel: {
        text: '<i class="iconfont icon-close-current"></i>',
        clas: 'cancel'
      },
    }
  };
  
  //余额对账明细
  $('.ValBalanceReconciliation001_dzmx').click(function() {
      Joyin.dialog(JoyinUtil.getLabel("ValBalanceReconciliation002"),
      basePath + "valuation/valuationmanage/balancereconcy/ValBalanceReconciliation002/init",
      "96%", "90%", {pageModel:PAGE_MODEL_NEW});
  });

});