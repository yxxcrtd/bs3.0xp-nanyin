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
//        var statusValid = $('#statusValid').val();
//        obj.statusValid = statusValid?statusValid.join(','):'';
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
            Joyin.dialog(JoyinUtil.getLabel("TrdStockBonus001.reference"),
                JoyinUtil.getRealPath("invest/period/trdstockbonus/TrdStockBonus002/init"),
                "98%", "95%",data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols:[
            {field:'statusValidString',sortField:'statusValid',width:'8%',title: 'TrdStockBonus001.pageDataStatus'} //数据状态
            ,{field:'busiCode',sortField:'busiCode',width:'15%',title: 'TrdStockBonus001.businessCode'}//业务编号
            ,{field:'finprodMarketId',sortField:'FINPROD_MARKET_ID',width:'15%',title:'TrdStockBonus001.finprodMarketId'}//资产代码
            ,{field:'finprodAbbr',sortField:'FINPROD_ABBR',width:'15%',title:'TrdStockBonus001.finprodMarketName'}//资产名称
            //子交易包含多证券账户外层交易不做展示
            /*,{field:'secManageAcctId',sortField:'SEC_MANAGE_ACCT_ID',width:'15%',title:'TrdStockBonus001.secManageAcctId'}//证券账户*/
            /*,{field:'chlId',sortField:'CHL_ID',width:'15%',title:'invest.channelId'}//通道*/
            ,{field:'psText',sortField:'PS',width:'8%',title:'TrdStockBonus001.bonusMode'}//分红类型
            ,{field:'regDate',sortField:'REG_DATE',width:'10%',title:'TrdStockBonus001.registDate',type:'date'}//权益登记日
            ,{field:'tradeDate',sortField:'TRADE_DATE',width:'10%',title:'TrdStockBonus001.exRightsDate',type:'date'}//除权除息日
            ,{field:'settleDate',sortField:'SETTLE_DATE',width:'10%',title:'TrdStockBonus001.dividDate',type:'date'}//派息日
            ,{field:'unitCprice',sortField:'UNIT_CPRICE',width:'10%',title:'TrdStockBonus001.perTransfer'}//每股送转
            ,{field:'unitInt',sortField:'UNIT_INT',width:'10%',title:'TrdStockBonus001.perDividends'}//每股派息
            ,{field:'regDateAmt',sortField:'REG_DATE_AMT',width:'10%',title:'TrdStockBonus001.registDateShare',type:'money'}//权益登记日股本
            ,{field:'fpriceAmt',sortField:'FPRICE_AMT',width:'10%',title:'TrdStockBonus001.dividAmount',type:'money'}//分红金额
            ,{field:'shareAmt',sortField:'SHARE_AMT',width:'10%',title:'TrdStockBonus001.dividShare',type:'money'}//分红股本
            // 多级法人改造 add by caiqun 20200615
            ,{field: 'institutionName', width:150, title: '机构名称',sortField:'institutionName'}
            // 多级法人改造 add by caiqun 20200615
            ,{field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: []}
        ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        //不为有效不可编辑
        if(data.statusValid != TRADE_FLOW_STATUS_02){
            return Joyin.error(JoyinUtil.getMessage('FIN_ASSET_E_0004'));
        }
        var title;
        
        pageModel = PAGE_MODEL_EDIT;
        data.mode = 'edit';
        data.operateType = 'edit';
        //发起check
        Joyin.ajaxJson(basePath + 'invest/period/trdstockbonus/TrdStockBonus001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel(title),
                basePath + "invest/period/trdstockbonus/TrdStockBonus002/init",
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

    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
        var param = {};
        //新建模式除权登记
        param.type = "00";
        Joyin.dialog(JoyinUtil.getLabel('TrdStockBonus002.add'), '../TrdStockBonus002/init', '80%', '80%',param);
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TrdStockBonus001_btn_add').click(showDialog);
         //导出
        $('#TrdStockBonus001_btn_export').on('click',function() {
            exportExcel();
        });
        //初始化查询
        $('#TrdStockBonus001_btn_search').click(function() {
            table.load();
            $('#TrdStockBonus001_btn_revoke').show();
        });
        $('#TrdStockBonus001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
//            勾选多条，或者不唯一时
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            //有效状态
            if (selArr[0].statusValid != TRADE_FLOW_STATUS_02) {
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0006"));
                return;
            }
            
            var object = {};
            object.busiCode = selArr[0].busiCode;
            object.operateType="revoke";
            object.mode="revoke";
            Joyin.ajaxJson(basePath + 'invest/period/trdstockbonus/TrdStockBonus001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                    Joyin.prompt(JoyinUtil.getLabel("TrdStockBonus001.revokeReason"),2,function(res){
                        object.cancelRemark = res;
                        WorkFlow.doRevokeList(basePath + 'invest/period/trdstockbonus/TrdStockBonus001/revoke', object, "TrdStockBonus001_btn_revoke", "TrdStockBonus002");
                    },null,200);
                });
            });
        });
    });
    
}());