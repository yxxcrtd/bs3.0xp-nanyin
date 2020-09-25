/**
 * 情景管理
 */


$(function() {
	function getSearchParams(){
		 return {
	        	pageId: pageId		      //
	        }
	}
    /**
	 * 保存提交事件
	 * @param operateType
	 * @returns
	 */
	function saveOrSubmit(operateType) {
		//取开始日期和结束日期
	    var dateRangeStr = $("#dateRange").val();
	    var startDate = "";
	    var endDate = "";
	    if(dateRangeStr != ""){
	        var dates = dateRangeStr.split(' - ');
	        startDate = dates[0];
	        endDate = dates[1];
	    }
	    var ftrTypeList = tableA.getAllRows();//因子
	    
	    //将选中的因子状态设置选中
        if(pageModel == PAGE_MODEL_NEW){
            for(var index in ftrTypeList){
                if(ftrTypeList[index].LAY_CHECKED){        //语句不需要优化，LAY_CHECKED有可能是undefined
                    ftrTypeList[index].ftrChecked = true;
                }else{
                    ftrTypeList[index].ftrChecked = false;
                }
            }
        }else{
            for(var index in ftrTypeList){
                if(ftrTypeList[index].ftrChecked){        //语句不需要优化，LAY_CHECKED有可能是undefined
                    ftrTypeList[index].ftrChecked = true;
                }else{
                    ftrTypeList[index].ftrChecked = false;
                }
            }
        }

		var proIndList = tableB.getAllRows();//产品指标
		var strIndList = tableC.getAllRows();//承压指标
        var object = $.serializeToObject('#marketRiskSceneManage002Form');
        object.startDate = startDate;
        object.endDate = endDate;
        object.pageModel = object.pageModel||pageModel;
        object.operateType = operateType;
        object.ftrTypeList = ftrTypeList;
        object.proIndList = proIndList;
        object.strIndList = strIndList;
        
        if(pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT){
        	Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/check', object, function(result) {
        	    
        	    var workFlowHandle = function(rtData){
        	        Joyin.confirm(result.data, function() {
//                        console.log(JSON.stringify(rtData));
                        if(operateType === 'save') {
                            WorkFlow.doSaveList(basePath + 'invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/save', rtData, "MarketRiskSceneManage002_btn_save");
                        }
                        else {
                            WorkFlow.doSubmitList(basePath + 'invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/save', rtData, "MarketRiskSceneManage002_btn_submit");
                        }
                    });
        	    }
        	    //如果是提交操作，则需要打开提示
        	    if(operateType === 'save'){
        	        workFlowHandle(object);
        	    }else{
        	        var data = {
                            object:JSON.stringify(object),
                            origin:"edit"
                    }
                    Joyin.dialog('历史情景详细信息展示',
                            basePath + "invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage003/init",
                            "1200", "90%", data, function(rtData){
                        if(rtData != null){
                            $('#sceneId').val(rtData.sceneId).trigger("change");
                            workFlowHandle(rtData);
                        }
                    },null);
        	    }
        	    
            })
        }
    };    
	/**
     * 产品规模-数据表格
     */
    var tableA = $("#dataTableA").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/queryMarketRiskFactor'),
            type: 'POST',
            data: function () {
                return {
                    workflowId:workflowId,
                    pageModel:pageModel,
                    sceneId:sceneId,
                };
            }
        },
    	searchOnInit: true, 
        loadonce: true,
        page: false,
        checkbox: true,   //非工作流过程显示勾选框
        height:180,
        cols: [
                  {field:'ftrTypeValue', title: 'MarketRiskSceneManage002.ftrRskName',sortable: false}
	             ,{field:'ftrType2Value' , title: 'MarketRiskSceneManage002.ftrName',sortable: false}
	             ,{field:'remark', title: 'MarketRiskSceneManage002.ftrRemark',sortable: false} 
            ],
        done:function(table, allData){
            var disableFlag = $('input[lay-filter="layTableAllChoose"]').prop("disabled");
            console.log("是否能编辑："+(!disableFlag && entity.newType==='01'));
            //流程审批中不可编辑
            if(disableFlag){        //在工作流审批过程中，checkbox框存在一些问题。点击表头的check框能取消勾选效果，所以隐藏表头的check框
                $('input[lay-filter="layTableAllChoose"]').next(".layui-form-checkbox")[0].click();
                $($('input[lay-filter="layTableAllChoose"]').next(".layui-form-checkbox")).css('display','none');
            }else if(entity.newType!='01'){
                $("#dataTableA").next('.layui-table-view').off('click');        //禁用table的行点击选中功能
                $($('input[lay-filter="layTableAllChoose"]').next(".layui-form-checkbox")).css('display','none');//隐藏全选
            }
            
            //初始化选中的因子
            for(var index in table){
                var item = table[index];
                if(entity.newType!='01'){
                    var elem = $("#dataTableA").next(".layui-table-view").find(".layui-table-fixed").find(".layui-table-body").find(".layui-form-checkbox")[index].parentNode;
                    var div = document.createElement('div');
                    div.classList.add("shelter-div");
                    elem.appendChild(div);
                }
                if(item.ftrChecked){
                    $("#dataTableA").next(".layui-table-view").find(".layui-table-fixed").find(".layui-table-body").        //在在工作流审批过程中，tableA.checkRow(index)方法失效
                        find(".layui-form-checkbox")[index].classList.add("layui-form-checked");
                    tableA.checkRow(index);
                }
                
            }
        }
    });
   
    var tableB = $("#dataTableB").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/queryIndexTypePro'),
            type: 'POST',
            data: function () {
                return {
                	modelId:$('#tmplId').val()
                };
            }
        },
        searchOnInit: true, 
        loadonce: true,
        page: false,
        checkbox: false,
        rowno: true,
        height:220,
        cols: [
               	 {field:'indId', visible:false}
	            ,{field:'indName', title: 'MarketRiskSceneManage002.indName'}
	             ,{field:'remark' , title: 'MarketRiskSceneManage002.remark'}
            ]
    });
    
    var tableC = $("#dataTableC").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/queryIndexTypeStr'),
            type: 'POST',
            data: function () {
                return {
                	modelId:$('#tmplId').val()
                };
            }
        },
        
        searchOnInit: true, 
        loadonce: true,
        page: false,
        checkbox: false,
        rowno: true,
        height:240,
        cols: [
               	 {field:'indId', visible:false}
	            ,{field:'indName', title: 'MarketRiskSceneManage002.indName'}
	             ,{field:'remark' , title: 'MarketRiskSceneManage002.remark'}
            ]
    });
	
    /**
     * 画面加载完成
     */
    $(function() {
    	// 初始化
    	$('#riskType').val('02').trigger("change");
    	$('#sceneType').val('01').trigger("change");
    	//如果pageModel是【编辑】或【详情】或流程id不为null
    	if(pageModel == '2'|| pageModel == PAGE_MODEL_EDIT || workflowId!=null){
    	//if(entity.startDate!=null && entity.endDate!=null ){
    	    //初始化时间区间输入框
    	    var startDate = entity.startDate.substr(0, 10);
            var endDate = entity.endDate.substr(0, 10);
            $("#dateRange").val(startDate+' - '+endDate).trigger("change");
    	}
    	if(entity.newType!='01'){
    	    $('#suitProduct').attr("disabled","disabled");     //禁止编辑适用产品
    	}
    	layui.form.render();
    	
    	
    	// 绑定按钮事件
    	$('#MarketRiskSceneManage002_btn_save').click(function(){
    		saveOrSubmit('save');
    	});
    	//
    	$('#MarketRiskSceneManage002_btn_submit').click(function(){
    		saveOrSubmit('submit');
    	});
    });
}());