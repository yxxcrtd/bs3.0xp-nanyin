/**
 * 场内基金类标的资产
 */

$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        return {
        	finprodMarketId:$('#finprodMarketId').val(),
            trmFundType: $('#trmFundType').val(),
            etfType: $('#etfType').val(),
        	tradeMarket: $('#tradeMarket').val(),
            manager: $('#manager').val(),
        	startDate:$('#vdate').startDate(),
        	endDate:$('#vdate').endDate(),
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/asset/exfund/FinExFundTarget002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId});
        },
        
        cols:[
             {field:'finprodMarketId', width:150 , title: 'FinExFundTarget001.finprodMarketId'}
            ,{field:'finprodAbbr', width:150 , title: 'FinExFundTarget001.finprodAbbr'}
            ,{field:'trmFundTypeStr', width:150 , sortField:"trmFundType",title: 'FinExFundTarget001.trmFundType'}
            ,{field:'etfTypeStr', width:150 , sortField:"etfType", title: 'FinExFundTarget001.etfType'}
            ,{field:'tradeMarketStr', width:150 , sortField:"tradeMarket",title: 'FinExFundTarget001.tradeMarket'}
            ,{field:'manager', width:150 ,title: 'FinExFundTarget001.manager'}
            ,{field:'purSpeed', width:150 , title: 'FinExFundTarget002.purSpeed'}
            ,{field:'redSpeed', width:150 , title: 'FinExFundTarget002.redSpeed'}
            ,{field:'divideMethodStr', width:150 , sortField:"divideMethod", title: 'FinExFundTarget001.divideMethod'}
            ,{field:'vdateStr',width:150 , sortField:"vdate", title: 'FinExFundTarget001.vdate',type:'date'}
            ,{field:'operation', width:100,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/asset/exfund/FinExFundTarget001/check', data, function(result) {
        	Joyin.ajaxJson(basePath + 'invest/asset/exfund/FinExFundTarget001/editCheck', data, function(editResult){
        		Joyin.dialog(JoyinUtil.getLabel("FinExFundTarget002.edit"),
                        basePath + "invest/asset/exfund/FinExFundTarget002/init",
                        "95%", "90%",data,function() {
                                            table.reload();
                                        });
        	});
        	
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/asset/exfund/FinExFundTarget001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/asset/exfund/FinExFundTarget001/delete', object, 
                        "FinExFundTarget001_btn_delete", "FinExFundTarget002",function() {
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinExFundTarget001_btn_search').click(function() {
            table.load();
            $('#FinExFundTarget001_btn_delete').show();
        });
        $('#FinExFundTarget001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinExFundTarget001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinExFundTarget002"),
                        basePath + "invest/asset/exfund/FinExFundTarget002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#FinExFundTarget001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/exfund/FinExFundTarget001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/asset/exfund/FinExFundTarget001/delete', object, 
                            "FinExFundTarget001_btn_delete","FinExFundTarget002");
                });
            });
        });
        
    });
}());