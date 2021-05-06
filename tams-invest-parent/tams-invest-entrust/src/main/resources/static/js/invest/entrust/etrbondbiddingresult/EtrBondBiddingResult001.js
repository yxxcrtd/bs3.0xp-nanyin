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
            ,{field:'bidPrinAmt', title:'投标券面总额',width:150,sortable:false,type:'number'}
            ,{field:'winPrinAmt', title:'中标券面总额',width:150,sortable:false,type:'number'}
            ,{field:'bidPrice', title:'投标标位%',width:150,sortable:false,visible:false}
            ,{field:'bidPriceStr', title:'投标标位%',width:150,sortable:false}
            ,{field:'winPrice', title:'中标标位%',width:150,sortable:false}
            ,{field:'bidDate', title:'投标日',type:'date',width:150,sortable:false}
        ]
    });

    $(function(){
        /**
         * 画面加载完成
         */
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

        var initBondTrade = function (result, selArr) {
            selArr[0].finprodMarketId = result.finprodMarketId;
            selArr[0].finprodId = result.finprodId;
            selArr[0].pageModel = PAGE_MODEL_NEW;
            selArr[0].yield = selArr[0].winPrice;
            selArr[0].tradeMarketMode = '01';
            selArr[0].tradeMarket = '01';
            //标识债券一级市场
            selArr[0].bondFirstSign = '01';
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