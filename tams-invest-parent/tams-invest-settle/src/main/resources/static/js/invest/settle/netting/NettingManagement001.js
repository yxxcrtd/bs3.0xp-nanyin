$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function () {

        var result = $.serializeToObject('#dvpTransferForm');
        if ($("#portfolioId1").val()) {
            result.portfolioId1 = $("#portfolioId1").val().join(",");
        } else {
            result.portfolioId1 = null;
        }
        if ($("#totleTradeId").val()) {
            result.totleTradeId = $("#totleTradeId").val();
        } else {
            result.totleTradeId = null;
        }
        if ($("#settleDate").val()) {
            result.settleDate = $("#settleDate").val();
            //result.settle = $("#settle").endDate();
        }
        if ($("#orderType").val()) {
            result.orderType = $("#orderType").val().join(",");
        }else{
            result.orderType = null;
        }
        if ($("#state").val()) {
            result.state = $("#state").val();
        }else{
            result.state = null;
        }

        if (result.finprodMarketId) {
            result.finprodMarketId = result.finprodMarketId.replace(/(^\s*)|(\s*$)/g, "");
        }
        if (result.totle) {
            result.totle = result.totle.replace(/(^\s*)|(\s*$)/g, "");
        }
        result.isDvp = true;
        //alert(JSON.stringify(result));
        //console.log(JSON.stringify(result));
        return result;
    };

    function getTransferFormParams() {
        var result = $.serializeToObject('#transferForm');
        if ($("#portfolioId").val()) {
            result.portid = $("#portfolioId").val().join(",");
        } else {
            result.portid = null;
        }
        if ($("#orderid").val()) {
            result.orderid = $("#orderid").val();
        } else {
            result.orderid = null;
        }
        if ($("#status").val()) {
            result.status = $("#status").val();
        } else {
            result.status = null;
        }

        if ($("#sdate").val()) {
            result.sdate = $("#sdate").val();
        }

        result.commonBusiType = 'PTL16';
        result.isDvp = false;
        return result;
    }

    var dvpTransferTable = $("#dvpTransferTable").joyintable({
        ajax: {
            url: 'findNettingTableData',
            type: 'POST',
            data: function () {
                return getPageParams();
            }
        },
        checkbox: true,
        searchOnInit: false,
        dblclick:function(table, data) {
            data.tradeId = data.totle;
            data.dealType = data.dataType;
            data.pageModel = PAGE_MODEL_REFERENCE;
            if(data.orderTypeString=='产品现金分红')
            {
                Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    JoyinUtil.getRealPath("invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme004/init"),
                    "98%", "95%",data,function(){detailDataTable.reload();})
            }
            else {
                Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
                    "98%", "95%",data,function(){detailDataTable.reload();})
            }

        },
        cols: [

            {field: 'totle', title: '业务编号', width: 160, sortField:'totle'}
            , {field: 'trade', title: '确认日', width: 160, sortField:'trade'}
            , {field: 'settle', title: '支付日', width: 200, sortField:'settle'}
            , {field: 'pormame', title: '组合名称', width: 160, sortField:'pormame'}
            , {field: 'porid', title: '组合代码', width: 160, sortField:'porid'}
            , {field: 'orderTypeString', title: '业务类型', width: 160, sortField:'ordertype'}
            , {field: 'payString', title: '划款类型', width: 120, sortField:'pay'}
            , {field: 'amt', title: '划款金额', width: 120, sortField:'amt'}
            , {field: 'ccy', title: '币种', width: 200,  sortField:'ccy'}
            , {field: 'state', title: '处理状态', width: 120, sortField:'state'}
            /*
            , {
                field: 'operation', title: 'SysCommon.operation', type: 'action', width: 120,
                buttons: [{
                    text: '',
                    clas: 'iconfont icon-download',
                    title: '下载'
                }]
            }*/
        ],
    });

    /**
     * 绑定编辑事件
     */
    dvpTransferTable.bind('click', '.icon-download', function (data, index) {
        var params = {};
        params.pageModel = PAGE_MODEL_REFERENCE;
        params.bizCode = data.bizCode;
        Joyin.dialog("附件信息查看-" + data.bizCode ,
            basePath + "component/common/BaseFileComm/init",
            "98%", "95%", params, function () {
            });
    });

    var transferTable = $("#transferTable").joyintable({
        ajax: {
            url: 'findTransferTableData',//请求路径
            type: 'POST',//请求类型
            data: function () {
                return getTransferFormParams();//获取请求参数
            }//ajax请求数据
        },
        checkbox: true,
        searchOnInit: false,
        /**
         * 定义表头;
         */
        cols: [
            {field: 'orderid', title: '业务编号', width: 120, render: function (data, type, row, meta) {
                    return "<a class='pro-link' style='cursor: pointer;' href=\"javascript:openDvp002('"+data+"')\">" + data + "</a>";
                }}
            , {field: 'portid', title: '组合代码', width: 120, sortField:'portid'}
            , {field: 'portname', title: '组合名称', width: 150, sortField:'portname'}
            , {field: 'send', title: '是否发送指令', width: 100, sortField:'send'}
            , {field: 'sdate', title: '支付日', width: 120, sortField:'sdate'}
            , {field: 'amt', title: '划款金额', width: 200, type: 'number', sortField:'amt'}
            , {field: 'ccy', title: '币种', width: 150, sortField:'ccy'}
            , {field: 'payno', title: '付款账号', width: 150, sortField:'payno'}
            , {field: 'payname', title: '付款账号名称', width: 200, sortField:'payname'}
            , {field: 'gatno', title: '收款账号', width: 150, sortField:'gatno'}
            , {field: 'gatname', title: '收款账号名称', width: 200, sortField:'gatname'}
            , {field: 'gatpayno', title: '收款账号网点/支付系统号', width: 150, sortField:'gatpayno'}
            , {field: 'status', title: '发送状态', width: 150, sortField:'status'}
            , {field: 'purpose', title: '用途', width: 200, sortField:'purpose'}
            , {field: 'remark', title: '备注', width: 200, sortField:'remark'}
//            , {field: 'orderStatus', title: '指令状态', width: 200}
//            , {field: 'sendStatus', title: '发送状态', width: 200}
            //, {field: 'orderStatus', visible : false}
            //, {field: 'sendStatus', visible : false}
        ],
    });

    var dvpExportExcel = function () {
        //alert("dvpExportExcel");
        Joyin.exportExcel('export', getPageParams());
    };

    var ExportExcel = function () {
        //alert("ExportExcel");
        Joyin.exportExcel('export', getTransferFormParams());
    };

    var printData = function() {
        var selectedRows = transferTable.getAllCheckedRows();
        if (!selectedRows || selectedRows.length != 1) {
            Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0046'))
            return;
        }
        var data = selectedRows[0]
        window.open("printPayOrders?bizCode=" + data.bizCode, "_blank", "fullscreen=yes,resizeable=no,scrollbars=yes,channelmode=yes,left=0,top=0,width=1366,height=768")
    }

    $(function () {
        $(".transfer-form #NettingManagement001_btn_search").click(function () {
            transferTable.load();
        })
        $(".dvp-transfer-form #NettingManagement001_btn_search").click(function () {
            dvpTransferTable.load();
        })
        $(".dvp-transfer-form #NettingManagement001_btn_export").click(dvpExportExcel)
        $(".transfer-form #NettingManagement001_btn_export").click(ExportExcel)
        $("#NettingManagement001_btn_print").click(printData)

        $('#NettingManagement001_btn_creatOrder').click(function () {

            var dvpCheckedRows = dvpTransferTable.getAllCheckedRows();
            if (!dvpCheckedRows || dvpCheckedRows.length == 0) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0026"))
                return;
            }
            var bizCodes = [];
            for (var key in dvpCheckedRows) {
                bizCodes.push(dvpCheckedRows[key].totle);
                if (dvpCheckedRows[key].state == '已处理') {

                    Joyin.error(JoyinUtil.getMessage("FIN_ASSET_E_0024"))
                    return;
                }
            }

            for (var key in dvpCheckedRows) {
                bizCodes.push(dvpCheckedRows[key].totle);
                //alert(dvpCheckedRows[key].ordertype);
                if (dvpCheckedRows[key].ordertype == 'C1') {

                    Joyin.error(JoyinUtil.getMessage("NETTING_E_001"))
                    return;
                }
            }

            for (var i=1;i<dvpCheckedRows.length;i++) {
                if (dvpCheckedRows[0].porid != dvpCheckedRows[i].porid
                    || dvpCheckedRows[0].ccy != dvpCheckedRows[i].ccy
                    || dvpCheckedRows[0].settle != dvpCheckedRows[i].settle
                    //|| dvpCheckedRows[0].account != dvpCheckedRows[i].account
                )
                {
                    Joyin.error(JoyinUtil.getMessage("NETTING_E_002"))
                    return;
                }
            }
            //20191211新增募集托管户校验 同一募集托管户做轧差交易
            for (var i=1;i<dvpCheckedRows.length;i++) {
                if ((dvpCheckedRows[0].account != dvpCheckedRows[i].account && 
                				dvpCheckedRows[0].account != dvpCheckedRows[i].trusteeship
                		) || (
                				dvpCheckedRows[0].trusteeship != dvpCheckedRows[i].account && 
                        		dvpCheckedRows[0].trusteeship != dvpCheckedRows[i].trusteeship
                		)) 
                {	
                    Joyin.error(JoyinUtil.getMessage("NETTING_E_002"))
                    return;
                }
            }
            Joyin.dialog(JoyinUtil.getLabel("轧差指令生成"),
                basePath + "invest/settle/netting/NettingManagement002/init",
                "95%", "90%", {bizCodes:bizCodes, pageModel: PAGE_MODEL_NEW});
        });

        $('#DvpOrdersManagement001_btn_revoke').click(function(){
            if(transferTable!=null && transferTable!=''){
                var selArr = transferTable.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            //判断指令状态 已撤销的 提示不能再撤销
            /*
            if(selArr[0].status == 02){
                Joyin.error(JoyinUtil.getMessage("DvpOrdersManagement001_E_002"));
                return;
            }*/

            //判断发送状态 已发送的 不能撤销
            if(selArr[0].status == 01){
                Joyin.error(JoyinUtil.getMessage("DvpOrdersManagement001_E_003"));
                return;
            }

            //alert(selArr[0].orderid);
            var object = {};
            //add by libaisong 2019-10-14 start
            object.actOrderId = selArr[0].orderid;
            object.settleDate=selArr[0].sdate;
            object.purpose = selArr[0].purpose;
            object.operateType = "revoke";

            Joyin.ajaxJson(basePath + 'invest/settle/netting/NettingManagement001/checkRevoke', object, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    object.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'invest/settle/netting/NettingManagement001/doRevoke', object, "DvpOrdersManagement001_btn_revoke", "NettingManagement002");
                });
            });
            //WorkFlow.doDeleteList(basePath + 'invest/settle/dvporder/DvpOrdersManagement001/doRevoke', object, "DvpOrdersManagement001_btn_revoke", "NettingManagement002");
            //add by libaisong 2019-10-14 end
        });
    })
});

