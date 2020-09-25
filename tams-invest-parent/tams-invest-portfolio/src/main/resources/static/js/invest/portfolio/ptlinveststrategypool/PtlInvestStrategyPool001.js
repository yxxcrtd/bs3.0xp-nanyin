$(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	if(param.strategyId){
    		param.strategyId = param.strategyId.join(",");
    	}
    	if(param.type2){
    		param.type2 = param.type2.join(",");
    	}
    	if(param.type3){
    	    param.type3 = param.type3.join(",");
    	}
    	if(param.type4){
    	    param.type4 = param.type4.join(",");
    	}
    	if(param.type5){
    	    param.type5 = param.type5.join(",");
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
        }
        ,tree:true
        ,checkbox: false
        ,searchOnInit:false
        ,lazyload:true
        ,treeId:'assetTypeId'//树形id字段名称
        ,treeUpId:'pAssetTypeId'//树形父id字段名称
        ,treeShowName:'strategyName'//以树形式显示的字段
       , dblclick:function(table, data) {
    	  //只能双击一级
    	  if(data.strategyId){
    		  data.pageModel=PAGE_MODEL_REFERENCE;
              Joyin.dialog(JoyinUtil.getLabel("PtlInvestStrategyPool002"),
                      basePath+ "invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/init",
                          "85%", "85%", data ,function(){
                              table.reload();
                          });
    	  }
      }
        /**
         * 定义表格名称
         */
        ,cols:[
            {field:'strategyId', title: 'PtlInvestStrategyPool001.strategyId'}//策略代码
            ,{field:'strategyTypeStr', title: 'PtlInvestStrategyPool001.strategyType',sortField:'STRATEGY_TYPE'}//策略类型
            ,{field:'strategyName', title: 'PtlInvestStrategyPool001.strategyName'}//策略名称
            ,{field:'assetTypeId', title: '树形结构代码',visible:false}
            ,{field:'batchId', title: 'PtlInvestStrategyPool001.batchId',visible:false}
            ,{field:'type2Str', title: 'PtlInvestStrategyPool001.type2',sortable:false}//资产二级分类
            ,{field:'type3Str', title: 'PtlInvestStrategyPool001.type3',sortable:false}//资产三级分类
            ,{field:'type4Str', title: 'PtlInvestStrategyPool001.type4',sortable:false}//资产四级分类
            ,{field:'type5Str', title: 'PtlInvestStrategyPool001.type5',sortable:false}//资产五级分类
            ,{field:'ratio', title: 'PtlInvestStrategyPool001.ratio'}//比例
            ,{field:'operation', title: 'SysCommon.operation', type:'action',
            render: function (data, type, row, meta) {
                if (row.level != 1){
                    return "";
                }
                else{
                	var str = "";
                	if(purviewElements.indexOf('_edit')!=-1){
                		str = '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
                    }
                	if(purviewElements.indexOf('_delete')!=-1){
                		str = str + '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
                    }
                	return str;
                }
            }}//编辑和删除事件
        ]
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	if(data.strategyId){
    		 var object = {strategyId: data.strategyId, operateType:"delete"};
    	        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool001/deleteCheck', object, function(result) {//数据验证
    	            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
    	            	Joyin.ajaxJson(basePath + 'invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool001/delete', object, function(result) {
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
    	if(data.strategyId){
    		Joyin.dialog(JoyinUtil.getLabel("PtlInvestStrategyPool002"),
                    basePath+ "invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_EDIT,strategyId:data.strategyId},function(){
                            table.reload();
                        });
    	}
    });
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#PtlInvestStrategyPool001_btn_search').click(function() {
            table.load();//加载表格
        });
        //绑定到处按钮
        $('#PtlInvestStrategyPool001_btn_export').click(function() {
        	var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        });
        //绑定新建按钮
        $('#PtlInvestStrategyPool001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlInvestStrategyPool002"),
                    basePath+ "invest/portfolio/ptlinveststrategypool/PtlInvestStrategyPool002/init",
                        "85%", "85%", {pageModel:PAGE_MODEL_NEW},function(){
                            table.reload();
                        });
        });
    });
}());