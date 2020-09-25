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
		
		var ftrList1 = tableA.getAllRows();//因子
		ftrList1.netOutf = $("input[name='netOutf']:checked").val();//申赎净流出基础选择radio单选
		var ftrList2 = tableB.getAllRows();//因子
//		var sceneType = $('#sceneType').val();//情景类型
//		var tmplId = $('#tmplId').val();//压测模板
//		var riskType = $('#riskType').val();//风险类型
//		var sceneType2 = $('#sceneType2').val();//情景二级类型
//		var openmode = $('#openmode').val();//适用产品
		var proIndList = tableC.getAllRows();//产品指标
		var strIndList = tableD.getAllRows();//承压指标
        var object = $.serializeToObject('#flowRiskSceneManage002Form');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.ftrList1 = ftrList1;
        object.ftrList2 = ftrList2;
        object.proIndList = proIndList;
        object.strIndList = strIndList;
        if(pageModel == PAGE_MODEL_NEW){
        	Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/check', object, function(result) {
        		Joyin.confirm(result.data, function() {
        			if(operateType === 'save') {
        				WorkFlow.doSaveList(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/save', object, "FlowRiskSceneManage002_btn_save");
                    }
                    else {
                    	WorkFlow.doSubmitList(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/save', object, "FlowRiskSceneManage002_btn_submit");
                    }
        		});
            })
        }
