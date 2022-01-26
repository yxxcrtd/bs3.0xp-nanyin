(function () {
    function query(ele){
        return $("#CfetsTrdRepoTrade002_bodydiv " + ele);
    }

    var riskResultdata;
    var initFlag = false;
    var cluflag;
    var businessId;
    var fprice;

    var businessModelReulst="";
    var entityBussCombDistritList;



    CfetsTrdRepoTrade002 = {

    }
    function getSearchParams() {
 debugger;
        var object = $.serializeToObject("#searchForm");
        return object;
    }
    function getBussCombDistribtTableParams() {
     debugger;
       var entityBussCombDistritList=entity_BussCombDistribtList||[];

       return entityBussCombDistritList;
    }
    var colsCombDistribt = [
        {
            field: 'entrustId', width: 100, title: '指令代码',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'portfolioId', width: 100, title: '组合代码',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'tradeAmt', width: 100, title: '交易金额（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'jyFeeAmt', width: 100, title: '交易手续费（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'jsFeeAmt', width: 100, title: '结算服务费（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'mintAmt', width: 100, title: '回购利息（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'mtradeAmt', width: 100, title: '到期结算金额（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'disFaceValue', width: 100, title: '质押券折算总额（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'priceModelStr', width: 100, title: '价格模式',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'rate', width: 100, title: '回购利率（%）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }

    ]
    var table = query("#bussCombDistribtTable").joyintable({
        data:getBussCombDistribtTableParams(),
        cols: colsCombDistribt,
        page: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 290
    });
    function getBussCombPledgeTableParams() {
       var entityBussCombPledgeList=entity_CfetsPledgeList||[];
       return entityBussCombPledgeList;
    }
    var colsCombPledge = [
        {
            field: 'finprodMarketId', width: 100, title: '债券代码',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'finprodAbbr', width: 100, title: '债券简称',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'portfolioId', width: 100, title: '组合代码',  sortable: false
             ,disabled:true
         }
         ,{
             field: 'portfolioName', width: 100, title: '组合名称',  sortable: false
             ,disabled:true
         }
        ,{
            field: 'pledgePrin', width: 100, title: '质押券面总额（万元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'firstNetPrice', width: 100, title: '首次净价（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'pledgeRate', width: 100, title: '折算比例（%）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'repRegistOrgStr', width: 100, title: '登记托管机构',  sortable: false
            ,disabled:true
        }
    ]
    var table = query("#bussCombPledgeTable").joyintable({
        data:getBussCombPledgeTableParams(),
        cols: colsCombPledge,
        page: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 290
    });
    var init = function () {
       debugger;
    }
    init();

}());
