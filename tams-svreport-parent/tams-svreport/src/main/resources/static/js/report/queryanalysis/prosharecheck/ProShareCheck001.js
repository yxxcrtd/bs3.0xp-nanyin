$(function() {
	/**
	 * 获取查询参数
	 */
	var getPageParams = function() {
		return {
			//查询日期
			date : $('#date').val(),
			//产品 
			prdCode : $('#prdCode').val() == null ? null : $('#prdCode').val()
					.join(","),
			//产品登记编码
			prdRegistCode : $('#prdRegistCode').val() == null ? null : $(
					'#prdRegistCode').val().join(","),
			checkResult : $('#checkResult').val()
		}
	};
	var table = $('#dataTable').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getPageParams();
			}
		},
		cols : [ {
			title : "查询日期",
			field : 'holdDateStr',
//			type : 'date',
			width : 150
		}, {
			title : "检查结果",
			field : 'checkRes',
			width : 160
		}, {
			title : "产品代码",
			field : 'prodId',
			width : 150
		}, {
			title : "产品名称",
			field : 'prodName',
			width : 200
		}, {
			title : "产品登记编码",
			field : 'prdRegistCode',
			width : 160
		}, {
			title : '币种',
			field : 'ccy',
			type : 'number',
			width : 110
		}, {
			title : '当日持有份额',
			field : 'qty',
			width : 150,
			type : 'number',
			digit : 5
		}, {
			title : "前一日持有份额",
			field : 'lastQty',
			type : 'number',
			digit : 5,
			width : 175
		}, {
			title : "当日明细变动份额",
			field : 'changeQty',
			type : 'number',
			digit : 5,
			width : 175
		} ]
	});

	$(function() {
		$('#date').val(defaultDate);

		$('#SvrProShareCheck001_btn_search').click(function() {
			table.load();
		});

        // 导出按钮点击事件
        $('#SvrProShareCheck001_btn_export').click(function() {
            var selectedItem = table.getAllCheckedRows();
            var paramObj={};
            if(selectedItem.length==0){
                paramObj= getPageParams();
            }
            else{
                paramObj.keyList = JSON.stringify(selectedItem);
            }
            Joyin.exportExcel('export', paramObj);
        });

	});

});