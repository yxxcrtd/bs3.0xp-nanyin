(function() {
	
	
    var getSearchParam = function() {
        
         return {
        	 prodIds : prodIds,
        	 sceneId : sceneId,
        	 baseDate : baseDate,
        	 portfolioSingleName : portfolioSingleName,//组合、产品
             analysisMdate : analysisMdate,//指标结束时间
             analysisDate : analysisDate,//基准时间
             testName : testName,//压力测试名称
             remark : remark,//备注
             riskFacHandling : riskFacHandling//风险因子的异常处理
         }
     }
     
    /**
     * 资产变现-数据表格
     */
    var tableA = $("#dataTableA").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest002/selectProdForPage'),
            type: 'POST',
            data: function () {
                return {
            		prodIds : prodIds,
	               	sceneId : sceneId,
	               	baseDate : baseDate,
	               	portfolioSingleName : portfolioSingleName,//组合、产品
                    analysisMdate : analysisMdate,//指标结束时间
                    analysisDate : analysisDate,//基准时间
                    testName : testName,//压力测试名称
                    remark : remark,//备注
                    riskFacHandling : riskFacHandling//风险因子的异常处理
                };
            }
        },
        searchOnInit: true, 
//        loadonce: true,
        checkbox: false,
        height:330,
        cols: [
	             {field:'prodId', title: 'MarketRiskStressTest002.prodId',width:'130',sortable:false}
	             ,{field:'prodName', title: 'MarketRiskStressTest002.prodName',width:'160',sortable:false}
	             ,{field:'ccsl', title: 'MarketRiskStressTest002.ccsl',sortable:false}
            ]
    });
    
    
    
    
    /**
     * 资产变现-数据表格
     */
    var tableB = $("#dataTableB").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest002/selectFacForPage'),
            type: 'POST',
            data: function () {
                return {
                	prodIds : prodIds,
	               	sceneId : sceneId,
	               	baseDate : baseDate,
	               	portfolioSingleName : portfolioSingleName,//组合、产品
                    analysisMdate : analysisMdate,//指标结束时间
                    analysisDate : analysisDate,//基准时间
                    testName : testName,//压力测试名称
                    remark : remark,//备注
                    riskFacHandling : riskFacHandling,//风险因子的异常处理
                    inIsOneRow : '0'
                };
            }
        },
        searchOnInit: true, 
//        loadonce: true,
        checkbox: false,
        height:330,
        cols: [
	              {field:'factorId', title: 'MarketRiskStressTest002.factorId', width:'100',sortable:false}
	             ,{field:'ftrName', title: 'MarketRiskStressTest002.ftrName',width:'130',sortable:false}
	             ,{field:'facStatus', title: 'MarketRiskStressTest002.facStatus',sortable:false}
            ]
    });
    
    
     

    /**
     * 画面加载完成
     */
    $(function() {
        $('#MarketRiskStressTest002_btn_submit').click(function(){
            var object = getSearchParam();
            
            Joyin.alert("后台已执行！请在压力测试概览中查看！",'1',function(){
            	Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest001/setex', object, function(result) {//请求路径，请求参数回调函数
    			}); 
            	WorkFlow.cancel();
            });
			
			
            
        });
       

    });
}());