(function() {
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
        if($("#arriveDate").val()){
            obj.arriveDateStart = $('#arriveDate').startDate();
            obj.arriveDateEnd = $('#arriveDate').endDate();
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
            Joyin.dialog(JoyinUtil.getLabel("TrdProjectNetDuration001.reference"),
                JoyinUtil.getRealPath("invest/period/trdprojnetduration/TrdProjectNetDuration002/init"),
                "98%", "95%",data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols:[
            {field:'statusValidStr',sortField:'statusValid',width:'10%',title: 'TrdProjectNetDuration001.pageDataStatus',align:'right', sortable: false} //金融产品代码
            ,{field:'totleTradeId',width:'20%',title: 'TrdProjectNetDuration001.busiCode',align:'right', sortField:'TOTLE_TRADE_ID'}
            ,{field:'finprodMarketId',width:'15%',title:'TrdProjectNetDuration001.finprodMarketId', sortField:'FINPROD_MARKET_ID'}//起息日
            ,{field:'finprodMarketName',width:'15%',title:'TrdProjectNetDuration001.finprodMarketName',type:'date', sortField:'finprodMarketName'}//到期日
            ,{field:'assetTypeName',width:'15%',title:'一级分类', sortField:'assetType', visible:false}//到期日
            ,{field:'bonusModeStr',sortField:'bonusMode',width:'15%',title:'TrdProjectNetDuration001.bonusMode'}//债券一级分类
            ,{field:'psStr',sortField:'ps',width:'10%',title:'TrdProjectNetDuration001.ps'}
            ,{field:'registDate',width:'10%',title:'TrdProjectNetDuration001.registDate',type:'date', sortField:'registDate'}//债券二级分类
            ,{field:'exRightsDate',width:'10%',title:'TrdProjectNetDuration001.exRightsDate',type:'date', sortField:'exRightsDate'}//交易场所
            ,{field:'arriveDate',width:'10%',title:'TrdProjectNetDuration001.arriveDate',type:'date', sortField:'arriveDate'}//交易场所
            /*,{field:'reInvesDate',width:'10%',title:'TrdProjectNetDuration001.reInvesDate',type:'date', sortField:'reInvesDate'}//交易场所*/
            ,{field:'qty',width:'10%',title:'TrdProjectNetDuration001.registShare',type:'money', sortField:'qty'}//交易场所
            // 修改单位分红净值查询结果精度不够 by 赵东东 20191213
            ,{field:'unitCprice',width:'10%',title:'TrdProjectNetDuration001.unitCprice',type:'money',digit:4,sortField:'UNIT_CPRICE'}//交易场所
            ,{field:'tradeAmt',width:'10%',title:'TrdProjectNetDuration001.tradeAmt',type:'money', sortField:'tradeAmt'}//交易场所
            ,{field:'fpriceAmt',width:'10%',title:'到账金额',type:'money', sortField:'fpriceAmt'}//交易场所
            /*,{field:'qty',width:'10%',title:'TrdProjectNetDuration001.qty',type:'money', sortField:'qty'}//交易场所*/
            , {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit']
                ,
                render: function (data, type, row, meta) {
                    if (row.updateFlag != '01')
                        return "";
                    else
                        return '<i class="btn layui-icon edit" title="确认">&#x1005;</i>';
                }
            }//编辑和删除事件
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        if(data.statusValid != PAGE_DATA_STATUS_05){
            return Joyin.error(JoyinUtil.getMessage('FIN_ASSET_E_0004'));
        }
        var title;
        if(data.bonusMode == BONUS_MODE_01){
            //非货币现金分红
            data.type = "03";
            title="TrdProjectNetDuration002.edit03";
        }
        else{
            //非货币红利再投
            data.type = "04";
            title="TrdProjectNetDuration002.edit04";
        }
        pageModel = PAGE_MODEL_EDIT;
        data.mode = 'edit';
        data.operateType = 'edit'
        Joyin.ajaxJson(basePath + 'invest/period/trdprojnetduration/TrdProjectNetDuration001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel(title),
                basePath + "invest/period/trdprojnetduration/TrdProjectNetDuration002/init",
                "95%", "90%", data,function(){table.reload();});

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
        var param = {};
        //新建模式除权登记
        param.type = "00";
        Joyin.dialog(JoyinUtil.getLabel('TrdProjectNetDuration002.add'), '../TrdProjectNetDuration002/init', '80%', '80%',param);
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TrdProjectNetDuration001_btn_add').click(showDialog);
        //导出
        $('#TrdProjectNetDuration001_btn_export').on('click',function() {
            exportExcel();
        });
        //初始化查询
        $('#TrdProjectNetDuration001_btn_search').click(function() {
            table.load();
            $('#TrdProjectNetDuration001_btn_revoke').show();
        });
        $('#TrdProjectNetDuration001_btn_revoke').click(function() {
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
            Joyin.ajaxJson(basePath + 'invest/period/trdprojnetduration/TrdProjectNetDuration001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                    Joyin.prompt(JoyinUtil.getLabel("TrdDepoDuration001.revokeReason"),2,function(res){
                        object.cancelRemark = res;
                        Joyin.ajax(basePath + 'invest/period/trdprojnetduration/TrdProjectNetDuration001/findAssetIntInfo', object, function (resultlist) {
                            object.finAssetIntInfoList = resultlist;
                        });
                        WorkFlow.doRevokeList(basePath + 'invest/period/trdprojnetduration/TrdProjectNetDuration001/revoke', object, "TrdProjectNetDuration001_btn_revoke", "TrdProjectNetDuration002",function(){
                            table.reload();
                        });
                    },null,200);
                });
            });
        });
    });
}());