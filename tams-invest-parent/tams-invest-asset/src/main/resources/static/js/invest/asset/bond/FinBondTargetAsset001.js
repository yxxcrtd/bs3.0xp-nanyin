(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#FinBondTargetAsset001_DIV");
        var param = $.serializeToObject('#searchForm');
        if(param.type4){
            param.type4 = param.type4.join(",");
        }
        //	查询条件换成资产二级分类多选项 -wangguiqi on 2019/7/18 -start
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
        param.vdateStart = objDiv.find("input[name='vdate']").startDate();//起息日
        param.vdateEnd = objDiv.find("input[name='vdate']").endDate();//起息日
        param.mdateStart = objDiv.find("input[name='mdate']").startDate();//起息日
        param.mdateEnd = objDiv.find("input[name='mdate']").endDate()//起息日
        return param;
    };
    
    //是否新增债券添加004控制  modify by liupeilin 2020-01-15  begin
    $(function() {
        if(BANK_CODE == '004'){           
            $('#isShowFileter').show();
        }
    });
    //是否新增债券添加004控制  modify by liupeilin 2020-01-15  end
    
    
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
                    basePath + "invest/asset/bond/FinBondTargetAsset002/init",
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
            ,{field:'finprodMarketId',  title: 'FinBondTargetAsset002.finprodId',sortField:'FINPROD_MARKET_ID',width:200}//资产代码
            ,{field:'finprodAbbr',  title: 'FinBondTargetAsset002.finprodAbbr',sortField:'FINPROD_ABBR',width:200}//资产简称
            ,{field:'vdate',title:'FinBondTargetAsset002.vdate',type:'date',width:150}//起息日
            ,{field:'mdate',title:'FinBondTargetAsset002.mdate',type:'date',width:150}//到期日
            ,{field:'ccyName',title:'FinBondTargetAsset002.ccy',sortField:'ccy',width:150}//币种
            ,{field:'couponSpeciesName',title:'FinBondTargetAsset002.couponSpecies',sortField:'COUPON_SPECIES',width:150}//息票
            ,{field:'intType',title:'FinBondTargetAsset002.rateType',visible:false,sortField:'rateType',width:150}//利率类型
            ,{field:'rateTypeName',title:'FinBondTargetAsset002.rateType',sortField:'rateType',width:150}//利率类型
            ,{field:'rate',title:'FinBondTargetAsset002.rate',type:'number',width:150}//票面利率
            //type4~5 changed to type9~12 by wangguiqi on 2019/7/18 -start
            ,{field:'type4Name',title:'FinBondTargetAsset002.type4',sortField:'TYPE_4',width:150, visible: false}//债券一级分类
            ,{field:'type5Name',title:'FinBondTargetAsset002.type5',sortField:'TYPE_5',width:150, visible: false}//债券二级分类
            ,{field:'type9Name',title:'FinBondTargetAsset002.type9',sortField:'TYPE_9',width:150}//资产二级分类
            ,{field:'type10Name',title:'FinBondTargetAsset002.type10',sortField:'TYPE_10',width:150}//资产三级分类
            ,{field:'type11Name',title:'FinBondTargetAsset002.type11',sortField:'TYPE_11',width:150,visible:IS_SHOW_TYPE == '01'?true:false}//资产四级分类
            ,{field:'type12Name',title:'FinBondTargetAsset002.type12',sortField:'TYPE_12',width:150,visible:IS_SHOW_TYPE == '01'?true:false}//资产五级分类
            //type4~5 changed to type9~12 by wangguiqi on 2019/7/18 -end
            ,{field:'indexIdName',title:'FinBondTargetAsset002.indexId',sortField:'indexId',type:'number',width:150}//挂钩利率
            ,{field:'coefficient',title:'FinBondTargetAsset002.coefficient',type:'number',width:150}//浮动系数
            ,{field:'spreadRate',title:'FinBondTargetAsset002.spreadRate',sortField:'SPREAD_RATE',type:'number',width:150}//利差
            ,{field:'basisName',title:'FinBondTargetAsset002.basis',sortField:'basis',width:150}//计息基础
            ,{field:'isSecBondName',title:'FinBondTargetAsset002.isSecBond',sortField:'IS_SEC_BOND',width:150}//是否次级债
            ,{field:'partyType2Name',title:'FinBondTargetAsset002.qyxz',sortField:'partyType2',width:150}//企业性质
            ,{field:'industryStr',title:'MstMarketInstitutions002.industry',sortField:'industry',width:150}//所属行业
            ,{field:'industry2Str',title:'MstMarketInstitutions002.industrySecondClass',sortField:'industry2',width:150}//行业二级分类
            ,{field:'tradeMarketName',title:'FinBondTargetAsset002.tradeMarket',sortField:'TRADE_MARKET',width:150}//交易场所
            ,{field:'idate',title:'FinBondTargetAsset002.idate',width:150,type:'date'}//发行日
            ,{field:'issuer', visible:false}//发行人
            ,{field:'issuerName',title:'FinBondTargetAsset002.issuer',width:150}//发行人
            ,{field:'registOrgName',title:'FinBondTargetAsset002.registOrg',width:150}//登记托管机构
            ,{field:'issueAmt',title:'FinBondTargetAsset001.issueAmt',width:150,type:'money',sortField:'ISSUE_AMT'}//发行金额
            ,{field:'payCycleStr',sortField:"payCycle",title:'FinBondTargetAsset002.payCycle',width:150}//付息频率
            ,{field:'ztGradeResultStr',sortable: false,title:'FinBondTargetAsset001.ztGradeResult',width:150}//主体评级结果
            ,{field:'ztGradeOrgIdStr',sortable: false,title:'FinBondTargetAsset001.ztGradeOrgId',width:150}//主体评级机构
            ,{field:'zxGradeResultStr',sortable: false,title:'FinBondTargetAsset001.zxGradeResult',width:150}//债项评级结果
            ,{field:'zxGradeOrgIdStr',sortable: false,title:'FinBondTargetAsset001.zxGradeOrgId',width:150}//债项评级机构
            ,{field:'exerciseDate',sortField:"exerciseDate",title:'FinBondTargetAsset001.exerciseDate',width:150,type:'date'}//行权日
            ,{field:'isSusStr',sortField:"IS_SUS",title:'FinBondTargetAsset002.isSus',width:150}//是否永续
            ,{field:'isRightStr',sortField:"IS_RIGHT",title:'FinBondTargetAsset002.isRight',width:150}//是否含权债
            ,{field:'isCityBondStr',sortField:"isCityBond",title:'FinBondTargetAsset002.isCityBond',width:150}//是否城投债
            ,{field:'isIssueAsset',sortField:"isIssueAsset",title:'FinBondTargetAsset002.isIssueAsset',width:150}//是否发行标的
           ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {selectedItem: [data]};//请求参数
        Joyin.ajaxJson(basePath + 'invest/asset/bond/FinBondTargetAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.ajax(basePath + 'invest/asset/bond/FinBondTargetAsset001/editCheck', data, function(editResult){
                Joyin.dialog(JoyinUtil.getLabel("FinBondTargetAsset002.editTitle"),
                        basePath + "invest/asset/bond/FinBondTargetAsset002/init",//打开新界面
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
        Joyin.ajaxJson(basePath + 'invest/asset/bond/FinBondTargetAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'invest/asset/bond/FinBondTargetAsset001/delete', object,"FinBondTargetAsset001_btn_delete", "FinBondTargetAsset002", function(){
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
        Joyin.dialog(JoyinUtil.getLabel('FinBondTargetAsset002.newTitle'), '../FinBondTargetAsset002/init', '98%', '98%');
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
      //利率调整
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
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/bond/FinBondTargetAsset001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/asset/bond/FinBondTargetAsset001/delete', object, "FinBondTargetAsset001_btn_delete", "FinBondTargetAsset002", function(){
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
