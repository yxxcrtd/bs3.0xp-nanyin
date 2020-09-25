(function () {
    var finprodId;
    var initOpen = true;
    var calcConditionObjBackUp;
    var setted = false;
    var ref;

    var getSearchParams = function () {
        return {
            finprodId: finprodId,
            tradeDate: $("#tradeDate").val(),
            isLay: isLay,
            totleTradeId: totleTradeId,
            tradeStatus:'01'
        }
    }

    var loadSubProductTable = function () {
        Joyin.ajax(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce002/findReduceInfoDataNoReduce',
            getSearchParams(), function (rows) {
                subProductTable.clear(function () {
                    if(rows && rows.length>0){
                        //周期型产品展示子产品号
                        if( $("#dealMode").val()=='10'){
                            subProductTable.showColumns([{field:'indexNo', visible: true}]);
                        }else{
                            subProductTable.showColumns([{field:'indexNo', visible: false}]);
                        }
                        subProductTable.addRow(rows)
                    }
                })
            })
    }

    var reduceInfoOption = {
//         ajax: {
//             url: basePath + 'invest/product/productduration/addreduce/FinProductAddReduce002/findReduceInfoData',
//             type: 'POST',
//             data: function () {
//                 return getSearchParams();
//             }
//         },
    		rowno: true,
	        cols: [
	            {field: 'indexNo', title: '子产品号', width: 168,visible: false,sortable: false}
	            ,{field: 'finprodId', title: '产品代码', visible:false,sortable: false}
	            , {field: 'prodStyle', title: '产品款型', width: 168,visible: isLay === "Y",sortable: false}
	            , {field: 'remainPrinAmt', title: '剩余本金', width: 168, sortable: false, type: 'number'}
	            , {field: 'reduceAmt', title: '提前赎回本金', width: 168, sortable: false, type: 'number', edit: true}
//	            , {field: 'vDate', title: '付息开始日', width: 168, sortable: false, edit: true}
//	            , {field: 'mDate', title: '付息截止日', width: 168, sortable: false, edit: true}
//	            , {field: 'interest', title: '应付利息', width: 168, sortable: false, type: 'number'}
	            , {field: 'actInterest', title: '实付客户收益', width: 168, sortable: false, type: 'number', edit: true}
	        ],
	        page: false,
	        checkbox:false,
	        height: 200,
	        searchOnInit: true,
	        editCell: function (value, row, field, cell, column) {
		            // 实付利率 = 实付利息*计息基础/强减本金/（付息截止日-付息开始日）
		            var basis = 365;
		            if (row.basis == basis360) {
		                basis = 360;
		            }
		            var terms = DateUtil.daysBetween(row.vDate, row.mDate);
		            // BSTOT-790 分层产品提前赎回时，点击【生成指令】，报赎回本金未输入或为0 guyunfei 20200120 start
                    var rowReduceAmt = row.reduceAmt;
		            var rowActInterest = row.actInterest;
		            if(rowReduceAmt){
		                //去除字符串中的逗号
                        rowReduceAmt = rowReduceAmt.replace(/,/g, "");
                    }
                    if(rowActInterest){
                        rowActInterest = rowActInterest.replace(/,/g, "");
                    }

		            if (field == 'actInterest' && (rowReduceAmt && JoyinMath.doMinus(0,rowReduceAmt) !=0 )) {
		                var actYield = JoyinMath.doDiv(JoyinMath.doDiv(JoyinMath.doMul(rowActInterest, basis), rowReduceAmt), terms);
		                row.actYield = JoyinMath.doMul(actYield, 100, 2);
		                if(rowActInterest){
		                	row.actInterest = rowActInterest;
		                }
		                subProductTable.renderRow(row.LAY_TABLE_INDEX, row);
		            }
		
		            if (field == 'reduceAmt' && (rowReduceAmt && JoyinMath.doMinus(0,rowReduceAmt) !=0 )) {
		                var actYield = JoyinMath.doDiv(JoyinMath.doDiv(JoyinMath.doMul(rowActInterest, basis), rowReduceAmt), terms);
		                // BSTOT-790 分层产品提前赎回时，点击【生成指令】，报赎回本金未输入或为0 guyunfei 20200120 end
                        row.actYield = JoyinMath.doMul(actYield, 100, 2);
                        if(rowReduceAmt){
		                	row.reduceAmt = rowReduceAmt;
		                }
                        subProductTable.renderRow(row.LAY_TABLE_INDEX, row);
		                // 应付利息 = 强减本金*应付利率*（付息截止日-付息开始日）/计息基础
//		                var interest = JoyinMath.doDiv(JoyinMath.doMul(JoyinMath.doMul(value, JoyinMath.doDiv(row.yield, 100, 4)), terms), basis);
//		                row.interest = interest;
//		                row.actInterest = interest;
//		                var actYield = JoyinMath.doDiv(JoyinMath.doDiv(JoyinMath.doMul(row.actInterest, basis), value), terms);
//		                row.actYield = JoyinMath.doMul(actYield, 100, 2);
//		                subProductTable.renderRow(row.LAY_TABLE_INDEX, row);
		
//		                var rows = productFeeTable.getAllRows();
//		                $.each(rows, function (i, v) {
//		                    v.prinAmt = value;
//		                    resetFeeTable(v);
//		                    productFeeTable.renderRow(v.LAY_TABLE_INDEX, v);
//		                })
		                

		            }
		
		            var allData = subProductTable.getAllRows();
		            var reduceAmt = 0;
                    // BSTOT-790 分层产品提前赎回时，点击【生成指令】，报赎回本金未输入或为0 guyunfei 20200120 start
		            $.each(allData, function (i, v) {
		                var str = v.reduceAmt;
		                if(str){
		                    str = str.replace(/,/g, "");
                        }
		                reduceAmt = JoyinMath.doAdd(reduceAmt, str);
		            })
		            $("#prinAmt").val(reduceAmt).trigger("blur");
		
		            var actInterest = 0;
		            $.each(allData, function (i, v) {
                        var str = v.actInterest;
                        if(str){
                            str = str.replace(/,/g, "");
                        }
		                actInterest = JoyinMath.doAdd(actInterest, str);
		            })
		            $("#actCusProfit").val(actInterest).trigger("blur");
		
		            var interest = 0;
		            $.each(allData, function (i, v) {
                        var str = v.interest;
                        if(str){
                            str = str.replace(/,/g, "");
                        }
		                interest = JoyinMath.doAdd(interest, str);
		            })
                    // BSTOT-790 分层产品提前赎回时，点击【生成指令】，报赎回本金未输入或为0 guyunfei 20200120 end
		            $("#interest").val(interest).trigger("blur");
        },
        done: function (allData) {
//            hideColumns();
            var reduceAmt = 0;
            $.each(allData, function (i, v) {
                reduceAmt = JoyinMath.doAdd(reduceAmt, v.reduceAmt);
            })
            $("#prinAmt").val(reduceAmt).trigger("blur");

            var actInterest = 0;
            $.each(allData, function (i, v) {
                actInterest = JoyinMath.doAdd(actInterest, v.actInterest);
            })
            $("#actCusProfit").val(actInterest).trigger("blur");

            var interest = 0;
            var minVdate = '';
            $.each(allData, function (i, v) {
                if (!minVdate || v.vDate < minVdate) {
                    minVdate = v.vDate;
                }
                interest = JoyinMath.doAdd(interest, v.interest);
            })
            $("#interest").val(interest).trigger("blur");

            // 计息开始日 = min（强减信息中.付息开始日）
            if($('#operationType').val()=='02'){
            	$("#vDate").val(minVdate);
            }

//            if (formTodoList && initOpen) {
//                calcConditionObjBackUp = stlCommandInput();
//            }
        },
        data: []
    }

    if (reduceInfoDtoList && reduceInfoDtoList.length > 0) {
        reduceInfoOption.data = reduceInfoOption.data.concat(reduceInfoDtoList);
    }

    var subProductTable;

    function resetFeeTable(row) {
        var basis = 365;
        if (row.basis == basis360) {
            basis = 360;
        }
        var terms = DateUtil.daysBetween(row.vDate, row.mDate);
        var actYield = JoyinMath.doDiv(JoyinMath.doDiv(JoyinMath.doMul(row.actFeeAmt, basis), row.prinAmt), terms);
        row.actYield = JoyinMath.doMul(actYield, 100, 2);
    }

    var loadProductFeeTable = function () {
        Joyin.ajax(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce002/findProdFeeData',
            getSearchParams(), function (rows) {
                productFeeTable.clear(function () {
                    productFeeTable.addRow(rows)
                })
            })
    }



    var productFeeTable;

    var initTables = function () {
        subProductTable = $("#subProductTable").joyintable(reduceInfoOption);
    }
    /**
     * 定义数据表格
     *
     */
    var settleOption = {
        searchOnInit: true,
        checkbox: false,
        cols: [
            {
                field: 'isNeedSend', width: 140, title: 'BussTrdTransferOrder.isNeedSend', edit: true, sortable: false,
                editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: YES_NO}
            }
            , {field: 'payTypeText', width: 130, title: 'BussTrdTransferOrder.payType', sortable: false, sortable: false}
            , {field: 'settleDateText', width: 130, title: 'BussTrdTransferOrder.settleDate', sortable: false}
            , {field: 'settleAmt', width: 130, title: 'BussTrdTransferOrder.settleAmt', type: 'money', sortable: false}
            , {field: 'ccyText', width: 130, title: 'BussTrdTransferOrder.ccy', sortable: false}
            , {
                field: 'payAcctNo', width: 130, title: 'BussTrdTransferOrder.payAcctNo', edit: true, sortable: false,
                editOption: {
                    type: 'select', multiple: false, searchable: true, showField: 'id', parentIds: 'orderId_select,acctNoList',
                    url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select"), id: 'payAcctNo', postData: function () {
                       // return {selectValueOrder: JSON.stringify(orderAcctMap), type: 'pay'};
                    	//解决账户列表太大页面卡死问题   zhangweiqi 2019-4-11
                    }
                }
            }
            , {field: 'payAcctName', width: 150, title: 'BussTrdTransferOrder.payAcctName', sortable: false}
            , {
                field: 'recAcctNo', width: 130, title: 'BussTrdTransferOrder.recAcctNo', edit: true, sortable: false,
                editOption: {
                    type: 'select', multiple: false, searchable: true, showField: 'id', parentIds: 'orderId_select,acctNoList',
                    url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select"), id: 'recAcctNo', postData: function () {
                       // return {selectValueOrder: JSON.stringify(orderAcctMap), type: 'rec'};
                    	//解决账户列表太大页面卡死问题   zhangweiqi 2019-4-11
                    }
                }
            }
            , {field: 'recAcctName', width: 150, title: 'BussTrdTransferOrder.recAcctName', sortable: false}
            , {field: 'purpose', width: 130, title: 'BussTrdTransferOrder.purpose', sortable: false, edit: true}
            , {field: 'portfolioId', width: 130, title: 'BussTrdTransferOrder.portfolioId', sortable: false}
            , {field: 'portfolioName', width: 150, title: 'BussTrdTransferOrder.portfolioName', sortable: false}
            , {
                field: 'orderId', visible: false, edit: true, width: 0,
                editOption: {
                    type: 'select', id: 'orderId_select', childrenIds: 'recAcctNo,payAcctNo'
                }
            }
            , {field: 'ps', visible: false}
            , {field: 'settleStatus', visible: false}
            , {field: 'isTotalTrade', visible: false}
            , {field: 'finprodType2', visible: false}
        ],
        done: function () {
            $("[data-field='orderId']").hide();
            resetTable();
        },
        page: false,
        data: [],
        editCell: function (value, row, field, cell, column) {
            if (field == 'payAcctNo') {
                $.each(JSON.parse(acctNoList.value), function (i, v) {
                    if (v.id == value) {
                        row.payAcctName = v.text;
                    }
                });
                if (typeof value == "undefined" || value == null || value == "") {
                    row.payAcctName = "";
                }
            } else {
                $.each(JSON.parse(acctNoList.value), function (i, v) {
                    if (v && v.id == value) {
                        row.recAcctName = v.text;
                    }
                });
                if (typeof value == "undefined" || value == null || value == "") {
                    row.recAcctName = "";
                }
            }
            table.renderRow(row.LAY_TABLE_INDEX, row);
        }
    }

    if (bussTrdTransferOrderDtos && bussTrdTransferOrderDtos.length > 0) {
        settleOption.data = settleOption.data.concat(bussTrdTransferOrderDtos);
    }
    var table = $("#bussTrdTransferOrder_table").joyintable(settleOption);

    // 生成指令参数保存
    var orderAcctMap = {};
    var stlCommandInput = function () {
        if (!profitType) {
            changeProdId();
        }
        if (!profitType) {
            Joyin.error("请选择产品代码");
            return;
        }
        if (!$("#ptlId").val()) {
            Joyin.error("未选择产品代码，或产品未挂组合");
            return;
        }
        if (!$("#ps").val()) {
            Joyin.error("请选择交易方向");
            return;
        }
        if (!$("#tradeDate").val()) {
            Joyin.error("请选择确认日");
            return;
        }
        if (!$("#ccy").val()) {
            Joyin.error("请输入币种");
            return;
        }

        var dtos = [];
        if (profitType == profitType02) {
            // BSTOT-790 分层产品提前赎回时，点击【生成指令】，报赎回本金未输入或为0 guyunfei 20200120 start
            //赎回本金 可以为0
            if (!$("#prinAmt").val()) {
                Joyin.error("赎回本金未输入");
                return;
            }
            //实付客户收益 可以为0
            if (!$("#actCusProfit").val()) {
                Joyin.error("实付客户收益未输入");
                return;
            }
            if($("#prinAmt").val()*1 == 0 && $("#actCusProfit").val()*1 == 0){
                Joyin.error("赎回本金和实付客户收益不能同时为0");
                return;
            }
            // BSTOT-790 分层产品提前赎回时，点击【生成指令】，报赎回本金未输入或为0 guyunfei 20200120 start
            var printAmt = $("#prinAmt").val();
            var actCusProfit = $("#actCusProfit").val();
            //交易金额 = 本金+利息
            var tradeAmt = JoyinMath.doAdd(printAmt, actCusProfit);
            dtos.push({
                'isTotalTrade': YES
                , 'bankLendingType': null
                , 'portfolioId': $("#ptlId").val()
                , 'deal': {
                    finprodType2: F16,
                    ps: $("#ps").val(),
                    //modify by wm 指令日期修改为支付日 2019-02-04 start
//                    tradeDate: $("#tradeDate").val(),
                    tradeDate: $("#settleDate").val(),
                    //modify by wm 指令日期修改为支付日 2019-02-04 end
                    finprodId: $("#finprodId").val(),
                    tradeAmt: tradeAmt,
                    ccy: $("#ccy").val()
                }
            })
        }
        return dtos;
    };

    // 检查生成指令参数是否发生变化
    var isCalcConditionChanged = function () {
        // 参照模式，不做check
        if (pageModel === PAGE_MODEL_REFERENCE) {
            return false;
        }
        if (!$("#bussTrdTransferOrder_div").is(":visible")) {
            return false;
        }
        if (calcConditionObjBackUp == null) {
            return false;
        }
        
        if (stlCommandInput != null) {
            var currentCalcConditionObj = stlCommandInput();
            // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
            for (var key in currentCalcConditionObj) {
                for (var key1 in currentCalcConditionObj[key]) {
                    if (typeof currentCalcConditionObj[key][key1] != "object"
                        && currentCalcConditionObj[key][key1] != calcConditionObjBackUp[key][key1]) {
                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                            [labelText, "生成划款指令"]));
                        return true;
                    }
                }
            }
            for (var key in currentCalcConditionObj) {
                for (var key1 in currentCalcConditionObj[key].deal) {
                    var currentDealObj = currentCalcConditionObj[key].deal;
                    var backupDealObj = calcConditionObjBackUp[key].deal;
                    if (typeof currentDealObj[key1] != "object"
                        && currentDealObj[key1] != backupDealObj[key1]) {
                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                            [labelText, "生成划款指令"]));
                        return true;
                    }
                }
            }
        }
    };

    // 生成划款指令
    $("#bussTrdTransferOrder_btn_generate").on("click", function () {
    	
        if (stlCommandInput != null) {
            var data = stlCommandInput();
            if (!data) {
                return;
            }
            // 保存生成指令参数
            calcConditionObjBackUp = data;
            orderAcctMap = {};
            var result = [];
            Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/genOrderCheck'), data, function () {
                Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/generateOrder'), data, function (ret) {
                    if (data.length == ret.data.length) {
                        for (var key in ret.data) {
                            orderAcctMap[ret.data[key].orderId] = {
                                payAcctNoList: ret.data[key].payAcctNoList,
                                recAcctNoList: ret.data[key].recAcctNoList
                            }
                        }
                        table.clear(function () {
                            table.addRow(ret.data)
                        })
                    }
                }, false);
            });
        }
    });

    function loadSubProdInfo() {
        if (!formTodoList && pageModel == PAGE_MODEL_NEW) {
            loadSubProductTable();
        }
    }

    function resetTable() {
        ref = setInterval(function () {
            if ($(".layui-table-view").length > 0) {
                var height = $(window).height();
                $(".layui-table-view").css({height: height / 2 - 92 + 'px'});
                $(".layui-table-view .layui-table-box .layui-table-body").css({height: (height / 2 - 175) + 'px'});
                setted = true;
            }
        }, 300);
        var set = setInterval(function () {
            if (setted) {
                clearInterval(ref);
                clearInterval(set);
            }
        }, 300);
    }

    var changeProdId = function () {
        var data = $("#finprodId").find("option:selected").data("data");
        finprodId = data ? data.id || '' : ''
        $("#prodAbbr").val(data.object ? data.object.shortName || '' : '');
        $("#prodName").val(data.object ? data.object.name || '' : '');
//        $("#saleDepartment").val(data.object ? data.object.saleDepartment || '' : '').trigger("change");
        var value = data.object ? data.object.saleDepartment || '' : '';
        $('#saleDepartment').val(value.split(",")).trigger('change');
        $("#ccy").val(data.object ? data.object.ccy || '' : '').trigger("change");
        $("#ptlId").val(data.object ? data.object.ptlId || '' : '');
        $("#prodRegistCode").val(data.object ? data.object.prodRegistCode || '' : '');
        $("#isLay").val(data.object ? data.object.isLay || '' : '');
        $("#layType").val(data.object ? data.object.layType || '' : '');
        $("#dealMode").val(data.object ? data.object.dealMode || '' : '');
        if (!formTodoList && pageModel == PAGE_MODEL_NEW) {
            $("#entrusterName").val(data.object ? data.object.entrusterName || '' : '');
            $("#entrusterId").val(data.object ? data.object.entrusterId || '' : '');
        }
        $("#operationType").val(data.object ? data.object.operationType || '' : '');
        $("#vDate").val(data.object ? data.object.prdVdate || '' : '').trigger('change');
        $("#prdVdate").val(data.object ? data.object.prdVdate || '' : '');
        $("#prdMdate").val(data.object ? data.object.prdMdate || '' : '');
        profitType = data.object ? data.object.profitType : "";
        isLay = data.object ? data.object.isLay : "";
        layType = data.object ? data.object.layType : "";
        if(isLay=='Y'){
            subProductTable.showColumns([{field:'prodStyle', visible: true}]);
        }else{
            subProductTable.showColumns([{field:'prodStyle', visible: false}]);
        }
        if(finprodId!=null && finprodId!=''){
        	loadSubProdInfo();
        }
//        if (profitType) {
//            if (!formTodoList && pageModel == PAGE_MODEL_NEW) {
////                resetNetValue();
////                changeAmount();
//            }
//            $(".net-product, .fund-product, .income-product").hide();
//            if (profitType == profitType01) {
////                $("#ps").prop("disabled", false).trigger("change")
//                $(".net-product").show();
//                resetTable();
//            } else if (profitType == profitType02) {
////                $("#ps").val(C6).prop("disabled", true).trigger("change")
//                $(".income-product").show();
//                resetTable();
//
//                if ($("#tradeDate").val()) {
//                    loadSubProdInfo();
//                    if (!formTodoList && pageModel == PAGE_MODEL_NEW) {
//                        loadProductFeeTable();
//                    }
//                }
//
//            } else if (profitType == profitType03) {
////                $("#ps").prop("disabled", false).trigger("change")
//                $(".fund-product").show();
//                resetTable();
//            }
//        }
    }

