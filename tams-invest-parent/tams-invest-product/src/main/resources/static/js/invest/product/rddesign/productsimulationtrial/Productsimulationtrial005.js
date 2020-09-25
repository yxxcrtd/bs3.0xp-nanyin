(function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var pageParam = $.serializeToObject('#searchForm');
        if($('#checkDate').val()) {
            pageParam.startCheckDate = $('#checkDate').startDate();
            pageParam.endCheckDate = $('#checkDate').endDate();
        }

        if ($("#assetTypeThree").val()) {
            pageParam.assetTypeThree = $("#assetTypeThree").val().join(",");
        } else {
            pageParam.assetTypeThree = null;
        }
        if ($("#assetTypeFour").val()) {
            pageParam.assetTypeFour = $("#assetTypeFour").val().join(",");
        } else {
            pageParam.assetTypeFour = null;
        }
        if ($("#assetTypeFive").val()) {
            pageParam.assetTypeFive = $("#assetTypeFive").val().join(",");
        } else {
            pageParam.assetTypeFive = null;
        }
        return pageParam;
    };


    var getElementById = function (id) {
        return $("#" +productSimulationTrial + "_" + id);
    };

    /**
     * 历史数据（Total）
     */
    var pledgeBondTotalTbl = getElementById("productSimulationTrialTotalList").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        page: false,
        loadonce:true,
        height: 200,
        /**
         * 定义表头
         */
        cols: [
            // 资产代码
            {
                field: 'assetId',
                width: 180,
                title: 'ProductSimulationTrial005.assetId',
                sortable: false
            }
            // 资产名称
            , {
                field: 'assetAbbr',
                width: 220,
                title: 'ProductSimulationTrial005.assetAbbr',
                sortable: false
            }
            //资产五级分类
            , {
                field: 'assetTypeFiveName',
                width: 260,
                title: 'ProductSimulationTrial005.assetTypeFiveName',
                sortable: false
            }
            //买入日
            , {
                field: 'buyDate',
                width: 200,
                title: 'ProductSimulationTrial005.buyDate',
                type:'date',
                sortable: false
            }
            //买入持仓
            , {
                field: 'buyHolding',
                width: 160,
                type: 'number',
                title: 'ProductSimulationTrial005.buyHolding',
                sortable: false
            }
            //到期收益率
            , {
                field: 'mYieldStr',
                width: 160,
                type: 'number',
                title: 'ProductSimulationTrial005.mYield',
                digit: 4,
                sortable: false
            }
        ],
        done:function(table, allData) {
            //计算加权收益率
            var list = pledgeBondTotalTbl.getAllRows();
            if(list){
                //总份额
                var buyHoldingTotal = 0;
                var additionalEquity = 0;
                for(var i=0;i<list.length;i++) {
                    buyHoldingTotal = JoyinMath.doAdd(buyHoldingTotal, list[i].buyHolding?list[i].buyHolding:0,4);
                }
                for(var i=0;i<list.length;i++) {
                    additionalEquity = JoyinMath.doAdd(additionalEquity,JoyinMath.doMul(JoyinMath.doDiv(list[i].buyHolding,buyHoldingTotal,14),list[i].mYield?list[i].mYield:0,14),14);
                }
                $("#additionalEquity").val(JoyinMath.doMul(additionalEquity,100,4));
            }
        }

    });

    /**
     * 画面加载完成
     */
    $(function () {
        /**
         * 资产大类联动事件
         */
        $('#assetTypeOne').change(function(){
            var assetTypeOne = $("#assetTypeOne").val();
            if(assetTypeOne == "02" || assetTypeOne == "03"){
                $("#oType_1").show();
            }else{
                $("#oType_1").hide();
            }
        });


        $('#ProductSimulationTrial005_btn_search').click(function() {
            //剩余期限
            if($("#lastMinPeriod").val()!="" && $("#lastMaxPeriod").val()!=""){
               if(parseInt($("#lastMaxPeriod").val())< parseInt($("#lastMinPeriod").val())){
                   layer.alert('剩余期限区间输入有误！');
                   return;
               }
            }else{
                if($("#lastMinPeriod").val()!= "" && $("#lastMaxPeriod").val()== ""){
                    layer.alert('请输入完整剩余期限！');
                    return;
                }
                if($("#lastMaxPeriod").val()!= "" && $("#lastMinPeriod").val()== ""){
                    layer.alert('请输入完整剩余期限！');
                    return;
                }
            }
//            //资产大类联动事件
//            if($("#assetTypeOne").val()== "02" || $("#assetTypeOne").val()== "03"){
//                if($("#debtRating").val()== ""){
//                    layer.alert('请选择外部债项评级！');
//                    return;
//                }
//                if($("#fcrGradeOut").val()== ""){
//                    layer.alert('请选择外部主体评级！');
//                    return;
//                }
//            }
            //资产大类
            if($("#assetTypeOne").val()== ""){
                layer.alert('请选择资产大类！');
                return;
            }
            //查询日
            if($("#checkDate").val()== ""){
                layer.alert('请选择查询日！');
                return;
            }
            pledgeBondTotalTbl.table?pledgeBondTotalTbl.reload():pledgeBondTotalTbl.load();
        });


    });

    //重置
    $('#ProductSimulationTrial005_btn_reset').click(function() {
        $("#assetTypeOne").val('').trigger('change');
        $("#assetTypeThree").val('').trigger('change');
        $("#assetTypeFour").val('').trigger('change');
        $("#assetTypeFive").val('').trigger('change');
        $("#oType").val('').trigger('change');
        $("#fcrGradeOut").val('').trigger('change');
        $("#partyType").val('').trigger('change');
        $("#lastMinPeriod").val('');
        $("#lastMaxPeriod").val('');
        $("#checkDate").val('');
        $("#additionalEquity").val('');
    })


}());