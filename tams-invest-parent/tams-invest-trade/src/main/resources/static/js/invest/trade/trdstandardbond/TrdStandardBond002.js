(function () {
    /**
     * 定义可质押债券列表
     */
    var avaPledgeBondTBL = $("#avaPledgeBondList").joyintable({
        // ajax请求数据
        ajax: {
            // 请求路径
            url: basePath+'invest/trade/trdstandardbond/TrdStandardBond002/selectAvaPledgeBondList', //请求路径
            // 请求类型
            type: 'POST',
            data: function () {
                var param = {};

                param.portfolioId = $("#portfolioId").val();
                param.bondCode = $("#bondCode").val() ? $("#bondCode").val().join(',') : '';
                param.tradePlace = $("#tradePlace").val();
                param.secAcctId = $("#secAcctId").val();

                // 获取请求参数
                return param;
            }
        },
        page: false,
        loadonce: true,
        searchOnInit:pageModel === PAGE_MODEL_NEW,
        height:200,
        /**
         * 定义表头
         */
        cols: [
            {field: 'portfolioId', width: 150, title: 'TrdStandardBond002.avaColModel.portfolioId'}
            // 债券代码
            ,{field: 'bondCode', width: 150, title: 'TrdStandardBond002.avaColModel.bondCode'}
            // 债券简称
            , {field: 'bondName', width: 150, title: 'TrdStandardBond002.avaColModel.bondName'}
            // 交易场所
            , {field: 'tradePlaceText', width: 150, title: 'TrdStandardBond002.avaColModel.tradePlace'}
            // 券面总额
            , {
                field: 'positionTotal',
                width: 150,
                title: 'TrdStandardBond002.avaColModel.positionTotal',
                sortable: false,
                type: 'money',
                digit: 2
            }
            // 不可入库券面总额
            , {
                field: 'pledgePrinAmt',
                width: 150,
                title: 'TrdStandardBond002.avaColModel.pledgePrinAmt',
                sortable: false,
                type: 'money',
                digit: 2
            }
            // 可入库券面总额
            , {
                field: 'avaPledgePrinAmt',
                width: 150,
                title: 'TrdStandardBond002.avaColModel.avaPledgePrinAmt',
                sortable: false,
                type: 'money',
                digit: 2
            }
            // 已质押券面总额（初始）
            , {
                field: 'pledgePrinAmtInit',
                sortable: false,
                type: 'money',
                digit: 2,
                visible: false
            }
            , {field: 'portfolioId', visible: false}
            , {field: 'tradePlace', visible: false}
            , {field: 'secAcctId', visible: false}
            //, {field: 'AvaStrdPrinAmt', visible: false}

        ]
    });

    var tempPledgePrinAmt;
    var tempInStdrdPrinAmt;

    /**
     * 定义质押券列表
     */
    var pledgeBondTbl = $("#pledgeBondList").joyintable({
        page: false,
        loadonce: true,
        height:200,
        cols: [
            {field: 'portfolioId', width: 150, title: 'TrdStandardBond002.pledgeColModel.portfolioId'}
            // 债券代码
            ,{field: 'bondCode', width: 150, title: 'TrdStandardBond002.pledgeColModel.bondCode', sortable: false}
            // 债券简称
            , {field: 'bondName', width: 150, title: 'TrdStandardBond002.pledgeColModel.bondName', sortable: false}
            // 交易场所
            , {
                field: 'tradePlaceText',
                width: 150,
                title: 'TrdStandardBond002.pledgeColModel.tradePlace',
                sortable: false
            }
            // 可质押券面总额
            , {
                field: 'avaPledgePrinAmt',
                width: 150,
                title: 'TrdStandardBond002.pledgeColModel.avaPledgePrinAmt',
                sortable: false,
                type: 'money',
                digit: 2
            }
            // 入库券面总额
            , {
                field: 'pledgePrinAmt',
                width: 150,
                title: 'TrdStandardBond002.pledgeColModel.pledgePrinAmt',
                sortable: false,
                type: 'money',
                edit: true,
                digit: 2
            }
            // 折算率%
            , {
                field: 'discountRate',
                width: 150,
                title: 'TrdStandardBond002.pledgeColModel.discountRate',
                sortable: false,
                type: 'number',
                digit: 2
            }
            // 入库标准券总额
            , {
                field: 'inStdrdPrinAmt',
                width: 150,
                title: 'TrdStandardBond002.pledgeColModel.inStdrdPrinAmt',
                sortable: false,
                type: 'money',
                edit: true,
                digit: 2
            }
            , {field: 'portfolioId', visible: false}
            , {field: 'tradePlace', visible: false}
            , {field: 'secAcctId', visible: false}
        ],
        beforeEditCell: function (preVal, field, rowData) {
            if (field === "pledgePrinAmt") {
                tempPledgePrinAmt = preVal.replace(/\,/g, '');
            }
            if (field === "inStdrdPrinAmt") {
                tempInStdrdPrinAmt = preVal.replace(/\,/g, '');
            }
            return true;
        },
        editCell: function (value, data, field, cell, column) {
            if (field === "pledgePrinAmt") {
                if (data.pledgePrinAmt * 1 > data.avaPledgePrinAmt * 1) {
                    var datas = pledgeBondTbl.getAllRows();
                    datas[data.LAY_TABLE_INDEX].pledgePrinAmt = tempPledgePrinAmt;
                    pledgeBondTbl.reload({data: datas});
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0003",
                        [JoyinUtil.getLabel("TrdStandardBond002.pledgeColModel.pledgePrinAmt"),
                            JoyinUtil.getLabel("TrdStandardBond002.pledgeColModel.avaPledgePrinAmt")]));
                } else {
                    var datas = pledgeBondTbl.getAllRows();
                    var pledgePrinAmt = data.pledgePrinAmt;
                    var discountRate = data.discountRate;
                    if (pledgePrinAmt != null && pledgePrinAmt != "" && discountRate != null && discountRate != "") {
                        datas[data.LAY_TABLE_INDEX].inStdrdPrinAmt = (Number(pledgePrinAmt) * Number(discountRate) / Number(100)).toFixed(2);
                        pledgeBondTbl.reload({data: datas});
                    }
                    else {
                        if (!pledgePrinAmt) {
                            datas[data.LAY_TABLE_INDEX].pledgePrinAmt = 0;
                            datas[data.LAY_TABLE_INDEX].inStdrdPrinAmt = 0;
                            pledgeBondTbl.reload({data: datas});
                        }
                    }
                }
            } else if (field === "inStdrdPrinAmt") {
                var datas = pledgeBondTbl.getAllRows();
                var inStdrdPrinAmt = data.inStdrdPrinAmt;
                var discountRate = data.discountRate;
                var newPledgePrinAmt = 0;
                if (inStdrdPrinAmt != null && inStdrdPrinAmt != "" && discountRate != null && discountRate != "") {
                    newPledgePrinAmt = (Number(inStdrdPrinAmt) / (Number(discountRate) / Number(100))).toFixed(2);
                }
                if (newPledgePrinAmt * 1 > data.avaPledgePrinAmt * 1) {
                    var datas = pledgeBondTbl.getAllRows();
                    datas[data.LAY_TABLE_INDEX].pledgePrinAmt = tempInStdrdPrinAmt;
                    pledgeBondTbl.reload({data: datas});
                    Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0003",
                        [JoyinUtil.getLabel("TrdStandardBond002.pledgeColModel.pledgePrinAmt"),
                            JoyinUtil.getLabel("TrdStandardBond002.pledgeColModel.avaPledgePrinAmt")]));
                } else {
                    var datas = pledgeBondTbl.getAllRows();
                    datas[data.LAY_TABLE_INDEX].pledgePrinAmt = newPledgePrinAmt;
                    pledgeBondTbl.reload({data: datas});
                }
            }
        }
    });

    /**
     * 校验行数据中证券账户是否不一致
     * @param array
     * @returns {boolean}
     */
    var checkIsDifferent = function (array) {
        var isDifferent = false;
        if (array != null && array.length > 1) {
            for (var index in array) {
                if (index < array.length - 1
                    && array[index].secAcctId != array[index * 1 + 1].secAcctId) {
                    isDifferent = true;
                    break;
                }
            }
        }
        return isDifferent;
    };

    /**
     * 校验折算率不能为空
     */
    var checkDiscountRate = function (array) {
        var isnull = false;
        $.each(array, function (i, v) {
                if (!v.discountRate) {
                    isnull = true;
                    return isnull;
                }
        });
        return isnull;
    }

    /**
     * 加入按钮按下
     * @param array
     * @returns {boolean}
     */
    var joyinProcess = function () {
        var avaSelectedRows = avaPledgeBondTBL.getAllCheckedRows();
        var pledgeAllRows = pledgeBondTbl.getAllRows();
        if (avaSelectedRows != null && avaSelectedRows.length > 0) {
            // 若添加的质押券不只一个，且登记托管机构不全相同，则保存质押券时提示质押券登记托管机构不同，不允许保存；
            // 如果 选择的待质押劵 和质押劵列表中债券托管机构不一致 则 提示债券托管机构不一致，不能添加！
            var isDuplicated = false;
            var mergeArray = new Array();
            mergeArray = _.concat(mergeArray, avaSelectedRows);
            mergeArray = _.concat(mergeArray, pledgeAllRows);
            if (checkIsDifferent(mergeArray)) {
                Joyin.error(JoyinUtil.getMessage("TRD_E_0010"));
                return false;
            }
            // 如果 选择的待质押劵在质押劵列表中存在  则 提示 是否覆盖  质押劵列表中债券
            var isConfirm = false;
            var confirmMessageArray = new Array();

            // 倒序删除，正序会存在问题
            for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                for (var j in pledgeAllRows) {
                    if (pledgeAllRows[j].portfolioId == avaSelectedRows[i].portfolioId
                        && pledgeAllRows[j].tradePlace == avaSelectedRows[i].tradePlace
                        && pledgeAllRows[j].secAcctId == avaSelectedRows[i].secAcctId
                        && pledgeAllRows[j].bondCode == avaSelectedRows[i].bondCode) {
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
                            if (pledgeAllRows[j].portfolioId == avaSelectedRows[i].portfolioId
                                && pledgeAllRows[j].tradePlace == avaSelectedRows[i].tradePlace
                                && pledgeAllRows[j].secAcctId == avaSelectedRows[i].secAcctId
                                && pledgeAllRows[j].bondCode == avaSelectedRows[i].bondCode) {
                                pledgeBondTbl.removeRow(pledgeAllRows[j]);
                            }
                        }
                        avaPledgeBondTBL.removeRow(avaSelectedRows[i]);
                        avaSelectedRows[i].pledgePrinAmt = avaSelectedRows[i].avaPledgePrinAmt;
                        if (avaSelectedRows[i].discountRate != null && avaSelectedRows[i].discountRate != ''
                            && avaSelectedRows[i].avaPledgePrinAmt != null && avaSelectedRows[i].avaPledgePrinAmt != '') {
                            avaSelectedRows[i].inStdrdPrinAmt = (Number(avaSelectedRows[i].avaPledgePrinAmt) * Number(avaSelectedRows[i].discountRate) / Number(100)).toFixed(2);
                        }
                        pledgeBondTbl.addRow(avaSelectedRows[i]);
                    }
                });
            } else {
                // 倒序删除，正序会存在问题
                for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                    avaPledgeBondTBL.removeRow(avaSelectedRows[i]);
                    avaSelectedRows[i].pledgePrinAmt = avaSelectedRows[i].avaPledgePrinAmt;
                    if (avaSelectedRows[i].discountRate != null && avaSelectedRows[i].discountRate != ''
                        && avaSelectedRows[i].avaPledgePrinAmt != null && avaSelectedRows[i].avaPledgePrinAmt != '') {
                        avaSelectedRows[i].inStdrdPrinAmt = (Number(avaSelectedRows[i].avaPledgePrinAmt) * Number(avaSelectedRows[i].discountRate) / Number(100)).toFixed(2);
                    }
                    pledgeBondTbl.addRow(avaSelectedRows[i]);
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
        var avaRows = avaPledgeBondTBL.getAllRows();
        var pledgeAllRows = pledgeBondTbl.getAllCheckedRows();
        if (pledgeAllRows != null && pledgeAllRows.length > 0) {
            // 倒序删除，正序会存在问题
            for (var i = pledgeAllRows.length - 1; i >= 0; i--) {
                var isAvaHave = false;
                // 删除可用质押券列表中相同记录
                for (var j in avaRows) {

                    if (avaRows[j].portfolioId == pledgeAllRows[i].portfolioId
                        && avaRows[j].tradePlace == pledgeAllRows[i].tradePlace
                        && avaRows[j].secAcctId == pledgeAllRows[i].secAcctId
                        && avaRows[j].bondCode == pledgeAllRows[i].bondCode) {
                        isAvaHave = true;
                        break;
                    }
                }
                if (!pledgeAllRows[i]["pledgePrinAmt"]) {
                    pledgeAllRows[i]["pledgePrinAmt"] = 0;
                }
                pledgeBondTbl.removeRow(pledgeAllRows[i]);
                if (!isAvaHave) {
                    pledgeAllRows[i].pledgePrinAmt = pledgeAllRows[i].pledgePrinAmtInit;
                    avaPledgeBondTBL.addRow(pledgeAllRows[i]);
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
    var saveOrSubmit = function (operateType) {
        var pledgeAllRows = pledgeBondTbl.getAllRows();
        if(pledgeAllRows.length==0){
            Joyin.error(JoyinUtil.getMessage("TRD_BOND_STANDAR_001"));
            return false;
        }
        if (checkIsDifferent(pledgeAllRows)) {
            Joyin.error(JoyinUtil.getMessage("TRD_E_0010"));
            return false;
        }
        //折算率为空不允许提交 start yjm 2019-07-04
        if (checkDiscountRate(pledgeAllRows)){
            Joyin.error(JoyinUtil.getMessage("TRD_BOND_STANDAR_004"));
            return false;
        } 
        
        //折算率为空不允许提交 end yjm 2019-07-04
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.pledgeBondList = pledgeBondTbl.getAllRows();
        object.avaPledgeBondList = avaPledgeBondTBL.getAllRows();
        //TISHKBS-1849:【投资管理-质押式回购交易】标准券管理，质押券入库，提交时报错
        delete object.bondCode;
        //TISHKBS-1849:【投资管理-质押式回购交易】标准券管理，质押券入库，提交时报错
        Joyin.ajaxJson(basePath + 'invest/trade/trdstandardbond/TrdStandardBond002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/trade/trdstandardbond/TrdStandardBond002/save', object, "TrdStandardBond002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/trade/trdstandardbond/TrdStandardBond002/save', object, "TrdStandardBond002_btn_submit");
                }
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {
        pledgeBondTbl.load();

        // 工作流查看时，直接加载质押券列表
        if (pledgeBondList) {
            pledgeBondTbl.reload({data: JSON.parse(pledgeBondList), url: null});
        }
        if (avaPledgeBondList) {
            
            avaPledgeBondTBL.load();
            avaPledgeBondTBL.reload({data: JSON.parse(avaPledgeBondList), url: null});
        }else{
            avaPledgeBondTBL.reload();
        }
        // 检索按钮事件
        $("#TrdStandardBond002_btn_search").click(function () {
            avaPledgeBondTBL.load();
        });
        // 加入按钮事件
        $("#TrdStandardBond002_btn_joyin").click(joyinProcess);
        // 移除按钮事件
        $("#TrdStandardBond002_btn_remove").click(removeProcess);
        // 保存按钮事件
        $("#TrdStandardBond002_btn_save").click(function () {
            saveOrSubmit('save');
        });
        // 提交按钮事件
        $("#TrdStandardBond002_btn_submit").click(function () {
            saveOrSubmit('submit');
        });
        // 重新初始化证券账户
        if (!$('#secAcctId').val() && secAcctId) {
            $('#secAcctId').textSelect('setValue', secAcctId);
        }
    });
}());