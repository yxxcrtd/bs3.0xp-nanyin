/**
 * RepInterLog003 接口日志管理详细
 */
(function() {
	
	/**
     * 定义数据表格
     */
	var options = {
		page:false,
		searchOnInit:true,
		loadonce : true,
        checkbox:false,
        height:200,
        cols:[
			 {field:'portfolioId',title:'RepInterLog003.portfolioId',width:250, sortable: false}
			,{field:'portfolioName',title:'RepInterLog003.portfolioName',width:250, sortable: false}
            ,{field:'kriName',title:'RepInterLog003.kriName',width:250, sortable: false}//指标名称
            ,{field:'kriType',title:'RepInterLog003.kriType',width:120,render: transferType, sortable: false}//指标类型
            ,{field:'approveLine',title: 'RepInterLog003.approveLine',width:120, sortable: false}//审批线
            ,{field:'warningLine', title: 'RepInterLog003.warningLine',width:120, sortable: false}//预警线
            ,{field:'forbiddenLn', title: 'RepInterLog003.forbiddenLn',width:120, sortable: false}//禁止线
            ,{field:'oldValue', title: 'RepInterLog003.oldValue',width:120, sortable: false}//原值
            ,{field:'kriTrend', title: 'RepInterLog003.kriTrend',width:120, sortable: false}//趋势
            ,{field:'uniteStatus', title: 'RepInterLog003.uniteStatus',width:120,render:transferStatus, sortable: false}//联合监控状态
            ,{field:'kriValue', title: 'RepInterLog003.kriValue', width:120,sortable: false}//指标计算值
            ,{field:'kriStatus', title: 'RepInterLog003.kriStatus',render:transferStatus, sortable: false}//指标状态
            ],
    	
    };
	
	function transferType(data, type, row, meta){
		if(data==''){
    		return data;
    	}
    	var retStr;
    	var indTypeArr = JSON.parse(IND_TYPE);
    	$.each(indTypeArr,function(i,v){
    		if(v.id==data){
    			retStr=v.text;
    		}
    	})
    	return retStr;
	};
	function transferStatus(data, type, row, meta){
		if(data==''){
    		return data;
    	}
    	var retStr;
    	var statusArr = JSON.parse(INF_KRI_STATUS);
    	$.each(statusArr,function(i,v){
    		if(v.id==data){
    			retStr=v.text;
    		}
    	})
    	return retStr;
	};
	function dataDisplay(){
		if(responseData){
			//var responseDataJ = responseData==''?'':JSON.parse(responseData);
			var responseDataJson = responseData==''?'':JSON.parse(responseData);
			if(responseDataJson.head){
				rskStatus = responseDataJson.body.rskStatus;
				var statusArr = JSON.parse(INF_KRI_STATUS);
		    	$.each(statusArr,function(i,v){
		    		if(v.id==rskStatus){
		    			$("#result").val(v.text);
		    			retStr=v.text;
		    		}
		    	})
				list = responseDataJson.body.RSK_LIST;
				if(list && list.length>0){
					var row = [];
					for(var i=0;i<list.length;i++){
						row[i] = {portfolioId:list[i].portfolioCode,portfolioName:list[i].portfolioName,kriName:list[i].kriName,
								kriType:list[i].kriType,approveLine:list[i].approveLine,warningLine:list[i].warningLine,
								forbiddenLn:list[i].forbiddenLn,oldValue:list[i].oldValue,kriTrend:list[i].kriTrend,
								uniteStatus:list[i].uniteStatus,kriValue:list[i].kriValue,kriStatus:list[i].kriStatus};
						}
					/*$.each(row,function(i,v){
						table.layOption.data.push(v);
					})*/
					setTimeout(function(){table.addRow(row);},500);
				}
			}
		}
	};
	/*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
	var init = function() {
		//setTimeout(dataDisplay(),5000);
		dataDisplay();
	};

	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
	});
}());
