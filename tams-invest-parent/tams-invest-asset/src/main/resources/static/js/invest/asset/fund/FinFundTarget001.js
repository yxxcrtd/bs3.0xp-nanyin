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
        	/* type10 added by wangguiqi on 2019/7/22 -start */
        	type9: param.type9,
        	type10: param.type10,
        	type11: param.type11,
        	/* type10 added by wangguiqi on 2019/7/22 -end */
        	ccy: $('#ccy').val(),
        	tradeMarket: $('#tradeMarket').val(),
        	manager: $('#manager').val(),
        	purSpeed: $("#purSpeed :selected").text(),
        	divideMethod: $('#divideMethod').val(),
        	carryoverFreq: $('#carryoverFreq').val(),
        	startDate:$('#vdate').startDate(),
        	endDate:$('#vdate').endDate(),
            purSpeed:$('#purSpeed').val(),
            redSpeed:$('#redSpeed').val(),
            trmFundType:param.trmFundType
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
                    basePath + "invest/asset/fund/FinFundTarget002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId});
        },
        
        cols:[
             //{field:'finprodId', width:150 , title: 'FinFundTarget001.finprodId',visible: false}
            {field:'finprodMarketId', width:150 , title: 'FinFundTarget001.finprodMarketId'}
            ,{field:'finprodName', width:150 , title: 'FinFundTarget001.finprodName'}
            //取资产简称 modified by lishuping 20191029 start
            ,{field:'finprodAbbr', width:150 , title: 'FinFundTarget001.finprodAbbr', visible: false}
            //取资产简称 modified by lishuping 20191029 end
            ,{field:'fundTypeStr', width:150 , sortField:"fundType", title: 'FinFundTarget001.fundType', visible: false}//债券一级分类
            ,{field:'secondTypeStr', width:150 , sortField:"secondType",title: 'FinFundTarget001.secondType', visible: false}//债券二级分类
            //type4~5 changed to type9~12 by wangguiqi on 2019/7/18 -start
            ,{field:'type9Name', width:150 , sortField:"type9", title: 'FinFundTarget001.type9'}
            ,{field:'type10Name', width:150 , sortField:"type10",title: 'FinFundTarget001.type10'}
            ,{field:'type11Name', width:150 , sortField:"type11", title: 'FinFundTarget001.type11',visible:IS_SHOW_TYPE == '01'?true:false}
            ,{field:'type12Name', width:150 , sortField:"type12", title: 'FinFundTarget001.type12',visible:IS_SHOW_TYPE == '01'?true:false}
            //type4~5 changed to type9~12 by wangguiqi on 2019/7/18 -end
            ,{field:'tradeMarketStr', width:150 , sortField:"tradeMarket",title: 'FinFundTarget001.tradeMarket'}
            ,{field:'trmFundTypeStr', width:150 , sortField:"trmFundType",title: 'FinFundTarget002.trmFundType'}
            ,{field:'trmFundAbbr', width:150 , sortField:"trmFundAbbr",title: 'FinFundTarget002.trmFundAbbr'}
            ,{field:'manager', width:150 ,title: 'FinFundTarget001.manager'}           
            ,{field:'purSpeed', width:150 , title: 'FinFundTarget002.purSpeed'}
            ,{field:'redSpeed', width:150 , title: 'FinFundTarget002.redSpeed'}
            ,{field:'divideMethodStr', width:150 , sortField:"divideMethod", title: 'FinFundTarget001.divideMethod'}
            ,{field:'carryoverFreqStr', width:150 , sortField:"carryoverFreq", title: 'FinFundTarget001.carryoverFreq'}
            ,{field:'ccyStr', width:150 ,sortField:"ccy",title: 'FinFundTarget001.ccy'}
            ,{field:'vdateStr',width:150 , sortField:"vdate", title: 'FinFundTarget001.vdate',type:'date'}
            ,{field:'operation', width:100,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/asset/fund/FinFundTarget001/check', data, function(result) {
        	Joyin.ajaxJson(basePath + 'invest/asset/fund/FinFundTarget001/editCheck', data, function(editResult){
        		Joyin.dialog(JoyinUtil.getLabel("FinFundTarget002.edit"),
                        basePath + "invest/asset/fund/FinFundTarget002/init",
                        "95%", "90%",data,function() {
                                            table.reload();
                                        });
        	});
        	
        });
        /*Joyin.ajaxJson(basePath + 'invest/asset/depo/FinDepositTarget001/editCheck', data, function(result) {//请求路径，请求参数回调函数
            Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002.editTitle"),
                    basePath + "invest/asset/depo/FinDepositTarget002/init",//打开新界面
                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                                    finprodId:data.finprodId,  //金融产品代码
                                    valDate:data.valDate,//估值日
                                    finprodMarketId:data.finprodMarketId//市场代码
                                    },function(){
                                        table.reload();//回调函数，表格刷新
                                    });     
        })*/
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/asset/fund/FinFundTarget001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/asset/fund/FinFundTarget001/delete', object, 
                        "FinFundTarget001_btn_delete", "FinFundTarget002",function() {
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
        $('#FinFundTarget001_btn_search').click(function() {
            table.load();
            $('#FinFundTarget001_btn_delete').show();
        });
        $('#FinFundTarget001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinFundTarget001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinFundTarget002"),
                        basePath + "invest/asset/fund/FinFundTarget002/init",
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
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/fund/FinFundTarget001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/asset/fund/FinFundTarget001/delete', object, 
                            "FinFundTarget001_btn_delete","FinFundTarget002");
                });
            });
        });
        
    });
  //是否展示资产四五级分类
    if(IS_SHOW_TYPE == '01'){
    	$('#type11Div').show();
    }else{
    	$('#type11Div').hide();
    }
}());