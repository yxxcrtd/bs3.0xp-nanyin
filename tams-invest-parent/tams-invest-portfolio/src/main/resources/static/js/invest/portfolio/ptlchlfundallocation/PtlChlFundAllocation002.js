/**
 * 通道资金划拨管理
 */

(function() {
    //划款指令信息
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder']) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var dealObj = {};
                    dealObj.finprodType2 = Fin_Type;
                    dealObj.settleDate = $("#settleDate").val();
                    dealObj.tradeDate = $("#settleDate").val();
                    dealObj.ps = $("#payType").val();
                    dealObj.ccy = $("#ccy").val();
                    dealObj.tradeAmt = $("#settleAmt").val();
                    
                    // 付款账户
                    var payAcctNoList = new Array();
                    if ($("#myAcctNo").val()) {
                        var payAcctNo = {};
                        payAcctNo.id = $("#myAcctNo").val();
                        payAcctNo.text = $("#myAcctNo").text();
                        payAcctNo.object = "isUsePageAcctTrue";
                        payAcctNoList[0] = payAcctNo;
                    }
                    
                    // 收款账户
                    var recAcctNoList = new Array();
                    if ($("#counterAcctNo").val()) {
                        var recAcctNo = {};
                        recAcctNo.id = $("#counterAcctNo").val();
                        recAcctNo.text = $("#counterAcctNo").text();
                        recAcctNo.object = "isUsePageAcctTrue";
                        recAcctNoList[0] = recAcctNo;
                    }
                    
                    return {
                        'isTotalTrade':YES_OR_NO_N
                        ,'bankLendingType':null
                        ,'portfolioId':$("#portfolioId").val()
                        ,'deal':dealObj
                        ,'isUsePageAcct':true
                        ,'payAcctNoList':payAcctNoList
                        ,'recAcctNoList':recAcctNoList
                    };
                });
                if (formTodoList) {
                    window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
                }
            }
            
            // 重新初始化收付款账户
            if (!$('#myAcctNo').val() && myAcctNoInitValue) {
                $('#myAcctNo').textSelect('setValue', myAcctNoInitValue);
            }
            if (!$('#counterAcctNo').val() && counterAcctNoInitValue) {
                $('#counterAcctNo').textSelect('setValue', counterAcctNoInitValue);
            }
            // 划款类型选择通道回款增加本金和收益 add by renping 20190929 start
            payTypeChange();
            // 划款类型选择通道回款增加本金和收益 add by renping 20190929 end
        }
    }
    //初始化
    var init =function(){
        if(pageModel != PAGE_MODEL_NEW){
            initMyAcctAmount();
            iniCounterAcctAmount();
        }
    }
    
    /**
     * 保存和提交处理函数
     * @param operateType 操作类型
     * @returns 没有返回值
     */
    function saveOrSubmit(operateType){
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	            return;
	        }
	        var object = $.serializeToObject("#submitForm");
	        object.operateType = operateType;
	        object.portfolioName = $("#portfolioId option:selected").text();
	        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation002/check', object, function(result) {
	            if(operateType === 'save') {
	                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
	                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation002/save', object, "PtlChlFundAllocation002_btn_save");
	                });
	            }
	            else {
	                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
	                WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation002/save', object, "PtlChlFundAllocation002_btn_submit");
	                });
	            }
	        });
    	});
    }
    /**
     * 初始化账户余额
     */
    var initAcctAmount = function(acctNoId, acctAmountId) {
        if ($('#portfolioId').val() && $('#portfolioId').val() != ''
            && $('#' + acctNoId).val() && $('#' + acctNoId).val() != ''
            && $('#settleDate').val() && $('#settleDate').val() != ''
            && $('#ccy').val() && $('#ccy').val() != ''
            && $('#payType').val() && $('#payType').val() != ''
            && $('#chlId').val() && $('#chlId').val() != ''){
            var data = {
                    acctId:$('#' + acctNoId).val(),
                    cdate:$('#settleDate').val(),
                    portfolioId:$('#portfolioId').val(),
                    ccy:$('#ccy').val(),
                    payType:$('#payType').val(),
                    calcAcctName:acctNoId,
                    chlId:$('#chlId').val()
            };
            Joyin.ajax(basePath + 'invest/portfolio/ptlchlfundallocation/PtlChlFundAllocation002/getAmount', data, function(result) {//数据验证
                if (result && result != ''){
                    $('#' + acctAmountId).val(result).trigger('blur');
                } else {
                    $('#' + acctAmountId).val('').trigger('blur');
                }    
            });
        } else {
            $('#' + acctAmountId).val('').trigger('blur');
        }
    }
    /**
     * 初始化付款账户余额
     */
    var initMyAcctAmount = function() {
        initAcctAmount("myAcctNo", "myAcctAmount");
    }
    /**
     * 初始化收款账户余额
     */
    var iniCounterAcctAmount = function() {
        initAcctAmount("counterAcctNo", "counterAcctAmount");
    }
    // 划款类型选择通道回款增加本金和收益 add by renping 20190929 start
    /**
     * 划款类型联动
     */
    var payTypeChange = function() {
    	if($('#payType').val()==payTypeHK10){
    		$('#prinAmt').prop('disabled', false);
    		$('#intAmt').prop('disabled', false);
    	} else {
    		$('#prinAmt').val('');
    		$('#intAmt').val('');
    		$('#prinAmt').prop('disabled', true);
    		$('#intAmt').prop('disabled', true);
    	}
    }
    /**
     * 本金利息计算联动
     */
    var prinAmtChange = function() {
    	if($('#prinAmt').val() && $('#settleAmt').val()){
    		$('#intAmt').val(Number($('#settleAmt').val())-Number($('#prinAmt').val())).trigger('blur');
    	}
    }
    /**
     * 利息本金计算联动
     */
    var intAmtChange = function() {
    	if($('#intAmt').val() && $('#settleAmt').val()){
    		$('#prinAmt').val(Number($('#settleAmt').val())-Number($('#intAmt').val())).trigger('blur');
    	}
    }
    // 划款类型选择通道回款增加本金和收益 add by renping 20190929 end
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，则自动生成划款指令
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		$('#bussTrdTransferOrder_btn_generate').click();
    	}
    	// 回调方法
	    if (callback && typeof callback === 'function') {
	        callback();
	    }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  end
    /**
     * 画面加载完成
     */
    $(function() {
        
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#bussTrdTransferOrder_div').hide() ;
        }else{
        	$('#bussTrdTransferOrder_div').show() ;
        }
        /**
         * 组合、币种、划款类型变动联动事件
         */
        $(".baseInfo").change(function(){
            $('#myAcctNo').val('').trigger('change');
            $('#counterAcctNo').val('').trigger('change');
        })
        
        $("#settleDate").change(function(){
            initMyAcctAmount();
            iniCounterAcctAmount();
        })
        /**
         * 付款账户联动事件
         */
        $('#myAcctNo').change(function(){
            initMyAcctAmount();
        });
        /**
         * 收款账户联动事件
         */
        $('#counterAcctNo').change(function(){
            iniCounterAcctAmount();
        });
        // 划款类型选择通道回款增加本金和收益 add by renping 20190929 start
        /**
         * 划款类型联动事件
         */
        $('#payType').change(function(){
        	payTypeChange();
        });
        $('#prinAmt,#settleAmt').change(function(){
        	prinAmtChange();
        });
        $('#intAmt').change(function(){
        	intAmtChange();
        });
        // 划款类型选择通道回款增加本金和收益 add by renping 20190929 end
        //保存
        $('#PtlChlFundAllocation002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        //提交
        $('#PtlChlFundAllocation002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());