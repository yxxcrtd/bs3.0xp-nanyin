/**
 * 资产配置快速转仓指令
 */
(function () {
    var divTrialInfo=$('#trial_info_add');
    var selectedPortMap={};
    var count=1;
    var getFilterSearchParams=function () {
        var paramObj = {
            portfolioId:PORTFOLIO_ID,
            finprodType:$("#filter_finprodType").val(),
            issueType:$("#filter_operationType").val()
        }
        comparePeriodNum($("#filter_durationStart"),$("#filter_durationEnd"),function (obj) {
            paramObj.durationStart=obj.valStart;
            paramObj.durationEnd=obj.valEnd;
        })
        comparePeriodNum($("#filter_yieldStart"),$("#filter_yieldEnd"),function (obj) {
            paramObj.yieldStart=obj.valStart;
            paramObj.yieldEnd=obj.valEnd;
        })
        return paramObj;
    }

    /**
     * 处理数字区间条件
     * @param start
     * @param end
     * @param callback
     */
    var comparePeriodNum=function (start,end,callback) {
        var valStart=$(start).val();
        var valEnd=$(end).val();
        if(valStart&&valEnd&&valStart>valEnd){
            valStart=valEnd;
            valEnd=$(start).val();
        }
        callback({valStart:valStart,valEnd:valEnd});
    }

    /**
     * 定义筛选资产数据表格
     */
    var filterTable = $("#filterAssetTable").joyintable({
        ajax: {
            url: 'getAstPosition',
            type: 'POST',
            data: function () {
                return getFilterSearchParams();
            }
        },
        height:200,
        searchOnInit: true,
        loadonce: true,
        page:false,
        cols: [
            {field: 'finprodId', title: 'EtrAssetConfigTransfer002.finprodId', width: 120},
            {field: 'finprodAbbr', title: 'EtrAssetConfigTransfer002.finprodName', width: 170},
            {field: 'buyCost', title: 'EtrAssetConfigTransfer002.buyCost', width: 100},
            {field: 'vdate', title: 'EtrAssetConfigTransfer002.vDate', width: 90, type: 'date'},
            {field: 'mdate', title: 'EtrAssetConfigTransfer002.mDate', width: 90, type: 'date'},
            {field: 'exerciseDate', title: 'EtrAssetConfigTransfer002.exercise', width: 100, type: 'date'},
            {field: 'tradeMarketStr', title: 'EtrAssetConfigTransfer002.address', width: 100},
            {field: 'type6Str', title: 'EtrAssetConfigTransfer002.bondType', width: 120},
            {field: 'acctName', title: 'EtrAssetConfigTransfer002.trader',width:140},
            {field: 'rate', title: 'EtrAssetConfigTransfer002.yield',type: 'number',textrange:2,width:90},
            {field: 'amount', title: 'EtrAssetConfigTransfer002.positionAmount',width:120}
        ]
    });


    /**
     * 定义试算数据表格
     */
    var trialTable = $("#trialAssetTable").joyintable({
        data:[],
        height:200,
        searchOnInit: true,
        loadonce: true,
        page:false,
        cols: [
            {field: 'portfolioId', visible : false},
            {field: 'portfolioName', title: 'EtrAssetConfigTransfer002.portfolioName', width: 150},
            {field: 'finprodId', title: 'EtrAssetConfigTransfer002.finprodId', width: 120},
            {field: 'finprodAbbr', title: 'EtrAssetConfigTransfer002.finprodName', width: 170},
            {field: 'price', title: 'EtrAssetConfigTransfer002.tradeCprice', width: 100},
            {field: 'buyCost', title: 'EtrAssetConfigTransfer002.buyCost', width: 100},
            {field: 'netCprice', title: 'EtrAssetConfigTransfer002.valCprice', width: 100},
            {field: 'deviation', title: 'EtrAssetConfigTransfer002.deviation',type: 'number',textrange:4,width: 100},
            {field: 'mdate', title: 'EtrAssetConfigTransfer002.mDate', width: 90, type: 'date'},
            {field: 'exerciseDate', title: 'EtrAssetConfigTransfer002.exerciseDate', width: 100, type: 'date'},
            {field: 'invAim', title: 'EtrAssetConfigTransfer002.invAim', width: 100,edit:true,editOption:{type:'select',formatoption:true,showField: 'text',result: INV_AIM}},
            {field: 'periodYield', title: 'EtrAssetConfigTransfer002.periodYield',type: 'number',textrange:2,width: 130},
            {field: 'entrustNum', title: 'EtrAssetConfigTransfer002.outputNum',type: 'number',textrange:2,width:100,edit:true},
            {field: 'radio', title: 'EtrAssetConfigTransfer002.radio',type: 'number',textrange:4, width:120}
        ]
    });

    /**
     * 快速生成指令点击动作
     */
    var generateClickAction=function() {
        if(trialTable.getAllRows() == null||trialTable.getAllRows() == ''){
            Joyin.error(JoyinUtil.getMessage("ETR_E_0036"));
            return;
        }
        var object={} ;
        object.pageModel = pageModel;
        object.portfolioId=PORTFOLIO_ID;//转出组合
        object.selectedItem = trialTable.getAllRows();
        Joyin.ajaxJson(basePath + 'invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer002/assembleImportData', object, function(assembleResult) {
            if (assembleResult) {
                var result = {};
                result.tempList = assembleResult.entrustData;
                var tradeMarket=object.selectedItem[0].tradeMarket;
                //银行间指令
                if(TRADE_PLACE_01==tradeMarket){
                    Joyin.dialog(JoyinUtil.getLabel("EtrInvestBondInfo004"),
                        JoyinUtil.getRealPath("/invest/entrust/etrinvestbondinfo/EtrInvestBondInfo004/init"),
                        "98%", "95%", result);
                }
                //上交所指令
                if(TRADE_PLACE_03==tradeMarket){
                    //todo
                }
                //深交所指令
                if(TRADE_PLACE_04==tradeMarket){
                    //todo
                }
            }
        });
    }

    /**
     * 组合联动事件
     */
    var portfolioChangeAction=function(obj,index){
        if($(obj).val()) {
            //与被转入组合的判断
            if(PORTFOLIO_ID==$(obj).val()){
                Joyin.error(JoyinUtil.getMessage("ETR_E_0035"));$(obj).val()
                $(obj).val('').trigger('change');
                return;
            }
            //与已选转入组合的判断
            for(var i=1;i<=count;i++){
                if(i!=index&&selectedPortMap[i]==$(obj).val()){
                    $(obj).val('').trigger('change');
                    return;
                }
            }
            //塞值进临时已选组合对象中
            selectedPortMap[index]=$(obj).val();
            //获取转入组合的其它信息，成立日规模等
            Joyin.ajaxJson(basePath + 'invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer002/selectPortfolioInfo', {portfolioId: $(obj).val()},
                function (result) {
                    if (result) {
                        $("input#trial_vdate:eq("+(index-1)+")").val(Date.ofDate(result.VDATE).format('yyyy-MM-dd'));
                        $("input#trial_valuation:eq("+(index-1)+")").val(result.TDY_ASSET_NETVALUE);
                    }
                }
            );
        }else{
            //清除临时已选组合对象
            delete selectedPortMap[index];
            $("input#trial_vdate:eq("+(index-1)+")").val('').trigger('change');
            $("input#trial_valuation:eq("+(index-1)+")").val('').trigger('change');
        }
    }

    /**
     * 试算资产转仓
     */
    var trialPositionClickAction=function () {
        var selArr= null!=filterTable?filterTable.getAllCheckedRows():null;
        //判断是否选中元素
        if( null!=selArr &&selArr.length > 0 ) {
            if (selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
        }else{
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object=selArr[0];
        var $portList=$("select#trial_portfolioId");
        object.portfolioValuationList=new Array();
        $.each($portList,function (i,v) {
            if($(v).val()){
                var item={
                    portfolioId:$(v).val(),
                    portfolioName:$(v).select2('data')[0].text.substr($(v).select2('data')[0].text.indexOf(":")+1),
                    tdyAssetNetvalue:$("input#trial_valuation:eq("+i+")").val()
                }
                object.portfolioValuationList.push(item)
            }
        })
        if(0==object.portfolioValuationList.length){
            Joyin.error(JoyinUtil.getMessage("ETR_E_0034"));
            return;
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrastconfigtransfer/EtrAssetConfigTransfer002/trialPosition', object,
            function(result) {
                if(result) {
                    if (result != null && result.length > 0) {
                        $.each(result,function (fi,fv) {
                            fv.invAim=INV_AIM_FVTPL;
                            $.each(object.portfolioValuationList,function (i,v) {
                                if(v.portfolioId==fv.portfolioId){
                                    fv.portfolioName=v.portfolioName;
                                    return false;
                                }
                            })
                        })
                        trialTable.clear(function () {
                            trialTable.addRow(result);
                        })
                    }
                }
            }
        );
    }

    /**
     * 添加转入组合点击事件
     */
    var addPortfolioClickAction=function () {
        if(pageModel == PAGE_MODEL_REFERENCE){
            return;
        }
        count++;
        var divStr=assembleHtmlPort();
        divTrialInfo.append(divStr);
        $("div#trial-info-"+count).find("select#trial_portfolioId").textSelect();
        var label=$("div#trial-info-"+count).find("label:eq(0)");
        $(label).html($(label).html().replace("1",count));
        var index=0;
        var $span=$("div#trial-info-"+count).find("span");
        var hasClass="select2 select2-container select2-container--default";
        var hasClassAbove=hasClass;
        if($("div#baseTrialDiv").find("select#trial_portfolioId").val()){
            hasClass="select2 select2-container select2-container--default select2-container--below";
            hasClassAbove="select2 select2-container select2-container--default select2-container--above";
        }
        $.each($span,function (i,v) {
            if($(v).hasClass(hasClass)||$(v).hasClass(hasClassAbove)){
                index=i;
            }
        })
        if(index>=1){
            $span.eq(index).hide();
        }
    }

    /**
     * 删除转入组合点击事件
     */
    var delPortfolioClickAction=function () {
        if(1==count){
            return;
        }
        $("div#trial-info-"+count).remove();
        count--;
    }

    //动态新增转入组合的行元素
    var assembleHtmlPort=function(){
        var divStr='';
        divStr+='<div class="layui-form-item width90" id="trial-info-'+count+'">';
        divStr+=$("div#baseTrialDiv").html();
        divStr+='</div>';
        return divStr;
    }

    //输入组合选择时联动事件
    $("#searchForm").on('change','.select_portfolio',function() {
        var jqDivId=$(this).closest('div.layui-form-item').attr('id');
        portfolioChangeAction($(this),jqDivId.substr(jqDivId.length-1,1))
    });

    //画面组件全部加载完
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
        }
    }

    /**
     * 绑定事件
     */
    $(function () {
        /**
         * 筛选资产查询按钮
         */
        $('#EtrAssetConfigTransfer002_btn_search').click(function () {
            if (!filterTable.table) {
                filterTable.load();
            } else {
                filterTable.reload();
            }
        });

        /**
         * 添加输入组合按钮
         */
        $('#EtrAssetConfigTransfer002_btn_add').click(addPortfolioClickAction);

        /**
         * 删除输入组合按钮
         */
        $('#EtrAssetConfigTransfer002_btn_del').click(delPortfolioClickAction);

        /**
         * 试算按钮
         */
        $('#EtrAssetConfigTransfer002_btn_math').click(trialPositionClickAction);

        /**
         * 快速生成指令
         */
        $('#EtrAssetConfigTransfer002_btn_generate').click(generateClickAction);
    });
}());
