/**
 * 质押回购交易对手白名单维护
 */


$(function() {
    /**
     * 获取查询参数
     */
	var getSearchParams = function() {
        var pflsh = $("#pflsh").val();
        var customerName = $("#customerName").val();
        var startUpdateTime = $('#updateTime').startDate();
        var endUpdateTime = $("#updateTime").endDate();
        return {
        	pflsh: pflsh,
        	customerName: customerName,
        	startUpdateTime: startUpdateTime,
        	endUpdateTime: endUpdateTime
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
     /*   dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/risk/creditrisklimitmanage/repdealcounterwhitelist/RepDealCounterWhiteList002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,seqNo:data.seqNo});
        },*/
        
        cols:[
             {field:'seqNo', visible: false}
            ,{field:'pflsh',sortField:"pflsh", title: 'QuotaImp001.pflsh'}
            ,{field:'customerId', width:120 ,sortField:"customerId",  title: 'QuotaImp001.customerId'}
            ,{field:'customerName', width:180 , sortField:"customerName", title: 'QuotaImp001.customerName'}
            ,{field:'mdate', width:180 , sortField:"mdate", title: 'QuotaImp001.mdate',type:'date'}
            ,{field:'amountName', sortField:"amountName", width:120 , title: 'QuotaImp001.amountName'}
            ,{field:'creditAmt', width:120 , sortField:"creditAmt",title: 'QuotaImp001.creditAmt',type: 'number', digit:4 }
            ,{field:'termStr', width:180 , sortField:"termStr",title: 'QuotaImp001.termStr'}
            ,{field:'updateTime', sortField:"updateTime",title: 'QuotaImp001.updateTime',type:'datetime'}           
            
        /*    ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']} */
        ]
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#QuotaImp001_btn_search').click(function() {
            table.load();
        });
        // 导入按钮点击事件
        $('#QuotaImp001_btn_import').click(function() {
        	var impType = $("#impType").val();
        	if(impType == null || impType == ''){
        		Joyin.alert("请选择导入类型！");
        		return;
        	}
            Joyin.confirm("请确认是否要导入数据？", function() {
            	var options = {};
            	if($("#impType").val() == '01'){
            		options.pageId = "TYQuotaImp001";
            	}else if($("#impType").val() == '02'){
            		options.pageId = "QYQuotaImp001";
            	}else if($("#impType").val() == '03'){
            		options.pageId = "XMQuotaImp001";
            	}else if($("#impType").val() == '04'){
            		options.pageId = "ABSQuotaImp001";
            	}
                Joyin.uploadFile(options,function(fileName) {
                	Joyin.ajax(basePath + 'invest/risk/creditrisklimitmanagement/quotaimp/QuotaImp001/importFile', {fileName: fileName,impType:$("#impType").val()});
      /*              Joyin.ajax(basePath + 'risk/creditrisklimitmanagement/repdealcounterwhitelist/QuotaImp001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        
                    }); */
                },function() {
                    table.reload();
                });
            });
        });
        $('#QuotaImp001_btn_export').click(function() {
            exportExcel();
        });
    });
}());