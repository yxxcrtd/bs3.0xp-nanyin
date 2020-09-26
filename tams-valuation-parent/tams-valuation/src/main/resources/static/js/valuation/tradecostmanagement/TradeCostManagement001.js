$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            bookSetId: $.trim($('#bookSetId').val()),//账套代码
            feeTypes: $.trim($('#feeType').val()),//费用类型List
            payDate: $.trim($('#payDate').val())//支付日期 交易日
        }
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

        //表格双击事件
        dblclick: function (table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("明细展示"),
                JoyinUtil.getRealPath("valuation/tradecostmanagement/TradeCostManagement004/init"),
                "95%", "95%", data, function () {
                    table.reload();
                }
            );
        },

        cols: [
            {field: 'tradeId', title: '交易编号', sort: true, visible: false}, // 隐藏域
            {field: 'bookSetId', title: '账套代码', sort: true},
            {field: 'bookSetName', title: '账套名称', sort: true, sortField: 'bookSetId'},
            {field: 'feeType', sortField: 'feeType', title: '费用类型', sort: true, visible:false},
            {field: 'feeTypeFmt', sortField: 'feeType', title: '费用类型', sort: true},
            {field: 'payDate', title: '支付日期', sort: true},
            {field: 'mDate', title: '应付日期', sort: true},
            {field: 'registOrgFmt', sortField: 'registOrg', title: '登记托管机构', sort: true},
            {field: 'registOrg', sortField: 'registOrg', title: '登记托管机构', sort: true, visible:false},
            {field: 'feeAmt', title: '金额', sort: true,type: 'money'},// TODO 金额type
            {field: 'ccyFmt', sortField: 'ccy', title: '币种', sort: true},
            {field: 'isPrepaidFmt', sortField: 'isPrepaid', title: '是否待摊', sort: true},
            {field: 'isPrepaid', sortField: 'isPrepaid', title: '是否待摊', sort: true,  visible:false},
            {field: 'remark', title: '备注', sort: true},
            // {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ],
    });


    // 表格导出
    var exportExcel = function () {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas: datas
            });
        }
    };


    // 删除
    var deleteData = function () {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("没有选中数据"));
            return;
        }
        for (let i = 0; i < selArr.length; i++) {
            selArr[i].operateType = "delete";
        }
        var object = {};
        object.tradeCostManagementDtoList = selArr;
        Joyin.ajaxJson(basePath + 'valuation/tradecostmanagement/TradeCostManagement001/deleteCheck', selArr, function (result) {
            if (result.length > 0) {//提取已预提待摊的账套信息
                var str = '';
                for (let i = 0; i < result.length; i++) {
                    str += (" "+ result[i]+'账套');
                }
                str += '产品交易费用数据已维护预提待摊设置，是否继续删除？';
                Joyin.confirm(JoyinUtil.getMessage(str, JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'valuation/tradecostmanagement/TradeCostManagement001/delete', object,
                        'TradeCostManagement001_btn_delete','TradeCostManagement003',
                        function (data) {
                        table.reload();
                    });
                });
            } else {//没有待摊
                var message = "是否确认删除所选交易费用数据？";
                Joyin.confirm(JoyinUtil.getMessage(message, JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'valuation/tradecostmanagement/TradeCostManagement001/delete', object,
                        'TradeCostManagement001_btn_delete','TradeCostManagement003',
                        function (data) {
                        table.reload();
                    });
                });
            }
        })

    };

    /**
     * 画面加载完成
     */

    $(function () {

        // 绑定查询按钮事件
        $('#TradeCostManagement001_btn_search').click(function () {
            table.load();
        });
        //  绑定导出按钮事件
        $('#TradeCostManagement001_btn_export').click(function () {
            exportExcel();
        });
        // 绑定删除按钮事件
        $('#TradeCostManagement001_btn_delete').click(function () {
            deleteData();
        });

        // 绑定新建按钮事件
        $('#TradeCostManagement001_btn_add').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("新建交易费用（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                basePath + "valuation/tradecostmanagement/TradeCostManagement002/init",
                "95%", "95%", {pageModel: PAGE_MODEL_NEW}, function () {
                    table.reload();
                });
        });

        // 绑定修改按钮事件
        $('#TradeCostManagement001_btn_edit').click(function () {

            var rows = table.getAllCheckedRows();
            if (rows.length != 1) {
                Joyin.error(JoyinUtil.getMessage('请选择一条数据'));
                return;
            }
            var object = rows[0];
            object.pageModel = PAGE_MODEL_EDIT;

            Joyin.ajaxJson(basePath + 'valuation/tradecostmanagement/TradeCostManagement001/editCheck', object, function (result) {
                if (result.data) {//判断当前交易费用是否预提待摊
                    Joyin.error(JoyinUtil.getMessage('此产品交易费用数据已维护待摊设置，如需修改请删除对应流水！'));
                    return;
                } else {
                    Joyin.dialog(JoyinUtil.getLabel("修改交易费用（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                        basePath + "valuation/tradecostmanagement/TradeCostManagement003/init",
                        "95%", "95%", object, function () {
                            table.reload();
                        });
                }
            });

        });
    });

});