//        else if(pageModel == PAGE_MODEL_EDIT && (wfl_operType == null || wfl_operType == 'NEW') ){
        else if(pageModel == PAGE_MODEL_EDIT ){
        	object.oldSceneName = $('#oldSceneName').val();
        	Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/check', object, function(result) {
        		Joyin.confirm(result.data, function() {
        			if(operateType === 'save') {
        				WorkFlow.doSaveList(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/save', object, "FlowRiskSceneManage002_btn_save");
                    }
                    else {
                    	WorkFlow.doSubmitList(basePath + 'invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/save', object, "FlowRiskSceneManage002_btn_submit");
                    }
        		});
            })
    	}
    };    
	/**
     * 产品规模-数据表格
     */
    var tableA = $("#dataTableA").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/queryFtrOne'),
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
        checkbox:false,
        height:100,
        cols: [
                  {field:'sceneFtrId', visible:false}
               	 ,{field:'ftrId', title: 'FlowRiskSceneManage002.ftrId', width:400 ,edit:true, editOption:{type:'select', multiple: false,result: FLOW_RISK_FACTOR_ONE}, sortable:false}
	             ,{field:'lightValue', title: 'FlowRiskSceneManage002.lightValue',width:150 , edit:true, type:'number'}
	             ,{field:'mediumValue' , title: 'FlowRiskSceneManage002.mediumValue', width:150 ,edit:true, type:'number'}
	             ,{field:'heavyValue', title: 'FlowRiskSceneManage002.heavyValue', width:150 ,edit:true, type:'number'}           
	             ,{field:'remark', title: 'FlowRiskSceneManage002.ftrRemark'}
            ],
        editCell: function(value, data, field, cell, column){
        	var flag = true;
        	//产品赎回比例（%），应大于0，小于100
        	if( field == 'lightValue' || field == 'mediumValue' || field == 'heavyValue'){
        		if(data.ftrId == PrdScl01002){
            		if( 0 > value || value > 100 ){
            			Joyin.error(JoyinUtil.getMessage("RSK_W_F_0005"));
            			flag = false;
            		}//申赎净流出增加比例（%），应大于0
            	}else if(data.ftrId == PrdScl01001){
            		if( 0 > value ){
            			Joyin.error(JoyinUtil.getMessage("RSK_W_F_0004"));
            			flag = false;
            		}
            	}
        	}
        	
        	if(field == 'ftrId' && value==''){
        		flag = false;
        	}
        	if(field == 'ftrId' && value == PrdScl01002){
        		$("#netOutfDiv").attr("style","display:none");
        		var ftrNameList = $.parseJSON(ftrRemarkList);
        		if(ftrNameList!=null && ftrNameList.length > 0){
        			for(var k=0; k<ftrNameList.length; k++){
        				if(ftrNameList[k].ftrId == PrdScl01002){
        					//document.getElementsByClassName('laytable-cell-1-remark')[1].textContent=ftrNameList[k].remark;
        				}
        			}
        		}
        	}else if(field == 'ftrId' && value == PrdScl01001){
        		$("#netOutfDiv").attr("style","height:60px;display:block");
        		var ftrNameList = $.parseJSON(ftrRemarkList);
        		if(ftrNameList!=null && ftrNameList.length > 0){
        			for(var k=0; k<ftrNameList.length; k++){
        				if(ftrNameList[k].ftrId == PrdScl01001){
        					//document.getElementsByClassName('laytable-cell-1-remark')[1].textContent=ftrNameList[k].remark;
        				}
        			}
        		}
        	}
			
			//轮训任务，直到渲染完毕
        	c =window.setInterval(function(){myFunction()}, 100);
        	return flag;
        	
        },
        done:function(table, allData){
        	var textA = document.getElementsByClassName('laytable-cell-1-0-1')[1].textContent;
        	document.getElementsByClassName('laytable-cell-1-0-1')[1].innerHTML=""+textA+"  <i class='iconfont icon-arrow-down pro-down-icon'></i>";
        	if(workflowId){
        		if(entity.netOutf ==null ){
        			$("input[name=netOutf][value='"+table[0].netOutf+"']").attr("checked", true);
        		}else{
        			$("input[name=netOutf][value='"+entity.netOutf+"']").attr("checked", true);
        		}
        		
        	}else if(pageModel == '0'){
        		$("input[name=netOutf][value='01']").attr("checked", true);
        	}else if(table[0].ftrId && table[0].ftrId == PrdScl01001){
        		$("input[name=netOutf][value='"+table[0].netOutf+"']").attr("checked", true);
        	}
        	//编辑赋初始值
        	if(pageModel == '1' && table[0].ftrId && table[0].ftrId == PrdScl01002){
        		$("input[name=netOutf][value='01']").attr("checked", true);
        	}
        	layui.form.render();
        	if(table[0].ftrId && table[0].ftrId == PrdScl01001){
        		$("#netOutfDiv").attr("style","height:60px;display:block");
        	}
        }
    });
    
    //为table中下拉框渲染下拉样式
    var myFunction =function() {
    	//var i=0;
    	var size=document.getElementsByClassName('laytable-cell-1-0-1').length;
    	//如果
    	if(size >1){
    		var textA = document.getElementsByClassName('laytable-cell-1-0-1')[1].textContent;
        	document.getElementsByClassName('laytable-cell-1-0-1')[1].innerHTML=""+textA+"  <i class='iconfont icon-arrow-down pro-down-icon'></i>";
        	//关闭轮询任务
			clearInterval(c);
    	}
        //console.log("轮训任务=================>" + i++);
    }
    /**
     * 资产变现-数据表格
     */
    var tableB = $("#dataTableB").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/queryFtrTwo'),
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
        checkbox: false,
        height:100,
        cols: [
	              {field:'ftrId', visible:false}
	             ,{field:'sceneFtrId', visible:false}
	             ,{field:'ftrName', title: 'FlowRiskSceneManage002.ftrId', width:400}
	             ,{field:'lightValue', title: 'FlowRiskSceneManage002.lightValue',width:150, edit:true, type:'number'}
	             ,{field:'mediumValue' , title: 'FlowRiskSceneManage002.mediumValue', width:150, edit:true, type:'number'}
	             ,{field:'heavyValue', title: 'FlowRiskSceneManage002.heavyValue', width:150, edit:true, type:'number'}           
	             ,{field:'remark', title: 'FlowRiskSceneManage002.ftrRemark'}
            ],
            editCell: function(value, data, field, cell, column){
            	//二级流动性资产变化率（%），应大于0，小于100
            	if( field == 'lightValue' || field == 'mediumValue' || field == 'heavyValue'){
            		if(data.ftrId == AssLqu01001){
                		if( 0 > value || value > 100 ){
                			Joyin.error(JoyinUtil.getMessage("RSK_W_F_0006"));
                		}
                	}
            	}
            	
            }
    });
    
    var tableC = $("#dataTableC").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/queryIndexTypePro'),
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
        height:200,
        cols: [
               	 {field:'indId', visible:false}
	            ,{field:'indName', title: 'FlowRiskSceneManage002.indName',width:150}
	             ,{field:'remark' , title: 'FlowRiskSceneManage002.remark',width:350}
            ]
    });
    
    var tableD = $("#dataTableD").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/flowriskscenemanage/FlowRiskSceneManage002/queryIndexTypeStr'),
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
        height:200,
        cols: [
               	 {field:'indId', visible:false}
	            ,{field:'indName', title: 'FlowRiskSceneManage002.indName',width:170}
	             ,{field:'remark' , title: 'FlowRiskSceneManage002.remark',width:350}
            ]
    });
	
    /**
     * 画面加载完成
     */
    $(function() {
    	// 初始化
//    	$('#sceneDemoName').val('流动性压力测试-情景设置模板');
//    	$('#sceneDemoId').val('');
    	$('#riskType').val('03').trigger("change");
    	$('#sceneType').val('02').trigger("change");
//    	$('#sceneType2').val('02').trigger("change");
//    	$("input[name=netOutf][value='01']").attr("checked", true);
    	layui.form.render();
    	
    	// 绑定按钮事件
    	$('#FlowRiskSceneManage002_btn_save').click(function(){
    		saveOrSubmit('save');
    	});
    	//
    	$('#FlowRiskSceneManage002_btn_submit').click(function(){
    		saveOrSubmit('submit');
    	});
    	
    	
    	
    });
}());