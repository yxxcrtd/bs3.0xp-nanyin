(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#FinDepositTarget001_DIV");
        //	查询条件换成资产二级分类多选项 -wangguiqi on 2019/7/18 -start
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
        //	查询条件换成资产二级分类多选项 -wangguiqi on 2019/7/18 -end
        return {
            /*finprodId: $('#finprodId_hidden').val(),//资产代码
*/          finprodId:objDiv.find("input[name='finprodId']").val(),//资产代码
            type6: objDiv.find("input[name='type6']").val(),//资产类型
        	/* type9~11 added by wangguiqi on 2019/8/1 -start */
        	type9: param.type9,
        	type10: param.type10,
        	type11: param.type11,
        	/* type9~11 added by wangguiqi on 2019/8/1 -end */
            ccy : objDiv.find("input[name='ccy']").val(),//币种
            vdateStart : objDiv.find("input[name='vdate']").startDate(),//起息日
            vdateEnd : objDiv.find("input[name='vdate']").endDate(),//起息日
            mdateStart : objDiv.find("input[name='mdate']").startDate(),//起息日
            mdateEnd : objDiv.find("input[name='mdate']").endDate(),//起息日
            issuer:$("#issuer").val()//存款机构
        };
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
    /**
     * 定义表格双击事件
     */
        dblclick:function(table, data) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002.title"),
                    basePath + "invest/asset/depo/FinDepositTarget002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    finprodId:data.finprodId  //金融产品代码
                                    });
        },
        /**
         * 定义表头
         */
        cols:[
             {field:'finprodId',  title: 'FinDepositTarget002.finprodId',visible:false} //金融产品代码
            ,{field:'finprodType2',  title: 'FinDepositTarget002.finprodType2',visible:false} //金融产品代码
            ,{field:'finprodMarketId',  title: 'FinDepositTarget002.finprodId',sortField:'FINPROD_MARKET_ID'}//资产代码
            ,{field:'finprodName',  title: 'FinDepositTarget002.finprodName',sortField:'FINPROD_NAME'}//资产名称
            ,{field:'finprodAbbr',  title: 'FinDepositTarget002.finprodAbbr',sortField:'FINPROD_ABBR',visible:false}//资产简称
            ,{field:'type6Name',title:'FinDepositTarget002.type6', visible:false}//隐藏资产类型 赵东东 2019.7.30
            // 增加推荐方与推荐费率 add by renping 20191014 start
            ,{field:'recommenderName',  title: 'FinDepositTarget002.recommender', sortField:'recommender'}//存款机构
            // 增加推荐方与推荐费率 add by renping 20191014 start
            ,{field:'sponsorName',  title: 'FinDepositTarget002.sponsor', sortField:'sponsor'}
            ,{field:'issuerName',  title: 'FinDepositTarget002.issuer', sortField:'issuer'}
            ,{field:'issueAmt',  title: 'FinDepositTarget002.issueAmt',type:'number', sortField:'ISSUE_AMT'}//存款本金
            ,{field:'vdate',title:'FinDepositTarget002.vdate',type:'date'}//起息日
            ,{field:'mdate',title:'FinDepositTarget002.mdate',type:'date'}//到期日
            ,{field:'ccyName',title:'FinDepositTarget002.ccy', sortField:'ccy'}//币种
            ,{field:'rate',title:'FinDepositTarget002.rate',type:'number',digit:'4'}//利率
            // 表头增加行内资产二级、三级、四级、五级分类 赵东东 2019.7.23 start
            ,{field:'type9Name',title:'FinDepositTarget002.type9',sortField:'TYPE_9'}//赵东东2019.7.20行内资产二级分类
            ,{field:'type10Name',title:'FinDepositTarget002.type10',sortField:'TYPE_10'}//赵东东2019.7.20行内资产三级分类
            ,{field:'type11Name',title:'FinDepositTarget002.type11',sortField:'TYPE_11',visible:IS_SHOW_TYPE == '01'?true:false}//赵东东2019.7.20行内资产四级分类
            ,{field:'type12Name',title:'FinDepositTarget002.type12',sortField:'TYPE_12',visible:IS_SHOW_TYPE == '01'?true:false}//赵东东2019.7.20行内资产五级分类
            // 表头增加行内资产二级、三级、四级、五级分类 赵东东 2019.7.23 end
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/asset/depo/FinDepositTarget001/editCheck', data, function(result) {//请求路径，请求参数回调函数
        	Joyin.ajax(basePath + 'invest/asset/common/editCheck', data, function(editResult){
        		Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002.editTitle"),
                    basePath + "invest/asset/depo/FinDepositTarget002/init",//打开新界面
                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                                    finprodId:data.finprodId,  //金融产品代码
                                    valDate:data.valDate,//估值日
                                    finprodMarketId:data.finprodMarketId//市场代码
                                    },function(){
                                        table.reload();//回调函数，表格刷新
                                    });   
        	});
        })
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};//请求参数
        Joyin.ajaxJson(basePath + 'invest/asset/depo/FinDepositTarget001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'invest/asset/depo/FinDepositTarget001/delete', object,"FinDepositTarget001_btn_delete", "FinDepositTarget002", function(){
                    table.reload();//删除事件请求及回调函数
                });
            });
        });
    });
    
    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };
    
    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
    	Joyin.dialog(JoyinUtil.getLabel('FinDepositTarget002.newTitle'), '../FinDepositTarget002/init', '98%', '98%')
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
        var object = {finprodId:finprodId,finprodName:finprodName//金融产品名称
                ,finprodType2:finprodType2//金融产品类型
                ,finprodAbbr:finprodAbbr
                ,finprodMarketId:finprodMarketId
                ,rateType:'01'};
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/isTransInAssetCheck', object, function(result) {
            if(result){
                Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0009'),function(){
                    AssetCommon.finalShowRateAdj(object,function(){
                        table.reload();
                    });
                });
            }else{
                AssetCommon.finalShowRateAdj(object,function(){
                    table.reload();
                });
            }
        });
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#FinDepositTarget001_btn_add').click(showDialog);
        //导出
        $('#FinDepositTarget001_btn_export').click(exportExcel);
        //初始化查询
        $('#FinDepositTarget001_btn_search').click(function() {
            table.load();
            $('#FinDepositTarget001_btn_delete').show();
        });
        //利率调整
        $("#FinDepositTarget001_btn_rateAdj").click(function(){
            showRateAdj();
        });
        $('#FinDepositTarget001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/depo/FinDepositTarget001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/asset/depo/FinDepositTarget001/delete', object, "FinDepositTarget001_btn_delete", "FinDepositTarget002", function(){
                        table.reload();//删除事件请求及回调函数
                    });
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