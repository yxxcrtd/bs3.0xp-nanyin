$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tradeId: $('#tradeId').val(),
            busiType: $('#busiType').val(),     
            ps: $('#ps').val(),     
            finprodId: $('#finprodId').val(),  
            prodId: $.trim($('#prodId').val()),
            tradeDateBegin: $("#tradeDate").startDate(),
            tradeDateEnd: $("#tradeDate").endDate(),
            settleDateBegin: $("#settleDate").startDate(),
            settleDateEnd: $("#settleDate").endDate()
        }
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
		checkbox: true,
		cellMinWidth : 100,
		cols: [
		   {field:'busiType', title: '业务类型',width:150, sort: true,sortField:'busiTypeCode'},
		   {field:'busiTypeCode', title: '业务类型Code',width:150, sort: true,visible: false},
		   {field:'prodId', title: '理财产品代码',width:120, sort: true},
	       {field:'finprodTypeFt', title: '金融产品类型',width:150,sortField:'finprodType'},
	       {field:'finprodId', title: '金融产品代码',width:150, sort: true},
	       {field:'psFt', title: '交易方向',width:100, sort: true,sortField:'ps'},
	       {field:'tradeDate', title: '交易日',width:120, sort: true},
	       {field:'settleDate', title: '交割日',width:120, sort: true},
	       {field:'vdate', title: '起息日',width:120, sort: true},
           {field:'mdate', title: '到期日',width:120, sort: true},
           {field:'cpriceDate', title: '净值日期',width:120, sort: true},
           {field:'unitCprice', type:"money", title: '单位净价',width:150, sort: true},
           {field:'unitInt', type:"money", title: '单位利息',width:150, sort: true},
           {field:'unitFprice', type:"money", title: '单位全价',width:150, sort: true},
           {field:'parValue', type:"money", title: '单位面值',width:150, sort: true},
           {field:'cpriceAmt', type:"money", title: '净价总额',width:150, sort: true},
           {field:'shareAmt', type:"number", title: '交易份额',width:150, sort: true},
           {field:'prinAmt', type:"money", title: '交易本金',width:150, sort: true},
           {field:'intAmt', type:"money", title: '利息总额',width:150, sort: true},
           {field:'fpriceAmt', type:"money", title: '全价总额',width:150, sort: true},
           {field:'feeAmtPay', type:"money", title: '随交易支付费用',width:150, sort: true},
           {field:'tradeAmt', type:"money", title: '交易金额',width:150, sort: true},
           {field:'feeAmtUnpay', type:"money", title: '未随交易支付费用',width:150, sort: true},
           {field:'feeAmt', type:"money", title: '总交易费用',width:150, sort: true},
		   {field:'ccyFt', title: '交易币种',width:150,sortField:'ccy'},
		   {field:'yield',  align:"right", title: '到期收益率(%)',width:150, sort: true},
           {field:'exerYield',  align:"right", title: '行权收益率(%)',width:150, sort: true},
           {field:'invAimFt', title: '投资目的',width:300,sortField:'invAim'},
           {field:'tradeStatusFt', title: '交易状态',width:120, sort: true,sortField:'tradeStatus'},
           {field:'pTradeId', title: '原交易编号',width:170, sort: true},
           {field:'isCleanFt', title: '是否结清',width:100, sort: true, sortField:'isClean'},
           {field:'totleTradeId', title: '汇总交易编号',width:170, sort: true},
           {field:'rTradeId', title: '反向交易编号',width:170, sort: true},
           {field:'chlId', title: '通道代码',width:150, sort: true},
           {field:'counterId', title: '交易对手',width:150, sort: true},
//           {field:'counterTypeFt', title: '交易对手类型',width:150, sort: true,sortField:'counterType'},
           {field:'isOutTrade', title: '是否外部交易',width:150, sort: true, sortField:'isOutTrade'},
           {field:'tradeMarketFmt', sortField:'tradeMarket', title: '交易场所',width:150, sort: true},
           {field:'trader', title: '交易员',width:140, sort: true},
           {field:'secManageAcctId', title: '证券管理户',width:150, sort: true},
           {field:'tradeId', title: '交易编号',width:200, sort: true},
           {field:'fTradeId', title: '关联系统交易编号',width:150, sort: true},
           {field:'booksetId', title: '账套代码',width:150},
           {field:'booksetDate', title: '录入日',width:120, sort: true},
           {field:'bookDate', title: '入账日',width:120, sort: true},
//          {field:'branch', title: '分支序号',width:100,visible: false},
           {field:'busiTable', title: '业务表',width:100,visible: false},
           {field:'bokDetailId', title: '账务明细代码',width:100,visible: false},
           {field:'operation', title: 'SysCommon.operation', type:'action',width:80,buttons:['delt']}
		],
	});
