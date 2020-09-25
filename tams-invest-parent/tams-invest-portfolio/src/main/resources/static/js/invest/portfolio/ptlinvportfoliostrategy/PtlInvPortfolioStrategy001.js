$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var portfolioId = $('#portfolioId').val();
        if(portfolioId && portfolioId != ''){
        	portfolioId = portfolioId.join(',');
        }else {
        	portfolioId = null;
        }
        
        var strategyId = $('#strategyId').val();
        if(strategyId && strategyId != ''){
        	strategyId = strategyId.join(',');
        }else {
        	strategyId = null;
        }
        
        var strategyType = $('#strategyType').val();
        if(strategyType && strategyType != ''){
        	strategyType = strategyType.join(',');
        }else {
        	strategyType = null;
        }
        
        return {
        	portfolioId: portfolioId,//组合代码
        	strategyId: strategyId,//策略代码
        	strategyType: strategyType,//策略类型
            startEffDate:$('#effDate').startDate(),
            endEffDate:$('#effDate').endDate()//策略生效日
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,ptlStgyId:data.ptlStgyId,indexId:data.indexId});
        },
        /**
         * 定义表格名称
         */
        cols:[
            {field:'ptlStgyId', title: 'PtlInvPortfolioStrategy001.ptlStgyId',visible:false}//组合策略代码
            ,{field:'indexId', title: 'PtlInvPortfolioStrategy001.indexId',visible:false}//指标代码
            ,{field:'portfolioId', title: 'PtlInvPortfolioStrategy001.portfolioId'}//组合代码
            ,{field:'portfolioName', title: 'PtlInvPortfolioStrategy001.portfolioName'}//组合名称
            ,{field:'strategyTypeStr', title: 'PtlInvPortfolioStrategy001.strategyType',sortField:'strategyType'}//策略类型
            ,{field:'strategyId', title: 'PtlInvPortfolioStrategy001.strategyId'}//策略代码
            ,{field:'strategyName', title: 'PtlInvPortfolioStrategy001.strategyName'}//策略名称
            ,{field:'strategyDesc', title: 'PtlInvPortfolioStrategy001.strategyDesc'}//策略描述
            ,{field:'effDate', title: 'PtlInvPortfolioStrategy001.effDate',visible:false}//生效日
            //,{field:'incomeTypeStr', title: 'PtlInvPortfolioStrategy001.incomeType',sortField:'incomeType'}//收益目标类型
            //,{field:'incomeTarget', title: 'PtlInvPortfolioStrategy001.incomeTarget'}//收益目标%
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
        ]
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {ptlStgyId: data.ptlStgyId, indexId:data.indexId,incomeType:data.incomeType,effDate:data.effDate, operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy001/check', object, function(result) {//数据验证
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            	Joyin.ajaxJson(basePath + 'invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy001/delete', object, function(result) {
                  table.reload();
                });
            });
        });
    });
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#PtlInvPortfolioStrategy001_btn_search').click(function() {
            table.load();//加载表格
        });
        //绑定到处按钮
        $('#PtlInvPortfolioStrategy001_btn_export').click(function() {
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
        });
        //绑定新建按钮
        $('#PtlInvPortfolioStrategy001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlInvPortfolioStrategy002"),
                    basePath+ "invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_NEW},function(){
                            table.reload();
                        });
        });
    });
}());