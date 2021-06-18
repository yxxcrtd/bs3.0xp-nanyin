/**
 * 主页面JS
 */
var changeFlg;
var pageModel;

/**
 * 双击校验后，将风险试算标识重置
 * @returns
 */
function dbclickRefreshRisk(){
    if(BaseRiskMathComm) {
        BaseRiskMathComm.resetRiskMathFlag(); 
    }
}

$(function() {
    //用于判断当前属于单组合打开还是多组合打开
    changeFlg = '01';

    layui.use(['element', 'form'], function() {
        // 组合平台TAB切换事件
        var element = layui.element;
        element.on('tab(zlPlatform)', function(data) {
            if(data.index == 0) {
                changeFlg = '01';
            }else if(data.index == 1) {
                changeFlg = '02';
            }
        });
    });

	var init = function() {
        Split(['#top_panel_dev', '#bottom_panel_dev'], {
            sizes: [48, 44],
            minSize: 100,
            direction: 'vertical',
        });

        Split(['#left_bottom_panel_dev', '#right_bottom_panel_dev'], {
           sizes: [35, 65],
           minSize: [300,600],
            direction: 'horizontal',
        });
		// //窗口容器的高度，设置为页面左右容器的高度
		// var height = window.innerHeight - 50;
        // $('.right_panel').css('height', height);
        // $('.left_panel').css('height', height);
    }


    /**
     * 风险试算
     * @returns
     */
    function doRiskcal() {
        if(changeFlg === '01'){
           doRiskcal002();
        } else if (changeFlg === '02'){
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
        var object = EtrIbRepoinfo002.getSearchParams();
        if(object && EtrIbRepoinfo002.isNeedRiskcal()) {
            var riskdata = EtrIbRepoinfo002.getriskdata(object);
            var riskInterfUrl = EtrIbRepoinfo002.getRiskInterfUrl();
            if(riskdata && riskInterfUrl) {
                window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object,riskdata,riskMathRedisCallback002);
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
        var object = EtrIbRepoinfo003.getSearchParams();
        var checkParam = {};
        checkParam.data = EtrIbRepoinfo003.getriskdata();
        var param = [];

        if (checkParam.data.length > 0) {
            for(var index in checkParam.data){
                var item = checkParam.data[index];
                item.data.ps = object.ps;
                item.data.ccy = 'CNY';
                item.data.portfolioId = item.data.portfolioCode;
                item.data.price=object.price;
                item.data.entrustAmt=item.data.entrustAmt;
                item.data.tradeMarket = object.tradeMarket;
                item.data.repoTerm = object.repoTerm;
                item.data.vdate = object.vdate,
                item.data.mdate=object.mdate,
                
                param.push(item.data);
            }
        }
//        window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/batchRiskTrialRds/0/F07', param, checkParam,riskMathRedisCallback003);
        window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/0/RSK002/F07',object,checkParam,riskMathRedisCallback003);
    }
    /**
     * 风险试算的回调函数
     * @param object
     * @param cluflag
     * @returns
     */
    function riskMathRedisCallback003(object,cluflag) {
        EtrIbRepoinfo003.riskResultdata = object;
        EtrIbRepoinfo003.cluflag = cluflag;
        Joyin.dialog(JoyinUtil.getLabel('EtribRepoInfo003Riskcal'), '/invest/entrust/etribrepo/EtribRepoInfo003Riskcal/init', '60%', '75%',null);
    }

    /**
    * 风险试算的回调函数002
    * @param object
    * @param cluflag
    * @returns
    */
    function riskMathRedisCallback002(object,cluflag) {
        EtrIbRepoinfo002.riskResultdata = object;
        EtrIbRepoinfo002.initFlag = cluflag;
        Joyin.dialog(JoyinUtil.getLabel('EtrIbRepoinfoRiskcal002'), '/invest/entrust/etribrepo/EtrIbRepoinfoRiskcal002/init', '60%', '75%',null); 
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
        var params = EtrIbRepoinfo002.getSearchParams();
        var object = {
            businessId: params.businessId
        };

        Joyin.dialog(JoyinUtil.getLabel("EtrIbRepoinfo002.upload"), "/invest/entrust/etribrepo/EtrIbRepoinfoFile/init", "60%", "75%", object);
    }

    /**
     * 多组合画面文件上传
     */
    function doFileupload003() {
        var params = EtrIbRepoinfo003.getSearchParams();
        var object = {
            businessId: params.businessId
        };

        Joyin.dialog(JoyinUtil.getLabel("EtribRepoInfo003.fileUpload"), "/invest/entrust/etribrepo/EtrIbRepoinfoFile/init", "60%", "75%", object);
    }

    /**
     * 保存和提交
     * @returns
     */
    function saveOrSubmit(operateType) {
        if(changeFlg === '01'){
            saveOrSubmit002(operateType);
        } else if (changeFlg === '02') {
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
           var checkData = {};
           checkData.data = EtrIbRepoinfo003.getriskdata();
           if (window.BaseRiskMathComm.isCalcConditionChanged(checkData)) {
               return;
           }
       }
       var res = EtrIbRepoinfo003.getSubmitParam(operateType)

       Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', res.param, function(result1) {
           if(res.objects.length>0){
               Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtribRepoInfo003/check',res.objects,
                   function(result) {
                       Joyin.confirm(result.data,function() {
                           if (operateType === "save") {
                               WorkFlow.doSaveList(basePath+'invest/entrust/etribrepo/EtribRepoInfo003/save',res.param,"EtribRepoInfo003_btn_save","EtribRepoInfo003",function(){
                            	   EtrIbRepoinfo003.doReset();
                            	   JoyinUtil.disableButton("EtrIbRepoMain_btn_save", false);
                               });
                           } else {
                               WorkFlow.doSubmitList(basePath+'invest/entrust/etribrepo/EtribRepoInfo003/save',res.param,"EtribRepoInfo003_btn_submit","EtribRepoInfo003",function(){
                            	   EtrIbRepoinfo003.doReset();
                            	   JoyinUtil.disableButton("EtrIbRepoMain_btn_submit", false);
                               });
                           }

                       });
                   });
           }else{
               Joyin.warn("请添加组合分配！");
               return;
           }
       })

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
            if (window.BaseRiskMathComm.isCalcConditionChanged(EtrIbRepoinfo002.getriskdata())) {
                return;
            }
        }
        var object = EtrIbRepoinfo002.getSaveOrSubmitParams(operateType);
        pageModel = object.pageModel;

        Joyin.ajaxJson(basePath + "invest/entrust/EtrCommonController/check", object, function (result1) {
            Joyin.ajaxJson(basePath + "invest/entrust/etribrepo/EtrIbRepoinfo002/check", object, function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === "save") {
                        WorkFlow.doSaveList(basePath + "invest/entrust/etribrepo/EtrIbRepoinfo002/save", object, "EtrIbRepoMain_btn_save","EtrIbRepoinfo002",function() {
                            JoyinUtil.disableButton("EtrIbRepoMain_btn_save", false);
                            EtrIbRepoinfo002.doReset();
                        });
                    } else {
                        WorkFlow.doSubmitList(basePath + "invest/entrust/etribrepo/EtrIbRepoinfo002/save", object, "EtrIbRepoMain_btn_submit","EtrIbRepoinfo002",function() {
                            JoyinUtil.disableButton("EtrIbRepoMain_btn_submit", false);
                            EtrIbRepoinfo002.doReset();
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
        if(changeFlg === '01'){
            EtrIbRepoinfo002.doReset();
        } else if(changeFlg === '02'){
            EtrIbRepoinfo003.doReset();
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
        $("#EtrIbRepoMain_btn_fileupload").click(function () {
            doFileupload();
        });

        /**
         * 保存
         */
        $("#EtrIbRepoMain_btn_save").click(function () {
            saveOrSubmit("save");
        });
        /**
         * 提交
         */
        $("#EtrIbRepoMain_btn_submit").click(function () {
            saveOrSubmit("submit");
        });
        /**
         * 重置
         */
        $("#EtrIbRepoMain_btn_reset").click(function () {
            doReset();
        });
        /**
         * 风险试算
         */
        $("#EtrIbRepoMain_btn_riskcal").click(function () {
            doRiskcal();
        });
    });	

}());

//指令导入
function importEtr() {
    Joyin.uploadFile({pageId : 'EtrIbRepo004MTM'}, function(fileName) {
        Joyin.ajax(basePath + 'invest/entrust/etribrepo/EtrIbRepoInfo004/checkImportData', {fileName: fileName}, function(checkResult) {
            setTimeout( function () {
                if(checkResult.data.errorMsg){
                    Joyin.error(checkResult.data.errorMsg);
                    return;
                }
                Joyin.confirm(checkResult.data.confirmMsg,function() {
                    var result = {};
                    result.tempList = checkResult.data.correctData;
                    Joyin.dialog(JoyinUtil.getLabel("EtrRepo003"),
                        JoyinUtil.getRealPath("/invest/entrust/etribrepo/EtrIbRepoInfo004/init"),
                        "80%", "80%", result,
                        function(){/*zlglTable.reload();*/});
                });
            },1000);
        });
    },function() {
    	zlglTable.reload();
    });
};