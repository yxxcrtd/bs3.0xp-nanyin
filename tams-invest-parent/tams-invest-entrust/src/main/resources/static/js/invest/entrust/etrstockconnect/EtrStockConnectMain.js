
//默认新增指令
var pageModel;

(function(){

    /**
     * 指令提交或者保存
     */
    function saveOrSubmit(operateType) {
        var s;
        var object = EtrStockConnect002.getSearchParams();

        object.operateType = operateType;
        var riskdata = EtrStockConnect002.getriskdata();
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(riskdata)){
                return;
            }
        }

        pageModel = object.pageModel;
        Joyin.ajaxJson(basePath + '/invest/entrust/etrstockconnect/EtrStockConnect002/check',object,
            function(result) {
                Joyin.confirm(result.data,function() {
                    if (object.operateType === 'save') {
                        WorkFlow.doSaveList(basePath+'/invest/entrust/etrstockconnect/EtrStockConnect002/save',object,"EtrStockConnect002_btn_save",'EtrStockConnect002',function() {
                            if (EtrStockConnect002) {
                                EtrStockConnect002.doReset(true);
                            }
                            if(BaseRiskMathComm) {
                                BaseRiskMathComm.resetRiskMathFlag();
                            }
                            JoyinUtil.disableButton("EtrStockConnect002_btn_save", false);
                        })
                    }else {
                        WorkFlow.doSubmitList(basePath+'/invest/entrust/etrstockconnect/EtrStockConnect002/save',object,"EtrStockConnect002_btn_submit",'EtrStockConnect002',function() {
                            if (EtrStockConnect002) {
                                EtrStockConnect002.doReset(true);
                            }
                            //提交后风险试算标识置为01
                            if(BaseRiskMathComm) {
                                BaseRiskMathComm.resetRiskMathFlag();
                            }
                            JoyinUtil.disableButton("EtrStockConnect002_btn_submit", false);
                        })
                    }
                });
        });
    }


    /**
     * 风险试算
     */
    function doRiskcal() {
        var object = EtrStockConnect002.getSearchParams();
        if(object) {
            var riskdata = EtrStockConnect002.getriskdata();
            var riskInterfUrl = EtrStockConnect002.getRiskInterfUrl();
            if(riskdata && riskInterfUrl) {
                window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object,riskdata,riskMathRedisCallback);
            }
        }

    }
    /**
     * 风险试算的回调函数
     */
    function riskMathRedisCallback(object,cluflag) {
        EtrStockConnect002.riskResultdata = object;
        EtrStockConnect002.cluflag = cluflag;
        Joyin.dialog(JoyinUtil.getLabel('EtrStockConnectRiskcal'), '/invest/entrust/etrstockconnect/EtrStockConnectRiskcal/init', '60%', '75%',null);
    }

    /**
     * 初始化
     */
    var init = function() {
        if (pageModel==PAGE_MODEL_REFERENCE || pageModel==PAGE_MODEL_EDIT) {
            $('#EtrStockConnect002_btn_reset').css('display','none');
        } else {
            $('#top_panel_dev').show();
            $('#left_bottom_panel_dev').show();
            Split(["#top_panel_dev", "#bottom_panel_dev"], {
                sizes: [38, 60],
                minSize: 150,
                direction: "vertical"
            });

            Split(["#left_bottom_panel_dev", "#right_bottom_panel_dev"], {
                sizes: [30, 69],
                minSize: [300, 600],
                direction: "horizontal"
            });
        }
    }


    $(function() {

        init();

        // 绑定提交按钮
        $('#EtrStockConnect002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

        // 绑定保存按钮
        $('#EtrStockConnect002_btn_save').click(function () {
            saveOrSubmit('save');
        });

        // 绑定取消按钮
        $('#EtrStockConnect002_btn_reset').click(function () {
            EtrStockConnect002.doReset();
        });

        // 绑定风险按钮
        $('#EtrStockConnect002_btn_riskcal').click(function () {
            doRiskcal();
        });
    })

}());

//指令导入
var importEtr = function () {
    Joyin.uploadFile({pageId:'EtrStockConnect003MTM'},function(fileName) {
        Joyin.ajax(basePath + 'invest/entrust/etrstockconnect/EtrStockConnect003/checkImportData', {fileName: fileName}, function(checkResult) {
            // 若是模板数据大于100条，提示重新导入，若是小于100条，进入多对多指令界面
            if(!checkResult.data.errorMsg){
                setTimeout( function () {
                    Joyin.confirm(checkResult.data.confirmMsg,function() {
                        var result = {};
                        result.operateType = "import";
                        result.pageModel='';
                        result.tempList = checkResult.data.correctData;
                        Joyin.dialog(JoyinUtil.getLabel("港股通指令多对多"),
                            JoyinUtil.getRealPath("invest/entrust/etrstockconnect/EtrStockConnect003/init"),
                            "98%", "95%", result,
                            function(){EtrStockConnect001.myrefresh();});
                    });
                },1000);
            }else{
                Joyin.alert(checkResult.data.errorMsg);
            }
        });
    },function() {
        table.reload();
    });
}
