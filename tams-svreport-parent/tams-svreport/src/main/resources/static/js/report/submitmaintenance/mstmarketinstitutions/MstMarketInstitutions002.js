$(function() {
	
    function saveOrSubmit(operateType) {
    	var paRskCustomer = table.getAllRows();
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        for(var i=0; i<paRskCustomer.length; i++){
        	paRskCustomer[i].startDate=Date.ofDate(paRskCustomer[i].startDate).format();
        	paRskCustomer[i].endDate=Date.ofDate(paRskCustomer[i].endDate).format();
        	paRskCustomer[i].createTime=new Date().format();
        	paRskCustomer[i].updateTime=new Date().format();
        }
        object.paRskCustomerStr = JSON.stringify(paRskCustomer);
        Joyin.ajaxJson(basePath + 'submitmaintenance/mstmarketinstitutions/MstMarketInstitutions002/check', object, function(result) {
        	Joyin.confirm(result.data, function() {
            	Joyin.ajax("save",object, function(result) {
            		parent.layer.close(parent.layer.getFrameIndex(window.name));
                    });
            });
        });
    };
    
    /**
     * 页面加载初始化
     */
	var options = {
		page: true,
		ajax: {
	        url: JoyinUtil.getRealPath('submitmaintenance/mstmarketinstitutions/MstMarketInstitutions002/findPage'),
	        type: 'POST',
	        data: function() {
	            return {customerId: $('#customerId').val(),
	            		workflowId: $('#workflowId').val()
	            	};
	        }
	    },
        checkbox: false,
        addbtn: true,
        searchOnInit: true,
        cols:[
            {field:'ratingtype', title: 'MstMarketInstitutions001.ratingType',edit:true, editOption:{type:'select', multiple: false,result: CUSTOMER_RATING_TYPE}, sortable:false}
            ,{field:'rating', title: 'MstMarketInstitutions001.ratingResult', edit:true, editOption:{type:'select', multiple: false,result: CUSTOMER_RATING_RESULT}, sortable:false}
            ,{field:'ratingYear', title: 'MstMarketInstitutions001.ratingReportYear', edit:true, editOption:{type:'select', multiple: false,result: REPORT_YEAR}, sortable:false}
            ,{field:'ratingMode', title: 'MstMarketInstitutions001.modelType', edit:true, editOption:{type:'select', multiple: false,result: CUSTOMER_RATING_MODULE_TYPE}, sortable:false}
            ,{field:'startDate', title: 'MstMarketInstitutions001.ratingStartDate', type:'date', edit:true , sortable:false}
            ,{field:'endDate', title: 'MstMarketInstitutions001.ratingEndDate', type:'date', edit:true , sortable:false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    };
    
    /*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
    /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    /**
     * 画面加载完成
     */
    // 绑定按钮事件
    $('#MstMarketInstitutions002_btn_save').click(function() {
        saveOrSubmit('save');
    });
    $('#MstMarketInstitutions002_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
    
    /**
     * BECIF
     */
    $('#linkId2').change(function(){
    	Joyin.ajax(basePath + 'submitmaintenance/mstmarketinstitutions/MstMarketInstitutions002/selectInterfCmsInstitution',{becif:$('#linkId2').val()},
    	          function(result) {
    				if(result != null && result != ''){
    					Joyin.confirm(JoyinUtil.getMessage("MST_INFO_E_0002"),function() {
    						$('#customerId').val(result.customerId);
    						$('#customerName').val(result.institutionName);
    						$('#partyType3').val(result.partyType3).trigger('change');
    						$('#partyType2').val(result.partyType2).trigger('change');
    						$('#partyType1').val(result.partyType1).trigger('change');
    						$('#industry').val(result.repIndustry).trigger('change');
    						$('#industry2').val(result.repInddustryLevel2).trigger('change');
    						$('#insitituteCode').val(result.insitituteCode);
    						$('#isGov').val(result.isGov).trigger('change');
    						//$('#mainCapital').val(result.insitituteCode);核心资本
    						$('#registerCapital').val(result.registeCapital);
    						$('#province').val(result.province).trigger('change');
    						$('#city').val(result.city).trigger('change');
    						$('#county').val(result.county).trigger('change');
    						$('#holdingType').val(result.holdingType).trigger('change');
    						$('#customerObjType').val(result.customerObjType).trigger('change');
    						$('#isListedCompany').val(result.isListedCompany).trigger('change');
    						$('#isGroupCustomer').val(result.isGroupCustomer).trigger('change');
    						$('#parentCustomerName').val(result.parentCustomerName);
    						$('#isPrivate').val(result.isPrivate).trigger('change');
    						$('#isEscapeDept').val(result.isEscapeDept).trigger('change');
    						$('#creditType').val(result.creditType).trigger('change');
    						$('#isCreditRelatedTrd').val(result.isCreditRelatedTrd).trigger('change');
    						$('#structAdjustType').val(result.structAdjustType).trigger('change');
    						$('#indusUpgrateType').val(result.indusUpgrateType).trigger('change');
    						$('#busiScope').val(result.busiScope);
    						$('#mainBusi').val(result.mainBusi);
      	                },function(){
      	                	return false;
            			}
    					);
    				}
    		});
    })
    /**
     * wind编码
     */
    $('#linkId1').change(function(){
    	Joyin.ajax(basePath + 'submitmaintenance/mstmarketinstitutions/MstMarketInstitutions002/selectInterfCompintroduction',{objectId:$('#linkId1').val()},
  	          function(result) {
  				if(result != null && result != ''){
  					Joyin.confirm(JoyinUtil.getMessage("MST_INFO_E_0002"),function() {
  						$('#customerId').val(result.compId);
  						$('#customerName').val(result.compName);
  						//$('#partyType3').val(result.partyType3).trigger('change');
  						$('#partyType2').val(result.compProperty).trigger('change');
  						//$('#partyType1').val(result.partyType1).trigger('change');
  						//$('#industry').val(result.repIndustry).trigger('change');
  						//$('#industry2').val(result.repInddustryLevel2).trigger('change');
  						//$('#insitituteCode').val(result.insitituteCode);
  						//$('#isGov').val(result.isGov).trigger('change');
  						//$('#mainCapital').val(result.insitituteCode);核心资本
  						$('#registerCapital').val(result.regcapital);
  						$('#province').val(result.province).trigger('change');
  						$('#city').val(result.city).trigger('change');
  						//$('#county').val(result.county).trigger('change');
  						//$('#holdingType').val(result.holdingType).trigger('change');
  						//$('#customerObjType').val(result.customerObjType).trigger('change');
  						//$('#isListedCompany').val(result.isListedCompany).trigger('change');
  						//$('#isGroupCustomer').val(result.isGroupCustomer).trigger('change');
  						//$('#parentCustomerName').val(result.parentCustomerName);
  						//$('#isPrivate').val(result.isPrivate).trigger('change');
  						//$('#isEscapeDept').val(result.isEscapeDept).trigger('change');
  						//$('#creditType').val(result.creditType).trigger('change');
  						//$('#isCreditRelatedTrd').val(result.isCreditRelatedTrd).trigger('change');
  						//$('#structAdjustType').val(result.structAdjustType).trigger('change');
  						//$('#indusUpgrateType').val(result.indusUpgrateType).trigger('change');
  						$('#busiScope').val(result.businessScope);
  						//$('#mainBusi').val(result.mainBusi);
    	                },function(){
    	                	return false;
          			}
  					);
  				}
  		});
    })
}());