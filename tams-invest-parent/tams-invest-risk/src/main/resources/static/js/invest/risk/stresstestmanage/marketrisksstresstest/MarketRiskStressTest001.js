(function() {
    var getSearchParam = function() {
        
        var  portfName=$("#portfolioSingleName").val();//组合
        var profitType=$("#profitType").val(); //收益类型
        var issueMode=$("#issueMode").val(); //开放类型
        var raiseType=$("#raiseType").val(); //募集方式
        var investType=$("#investType").val(); //投资性质
   
         return {
             portfName : portfName,
             profitType : profitType,
             issueMode : issueMode,
             raiseType : raiseType,
             investType : investType
         }
     }
    var getSearchParams = function() {
//                var  portfName=$("#portfolioSingleName").val();//组合
//                var profitType=$("#profitType").val(); //收益类型
//                var issueMode=$("#issueMode").val(); //开放类型
//                var raiseType=$("#raiseType").val(); //募集方式
//                var investType=$("#investType").val(); //投资性质
//                var sceneId=$("#sceneId").val(); //情景选择
//                var analysisDate=$("#analysisDate").val(); //压力测试基准时间
//                var anastartDate=$("#anastartDate").val(); //指标计算开始时间
//                var anaendDate=$("#anaendDate").val(); //指标计算结束时间
//                var analysisDate=$("#analysisname").val(); // 压力测试名称
//                var null_remark=$("#null_remark").val(); //备注
//                var exeDate=$("#exeDate").val(); //压力测试执行时间
                
                var portfolioSingleName = $('#selecttree').val();//组合、产品
                var sceneId = $('#sceneId').val();//情景选择
//                var analysisVdate = $('#anastartDate').startDate();//指标开始时间
//                var analysisMdate = $('#anastartDate').endDate();//指标结束时间
                var analysisDate=$("#analysisDate").val(); //压力测试基准时间
                var analysisMdate = $('#anaendDate').val();//指标结束时间
                var testName = $('#analysisname').val();//压力测试名称
                var remark = $('#remark').val();//备注
                var riskFacHandling=$('#RISK_FAC_HANDLING').val();//风险因子的异常处理

                 return {
//                     sceneId : sceneId,
//                     investType : investType,
//                     analysisDate : analysisDate,
//                     anastartDate : anastartDate,
//                     anaendDate : anaendDate,
//                     analysisDate : analysisDate,
//                     null_remark : null_remark,
//                     exeDate : exeDate,
//                     portfName : portfName,
//                     profitType : profitType,
//                     issueMode : issueMode,
//                     raiseType : raiseType,
//                     investType : investType
                	 portfolioSingleName:portfolioSingleName,
                	 sceneId:sceneId,
//                	 analysisVdate:analysisVdate,
                	 analysisMdate:analysisMdate,
                	 analysisDate:analysisDate,
                	 testName:testName,
                	 remark:remark,
                	 riskFacHandling:riskFacHandling
                	 
                 }
     }
     function searchTreeSingle() {
      $('#treePortfInd').joyinMixTree({
            onTreeLoadSuccess: function () {
               var node2='';
                $("div.mixtree-div").find('a.refreshAicon').click(function(){
                            event.stopPropagation();
                            $("#sortType").trigger('change');
                        });
                
                var treeObj = $.fn.zTree.getZTreeObj("treePortfInd_tree");
                var nodes = treeObj.getNodes();
                for(var i=0; i < nodes.length;i++) {
                    var node = nodes[i].children;
                    if(node!=null&&node!=''&&node!='undefined'){
                        
                        for(var j=0; j < node.length;j++) {
                            node2 = node2 + node[j].id +',';
                          
                       }
                    }
                   
                }
                if(node2 != ''){
                	//去掉最后的逗号
                	node2 = (node2.substring(node2.length - 1) == ',') ? node2.substring(0, node2.length - 1) : node2;
                }
                
                $("#selecttree").val(node2);
                
            }
        });
     }
     
     

    /**
     * 画面加载完成
     */
    $(function() {
        $('#MarketRiskStressTest001_btn_access').click(function(){
            var object = getSearchParams();
            object.prodIds = object.portfolioSingleName;
            object.baseDate = object.analysisDate;
            //是否只查一条数据
            object.ftrValueStruct = '1';
            Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest001/check', object, function(result) {
//            	Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest002/judgeFacStatus', object, function(result) {
//		        	if(result && result.root.length >0){
		        		Joyin.dialog('未准备因子详细',
		                    basePath + "invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest002/init",
		                    "1000", "83%", object, function(rtData){
		                   
		                },null);
//		        	}
//		        	else{
//		        		Joyin.alert("后台已执行！请在压力测试概览中查看！",'1',function(){
////		                	Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest001/setex', object, function(result) {//请求路径，请求参数回调函数
////		        			}); 
//		                });
//		        	}
		        
//		        });	
            
            });
            	
//        		Joyin.confirm(result.data, function() {
//        			Joyin.alert("后台已执行！请在压力测试概览中查看！");
//        			Joyin.ajaxJson(basePath + 'invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest001/setex', object, function(result) {//请求路径，请求参数回调函数
//        	             
//                        Joyin.alert(JoyinUtil.getMessage('WFL_W00003', '请在概述中查看进度'));
//                }); 
//        		});
            
            /*$.ajax({
                url : basePath + 'invest/risk/stresstestmanage/flowriskstresstest/FlowRiskStressTest001/setex',
                type : 'POST',
                data :  function () {
                    return getSearchParams();
                },
                success : function(result) {
                 
                },
                error:function(msg){
                 
                }
              }) */
            
        });
       
        //初始化，产生树结构
        var sortType= $("#sortType").val();
        sortType='01';
        $("#treePortfInd").attr('url',getAllRelationUrl+'?type='+sortType)
        searchTreeSingle('01');
        
        //默认选择第一个（单组合）
         $("input[name='portfolioLevel'][value='01']").attr('checked','true');
         layui.form.render('radio')
     
      
     
        //分类维度联动事件
        $("#sortType").change(function(){
        
            var sortType=$(this).val();
            $("#treePortfInd").css('height','470px')
            $("#treePortfInd").attr('url',getAllRelationUrl+'?type='+sortType)
            searchTreeSingle();
        })
        layui.form.on('radio(portfolioLevel)',function(data){
        
            $("#treePortfInd").css('height','470px');
            $("#portfolioId").val("");
            $("#portfolioName").val("");
            $("#indName").val("");
            $("#singlePortfolioDiv").show();
            $("#sortType").val('01');
            $("#portfolioBagNameDiv").hide();
            $("#sortType").trigger('change')//触发分类维度事件
            
        })  

        $("#selectorAtag001").click(function(){
            if($("#selectorConditionFilter001").css("display")=="none"){
           
                $("#selectorConditionFilter001").show();
            }
            else{
                clearBasicCondition();
                $("#selectorConditionFilter001").hide();
            }
        })
        $("#RskSupervisionPortfPoint001_btn_portQuery").click(function(){
            var type=$("#sortType").val();
            if(type==""){
                type='01';
            }
            $("#treePortfInd").css('height','470px');
            var preUrl=getAllRelationUrl+"?";
            $("#treePortfInd").attr('url',preUrl+"type="+type+'&portfName='+$("#portfolioSingleName").val()+'&profitType='+$("#profitType").val()+'&issueMode='+$("#issueMode").val()+'&raiseType='+$("#raiseType").val()+'&investType='+$("#investType").val()+'&portfolioType='+$("#portfolioType").val()+'&saleObject='+$("#saleObject").val()+'&isComplianceProd='+$("#isComplianceProd").val());
            searchTreeSingle();
        })

        $("#RskSupervisionPortfPoint001_btn_portClose").click(function(){
          
            $("#selectorConditionFilter001").hide();
        })

    });
    function clearBasicCondition(){
        $("#portfolioSingleName").val('');
        $("#profitType").val('').trigger('change');
        $("#issueMode").val('').trigger('change');
        $("#raiseType").val('').trigger('change');
        $("#investType").val('').trigger('change');
        $("#portfolioType").val('').trigger('change');
        $("#saleObject").val('').trigger('change');
        $("#isComplianceProd").val('').trigger('change');
    }
}());