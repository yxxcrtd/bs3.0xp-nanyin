/**
 * 资金账户共通JS
 */
(function(){
	// 快速录入标志
	var quickFlag = false;
	   /**
     * 获取画面元素
     */
	function getElementById(id) {
		return $('#' + fundInfoEntityName + '_' + id);
	}
	var getSearchParams = function(){
    	var data = {};
    	data.quickFlag = quickFlag;
    	if($('#FinProductBuild002_quickFlag') && $('#FinProductBuild002_quickFlag').val() === 'N'){
    		data.ptlId = $('#' + getElementById('ptlIdElement').val()).val();
    	}else{
    		data.workflowId = workflowId;
        	data.finprodId = finprodFeeFinprodId;
        	data.entityName = fundInfoEntityName;
    	}
    	
    	return data;
    }
	
      var table = $("#fundAccountTable").joyintable({
        data:funDto==null?[]:funDto,
    	 searchOnInit: true,
          loadonce: true,
          rowno: true,
          page: false,
          checkbox:false,
          addbtn: true,
          height : 200,
        cols:[
              {field:'acctType',width:140, title: 'BussFinProductFundAccountInfo.acctType',sortable:false, edit:true, editOption:{type:'select',id:"acctType",childrenIds:"feeType,acctNo",formatoption:true,result: ACCT_TYPE_FG}}
             , {field:'feeType', width:200, title: 'BussFinProductFundAccountInfo.feeName', sortable: false,
            	edit:true,
            	editOption:{type:'select',multiple:true,id:"feeType",parentIds:getElementById('finprodMarketIdElement').val() + ',acctType,' +getElementById('profitTypeElement').val(), url: JoyinUtil.getRealPath('/component/prod/BussFinProductFeeInfoCommonRate/queryFundFeeInfoList')}}
             ,{field:'acctNo',width:220, title: 'BussFinProductFundAccountInfo.acctNo', edit:true,sortable:false, editOption:{type:'select',showField:'id',id:'acctNo',parentIds:"acctType",formatoption:true,url: JoyinUtil.getRealPath('/component/portfolio/FundAccountInfo/getAcctNo')}}
             ,{field:'ccy', title: 'BussFinProductFundAccountInfo.ccy',sortable:false}
             ,{field:'acctName', title: 'BussFinProductFundAccountInfo.acctName',sortable:false}
             ,{field:'bankDep', title: 'BussFinProductFundAccountInfo.bankDep',sortable:false}
             ,{field:'corrNo', title: 'BussFinProductFundAccountInfo.corrNo',sortable:false}
             ,{field:'largePayno', title: 'BussFinProductFundAccountInfo.largePayno',sortable:false}
             ,{field:'swiftCode', title: 'BussFinProductFundAccountInfo.swiftCode',sortable:false}
             ,{field:'remark', title: 'BussFinProductFundAccountInfo.remark',edit:true,sortable:false}
             ,{field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
         ],
         addbtn:true,
         done:function(){
        	 
              this.bind('click', '.delt', function(data, index) {
                  var object = {selectedItem: [data], operateType:"delete"};
                  table.deleteRow(index);
              });
              $('body').on('change','#acctNo',function(event,data){
                  if(data.value[0] != ''){
                      var selectData  = $('#acctNo').parents('td').data('sd')[0];
                      var obj = {'acctNo':data.value[0].concat('_',selectData.object)};
                      Joyin.ajax(JoyinUtil.getRealPath( "/component/prod/BussFinProductFeeInfoCommonRate/getCctInfo"),obj,function(result){
                          table.renderRow(data.rowIndex, {acctNo:result.acctNo,acctName:result.acctName,bankDep:result.bankDep,largePayno:result.largePayno, ccy: selectData.object,corrNo:result.corrNo,swiftCode:result.swiftCode});
                      });
                  }else{
                      table.renderRow(data.rowIndex, {acctNo:'',acctName:'',bankDep:'',largePayno:'', ccy: '',corrNo:'',swiftCode:''});
                  }
              });
             
             
         },
        beforeEditCell : function(preVal, field, rowData){
        	if (field === "feeType"){
        		 var allFee=BussFinProductFeeInfo.getAllRows();
              	  if(allFee.length==0){
              		Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0055'));
              		return false;
              	  }
        	}
        	return true;
        },
        editCell : function(value, data, field, cell, column){
        	/*
        	if (field === "feeType"){
        		if(value != ''){
        	    	var rowData = {};
                   	  var allFee=BussFinProductFeeInfo.getAllRows();
                   	  var feeStr="";
                   	 for(var i=0;i<allFee.length;i++){
                   		feeStr=allFee[i].feeId+",";
                   	 }
                   	if( $("#bussFinProductFloatFeeDto_feeId").val())
                   		feeStr+=$("#bussFinProductFloatFeeDto_feeId").val();
                     for(var j=0;j<value.split(",").length;j++){
                   		 if(feeStr.indexOf(value.split(",")[j])<0){
                   			 
                   			rowData.feeType="";
                   			table.renderRow(data.LAY_TABLE_INDEX, rowData);
                   			//Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0057'));
                   			//return false;
                   		 }
                   	 }
                   	 var rows=table.getAllRows();
                   	 for(var x=0;x<rows.length;x++){
                   		 if(x!=data.LAY_TABLE_INDEX){
                   			if(rows[x].feeType){
                   			 for(var j=0;j<value.split(",").length;j++){
                           		 if(rows[x].feeType.indexOf(value.split(",")[j])>=0){
                           			 
                           			 rowData.feeType="";
                           			table.renderRow(data.LAY_TABLE_INDEX, rowData);
                           			//Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0056'));
                           			//return false;
                           		 }
                   			 }
                   			}
                   		 }
                   	 }
                 }
        	}*/
        }
//        , dblclick:function(table, data) {
//            data.pageModel=PAGE_MODEL_REFERENCE;
//            Joyin.dialog(JoyinUtil.getLabel("MstCaptialAccount002"),
//                    JoyinUtil.getRealPath("invest/basicdata/mstcaptialaccount/MstCaptialAccount002/init"),
//                    "98%", "95%", data);
//        }
    });
        
    // 隐藏
    var hide = function() {
        $('#FundAccountInfo_DIV').hide();
    };
    // 展示
    var show = function() {
        $('#FundAccountInfo_DIV').show();
    };
    function productFundAccountInitAction(input_finprodId,bussFinProductFundAccountInfoDto){
    	
    	finprodFeeFinprodId = input_finprodId;
    	funDto=bussFinProductFundAccountInfoDto;
       table.reload({data:funDto,url:null}) ;
    	show();
    }
    // 开放计划公开接口
    window.BussFinProductFundAccountInfo = {
        table:table,
        hide: hide,
        show: show,
        productFundAccountInitAction:productFundAccountInitAction
    };
}());