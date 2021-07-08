(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var obj =  $.serializeToObject('#searchForm');

        var secManageAcctId = $('#secManageAcctId').val();
        obj.secManageAcctId = secManageAcctId?secManageAcctId.join(','):'';
        var finprodMarketId = $('#finprodMarketId').val();
        obj.finprodMarketId = finprodMarketId?finprodMarketId.join(','):'';
        var chlId = $('#chlId').val();
        obj.chlId = chlId?chlId.join(','):'';
        var bonusMode = $('#bonusMode').val();
        obj.bonusMode = bonusMode?bonusMode.join(','):'';
        obj.registDateStart = $('#registDate').startDate();
        obj.registDateEnd = $('#registDate').endDate();
        obj.exRightsDateStart = $('#exRightsDate').startDate();
        obj.exRightsDateEnd = $('#exRightsDate').endDate();
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
            Joyin.dialog(JoyinUtil.getLabel("TrdStockConnectBonus001.reference"),
                JoyinUtil.getRealPath("invest/period/trdstockconnectbonus/TrdStockConnectBonus002/init"),
                "98%", "95%",data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols:[
            {field:'statusValidString',sortField:'statusValid',width:100,title: 'TrdStockConnectBonus001.pageDataStatus'} //数据状态
            ,{field:'busiCode',sortField:'busiCode',width:250,title: 'TrdStockConnectBonus001.businessCode'}//业务编号
            ,{field:'pTradeId',sortField:'pTradeId',width:150,visible:false}//原业务编号
            ,{field:'finprodMarketId',sortField:'finprodMarketId',width:150,title:'TrdStockConnectBonus001.finprodMarketId'}//资产代码
            ,{field:'finprodAbbr',sortField:'finprodAbbr',width:150,title:'TrdStockConnectBonus001.finprodMarketName'}//资产名称
            ,{field:'psText',sortField:'ps',width:150,title:'TrdStockConnectBonus001.ps'}//分红类型
            ,{field:'regDate',sortField:'regDate',width:150,title:'TrdStockConnectBonus001.regDate',type:'date'}//权益登记日
            ,{field:'tradeDate',sortField:'tradeDate',width:150,title:'TrdStockConnectBonus001.tradeDate',type:'date'}//除权除息日
            ,{field:'settleDate',sortField:'settleDate',width:150,title:'TrdStockConnectBonus001.settleDate',type:'date'}//派息日
            ,{field:'dividCcy',sortField:'dividCcy',width:150,title:'TrdStockConnectBonus001.dividCcy',visible:false}//派息币种
            ,{field:'dividCcyStr',sortField:'dividCcy',width:150,title:'TrdStockConnectBonus001.dividCcy'}//派息币种
            ,{field:'unitCprice',sortField:'unitCprice',width:150,title:'TrdStockConnectBonus001.unitCprice',type:'money',digit:8}//每股送转
            ,{field:'unitInt',sortField:'unitInt',width:150,title:'TrdStockConnectBonus001.unitInt',type:'money',digit:8}//每股派息
            ,{field:'regDateAmt',sortField:'regDateAmt',width:150,title:'TrdStockConnectBonus001.regDateAmt',type:'money',digit:0}//权益登记日股本
            ,{field:'replaceAmt',sortField:'replaceAmt',width:150,title:'TrdStockConnectBonus001.replaceAmt',type:'money',digit:0}//以股代息股本
            ,{field:'fpriceAmt',sortField:'fpriceAmt',width:150,title:'TrdStockConnectBonus001.fpriceAmt',type:'money',digit:2}//分红金额
            ,{field:'shareAmt',sortField:'shareAmt',width:150,title:'TrdStockConnectBonus001.shareAmt',type:'money',digit:0}//分红股本
            ,{field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit'],
                render: function (data, type, row, meta) {
                    if (row.statusValid != '01')
                        return "";
                    else
                    if(purviewElements.indexOf('_edit')!=-1){
                        return '<i class="btn layui-icon edit" title="确认">&#x1005;</i>';
                    }
                }
            }
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        //不为有效不可编辑
        if(data.statusValid != PAGE_DATA_STATUS01){
            return Joyin.error(JoyinUtil.getMessage('FIN_ASSET_E_0004'));
        }
        var title;
        data.mode = 'edit';
        data.operateType = 'edit';
        //发起check
        //Joyin.ajaxJson(basePath + 'invest/period/trdstockconnectbonus/TrdStockConnectBonus001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel(title),
                basePath + "invest/period/trdstockconnectbonus/TrdStockConnectBonus002/init",
                "95%", "90%", data,function(){table.reload();});

        //});
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数
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
        Joyin.dialog(JoyinUtil.getLabel('TrdStockConnectBonus002.add'), '../TrdStockConnectBonus002/init', '80%', '80%',param);
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TrdStockConnectBonus001_btn_add').click(showDialog);
         //导出
        $('#TrdStockConnectBonus001_btn_export').on('click',function() {
            exportExcel();
        });
        //初始化查询
        $('#TrdStockConnectBonus001_btn_search').click(function() {
            table.load();
            $('#TrdStockConnectBonus001_btn_revoke').show();
        });
        $('#TrdStockConnectBonus001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
//            勾选多条，或者不唯一时
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            //有效状态
            if (selArr[0].statusValid != PAGE_DATA_STATUS05) {
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0006"));
                return;
            }
            
            var object = {};
            object.busiCode = selArr[0].busiCode;
            object.operateType="revoke";
            object.mode="revoke";
            Joyin.ajaxJson(basePath + 'invest/period/trdstockconnectbonus/TrdStockConnectBonus001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                    Joyin.prompt(JoyinUtil.getLabel("TrdStockConnectBonus001.revokeReason"),2,function(res){
                        object.cancelRemark = res;
                        WorkFlow.doRevokeList(basePath + 'invest/period/trdstockconnectbonus/TrdStockConnectBonus001/revoke', object, "TrdStockConnectBonus001_btn_revoke", "TrdStockConnectBonus002");
                    },null,200);
                });
            });
        });
    });
    
}());