(function(){

    var init = function () {

        Split(["#top_panel_dev", "#bottom_panel_dev"], {
            sizes: [48, 44],
            minSize: 100,
            direction: "vertical"
        });

        Split(["#left_bottom_panel_dev", "#right_bottom_panel_dev"], {
            sizes: [35, 65],
            minSize: [300, 600],
            direction: "horizontal"
        });
        // Split(["#top_panel_dev", "#bottom_panel_dev"], {
        //     sizes: [46, 44],
        //     minSize: 100,
        //     direction: "vertical"
        // });
    };

    var changeFlg = "01";
    var DHOrQQ = "DH";

    layui.use(["element", "form"], function () {
        // 组合平台TAB切换事件
        var element = layui.element;
        element.on("tab(zlPlatform)", function (data) {
            if (data.index == 0) {
                changeFlg = "01";
            } else if (data.index == 1) {
                changeFlg = "02";
            }
            window.EtrIbBondinfoHq.changeElement(changeFlg);
        });
    });
    
    $(function () {
    	init();

        /**
         * 对话
         */
        $('#cfetsBondDealQuotedPriceSingle_dialogueBtn').click(function() {

            DHOrQQ = "DH";
            var entrustOrderTable = window.CfetsBondDealQuotedPriceMainQuery.entrustOrderTable;
            if(entrustOrderTable!=null && entrustOrderTable!='') {
                var selArr = entrustOrderTable.getAllCheckedRows();
                if (!selArr || selArr.length !== 1){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_042"));
                    return;
                }

                if (selArr[0].entrustStatus != entrustStatusAccept){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_002"));
                    return;
                }

                //委托开始日=<平台日<=委托终止日的委托，可发起对话报价或请求报价
                if (new Date(selArr[0].vdate) > platDate || new Date(selArr[0].mdate) < platDate){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_003"));
                    return;
                }

                var object = {entrustId:selArr[0].entrustId};
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/checkEntrust', object, function(result) {
                    if (result.data){
                        if (selArr[0].quoteType && selArr[0].quoteType != quoteTypeDialog){
                            Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_001"), function(){
                                window.CfetsBondDealQuotedPriceSingle.showDiv(DHOrQQ,selArr);
                            });
                        }else{
                            window.CfetsBondDealQuotedPriceSingle.showDiv(DHOrQQ,selArr);
                        }
                    }
                });
            }
        });

        /**
         * 请求
         */
        $('#cfetsBondDealQuotedPriceSingle_requestBtn').click(function() {
            DHOrQQ = "QQ";
            var entrustOrderTable = window.CfetsBondDealQuotedPriceMainQuery.entrustOrderTable;
            if(entrustOrderTable!=null && entrustOrderTable!='') {
                var selArr = entrustOrderTable.getAllCheckedRows();
                if (!selArr || selArr.length !== 1){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_042"));
                    return;
                }

                if (selArr[0].entrustStatus != entrustStatusAccept){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_002"));
                    return;
                }

                //委托开始日=<平台日<=委托终止日的委托，可发起对话报价或请求报价
                if (new Date(selArr[0].vdate) > platDate || new Date(selArr[0].mdate) < platDate){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_003"));
                    return;
                }
                var object = {entrustId:selArr[0].entrustId};
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/checkEntrust', object, function(result) {
                    if (result.data){
                        if (selArr[0].quoteType && selArr[0].quoteType != quoteTypeRequest){
                            Joyin.confirm(JoyinUtil.getMessage("CFETS_COMMON_C_001"), function(){
                                window.CfetsBondDealQuotedPriceSingle.showDiv(DHOrQQ,selArr);
                            });
                        }else{
                            window.CfetsBondDealQuotedPriceSingle.showDiv(DHOrQQ,selArr);
                        }
                    }
                });
            }
        });

        /**
         * 对话
         */
        $('#cfetsBondDealQuotedPriceMuti_dialogueBtn').click(function() {
            DHOrQQ = "DH";
            var entrustOrderTable = window.CfetsBondDealQuotedPriceMainQuery.entrustOrderTable;
            if(entrustOrderTable!=null && entrustOrderTable!='') {
                var selArr = entrustOrderTable.getAllCheckedRows();
                if (!selArr || selArr.length <= 1){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_043"));
                    return;
                }

                var error ="";
                var entrustIdList = "";
                $.each(selArr,function(index,val){

                    if(index==selArr.length-1){
                        entrustIdList = entrustIdList.concat(val.entrustId);
                    }else{
                        entrustIdList = entrustIdList.concat(val.entrustId+",");
                    }
                    if (val.entrustStatus != entrustStatusAccept){
                        error = error.concat(JoyinUtil.getMessage("CFETS_COMMON_E_002")+"指令编号为:"+val.entrustId);
                    }

                    //委托开始日=<平台日<=委托终止日的委托，可发起对话报价或请求报价
                    if (new Date(val.vdate) > platDate || new Date(val.mdate) < platDate){
                        error = error.concat(JoyinUtil.getMessage("CFETS_COMMON_E_003")+"指令编号为:"+val.entrustId);
                    }

                });
                if(error){
                    Joyin.error(error);
                    return;
                }
                var object = {entrustIdList:entrustIdList};
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/checkEntrustList', object, function(result) {
                    if (result.data){
                        window.CfetsBondDealQuotedPriceMuti.showDiv(DHOrQQ,entrustIdList);
                    }
                });
            }
        });

        /**
         * 请求
         */
        $('#cfetsBondDealQuotedPriceMuti_requestBtn').click(function() {
            DHOrQQ = "QQ";
            var entrustOrderTable = window.CfetsBondDealQuotedPriceMainQuery.entrustOrderTable;
            if(entrustOrderTable!=null && entrustOrderTable!='') {
                var selArr = entrustOrderTable.getAllCheckedRows();
                if (!selArr || selArr.length <= 1){
                    Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_043"));
                    return;
                }

                var error ="";
                var entrustIdList = "";
                $.each(selArr,function(index,val){

                    if(index==selArr.length-1){
                        entrustIdList = entrustIdList.concat(val.entrustId);
                    }else{
                        entrustIdList = entrustIdList.concat(val.entrustId+",");
                    }
                    if (val.entrustStatus != entrustStatusAccept){
                        error = error.concat(JoyinUtil.getMessage("CFETS_COMMON_E_002")+"指令编号为:"+val.entrustId);
                    }

                    //委托开始日=<平台日<=委托终止日的委托，可发起对话报价或请求报价
                    if (new Date(val.vdate) > platDate || new Date(val.mdate) < platDate){
                        error = error.concat(JoyinUtil.getMessage("CFETS_COMMON_E_003")+"指令编号为:"+val.entrustId);
                    }

                });
                if(error){
                    Joyin.error(error);
                    return;
                }
                var object = {entrustIdList:entrustIdList};
                Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/checkEntrustList', object, function(result) {
                    if (result.data){
                        window.CfetsBondDealQuotedPriceMuti.showDiv(DHOrQQ,entrustIdList);
                    }
                });
            }
        });

        /**
         * 保存
         */
        $("#EtrIbBondinfoMain_btn_save").click(function () {
            saveOrSubmit("save");
        });
        /**
         * 提交
         */
        $("#EtrIbBondinfoMain_btn_submit").click(function () {
            saveOrSubmit("submit");
        });


        /**
         * 风险试算
         */
        $("#EtrIbBondinfoMain_btn_riskcal").click(function () {
            doRiskcal();
        });

        /**
         * 附件上传
         */
        $("#EtrIbBondinfoMain_btn_fileupload").click(function () {
            doFileupload();
        });

        /**
         * 重置
         */
        $("#EtrIbBondinfoMain_btn_reset").click(function () {
            reset();
        });

        /**
         * 附件上传
         * @returns
         */
        function reset() {
            if (changeFlg === "01") {
                if(DHOrQQ ==="DH"){
                    window.CfetsBondDealQuotedPriceSingle.resetSingle001();
                }else if(DHOrQQ ==="QQ"){
                    window.CfetsBondDealQuotedPriceSingle.resetSingle002();
                }
            } else if (changeFlg === "02") {
                if(DHOrQQ ==="DH"){
                    window.CfetsBondDealQuotedPriceMuti.resetMuti001();
                }else if(DHOrQQ ==="QQ"){
                    window.CfetsBondDealQuotedPriceMuti.resetMuti002();
                }
            }
        }


        /**
         * 附件上传
         * @returns
         */
        function doFileupload() {
            if (changeFlg === "01") {
                if(DHOrQQ ==="DH"){
                    doFileuploadSingle001();
                }else if(DHOrQQ ==="QQ"){
                    doFileuploadSingle002();
                }
            } else if (changeFlg === "02") {
                if(DHOrQQ ==="DH"){
                    doFileuploadMuti001();
                }else if(DHOrQQ ==="QQ"){
                    doFileuploadMuti002();
                }
            }
        }

        function doFileuploadSingle001() {
            var params = window.CfetsBondDealQuotedPriceSingle.getSaveOrSubmitParams_Single001();
            var object = {
                businessId: params.businessId
            };

            Joyin.dialog(JoyinUtil.getLabel("附件上传"), "/invest/entrust/etribbondinfo/EtrIbBondinfoFile/init", "60%", "75%", object);
        }

        function doFileuploadSingle002() {
            var params =  window.CfetsBondDealQuotedPriceSingle.getSaveOrSubmitParams_Single002();
            var object = {
                businessId: params.businessId
            };

            Joyin.dialog(JoyinUtil.getLabel("附件上传"), "/invest/entrust/etribbondinfo/EtrIbBondinfoFile/init", "60%", "75%", object);
        }

        function doFileuploadMuti001() {
            var params =  window.CfetsBondDealQuotedPriceSingle.getSaveOrSubmitParams_Muti001();
            var object = {
                businessId: params.businessId
            };

            Joyin.dialog(JoyinUtil.getLabel("附件上传"), "/invest/entrust/etribbondinfo/EtrIbBondinfoFile/init", "60%", "75%", object);
        }


        function doFileuploadMuti002() {
            var params =window.CfetsBondDealQuotedPriceSingle.getSaveOrSubmitParams_Muti002();
            var object = {
                businessId: params.businessId
            };

            Joyin.dialog(JoyinUtil.getLabel("附件上传"), "/invest/entrust/etribbondinfo/EtrIbBondinfoFile/init", "60%", "75%", object);
        }


        /**
         * 风险试算
         * @returns
         */
        function doRiskcal() {
            if (changeFlg === "01") {
                if(DHOrQQ ==="DH"){
                    doRiskcalSingle001();
                }else if(DHOrQQ ==="QQ"){
                    doRiskcalSingle002();
                }
            } else if (changeFlg === "02") {
                if(DHOrQQ ==="DH"){
                    doRiskcalMuti001();
                }else if(DHOrQQ ==="QQ"){
                    doRiskcalMuti002();
                }
            }
        }

        /**
         * 单组合对话报价风险试算
         */
        function  doRiskcalSingle001() {
            var object = window.CfetsBondDealQuotedPriceSingle.getSaveOrSubmitParams_Single001();
            if (object) {
                var riskdata = window.CfetsBondDealQuotedPriceSingle.getriskdata_Single001(object);
                var riskInterfUrl = basePath + "riskrds/interf/doInfData/1/RSK002/F01";
                if (riskdata && riskInterfUrl) {
                    window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object, riskdata, riskMathRedisCallbackSingle001);
                }
            }
        }
        /**
         * 单组合对话报价风险试算回调
         */
        function riskMathRedisCallbackSingle001(object, paraCluflag) {
            window.CfetsBondDealQuotedPriceSingle.saveRiskResultdata_Single001(object);
            window.CfetsBondDealQuotedPriceSingle.saveCluflag_Single001(paraCluflag);

            var paramObj = {};
            if (object) {
                paramObj.riskResultdata = JSON.stringify(object);
            }
            paramObj.cluflag = paraCluflag;
            Joyin.dialog("风险试算", "/component/common/BaseRiskMathCommDialog/init", "60%", "75%", paramObj);
        }

        /**
         * 单组合请求报价风险试算
         */
        function  doRiskcalSingle002() {
            var object = window.CfetsBondDealQuotedPriceSingle.getSaveOrSubmitParams_Single002();
            if (object) {
                var riskdata = window.CfetsBondDealQuotedPriceSingle.getriskdata_Single002(object);
                var riskInterfUrl = basePath + "riskrds/interf/doInfData/1/RSK002/F01";
                if (riskdata && riskInterfUrl) {
                    window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object, riskdata, riskMathRedisCallbackSingle002);
                }
            }
        }
        /**
         * 单组合请求报价风险试算回调
         */
        function riskMathRedisCallbackSingle002(object, paraCluflag) {
            window.CfetsBondDealQuotedPriceSingle.saveRiskResultdata_Single002(object);
            window.CfetsBondDealQuotedPriceSingle.saveCluflag_Single002(paraCluflag);

            var paramObj = {};
            if (object) {
                paramObj.riskResultdata = JSON.stringify(object);
            }
            paramObj.cluflag = paraCluflag;
            Joyin.dialog("风险试算", "/component/common/BaseRiskMathCommDialog/init", "60%", "75%", paramObj);
        }

        /**
         * 多组合对话报价风险试算
         */
        function  doRiskcalMuti001() {
            var object = window.CfetsBondDealQuotedPriceMuti.getSaveOrSubmitParams_Muti001();
            if (object) {
                var riskdata = window.CfetsBondDealQuotedPriceMuti.getriskdata_Muti001(object);
                var riskInterfUrl = basePath + "riskrds/interf/doInfBatchData/1/RSK002/F01";
                object.cfetsBondDealQuoteDtoList = riskdata.data;
                if (riskdata && riskInterfUrl) {
                    window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object, riskdata, riskMathRedisCallbackMuti001);
                }
            }
        }
        /**
         * 多组合对话报价风险试算回调
         */
        function riskMathRedisCallbackMuti001(object, paraCluflag) {
            window.CfetsBondDealQuotedPriceMuti.saveRiskResultdata_Muti001(object);
            window.CfetsBondDealQuotedPriceMuti.saveCluflag_Muti001(paraCluflag);

            var paramObj = {};
            if (object) {
                paramObj.riskResultdata = JSON.stringify(object);
            }
            paramObj.cluflag = paraCluflag;
            Joyin.dialog("风险试算", "/component/common/BaseRiskMathCommDialog/init", "60%", "75%", paramObj);
        }

        /**
         * 多组合请求报价风险试算
         */
        function  doRiskcalMuti002() {
            var object = window.CfetsBondDealQuotedPriceMuti.getSaveOrSubmitParams_Muti002();
            if (object) {
                var riskdata = window.CfetsBondDealQuotedPriceMuti.getriskdata_Muti002(object);
                var riskInterfUrl = basePath + "riskrds/interf/doInfBatchData/1/RSK002/F01";
                object.cfetsBondDealQuoteDtoList = riskdata.data;
                if (riskdata && riskInterfUrl) {
                    window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object, riskdata, riskMathRedisCallbackMuti002);
                }
            }
        }
        /**
         * 多组合请求报价风险试算回调
         */
        function riskMathRedisCallbackMuti002(object, paraCluflag) {
            window.CfetsBondDealQuotedPriceMuti.saveRiskResultdata_Muti002(object);
            window.CfetsBondDealQuotedPriceMuti.saveCluflag_Muti002(paraCluflag);

            var paramObj = {};
            if (object) {
                paramObj.riskResultdata = JSON.stringify(object);
            }
            paramObj.cluflag = paraCluflag;
            Joyin.dialog("风险试算", "/component/common/BaseRiskMathCommDialog/init", "60%", "75%", paramObj);
        }


        /**
         * 保存和提交
         * @returns
         */
        function saveOrSubmit(operateType) {
            if (changeFlg === "01") {
                if(DHOrQQ ==="DH"){
                    saveOrSubmitSingle001(operateType);
                }else if(DHOrQQ ==="QQ"){
                    saveOrSubmitSingle002(operateType);
                }
            } else if (changeFlg === "02") {
                if(DHOrQQ ==="DH"){
                    saveOrSubmitMuti001(operateType);
                }else if(DHOrQQ ==="QQ"){
                    saveOrSubmitMuti002(operateType);
                }
            }
        }

        /**
         * saveOrSubmitSingle001
         *
         * @param operateType 操作类型（save，submit）
         * @returns
         */
        function saveOrSubmitSingle001(operateType) {
            var s;
            if (BaseRiskMathComm) {
                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                if (!s && s != "") {
                    return;
                }
                if (window.BaseRiskMathComm.isCalcConditionChanged(window.CfetsBondDealQuotedPriceSingle.getriskdata_Single001())) {
                    return;
                }
            }
            var object = window.CfetsBondDealQuotedPriceSingle.getSaveOrSubmitParams_Single001(operateType);

            pageModel = object.pageModel;

            if (!object) {
                return;
            }

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/check', object,
                function(result) {
                    if (result.data) {
                        Joyin.confirm(result.data,
                            function() {
                                //保存 走流程
                                if (operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/save', object, "CfetsBondDealQuotedPriceSingle001_btn_save", "CfetsBondDealQuotedPriceSingle001",
                                        function() {
                                            window.location.reload( );
                                        })
                                }
                                //成交 走流程
                                else if (operateType === 'done') {
                                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/save', object, "CfetsBondDealQuotedPriceSingle001_btn_done", "CfetsBondDealQuotedPriceSingle001",
                                        function() {
                                            window.location.reload();
                                        })
                                }
                                //提交 走流程
                                else if (operateType === 'submit') {
                                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/save', object, "CfetsBondDealQuotedPriceSingle001_btn_submit", "CfetsBondDealQuotedPriceSingle001",
                                        function() {
                                            window.location.reload();
                                        })
                                }
                                //拒绝 不走流程
                                else if (operateType === 'refuse'){
                                    Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle001/saveData', object, function(result){
                                        window.location.reload();
                                    })
                                }
                            })
                    }
                });

        }


        /**
         * saveOrSubmitSingle002
         *
         * @param operateType 操作类型（save，submit）
         * @returns
         */
        function saveOrSubmitSingle002(operateType) {
            var object = window.CfetsBondDealQuotedPriceSingle.getSaveOrSubmitParams_Single002(operateType);
            object.operatorType = operateType;
            pageModel = object.pageModel;
            if (!object) {
                return;
            }

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/check', object,
                function(result) {
                    if (result.data) {
                        Joyin.confirm(result.data,
                            function() {
                                //保存 走流程
                                if (operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/save', object, "CfetsBondDealQuotedPriceSingle002_btn_save", "CfetsBondDealQuotedPriceSingle002",
                                        function() {
                                            window.location.reload( );
                                        })
                                }
                                //成交 走流程
                                else if (operateType === 'done') {
                                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/save', object, "CfetsBondDealQuotedPriceSingle002_btn_done", "CfetsBondDealQuotedPriceSingle002",
                                        function() {
                                            window.location.reload();
                                        })
                                }
                                //提交 走流程
                                else if (operateType === 'submit') {
                                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/save', object, "CfetsBondDealQuotedPriceSingle002_btn_submit", "CfetsBondDealQuotedPriceSingle002",
                                        function() {
                                            window.location.reload();
                                        })
                                }
                                //拒绝 不走流程
                                else if (operateType === 'refuse'){
                                    Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceSingle002/saveData', object, function(result){
                                        window.location.reload();
                                    })
                                }
                            })
                    }
                });
        }


        /**
         * saveOrMutiSingle001
         *
         * @param operateType 操作类型（save，submit）
         * @returns
         */
        function saveOrSubmitMuti001(operateType) {
            var s;
            if (BaseRiskMathComm) {
                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                if (!s && s != "") {
                    return;
                }
                if (window.BaseRiskMathComm.isCalcConditionChanged(window.CfetsBondDealQuotedPriceMuti.getriskdata_Muti001())) {
                    return;
                }
            }
            var object = window.CfetsBondDealQuotedPriceMuti.getSaveOrSubmitParams_Muti001(operateType);

            pageModel = object.pageModel;

            if (!object) {
                return;
            }

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/check', object,
                function(result) {
                    if (result.data) {
                        Joyin.confirm(result.data,
                            function() {
                                //保存 走流程
                                if (operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_save", "CfetsBondDealQuotedPriceMuti001",
                                        function() {
                                            window.location.reload( );
                                        })
                                }
                                //成交 走流程
                                else if (operateType === 'done') {
                                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_done", "CfetsBondDealQuotedPriceMuti001",
                                        function() {
                                            window.location.reload();
                                        })
                                }
                                //提交 走流程
                                else if (operateType === 'submit') {
                                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/save', object, "CfetsBondDealQuotedPriceMuti001_btn_submit", "CfetsBondDealQuotedPriceMuti001",
                                        function() {
                                            window.location.reload();
                                        })
                                }
                                //拒绝 不走流程
                                else if (operateType === 'refuse'){
                                    Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti001/saveData', object, function(result){
                                        window.location.reload();
                                    })
                                }
                            })
                    }
                });

        }


        /**
         * saveOrMutiSingle002
         *
         * @param operateType 操作类型（save，submit）
         * @returns
         */
        function saveOrSubmitMuti002(operateType) {
            var s;
            if (BaseRiskMathComm) {
                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                if (!s && s != "") {
                    return;
                }
                if (window.BaseRiskMathComm.isCalcConditionChanged(window.CfetsBondDealQuotedPriceMuti.getriskdata_Muti002())) {
                    return;
                }
            }
            var object = window.CfetsBondDealQuotedPriceMuti.getSaveOrSubmitParams_Muti002(operateType);

            pageModel = object.pageModel;

            if (!object) {
                return;
            }

            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/check', object,
                function(result) {
                    if (result.data) {
                        Joyin.confirm(result.data,
                            function() {
                                //保存 走流程
                                if (operateType === 'save') {
                                    WorkFlow.doSaveList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/save', object, "CfetsBondDealQuotedPriceMuti002_btn_save", "CfetsBondDealQuotedPriceMuti002",
                                        function() {
                                            window.location.reload( );
                                        })
                                }
                                //成交 走流程
                                else if (operateType === 'done') {
                                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/save', object, "CfetsBondDealQuotedPriceMuti002_btn_done", "CfetsBondDealQuotedPriceMuti002",
                                        function() {
                                            window.location.reload();
                                        })
                                }
                                //提交 走流程
                                else if (operateType === 'submit') {
                                    WorkFlow.doSubmitList(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/save', object, "CfetsBondDealQuotedPriceMuti002_btn_submit", "CfetsBondDealQuotedPriceMuti002",
                                        function() {
                                            window.location.reload();
                                        })
                                }
                                //拒绝 不走流程
                                else if (operateType === 'refuse'){
                                    Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti002/saveData', object, function(result){
                                        window.location.reload();
                                    })
                                }
                            })
                    }
                });
        }

    });
}());