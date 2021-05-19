/**
 * 产品业绩基准
 */

$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodId: $('#finprodId').val(),
        	scheduletype: $('#scheduletype').val(),
        	workflowId:$('#workflowId').val(),
        	sendStatus:$('#sendStatus').val(),
        }
    };
    var isCycle = $("#isCycle").val();
    var dealMode = $("#dealMode").val();
    var tableCols = new Array();
    tableCols = [
        {field:'cashId', visible: false}
        ,{field:'updateFlag', visible: false}
        ,{field:'openDateStr', title: 'FinOpenPlanMaintenance001.openDateStr', width:300,edit:true,type:'date',sortable: false}
        ,{field:'openDateEnd',title: 'FinOpenPlanMaintenance001.openDateEnd', width:300, edit:true,type:'date',sortable: false}
        ,{field:'openVdate',title: 'FinOpenPlanMaintenance001.openVdate', width:300, edit:true,type:'date',sortable: false
            /*editOption:{type:'date', multiple: false, searchable: false, parentIds: 'openVdate', showField:'id',
                url: JoyinUtil.getRealPath("/invest/product/messageadjust/maintenance/FinOpenPlanMaintenance001/getMdate")},*/
        }
        ,{field:'openNdate',title: 'FinOpenPlanMaintenance001.Ndate', width:300, edit:true,type:'date', visible : true,sortable: false}
        ,{field:'sendStatusStr', title: 'FinOpenPlanMaintenance001.sendStatus', width:'100',visible: isSendStatusTableShow === YES,
            render : function(data, type, row, meta) {
                //alert(isSendStatusTableLinkShow+'*'+row.sendStatusStr+'*');
                if (isSendStatusTableLinkShow === 'N') {
                    return row.sendStatusStr;
                }
                if (!row.sendStatusStr) {
                    row.sendStatusStr='';
                }
                return "<a  class='pro-link sendStatusLink' style='cursor: pointer;'>"+row.sendStatusStr+"</ a>";
            },sortField:"sendStatus"
        }
        ,{field:'sendRemark',sortField:'sendRemark', title: 'FinOpenPlanMaintenance001.sendRemark', width:130,visible: isSendStatusTableShow === YES}
        ,{field:'openDateStrUn',visible: false}
        ,{field:'openDateEndUn',visible: false}
        ,{field:'openVdateUn', visible: false}
        ,{field:'openMdateUn', visible: false}
        ,{field:'openType', visible: false}
        ,{field:'branch', visible: false}
        ,{field:'openStatus', visible: false}
        ,{field:'operation',width:80,title: 'SysCommon.operation', type:'action', buttons: [ 'delt']}
    ]
