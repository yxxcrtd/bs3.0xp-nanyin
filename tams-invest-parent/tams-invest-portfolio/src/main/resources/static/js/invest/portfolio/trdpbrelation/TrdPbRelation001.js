$(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	if(param.prdId){
    		param.prdId = param.prdId.join(",");
    	}else{
    	   param.prdId=null;
    	}
    	if(param.portfolioId){
    		param.portfolioId = param.portfolioId.join(",");
    	}else{
    	   param.portfolioId=null;
    	}
    	return param;
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
                Joyin.dialog(JoyinUtil.getLabel("TrdPbRelation003"),
                    basePath + "invest/portfolio/trdpbrelation/TrdPbRelation002/init",
                    "98%", "98%",  {pageModel:PAGE_MODEL_EDIT,seqno:data.seqno} ,function(){
	                    table.reload();
	                });
        }
        /**
         * 定义表格名称
         */
        ,cols:[
            {field:'prdPortfolioId', title: 'TrdPbRelation001.prdPortfolioId',width:220},//产品/子组合代码
            {field:'prdPortfolioName', title: 'TrdPbRelation001.prdPortfolioName',width:300},//产品/子组合名称
            {field:'factorTypeStr', title: 'TrdPbRelation001.factorType',width:120},//要素类型
            {field:'pbId', title: 'TrdPbRelation001.pbId',width:220},//PB编号
            {field:'updateTimeStr', title: 'TrdPbRelation001.updateTime',width:160},//更新日期
            {field:'updateUser', title: 'TrdPbRelation001.updateUser',width:150},//更新人
            {field:'seqno', title: 'seqno',visible:false,width:0},//流水号
            {field:'operation', title: 'SysCommon.operation', type:'action',
            render: function (data, type, row, meta) {
            	var str = '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>      ';
                str = str + '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
                return str;
            }}//编辑和删除事件
        ]
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	if(data.seqno){
    		 var object = {seqno: data.seqno, operateType:"delete"};
    	        Joyin.ajaxJson(basePath + 'invest/portfolio/trdpbrelation/TrdPbRelation001/deleteCheck', object, function(result) {//数据验证
    	            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
    	            	Joyin.ajaxJson(basePath + 'invest/portfolio/trdpbrelation/TrdPbRelation001/delete', object, function(result) {
    	                  table.reload();
    	                });
    	            });
    	        });
    	}
    });
    /**
     * 绑定修改事件
     */
    table.bind('click', '.edit', function(data, index) {
    	if(data.seqno){
    		Joyin.dialog(JoyinUtil.getLabel("TrdPbRelation003"),
                    basePath+ "invest/portfolio/trdpbrelation/TrdPbRelation002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_EDIT,seqno:data.seqno},function(){
                            table.reload();
                        });
    	}
    });
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#TrdPbRelation001_btn_search').click(function() {
            table.load();//加载表格
        });
        //绑定到处按钮
        $('#TrdPbRelation001_btn_export').click(function() {
        	var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        });
        //绑定新建按钮
        $('#TrdPbRelation001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("TrdPbRelation002"),
                    basePath+ "invest/portfolio/trdpbrelation/TrdPbRelation002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_NEW},function(){
                            table.reload();
                        });
        });
    });
}());