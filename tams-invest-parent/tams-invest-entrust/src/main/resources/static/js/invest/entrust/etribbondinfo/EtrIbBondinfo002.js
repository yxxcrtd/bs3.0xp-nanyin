/**
 * 债券投资委托新建
 */
(function () {
    //试算结果
    var riskResultdata;
    var cluflag;

    var initFlag = true;
    var pageModel = PAGE_MODEL_NEW;

    var getriskdata = function () {
        var obj = {
            tradeDirection: $("#ps").val(),
            timePoint: MY_SUP_TIME_TYPE_02,
            valDate: sysDate,
            portfolioCode: $("#portfolioId").val(),
            tradeAmount: $("#entrustNum").val(),
            assetType: fintype,
            isEnabled: "01",
            assetCode: $("#finprodIdhid").val()
        };
        obj.counterId = $("#counterId").val();
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


    /**
     * 获取表单参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSaveOrSubmitParams(operateType) {
        if ($("#priceModel").val() == "PRM02") {
            //限价指令
            if ($("#isRight").val() == "Y") {
                //为含权债的时候
                if (!$("#price").val() && !$("#matureYield").val() && !$("#exerYield").val()) {
                    Joyin.warn("含权债时净价、到期收益率、行权收益率三选一必填");
                    return false;
                }
            } else {
                if (!$("#price").val() && !$("#matureYield").val()) {
                    Joyin.warn("净价、到期收益率二选一必填");
                    return false;
                }
            }
        }
        
        //证券账户
        if ($("#chlFinprodId").val() != null && $("#chlFinprodId").val() != '') {
            $("#secManageAcctId").val('');
        } else {
            $("#secManageAcctId").val('999999');
        }
        if (!$("#secManageAcctId").val() && !$("#chlFinprodId").val()) {
            Joyin.warn("证券账户和通道不能同时为空！");
            return false;
        }
        var object = $.serializeToObject("#searchForm");
        if ($("#select2-finprodId-container").attr("title")) {
            object.finprodName = $("#select2-finprodId-container").attr("title");
        }

        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $("#entrustId").val();
        object.entrustStatus = $("#entrustStatus").val();
        object.ccy = $("#ccy").val();
        object.tradeMarket = $("#tradeMarket").val();
        object.finvdate = $("#finvdate").val();
        object.finmdate = $("#finmdate").val();
        //提交获取计算方式单选框的值
        object.calculateField = $("input[name=calculateField]:checked").val();

        if ($("#ps").val() == T03 || $("#ps").val() == T39) {
            object.counterType=$('#counterId2').val();
        }else{
            object.counterType=$('#counterId1').val();
        }
        // object.counterType = $('#counterId').select2('data')[0].object;  //交易对手类型，校验时用到
        return object;
    }

    //到期日小于委托结束日，给出提示
    var checkFinMdate = function () {
        if ($("#finprodId").val()) {
            var finMdate = new Date($("#finprodId").select2("data")[0].object.finmdate).format("yyyy-MM-dd");
            if (new Date(finMdate).DateDiff("d", $("#mdate").val()) > 0) {
                Joyin.error(JoyinUtil.getMessage("ETR_X_007"));
                $("#finprodId").val("").trigger("change");
            }
        }
    };

    //净价、行权收益率、到期收益率联动
    var calData = function (calflag) {
        if (!initFlag) {
            var ps = $("#ps").val();
            if (!ps) {
                return;
            }
            if (!$("#priceModel").val() || !($("#priceModel").val() == "PRM01" || $("#priceModel").val() == "PRM02")) {
                return;
            }
            if (!calflag) {
                return;
            }
            if (!$("#finprodId").val()) {
                return;
            }
            var object = $.serializeToObject("#searchForm");

            //连动时不需要券面总额，传给后台一个默认值
            if (!object.entrustNum) {
                object.entrustNum = 100;
            }
            //只有选中单选按钮的值才进行联动计算  add by yzg 20210622 start
            var calculateField =  $("input[name=calculateField]:checked").val();
            if(calflag != calculateField){
            	return;
            }
            //只有选中单选按钮的值才进行联动计算  add by yzg 20210622 end
            object.calflag = calflag;
            object.pageModel = pageModel;
            object.entrustId = $("#entrustId").val();
            object.entrustStatus = $("#entrustStatus").val();
            object.finvdate = $("#finvdate").val();
            object.finmdate = $("#finmdate").val();
            object.finprodId = $("#finprodIdhid").val();
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
                        $("#price").val(data.price).trigger("blur");
                        $("#fpriceAmt").val(data.unitFprice).trigger("blur");
                    } else {
                        $("#price").val("");
                        $("#fpriceAmt").val("");
                    }
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        $("#exerYield").val(data.exerYield).trigger("blur");
                        $("#matureYield").val(data.matureYield).trigger("blur");
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


    /**
     * 初始化
     */
    var init = function () {
        if (todoList == false) {
            $('#EtrIbBondinfo002_btn_fileupload').css('display', 'none');
            $('#EtrIbBondinfo002_btn_riskcal').css('display', 'none');
        }else {
            if ($('#priceModel').val() == PRICEMODEL_03) {
                // 净价置灰不可填
                $('#price').val('').attr('disabled', true);
                $('#price').removeAttr("required");
                // 全价置灰不可填
                $('#fpriceAmt').val('').attr('disabled', true);

                // 到期收益率
                $('#matureYield').val('').attr('disabled', true);

                // 行权收益率
                $('#exerYield').val('').attr('disabled', true);

            } else {
                // 净价必填
                $('#price').attr('disabled', false);
                $('#price').attr("required", "true");
                // 全价 可填
                $('#fpriceAmt').attr('disabled', false);

                $('#matureYield').attr('disabled', false);

                $('#exerYield').attr('disabled', false);
            }
        }

        businessId = $('#businessId').val()
        if(calculateField){
        	$("#calculateField_" + calculateField).attr("checked", true);
        }
        $("#ps").change();
        if ($("#finprodId").val() != null && $("#finprodId").val() != "") {
            $("#finprodId").change();
        }
        $("#portfolioId").change();
        $("#priceModel").trigger("change");
        
        initFlag = false;
        // 画星星
        star();
        //
        if (typeof shortSellingSupported !== "undefined" && shortSellingSupported == "02") {
            $(".shortSellingSupported").hide();
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
        ps = $("#ps").val();
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
                $("#portfolioId").textSelect('setValue',param.portfolioId);
            }
            if (param.finprodMarketId) {
                $("#finprodId").textSelect('setValue',param.finprodMarketId);
            }
            if (param.avaShareAmt) {
                $("#entrustNum").val(param.avaShareAmt).trigger("change");
            }
            if (param.channelId) {
                $("#chlFinprodId").val(param.channelId).trigger("change");//通道
            }
            
            if (param.price){
                $("#calculateField").val(param.price).trigger("change");//净价
            }
            $("#ps").textSelect('setValue',"T02");
            $("#priceModel").textSelect('setValue',"PRM02");
            $("#vdate").val(sysDate).trigger("change");
            $("#mdate").val(sysDate).trigger("change");
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
                $("#entrustId").val(param.entrustId).trigger("change");//委托代码
            }
            if (param.entrustStatus) {
                $("#entrustStatus").val(param.entrustStatus).trigger("change");//委托状态
            }
            if (param.portfolioId) {
                $("#portfolioId").textSelect('setValue',param.portfolioId);//组合代码
            }
            if (param.finprodMarketId) {
                $("#finprodId").textSelect('setValue',param.finprodMarketId);//资产代码
            }
            if (param.ps) {
                $("#ps").textSelect('setValue',param.ps);//交易方向
            }
            if (param.shareNum) {
                $("#entrustNum").val(param.shareNum).trigger("change");//券面总额
            }
            if (param.settleSpreed) {
                if(param.settleSpreed === "0"){
                    $("#settleSpreed").textSelect('setValue',"01");
                }else if(param.settleSpreed === "1"){
                    $("#settleSpreed").textSelect('setValue',"02");
                }
            }
            if (param.priceModel) {
                $("#priceModel").val(param.priceModel).trigger("change");//价格模式
            }
            if (param.unitPrice) {
                $("#price").val(param.unitPrice).trigger("change");//净价
            }
            if (param.allPrice) {
                $("#fpriceAmt").val(param.allPrice).trigger("change");//全价
            }
            if (param.matureYield) {
                $("#matureYield").val(param.matureYield).trigger("change");//到期收益率
            }
            if (param.exerYield) {
                $("#exerYield").val(param.exerYield).trigger("change");//行权收益率
            }
            /*             if (param.vdate) {
                $("#vdate").val(param.vdate).trigger("change");//成立日
            }
            if (param.mdate) {
                $("#mdate").val(param.mdate).trigger("change");//终止日
            } */
            if (param.remark) {
                $("#remark").val(param.remark).trigger("change");//备注
            }
            if (param.counterId) {
                $("#counterId1").textSelect('setValue',param.counterId);
                $("#counterId2").textSelect('setValue',param.counterId);
            }
            
            pageModel = PAGE_MODEL_EDIT;
        }
    };

    //查询组合可用现金,资产可用券面总额
    var getPtlCashPositionInfo = function () {
        if (!$('#portfolioId').val()) {
            return;
        }
        var param = {};
        param.ccy = $('#ccy').val();
        param.vdate = $('#vdate').val();
        param.finprodId = $("#finprodIdhid").val();
        param.portfolioId = $("#portfolioId").val();

        Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtrIbBondinfo001/findPositionInfo', param, function (result) {
            if (result) {
                $("#proAvailableCash").val(result.zhkyxj_t);
                $("#assetAvailablePosition").val(result.zcky_qmze);
            }
        });
    }

    /**
     * 左下行情信息联动
     */
    var getLeftMarketInfo = function () {
        var chlFinprodId = $("#chlFinprodId").val();
        var finprodIdhid = $("#finprodIdhid").val();
        var portfolioId = $("#portfolioId").val();
        var ccy = $('#ccy').val();
        var sysDateT1 = $('#vdate').val();
		var calId = $('#calendarId').val();
        if (todoList == false) {
            parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId,ccy,sysDateT1,calId);
        }
    }

    var getLeftZHinfo = function () {
        var chlFinprodId = $("#chlFinprodId").val();
        var finprodIdhid = $("#finprodIdhid").val();
        var portfolioId = $("#portfolioId").val();
        var ccy = $('#ccy').val();
        var vdate = $('#vdate').val();
        var calId = $('#calendarId').val();
        if (todoList == false) {
            parent.EtrIbBondinfoHq.queryPositionInfo(finprodIdhid,portfolioId,chlFinprodId,ccy,vdate,calId);
        }
    }
    /**
     * 取消,清空指令数据
     */
    var doReset = function () {
        $("#portfolioId").val("").trigger("change");
        $("#finprodId").val("").trigger("change");
        $("#ps").val("").trigger("change");
        $("#entrustNum").val("").trigger("change");
        $("#settleSpreed").val("").trigger("change");
        $("#priceModel").val("").trigger("change");
        $("#price").val("").trigger("change");
        $("#fpriceAmt").val("").trigger("change");
        $("#matureYield").val("").trigger("change");
        $("#exerYield").val("").trigger("change");
        $("#counterId").val("").trigger("change");
        $("#chlFinprodId").val("").trigger("change");
        $("#remark").val("").trigger("change");
        $("#entrustId").val("").trigger("change");
        $("#entrustStatus").val("").trigger("change");
        $("#invAim").val("").trigger("change");
        $("#calculateField_price").prop('checked', false);
        $("#calculateField_exerYield").prop('checked', false);
        $("#calculateField_matureYield").prop('checked', false);
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
        $("#ps").change(function () {
            var finprodIdhid = $("#finprodIdhid").val();
            if ($("#ps").val() == T01) {
                if ($("#isRight").val() != N) {
                    $("#exerYield").removeAttr("disabled");
                }
                $("#matureYield").removeAttr("disabled");
                
                // 当交易方向为买入卖出时，交易对手下拉框显示为银行间机构信息中的交易账户21位码+交易成员全称
                $("#div_counterId1").css("display","block");
                $("#div_counterId2").css("display","none");
            } else if ($("#ps").val() == T02) {
                $("#exerYield").attr("disabled", true);
                $("#matureYield").attr("disabled", true);
                $("#exerYield").val("");
                $("#matureYield").val("");
                
                // 当交易方向为买入卖出时，交易对手下拉框显示为银行间机构信息中的交易账户21位码+交易成员全称
                $("#div_counterId1").css("display","block");
                $("#div_counterId2").css("display","none");
            } else if ($("#ps").val() == T03 || $("#ps").val() == T39) {
                $("#exerYield").attr("disabled", true);
                $("#matureYield").attr("disabled", true);
                $("#exerYield").val("");
                $("#matureYield").val("");
                
                // 当交易方向为行权回售时，可选项为市场机构
                $("#div_counterId1").css("display","none");
                $("#div_counterId2").css("display","block");
            }
            if (pageModel != PAGE_MODEL_REFERENCE) {
                if ($("#ps").val() == T03) {
                    //交易方向选择“行权-回售”时，将“价格模式”字段默认为“指定价格”，且价格默认为 债券行权价 ；
                    $("#priceModel").val("PRM01").trigger("change");
                    if ($("#finprodId").val()) {
                        Joyin.ajaxJson(
                            basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/getExercisePricebyPara",
                            {
                                finprodId: finprodIdhid,
                                ps: "T03"
                            },
                            function (result) {
                                $("#price").val(result);
                            },
                            false
                        );
                    }
                } else {
                    $("#priceModel").val("").trigger("change");
                    $("#price").val("");
                }
            }
            if (pageModel != PAGE_MODEL_REFERENCE) {
                if ($("#price").val()) {
                    calData("price");
                }
            }
            // 画星星
            star();
            
            //【银行间指令】债券二级-当交易方向为“行权回售”时-清算速度请默认T+0,且不可修改
            if ($("#ps").val() == T03) {
            	$('#settleSpreed').textSelect('setValue',"01");
            	$('#settleSpreed').attr('disabled', true);
            } else {
            	$('#settleSpreed').textSelect('setValue',"");
            	$('#settleSpreed').attr('disabled', false);
            }
        });

        /**
         * 资产代码
         */
        $("#finprodId").change(function () {
            var portfolioId = $("#portfolioId").val();
            var finprodId = $("#finprodId").val();
            if (finprodId) {
                var data = $("#finprodId").select2("data")[0].object;
                if (!data.finprodId) {
                    //自动加券
                    data = AutoCoupon.autoBondTradeCoupon($("#finprodId").val());
                }
                // 不为空
                $("#price").attr("required", false);
                $("#exerYield").attr("required", false);
                $("#matureYield").attr("required", false);
                //var data = $('#finprodId').select2('data')[0].object;
                $("#finprodIdhid").val(data.finprodId).trigger("change");
                $("#finprodName").val(finprodId).trigger("change");
                $("#isRight").val(data.isRight).trigger("change");
                $("#calendarId").val(data.calendarId).trigger("change");
                $("#finvdate").val(new Date(data.finvdate)).trigger("change");
                $("#finmdate").val(new Date(data.finmdate)).trigger("change");
                $("#ccy").val(data.ccy).trigger("change").attr("disabled", true);
                checkFinMdate();
                if ($("#price").val()) {
                    calData("price");
                }
            } else {
                // 为空
                $("#price").attr("required", true);
                $("#exerYield").attr("required", true);
                $("#matureYield").attr("required", true);
                $("#finprodIdhid").val("").trigger("change");
                $("#finprodName").val("").trigger("change");
                $("#calendarId").val("").trigger("change");
                //$("#tradeMarket").val("").trigger("change").attr("disabled", false);
                $("#ccy").val($("#EtrIbBondinfo002_default_ccy").val()).trigger("change").attr("disabled", false);
                $("#isRight").val("").trigger("change");

                $("#exerYield").val("").trigger("change").trigger("blur");
                $("#matureYield").val("").trigger("change").trigger("blur");
            }

            //左下行情信息联动 & 组合可用现金,资产可用持仓
            getLeftMarketInfo();

            //组合可用现金,资产可用持仓
            getPtlCashPositionInfo();

            // 画星星
            star();
        });

        /**
         * 组合代码
         */
        $("#portfolioId").change(function () {
            var portfolioId = $("#portfolioId").val();
            
            //左下行情信息联动 & 组合可用现金,资产可用持仓
            // getLeftMarketInfo();
            getLeftZHinfo();
            
            //组合可用现金,资产可用持仓
            getPtlCashPositionInfo();
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
        $("#mdate").change(function () {
            checkFinMdate();
            if ($("#price").val()) {
                calData("price");
            }
        });

        /**
         * 清算速度
         */
        $("#settleSpreed").change(function () {
            if ($("#price").val() || $("#exerYield").val() || $("#matureYield").val()) {
                if ($("#price").val()) {
                    calData("price");
                }
            }
        });

        /**
         * 净价
         */
        $("#price").change(function () {
            var ps = $("#ps").val();
            if ($("#finprodId").val() && $(this).val()) {
                calData("price");
            } else if (!$("#finprodId").val() && ps == T01) {
                if ($("#price").val()) {
                    $("#exerYield").attr("disabled", true);
                    $("#matureYield").attr("disabled", true);
                    $("#exerYield").val("");
                    $("#matureYield").val("");
                } else {
                    $("#exerYield").removeAttr("disabled");
                    $("#matureYield").removeAttr("disabled");
                }
            }
        });
        /**
         * 行权收益率
         */
        $("#exerYield").change(function () {
            var ps = $("#ps").val();
            if ($("#finprodId").val()) {
                if ($("#exerYield").val()) {
                    calData("exerYield");
                }
            } else if (!$("#finprodId").val() && ps == T01) {
                if ($("#exerYield").val()) {
                    $("#price").attr("disabled", true);
                    $("#matureYield").attr("disabled", true);
                    $("#price").val("");
                    $("#matureYield").val("");
                } else {
                    $("#price").removeAttr("disabled");
                    $("#matureYield").removeAttr("disabled");
                }
            }
        });
        /**
         * 到期收益率
         */
        $("#matureYield").change(function () {
            var ps = $("#ps").val();
            if ($("#finprodId").val()) {
                if ($("#matureYield").val()) {
                    calData("matureYield");
                }
            } else if (!$("#finprodId").val() && ps == T01) {
                if ($("#matureYield").val()) {
                    $("#price").attr("disabled", true);
                    $("#exerYield").attr("disabled", true);
                    $("#price").val("");
                    $("#exerYield").val("");
                } else {
                    $("#price").removeAttr("disabled");
                    $("#exerYield").removeAttr("disabled");
                }
            }
        });

        /**
         * 价格模式
         */
        $("#priceModel").change(function () {
            if (pageModel != PAGE_MODEL_REFERENCE) {
                if ($("#priceModel").val() == PRICEMODEL_03) {
                    $("#price").val("").attr("disabled", true);
                    $("#fpriceAmt").val("").attr("disabled", true);
                    $("#matureYield").val("").attr("disabled", true);
                    $("#exerYield").val("").attr("disabled", true);
                } else {
                    $("#price").val("").attr("disabled", false);
                    $("#matureYield").attr("disabled", false);
                    $("#exerYield").attr("disabled", false);
                }
                if ($("#price").val()) {
                    calData("price");
                }
            }
        });

        /**
         * 通道
         */
        $("#chlFinprodId").change(function () {

            //证券账户
            if ($("#chlFinprodId").val() != null && $("#chlFinprodId").val() != '') {
                $("#secManageAcctId").val('');
            } else {
                $("#secManageAcctId").val('999999');
            }

            //左下行情信息联动
            getLeftMarketInfo();
         
        });

        /**
         * 是否行权
         */
        $('#isRight').change(function () {
            var isRight = $('#isRight').val();
            if (!isRight || isRight == N) {
                $('#exerYield').val('').attr('disabled', true);
            } else {
                if($("#priceModel").val() !== PRICEMODEL_03){
                    $('#exerYield').attr('disabled', false);
                }   
            }
        });

    });
    // 解决查看画面打开联动仍然触发的bug
    initFlag = false;
})();
