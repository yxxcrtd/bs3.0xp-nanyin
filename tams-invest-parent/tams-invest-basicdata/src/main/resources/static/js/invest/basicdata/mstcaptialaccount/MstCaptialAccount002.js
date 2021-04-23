//var file;
(function() {
	/**
     * 画星星
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
	//存放多文件上传的文件信息
	var fileArray =[];
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#informationForm',true);
    	if(FileCommon) {
    		object.fileArray = FileCommon.getUploadFileInfo()
    	}
    	if(object.accountType){
    	    object.accountType = object.accountType.join(",");
    	}
    	
    	object.acctNo = object.largePayno + ':' + object.busiAcctNo;
        object.remark=$('#remark').val();
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount002/save', object, "MstCaptialAccount002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount002/save', object, "MstCaptialAccount002_btn_submit");
                }
            });
        });
    }
    
    var editable = pageModel != PAGE_MODEL_REFERENCE;
    var init = function(){
    	var wfl_operType = $("#wfl_operType").val();
    	if(pageModel==PAGE_MODEL_EDIT){
    		if(wfl_operType == null || wfl_operType != 'NEW'){
    			$('#busiAcctNo').attr("disabled",true);
        		$('#largePayno').attr("disabled",true);
        		$('#inOutBank').attr("disabled",true).trigger('change');
        		
        		$('#accountNature').attr("disabled",true);
        		$('#isCalint').attr("disabled",true).trigger('change');
        		
        		$('#effectDate').attr("disabled",true);
        		$('#basis').attr("disabled",true);
        		$('#rateType').attr("disabled",true);
        		$('#rate').attr("disabled",true);
        		
        		$('#indexId').attr("disabled",true);
        		$('#coefficient').attr("disabled",true);
        		$('#spreadRate').attr("disabled",true);

        		$('#ccy').attr("disabled",true);
    		}
    	}
    	var ccy = $('#ccy').val();
        if(ccy == CNY){
        	$('#swiftCode').attr("required",false).trigger('change');
        }
        else{
        	$('#swiftCode').attr("required",true).trigger('change');
        }
    	
    	if(pageModel == PAGE_MODEL_REFERENCE && dooubleRecord && dooubleRecord =='01'
            && $('#isFirstApprovalNode').val() && $('#isFirstApprovalNode').val() =='Y') {
        	if(wfl_operType === 'NEW') {
            	$('#ccy').val('CNY').trigger("change");
            	/*$('#swiftCode').val('');   BSTOH-357  资金账户管理-新建，双录审批时，应该将Swift Code字段置灰，不可编辑。*/
            	$('#ccy').attr('disabled',false);
                $('#ccy').attr('data-ignore', true);
                
            	$('#bankDep').val('').trigger("change");
            	$('#bankDep').attr('disabled',false);
                $('#bankDep').attr('data-ignore', true);
                
            	$('#largePayno').val('').trigger("change");
            	$('#largePayno').attr('disabled',false);
                $('#largePayno').attr('data-ignore', true);
        	} 
        	else if (wfl_operType === 'MODIFY') {
            	$('#bankDep').val('').trigger("change");
            	$('#bankDep').attr('disabled',false);
                $('#bankDep').attr('data-ignore', true);
        	}
    	}
    	
    	if(pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
    		//新建或者修改时正常显示账号密码和交易密码
    		$('#accPass').attr("type",'text').trigger('change');
    		$('#tradePass').attr("type",'text').trigger('change');
    	} 
    	else {
    		//修改时已****显示账号密码和交易密码
    		$('#accPass').attr("type",'password').trigger('change');
    		$('#tradePass').attr("type",'password').trigger('change');
    		
    	}
    	
    	if(pageModel === PAGE_MODEL_NEW){
    		//初始化时需要隐藏部分属性，根据实际选择项带出
    		$('#effectDate_div').toggle(false);
    		$('#basis_div').toggle(false);
    		$('#rateType_div').toggle(false);
    		
        	$('#rate_div').toggle(false);
    		$('#indexId_div').toggle(false);
    		$('#coefficient_div').toggle(false);
    		$('#spreadRate_div').toggle(false);
    		
    		$('#qszh_div').toggle(false);
    	} else {
        	if($('#isCalint').val() ===YES_OR_NO_Y) {
        		$('#effectDate_div').toggle(true);
        		$('#basis_div').toggle(true);
        		$('#rateType_div').toggle(true);
        	} else {
        		$('#effectDate_div').toggle(false);
        		$('#basis_div').toggle(false);
        		$('#rateType_div').toggle(false);
        	}
        	
        	if($('#rateType').val() === INT_TYPE_01) {            	
            	$('#rate_div').toggle(true);
        		$('#indexId_div').toggle(false);
        		$('#coefficient_div').toggle(false);
        		$('#spreadRate_div').toggle(false);
        	} 
        	else if($('#rateType').val() === INT_TYPE_02){
            	$('#rate_div').toggle(false);
        		$('#indexId_div').toggle(true);
        		$('#coefficient_div').toggle(true);
        		$('#spreadRate_div').toggle(true);
        	}
        	else {
            	$('#rate_div').toggle(false);
        		$('#indexId_div').toggle(false);
        		$('#coefficient_div').toggle(false);
        		$('#spreadRate_div').toggle(false);
        	}
            var accountType = $('#accountType').val();
            if(accountType && accountType != null && accountType.indexOf(ACCT_TYPE_A07)>=0) {
        		$('#qszh_div').toggle(true);
            } else if (accountType && accountType != null && accountType.indexOf(ACCT_TYPE_B24)>=0){
        		$('#qszh_div').toggle(true);
        	} else {
        		$('#qszh_div').toggle(false);
        	}
    		
    	}

    	
        star();
    };
        
        
    $(function() {
    	init();
        // 按钮事件
        $('#MstCaptialAccount002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstCaptialAccount002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#largePayno').change(function(){
            var largePayno = $('#largePayno').val();
            if(largePayno.length > 12){
                Joyin.error(JoyinUtil.getMessage("MST_CAP_COUNT_1"));
            }
        });
        $('#ccy').change(function(){
            var ccy = $('#ccy').val();
            if(ccy == CNY){
            	$('#swiftCode').attr("required",false).trigger('change');
            }
            else{
            	$('#swiftCode').attr("required",true).trigger('change');
            }
            
            if($('#accountNature').val()===ACCOUNT_NATURE_02
                    ||ccy != CNY) {
                $('#largePayno').attr("required",false);
            } else {
                $('#largePayno').attr("required",true);
            }
            
            var wfl_operType = $("#wfl_operType").val();
            if((wfl_operType === 'NEW'||wfl_operType === 'MODIFY') && pageModel == PAGE_MODEL_REFERENCE 
                	&& dooubleRecord && dooubleRecord =='01' && $('#isFirstApprovalNode').val() 
                	&& $('#isFirstApprovalNode').val() =='Y') {
            	if(ccy == CNY){
                	$('#swiftCode').attr('disabled',true);
                    $('#swiftCode').attr('data-ignore', false);
            	} else {
            		/*  BSTOH-357  资金账户管理-新建，双录审批时，应该将Swift Code字段置灰，不可编辑。
                	$('#swiftCode').attr('disabled',false);
                    $('#swiftCode').attr('data-ignore', true);
                    */
                    
            		$('#swiftCode').attr('disabled',true);
                    $('#swiftCode').attr('data-ignore', false);
            	}
            }

            star();
        });
        
        //当“账户性质”为非银行账户时，“开户网点/大额支付行号”非必填
        $('#accountNature').change(function(){
            var ccy = $('#ccy').val();
        	if($('#accountNature').val()===ACCOUNT_NATURE_02
        	        ||ccy != CNY) {
        		$('#largePayno').attr("required",false);
        	} else {
        		$('#largePayno').attr("required",true);
        	}
        	
        	star();
        });
        
        $('#isCalint').change(function(){
    		$('#effectDate').val('').trigger('change');
    		$('#basis').val('').trigger('change');
    		$('#rateType').val('').trigger('change');
    		    		
        	if($('#isCalint').val() ===YES_OR_NO_Y) {
        		$('#effectDate_div').toggle(true);
        		$('#basis_div').toggle(true);
        		$('#rateType_div').toggle(true);
        		debugger;
            	//默认固定利率
            	$('#rateType').val(INT_TYPE_01).trigger('change');
            	//默认计息基础为360/A
            	$('#basis').val(BASIS_A_360).trigger('change');
        	} else {
        		$('#effectDate_div').toggle(false);
        		$('#basis_div').toggle(false);
        		$('#rateType_div').toggle(false);
        	}

        });
        
        $('#rateType').change(function(){
        	$('#rate').val('').trigger('change');
        	$('#indexId').val('').trigger('change');
        	$('#coefficient').val('').trigger('change');
        	$('#spreadRate').val('').trigger('change');
        	
        	if($('#rateType').val() === INT_TYPE_01) {            	
            	$('#rate_div').toggle(true);
        		$('#indexId_div').toggle(false);
        		$('#coefficient_div').toggle(false);
        		$('#spreadRate_div').toggle(false);
        		
        		//默认固定利率0.3
        		$('#rate').val(RATE_DEF_DEFAULT);
        	} 
        	else if($('#rateType').val() === INT_TYPE_02){
            	$('#rate_div').toggle(false);
        		$('#indexId_div').toggle(true);
        		$('#coefficient_div').toggle(true);
        		$('#spreadRate_div').toggle(true);
        	}
        	else {
            	$('#rate_div').toggle(false);
        		$('#indexId_div').toggle(false);
        		$('#coefficient_div').toggle(false);
        		$('#spreadRate_div').toggle(false);
        	}
        });
        
        $('#accountType').change(function(){
        	$('#ownDept').val('').trigger('change');
        	$('#accPass').val('').trigger('change');
        	$('#tradePass').val('').trigger('change');
        	var accountType = $('#accountType').val();
        	if(accountType && accountType!= null && accountType.indexOf(ACCT_TYPE_A07)>=0) {
        		$('#qszh_div').toggle(true);
        	} else if (accountType && accountType!= null && accountType.indexOf(ACCT_TYPE_B24)>=0){
        		$('#qszh_div').toggle(true);
        	} else {
        		$('#qszh_div').toggle(false);
        	}
        	
        	if(pageModel === PAGE_MODEL_NEW){
        		if(accountType &&  accountType!= null && (accountType.indexOf(ACCT_TYPE_A01)>=0
               	     || accountType.indexOf(ACCT_TYPE_A07)>=0) 
        			 || accountType.indexOf(ACCT_TYPE_B24)>=0) {
               	    $('#isCalint').val(YES_OR_NO_Y).trigger('change');  
               	} else {
               	    $('#isCalint').val(YES_OR_NO_N).trigger('change'); 
               	}
        	}
        });
        
        $('#indexId').change(function(){
        	if($("#indexId").val()&& $("#indexId").val()!='' && $("#indexId").val() != null) {
                if ($("#indexId").select2('data')[0].object) {
                	$("#indexSource").val($("#indexSource").select2('data')[0].object);
                }
        	}

        });
        
        //双录校验
        JoyinWorkFlow.doCheckOnSubmit = function (object) {
        	var param = $.serializeToObject('#informationForm', true);
            if(param.accountType){
                param.accountType = param.accountType.join(",");
            }
        	param.pageModel = pageModel;
        	param.acctNo = param.largePayno + ':' + param.busiAcctNo;
        	param.remark=$('#remark').val();
        	param.wflOperType = $("#wfl_operType").val();
            var checkResult = false;
            
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstcaptialaccount/MstCaptialAccount002/check', param, function(result) {
            	checkResult = true;
            },false);

            return checkResult;
        };
    });
}());
