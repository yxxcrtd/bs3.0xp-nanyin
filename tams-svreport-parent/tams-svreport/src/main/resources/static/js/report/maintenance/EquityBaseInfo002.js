(function() {
	var getPageParams = function() {
		return {
			// 登记银行代码
			bankCode : $('#bankCode').val()==null?null:$('#bankCode').val().join(","),
			// 产品名称
			prdName : $('#prdName').val(),
			// 数据状态
			status : $('#status').val()==null?null:$('#status').val().join(","),
			// 识别标志
			indentifyMark : $('#indentifyMark').val()
		};
	};
	var table = $('#equityBaseInfo').joyintable({
    	ajax: {
    		url: 'searchPage',
    		type: 'POST',
    		data: function() {
    			return getPageParams();
    		}
    	},
    	rowno: true, // 显示行号
    	searchOnInit: true, //初始化的时候就请求后台数据
        scrollX:true,
        cols: [
            { title: "数据状态", field: 'status'},
            { title: "登记银行代码", field: 'bankCode' },
            { title: "产品代码", field: 'prdCode',width:100 },
            { title: "产品名称", field: 'prdName' },
            { title: "产品登记编码", field: 'prdRegistCode' },
            { title: "识别标识", field: 'indentifyMark' },
            { title: "币种", field: 'ccy' },
            { title: "可用数量", field: 'availableQuantity' },
            { title: "冻结数量", field: 'frozenQuantity' },
            { title: "质押数量", field: 'pledgedQuantity' },
            { title: "初值日期", field: 'initValDate', render: function(value){
            	var date = Date.ofDate(value);
            	return date.format();
            } },
            { title: "初值登记日期", field: 'initValRegistDate', render: function(value){
            	var date = Date.ofDate(value);
            	return date.format();
            } }
        ]
    });
	
	/**
	 * 删除数据
	 * @returns
	 */
	function removeSelectedData() {
		var rows = table.getAllCheckedRows();
		if(rows.length === 0) {
			return Joyin.Error("请至少选择一条记录");
		}
		var keyList = [];
		$.each(rows, function(i, row) {
			keyList.push(row.bankCode + '|' + row.indentifyMark);
		});
		Joyin.confirm("请确认是否要删除选中记录？",function() {
			Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
				table.load();
			});
		});
	}
	
    $(function() {
        // 查询按钮点击事件
        $('#btnSearch').click(function() {
        	if($('#searchForm').joyinvalid()) {
        		table.load();
        	}
        });
        // 导入按钮点击事件
        $('#btnImport').click(function() {
        	console.dir(table.getAllCheckedRows());
        });
        // 导出按钮点击事件
        $('#btnExport').click(function() {
        	Joyin.exportExcel("export",getPageParams());
        });
        // 删除按钮点击事件
        $('#btnDelete').click(removeSelectedData);
    }); 
}());