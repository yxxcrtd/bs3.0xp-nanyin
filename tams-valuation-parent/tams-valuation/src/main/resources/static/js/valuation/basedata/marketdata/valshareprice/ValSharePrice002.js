$(function() {
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        /*object.curveCode=$("#curveCode").val();*/
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/valshareprice/ValSharePrice002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'valuation/basedata/marketdata/valshareprice/ValSharePrice002/save', object, "ValSharePrice002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'valuation/basedata/marketdata/valshareprice/ValSharePrice002/save', object, "ValSharePrice002_btn_submit");
                }
            });
        });
    };
  

  //画面初始化
    var init = function(){
    	//编辑模式下  资产代码与交易日期不可以修改
        if(pageModel==PAGE_MODEL_EDIT){
            $('#sharesId').attr("disabled",true);
            $('#dealDate').attr("disabled",true);
        }
        
         //*  资产代码
        $('#sharesId').on('change', function () {
            if ($('#sharesId').val()) {
                var data = $('#sharesId').select2('data')[0].object;
                $('#finprodAbbr').val(data.finprodAbbr).trigger('change');
                if($('#dealDate').val()){
                	getLatelyShareById();
        		}
            }else{
            	clean();
            }
        });
        
        //* 交易日期
    	$('#dealDate').on('change', function () {
    		if ($('#sharesId').val()) {
    			getLatelyShareById();
    		}else{
    			clean();
    		}
    	});
    	
    	//前收盘价
    	$('#preClosePrice').on('change', function () {
    		//收盘价作为分母计算涨跌幅，所以不能为0
			if ($('#preClosePrice').val() == 0){
                Joyin.error("收盘价不能为0！");
                $('#preClosePrice').val(null);
				return;
			} 
    		modifyChange();
    		if($('#dealDate').val()){
    			modifyFactorAdj();
    		}
    		modifyAdj();
    	});
    	
    	//收盘价
    	$('#closePrice').on('change', function () {
    		modifyChange();
    		modifyAdj();
    	});
    	
    	//开盘价
    	$('#openPrice').on('change', function () {
    		modifyAdj();
    	});
    	
    	//最高价
    	$('#highPrice').on('change', function () {
    		modifyAdj();
    	});
    	
    	
    	//最低价
    	$('#lowPrice').on('change', function () {
    		modifyAdj();
    	});
    	
    	//成交量
    	$('#dealValume').on('change', function () {
    		//成交量作为分母计算均价，不能为0
            if ($('#dealValume').val() == 0){
                Joyin.error("成交量不能为0！");
                $('#dealValume').val(null);
                return;
            }
            modifyAvgPrice();
    	});
    	
    	//成交金额
    	$('#dealAmount').on('change', function () {
    		modifyAvgPrice();
    	});
    };
    
    /**
     * 修改 涨跌 涨跌幅
     */
    var modifyChange =function(){
    	if($('#preClosePrice').val()&&$('#closePrice').val()){
    		$("#change").val(($('#closePrice').val()-$('#preClosePrice').val()).toFixed(2));
    		var changePercent = (($('#closePrice').val()-$('#preClosePrice').val())/$('#preClosePrice').val()*100).toFixed(2)
    		$("#changePercent").val(changePercent);
    	}
    }
    
    /**
     * 修改 复权价格
     */
    var modifyAdj =function(){
    	if($('#preClosePrice').val()){
    		$("#preClosePriceAdj").val(($('#preClosePrice').val()*$('#factorAdj').val()).toFixed(2));
    	}
    	if($('#openPrice').val()){
    		$("#openPriceAdj").val(($('#openPrice').val()*$('#factorAdj').val()).toFixed(2));
    	}
    	if($('#highPrice').val()){
    		$("#highPriceAdj").val(($('#highPrice').val()*$('#factorAdj').val()).toFixed(2));
    	}
    	if($('#lowPrice').val()){
    		$("#lowPriceAdj").val(($('#lowPrice').val()*$('#factorAdj').val()).toFixed(2));
    	}
    	if($('#closePrice').val()){
    		$("#closePriceAdj").val(($('#closePrice').val()*$('#factorAdj').val()).toFixed(2));
    	}
    }
    
    /**
     * 修改均价
     */
    var modifyAvgPrice =function(){
    	if($('#dealValume').val()&&$('#dealAmount').val()){
    		$("#avgPrice").val(($('#dealAmount').val()/$('#dealValume').val()).toFixed(2));
    	}
    }
    
    /**
     * 修改 复权因子
     */
    var modifyFactorAdj =function(){
    	if($('#lastClosePrice').val()&&$('#lastFactorAdj').val()&&$('#preClosePrice').val()){
    		$("#factorAdj").val(($('#lastClosePrice').val()/$('#preClosePrice').val()*$('#lastFactorAdj').val()).toFixed(2));
    	}
    	modifyAdj();
    }
    
    /**
     * 获取之前最近一日的信息
     */
    var getLatelyShareById= function(){
    	$.ajax({
			url:basePath + 'valuation/basedata/marketdata/valshareprice/ValSharePrice002/getLatelyShareById',
			data:{
				sharesId:$('#sharesId').val(),
				dealDateString:$('#dealDate').val()
				},
			type:"post",
			dataType: "json",
			success:function(data){
				if(data){
					$('#sShares').val(data.sShares).trigger('change');
					$('#cShares').val(data.cShares).trigger('change');
					$('#lastClosePrice').val(data.closePrice).trigger('change');
					$('#lastFactorAdj').val(data.factorAdj).trigger('change');
					modifyFactorAdj();
				}else{
					//取不到最近交易信息 复权因子置位1
					$("#factorAdj").val('1');
				}
			},
			error:function(data){
				//取不到最近交易信息 复权因子置位1
				$("#factorAdj").val('1');
			}
		});
    }
    /**
     * 清除数据项 对于复权信息进行清除
     */
    var clean = function(){
    	//流通资本置空
    	$('#sShares').val('').trigger('change');
		$('#cShares').val('').trigger('change');
		//前一日交易信息置空
		$('#lastClosePrice').val('').trigger('change');
		$('#lastFactorAdj').val('').trigger('change');
		//复权因子置空
		$('#factorAdj').val('').trigger('change');
		//修改所有复权信息
		modifyAdj();
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	
        //页面初始化及绑定onchange事件
        init();
        /**
         * 绑定事件
         */
        // 绑定按钮事件
        $('#ValSharePrice002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ValSharePrice002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());