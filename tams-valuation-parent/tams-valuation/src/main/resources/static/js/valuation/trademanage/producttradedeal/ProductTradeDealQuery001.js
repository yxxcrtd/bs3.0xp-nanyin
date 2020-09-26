$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodId: $.trim($('#finprodId').val()),
            settleDateBegin: $('#Date').startDate(),//确认日开始            
            settleDateEnd: $('#Date').endDate(),//确认日结束
            ccy: $.trim($('#ccy').val()),
            profitType: $.trim($('#profitType').val()),// 产品类型 - 基金类型
            issueType: $.trim($('#issueType').val()),
            parentOrson: $.trim($('#parentOrson').val())
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
         dblclick:function(table, data) {// 绑定双击事件，
                 Joyin.dialog(JoyinUtil.getLabel("TA交易-明细查询"),
                         JoyinUtil.getRealPath("/valuation/trademanage/producttradedeal/ProductTradeDealQuery002/init"),
                         "90%", "90%", {pageModel:PAGE_MODEL_EDIT, finprodId:data.finprodId, settleDate: data.settleDate,
                	 	 finprodAbbr: data.finprodAbbr, profitType: data.profitType, hasChildren: data.hasChildren,
                	 	 pFinprodId :data.pFinprodId},
                         function(){
                             //dataTable.reload();
                         });
         },

//
        tree: false,
        treeId: 'finprodId',
        treeUpId: 'pFinprodId',
        treeShowName: 'finprodAbbr',
        checkbox: true,
		cellMinWidth : 100,
		cols: [
           {field:'finprodId', title: '产品代码', width:120, sort: true},
		   {field:'finprodAbbr', title: '产品简称', width:150, sort: true},
		   {field:'profitTypeCode', title: '产品类型Code', sort: true, visible:false},
		   {field:'profitType', title: '产品类型', width:100, sort: true,sortField:'profitTypeCode'},
           {field:'ccyCode', title: '币种Code',sort: true, visible:false},
           {field:'ccy', title: '币种', width:120, sort: true,sortField:'ccyCode'},
           {field:'settleDate', title: '确认日', width:100, sort: true},
           {field:'netRedemptionShare',type:"money", title: '净申赎份额', width:150, sort: true},
           {field:'netRedemptionAmt',type:"money", title: '净申赎金额', width:150, sort: true},
           {field:'divIntAmt', type:"money", width:150, title: '分红/付息金额', sort: true},
           {field:'reShare', type:"money", width:150, title: '再投份额', sort: true},
           {field:'shareLastDay', type:"money", title: '上一日份额', width:150 ,sort: true},
           {field:'shareRemainderDay', type:"money", width:150, title: '当日剩余份额', sort: true},
           {field:'cpriceDate', title: '净值日', width:100, sort: true},
           {field:'unitCprice', type:"money",  title: '净值日单位净值', digit:4,width:150, sort: true,render: function (data, type, row, meta) {
               if(row.profitTypeCode!='01'){
                   return null;
               }
           }},
           {field:'pFinprodId', title: '母金融产品代码', sort: true, visible:false},
           {field:'hasChildren', title: '是否含有孩子节点', sort: true, visible:false}
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
//		var paramObj = getSearchParams();
//		Joyin.exportExcel('export', paramObj);
	};

	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ProductTradeDealQuery001_btn_search').click(function() {
            table.load();
        });
        
        //  绑定导出事件
        $('#ProductTradeDealQuery001_btn_export').click(function() {
            exportExcel();
        });

    });
	
});



