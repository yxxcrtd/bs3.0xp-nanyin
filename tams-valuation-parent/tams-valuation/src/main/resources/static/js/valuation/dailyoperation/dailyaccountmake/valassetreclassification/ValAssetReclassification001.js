$(function() {

	var newInvAim;


	var assetType;
	var booksetId;
	var finprodId;
	var checkfalg = true;


	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		assetType = $.trim($('#assetType').val());
		booksetId = $.trim($('#booksetId').val());
		finprodId = $.trim($('#finprodId').val());
		return {
			assetType: $.trim($('#assetType').val()),
			booksetId: $.trim($('#booksetId').val()),
			finprodId: $.trim($('#finprodId').val()),
			workflowId:workflowId,
			assetDetailType: $.trim($('#assetDetailType').val())
		}
	};


	var tableCols = [
		{field:'booksetId',title: '所属账套'}
		,{field:'booksetDate', type:'date', title: '重分类日期'}
		,{field:'finprodId', title: '资产代码'}
		,{field:'finprodName',  title: '资产名称'}
		,{field:'invAimFmt', sortField:"invAim",  title: '原会计分类'}
		,{field:'bokDetailId',  title: '账务明细代码',visible:false}
		,{field:'cyy',  title: '币种',visible:false}
		,{field:'newInvAim', width:180, title: '新会计分类', visible:false}];

	if(workflowId==null || workflowId.length==0){
		tableCols.push({field:'newInvAimName', width:180, title: '新会计分类',sortField:'newInvAim', edit:true,
			editOption:{type:'select',result: INVAIM,initValue:"AC"}});
	}else{
		checkfalg = false;
		tableCols.push({field:'newInvAimName', width:180, sortField:'newInvAim',title: '新会计分类'});
	}

	/**
	 * 定义数据表格
	 */
	var tabTable=$("#dataTable").joyintable({
		ajax: {
			url: basePath + 'valuation/dailyoperation/dailyaccountmake/valassetreclassification/ValAssetReclassification001/findPage',
			type: 'POST',
			data: function() {
				return getSearchParams();
			}
		},
		editCell: function(value, data, field, cell, column) {
			if (field != 'newInvAimName') {
				return;
			}
			//为新会计分类赋值
			data.newInvAim=getVal(null,value,INVAIM);
			newInvAim=data.newInvAim;
		},
		page:false,
		checkbox: checkfalg,
		cellMinWidth : 100,
		loadonce:true,
		cols:tableCols
	});


	//根据json中的value获取key
	function getVal(key,value,json){
		var results = $.parseJSON(json);
		//为新会计分类Code赋值
		for (var p in results) {
			if(key&&results[p].id==key){
				return results[p].text;
			}
			if(value&&results[p].text==value){
				return results[p].id;
			}
		}
	}

	//引用到所选列按钮
	var doButton = function(type){
		var rows = new Array();
		var allRows = new Array();
		//全选
		if(type==1){
			rows = tabTable.getAllRows();
			//选择行
		}else if(type==2){
			var all = tabTable.getAllRows();
			for (var int = 0; int < all.length; int++) {
				if(all[int].LAY_CHECKED){
					rows.push(all[int]);
				}
			}
			//选择空行
		}else{
			var all = tabTable.getAllRows();
			for (var i = 0; i < all.length; i++) {
				if(!all[i].newInvAimName){
					rows.push(all[i]);
				}
			}
		}
		//将数据应用到所选的地方
		for (var i = 0; i < rows.length; i++) {
			if(newInvAim){
				rows[i].newInvAimNameVal=newInvAim;
				rows[i].newInvAimName=getVal(newInvAim,null,INVAIM);
			}
		}

		//页面刷新.否则无法展示
		var allrows = tabTable.getAllRows();
		tabTable.reload({data : allrows});
		tabTable.events();
	}


	/**
	 * 保存或提交
	 * @param operateType 操作类型（save，submit）
	 * @returns
	 */
	function saveOrSubmit(operateType,rows) {
		var object = $.serializeToObject('#searchForm');

		if(rows.length<=0){
			layui.layer.alert("无可提交数据!");
			return;
		}

		object.pageModel = pageModel;
		object.valAssetReclassification001DtoList=rows;
		object.assetType=assetType;
		object.booksetId=booksetId;
		object.finprodId=finprodId;

		//启动流程提交
		object.operateType = 'submit';

		Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/valassetreclassification/ValAssetReclassification001/check', object, function(result) {
			WorkFlow.doSubmitList(
				basePath + 'valuation/dailyoperation/dailyaccountmake/valassetreclassification/ValAssetReclassification001/doSave',
				object,
				"ValAssetReclassification001_btn_submit",
				null,
				function (data) {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				});
		});
	}


	var showDetail = function(){

		var rows = tabTable.getAllCheckedRows();
		//检查是否有未选择的会计分类
		if(rows.length>0){
			for (var p in rows) {

				if(!rows[p].newInvAimNameVal){
					rows[p].newInvAimNameVal = rows[p].newInvAimName
				}

				if(!rows[p].newInvAimNameVal){
					layui.layer.alert("请选择新会计分类!");
					return ;
				}else{
					//为数组赋值
					rows[p].newInvAim=rows[p].newInvAimNameVal;
				}
			}
		}else{
			layui.layer.alert("请选择要提交的数据!");
			return ;
		}

		var rowList = new Array();
		//将原会计分类和新会计分类相同的数据过滤
		for (var p in rows) {
			if(rows[p].newInvAim!=rows[p].invAim){
				rowList.push(rows[p]);

			}
		}

		var detail = {'detailList': JSON.stringify(rowList)};


		var btns = {};
		var dialogIndex = -1;
		btns['提交'] = function () {
			//调用提交方法
			saveOrSubmit('submit',rowList);
			layer.close(dialogIndex);
		};
		btns['取消'] = function () {
			layer.close(dialogIndex);
		};

		dialogIndex = Joyin.dialog('资产重分类预览',
			basePath + 'valuation/dailyoperation/dailyaccountmake/valassetreclassification/ValAssetReclassification002/init',
			'900px', '400px', detail, function (params) {

			}, btns);
	}




	/**
	 * 画面加载完成
	 */
	$(function() {

		if(workflowId){
			$('#submitDiv').hide();
			tabTable.load();
			$('.condition-area').hide();
			$('.form-button-area').hide();
		}

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

		$('#ValAssetReclassification001_btn_submit').click(function() {
			showDetail();
		});

		$('#doAll').click(function(){
			doButton(1);
		});

		$('#doSelect').click(function(){
			doButton(2);
		});

		$('#doEmpty').click(function(){
			doButton(3);
		});

	});
}());