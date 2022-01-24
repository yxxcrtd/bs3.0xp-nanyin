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
            field: 'unFinshAmt', width: 100, title: '剩余指令额度（万元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'qtyd', width: 100, title: '券面总额（万元）',  sortable: false
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
            field: 'cpriceAmt', width: 100, title: '净价总额（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'intAmt', width: 100, title: '应计利息总额（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'fpriceAmt', width: 100, title: '全价总额（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'priceModelStr', width: 100, title: '价格模式',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'busiTypePositionStr', width: 100, title: '业务模式',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'secAcctId', width: 100, title: '证券账户',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'unitPrice', width: 100, title: '净价（元）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'matureYield', width: 100, title: '到期收益率（%）',  sortable: false
            ,disabled:true
            ,type: 'number'
            ,digit: 2
        }
        ,{
            field: 'exeYield', width: 100, title: '行权收益率（%）',  sortable: false
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

    var init = function () {
       debugger;
    }
    init();

}());
