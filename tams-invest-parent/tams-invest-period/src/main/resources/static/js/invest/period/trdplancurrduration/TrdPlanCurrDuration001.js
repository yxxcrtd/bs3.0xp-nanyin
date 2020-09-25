(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var obj =  $.serializeToObject('#searchForm');
        var statusValid = $('#statusValid').val();
        var portfolioId = $('#portfolioId').val();
        obj.exRightsDateStart = $('#exRightsDate').startDate();
        obj.exRightsDateEnd = $('#exRightsDate').endDate();
        obj.registDateStart = $('#registDate').startDate();
        obj.registDateEnd = $('#registDate').endDate();
        obj.statusValid = statusValid?statusValid.join(','):'';
        obj.portfolioId = portfolioId?portfolioId.join(','):'';
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
            Joyin.dialog(JoyinUtil.getLabel("TrdPlanCurrDuration001.reference"),
                JoyinUtil.getRealPath("invest/period/trdplancurrduration/TrdPlanCurrDuration002/init"),
                "98%", "95%",data,function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols:[
            {field:'statusValidStr',width:'8%',title: 'TrdPlanCurrDuration001.pageDataStatus'} //数据状态
            ,{field:'totleTradeId',width:'15%',title: 'TrdPlanCurrDuration001.busiCode',sortField:'TOTLE_TRADE_ID'}//业务编号
            ,{field:'finprodMarketId',width:'15%',title:'TrdPlanCurrDuration001.finprodMarketId'}//资产代码
            ,{field:'finprodMarketName',width:'15%',title:'TrdPlanCurrDuration001.finprodMarketName'}//资产名称
            ,{field:'bonusModeStr',width:'8%',title:'TrdPlanCurrDuration001.bonusMode'}//分红类型
            ,{field:'psStr',width:'8%',title:'TrdPlanNetDuration001.ps'}//交易方向
            ,{field:'registDate',width:'10%',title:'TrdPlanCurrDuration001.registDate',type:'date'}//登记日
            ,{field:'exRightsDate',width:'10%',title:'TrdPlanCurrDuration001.exRightsDate',type:'date'}//除权日
            ,{field:'arriveDate',width:'10%',title:'TrdPlanCurrDuration001.arriveDate',type:'date'}//到账日
            ,{field:'reInvesDate',width:'10%',title:'TrdPlanCurrDuration001.reInvesDate',type:'date'}//再投日
            ,{field:'registShare',width:'10%',title:'TrdPlanCurrDuration001.registShare',type:'money'}//登记份额
            // ,{field:'unitCprice',width:'10%',title:'TrdPlanCurrDuration001.unitCprice',type:'money'}//单位分红净值
            ,{field:'tradeAmt',width:'10%',title:'TrdPlanCurrDuration001.tradeAmt',type:'money'}//交易金额
            ,{field:'qty',width:'10%',title:'TrdPlanCurrDuration001.qty',type:'money'}//交易净值
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
        var title;
        data.type = "06";
        title="TrdPlanCurrDuration002.edit03";
        pageModel = PAGE_MODEL_EDIT;
        data.mode = 'edit';
        data.operateType = 'edit'
        //发起check
        Joyin.ajaxJson(basePath + 'invest/period/trdplancurrduration/TrdPlanCurrDuration001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel(title),
                basePath + "invest/period/trdplancurrduration/TrdPlanCurrDuration002/init",
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
        Joyin.dialog(JoyinUtil.getLabel('TrdPlanCurrDuration002.add'), '../TrdPlanCurrDuration002/init', '80%', '80%',param);
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TrdPlanCurrDuration001_btn_add').click(showDialog);
         //导出
        $('#TrdPlanCurrDuration001_btn_export').on('click',function() {
            exportExcel();
        });
        //初始化查询
        $('#TrdPlanCurrDuration001_btn_search').click(function() {
            table.load();
            $('#TrdPlanCurrDuration001_btn_revoke').show();
        });
        $('#TrdPlanCurrDuration001_btn_revoke').click(function() {
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
            Joyin.ajaxJson(basePath + 'invest/period/trdplancurrduration/TrdPlanCurrDuration001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                    Joyin.prompt(JoyinUtil.getLabel("TrdDepoDuration001.revokeReason"),2,function(res){
                        object.cancelRemark = res;
                        WorkFlow.doRevokeList(basePath + 'invest/period/trdplancurrduration/TrdPlanCurrDuration001/revoke', object, "TrdPlanCurrDuration001_btn_revoke", "TrdPlanCurrDuration002");
                    },null,200);
                });
            });
        });
    });
}());
