/**
 * ChannelAsset001 通道资产查询
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = $.serializeToObject('#searchForm');
        //获取查询条件金融产品代码
        var finprodId;
        var finprodIdTemp = param.secAcctId;
        if(finprodIdTemp)
        	finprodId=finprodIdTemp.join(",");
        param.finprodId = finprodId;
        //获取查询条件金融产品名称
        var finprodName;
        var finprodNameTemp = param.secAcctName;
        if(finprodNameTemp)
        	finprodName=finprodNameTemp.join(",");
        param.finprodName = finprodName;
        
        param.vdateStart = $('#searchForm').find("input[name='vdate']").startDate();//起息日
        param.vdateEnd = $('#searchForm').find("input[name='vdate']").endDate();//起息日
        param.mdateStart = $('#searchForm').find("input[name='mdate']").startDate();//起息日
        param.mdateEnd = $('#searchForm').find("input[name='mdate']").endDate()//起息日
        return param;
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
         * 定义表格双击事件
         */
            dblclick:function(table, data) {
                //在新建界面进行显示单行的详细信息
                Joyin.dialog(JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.editTitle"),
                        basePath + "invest/asset/channel/ChannelAsset002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,
                                        finprodId:data.finprodId,  //金融产品代码
                                        valDate:data.valDate,//估值日
                                        finprodMarketId:data.finprodMarketId//市场代码
                                        });
            },
        cols:[
        	// 调整表格列宽 by 赵东东 2019.10.29 start
             {field:'finprodId',width:200,title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.finprodIdBak");}(),visible:false}
            ,{field:'finprodMarketId',width:250,title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.finprodIdBak");}() ,sortField:'FINPROD_MARKET_ID'}
            ,{field:'finprodName',width:200,title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.finprodNameBak");}(),sortField:'FINPROD_NAME'}
            ,{field:'feeName',width:200,title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.feeName");}(),sortField:'FEE_NAME'}
            ,{field:'vdate',width:250,title: 'ChannelAsset002.vdate',type:'date'}
            ,{field:'mdate',width:250,title: 'ChannelAsset002.mdate',type:'date'}
            ,{field:'managerName',width:250,title: 'ChannelAsset002.manager',sortField:'MANAGER_NAME'}
            ,{field:'trusteeName',width:250,title: 'ChannelAsset002.trusteeId',sortField:'TRUSTEE_NAME'}
            // 调整表格列宽 by 赵东东 2019.10.29 end
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var object = data;//请求参数
        Joyin.ajaxJson(basePath + 'invest/asset/channel/ChannelAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.dialog(JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.editTitle"),
                    basePath + "invest/asset/channel/ChannelAsset002/init",//打开新界面
                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                                    finprodId:data.finprodId  //金融产品代码
                                    },function(){
                                        table.reload();//回调函数，表格刷新
                                    }); 
        });
        });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};//请求参数
        Joyin.ajaxJson(basePath + 'invest/asset/channel/ChannelAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'invest/asset/channel/ChannelAsset001/delete', object,"ChannelAsset001_btn_delete", "ChannelAsset002", function(){
                    table.reload();//删除事件请求及回调函数
                });
            });
        });
    });
    
    
    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("ChannelAsset002.title"), '../ChannelAsset002/init', '98%', '98%');
    }

    /**
     *弹出利率调整 
     */
    function showRateAdj(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        if (!selArr || selArr.length >1) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        var finprodId = selArr[0].finprodId;
        var finprodName = typeof selArr[0].finprodName == 'undefined'?"":selArr[0].finprodName;
        var finprodAbbr =  typeof selArr[0].finprodAbbr == 'undefined'?"":selArr[0].finprodAbbr; 
        var finprodType2 = selArr[0].finprodType2;
        var finprodMarketId = selArr[0].finprodMarketId;
        var object = selArr[0];
        object.rateType = '01';
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/check', object, function(
                result) {
            Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/isTransInAssetCheck', object, function(result2) {
                if(result2){
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0009'),function(){
                        AssetCommon.finalShowRateAdjTD(object,function(){
                            table.reload();
                        });
                    });
                }else{
                    AssetCommon.finalShowRateAdjTD(object,function(){
                        table.reload();
                    });
                }
            });
        });
        
    }
    
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
        $('#ChannelAsset001_btn_add').click(showDialog);
        $('#ChannelAsset001_btn_search').click(function() {
            table.load();
            $('#ChannelAsset001_btn_delete').show();
        });
        // 绑定按钮事件
        $('#ChannelAsset001_btn_export').click(function() {
            exportExcel();
        });
        
      //利率调整
        $("#ChannelAsset001_btn_rateAdj").click(function(){
            showRateAdj();
        });
        
        $('#ChannelAsset001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/channel/ChannelAsset001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/asset/channel/ChannelAsset001/delete', object, "ChannelAsset001_btn_delete", "ChannelAsset002", function(){
                        table.reload();//删除事件请求及回调函数
                    });
                });
            });
        });
    });
}());