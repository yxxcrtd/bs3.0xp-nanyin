(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#FinBondTargetAsset001_DIV");
        var param = $.serializeToObject('#searchForm');
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
            Joyin.dialog(JoyinUtil.getLabel("FinBondTargetAsset002.title"),
                basePath + "valuation/basicdata/asset/bond/FinBondTargetAsset002/init",
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
            {field:'finprodId',  title: 'FinBondTargetAsset002.finprodId',visible:false} //金融产品代码
            ,{field:'finprodMarketId',  title: 'FinBondTargetAsset002.finprodId',sortField:'FINPROD_MARKET_ID',width:150}//资产代码
            ,{field:'finprodAbbr',  title: 'FinBondTargetAsset002.finprodAbbr',sortField:'FINPROD_ABBR',width:200}//资产简称
            ,{field:'vdate',title:'FinBondTargetAsset002.vdate',type:'date',width:130}//起息日
            ,{field:'mdate',title:'FinBondTargetAsset002.mdate',type:'date',width:130}//到期日
            ,{field:'couponSpeciesName',title:'FinBondTargetAsset002.couponSpecies',sortField:'COUPON_SPECIES',width:150}//息票
            ,{field:'accountintTypeFmt',title:'FinBondTargetAsset002.accountintType',sortField:'accountintType',width:150}//核算分类
            ,{field:'rate',title:'FinBondTargetAsset002.rate',type:'number',width:130}//当前利率(%)
            ,{field:'assetTypeFmt',title:'FinBondTargetAsset002.assetType',sortField:'ASSET_TYPE',width:150,visible:false}//资产分类
            ,{field:'basisName',title:'FinBondTargetAsset002.basis',sortField:'basis',width:130}//计息基础
            ,{field:'issuePrice',title:'FinBondTargetAsset002.issuePrice',width:150,type:'money'}//发行价格
            ,{field:'finprodName',  title: 'FinBondTargetAsset002.finprodName',width:200}//资产名称
            ,{field:'dataSourceFmt',  title: 'FinBondTargetAsset001.dataSource',sortField:'dataSource',width:120}//数据来源
            ,{field:'updateUserFmt',  title: 'FinBondTargetAsset001.updateUser',sortField:'UPDATE_USER',width:120}//更新人
            ,{field:'updateTimeFmt',  title: 'FinBondTargetAsset001.updateTime',sortField:'UPDATE_TIME',width:160}//更新时间
            ,{field:'operation', title: 'SysCommon.operation', type:'action', width:120,buttons: [
                    {text: '&#xe642;',clas: 'edit',title: '编辑'},
                    {clas: 'iconfont icon-copy',title: '复制'},
                    {text: '&#xe640;',clas: 'delt',title: '删除'}]
            }//编辑和删除事件
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {selectedItem: [data]};//请求参数
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.ajax(basePath + 'valuation/basicdata/asset/ValAssetCommon/editCheck', data, function(editResult){
                Joyin.dialog(JoyinUtil.getLabel("FinBondTargetAsset002.editTitle"),
                    basePath + "valuation/basicdata/asset/bond/FinBondTargetAsset002/init",//打开新界面
                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                        finprodId:data.finprodId,  //金融产品代码
                        finprodMarketId:data.finprodMarketId//市场代码
                    },function(){
                        table.reload();//回调函数，表格刷新
                    });
            });
        });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};//请求参数
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset001/delete', object,"FinBondTargetAsset001_btn_delete", "FinBondTargetAsset002", function(){
                    table.reload();//删除事件请求及回调函数
                });
            });
        });
    });

    /**
     * 绑定复制事件
     */
    table.bind('click', '.icon-copy', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("FinBondTargetAsset002.copyTitle"),
            basePath + "valuation/basicdata/asset/bond/FinBondTargetAsset002/init",//打开新界面
            "98%", "98%", {pageModel:PAGE_MODEL_NEW,//请求的page_model
                finprodId:data.finprodId,  //金融产品代码
                finprodMarketId:data.finprodMarketId,//市场代码
                copyFlag:YES_OR_NO_Y
            },function(){
                table.reload();//回调函数，表格刷新
            });
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {

        var selArr = [];
        var datas = null;
        if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

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
        var finprodType = selArr[0].finprodType;
        var vdateString = new Date(selArr[0].vdate).format('yyyy-MM-dd');
        var finprodMarketId = selArr[0].finprodMarketId;
        var rateType = selArr[0].intType;
        var object = selArr[0];
        object.type = "01";
        //modify by yanya on 20210322 添加起息日
        object.vdateString = vdateString;
        Joyin.ajaxJson(basePath + 'valuation/component/asset/FinAssetRateAdj001/check', object, function(result) {
            Joyin.ajaxJson(basePath + 'valuation/component/asset/FinAssetRateAdj001/isTransInAssetCheck', object, function(result2) {
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

    }

    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('FinBondTargetAsset002.newTitle'), '../FinBondTargetAsset002/init', '98%', '98%');
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinBondTargetAsset001_btn_add').click(showDialog);
        //导出
        $('#FinBondTargetAsset001_btn_export').click(exportExcel);
        //初始化查询
        $('#FinBondTargetAsset001_btn_search').click(function() {
            table.load();
            $('#FinBondTargetAsset001_btn_delete').show();
        });
        //modify by yanya on 20210391 增加【利率调整】按钮
        $("#FinBondTargetAsset001_btn_rateAdj").click(function(){
            showRateAdj();
        });
        $('#FinBondTargetAsset001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete", batchDelFlag:YES_OR_NO_Y};
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/bond/FinBondTargetAsset001/delete', object, "FinBondTargetAsset001_btn_delete", "FinBondTargetAsset002", function(){
                        table.reload();//删除事件请求及回调函数
                    });
                });
            });
        });
    });
}());