//    /**
//     * 绑定编辑事件
//     */
//    table.bind('click', '.delt', function(data, index) {
//        Joyin.confirm("确定要删除?",
//                function() {},
//                function() {}
//                );
//    });
    
//    /**
//     * 绑定事件：删除
//     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {};
//        object.booksetId = data.booksetId;//账套代码
//        object.tradeId = data.tradeId;//交易编号
//        object.busiTable = data.busiTable;//业务表
//        object.bokDetailId = data.bokDetailId;//账务明细代码
//        object.busiTypeCode = data.busiTypeCode;//业务类型
//        object.bookDate = data.bookDate;//入账日
//        object.prodId = data.prodId; // 金融产品代码（业务类型为指令时，存指令代码）
//        Joyin.ajaxJson(basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001/deleteCheck', object, function(result) {
//                Joyin.ajaxJson(basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001/delete', object, function(result) {
//                    table.reload();
//                });
//           });
//     });
    
    
    /**
     * 删除走流程
     */
    table.bind('click', '.delt', function(data, index) {
        
        var object = {};
        object.listValFinprodTradeQuery001Dto =
            [{booksetId:data.booksetId,tradeId:data.tradeId,busiTable:data.busiTable,prodId:data.prodId
                ,bokDetailId:data.bokDetailId,busiTypeCode:data.busiTypeCode,bookDate:data.bookDate }];
            
//        var object = {};
//        object.booksetId = data.booksetId;//账套代码
//        object.tradeId = data.tradeId;//交易编号
//        object.busiTable = data.busiTable;//业务表
//        object.bokDetailId = data.bokDetailId;//账务明细代码
//        object.busiTypeCode = data.busiTypeCode;//业务类型
//        object.bookDate = data.bookDate;//入账日
//        object.prodId = data.prodId; // 金融产品代码（业务类型为指令时，存指令代码）
        //data.operateType = "delete";
//        Joyin.ajaxJson(basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001/deleteCheck', object, function(result) {
//                WorkFlow.doDeleteList(
//                        basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001/delete', 
//                        data, 
//                        "ValFinprodTradeQuery001_btn_delete", 
//                        "ValFinprodTradeQuery002",
//                        function() {
//                            table.reload();
//                        }
//                    );
//        });
        object.operateType = "delete";
//      var message = "是否确认删除？"+selArr.length+"条记录";
      var message = "是否确认删除？";
      Joyin.ajaxJson(basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001/deleteCheck', object, function(result) {
          Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
//    
              WorkFlow.doDeleteList(
                      basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001/delete', 
                      object, 
                      "ValFinprodTradeQuery001_btn_delete", 
                      "ValFinprodTradeQuery002",
                      function() {
                          table.reload();
                      }        
              );
              
              
          });
      });
     });
    
    var deleteData = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
//            console.log(selArr);
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("没有选中数据"));
            return;
        }

        var object = {};
        object.listValFinprodTradeQuery001Dto = selArr;
        object.data = datas;
        object.operateType = "delete";
//        var message = "是否确认删除？"+selArr.length+"条记录";
        var message = "是否确认删除？";
        Joyin.ajaxJson(basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001/deleteCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
//      
                WorkFlow.doDeleteList(
                        basePath + 'valuation/trademanage/finprodtradequery/ValFinprodTradeQuery001/delete', 
                        object, 
                        "ValFinprodTradeQuery001_btn_delete", 
                        "ValFinprodTradeQuery002",
                        function() {
                            table.reload();
                        }        
                );
                
                
            });
        });
    };
    
    
    
//	var exportExcel = function() {
//        var paramObj = getSearchParams();
//        Joyin.exportExcel('export', paramObj);
//    };
	
    var exportExcel = function() {
	    var rows=table.getAllCheckedRows();
	    var flag=true;
        if(rows.length<=0){
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        }else {
            var datas = JSON.stringify(rows);
            Joyin.exportExcel('export', {datas:datas});
        }
    };
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValFinprodTradeQuery001_btn_search').click(function() {
            table.load();
        });
        $('#ValFinprodTradeQuery001_btn_export').click(function() {
            exportExcel();
        });
        
        $('#ValFinprodTradeQuery001_btn_delete').click(function() {
            deleteData();
        });
        
        
        
    });
	
});