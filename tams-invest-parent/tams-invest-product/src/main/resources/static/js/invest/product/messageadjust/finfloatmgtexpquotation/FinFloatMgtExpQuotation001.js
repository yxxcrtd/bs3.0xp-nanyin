/**
 * 信息调整管理_浮动管理费报价查询
 */
(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
    	var finprodId;
    	if($("#FinFloatMgtExpQuotation001_finprodId").val() == null || $("#FinFloatMgtExpQuotation001_finprodId").val() == ''){
    		finprodId = $("#FinFloatMgtExpQuotation001_finprodId").val();
    	}
    	else{
    		finprodId = $("#FinFloatMgtExpQuotation001_finprodId").val().join(',');
    	} 	
    	var prodStyle = null;
    	if($("#prodStyle").val()){
    		prodStyle = $("#prodStyle").val().join(',');
    	}
    	var sendStatusStr = $('#sendStatus').val();
    	
    	var sendStatus=null;
    	
    	if(sendStatusStr!=null)
        	sendStatus= $('#sendStatus').val();
    	
        return {
            finprodId: finprodId,
            prodStyle: prodStyle,
            effDateStart: $('#FinFloatMgtExpQuotation001_effDate').startDate(),
            effDateEnd: $('#FinFloatMgtExpQuotation001_effDate').endDate(),
            sendStatus:sendStatus
        }
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FinFloatMgtExpQuotation002.look"),
                    JoyinUtil.getRealPath("invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols: [
            {field: 'finprodMarketId', title: 'FinFloatMgtExpQuotation001.finprodId', width: 200}
            , {field: 'finprodAbbr', title: 'FinFloatMgtExpQuotation001.finprodAbbr', width: 200}
            , {field: 'prodStyle', title: 'FinFloatMgtExpQuotation002.prodStyle',width:200}
            , {field: 'investmentCycle', title: 'FinFloatMgtExpQuotation002.investmentCycle',width:200}
            , {field: 'feeName', title: 'FinFloatMgtExpQuotation001.feeName',width:150,visible:false}// add by hujinjin
            ,{field:'sendStatusStr', title: 'FinFloatMgtExpQuotation001.sendStatus',visible: isSendStatusTableShow === YES,
                render : function(data, type, row, meta) {
                    //alert(isSendStatusTableLinkShow+'*'+row.sendStatusStr+'*');
                    if (isSendStatusTableLinkShow === 'N') {
                        return row.sendStatusStr;
                    }
                    return "<a  class='pro-link sendStatusLink' style='cursor: pointer;'>"+row.sendStatusStr+"</ a>";
                },
            	width:200,sortable:false
            }
            ,{field:'sendRemark', title: 'FinMdateAdjustment001.sendStatusRemark',sortable:false,width:200,visible: isSendStatusTableShow === YES}
            , {field: 'lev', title: 'FinFloatMgtExpQuotation001.lev',width:150}//业绩基准比较层级 add by hujinjin
            , {field:'baseRuleStr', width: 200, title:'FinFloatMgtExpQuotation002.baseRule', sortField:'BASE_RULE'}//业绩基准规则
            //, {field: 'intRate', title: 'FinFloatMgtExpQuotation001.intRate',render:function(){ return '业绩基准'}, visible:false}
            , {field: 'coefficient', width: 200, title: 'FinFloatMgtExpQuotation001.coefficient'}//调整系数
            , {field: 'spreadRate', width: 200, title: 'FinFloatMgtExpQuotation001.spreadRate'}//利差(%)
            , {field: 'chargeRatio', title: 'FinFloatMgtExpQuotation001.chargeRatio',width:180}//银行收取比例%
//            , {field: 'relaeseDate', title: 'FinFloatMgtExpQuotation001.relaeseDate', type:'date'}
            , {field: 'effDate', width: 200, title: 'FinFloatMgtExpQuotation001.effDate', type:'date'}
            , {field: 'effDateStr', visible:false}
            , {field: 'finprodId', visible:false}
            , {field: 'cashId', visible:false}
            //update by dgf 需求变动不需要删除了 20200116
            , {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']}//编辑
            // , {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ]
    });
    
//    if (isSendStatusTableShow === 'N') {
//        tableCols[1].visible = false;
//    }
//    if (isSendRemarkTableShow === 'N') {
//        tableCols[2].visible = false;
//    }
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'BEFORE_EDIT';
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("FinFloatMgtExpQuotation002.edit"),
                    basePath + "invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/init",
                    "98%", "95%", data,function(){table.reload();});
            
        });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	//发起流程
