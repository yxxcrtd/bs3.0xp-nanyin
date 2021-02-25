$(function(){
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var   tradeDate= $('#tradeDate').val()	;//交易日期
        var   counterId	= $('#counterId').val() ;//对手方
        var   counterTrader= $('#counterTrader').val()	;//对手方交易员
        var   ps = $('#ps').val();//交易方向
        var  settleSpreed	= $('#settleSpreed').val();//清算速度
        var  term	= $('#term').val();//回购期限（天）
        var  status	= $('#status').val();//有效状态
        var  depCode	= $('#depCode').val();//托管机构
        var  capitalAccount	= $('#capitalAccount').val();//资金账户
        var   secAcct	= $('#secAcct').val();//托管账号
        return {
            tradeDate : tradeDate,
            counterId : counterId,
            counterTrader : counterTrader,
            ps : ps,
            settleSpreed : settleSpreed,
            term : term,
            status : status,
            depCode : depCode,
            capitalAccount : capitalAccount,
            secAcct : secAcct
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        dblclick:function(table, data) {
            if(data.parentId != 'root'){
                return;
            }
            data.tradeId = data.totleTradeId;
            data.pageModel = PAGE_MODEL_REFERENCE;//page_model:查看
            data.workflowStatus = data.validStatus
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),basePath + "invest/trade/pretrdrepotrade/PreTrdRepoTrade002/init",
                "98%", "98%",data, function(){table.reload();})
        },
        tree:true,
        treeId: 'childId',
        treeUpId: 'parentId',
        treeShowName: 'validStatusText',
        lazyload:true,
        cols:[
            {field:'validStatusText', width:120, title: '有效状态',sortField:'validStatus'}
            ,{field:'tradeDateText', width:120, title: '交易日期',sortField:'tradeDateText'}
            ,{field:'counterId', width:120, title: '对手方',sortField:'counterId'}
            ,{field:'counterTrader', width:120, title: '对手方交易员',sortField:'counterTrader'}
            ,{field:'psText', width:120, title: '交易方向',sortField:'ps'}
            ,{field:'tradeAmt', width:120, title: '交易金额(元)',type: 'number',digit:2,sortField:'tradeAmt'}
            ,{field:'rate', width:120, title: '回购利率(%)',sortField:'rate'}
            ,{field:'repoTerm', width:120, title: '回购期限(天)',sortField:'repoTerm'}
            ,{field:'settleSpeedStr', width:120, title: '清算速度',sortField:'settleSpeed'}
            ,{field:'deliveryTypeText', width:120, title: '首次结算方式',sortField:'deliveryType'}
            ,{field:'mDeliveryTypeText', width:120, title: '到期结算方式',sortField:'mDeliveryType'}
            ,{field:'clearTypeText', width:120, title: '清算类型',sortField:'clearType'}
            ,{field:'effTimeText', width:120, title: '有效时间',sortField:'effTime'}
            ,{field:'remark', width:120, title: '补充条款',sortField:'remark'}
            ,{field:'acctBankDep', width:120, title: '资金开户行',sortField:'acctBankDep'}
            ,{field:'acctNo', width:120, title: '账户',sortField:'acctNo'}
            ,{field:'acctName', width:120, title: '账户名',sortField:'acctName'}
            ,{field:'acctBankDepoPayno', width:120, title: '资金开户行支付系统行号',sortField:'acctBankDepoPayno'}
            ,{field:'registOrgStr', width:120, title: '托管机构名称',sortField:'registOrg'}
            ,{field:'secAcctId', width:120, title: '托管账号',sortField:'secAcctId'}
            ,{field:'secAcctName', width:120, title: '托管账户户名',sortField:'secAcctName'}
            ,{field:'bondCcy', width:120, title: '债券币种',sortField:'bondCcy'}
            ,{field:'settleCcy', width:120, title: '结算币种',sortField:'settleCcy'}
            ,{field:'portfolioName', width:120, title: '组合名称',sortField:'portfolioName'}
            ,{field:'finprodMarketId', width:120, title: '债券代码',sortField:'finprodMarketId'}
            ,{field:'finprodAbbr', width:120, title: '债券名称',sortField:'finprodAbbr'}
            ,{field:'pledgeAmt', width:120, title: '券面总额(万元)',sortField:'pledgeAmt'}
            ,{field:'pledgeRatio', width:120, title: '折算比例(%)',sortField:'pledgeRatio'}
        ]
    });
    /**
     * 导出事件
     */
    var exportExcel = function() {
    	//modify by yujiaping 2020-12-10 BS-1409【 质押式回购交易预录入】未选择点击导出应导出全部，选中点击导出，只导出选中数据start
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
        	 var paramObj = getPageParams();
             Joyin.exportExcel('export', paramObj);
        }else{
        	 Joyin.exportExcel('export', {
                 datas : datas
             });
        }
        /*var object = {
            operateType: 'export',
            preTrdRepoTrade002DtoList: selArr
        }
        Joyin.ajaxJson(basePath + 'invest/trade/pretrdrepotrade/PreTrdRepoTrade001/businessCheck', object, function (result) {
            Joyin.confirm(result.data, function () {
                object.preTrdRepoTrade002DtoList = JSON.stringify(selArr);
                Joyin.exportExcel('export', object);
            });
        },false)*/
    	//modify by yujiaping 2020-12-10 BS-1409【 质押式回购交易预录入】未选择点击导出应导出全部，选中点击导出，只导出选中数据end
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $('#PreTrdRepoTrade001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PreTrdRepoTrade002"),
                basePath+ "invest/trade/pretrdrepotrade/PreTrdRepoTrade002/init",
                "98%", "98%", {pageModel:PAGE_MODEL_NEW},function(){table.reload()});
        });

        //导出
        $('#PreTrdRepoTrade001_btn_export').click(function() {
            exportExcel();
        });
        //查询
        $('#PreTrdRepoTrade001_btn_search').click(function(){
            table.load();
        });
        //撤销操作
        $('#PreTrdRepoTrade001_btn_revoke').click(function(){
            var selArr1 = new Array();
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
                if (selArr.length > 1){
                    //撤销时只选取具有有效状态的数据
                    for(var i = 0;i < selArr.length; i++){
                        if (selArr[i].validStatusText != null){
                            var j = selArr1.length;
                            if (j>0){
                                j = j - 1;
                            }
                            selArr1[j] = selArr[i]    
                        }        
                    }            
                }
            }
            selArr = selArr1;
            if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0026"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }

            var object = {
                operateType: 'revoke',
                preTrdRepoTrade002DtoList: selArr
            }

            Joyin.ajaxJson(basePath + 'invest/trade/pretrdrepotrade/PreTrdRepoTrade001/businessCheck', object, function (result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    selArr[0].revokeReason = result;
                    object.preTrdRepoTrade002DtoList = selArr;
                    WorkFlow.doRevokeList(basePath + 'invest/trade/pretrdrepotrade/PreTrdRepoTrade001/doRevoke', object, "PreTrdRepoTrade001_btn_revoke", "PreTrdRepoTrade002");
                });
            });

        });

    });
}());