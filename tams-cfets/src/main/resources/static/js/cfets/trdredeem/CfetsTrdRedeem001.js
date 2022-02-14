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
       var entityBussCombDistritList=ptlAllocatios||[];

       return entityBussCombDistritList;
    }
    var colsCombDistribt = [
        {
            field: 'portfolioId', width: 290, title: '组合代码',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'portfolioName', width: 290, title: '组合名称',  sortable: false
            ,disabled:true
        }
        ,{
            field: 'secManageAcctId', width: 280, title: '证券账户',  sortable: false
            ,disabled:true
        }
       ,{
           field: 'shareAmt', width: 280, title: '账户持仓（万元）',  sortable: false
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
     function saveOrSubmit(operatorType) {
      debugger;
        var object = $.serializeToObject('#searchForm');
        object.operatorType = operatorType;
        object.pageId = "CfetsTrdRedeem001";
        if (operatorType === "save") {
            WorkFlow.doSaveList(basePath+'cfets/trdredeem/CfetsTrdRedeem001/save',object,"CfetsTrdRedeem001_btn_save","CfetsTrdRedeem001");
        } else {
           WorkFlow.doSubmitList(basePath+'cfets/trdredeem/CfetsTrdRedeem001/save',object,"CfetsTrdRedeem001_btn_submit","CfetsTrdRedeem001");
        }
        }
    // 绑定提交按钮
    $('#CfetsTrdRedeem001_btn_submit').click(function () {
    debugger;
        saveOrSubmit('submit');
    });

    // 绑定保存按钮
    $('#CfetsTrdRedeem001_btn_save').click(function () {
        saveOrSubmit('save');
    });

}());
