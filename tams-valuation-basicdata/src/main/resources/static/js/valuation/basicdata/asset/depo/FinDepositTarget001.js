(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#FinDepositTarget001_DIV");
        var param = $.serializeToObject('#searchForm');
        param.vdateStart=objDiv.find("input[name='vdate']").startDate();//起息日
        param.vdateEnd=objDiv.find("input[name='vdate']").endDate();//起息日
        param.mdateStart=objDiv.find("input[name='mdate']").startDate();//起息日
        param.mdateEnd=objDiv.find("input[name='mdate']").endDate();//起息日
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
            Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002.title"),
                    basePath + "valuation/basicdata/asset/depo/FinDepositTarget002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    finprodId:data.finprodId,  //金融产品代码
                                    assetType:data.assetType
                                    });
        },
        /**
         * 定义表头
         */
        cols:[
             {field:'finprodId',  title: 'FinDepositTarget002.finprodId',visible:false} //金融产品代码
            ,{field:'finprodType',  title: 'FinDepositTarget002.finprodType2',visible:false} //金融产品代码
            ,{field:'finprodMarketId',  title: 'FinDepositTarget002.finprodId',sortField:'FINPRODMARKETID',width:200}//资产代码
            ,{field:'finprodName',  title: 'FinDepositTarget002.finprodName',sortField:'FINPRODNAME',width:200}//资产名称
            ,{field:'finprodAbbr',  title: 'FinDepositTarget002.finprodAbbr',sortField:'FINPRODABBR',visible:false}//资产简称
            ,{field:'assetTypeFmt',  title: 'FinDepositTarget002.assetType',sortField:'assetType',width:150}//资产类型
            ,{field:'capiIncomeFeatureFmt',  title: 'FinDepositTarget002.capiIncomeFeature',sortField:'CAPIINCOMEFEATURE',width:150}//本金收益特征
            ,{field:'issueAmt',  title: 'FinDepositTarget002.issueAmt',type:'number', sortField:'ISSUEAMT',width:150}//存款本金
            ,{field:'vdate',title:'FinDepositTarget002.vdate',type:'date',width:150}//起息日
            ,{field:'mdate',title:'FinDepositTarget002.mdate',type:'date',width:150}//到期日
            ,{field:'basisName',  title: 'FinDepositTarget002.basis', sortField:'BASIS',width:120}//计息基础
            ,{field:'intTypeFmt',  title: 'FinDepositTarget002.intType', sortField:'INTTYPE',width:120}//利率类型
            ,{field:'rate',title:'FinDepositTarget002.rate',type:'number',digit:'4',width:150}//最新利率
            ,{field:'dataSourceFmt',  title: 'FinDepositTarget002.dataSource', sortField:'DATASOURCE',width:120}//数据来源
            ,{field:'updateUserFmt',  title: 'FinBondTargetAsset001.updateUser',sortField:'UPDATEUSER',width:120}//更新人
            ,{field:'updateTimeFmt',  title: 'FinBondTargetAsset001.updateTime',sortField:'UPDATETIME',width:180}//更新时间
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
        data.operateType = "edit";
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget001/check', data, function(result) {//请求路径，请求参数回调函数
        	Joyin.ajax(basePath + 'valuation/basicdata/asset/ValAssetCommon/editCheck', data, function(editResult){
        		Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002.editTitle"),
                    basePath + "valuation/basicdata/asset/depo/FinDepositTarget002/init",//打开新界面
                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                                    finprodId:data.finprodId,  //金融产品代码
                                    valDate:data.valDate,//估值日
                                    finprodMarketId:data.finprodMarketId,//市场代码
                                    assetType:data.assetType
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
        var object = {selectedItem: [data], operateType:"delete",finprodMarketId:data.finprodMarketId};//请求参数
        Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget001/delete', object,"FinDepositTarget001_btn_delete", "FinDepositTarget002", function(){
                    table.reload();//删除事件请求及回调函数
                });
            });
        });
    });

     /**
     * 绑定复制事件
     */
    table.bind('click', '.icon-copy', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("存款类标的信息复制（ "+ "<span style='color:red'> * </span>" + "必填）"),
                   basePath + "valuation/basicdata/asset/depo/FinDepositTarget002/init",
                   "95%", "95%", {pageModel:PAGE_MODEL_NEW, finprodId: data.finprodId, finprodMarketId:data.finprodMarketId, operateType: "copy",copyFlag:YES_OR_NO_Y,assetType:data.assetType},function(){
                       table.reload();
           });
   });
    
    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        }
        else{
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
            object.rateType=rateType;
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
    	Joyin.dialog(JoyinUtil.getLabel('FinDepositTarget002.newTitle'), '../FinDepositTarget002/init', '98%', '98%')
    }

    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('#FinDepositTarget001_btn_add').click(showDialog);
        // 绑定导出事件
        $('#FinDepositTarget001_btn_export').click(function() {
            exportExcel();
        });
        //初始化查询
        $('#FinDepositTarget001_btn_search').click(function() {
            table.load();
            $('#FinDepositTarget001_btn_delete').show();
        });
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
            var object = {selectedItem: selArr, operateType:"delete",batchDelFlag:YES_OR_NO_Y};
            Joyin.ajaxJson(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/basicdata/asset/depo/FinDepositTarget001/delete', object, "FinDepositTarget001_btn_delete", "FinDepositTarget002", function(){
                        table.reload();//删除事件请求及回调函数
                    });
                });
            });
        });
    });

}());