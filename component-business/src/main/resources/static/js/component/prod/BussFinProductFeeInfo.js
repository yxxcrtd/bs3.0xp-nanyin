/**
\ * 产品费用共通JS
 * @auth zhengyan
 */
(function(){

	var profitTypeElement1 = $('#' + getElementById('profitTypeElement').val());
	// 快速录入标志
	var quickFlag = false;
	
	// 预览按钮在产品模板新建时不显示
	//增加ignore 属性
	var buttons = [{ text: '&#xe614;',ignore:true, clas: 'preview', title: '预览' }, 'delt'];
	if (getElementById('pageId').val() != 'FinProductBuild002' && getElementById('pageId').val() != 'FinNetValueProductBuild002'){
		buttons = ['delt'];
	}
	
    /**
     * 获取画面元素
     */
	function getElementById(id) {
		return $('#' + feeInfoEntityName + '_' + id);
	}
	
    /**
     * 转换表格数据
     */
    var convertRow = function(rowData) {
    	_.each(FEE_TYPE, function(v) {
    		if(v.id === rowData.feeType) {
				rowData.feeType = v.text;
				//通道
				//rowData.feeTypeStr = v.text;
    		}
    	});
    	_.each(BILL_BASE_FEE_KIND, function(v) {
    		if(v.id === rowData.basicBill) {
    			rowData.basicBillStr = v.text;
    			//通道
               // rowData.basicBillStr = v.text;
    		}
    	});
    	_.each(BASIS, function(v) {
    		if(v.id === rowData.basis) {
    			rowData.basis = v.text;
    		}
    	});
    }
    
    var getSearchParams = function(){
    	var data = {};
    	data.quickFlag = quickFlag;
    	if($('#FinProductBuild002_quickFlag') && $('#FinProductBuild002_quickFlag').val() === 'N'){
    		data.ptlId = $('#' + getElementById('ptlIdElement').val()).val();
    	}else{
    		data.workflowId = workflowId;
        	data.finprodId = finprodFeeFinprodId;
        	data.entityName = feeInfoEntityName;
    	}
    	if(finprodType == FINPROD_TYPE_F28){
    	    data.finprodType = finprodType;
    	}
    	return data;
    }
    
    var cols = [];
    var handleCols = function(){
        if(finprodType == FINPROD_TYPE_F28){
          cols = [
                  {field:'feeId', width:200, title: 'BussFinProductFeeInfo.feeName', sortable: false,
                      edit:true,
                      editOption:{type:'select',parentIds:getElementById('finprodMarketIdElement').val() + ',' +getElementById('profitTypeElement').val(), url: JoyinUtil.getRealPath('/component/prod/BussFinProductFeeInfo/queryFeeInfoList')
                          ,postData:function () {
                              return {finprodType:getElementById('finprodType').val()};
                          }
                      }
                  }
                  ,{field:'feeType', width:200,visible : false,title: 'BussFinProductFeeInfo.feeType', sortable: false}
                  ,{field:'feeTypeStr', width:200, title: 'BussFinProductFeeInfo.feeType', sortable: false}
                  ,{field:'feeRate', width:200, title: 'BussFinProductFeeInfo.feeRate', sortable: false, type:'number', edit:true, digit:4}
                  ,{field:'basicBill', width:150, title: 'BussFinProductFeeInfo.basicBill', sortable: false,visible : false}
                  ,{field:'basicBillStr', width:150, title: 'BussFinProductFeeInfo.basicBill', sortable: false}
                  ,{field:'netAssetValueDay', width:150, title: 'BussFinProductFeeInfo.netAssetValueDay', sortable: false,
                      edit:true,
                      editOption:{type:'select', result: BASE_DATE_TYPE}
                  }
                  ,{field:'basis', width:150, title: 'BussFinProductFeeInfo.basis', sortable: false}
                  /*,{field:'collectType', width:150, title: 'BussFinProductFeeInfo.collectType', sortable: false,
                      edit:true,
                      editOption:{type:'select', result: COLLECT_TYPE}
                  }*/
                  ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
              ]
        }else{
          cols =  [
             {field:'feeId', width:200, title: 'BussFinProductFeeInfo.feeName', sortable: false,
                 edit:true,
                 editOption:{type:'select',parentIds:getElementById('finprodMarketIdElement').val() + ',' +getElementById('profitTypeElement').val(), url: JoyinUtil.getRealPath('/component/prod/BussFinProductFeeInfo/queryFeeInfoList')}}
             ,{field:'feeType', visible : false, width:200, title: 'BussFinProductFeeInfo.feeType', sortable: false}
             ,{field:'feeTypeStr', width:200, title: 'BussFinProductFeeInfo.feeType', sortable: false}
             ,{field:'feeRate', width:100, title: 'BussFinProductFeeInfo.feeRate', sortable: false, type:'number', edit:true, digit:4}
             ,{field:'basicBill', width:150, title: 'BussFinProductFeeInfo.basicBill', sortable: false,visible : false}
             ,{field:'basicBillStr', width:150, title: 'BussFinProductFeeInfo.basicBill', sortable: false}
             ,{field:'basis', width:100, title: 'BussFinProductFeeInfo.basis', sortable: false}
             ,{field:'netAssetValueDay', width:150, title: 'BussFinProductFeeInfo.netAssetValueDay', sortable: false,
                 visible:[PROFIT_TYPE_01, PROFIT_TYPE_03].indexOf($('#' + profitTypeElement).val()) != -1,
                 edit:true,
                 // 一体化对应etliu start
    //           editOption:{type:'select', result: AST_VLU_DATE_TYPE}
                 editOption:{type:'select', result: BASE_DATE_TYPE}
                 // 一体化对应etliu end
             }
             ,{field:'provisionFirstDay', width:150, title: 'BussFinProductFeeInfo.provisionFirstDay1', sortable: false,
                 visible:getElementById('pageId').val() === 'FinProductBuild002'||getElementById('pageId').val() === 'FinNetValueProductBuild002',
                 edit:true,
                 type:'date'
             }
             ,{field:'payFeeRule', width:150, title: 'BussFinProductFeeInfo.payFeeRule', sortable: false,
                 edit:true,
                 editOption:{type:'select', result: PAY_RULE}
             }
             ,{field:'calcFeeTradeRule', width:150, title: 'BussFinProductFeeInfo.calcFeeTradeRule', sortable: false,
                 edit:true,
                 editOption:{type:'select', result: TDAY_RULE}
             }
             ,{field:'payFeeTradeRule', width:150, title: 'BussFinProductFeeInfo.payFeeTradeRule', sortable: false,
                 edit:true,
                 editOption:{type:'select', result: TDAY_RULE}
             }
             ,{field:'orgType', visible : false}
             ,{field:'orgType2', visible : false}
             ,{field:'chargeType', visible : false}
             ,{field:'remark', visible : false}
             ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
         ]
        }
        return cols;
    }

    /**
     * 定义数据表格
     */
    var bussFinProductFeeInfoTable = getElementById('feeInfo').joyintable({
    	ajax: {
    		url: JoyinUtil.getRealPath('/component/prod/BussFinProductFeeInfo/getFeeInfo'),
    		type: 'POST',
    		data: function() {
                return getSearchParams();
            }
    	},
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: true,
        height : 200,
        cols:handleCols(),
        beforeEditCell : function(preVal, field, rowData){
        	var profitType = profitTypeElement1.val();
        	if (field === "payFeeRule" && profitType === PROFIT_TYPE_02){
        		return false;
        	}else if (field === "provisionFirstDay" && (rowData.payFeeRule === PAY_RULE_09)){
        		return false;
        	}else if (field === "feeRate" && rowData.feeType === FEE_TYPE_05NAME){
        		return false;
        	} else if (field==="netAssetValueDay" && rowData.basicBill === BASIC_BILL_04 && finprodType == FINPROD_TYPE_F28){
        	    return false;
        	}
        	
        	return true;
        },
        editCell : function(value, data, field, cell, column){
        	if (field === "feeId"){
        		var rowData = {};
        		rowData.feeType = ""; // 费用类型
        		rowData.basicBill = ""; // 计费基数
        		rowData.basis = ""; // 计算基础
        		if (value != null && value != ''){
        			Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductFeeInfo/queryMstFeeinfoByFeeId'), {feeId : data.feeId, finprodMarketId : $('#' + getElementById('finprodMarketIdElement').val()).val()}, function(result){
        				if (result){
        					// 差额中收时费率(%)清空，不可编辑
        					if (result.feeType === FEE_TYPE_05){
        						rowData.feeRate = '';
        					}
        					rowData.feeType = result.feeType;
        					rowData.feeTypeStr = result.feeTypeStr;
        	    			if(result.basicBill){
                                rowData.basicBill = result.basicBill;
                                //如果计费基础为存续本金  净值日类型默认为当日
                                if(result.basicBill === BASIC_BILL_04){
                                    rowData.netAssetValueDay = BASE_DATE_TYPE_01;
                                } else {
                                    rowData.netAssetValueDay = BASE_DATE_TYPE_02;
                                }
                            }
        	    			rowData.basis = result.basis;
        	    			rowData.orgType = result.orgType;
        	    			rowData.orgType2 = result.orgType2;
        	    			rowData.chargeType = result.chargeType;
        	    			rowData.remark = result.remark;
        	    			convertRow(rowData);
        	        		bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
        	        		/*// 更新缓存值
        	        		var cacheData = bussFinProductFeeInfoTable.getRow(data.LAY_TABLE_INDEX, true);
        	        		cacheData.feeType = result.feeType;
        	        		cacheData.basicBill = result.basicBill;
        	        		cacheData.basis = result.basis;*/
        				}
        			})
        		}else{
        			bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}
        	}else if (field === "payFeeRule"){
        		var rowData = {};
        		if (value === PAY_RULE_09){
        			rowData.provisionFirstDay = "";
        			bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
        		}
        	}else if (field === "netAssetValueDay"){
        	    
                if(data.basicBill === BILL_BASE_FEE_KIND_03 && data.netAssetValueDay === BASE_DATE_TYPE_01){
                    Joyin.error(JoyinUtil.getMessage('FIN_PROD_E_0113') ,function(){
                        var rowData = {};
                        rowData.netAssetValueDay = "";
                        bussFinProductFeeInfoTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
                }
            }
        },
        addclick : function(rowData) {
        	var profitType = profitTypeElement1.val();
        	if (profitType === PROFIT_TYPE_02){
        		rowData.payFeeRule = PAY_RULE_09;
        	}
        },
        done : function(table, allData){
        	if($('#FinProductBuild002_quickFlag')){
        		$('#FinProductBuild002_quickFlag').val('N');
        	}
        }
    });

    /**
	 * 绑定删除事件
	 */
    bussFinProductFeeInfoTable.bind('click', '.delt', function(data, index) {
    	bussFinProductFeeInfoTable.removeRow(data);
	});
    
    bussFinProductFeeInfoTable.bind('click', '.preview', function(data, index) {
    	data.dealCalId = $('#' + getElementById('dealCalIdElement').val()).val();
    	data.vdate = $('#' + getElementById('vdateElement').val()).val();
    	data.mdate = $('#' + getElementById('mdateElement').val()).val();
    	data.profitType = $('#' + getElementById('profitTypeElement').val()).val();
    	Joyin.ajaxJson(JoyinUtil.getRealPath('component/prod/BussFinProductFeeInfo/checkPreview'), data, function(result){
    		if (result.data){
    			Joyin.confirm(result.data, function() {
        			// 生成付费计划
            		Joyin.dialog(JoyinUtil.getLabel("FinProductFeePlanInfo"), JoyinUtil.getRealPath('component/prod/FinProductFeePlanInfo/init'),
            				"45%", "50%", {pageModel:PAGE_MODEL_REFERENCE, feeInfo: JSON.stringify(data)})
        		});
    		}else{
    			// 生成付费计划
        		Joyin.dialog(JoyinUtil.getLabel("FinProductFeePlanInfo"), JoyinUtil.getRealPath('component/prod/FinProductFeePlanInfo/init'),
        				"45%", "50%", {pageModel:PAGE_MODEL_REFERENCE, feeInfo: JSON.stringify(data)})
    		}
    	})
	});
    
    function profitTypeChange() {
        //通道管理专用  nzhang
        if(finprodType == FINPROD_TYPE_F28){
            getElementById('div').show();
        }else{
            // 收益类型
            var selectedValue = $('#' + profitTypeElement).val();
            if (!selectedValue) {
                getElementById('div').hide();
            } else {
                getElementById('div').show();
            }
        }
    }
    
    
    // 公开接口
    window.BussFinProductFeeInfo = {
        getAllRows:getAllRows,
        feeInfoInitAction:feeInfoInitAction,
        feeInfoInitActionByTmpl:feeInfoInitActionByTmpl 
    };
    
    function feeInfoInitAction(input_finprodId){
    	debugger;
    	finprodFeeFinprodId = input_finprodId;
    	quickFlag = true;
    	bussFinProductFeeInfoTable.table ? bussFinProductFeeInfoTable.reload() : bussFinProductFeeInfoTable.load();
    }
    
    function feeInfoInitActionByTmpl(bussFinProductFeeInfoDto) {
    	if(bussFinProductFeeInfoTable) {
    		if(bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList)
    		_.each(bussFinProductFeeInfoDto.bussFinProductFeeInfoDtoList, function(rowData) {
    			bussFinProductFeeInfoTable.addRow(rowData);
    		});
    	}
    }

    function getAllRows(){
        if(bussFinProductFeeInfoTable != ''){
            var rowDatas = [];
            _.each(bussFinProductFeeInfoTable.getAllRows(), function(rowData) {
                _.each(FEE_TYPE, function(v) {
                    if(v.text === rowData.feeType) {
                        rowData.feeType = v.id;
                    }
                });
                _.each(BILL_BASE_FEE_KIND, function(v) {
                    if(v.text === rowData.basicBill) {
                        rowData.basicBill = v.id;
                    }
                });
                _.each(BASIS, function(v) {
                    if(v.text === rowData.basis) {
                        rowData.basis = v.id;
                    }
                });
                rowDatas.push(rowData);
            });
            return rowDatas;
        }
    }
    
    /*
     * 当产品的收益类型为“净值型”和“货币型”时，选择“组合代码”时带出组合下产品的费用信息
     */
    function ptlIdChange(){
    	if($('#FinProductBuild002_quickFlag')){
    		if ($('#FinProductBuild002_quickFlag').val() === 'N'){
    			var ptlIdElement = getElementById('ptlIdElement').val();
    	    	if (ptlIdElement && ptlIdElement != ''){
    	    		var profitType = $('#' + getElementById('profitTypeElement').val()).val();
    	        	var ptlId = $('#' + ptlIdElement).val();
    	        	if ((profitType === PROFIT_TYPE_01 || profitType === PROFIT_TYPE_03) && ptlId){
    	        		if($('#FinProductBuild002_quickFlag') && $('#FinProductBuild002_quickFlag').val() === 'N'){
    	        			bussFinProductFeeInfoTable.table ? bussFinProductFeeInfoTable.reload() : bussFinProductFeeInfoTable.load();
    	        		}
    	        	}
    	    	}
    		}
    	}
    	
    }
    
    var modifyTitleDiv = function(){
        if(finprodType == FINPROD_TYPE_F28){
            getElementById('div').find('.pro-module-title').find('span').html('合作资产费用');
            getElementById('div').find('.pro-module-title').find('a').hide();
        }
    }

    $(function() {
        //修改标题div
        modifyTitleDiv()
        // 父画面收益类型联动事件
    	profitTypeChange();
    	$('#' + getElementById('profitTypeElement').val()).on('change', function () {
            profitTypeChange();
        });
    	// 收益类型切换事件
    	$('#' + getElementById('profitTypeElement').val()).change(function() {
    		var profitType = $(this).val();
    		if(profitType) {
    			// 设置表格列“资产净值日”是否显示
    			bussFinProductFeeInfoTable.showColumns([{field:'netAssetValueDay', visible: [PROFIT_TYPE_01, PROFIT_TYPE_03].indexOf(profitType) > -1}]);
    			// 清空表格(预期型产品强制不规则费用)
    			if (profitType === PROFIT_TYPE_02){
    				bussFinProductFeeInfoTable.clear();
    			}
    		}
    		if (getElementById('pageId').val() === 'FinProductBuild002'||getElementById('pageId').val() === 'FinNetValueProductBuild002'){
    			ptlIdChange();
    		}
    	});
        
        // 父画面组合代码联动事件
    	if (getElementById('pageId').val() === 'FinProductBuild002'||getElementById('pageId').val() === 'FinNetValueProductBuild002'){
    		$('#' + getElementById('ptlIdElement').val()).on('change', function () {
                ptlIdChange();
            });
    	}
        
    	// 新建按钮点击事件
    	getElementById('btn_create').click(function() {
    		var finprodMarketId = $('#' + getElementById('finprodMarketIdElement').val()).val();
    		if (finprodMarketId && finprodMarketId !== ''){
    			var feeList = JSON.stringify(bussFinProductFeeInfoTable.getAllRows());
    			var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("MstFeeVarieties002"),
                        "/invest/basicdata/mstfeevarieties/MstFeeVarieties002/init",
                        "98%", "95%", {pageModel:PAGE_MODEL_NEW, applyType: 'prod', finprodMarketId : finprodMarketId, feeList : feeList, profitType : profitTypeElement1.val()}, function(data) {
                        	if(!data) return;
                        	// 新建费用品种后，在费用列表中，添加一行数据，费用名称，费用类型，计费基数，计算基础，默认带出上述添加的数据。
                        	var row = {
                    			feeId: data.feeId,
                    			feeName: data.feeName,
                    			feeType: data.feeType,
                    			basicBill: data.basicBill,
                    			basis: data.basis,
                    			orgType: data.orgType,
                    			orgType2: data.orgType2,
                    			chargeType: data.chargeType,
                    			remark : data.remark
                    		};
                        	var profitType = profitTypeElement1.val();
                        	if (profitType === PROFIT_TYPE_02){
                        		row.payFeeRule = PAY_RULE_09;
                        	}
                        	convertRow(row);
                        	bussFinProductFeeInfoTable.addRow(row);
                        });
    		}
//    		else{
//    			// 请输入产品代码
//    			if (getElementById('pageId').val() != 'FinProductBuild002'){
//    				return Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038', JoyinUtil.getLabel('FinProductModule002.templetName')));
//    			}else{
//    				return Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038', JoyinUtil.getLabel('FinProductBuild002.finprodMarketId')));
//    			}
//    			
//    		}
    	});
    	
    });
}());