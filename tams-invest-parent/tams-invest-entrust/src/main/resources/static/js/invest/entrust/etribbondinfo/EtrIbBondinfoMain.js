/**
 * 主页面JS
 */
var changeFlg;
var pageModel;

/**
 * 双击校验后，将风险试算标识重置
 * @returns
 */
function dbclickRefreshRisk() {
    if (BaseRiskMathComm) {
        BaseRiskMathComm.resetRiskMathFlag();
    }
}

$(
    (function () {
        //用于判断当前属于单组合打开还是多组合打开
        changeFlg = "01";
        
        //add by yzg 隐藏单组合页面 20210621 start
        if(dan_etr_isshow == 'N'){
        	$("#danEtr_div").hide();
        	$("#danEtr_li").hide();
        }
        //add by yzg 隐藏单组合页面 20210621 end

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
            // //窗口容器的高度，设置为页面左右容器的高度
            // var height = window.innerHeight - 50;
            // $('.right_panel').css('height', height);
            // $('.left_panel').css('height', height);
        };

        /**
         * 风险试算
         * @returns
         */
        function doRiskcal() {
            if (changeFlg === "01") {
                doRiskcal002();
            } else if (changeFlg === "02") {
                doRiskcal003();
            }
        }

        /**
         * 风险试算002
         * @param url
         * @param object
         * @param riskdata
         * @returns
         */
        function doRiskcal002() {
            var object = EtrIbBondinfo002.getSearchParams();
            if (object && EtrIbBondinfo002.isNeedRiskcal()) {
                var riskdata = EtrIbBondinfo002.getriskdata(object);
                var riskInterfUrl = EtrIbBondinfo002.getRiskInterfUrl();
                if (riskdata && riskInterfUrl) {
                    window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object, riskdata, riskMathRedisCallback002);
                }
            }
        }

        /**
         * 风险试算003
         * @param url
         * @param object
         * @param riskdata
         * @returns
         */
        function doRiskcal003() {
            var object = EtrIbBondinfo003.getSearchParams();
            // var checkParam = {};
            // checkParam.data = EtrIbBondinfo003.getriskdata();
            // if(!checkParam.data){
            //     return null;
            // }
            // var param = [];

            // if (checkParam.data.length > 0) {
            //     for (var index in checkParam.data) {
            //         var item = checkParam.data[index];
            //         item.data.assetCode = object.finprodId;
            //         item.data.finprodId = object.finprodId;
            //         item.data.counterId = object.counterId;
            //         item.data.ps = object.ps;
            //         item.data.ccy = "CNY";
            //         item.data.portfolioId = item.data.portfolioCode;
            //         item.data.price = object.price;
            //         item.data.entrustNum = item.data.entrustNum;
            //         param.push(item.data);
            //     }
            // }
            let checkParam = EtrIbBondinfo003.getriskdata();
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + "riskrds/interf/doInfBatchData/0/RSK002/F01", object, checkParam, riskMathRedisCallback003);
        }
        /**
         * 风险试算的回调函数
         * @param object
         * @param cluflag
         * @returns
         */
        function riskMathRedisCallback003(object, cluflag) {
            EtrIbBondinfo003.riskResultdata = object;
            EtrIbBondinfo003.cluflag = cluflag;
            Joyin.dialog(JoyinUtil.getLabel("EtribBondInfo003Riskcal"), "/invest/entrust/etribbondinfo/EtribBondInfo003Riskcal/init", "60%", "75%", null);
        }

        /**
         * 风险试算的回调函数002
         * @param object
         * @param cluflag
         * @returns
         */
        function riskMathRedisCallback002(object, cluflag) {
            EtrIbBondinfo002.riskResultdata = object;
            EtrIbBondinfo002.cluflag = cluflag;
            Joyin.dialog(JoyinUtil.getLabel("EtrIbBondinfoRiskcal002"), "/invest/entrust/etribbondinfo/EtrIbBondinfoRiskcal002/init", "60%", "75%", null);
        }

        /**
         * 保存和提交
         * @returns
         */
        function saveOrSubmit(operateType) {
            if (changeFlg === "01") {
                saveOrSubmit002(operateType);
            } else if (changeFlg === "02") {
                saveOrSubmit003(operateType);
            }
        }
        
        function saveOrSubmit003(operateType) {
            var s;
            if (BaseRiskMathComm) {
                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                if (!s && s != "") {
                    return;
                }
                var checkData = EtrIbBondinfo003.getriskdata();
                if (window.BaseRiskMathComm.isCalcConditionChanged(checkData)) {
                    return;
                }
            }
            
            var res = EtrIbBondinfo003.getSubmitParam(operateType);
            if(res == null) return;

            Joyin.ajaxJson(basePath + "invest/entrust/EtrCommonController/check", res.param, function (result1) {
                if (res.objects.length > 0) {
                    if (operateType === "save") {
                        WorkFlow.doSaveList(basePath + "invest/entrust/etribbondinfo/EtribBondInfo003/save", res.param, "EtribBondInfo003_btn_save", "EtribBondInfo003"
                            ,function(){
                                window.location.reload();
                            });
                    }else{
                                Joyin.ajaxJson(basePath + "invest/entrust/etribbondinfo/EtribBondInfo003/check", res.objects, function (result) {
                                    Joyin.confirm(result.data, function () {
                                        WorkFlow.doSubmitList(basePath + "invest/entrust/etribbondinfo/EtribBondInfo003/save", res.param, "EtribBondInfo003_btn_submit", "EtribBondInfo003"
                                            ,function(){
                                                window.location.reload();
                                            });
                                    });
                                });
                    }
                } else {
                    Joyin.warn("请添加组合分配！");
                    return;
                }
            });
        }

        /**
         * 文件上传
         */
        function doFileupload() {
            if (changeFlg === "01") {
                doFileupload002();
            } else if (changeFlg === "02") {
                doFileupload003();
            }
        }

        /**
         * 单组合画面文件上传002
         */
        function doFileupload002() {
            var params = EtrIbBondinfo002.getSearchParams();
            var object = {
                businessId: params.businessId
            };

            Joyin.dialog(JoyinUtil.getLabel("EtrIbBondinfo002.upload"), "/invest/entrust/etribbondinfo/EtrIbBondinfoFile/init", "60%", "75%", object);
        }

        /**
         * 多组合画面文件上传
         */
        function doFileupload003() {
            var params = EtrIbBondinfo003.getSearchParams();
            var object = {
                businessId: params.businessId
            };

            Joyin.dialog(JoyinUtil.getLabel("EtribBondInfo003.fileUpload"), "/invest/entrust/etribbondinfo/EtrIbBondinfoFile/init", "60%", "75%", object);
        }

        /**
         * 保存或提交002
         *
         * @param operateType 操作类型（save，submit）
         * @returns
         */
        function saveOrSubmit002(operateType) {
            var s;
            if (BaseRiskMathComm) {
                s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
                if (!s && s != "") {
                    return;
                }
                if (window.BaseRiskMathComm.isCalcConditionChanged(EtrIbBondinfo002.getriskdata())) {
                    return;
                }
            }
            var object = EtrIbBondinfo002.getSaveOrSubmitParams(operateType);

            pageModel = object.pageModel;

            if (!object) {
                return;
            }

            Joyin.ajaxJson(basePath + "invest/entrust/EtrCommonController/check", object, function (result1) {
                Joyin.ajaxJson(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/check", object, function (result) {
                    Joyin.confirm(result.data, function () {
                        if (operateType === "save") {
                            WorkFlow.doSaveList(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/save", object, "EtrIbBondinfoMain_btn_save", "EtrIbBondinfo002", function () {
                                window.location.reload();
                            });
                        } else {
                            WorkFlow.doSubmitList(basePath + "invest/entrust/etribbondinfo/EtrIbBondinfo002/save", object, "EtrIbBondinfoMain_btn_submit", "EtrIbBondinfo002", function () {
                                window.location.reload();
                            });
                        }
                    });
                });
            });
        }

        /**
         * 重置
         * @returns
         */
        function doReset() {
            if (changeFlg === "01") {
                EtrIbBondinfo002.doReset();
            } else if (changeFlg === "02") {
                EtrIbBondinfo003.doReset();
            }
        }

        var defaultStyle = "";
        $("#enlarge").click(function () {
            enlarge_narrow("enlarge");
        });
        $("#narrow").click(function () {
            enlarge_narrow("narrow");
        });
        function enlarge_narrow(flag) {
            let rightDiv = $("#right_bottom_panel_dev");
            if(!rightDiv.attr("flag") || rightDiv.attr("flag") === "1"){
                defaultStyle = rightDiv.attr("style");
                rightDiv.attr("style", "position: absolute; left: 0px; top:0px; width: 100%; height:100%;background: #fff; z-index: 100");
                rightDiv.attr("flag","0");
            }else{
                rightDiv.attr("style", defaultStyle);
                rightDiv.attr("flag","1");
            }
            if(flag === "narrow"){
                $("#enlarge").show();
                $("#narrow").hide();
            }else{
                $("#enlarge").hide();
                $("#narrow").show();
            }
        }
        /**
         * 绑定事件
         */
        $(function () {
            init();
            /**
             * 附件上传
             */
            $("#EtrIbBondinfoMain_btn_fileupload").click(function () {
                doFileupload();
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
             * 重置
             */
            $("#EtrIbBondinfoMain_btn_reset").click(function () {
                doReset();
            });
            /**
             * 风险试算
             */
            $("#EtrIbBondinfoMain_btn_riskcal").click(function () {
                doRiskcal();
            });
        });
    })()
);

