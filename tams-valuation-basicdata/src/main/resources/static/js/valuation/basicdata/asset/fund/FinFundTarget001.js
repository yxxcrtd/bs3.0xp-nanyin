/**
 * 平安基金类标 的资产
 */

$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var finprodId = $('#finprodMarketId_hidden').val(), finprodMarketId = '';
    	if(finprodId == null){
    		finprodMarketId = $('#finprodMarketId').val();
    	}
        //	查询条件换成资产二级分类多选项 -wangguiqi on 2019/8/6 -start
        var param = $.serializeToObject('#searchForm');
        if(param.type9){
            param.type9 = param.type9.join(",");
        }
        if(param.type10){
            param.type10 = param.type10.join(",");
        }
        if(param.type11){
            param.type11 = param.type11.join(",");
        }
        if(param.trmFundType){
            param.trmFundType = param.trmFundType.join(",");
        }
        //	查询条件换成资产二级分类多选项 -wangguiqi on 2019/8/6 -end
        return {
        	finprodId: finprodId,
        	finprodMarketId:finprodMarketId,
        	fundType: $('#fundType').val(),
        	ccy: $('#ccy').val(),
        	tradeMarket: $('#tradeMarket').val(),
        	manager: $('#manager').val(),
        	purSpeed: $("#purSpeed :selected").text(),
        	divideMethod: $('#divideMethod').val(),
        	carryoverFreq: $('#carryoverFreq').val(),
            purSpeed:$('#purSpeed').val(),
            redSpeed:$('#redSpeed').val()
            // startDate:$('#vdate').startDate(),
            // endDate:$('#vdate').endDate(),
            // type9: param.type9,
            // type10: param.type10,
            // type11: param.type11,
            // trmFundType:param.trmFundType
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
                    basePath + "valuation/basicdata/asset/fund/FinFundTarget002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId});
        },
        
        cols:[
             //{field:'finprodId', width:150 , title: 'FinFundTarget001.finprodId',visible: false}
            {field:'finprodMarketId', width:100 , title: 'FinFundTarget001.finprodMarketId'}
            //取资产简称 modified by lishuping 20191029 start
            ,{field:'finprodName', width:100 , title: 'FinFundTarget001.finprodName'}
            //取资产简称 modified by lishuping 20191029 end
            ,{field:'fundTypeStr', width:100 , sortField:"fundType", title: 'FinFundTarget001.fundType'}
            ,{field:'tradeMarketStr', width:150 , sortField:"tradeMarket",title: 'FinFundTarget001.tradeMarket'}
            ,{field:'calendarName', width:150 , sortField:"calendarId",title: 'FinFundTarget001.calendarId'}
            ,{field:'capiIncomeFeatureStr', width:150 , sortField:"capiIncomeFeature",title: 'FinFundTarget001.capiIncomeFeature'}
            ,{field:'carryoverFreqStr', width:150 , sortField:"carryoverFreq", title: 'FinFundTarget001.carryoverFreq'}
            ,{field:'manager', width:150 , sortField:"manager", title: 'FinFundTarget001.manager'}
            ,{field:'ccyStr', width:150 ,sortField:"ccy",title: 'FinFundTarget001.ccy'}
            ,{field:'dataSourceStr', width:150 ,sortField:"dataSource",title: 'FinFundTarget001.dataSource'}
            ,{field:'updateUserStr', width:150 ,sortField:"UPDATEUSER",title: 'FinFundTarget001.updateUser'}
            ,{field:'updateTimeStr', width:150 ,sortField:"UPDATETIME",title: 'FinFundTarget001.updateTime'}
            ,{field:'operation', width:150,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt',{clas: 'iconfont icon-copy',title: '复制'}]}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/fund/FinFundTarget001/check', data, function(result) {
        	Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/fund/FinFundTarget001/editCheck', data, function(editResult){
        		Joyin.dialog(JoyinUtil.getLabel("FinFundTarget002.edit"),
                        basePath + "valuation/basicdata/asset/fund/FinFundTarget002/init",
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
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/fund/FinFundTarget001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/fund/FinFundTarget001/delete', object, 
                        "FinFundTarget001_btn_delete", "FinFundTarget002",function() {
                    table.reload();
                });
            });
        });
    });

    /**
     * 绑定复制事件
     */
    table.bind('click', '.icon-copy', function(data, index) {
        data.pageModel = PAGE_MODEL_NEW;
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/fund/FinFundTarget001/check', data, function(result) {
                Joyin.dialog(JoyinUtil.getLabel("复制：基金类资产"),
                    basePath + "valuation/basicdata/asset/fund/FinFundTarget002/init",
                    "90%", "90%",data,function() {
                        table.reload();
                    });
            });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        // var paramObj = getSearchParams();
        // Joyin.exportExcel('export', paramObj);
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }

    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinFundTarget001_btn_search').click(function() {
            table.load();
            $('#FinFundTarget001_btn_delete').show();
        });
        $('#FinFundTarget001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinFundTarget001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinFundTarget002"),
                        basePath + "valuation/basicdata/asset/fund/FinFundTarget002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#FinFundTarget001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete",batchDelFlag:"Y"};
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/fund/FinFundTarget001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/fund/FinFundTarget001/delete', object, 
                            "FinFundTarget001_btn_delete","FinFundTarget002");
                });
            });
        });
        
    });

}());