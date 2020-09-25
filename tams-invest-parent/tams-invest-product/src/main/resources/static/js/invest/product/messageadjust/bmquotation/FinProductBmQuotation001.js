/**
 * 产品业绩基准
 */

$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	 
    	var finprodMarketIdTemp = $('#finprodMarketId').val();
    	var prodStyleTemp = $('#prodStyle').val();
    	var sendStatusStr = $('#sendStatus').val();
        var finprodMarketId= null;
        var prodStyle= null; 
        var sendStatus=null;
        if(finprodMarketIdTemp != null)
        	finprodMarketId=finprodMarketIdTemp.join(",");
        if(prodStyleTemp != null)
            prodStyle =	$('#prodStyle').val().join(",");
        if(sendStatusStr!=null)
        	sendStatus= $('#sendStatus').val();
        return {
        	finprodMarketId: finprodMarketId,
        	
        	sDate:$('#effDate').startDate(),
        	eDate:$('#effDate').endDate(),
        	prodStyle:prodStyle,
        	sendStatus: sendStatus
        }
    };
    /**
     * 定义数据表格
     */
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
            var effDate = Date.ofDate(data.effDate);
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/product/messageadjust/bmquotation/FinProductBmQuotation002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId,baseRuleId:data.baseRuleId,effDate:effDate.format()});
        },
        
        cols:[
             {field:'finprodId', width:150 , title: 'FinProductBmQuotation001.finprodId',visible: false}
            ,{field:'baseRuleId', width:150 , title: 'FinProductBmQuotation001.baseRuleId',visible: false}
            ,{field:'sendStatusStr', title: 'FinProductBmQuotation001.sendStatus', width:'100',visible: isSendStatusTableShow === YES,
                render : function(data, type, row, meta) {
                    if (isSendStatusTableLinkShow === 'N') {
                        return row.sendStatusStr;
                    }
                    return "<a  class='pro-link sendStatusLink' style='cursor: pointer;'>"+row.sendStatusStr+"</ a>";
                },sortField:"sendStatus"
            }
            ,{field:'finprodMarketId', width:150 , title: 'FinProductBmQuotation001.finprodMarketId'}
            ,{field:'finprodAbbr', width:150 , title: 'FinProductBmQuotation001.finprodAbbr'}
            ,{field:'rateTypeStr', width:150 , sortField:"rateType",title: 'FinProductBmQuotation001.rateType'}
            ,{field:'prodStyle', width:150 , sortField:"rateType",title: 'FinProductBmQuotation001.prodStyle'}
            ,{field:'investmentCycle', width:150 ,title: 'FinProductBmQuotation001.investmentCycle'}
            ,{field:'rate', width:120 , sortField:"rate",title: 'FinProductBmQuotation001.rate'}
            ,{field:'lowLimitRate', width:150,title: 'FinProductBmQuotation001.lowLimitRate'}
            ,{field:'floatRate', sortable:false, width:150 ,title: 'FinProductBmQuotation001.floatRate'}           
            ,{field:'resetTypeStr', sortField:"resetType",width:150 , title: 'FinProductBmQuotation001.resetType'}
            ,{field:'effDate', width:130 , sortField:"effDate", title: 'FinProductBmQuotation001.effDate',type:'date'}
            //,{field:'sendRemark',sortField:'sendRemark', title: 'FinProductBmQuotation001.sendStatusRemark', width:130, visible: isSendStatusTableShow === YES}
            //add by zhangJin 20191016 start 修复bug【删除数据提示已被删除】
            ,{field:'baseRuleType',visible: false}
            //add by zhangJin 20191016 end 修复bug【删除数据提示已被删除】
            ,{field:'operation', width:150,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
            ]
    });   
    if (isSendStatusTableShow === 'N') {
    	table.showColumns([
            {field:'sendStatusStr', visible: false}
        ]);
    }
//    if (isSendRemarkTableShow === 'N') {
//    	table.showColumns([
//            {field:'sendRemark', visible: false}
//        ]);
//    }
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.effDate = Date.ofDate(data.effDate).format();
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("FinProductBmQuotation002.edit"),
                    basePath + "invest/product/messageadjust/bmquotation/FinProductBmQuotation002/init",
                    "100%", "95%",data,function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation001/delete', object, 
                        "FinProductBmQuotation001_btn_delete", "FinProductBmQuotation002",function() {
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var param ={};
        var rows = table.getAllCheckedRows();
        if(rows.length > 0) {
            var keyList = [];
            $.each(rows, function(i, row) {
                keyList.push(row.finprodId + '|' + row.baseRuleId);
            });
            param.keys = keyList.join(',');
        }
        else{
            //有勾选优先勾选数据
            param = getSearchParams();
        }
        Joyin.exportExcel("export",param);
    
    };
    
    var finprodMarketIdAction = function(){
    	Joyin.ajax(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation001/queryLayerProdStyleList', 
      			 {finprodMarketId:$('#finprodMarketId').val()?$('#finprodMarketId').val().join(","):null}, function(result) {
      		 $("#prodStyle option").remove();
      		 if(result != null && result.length > 0){
   	           // $("#prodStyle").append("<option></option>");
   	            for(var i in result){
   	            	$("#prodStyle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");
   	            	
   	            }
   	          }
           });
    };
    /**
     * 发送产品业绩基准系统接口
     */
    var sendProductBmQuotation = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
            if( selArr != null &&  selArr.length > 0 ){
                var data = {};
                data.selectedItem = selArr;
                data.operateType = 'sendProductBmQuotation';
                Joyin.ajaxJson(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation001/sendProductBmQuotation', data,
                    function(result) {
                        var baseRuleIdStr = "";
                        $.each(selArr, function(idx,v) {
                        	baseRuleIdStr = baseRuleIdStr + v.baseRuleId + ",";
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
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinProductBmQuotation001_btn_search').click(function() {
            table.load();
            $('#FinProductBmQuotation001_btn_delete').show();
        });
        $('#FinProductBmQuotation001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinProductBmQuotation001_btn_sendSale').click(function() {
            sendProductBmQuotation();
        });
        
        $('#FinProductBmQuotation001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinProductBmQuotation002"),
                        basePath + "invest/product/messageadjust/bmquotation/FinProductBmQuotation002/init",
                        "80%", "85%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#FinProductBmQuotation001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            if (selArr.length > 1) {
                Joyin.error('只能选择一条数据');
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/bmquotation/FinProductBmQuotation001/delete', object, 
                            "FinProductBmQuotation001_btn_delete","FinProductBmQuotation002");
                });
            });
        });
        var bindFlag = false;
        /*$("#finprodMarketId").change(function(){
        	//防止重复执行 必须用mouseleave
        	if(!bindFlag){
        		$('#select2-finprodMarketId-results').mouseleave(finprodMarketIdAction);
        		bindFlag = true;
        	}
        	});
        finprodMarketIdAction();*/
        // 发送状态link页面，各系统酌情使用
        table.bind('click','.sendStatusLink', function(row) {
            console.log(row.finprodId);
           var label='';
           var pagePath='';
           pagePath = "invest/product/messageadjust/bmquotation/FinProductBmQuotation006/init";
           Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
               "95%", "90%", {
                   pageModel: PAGE_MODEL_REFERENCE,
                   finprodId: row.finprodId
               });
        });

    });
}());