(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
            // 报价编号
            quoteid: $('#quoteid').val()
            // 委托编号
            , trustid: $('#trustid').val()
            // 内部报价编号
            , nbquoteid: $('#nbquoteid').val()
            // 组合代码
            , accountid: $('#accountid').val()
            // 债券代码
            , seccode: $('#seccode').val()
            // 本方交易方向
            , ps: $('#ps').val()
            // 交易日期
            , dealdateBegin: $('#dealdate').startDate()
            , dealdateEnd: $('#dealdate').endDate()
            // 交易状态
            , tradestatus: $('#tradestatus').val()
            // 发送状态
            , sendstatus: $('#sendstatus').val()
        };
        return pageParam;
    };
    /**
     * 定义数据表格
     */

    var listTbl = $("#dataTable").joyintable({
        // ajax请求数据
        ajax: {
            // 请求路径
            url: 'findPage',
            // 请求类型
            type: 'POST',
            data: function () {
                // 获取请求参数
                return getSearchParams();
            }
        },
        rowno:true,
        checkbox:true,
        dblclick: function (table, data) {
            Joyin.dialog(JoyinUtil.getLabel("FxSecRequestQuote.see"),
                JoyinUtil.getRealPath("interf/cfetsup/secrequestquote/FxSecRequestQuote002/init"),
                "99%", "99%", {operateType: 'see',seqno:data.seqno,pageModel:PAGE_MODEL_REFERENCE}
                , function(){table.reload();}
            );
        },
        /**
         * 定义表头
         */
        cols: [
            {field: 'dealdate', width:100, sortField:'dealdate', title: 'FxSecRequestQuote.dealdate', type: 'date'}
            , {field: 'procstatus', width:100, sortField:'procstatus', title: 'FxSecRequestQuote.procstatus'}
            , {field: 'quoteid', width:210, sortField:'quoteid', title: 'FxSecRequestQuote.quoteid'}
            , {field: 'nbquoteid', width:210, sortField:'nbquoteid', title: 'FxSecRequestQuote.nbquoteid'}
            , {field:'tradestatus',sortField:'tradestatus', width:100, title: 'FxSecRequestQuote.tradestatus'}
            , {field: 'sendstatus', width:100, sortField:'sendstatus', title: 'FxSecRequestQuote.sendstatus'}
            , {field: 'errormsg', width:120, sortField:'errormsg', title: 'FxSecRequestQuote.errormsg'}
            , {field: 'accountid', width:100, sortField:'accountid', title: 'FxSecRequestQuote.accountid'}
            , {field: 'accountname', width:140, sortField:'accountname', title: 'FxSecRequestQuote.accountname'}
            , {field: 'homecstpname', width:140, sortField:'homecstpname', title: 'FxSecRequestQuote.homecstpname'}
            , {field: 'ps', width:100, sortField:'ps', title: 'FxSecRequestQuote.ps'}
            , {field: 'seccode', width:140, sortField:'seccode', title: 'FxSecRequestQuote.seccode'}
            , {field: 'prinamt', width:100, sortField:'prinamt', title: 'FxSecRequestQuote.prinamt',type: 'money'}
            , {field: 'settlespeed', width:100, sortField:'settlespeed', title: 'FxSecRequestQuote.settlespeed'}
            , {field: 'trustid', width:150, sortField:'trustid', title: 'FxSecRequestQuote.trustid'}
            , {field: 'homerole', width:100, sortField:'homerole', title: 'FxSecRequestQuote.homerole'}
            , {field : 'seqno', visible : false}
        ]
    });

    //导出
    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 画面加载完成
     */
    $(function () {
        listTbl.load();
        $('#FxSecRequestQuote001_btn_delete').show();
        // 初始化查询
        $('#FxSecRequestQuote001_btn_search').click(function () {
            listTbl.load();
            $('#FxSecRequestQuote001_btn_delete').show();
        });

        // 绑定新建按钮事件
        $('#FxSecRequestQuote001_btn_add').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("FxSecRequestQuote.add"), basePath
                + "interf/cfetsup/secrequestquote/FxSecRequestQuote002/init", "99%", "99%", {
                operateType: 'add',businessType:'add',trustid:'WTBOND201906040581'
            }, function () {
                listTbl.reload();
            });
        });

        // 绑定回复报价
        $('#FxSecRequestQuote001_btn_reply').click(function () {
            var selArr;
            if (listTbl != null && listTbl != '') {
                selArr = listTbl.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            Joyin.dialog(JoyinUtil.getLabel("FxSecRequestQuote.reply"), basePath
                + "interf/cfetsup/secrequestquote/FxSecRequestQuote002/init", "99%", "99%", {
                operateType: 'reply',pageModel:"reply",businessType:'confirm',seqno:selArr[0].seqno
            }, function () {
                listTbl.reload();
            });
        });

        // 绑定撤销报价按钮事件
        $('#FxSecRequestQuote001_btn_cancel').click(function () {
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
                data:{operateType: 'cancel',seqno:selArr[0].seqno},
                url: basePath + 'interf/cfetsup/secrequestquote/FxSecRequestQuote001/cancel',
                success: function (result) {
                    if (result.statuCode == "200") {
                        JoyinUtil.showMessage(result.message,"info");
                    }
                }
            });
        });


        // 复制
        $('#FxSecRequestQuote001_btn_copy').click(function () {
            var selArr;
            if (listTbl != null && listTbl != '') {
                selArr = listTbl.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            Joyin.dialog(JoyinUtil.getLabel("FxSecRequestQuote.copy"), basePath
                + "interf/cfetsup/secrequestquote/FxSecRequestQuote002/init", "99%", "99%", {
                operateType: 'copy',pageModel:PAGE_MODEL_NEW,businessType:'add',seqno:selArr[0].seqno
            }, function () {
                listTbl.reload();
            });
        });

        // 撤回报价
        $('#FxSecRequestQuote001_btn_revoke').click(function () {
            var selArr;
            var seqnos='';
            debugger;
            if (listTbl != null && listTbl != '') {
                selArr = listTbl.getAllCheckedRows();
                for(var i=0;i<selArr.length;i++){
                    seqnos+=selArr[i].seqno+","
                }
            }
            // 列表中未勾选
            if (!selArr || selArr.length != 1) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                return;
            }
            $.ajax({
                type: "POST",
                data:{operateType: 'revoke',seqnos:seqnos},
                url: basePath + 'interf/cfetsup/secrequestquote/FxSecRequestQuote001/revoke',
                success: function (result) {
                    if (result.statuCode == "200") {
                        JoyinUtil.showMessage(result.message,"info");
                    }else {
                        JoyinUtil.showMessage(result.message,"error");
                    }
                }
            });
        });

        // 再次发送按钮事件
        $('#FxSecRequestQuote001_btn_resend').click(function () {
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
                url: basePath + 'interf/cfetsup/secrequestquote/FxSecRequestQuote001/resend',
                success: function (result) {
                    if (result.statuCode == "200") {
                        JoyinUtil.showMessage(result.message,"info");
                    }
                }
            });
        });
    });

}());