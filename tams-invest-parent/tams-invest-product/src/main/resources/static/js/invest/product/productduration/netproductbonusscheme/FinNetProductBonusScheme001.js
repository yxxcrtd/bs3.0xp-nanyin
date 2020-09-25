/**
 * FinNetProductBonusScheme001 净值型分红方案管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
//        var finprodMarketIdTemp = $('#finprodMarketId').val();
//        var finprodMarketId = null;
//        if(finprodMarketIdTemp){
//            finprodMarketId = finprodMarketIdTemp.join(',');
//        }
    	var finprodMarketId = $('#finprodMarketId').val();
        var startDelDate = $('#delDate').startDate();
        var endDelDate = $('#delDate').endDate();
        var startRegDate = $('#regDate').startDate();
        var endRegDate = $('#regDate').endDate();
        var startVluRefDate = $('#vluRefDate').startDate();
        var endVluRefDate = $('#vluRefDate').endDate();
        var dealMode = $('#dealMode').val();
        var sendStatus = $('#sendStatus').val();
        var prodStyle = $('#prodStyle').val();
        return {
            finprodMarketId : finprodMarketId,
            startDelDate : startDelDate,
            endDelDate : endDelDate,
            startRegDate : startRegDate,
            endRegDate : endRegDate,
            startVluRefDate : startVluRefDate,
            endVluRefDate : endVluRefDate,
            dealMode : dealMode,
            sendStatus : sendStatus,
            prodStyle : prodStyle
        }
    };
    /**
     * 定义数据表格
     */
    var tableCols = new Array();


	tableCols = [
        {field:'finprodId',visible :false}
        ,{field:'baseDate',visible :false}
        ,{field:'bonusDate',visible :false}
        ,{field:'finprodName',visible :false}
        ,{field:'bTradeId',visible :false}
        ,{field:'bonusPlanId',title: 'FinNetProductBonusScheme001.bonusPlanId', width:200}
		,{field:'sendStatusStr', title: 'FinNetProductBonusScheme001.sendStatus', width:'100', visible: isSendStatusTableShow === YES,
			render : function(data, type, row, meta) {
				//alert(isSendStatusTableLinkShow+'*'+row.sendStatusStr+'*');
			 	if (isSendStatusTableLinkShow === 'N') {
			 		return row.sendStatusStr;
				}
				return "<a  class='pro-link sendStatusLink' style='cursor: pointer;'>"+row.sendStatusStr+"</ a>";
			},sortField:"sendStatus"
		}
		,{field:'sendRemark',sortField:'sendRemark', title: 'FinNetProductBonusScheme001.sendStatusRemark', width:130, visible: isSendStatusTableShow === YES}
        ,{field:'finprodMarketId',title: 'FinNetProductBonusScheme001.finprodMarketId', width:140}
        ,{field:'prodStyle',title: 'FinNetProductBonusScheme001.prodStyle', width:140}
        ,{field:'finprodAbbr', title: 'FinNetProductBonusScheme001.finprodAbbr', width:150}            
        ,{field:'delDate', title: 'FinNetProductBonusScheme001.delDate',type:'date', width:100}
        ,{field:'regDate', title: 'FinNetProductBonusScheme001.regDate',type:'date', width:100}
        ,{field:'vluRefDate', title: 'FinNetProductBonusScheme001.vluRefDate',type:'date', width:100}
        ,{field:'bonuScalcFlagAbbr',sortField: 'bonuScalcFlag', title: 'FinNetProductBonusScheme001.bonuScalcFlag', width:100}
        ,{field:'unitBonusVlu', title: 'FinNetProductBonusScheme001.unitBonusVlu', width:120}
        ,{field:'totalAmt', title: 'FinNetProductBonusScheme001.totalAmt', width:120}
        ,{field:'actTotalAmt', title: 'FinNetProductBonusScheme001.actTotalAmt', width:120}
        ,{field:'ccyName',sortField: 'CCY', title: 'FinNetProductBonusScheme001.ccy', width:120}
        ,{field:'bonusCashAmt', title: 'FinNetProductBonusScheme001.bonusCashAmt', width:120}
        ,{field:'bonusShareAmt', title: 'FinNetProductBonusScheme001.bonusShareAmt', width:120}
        ,{field:'bonusShareNum', title: 'FinNetProductBonusScheme001.bonusShareNum', width:120}
        /*,{field:'operation',width:200,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}*/
        ,{field:'operation',title: 'FinNetProductBonusScheme001.bonusDetail', type: 'action', width: 80, 
//        	buttons: [{
//            text: '明细',
//            clas: 'details',
//            title: ''}]
            render : function(data, type, row, meta) {
                if (purviewElements.indexOf('_manual')!=-1){
                    return '<a class="btn details layui-btn manual" title="" style="color:white">明细</a>';
                }
            }
        }
    ];
		
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                    bonusPlanId: data.bonusPlanId});
        },
        cols:tableCols
    });
    
