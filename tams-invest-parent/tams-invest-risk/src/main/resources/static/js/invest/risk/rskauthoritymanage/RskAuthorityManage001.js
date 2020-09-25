/**
 * 风险授权管理
 */


$(function() {
    /**
     * 获取查询参数
     */
	var getSearchParams = function() {
		var userNameTemp = $('#userName').val();
        var userName= null;
        if(userNameTemp != null)
        	userName=userNameTemp.join(",");
        var tradeMarketTemp = $("#tradeMarket").val();
        var tradeMarket = null;
        if(tradeMarketTemp != null)
        	tradeMarket=tradeMarketTemp.join(",");
        var tradeDirectionTemp = $("#tradeDirection").val();
        var tradeDirection = null;
        if(tradeDirectionTemp != null)
        	tradeDirection=tradeDirectionTemp.join(",");
        var assetSecTypeTemp = $('#assetSecType').val();
        var assetSecType= null;
        if(assetSecTypeTemp != null)
        	assetSecType=assetSecTypeTemp.join(",");
        return {
        	rolePos: $('#rolePos').val(),
        	userName: userName,
        	assetType: $('#assetType').val(),
        	assetSecType: assetSecType,
        	tradeMarket: tradeMarket,
        	tradeDirection: tradeDirection,
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
                    basePath + "invest/risk/rskauthoritymanage/RskAuthorityManage002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,seqNo:data.seqNo});
        },
        
        cols:[
             {field:'seqNo', visible: false}
            ,{field:'rolePosStr', sortField:"rolePos", width:120 , title: 'RskAuthorityManage001.rolePos'}
            ,{field:'userName', width:120 , title: 'RskAuthorityManage001.userName'}
            ,{field:'assetTypeStr', width:180 , sortField:"assetType", title: 'RskAuthorityManage001.assetType'}
            ,{field:'assetSecTypeStr', width:180 , sortField:"assetSecType",title: 'RskAuthorityManage001.assetSecType'}
            ,{field:'tradeMarketStr', width:120 , sortField:"tradeMarket",title: 'RskAuthorityManage001.tradeMarket'}
            ,{field:'tradeDirectionStr', width:120 ,sortField:"tradeDirection",title: 'RskAuthorityManage001.tradeDirection'}           
            ,{field:'authorizeTypeStr' , width:120,sortField:"authorizeType",title: 'RskAuthorityManage001.authorizeType'}
            ,{field:'authorizeLimitAmt',sortField:"authorizeLimitAmt", width:180 , title: 'RskAuthorityManage001.authorizeLimitAmt'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/risk/rskauthoritymanage/RskAuthorityManage002/check', data, function(result) {
        		Joyin.dialog(JoyinUtil.getLabel("RskAuthorityManage002.edit"),
                        basePath + "invest/risk/rskauthoritymanage/RskAuthorityManage002/init",
                        "95%", "90%",data,function() {
                                            table.reload();
                                        });
        	
        });
        /*Joyin.ajaxJson(basePath + 'asset/depo/FinDepositTarget001/editCheck', data, function(result) {//请求路径，请求参数回调函数
            Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002.editTitle"),
                    basePath + "asset/depo/FinDepositTarget002/init",//打开新界面
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
        Joyin.ajaxJson(basePath + 'invest/risk/rskauthoritymanage/RskAuthorityManage001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/risk/rskauthoritymanage/RskAuthorityManage001/delete', object, 
                        "RskAuthorityManage001_btn_delete", "RskAuthorityManage002",function() {
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
        $('#RskAuthorityManage001_btn_search').click(function() {
            table.load();
            $('#RskAuthorityManage001_btn_delete').show();
        });
        $('#RskAuthorityManage001_btn_export').click(function() {
            exportExcel();
        });
        $('#RskAuthorityManage001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("RskAuthorityManage002"),
                        basePath + "invest/risk/rskauthoritymanage/RskAuthorityManage002/init",
                        "90%", "85%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#RskAuthorityManage001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/risk/rskauthoritymanage/RskAuthorityManage001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/risk/rskauthoritymanage/RskAuthorityManage001/delete', object, 
                            "RskAuthorityManage001_btn_delete","RskAuthorityManage002");
                });
            });
        });
        
    });
}());