//    	var object = {selectedItem: [data], operateType:"delete", finprodMarketId : data.finprodMarketId, effDate : data.effDate};
    	var object = {selectedItem1: [data], cashId: data.cashId, finprodId:data.finprodId ,operateType:"delete", lev : data.lev, effDate : data.effDateStr};
    	data.operateType = 'delete';
    	Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/check', object, function(result) {
	        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	         	WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/delete', object, "FinFloatMgtExpQuotation001_btn_delete", "FinFloatMgtExpQuotation002");
	        });
    	})
//    	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//        	WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/delete', object, "FinFloatMgtExpQuotation001_btn_delete", "FinFloatMgtExpQuotation002");
//        });
    });
    
    
    //导出
    var exportExcel = function () {
//        Joyin.exportExcel('export', getSearchParams());
        var param ={};
        var rows = table.getAllCheckedRows();
        if(rows.length > 0) {
            var keyList = [];
            $.each(rows, function(i, row) {
                keyList.push(row.finprodId + '|' + row.effDateStr + '|' + row.lev);
            });
            param.keys = keyList.join(',');
        }
        else{
            //有勾选优先勾选数据
            param = getSearchParams();
        }
        Joyin.exportExcel("export",param);
    };

    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
    	Joyin.dialog(JoyinUtil.getLabel("FinFloatMgtExpQuotation002"), basePath
                + "invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation002/init",
                "98%", "95%", {pageModel : PAGE_MODEL_NEW},function(){table.reload();});
    }

    /**
     * 发送销售系统接口
     */
    var sendFloatMgtExpQuotationSale = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
            if( selArr != null &&  selArr.length > 0 ){
                var data = {};
                data.selectedItem1 = selArr;
                data.operateType = 'sendFloatMgtExpQuotationSale';
                Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/sendFloatMgtExpQuotationSale', data,
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
        // 绑定按钮事件
    	//查询
    	$('#FinFloatMgtExpQuotation001_btn_search').click(function(){
    		table.load();
    	});
    	//新建
        $('#FinFloatMgtExpQuotation001_btn_add').click(function(){
        	showDialog();
        });
        //发送
        $('#FinFloatMgtExpQuotation001_btn_sendSale').click(function() {
            sendFloatMgtExpQuotationSale();
        });
        //导出
        $('#FinFloatMgtExpQuotation001_btn_export').click(function() {
            exportExcel();
        });
        //批量删除
        $('#FinFloatMgtExpQuotation001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem1: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/check', object, function(result) {														
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                	WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/delete', object, "FinFloatMgtExpQuotation001_btn_delete", "FinFloatMgtExpQuotation002");
                });
            });
//            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
//                WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/delete', object, "FinFloatMgtExpQuotation001_btn_delete", "FinFloatMgtExpQuotation002");
//            });
        });
        
        
        /*var bindFlag = false;
        $("#FinFloatMgtExpQuotation001_finprodId").change(function(){
        	//防止重复执行 必须用mouseleave
        	if(!bindFlag){
        		$('#select2-FinFloatMgtExpQuotation001_finprodId-results').mouseleave(finprodMarketIdAction);
        		bindFlag = true;
        	}
        	});
        finprodMarketIdAction();*/
    });
    
    var finprodMarketIdAction = function(){
    	Joyin.ajax(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/queryLayerProdStyleList', 
    			{finprodMarketId:$('#FinFloatMgtExpQuotation001_finprodId').val()?$('#FinFloatMgtExpQuotation001_finprodId').val().join(","):null}, function(result) {
      		 $("#prodStyle option").remove();
      		 if(result != null && result.length > 0){
      			 //add by  chenxiaoqiu 20191202 
      			 if($('#FinFloatMgtExpQuotation001_finprodId').val()!= null && $('#FinFloatMgtExpQuotation001_finprodId').val()!=''){
      			 // $("#prodStyle").append("<option></option>");
      			 for(var i in result){
      				 $("#prodStyle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");
      			 }
      			 }
      		 }
    	});
    };
    
 // 发送状态link页面，各系统酌情使用
    table.bind('click','.sendStatusLink', function(row) {
        console.log(row.finprodId);
       var label='';
       var pagePath='';
       pagePath = "invest/FinFloatMgtExpQuotation006/init";
       Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
           "95%", "90%", {
               pageModel: PAGE_MODEL_REFERENCE,
               finprodId: row.finprodId
           });
    });
    
    
}());