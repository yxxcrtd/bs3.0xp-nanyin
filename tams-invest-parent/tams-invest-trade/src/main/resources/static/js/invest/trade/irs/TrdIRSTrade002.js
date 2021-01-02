/**
 * 利率互换交易
 */
(function(){

    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),assetType:FINPROD_TYPE_F14,tradeAmount:$('#prin').val()?$('#prin').val()*10000:$('#prin').val(),isEnabled:USE_STATUS_01,tradeDirection:'T01'}
        var object = {valDate:['tradeDate','TrdIRSTrade.tradeDate'],portfolioCode:['portfolioId','TrdIRSTrade.portfolioId'],tradeAmount:['prin','TrdIRSTrade.prin'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本
	/**
	 * 根据起息日，到期日  得到期限(天)
	 */
	function calcTerm(){
		// 起息日到期日都不为空
		var vdate = $('#vdate').val();
		var mdate = $('#mdate').val();
		if(vdate&&mdate){
			// 期限（天）=到期日-起息日
			 var days = (Date.ofDate(mdate) - Date.ofDate(vdate))/ (1000 * 60 * 60 * 24);
			 $('#term').val(days);
		}
		
	}
	
	/**
	 * 计算结算费
	 */
	function calFeeAmtB07(){
		if($('#vdate').val() && $('#mdate').val() && $('#prin').val()){
            object = $.serializeToObject('#submitForm');
            Joyin.ajaxJson(basePath+ "invest/trade/irs/TrdIRSTrade002/calFeeAmtB07",object,function(result){
                if(result) {
                    $('#feeB07').val(result).trigger('blur');
                }
            });
        } else {
            $('#feeB07').val('').trigger('blur');
        }
	}
	
	window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(typeof(BaseRiskMathComm) != 'undefined') {
            //----add by jjpei 20190718 风险试算----start----
            var riskParam = getRiskData();
            if (pageModel==PAGE_MODEL_NEW){
                riskParam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskParam);
            //----add by jjpei 20190718 风险试算----end----
            }
        }
    };
    
    
	function saveOrSubmit(operateType) {
      var s;
      if(typeof(BaseRiskMathComm) != 'undefined') {
      if(BaseRiskMathComm) {
          s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
          if(!s && s != ''){
              return;
          }
          if (window.BaseRiskMathComm.isCalcConditionChanged(getRiskData())){
              return;
                }
          }
      }
      // 检查生成划款指令参数是否发生变化
      if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
          return;
      }
      /**
       * 获取列表参数
       */
      var object = $.serializeToObject('#submitForm');
      if (FileCommon) {
          object.uploadFileList = FileCommon.getUploadFileInfo();
      }

      object.portfolioId = $('#portfolioId').val();
      object.operateType = operateType;
      object.inputValue1 = PINAN_ASSET_TYPE_F16;
      if ($('#trdInvestPersonInfo_counterId').select2('data') && $('#trdInvestPersonInfo_counterId').select2('data')[0]) {
          object.counterId=$('#trdInvestPersonInfo_counterId').select2('data')[0].id;
      }
      object.ps = object.irsType;
      object.prinAmt =object.prin;
      Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
          Joyin.ajaxJson(basePath + 'invest/trade/irs/TrdIRSTrade002/check', object, function (result) {
        	  //授信占用额度
//              Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/delLinesDeal',result1.data,function(result2) {});
              Joyin.confirm(result.data, function () {
                  var elementId;
                  object.riskCreditListCommonDto = result1.data;
                  if (operateType === 'save') {
                      elementId = "TrdIRSTrade002_btn_save";
                      WorkFlow.doSaveList(basePath + 'invest/trade/irs/TrdIRSTrade002/save', object, elementId);
                  } else {
                      elementId = "TrdIRSTrade002_btn_submit";
                      WorkFlow.doSubmitList(basePath + 'invest/trade/irs/TrdIRSTrade002/save', object, elementId);
                  }
              });
          });
      });
  }
	
	
	
	$(function () {
		/**
         * 保存
         */
        $('#TrdIRSTrade002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#TrdIRSTrade002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        //风险试算
        $('#TrdIRSTrade002_btn_risk').click(function () {
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
            //-----------add by jjpei 2020-01-07 风险新版本 start----------
            var object = $.serializeToObject('#submitForm', true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F16', object,getRiskData());
            //-----------add by jjpei 2020-01-07 风险新版本 end----------
            window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

        })
        
        
		/**
		 * 起息日change事件
		 */
		$('#vdate').change(function(){
			//计算期限
			calcTerm();
			//首次付息日
			$('#firstPayDate').val($('#vdate').val());
			// 计算结算费
			calFeeAmtB07();
        });

		/**
		 * 到息日change事件
		 */
		$('#mdate').change(function(){
			//计算期限
			calcTerm();
			// 计算结算费
			calFeeAmtB07();
        });
		
		/**
		 * 名义本金change事件
		 */
		$('#prin').change(function(){
			// 计算结算费
			calFeeAmtB07();
        });
		
		
	});
	
}());