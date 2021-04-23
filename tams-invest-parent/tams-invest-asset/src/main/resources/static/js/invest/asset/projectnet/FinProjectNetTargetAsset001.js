(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#FinProjectNetTargetAsset001_DIV");
        var param = $.serializeToObject('#submitForm');
        if(param.type6){
            param.type6 = param.type6.join(",");
        }
        // 项目类资产五级分类改造 add by renping 2019-07-25 start
        if(param.type9){
            param.type9 = param.type9.join(",");
        }
        if(param.type10){
            param.type10 = param.type10.join(",");
        }
        if(param.type11){
            param.type11 = param.type11.join(",");
        }
        if(param.type12){
            param.type12 = param.type12.join(",");
        }
        // 项目类资产五级分类改造 add by renping 2019-07-25 end
        param.finprodId = objDiv.find("input[name='finprodId']").val(),//资产代码
            param.ccy = objDiv.find("input[name='ccy']").val(),//资产代码
            param.vdateStart = objDiv.find("input[name='vdate']").startDate(),//起息日
            param.vdateEnd = objDiv.find("input[name='vdate']").endDate(),//起息日
            param.mdateStart = objDiv.find("input[name='mdate']").startDate(),//起息日
            param.mdateEnd = objDiv.find("input[name='mdate']").endDate(),//起息日
            param.financier = $('#financier').val()//融资人
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
            Joyin.dialog(JoyinUtil.getLabel("FinProjectNetTargetAsset002.title"),
                basePath + "invest/asset/projectnet/FinProjectNetTargetAsset002/init",
                "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,
                    finprodId:data.finprodId,  //金融产品代码
                    valDate:data.valDate,//估值日
                    finprodMarketId:data.finprodMarketId//市场代码
                });
        },
        searchOnInit :REM_TYPE && REM_TYPE == 'R7S2',
        /**
         * 定义表头
         */
        cols:[
            {field:'finprodId',  title: 'FinProjectTargetAsset001.finprodId',visible:false} //金融产品代码
            ,{field:'finprodMarketId',  title: 'FinProjectTargetAsset001.finprodId',sortField:'FINPROD_MARKET_ID',width:200}//资产代码
            ,{field:'finprodName',  title: 'FinProjectTargetAsset001.finprodName',sortField:'FINPROD_NAME',width:200}//资产名称
            ,{field:'finprodAbbr',  title: 'FinProjectTargetAsset001.finprodAbbr',width:200,visible:false}//资产简称
            // 项目类资产五级分类改造 modify by renping 2019-07-25 start
            ,{field:'type6Name',title:'FinProjectTargetAsset001.type6',sortField:'TYPE_6',width:150,visible:false}//资产类别
            ,{field:'type9Name',title:'资产二级类型',sortField:'TYPE_9',width:150}//资产二级类型
            ,{field:'type10Name',title:'资产三级类型',sortField:'TYPE_10',width:150}//资产三级类型
            ,{field:'type11Name',title:'FinProjectTargetAsset001.type11',sortField:'TYPE_11',width:150,visible:IS_SHOW_TYPE == '01'?true:false}//资产四级类型
            ,{field:'type12Name',title:'FinProjectTargetAsset001.type12',sortField:'TYPE_12',width:150,visible:IS_SHOW_TYPE == '01'?true:false}//资产五级类型
            // 项目类资产五级分类改造 modify by renping 2019-07-25 end
            ,{field:'ccyName',title:'FinProjectTargetAsset001.ccy',sortField:'ccy',width:150}//币种
            ,{field:'issueAmt',title:'FinProjectTargetAsset001.issueAmt',sortField:'ISSUE_AMT',width:150,type:'number'}//融资金额
            ,{field:'vdateStr',title:'FinProjectNetTargetAsset001.vdate',type:'date',width:150,sortField:'vdate'}//起息日
            ,{field:'mdateStr',title:'FinProjectNetTargetAsset001.mdate2',type:'date',width:150,sortField:'mdate'}//退出日
            ,{field:'financierName',title:'FinProjectTargetAsset002.financier',sortField:'financier',width:150}//融资人
            ,{field:'sponsorName',title:'FinProjectTargetAsset002.sponsor',sortField:'sponsor',width:150}//融资人
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {selectedItem: [data]};//请求参数
        Joyin.ajaxJson(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset001/check', object, function(result) {
            Joyin.ajaxJson(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset001/editCheck', data, function(editResult) {
                Joyin.dialog(JoyinUtil.getLabel("FinProjectNetTargetAsset002.editTitle"),
                    basePath + "invest/asset/projectnet/FinProjectNetTargetAsset002/init",//打开新界面
                    "98%", "98%", {
                        pageModel: PAGE_MODEL_EDIT,//请求的page_model
                        finprodId: data.finprodId,  //金融产品代码
                        finprodMarketId: data.finprodMarketId//市场代码
                    }, function () {
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
        Joyin.ajaxJson(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset001/delete', object,"FinProjectNetTargetAsset001_btn_delete", "FinProjectNetTargetAsset002", function(){
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
        Joyin.dialog(JoyinUtil.getLabel('FinProjectNetTargetAsset002.newTitle'), '../FinProjectNetTargetAsset002/init', '98%', '98%');
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinProjectNetTargetAsset001_btn_add').click(showDialog);
        //导出
        $('#FinProjectNetTargetAsset001_btn_export').click(exportExcel);
        //初始化查询
        $('#FinProjectNetTargetAsset001_btn_search').click(function() {
            table.load();
            $('#FinProjectNetTargetAsset001_btn_delete').show();
        });
        $('#FinProjectNetTargetAsset001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/asset/projectnet/FinProjectNetTargetAsset001/delete', object, "FinProjectNetTargetAsset001_btn_delete", "FinProjectNetTargetAsset002", function(){
                        table.reload();//删除事件请求及回调函数
                    });
                });
            });
        });
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
            $('#type11Div').show();
            $('#type12Div').show();
        }else{
            $('#type11Div').hide();
            $('#type12Div').hide();
        }
    });
}());