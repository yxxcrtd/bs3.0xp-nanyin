$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
        	   booksetId: $.trim($('#booksetId').val()),
               happenDateBegin: $("#happenDate").startDate(),
               happenDateEnd: $("#happenDate").endDate(),
        }
    };
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
        cols: [
             {field:'finProdName',  title: '产品全称',sort : true}
            ,{field:'finProdNameAbbr',  title: '产品简称',sort : true}
            ,{field:'finProdType',  title: '产品类型',width:160,sort : true}
            ,{field:'happenDate', title: '所属日期',width:160,sort : true}
            ,{field:'netAmt', type:"money", width:260, digit: 4, title: '净资产(亿元)',sort : true}
        ]
      });

    /**
     * 导出
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getPageParams();
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
      $('#NonLggalFinancialDataSubReport001_btn_search').click(function() {  
    	    table.load();        
      });
      $('#NonLggalFinancialDataSubReport001_btn_export').click(function() {
          exportExcel();
      });    
    });
});
