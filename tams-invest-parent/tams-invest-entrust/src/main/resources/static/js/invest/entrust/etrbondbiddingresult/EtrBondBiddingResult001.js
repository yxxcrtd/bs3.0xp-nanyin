$(function() {
    /**
     * 获取查询参数
     */
    var searchform =function(){
        var object = $.serializeToObject('#submitForm');
        if(object.portfolioId){
            object.portfolioId = object.portfolioId.join(',');
        }
        return {
            portfolioId: object.portfolioId,
            finprodId:$("#finprodId").val(),
            tradeMarket:$("#tradeMarket").val(),
            counterId:$("#counterId").val(),
            paymentStatus:$("#paymentStatus").val(),
            startDate:$('#bidDate').startDate(),
            endDate:$('#bidDate').endDate()
        };
        // var object = $.serializeToObject('#submitForm');
        // if(object.portfolioId){
        //     object.portfolioId = object.portfolioId.join(',');
        // }
        // return object;
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return searchform();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表头
         */
        cols:[
            {field:'otherPaymentStatus', sortField:'paymentStatus', title: '缴款状态',width:150,sortable:true}
            ,{field:'paymentStatus',  title: '缴款状态',width:150,sortable:true,visible:false}
            ,{field:'entrustId',title:'委托编号',width:150,sortable:false}
            ,{field:'entrustStatus',title:'委托状态',width:150,sortable:false,visible:false}
            ,{field:'bidId',title:'投标编号',width:150,sortable:false}
            ,{field:'portfolioId',title:'组合代码',width:150,sortable:false,
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title:'组合名称',width:150,sortable:false}
            ,{field:'finprodMarketId', title:'资产代码',width:150,sortable:false,
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"finprodMarketId"
            }
            ,{field:'finprodId', title:'金融产品代码',width:150,sortable:false,visible:false}
            ,{field:'finprodName', title:'资产名称',width:150,sortable:false}
            ,{field:'tradeMarket', title:'交易场所',width:150,sortable:false,visible:false}
            ,{field:'otherTradeMarket', title:'交易场所',width:150,sortable:false}
            ,{field:'payDate', title:'缴款日',type:'date',width:150,sortable:false}
            ,{field:'payType', title:'缴款方式',width:150,sortable:false,visible:false}
            ,{field:'otherPayType', title:'缴款方式',width:150,sortable:false}
            ,{field:'bidPrinAmt', title:'投标券面总额/万元',width:150,digit:4,sortable:false,type:'number'}
            ,{field:'winPrinAmt', title:'中标券面总额/万元',width:150,digit:4,sortable:false,type:'number'}
            ,{field:'bidPrice', title:'投标标位%',width:150,sortable:false,visible:false}
            ,{field:'bidPriceStr', title:'投标标位%',width:150,sortable:false}
            ,{field:'winPrice', title:'中标标位%',width:150,sortable:false}
            ,{field:'winCprice', title:'中标净价',width:150,sortable:false,visible:false,type:'number'}
            ,{field:'bidDate', title:'投标日',type:'date',width:150,sortable:false}
        ]
    });

    $(function(){
        /**
         * 画面加载完成
         */
        //add by chenjiaxing 主动提醒初始化 start
        var a = finprodId;
        var b = mdate;
        if(finprodId && mdate){
            $('#finprodId').val(finprodId);
            $('#bidDate').val(mdate+' '+'-'+' '+mdate);
            table.load();
        }
        //add by chenjiaxing 主动提醒初始化 end
        $('#'+pageId+"_btn_search").click(function () {
            // $(".ptl-table1").css("display","none");
            // $(".ptl-table").css("display","block");
            table.load();

        })
        /**
         * 导出按钮
         */
        $('#'+pageId+'_btn_export').click(function() {
            exportExcel();
        });
        // 分销缴款
        $('#'+pageId+"_btn_payment").click(function () {

            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            //check委托已关闭
            if ('06' == selArr[0].entrustStatus){
                Joyin.error(JoyinUtil.getMessage("ETR_BBR_002"));
                return;
            }
            //check流程中和已完成
            if ('04' == selArr[0].paymentStatus || '05' == selArr[0].paymentStatus){
                Joyin.error(JoyinUtil.getMessage("ETR_BBR_001"));
                return;
            }

            var paramObj ={};
            paramObj.finprodId=selArr[0].finprodId;
            Joyin.ajaxJson(basePath + 'invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001/findFinProductByOFinprodId',paramObj,function (result){
                if (!result || !result.finprodMarketId) {
                    Joyin.error("没有关联一级市场债，是否直接录入");
                }
                else {
                    initBondTrade(result, selArr);
                }
            });
        });
        /**add by sunhui 20210721 重庆银行：手工释放中标结果占用的头寸 start**/
        // 手工释放
        $('#'+pageId+"_btn_manualRelease").click(function () {

            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            //check委托已关闭
            if ('06' == selArr[0].entrustStatus){
                Joyin.error(JoyinUtil.getMessage("ETR_BBR_005"));
                return;
            }
            //check流程中和已完成
            if ('04' == selArr[0].paymentStatus || '05' == selArr[0].paymentStatus){
                Joyin.error(JoyinUtil.getMessage("ETR_BBR_003"));
                return;
            }
            //平台日是缴款日时提示
            if (platDate >= selArr[0].payDate){
                Joyin.error(JoyinUtil.getMessage("ETR_BBR_006"));
                return;
            }

            var paramObj ={};
            paramObj.finprodId=selArr[0].finprodId;//金融产品代码
            paramObj.finprodMarketId=selArr[0].finprodMarketId;//资产代码
            paramObj.payDate=selArr[0].payDate;//缴款日
            paramObj.entrustId=selArr[0].entrustId;//委托代码
            paramObj.portfolioId=selArr[0].portfolioId;//组合代码
            paramObj.portfolioName=selArr[0].portfolioName;//组合名称
            paramObj.bidId=selArr[0].bidId;//投标编号
            paramObj.bidPrice=selArr[0].bidPrice;//投标标位
            Joyin.ajaxJson(basePath + 'invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001/checkManualRelease', paramObj, function(result) {//数据验证
                Joyin.ajaxJson(basePath + 'invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001/doManualRelease',paramObj,function (result){
                    table.reload();
                });
            });
        });
        /**add by sunhui 20210721 重庆银行：手工释放中标结果占用的头寸 end**/

        /**add by sunhui 20210722 重庆银行：中标结果批量缴款 start**/
        // 批量缴款
        $('#'+pageId+"_btn_paymentBatch").click(function () {
            //定义多组合交易入口标记
            var flg = true;

            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                flg = false;
                return;
            }
            /*// 列表中勾选对象只有一条时提示请选择至少2条记录批量缴款
            if (selArr.length == 1) {
                Joyin.error(JoyinUtil.getMessage("ETR_BBR_008"));
                flg = false;
                return;
            }*/
            //遍历校验
            var finprodIdOne = selArr[0].finprodId;//第一个资产代码
            var payDateOne = selArr[0].payDate;//第一个缴款日
            var portfolioIdOne = selArr[0].portfolioId;//第一个缴款组合
            $.each(selArr, function(i, d) {
                //check委托已关闭
                if ('06' == d.entrustStatus){
                    Joyin.error(JoyinUtil.getMessage("ETR_BBR_002") + d.entrustId);
                    flg = false;
                    return;
                }
                //check流程中和已完成
                if ('04' == d.paymentStatus || '05' == d.paymentStatus){
                    Joyin.error(JoyinUtil.getMessage("ETR_BBR_009"));
                    flg = false;
                    return;
                }
                //所选结果非同一资产，请重新选择!
                if(finprodIdOne != d.finprodId){
                    Joyin.error(JoyinUtil.getMessage("ETR_BBR_010"));
                    flg = false;
                    return;
                }
                //所选结果非同一缴款日，请重新选择!
                if(payDateOne != d.payDate){
                    Joyin.error(JoyinUtil.getMessage("ETR_BBR_011"));
                    flg = false;
                    return;
                }
                //所选组合存在相同组合，请重新选择！
                if(i != 0 && portfolioIdOne ==d.portfolioId){
                    Joyin.error(JoyinUtil.getMessage("ETR_BBR_012"));
                    flg = false;
                    return;
                }
            });

            //所有校验都通过则调用多组合交易界面
            if(flg){
                var paramObj ={};
                paramObj.finprodId=selArr[0].finprodId;
                Joyin.ajaxJson(basePath + 'invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001/findFinProductByOFinprodId',paramObj,function (result){
                    if (!result || !result.finprodMarketId) {
                        Joyin.error("没有关联一级市场债，是否直接录入");
                    }
                    else {
                        initBondTradeBatch(result, selArr);
                    }
                });
            }
        });
        var initBondTradeBatch = function (result, selArr) {
            /*selArr[0].finprodMarketId = result.finprodMarketId;
            selArr[0].finprodId = result.finprodId;
            selArr[0].pageModel = PAGE_MODEL_NEW;
            selArr[0].yield = selArr[0].winPrice;
            selArr[0].tradeMarketMode = '01';
            selArr[0].tradeMarket = '01';
            //标识债券一级市场
            selArr[0].bondFirstSign = '01';*/
            //改造定义参数 sunhui start
            var winPrinAmt = 0;
            var entrustId = "";
            $.each(selArr, function(i, d) {
                winPrinAmt = JoyinMath.doAdd(d.winPrinAmt) ;
                entrustId = entrustId + "," + d.entrustId;
            });
            var paramObjs ={};
            paramObjs.bondFirstSign = '01';
            paramObjs.pageModel = PAGE_MODEL_NEW;
            paramObjs.tradeMarket = '01';
            paramObjs.finprodMarketId = result.finprodMarketId;
            paramObjs.finprodId = result.finprodId;
            paramObjs.needDoTrade = selArr;
            paramObjs.winPrinAmt = winPrinAmt;// 中标 "中标券面总额"
            paramObjs.entrustId = entrustId.substr(1,entrustId.length);
            paramObjs.winCprice = selArr[0].winCprice;//第一条的中标净价
            //改造定义参数 sunhui end
            Joyin.dialog(JoyinUtil.getLabel("TrdBondInvestTrade003"), basePath + "invest/trade/bond/TrdBondInvestTrade003/init", "98%", "98%", paramObjs, function () {
                table.reload();
            });
        }
        /**add by sunhui 20210722 重庆银行：中标结果批量缴款 end**/

        var initBondTrade = function (result, selArr) {
            selArr[0].finprodMarketId = result.finprodMarketId;
            selArr[0].finprodId = result.finprodId;
            selArr[0].pageModel = PAGE_MODEL_NEW;
            selArr[0].yield = selArr[0].winPrice;
            selArr[0].tradeMarketMode = '01';
            selArr[0].tradeMarket = '01';
            //标识债券一级市场
            selArr[0].bondFirstSign = '01';
            // add by zhangrui1 start
            selArr[0].oFinprodId = result.oFinprodId;
            // add by zhangrui1 end
            Joyin.dialog(JoyinUtil.getLabel("TrdBondInvestTrade002"), basePath + "invest/trade/bond/TrdBondInvestTrade002/init", "98%", "98%", selArr[0], function () {
                table.reload();
            });
        }

        /**
         * 导出
         */
        var exportExcel = function(){
            Joyin.exportExcel(basePath + 'invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001/export', searchform());
        };
    })

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
            basePath
            + "invest/asset/tenderbond/FinTenderBondTargetAsset002/init",
            "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                finprodId:row.finprodId  //金融产品代码
            });
    });
}());