//	if (isSendStatusTableShow === 'N') {
//		tableCols[6].visible = false;
//	}
//	if (isSendRemarkTableShow === 'N') {
//		tableCols[7].visible = false;
//	}
	
    /**
     * 绑定分红明细查看事件
     */
    table.bind('click', '.manual', function(data, index) {
        data.pageModel = PAGE_MODEL_REFERENCE;
//        alert("开发中");
//        Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/check', data, function(result) {
//            
//        });
        Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusDetails"),
                basePath + "invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme001/bonusDetailInit",
                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,bTradeId: data.bTradeId},null);
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",dealType:"bonusScheRegister"};
        Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme001/check', 
    		object, 
    		function(result) {        		
	            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	                WorkFlow.doDeleteList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme001/delete', object, 
	                        "FinNetProductBonusScheme001_btn_delete", "FinNetProductBonusScheme002",function() {
	                    table.reload();
	                });
	            });
            }
        );
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 分红金额确认
     */
    var bonusAmtConfirm = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		if (selArr.length != 1){
        			Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
        		}
        		var data = selArr[0];
        		if(data){
        			data.dealType = "bonusAmtConfirm";
        		}
        		Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/check', data, function(result) {
        			Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme003"),
                            JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/init"),
                            "98%", "95%",{pageModel : PAGE_MODEL_NEW, confirmFlag : true, bonusPlanId: data.bonusPlanId},function(){table.reload();}
                    );
                });        		
                
            }else{
            	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        	return;
        }
    }
    
    /**
     * 现金红利下发
     */
    var bonusCash = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		if (selArr.length != 1){
        			Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
        		}
        		var data = selArr[0];
        		if(data){
        			data.dealType = "bonusCash";
        		}
        		Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/check', 
    				data, 
    				function(result) {
                    Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme004"),
                        basePath + "invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/init",
                        "95%", "90%", {pageModel:PAGE_MODEL_NEW,
                            bonusPlanId: data.bonusPlanId},function() {
                            table.reload();
                        });
                	}
        		);
            }else{
            	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        	return;
        }
    }
    
    /**
     * 再投份额确认
     */
    var bonusShareConfirm = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		if (selArr.length != 1){
        			Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
        		}
        		var data = selArr[0];
        		if(data){
        			data.dealType = "bonusShareConfirm";
        		}
        		Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/check', data, function(result) {
                    Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme005"),
                        basePath + "invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme005/init",
                        "95%", "90%", {pageModel:PAGE_MODEL_NEW,
                            bonusPlanId: data.bonusPlanId},function() {
                            table.reload();
                        });
                });
            }else{
            	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        	return;
        }
    }
    
	// 发送状态link页面，各系统酌情使用
	table.bind('click','.sendStatusLink', function(row) {
		console.log(row.finprodId);
       var label='';
       var pagePath='';
       pagePath = "invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme006/init";
       Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
           "95%", "90%", {
               pageModel: PAGE_MODEL_REFERENCE,
               bonusPlanId: row.bonusPlanId
           });
	});


  //画面联动：输入产品代码，联动显示产品名称和产品简称
    var finprodMarketIdPChange = function() {
        var finprodMarketId = $("#finprodMarketId").val();
        if(finprodMarketId){
        	var str = $('#finprodMarketId').select2('data')[0].object.split(',');
            var finprodId = '';
        	if(str != null && str.length==2){
        		finprodId = str[0];
        		$('#dealMode').val(str[1]);
        	}
        	if($('#dealMode').val() == '11'){
    			$('#prodStyle').attr("disabled",false);
        		//交易类型 下拉枚举值联动
            	prodStyleSelectInit(finprodId);
        	}
        	else{
        		$('#prodStyle').empty();

    			$('#prodStyle').attr("disabled",true);
        	}
        }
        

        
    };
    
    //交易类型 下拉枚举值联动初始化
    var prodStyleSelectInit = function(finprodId){
    	
    	$('#prodStyle').empty();
    	Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/getProdStyleList', 
        	{'finprodId':finprodId}, 
        	function(result) {
        		$('#prodStyle').append("<option value=''>请选择...</option>");
        		$(result).each(function (i, n) {
                	$('#prodStyle').append("<option value='"+n.finprodId+"'>"+n.finprodId+"</option>");
                });
        	}
        );
    };
    
    /**
     * 发送销售系统接口
     */
    var sendProductBonusSchemeToSale = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
        	if( selArr != null &&  selArr.length > 0 ){
        		var data = {};
        		data.selectedItem = selArr;
        		data.operateType = 'sendProductBonusScheme';
                Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme001/sendProductBonusSchemeToSale', data,
            		function(result) {
                		var finprodIdStr = "";
                		$.each(selArr, function(idx,v) {
                			finprodIdStr = finprodIdStr + v.finprodId + ",";
                		});
                	}
                );
            }else{
            	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
        	Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        	return;
        }
    }
	    
    /**
     * 画面加载完成
     */
    $(function() {
    	
        //产品款型联动	
        $('#finprodMarketId').change(function() {
            finprodMarketIdPChange();
        });
        // 绑定按钮事件
        $('#FinNetProductBonusScheme001_btn_search').click(function() {
            table.load();
        });
        $('#FinNetProductBonusScheme001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinNetProductBonusScheme001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            if (selArr.length != 1){
    			Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
    		}
            var object = selArr[0];
            object.operateType = "delete";
            object.dealType = "bonusScheRegister";
            Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme001/check', 
        		object, 
        		function(result) {            	
	            	//查询是否做个金额确认，做过确认，则先撤销金额确认
            		Joyin.ajaxJson(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme001/idBonusAmtConfirm', 
                		object, 
                		function(ret) {
            				if(null!=ret.data && ""!=ret.data){
            					object.dealType = "bonusAmtConfirm";
            					object.tradeId = ret.data;
            					Joyin.confirm("方案已经做过金额确认，将先撤销金额确认", 
        		        			function(){
        				                WorkFlow.doDeleteList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme003/doRevoke', 
        				                	object,"FinNetProductBonusScheme003_btn_revoke", "FinNetProductBonusScheme003"
        				                );
        			            	}
        			        	);
            				}else{
            					Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, 
        		                	function(){
        		                    	WorkFlow.doDeleteList(basePath + 'invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme001/delete', 
        		                    		object, 
        		                            "FinNetProductBonusScheme001_btn_delete","FinNetProductBonusScheme002"
        	                            );
        		                	}
        		                );
            				}
            			}
            		);
            	}
            );
        });
        $('#FinNetProductBonusScheme001_btn_bonusScheRegister').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinNetProductBonusScheme002"),
                basePath + "invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#FinNetProductBonusScheme001_btn_bonusAmtConfirm').click(function() {
        	//分红金额确认
        	bonusAmtConfirm();
        });
        $('#FinNetProductBonusScheme001_btn_bonusCash').click(function() {
        	//现金红利下发
        	bonusCash();
        });
        $('#FinNetProductBonusScheme001_btn_bonusShareConfirm').click(function() {
        	//再投份额确认
        	bonusShareConfirm();
        });
        $('#FinNetProductBonusScheme001_btn_sendSale').click(function() {
        	sendProductBonusSchemeToSale();
        });
    });
}());