var openOperateWindow = function (statusValid, paBussType, portfolioId, bizCode, finprodId) {
    var data = statusValid;
    alert(JSON.stringify(data));
    //alert(paBussType);
    //debugger;
    var url = '';
    /*
    var data = {
        pageModel: PAGE_MODEL_REFERENCE,
        tradeId: bizCode,
        busiCode: bizCode,
        bizCode: bizCode,
        workflowStatus: statusValid,
        portfolioId: portfolioId,
        statusValid: statusValid,
        validStatus: statusValid,
        finprodId: finprodId,
        isFromCheck: true
    };

    if (paBussType == 债券买入 || paBussType == 债券卖出 || paBussType == 债券行权) {
        url = 债券交易URL;
    } else if (paBussType == 正回购 || paBussType == 逆回购) {
        url = 回购交易URL;
    } else if (paBussType == 正回购到期 || paBussType == 逆回购到期) {
        url = 回购存续URL;
    } else if (paBussType == 买断式正回购 || paBussType == 买断式逆回购) {
        url = 买断式回购交易URL;
    } else if (paBussType == 买断式正回购到期 || paBussType == 买断式逆回购到期) {
        url = 买断式回购存续URL;
    }
*/
    //url = "invest/product/productduration/netproductDuration/FinNetProductDuration/init";
    //var title = JoyinUtil.getLabel("SysCommon.refrence");
    //Joyin.dialog(title, url, "98%", "98%", data);

    Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
        JoyinUtil.getRealPath("invest/product/productduration/netproductDuration/FinNetProductDuration/init"),
        "98%", "95%",data,function(){detailDataTable.reload();})
}

var openDvp002 = function(bizCode) {
    //在新建界面进行显示单行的详细信息
    var params = {}
    params.pageModel = PAGE_MODEL_REFERENCE;
    params.orderId = bizCode;
    Joyin.dialog("轧差指令查看",
        basePath + "invest/settle/netting/NettingManagement002/init",
        "98%", "98%", params, function(){});
}