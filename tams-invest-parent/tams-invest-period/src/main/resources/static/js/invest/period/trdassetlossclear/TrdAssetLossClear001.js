(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var obj =  $.serializeToObject('#searchForm');        
        var statusValid = $('#statusValid').val();
        obj.finprodMarketId = $('#finprodMarketId').val();
        obj.clearDateStart = $('#clearDate').startDate();
        obj.clearDateEnd = $('#clearDate').endDate();
        obj.statusValid = statusValid?statusValid.join(','):'';
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
            Joyin.dialog(JoyinUtil.getLabel("资产清理详细"),
                JoyinUtil.getRealPath("invest/period/trdassetlossclear/TrdAssetLossClear003/init"),
                "98%", "95%",data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols:[
             {field:'totleTradeId',sortField:'totleTradeId',title: 'TrdAssetLossClear001.totleTradeId', sortable: false, visible: false} //汇总交易编号
            ,{field:'finprodId',title: 'TrdAssetLossClear001.finprodId', sortField:'finprodId'}//资产代码
            ,{field:'finprodName',title:'TrdAssetLossClear001.finprodName', sortField:'finprodName'}//资产名称
            ,{field:'tradeDate',title:'TrdAssetLossClear001.tradeDate',type:'date', sortField:'tradeDate'}//清理日期
            ,{field:'shareAmt',sortField:'shareAmt',title:'TrdAssetLossClear001.shareAmt',type:'money'}//持仓份额
            ,{field:'intAmt',sortField:'intAmt',title:'TrdAssetLossClear001.intAmt',type:'money'}//持仓应收利息
            ,{field:'prinAmt',sortField:'prinAmt',title:'TrdAssetLossClear001.prinAmt',type:'money'}//持仓成本
            ,{field:'clearRecAmt',sortField:'clearRecAmt',title:'TrdAssetLossClear001.clearRecAmt',type:'money'}//已到期本息
            ,{field:'clearLossAmt',sortField:'clearLossAmt',title:'TrdAssetLossClear001.clearLossAmt',type:'money'}//当前账面减值余额
            ,{field:'portfolioId',sortField:'portfolioId',title:'TrdAssetLossClear002.portfolioId',visible: false}//当前账面减值余额
            ,{field:'penaltyIntAmt',sortField:'penaltyIntAmt',title:'TrdAssetLossClear002.penaltyIntAmt',type:'money'}//应收逾期罚息
        ]
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
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog() {
        var param = {};
        //新建模式除权登记
        param.type = "00";
        Joyin.dialog(JoyinUtil.getLabel('新建资产减值清理'), '../TrdAssetLossClear002/init', '80%', '80%',param);
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TrdAssetLossClear001_btn_add').click(showDialog);
        //导出
        $('#TrdAssetLossClear001_btn_export').on('click',function() {
            exportExcel();
        });
        //初始化查询
        $('#TrdAssetLossClear001_btn_search').click(function() {
            table.load();
            $('#TrdAssetLossClear001_btn_revoke').show();
        });
        $('#TrdAssetLossClear001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
//            勾选多条，或者不唯一时
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            var object = {};
            object.selectedItem = selArr; 
            object.totleTradeId =selArr[0].totleTradeId;
            object.tradeDate =selArr[0].tradeDate;
            object.operateType="revoke";
            object.mode="revoke";
            Joyin.ajaxJson(basePath + 'invest/period/trdassetlossclear/TrdAssetLossClear001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("ASSET_LOSS_CLEAR_001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
//                    Joyin.prompt(JoyinUtil.getLabel("撤销原因"),2,function(res){
//                        object.cancelRemark = res;
                        WorkFlow.doRevokeList(basePath + 'invest/period/trdassetlossclear/TrdAssetLossClear001/revoke', object, "TrdAssetLossClear001_btn_revoke", "TrdAssetLossClear003");
//                    },null,200);
                });
            });
        });
        
    });
}());