//    if (isSendStatusTableShow === 'N') {
//        tableCols[6].visible = false;
//    }
//    if (isSendRemarkTableShow === 'N') {
//        tableCols[7].visible = false;
//    }
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
    	page : false,
    	ajax: {
    		url : JoyinUtil.getRealPath('invest/product/messageadjust/maintenance/FinOpenPlanMaintenance001/findPage'),
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        beforeEditCell : function(preVal, field,data){
            //OPEX-792 产品端--开放计划调整，关于开放开始日、开放结束日、开放确认日与平台日的日期校验，需求优化 guyunfei 20200113 start

            //开放确认日 <= 平台日，不可编辑
            if(data.openVdateStr <= SESSION_SYS_DATE){
                return false;
            }

            //开放起始日
            if(field == "openDateStr"){
                if(data.openDateStrStr < SESSION_SYS_DATE
                    && SESSION_SYS_DATE <= data.openDateEndStr
                    && SESSION_SYS_DATE <= data.openVdateStr){
                    return false;
                }
                if(data.openDateEndStr < SESSION_SYS_DATE
                    && SESSION_SYS_DATE <= data.openVdateStr){
                    return false;
                }
            }
            //开放结束日
            if(field == "openDateEnd"){
                if(data.openDateEndStr < SESSION_SYS_DATE
                    && SESSION_SYS_DATE <= data.openVdateStr){
                    return false;
                }
            }
            return true;
        	/*if(data.updateFlag =="02"){
        		return false;
        	}
           	return true;*/
            //OPEX-792 产品端--开放计划调整，关于开放开始日、开放结束日、开放确认日与平台日的日期校验，需求优化 guyunfei 20200113 end
           	},
        addbtn : true,
        addclick : function(rowData){
        	if(!$('#finprodMarketId').val()){
        		msg = JoyinUtil.getMessage('FIN_PROD_E_0090',
        		        JoyinUtil.getLabel('FinOpenPlanMaintenance001.finprodId'));
        		Joyin.warn(msg);
        	}
        },
        loadonce:true,
        searchOnInit : true,
        rowno: true,
        cols:tableCols
    });    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	table.removeRow(data);
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    var init = function(){
    	//隐藏查询和筛选按钮
    	$('#FinOpenPlanMaintenance001_btn_search').attr("style","display:none;");
    	$('#FinOpenPlanMaintenance001_btn_filter').attr("style","display:none;");
    	var workflowId = $("#workflowId").val();
    	if(workflowId != null && workflowId != ''){
    		$('#FinOpenPlanMaintenance001_btn_delete').attr("style","disabled:true;");
    		$('#FinOpenPlanMaintenance001_btn_export').attr("style","display:none;");
    	}
    };
    /**
     * 发送销售系统接口
     */
    var sendOpenPlanSale = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
            if( selArr != null &&  selArr.length > 0 ){
                var data = {};
                data.selectedItem = selArr;
                data.operateType = 'sendOpenPlanSale';
                Joyin.ajaxJson(basePath + 'invest/product/messageadjust/maintenance/FinOpenPlanMaintenance001/sendOpenPlanSale', data,
                    function(result) {
                        var cashIdStr = "";
                        $.each(selArr, function(idx,v) {
                            cashIdStr = cashIdStr + v.cashId + ",";
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
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
    	Joyin.dialog(JoyinUtil.getLabel("FinOpenPlanMaintenance001.newOpenPlan"), basePath
                + "invest/product/messageadjust/maintenance/FinOpenPlanMaintenance002/init",
                "98%", "95%", {pageModel : PAGE_MODEL_NEW},function(){table.reload();});
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 产品代码联动
        $('#finprodMarketId').change(function(){
        	var str = $('#finprodMarketId').select2('data')[0].object.split(',');
        	if(str != null && str.length== 8 ){
        		$("#finprodId").val(str[0]);
        		$("#isCycle").val(str[1]);
        		$("#dealMode").val(str[2]);
        		$("#cashId").val(str[3]);
        		PROFIT_TYPE = str[4];
        		vdate = str[5];
        		OPEN_TYPE = str[6]
        	}
        	if(OPEN_TYPE === RANSOM_ISSUE_MODE_03){
        	    if(PROFIT_TYPE === PROFIT_TYPE_01){
        	        table.showColumns([{field:'openNdate', visible: true}]);
        	    } else {
        	        table.showColumns([{field:'openNdate', visible: false}]);
        	    }
        	} 
            $('#FinOpenPlanMaintenance001_btn_delete').show();
        });
        //
        $('#scheduletype').change(function(){
            if(PROFIT_TYPE === PROFIT_TYPE_01){
                table.showColumns([{field:'openNdate', visible: true}]);
            } else {
                table.showColumns([{field:'openNdate', visible: false}]);
            }
        });
      //提交
        $('#FinOpenPlanMaintenance001_btn_submit').click(function(){
        	saveOrSubmit('submit');
        });
        $('#FinOpenPlanMaintenance001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinOpenPlanMaintenance001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            $.each(selArr,function(index,obj){
            	if(obj.updateFlag == '02'){
            		Joyin.error("起息日为："+Date.ofDate(obj.openVdate).format()+"的数据不允许删除");
            	}
            	else{
            		table.removeRow(obj);
            	}
            })
            
            /*var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/product/messageadjust/maintenance/FinOpenPlanMaintenance001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/product/messageadjust/maintenance/FinOpenPlanMaintenance001/delete', object, 
                            "FinOpenPlanMaintenance001_btn_delete","FinOpenPlanMaintenance001");
                });
            });*/
        });
        $("#sendStatus").change(function () {
            table.reload();
        })
        
        $("#finprodMarketId").change(function () {
        	 $('#FinOpenPlanMaintenance001_btn_submit').attr('style',"display:display");
        	 $('#FinOpenPlanMaintenance001_btn_submit_disabled').attr('style',"display:none");
        })
        
        $('#FinOpenPlanMaintenance001_btn_sendSale').click(function() {
            sendOpenPlanSale();
        });
        
        // 发送状态link页面，各系统酌情使用
        table.bind('click','.sendStatusLink', function(row) {
            console.log(row.finprodId);
           var label='';
           var pagePath='';
           pagePath = "invest/product/messageadjust/maintenance/FinOpenPlanMaintenance006/init";
           Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
               "95%", "90%", {
                   pageModel: PAGE_MODEL_REFERENCE,
                   cashId: row.cashId,
                   openType: row.openType,
                   openDateEnd: row.openDateEnd
               });
        });
        
    });
    function saveOrSubmit(operateType) {
    	var selArr = table.getAllRows();
    	if (selArr && selArr.length == 0) {
    	    Joyin.error('开放计划不能为空！');
            return;
        }
    	var object = {selectedDto: selArr, operateType:operateType};
    	pageModel= PAGE_MODEL_EDIT;
    	object.pageModel = PAGE_MODEL_EDIT;
    	object.finprodId = $("#finprodId").val();
    	object.cashId = $("#cashId").val();
    	object.isCycle = $("#isCycle").val();
    	object.dealMode = $("#dealMode").val();
    	object.profitType = PROFIT_TYPE;
    	object.vdate = vdate;
        object.openType = $("#scheduletype").val();
    	$.each(object.selectedDto, function(i, v) {  
    	    if(v.cashId == null || v.cashId == ''){
    	    	v.cashId = object.cashId;
    	    } 
    	    
    	    v.updateFlagStr = v.updateFlag;
    	});  
        Joyin.ajaxJson(basePath + 'invest/product/messageadjust/maintenance/FinOpenPlanMaintenance001/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                 if(operateType === 'submit') {
                    WorkFlow.doSubmitList(basePath + 'invest/product/messageadjust/maintenance/FinOpenPlanMaintenance001/save', object, "FinOpenPlanMaintenance001_btn_submit");
                }
            });
        });
    };
    
    $("#FinOpenPlanMaintenance001_btn_add").click(function(){
    	showDialog();
    });
}());
