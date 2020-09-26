(function() {
	
	function getPageParams() {
		var pageId = parent.pageId;
		var param = {};
		if(pageId) {
			var columns = parent[pageId].columns;
			var fields = [];
			//分类总量登记画面专用处理
//			if(pageId === 'ClassifyTotalInfoRegist001'){
//			    var arr = [];
//			    arr=columns[0].concat(columns[1]);
//			    columns = arr;
//			}
		    if(parent[pageId].rowData){
		        var rowArr = JSON.parse(parent[pageId].rowData);
		    }
		    $.each(columns, function(i, col) {
                if((col.field)) {
                    fields.push({title:col.title, data: col.field, type: col.type, fixed: col.digit,currentValue:rowArr[col.field]});
                }
            });
			param.fields = JSON.stringify(fields);
			param.registSeqNo = parent[pageId].registSeqNo;
			param.pageId = pageId;
		}
		return param;
	}

	$(function() {
		var showOldValue = ['01','新建', '03', '删除'].indexOf(parent[parent.pageId].status) == -1;
		var tdWidth = showOldValue ? '220px' : '450px';
		Joyin.ajax('search', getPageParams(), function(result) {
			if(result.data && $.isArray(result.data)) {
				var trs = [];
				$.each(result.data, function(i, d) {
					// 数据状态、报送状态、数据日期 不参加详细画面的比较
					if(['status', 'sendStatus', 'statusDate'].indexOf(d.data) != -1) {
						return;
					}
					if(['数据状态', '报送状态', '数据日期',''].indexOf(d.title) != -1 || d.title==null) {
						return;
					}
					$field = $('<td></td>').addClass('text-right').text(d.title);
					var type = d.type;
					var value = d.currentValue;
					var oldVal = d.oldValue;
					if(type === 'date') {
						if(value) {
							var date = Date.ofDate(parseInt(value));
							value = date.format('yyyy-MM-dd');
						}
						if(oldVal) {
							var date = Date.ofDate(parseInt(oldVal));
							oldVal = date.format('yyyy-MM-dd');
						}
					}
					else if(type === 'datetime') {
						if(value) {
							var date = Date.ofDate(parseInt(value));
							value = date.format('yyyy-MM-dd HH:mm:ss');
						}
						if(oldVal) {
							var date = Date.ofDate(parseInt(oldVal));
							oldVal = date.format('yyyy-MM-dd HH:mm:ss');
						}
					}
					else if(type === 'number') {
						var decimal = d.fixed || 2;
						// 是否为百分比自动，自动乘以100
						if(d.title.indexOf('(%) ') == -1
								&& d.title && d.title.indexOf('%') != -1) {
							if(value) {
								value = value * 100;
							}
							if(oldVal) {
								oldVal = oldVal * 100;
							}
						}
						if(value) {
							value = formatNumber(value, decimal);
						}
						if(oldVal) {
							oldVal = formatNumber(oldVal, decimal);
						}
					}
					$current = $('<td style="max-width:'+ tdWidth + ';overflow:hidden;word-wrap:break-word;">--</td>').text(value).attr('title',value);
					$old = $('<td style="max-width:'+ tdWidth + ';overflow:hidden;word-wrap:break-word;">--</td>');
					$tr = $('<tr></tr>').append($field).append($current);
					if(showOldValue) {
						$tr.append($old);
						if(d.hasOldValue) {
							$old.text(oldVal).attr('title',oldVal);
							if(d.currentValue !== d.oldValue) {
								$tr.addClass('different');
							}
						}
					}
					trs.push($tr);
				});
				if(!showOldValue) {
					$('#classfy_table th:last').remove();
				}
				$('#classfy_table tbody').append(trs);
			}
		});
	});
	
	/**
	 * 金额格式化
	 * @returns
	 */
	function formatNumber(value, decimal) {
	    var num = (value || 0).toString();
	    if(decimal && $.isNumeric(decimal)) {
	        num = parseFloat(value || 0).toFixed(decimal);
	    }
	    var result = '', last = '', index = num.indexOf('.');
	    if(index > 0) {
	        last = num.substring(index);
	        num = num.substring(0, index);
	    }
	    while (num.length > 3) {
	        result = ',' + num.slice(-3) + result;
	        num = num.slice(0, num.length - 3);
	    }
	    if (num) { result = num + result; }
	    return result + last;
	};
}());