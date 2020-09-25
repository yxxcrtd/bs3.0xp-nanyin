(function() {
	/**
	 * 投资比例区间
	 */
	var investProporRangeTable = $('#investProporRange').joyintable({
		ajax: {
    		url: 'queryInvestProporPF',
    		type: 'POST',
    		data: {prodId:prodId}
    	},
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: true,
        height : 200,
        cols:[
            {field:'code', title: '投资种类', sortable: false,
            	edit:true,
            	editOption:{type:'select', result:PINAN_ASSET_TYPE}}
            ,{field:'tLimit', title: '投资比例上限', sortable: false, edit:true, digit:6}
            ,{field:'dLimit', title: '投资比例下限', sortable: false, edit:true, digit:6}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ],
        done:function(){
            this.bind('click', '.delt', function(data, index) {
                var object = {selectedItem: [data], operateType:"delete"};
                investProporRangeTable.deleteRow(index);
            });
       },
	});
			
	/**
     * 定义费用信息数据表格
     */
    var feeInfoTable = $('#feeInfo').joyintable({
    	ajax: {
    		url: 'queryFeeInfoPF',
    		type: 'POST',
    		data: {prodId:prodId}
    	},
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        addbtn: true,
        height : 200,
        cols:[
            {field:'feeId', title: '费用名称', sortable: false,
            	edit:true,
            	editOption:{type:'select', result:FEEINFO_LIST}}//url: JoyinUtil.getRealPath('/disclosure/information/manage/prodfilemanage/ReportProdFileManage002/queryFeeInfoList')
            ,{field:'feerate', title: '费率%', sortable: false, type:'number', edit:true, digit:6}
            ,{field:'basicbill', title: '计费基数', sortable: false,
            	edit:true,
            	editOption:{type:'select',result:BILL_BASE_FEE_KIND}
            }
            ,{field:'basis', title: '计费基础', sortable: false,
            	edit:true,
            	editOption:{type:'select',result:BASIS_SELECT}
            }
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
        ,done:function(){
            this.bind('click', '.delt', function(data, index) {
                var object = {selectedItem: [data], operateType:"delete"};
                feeInfoTable.deleteRow(index);
            });
       },
    });
    
//    /**
//	 * 绑定删除事件
//	 */
//    feeInfoTable.bind('click', '.delt', function(data, index) {
//    	feeInfoTable.removeRow(data);
//	});
    
	var init=function() {
		feeInfoTable.reload();
		investProporRangeTable.reload();
		
        if(pageModel!=PAGE_MODEL_NEW){
            $("#prodId").attr('disabled',true);
            $('#profitType').attr('disabled',true);
            $('#dealMode').attr('disabled',true);
            $('#issueType').attr('disabled',true);
            $('#operateMode').attr('disabled',true);
            $('#profitFlag').attr('disabled',true);
            $('#raiseType').attr('disabled',true);
            $('#riskLevel').attr('disabled',true);
        }
        if(pageModel == PAGE_MODEL_EDIT){
        	//收益类型下拉框change事件
    		profitTypeChangeAction();
        	//是否有投资封闭期change事件
    		investcloseFlagChangeAction();
        	//产品处理模式下拉框change事件
    		dealModeChangeAction();
        	//期限change事件
    		termtypeChangeAction();
        	//发行方式change事件
    		issueTypeChangeAction();
        }
    };
    
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        //获取费用信息列表值
        object.feeLists = object.feeInfoList;
        
        //获取投资比例区间列表值
        var investRangeTable = investProporRangeTable.getAllRows();
//        var investTypes = '';
//        var investPropors = '';
        if(investRangeTable&&investRangeTable.length>0){
     	   for(var i=0;i<investRangeTable.length;i++){
     		   if(!investRangeTable[i].code){
//     			  investTypes+=investRangeTable[i].investTypes+";";
//     		   }else{
     			  Joyin.error("请选择投资种类！");
     			  return;
     		   }
     		   if(!investRangeTable[i].tLimit){
//     			  investPropors+=investRangeTable[i].investPropors+";";
//     		   }else{
     			  Joyin.error("请输入投资比例上限！");
     			  return;
     		   }
     		  if(!investRangeTable[i].dLimit){
     			  Joyin.error("请输入投资比例下限！");
    			  return;
     		  }
     	   }
     	   //获取投资比例区间列表
     	  object.investLists=investRangeTable;
//     	  object.investTypes=investTypes;
//     	  object.investPropors=investPropors;
        }else{
        	Joyin.error("请选择投资比例区间！");
        	return;
        }
        Joyin.ajaxJson("check",object,function(result){
            if(result){
                    Joyin.ajaxJson("doSave",object,function(result){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                });
            }
        })
    }
    
    /**
     * 收益类型下拉框change事件
     */
    var profitTypeChangeAction = function(){
    	var profitType = $('#profitType').val();
    	
    	//1 收益类型联动控制产品处理模式下拉框的值
        if(profitType === PROFIT_TYPE_01){//净值型
            //产品处理模式-普通/周期型/分档
        	$("#dealMode").empty();
            $('#dealMode').append("<option value='01'>普通</option>"); 
            $('#dealMode').append("<option value='10'>周期型</option>");
            $('#dealMode').append("<option value='11'>分档</option>");
        }else if(profitType === PROFIT_TYPE_03){ //货币型
        	//产品处理模式-普通
        	$("#dealMode").empty();
            $('#dealMode').append("<option value='01'>普通</option>"); 
        }else if(profitType === PROFIT_TYPE_02){//预期收益型
        	//产品处理模式-普通/分档
        	$("#dealMode").empty();
        	$('#dealMode').append("<option value='01'>普通</option>"); 
            $('#dealMode').append("<option value='11'>分档</option>");
        }
        dealModeChangeAction();
        
        //2 根据收益类型和发行方式联动带出产品运作模式
        var issueType = $('#issueType').val();//发行方式
        if(issueType === ISSUE_MODE_01){//发行方式-封闭式
        	if(profitType === PROFIT_TYPE_02){//收益类型-预期收益型
        		//产品运作模式-封闭式非净值型
        		$('#operateMode').val(SVR_OPERATEMODE_DECLARE_02).trigger("change").prop('disabled', true);
        	}else{//收益类型-净值型/货币型
        		//产品运作模式-封闭式净值型
        		$('#operateMode').val(SVR_OPERATEMODE_DECLARE_01).trigger("change").prop('disabled', true);
        	}
        }else if(issueType === ISSUE_MODE_02){//发行方式-开发式
        	if(profitType === PROFIT_TYPE_02){//收益类型-预期收益型
        		//产品运作模式-开放式非净值型
        		$('#operateMode').val(SVR_OPERATEMODE_DECLARE_04).trigger("change").prop('disabled', true);
        	}else{//收益类型-净值型/货币型
        		//产品运作模式-开放式净值型
        		$('#operateMode').val(SVR_OPERATEMODE_DECLARE_03).trigger("change").prop('disabled', true);
        	}
        }
        
        //3 收益类型为“预期收益型”时，必填 预期年化收益率
        if(profitType === PROFIT_TYPE_02){
        	$('#yield').prop('disabled',false);
        }else{
        	$('#yield').val('').trigger('change').attr('disabled',true);
        }
        
    }
    /**
     * 是否有投资封闭期change事件
     */
    var investcloseFlagChangeAction = function(){
    	var investcloseFlag = $('#investcloseFlag').val();
    	if(investcloseFlag === YES){//是
    		$('#investcloseStrdate').prop('disabled',false);
    		$('#investcloseEnddate').prop('disabled',false);
    	}else{//否
    		//投资封闭期（从）、投资封闭期（到）不可填
    		$('#investcloseStrdate').val('').trigger('change').attr('disabled',true);
    		$('#investcloseEnddate').val('').trigger('change').attr('disabled',true);
    	}
    }
    /**
     * 产品处理模式change事件
     */
    var dealModeChangeAction = function(){
    	var dealMode = $('#dealMode').val();
    	if(dealMode === DISPOSE_PATTERN_10){//周期型
    		$('#termtype').prop('disabled',false);
    		$('#autocontinueFlag').prop('disabled',false);
    	}else{
    		//期限不可填
    		$('#termtype').val('').trigger('change').attr('disabled',true);
    		//是否自动续投不可填
    		$('#autocontinueFlag').trigger('change').attr('disabled',true);
    	}
    }
    /**
     * 期限change事件
     */
    var termtypeChangeAction = function(){
    	var termtype = $('#termtype').val();
    	if(termtype === TERM_TYPE_03){//期限类型_无固定期限
    		//投资周期（天）不可填
    		$('#investmentCycle').val('').trigger('change').attr('disabled',true);
    	}else{
    		$('#investmentCycle').prop('disabled',false);
    	}
    }
    /**
     * 发行方式change事件
     */
    var issueTypeChangeAction = function(){
    	var issueType = $('#issueType').val();
    	 
        //1 根据收益类型和发行方式联动带出产品运作模式
        var profitType = $('#profitType').val();//收益类型
        if(profitType){
        if(profitType === PROFIT_TYPE_02){//收益类型-预期收益
        	if(issueType === ISSUE_MODE_01){//发行方式_封闭式
        		//产品运作模式-封闭式非净值型
        		$('#operateMode').val(SVR_OPERATEMODE_DECLARE_02).trigger("change").prop('disabled', true);
        	}else{//发行方式-开放式
        		//产品运作模式-开放式非净值型
        		$('#operateMode').val(SVR_OPERATEMODE_DECLARE_04).trigger("change").prop('disabled', true);
        	}
        }else{//收益类型-净值型/货币型
        	if(issueType === ISSUE_MODE_01){//发行方式_封闭式
        		//产品运作模式-封闭式净值型
        		$('#operateMode').val(SVR_OPERATEMODE_DECLARE_01).trigger("change").prop('disabled', true);
        	}else{//发行方式-开放式
        		//产品运作模式-开放式净值型
        		$('#operateMode').val(SVR_OPERATEMODE_DECLARE_03).trigger("change").prop('disabled', true);
        	}
        }
        }
        
        //2 发行方式为“开放式”时，必填申购开放日、赎回开放日
        if(issueType === ISSUE_MODE_02){//发行方式_开放式
        	$('#purOpenDate').prop('disabled',false);
        	$('#redOpenDate').prop('disabled',false);
        }else{
        	$('#purOpenDate').val('').trigger('change').attr('disabled',true);
        	$('#redOpenDate').val('').trigger('change').attr('disabled',true);
        }

    }
    /**
	 * 起息日、到期日联动处理产品期限
	 */
	var vdateMdateChangeAction = function(){
		var vdate = $('#vdate').val();
		var mdate = $('#mdate').val();
		if (vdate && mdate){
			var days = (Date.ofDate(mdate) - Date.ofDate(vdate))/(24*60*60*1000);
			if(days<=0){
				Joyin.error(JoyinUtil.getMessage("起息日必须小于到期日", JoyinUtil.getLabel("ReportProdFileManage002.mdate")));
				$('#mdate').val('');	
				return;
			}else{
				$('#term').val(days);
			}
		}
	};
    $(function() {
    	init();
    	
    	//收益类型下拉框change事件
    	$('#profitType').change(function(){
    		profitTypeChangeAction();
    	});

    	//是否有投资封闭期change事件
    	$('#investcloseFlag').change(function(){
    		investcloseFlagChangeAction();
    	});
    	
    	//产品处理模式下拉框change事件
    	$('#dealMode').change(function(){
    		dealModeChangeAction();
    	});
    	
    	//期限change事件
    	$('#termtype').change(function(){
    		termtypeChangeAction();
    	});
    	
    	//发行方式change事件
    	$('#issueType').change(function(){
    		issueTypeChangeAction();
    	});
    	
    	// 起息日、到期日联动处理产品期限
		$('#vdate').change(function(){
			vdateMdateChangeAction();
		});
		$('#mdate').change(function(){
			vdateMdateChangeAction();
		});
    	
        // 按钮事件
        $('#ReportProdFileManage002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());
