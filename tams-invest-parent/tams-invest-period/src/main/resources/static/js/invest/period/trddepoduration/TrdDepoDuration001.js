/**
 * 存款存续期
 */
$(function() {
    top.TrdDepoDuration001 = function(){
        return{
            getTable:function(){
                return table;
            }
        }
    }

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var object = $.serializeToObject('#searchForm');
        if($('#portfolioId').val() && $('#portfolioId').val() !=''){
            object.portfolioId = $('#portfolioId').val().join(',');
        }else{
            object.portfolioId = null;
        }
        if($('#statusValid').val() && $('#statusValid').val() !=''){
            object.statusValid = $('#statusValid').val().join(',');
        }else{
            object.statusValid = null;
        }
        object.mdateProdStart = $('#mdateProd').startDate();
        object.mdateProdEnd = $('#mdateProd').endDate();
        //add by yejiejie 2019-3-9 start
        object.tradeDateStart = $('#tradeDate').startDate();
        object.tradeDateEnd = $('#tradeDate').endDate();
        //add by yejiejie 2019-3-9 end
        if($('#finprodMarketId').val() && $('#finprodMarketId').val() !=''){
            object.finprodMarketId = $('#finprodMarketId').val().join(',');
        }else{
            object.finprodMarketId = null;
        }
        return object;
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
        searchOnInit :REM_TYPE && (REM_TYPE == 'R2S4'||REM_TYPE == 'R2S5'||REM_TYPE == 'R2S6'||REM_TYPE == 'R3S13'),
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            data.adjustDate = Date.ofDate(data.adjustDate).format();
            var url = "invest/period/trddepoduration/TrdDepoDuration002/init";

            if(data.assetType==DEPO_ASSET_TYPE_03){
                var url = "invest/period/trddepoduration/TrdDepoDuration004/init";

            }
            else{
                if(data.pingAnCodeSin != PA_TQZQ){
                    url = "invest/period/trddepoduration/TrdDepoDuration003/init";
                }
            }
            Joyin.dialog(JoyinUtil.getLabel("TrdDepoDuration001.reference"),
                JoyinUtil.getRealPath(url),
                "98%", "95%",data,function(){table.reload();});
        },
        sortfont:true,
        cols:[
            {field:'statusValidStr',width:'8%',sortField:"statusValid", title: 'TrdDepoDuration001.pageDataStatus'}
            ,{field:'busiCode',width:'12%', title: 'TrdDepoDuration001.busiCode'}
            , {field: 'channelName', width:130, title: 'invest.channelId', sortField:'channelName'}
            ,{field:'pingAnCodeStr',width:'8%', sortField:'cashType',title: 'TrdDepoDuration001.cashType'}
            ,{field:'finprodMarketId',width:'11%',sortField:'FINPROD_MARKET_ID', title: 'TrdDepoDuration001.finprodMarketId'}
            ,{field:'finprodMarketName',width:'15%', title: 'TrdDepoDuration001.finprodName'}
            ,{field:'assetTypeStr',width:'8%',sortField:'assetType', title: 'TrdDepoDuration001.assetType'}
            ,{field:'vdateProd', type:'date',title: 'TrdDepoDuration001.vdateProd'}
            ,{field:'mdateProd', type:'date',title: 'TrdDepoDuration001.mdateProd'}
            ,{field:'tradeDate', type:'date',title: 'TrdDepoDuration001.tradeDate'}
            ,{field:'remainPrinAmt',width:'20%', title: 'TrdDepoDuration001.remainPrinAmt',type:'money'}
            ,{field:'fpriceAmt',width:'20%', title: 'TrdDepoDuration001.fpriceAmt',type:'money'}
            ,{field:'pTradeId',  title: 'TrdDepoDuration001.pTradeId',visible:false} //金融产品代码
            ,{field:'tradeAmt',width:'20%', title: 'TrdDepoDuration001.tradeAmt',type:'money'}
            ,{field:'assetType',visible: false}
            ,{field:'assetType',visible: false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']
                ,render: function (data, type, row, meta) {
                //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 S
                if(purviewElements.indexOf('_edit')!=-1){
                    return '<i class="btn iconfont edit icon-circle-correct_fill" title="确认"></i>';
                }
                //modifiled by liupengfei for 多级法人改造 确认按钮权限控制 E
                }
            }
        ],
        progressBar: true
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        if(data.statusValid != PAGE_DATA_STATUS_01){
            return Joyin.error(JoyinUtil.getMessage('FIN_ASSET_E_0004'));
        }
        data.pageModel = PAGE_MODEL_NEW;
        data.opType = 'edit'
        data.adjustDate = Date.ofDate(data.adjustDate).format();
        Joyin.ajaxJson(basePath + 'invest/period/trddepoduration/TrdDepoDuration001/check',data, function(result) {
            var title = data.cashType==CASH_CLASS_TYPE_ZJSX ?"TrdDepoDuration001.midEdit":"TrdDepoDuration001.edit";
            Joyin.dialog(JoyinUtil.getLabel(title),
                basePath + "invest/period/trddepoduration/TrdDepoDuration003/init",
                "95%", "90%", data,function(){table.reload();});

        });
    });

    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams())
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TrdDepoDuration001_btn_search').click(function() {
            table.load();
            $('#TrdDepoDuration001_btn_revoke').show();
            $('#TrdDepoDuration001_btn_draw').show();
        });

        $('#TrdDepoDuration001_btn_export').click(function() {
            exportExcel();
        });
        $('#TrdDepoDuration001_btn_draw').click(function() {
            // if(table!=null && table!=''){
            //     var selArr = table.getAllCheckedRows();
            // }
            // if (!selArr || selArr.length != 1) {
            //     Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
            //     return;
            // }
            // if (selArr[0].assetType == assetType_tyjk) {
            //     Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0007"));
            //     return;
            // }
            var object = {};
            object.opType = "draw";
            object.pageModel = PAGE_MODEL_NEW;
            Joyin.dialog(JoyinUtil.getLabel("TrdDepoDuration001.drawTitle"),
                basePath + "invest/period/trddepoduration/TrdDepoDuration002/init",
                "95%", "90%", object, function () {
                    table.reload();
                });

        });
        $('#TrdDepoDuration001_btn_revoke').click(function() {
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
            var object = selArr[0];
            object.operateType="revoke";
            object.opType="revoke";
            object.mode="revoke";
            if(object && object.busiCode && object.busiCode != ''){
                object.pTradeId = object.busiCode;
                object.busiCode = object.busiCode + 'R';
            }
            Joyin.ajaxJson(basePath + 'invest/period/trddepoduration/TrdDepoDuration001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                    Joyin.prompt(JoyinUtil.getLabel("TrdDepoDuration001.revokeReason"),2,function(res){
                        object.cancelRemark = res;
                        WorkFlow.doRevokeList(basePath + 'invest/period/trddepoduration/TrdDepoDuration001/revoke', object, "TrdDepoDuration001_btn_revoke", "TrdDepoDuration002");
                    },null,200);
                });
            });
        });
        /**
         * 活期存放结息
         */
        $('#TrdDepoDuration001_btn_demand').click(function() {
            var object = {};
            object.opType = "demand";
            object.pageModel = PAGE_MODEL_NEW;
            Joyin.dialog(JoyinUtil.getLabel("TrdDepoDuration001.demandTitle"),
                basePath + "invest/period/trddepoduration/TrdDepoDuration004/init",
                "95%", "90%", object, function () {
                    table.reload();
                });
        });
    });
}());