/**
 * 外汇远期投资委托查询
 */
(function(){

    //---add by jjpei 20190718----start---获取风险试算画面要素与Label提示文本
    var getRiskData = function () {
        var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_01,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:FINPROD_TYPE_TG_F12,isEnabled:USE_STATUS_01}
        var object = {tradeDirection:['ps','EtrForwex001.ps'],portfolioCode:['portfolioId','EtrForwex001.portfolioId'],tradeAmount:['entrustAmt','EtrForwex001.entrustAmt'],data:obj}
        return object;
    }
    //---add by jjpei 20190718----end---获取风险试算画面要素与Label提示文本

	//交易币种 change事件   根据货币对 交易币种  获取对应币种  再联动得到两种币种可用持仓
    function changeCcy(){
    	//货币对
    	var curPair = $('#curPair').val();
    	//交易方向
    	var ccy = $("#ccy").val();
    	if(ccy&&curPair){
    		//分割货币对 获取两个币种
    		var curPairArr = curPair.split("/");
    		//交易币种为货币对的第一个币种
    		if(curPairArr[0]==ccy){
            	$('#corCcy').val(curPairArr[1]).trigger('change');
    		}
    		//交易币种为货币对的第二个币种
    		if(curPairArr[1]==ccy){
            	$('#corCcy').val(curPairArr[0]).trigger('change');
    		}
    	}else{
    		//对应币种 置空
        	$('#corCcy').val('').trigger('change');
    	}
    	//联动获取可用持仓
    	showAvailableCash();
    }
    
    //期限change事件   用于控制期限和交割日 只能二选一
    function changeTermType(){
    	//期限
    	var termType = $("#termType").val();
    	if(!termType||termType==""){
    		$('#settleDate').attr('disabled',false).trigger('change');
    	}else{
    		$('#settleDate').val('').attr('disabled',true).trigger('change');
    	}
    }
    
    //交割日change事件   用于控制期限和交割日 只能二选一
    function changeSettleDate(){
    	//交割日
    	var settleDate = $("#settleDate").val();
    	if(!settleDate||settleDate==""){
    		$('#termType').attr('disabled',false)
    	}else{
    		$('#termType').val('').attr('disabled',true);
    	}
    }
    
    //显示可用持仓  
    function showAvailableCash(){
    	//组合代码 或者  货币对 或者 交易币种 为空 
    	if(!$('#portfolioId').val()||!$('#ccy').val()||!$('#curPair').val()){
	       	 $("#proAvailableCash").val('');
	       	 $("#corCurPos").val('');
    	}else{
    		//交易币种可用持仓
        	getAssetAvailablePosition($('#ccy').val(),$('#proAvailableCash'));
        	//对应币种可用持仓
        	getAssetAvailablePosition($('#corCcy').val(),$('#corCurPos'));
    	}
    }
    
    //根据币种ccy,组合代码    获取组合可用持仓  加载到cash控件 
    function getAssetAvailablePosition(ccy,cash){
    	if($("#portfolioId").val()&& ccy){
			var data = {portfolioId:$('#portfolioId').val(),ccy:ccy};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrforwex/EtrForwex002/getProAvailableCash',data,
                    function(result) {
            	cash.val(result.cashTG).trigger('blur');
            });
		}
    }
    
    //根据委托金额 全价 货币对 交易币种获取 预计对应货币金额
    function getExpCorAmt(){
    	//委托金额，全价，货币对，交易币种  为空时   预计对应货币金额 置空
    	if(!$('#entrustAmt').val()||!$("#price").val()||
    			!$('#curPair').val()||!$('#ccy').val()){
	       	 $("#expCorAmt").val('');
    	}else{
    		// ex  用于根据货币对是否为JPY/CNY，而是否需要再乘以或者除以 ex
    		//  若交易货币是货币对中的前一个币种，如货币对为JPY/CNY，预计对应货币金额=远期全价*交易货币金额/100，对于其他货币对，预计对应货币金额=远期全价*交易货币金额；
    		//  若交易货币是货币对中后一个币种，如货币对为JPY/CNY，预计对应货币金额=交易货币金额/远期全价*100
    		var ex = 1;
    		if('JPY/CNY'==$('#curPair').val()){
    			ex = 100;
    		}
    		var expCorAmt = 0;
    		//分割货币对 获取两个币种
    		var curPairArr = $('#curPair').val().split("/");
    		// 交易方向为
    		if($('#entrustAmt').val()&&$("#price").val()&&$('#ccy').val()==curPairArr[0]){
    			expCorAmt =  JoyinMath.doDiv(JoyinMath.doMul($("#price").val(),$('#entrustAmt').val()),ex);
			}
			if($('#entrustAmt').val()&&$("#price").val()&&$('#ccy').val()==curPairArr[1]&&$("#price").val()!=0){
				expCorAmt = JoyinMath.doMul(JoyinMath.doDiv($('#entrustAmt').val(),$("#price").val()),ex);
			}
		
    		// 加载预计对应货币金额
			if(!isNaN(expCorAmt)){
	    		$("#expCorAmt").val(expCorAmt);
			}
    	}
    }
    
    
    //获取清算账户
    function selectPtlCapAcctNo(ccy){
//    	//下拉框先置空
//		$('#dealCurAcct').empty();
//		$('#corCurAcct').empty();
//		$('#cnyAcct').empty();
		//组合代码不为空
    	if($("#portfolioId").val()){
    		//是否差额清算 为是
	    	if($('#isDifSqu').val()==YES){
	    		// 是否差额清算为是时
	    		// 交易货币账户,对应货币账户 置灰 
	    		// 人民币账户 下拉框 可用 
	    		$('#dealCurAcct').attr("disabled",true);
	    		$('#corCurAcct').attr("disabled",true);
	    		$('#cnyAcct').attr("disabled",false);
//	    		// 获取人民币托管账户
//	    		selectPtlCapAcctNoAjax('CNY',$("#cnyAcct"));
	    	}
	    	//是否差额清算 为否
	    	else if($('#isDifSqu').val()==NO){
//	    		// 获取交易货币币种的托管账户
//	    		if($('#ccy').val()){
//	        		selectPtlCapAcctNoAjax($('#ccy').val(),$("#dealCurAcct"));
//	    		}
//	    		// 获取对应货币币种的托管账户
//	    		if($('#corCcy').val()){
//	        		selectPtlCapAcctNoAjax($('#corCcy').val(),$("#corCurAcct"));
//	    		}
	    		// 交易货币账户,对应货币账户 可用
	    		// 人民币账户 下拉框 置灰
	    		$('#dealCurAcct').attr("disabled",false);
	    		$('#corCurAcct').attr("disabled",false);
	    		$('#cnyAcct').attr("disabled",true);
	    	}else{
	    		// 交易货币账户,对应货币账户 人民币账户 下拉框 皆置灰
	    		// 人民币账户 下拉框 置灰
	    		$('#dealCurAcct').attr("disabled",true);
	    		$('#corCurAcct').attr("disabled",true);
	    		$('#cnyAcct').attr("disabled",true);
	    	}
    	}
    	else{
    		// 交易货币账户,对应货币账户 人民币账户 下拉框 皆置灰
    		// 人民币账户 下拉框 置灰
    		$('#dealCurAcct').attr("disabled",true);
    		$('#corCurAcct').attr("disabled",true);
    		$('#cnyAcct').attr("disabled",true);
    	}
    }
    
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            //----add by jjpei 20190718 风险试算-----start---------
			if(typeof(BaseRiskMathComm) != 'undefined') {
				var riskParam = getRiskData();
				if (pageModel == PAGE_MODEL_NEW) {
					riskParam.data = null;
				}
				window.BaseRiskMathComm.getparam(riskParam);
			}
            //----add by jjpei 20190718 风险试算-----end---------
        	// 调用共同方法  根据货币对得到全价的精度
        	window.EtrForeingExchange.setDecimalPoint('curPair','price',2);
        }
    };
    
    
    /**
     * 保存或提交
     * 
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var s;
		if(typeof(BaseRiskMathComm) != 'undefined') {
			if (BaseRiskMathComm) {
				s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
				if (!s && s != '') {
					return;
				}
				if (window.BaseRiskMathComm.isCalcConditionChanged(getRiskData())) {
					return;
				}
			}
		}
        /**
         * 获取列表参数
         */
    	// 界面控件序列化
        var object = $.serializeToObject('#searchForm');

        // 预计对应货币金额
        object.expCorAmt = $('#expCorAmt').val();
        // 本方币种可用持仓
        object.proAvailableCash = $('#proAvailableCash').val();
        // 对应币种可用持仓
        object.corCurPos = $('#corCurPos').val();
        // entrustId
        object.entrustId = $("#entrustId").val();
        // 交割日区间 开始日
        object.settleVdate = $('#settleDate').startDate();
        // 交割日区间 结束日
        object.settleMdate = $('#settleDate').endDate();
        // 投资管理人
        object.bussEtrInvestPersonInfoDto.invManagerId = $('#bussEtrInvestPersonInfoDto_invManagerId').val();
        // 交易员
        if(object.bussEtrInvestPersonInfoDto.trader != null && object.bussEtrInvestPersonInfoDto.trader != ''){
        	object.bussEtrInvestPersonInfoDto.trader = object.bussEtrInvestPersonInfoDto.trader.join(",");
        }
        //文件
        if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo();
    		if(object.fileArray && _.isArray(object.fileArray)){
    			$.each(object.fileArray, function(index,obj){
    				obj.seqNo = obj.fileNo;
    			});
    		}
    	}
        // 委托金额
        object.entrustAmt = $('#entrustAmt').val();
        // 操作类型
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.inputValue1 = PINAN_ASSET_TYPE_F14;
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
        	Joyin.ajaxJson(basePath + 'invest/entrust/etrforwex/EtrForwex002/checkAuthorization', object, function(result2) {
				Joyin.ajaxJson(basePath + 'invest/entrust/etrforwex/EtrForwex002/check',object,
					function(result) {
	//        		if(checkAssetAvailablePosition()){
						Joyin.confirm(result.data,function() {
							if (operateType === 'save') {
								WorkFlow.doSaveList(basePath+'invest/entrust/etrforwex/EtrForwex002/save',object,"EtrForwex002_btn_save");
							} else {
								WorkFlow.doSubmitList(basePath+'invest/entrust/etrforwex/EtrForwex002/save',object,"EtrForwex002_btn_submit");
							}
						});
	//        		}
					});
        	});
		});
    }
    
    /**
     * 编辑按钮事件
     */
    $(function () {
    	
    	/**
    	 * 保存
    	 */
    	$('#EtrForwex002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        /**
         * 提交
         */
        $('#EtrForwex002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        /**
         * add by jjpei 20190718 风险试算
         */
        $('#EtrForwex002_btn_riskCalculate').click(function() {
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getRiskData());
			var object = $.serializeToObject('#searchForm');
			// 交割日区间 开始日
			object.settleVdate = $('#settleDate').startDate();
			// 交割日区间 结束日
			object.settleMdate = $('#settleDate').endDate();
			window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F14',object,getRiskData());
        });

        /**
         * 组合代码change事件
         */
    	$("#portfolioId").change(function(){
    		showAvailableCash();
    		selectPtlCapAcctNo();
        });
    	
    	/**
         * 货币对change事件
         */
        $("#curPair").change(function(){
        	// 调用共同方法  根据货币对得到全价的精度
        	window.EtrForeingExchange.setDecimalPoint('curPair','price',2);
        });
        
    	/**
         * 交易币种change事件
         */
        $("#ccy").change(function(){
        	changeCcy();
        	getExpCorAmt();
        	selectPtlCapAcctNo();
        });
        
        /**
         * 期限change事件
         */
        $("#termType").change(function(){
        	changeTermType();
        });
        
        /**
         * 交割日区间change事件
         */
        $("#settleDate").change(function(){
        	changeSettleDate();
        });
        

        /**
         * 委托金额change事件
         */
    	$("#entrustAmt").change(function(){
    		getExpCorAmt();
        });
    	
    	/**
    	 * 全价change事件
    	 */
    	$("#price").change(function(){
    		getExpCorAmt();
        });
    	
    	/**
    	 * 是否差额清算change事件
    	 */
    	$('#isDifSqu').change(function(){
    		selectPtlCapAcctNo();
        });
    	
    	/**
    	 * 部分控件查看时候是否置灰问题根据控件控制
    	 */
    	changeTermType();
    	changeSettleDate();
    	selectPtlCapAcctNo();
    	
    });
    
    
}());