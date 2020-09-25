(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var obj =  $.serializeToObject('#searchForm');
        //console.log(obj);
        if($('#portfolioId').val() && $('#portfolioId').val() !=''){
            obj.portfolioId = $('#portfolioId').val().join(',');
        }else{
            obj.portfolioId = null;
        }
        var excPs = $('#excPs').val();
        obj.ps = excPs;
        var targetId = $('#targetId').val();
        obj.targetId = targetId;
        var hylx = $('#assetType11').val();
        obj.hylx = hylx;
        var exerciseType = $('#exerciseType').val();
        obj.exerciseType = exerciseType;
        obj.cDateStart = $("#mDate").startDate();
        obj.cDateEnd = $('#mDate').endDate();
        obj.xqzt = $('#xqzt').val();
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
            if("放弃行权" == data.xqzt){
                Joyin.dialog(JoyinUtil.getLabel("期权存续期查看"),
                    JoyinUtil.getRealPath("invest/period/trdoptionduration/TrdOptionDuration003/init"),
                    "98%", "95%",data,function(){table.reload();});
            }else{
                Joyin.dialog(JoyinUtil.getLabel("期权存续期查看"),
                    JoyinUtil.getRealPath("invest/period/trdoptionduration/TrdOptionDuration002/init"),
                    "98%", "95%",data,function(){table.reload();});
            }

        },
        /**
         * 定义表头
         */
        cols:[
            {field:'statusValidStr',sortField:'statusValid',width:'10%',title: 'TrdOptionDuration001.pageDataStatus'} //数据状态
            ,{field:'busiCode',sortField:'busiCode',width:'15%',title: 'TrdOptionDuration001.businessCode'}//业务编号
            ,{field:'xqzt',sortField:'FINPROD_MARKET_ID',width:'15%',title:'TrdOptionDuration001.xqzt'}//行权状态
            ,{field:'portfolioName',sortField:'PORTFOLIO_NAME',width:'15%',title:'TrdOptionDuration001.portfolioName'}//资产名称
            //子交易包含多证券账户外层交易不做展示
            ,{field:'finprodId',sortField:'FINPROD_ID',width:'15%',title:'TrdOptionDuration001.finprodId',visible: false}//金融代码
            ,{field:'excPsStr',sortField:'EXC_PS',width:'10%',title:'TrdOptionDuration001.excPs'}//交易方向
            ,{field:'assetType11Str',sortField:'assetType11',width:'10%',title:'TrdOptionDuration001.assetType11'}//合约类型
            ,{field:'targetId',sortField:'targetId',width:'10%',title:'TrdOptionDuration001.targetId'}//合约标的
            ,{field:'exerciseTypeStr',sortField:'exerciseType',width:'10%',title:'TrdOptionDuration001.exerciseType'}//行权方式
            ,{field:'cDateStr',sortField:'cDate',width:'10%',title:'交易日'}//交易日
            ,{field:'xqDate',sortField:'xqDate',width:'10%',title:'行权日', type: 'date'}//行权日
            ,{field:'mdateStr',sortField:'mdate',width:'10%',title:'到期日'}//到期日
            ,{field:'sffxStr',sortField:'sffx',width:'10%',title:'收付方向'}//收付方向
            ,{field:'tradeAmt',sortField:'tradeAmt',width:'10%',title:'到期金额'}//到期金额
            ,{field:'optFeePayType',sortField:'optFeePayType',width:'10%',visible: false}//期权费支付类型
            ,{field:'sysDate',sortField:'sysDate',width:'10%',visible: false}//平台日
            ,{field:'workflowId', visible: false}//工作流代码
            ,{field:'workflowStatus', visible: false}
            ,{field:'statusValid', visible: false}
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
        Joyin.ajaxJson(basePath + 'invest/period/trdOptionDuration/TrdOptionDuration001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel(title),
                basePath + "invest/period/trdOptionDuration/TrdOptionDuration002/init",
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

    function showDialogdq() {
        //新建模式
        var rows = table.getAllCheckedRows();
        if (rows.length != 1) {
            Joyin.error(JoyinUtil.getMessage('请选择一条数据'));
            return;
        }
        var object = rows[0];
        object.type = "00";
        object.xqType = "DQXQ";

        if(object.statusValidStr == '流程中' ){
            Joyin.warn(JoyinUtil.getMessage('流程中不支持到期行权'));
            return;
        }
        if(object.statusValidStr == '有效' ){
            Joyin.warn(JoyinUtil.getMessage('已生效数据不支持到期行权'));
            return;
        }
        if(object.mdateStr != object.sysDate ){
            Joyin.warn(JoyinUtil.getMessage('未到到期日'));
            return;
        }
        //发起check
        Joyin.ajaxJson(basePath + 'invest/period/trdoptionduration/TrdOptionDuration002/check', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel('期权到期'),
                '../TrdOptionDuration002/init',
                '80%', '90%',object,function(){table.reload();});
        });
    }

    function showDialogtq() {
        //新建模式
        var rows = table.getAllCheckedRows();
        if (rows.length != 1) {
            Joyin.error(JoyinUtil.getMessage('请选择一条数据'));
            return;
        }
        var object = rows[0];
        object.type = "00";
        object.xqType = "TQXQ";
        if(object.statusValidStr == '流程中' ){
            Joyin.warn(JoyinUtil.getMessage('流程中不支持提前行权'));
            return;
        }
        if(object.statusValidStr == '有效' ){
            Joyin.warn(JoyinUtil.getMessage('已生效数据不支持提前行权'));
            return;
        }
        if(object.exerciseTypeStr == '欧式' ){
            Joyin.warn(JoyinUtil.getMessage('行权方式 欧式 不支持提前行权'));
            return;
        }
        //发起check
        Joyin.ajaxJson(basePath + 'invest/period/trdoptionduration/TrdOptionDuration002/check', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel('提前行权'),
                '../TrdOptionDuration002/init',
                '80%', '90%',object,function(){table.reload();});
        });
    }

    function showDialogfq() {
        var rows = table.getAllCheckedRows();
        if (rows.length != 1) {
            Joyin.error(JoyinUtil.getMessage('请选择一条数据'));
            return;
        }
        var object = rows[0];
        if(object.statusValidStr == '流程中' ){
            Joyin.warn(JoyinUtil.getMessage('流程中不支持放弃行权'));
            return;
        }
        if(object.statusValidStr == '有效' ){
            Joyin.warn(JoyinUtil.getMessage('已生效数据不支持放弃行权'));
            return;
        }
        if(object.optFeePayType == '02' ){
            Joyin.warn(JoyinUtil.getMessage('此期权交易是后付期权费交易，不可进行放弃行权交易操作'));
            return;
        }

        object.type = "00";
        object.xqType = "FQXQ";
        //发起check
        Joyin.ajaxJson(basePath + 'invest/period/trdoptionduration/TrdOptionDuration002/check', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel('TrdOptionDuration003.add'),
                '../TrdOptionDuration003/init',
                '80%', '90%',object,function(){table.reload();});
        });
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#TrdOptionDuration001_btn_daoqi').click(showDialogdq);
        $('#TrdOptionDuration001_btn_tiqian').click(showDialogtq);
        $('#TrdOptionDuration001_btn_fangqi').click(showDialogfq);

        //导出
        $('#TrdOptionDuration001_btn_export').on('click',function() {
            exportExcel();
        });
        //初始化查询
        $('#TrdOptionDuration001_btn_search').click(function() {
            table.load();
            $('#TrdOptionDuration001_btn_revoke').show();
        });
        $('#TrdOptionDuration001_btn_revoke').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
//            勾选多条，或者不唯一时
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }
            //有效状态
            if (selArr[0].statusValidStr != '有效') {
                Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0006"));
                return;
            }

            var object = selArr[0];
            object.busiCode = selArr[0].busiCode;
            object.operateType="submit";
            object.mode="revoke";
            if(object && object.busiCode && object.busiCode != ''){
                object.pTradeId = object.busiCode;
              //  object.busiCode = object.busiCode + 'R';
            }
            Joyin.ajaxJson(basePath + 'invest/period/trdoptionduration/TrdOptionDuration001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.revoke")), function(){
                    Joyin.prompt(JoyinUtil.getLabel("TrdLendDuration001.revokeReason"),2,function(res){
                        object.cancelRemark = res;
                        WorkFlow.doRevokeList(basePath + 'invest/period/trdoptionduration/TrdOptionDuration001/revoke', object, "TrdOptionDuration001_btn_revoke", "TrdOptionDuration002");
                    },null,200);
                });
            });
        });
    });

}());