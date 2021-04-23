/**
 * 权益类资产投资交易
 */
(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam = {
            entrustId: $('#entrustId').val()
            ,portfolioId: $('#portfolioId').val()
            ,type6: $('#type6').val()
            /*添加二-四级分类的查询参数 zhangpuyu 2019/7/29 start*/
            ,type9: $('#type9').val()
            ,type10: $('#type10').val()
            ,type11: $('#type11').val()
            /*添加二-四级分类的查询参数 zhangpuyu 2019/7/29 end*/
            ,finprodId: $('#finprodId').val()
            ,tradeMarket: $('#tradeMarket').val()
            ,ps: $('#ps').val()
            ,validStatus: $('#validStatus').val()
            ,tradeDateBegin: $('#tradeDate').startDate()
            ,tradeDateEnd: $('#tradeDate').endDate()
        }
        if ($("#portfolioId").val()) {
            pageParam.portfolioId = $("#portfolioId").val().join(",");
        } else {
            pageParam.portfolioId = null;
        }
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            if(data.ps == ps_T04 || data.ps == ps_T06 || data.ps == ps_mr || data.ps == ps_mc){
                Joyin.dialog(JoyinUtil.getLabel("TrdProjectNetInvestTrade002.reference"),
                    JoyinUtil.getRealPath("invest/trade/projectnet/TrdProjectNetInvestTrade002/init"),
                    "98%", "90%", data,
                    function(){table.reload();});
            }else if(data.ps == ps_T05 || data.ps == ps_T19 || data.ps == ps_T24){
                Joyin.dialog(JoyinUtil.getLabel("TrdProjectNetInvestTrade002.reference"),
                    JoyinUtil.getRealPath("invest/trade/projectnet/TrdProjectNetInvestTrade003/init"),
                    "98%", "90%", data,
                    function(){table.reload();});
            }
        },
        cols:[
            {field:'entrustId', width:'15%', title: 'TrdProjectNetInvestTrade001.entrustId',sortField:"ENTRUST_ID"}
            ,{field:'tradeId', width:'15%', title: 'TrdProjectNetInvestTrade001.tradeId',sortField:"TRADE_ID"}
            ,{field:'validStatus',visible:false}
            ,{field:'validStatusStr', width:100, title: 'TrdProjectNetInvestTrade001.validStatus',sortField:"VALID_STATUS"}
            ,{field:'portfolioId', width:'12%', title: 'TrdProjectNetInvestTrade001.portfolioId',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"PORTFOLIO_ID"}
            ,{field:'portfolioName', width:'12%', title: 'TrdProjectNetInvestTrade001.portfolioName',sortField:"PORTFOLIO_NAME"}
            ,{field:'finprodMarketId', width:'12%', title: 'TrdProjectNetInvestTrade001.finprodId',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"FINPROD_MARKET_ID"
            }
            ,{field:'finprodId',  visible:false}
            ,{field:'finprodName', width:'12%', title: 'TrdProjectNetInvestTrade001.finprodName',sortField:"FINPROD_NAME"}
            /*添加二-四级分类的表单头,隐藏资产类型 zhangpuyu 2019/7/29 start*/
            /*,{field:'type6', visible:false}
            ,{field:'type6Str', width:'12%', title: 'TrdPlanCcyInvestTrade001.type6',sortField:"TYPE_6"}*/
            ,{field:'type9', visible:false}
            ,{field:'type9Str', width:'12%', title: 'TrdProjectNetInvestTrade001.type9',sortField:"TYPE_9"}
            ,{field:'type10', visible:false}
            ,{field:'type10Str', width:'12%', title: 'TrdProjectNetInvestTrade001.type10',sortField:"TYPE_10"}
            ,{field:'type11', visible:false}
            ,{field:'type11Str', width:'12%', title: 'TrdProjectNetInvestTrade001.type11',sortField:"TYPE_11",visible:IS_SHOW_TYPE == '01'?true:false}
            /*添加二-四级分类的表单头,隐藏资产类型 zhangpuyu 2019/7/29 end*/
            ,{field:'ps',visible:false}
            ,{field:'psStr', width:100, title: 'TrdProjectNetInvestTrade001.ps',sortField:"PS"}
            ,{field:'tradeDateTxt', width:100, title: 'TrdProjectNetInvestTrade001.tradeDate',type:'date',sortField:"TRADE_DATE"}
            ,{field:'confirmDateText', width:100, title: '确认日期',type:'date',sortField:"CONFIRM_DATE"}
            ,{field:'confirmDate', visible:false}
            ,{field:'tradeAmt', width:100, title: 'TrdProjectNetInvestTrade001.tradeAmt',sortField:"PRIN_AMT",type:'money',digit:2}
            ,{field:'shareAmt', width:100, title: 'TrdProjectNetInvestTrade001.shareAmt',sortField:"SHARE_AMT",type:'money',digit:2}
            ,{field:'ccy',visible:false}
            ,{field:'ccyStr', width:'10%', title: 'TrdProjectNetInvestTrade001.ccy',sortField:"CCY"}
            ,{field:'remark', width:'20%', title: 'TrdProjectNetInvestTrade001.remark'}
            ,{field:'bTradeId',visible:false}
            ,{field:'totleTradeId',visible:false}
            ,{field:'workflowId',visible:false}
            /* 指令日期 start */
            ,{field:'entrustDate',  visible:false}
            /*指令日期 end */
            ,{field:'flg',visible:false}
            ,{field:'operation', title: '操作' , type:'action',
                render: function (data, type, row, meta) {
                    if (row.flg != '01'){
                        return "";
                    }else {
                        if (row.ps !== "T19"){
                            return '<i class="btn layui-icon edit" title="确认">&#x1005;</i>';
                        }
                    }
                }
//                buttons: [{
//                text: '',
//                clas: 'iconfont icon-circle-correct_fill config',
//                title: '确认'
//            }]
            }
        ],
    });

    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel(basePath+"invest/trade/projectnet/TrdProjectNetInvestTrade001/export", getPageParams());
    };

    /**
     *
     *  交易确认
     */
    table.bind('click','.edit',function(data,index){
        data.operateType = "confirm";
        data.pageModel= PAGE_MODEL_NEW;
        Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade001/check', data, function(result1) {
            if(data.totleTradeId){
                Joyin.dialog(JoyinUtil.getLabel("TrdProjectNetInvestTrade005.title"),
                    basePath + "invest/trade/projectnet/TrdProjectNetInvestTrade005/init",
                    "98%", "90%", data,function(){table.reload();});
            } else {
                Joyin.dialog(JoyinUtil.getLabel("TrdProjectNetInvestTrade003.confirm"),
                    basePath + "invest/trade/projectnet/TrdProjectNetInvestTrade003/init",
                    "98%", "90%", data,function(){table.reload();});
            }
        });
    });
    /**
     * 组合代码链接
     */
    table.bind('click','.portfolioIdLink', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                portfolioId:row.portfolioId});//主键组合代码
    });
    /**
     * 资产代码链接
     */
    table.bind('click','.finprodIdLink', function(row) {
        //在新建界面进行显示单行的详细信息
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath + "invest/asset/projectnet/FinProjectNetTargetAsset002/init",
            "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                finprodId:row.finprodId,  //金融产品代码
                //valDate:data.valDate,//估值日
                //finprodMarketId:data.finprodMarketId//市场代码
            });
    });

    $(function () {
        // 绑定按钮事件
        $('#TrdProjectNetInvestTrade001_btn_search').click(function() {
            table.load();
            $('#TrdProjectNetInvestTrade001_btn_revoke').show();
        });

        $('#TrdProjectNetInvestTrade001_btn_export').click(function() {
            exportExcel();
        });
        $('#TrdProjectNetInvestTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdProjectNetInvestTrade002"),
                basePath + "invest/trade/projectnet/TrdProjectNetInvestTrade002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#TrdProjectNetInvestTrade001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("TRDFUND_E_XXT_001"));
                return;
            }else if(!selArr || selArr.length > 1){
                var mes = "";
                for(var data of selArr){
                    mes = mes+ data.tradeId + ","
                }
                // COMMON_C_0029 不存在 提示错误 改为 COMMON_E_0058 yjm 20190904
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058","产品代码",mes));
                return;
            }
            var object = {selectedItem: selArr, operateType:"revoke"};
            var obj = object;
            if(object.selectedItem[0].ps == ps_T19 || object.selectedItem[0].ps == ps_T05){
                obj.selectedItem[0].bTradeId = object.selectedItem[0].tradeId;
                obj.selectedItem[0].busiType = BUS_TYPE;
                obj.selectedItem[0].pageId = "TrdProjectNetInvestTrade003";
                Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade001/check2', obj, function(result1) {
//                    Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade001/check', object, function(result1) {
                    Joyin.prompt(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")),0 ,function(result2){
                        object.cancelRemark = result2;
                        WorkFlow.doRevokeList(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade001/revoke', object, "TrdProjectNetInvestTrade001_btn_revoke", "TrdProjectNetInvestTrade003",function(){
                            table.reload();
                        });
                    });
//                    });
                });
            }else{
                obj.selectedItem[0].pageId = "TrdProjectNetInvestTrade002";
                Joyin.ajaxJson(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade001/check', obj, function(result1) {
                    Joyin.prompt(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")),0 ,function(result2){
                        object.cancelRemark = result2;
                        WorkFlow.doRevokeList(basePath + 'invest/trade/projectnet/TrdProjectNetInvestTrade001/revoke', object, "TrdProjectNetInvestTrade001_btn_revoke", "TrdProjectNetInvestTrade002",function(){
                            table.reload();
                        });
                    });
                });
            }

        });
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
            $('#type11Div').show();
        }else{
            $('#type11Div').hide();
        }

        $('#TrdPlanNetInvestTrade004_btn_add').click(function() {
            Joyin.dialog('权益类资产多组合交易新建', basePath + "invest/trade/projectnet/TrdProjectNetInvestTrade004/init", "99%", "99%", {
                    pageModel : PAGE_MODEL_NEW
                }, function() {
                    table.reload();
                }
            );
        });
    });
}());