/**
 * 组合分配JS
 */
(function(){
	var getriskdatas = function () {
        var mun=null;
        var object = $.serializeToObject('#searchForm');
        var bussCombDistribtList=null;
        if(object!=null&&object!=""&&object.bussCombDistribtList!=null&&object.bussCombDistribtList!=""){
        	bussCombDistribtList=object.bussCombDistribtList;
        }
        var list=[];
        for(var i in bussCombDistribtList){
            	var item = bussCombDistribtList[i];
	    		var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:item.portfolioId,tradeAmount:item.entrustNum, assetType:fintype,assetSecType:$('#assetType1').val(),isEnabled:'01',assetCode:$('#finprodIdhid').val()}
	            list[i]=obj;
        } 
        var object = {tradeDirection:['ps','EtrInvestBondInfo002.ps'],portfolioCode:['portfolioId','EtrInvestBondInfo002.portfolioId'],tradeAmount:['entrustNum','EtrInvestBondInfo002.entrustAmt'],assetType:['assetType1','EtrInvestBondInfo002.bondType'],data:list}
        return object;
    }
 
	var getriskdata = function () {
		var bussCombDistribtList = $.serializeToObject('#searchForm').bussCombDistribtList;
		if(bussCombDistribtList.length>0){
			var objects = new Array();
	    	for(var index in bussCombDistribtList){
	    		var item = bussCombDistribtList[index];
	    		var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:item.portfolioId,tradeAmount:item.entrustNum, assetType:fintype,assetSecType:$('#assetType1').val(),isEnabled:'01',assetCode:$('#finprodIdhid').val()}
	            var object = {tradeDirection:['ps','EtrInvestBondInfo002.ps'],portfolioCode:['portfolioId','EtrInvestBondInfo002.portfolioId'],tradeAmount:['entrustNum','EtrInvestBondInfo002.entrustAmt'],assetType:['assetType1','EtrInvestBondInfo002.bondType'],data:obj}
	            objects.push(object);
	    	}
    		return objects;
		}else{//若组合分配列表为空，则单个check
			var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate, assetType:fintype,assetSecType:$('#assetType1').val(),isEnabled:'01',assetCode:$('#finprodIdhid').val()}
            var object = {tradeDirection:['ps','EtrInvestBondInfo002.ps'],portfolioCode:['portfolioId','EtrInvestBondInfo002.portfolioId'],tradeAmount:['entrustNum','EtrInvestBondInfo002.entrustAmt'],assetType:['assetType1','EtrInvestBondInfo002.bondType'],data:obj}
    		return object;
		} 
    }
	
	var cols = [
        {field:'portfolioId', width:150,  title: 'EtrInvestBondInfo002.portfolioId',edit:true,sortable: false,
            editOption:{type:'select',childrenIds:'secManageAcctId', id:'portfolioId', multiple: false, showField:'text',searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
           }
        }
        ,{field:'invAim', width:100, title: 'EtrInvestBondInfo002.invAim', sortable: false, edit:true
          	 ,editOption:{type:'select',id:"invAim",formatoption:true,result:INV_AIM} }
        ,{field:'entrustNum', width:100, title: 'EtrInvestBondInfo002.entrustAmt', edit:true, sortable: false, type:'number', digit:2}
        ,{field:'secManageAcctId', width:100,  title: 'EtrInvestBondInfo003.secManageAcctId',edit:true,sortable: false,
            editOption:{type:'select', parentIds:'portfolioId,tradeMarket', id:'secManageAcctId', multiple: false, showField:'text',searchable: true,
                url: JoyinUtil.getRealPath('/invest/trade/bond/TrdBondInvestTrade002/getSecManageAcctId')
           }
        }
        ,{field:'proAvailableCash', width:150, title: 'EtrInvestBondInfo003.proAvailableCash', sortable: false, type:'number', digit:2}
        ,{field:'proAvailableCash1', width:150, title: 'EtrPlanAssetN002.proAvailableCash1', sortable: false, type:'number', digit:2}
        ,{field:'assetAvailablePosition', width:150, title: 'EtrInvestBondInfo003.assetAvailablePosition', sortable: false, type:'number', digit:2}
        ,{field:'assetAvailablePosition1', width:150, title: 'EtrInvestBondInfo002.assetAvailablePosition1', sortable: false, type:'number', digit:2}
        ,{field:'SecManageAvailableCash', width:150, title: 'EtrInvestBondInfo003.SecManageAvailableCash', sortable: false, type:'number', digit:2, visible:false}
        ,{field:'channelAvailableCash', width:100, title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("EtrInvestBondInfo002.channelAvailableCash");}(), sortable: false, type:'number', digit:2}
        ,{field:'accAvailableCash', width:100, title: 'EtrInvestBondInfo002.accAvailableCash', sortable: false, type:'number', digit:2, visible:false}
        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
    ]
	var table =$("#bussCombDistribtTable").joyintable({
        cols:cols,
        page: true,
        addbtn:true,
        addToLast:true,
        searchOnInit: true,
        checkbox:false,
        addclick: function(row) {
        	row.invAim = $('#invAim').val();
        },
        editCell : function(value, data, field, cell, column){
            if (field == "portfolioId"){
                var rowData = {};
                rowData.entrustNum = data.entrustNum;
                rowData.invAim = data.invAim;
            	rowData.secManageAcctId = '';
            	rowData.SecManageAvailableCash = ' ';
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
                if (value != null && value != '' && $('#ps').val() == T01 && $('#ccy').val() != ''){
                	var datas = {portfolioId:data.portfolioId, ccy:$('#ccy').val(), sysDateT1:$('#vdate').val(), calId:$('#calendarId').val(), chlFinprodId: $("#chlFinprodId").val()};
                	Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getProAvailableCash', datas, function(result) {
                    	if(result){
                        	rowData.proAvailableCash = result.cashT;
                        	rowData.proAvailableCash1 = result.cashT1;
                        	rowData.channelAvailableCash = result.cashT2;
                        	rowData.accAvailableCash = result.cashT3;
                            table.renderRow(data.LAY_TABLE_INDEX, rowData);
                    	}
                	});
                }
                if(value != null && value != '' && ($('#ps').val() == T02 ||$('#ps').val() == T03 ||$('#ps').val() == T39) && $('#ccy').val() != ''){
                	var datas = {portfolioId: data.portfolioId, calId:$('#calendarId').val(), 
            				finprodId:$('#finprodIdhid').val(),ccy:$('#ccy').val(), invAim: data.invAim,
            				secAcctId: data.secManageAcctId, chlId: $('#chlFinprodId').val()};
            		
                    Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getAssetAvailablePosition',datas,
                            function(result) {
                    	if(result){
                        	rowData.assetAvailablePosition = result.position;
                        	rowData.assetAvailablePosition1 = result.positionT1;
                            table.renderRow(data.LAY_TABLE_INDEX, rowData);
                    	}
                    });
                }
                if(value == null || value == ''){
                	rowData.proAvailableCash = ' ';
                	rowData.proAvailableCash1 = ' ';
                	rowData.channelAvailableCash = ' ';
                	rowData.accAvailableCash = ' ';
                	rowData.assetAvailablePosition = ' ';
                	rowData.assetAvailablePosition1 = ' ';
                    table.renderRow(data.LAY_TABLE_INDEX, rowData);
                }
            }
            if ((field == "secManageAcctId" || field == "invAim") && $('#ccy').val() != ''){
            	var rowData = {};
            	rowData.SecManageAvailableCash = ' ';
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
            	switch($('#ps').val()){
            	case T01:
                	if(data.secManageAcctId != null && data.secManageAcctId != '' && data.portfolioId!= null 
                			&& data.portfolioId != ''){
                		var param = {
                				portfolioId: data.portfolioId, 
                				ccy:$('#ccy').val(),
                				calendarId:$('#calendarId').val(),
                				secManageAcctId: data.secManageAcctId
                		};
                		Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo003/getSecManageAvailableCash',param,
                                function(result) {
                        	if(result){
                            	rowData.SecManageAvailableCash = result;
                                table.renderRow(data.LAY_TABLE_INDEX, rowData);
                        	}
                        });
                	}
                	break;
            	case T02:
            	case T03:
            	case T39:
                	if (data.secManageAcctId != null && data.secManageAcctId != ''){
                		var datas = {portfolioId: data.portfolioId, calId:$('#calendarId').val(), 
                				finprodId:$('#finprodIdhid').val(),ccy:$('#ccy').val(), invAim: data.invAim,
                				secAcctId: data.secManageAcctId, chlId: $('#chlFinprodId').val()};
                		
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getAssetAvailablePosition',datas,
                                function(result) {
                        	if(result){
                            	rowData.assetAvailablePosition = result.position;
                            	rowData.assetAvailablePosition1 = result.positionT1;
                                table.renderRow(data.LAY_TABLE_INDEX, rowData);
                        	}
                        });
                	}
            		break;
            	}
            }
        }
    });
	/**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });

    function saveOrSubmit(operateType) {
    	var s;
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdatas())){
                return;
            }
        }
        var object = $.serializeToObject('#searchForm');
        if ($('#select2-finprodId-container').attr('title')){
            object.finprodName = $('#select2-finprodId-container').attr('title');
        }
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
        object.entrustStatus = $('#entrustStatus').val();
        object.finvdate = $('#finvdate').val();
        if ($('#etrInvestPersonInfo_counterId').select2('data') && $('#etrInvestPersonInfo_counterId').select2('data')[0]) {
            object.counterType = $('#etrInvestPersonInfo_counterId').select2('data')[0].object;;
        }
        object.finmdate = $('#finmdate').val();
        object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val();
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
        var objects = new Array();
        for(var index in object.bussCombDistribtList){
        	var item = object.bussCombDistribtList[index];
        	var temp = JSON.parse(JSON.stringify(object));
        	temp.portfolioId = item.portfolioId;
        	temp.entrustNum = item.entrustNum;
        	temp.invAim = item.invAim;
        	temp.secAcctId = item.secManageAcctId;
        	temp.proAvailableCash = item.proAvailableCash;
        	temp.accAvailableCash = item.accAvailableCash;
        	temp.assetAvailablePosition  = item.assetAvailablePosition;
        	temp.elementId = 'EtrInvestBondInfo003_btn_submit';
            temp.riskTrialRetDto = object.riskTrialRetDto;
        	objects.push(temp);
        }
        if(objects.length>0){
            Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo003/check',objects,
                    function(result) {
                Joyin.confirm(result.data,function() {
                     WorkFlow.doSubmitList(basePath+'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo003/save',objects,"EtrInvestBondInfo003_btn_submit","EtrInvestBondInfo003");
                 });
             });
        }else{
            Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/check',object,
                    function(result) {
                Joyin.confirm(result.data,function() {
                     WorkFlow.doSubmitList(basePath+'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo003/save',objects,"EtrInvestBondInfo003_btn_submit","EtrInvestBondInfo003");
                 });
             });
        }
    }
    /**
     * 交易所资金账户可用现金条件隐藏
     */
    $('#tradeMarket,#ps').change(function(){
    	if($('#tradeMarket').val() == TREAD_MARKET_SH || $('#tradeMarket').val() == TREAD_MARKET_SZ){
    		if($('#ps').val() == T01){
    			table.showColumns([{field:'SecManageAvailableCash',visible: true}]);
    			var rowsData = table.getAllRows();
    	    	table.clear();
    	    	table.reload({data: rowsData});
    		}else{
    			table.showColumns([{field:'SecManageAvailableCash',visible: false}]);
    			var rowsData = table.getAllRows();
    	    	table.clear();
    	    	table.reload({data: rowsData});
    		}
    	}else{
    		if($('#ps').val() != undefined && $('#ps').val() != ''){
    			table.showColumns([{field:'SecManageAvailableCash',visible: false}]);
    			var rowsData = table.getAllRows();
    	    	table.clear();
    	    	table.reload({data: rowsData});
    		}
    	}
    });
    /**
     * 交易平台联动
     */
    $('#tradeMarket,#tradeMarketMode').on("change", function (e, isInit) {
        var tradeMarket = $('#tradeMarket').val();
        var tradeMarketMode = $('#tradeMarketMode').val();
        var $tradePlat = $('#tradePlat');

        if (pageModel != PAGE_MODEL_REFERENCE) {
            var key = tradeMarket + tradeMarketMode;
            switch (key) {
            	//如果是交易场所+交易市场 == 银行间+一级市场，就可以选择01和99
            	case TRADE_PLACE_01 + SHARES_TRADE_MARKET_01:
                    $tradePlat.updateChosenArr({
                        item: [SHARES_TRADE_PLATFORM_01,SHARES_TRADE_PLATFORM_99],
                        chosen: SHARES_TRADE_PLATFORM_01,
                        disabled: false
                    });
                    break;
                case TRADE_PLACE_01 + SHARES_TRADE_MARKET_02:
                	//如果是交易场所+交易市场 == 银行间+二级市场，只能选择01且置灰
                    $tradePlat.updateChosenArr({
                        item: [SHARES_TRADE_PLATFORM_01],
                        chosen: SHARES_TRADE_PLATFORM_01,
                        disabled: true
                    });
                    break;
                case TRADE_PLACE_02+SHARES_TRADE_MARKET_01:
                    //如果是交易场所+交易市场 == 上海证券交易所+一级市场;
                    $tradePlat.updateChosenArr({
                        disabled: true
                    });
                    break;
                case TRADE_PLACE_02+SHARES_TRADE_MARKET_02:
                    //如果是交易场所+交易市场 == 上海证券交易所+二级市场;
                    $tradePlat.updateChosenArr({
                        item: ['02','03','04'],
                        disabled: false
                    });
                    break;
                case TRADE_PLACE_03+SHARES_TRADE_MARKET_01:
                    //如果是交易场所+交易市场 == 深圳证券交易所+一级市场;
                    $tradePlat.updateChosenArr({
                        disabled: true
                    });
                    break;
                case TRADE_PLACE_03+SHARES_TRADE_MARKET_02:
                    //如果是交易场所+交易市场 == 深圳证券交易所+二级市场;
                    $tradePlat.updateChosenArr({
                        item: ['02','05'],
                        disabled: false
                    });
                    break;
                default:
                    if (isInit || pageModel == PAGE_MODEL_REFERENCE || pageModel ==PAGE_MODEL_EDIT) {
                        break;
                    }
                	if($("#entrustId").val() != ''){
                            $('#tradePlat').prop("disabled",true);
                	}else{
                		$tradePlat.updateChosenArr({
                			disabled: false
                		});
                	}
                    break;
            }
        }
    });
    
    $.fn.updateChosenArr = function updateChosenArr(obj){
		var $this = $(this),arr = obj.dataResult || JSON.parse($this.attr('data-result') || '[]'),item = obj.item;
		$this.empty();
		$this.append($('<option value>'));
		for(var i in arr){
			if (!arr.hasOwnProperty(i))continue;
			if (obj.item && obj.item.length > 0){
				if ($.inArray(arr[i].id,item) >= 0)
					$('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this);
			} else
				$('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this)
		}
		if (obj.chosen)
			$this.val(obj.chosen);
		if (obj.disabled === true || obj.disabled === false)
			$this.attr('disabled', obj.disabled);
		$this.trigger("chosen:updated").trigger('change');
		if (obj.callback)
			obj.callback($this);
		return $this;
	}
    /**
     * 组合可用现金和资产可用持仓的联动
     */
    $('#finprodId,#ps,#ccy').change(function(){
    	var rowsData = table.getAllRows();
    	if(rowsData.length>0 && $('#ccy').val() != ''){
    		switch($('#ps').val()){
	    		case T01:
		    		for(var index in rowsData){
		    			if(rowsData[index].portfolioId == '' || rowsData[index].portfolioId == undefined) continue;
		    			rowsData[index].assetAvailablePosition = '';
		    			rowsData[index].assetAvailablePosition1 = '';
		    			var datas = {portfolioId:rowsData[index].portfolioId, ccy:$('#ccy').val(), sysDateT1:$('#vdate').val(), calId:$('#calendarId').val(), chlFinprodId: $("#chlFinprodId").val()};
		            	Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getProAvailableCash', datas, function(result) {
		                	if(result){
		                    	rowsData[index].proAvailableCash = result.cashT;
		                    	rowsData[index].proAvailableCash1 = result.cashT1;
		                    	rowsData[index].channelAvailableCash = result.cashT2;
		                    	rowsData[index].accAvailableCash = result.cashT3;
		                	}
		            	},false);
		    		}
		    		table.reload({data: rowsData});
		    		break;
	    		case T02:
	    		case T03:
	    		case T39:
		    		for(var index in rowsData){
		    			if(rowsData[index].portfolioId == '' || rowsData[index].portfolioId == undefined) continue;
                    	rowsData[index].proAvailableCash = '';
                    	rowsData[index].proAvailableCash1 = '';
                    	rowsData[index].channelAvailableCash = '';
                    	rowsData[index].accAvailableCash = '';
	    				var datas = {portfolioId: rowsData[index].portfolioId, calId:$('#calendarId').val(), 
	        				finprodId:$('#finprodIdhid').val(),ccy:$('#ccy').val(), invAim: rowsData[index].invAim,
	        				secAcctId: rowsData[index].secManageAcctId, chlId: $('#chlFinprodId').val()};
		                Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getAssetAvailablePosition',datas,
		                        function(result) {
		                	if(result){
		                    	rowsData[index].assetAvailablePosition = result.position;
		                    	rowsData[index].assetAvailablePosition1 = result.positionT1;
		                	}
		                },false);
		    		}
		    		table.reload({data: rowsData});
		    		break;
    		}
    	}
    	
    });
    $('#chlFinprodId').change(function(){
    	if($('#chlFinprodId').val() =='' ||$('#chlFinprodId').val() == undefined){
        	var rowsData = table.getAllRows();
    		for(var index in rowsData){
            	rowsData[index].channelAvailableCash = '';
    		}
    		table.reload({data: rowsData});
    	}
    	if($('#chlFinprodId').val() != null && $('#chlFinprodId').val() != '' && $('#ps').val() == T01 && $('#ccy').val() != ''){
        	var rowsData = table.getAllRows();
    		for(var index in rowsData){
    			rowsData[index].assetAvailablePosition = '';
    			rowsData[index].assetAvailablePosition1 = '';
    			var datas = {portfolioId:rowsData[index].portfolioId, ccy:$('#ccy').val(), sysDateT1:$('#vdate').val(), calId:$('#calendarId').val(), chlFinprodId: $("#chlFinprodId").val()};
            	Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/getProAvailableCash', datas, function(result) {
                	if(result){
                    	rowsData[index].proAvailableCash = result.cashT;
                    	rowsData[index].proAvailableCash1 = result.cashT1;
                    	rowsData[index].channelAvailableCash = result.cashT2;
                    	rowsData[index].accAvailableCash = result.cashT3;
                	}
            	},false);
    		}
    		table.reload({data: rowsData});
    	}
    });
    /**
     * 风险试算
     */
    $('#EtrInvestBondInfo003_btn_riskcal').click(function(){  
         var object = $.serializeToObject('#searchForm');
         window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/0/RSK002/F01', object,getriskdatas());
    });
    /**
     * 提交
     */
    $('#EtrInvestBondInfo003_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
  //是否展示资产四五级分类
    if(IS_SHOW_TYPE == '01'){
    	$('#assetType11Div').show();
    }else{
    	$('#assetType11Div').hide();
    }
    $(function () {
    	 $('#EtrInvestBondInfo003_btn_riskcal').click(function(){  
             var object = $.serializeToObject('#searchForm');
             window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfBatchData/0/RSK002/F01', object,getriskdatas());
        }); 
    });
}());
