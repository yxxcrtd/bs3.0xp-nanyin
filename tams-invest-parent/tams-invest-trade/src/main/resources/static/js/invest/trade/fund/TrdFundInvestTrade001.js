/**
 * 基金投资交易
 */
(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        //新增资产二、三、四级分类	王贵奇	2019/7/30	开始
    	var type9;
    	var type10;
    	var type11;
    	if($("#type9").val() == null || $("#type9").val() == ''){
    		type9 = $("#type9").val();
    	}
    	else{
    		type9 = $("#type9").val().join(',');
    	}
    	if($("#type10").val() == null || $("#type10").val() == ''){
    		type10 = $("#type10").val();
    	}
    	else{
    		type10 = $("#type10").val().join(',');
    	}
    	if($("#type11").val() == null || $("#type11").val() == ''){
    		type11 = $("#type11").val();
    	}
    	else{
    		type11 = $("#type11").val().join(',');
    	}
    	if($("#portfolioId").val() == null || $("#portfolioId").val() == ''){
            portfolioId = $("#portfolioId").val();
    	}
    	else{
            portfolioId = $("#portfolioId").val().join(',');
    	}
        //新增资产二、三、四级分类	王贵奇	2019/7/30	结束
        return {
            entrustId: $('#entrustId').val()
            ,portfolioId: portfolioId
            ,type6: $('#type6').val()
            //新增资产二、三、四级分类	王贵奇	2019/7/30	开始
            ,type9: type9
            ,type10: type10
            ,type11: type11
            //新增资产二、三、四级分类	王贵奇	2019/7/30	开始
            ,finprodId: $('#finprodId').val()
            ,tradeMarket: $('#tradeMarket').val()
            ,ps: $('#ps').val()
            ,validStatus: $('#validStatus').val()
            ,tradeDateBegin: $('#tradeDate').startDate()
            ,tradeDateEnd: $('#tradeDate').endDate()
            ,secondType:$('#secondType').val()
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
                return getPageParams();
            }
        },
