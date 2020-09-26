$(function () {
	
	 $('#bookDate').val(sysDate);
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	bookSetId: $.trim($('#bookSetId').val()),//账套代码
        	bookDate: $('#bookDate').val(),//入账日 
            vdate: $('#Date').startDate(),//日期开始            
            mdate: $('#Date').endDate(),//日期结束
            receivablesType: $.trim($('#receivablesType').val()),//应收款项类别
            bdFinprodId: $.trim($('#bdFinprodId').val())//标的资产
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
        checkbox: true,
        //cellMinWidth : 100,
		cols: [
           {field:'bookSetId', width:150,title: '账套代码', sort: true},
           /**modify by wujie on 20190314 start
		   {field:'finprodId', width:150,title: '资产代码', sort: true},
           {field:'finprodName',width:250, title: '资产名称', sort: true},**/
           {field:'receivablesType', width:150,title: '应收款项类别', sort: true},
           /**modify by wujie on 20190314 end**/
           {field:'bdFinprodId',width:150, title: '标的资产代码', sort: true},
           {field:'bdFinprodName',width:150, title: '标的资产名称', sort: true},
           {field:'prinAmt',type:"money",width:150, title: '本金金额', sort: true},
           {field:'intAmt',type:"money",width:150, title: '利息金额', sort: true},
           {field:'vdate',width:100, title: '计息开始日', sort: true},
           {field:'mdate',width:100, title: '计息结束日', sort: true},
           {field:'tradeDate',width:100, title: '还本日', sort: true},
           {field:'basis',width:150, title: '计息基础', sort: true},
           {field:'calcType',width:150, title: '利率类型', sort: true},
           {field:'yield',type:"money",width:150,align : "right",title: '最新利率（%）', sort: true},
		],
	});
	
	var exportExcel = function() {
		if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
	};

	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#AccountsReceivableInfoQuery001_btn_search').click(function() {
        	if($('#bookDate').val()){
        		  table.load();
        	}else{
    			layui.layer.alert("请选择账务日!");
    		}
          
        });
        
     // 绑定按钮事件
    	$('#ValAssetReclassification001_btn_search').click(function() {
    		
    		if($('#assetType').val()&&$('#booksetId').val()){
    			if(!tabTable.table){
        			tabTable.load();
        		}else {
        			tabTable.reload();
        		}
    		}else{
    			layui.layer.alert("请选择账套!");
    		}
    	});
        
        
        //  绑定导出事件
        $('#AccountsReceivableInfoQuery001_btn_export').click(function() {
            exportExcel();
        });

    });
	
});



