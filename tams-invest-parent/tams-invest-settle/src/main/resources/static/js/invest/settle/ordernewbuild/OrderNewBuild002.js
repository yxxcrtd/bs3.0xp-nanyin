(function(){
	
	//划款指令信息
    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder']) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    var dealObj = {};
                    //dealObj.finprodType2 = $("#busiType").val();
                    //dealObj.busiType = $("#busiType").val();
                    dealObj.settleDate = $("#settleDate").val();
                    dealObj.tradeDate = $("#settleDate").val();
                    dealObj.ccy = $("#ccy").val();
                    dealObj.settleAmt = $("#settleAmt").val();
                    dealObj.myAcctNo = $("#myAcctNo").val();
                    dealObj.counterAcctNo = $("#counterAcctNo").val();
                    dealObj.purpose = $("#purpose").val();
                    dealObj.isNeedSend = $("#isNeedSend").val();
                    dealObj.payAcctNo = $("#myAcctNo").val();
                    dealObj.recAcctNo = $("#counterAcctNo").val();
                    dealObj.payType = $("#payType").val();
                    
                    //划款类型
                    var payTypeList = new Array();
                    if($("#payType").val()){
                    	var payType = {};
                    	payType.id = $("#payType").val();
                    	payType.text = $("#payType").select2('data')[0].text;
                    	payTypeList[0] = payType;
                    }
                    // 付款账户
                    var payAcctNoList = new Array();
                    if ($("#myAcctNo").val()) {
                        var payAcctNo = {};
                        payAcctNo.id = $("#myAcctNo").val();
                        payAcctNo.text = $("#myAcctNo").select2('data')[0].text;
                        payAcctNo.object = $("#myAcctNo").select2('data')[0].object;
                        payAcctNoList[0] = payAcctNo;
                    }
                    
                    // 收款账户
                    var recAcctNoList = new Array();
                    if ($("#counterAcctNo").val()) {
                        var recAcctNo = {};
                        recAcctNo.id = $("#counterAcctNo").val();
                        recAcctNo.text = $("#counterAcctNo").select2('data')[0].text;
                        recAcctNo.object = $("#counterAcctNo").select2('data')[0].object;
                        recAcctNoList[0] = recAcctNo;
                    }
                    
                    return {
                        'isTotalTrade':'01'
                        ,'bankLendingType':null
                        //,'portfolioId':'123456'
                        ,'deal':dealObj
                        ,'repPageId':'OrderNewBuild002'
                        ,'isUsePageAcct':true
                        ,'payAcctNoList':payAcctNoList
                        ,'recAcctNoList':recAcctNoList
                        ,'payTypeList':payTypeList
                    };
                });
            }
            
            // 重新初始化收付款账户
            if (!$('#myAcctNo').val() && myAcctNoInitValue) {
                $('#myAcctNo').textSelect('setValue', myAcctNoInitValue);
            }
            if (!$('#counterAcctNo').val() && counterAcctNoInitValue) {
                $('#counterAcctNo').textSelect('setValue', counterAcctNoInitValue);
            }
        }
    }
	/**
	 * 初始化方法
	 */
	var OrderNewBuild002Init = function(){
		if(pageModel === PAGE_MODEL_NEW ){
			$('#settleDate').val(Date.ofDate(platDate).format('yyyy-MM-dd'));
		}
	}
    /**
     * 添加必填标志
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }
	
	/**
	 * 保存或提交按钮执行事件
	 */
	var onSaveOrSubmitAction = function(operateType){
		if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
			return;
	    }
		var object = $.serializeToObject('#OrderNewBuild002_form',true);
		if (object.bussTrdTransferOrder && object.bussTrdTransferOrder.length > 0) {
            object.actOrderId = object.bussTrdTransferOrder[0].orderId;
        }
        object.commonBusiType = 'PTL15';
		object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/settle/ordernewbuild/OrderNewBuild002/check',object, function(result) {
    		Joyin.confirm(result.data, function() {
                	if(operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/settle/ordernewbuild/OrderNewBuild002/save', object, "OrderNewBuild002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmitList(basePath + 'invest/settle/ordernewbuild/OrderNewBuild002/save', object, "OrderNewBuild002_btn_submit");
                    }
                })
            })
	}
	
	$(function() {
		OrderNewBuild002Init();
		
		// 保存按钮执行事件
		$('#OrderNewBuild002_btn_save').click(function(){
			onSaveOrSubmitAction('save');
		});
		// 提交按钮执行事件
		$('#OrderNewBuild002_btn_submit').click(function(){
			onSaveOrSubmitAction('submit');
		});
	});
}());
