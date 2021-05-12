$(function() {
    
    /**
     * 导出表格
     */
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
	 * 获取查询参数
	 */
	var getSearchParams = function() {

		return {
			fundCode : $("#fundCode").val(),
			fundName : $('#fundName').find("option:selected").text(),
			fundType : $('#fundType').val(),
			valueSource : $('#valueSource').val(),
			startDate : $('#valueDate').startDate(),
			endDate : $('#valueDate').endDate()

		}
	};

	
	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'findPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
		
/*		dblclick:function(table , data) {
	          console.log(data);
	          Joyin.dialog(JoyinUtil.getLabel("查看详情"), basePath
	                  + "common/basicdata/marketdata/finproductvaluation/ValFinProductValuation002/init","90%",
	                  "90%", {pageModel:PAGE_MODEL_REFERENCE,datas:JSON.stringify(data)},
	                  function(){
	                      table.reload();
	                      pageModel : PAGE_MODEL_NEW
	                  },
	                  null
	          ); 
		},*/
		
	
		 cellMinWidth : 50,
//		 loadonce : true,
		cols : [ {
			field : 'finprodId',
			width : 160,
			title : '基金代码'
		},{
			field : 'fundName',
			width : 230,
			title : '基金名称'
		}, {
			field : 'fundTypeFmt',
			sortField:'fundType', 
			width : 100,
			title : '基金类型'
		}, 
//		{
//			field : 'valueSourceFmt',
//			sortField:'valueSource', 
//			width : 120,
//			title : '估值来源'
//		}, 
		{
			field : 'valueDate',
			width : 100,
			title : '估值日期'
		}, {
			field : 'closePrice',
			width : 50,
				digit: 3,
			type  : "money",
			title : '收盘价'
		}, {
			field : 'averagePrice',
				digit: 3,
			width : 50,
			type  : "money",
			title : '平均价'
		}, {
			field : 'maxPrice',
				digit: 3,
			width : 50,
			type  : "money",
			title : '最高价'
		}, {
			field : 'minPrice',
				digit: 3,
			width : 50,
			type  : "money",
			title : '最低价'
		}, {
			field : 'buyOnePrice',
				digit: 3,
			width : 50,
			type  : "money",
			title : '买一价'
		}, {
			field : 'sellOnePrice',
				digit: 3,
			width : 50,
			type  : "money",
			title : '卖一价'
		},
		{
			field:'operation',
			title: '操作' ,
			type:'action',
			buttons: ['edit', 'delt'] ,
			width:50
		}]
	});

	/**
	 * 绑定编辑事件
	 */
	table.bind('click', '.edit', function(data, index) {
		data.valueDate = new Date(data.valueDate).format();
		data.pageModel=PAGE_MODEL_EDIT;
		data.operateType='edit';
		Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation002/check', data, function(result) {
			Joyin.confirm("请确认是否要修改？", function(){
				Joyin.dialog(JoyinUtil.getLabel("每日债券行情编辑"),
					basePath + "common/basicdata/marketdata/finproductvaluation/ValFinProductValuation002/init",
					"85%", "85%",data,
					function(){table.reload();});
			});
		});

	});
	/**
	 * 绑定删除事件
	 */
	table.bind('click', '.delt', function(data, index) {
		var object = {
			finprodId:data.finprodId,
			secId:data.secId,
			valueDate:data.valueDate,
			selectedItem: [data],
			operateType:"delete"
		};
		Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation001/check', object, function(result) {
			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
				WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation001/delete', object, "ComFundMarketInfo001_btn_delete", "ValFundMarketInfo002",function(){
					table.reload();
				});
			});
		});
	});
	
	/**
	 * 画面加载完成
	 */
	
	/**
	 * 查询
	 */
	$('#ComFundMarketInfo001_btn_search').click(function() {
		table.load();
		$('#ComFundMarketInfo001_btn_delete').show();
	});
	 /**
     * 导出
     */
    $('#ComFundMarketInfo001_btn_export').click(function() {
        exportExcel();
    });

	/**
	 * 删除选中的数据
	 */
	$('#ComFundMarketInfo001_btn_delete').click(function () {
		if(table!=null && table!=''){
			var selArr = table.getAllCheckedRows();
		}
		if (!selArr || selArr.length == 0) {
			Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
			return;
		}
		var object = {selectedItem: selArr, operateType:"delete"};
		Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation001/check', object, function(result) {
			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
				WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation001/delete', object, "ComFundMarketInfo001_btn_delete", "ValFundMarketInfo002",function () {
					table.reload();
				});
			});
		});
	});

	/**
	 * 新建
	 */
	$('#ComFundMarketInfo001_btn_add').click(function () {
		Joyin.dialog(JoyinUtil.getLabel("每日基金行情新建"), basePath +'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation002/init', '85%', '85%');
	});
	/**
	 * 导入按钮
	 */
	$('#ComFundMarketInfo001_btn_import').click(function() {
		Joyin.uploadFile({pageId:'ValFundMarketInfo001MTM'},function(fileName) {
			Joyin.ajax(basePath + 'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation001/checkImportData', {fileName: fileName}, function(checkResult) {
				if (checkResult) {
					setTimeout( function () {
						if(checkResult.data.errorMsg){
							Joyin.error(checkResult.data.errorMsg);
							return;
						}
						if (checkResult.data.confirmMsg) {
							Joyin.confirm(checkResult.data.confirmMsg, function () {
								var data = {
									tempList: checkResult.data.correctData
								}
								Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation001/importFile', data,
									function (resultObj) {
										if (resultObj.statuCode.toString() === '200') {
											/** modify by yanya on 20210329 修正导入逻辑**/
											//Joyin.alert("文件导入成功！");
											table.reload();
											return;
										}
									});
							}, {}, function () {
								layui.layer.closeAll()
								table.reload();
							});
						}else {
							var data = {
								tempList: checkResult.data.correctData
							}
							Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/finproductvaluation/ValFinProductValuation001/importFile', data,
								function (resultObj) {
									if (resultObj.statuCode.toString() === '200') {
										/** modify by yanya on 20210329 修正导入逻辑**/
										//Joyin.alert("文件导入成功！");
										table.reload();
										return;
									}
								});
						}
					}
					,1000);
				}
			});
		},function() {
			table.reload();
		});
	});
});