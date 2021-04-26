/**
 * 债券一级投标委托新建
 */
(function(){
    var bidPrinAmt = 0;
    var getriskdata = function () {
        var obj = {tradeDirection:etrPs,timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustNum').val(),assetType:fintype,assetSecType:$('#assetType1').val(),isEnabled:'01',assetCode:$('#finprodIdhid').val(),tradeAmt:$('#tradeAmt').val(),counterId:$('#etrInvestPersonInfo_counterId').val()}
        var object = {tradeDirection:['ps','EtrBondFirstLBidding002.ps'],portfolioCode:['portfolioId','EtrBondFirstLBidding002.portfolioId'],tradeAmount:['entrustNum','EtrBondFirstLBidding002.entrustAmt'],assetType:['assetType1','EtrBondFirstLBidding002.bondType'],counterId:['counterId','trdInvestPersonInfo_counterId'],assetCode:['finprodId','EtrBondFirstLBidding002.finprodId'],data:obj}
        return object;
    }
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
    
    var getElementById = function(id) {
    };
    //初始化
    /**
     * 定义数据表格
     */
    var cols = [
        {field:'bidPrice',  title: '投标标位（%）',edit:true, type:'number', fixtype:2, width:500}
        ,{field:'bidPrinAmt',  title: '投标券面总额',edit:true, width:500}
        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
    ];
    var table =$("#dataTable").joyintable({
        data:bidPriceList==null || bidPriceList == '' ? null:JSON.parse(bidPriceList),
        cols:cols,
        searchOnInit: false,
        loadonce:false,
        searchOnInit : true,
        checkbox:false,
        height:290,
        addbtn:true,
        editCell: function(value, data, field, cell, column){
            if (value.length > 16){
                Joyin.alert("投标券面总额的值不能超过16位！", 2);
                return false;
            }
        	if (value && field ==='bidPrinAmt') {
        		bidPrinAmt = 0;
        		var tableRows  = table.getAllRows();
            	$.each(tableRows, function(id, value){
                    bidPrinAmt = JoyinMath.doAdd(bidPrinAmt, parseInt(value.bidPrinAmt), 2)
            	});
            	$('#entrustNum').val(bidPrinAmt);
            	return true;
			}
        	
        	}
    });
    
    table.bind('click', '.delt', function(data, index) {
    	if (data.bidPrinAmt) {
        	$('#entrustNum').val($('#entrustNum').val()-parseInt(data.bidPrinAmt));
		}
    	
    	table.deleteRow(index);
    })

    /**
     * 资产代码
     */
    $('#finprodId').change(function() {

        var ccy = $('#ccy').val();
        var portfolioId = $('#portfolioId').val();
        var vdate = $('#vdate').val();
        var finprodId = $('#finprodId').val();
        // 获取从一级新债日历获取到的mId值 By zhaodongdong start 20191116
        var mId = $('#mId').val();
        if(finprodId){
        
        	var data = $('#finprodId').select2('data')[0].object;
        	
            $('#finprodIdhid').val(data.finprodId).trigger('change');
            $('#finprodName').val(data.finprodName).trigger('change');
            $('#tradeMarket').val(data.tradeMarket).trigger('change').attr('disabled',true);
            // 一级新债日进入委托界面二级，三级，四级分类暂时可选 by 赵东东 start
            if(mId){
            	$('#assetType1').val(data.assetType1).trigger('change').attr('disabled',false);
                $('#assetType10').val(data.assetType10).trigger('change').attr('disabled',false);//新增资产三级分类 王贵奇 2019/7/27
                $('#assetType11').val(data.assetType11).trigger('change').attr('disabled',false);//新增资产四级分类 王贵奇 2019/7/27
            }else{
            $('#assetType1').val(data.assetType1).trigger('change').attr('disabled',true);
            $('#assetType10').val(data.assetType10).trigger('change').attr('disabled',true);//新增资产三级分类 王贵奇 2019/7/27
            $('#assetType11').val(data.assetType11).trigger('change').attr('disabled',true);//新增资产四级分类 王贵奇 2019/7/27
            }
            // 一级新债日进入委托界面二级，三级，四级分类暂时可选 by 赵东东 end
            $('#finvdate').val(new Date(data.finvdate)).trigger('change');
            $('#finmdate').val(new Date(data.finmdate)).trigger('change');
            $('#entrustDate').val(new Date(data.biddate).format("yyyy-MM-dd")).trigger('change').attr('disabled',true);
            $('#ccy').val(data.ccy).trigger('change').attr('disabled',true);
            if(ccy && portfolioId && vdate){
                getPtlCashPositionInfo();
            }
            // 一级新债日历进入委托界面时，带出的finprodId不可修改 by 赵东东 20191116 start
         if(mId){
    		$('#finprodId').val(data.finprodId).attr('disabled',true);
    	}
           // 一级新债日历进入委托界面时，带出的finprodId不可修改 by 赵东东 20191116 end
        } else {
            $('#finprodIdhid').val('').trigger('change');
            $('#finprodName').val('').trigger('change');
            $('#assetType1').val('').trigger('change');
            $('#assetType10').val('').trigger('change');//新增资产三级分类 王贵奇 2019/7/27
            $('#assetType11').val('').trigger('change');//新增资产四级分类 王贵奇 2019/7/27
            $('#proAvailableCash1').val('');
            $('#availableNum').val('');
            $('#tradeMarket').val('').trigger('change').attr('disabled',false);
            $('#ccy').val(ccy).trigger('change').attr('disabled',false);
        }
        // 画星星
        star();
    });
    
    /**
     * 组合代码
     */
    $('#portfolioId').change(function(){
        var ccy = $('#ccy').val();
        var portfolioId = $('#portfolioId').val();
        var vdate = $('#vdate').val();
        var finprodId = $('#finprodIdhid').val();
        if(portfolioId){
            var data = $('#portfolioId').select2('data')[0].object;
            $('#profitType').val(data).trigger('change');
        }
        if(ccy && portfolioId && vdate){
            getPtlCashPositionInfo();
        } 
    });
    
    
    /**
     * 委托开始日
     */
    $('#vdate').change(function(){
        var ccy = $('#ccy').val();
        var portfolioId = $('#portfolioId').val();
        var vdate = $('#vdate').val();
        $("#mdate").val(vdate);
        var finprodId = $('#finprodIdhid').val();
        if(ccy && portfolioId && vdate){
            getPtlCashPositionInfo();
        }
    });
    
    
  //查询组合可用现金
    var getPtlCashPositionInfo = function(){
        var data = {portfolioId:$('#portfolioId').val(),ccy:$('#ccy').val(),vdate:$('#vdate').val(),calId:$('#calendarId').val()}
        Joyin.ajaxJson(basePath + 'invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding002/getProAvailableCash',data,
                function(result) {
            $('#proAvailableCash').val(result.cashT).trigger('blur');
            $('#proAvailableCash1').val(result.cashT1).trigger('blur');
        });
    }  
   
    
    /**
     * 保存或提交
     * 
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	//风险试算 start
    	 var s;
        if(typeof(BaseRiskMathComm) != 'undefined') {
    	 if(BaseRiskMathComm) {
             s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
             if(!s && s != ''){
                 return;
             }
             if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                 return;
                }
             }
         }
    	//风险试算 end
    	//校验数据  校验表格
    	var tableData  = table.getAllRows();
    	if (tableData.length == 0) {
    		Joyin.alert("投标标位至少有一条数据！", 2);
			return false;
		}
    	for (var i = 0; i < tableData.length; i++) {
    		if (!tableData[i].bidPrice ) {
    			Joyin.alert("投标标位不能为空！", 2);
				return false;
			}
            if (tableData[i].bidPrice > 100 || tableData[i].bidPrice <= 0) {
                Joyin.alert("投标标位的值应大于0，小于等于100！", 2);
                return false;
            }
    		if (!tableData[i].bidPrinAmt) {
    			Joyin.alert("投标券面总额不能为空！", 2);
				return false;
			}
            if (tableData[i].bidPrinAmt.length > 16) {
                Joyin.alert("投标券面总额的值不能超过16位！", 2);
                return false;
            }
			for (var j = i+1; j < tableData.length; j++) {
				if (Number(tableData[i].bidPrice) ==
                    Number(tableData[j].bidPrice)) {
					Joyin.alert( tableData[j].bidPrice + "投标标位不能重复存在！", 2);
					return false;
				}
				
			}
		}
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm',true);
        object.bidPriceList = tableData;
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.entrustStatus = $('#entrustStatus').val();
        object.finvdate = $('#finvdate').val();
        object.finmdate = $('#finmdate').val();
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
        if(object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader != ''){
            object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader;//.join(",");
        }
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        var elementId = "";
        var entrustNum_old = $("#entrustNum_old").val();
        var entrustNum = $("#entrustNum").val();
        //改高投标量
        if(pageModel == PAGE_MODEL_EDIT&&entrustNum_old&&parseFloat(entrustNum_old) < parseFloat(entrustNum)){
            elementId = 'EtrBondFirstLBidding002_btn_total';
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
            Joyin.ajaxJson(basePath + 'invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding002/check',object,
                function(result) {
                    Joyin.confirm(result.data,function() {
                        if (operateType === 'save') {
                            WorkFlow.doSaveList(basePath+'invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding002/save',object,elementId||"EtrBondFirstLBidding002_btn_save");
                        } else {
                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding002/save',object,elementId||"EtrBondFirstLBidding002_btn_submit");
                        }
                    });
                });
        });
    }
    
    window.componentLoadCallback = function(isDone, pagePath, params){
    	$('#etrInvestPersonInfo_trader').attr('multiple',true);
//    	$('#etrInvestPersonInfo_counterId').val('').prop('disabled',false).prop('required',true).trigger('change');
    }

    /**
     * 将资产代码带出的值置灰不可修改
     */
    var init = function() {
        if (entity.finprodId){
            $("#assetType1").trigger("change").attr("disabled", true);
            $("#assetType10").trigger("change").attr("disabled", true);
            $("#assetType11").trigger("change").attr("disabled", true);
            $("#ccy").trigger("change").attr("disabled", true);
            $("#tradeMarket").trigger("change").attr("disabled", true);
            $("#entrustDate").trigger("change").attr("disabled", true);
        }
    }

    $(function(){
        star();
        init();
		if (bidPriceList) {
			$.each(JSON.parse(bidPriceList), function(id, value){
				bidPrinAmt += parseInt(value.bidPrinAmt);
			});
			$('#entrustNum').val(bidPrinAmt);
            $('#entrustNum_old').val(bidPrinAmt);
			table.reload( {data: JSON.parse(bidPriceList)});
		}
	    /**
	     * 保存
	     */
	    $('#EtrBondFirstLBidding002_save').click(function() {
	        saveOrSubmit('save');
	    });
	    /**
	     * 提交
	     */
	    $('#EtrBondFirstLBidding002_btn_submit').click(function() {
	        saveOrSubmit('submit');
	    });
	   
	    
	 // 代办显示点击查看风险试算结果  Start
        /**
         * 代办点击查看风险试算结果
         */
        if(todoList==true){
            $("#riskOfTrialLink").show();
        }else{
            $("#riskOfTrialLink").hide();
        }
        $("#riskOfTrialLink").click(function() {
            Joyin.dialog(JoyinUtil.getLabel("BaseRiskMathComm.riskAndCredit"),
                basePath + "component/common/RiskMathAndCredit/init",
                "90%", "90%",{workflowId:workflowId,tradeId:tradeId,TodoList:todoList});
        });
        // 代办显示点击查看风险试算结果   End

        /**
	     * 风险试算
	     */
	    $('#EtrBondFirstLBidding002_btn_riskcal').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
        	var object = $.serializeToObject('#searchForm',true);
            window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/0/RSK002/F17',object,getriskdata());

	    });
	    if(IS_SHOW_TYPE == '01'){
        	$('#assetType11Div').show();
        }else{
        	$('#assetType11Div').hide();
        }
	})
}());