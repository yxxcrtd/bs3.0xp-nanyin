(function () {
    var avaPledgeBondDetailNeedInit = true;
    var pledgeBondDetailTblNeedInit = true;
    var getElementById = function (id) {
        return $('#' + entityName + '_' + id);
    };
    /**
     * 定义可质押券列表（Detail）
     */
    var avaPledgeBondDetailTBL = getElementById("avaPledgeBondDetailList").joyintable({
        ajax: {
            url: 'selectAvaPledgeBondDetailList',
            type: 'POST',
            data: function () {
                var param = {};
                param.portfolioId = getElementById("portfolioId").val();
                param.portfolioName = getElementById("portfolioName").val();
                param.ps = getElementById("ps").val();
                param.chlId = getElementById("chlId").val();
                param.secAcctId = getElementById("secAcctId").val();
                param.tradeMarket = getElementById("tradeMarket").val();
                param.bondType = getElementById("bondType").val() ? getElementById("bondType").val().join(",") : '';
                var bondCodeSelObject = getElementById("bondCode").select2('data')[0].object;
                param.bondCode = bondCodeSelObject == null ? '' : bondCodeSelObject.finprodMarketId;
                param.repRegistOrg = getElementById("repRegistOrg").val();
                // 获取请求参数
                return param;
            }
        },
        searchOnInit: true,
        page: false,
        loadonce: true,
        cols: [
            // 债券代码
            {
                field: 'bondCode',
                width: 150,
                title: 'BussRepoSec.avaPledgeBondDetailTBL.colmodel.bondCode',
                sortable: false
            }
            // 债券简称
            , {
                field: 'bondAbbrName',
                width: 150,
                title: 'BussRepoSec.avaPledgeBondDetailTBL.colmodel.bondAbbrName',
                sortable: false
            }
            // 券面总额
            , {
                field: 'prin',
                width: 150,
                title: 'BussRepoSec.avaPledgeBondDetailTBL.colmodel.prin',
                sortable: false,
                type: 'money',
                digit: 2,
            }
            // 已质押券面总额
            , {
                field: 'pledgePrin',
                width: 150,
                title: 'BussRepoSec.avaPledgeBondDetailTBL.colmodel.pledgePrin',
                sortable: false,
                type: 'money',
                digit: 2,
            }
            // 已质押券面总额（初始）
            , {
                field: 'pledgePrinInit',
                width: 150,
                sortable: false,
                type: 'money',
                digit: 2,
                visible: false
            }
            // 可质押券面总额
            , {
                field: 'avaPledgePrin',
                width: 150,
                title: 'BussRepoSec.avaPledgeBondDetailTBL.colmodel.avaPledgePrin',
                sortable: false, type: 'money',
                digit: 2,
            }
            // 组合代码
            , {
                field: 'portfolioId',
                width: 150,
                title: 'BussRepoSecPtl.portfolioId',
                sortable: false, type: 'money',
                edit:false,
                digit: 2,
                visible:false,
            }
         // 组合名称
            , {
                field: 'portfolioName',
                title: 'BussRepoSecPtl.portfolioName',
                sortable: false, type: 'money',
                edit:false,
                digit: 2,
                visible:false,
            }
        ],
        done:function () {
            if(avaPledgeBondDetailNeedInit){
                avaPledgeBondDetailNeedInit = false;
                var ps = getElementById("ps").val();
                if(BACK_LEND_DIRECTION_04 == ps){
                    avaPledgeBondDetailTBL.showColumns([{field:'prin',visible:false},{field:'pledgePrin',visible:false},{field:'pledgePrinInit',visible:false},{field:'avaPledgePrin',visible:false}]);
                }
            }
        }
    });

    var tempPledgePrin;

    /**
     * 定义质押券列表（Detail）
     */
    var pledgeBondDetailTbl = getElementById("pledgeBondDetailList").joyintable({
        data: (entity && entity.pledgeBondList) ? entity.pledgeBondList : [],
        page: false,
        loadonce: true,
        cols: [
            // 债券代码
            {field: 'bondCode', width: 150, title: 'BussRepoSec.pledgeBondDetailTbl.colmodel.bondCode', sortable: false}
            // 债券简称
            , {
                field: 'bondAbbrName',
                width: 150,
                title: 'BussRepoSec.pledgeBondDetailTbl.colmodel.bondAbbrName',
                sortable: false
            }
            // 债券类型
            ,{field: 'secType',title: 'BussRepoSecPtl.secType',type: 'money',align: 'left',edit: false,sortable: false,visible:false}
            // 可质押券面总额
            , {
                field: 'avaPledgePrin',
                width: 150,
                title: 'BussRepoSec.pledgeBondDetailTbl.colmodel.avaPledgePrin',
                type: 'money',
                digit: 2,
                sortable: false
            }
            // 入库券面总额
            , {
                field: 'pledgePrin',
                width: 150,
                title: 'BussRepoSec.pledgeBondDetailTbl.colmodel.pledgePrin',
                type: 'money',
                digit: 2,
                edit: true,
                sortable: false
            }
            // 入库券面总额（初始）
            , {
                field: 'pledgePrinInit',
                width: 150,
                type: 'money',
                digit: 2,
                sortable: false,
                visible: false
            }
            // 首次净价
            , {
                field: 'firstNetPrice',
                width: 100,
                title: 'BussRepoSec.pledgeBondDetailTbl.colmodel.firstNetPrice',
                sortable: false
            }
            // 质押比例
            , {
                field: 'pledgeRatio',
                width: 100,
                title: 'BussRepoSec.pledgeBondDetailTbl.colmodel.pledgeRatio',
                type: 'number',
                digit: 2,
                edit: true,
                sortable: false
            }
            // 组合代码
            , {
                field: 'portfolioId',
                width: 150,
                title: 'BussRepoSecPtl.portfolioId',
                sortable: false, type: 'money',
                edit:false,
                digit: 2,
                visible:false,
            }
         // 组合名称
            , {
                field: 'portfolioName',
                width: 150,
                title: 'BussRepoSecPtl.portfolioName',
                sortable: false, type: 'money',
                edit:false,
                digit: 2,
                visible:false,
            }
        ],
        beforeEditCell: function (preVal, field, rowData) {
            if (field === "pledgePrin") {
                tempPledgePrin = preVal.replace(/\,/g, '');
            }
            return true;
        },
        editCell: function (value, data, field, cell, column) {
            if (field === "pledgePrin" && getElementById("ps").val() == BACK_LEND_DIRECTION_03 && data.pledgePrin * 1 > data.avaPledgePrin * 1) {
                var datas = pledgeBondDetailTbl.getAllRows();
                datas[data.LAY_TABLE_INDEX].pledgePrin = tempPledgePrin;
                pledgeBondDetailTbl.reload({data: datas});
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0003",
                    [JoyinUtil.getLabel("BussRepoSec.pledgeBondDetailTbl.colmodel.pledgePrin"),
                        JoyinUtil.getLabel("BussRepoSec.pledgeBondDetailTbl.colmodel.avaPledgePrin")]));
            }
        },
        done:function () {
            if(pledgeBondDetailTblNeedInit){
                pledgeBondDetailTblNeedInit = false;
                var ps = getElementById("ps").val();
                if(BACK_LEND_DIRECTION_04 == ps){
                    pledgeBondDetailTbl.showColumns([{field:'avaPledgePrin',visible:false}
                    ,{field:'pledgePrin',visible:true,title:'BussRepoSec.pledgeBondDetailTbl.colmodel.pledgePrinTotle'}]);
                }
            }
        }
    });

    /**
     * 校验行数据中登记托管机构是否不一致
     * @param array
     * @returns {boolean}
     */
    var checkIsDifferent = function (array) {
        var isDifferent = false;
        if (array != null && array.length > 1) {
            for (var index in array) {
                if (index < array.length - 1
                    && array[index].repRegistOrg != array[index * 1 + 1].repRegistOrg) {
                    isDifferent = true;
                    break;
                }
            }
        }
        return isDifferent;
    };

    /**
     * 弹出债券新建画面
     * @returns
     */
    function showSecCreateDialog() {
        Joyin.dialog(JoyinUtil.getLabel('FinBondTargetAsset002.newTitle'), '/invest/asset/bond/FinBondTargetAsset002/init', '98%', '98%');
    }

    /**
     * 加入按钮按下
     * @param array
     * @returns {boolean}
     */
    var joyinProcess = function () {
        var avaSelectedRows = avaPledgeBondDetailTBL.getAllCheckedRows();
        var pledgeAllRows = pledgeBondDetailTbl.getAllRows();
        if (avaSelectedRows != null && avaSelectedRows.length > 0) {
            // 若添加的质押券不只一个，且登记托管机构不全相同，则保存质押券时提示质押券登记托管机构不同，不允许保存；
            // 如果 选择的待质押劵 和质押劵列表中债券托管机构不一致 则 提示债券托管机构不一致，不能添加！
            var isDuplicated = false;
            var mergeArray = new Array();
            mergeArray = _.concat(mergeArray, avaSelectedRows);
            mergeArray = _.concat(mergeArray, pledgeAllRows);
            if (checkIsDifferent(mergeArray)) {
                Joyin.error(JoyinUtil.getMessage("TRD_E_0007"));
                return false;
            }

            // 如果 选择的待质押劵 在质押劵列表中存在  则 提示 是否覆盖  质押劵列表中债券
            var isConfirm = false;
            var confirmMessageArray = new Array();

            // 倒序删除，正序会存在问题
            for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                for (var j in pledgeAllRows) {
                    if (pledgeAllRows[j].bondCode == avaSelectedRows[i].bondCode) {
                        isConfirm = true;
                        confirmMessageArray.push(avaSelectedRows[i].bondCode);
                        break;
                    }
                }
            }
            if (isConfirm) {
                Joyin.confirm(JoyinUtil.getMessage("TRD_C_0003", confirmMessageArray.join("，")), function () {
                    // 倒序删除，正序会存在问题
                    for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                        for (var j in pledgeAllRows) {
                            // 覆盖质押券列表中重复记录
                            if (pledgeAllRows[j].bondCode == avaSelectedRows[i].bondCode) {
                                pledgeBondDetailTbl.removeRow(pledgeAllRows[j]);
                            }
                        }
                        avaPledgeBondDetailTBL.removeRow(avaSelectedRows[i]);
                        avaSelectedRows[i].pledgePrin = avaSelectedRows[i].avaPledgePrin;
                        if(avaSelectedRows[i].firstNetPrice != null && avaSelectedRows[i].firstNetPrice != '')
                        {
                            avaSelectedRows[i].pledgeRatio = (Number(avaSelectedRows[i].firstNetPrice) / 100 * 100).toFixed(2);
                        }
                        if(avaSelectedRows[i].pledgeRatio == null || avaSelectedRows[i].pledgeRatio == ''){
                            avaSelectedRows[i].pledgeRatio = 100;
                        }
                        pledgeBondDetailTbl.addRow(avaSelectedRows[i]);
                    }
                });
            } else {
                // 倒序删除，正序会存在问题
                for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                    avaPledgeBondDetailTBL.removeRow(avaSelectedRows[i]);
                    avaSelectedRows[i].pledgePrin = avaSelectedRows[i].avaPledgePrin;
                    if(avaSelectedRows[i].firstNetPrice != null && avaSelectedRows[i].firstNetPrice != '') {
                        avaSelectedRows[i].pledgeRatio = (Number(avaSelectedRows[i].firstNetPrice) / 100 * 100).toFixed(2);
                    }
                    if (avaSelectedRows[i].pledgeRatio == null || avaSelectedRows[i].pledgeRatio == ''){
                        avaSelectedRows[i].pledgeRatio = 100;
                    }
                    pledgeBondDetailTbl.addRow(avaSelectedRows[i]);
                }
            }
        }
    };

    /**
     * 移除按钮按下
     * @param array
     * @returns {boolean}
     */
    var removeProcess = function () {
        var avaRows = avaPledgeBondDetailTBL.getAllRows();
        var pledgeAllRows = pledgeBondDetailTbl.getAllCheckedRows();
        if (pledgeAllRows != null && pledgeAllRows.length > 0) {
            // 倒序删除，正序会存在问题
            for (var i = pledgeAllRows.length - 1; i >= 0; i--) {
                var isAvaHave = false;
                // 删除可用质押券列表中相同记录
                for (var j in avaRows) {
                    if (avaRows[j].bondCode == pledgeAllRows[i].bondCode) {
                        isAvaHave = true;
                        break;
                    }
                }
                if(!pledgeAllRows[i]["pledgePrin"])
                {
                    pledgeAllRows[i]["pledgePrin"] = null;
                }
                pledgeBondDetailTbl.removeRow(pledgeAllRows[i]);
                if (!isAvaHave) {
                    pledgeAllRows[i].pledgePrin = pledgeAllRows[i].pledgePrinInit;
                    avaPledgeBondDetailTBL.addRow(pledgeAllRows[i]);
                }
            }
        }
    };

    /**
     * 保存按钮按下
     *
     * @param array
     * @returns {boolean}
     */
    var addPledgeBond = function () {
        var pledgeAllRows = pledgeBondDetailTbl.getAllRows();
        if (checkIsDifferent(pledgeAllRows)) {
            Joyin.error(JoyinUtil.getMessage("TRD_E_0007"));
            return false;
        }
        window.parent.BussRepoSecPtl[entityName].addPledgeBond(pledgeAllRows);
        var index = window.parent.layer.getFrameIndex(window.name);
        window.parent.layer.close(index);
    };

    /**
     * 债券代码change事件
     */
    var bongCodeChange = function(){
        if(getElementById("bondCode").val() != null && getElementById("bondCode").val() != ''){
            var object = {};object.finprodId = getElementById("bondCode").val();
            Joyin.ajaxJson(basePath + 'invest/asset/bond/FinBondTargetAsset002/getBasicInfo',object, function(
                result) {
                getElementById("repRegistOrg").val(result.registOrg).attr('disabled',true).trigger('change');
            })
        }
    }

    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化时，直接加载质押券列表
        var portfolioId=$("#bussRepoSecPtl_portfolioId").val();
        pledgeBondDetailTbl.load();
        // 添加检索事件
        getElementById("btn_search").click(function () {
            // 检索可质押债券列表
            avaPledgeBondDetailTBL.load();
        });
        // 债券新建按钮
        getElementById("btn_add").click(showSecCreateDialog);
        // 加入按钮事件
        getElementById("btn_joyin").click(joyinProcess);
        // 移除按钮事件
        getElementById("btn_remove").click(removeProcess);
        // 保存按钮事件
        getElementById("save").click(addPledgeBond);
        //增加债券代码change事件
        getElementById("bondCode").change(function(){
            bongCodeChange();
        })
    });
}());