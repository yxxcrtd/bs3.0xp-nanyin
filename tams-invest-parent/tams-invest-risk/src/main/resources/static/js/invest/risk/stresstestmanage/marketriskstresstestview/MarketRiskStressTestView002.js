/**
 * 压力测试
 */
/**
 * 弹出压力测试现金流详情页面
 * 
 * @returns
 */
var openCashFlowDetail = function(portfolioId,flowTestId,testName,finprodId,finprodName,baseDate,startTime,testStartDate,testEndDate) {
	Joyin.dialog(
		JoyinUtil.getLabel("MarketRiskStressTestView003"),
			JoyinUtil.getRealPath("invest/risk/stresstestmanage/marketriskstresstestview/MarketRiskStressTestView003/init"),
			"98%", "98%", {
				pageModel : PAGE_MODEL_NEW,
				testId:"1",
				flowTestId:flowTestId,
				testName:testName,
				portfolioId:portfolioId,
				finprodId:finprodId,
				finprodName:finprodName,
				baseDate:baseDate,
				startTime:startTime,
				testStartDate:testStartDate,
				testEndDate:testEndDate,
			}, function() {
				//table.reload();
			});
}

$(function() {
	var flag = true;
	/**
	 * 点击【查询】获取查询参数
	 */
	var getSearchParams1 = function() {
		//alert($('#testId').val());
		return {
			testId: $('#testId').val(),	//测试id
        }	
	};
	
	/**
     * 初始化获取查询参数
     */
    var getSearchParams = function() {
        return  entity1;
    };
    var exportExcel = function() {
        var paramObj = getSearchParams1();
        paramObj.parentId = 'root';
        Joyin.exportExcel('export', paramObj);
    };
	/**
     * 页面跳转初始化定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findOnePage',
            type: 'POST',
            data: function () {
                return getSearchParams1();
            }
        },
        tree:true,
        treeId: 'id',
        treeUpId: 'parentId',
        treeShowName: 'portfolioName',
        lazyload:true,
//        height: 270,
        searchOnInit: true,
        checkbox: false,
        page : false,
        isMulti:true,
        cols: [
               [
				  {field:'baseDate', visible:false}
				 ,{field:'testStartDate', visible:false}
				 ,{field:'testEndDate' , visible:false}
				 ,{field:'portfolioId' , visible:false}
	             ,{field:'portfolioName', rowspan: 2, sortable:false, width:200, title: 'MarketRiskStressTestView002.portfolioName', render: function (data, type, row, meta) {
	 	            if(row.level == 1) {
	 	                return '<strong>' + data + '</strong>';
	 	            }
	 	            return data;
	 	        }}
	             ,{field:'portfolioId', title: 'MarketRiskStressTestView002.finprodId', rowspan: 2,sortable:false, width: 100}
	             /*,{field:'finprodName' , title: '产品名称', rowspan: 2,sortable:false, width: 100}*/
	             ,{field:'type9Str' , title: 'MarketRiskStressTestView002.type8', rowspan: 2,sortable:false, width: 100}
	             ,{field:'assetType2Str' , title: 'MarketRiskStressTestView002.type9', rowspan: 2,sortable:false, width: 150}
	             ,{field:'assetType2Str' , title: 'MarketRiskStressTestView002.assetType2', rowspan: 2,sortable:false, width: 150,visible:false}
	             ,{field:'assetId', title: 'MarketRiskStressTestView002.assetId', rowspan: 2,sortable:false, width: 200}           
	             ,{field:'assetName', title: 'MarketRiskStressTestView002.assetName', rowspan: 2,sortable:false, width: 200}  
	             ,{field:'openAmount', title: 'MarketRiskStressTestView002.openAmount', rowspan: 2,sortable:false,width: 150,type:'number'}
	             ,{align : "center", title: 'MarketRiskStressTestView002.currentScene', field:'currentScene', colspan: 3, sortable : false}
	             ,{align : "center", title: 'MarketRiskStressTestView002.riskScene', field:'riskScene', colspan: 6, sortable : false}
	           /*  ,{
						field : 'action',
						width : 100,
						title : '现金流详情',
						rowspan: 2,
						fixed:'right',
						render : function(data, type, row, meta) {
							//return '<a style="border-bottom: 1px solid black" onclick="openCashFlowDetail("+")" id="FlowRiskStressTestView001_btn_cashFlowDetail">'+ '现金流详情' + '</a>';
							return "<a onclick=\"openCashFlowDetail('"+row.portfolioId+"','"+row.flowTestId+"','"+row.testName+"','"+row.finprodId+"','"+row.finprodName+"','"+row.baseDate+"','"+row.startTime+"','"+row.testStartDate+"','"+row.testEndDate+"')\" + style='border-bottom: 1px solid black'>"+'现金流详情'+"</a>";
						},
						sortable : false
					}*/
	           ],[
	             
	             /*{field:'openAmount', title: 'MarketRiskStressTestView002.openAmount', sortable:false,width: 130}*/
	             {field:'currentPrice', title: 'MarketRiskStressTestView002.currentPrice', sortable:false,width: 130,type:'number'}
	             ,{field:'marketCap', title: 'MarketRiskStressTestView002.marketCap', sortable:false,width: 130,type:'number'}
	             ,{field:'allAssetsRate', title: 'MarketRiskStressTestView002.allAssetsRate', sortable:false,width: 200,type:'number',digit:4}
	             ,{field:'netAssetsRate', title: 'MarketRiskStressTestView002.netAssetsRate',sortable:false, width: 200,visible:false,type:'number',digit:4}
	             
	             ,{field:'scenePrice', title: 'MarketRiskStressTestView002.scenePrice',sortable:false, width: 130,type:'number'}
	             ,{field:'priceChange', title: 'MarketRiskStressTestView002.priceChange', sortable:false,width: 130,type:'number'}
	             ,{field:'sceneMarVal', title: 'MarketRiskStressTestView002.sceneMarVal', sortable:false,width: 130,type:'number'}
	             ,{field:'allAssetsRate2', title: 'MarketRiskStressTestView002.allAssetsRate2',sortable:false, width: 200,type:'number',digit:4}
	             ,{field:'netAssetsRate2', title: 'MarketRiskStressTestView002.netAssetsRate2', sortable:false,width: 200,visible:false,type:'number',digit:4}
				 ,{field:'amountOfDam', title: 'MarketRiskStressTestView002.amountOfDam', sortable:false,width: 130,type:'number'}
	             ,{field:'proAndLosRat', title: 'MarketRiskStressTestView002.proAndLosRat', sortable:false,width: 130,type:'number',digit:4}
	           ]
             ],
             done:function(res,table, allData){
            	 //初始化显示二级
            	 if($('#testId').val() != undefined){
            		flag =true; 
            	 } 
            	 //选中测试代码
            	 if(flag && $('.layui-tree-head').length >0){
            		 $('.layui-tree-head')[0].click();
            	 }
            	 //点击事件只生效一次
            	 flag = false;
            	 
             }
            
    });
    
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
    	   $('#MarketRiskStressTestView002_btn_export').click(function() {
               if(($('#testId').val())==null||($('#testId').val())==''){
            	   Joyin.error('请选择压力测试编号或名称！');
               }else{
            	   exportExcel();
               }
           });
        $('#MarketRiskStressTestView002_btn_search').click(function() {
        	Joyin.ajax(basePath + 'invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView002/queryDetByTestId', {testId: $('#testId').val()}, function (data) {
	       		 //适用产品
	       		 $('#openMode').val(data.openModeStr);
	       		 //压力测试执行时间
	       		 $('#startTime').val(data.startTime);
	       		 //分析基准日期
	       		 $('#baseDate').val(data.baseDate);
	       		 //分析开始日期
	       		 $('#analysisVDate').val(data.testStartDate);
	       		 //分析结束日期
	       		 $('#analysisMDate').val(data.testEndDate);
	       		 //备注
	       		 $('#remark').val(data.remark);	
	       	//情景编号和名称
        		 if(data.sceneIdAndsceneName!=':'){//判断是否是':',如果不是则赋值
        		 $('#sceneIdAndsceneName').val(data.sceneIdAndsceneName);
        		 }
	       	 });
            table.load();
        });
        
        //点击情景弹出情景页面
          $('#sceneIdAndsceneName').click(function(){
          	var sceneIdAndsceneName=$('#sceneIdAndsceneName').val();
          	//取出情景代码
          	if(sceneIdAndsceneName!=null && sceneIdAndsceneName!=''){
          	var sceneId=sceneIdAndsceneName.substr(0,sceneIdAndsceneName.indexOf(':'));
          	Joyin.dialog(JoyinUtil.getLabel("MarketRiskSceneManage005"),
                      basePath + "invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage002/init",
                      "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,//详情
          							sceneId:sceneId //情景id
                                      });
          	}else{
          		Joyin.error("无效的情景！！！");
          	}
          	
          });
    });
}());