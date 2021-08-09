/**
 * 债券投资委托新建
 */
(function () {
    //试算结果
    var riskResultdata;
    var cluflag;

    var initFlag = true;
    var pageModel = PAGE_MODEL_NEW;

    var doChange = {};
    function query(ele){
        return $("#EtribBondInfo002_bodydiv " + ele);
    }
    
    doChange.reloadLayui = function(){
        layui.form.render();
    }
    doChange.exerYield_setDisabled = function(isDisabled){
        // 行权收益率
        let exerYield = query('#exerYield');
        if(isDisabled){
            exerYield.val('').attr('disabled', true).removeAttr("required");
            query('#calculateField_exerYield').prop('checked', false).attr('disabled', true);
        }else{
            if(query("#isRight").val() !== N && query("#priceModel").val() !== PRICEMODEL_03){
                // 含权债或为空 且 价格模式不是市价
                exerYield.val('').attr('disabled', false).trigger("change").trigger("blur");
                query('#calculateField_exerYield').val('').attr('disabled', false);
            }
        }
        return exerYield;
    };
    
    var getriskdata = function () {
        var obj = {
            tradeDirection: query("#ps").val(),
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: sysDate,
            portfolioCode: query("#portfolioId").val(),
            tradeAmount: query("#entrustNum").val(),
            assetType: fintype,
            isEnabled: "01",
            assetCode: query("#finprodIdhid").val()
        };
        if (query("#ps").val() === T03 || query("#ps").val() === T39) {
            obj.counterId=query('#counterId2').val();
        }else{
            obj.counterId=query('#counterId1').val();
        }
        var object = {
            tradeDirection: ["ps", "EtrIbBondinfo002.ps"],
            portfolioCode: ["portfolioId", "EtrIbBondinfo002.portfolioId"],
            tradeAmount: ["entrustNum", "EtrIbBondinfo002.entrustAmt"],
            assetType: ["assetType1", "EtrIbBondinfo002.bondType"],
            data: obj
        };
        object.counterId = ["counterId", "EtrIbBondinfo002.counterId"];
        return object;
    };

    /**
     * 获取风险试算参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSearchParams() {
        var object = $.serializeToObject("#searchForm");
        object.entrustNum = object.entrustNum * 10000; //页面单位是万元
        return object;
    }


    function setCashPosition(result) {
        
    }
    /**
     * 获取表单参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSaveOrSubmitParams(operateType) {
        if (query("#priceModel").val() == "PRM02") {
            //限价指令
            if (query("#isRight").val() == "Y") {
                //为含权债的时候
                if (!query("#price").val() && !query("#matureYield").val() && !query("#exerYield").val()) {
                    Joyin.warn("含权债时净价、到期收益率、行权收益率三选一必填");
                    return false;
                }
            } else {
                if (!query("#price").val() && !query("#matureYield").val()) {
                    Joyin.warn("净价、到期收益率二选一必填");
                    return false;
                }
            }
        }
        
        //证券账户
        if (query("#chlFinprodId").val() != null && query("#chlFinprodId").val() != '') {
            query("#secManageAcctId").val('');
        } else {
            query("#secManageAcctId").val('999999');
        }
        if (!query("#secManageAcctId").val() && !query("#chlFinprodId").val()) {
            Joyin.warn("证券账户和通道不能同时为空！");
            return false;
        }
        var object = $.serializeToObject("#searchForm");
        if ($("#select2-finprodId-container").attr("title")) {
            object.finprodName = $("#select2-finprodId-container").attr("title");
        }

        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = query("#entrustId").val();
        object.entrustStatus = query("#entrustStatus").val();
        object.ccy = query("#ccy").val();
        object.tradeMarket = query("#tradeMarket").val();
        object.finvdate = query("#finvdate").val();
        object.finmdate = query("#finmdate").val();
        //提交获取计算方式单选框的值
        object.calculateField = query("input[name=calculateField]:checked").val();

        if (query("#ps").val() == T03 || query("#ps").val() == T39) {
            object.counterType=query('#counterId2').val();
        }else{
            object.counterType=query('#counterId1').val();
        }
        object.counterId=object.counterType;
        // object.counterType = query('#counterId').select2('data')[0].object;  //交易对手类型，校验时用到
        return object;
    }

    //到期日小于委托结束日，给出提示
    var checkFinMdate = function () {
        if (query("#finprodId").val()) {
            var finMdate = new Date(query("#finprodId").select2("data")[0].object.finmdate).format("yyyy-MM-dd");
            if (new Date(finMdate).DateDiff("d", query("#mdate").val()) > 0) {
                Joyin.error(JoyinUtil.getMessage("ETR_X_007"));
                query("#finprodId").val("").trigger("change");
            }
        }
    };

    /**
     * 净值偏离度
     */
    function calDeviationDegree() {
        document.getElementById('deviationDegree').innerHTML = "<span style='color: red'>"+
            '0.0000%' +"</span>";
        if(!query("#price").val()||!query("#finprodId").val()||!query('#vdate').val()){
            return;
        }
        Joyin.ajaxJson(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/calDeviationDegree",
            {
                price: query("#price").val(),
                finprodId: query("#finprodId").val(),
                vdate:query('#vdate').val()
            }, function (result) {
            if (result ) {
                document.getElementById('deviationDegree').innerHTML ="<span style='color: red'>"+
                    JoyinUtil.formatNumber(result, 4, null, 2) + '%' +"</span>";
            }
        });
    }

    //净价、行权收益率、到期收益率联动
    var calData = function (calflag) {
        if (!initFlag) {
            let finvdate = query("#vdate").val();
            let finmdate = query("#mdate").val();
            if(finvdate !=='' && finvdate !== finmdate){
                // 只有指令开始日=指令结束日时才联动
                return;
            }
            
            var ps = query("#ps").val();
            if (!ps) {
                return;
            }
            if (!query("#priceModel").val() || !(query("#priceModel").val() == "PRM01" || query("#priceModel").val() == "PRM02")) {
                return;
            }
            if (!calflag) {
                return;
            }
            if (!query("#finprodId").val()) {
                return;
            }
            var object = $.serializeToObject("#searchForm");

            //连动时不需要券面总额，传给后台一个默认值
            if (!object.entrustNum) {
                object.entrustNum = 100;
            }
            // 根据净价计算可买数量
            var portfolioIds = [];
            portfolioIds.push(query("#portfolioId").val());
            parent.EtrIbBondinfoHq.calCanBuyAmount(portfolioIds,query('#ccy').val(),query('#vdate').val(),query('#price').val());
            // 根据净价计算净价偏离度
            calDeviationDegree();
            //只有选中单选按钮的值才进行联动计算  add by yzg 20210622 start
            var calculateField =  query("input[name=calculateField]:checked").val();
            if(calflag != calculateField){
            	return;
            }
            //只有选中单选按钮的值才进行联动计算  add by yzg 20210622 end
            object.calflag = calflag;
            object.pageModel = pageModel;
            object.entrustId = query("#entrustId").val();
            object.entrustStatus = query("#entrustStatus").val();
            object.finvdate = query("#finvdate").val();
            object.finmdate = query("#finmdate").val();
            object.finprodId = query("#finprodIdhid").val();
            object.tradeMarketMode = SHARES_TRADE_MARKET_02;
            if (object.bussCombDistribtList != null) {
                if (object.bussCombDistribtList.length > 0) {
                    var entrustNum = 0.0;
                    for (var index in object.bussCombDistribtList) {
                        var item = object.bussCombDistribtList[index];
                        if (item.entrustNum == null || item.entrustNum == "") return;
                        entrustNum += parseFloat(item.entrustNum);
                    }
                    object.entrustNum = String(entrustNum);
                } else {
                    return;
                }
            }

            Joyin.ajaxJson(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/calData", object, function (result) {
                if (result && result.data) {
                    var data = result.data;
                    if (data.price) {
                        query("#price").val(data.price).trigger("blur");
                        query("#fpriceAmt").val(data.unitFprice).trigger("blur");
                    } else {
                        query("#price").val("");
                        query("#fpriceAmt").val("");
                    }
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        query("#exerYield").val(data.exerYield).trigger("blur");
                        query("#matureYield").val(data.matureYield).trigger("blur");
                    }
                }
            });
        }
    };
    /**
     * 画星星
     */
    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
            var $el = element.prev();
            if (!$el.is("label") && level > 0) {
                $el = $el.find("label");
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== "undefined" && pageModel !== PAGE_MODEL_REFERENCE) {
            $(".must").each(function () {
                $(this).remove();
            });
            $("*[required]").each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find("> .must").length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    };
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            getLeftZHinfo();
            // 代办事项中 行权收益率置灰问题
            let exerYield = query('#exerYield');
            exerYield.attr('disabled', true).removeAttr("required");
            query('#calculateField_exerYield').attr('disabled', true);
            if(query("#isRight").val() === Y && query("#priceModel").val() !== PRICEMODEL_03){
                // 含权债或为空 且 价格模式不是市价
                exerYield.attr('disabled', false).removeAttr("required").trigger("change").trigger("blur");
                query('#calculateField_exerYield').val('').attr('disabled', false);
            }
            calDeviationDegree();
        }
    };

    /**
     * 初始化
     */
    var init = function () {
        if (todoList == false) {
            query('#EtrIbBondinfo002_btn_fileupload').css('display', 'none');
            query('#EtrIbBondinfo002_btn_riskcal').css('display', 'none');
        }else {
            if (query('#priceModel').val() == PRICEMODEL_03) {
                // 净价置灰不可填
                query('#price').val('').attr('disabled', true);
                query('#price').removeAttr("required");
                // 全价置灰不可填
                query('#fpriceAmt').val('').attr('disabled', true);

                // 到期收益率
                query('#matureYield').val('').attr('disabled', true);

                // 行权收益率
                doChange.exerYield_setDisabled(true);

            } else {
                // 净价必填
                query('#price').attr('disabled', false);
                query('#price').attr("required", "true");
                // 全价 可填
                query('#fpriceAmt').attr('disabled', false);

                query('#matureYield').attr('disabled', false);
                
                // 行权收益率
                query('#exerYield').attr('disabled', false);
                
            }
        }

        businessId = query('#businessId').val()
        if(calculateField){
            query("#calculateField_" + calculateField).attr("checked", true);
        }
        query("#ps").change();
        if (query("#finprodId").val() != null && query("#finprodId").val() != "") {
            query("#finprodId").change();
        }
        query("#portfolioId").change();
        query("#priceModel").trigger("change");
        
        initFlag = false;
        // 画星星
        star();
        //
        if (typeof shortSellingSupported !== "undefined" && shortSellingSupported == "02") {
            query(".shortSellingSupported").hide();
        }
    };

    /**
     * 风险试算
     */
    function doRiskcal() {
        var object = $.serializeToObject("#searchForm");
        if (object) {
            var riskdata = getriskdata(object);
            var riskInterfUrl = basePath + "riskrds/interf/doInfData/0/RSK002/F01";
            if (riskdata && riskInterfUrl) {
                window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object, riskdata, riskMathRedisCallback);
            }
        }
    } 
    /**
     * 风险试算的回调函数
     */
     function riskMathRedisCallback(object, cluFlag) {
        var data = {};
        EtrIbBondinfo002.riskResultdata = object;
        EtrIbBondinfo002.cluflag = cluFlag;  
        Joyin.dialog(JoyinUtil.getLabel("EtrIbBondinfoRiskcal002"), "/invest/entrust/etribbondinfo/EtrIbBondinfoRiskcal002/init", "60%", "75%", null);
    } 

    /**
     * 风险试算时不需要进行风险试算的校验
     */
    var isNeedRiskcal = function (){
        //质押券入库和出库不需要走风险试算
        ps = query("#ps").val();
        return true;
    }

    /**
     * 风险试算时不需要进行风险试算的校验
     */
    var getRiskInterfUrl = function (){
        var riskInterfUrl = basePath + "riskrds/interf/doInfData/0/RSK002/F01";
        return riskInterfUrl;
    }

    /**
     * 上传附件
     */
    var fileUpload = function () {
        var params = $.serializeToObject('#searchForm');
        var object = {
            businessId: params.businessId
        }
        Joyin.dialog(JoyinUtil.getLabel('EtrIbBondinfo002.upload'), '/invest/entrust/etribbondinfo/EtrIbBondinfoFile/init', '60%', '75%',object);
    }

    /**
     * 双击持仓自动填充表单
     */
    var dbclickRefresh002 = function (data) {
        setEntrust002PageItems(data);
        //双击校验后，将风险试算标识重置
        window.parent.dbclickRefreshRisk();
    };

    /**
     * 根据传入的数据，设置画面元素
     */
    var setEntrust002PageItems = function (param) {
        setPageItemsFlag = true;
        if (param) {
            if (param.portfolioId) {
                query("#portfolioId").textSelect('setValue',param.portfolioId);
            }
            if (param.finprodMarketId) {
                query("#finprodId").textSelect('setValue',param.finprodMarketId);
            }
            if (param.avaShareAmt) {
                query("#entrustNum").val(param.avaShareAmt).trigger("change");
            }
            if (param.channelId) {
                query("#chlFinprodId").val(param.channelId).trigger("change");//通道
            }
            query("#ps").textSelect('setValue',"T02");
            query("#priceModel").textSelect('setValue',"PRM02");
            query("#vdate").val(sysDate).trigger("change");
            query("#mdate").val(sysDate).trigger("change");

            if (param.price){
                //净价
                query("#calculateField_price").prop('checked', true);
                query("#price").val(param.price).trigger("change");
                layui.form.render();
            }
        }
    };

     /**
     * 双击指令管理
     */
    var dbclickEtr = function (data) {
        setEntrust(data);
        //双击校验后，将风险试算标识重置
        window.parent.dbclickRefreshRisk();
    };

     /**
     * 根据传入的数据，设置画面元素
     */
    var setEntrust = function (param) {
        setPageItemsFlag = true;
        if (param) {
            if (param.entrustId) {
                query("#entrustId").val(param.entrustId).trigger("change");//委托代码
            }
            if (param.entrustStatus) {
                query("#entrustStatus").val(param.entrustStatus).trigger("change");//委托状态
            }
            if (param.portfolioId) {
                query("#portfolioId").textSelect('setValue',param.portfolioId);//组合代码
            }
            if (param.finprodMarketId) {
                query("#finprodId").textSelect('setValue',param.finprodMarketId);//资产代码
            }
            if (param.ps) {
                query("#ps").textSelect('setValue',param.ps);//交易方向
            }
            if (param.shareNum) {
                query("#entrustNum").val(param.shareNum).trigger("change");//券面总额
            }
            if (param.settleSpreed) {
                if(param.settleSpreed === "0"){
                    query("#settleSpreed").textSelect('setValue',"01");
                }else if(param.settleSpreed === "1"){
                    query("#settleSpreed").textSelect('setValue',"02");
                }
            }
            if (param.priceModel) {
                query("#priceModel").val(param.priceModel).trigger("change");//价格模式
            }
            // if (param.unitPrice) {
            //     query("#price").val(param.unitPrice).trigger("change");//净价
            // }
            // if (param.allPrice) {
            //     query("#fpriceAmt").val(param.allPrice).trigger("change");//全价
            // }
            // if (param.matureYield) {
            //     query("#matureYield").val(param.matureYield).trigger("change");//到期收益率
            // }
            // if (param.exerYield) {
            //     query("#exerYield").val(param.exerYield).trigger("change");//行权收益率
            // }
            /*             if (param.vdate) {
                query("#vdate").val(param.vdate).trigger("change");//成立日
            }
            if (param.mdate) {
                query("#mdate").val(param.mdate).trigger("change");//终止日
            } */
            if (param.remark) {
                query("#remark").val(param.remark).trigger("change");//备注
            }
            if (param.counterId) { // 交易对手
                query("#counterId1").val(param.counterId).trigger("change");
                query("#counterId2").val(param.counterId).trigger("change");
            }
            if(param.invAim){
                query("#invAim").val(param.invAim).trigger("change");//投资目的
            }

            if (param.unitPrice){
                //净价
                query("#calculateField_price").prop('checked', true);
                query("#price").val(param.unitPrice).trigger("change");
                layui.form.render();
            }
            pageModel = PAGE_MODEL_EDIT;
        }
    };

    //查询组合可用现金,资产可用券面总额
    var getPtlCashPositionInfo = function (rs) {
        // if (!query('#portfolioId').val()) {
        //     return;
        // }
        // var param = {};
        // param.ccy = query('#ccy').val();
        // param.vdate = query('#vdate').val();
        // param.finprodId = query("#finprodIdhid").val();
        // param.portfolioId = query("#portfolioId").val();
        //
        // Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtrIbBondinfo001/findPositionInfo', param, function (result) {
        //     if (result) {
        //         query("#proAvailableCash").val(result.zhkyxj_t);
        //         query("#assetAvailablePosition").val(result.zcky_qmze);
        //     }
        // });
        if(rs){ 
            // 避免重复请求头寸信息
            query("#proAvailableCash").val(rs.zhkyxj_t);
            query("#assetAvailablePosition").val(rs.zcky_qmze);
        }
    }

    /**
     * 左下行情信息联动
     */
    var getLeftMarketInfo = function () {
        var chlFinprodId = query("#chlFinprodId").val();
        var finprodIdhid = query("#finprodIdhid").val();
        var portfolioId = query("#portfolioId").val();
        var ccy = query('#ccy').val();
        var sysDateT1 = query('#vdate').val();
		var calId = query('#calendarId').val();
        if (todoList == false) {
            var rs = parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId,ccy,sysDateT1,calId);
            getPtlCashPositionInfo(rs);
        }
    }

    var getLeftZHinfo = function () {
        var chlFinprodId = query("#chlFinprodId").val();
        var finprodIdhid = query("#finprodIdhid").val();
        var portfolioId = query("#portfolioId").val();
        var ccy = query('#ccy').val();
        var vdate = query('#vdate').val();
        var calId = query('#calendarId').val();
        if (todoList == false) {
            var rs = parent.EtrIbBondinfoHq.queryPositionInfo(finprodIdhid,portfolioId,chlFinprodId,ccy,vdate,calId);
            getPtlCashPositionInfo(rs);
        }
        window.EtrInitCash.queryCash(ccy,vdate,portfolioId);
    }
    /**
     * 取消,清空指令数据
     */
    var doReset = function () {
        query("#portfolioId").val("").trigger("change");
        query("#finprodId").val("").trigger("change");
        query("#ps").val("").trigger("change");
        query("#entrustNum").val("").trigger("change");
        query("#settleSpreed").val("").trigger("change");
        query("#priceModel").val("").trigger("change");
        query("#price").val("").trigger("change");
        query("#fpriceAmt").val("").trigger("change");
        query("#matureYield").val("").trigger("change");
        query("#exerYield").val("").trigger("change");
        query("#counterId").val("").trigger("change");
        query("#chlFinprodId").val("").trigger("change");
        query("#remark").val("").trigger("change");
        query("#entrustId").val("").trigger("change");
        query("#entrustStatus").val("").trigger("change");
        query("#invAim").val("").trigger("change");
        query("#calculateField_price").prop('checked', false);
        query("#calculateField_exerYield").prop('checked', false);
        query("#calculateField_matureYield").prop('checked', false);
        query("#vdate").val(sysDate).trigger("change");
        query("#mdate").val(sysDate).trigger("change");
        query("#counterId1").val("").trigger("change");
        query("#counterId2").val("").trigger("change");
        document.getElementById('deviationDegree').innerHTML = '';
        layui.form.render(); // reload layui
        pageModel = PAGE_MODEL_NEW;
    };
    
    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var s;
        if (BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if (!s && s != "") {
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())) {
                return;
            }
        }
        var object = getSaveOrSubmitParams(operateType);


        if(!object){
            return;
        }

        Joyin.ajaxJson(basePath + "invest/entrust/EtrCommonController/check", object, function (result1) {
            Joyin.ajaxJson(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/check", object, function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === "save") {
                        WorkFlow.doSaveList(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/save", object, "EtrIbBondinfoMain_btn_save", "EtrIbBondinfo002", function () {
                            JoyinUtil.disableButton("EtrIbBondinfoMain_btn_save", false);
                            doReset();
                        });
                    } else {
                        WorkFlow.doSubmitList(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/save", object, "EtrIbBondinfoMain_btn_submit", "EtrIbBondinfo002", function () {
                            JoyinUtil.disableButton("EtrIbBondinfoMain_btn_submit", false);
                            doReset();
                        });
                    }
                });
            });
        });
    }

    /**
     * 对外公开的接口
     */
    parent.EtrIbBondinfo002 = {
        dbclickRefresh002: dbclickRefresh002,
        dbclickEtr: dbclickEtr,
        doReset: doReset,
        getriskdata: getriskdata,
        getSearchParams: getSearchParams,
        isNeedRiskcal: isNeedRiskcal,
        getRiskInterfUrl: getRiskInterfUrl,
        getSaveOrSubmitParams:  getSaveOrSubmitParams,
        setCashPosition:setCashPosition,

        riskResultdata: riskResultdata,
        cluflag: cluflag
    };

    EtrIbBondinfo002 = {
        riskResultdata: riskResultdata,
        cluflag: cluflag
    };

    /**
     * 绑定事件
     */
    $(function () {
        document.getElementById('deviationDegree').innerHTML = "<span style='color: red'>"+
            '0.0000%' +"</span>";

        // 绑定提交按钮
        $('#EtrIbBondinfo002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

        // 绑定保存按钮
        $('#EtrIbBondinfo002_btn_save').click(function () {
            saveOrSubmit('save');
        });

        // 绑定取消按钮
        $('#EtrIbBondinfo002_btn_reset').click(function () {
            doReset();
        });

        // 绑定风险按钮
        $('#EtrIbBondinfo002_btn_riskcal').click(function () {
            doRiskcal();
        });

        $('#EtrIbBondinfo002_btn_fileupload').click(function () {
            fileUpload();
        })
        
        /**
         * 初始化
         */
        init();

        /**
         * 交易方向
         */
        query("#ps").change(function () {
            var finprodIdhid = query("#finprodIdhid").val();
            if (query("#ps").val() == T01) {
                doChange.exerYield_setDisabled(false);
                query("#matureYield").removeAttr("disabled");
                
                // 当交易方向为买入卖出时，交易对手下拉框显示为银行间机构信息中的交易账户21位码+交易成员全称
                query("#div_counterId1").css("display","block");
                query("#div_counterId2").css("display","none");
            } else if (query("#ps").val() == T02) {
                doChange.exerYield_setDisabled(false);
                query("#matureYield").attr("disabled", true);
                query("#matureYield").val("");
                
                // 当交易方向为买入卖出时，交易对手下拉框显示为银行间机构信息中的交易账户21位码+交易成员全称
                query("#div_counterId1").css("display","block");
                query("#div_counterId2").css("display","none");
            } else if (query("#ps").val() == T03 || query("#ps").val() == T39) {
                doChange.exerYield_setDisabled(true);
                
                query("#matureYield").attr("disabled", true);
                query("#matureYield").val("");
                
                // 当交易方向为行权回售时，可选项为市场机构
                query("#div_counterId1").css("display","none");
                query("#div_counterId2").css("display","block");
            }
            if (pageModel != PAGE_MODEL_REFERENCE) {
                if (query("#ps").val() == T03) {
                    //交易方向选择“行权-回售”时，将“价格模式”字段默认为“指定价格”，且价格默认为 债券行权价 ；
                    query("#priceModel").val("PRM01").trigger("change");
                    if (query("#finprodId").val()) {
                        Joyin.ajaxJson(
                            basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/getExercisePricebyPara",
                            {
                                finprodId: finprodIdhid,
                                ps: "T03"
                            },
                            function (result) {
                                query("#price").val(result);
                            },
                            false
                        );
                    }
                } else {
                    query("#priceModel").val("").trigger("change");
                    query("#price").val("");
                }
            }
            if (pageModel != PAGE_MODEL_REFERENCE) {
                if (query("#price").val()) {
                    calData("price");
                }
            }
            // 画星星
            star();
            
            //【银行间指令】债券二级-当交易方向为“行权回售”时-清算速度请默认T+0,且不可修改
            if (query("#ps").val() == T03) {
            	query('#settleSpreed').textSelect('setValue',"01");
            	query('#settleSpreed').attr('disabled', true);
            } else {
            	query('#settleSpreed').textSelect('setValue',"");
            	query('#settleSpreed').attr('disabled', false);
            }
        });

        /**
         * 资产代码
         */
        query("#finprodId").change(function () {
            var portfolioId = query("#portfolioId").val();
            var finprodId = query("#finprodId").val();
            if (finprodId) {
                var data = query("#finprodId").select2("data")[0].object;
                if (!data.finprodId) {
                    //自动加券
                    data = AutoCoupon.autoBondTradeCoupon(query("#finprodId").val());
                }
                // 不为空
                //var data = query('#finprodId').select2('data')[0].object;
                query("#finprodIdhid").val(data.finprodId).trigger("change");
                query("#finprodName").val(finprodId).trigger("change");
                query("#isRight").val(data.isRight).trigger("change");
                // // 资产为非含权债时，置灰“行权收益率”字段
                if(data.isRight === N){
                    doChange.exerYield_setDisabled(true);
                }else{
                    doChange.exerYield_setDisabled(false);
                }
                query("#calendarId").val(data.calendarId).trigger("change");
                query("#finvdate").val(new Date(data.finvdate)).trigger("change");
                query("#finmdate").val(new Date(data.finmdate)).trigger("change");
                query("#ccy").val(data.ccy).trigger("change").attr("disabled", true);
                checkFinMdate();
                if (query("#price").val()) {
                    calData("price");
                }
            } else {
                // 为空
                query("#finprodIdhid").val("").trigger("change");
                query("#finprodName").val("").trigger("change");
                query("#calendarId").val("").trigger("change");
                query("#ccy").val(CCY).trigger("change").attr("disabled", false);
                query("#isRight").val("").trigger("change");
                query("#matureYield").val("").trigger("change").trigger("blur");
                
                doChange.exerYield_setDisabled(false);
            }

            //左下行情信息联动 & 组合可用现金,资产可用持仓
            getLeftMarketInfo();

            //组合可用现金,资产可用持仓
            // getPtlCashPositionInfo();

            // 画星星
            star();
        });

        /**
         * 组合代码
         */
        query("#portfolioId").change(function () {
            var portfolioId = query("#portfolioId").val();
            
            //左下行情信息联动 & 组合可用现金,资产可用持仓
            // getLeftMarketInfo();
            getLeftZHinfo();
            
            //组合可用现金,资产可用持仓
            // getPtlCashPositionInfo();
        });

        $.fn.updateChosenArr = function updateChosenArr(obj) {
            var $this = $(this),
                arr = obj.dataResult || JSON.parse($(this).attr("data-result") ? $(this).attr("data-result") : "[]"),
                item = obj.item;
            $this.empty();
            $this.append($("<option value>"));
            for (var i in arr) {
                if (!arr.hasOwnProperty(i)) continue;
                if (obj.item && obj.item.length > 0) {
                    if ($.inArray(arr[i].id, item) >= 0) $("<option>").attr("value", arr[i].id).text(arr[i].text).appendTo($this);
                } else $("<option>").attr("value", arr[i].id).text(arr[i].text).appendTo($this);
            }
            if (obj.chosen) $this.val(obj.chosen);
            if (obj.disabled === true || obj.disabled === false) $this.attr("disabled", obj.disabled);
            $this.trigger("chosen:updated").trigger("change");
            if (obj.callback) obj.callback($this);
            return $this;
        };


        /**
         * 委托终止日
         */
        query("#mdate").change(function () {
            checkFinMdate();
            if (query("#price").val()) {
                calData("price");
            }
        });

        /**
         * 清算速度
         */
        query("#settleSpreed").change(function () {
            if (query("#price").val() || query("#exerYield").val() || query("#matureYield").val()) {
                if (query("#price").val()) {
                    calData("price");
                }
            }
        });

        /**
         * 净价
         */
        query("#price").change(function () {
            // var ps = query("#ps").val();
            if (query("#finprodId").val() && $(this).val()) {
                calData("price");
            } 
            // else if (!query("#finprodId").val() && ps == T01) {
            //     if (query("#price").val()) {
            //         query("#matureYield").attr("disabled", true);
            //         query("#matureYield").val("");
            //        
            //         etrIbBondinfo002Funcs.exerYield_setDisabled(true);
            //     } else {
            //         query("#matureYield").removeAttr("disabled");
            //
            //         etrIbBondinfo002Funcs.exerYield_setDisabled(false, query("#isRight").val(), query("#priceModel").val());
            //     }
            // }
        });
        /**
         * 行权收益率
         */
        query("#exerYield").change(function () {
            // var ps = query("#ps").val();
            if (query("#finprodId").val()) {
                if (query("#exerYield").val()) {
                    calData("exerYield");
                }
            } 
            // else if (!query("#finprodId").val() && ps == T01) {
            //     if (query("#exerYield").val()) {
            //         query("#price").attr("disabled", true);
            //         query("#matureYield").attr("disabled", true);
            //         query("#price").val("");
            //         query("#matureYield").val("");
            //     } else {
            //         query("#price").removeAttr("disabled");
            //         query("#matureYield").removeAttr("disabled");
            //     }
            // }
        });
        /**
         * 到期收益率
         */
        query("#matureYield").change(function () {
            // var ps = query("#ps").val();
            if (query("#finprodId").val()) {
                if (query("#matureYield").val()) {
                    calData("matureYield");
                }
            } 
            // else if (!query("#finprodId").val() && ps == T01) {
            //     if (query("#matureYield").val()) {
            //         query("#price").attr("disabled", true);
            //         query("#price").val("");
            //
            //         etrIbBondinfo002Funcs.exerYield_setDisabled(true);
            //     } else {
            //         query("#price").removeAttr("disabled");
            //
            //         etrIbBondinfo002Funcs.exerYield_setDisabled(false, query("#isRight").val(), query("#priceModel").val());
            //     }
            // }
        });

        /**
         * 价格模式
         */
        query("#priceModel").change(function () {
            if (pageModel !== PAGE_MODEL_REFERENCE) {
                if (query("#priceModel").val() === PRICEMODEL_03) {
                    query("#price").attr("disabled", true).removeAttr("required"); // 净价
                    query("#fpriceAmt").val("").attr("disabled", true); // 全价
                    query("#matureYield").val("").attr("disabled", true); // 到期收益率

                    doChange.exerYield_setDisabled(true); // 行权收益率
                } else {
                    query("#price").val("").attr("disabled", false).attr("required", "true"); // 净价
                    query("#fpriceAmt").val("").attr("disabled", false); // 全价
                    query("#matureYield").val("").attr("disabled", false); // 到期收益率
                    
                    doChange.exerYield_setDisabled(false); // 行权收益率
                }

                query('#finprodId').trigger("change"); // 触发star功能
                
                if (query("#price").val()) {
                    calData("price");
                }
            }
        });

        /**
         * 通道
         */
        query("#chlFinprodId").change(function () {

            //证券账户
            if (query("#chlFinprodId").val() != null && query("#chlFinprodId").val() != '') {
                query("#secManageAcctId").val('');
            } else {
                query("#secManageAcctId").val('999999');
            }

            //左下行情信息联动
            getLeftMarketInfo();
         
        });

    });
    // 解决查看画面打开联动仍然触发的bug
    initFlag = false;
})();
