/**
 * 持仓信息（右上）
 */
(function(){

    // 持仓
    function getPositionsSearchParams(){
        var data = $.serializeToObject('#searchForm_cc');
        data.portfolioId = data.portfolioId ? data.portfolioId.join(','):'';
        data.finprodId = data.finprodId ? data.finprodId.join(','):'';
        data.isDvp = true;
        return data;
    }

    // 指令管理tab-获取查询条件
    function getZqzyPositionParams() {
        var data = $.serializeToObject('#searchForm_zqzy');
        data.portfolioId = data.portfolioId ? data.portfolioId.join(','):'';
        if(data.entrustDate){
            data.entrustStartDate=$("#entrustDate").startDate();
            data.entrustEndDate=$("#entrustDate").endDate();
        }
        return data;
    }

    // 放大镜触发事件
    serachKey = function () {
        positionsTable_zqzy.reload();
    }
    
    // 导入按钮点击事件
    $('#EtrIbBondinfo001_btn_import').click(function() {
        window.parent.importEtr();
    });

    /**
     * 持仓定义数据表格
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
            parent.EtrIbBondinfo002.dbclickRefresh002(data);
        },
        /**
         * 定义表格名称
         */
        checkbox:false,
        searchOnInit:true,
        cols:[
            {field:'portfolioId', title: 'EtrInvestBondInfo001.portfolioId', width: 100,sortField: "portfolioId"}
            ,{field:'portfolioName', title: 'EtrInvestBondInfo001.portfolioName', width:180,sortField:"portfolioName"}
            ,{field:'finprodId', visible:false}
            ,{field:'finprodMarketId', title: 'EtrInvestBondInfo001.finprodId', width:100,
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"finprodMarketId"
            }
            ,{field:'finprodAbbr', title: 'EtrInvestBondInfo001.finprodAbbr', width:180,sortField:"finprodAbbr"}
            ,{field:'invAimStr', title: 'EtrIbBondinfo001.invAim', width:180,sortField:"invAim"}//投资目的
            ,{field:'secType1Name', title: 'EtrIbBondinfo001.secType1', sortField:'secType1',width : 100}//资产名称
            ,{field:'shareAmt', title: 'EtrIbBondinfo001.shareAmt',type:'money', width : 150}//持仓券面总额(万元)
            ,{field:'avaPledgePrin', title: 'EtrIbBondinfo001.avaPledgePrin',type:'money', width : 150}//质押券面总额(万元)
            ,{field:'avaShareAmt', title: 'EtrIbBondinfo001.avaShareAmt',type:'money', width : 150}//可用券面总额(万元)
            ,{field:'positionsAmt', title: 'EtrIbBondinfo001.positionsAmt',type:'money', digit:2, width : 150}//持仓成本
            ,{field:'unitCost', title: 'EtrIbBondinfo001.unitCost',type:'money', digit:4, width : 150}//持仓成本价
            ,{field:'price', title: 'EtrIbBondinfo001.price',type:'number', digit:4, width : 100}//最新价
            ,{field:'positionsPrice', title: 'EtrIbBondinfo001.positionsPrice',type:'money', digit:2, width : 150}//持仓市值（元）
            ,{field:'floatingPl', title: 'EtrIbBondinfo001.floatingPl',type:'money', digit:2, width : 150}//浮动盈亏
            ,{field:'channelId', visible:false}
        ]
    });

    /**
     * 指令管理定义数据表格
     */
    var positionsTable_zqzy = $("#positionsTable_zqzy").joyintable({
        ajax: {
            url: 'findZqzyPositionsPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getZqzyPositionParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            if(data.entrustStatus === "05" || data.entrustStatus === "07"){
                parent.EtrIbBondinfo002.dbclickEtr(data);
            }
        },
        /**
         * 定义表格名称
         */
        checkbox: true,
        searchOnInit:true,
        // height: document.documentElement.clientHeight -110,
        cols:[
            {field:'entrustId', title: 'EtrIbBondinfo001.entrustId', sortField:'entrustId',width : 100}//指令编号
            ,{field:'entrustStatus', visible:false}
            ,{field:'counterId', visible:false} // 交易对手
            ,{field:'invAim', visible:false} // 投资目的
            ,{field:'effectiveStatus', title: 'EtrIbBondinfo001.effectiveStatus',render : function(data, type, row, meta) {
                    return row.entrustStatusName==='流程中'?'流程中':"有效";
                }, width : 100}//有效状态
            ,{field:'entrustStatusName', title: 'EtrIbBondinfo001.entrustStatus', sortField:'entrustStatus',width : 100}//指令状态
            ,{field:'invManagerId', title: 'EtrInvestBondInfo001.invManagerId', sortField:"invManagerId", width : 100}
            /*,{field:'parentPortfolioId', title: 'EtrInvestBondInfo001.portfolioId', width: 100, sortField:"parentPortfolioId",visible:false}*/
            ,{field:'portfolioId', title: 'EtrInvestBondInfo001.portfolioId', width: 100,
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrInvestBondInfo001.portfolioName', width:180,sortField:"portfolioName"}
            ,{field:'finprodId', visible:false}
            ,{field:'finprodMarketId', title: 'EtrInvestBondInfo001.finprodId', width:100,
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"finprodMarketId"
            }
            ,{field:'finprodAbbr', title: 'EtrInvestBondInfo001.finprodAbbr', width:180,sortField:"finprodAbbr"}
            ,{field:'secType1Name', title: 'EtrIbBondinfo001.secType1', sortField:'secType1',width : 100}
            ,{field:'ps', visible:false}
            ,{field:'psName', title: 'EtrIbBondinfo001.ps', sortField:'ps',width : 100}
            ,{field:'shareNum', title: 'EtrIbBondinfo001.shareNum',type:'money', width : 150}//券面总额(万元)
            ,{field:'priceModelName', title: 'EtrInvestBondInfo001.priceModel', width:100,sortField:"priceModel"}
            ,{field:'unitPrice', title: 'EtrIbBondinfo001.unitPrice',type:'number', digit:4, width : 100}
            ,{field:'allPrice', title: 'EtrIbBondinfo001.allPrice',type:'number', digit:4, width : 100}
            ,{field:'matureYield', title: 'EtrIbBondinfo001.matureYield',type:'number',digit : 4, width : 100}
            ,{field:'exerYield', title: 'EtrIbBondinfo001.exerYield',type:'number',digit : 4, width : 100}
            ,{field:'settleSpreed', visible:false}
            ,{field:'settleSpreedName', title: 'EtrInvestBondInfo002.settleSpreed', width : 100}
            ,{field:'vdate', title: 'EtrInvestBondInfo001.vdate', width:100,type:'date',sortField:"vdate"}
            ,{field:'mdate', title: 'EtrInvestBondInfo001.mdate', width:100,type:'date',sortField:"mdate"}
            
            ,{field:'transactioncom', title: 'EtrInvestBondInfo001.transactioncom',render : function(data, type, row, meta){
                    return "<div class='layui-progress layui-progress-big' style='margin-top:3px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text'>"+row.transactioncom+"%</span></div></div>";
                }, width:100,sortable:false}
            ,{field:'remark', title: 'EtrInvestBondInfo001.remark', width:180,sortable:false}
            ,{field:'finishAmt',type:'money', title: 'EtrIbBondinfo001.finishAmt', fixed:'right',width:100}
            ,{field:'counterId', visible:false}
        ]
    });


    /**
     * 组合代码链接
     */
    positionsTable.bind('click','.portfolioIdLink', function(row) {
        // 在父页面全屏展示
        parent.EtrIbBondinfoMain.dialogSelf.dialog(JoyinUtil.getLabel("SysCommon.refrence"),basePath
            + "invest/asset/bond/FinBondTargetAsset002/init",
            {pageModel:PAGE_MODEL_REFERENCE, portfolioId:row.portfolioId});
        
        // Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
        //     basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
        //     "100%", "100%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
        //         portfolioId:row.portfolioId});//主键组合代码
    });
    /**
     * 资产代码链接
     */
    positionsTable.bind('click','.finprodIdLink', function(row) {
        parent.EtrIbBondinfoMain.dialogSelf.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            {pageModel:PAGE_MODEL_REFERENCE,
                finprodId:row.finprodId,  //金融产品代码
                //valDate:data.valDate,//估值日
                //finprodMarketId:data.finprodMarketId//市场代码
            });
        
        //在新建界面进行显示单行的详细信息
        // Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
        //     basePath
        //     + "invest/asset/bond/FinBondTargetAsset002/init",
        //     "100%", "100%", {pageModel:PAGE_MODEL_REFERENCE,
        //         finprodId:row.finprodId,  //金融产品代码
        //         //valDate:data.valDate,//估值日
        //         //finprodMarketId:data.finprodMarketId//市场代码
        //     });
    });

    /**
     * 组合代码链接
     */
    positionsTable_zqzy.bind('click','.portfolioIdLink', function(row) {
        // 在父页面全屏展示
        parent.EtrIbBondinfoMain.dialogSelf.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            {pageModel:PAGE_MODEL_REFERENCE, portfolioId:row.portfolioId});
        
        // Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
        //     basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
        //     "100%", "100%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
        //         portfolioId:row.portfolioId});//主键组合代码
    });
    /**
     * 资产代码链接
     */
    positionsTable_zqzy.bind('click','.finprodIdLink', function(row) {
        // 在父页面全屏展示
        parent.EtrIbBondinfoMain.dialogSelf.dialog(JoyinUtil.getLabel("SysCommon.refrence"),basePath
            + "invest/asset/bond/FinBondTargetAsset002/init",{pageModel:PAGE_MODEL_REFERENCE,
            finprodId:row.finprodId,  //金融产品代码
            //valDate:data.valDate,//估值日
            //finprodMarketId:data.finprodMarketId//市场代码
        });
        //在新建界面进行显示单行的详细信息
        // Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
        //     basePath
        //     + "invest/asset/bond/FinBondTargetAsset002/init",
        //     "100%", "100%", {pageModel:PAGE_MODEL_REFERENCE,
        //         finprodId:row.finprodId,  //金融产品代码
        //         //valDate:data.valDate,//估值日
        //         //finprodMarketId:data.finprodMarketId//市场代码
        //     });
    });

    /**
     * 刷新列表
     */
    var refreshPositionsTable = function(){
        positionsTable.table ? positionsTable.reload() : positionsTable.load();
    }
    /**
     * 刷新债券质押列表
     */
    var refreshZqzyPositionsTable = function(){
        positionsTable_zqzy.table ? positionsTable_zqzy.reload() : positionsTable_zqzy.load();
    }

    var getPositionsExportExcel = function () {
        Joyin.exportExcel('export', getPositionsSearchParams());
    };

    var getZqzyPositionExportExcel = function () {
        Joyin.exportExcel('export', getZqzyPositionParams());
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $(".cc-transfer-form #EtrIbBondinfo001_btn_search").click(function () {
            positionsTable.load();
        })
        
        var resetBtn = $('<a class="layui-btn layui-btn-sm btn-reset"></a>');
        resetBtn.attr('id', pageId + '_btn_reset')
            .text(JoyinUtil.getLabel('SysCommon.reset'));
        $(".cc-transfer-form #EtrIbBondinfo001_btn_search").after(resetBtn);
        resetBtn.click(function () {
            $("#searchForm_cc #portfolioId").val('').trigger("change");
            $("#searchForm_cc #secType1").val('').trigger("change");
            $("#searchForm_cc #finprodId").val('').trigger("change");
        })
        
        $(".transfer-form #EtrIbBondinfo001_btn_search").click(function () { 
            // 指令管理tab-->查询
            if($("#EtrIbBondinfo001_selectorConditionFilter").css("display")=="none"){
                $("#EtrIbBondinfo001_selectorConditionFilter").show();
            }
            else{
                $("#EtrIbBondinfo001_selectorConditionFilter").hide();
            }
        })

        // 指令管理tab-->查询dialog 确认
        $('#EtrIbBondinfo001_queryDialog_btn_realseach').click(function() {
            positionsTable_zqzy.table ? positionsTable_zqzy.reload() : positionsTable_zqzy.load();
            $("#EtrIbBondinfo001_selectorConditionFilter").hide();
        });
        // 指令管理tab-->查询dialog 取消
        $('#EtrIbBondinfo001_queryDialog_btn_seachClose').click(function() {
            $("#EtrIbBondinfo001_selectorConditionFilter").hide();
        });
        // 指令管理tab-->查询dialog 重置
        $('#EtrIbBondinfo001_queryDialog_btn_seachReset').click(function() {
            $("#entrustOrder_portfolioId").val('').trigger("change");
            $("#searchForm_zqzy #ps").val('').trigger("change");
            $("#searchForm_zqzy #settleSpeed").val('').trigger("change");
            $("#searchForm_zqzy #repManager").val('').trigger("change");
            $('#searchForm_zqzy')[0].reset();
        });
        // 指令管理tab-->委托单选按钮查询
        $("input[name=entrustStatus]").click(function(){
            positionsTable_zqzy.table ? positionsTable_zqzy.reload() : positionsTable_zqzy.load();
        });
        
        $(".cc-transfer-form #EtrIbBondinfo001_btn_export").click(getPositionsExportExcel)
        $(".transfer-form #EtrIbBondinfo001_btn_export").click(getZqzyPositionExportExcel)

        /**
         * 关闭委托
         */
        $('#EtrIbBondinfo001_btn_close').click(function() {
            if (positionsTable_zqzy != null && positionsTable_zqzy != '') {
                var selArr = positionsTable_zqzy.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else {
                for(var index in selArr){
                    if(selArr[index].entrustStatus == '08'){
                        Joyin.error("流程中的数据不允许关闭");
                        return ;
                    }
                }
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/entrustCheckCloseList', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo001/close', object, function(result) {
                            positionsTable_zqzy.reload();
                        });
                    });
                });
            }
        });
        /**
         * 撤销
         */
        $('#EtrIbBondinfo001_btn_revoke').click(function() {
            if (positionsTable_zqzy != null && positionsTable_zqzy != '') {
                var selArr = positionsTable_zqzy.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }else {
                for(var index in selArr){
                    if(selArr[index].entrustStatus == '09' || selArr[index].entrustStatus == '02'){
                        continue;
                    }else{
                        Joyin.error("只有指令状态为待分发、待接收的指令允许撤回");
                        return ;
                    }
                }
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo001/recall', object, function(result) {
                            positionsTable_zqzy.reload();
                        });
                    });
                });
            }
        });
        /**
         * 定时刷新
         */
        setInterval(function(){
            refreshPositionsTable();
            refreshZqzyPositionsTable();
        },300000);
    });

}());