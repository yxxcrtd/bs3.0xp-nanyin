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
            , portfolioId: $('#portfolioId').val()
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
            url: 'selectForPage',
            // 请求类型
            type: 'POST',
            data: function () {
                // 获取请求参数
                return getSearchParams();
            }
        },
        rowno:true,
        dblclick: function (table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            data.seqno = data.seqno;
            data.quoteid = data.quoteid;
            Joyin.dialog(JoyinUtil.getLabel("FxSecrepoQuote002.see"),
                JoyinUtil.getRealPath("interf/cfetsup/secrepoquote/FxSecrepoQuote002/init"),
                "99%", "99%", data
                , function(){table.reload();}
            );
        },
        /**
         * 定义表头
         */
        cols: [
            {field: 'dealdate', width:100, sortField:'dealdate', title: 'FxSecrepoQuote001.dealdate', type: 'date'}
            , {field: 'quoteid', width:120, sortField:'quoteid', title: 'FxSecrepoQuote001.quoteid'}
            , {field: 'nbquoteid', width:120, sortField:'nbquoteid', title: 'FxSecrepoQuote001.nbquoteid'}
            ,{field:'tradestatus',sortField:'tradestatus', width:80, title: 'FxSecrepoQuote001.tradestatus'}
            , {field: 'sendstatus', width:80, sortField:'sendstatus', title: 'FxSecrepoQuote001.sendstatus'}
            , {field: 'errorDesc', width:180, sortField:'errorDesc', title: 'FxSecrepoQuote001.errorDesc'}
            , {field: 'portfolioId', width:120, sortField:'portfolioId', title: 'FxSecrepoQuote001.portfolioId'}
            , {field: 'portfolioName', width:120, sortField:'portfolioName', title: 'FxSecrepoQuote001.portfolioName'}
            , {field: 'ywqc', width:120, sortField:'ywqc', title: 'FxSecrepoQuote001.ywqc'}
            , {field: 'jycyid', width:120, sortField:'jycyid', title: 'FxSecrepoQuote001.jycyid'}
            , {field: 'countertrader', width:120, sortField:'countertrader', title: 'FxSecrepoQuote001.countertrader'}
            , {field: 'ps', width:120, sortField:'ps', title: 'FxSecrepoQuote001.ps'}
            , {field: 'rate', width:80, sortField:'rate', title: 'FxSecrepoQuote001.rate', type: 'money'}
            , {field: 'dealamt', width:120, sortField:'dealamt', title: 'FxSecrepoQuote001.dealamt', type: 'money'}
            , {field: 'zzdamt', width:120, sortField:'zzdamt', title: 'FxSecrepoQuote001.zzdamt', type: 'money'}
            , {field: 'sqsamt', width:120, sortField:'sqsamt', title: 'FxSecrepoQuote001.sqsamt', type: 'money'}
            , {field: 'vsettledate', width:120, sortField:'vsettledate', title: 'FxSecrepoQuote001.vsettledate', type: 'date'}
            , {field: 'term', width:120, sortField:'term', title: 'FxSecrepoQuote001.term'}
            , {field: 'settlespeed', width:120, sortField:'settlespeed', title: 'FxSecrepoQuote001.settlespeed'}
            , {field: 'trustid', width:120, sortField:'trustid', title: 'FxSecrepoQuote001.trustid'}
            , {field: 'homerole', width:120, sortField:'homerole', title: 'FxSecrepoQuote001.homerole'}
            , {field: 'createdtime', width:100, sortField:'createdtime', title: 'FxSecrepoQuote001.createdtime', type: 'date'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ]
    });

    /**
     * 绑定编辑事件
     */
    listTbl.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("FxSecrepoQuote002.edit"),
            basePath + "interf/cfetsup/secrepoquote/FxSecrepoQuote002/init",//打开新界面
            "98%", "98%" ,data,function(){
                listTbl.reload();//回调函数，表格刷新
            });
    });
    /**
     * 绑定删除事件
     */
    listTbl.bind('click', '.delt', function(data, index) {
        Joyin.ajaxJson(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote001/check', data, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
                WorkFlow.doDeleteList(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote001/delete', data,"FxSecrepoQuote001_btn_delete", "FxSecrepoQuote001", function(){
                    listTbl.reload();//删除事件请求及回调函数
                });
            });
        });
    });

    var exportExcel = function () {
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 点击组合代码链接跳转到该组合信息查看页面
     */
    /*listTbl.bind('click', '.portfolioId-link', function (row) {
        Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002.see"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "95%", "90%",
            {
                pageModel: PAGE_MODEL_REFERENCE,
                portfolioId: row.portfolioId,
                portfolioName: row.portfolioName
            });操作
    });*/

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
            //修改报价

        }else if(operateType == '5'){
            //交谈报价

        }else if(operateType == '6'){
            //撤销报价

        }else if(operateType == '7'){
            //复制

        }
        Joyin.dialog(JoyinUtil.getLabel("FxSecrepoQuote002.edit"), basePath
            + "interf/cfetsup/secrepoquote/FxSecrepoQuote002/init", "99%", "99%", {
            pageModel: operateType,seqno:selArr[0].seqno,quoteid:selArr[0].quoteid},
            function () {
            listTbl.reload();
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxSecrepoQuote001_btn_search').click(function () {
            listTbl.load();
        });

        $('#FxSecrepoQuote001_btn_export').click(function () {
            exportExcel();
        });
        
        // 绑定新建按钮事件
        $('#FxSecrepoQuote001_btn_add').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("FxSecrepoQuote002.new"), basePath
                + "interf/cfetsup/secrepoquote/FxSecrepoQuote002/init", "99%", "99%", {
                pageModel: PAGE_MODEL_NEW
            }, function () {
                listTbl.reload();
            });
        });

        // 绑定回复报价按钮事件
        $('#FxSecrepoQuote001_btn_quotation_reply').click(function () {
            doOperate(3);
        });

        // 绑定修改报价按钮事件
        $('#FxSecrepoQuote001_btn_quotation_update').click(function () {
            doOperate(4);
        });

        // 绑定交谈报价按钮事件
        $('#FxSecrepoQuote001_btn_quotation_chat').click(function () {
            doOperate(5);
        });

        // 绑定撤销报价按钮事件
        $('#FxSecrepoQuote001_btn_quotation_revoke').click(function () {
            doOperate(6);
        });

        // 绑定风险试算按钮事件
        $('#FxSecrepoQuote001_btn_riskcal').click(function () {
            /*Joyin.dialog(JoyinUtil.getLabel("FxSecrepoQuote002.riskcal"), basePath
                + "interf/cfetsup/secrepoquote/FxSecrepoQuote002/init", "99%", "99%", {
                pageModel: PAGE_MODEL_EDIT
            }, function () {
            });*/
        });

        // 绑定复制按钮事件
        $('#FxSecrepoQuote001_btn_copy').click(function () {
            doOperate(7);
        });

        // 绑定撤销按钮事件
        $('#FxSecrepoQuote001_btn_revoke').click(function () {
            var PAGE_MODEL_REVOKE = "6";
            Joyin.dialog(JoyinUtil.getLabel("FxSecrepoQuote002.revoke"), basePath
                + "interf/cfetsup/secrepoquote/FxSecrepoQuote002/init", "99%", "99%", {
                pageModel: PAGE_MODEL_REVOKE
            }, function () {
            });
        });

        // 再次发送按钮事件
        $('#FxSecrepoQuote001_btn_resend').click(function () {
            var selArr ;
            if (listTbl != null && listTbl != '') {
                selArr = listTbl.getAllCheckedRows();
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
            var object = {
                data:JSON.stringify(selArr)
            };
            Joyin.confirm(JoyinUtil.getMessage("请确定是否发送"), function () {
                //确定
                Joyin.ajaxJson(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote001/resend', object, function(result) {
                    if(result){

                    }
                });
            },function () {
                //取消
            });
            //只有交易状态为有效，回购类型为质押式协议回购才允许进行质押券置换操作
            /*if(selArr[0]["tradeStatus"]=='修改中' || selArr[0]["tradeStatus"]=='交谈中'
                || selArr[0]["tradeStatus"]=='成交中' || selArr[0]["tradeStatus"]=='交谈中'
                || selArr[0]["tradeStatus"]=='撤销中' && selArr[0]["sendStatus"]=='失败') {
                Joyin.confirm(JoyinUtil.getMessage("请确定是否发送"), function () {
                //确定
                Joyin.ajaxJson(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote001/resend', object, function(result) {
                    if(result){

                    }
                    });
                },function () {
                    //取消
                });
            } else {
                Joyin.error(JoyinUtil.getMessage("交易状态为新增中或修改中或交谈中或成交中或终止中或撤回中、发送状态为失败!"));
                return;
            }*/
        });

        /**
         * 撤销
         */
        $('#FxSecrepoQuote001_btn_revoke').click(function () {
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
            var object = {
                data:JSON.stringify(selArr)
            };
            Joyin.confirm(JoyinUtil.getMessage("请确定是否撤销"), function () {
                //确定
                Joyin.ajaxJson(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote001/revoke', object, function(result) {
                    if(result){

                    }
                });
            },function () {
                //取消
            });
        });

    });
}());