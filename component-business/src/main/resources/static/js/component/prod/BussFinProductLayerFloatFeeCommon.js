/**
 * 分档产品浮动管理费共通JS
 * @auth 孟宏伟
 */
(function(){
	 /**
     * 获取画面元素
     */
  function getElementById(id) {
    return $('#' + floatFeeEntityName + '_' + id);
  }

  // 公开接口
  window.BussLayerFinProductFloatFee = {
          floatFeeInitAction : floatFeeInitAction,
          floatFeeInitActionTmpl : floatFeeInitActionTmpl
  };
  function floatFeeInitAction(input_finprodId, input_vdate) {
      finprodId = input_finprodId;
      effDate = input_vdate;
      bussFinProductFloatFeeInfoTable.table ? bussFinProductFloatFeeInfoTable.reload() : bussFinProductFloatFeeInfoTable.load();
  }
  
  function floatFeeInitActionTmpl(bussBenchMarkLayeredInfoList) {
	  if(bussFinProductFloatFeeInfoTable) {
	    _.each(bussBenchMarkLayeredInfoList, function(rowData) {
	    	bussFinProductFloatFeeInfoTable.addRow(rowData);
	    });
	  }
  }
  
  var getSearchParams = function(){
      var data = {};
       data.workflowId = workflowId;
       data.finprodId = finprodId;
       data.entityName = floatFeeEntityName;
       data.effDate = effDate;
      return data;
    }
	 /**
     * 定义数据表格
     */
    var bussFinProductFloatFeeInfoTable = getElementById('floatfeeInfo').joyintable({
    	ajax: {
    		url: JoyinUtil.getRealPath('/component/prod/BussFinProductLayerFloatFeeCommon/getFloatFeeInfoList'),
    		type: 'POST',
    		data: function() {
                return getSearchParams();
            }
    	},
       
        searchOnInit: true,
        loadonce: true,
        page: false,
        checkbox:false,
        height : 200,
        cols:[
            {field:'feeId', width:150, title: '费用名称', sortable: false,edit:true,editOption:{type:'select',formatoption:true,url: JoyinUtil.getRealPath('/component/prod/BussFinProductFloatFee/queryFeeNameInfoList')}}
            ,{field:'prodStyle', width:100, title: '适用款型', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: PROD_STYLE}}
            ,{field:'investmentCycle', width:100, title: '投资周期', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: INVESTMENT_CLCYE}}
            ,{field:'basicBill', width:100, title: '计费基数', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: BILL_BASE_FEE_KIND}}
            ,{field:'basis', width:100, title: '计费基础', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: BASIS}}
            ,{field:'netAssetValueDay', width:100, title: '资产净值日',visible:false,edit:true, sortable: false,editOption:{type:'select',formatoption:true,result: BASE_DATE_TYPE}}
            ,{field:'payCycle', width:100, title: '付费规则', sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: FREQUENCY_UNIT}}
            ,{field:'endTdayRule', width:100, title: '计费营业日准则',sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: TDAY_RULE}}
            ,{field:'payTdayRule', width:100, title: '付费营业日准则',sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: TDAY_RULE}}
            ,{field:'provisionFirstDay', width:100, title: '首次计提日', type:'date',sortable: false}
            ,{field:'eAccrDate', width:100, title: '计提结束日',type:'date', sortable: false}
            ,{field:'fAccrEndDate', width:100, title: '首个计提区间结束日',type:'date',visible:false, sortable: false}
            ,{field:'accrTdayRule', title: '计提营业日准则', visible:false,edit:true,editOption:{type:'select',formatoption:true,result: TDAY_RULE}}
            ,{field:'firstPayDate', title: '首次付费结束日', type:'date',visible:false}
            ,{field:'interValRule', title: '区间规则',visible:false,edit:true,editOption:{type:'select',formatoption:true,result: FREQUENCY_UNIT}}
            ,{field:'accrType', title: '计提方式', visible: false,edit:true,editOption:{type:'select',formatoption:true,result: DRAW_TYPE}}
            ,{field:'finFloatCalcRuleDetailDtoList', visible:false}
            ,{field:'rateType', visible:false}
            ,{field:'rate', visible:false}
            ,{field:'indexId', visible:false}
            ,{field:'coefficient', visible:false}
            ,{field:'spreadRate', visible:false}
            ,{field:'resetType', visible:false,editOption:{type:'select',formatoption:true,result: EFFECTIVE_RULE}}
            ,{field:'resetTypeName',title: '业绩基准重置规则',sortable: false,edit:true,editOption:{type:'select',formatoption:true,result: EFFECTIVE_RULE}}
            ,{field:'remark', visible:false}
            ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
            
        ],
        done : function(table, allData){
        	//设置都不可编辑
        	 var allrowData =  bussFinProductFloatFeeInfoTable.options.cols;
             for(var i in allrowData){
            	   allrowData[i].edit = false;
             }
        },
        //增加双击查看事件 yzg 20191111 start
        dblclick:function(table, data) {
        	data.pageModel=PAGE_MODEL_REFERENCE;
        	var feeList = bussFinProductFloatFeeInfoTable.getAllRows();
        	var prdinfo = $.serializeToObject('#'+productBuildForm, false);
        	feeList = JSON.stringify(feeList);
            Joyin.dialog(JoyinUtil.getLabel("MstFloatFeeVarieties002.reference"),
                    JoyinUtil.getRealPath("invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002/init"),
                    "98%", "95%",{operateType:'reference',pageModel:PAGE_MODEL_REFERENCE,pageId:pageId, PROD_STYLE: PROD_STYLE,INVESTMENT_CLCYE: INVESTMENT_CLCYE ,dealMode: $('#' + dealModeElement).val(),vdate:$('#'+vdate).val(),mdate:$('#'+mdate).val(),prdinfo:JSON.stringify(prdinfo), feeList:feeList,data:JSON.stringify(data)},function(){}
            );
        }
        //增加双击查看事件 yzg 20191111 end
    });
    /**
	 * 绑定删除事件
	 */
    bussFinProductFloatFeeInfoTable.bind('click', '.delt', function(data, index) {
    	bussFinProductFloatFeeInfoTable.removeRow(data);
	});
    
    /**
     * 绑定编辑事件
     */
    bussFinProductFloatFeeInfoTable.bind('click', '.edit', function(data,index) {
    	
    	var feeList = bussFinProductFloatFeeInfoTable.getAllRows();
    	var prdinfo = $.serializeToObject('#'+productBuildForm, false);
    	feeList.splice(index,1);//不包括修改对象本身的校验
    	feeList = JSON.stringify(feeList);
    	Joyin.dialog(JoyinUtil.getLabel("MstFloatFeeVarieties002_edit"),
                basePath + "invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002/init","98%", "95%",{operateType:'edit',pageId:pageId, PROD_STYLE: PROD_STYLE,INVESTMENT_CLCYE: INVESTMENT_CLCYE ,dealMode: $('#' + dealModeElement).val(),vdate:$('#'+vdate).val(),mdate:$('#'+mdate).val(),prdinfo:JSON.stringify(prdinfo), feeList:feeList,data:JSON.stringify(data)},function(result){
	        	if(!result){
	        		return;
	        	} else{
	        		bussFinProductFloatFeeInfoTable.removeRow(data);
                	var resetTypeName = '';
                	if(result.resetType==01)
                		resetTypeName = '期初'
            		else if(result.resetType==02)
                		resetTypeName = '期末'
            		else if(result.resetType==03)
                		resetTypeName = '当期生效'
	        		var row = {
	                          feeId: result.feeId,
	                          basicBill: result.basicBill,
	                          prodStyle: result.prodStyle,
	                          investmentCycle: result.investmentCycle,
	                          basis: result.basis,
	                          netAssetValueDay: result.netAssetValueDay,
	                          payCycle: result.payCycle,
	                          endTdayRule: result.endTdayRule,
	                          payTdayRule : result.payTdayRule,
	                          provisionFirstDay: result.provisionFirstDay,
	                          eAccrDate: result.eAccrDate,
	                          fAccrEndDate: result.fAccrEndDate,
	                          accrTdayRule : result.accrTdayRule,
	                          firstPayDate: result.firstPayDate,
	                          interValRule: result.interValRule,
	                          accrType: result.accrType,
	                          finFloatCalcRuleDetailDtoList : result.finFloatCalcRuleDetailDtoList,
	                          rateType: result.rateType,
	                          rate: result.rate,
	                          indexId: result.indexId,
	                          coefficient: result.coefficient,
	                          spreadRate: result.spreadRate,
	                          resetType: result.resetType,
	                          resetTypeName: resetTypeName,
	                          remark: result.remark
	                        };
                          bussFinProductFloatFeeInfoTable.addRow(row);
	        	}
        	});
    });
    /**
     * 产品处理模式联动事件
     */
    var dealModeChange = function (){
        //收益类型为净值型 且产品处理模式为分档 改界面显示  其他场景不显示
        if($('#' + profitType).val() === PROFIT_TYPE_01 &&$('#' + dealModeElement).val() === DISPOSE_PATTERN_11){
            if(bussFinProductFloatFeeInfoTable.table){
            	bussFinProductFloatFeeInfoTable.reload();
			}else{
				bussFinProductFloatFeeInfoTable.load();
			}
            bussFinProductFloatFeeInfoTable.showColumns([{field:'investmentCycle', visible: false},{field:'prodStyle', visible: true}]);
        	getElementById('Layerdiv').show();
        } else {
            getElementById('Layerdiv').hide();
            bussFinProductFloatFeeInfoTable.reload({ajax:null, url: null, data:[]})
        }
    }
    /**
     * 期限联动事件
     */
    var periodChange = function (){
    	if($('#' + dealModeElement).val() === DISPOSE_PATTERN_10){
    		//多固定期限展示
            if($('#' + profitType).val() === PROFIT_TYPE_01 && $('#' + periodElement).val() === TERM_TYPE_02){
            	if(bussFinProductFloatFeeInfoTable.table){
                	bussFinProductFloatFeeInfoTable.reload();
    			}else{
    				bussFinProductFloatFeeInfoTable.load();
    			}
            	bussFinProductFloatFeeInfoTable.showColumns([{field:'prodStyle', visible: false},{field:'investmentCycle', visible: true}]);
            	getElementById('Layerdiv').show();
            }  else {
                getElementById('Layerdiv').hide();
                bussFinProductFloatFeeInfoTable.reload({ajax:null, url: null, data:[]})
            }
    	}
    }
    $(function() {
    	 // 新建按钮点击事件
        getElementById('btn_create').click(function() {
        var feeList = bussFinProductFloatFeeInfoTable.getAllRows();
        feeList = JSON.stringify(feeList);
        var prdinfo = $.serializeToObject('#'+productBuildForm, false);
        var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("MstFloatFeeVarieties002_add"),
        		basePath +"invest/basicdata/mstfloatfeevarieties/MstFloatFeeVarieties002/init",
                    "98%", "95%", {pageModel:PAGE_MODEL_NEW,pageId:pageId,PROD_STYLE: PROD_STYLE ,INVESTMENT_CLCYE: INVESTMENT_CLCYE ,dealMode: $('#' + dealModeElement).val(),feeList:feeList, entityName: floatFeeEntityName, workflowId:workflowId,finprodId : finprodId, profitType : $('#'+profitType).val(),vdate:$('#'+vdate).val(),mdate:$('#'+mdate).val(),prdinfo:JSON.stringify(prdinfo)}, function(data) {
                    	var resetTypeName = '';
                    	if(data.resetType==01)
                    		resetTypeName = '期初'
                		else if(data.resetType==02)
                    		resetTypeName = '期末'
                		else if(data.resetType==03)
                    		resetTypeName = '当期生效'
                      if(!data) return;
                      // 新建费用品种后，在费用列表中，添加一行数据，费用名称，费用类型，计费基数，计算基础，默认带出上述添加的数据。
                      
                      var row = {
                      feeId: data.feeId,
                      basicBill: data.basicBill,
                      prodStyle: data.prodStyle,
                      investmentCycle: data.investmentCycle,
                      basis: data.basis,
                      netAssetValueDay: data.netAssetValueDay,
                      payCycle: data.payCycle,
                      endTdayRule: data.endTdayRule,
                      payTdayRule : data.payTdayRule,
                      provisionFirstDay: data.provisionFirstDay,
                      eAccrDate: data.eAccrDate,
                      fAccrEndDate: data.fAccrEndDate,
                      accrTdayRule : data.accrTdayRule,
                      firstPayDate: data.firstPayDate,
                      interValRule: data.interValRule,
                      accrType: data.accrType,
                      rateType: data.rateType,
                      rate: data.rate,
                      indexId: data.indexId,
                      coefficient: data.coefficient,
                      spreadRate: data.spreadRate,
                      resetType: data.resetType,
                      resetTypeName: resetTypeName,
                      remark: data.remark,
                      finFloatCalcRuleDetailDtoList : data.finFloatCalcRuleDetailDtoList
                    };
                     // convertRow(row);
                      bussFinProductFloatFeeInfoTable.addRow(row);
                    });
        });
        
        //产品处理模式联动事件
        dealModeChange();
        $('#' + dealModeElement).change(dealModeChange);
        //期限联动事件
        periodChange();
        $('#' + periodElement).change(periodChange);
    });
}());