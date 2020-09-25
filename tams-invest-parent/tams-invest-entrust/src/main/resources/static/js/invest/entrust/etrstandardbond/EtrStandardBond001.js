(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var param = {};
        // 组合代码
//        if ($('#portfolioId').val()) {
//        	param.portfolioId = $("#portfolioId").val().join(",");
//        } else {
//        	param.portfolioId = null;
//        }
        param.portfolioId = $('#portfolioId').val();
        // 交易场所
//        if ($('#tradePlace').val()) {
//        	param.tradePlace = $("#tradePlace").val().join(",");
//        } else {
//        	param.tradePlace = null;
//        }
        param.tradePlace = $("#tradePlace").val() ;
        // 证券账户/通道代码
//        if ($('#secAcctId').val()) {
//        	param.secAcctId = $("#secAcctId").val().join(",");
//        } else {
//        	param.secAcctId = null;
//        }
        param.secAcctId = $("#secAcctId").val()
        
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
            {field: 'portfolioId', title: 'EtrStandardBond001.colModel.portfolioId', sortField: 'portfolioId'}
            // 证券账户/通道
            , {field: 'secAcctId', title: function () {
                    return  JoyinUtil.getLabel("EtrStandardBond001.colModel.secAcctId")+JoyinUtil.getLabel("invest.channelId");}(), sortField: 'secAcctId'}
            // 交易场所
            , {field: 'tradePlaceText', title: 'EtrStandardBond001.colModel.tradePlace', sortField: 'tradePlace'}
            // 债券代码
            , {field: 'finprodMarketId', title: 'EtrStandardBond001.colModel.finProdId', sortField: 'finProdId'}
            // 债券名称
            , {field: 'finProdName', title: 'EtrStandardBond001.colModel.finProdName', sortField: 'finProdName'}
            // 持仓券面总额
            , {
                field: 'posStrdPrinAmt',
                title: 'EtrStandardBond001.colModel.posStrdPrinAmt',
                type: 'money',
                sortField: 'posStrdPrinAmt'
            }
            // 可质押券面总额
            , {
                field: 'pledStrdPrinAmt',
                title: 'EtrStandardBond001.colModel.pledStrdPrinAmt',
                type: 'money',
                sortField: 'pledStrdPrinAmt'
            }
            // 折算比例%
            , {
                field: 'scale',
                title: 'EtrStandardBond001.colModel.scale',
                type: 'number',
                sortField: 'scale'
            }
            , {field: 'tradePlace', visible: false}
        ],
    });

    /**
     * 弹出优先级设置画面
     * @returns
     */
    var priorityDialog = function () {
//        var param = {};
//        Joyin.dialog(JoyinUtil.getLabel('TrdStandardBond003'), '../EtrStandardBond003/init', '98%', '98%', param);
        Joyin.dialog(JoyinUtil.getLabel('EtrStandardBond003'), '../EtrStandardBond003/init', '80%', '80%',
        	null,function(){
        		table.reload();//回调函数，表格刷新
        });
    };
    
    /**
     * 弹出入库画面
     * @returns
     */
    var inDialog = function () {
        var param = {};
        param.portfolioId = $("#portfolioId").val();
        param.tradePlace = $("#tradePlace").val();
        param.secAcctId = $("#secAcctId").val();
        Joyin.dialog(JoyinUtil.getLabel('EtrStandardBond002'), '../EtrStandardBond002/init', '98%', '98%', param);
    };

    /**
     * 出库操作
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

        if (selArr.length > 20) {
            Joyin.error("出库券最多不能同时操作20个以上");
            return;
        }
        // 出库操作,是否确认对所选债券进行出库操作？
        Joyin.confirm(JoyinUtil.getMessage('EtrStandardBond001.outSave_001'), function() {
        	var data = {};
    		data.selectedItem = selArr;
            Joyin.ajaxJson(basePath + 'invest/entrust/etrstandardbond/EtrStandardBond001/outSave', data, function(result) {
            	table.reload();
            });
        });
//        Joyin.ajaxJson(basePath + '/invest/entrust/etrstandardbond/EtrStandardBond001/outSave', data,
//        		function(result) {
//            		var finprodIdStr = "";
//            		$.each(selArr, function(idx,v) {
//            			finprodIdStr = finprodIdStr + v.finprodId + ",";
//            		});
//            	}
//            );
    };

    /**
     * 检索数据
     * @returns
     */
    var search = function () {
//        if (($("#secAcctId").val() == null || $("#secAcctId").val() == '')
//        	&&($("#portfolioId").val() == null || $("#portfolioId").val() == '')) {
//            $("#secAcctId").find(' ~ .select2').find('.select2-selection').addClass('errors');
//            $("#portfolioId").find(' ~ .select2').find('.select2-selection').addClass('errors');
//            Joyin.error(JoyinUtil.getMessage("FW_E_0006", JoyinUtil.getLabel("EtrStandardBond001.portfolioId")+'/'+ JoyinUtil.getLabel("EtrStandardBond001.secAcctId")+JoyinUtil.getLabel("invest.channelId")));
//            return;
//        }
//        $("#secAcctId").find(' ~ .select2').find('.select2-selection').removeClass('errors');
//        $("#portfolioId").find(' ~ .select2').find('.select2-selection').removeClass('errors');
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
//        if ($("#secAcctId") == null || $("#secAcctId").val() == '') {
//            $("#secAcctId").find(' ~ .select2').find('.select2-selection').addClass('errors');
//            Joyin.error(JoyinUtil.getMessage("FW_E_0006", JoyinUtil.getLabel("TrdStandardBond001.secAcctId")));
//            return;
//        }
//        $("#secAcctId").find(' ~ .select2').find('.select2-selection').removeClass('errors');
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // 绑定入库按钮事件
        $('#EtrStandardBond001_btn_in').click(inDialog);
        // 绑定出库按钮事件
        $('#EtrStandardBond001_btn_out').click(outDialog);
        // 绑定导出按钮事件
        $('#EtrStandardBond001_btn_export').click(exportExcel);
        // 绑定查询按钮事件
        $('#EtrStandardBond001_btn_search').click(search);
        // 绑定优先级设置按钮事件
        $('#EtrStandardBond001_btn_priority').click(priorityDialog);
        //2019/3/19 start
        $("#portfolioId").change(function(){
            $('#secAcctId').val("").trigger("change");
        });
        //2019/3/19 end
    });
}());