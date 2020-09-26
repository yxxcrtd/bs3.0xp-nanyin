$(function() {
	
	$("#SvrOperationalInfo004_mgrTableName").prop("disabled", true);
	
	var SvrOperationalInfo004_table001 = $('#queryCondition').joyintable({
        ajax: {
            url: basePath + "common/infomanagement/SvrOperationalInfo002/queryConditionPage",
            type: 'POST',
            async: false,
            data: {
            	mgrTableId : $("#SvrOperationalInfo004_mgrTableId").val()
            }
        },
        checkbox: false,
        addbtn: true,
        searchOnInit: true,
        cols: [
            { title: "查询条件主键", field:'baseInformationId', visible: false},
            { title: "字段名", field:'baseColumnId', edit:true, editOption: {type:'input'}, sortable:false},
            { title: "字段中文名", field:'baseColumnName', edit:true, editOption: {type:'input'}, sortable:false},
            { title: "字段类型", field:'baseColumnType', edit:true, editOption: {type:'select' , multiple: false,result: BASE_COLUMN_TYPE_LIST}, sortable:false},
            { title: "是否业务主键", field:'businesspkFlag', edit:true, editOption: {type:'select' , multiple: false,result: PK_FLAG}, sortable:false},
            { title: "数据字典", field:'dictCode', edit:true, editOption: {type:'input'}, sortable:false},
            { field:'operation', title: '操作', type:'action', buttons: ['delt']}
        ]
    });
	
	var SvrOperationalInfo004_table002 = $('#dataResult').joyintable({
        ajax: {
            url: basePath + "common/infomanagement/SvrOperationalInfo002/queryDataResultPage",
            type: 'POST',
            async: false,
            data: {
            	tableId : $("#SvrOperationalInfo004_mgrTableId").val()
            }
        },
        checkbox: false,
        addbtn: true,
        searchOnInit: true,
        cols: [
            { title: "查询结果列主键", field:'resultId', visible: false},
            { title: "字段名", field:'columnId', edit:true, editOption: {type:'input'}, sortable:false},
            { title: "字段中文名", field:'columnName', edit:true, editOption: {type:'input'}, sortable:false},
            { title: "数据字典", field:'resultdictCode', edit:true, editOption: {type:'input'}, sortable:false},
            { title: "序号", field:'orderNo', edit:true, editOption: {type:'input'}, sortable:false},
            { title: "是否前台显示", field:'showFlag', edit:true, editOption: {type:'select' , multiple: false,result: PK_FLAG}, sortable:false},
            { field:'operation', title: '操作', type:'action', buttons: ['delt']}
        ]
    });
	
	/**
     *绑定删除事件
    */
	SvrOperationalInfo004_table001.bind('click', '.delt', function(data, index) {
		SvrOperationalInfo004_table001.removeRow(data);
    });
	
	SvrOperationalInfo004_table002.bind('click', '.delt', function(data, index) {
		SvrOperationalInfo004_table002.removeRow(data);
    });
	
	
	$("#SvrOperationalInfo004_BTN_submit").click(function() {
		var mgrTableChiName = $("#SvrOperationalInfo004_mgrTableChiName").val();
		// 待维护表信息非空检查
		if (typeof (mgrTableChiName) == "undefined" || mgrTableChiName == null || mgrTableChiName == "") {
			layer.alert('待维护表中文名称不能为空！', {
				icon : 2,
				skin : 'layer-ext-moon'
			});
			return;
		}
		
		// 查询条件合理性检查
		var conditionData = SvrOperationalInfo004_table001.getAllRows();
		for(var i=0; i<conditionData.length; i++){
			var baseColumnId = conditionData[i].baseColumnId;
			var baseColumnName = conditionData[i].baseColumnName;
			var baseColumnType = conditionData[i].baseColumnType;
			var businesspkFlag = conditionData[i].businesspkFlag;
			var dictCode = conditionData[i].dictCode;
			if (typeof (baseColumnId) == "undefined" || baseColumnId == null || baseColumnId == "" ||
					typeof (baseColumnName) == "undefined" || baseColumnName == null || baseColumnName == "" ||
					typeof (baseColumnType) == "undefined" || baseColumnType == null || baseColumnType == "" ||
					typeof (businesspkFlag) == "undefined" || businesspkFlag == null || businesspkFlag == "") {
				layer.alert('查询条件的字段名，字段中文名，字段类型，是否业务主键存在空值，请填写完整再提交！', {
					icon : 2,
					skin : 'layer-ext-moon'
				});
				return;
			}
			if (baseColumnType == '序列' && (typeof (dictCode) == "undefined" || dictCode == null || dictCode == "") ) {
				layer.alert('查询条件的字段类型为序列S，必须指定数据字典！', {
					icon : 2,
					skin : 'layer-ext-moon'
				});
				return;
			}
 		}
		// 查询结果合理性检查
		var resultData = SvrOperationalInfo004_table002.getAllRows();
		for (var i=0; i<resultData.length; i++) {
			var columnId = resultData[i].columnId;
			var columnName = resultData[i].columnName;
			var orderNo = resultData[i].orderNo;
			var showFlag = resultData[i].showFlag;
			if (typeof (columnId) == "undefined" || columnId == null || columnId == "" ||
					typeof (columnName) == "undefined" || columnName == null || columnName == "" ||
					typeof (orderNo) == "undefined" || orderNo == null || orderNo == "" ||
					typeof (showFlag) == "undefined" || showFlag == null || showFlag == "") {
				layer.alert('查询结果的字段名，字段中文名，序号存在空值，请填写完整再提交！', {
					icon : 2,
					skin : 'layer-ext-moon'
				});
				return;
			}
			if(!(isInteger(orderNo)) || orderNo == '' || orderNo == []) {
				layer.alert('查询结果序号必须为整数，请修改不合理数据！', {
					icon : 2,
					skin : 'layer-ext-moon'
				});
				return;
			}
		}
		var param = {};
		param.conditionData = JSON.stringify(conditionData);
		param.resultData = JSON.stringify(resultData);
		param.mgrTableId = $("#SvrOperationalInfo004_mgrTableId").val();
		param.mgrTableName = $("#SvrOperationalInfo004_mgrTableName").val();
		param.mgrTableChiName = $("#SvrOperationalInfo004_mgrTableChiName").val();
		param.dataObject = $("#SvrOperationalInfo004_dataObject").val();
		Joyin.ajaxSync('check', param, function(result){
			if (result && result.length > 0) {
				window.checkResult = result;
	            Joyin.checkData(dataTitle, checkUrl, param);
			} else {
				Joyin.ajaxSync('savePlanData', param, function(result){
//					alert(result);
					if (result == 'success') {
						var index = parent.layer.getFrameIndex(window.name);  
		                layer.alert('保存成功！', {
							icon : 1,
							skin : 'layer-ext-moon'
						});
//		                window.location.reload()
					} else {
						layer.alert('保存失败！', {
							icon : 2,
							skin : 'layer-ext-moon'
						});
					}
				});
			}
            
        })
	});
	
	function isInteger(obj) {
		return obj%1 === 0;
	}
	SvrOperationalInfo004_table001._bindEvent("queryCondition");
});