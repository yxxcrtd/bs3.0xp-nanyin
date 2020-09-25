/**
 * PltMedianManagement001
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var param = $.serializeToObject('#searchForm');
    	if(param.paraCode) {
        	param.paraCode = param.paraCode.join(",");
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
        checkbox:false,
        /**
         * 定义表格名称
         */
        cols:[
             {field:'supDate', title: 'PltMedianManagement001.supDate',width:100,type:'date'}
            ,{field:'paraCode', title: 'PltMedianManagement001.paraCode',width:230}
            ,{field:'paraName', title: 'PltMedianManagement001.paraName',width:500}
            ,{field:'portfolioId',title: 'PltMedianManagement001.portfolioId',width:270}
            ,{field:'tassetCode', title: 'PltMedianManagement001.tassetCode',width:170 }
            ,{field:'paraGranularityStr',sortField:"paraGranularity", title: 'PltMedianManagement001.paraGranularity',width:100}
            ,{field:'newValueStr',sortField:"newValue", title: 'PltMedianManagement001.newValue',width:170 }
            ,{field:'changeValueStr',sortField:"changeValue",title: 'PltMedianManagement001.changeValue',width:170 }
            ,{field:'oldValueStr',sortField:"oldValue",title: 'PltMedianManagement001.oldValue',width:170 }
            ,{field:'num',title: 'PltMedianManagement001.num',width:80 }
            ,{field:'typeyStr',sortField:"typey",title: 'PltMedianManagement001.typey',width:200 }
            ,{field:'tradeId', title: 'PltMedianManagement001.tradeId',width:240}
            ,{field:'dotimestampStr', sortField:"dotimestamp",title: 'PltMedianManagement001.dotimestamp',width:200 }
            ,{field:'createTimeStr',sortField:"createTime", title: 'PltMedianManagement001.createTime',width:200}
            ,{field:'prodId',title: 'PltMedianManagement001.prodId',width:120}
            ,{field:'portfolioName',title: 'PltMedianManagement001.portfolioName',width:350}
            ,{field:'assetCode', title: 'PltMedianManagement001.assetCode',width:170 }
            ,{field:'tradeNumStr',sortField:"tradeNum",title: 'PltMedianManagement001.tradeNum',width:170}
            ,{field:'amountStr',sortField:"amount",title: 'PltMedianManagement001.amount',width:170}
            ,{field:'priceAmtStr',sortField:"priceAmt",title: 'PltMedianManagement001.priceAmt',width:100}
            ,{field:'assetTypeStr',sortField:"assetType",title: 'PltMedianManagement001.assetType',width:170}
            ,{field:'psStr',sortField:"ps",title: 'PltMedianManagement001.ps',width:100}
        ]
    });
    
    
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    
    /**
     * 查询
     */
    var demand = function() {
    	debugger;
    	var paramObj = getSearchParams();//获取参数
    	var blank = null;
    	if(paramObj.supDate == null || paramObj.supDate == ''){
    		Joyin.ajaxJson(basePath + 'invest/riskredis/quota/medianmanagement/PltMedianManagement001/check', paramObj, function(result) {
            });
    	}else{
    		table.load();
    	}
    };
    
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#PltMedianManagement001_btn_search').click(function() {
        	demand();
        });
        //绑定导出按钮
        $('#PltMedianManagement001_btn_export').click(function() {
            exportExcel();//导出
        });
    });
}());