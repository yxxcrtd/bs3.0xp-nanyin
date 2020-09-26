(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#ValBondTargetAsset001_DIV");
        var param = $.serializeToObject('#searchForm');
        if(param.type4){
            param.type4 = param.type4.join(",");
        }
        //	查询条件换成资产二级分类多选项 -wangguiqi on 2019/7/18 -end
        param.vdateStart = objDiv.find("input[name='vdate']").startDate();//起息日
        param.vdateEnd = objDiv.find("input[name='vdate']").endDate();//起息日
        param.mdateStart = objDiv.find("input[name='mdate']").startDate();//起息日
        param.mdateEnd = objDiv.find("input[name='mdate']").endDate()//起息日
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
    /**
     * 定义表格双击事件
     */
        dblclick:function(table, data) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("查看债券类资产"),
                    basePath + "valuation/assets/bond/ValBondTargetAsset002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,
                                    finprodId:data.finprodId,  //金融产品代码
                                    valDate:data.valDate,//估值日
                                    finprodMarketId:data.finprodMarketId//市场代码
                                    });
        },
        /**
         * 定义表头
         */
        cols:[
        	{field:'finprodId',width:106,title: '资产代码', sort: true},
            {field:'bondId',width:106,title: '债券代码', sort: true},
            {field:'bondName',width:160,title: '债券简称', sort: true},
            {field:'vdate',width:94,type:'date',title: '起息日',sort: true},
            {field:'mdate',width:94,type:'date',title: '到期日',sort: true},
            {field:'couponTypeName',width:90, title: '息票品种', sortField:'couponType',sort: true},
            {field:'couponTypeCode', title: '息票品种', sort: true,visible:false},
            {field:'bondType1', width:100,title: '债券一级分类', sort: true, sortField:'bondTypeCode1'},
            {field:'bondType2', width:100,title: '债券二级分类', sort: true, sortField:'bondTypeCode2'},
            {field:'bondTypeCode1', title: '债券一级分类', sort: true,visible:false},
            {field:'bondTypeCode2', title: '债券二级分类', sort: true,visible:false},
            {field:'rate',width:90, align : "right", title: '当前利率(%)',sort: true},
            {field:'basis',width:140,title: '计息基础', sort: true},
            {field:'price',width:60, type:"money", title: '发行价格', sort: true},
            {field:'operation', title: 'SysCommon.operation', type:'action',width:80,buttons:['edit']}
        	
        	/*{field:'finprodMarketId',  title: '资产代码',sortField:'FINPROD_MARKET_ID',width:200}//资产代码
            ,{field:'finprodId',width:106,title: '债券代码', sort: true} //金融产品代码
            ,{field:'finprodAbbr',  title: '债券简称',sortField:'FINPROD_ABBR',width:200}//资产简称
            ,{field:'vdate',title:'起息日',type:'date',width:150}//起息日
            ,{field:'mdate',title:'到期日',type:'date',width:150}//到期日
            ,{field:'couponSpeciesName',title:'息票品种',sortField:'COUPON_SPECIES',width:150}//息票
            ,{field:'rate',title:'当前利率(%)',type:'number',width:150}//票面利率
            ,{field:'type4Name',title:'债券一级分类',sortField:'TYPE_4',width:150, visible: false}//债券一级分类
            ,{field:'type5Name',title:'债券二级分类',sortField:'TYPE_5',width:150, visible: false}//债券二级分类
            ,{field:'basisName',title:'计息基础',sortField:'basis',width:150}//计息基础
            ,{field:'price',width:60, type:"money", title: '发行价格', sort: true}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
*/        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {selectedItem: [data]};//请求参数
        Joyin.ajaxJson(basePath + 'valuation/assets/bond/ValBondTargetAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.dialog(JoyinUtil.getLabel("修改债券类资产"),
                    basePath + "valuation/assets/bond/ValBondTargetAsset002/init",//打开新界面
                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                                    finprodId:data.finprodId,  //金融产品代码
                                    finprodMarketId:data.finprodMarketId//市场代码
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
        Joyin.ajaxJson(basePath + 'valuation/assets/bond/ValBondTargetAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'valuation/assets/bond/ValBondTargetAsset001/delete', object,"ValBondTargetAsset001_btn_delete", "ValBondTargetAsset002", function(){
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
        Joyin.dialog(JoyinUtil.getLabel('债券资产类新建'), '../ValBondTargetAsset002/init', '98%', '98%');
    }

    /**
     *弹出利率调整 
     */
   /* function showRateAdj(){
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
        var rateType = selArr[0].intType;
        var object = selArr[0];
        object.type = "01"
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/check', object, function(
                result) {
            Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/isTransInAssetCheck', object, function(result2) {
                if(result2){
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
        });
        
    }*/
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValBondTargetAsset001_btn_add').click(showDialog);
        //导出
        $('#ValBondTargetAsset001_btn_export').click(exportExcel);
        //初始化查询
        $('#ValBondTargetAsset001_btn_search').click(function() {
            table.load();
            $('#ValBondTargetAsset001_btn_delete').show();
        });
      //利率调整
/*        $("#ValBondTargetAsset001_btn_rateAdj").click(function(){
            showRateAdj();
        });*/
        $('#ValBondTargetAsset001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'valuation/assets/bond/ValBondTargetAsset001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/assets/bond/ValBondTargetAsset001/delete', object, "ValBondTargetAsset001_btn_delete", "ValBondTargetAsset002", function(){
                        table.reload();//删除事件请求及回调函数
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
    });
}());
