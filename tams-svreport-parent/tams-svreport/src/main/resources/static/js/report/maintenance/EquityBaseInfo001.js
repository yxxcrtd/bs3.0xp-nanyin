(function() {
	var getPageParams = function() {
	    var status=$('#status').val();
	    if(status){
	        status=status.join();
        }
		return {
			// 登记银行代码
			bankCode : $('#bankCode').val()==null?null:$('#bankCode').val().join(","),
			// 产品名称
			prdName : $('#prdName').val(),
			// 数据状态
			status : status,
			// 识别标志
			indentifyMark : $('#indentifyMark').val(),
			orgCode: $('#orgCode').val()==null?null:$('#orgCode').val().join(","),
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
        cols:[
            { title: "数据状态", field: 'status',width:100},
            { title: "登记银行代码", field: 'bankCode',width:150},
            { title: "产品代码", field: 'prodId',width:170},
            { title: "产品名称", field: 'prdName',width:150},
            { title: "机构代码",field:'orgCode',width:150},
            { title: "识别标识", field: 'indentifyMark',width:150 },
            { title: "币种", field:'ccy',width:140 },
            { fielda: 'ccyCode',visible:false },
            { title: "金额", field: 'amount',width:200,type:'number' },
            { title: "总折算人民币金额（元）", field: 'converCnyAmount',width:200,type:'number' },
            { title: "可用数量", field: 'availableQuantity',width:160,type:'number', digit:5 },
            { title: "冻结数量", field: 'frozenQuantity',width:160,type:'number', digit:5 },
            { title: "质押数量", field: 'pledgedQuantity',width:160,type:'number', digit:5 },
            { title: "初值日期", field: 'initValDate', width:100, type: 'date'},
            { title: "初值登记日期", field: 'initValRegistDate', width:130, type: 'date'}
        ]
    });
	
    $(function() {
        // 查询按钮点击事件
        $('#EquityBaseInfo001_btn_search').click(function() {
        	if($('#searchForm').joyinvalid()) {
        		table.load();
        	}
        });
        // 导入按钮点击事件
        $('#EquityBaseInfo001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'),function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax('checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax('importFile', {fileName: fileName});
                    },function() {
                        $('iframe').contents().find("#loading").hide();
                    });
                },function() {
                    table.load();
                });
            });
        });
        // 导出按钮点击事件
        $('#EquityBaseInfo001_btn_export').click(function() {
        	var param = getPageParams();
        	var rows = table.getAllCheckedRows();
    		if(rows.length > 0) {
    			var keyList = [];
        		$.each(rows, function(i, row) {
        			keyList.push(row.bankCode + '|' + row.indentifyMark);
        		});
        		param.keys = keyList.join(',');
    		}
        	Joyin.exportExcel("export", param);
        });
        // 删除按钮点击事件
        $('#EquityBaseInfo001_btn_delete').click(function(){
        	var rows = table.getAllCheckedRows();
    		if(rows.length === 0) {
    			return Joyin.error("请至少选择一条记录");
    		}
    		for(var i=0;i<rows.length;i++){
                if(rows[i].status !="有效"){
                    return Joyin.error("只能删除有效数据，请重新选择");
                }
            }
    		var keyList = [];
    		$.each(rows, function(i, row) {
    			keyList.push(row.bankCode + '|' + row.prodId + '|' + row.indentifyMark + '|' + row.ccyCode);
    		});
    		Joyin.confirm("请确认是否要删除选中记录？",function() {
    			Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
    			    $('#EquityBaseInfo001_btn_search').trigger('click');
    			});
    		});
        });
    }); 
}());