// 多对多指令导入
function importEtr() {
	Joyin.uploadFile({pageId : 'EtrIbBondInfo004MTM'}, function(fileName) {
        Joyin.ajax(basePath + 'invest/entrust/etribbondinfo/EtrIbBondInfo004/checkImportData', {fileName: fileName}, function(checkResult) {
            if (checkResult) {
                setTimeout( function () {
                    if(checkResult.data.errorMsg){
                        Joyin.error(checkResult.data.errorMsg);
                        return;
                    }
                    Joyin.confirm(checkResult.data.confirmMsg,function() {
                        var result = {};
                        result.tempList = checkResult.data.correctData;
                        Joyin.dialog(JoyinUtil.getLabel("EtrInvestBondInfo004"),
                            JoyinUtil.getRealPath("/invest/entrust/etribbondinfo/EtrIbBondInfo004/init"),
                            "98%", "95%", result,
                            function(){
                            // table.reload();
                        });
                    });
                },1000);
            }
        });
    },function() {
        // table.reload();
    });
}

var dialogSelf = {}
dialogSelf.flag="abc";
dialogSelf.dialog=function(msg, url, param){
    Joyin.dialog(msg, url, "98%", "95%", param);
}

let changeFlgObj = {}; 
changeFlgObj.getChangeFlg=function () {
    return changeFlg;
}
parent.EtrIbBondinfoMain = {
    dialogSelf:dialogSelf,
    changeFlgObj:changeFlgObj
}
EtrIbBondinfoMain = {
    dialogSelf:dialogSelf,
    changeFlgObj:changeFlgObj
}