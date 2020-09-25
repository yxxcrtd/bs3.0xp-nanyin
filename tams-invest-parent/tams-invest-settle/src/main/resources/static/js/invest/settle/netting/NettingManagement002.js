$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function () {
        return {
            bizCodes: bizCodes,
            workflowId: workflowId,
            orderId: orderId,
            isTodoList: isTodoList
        }
    };

    // searchOnInit必须是true，才能reload，所以加上返回空数据的请求
    var isEmpty = true;

    var orderDataTable = $("#orderDataTable").joyintable({
        ajax: {
            url: basePath + 'invest/settle/netting/NettingManagement002/findOrderDataTable',
            type: 'POST',
            data: function () {
                return {isEmpty: isEmpty};
            }
        },
        checkbox: false,
        searchOnInit: true,
        page: false,
        cols: [
            {field: 'isNeedSend', title: '是否发送指令', width: 100, edit:true,
                editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: YES_NO}}
            , {field: 'settleDate', title: '支付日期', width: 120, type: 'date', edit: true}
            , {field: 'settleAmt', title: '金额', width: 200, type: 'number'}
            , {field: 'ccyString', title: '币种', width: 150}
            , {field: 'payAcctNo', title: '付款账号', width: 150}
            , {field: 'payAcctName', title: '付款账号名称', width: 200}
            , {field: 'recAcctNo', title: '收款账号', width: 150}
            , {field: 'recAcctName', title: '收款账号名称', width: 200}
            , {field: 'largePayNo', title: '收款账号网点', width: 150}
            , {field: 'purpose', title: '用途', width: 200, edit: true}
            , {field: 'remark', title: '备注', width: 200, edit: true}
            , {field: 'portfolioId', title: '组合代码', width: 160}
            , {field: 'portfolioName', title: '组合名称', width: 200}
            , {field: 'payString', title: '划款类型', width: 200}
          //  , {field: 'inOutBankString', title: '划款性质', width: 200}
        ]
    });

    var tradeDataTable = $("#tradeDataTable").joyintable({
        ajax: {
            url: basePath + 'invest/settle/netting/NettingManagement002/findTradeData',//请求路径
            type: 'POST',//请求类型
            data: function () {
                return getPageParams();//获取请求参数
            }//ajax请求数据
        },
        checkbox: false,
        searchOnInit: true,
        page: false,
        /**
         * 定义表头
         */
        cols: [
            {field: 'paBussTypeString', title: '业务类型', width: 200}
            , {field: 'bizCode', title: '业务编号', width: 200}
            , {field: 'portfolioId', title: '组合代码', width: 160}
            , {field: 'portfolioName', title: '组合名称', width: 200}
           // , {field: 'finprodMarketId', title: '资产代码', width: 160}
           // , {field: 'finprodName', title: '资产名称', width: 200}
           // , {field: 'tradeDate', title: '交易日', width: 120}
            , {field: 'settleDate', title: '现金流日期', width: 120}
            , {field: 'settleAmt', title: '交易金额', width: 200, type: 'number'}
        ],
        done: function (table, allData) {
            isEmpty = false;
            orderDataTable.reload();
            resetTable();
        }
    });

    function saveOrSubmit(operateType) {
        var rows = orderDataTable.getAllRows();
        var tradeRows = tradeDataTable.getAllRows();

        //alert(JSON.stringify(rows));
        //alert(JSON.stringify(tradeRows));

        if (!rows || rows.length == 0 || !tradeRows || tradeRows.length == 0) {
            Joyin.error("没有指令要生成。")
            return;
        }
        var object = rows[0];
        object.commonBusiType = 'PTL16';
        object.tradeDatas = tradeRows;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/settle/netting/NettingManagement002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/settle/netting/NettingManagement002/save', object, "NettingManagement002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/settle/netting/NettingManagement002/save', object, "NettingManagement002_btn_submit");
                }
            });
        });

    }

    function resetTable() {
        ref = setInterval(function () {
            if ($(".layui-table-view").length > 0) {
                var height = $(window).height();
                $(".layui-table-view").css({height: height / 2 - 120 + 'px'});
                $(".layui-table-view .layui-table-box .layui-table-body").css({height: (height / 2 - 150) + 'px'});
                setted = true;
            }
        }, 300);
        var set = setInterval(function () {
            if (setted) {
                clearInterval(ref);
                clearInterval(set);
            }
        }, 300);
    }


    $(function () {
        resetTable();
        $('#NettingManagement002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#NettingManagement002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    })
});
