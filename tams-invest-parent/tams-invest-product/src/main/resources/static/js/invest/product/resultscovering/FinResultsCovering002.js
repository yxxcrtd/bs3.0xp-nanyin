(function() {

	/**
	 * 进行保存和提交操作
	 * @param operateType 操作类型
	 * @returns
	 */
	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#basicInfoForm',true);
		if(pageModel==PAGE_MODEL_NEW){
			object.prodId == "" ? object.prodId = $("#prodId").val() : object.prodId;
		}
		//设置pageModel和operateType
		object.pageModel = pageModel;
		object.operateType = operateType;
		//对保存事件进行验证
		//对保存事件进行验证
		Joyin.ajaxJson(basePath + 'invest/product/resultscovering/FinResultsCovering002/check', object, function(result) {
			if(operateType === 'save'){
				//保存工作流
				Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
					WorkFlow.doSaveList(basePath + 'invest/product/resultscovering/FinResultsCovering002/save', object, "FinResultsCovering002_btn_save");
				});
			}else{
				//提交工作流
				Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
					WorkFlow.doSubmitList(basePath + 'invest/product/resultscovering/FinResultsCovering002/save', object, "FinResultsCovering002_btn_submit");
				});
			}
		});
	};
	/**
	 * 界面初始化时进行调用
	 */
	var init = function(){
//		$("#fundManager").attr("disabled",true);
//		$('#finprodChiType').attr("disabled",true);
//		if(pageModel === PAGE_MODEL_NEW){
//		if (!$('#publishDate').val()) {
//		getIplatDate();
//		}
//		}else{
//		$('#finprodMarketId').attr("disabled",true);
//		$('#valDate').attr("disabled",true);
//		}
		//回补形式 为 自动回补，应回补金额展示，否则不展示 
		if($('#coveringForm').val() == '02'){
			$('#theoryAmountDiv').show();
		}else{
			$('#theoryAmountDiv').hide();
		}
		if(pageModel === PAGE_MODEL_NEW) {
		    $('#layFinprodIdDiv').hide(); 
		} else {
		    if($("#isLay").val()&&$("#isLay").val() == YES_NO_YES) {
		        $('#layFinprodIdDiv').show(); 
		    } else {
		        $('#layFinprodIdDiv').hide(); 
		    }
		}
		
	};

	/**
	 * 下拉框进行选择时进行一些联动操作
	 */
	var prodIdChange = function() {
		var prodId = $("#prodId").val();
		var object = $('#prodId').select2('data')[0].object;
		if(object) {
		    objectArr= object.split(',')
		    $("#isLay").val(objectArr[0]); 
		    $("#portfolioId").val(objectArr[1]); 
		} else {
		    $("#isLay").val(''); 
		    $("#portfolioId").val(''); 
		}
		
		if(pageModel === PAGE_MODEL_NEW) {
		    $('#layFinprodId').val('');  
		}
		if($("#isLay").val()&& $("#isLay").val() == YES_NO_YES) {
		    $('#layFinprodIdDiv').show(); 
		} else {
		    $('#layFinprodIdDiv').hide(); 
		    selectBaseInfo();
		}
		
		$("#coveringWay").val("").trigger("change");
		$('#coveringForm').val("").trigger("change");
		$('#covCycleStartDate').val("");
		$('#covCycleEndDate').val("");
		$('#ccy').val("").trigger("change");
		
		$("#coveringWay").attr("disabled",true);
		$('#coveringForm').attr("disabled",true);
		$("#covCycleStartDate").attr("disabled",true);
		$('#covCycleEndDate').attr("disabled",true);
		$('#ccy').attr("disabled",true);
	};
	
	
	var layFinprodIdChange = function() {	    
	    if($("#layFinprodId").val()!=null && $("#layFinprodId").val()!='') {
	        $("#prodStyle").val($('#layFinprodId').select2('data')[0].text);
	        selectBaseInfo();  
	    } else {
	        $("#prodStyle").val("");
	    }
	}
	
	var selectBaseInfo = function(){
	    var prodId = $("#prodId").val();
	    var object = {'prodId':prodId,'dates':platDate};

	    if($("#isLay").val()&& $("#isLay").val() == YES_NO_YES) {
	        object.prodId = $("#layFinprodId").val();
	    } 
        Joyin.ajax(basePath + 'invest/product/resultscovering/FinResultsCovering002/selectBaseInfo', object, function(data){
            if(data){
                var coveringWay=data.coveringWay;
                var coveringForm=data.coveringForm;
                var covCycleStartDate=Date.ofDate(data.covCycleStartDate).format('yyyy-MM-dd');
                var covCycleEndDate=Date.ofDate(data.covCycleEndDate).format('yyyy-MM-dd');
                var ccy=data.ccy;
                $("#coveringWay").val(coveringWay).trigger("change");
                $('#coveringForm').val(coveringForm).trigger("change");
                $('#covCycleStartDate').val(covCycleStartDate).trigger("change");
                $('#covCycleEndDate').val(covCycleEndDate).trigger("change");
                $('#ccy').val(ccy).trigger("change");
            }
        }); 
	}
	

	var coveringWayChange = function() {
		var coveringWay=$("#coveringWay").val();
		if(coveringWay){
			if(coveringWay===ANAPLEROTIC_RULE_01){
				$("#afterCovFloatFee").attr("disabled",true);
			}else if(coveringWay===ANAPLEROTIC_RULE_02){
				$("#afterCovFixFee").attr("disabled",true);
			}else if(coveringWay===ANAPLEROTIC_RULE_03){
				$("#afterCovFixFee").attr("disabled",false);
				$("#afterCovFloatFee").attr("disabled",false);
			}
		}
	};

	var afterCovFixFeeChange = function() {
		var afterCovFixFee=$("#afterCovFixFee").val();
		var afterCovFloatFee=$("#afterCovFloatFee").val();
		if(afterCovFixFee){
			$("#actualAmount").val("");
			$("#actualAmount").val(afterCovFixFee);
			if(afterCovFloatFee){
				$("#actualAmount").val(Number(afterCovFixFee)+Number(afterCovFloatFee));
			}
		}
	};

	var afterCovFloatFeeChange = function() {
		var afterCovFloatFee=$("#afterCovFloatFee").val();
		var afterCovFixFee=$("#afterCovFixFee").val();
		if(afterCovFloatFee){
			$("#actualAmount").val("");
			$("#actualAmount").val(afterCovFloatFee);
			if(afterCovFixFee){
				$("#actualAmount").val(Number(afterCovFixFee)+Number(afterCovFloatFee));
			}
		}
	};
	/**
	 * 画面加载完成
	 */
	$(function() {
		//初始化
		init();
		//绑定保存事件
		$("#FinResultsCovering002_btn_save").click(function(){
			saveOrSubmit('save');
		});
		//绑定提交事件
		$("#FinResultsCovering002_btn_submit").click(function(){
			saveOrSubmit('submit');
		});
		//绑定下拉框改变事件
		$('#prodId').change(function() {
		    prodIdChange();
		});
		
	      //绑定下拉框改变事件
        $('#layFinprodId').change(function() {
            layFinprodIdChange();
        });

		$('#coveringWay').change(function() {
			coveringWayChange();
		});
		//固定管理费回补金额变化时
		$('#afterCovFixFee').change(function() {
			afterCovFixFeeChange();
		});
		//浮动管理费回补金额变化时
		$('#afterCovFloatFee').change(function() {
			afterCovFloatFeeChange();
		});
	});
}());