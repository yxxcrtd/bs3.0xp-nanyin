(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var data = {
            //dealType: $('#dealType').val(),
            groupId: $('#groupId').val()
        }
        //add by yejiejie 2019-4-4 start
        var dealTypes = $("#dealType").val();
        if (dealTypes) {
        	dealTypes = dealTypes.join(',');
        }
        data.dealType = dealTypes;
        //add by yejiejie 2019-4-4 end

        if ($('#cfmDate').val()) {
            data.startCfmDate = $('#cfmDate').startDate();
            data.endCfmDate = $('#cfmDate').endDate();
        }
        var prodIds = $("#prodId").val();
        if (prodIds) {
            prodIds = prodIds.join(',');
        }
        data.prodId = prodIds;
        data.effectStatus = $("#effectStatus").val();
        return data;
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        dblclick: function (table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            showDialog(data, '查看');
        },
        cols: [
            {field: 'busiCode', width: 200, title: '业务编号'}
            , {field: 'effectStatusString', width: 200, title: '有效状态',sortField:'effectStatus'}
            , {field: 'portfolioId', width: 200, title: '组合代码'}
            , {field: 'finprodMarketId', width: 200, title: '产品代码',sortField:'finProdId'}
            , {field: 'finprodAbbr', width: 200, title: '产品简称'}
//            , {field: 'saleChannel', width: 200, title: '销售部门/分行'}
            , {field: 'psString', width: 200, title: '交易类型',sortField:'ps'}
            , {field: 'settleDate', width: 200, title: '确认日'}
            , {field: 'tradeAmt', width: 200, title: '金额', type:'number'}
            , {field: 'shareAmt', width: 200, title: '份额', type:'number'}
            , {field: 'ccy', width: 200, title: '币种'}
            , {field: 'totleTradeId', width: 200, title: '汇总交易编号',visible: false}
            //update by leipeng BSTWO-174 【收益型产品提前赎回管理】查询列表去掉‘违约金’字段。
            //, {field: 'sdefaultAmt', width: 200, title: '违约金'}//add by yejiejie 2019-4-4
        ],
        checkbox: true,
        progressBar: true
    });

    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 弹出新建画面
     * @returns
     */
    function showDialog(data, title) {
        Joyin.dialog("收益型产品提前赎回管理-" + title, '../FinProductAddReduce002/init', '98%', '95%', data||{pageModel:PAGE_MODEL_NEW});
    }

    function serchData() {
        table.load();
    }

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $("#FinProductAddReduce001_btn_export").click(exportExcel);
        $('#FinProductAddReduce001_btn_addreduce').click(function () {
            showDialog(null, "新建")
        });
        $('#FinProductAddReduce001_btn_search').click(serchData);
        $("#FinProductAddReduce001_btn_revoke").click(function () {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
            }

            var rows = table.getAllCheckedRows();
            if (rows.length != 1) {
                Joyin.error(JoyinUtil.getMessage('请选择一条数据'))
                return;
            }
            var object = rows[0];
            object.operateType = "revoke";
            Joyin.ajax(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce001/revokeCheck', object, function (result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    object.revokeReason = result;
                    WorkFlow.doRevoke(basePath + 'invest/product/productduration/addreduce/FinProductAddReduce001/revoke', object, "FinProductAddReduce001_btn_revoke", "FinProductAddReduce002");
                });
            });
        })
    });
}());