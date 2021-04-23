(function(){
	
    /**
     * 获取查询参数
     */
    var genEntrustOrderParam = function() {
        var pageParam = $.serializeToObject('#cfetsBondDealQuotedPrice006_entrustOrder_form');
        if (pageParam.invManagerId && pageParam.invManagerId != '') {
            pageParam.invManagerId = pageParam.invManagerId.join(",");
        } else {
            pageParam.invManagerId = null;
        }
        // 只查询已接受的 委托
        pageParam.entrustStatus = 'unComplete';
        pageParam.mySide = mySide;
        pageParam.quotaPageModel = quotaPageModel;
        if(quotaPageModel == 'dialogueResponse' || isDzh == 'Y'){
        	pageParam.settleSpreed = settleSpreed;
        }
        if (pageParam.portfolioId && pageParam.portfolioId != '') {
            pageParam.portfolioId = pageParam.portfolioId.join(",");
        } else {
            pageParam.portfolioId = null;
        }
        if (pageParam.entrustId && pageParam.entrustId != '') {
            pageParam.entrustId = pageParam.entrustId.join(",");
        } else {
            pageParam.entrustId = null;
        }
        pageParam.isDzh = isDzh;
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var entrustOrderTable=$("#CfetsBondDealQuotedPrice006_entrust_table").joyintable({
        ajax: {
            url: 'queryEntrustOrderList',
            type: 'POST',
            data: function() {
                return genEntrustOrderParam();
            }
        },
        height:330,
        searchOnInit:true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols:[
            {field:'entrustStatusStr', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.orderStatus', sortField:"entrustStatus"}
           ,{field:'transactioncom', title: 'CfetsBondDealQuotedPrice006.entrust.table.finishPercent',render : function(data, type, row, meta){
	               return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text'>"+row.transactioncom+"%</span></div></div>";
	           }, width:'8%',sortable:false}
           ,{field:'invManagerName', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.invManagerId'}
           ,{field:'portfolioId', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.portfolioId'}
           ,{field:'portfolioName', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.portfolioName'}
           ,{field:'secAcctName', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.secAcctId'}
           ,{field:'finprodMarketId', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.assetId'}
           ,{field:'finprodAbbr', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.assetAbbr'}
           ,{field:'settleSpreedStr', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.settleSpeed', sortField:"settleSpreed"}
           ,{field:'psStr', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.entrustPs', sortField:"ps"}
           ,{field:'entrustNum', width:120,type:'number', title: 'CfetsBondDealQuotedPrice006.entrust.table.entrustQuota'}
           ,{field:'finishAmt', width:120,type:'number', title: 'CfetsBondDealQuotedPrice006.entrust.table.comQuota'}
           ,{field:'avaNum', width:120,type:'number', title: 'CfetsBondDealQuotedPrice006.entrust.table.avaQuota'}
           ,{field:'price', width:120,type:'number', title: 'CfetsBondDealQuotedPrice006.entrust.table.cleanPrice'}
           ,{field:'matureYield', width:120,type:'number', title: 'CfetsBondDealQuotedPrice006.entrust.table.yield'}
           ,{field:'counterName', width:120, title: 'CfetsBondDealQuotedPrice006.entrust.table.dealCounter'}
           ,{field:'vdate', width:120,type:'date', title: 'CfetsBondDealQuotedPrice006.entrust.table.entrustStartDate'}
           ,{field:'mdate', width:120,type:'date', title: 'CfetsBondDealQuotedPrice006.entrust.table.entrustEndDate'}
           ,{field:'entrustId', width:180, title: 'CfetsBondDealQuotedPrice006.entrust.table.entrustId'}
           ,{field:'quoteType', visible: false}
           ,{field:'entrustStatus', visible: false}
           ,{field:'calculateField', visible: false}
           ,{field:'exerYield', visible: false}
		   ,{field:'ps', visible:false}
		   ,{field:'settleSpreed', visible:false}
		   ,{field:'entrustAvaNum', visible:false}
        ]
    });
    
    /**
	 * 确认点击事件
	 */
    var confirmAction = function(){
    	if(entrustOrderTable!=null && entrustOrderTable!=''){
            var selArr = entrustOrderTable.getAllCheckedRows();
            
            if (!selArr || selArr.length == 0){
            	Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R"));
                return;
            }

			if(isDzh=="Y"){
				
				//是否满足如下条件：同一回购期限+同一方向+同一清算速度+同一交易账户（21位码/6位码）
				//是否满足如下条件：同一标的+同一方向+同一清算速度+同一交易账户（21位码/6位码）
                var msg="";var entrustId = "";
                var finprodFlag = true; var psFlag = true; var settleSpreedFlag = true; var mysideFlag = true;
                /*for(var i=0;i<selArr.length-1;i++){
                    if(finprodFlag&&selArr[i].finprodMarketId!=selArr[i+1].finprodMarketId){finprodFlag=false;msg += "标的信息&"}
                    if(psFlag&&selArr[i].ps!=selArr[i+1].ps){psFlag=false;msg += "交易方向&"}
                    if(settleSpreedFlag&&selArr[i].settleSpreed!=selArr[i+1].settleSpreed){settleSpreedFlag=false;msg += "清算速度&"}
                    if(mysideFlag&&selArr[i].myside!=selArr[i+1].myside){mysideFlag=false;msg += "交易账户&"}
					selArr[i].avaNum = '';
                }*/
				//新增指令券面默认为空
				for(var i=0;i<=selArr.length-1;i++){
					selArr[i].avaNum = '';
				}
                if(!(finprodFlag&&psFlag&&settleSpreedFlag&&mysideFlag)){
                    //“所选指令不满足汇总条件，【标的信息&交易方向&清算速度&交易账户】不同
                    msg = msg.charAt(msg.length-1) == "&"?msg.substr(0,msg.length-1):msg;
                    Joyin.error("所选指令不满足汇总条件["+msg+"]不同");
                    return;
                }else{
                    //进入多组合界面
                    $.each(selArr,function (i,e) {
                        entrustId += e.entrustId;
                    })
                    //var object = {entrustId:entrustId,operation:"CfetsRepoDealQuotedPrice001.new",isDzh:isDzh};
                    //Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/checkEntrust', object, function(result) {
                        //if (result.data){
                            //添加组合分配列表
                            // 将选中的委托信息返还给报价页面
                            //window.parent.CfetsRepoDealQuotedPrice003.reloadPtlAllocation(selArr);
                            window.parent.CfetsQuotedCommon001.reloadPtlAllocation(selArr);
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        //}
                    //});
                }

			}else{
				// 列表中勾选对象1条以上时
            if (selArr && selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            var object = {entrustId:selArr[0].entrustId};
            Joyin.ajaxJson(basePath + 'invest/bankuplink/bond/CfetsBondDealQuotedPrice002/checkEntrust', object, function(result) {
                if (result.data){
                    // 将选中的委托信息返还给报价页面
                    window.parent.CfetsBondDealQuotedPrice002.saveEntrustSelectData(selArr[0]);
                    var index = window.parent.layer.getFrameIndex(window.name);
                    window.parent.layer.close(index);
                }
            });
			}
        }
    }
    
    
    
    $(function () {
    	var finprodId = $('#CfetsBondDealQuotedPrice006_finprodId').val();
    	if (finprodId && finprodId != ''){
    		$('#CfetsBondDealQuotedPrice006_finprodId').attr('disabled', true);
    	}
    	var ps = $('#CfetsBondDealQuotedPrice006_ps').val();
    	if (ps && ps != ''){
    		$('#CfetsBondDealQuotedPrice006_ps').attr('disabled', true);
    	}
    	if(editFlag == "false"){
    		$('#CfetsBondDealQuotedPrice006_btn_confirm').addClass("layui-btn-disabled");
    		$('#CfetsBondDealQuotedPrice006_btn_cancel').addClass("layui-btn-disabled");
    	}
    	
    	/**
    	 * 委托指令放大镜点击事件
    	 */
    	$('#CfetsBondDealQuotedPrice006_entrustOrder_search').click(function(){
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    	/**
    	 * 确认点击事件
    	 */
    	$('#CfetsBondDealQuotedPrice006_btn_confirm').click(function(){
    		confirmAction();
        });
    	
    });
}());