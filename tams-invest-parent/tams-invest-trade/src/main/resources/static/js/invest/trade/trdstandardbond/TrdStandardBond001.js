(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var param = {};pledgeStdrdPrinAmtSum
        // 组合代码
        param.portfolioId = $('#portfolioId').val();
        // 债券code
        if ($("#bondCode").val()) {
            param.bondCode = $("#bondCode").val().join(",");
        } else {
            param.bondCode = null;
        }
        // 证券账户
        param.secAcctId = $("#secAcctId").val();
        // 查询日期
        if ($('#searchDate').val()) {
            param.searchDate = $('#searchDate').val();
        }else{
            param.searchDate =platDate;
        }
        
        param.tradePlace = $('#tradePlace').val();
        return param;
    };
    /**
     * 定义数据表格
     */

    var table = $("#dataTable").joyintable({
        // 获取请求参数
        ajax: {
            // 请求路径
            url: 'selectPageData',
            // 请求类型
            type: 'POST',
            data: function () {
                // 获取请求参数
                return getSearchParams();
            }
        },
        /**
         * 定义表头
         */
        cols: [
            // 组合代码
            {field: 'portfolioId', title: 'TrdStandardBond001.colModel.portfolioId', sortField: 'portfolioId'}
            // 债券代码
            , {field: 'bondCode', title: 'TrdStandardBond001.colModel.bondCode', sortField: 'bondCode'}
            // 债券名称
            , {field: 'bondName', title: 'TrdStandardBond001.colModel.bondName', sortField: 'bondName'}
            // 交易场所
            , {field: 'tradePlaceText', title: 'TrdStandardBond001.colModel.tradePlace', sortField: 'tradePlace'}
            // 债券券面总额
            , {
                field: 'positionTotal',
                title: 'TrdStandardBond001.colModel.positionTotal',
                type: 'money',
                sortField: 'positionTotal'
            }
            // 入出库券面总额
            , {
                field: 'inTotalPrinAmt',
                title: 'TrdStandardBond001.colModel.inTotalPrinAmt',
                type: 'money',
                sortField: 'inTotalPrinAmt'
            }
            // 折算率%
            , {
                field: 'discountRate',
                title: 'TrdStandardBond001.colModel.discountRate',
                type: 'number',
                sortField: 'discountRate'
            }
            // 标准券额度
            , {
                field: 'stdrdPrinAmt',
                title: 'TrdStandardBond001.colModel.stdrdPrinAmt',
                type: 'money',
                sortField: 'stdrdPrinAmt'
            }
            , {field: 'tradePlace', visible: false}
            , {field: 'cdate', visible: false}
            , {field: 'secAcctId', visible: false}
            // 已使用标准券总额度
            , {field: 'pledgeStdrdPrinAmt', visible: false}
            // 可使用标准券总额度
            , {field: 'avaStrdPrinAmt', visible: false}
        ],
        done: function (table, allData) {
            if (table != null && table[0] != null) {
                if (table[0].inTotalPrinAmtSum != null) {
                    // 质押券总额度
                    $("#inTotalPrinAmtSum").html(JoyinUtil.formatNumber(table[0].inTotalPrinAmtSum, 3, null, 2));
                }
                if (table[0].stdrdPrinAmtSum != null) {
                    // 标准券额度
                    $("#stdrdPrinAmtSum").html(JoyinUtil.formatNumber(table[0].stdrdPrinAmtSum, 3, null, 2));
                }
                if (table[0].pledgeStdrdPrinAmtSum != null) {
                    // 已使用标准券总额度
                    $("#pledgeStdrdPrinAmtSum").html(JoyinUtil.formatNumber(table[0].pledgeStdrdPrinAmtSum, 3, null, 2));
                }
                if (table[0].avaStrdPrinAmtSum != null) {
                    // 可使用标准券总额度
                    $("#avaStrdPrinAmtSum").html(JoyinUtil.formatNumber(table[0].avaStrdPrinAmtSum, 3, null, 2));
                }
            }
        }
    });
    // 给子画面使用
    window.dataTable = table;

    /**
     * 弹出入库画面
     * @returns
     */
    var inDialog = function () {
        var param = {};
        param.portfolioId = $("#portfolioId").val();
        param.tradePlace = $("#tradePlace").val();
        param.secAcctId = $("#secAcctId").val();
        Joyin.dialog(JoyinUtil.getLabel('TrdStandardBond002'), '../TrdStandardBond002/init', '98%', '98%', param);
    };

    /**
     * 弹出出库画面
     * @returns
     */
    var outDialog = function () {
        //
        var selArr;
        if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
        }
        // 列表中未勾选
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        //遍历选中列表，非平台日不能出库
        var flag = false;
        $.each(selArr, function(id,value){
            if (value.cdate != platDate) {
                flag = true;
                return false;
            }
            
        });
        if (flag) {
            Joyin.error(JoyinUtil.getMessage("TRD_STAND_0001"));
            return;
        }
        $.each(selArr, function(id, value){
            if (value.inTotalPrinAmt == 0){
                flag = true;
                return false;
            }
        });
        if (flag){
            Joyin.error('质押券为0不能出库！');
            return;
        }

        if (selArr.length > 20) {
            Joyin.error("出库券最多不能同时操作20个以上");
            return;
        }
        var bondCodes = [];
        for (var row in selArr) {
            bondCodes.push(selArr[row].bondCode);
        }
        var portfolioIds = [];
        for (var row in selArr) {
            portfolioIds.push(selArr[row].portfolioId);
        }
       $.each(selArr, function(id,value){
            if (value.cdate != platDate) {
                flag = true;
                return false;
            }
            
        });

        var isDifferent = checkIsDifferent(selArr);
        if (isDifferent) {
            Joyin.error(JoyinUtil.getMessage(isDifferent));
            return false;
        }
        var param = {};
        param.portfolioId = $("#portfolioId").val();
        param.tradePlace = $("#tradePlace").val();
        param.secAcctId = $("#secAcctId").val();
        param.bondCode = $('#bondCode').val();
        Joyin.dialog(JoyinUtil.getLabel('TrdStandardBond003'), '../TrdStandardBond003/init', '98%', '98%', {initBondCode: bondCodes.join(','),initPortfolioId: portfolioIds.join(','),param: param});
    };

    /**
     * 检索数据
     * @returns
     */
    var search = function () {
        if (($("#secAcctId") == null || $("#secAcctId").val() == '')&&!($("#portfolioId").val())) {
            $("#secAcctId").find(' ~ .select2').find('.select2-selection').addClass('errors');
            $("#portfolioId").find(' ~ .select2').find('.select2-selection').addClass('errors');
            Joyin.error(JoyinUtil.getMessage("FW_E_0006", JoyinUtil.getLabel("TrdStandardBond001.secAcctId")+'/'+ JoyinUtil.getLabel("TrdStandardBond001.portfolioId")));
            return;
        }
        $("#secAcctId").find(' ~ .select2').find('.select2-selection').removeClass('errors');
        $("#portfolioId").find(' ~ .select2').find('.select2-selection').removeClass('errors');
        // 质押券总额度
        $("#inTotalPrinAmtSum").html("&nbsp;");
        // 标准券额度
        $("#stdrdPrinAmtSum").html("&nbsp;");
        // 已使用标准券总额度
        $("#pledgeStdrdPrinAmtSum").html("&nbsp;");
        // 可使用标准券总额度
        $("#avaStrdPrinAmtSum").html("&nbsp;");
        table.load();
    };
    /**
     * 校验行数据中证券账户是否不一致
     * @param array
     * @returns {boolean}
     */
    var checkIsDifferent = function (array) {
        var isDifferent = '';
        if (array != null && array.length > 1) {
            for (var index in array) {
                if (index < array.length - 1
                    && array[index].secAcctId != array[index * 1 + 1].secAcctId) {
                    isDifferent = "TRD_E_0010";
                    break;
                }
                if (index < array.length - 1
                    && array[index].portfolioId != array[index * 1 + 1].portfolioId) {
                    isDifferent = "TRD_BOND_STANDAR_003";
                    break;
                }
            }
        }
        return isDifferent;
    };
    /**
     * 导出
     */
    var exportExcel = function () {
        if ($("#secAcctId") == null || $("#secAcctId").val() == '') {
            $("#secAcctId").find(' ~ .select2').find('.select2-selection').addClass('errors');
            Joyin.error(JoyinUtil.getMessage("FW_E_0006", JoyinUtil.getLabel("TrdStandardBond001.secAcctId")));
            return;
        }
        $("#secAcctId").find(' ~ .select2').find('.select2-selection').removeClass('errors');
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // 绑定入库按钮事件
        $('#TrdStandardBond001_btn_in').click(inDialog);
        // 绑定出库按钮事件
        $('#TrdStandardBond001_btn_out').click(outDialog);
        // 绑定导出按钮事件
        $('#TrdStandardBond001_btn_export').click(exportExcel);
        // 绑定出库按钮事件
        $('#TrdStandardBond001_btn_search').click(search);
        //2019/3/19 start
        $("#portfolioId").change(function(){
            $('#secAcctId').val("").trigger("change");
        });
        //2019/3/19 end
    });
}());