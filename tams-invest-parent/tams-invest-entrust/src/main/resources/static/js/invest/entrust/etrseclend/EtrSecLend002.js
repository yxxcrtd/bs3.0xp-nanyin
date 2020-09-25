/**
 * 债券借贷投资委托新建
 */
(function() {
    var initFlag = true;
    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {tradeDirection:$('#busiPs').val(),timePoint:MY_SUP_TIME_TYPE_01,valDate:sysDate,assetCode:$('#finprodId').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#totalAmt').val()?$('#totalAmt').val()*10000:null,isEnabled:USE_STATUS_01}
        if ($("#busiPs").val() == BACK_LEND_DIRECTION_INTO ) {
            obj.assetType=FINPROD_TYPE_TG_F29;
        }
        if ($("#busiPs").val() == BACK_LEND_DIRECTION_OUT ) {
            obj.assetType=FINPROD_TYPE_TG_F30;
        }
        var object = {tradeDirection:['busiPs','EtrSecLend.busiPs'],portfolioCode:['portfolioId','EtrSecLend.portfolioId'],tradeAmount:['totalAmt','EtrSecLend.totalAmt'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本
    /**
     * 保存或提交
     * 
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var s;
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getRiskData())){
                return;
            }
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.entrustStatus = $('#entrustStatus').val();
        object.objectAvailablePosition = $("#bussTrdAvailableCashQuery_assetPosition").val(); // 资产可用持仓
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        if(object.busiRate != null && object.busiRate != ''){
            object.busiRate = JoyinMath.doDiv(object.busiRate,100,6);
        }
        if(object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader != ''){
            object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader.join(",");
        }
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        object.inputValue1 = PINAN_ASSET_TYPE_F12; 
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrseclend/EtrSecLend002/checkAuthorization', object, function(result2) {
	            Joyin.ajaxJson(basePath + 'invest/entrust/etrseclend/EtrSecLend002/check',object,
	                function(result) {
	                    Joyin.confirm(result.data,function() {
	                        if (operateType === 'save') {
	                            WorkFlow.doSaveList(basePath+'invest/entrust/etrseclend/EtrSecLend002/save',object,"EtrSecLend002_btn_save");
	                        } else {
	                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrseclend/EtrSecLend002/save',object,"EtrSecLend002_btn_submit");
	                        }
	                    });
	                });
        	});
        });
    }
    //查询组合可用持仓
    var getPtlSecPositionInfo = function(){
      var params = {};
      params.portfolioId = $("#portfolioId").val();
      params.calendarId = $("#calendarId").val();
      params.ccy = $("#ccy").val();
      params.tradeDate = sysDate;
      params.finprodId = $("#finprodId").val();
      params.secAcctId = $("#secAcctId").val();
      if (window.BussTrdAvailableCashQuery && window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"]) {
        window.BussTrdAvailableCashQuery["bussTrdAvailableCashQuery"].updateCash(function () {
            return params
        });
    }}
    
   
    //添加风险试算验证
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            var riskParam = getRiskData();
            if (pageModel==PAGE_MODEL_NEW){
                riskParam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskParam);
            window.BussTrdAvailableCashQuery['bussTrdAvailableCashQuery'].showPosition(true);//只展示持仓
            init();
         // 设置添加质押券共通参数
            setBussRepoSecInput();
        }
    };
    
    /**
     * 设置添加质押券共通参数
     */
    var setBussRepoSecInput = function () {
        var data = {
            portfolioId: $('#portfolioId').val(),
            ps: $("#busiPs").val(),
            secAcctId: $('#secAcctId').val(),
            chlId: ''
        }
        var object = {
            portfolioId: ['portfolioId', 'EtrSecLend.portfolioId'],
            ps: ['ps', 'EtrSecLend.busiPs'],
            secAcctId: ['secAcctId', 'EtrSecLend.secAcctId'],
            data: data
        }
        if (window.BussRepoSec && window.BussRepoSec["bussRepoSec"]) {
            window.BussRepoSec["bussRepoSec"].setInputParam(object);
        }
    };
    //初始化页面
    var init= function(){
        $("#avaStandardBondPositionDiv").hide();
        $("#pledgeStamps_div").hide();
        $('#busiPs').change();
    }
    
    /**
     * 绑定事件
     */
    $(function() {
        /**
         * 保存
         */
        $('#EtrSecLend002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrSecLend002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrSecLend002_btn_riskcal').click(function(){
            // window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
            var object = $.serializeToObject('#searchForm');
            object.entrustAmt=$('#totalAmt').val()?$('#totalAmt').val()*10000:null;
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F12',object,getRiskData());
        });
        /**
         * 交易场所
         */
        $('#tradeMarket').change(function() {
            
        });
    
        /**
         * 借贷方向
         */
        $('#busiPs').change(function() {
            var busiPs= $('#busiPs').val();
           // 设置添加质押券共通参数
            setBussRepoSecInput();
            if(BACK_LEND_DIRECTION_INTO == busiPs){
                
                $("#pledgeStamps_div").show();
                $('#objectAvailablePosition').val(null).trigger('blur');
                $("#avaStandardBondPositionDiv").hide();
                
            }else if(BACK_LEND_DIRECTION_OUT == busiPs){
                $("#pledgeStamps_div").hide();
                getPtlSecPositionInfo();
                $("#avaStandardBondPositionDiv").show();
            }

        });
        /**
         * 交易组合
         */
        $("#portfolioId").on("change", function (e, isInit) {
            getPtlSecPositionInfo();
         // 设置添加质押券共通参数
            setBussRepoSecInput();
         // 债券投资 改变组合 需联动 证券账户 ,证券托管户应清空选择项
            if (!isInit && pageModel !=PAGE_MODEL_REFERENCE&&pageModel !=PAGE_MODEL_EDIT) {
                $("#secAcctId").val('').trigger("change");
            }
        });
        
        /**
         * 证券托管户
         */
        $('#secAcctId').change(function(){
            getPtlSecPositionInfo();
         // 设置添加质押券共通参数
            setBussRepoSecInput();
        });
        /**
         * 标的债券
         */
        $('#finprodId').change(function(){
            getPtlSecPositionInfo();
        });
        
        /**
         * 交易日历
         */
        $('#calendarId').change(function(){
            getPtlSecPositionInfo();
        });
        /**
         * 币种
         */
        $('#ccy').change(function() {
            getPtlSecPositionInfo();
        });
      
      
    });
}());
