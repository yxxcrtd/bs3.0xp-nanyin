(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = {};
    	if ($('#FinMdateAdjustment001_prodId').val()){
    		param.finprodId = $('#FinMdateAdjustment001_prodId').val().join(',');
    	}
    	param.adjustAfterMdateStart = $('#FinMdateAdjustment001_adjustAfterMdate').startDate();
    	param.adjustAfterMdateEnd = $('#FinMdateAdjustment001_adjustAfterMdate').endDate();
    	param.adjustType = $("#FinMdateAdjustment001_adjustType").val();
    	param.sendStatus = $('#sendStatus').val();
        return param;
    };
	/**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
    	ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
    	
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FinMdateAdjustment002"),
                    JoyinUtil.getRealPath("invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/init"),
                    "90%", "90%",data,function(){table.reload();}
            );
        },
        //rowno: true,
        cols:[
             //{field:'statusStr', sortField:'status',title: 'FinMdateAdjustment001.table.status', width:200},//产品代码
             //{field:'num', sortField:'status',title: 'FinMdateAdjustment001.table.num', width:50},//序号
             {field:'finprodMarketId', title: 'FinMdateAdjustment001.table.prodId', width:200}//产品代码
             ,{field:'adjustNum', title: 'FinMdateAdjustment001.table.adjustNum' ,width:90}//调整批次
             ,{field:'sendStatusStr', title: 'FinMdateAdjustment001.sendStatus', width:'100', visible: isSendStatusTableShow === YES, 
                 render : function(data, type, row, meta) {
                     //alert(isSendStatusTableLinkShow+'*'+row.sendStatusStr+'*');
                     if (isSendStatusTableLinkShow === 'N') {
                         return row.sendStatusStr;
                     }
                     return "<a  class='pro-link sendStatusLink' style='cursor: pointer;'>"+row.sendStatusStr+"</ a>";
                 },sortField:"sendStatus"
             }
             ,{field:'sendRemark',sortField:'sendRemark', title: 'FinMdateAdjustment001.sendStatusRemark', width:130, visible: isSendRemarkTableShow === YES}
			,{field:'finprodAbbr', title: 'FinMdateAdjustment001.table.prodAbbr', width:200}//产品简称
			,{field:'adjustTypeStr',sortField:'adjustType', title: 'FinMdateAdjustment001.table.adjustType', width:100}//调整类型
			,{field:'mdateUnadjust', title: 'FinMdateAdjustment001.table.adjustBeforeMdate', type:'date'}//调整前到期日
			,{field:'ldateUnadjust', title: 'FinMdateAdjustment001.table.adjustBeforeLdate', type:'date'}//调整前清盘日
			,{field:'bdate', title: 'FinMdateAdjustment001.table.BeforeEnddate', type:'date'}//提前终止日
			,{field:'rdate', title: 'FinMdateAdjustment001.table.RolloverDate', type:'date'}//展期日
			,{field:'mdate', title: 'FinMdateAdjustment001.table.adjustAfterMdate', visible:false, type:'date'}//调整后到期日
			,{field:'ldate', title: 'FinMdateAdjustment001.table.adjustAfterLdate', type:'date'}//调整后清盘日
			,{field:'remark', title: 'FinMdateAdjustment001.table.remark'}//备注
            ,{field:'finprodId', visible:false}//金融产品代码
            
			,{field:'operation',width:50,title: 'SysCommon.operation', type:'action', buttons: [ 'edit']}
			
        ]
//        ,done: function(table, allData){
//        	$("[data-field='1']").find("span").text("序号");//序号
//        }
    });
    
//    
//    if (isSendStatusTableShow === 'N') {
//        tableCols[1].visible = false;
//    }
//    if (isSendRemarkTableShow === 'N') {
//        tableCols[2].visible = false;
//    }
    
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("FinMdateAdjustment002"),
                JoyinUtil.getRealPath("invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/init"),
                "75%", "75%",{pageModel : PAGE_MODEL_NEW},function(){table.table ? table.reload() : table.load();}
        );
    }

    /**
     * 发送销售系统接口
     */
    var sendMdateAdjustmentSale = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
            if( selArr != null &&  selArr.length > 0 ){
                var data = {};
                data.selectedItem1 = selArr;
                data.operateType = 'sendMdateAdjustmentSale';
                Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment001/sendMdateAdjustmentSale', data,
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
        
    $(function() {
    
    	$("[data-field='1']").find("span").text("序号");//序号
    	
        $('#FinMdateAdjustment001_btn_search').click(function() { 
            table.load();
        });
        $('#FinMdateAdjustment001_btn_export').click(function() {
        	exportExcel();
        });
        $('#FinMdateAdjustment001_btn_add').click(function() {
        	openNew();
        });
        $('#FinMdateAdjustment001_btn_sendSale').click(function() {
            sendMdateAdjustmentSale();
        });
        
        /**
         * 绑定编辑事件
         */
        table.bind('click', '.edit', function(data, index) {
            data.pageModel = PAGE_MODEL_EDIT;
            data.operateType = 'edit';
            Joyin.ajaxJson(basePath + 'invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment001/check', data, function(result) {
                Joyin.dialog(JoyinUtil.getLabel("FinMdateAdjustment002.edit"),
                        basePath + "invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment002/init",
                        "98%", "95%", {finprodId : data.finprodId,adjustNum:data.adjustNum, pageModel : PAGE_MODEL_EDIT},function(){table.reload();});
                
            });
        });
        
        // 发送状态link页面，各系统酌情使用
        table.bind('click','.sendStatusLink', function(row) {
            console.log(row.finprodId);
           var label='';
           var pagePath='';
           pagePath = "invest/product/messageadjust/finmdateadjustment/FinMdateAdjustment006/init";
           Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
               "95%", "90%", {
                   pageModel: PAGE_MODEL_REFERENCE,
                   finprodId: row.finprodId
               });
        });

	});
}());