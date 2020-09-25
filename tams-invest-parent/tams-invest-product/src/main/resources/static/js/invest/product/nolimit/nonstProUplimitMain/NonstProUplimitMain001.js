/**
 * 非标比例上限维护
 */
(function() {
	  /**
     * 获取查询参数
     */
	var getSearchParams = function() {
		var param = $.serializeToObject('#searchForm');
		//查询日期
		param.sreachTime = $('#sreachTime').val();
		//系列代码
		param.finPrdFamily = $('#finPrdFamily').val();
		return param;
	};
	/**
     * 查询列表
     */
	var table = $('#dataTable').joyintable({
    	ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
		rowno : true,
		cols : [
			 {field:'prdPlanSerisId', width:200, title: 'NonstProUplimitMain.prdPlanSerisId'},
			 {field:'prdPlanSerisName', width:200, title: 'NonstProUplimitMain.prdPlanSerisName'},
			 {field:'nonStandardLimit', width:200, title: 'NonstProUplimitMain.nonStandardLimit'},
			 {field:'effDate', width:200, title: 'NonstProUplimitMain001.table.effdate',type:'date'},
			 {field: 'operation', width: 200, title: 'SysCommon.operation', type: 'action', buttons: ['edit', 'delt']}
			 ]
	});

	/**
	 * 新建
	 */
	var openNew = function() {
		Joyin.dialog(JoyinUtil.getLabel("NonstProUplimitMain002.new"),
						JoyinUtil.getRealPath("invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain002/init"),
						"98%", "95%", {
							pageModel : PAGE_MODEL_NEW,
							confirmFlag : 'new'
						}, function() {
							table.reload();
						});
	};
	    /**
		 * 绑定修改事件
		 */
	table.bind('click','.edit',function(data, index) {
		if (data.prdPlanSerisId) {
			Joyin.dialog(JoyinUtil.getLabel("非标比例上限维护修改"),
				basePath+ "invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain002/init","85%","85%",
					{pageModel : PAGE_MODEL_EDIT,
			         operateType : 'edit',
					 effDate : data.effDate,
					 prdPlanSerisId : data.prdPlanSerisId,
					 prdPlanSerisName : data.prdPlanSerisName,
					 nonStandardLimit : data.nonStandardLimit
					 }, 
					 	function() {
						 	table.reload();
					 			});
		}
	});
	//删除
	table.bind('click', '.delt', function (data, index) {
		data.operateType = 'delete';
        Joyin.confirm("是否删除该行数据", function () {
            WorkFlow.doDeleteList(basePath + "invest/product/nolimit/nonstProUplimitMain/NonstProUplimitMain002/delete", data, "NonstProUplimitMain001_btn_delete", "NonstProUplimitMain001", function () {
                table.reload();
            })
        })
    });
	/**
	 * 导出
	 */
	var exportExcel = function() {
		Joyin.exportExcel('export', getSearchParams());
	};

	$(function() {

		$('#NonstProUplimitMain001_btn_search').click(function() {
			table.load();
		});
		$('#NonstProUplimitMain001_btn_export').click(function() {
			exportExcel();
		});
		$('#NonstProUplimitMain001_btn_new').click(function() {
			openNew();
		});

	});
}());