//         beforeEditCell: function(value, data, field){
//             
//             return data.updateFlg == "02"? false:true;
//         },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            if(data.ps == ps_T04 || data.ps == ps_T06 || data.ps == ps_mr || data.ps == ps_mc || data.ps == ps_T52 || data.ps == ps_T65){
                Joyin.dialog(JoyinUtil.getLabel("TrdFundInvestTrade002.reference"),
                        JoyinUtil.getRealPath("invest/trade/fund/TrdFundInvestTrade002/init"),
                        "98%", "90%", data,
                        function(){table.reload();});
            }else if(data.ps == ps_T05 || data.ps == ps_T19 || data.ps == ps_T24 || data.ps == ps_T18 || data.ps == ps_T66){
                Joyin.dialog(JoyinUtil.getLabel("TrdFundInvestTrade002.reference"),
                        JoyinUtil.getRealPath("invest/trade/fund/TrdFundInvestTrade003/init"),
                        "98%", "90%", data,
                        function(){table.reload();});
            }
        },
        cols:[
            {field:'entrustId', width:'15%', title: 'TrdFundInvestTrade001.entrustId',sortField:"ENTRUST_ID"}
            ,{field:'tradeId', width:'15%', title: 'TrdFundInvestTrade001.tradeId',sortField:"TRADE_ID"}
            ,{field:'validStatus',visible:false}
            ,{field:'validStatusStr', width:100, title: 'TrdFundInvestTrade001.validStatus',sortField:"VALID_STATUS"}
            ,{field:'portfolioId', width:'12%', title: 'TrdFundInvestTrade001.portfolioId',
                render : function(data, type, row, meta) {
                return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
            },sortField:"PORTFOLIO_ID"}
            ,{field:'portfolioName', width:'12%', title: 'TrdFundInvestTrade001.portfolioName',sortField:"PORTFOLIO_NAME"}
            ,{field:'finprodMarketId', width:'12%', title: 'TrdFundInvestTrade001.finprodId',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"FINPROD_MARKET_ID"
            }
            ,{field:'finprodId',  visible:false}
            ,{field:'finprodName', width:'12%', title: 'TrdFundInvestTrade001.finprodName',sortField:"FINPROD_ABBR"}
            ,{field:'type6', visible:false}
            ,{field:'type6Str', width:'12%', title: 'TrdFundInvestTrade001.type6',sortField:"TYPE_6", visible:false}//隐藏 王贵奇 2019/7/30
            ,{field:'type9Str', width:'12%', title: 'TrdFundInvestTrade001.type9',sortField:"type9"}//新增资产二级分类 王贵奇 2019/7/30
            ,{field:'type10Str', width:'12%', title: 'TrdFundInvestTrade001.type10',sortField:"type10"}//新增资产三级分类 王贵奇 2019/7/30
            ,{field:'type11Str', width:'12%', title: 'TrdFundInvestTrade001.type11',sortField:"type11",visible:IS_SHOW_TYPE == '01'?true:false}//新增资产四级分类 王贵奇 2019/7/30
            ,{field:'ps',visible:false}
            ,{field:'psStr', width:100, title: 'TrdFundInvestTrade001.ps',sortField:"PS"}
            ,{field:'tradeMarket',visible:false}
            ,{field:'tradeMarketStr', width:100, title: 'TrdFundInvestTrade001.tradeMarket',sortField:"TRADE_MARKET"}
            ,{field:'tradeDate', width:100, title: 'TrdFundInvestTrade001.tradeDate',type:'date',sortField:"TRADE_DATE"}
            ,{field:'tradeAmt', width:100, title: 'TrdFundInvestTrade001.tradeAmt',sortField:"PRIN_AMT", type: 'number'}
            ,{field:'shareAmt', width:100, title: 'TrdFundInvestTrade001.shareAmt',sortField:"SHARE_AMT", type: 'number'}
            ,{field:'ccy',visible:false}
            ,{field:'ccyStr', width:'10%', title: 'TrdFundInvestTrade001.ccy',sortField:"CCY"}
            // <!-- 资产推荐方 新增 zhuyongkang 20191023 start-->
            ,{field:'assetRecommandOrg',sortField:'ASSET_RECOMMAND_ORG', width:150, title: 'TrdFundInvestTrade001.assetRecommandOrg'}
            // <!-- 资产推荐方 新增 zhuyongkang 20191023 end-->
            ,{field:'remark', width:'20%', title: 'TrdFundInvestTrade001.remark',sortable:false}
            ,{field:'bTradeId',visible:false}
            ,{field:'workflowId',visible:false}
            ,{field:'secondTypeStr', width:150 , sortField:"secondType",title: 'TrdFundInvestTrade001.secondType', visible:false}//隐藏 王贵奇 2019/7/30
            ,{field:'flg',visible:false}
            ,{field:'action', title: '操作' , type:'action',
                render: function (data, type, row, meta) {
                if (row.flg != '01')
                    return "";
                else
                    return '<i class="btn layui-icon edit" title="确认">&#x1005;</i>';
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
        Joyin.exportExcel(basePath+"invest/trade/fund/TrdFundInvestTrade001/export", getPageParams());
    };
    /**
     * 交易确认
     */
    table.bind('click','.edit',function(data,index){
        data.operateType = "confirm";
        data.pageModel= PAGE_MODEL_NEW;
        Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade001/check', data, function(result1) {
            if((data.ps == 'T04' || data.ps == 'T06') && data.totleTradeId){
                Joyin.dialog(JoyinUtil.getLabel("TrdFundInvestTrade004.confirm"),
                    basePath + "invest/trade/fund/TrdFundInvestTrade005/init",
                    "98%", "90%", data,function(){table.reload();});
            } else {
                Joyin.dialog(JoyinUtil.getLabel("TrdFundInvestTrade003.confirm"),
                    basePath + "invest/trade/fund/TrdFundInvestTrade003/init",
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
                basePath + "invest/asset/fund/FinFundTarget002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                finprodId:row.finprodId  //金融产品代码
                                //valDate:data.valDate,//估值日
                                //finprodMarketId:data.finprodMarketId//市场代码
                                });
    });
    
    $(function () {
        // 绑定按钮事件
        $('#TrdFundInvestTrade001_btn_search').click(function() {
            table.load();
            $('#TrdFundInvestTrade001_btn_revoke').show();
        });
        
        $('#TrdFundInvestTrade001_btn_export').click(function() {
            exportExcel();
        });
        $('#TrdFundInvestTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdFundInvestTrade002"),
                    basePath + "invest/trade/fund/TrdFundInvestTrade002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#TrdFundInvestTrade001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("TRDFUND_E_XXT_001"));
                return;
            }else if(!selArr || selArr.length > 1){
                var mes = "";
                for(var data in selArr){
                    mes = mes+ data.tradeId + ","
                }
                // COMMON_C_0029 不存在 提示错误 改为 COMMON_E_0058 yjm 20190904
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058","产品代码",mes));
                return;
            }
            var object = {selectedItem: selArr, operateType:"revoke"};
            var obj = object;
            if(object.selectedItem[0].ps == ps_T19 || object.selectedItem[0].ps == ps_T05 || object.selectedItem[0].ps == ps_T18){
                obj.selectedItem[0].bTradeId = object.selectedItem[0].tradeId;
                Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade001/check2', obj, function(result1) {
                    Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade001/check', object, function(result1) {
                        Joyin.prompt(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")),0 ,function(result2){
                            object.cancelRemark = result2;
                            WorkFlow.doRevokeList(basePath + 'invest/trade/fund/TrdFundInvestTrade001/revoke', object, "TrdFundInvestTrade001_btn_revoke", "TrdFundInvestTrade003");
                        });
                    });
                });
            }else{
                Joyin.ajaxJson(basePath + 'invest/trade/fund/TrdFundInvestTrade001/check', object, function(result1) {
                    var resdata = result1.data;
                    Joyin.prompt(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")),0 ,function(result2){
                        Joyin.confirm(resdata, function() {
                        object.cancelRemark = result2;
                        WorkFlow.doRevokeList(basePath + 'invest/trade/fund/TrdFundInvestTrade001/revoke', object, "TrdFundInvestTrade001_btn_revoke", "TrdFundInvestTrade002");
                        });
                    });
                });
            }
        });
        $('#TrdFundInvestTrade001_btn_dzhadd').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdFundInvestTrade004"),
                basePath + "invest/trade/fund/TrdFundInvestTrade004/init",
                "98%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        }else{
        	$('#type11Div').hide();
        }
    });
}());