$(function() {
    
    var table = $("#table").joyintable({
        page: false,
        loadonce: true,
        checkbox: false,
        data: childrenPortfolioDetails,
        searchOnInit: true,
        rowno: true,
        cols:[
              {field:'portfolioId', width: 200, title: 'PtlActivityPeriod001.portfolioId',sortField:'portfolioId'}//组合代码
             ,{field:'portfolioName', width: 250, title: 'PtlActivityPeriod001.portfolioName',sortField:'portfolioName'}//组合名称
             ,{field:'theoryTradeAmt', width: 200,title: 'PtlActivityPeriod001.theoryTradeAmt',sortField:'theoryTradeAmt',type:'money'}//理论收息金额
             ,{field:'actualTradeAmt', width: 200,title: 'PtlActivityPeriod001.actualTradeAmt',sortField:'actualTradeAmt',edit:true,type:'money'}//实际收息金额
             ,{field:'busiAcctNo',visible:false}
             ,{field:'acctNo',visible:false}
             ,{field:'acctName',visible:false}
             ,{field:'cyy',visible:false}
             ]
    })
    
    var doSave = function(){
        if(table) {
            var tableArr = table.getAllRows();
            top.returnData=tableArr;
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            return;
        }
    }
    
    $(function() {    
       $('#PtlActivityPeriod003_btn_save').click(function() {
           doSave();
       });
    });
}());