//    var resetNetValue = function () {
//        if (profitType == profitType01) {
//            var cpriceDate = $("#cpriceDate").val();
//            if (!cpriceDate || !$("#finprodId").val()) {
//                $("#unitCprice").val("");
//            } else {
//                var data = $("#finprodId").find("option:selected").data("data");
//                Joyin.ajax(getValueByDate, {
//                    valueDate: cpriceDate, prodId: $("#finprodId").val(),
//                    unitvalueDec: data.object ? data.object.unitvalueDec || '' : '',
//                    unitvalueMode: data.object ? data.object.unitvalueMode || '' : ''
//                }, function (result) {
//                    if (result) {
//                        $("#unitCprice").attr('data-textrange', data.object.unitvalueDec || 2)
//                            .val(result).trigger("blur");
//                        changeAmount();
//                    } else {
//                        $("#unitCprice").val('').trigger("blur");
//                    }
//                })
//            }
//        } else if (profitType == profitType03) {
//        }
//    }

    var changeAmount = function () {
        if (profitType == profitType01) {
            var tradeAmt = $("#prinAmt").val();
            var unitCprice = $("#unitCprice").val();
            if (!tradeAmt || !unitCprice || unitCprice == 0) {
                $("#shareAmt").val('').trigger("blur");
            } else if (unitCprice && unitCprice > 0 && prinAmt) {
                $("#shareAmt").val(JoyinMath.doDiv(prinAmt, unitCprice, 2)).trigger("blur");
            }
        } else if (profitType == profitType03) {
            $("#shareAmt").val($("#prinAmt").val()).trigger("blur");
        }
    }

    var changeCfmDate = function () {
        var tradeDate = $("#tradeDate").val();
        if (tradeDate) {
//            var cpriceDate = new Date(tradeDate).DateAdd('d', -1).format('yyyy-MM-dd');
//            $('#cpriceDate').val(cpriceDate).trigger("change");
            if ($("#finprodId").val() && profitType == profitType02) {
                // 计息结束日 = 画面.确认日
                $("#mDate").val(tradeDate);
                loadSubProdInfo();
//                loadProductFeeTable();
                resetTable();
            }
        }
    }

    var changeSaleDepartmentId = function () {
        if (!$("#saleDepartment").val()) {
            return;
        }
        Joyin.ajax(getSaleDepartment, {search: $("#saleDepartment").val()}, function (data) {
            if (data && data.data) {
                $("#saleDepartment").val(data.data);
            }
        })
    }

    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        if (isCalcConditionChanged()) {
            return;
        }
        var data = $("#finprodId").find("option:selected").data("data");
        var object = $.serializeToObject('#informationForm',true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.profitType = data.object ? data.object.profitType : "";
        object.capiIncomeFeature = data.object ? data.object.capiIncomeFeature : "";
        object.actmdate = data.object ? data.object.actmdate : "";
        object.vdate = data.object ? data.object.vdate : "";
        var reduceInfoDtoList = subProductTable.getAllRows();
        object.reduceInfoDtoList = reduceInfoDtoList;
        
        if (object.saleDepartment){
        	object.saleDepartment = object.saleDepartment.join(',');
    	}
        if(operateType === 'revoke') {//撤销
        	object.effectStatus = '01';
        	object.totleTradeId = object.bussTrdTransferOrder[0].tradeId;
        	object.busiCode = totleTradeId;
        	object.elementId = "FinProductAddReduce002_btn_revoke";
        	object.tradeAmt = object.bussTrdTransferOrder[0].settleAmt;
        	delete object.bussTrdTransferOrder;
        	delete object.reduceInfoDtoList;
        	Joyin.ajax(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce001/revokeCheck', object, function (result) {
        		Joyin.confirm(result.data, function() {
        			WorkFlow.doRevoke(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce001/revoke', object, "FinProductAddReduce001_btn_revoke","FinProductAddReduce002");
        			
        		});
        	});
        }else{
        	//在代办提交时，当totalTradeId不为空时，赋值object
        	if(totleTradeId){
            	object.totleTradeId = totleTradeId;
        	}
        	Joyin.ajaxJson(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce002/check', object, function (result) {
        		Joyin.confirm(result.data, function () {
        			if (operateType === 'save') {
        				WorkFlow.doSaveList(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce002/save', object, "FinProductAddReduce002_btn_save");
        			} else {
        				WorkFlow.doSubmitList(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce002/save', object, "FinProductAddReduce002_btn_submit");
        			}
        		});
        	});
        }
    }

    var hideColumns = function () {
        var cell = ".laytable-cell-3-";
        var columns = [];
        if (isLay == YES) {
            // var cols = [
            //     {field: 'indexNo', visible: true}
            //     , {field: 'remainPrinAmt', visible: true}
            //     , {field: 'reduceAmt', visible: true}
            //     , {field: 'vDate', visible: true}
            //     , {field: 'mDate', visible: true}
            //     , {field: 'yield', visible: true}
            //     , {field: 'interest', visible: true}
            //     , {field: 'actYield', visible: true}
            //     , {field: 'actInterest', visible: true}
            // ];
            if (layType.indexOf(CLASSIFICATION_TYPE_01) < 0) {
                columns.push(cell + "amtMin");
                columns.push(cell + "amtMax");
                columns.push("th[data-field='amtMin']");
                columns.push("th[data-field='amtMax']");
                columns.push("td[data-field='amtMin']");
                columns.push("td[data-field='amtMax']");
            }

            if (layType.indexOf(CLASSIFICATION_TYPE_02) < 0) {
                columns.push(cell + "termMax");
                columns.push(cell + "termMin");
                columns.push("th[data-field='termMax']");
                columns.push("th[data-field='termMin']");
                columns.push("td[data-field='termMax']");
                columns.push("td[data-field='termMin']");
            }

            if (layType.indexOf(CLASSIFICATION_TYPE_03) < 0) {
                columns.push(cell + "customLevel");
                columns.push("th[data-field='customLevel']");
                columns.push("td[data-field='customLevel']");
            }
        } else {
            columns.push(cell + "amtMin");
            columns.push(cell + "amtMax");
            columns.push(cell + "termMax");
            columns.push(cell + "termMin");
            columns.push(cell + "customLevel");
            columns.push("th[data-field='amtMin']");
            columns.push("th[data-field='amtMax']");
            columns.push("th[data-field='termMax']");
            columns.push("th[data-field='termMin']");
            columns.push("th[data-field='customLevel']");
            columns.push("td[data-field='amtMin']");
            columns.push("td[data-field='amtMax']");
            columns.push("td[data-field='termMax']");
            columns.push("td[data-field='termMin']");
            columns.push("td[data-field='customLevel']");
        }
        $(columns.join(',')).hide();
    }

    var init = function () {
//        if (!formTodoList && pageModel == PAGE_MODEL_NEW) {
//            changeCfmDate();
//        }
//        initTables();
//        changeProdId();
        initTables();
    }
    
    var changeSdefault = function(){
    	var sdf = $("#sdefault").val();
    	if(sdf=='Y'){
    		$('#FinProductAddReduce002_sdefaultAmt').show();
    		$('#ps').val('C18').trigger("change");
    	}else{
    		$('#FinProductAddReduce002_sdefaultAmt').hide();
    		$('#ps').val('C17').trigger("change");
    	}
    }

    /**
     * 画面加载完成
     */
    $(function () {
    	 var operateType = $('#operateType').val();
         if("revoke"==operateType || "delete"==operateType){
             $('#FinProductAddReduce002_btn_revoke').show();
         }else{
             $('#FinProductAddReduce002_btn_revoke').hide();
         }
    	$('#finprodId').change(function(){
    		changeProdId();
    	});
    	$('#prinAmt').change(function(){
    		changeAmount();
    	});
    	$('#tradeDate').change(function(){
    		changeCfmDate();
    	});
    	$('#sdefault').change(function(){
    		changeSdefault();
    	});
    	
        $('#FinProductAddReduce002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#FinProductAddReduce002_btn_revoke').click(function () {
            saveOrSubmit('revoke');
        });
        $('#FinProductAddReduce002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

//        var data = $("#finprodId").find("option:selected").data("data");
//        $("#prodAbbr").val(data.object ? data.object.shortName || '' : '');
//        $("#prodName").val(data.object ? data.object.name || '' : '');
//        $("#saleDepartment").val(data.object ? data.object.saleDepartment || '' : '');

//        if ($("#saleDepartment").val()) {
//            changeSaleDepartmentId();
//        }
        changeSdefault();
        init();
    });
}());

