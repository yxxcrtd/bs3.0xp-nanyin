/**
 * 表格信息
 */
(function(){

    /**
     * 指令管理
     */
    function getSearchParams(){
        var data ={};
        data.entrustStatus = $('#searchForm_zlgl').find('input:radio:checked').val();
        return data;
    }

    /**
     * 定义数据表格
     */
    var dateTable = $("#dateTable").joyintable({
        ajax: {
            url: 'selectEtrStockConnect',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        page: false, // 去除分页
        dblclick:function(table, data) {
            if(data.entrustStatus === "05" || data.entrustStatus === "07"){
                parent.EtrStockConnect002.dbclickRefresh001(data);
            }
        },
        searchOnInit:true,
        cols:[
            {field:'entrustId', title: 'EtrStockConnect001.entrustId', sortField:'entrustId',width : 200}
            ,{field:'entrustStatus',visible:false}
            ,{field:'entrustStatusStr', title: 'EtrStockConnect001.entrustStatus', sortField:'entrustStatus',width : 150}
            ,{field:'implementPercentAge', title: 'EtrStockConnect001.implementPercentAge',render: function (data, type, row, meta) {
                    return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"
                        + (row.implementPercentAge)
                        + "%'><span class='layui-progress-text' style='color:black;'>"
                        + (row.implementPercentAge)
                        + "%</span></div></div>";
                }, sortField:'implementPercent',width : 150}
            ,{field:'dealPercentAge', title: 'EtrStockConnect001.dealPercentAge',render: function (data, type, row, meta) {
                    if(row.entrustType == "09" || row.entrustType == "10" || row.entrustType == "11" || row.entrustType == "12" || row.entrustType == "13" || row.entrustType == "16"){
                        return '<span class="s-color-gray">N/A</span>';
                    }
                    return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"
                        + (row.dealPercentAge)
                        + "%'><span class='layui-progress-text' style='color:black;'>"
                        + (row.dealPercentAge)
                        + "%</span></div></div>";
                },sortField:'dealPercent', width : 150}
            ,{field:'parentPortfolioId', visible: false}
            ,{field:'portfolioId', title: 'EtrStockConnect001.portfolioId', sortField:'portfolioId',width : 150}
            ,{field:'portfolioName', title: 'EtrStockConnect001.portfolioName', sortField:'portfolioId',width : 150}
            ,{field:'finprodId',visible: false}
            ,{field:'finprodName', title: 'EtrStockConnect001.finprodName',sortField:'finprodId', width : 150}
            ,{field:'finprodMarketId',visible:false}
            ,{field:'tradeMarketStr', title: 'EtrStockConnect001.tradeMarket',sortField:'tradeMarket', width : 150}
            ,{field:'hkseMarket', visible:false}
            ,{field:'hkseMarketStr', title: 'EtrStockConnect001.hkseMarket',sortField:'hkseMarket', width : 150}
            ,{field:'ps', visible: false}
            ,{field:'psStr', title: 'EtrStockConnect001.ps',sortField:'ps', width : 150}
            ,{field:'priceModel',visible:false}
            ,{field:'priceModelStr', title: 'EtrStockConnect001.priceModel',sortField:'priceModel', width : 150}
            ,{field:'entrustNum', title: 'EtrStockConnect001.entrustNum',type:'number',digit:4, width : 150,sortField:'entrustNum'}
            ,{field:'price', title: 'EtrStockConnect001.price',type:'number',digit:4, width : 150,sortField:'price'}
            ,{field:'entrustAmtHk', title: 'EtrStockConnect001.entrustAmtHk',type:'number',digit:4, width : 150,sortField:'entrustAmt'}
            ,{field:'entrustAmt', title: 'EtrStockConnect001.entrustAmt',type:'number',digit:4, width : 150,sortField:'entrustAmtRmb'}
            ,{field:'vdate', title: 'EtrStockConnect001.vdate',type:'date', width : 150,sortField:'vdate'}
            ,{field:'mdate', title: 'EtrStockConnect001.mdate',type:'date', width : 150,sortField:'mdate'}
            ,{field:'targetType',visible:false}
            ,{field:'secAcctId',visible:false}
        ]
    });

    

    /**
     * 持仓信息（上）
     */
    function getPositionsSearchParams(){
        var data = $.serializeToObject('#searchForm_cc');
        data.portfolioId = data.portfolioId ? data.portfolioId.join(','):'';
        return data;
    }
    
    /**
     * 定义数据表格
     */
    var positionsTable = $("#positionsTable").joyintable({ 
        ajax: {
            url: 'findPositionsPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getPositionsSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            parent.EtrStockConnect002.dbclickRefresh002(data);
        },
        /**
         * 定义表格名称
         */
        checkbox:false,
        searchOnInit:true,
        cols:[
            {field:'parentPortfolioId', width : 150,visible:false}//组合名称
            ,{field:'portfolioId', title: 'EtrStockConnect001.portfolioId', sortField:'portfolioId',width : 150}//组合名称
            ,{field:'portfolioName', title: 'EtrStockConnect001.portfolioName', sortField:'portfolioName',width : 150}//组合名称
            ,{field:'tradeMarketStr', title: 'EtrStockConnect001.tradeMarket',sortField:'tradeMarket', width : 150}//市场
            ,{field:'finprodId', title: 'EtrStockConnect001.finprodId', width : 150}//资产代码
            ,{field:'finprodName', title: 'EtrStockConnect001.finprodName', width : 150}//资产名称
            ,{field:'finprodMarketId', title: 'EtrStockConnect001.finprodId', width : 150,visible:false}//市场代码
            ,{field:'parentPortfolioId', title: 'EtrStockConnect001.portfolioId', width: 150, sortField:"parentPortfolioId",visible:false}
            ,{field:'hkseMarketStr', title: 'EtrStockConnect001.hkseMarket', width : 150}//港股通市场
            ,{field:'positionsNum', title: 'EtrStockConnect001.positionsNum',type:'number', digit : 0, width : 100}//持仓数量
            ,{field:'sellNum', title: 'EtrStockConnect001.sellNum',type:'number', digit : 0, width : 100}//可卖数量
            ,{field:'positionsAmt', title: 'EtrStockConnect001.positionsAmt',type:'number', width : 100}//持仓成本
            ,{field:'price', title: 'EtrStockConnect001.closePrice',type:'number', width : 100}//最新价
            ,{field:'floatingPL', title: 'EtrStockConnect001.floatingPL',type:'number', width : 100}//浮动盈亏（%）
        ]
    });


    
    var init = function() {

    }

    /**
     * 对外公开的接口
     */
    var myrefresh = function(){
        dateTable.table ? dateTable.reload() : dateTable.load();
    }
    parent.EtrStockConnect001 = {
        myrefresh:myrefresh
    }

    /**
	 * 画面加载完成
	 */
    $(function() {
        init();
        $('#EtrStockConnect001_btn_import').click(function() {
            window.parent.importEtr();
        });
        // 绑定查询按钮
        $('#EtrStockConnect001_search_btn').click(function() {
            positionsTable.table ? positionsTable.reload() : positionsTable.load();
        });

        /**
         * radio事件
         */
        layui.form.on('radio(entrustStatus)',function(data){
            dateTable.table ? dateTable.reload() : dateTable.load();
        })

        /**
         * 关闭委托
         */
        $('#EtrStockConnect001_btn_close').click(function() {
            if (dateTable != null && dateTable != '') {
                var selArr = dateTable.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else {
                for(var index in selArr){
                    if((selArr[index].entrustStatus != '03') &&
                       (selArr[index].entrustStatus != '05') &&
                       (selArr[index].entrustStatus != '07') &&
                       (selArr[index].entrustStatus != '09') ){
                        Joyin.error("只能对指令状态为待分发、已接收、已退回和已撤回的指令进行主动关闭");
                        return ;
                    }
                }
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect001/close', object, function(result) {
                            dateTable.reload();
                        });
                    });
                });
            }
        });

        /**
         * 撤销
         */
        $('#EtrStockConnect001_btn_revoke').click(function() {
            if (dateTable != null && dateTable != '') {
                var selArr = dateTable.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else {
                for(var index in selArr){
                    if(selArr[index].entrustStatus == '08'){
                        Joyin.error("流程中的数据不允许撤回");
                        return ;
                    }
                }
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect001/recall', object, function(result) {
                            dateTable.reload();
                        });
                    });
                });
            }
        });

        // 多对多导入
//        $('#EtrStockConnect001_btn_import').click(function() {
//            Joyin.uploadFile({pageId:'EtrStockConnect003MTM'},function(fileName) {
//                Joyin.ajax(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect003/checkImportData', {fileName: fileName}, function(checkResult) {
//                    // 若是模板数据大于100条，提示重新导入，若是小于100条，进入多对多指令界面
//                    if(!checkResult.data.errorMsg){
//                        setTimeout( function () {
//                            Joyin.confirm(checkResult.data.confirmMsg,function() {
//                                var result = {};
//                                result.operateType = "import";
//                                result.pageModel='';
//                                result.tempList = checkResult.data.correctData;
//                                Joyin.dialog(JoyinUtil.getLabel("港股通指令多对多"),
//                                    JoyinUtil.getRealPath("invest/entrust/etrstockconnect/EtrStockConnect003/init"),
//                                    "98%", "95%", result,
//                                    function(){table.reload();});
//                            });
//                        },1000);
//                    }else{
//                        Joyin.alert(checkResult.data.errorMsg);
//                    }
//                });
//            },function() {
//                dateTable.reload();
//            });
//        });


    });
}());