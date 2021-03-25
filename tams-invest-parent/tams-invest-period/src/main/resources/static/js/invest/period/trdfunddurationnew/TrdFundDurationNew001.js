$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var obj =  $.serializeToObject('#searchForm');
        var statusValid = $('#statusValid').val();
        obj.exRightsDateStart = $('#exRightsDate').startDate();
        obj.exRightsDateEnd = $('#exRightsDate').endDate();
        obj.registDateStart = $('#registDate').startDate();
        obj.registDateEnd = $('#registDate').endDate();
        obj.statusValid = statusValid?statusValid.join(','):'';
        if ($("#chlId").val()) {
        	obj.chlId = $("#chlId").val().join(",");
        }
        return obj;
    };
    var getSearchParamsTwo = function() {
        var obj =  $.serializeToObject('#searchFormTwo');
        var statusValid = $('#statusValidTwo').val();
        obj.conversionType = $('#conversionType').val()
        obj.finprodMarketId = $('#finprodMarketIdTwo').val()
        obj.conversionTimeStart = $('#conversionTime').startDate();
        obj.conversionTimeEnd = $('#conversionTime').endDate();
        obj.statusValid = statusValid?statusValid.join(','):'';
        if ($("#chlId").val()) {
            obj.chlId = $("#chlId").val().join(",");
        }
        return obj;
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
        //双击查看
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdFundDurationNew001.reference"),
                JoyinUtil.getRealPath("invest/period/trdfunddurationnew/TrdFundDurationNew002/init"),
                "98%", "95%",data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        sortfont:true,
        cols:[
            {field:'statusValidStr',sortField:'statusValid',width:'8%',title: 'TrdFundDurationNew001.pageDataStatus'} //数据状态
            ,{field:'totleTradeId',width:'15%',title: 'TrdFundDurationNew001.busiCode'}//业务编号
            ,{field:'finprodMarketId',width:'15%',title:'TrdFundDurationNew001.finprodMarketId'}//资产代码
            ,{field:'finprodAbbr',width:'15%',title:'资产简称'}//资产简称
            ,{field:'chlName',width:'15%',title:'invest.channelId'}//通道
            ,{field:'assetTypeStr',sortField:'assetType',width:'15%',title:'TrdFundDurationNew001.finprodType'}//资产类型
            ,{field:'bonusModeStr',sortField:'bonusMode',width:'8%',title:'TrdFundDurationNew001.bonusMode'}//分红类型
            ,{field:'psStr',sortField:'ps',width:'8%',title:'TrdPlanNetDuration001.ps'}//交易方向
            ,{field:'registDate',width:'10%',title:'TrdFundDurationNew001.registDate',type:'date'}//登记日
            ,{field:'exRightsDate',width:'10%',title:'TrdFundDurationNew001.exRightsDate',type:'date'}//除权日
            ,{field:'arriveDate',width:'10%',title:'TrdFundDurationNew001.arriveDate',type:'date'}//到账日
            ,{field:'reInvesDate',width:'10%',title:'TrdFundDurationNew001.reInvesDate',type:'date'}//再投日
            ,{field:'registShare',width:'10%',title:'TrdFundDurationNew001.registShare',type:'money'}//登记份额
            ,{field:'tradeAmt',width:'10%',title:'TrdFundDurationNew001.tradeAmt',type:'money'}//交易金额
            ,{field:'qty',width:'10%',title:'TrdFundDurationNew001.qty',type:'money'}//交易净值
            // 多级法人改造 add by caiqun 20200615
            ,{field: 'institutionName', width:150, title: '机构名称',sortField:'institutionName',visible:AUTHORITY_TYPE=='02'}
            // 多级法人改造 add by caiqun 20200615
            ,{field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit']
                ,render: function (data, type, row, meta) {
                    if (row.updateFlag != '01')
                        return "";
                    else
                        //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 S
                        if(purviewElements.indexOf('_edit')!=-1){
                            return '<i class="btn layui-icon edit" title="确认">&#x1005;</i>';
                        }
                        //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 E
                }}
        ]
    });

    var tableTwo=$("#dataTableTwo").joyintable({
        ajax: {
            url: 'findPageObversion',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParamsTwo();//获取请求参数
            }//ajax请求数据
        },
        //双击查看
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("TrdFundObversion002.add"),
                JoyinUtil.getRealPath("invest/period/trdfundduration/TrdFundObversion002/init"),
                "98%", "95%",data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        sortfont:true,
        cols:[
            {field:'statusValidStr',sortField:'statusValid',width:'8%',title: 'TrdFundDurationNew001.pageDataStatus'} //数据状态
            ,{field:'totleTradeId',sortField:'totleTradeId',width:'15%',title: 'TrdFundDurationNew001.busiCode'}//业务编号
            ,{field:'finprodMarketId',sortField:'finprodMarketId',width:'10%',title:'TrdFundDurationNew001.finprodMarketId'}//资产代码
            ,{field:'finprodMarketName',sortField:'finprodMarketName',width:'10%',title:'TrdFundDurationNew001.finprodMarketName'}//资产名称
            ,{field:'conversionTypeStr',sortField:'conversionType',width:'10%',title:'TrdFundObversion001.conversionType'}//折算类型
            ,{field:'conversionTime',sortField:'conversionTime',width:'10%',title:'TrdFundObversion001.conversionTime',type:'date'}//份额折算日
            ,{field:'conversionRat',sortField:'conversionRat',width:'10%',title:'TrdFundObversion001.conversionRat',type:'money',digit: 8}//折算比例
            ,{field:'bconUnitCprice',sortField:'bconUnitCprice',width:'18%',title:'TrdFundObversion001.bconUnitCprice',type:'money',digit: 4}//单位分红净值
            ,{field:'afterUnitCprice',sortField:'afterUnitCprice',width:'18%',title:'TrdFundObversion001.afterUnitCprice',type:'money'}//交易金额
            // 多级法人改造 add by caiqun 20200615
            ,{field: 'institutionName', width:150, title: '机构名称',sortField:'institutionName',visible:AUTHORITY_TYPE=='02'}
            // 多级法人改造 add by caiqun 20200615
            ,{field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit'],
                render: function (data, type, row, meta) {
                    if (row.updateFlag != '01')
                        return "";
                    else
                        //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 S
                        if(purviewElements.indexOf('_edit')!=-1){
                            return '<i class="btn layui-icon edit" title="确认">&#x1005;</i>';
                        }
                        //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 E
                }}
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        //不为有效不可编辑
        if(data.statusValid != PAGE_DATA_STATUS_05){
            return Joyin.error(JoyinUtil.getMessage('FIN_ASSET_E_0004'));
        }
        var title = "TrdFundDurationNew001";
        //分场景设置标题
//        if(data.assetType == F01){
//            if(data.bonusMode == BONUS_MODE_01){
//                //货币现金分红
//                data.type = "06";
//                title="TrdFundDurationNew002.edit03";
//            }
//            else{
//                //货币红利再投
//                data.type = "01";
//                title="TrdFundDurationNew002.edit04";
//            }
//        }
//        else{
//            if(data.bonusMode == BONUS_MODE_01){
//                // 非货币现金分红
//                data.type = "03";
//                title="TrdFundDurationNew002.edit05";
//            }
//            else{
//                // 非货币红利再投
//                data.type = "04";
//                title="TrdFundDurationNew002.edit06";
//            }
//        }
        pageModel = PAGE_MODEL_EDIT;
        data.mode = 'edit';
        data.operateType = 'edit';
        data.pageModel = PAGE_MODEL_EDIT;
        //发起check
        Joyin.ajaxJson(basePath + 'invest/period/trdfunddurationnew/TrdFundDurationNew001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel(title),
                basePath + "invest/period/trdfunddurationnew/TrdFundDurationNew002/init",
                "95%", "90%", data,function(){table.reload();});

        });
    });
    /**
     * 绑定编辑事件
     */
    tableTwo.bind('click', '.edit', function(data, index) {
        //不为有效不可编辑
        if(data.statusValid != PAGE_DATA_STATUS_05){
            return Joyin.error(JoyinUtil.getMessage('FIN_ASSET_E_0004'));
        }
        var title;
        //分场景设置标题
        if(data.assetType == F01){
            if(data.bonusMode == BONUS_MODE_01){
                //货币现金分红
                data.type = "06";
                title="TrdFundDurationNew002.edit03";
            }
            else{
                //货币红利再投
                data.type = "01";
                title="TrdFundDurationNew002.edit04";
            }
        }
        else{
            if(data.bonusMode == BONUS_MODE_01){
                // 非货币现金分红
                data.type = "03";
                title="TrdFundDurationNew002.edit05";
            }
            else{
                // 非货币红利再投
                data.type = "04";
                title="TrdFundDurationNew002.edit06";
            }
        }
        pageModel = PAGE_MODEL_EDIT;
        data.mode = 'edit';
        data.operateType = 'edit'
        //发起check
        Joyin.ajaxJson(basePath + 'invest/period/trdfunddurationnew/TrdFundDurationNew001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel(title),
                basePath + "invest/period/trdfundduration/TrdFundObversion002/init",
                "95%", "90%", data,function(){table.reload();});

        });
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    var exportExcelTwo = function() {
        var paramObj = getSearchParamsTwo();//请求参数
        Joyin.exportExcel('exportConversion', paramObj);//导出（共通）
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $('#select2-conversionType-results').parent().parent().find('span').eq(0).addClass("select2-search--hide");
        // 绑定按钮事件
        $('#TrdFundDurationNew001_btn_add').on('click',function () {
            var param = {};
            //新建模式除权登记
            //param.type = "00";
            Joyin.dialog(JoyinUtil.getLabel('TrdFundDurationNew002.add'), '../TrdFundDurationNew002/init', '80%', '80%',param);
        });

        $('#TrdFundObversion001_btn_add').on('click',function(){
            var param = {};
            //新建模式除权登记
            param.type = "00";
            Joyin.dialog(JoyinUtil.getLabel('TrdFundObversion002.add'), '/invest/period/trdfundduration/TrdFundObversion002/init', '80%', '80%',param);
        });
        //导出
        $('.dvp-transfer-form #TrdFundDurationNew001_btn_export').on('click',function() {
            exportExcel();
        });
        $('.transfer-form #TrdFundDurationNew001_btn_export').on('click',function() {
            exportExcelTwo();
        });
        //初始化查询
        $('.dvp-transfer-form #TrdFundDurationNew001_btn_search').on('click',function () {
            table.load();
            $('#TrdFundDurationNew001_btn_revoke').show();
        });
        $('.transfer-form #TrdFundDurationNew001_btn_search').on('click',function () {
            if(!$('#conversionType').val()){
                Joyin.error('请先选择折算类型！');
                return;
            }
            tableTwo.load();
            $('#TrdFundObversion001_btn_revoke').show();
        });
        $('#TrdFundDurationNew001_btn_revoke').on('click',function () {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
//            勾选多条，或者不唯一时
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            if (selArr[0].statusValid != PAGE_DATA_STATUS_05) {
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0006"));
                return;
            }
            if (selArr[0].ps === PS_T11) {
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0032"));
                return;
            }
            var object = selArr[0];
            object.operateType="revoke";
            object.mode="revoke";
            Joyin.ajaxJson(basePath + 'invest/period/trdfunddurationnew/TrdFundDurationNew001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                    Joyin.prompt(JoyinUtil.getLabel("TrdDepoDuration001.revokeReason"),2,function(res){
                        object.cancelRemark = res;
                        WorkFlow.doRevokeList(basePath + 'invest/period/trdfunddurationnew/TrdFundDurationNew001/revoke', object, "TrdFundDurationNew001_btn_revoke", "TrdFundDurationNew002",function(){
                            table.reload();//回调事件请求及回调函数
                        });
                    },null,200);
                });
            });
        });
        $('#TrdFundObversion001_btn_revoke').on('click',function () {
            if(tableTwo!=null && tableTwo!=''){
                var selArr = tableTwo.getAllCheckedRows();
            }
//            勾选多条，或者不唯一时
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            if (selArr[0].statusValid != PAGE_DATA_STATUS_05) {
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0006"));
                return;
            }
            if (selArr[0].ps === PS_T11) {
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0032"));
                return;
            }
            var object = selArr[0];
            object.operateType="revoke";
            object.mode="revoke";
            Joyin.ajaxJson(basePath + 'invest/period/trdfundduration/TrdFundObversion001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                    Joyin.prompt(JoyinUtil.getLabel("TrdDepoDuration001.revokeReason"),2,function(res){
                        object.cancelRemark = res;
                        WorkFlow.doRevokeList(basePath + 'invest/period/trdfundduration/TrdFundObversion001/revoke', object, "TrdFundObversion001_btn_revoke", "TrdFundObversion002",function(){
                            tableTwo.reload();//回调事件请求及回调函数
                        });
                    },null,200);
                });
            });
        });
    });
}());