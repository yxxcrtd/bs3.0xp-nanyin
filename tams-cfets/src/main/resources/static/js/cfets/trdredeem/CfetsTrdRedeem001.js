(function () {
    function query(ele){
        return $("#CfetsTrdRedeem001_bodydiv " + ele);
    }

    var riskResultdata;
    var initFlag = false;
    var cluflag;
    var businessId;
    var fprice;

    var businessModelReulst="";
    var entityBussCombDistritList;


    CfetsTrdRedeem001 = {

    }
    function getSearchParams() {
        var object = $.serializeToObject("#searchForm");
        return object;
    }
    function getBussCombDistribtTableParams() {
       var entityBussCombDistritList=entity_BussCombDistribtList||[];

       return entityBussCombDistritList;
    }
    var colsCombDistribt = [
        {
            field: 'portfolioId', width: 100, title: '组合代码',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'portfolioIdName', width: 100, title: '组合名称',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'secManageAcctId', width: 100, title: '证券账户',  sortable: false
            ,disabled:true
        }
       ,{
           field: 'shareAmt', width: 100, title: '账户持仓（万元）',  sortable: false
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

    }
    init();

}());
