$(function(){
    var counterId2;
	/**
     * 定义数据表格
     */
    var selectItem = COUNTER_TYPE_01;
    var table=$("#dataTable").joyintable({
        data:stlList != null ? stlList : [],
        searchOnInit:true,
        beforeEditCell: function(preVal, field, rowData){
            if(selectItem == COUNTER_TYPE_05 && rowData.accountType ==ACCT_TYPE_FG_A01){
                return false;
            }else{
                return true;
            }
        },
        height:'180px',
        cols:[
              {field:'accountType', width:150, title: 'ComTradeOpponent002.fundAccountType',sortable:false, edit:true, editOption:{type:'select',formatoption:true,result: ACCT_TYPE,initValue:ACCT_TYPE_A05}}
             ,{field:'acctNo', width:190, title: 'ComTradeOpponent002.account', edit:true,sortable:false, editOption:{type:'select',id:'acctNo',showField:'id',formatoption:true,url: JoyinUtil.getRealPath('/valuation/basicdata/comtradeopponent/ComTradeOpponent002/getAccount')}}
             ,{field:'acctName', width:220, title: 'ComTradeOpponent002.accountName',sortable:false}
             ,{field:'bankDep', width:190, sortField:'nowUserId', title: 'ComTradeOpponent002.bank',sortable:false}
             ,{field:'largePayno', width:155, title: 'ComTradeOpponent002.largePaymentBankNumber',sortable:false}
             ,{field:'isOpen', title: 'ComTradeOpponent002.state',edit:true,sortable:false,editOption:{type:'select',formatoption:true,result: USE_STATUS,initValue:IS_OPEN_01}}
             ,{field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
         ],
         addbtn:true
    });
    /**
     * 账号删除事件
     */
    var acctNoDel = function(table,data,index){
       
        if(pageModel==PAGE_MODEL_NEW){
            table.deleteRow(index);
        }else if(pageModel==PAGE_MODEL_EDIT){
//            if($('#wfl_operType').val() == 'MODIFY' || $('#wfl_operType').val() == null){
//                Joyin.ajaxJson(basePath + 'invest/basicdata/msttradeopponent/MstTradeOpponent002/getIsOpen', object, function(result) {
//                    if(result.data === "Y"){
//                        if(table && table.getAllRows()){
//                           for(var s in table.getAllRows()){
//                               if(table.getAllRows()[s].acctNo == data.acctNo &&
//                                       table.getAllRows()[s].ccy == data.ccy && s != index){
//                                   table.deleteRow(index);
//                                   return ;
//                               }else if(s==table.getAllRows().length - 1) {
//                                   Joyin.confirm(JoyinUtil.getMessage("COMMON_E_0030",JoyinUtil.getLabel("MstTradeOpponent002.account"),JoyinUtil.getLabel("MstTradeOpponent002.openList"))); 
//                                   return;
//                               }
//                           } 
//                        }
//                    }else{
//                        table.deleteRow(index);
//                    }
//                });
//                    
//                }
        	   if(data.acctNo && data.ccy && $("#counterId").val()) {
            	   var object = {acctNo:data.acctNo,ccy:data.ccy,counterId:$("#counterId").val()}
            	   Joyin.ajaxJson(basePath + 'valuation/basicdata/comtradeopponent/ComTradeOpponent002/deleteAcctNoCheck', object, function(result) {
            		   if(result.data === "Y"){
            			   Joyin.error(JoyinUtil.getMessage("MST_TRADE_OPPONENT_E_0001",data.acctNo)); 
                           return;
            		   } else {
            			   table.deleteRow(index); 
            		   }
            	   });  
        	   } else {
        		   table.deleteRow(index);   
        	   }
            }
           
        }
    /**
     * 表格一的删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        acctNoDel(table,data,index);
    });
    /**
     * 定义数据表格
     */
    var table2=$("#dataTable2").joyintable({
        data:  stlList != null ? stlList : [],
        searchOnInit:true,
        beforeEditCell: function(preVal, field, rowData){
            
            if(selectItem == COUNTER_TYPE_05 && rowData.accountType ==ACCT_TYPE_FG_A01){
                return false;
            }else{
                return true;
            }
        },
        addclick:function(rowData){
            if(selectItem == COUNTER_TYPE_03){
                rowData.accountType=ACCT_TYPE_A06;
            }
        },
        cols : [
              {field:'accountType', title: 'ComTradeOpponent002.fundAccountType',sortable:false, edit:true, editOption:{type:'select',formatoption:true,result: ACCT_TYPE,initValue:ACCT_TYPE_A05}}
             ,{field:'acctNo', title: 'ComTradeOpponent002.account',width:300, edit:true,sortable:false, editOption:{type:'select',id:'acctNo1',showField:'id',formatoption:true, url: JoyinUtil.getRealPath('/valuation/basicdata/comtradeopponent/ComTradeOpponent002/getAccount')}}
             ,{field:'acctName', title: 'ComTradeOpponent002.accountName',sortable:false}
             ,{field:'bankDep', sortField:'nowUserId', title: 'ComTradeOpponent002.bank',sortable:false}
             ,{field:'largePayno', title: 'ComTradeOpponent002.largePaymentBankNumber',sortable:false}
             ,{field:'isOpen', title: 'ComTradeOpponent002.state',sortable:false, edit:true,editOption:{type:'select',formatoption:true,result: USE_STATUS,initValue:IS_OPEN_01}}
             ,{field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
        ],
        height:'180px',
        addbtn:true
    });
    
    /**
     * 账号表删除事件
     */
    table2.bind('click', '.delt', function(data, index) {

        if(selectItem != COUNTER_TYPE_05 || data.accountType != ACCT_TYPE_FG_A01){
            acctNoDel(table2,data,index);
        }
    });
    /**
     * 定义数据表格
     */
    // var table3=$("#dataTable3").joyintable({
    //     data:finList != null ? finList : [],
    //     searchOnInit:true,
    //     beforeEditCell: function(preVal, field, rowData){
    //         if(field == 'feeType' || field == 'basis' || field == 'basicBill'){
    //             return false;
    //         }else{
    //             return true;
    //         }
    //     },
    //     cols : [
    //             {field:'feeId', title: 'ComTradeOpponent002.costName', edit:true,sortable:false, editOption:{type:'select',formatoption:true,id:'feeId', url: JoyinUtil.getRealPath('/common/basicdata/comtradeopponent/ComTradeOpponent002/getFeeId')}}
    //             ,{field:'feeType', title: 'ComTradeOpponent002.feeType', edit:true,sortable:false, editOption:{type:'select',formatoption:true,id:'feeType', result:FEE_SPECIES_TYPE}}
    //             ,{field:'rate', title: 'ComTradeOpponent002.initialRate', edit:true,sortable:false,type:'number',textrange:'2'}
    //             ,{field:'basicBill', sortField:'nowUserId', title: 'ComTradeOpponent002.basedOn',edit:true,sortable:false,editOption:{type:'select',formatoption:true,result: BILL_BASE_FEE_KIND}}
    //             ,{field:'basis', title: 'ComTradeOpponent002.calculationBasis',edit:true,sortable:false,editOption:{id:'basis',type:'select',formatoption:true,result: BASIS}}
    //             ,{field:'operation', title: 'SysCommon.operation', type:'action',sortable:false, buttons: ['delt']}
    //        ],
    //     height:'225px',
    //     addbtn:true
    // });
    //
    // table3.bind('click', '.delt', function(data, index) {
    //     table3.deleteRow(index);
    // });
    /**
     * 画面联动
     * @param temp 账号类型
     * @returns
     */
	function chan(temp){
	    selectItem = temp;
	    if(temp == COUNTER_TYPE_05){
	        $('a.btn-table-add').css('display','none');
	    }else{
	        $('a.btn-table-add').css('display','');
	    }
	    if(temp == COUNTER_TYPE_01){//资管产品类型
            $(".branch").css("display","none");
            $(".managementProduct").css("display","none");
            // $(".channelPlan").css("display","none");
            $("#FFRJG").hide();
            $(".counterparty").css("display","none");
            $(".marketInstitutions").show();
            $("#MINSACCOUNT").hide();
            $("#counterType").val(temp).trigger("change");
        }else if(temp == COUNTER_TYPE_02){//市场机构类型
            $(".branch").css("display","none");
            $(".marketInstitutions").css("display","none");
            // $(".channelPlan").css("display","none");
            $("#FFRJG").hide();
            $(".counterparty").css("display","none");
            $(".managementProduct").show();
            $("#ACCOUNTINFO").hide();
            $("#counterType2").val(temp).trigger("change");
        }else if(temp == COUNTER_TYPE_04){//分行类型
            $(".managementProduct").css("display","none");
            $(".marketInstitutions").css("display","none");
            // $(".channelPlan").css("display","none");
            $("#FFRJG").hide();
            $(".counterparty").css("display","none");
            $(".branch").show();
            $("#ACCOUNTINFO").hide();
            $("#counterType4").val(temp).trigger("change");
        }else if(temp == COUNTER_TYPE_05){//内部交易对手类型
            $(".branch").css("display","none");
            $(".marketInstitutions").css("display","none");
            $(".managementProduct").css("display","none");
            // $(".channelPlan").css("display","none");
            $("#FFRJG").hide();
            $(".counterparty").show();
            $("#ACCOUNTINFO").hide();
            $("#counterType5").val(temp).trigger("change");
        }else{
            $(".branch").css("display","none");
            $(".marketInstitutions").css("display","none");
            $(".managementProduct").css("display","none");
            $(".counterparty").css("display","none");
            $("#FFRJG").show();
            $("#commonRemark").show();
            // $(".channelPlan").show();
            if(pageModel==PAGE_MODEL_NEW){
                $('#repPlanProp').val(CHB_PRO_PROPERTY_99).trigger('change');
                $('#repBuySruct').val(CHB_BUY_STRUCT_01).trigger('change');
                $('#repManageType').val(CHB_MANATYPE_PLAN_ASSET_01).trigger('change');
                $('#repIsCtrlBttom').val(YES_OR_NO_Y).trigger('change');
            }
            $("#counterType3").val(temp).trigger("change");
        }
	}
    /**
     * 页面切换
     */
	function initChange(){
	    $(".counterType").change(function(){
	        var temp = this.value;
	        if(selectItem != temp){
	            for(var s = table.getAllRows().length-1;s>-1;s--){
	                table.deleteRow(s);
	            }
	            for(var s = table2.getAllRows().length-1;s>-1;s--){
	                table2.deleteRow(s);
	            }
	            // for(var s = table3.getAllRows().length-1;s>-1;s--){
	            //     table3.deleteRow(s);
	            // }
	            $('input').val('');
	            $('.tabJump').val('').trigger('change');
	            $('.tabJump').val('');
	            chan(temp);
	        }
	    })
   }
	$("#type1").change(function(){
	    var type = $("#type1").val();
	    if(PLAN_ASSET_TYPE_02 == type){
	        $("#type2").attr("disabled",false);
	    }else{
	        $("#type2").attr("disabled",true);
	        $('#type2').val('').trigger('change');
	    }
	});
	$("#counterId5").change(function(){
	    //


	    var text = $("#counterId5").select2('data')[0].text;
	    $("#counterName5").val(text);
	    /*Joyin.ajax(basePath + "common/basicdata/comtradeopponent/ComTradeOpponent002/getCountInfo",{'portfolioId':$("#counterId5").val()},function(result){
	        table2.clear();
            for(var i in result){
                result[i].isOpen = YES_OR_NO_Y;
                result[i].accountType = result[i].busiAcctNo
                table2.addRow(result[i]);
            }
            $('a.btn-table-add').css('display','none');
        });*/

	});
	$("#headOffice").change(function(){
	    var text = "";
	    text = $("#headOffice").find("option:selected").text();
	    $("#counterName4").val(text);
	});
	//交易对手一二级联动 yjm start 20190808
	var counterType1Change = function () {
       var pTypeOne = $('#pTypeOne').val();
        if (pTypeOne == C01) {
            $('#pTypeTwo').val(C0100).trigger('change').attr('disabled', true);
        }else if (pTypeOne == C02) {
            $('#pTypeTwo').val(C0200).trigger('change').attr('disabled', true);
        }else if (pTypeOne == C03) {
            $('#pTypeTwo').val(C0300).trigger('change').attr('disabled', true);
        }else if (pTypeOne == C06) {
            $('#pTypeTwo').val(C0600).trigger('change').attr('disabled', true);
        }else {
            $('#pTypeTwo').attr('disabled', false);
        }
        
    }
    //交易对手一二级联动 yjm end 20190808
	
	
	//START 资管产品 交易对手一二级联动 add by wj 20200413
	var counterType2Change = function () {
	       var pTypeOne = $('#pTypeOne2').val();
	        if (pTypeOne == C01) {
	            $('#pTypeTwo2').val(C0100).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C02) {
	            $('#pTypeTwo2').val(C0200).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C03) {
	            $('#pTypeTwo2').val(C0300).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C06) {
	            $('#pTypeTwo2').val(C0600).trigger('change').attr('disabled', true);
	        }else {
	            $('#pTypeTwo2').attr('disabled', false);
	        }
	        
	}
	
	var counterType3Change = function () {
	       var pTypeOne = $('#pTypeOne3').val();
	        if (pTypeOne == C01) {
	            $('#pTypeTwo3').val(C0100).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C02) {
	            $('#pTypeTwo3').val(C0200).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C03) {
	            $('#pTypeTwo3').val(C0300).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C06) {
	            $('#pTypeTwo3').val(C0600).trigger('change').attr('disabled', true);
	        }else {
	            $('#pTypeTwo3').attr('disabled', false);
	        }
	        
	}
	
	var counterType4Change = function () {
	       var pTypeOne = $('#pTypeOne4').val();
	        if (pTypeOne == C01) {
	            $('#pTypeTwo4').val(C0100).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C02) {
	            $('#pTypeTwo4').val(C0200).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C03) {
	            $('#pTypeTwo4').val(C0300).trigger('change').attr('disabled', true);
	        }else if (pTypeOne == C06) {
	            $('#pTypeTwo4').val(C0600).trigger('change').attr('disabled', true);
	        }else {
	            $('#pTypeTwo4').attr('disabled', false);
	        }
	        
	}
	//end 资管产品 交易对手一二级联动 add by wj 20200413
	
	/**
	 * 界面初始化
	 * @returns
	 */
    function init(){
        counterId2 = $('#counterId').val();
    	// /**新建页面不展示内部交易对手 add by chendijun**/
    	// if(pageModel===PAGE_MODEL_NEW) {
    	// 	$(".counterType").updateChosenArr({item: [COUNTER_TYPE_01,COUNTER_TYPE_02,COUNTER_TYPE_03,COUNTER_TYPE_04],
        //          disabled: false});
    	// }
    	// /**新建页面不展示内部交易对手 add by chendijun**/
    	
        var initType =  $(".counterType").val();
        chan(initType);
        <!--update by chenjiaixng 交易对手改造 20210729 start-->
/*        $('#pTypeOne').change(counterType1Change);
        $('#pTypeOne2').change(counterType2Change);
        $('#pTypeOne3').change(counterType3Change);
        $('#pTypeOne4').change(counterType4Change);*/
        //counterType1Change($('#pTypeOne').val());
        <!--update by chenjiaixng 交易对手改造 20210729 end-->
        var counterType =[COUNTER_TYPE_02,COUNTER_TYPE_03,COUNTER_TYPE_04];
        if(pageModel!=PAGE_MODEL_NEW){
            //交易对手代码不允许修改
            $(".counterId").attr("disabled",true);
            if(counterType.indexOf(initType)<0){
                $("#type2").attr("disabled",true);
            }
            $(".counterType").attr("disabled",true);
            var ttt = $(".counterId").attr("disabled");
        }else{
            chan(COUNTER_TYPE_01);
            initChange();
        }
    }
    function serializeTable(tables){
        if(tables!=null && tables!=''){
            var selArr = tables.getAllRows();
        }
        if (!selArr || selArr.length == 0) {
            return;
        }else{
            for(var j=selArr.length-1;j>=0;j--){
                if(selArr[j].acctNo!=null){
                    if(selArr[j].acctNo==null || selArr[j].accNo==''){
                        selArr.splice(j,1);
                        
                    }else{
                    }
                }else{
                    if(selArr[j].feeId==null || selArr[j].feeId==''){
                        selArr.splice(j,1);
                    }else{
                    }
                }
            }
        }
        return selArr;
    }
    /**
     * 进行保存和提交操作
     * @param operateType 操作类型
     * @returns
     */
    function saveOrSubmit(operateType) {
        var object = null;
        if(selectItem == COUNTER_TYPE_01){
            object = $.serializeToObject('#marketForm');
            // if(pageModel!=PAGE_MODEL_NEW){
            //     object.counterId=counterId2 + "/" + object.counterId;
            // }
            var remarkObj = $.serializeToObject('#marketRemarksForm');
            $.extend(object,remarkObj);
            object.counterName = $("#customerId").select2('data')[0].object;
             var selectDto= serializeTable(table);
             for(var i in selectDto){
                 selectDto[i].acctNo = selectDto[i].acctNo.concat('_',selectDto[i].ccy);
             }
             object.selectStlCapacct = selectDto;
        }else if(selectItem == COUNTER_TYPE_02){
            object = $.serializeToObject('#manageForm');
            // if(pageModel!=PAGE_MODEL_NEW){
            //     object.counterId=counterId2 + "/" + object.counterId;
            // }
            var remarkObj = $.serializeToObject('#marketRemarksForm');
            $.extend(object,remarkObj);
            var selectDto= serializeTable(table2);
            for(var i in selectDto){
                selectDto[i].acctNo = selectDto[i].acctNo.concat('_',selectDto[i].ccy);
            }
            object.selectStlCapacct = selectDto;
        }else if(selectItem == COUNTER_TYPE_04){
            object = $.serializeToObject('#branchForm');
            // if(pageModel!=PAGE_MODEL_NEW){
            //     object.counterId=counterId2 + "/" + object.counterId;
            // }
            var remarkObj = $.serializeToObject('#marketRemarksForm');
            $.extend(object,remarkObj);
            var selectDto= serializeTable(table2);
            for(var i in selectDto){
                selectDto[i].acctNo = selectDto[i].acctNo.concat('_',selectDto[i].ccy);
            }
            object.selectStlCapacct = selectDto;
            // object.selectFinCash = serializeTable(table3);
        }else if(selectItem == COUNTER_TYPE_05){
            object = $.serializeToObject('#counterpartyForm');
            // if(pageModel!=PAGE_MODEL_NEW){
            //     object.counterId=counterId2 + "/" + object.counterId;
            // }
            var remarkObj = $.serializeToObject('#marketRemarksForm');
            $.extend(object,remarkObj);
            var selectDto= serializeTable(table2);
            for(var i in selectDto){
                selectDto[i].acctNo = selectDto[i].acctNo.concat('_',selectDto[i].ccy);
            }
            object.selectStlCapacct = selectDto;
        }else{
            /*object = $.serializeToObject('#branchForm');*/
            object = $.serializeToObject('#channelForm');
            // if(pageModel!=PAGE_MODEL_NEW){
            //     object.counterId=counterId2 + "/" + object.counterId;
            // }
            var remarkObj = $.serializeToObject('#marketRemarksForm');
            $.extend(object,remarkObj);
            var selectDto= serializeTable(table2);
            for(var i in selectDto){
                selectDto[i].acctNo = selectDto[i].acctNo.concat('_',selectDto[i].ccy);
            }
            object.selectStlCapacct = selectDto;
        }
        //设置pageModel和operateType
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo()
        }
        object.counterType = selectItem;
        object.pageModel = pageModel;
        object.operateType = operateType;
        //对保存事件进行验证
        Joyin.ajaxJson(basePath + 'valuation/basicdata/comtradeopponent/ComTradeOpponent002/check', object, function(result) {
            if(operateType === 'save'){
                //保存工作流
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
                    WorkFlow.doSaveList(basePath + 'valuation/basicdata/comtradeopponent/ComTradeOpponent002/save', object, "ComTradeOpponent002_btn_save");
                });
            }else{
                //提交工作流
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                        WorkFlow.doSubmitList(basePath + 'valuation/basicdata/comtradeopponent/ComTradeOpponent002/save', object, "ComTradeOpponent002_btn_submit");
                });
            }
        });
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        init();
        $('#ComTradeOpponent002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ComTradeOpponent002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        $('#ComTradeOpponent002_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PerformanceBenchmark002.add"),
                        basePath + "valuation/basicdata/comtradeopponent/ComTradeOpponent002/init",
                        "95%", "95%", {pageModel:PAGE_MODEL_NEW});
        });
        
        // $('body').on('change','#feeId',function(event,data){
        //     if(data.value[0] != ''){
        //         var obj = {'feeId':data.value[0]};
        //         Joyin.ajax(basePath + "common/basicdata/comtradeopponent/ComTradeOpponent002/getBasis",obj,function(result){
		// 			var rowDataFix = {};
        //         	rowDataFix.basis = result.basisStr;
        //             rowDataFix.basicBill = result.basicBillStr;
        //             rowDataFix.feeType=result.feeTypeStr;
        //             table3.renderRow(data.rowIndex, rowDataFix);
	    //     		// 更新缓存值
	    //     		var cacheData = table3.getRow(data.rowIndex, true);
	    //     		cacheData.basis = result.basis;
	    //     		cacheData.basicBill = result.basicBill;
	    //     		cacheData.feeType = result.feeType;
        //         });
        //     }else{
        //         table3.renderRow(data.rowIndex, {basis:'',basicBill:''});
        //     }
        // });
        
        $('body').on('change','#acctNo',function(event,data){
            if(data.value[0] != ''){
                var selectData  = $('#acctNo').parents('td').data('sd')[0];
                var obj = {'acctNo':data.value[0].concat('_',selectData.object)};
                Joyin.ajax(basePath + "valuation/basicdata/comtradeopponent/ComTradeOpponent002/getCctInfo",obj,function(result){
                    table.renderRow(data.rowIndex, {acctName:result.acctName,bankDep:result.bankDep,largePayno:result.largePayno, ccy: selectData.object});
                });
            }else{
                table.renderRow(data.rowIndex, {acctName:'',bankDep:'',largePayno:'', ccy: ''});
            }
        });
        $('body').on('change','#acctNo1',function(event,data){
            if(data.value[0] != ''){
                var selectData  = $('#acctNo1').parents('td').data('sd')[0];
                var obj = {'acctNo':data.value[0].concat('_',selectData.object)};
                Joyin.ajax(basePath + "valuation/basicdata/comtradeopponent/ComTradeOpponent002/getCctInfo",obj,function(result){
                    if(result == null || result == ''){
                        table2.renderRow(data.rowIndex, {acctName:'',bankDep:'',largePayno:'', ccy: ''});
                    }else{
                        table2.renderRow(data.rowIndex, {acctName:result.acctName,bankDep:result.bankDep,largePayno:result.largePayno, ccy: selectData.object});
                    }
                });
            }
        });
    });    
})
