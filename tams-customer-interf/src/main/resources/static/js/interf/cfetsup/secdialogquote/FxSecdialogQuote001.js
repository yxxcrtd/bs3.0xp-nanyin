(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            //报价编号
            quoteid: $('#quoteid').val()
            // 委托代码
            ,trustid: $('#trustid').val()
            //资产代码
            ,seccode: $('#seccode').val()
            // 组合代码
            , accountid: $('#accountid').val()
            // 交易方向
            , ps: $('#ps').val()
            // 交易日期
            ,tradeDateBegin:$('#dealdate').startDate()
            ,tradeDateEnd: $('#dealdate').endDate()
            //交易状态
            ,tradestatus:$('#tradestatus').val()
            // 发送状态
            , sendstatus: $('#sendstatus').val()
            // 内部报价编码
            , nbquoteid: $('#nbquoteid').val()
        };

        if ($("#portfolioId").val()) {
            pageParam.accountid = $("#portfolioId").val().join(",");
        } else {
            pageParam.accountid = null;
        }
        return pageParam;
    };
    /**
     * 定义数据表格
     */

    var listTbl = $("#dataTable").joyintable({
        // ajax请求数据
        ajax: {
            // 请求路径
            url: 'selectForPage',
            // 请求类型
            type: 'POST',
            data: function () {
                // 获取请求参数
                return getSearchParams();
            }
        },
        dblclick: function (listTbl, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FxSecdialogQuote002.reference"),
                JoyinUtil.getRealPath("interf/cfetsup/secdialogquote/FxSecdialogQuote002/init"),
                "95%", "95%", data
                , function(){listTbl.reload();}
            );
        },
        /**
         * 定义表头
         */
        cols: [
            {field: 'dealdate', width:100,sortField:'dealdate', title: 'FxSecdialogQuote001.dealdate', type: 'date'}
            , {field: 'ps', width:100,sortField:'ps', title: 'FxSecdialogQuote001.ps'}
            , {field: 'seccode',width:100, sortField:'seccode', title: 'FxSecdialogQuote001.seccode'}
            , {field: 'secname',width:100, sortField:'secname', title: 'FxSecdialogQuote001.secname'}
            ,{field: 'tradestatus',width:100,sortField:'tradestatus', title: 'FxSecdialogQuote001.tradestatus'}
            , {field: 'sendstatus',width:100,sortField:'sendstatus', title: 'FxSecdialogQuote001.sendstatus'}
            , {field: 'errormsg',width:100, sortField:'errormsg', title: 'FxSecdialogQuote001.errormsg'}
            , {field: 'homeid',width:100, sortField:'homeid', title: 'FxSecdialogQuote001.homeid'}
            , {field: 'jycyid', width:100, sortField:'jycyid', title: 'FxSecdialogQuote001.jycyid'}
            , {field: 'countertrader',width:100,sortField:'countertrader', title: 'FxSecdialogQuote001.countertrader'}
            , {field: 'prinamt',width:100, sortField:'prinamt', title: 'FxSecdialogQuote001.prinamt',type: 'money'}
            , {field: 'cleanprice', width:100, sortField:'cleanprice', title: 'FxSecdialogQuote001.cleanprice'}
            , {field: 'matureyield',width:100, sortField:'matureyield', title: 'FxSecdialogQuote001.matureyield'}
            , {field: 'dirtyprice',width:100, sortField:'dirtyprice', title: 'FxSecdialogQuote001.dirtyprice'}
            , {field: 'exerciseyield', width:100,sortField:'exerciseyield', title: 'FxSecdialogQuote001.exerciseyield'}
            , {field: 'settlespeed', width:100,sortField:'settlespeed', title: 'FxSecdialogQuote001.settlespeed'}
            , {field: 'bondccy',width:100, sortField:'bondccy', title: 'FxSecdialogQuote001.bondccy'}/*债券币种*/
            , {field: 'ccy', width:100, sortField:'ccy', title: 'FxSecdialogQuote001.ccy'}
            , {field: 'waitdate', width:100, sortField:'waitdate', title: 'FxSecdialogQuote001.waitdate'}/*待偿期*/
            , {field: 'seccreditgrade', width:100, sortField:'seccreditgrade', title: 'FxSecdialogQuote001.seccreditgrade'}/*债项评级*/
            , {field: 'homerole',width:100,sortField:'homerole', title: 'FxSecdialogQuote001.homerole'}/*本方角色*/
            , {field: 'quoteid',width:100, sortField:'quoteid', title: 'FxSecdialogQuote001.quoteid'}
            , {field: 'nbquoteid', width:100, sortField:'nbquoteid', title: 'FxSecdialogQuote001.nbquoteid'}
            , {field: 'trustid', width:100, sortField:'trustid', title: 'FxSecdialogQuote001.trustid'}
            , {field: 'portfolioId', width:100,sortField:'portfolioId', title: 'FxSecdialogQuote001.portfolioId'}
            , {field: 'portfolioName',width:100, sortField:'portfolioName', title: 'FxSecdialogQuote001.portfolioName'}
            , {field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

    /**
     * 绑定编辑事件
     */
    listTbl.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("FxSecdialogQuote002.edit"),
            basePath + "interf/cfetsup/secdialogquote/FxSecdialogQuote002/init",//打开新界面
            "98%", "98%" ,data,function(){
                listTbl.reload();//回调函数，表格刷新
            });
    });
    /**
     * 绑定删除事件
     */
    listTbl.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};//请求参数
        Joyin.ajaxJson(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote001/delete', data,"FxSecdialogQuote001_btn_delete", "FxSecdialogQuote002", function(){
                    listTbl.reload();//删除事件请求及回调函数
                });
            });
        });
    });


    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    var doOperate = function (operateType) {
        var selArr;
        if (listTbl != null && listTbl != '') {
            selArr = listTbl.getAllCheckedRows();
        }
        // 列表中未勾选
        if (!selArr || selArr.length != 1) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        if(operateType == '3'){
            //回复报价

        }else if(operateType == '4'){
            //更新报价

        }else if(operateType == '7'){
            //复制报价

        }
        Joyin.dialog(JoyinUtil.getLabel("FxSecdialogQuote002.edit"), basePath
            + "interf/cfetsup/secdialogquote/FxSecdialogQuote002/init", "99%", "99%", {
                pageModel: operateType,seqno:selArr[0].seqno,quoteid:selArr[0].quoteid},
            function () {
                listTbl.reload();
            });
    };

    // 再次发送按钮事件
    var confirmFunction = function () {
        var selArr;
        if (listTbl != null && listTbl != '') {
            selArr = listTbl.getAllCheckedRows();
        }
        // 列表中未勾选
        if (!selArr || selArr.length != 1) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        $.ajax({
            type: "POST",
            data:{operateType: 'resend',seqno:selArr[0].seqno},
            url: basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote001/send',
            success: function (result) {
                if (result.statuCode == "200") {
                    JoyinUtil.showMessage(result.message,"info");
                }
            }
        });
    };
    // 撤回报价按钮事件
    var revokeFunction = function () {
        var selArr;
        if (listTbl != null && listTbl != '') {
            selArr = listTbl.getAllCheckedRows();
        }
        // 列表中未勾选
        if (!selArr || selArr.length != 1) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        $.ajax({
            type: "POST",
            data:{operateType: 'revoke',seqno:selArr[0].seqno},
            url: basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote001/revoke',
            success: function (result) {
                if (result.statuCode == "200") {
                    JoyinUtil.showMessage(result.message,"info");
                }
            }
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxSecdialogQuote001_btn_search').click(function () {
            listTbl.load();
            $('#FxSecdialogQuote001_btn_revoke').show();
        });
        $('#FxSecdialogQuote001_btn_export').click(function () {
            exportExcel();
        });

        // 绑定新建按钮事件
        $('#FxSecdialogQuote001_btn_add').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("FxSecdialogQuote002.new"), basePath
                + "interf/cfetsup/secdialogquote/FxSecdialogQuote002/init", "98%", "98%", {
                pageModel: PAGE_MODEL_NEW
            });
        });

        // 绑定回复报价按钮事件
        $('#FxSecdialogQuote001_btn_quotation_reply').click(function () {
            doOperate(3);
        });

        // 绑定修改报价按钮事件
        $('#FxSecdialogQuote001_btn_quotation_update').click(function () {
            doOperate(4);
        });

        // 绑定撤回报价按钮事件
        $('#FxSecdialogQuote001_btn_quotation_revoke').click(function () {
            revokeFunction();
        });

        // 绑定复制按钮事件
        $('#FxSecdialogQuote001_btn_copy').click(function () {
            doOperate(7);
        });

        // 发送按钮事件
        $('#FxSecdialogQuote001_btn_send').click(function () {
            confirmFunction();
        });

        /**
         * 撤销
         */
        $('#FxSecdialogQuote001_btn_revoke').click(function () {
            if (listTbl != null && listTbl != '') {
                var selArr = listTbl.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            // 列表中勾选对象1条以上时
            if ( selArr != null &&  selArr.length > 1 ) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            var checkObj = {};
            checkObj.operateType = "revoke";
            Joyin.ajaxJson(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote001/checkRevoke', checkObj, function(result) {
                Joyin.prompt(JoyinUtil.getLabel("pingan.common.revokeReason"), 0, function (result) {
                    result = result.replace(/\s+/g,"");
                    if (!result) {
                        result = '无';
                    }
                    checkObj.revokeReason = result;
                    WorkFlow.doDeleteList(basePath + 'interf/cfetsup/secdialogquote/FxSecdialogQuote001/doRevoke', checkObj, "FxSecdialogQuote001_btn_revoke", "FxSecdialogQuote001");
                });
            });
        });

    });
}());