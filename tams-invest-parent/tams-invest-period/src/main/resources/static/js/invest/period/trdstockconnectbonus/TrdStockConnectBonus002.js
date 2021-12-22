(function () {
    var initFlag = true;
    var needInit = false;
    var sumRegistShare;//权益登记日股本总和
    var acctNoList;

    /**
     * 分红明细表格
     */
    var repoInfoTableOption = {
            rowno: true,
            searchOnInit: true,
            checkbox: false,
            page: false,
            cols: [
                //组合代码
                {field: 'portfolioId', width: 150, title: 'IntDetailDuration.portfolioId', sortable: false}
                //组合名称
                , {field: 'portfolioName', width: 150, title: 'IntDetailDuration.portfolioName', sortable: false}
                //证券托管户
                , {field: 'secAcctId', width: 150, title: 'TrdStockConnectBonus001.secManageAcctId', sortable: false}
                //权益登记日股本
                , {field: 'regDateAmt', width: 150, digit: '0', title: 'TrdStockConnectBonus001.regDateAmt', type: 'money', sortable: false}
                //以股代息股本
                ,{field:'replaceAmt',width:150, digit: '0',title:'TrdStockConnectBonus001.replaceAmt',sortable: false,type:'money', edit: true}
                //分红金额
                , {field: 'fpriceAmt', width: 150, title: 'TrdStockConnectBonus001.fpriceAmt', type: 'money', sortable: false, edit: true}
                //分红股本
                , {field: 'shareAmt', width: 150, digit: '0', title: 'TrdStockConnectBonus001.shareAmt', type: 'money', sortable: false, edit: true}
                , {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit'],
                    render: function (data, type, row, meta) {
                        //实际交易组合无子组合交易明细
                        if(row.investYesorno =='Y'){
                            return;
                        }
                        return '<i class="btn layui-icon detail" data-ignore="true" title="明细">&#xe60a;</i>';
                    }
                }
            ],
            beforeEditCell: function (value, field, data) {
                if($('#ps').val() === STOCK_DEVISION_TYPE01 && $('#ifDividend').val() == YES_OR_NO_Y){
                    if (field == 'replaceAmt') {
                        return true;
                    }
                }

                return false;
            },
            editCell : function(value, data, field, cell, column){
                if($('#ps').val() === STOCK_DEVISION_TYPE01 && $('#ifDividend').val() == YES_OR_NO_Y){
                    if(value && Number(value) > Number(data.regDateAmt)){
                        Joyin.warn("以股代息股本不能大于权益登记日股本!");
                        return;
                    }
                    if (field == 'replaceAmt' && value) {
                        resetTableData();
                    }
                }
            },
            done: function () {
                if (!(typeof needInit === "undefined") && needInit) {
                    resetTableData();
                    needInit = false;
                }
            },
            data: []
        }

    if (dividInfoList && dividInfoList.length > 0) {
        repoInfoTableOption.data = repoInfoTableOption.data.concat(dividInfoList);
    }

    /**
     * 定义数据表格
     */
    var repoInfoTable = $("#intDetailTable").joyintable(repoInfoTableOption);

    /**
     * 分红明细初始化
     */
    var dealIntDetail = function () {
        var finprodMarketId = $('#finprodMarketId').val();
        var regDate = $('#regDate').val();
        if (finprodMarketId && regDate) {
            var param = {
                finprodMarketId: finprodMarketId,
                regDate: regDate
            };
            Joyin.ajaxSync(JoyinUtil.getRealPath('/invest/period/trdstockconnectbonus/TrdStockConnectBonus002/findividInfo'), param, function (result) {
                        if (result.data) {
                            dividInfoList = result.data;
                            /* 计算股本总数 */
                            sumRegistShare = 0;
                            for (var i = 0; i < dividInfoList.length; i++) {
                                sumRegistShare = JoyinMath.doAdd(sumRegistShare, dividInfoList[i].regDateAmt);
                            }
                            triValueChange('regDateAmt', sumRegistShare);
                        }
                    });
        } else {
            dividInfoList = [];
        }

        //新数据flag!
        initFlag = true
        resetTableData();
    }

    /**
     * 利用占比计算所有的金额
     */
    var resetTableData = function () {
        var finAssetIntInfos = dividInfoList;
        //初始化后才能取画面表格数据
        if(!initFlag){
            finAssetIntInfos = repoInfoTable.getAllRows();
        }
        if (!finAssetIntInfos) {
            return;
        }

        var obj =  $.serializeToObject('#informationForm');
        obj.finAssetIntInfoList = finAssetIntInfos;
        if(!obj.finprodId) {
            obj.finprodId = $("#finprodId").val();
        }
        Joyin.ajaxJson(basePath + 'invest/period/trdstockconnectbonus/TrdStockConnectBonus002/calNumAndAmt', obj,
            function (result) {
                if (result && result.data) {
                    finAssetIntInfos = result.data.finAssetIntInfoList;
                    repoInfoTable.reload({ data:finAssetIntInfos});
                }
            });
    }

    /**
     * 绑定编辑事件
     */
    repoInfoTable.bind('click', '.detail', function (data, index) {
        var param = {
            "portfolioId": data.portfolioId,
            "finprodMarketId": $('#finprodMarketId').val(),
            "dealDate": new Date($("#regDate").val()).format(),
            "isAdvance": "N",
            "bizCode": $("#busiCode").val(),
            "workflowId": typeof workflowId == "undefined" ? '' : $("#workflowId").val(),
            // 修改字段删除
            "chlId": $("#chlId").val() ? $("#chlId").val() : $('#secManageAcctId').val() || "",
            "preMap": JSON.stringify({
                "regDateAmt": data.regDateAmt,
                "replaceAmt": data.replaceAmt,
                "fpriceAmt": data.fpriceAmt,
                "shareAmt": data.shareAmt,
            }),
            cols: JSON.stringify([
                //权益登记日股本
                {field: 'regDateAmt', width: 150, title: 'TrdStockConnectBonus001.regDateAmt', type: 'money', sortable: false}
                //以股代息股本
                ,{field:'replaceAmt',width:150, title:'TrdStockConnectBonus001.replaceAmt',    type:'money', sortable: false, digit: '0'}
                //分红金额
                , {field: 'fpriceAmt', width: 150, title: 'TrdStockConnectBonus001.fpriceAmt', type: 'money', sortable: false}
                //分红股本
                , {field: 'shareAmt', width: 150, title: 'TrdStockConnectBonus001.shareAmt', type: 'money', sortable: false}
            ])
        };
        Joyin.dialog(JoyinUtil.getLabel('TrdDurationCommon.ChildPortfolioInfo'), JoyinUtil.getRealPath('invest/period/common/ChildPortfolioInfo/init'), '80%', '80%', param);
    });

    /**
     * 提交
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#informationForm', true);
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/period/trdstockconnectbonus/TrdStockConnectBonus002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'invest/period/trdstockconnectbonus/TrdStockConnectBonus002/save', object,
                                "TrdStockConnectBonus002_btn_save", "TrdStockConnectBonus002");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'invest/period/trdstockconnectbonus/TrdStockConnectBonus002/save', object,
                                "TrdStockConnectBonus002_btn_submit", "TrdStockConnectBonus002");
                    }
            });
        });
    }

    var triValueChange = function (id, data) {
        if (data) {
            $('#' + id).val(data).trigger("change").trigger('blur');
        } else if (!initFlag) {
            $('#' + id).val('').trigger("change").trigger('blur');
        } else {
            $('#' + id).trigger("change").trigger('blur');
        }
    };
    // 判空处理
    var isNotNull = function (value) {
        return value && value != "";
    };

    /**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }


    var init = function () {
        if(statusValid && statusValid == statusValid_01){
            //2021-04-25
            dealIntDetail();
            triValueChange("dividCcy");
            triValueChange("ps");
        }

        initFlag = false;
        var mode = $("#mode").val();
        if(mode==='edit'){
            $("#finprodMarketId").prop('disabled',true);
            $("#ps").prop('disabled',true);
            $("#ifDividend").prop('disabled',true);
            $("#regDate").prop('disabled',true);
            $("#tradeDate").prop('disabled',true);
            $("#dividCcy").prop('disabled',true);
            $("#replacePrice").prop('disabled',true);
            $("#unitCprice").prop('disabled',true);
            $("#unitInt").prop('disabled',true);
        }

    };
    /**
     * 画面加载完成
     */
    $(function () {

        $('#TrdStockConnectBonus002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdStockConnectBonus002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

        // 市场代码
        $('#finprodMarketId').change(
            function () {
                var finprodMarketId = $('#finprodMarketId').val();

                if (finprodMarketId || finprodMarketId != '') {
                    triValueChange('finprodAbbr', $("#finprodMarketId").select2('data')[0].text);
                    triValueChange('finprodId', $("#finprodMarketId").select2('data')[0].object);
                    dealIntDetail();
                } else {
                    triValueChange('finprodAbbr');
                    triValueChange('finprodId');
                }
            });
        //分红类型联动
        $('#ps').change(function () {
            var ps = $('#ps').val();
            var ifDividend = $('#ifDividend').val();

            triValueChange('unitCprice');
            triValueChange('unitInt');
            // triValueChange('regDateAmt');
            //现金分红
            if (ps == STOCK_DEVISION_TYPE01) {
                //分红股本
                triValueChange('shareAmt');
                $('.ps02').val('').attr('disabled', true).removeAttr("required");
                $('.ps01').attr('disabled', false);
                $('#exchangeRate').attr('disabled', false).attr("required", "true");
                //含股利选择权
                if (ifDividend == YES_OR_NO_Y) {
                    $('#redStockDate').attr('disabled', false).attr("required", "true");
                    $('#replacePrice').attr('disabled', false).attr("required", "true");
                }
                //不含股利选择权
                else if (ifDividend == YES_OR_NO_N) {
                    $('#redStockDate').val('').attr('disabled', true).removeAttr("required");
                    $('#replacePrice').val('').attr('disabled', true).removeAttr("required");
                }
            }
            //送转
            else if (ps == STOCK_DEVISION_TYPE02) {
                //分红金额
                triValueChange('fpriceAmt');
                $('.ps01').val('').attr('disabled', true).removeAttr("required");
                $('.ps02').attr('disabled', false).attr("required", "true");
                $('#redStockDate').attr('disabled', false);
                $('#replacePrice').val('').attr('disabled', true).removeAttr("required");
                $('#exchangeRate').val('').attr('disabled', true).removeAttr("required");
            }

            star();
        });
        // 权益登记日联动
        $('#regDate').change(function () {
            var registDate = $('#regDate').val();
            if (isNotNull(registDate)) {
                dealIntDetail();
            }
        });

        //是否含股利选择权
        $('#ifDividend').change(function () {
            var ps = $('#ps').val();
            var ifDividend = $('#ifDividend').val();
            if(ifDividend){
                //含股利选择权
                if (ifDividend == YES_OR_NO_Y) {
                    $('#redStockDate').attr('disabled', false).attr("required", "true");
                    if(ps == STOCK_DEVISION_TYPE01){
                        $('#replacePrice').attr('disabled', false).attr("required", "true");
                    }
                }
                //不含股利选择权
                else if (ifDividend == YES_OR_NO_N) {
                    $('#redStockDate').val('').attr('disabled', true).removeAttr("required");
                    $('#replacePrice').val('').attr('disabled', true).removeAttr("required");
                }
                resetTableData();
            }
            star();
        });

        //派息币种
        $('#dividCcy').change(function () {
            var dividCcy = $('#dividCcy').val();
            if(dividCcy && dividCcy === default_ccy){
                $('#exchangeRate').val(Number(1));
            }
        });

        //汇率
        $('#exchangeRate').change(function () {
            var exchangeRate = $('#exchangeRate').val();
            resetTableData();
        });

        //以股代息价格
        $('#replacePrice').change(function () {
            var replacePrice = $('#replacePrice').val();
            resetTableData();
        });


        //每股送转联动
        $('#unitCprice').change(function () {
            var unitCprice = $('#unitCprice').val();
            //权益登记日股本
            var regDateAmt = $('#regDateAmt').val();
            if (isNotNull(unitCprice) && isNotNull(regDateAmt)) {
                //4舍5入取整
                $('#shareAmt').val(JoyinMath.doMul(regDateAmt, unitCprice, 0, JoyinMath.ROUND_HALF_UP));
                resetTableData();
            }

        });
        //每股派息联动
        $('#unitInt').change(function () {
            var unitInt = $('#unitInt').val();
            //权益登记日股本
            var regDateAmt = $('#regDateAmt').val();
            if (isNotNull(unitInt) && isNotNull(regDateAmt)) {
                $('#fpriceAmt').val(JoyinMath.doMul(regDateAmt, unitInt, 2, JoyinMath.ROUND_HALF_UP));
                resetTableData();
            }
        });

        //修改除权除息日联动
        $('#tradeDate').change(function () {
            var tradeDate = $('#tradeDate').val();
            $('#settleDate').val(tradeDate);
            var perDate = new Date(tradeDate);
            perDate.setDate(perDate.getDate() - 1);
            triValueChange("regDate", perDate.format('yyyy-MM-dd'));
            var nextDate = new Date(tradeDate);
            nextDate.setDate(nextDate.getDate() + 1);
            $('#redStockDate').val(nextDate.format('yyyy-MM-dd'));

        });

        /*      //输入分红股本联动
        $('#shareAmt').change(function () {
            var shareAmt = $('#shareAmt').val();
            if (isNotNull(shareAmt)) {
                resetTableData();
            }
        });
        //分红金额联动
        $('#fpriceAmt').change(function () {
            var fpriceAmt = $('#fpriceAmt').val();
            if (isNotNull(fpriceAmt)) {
                resetTableData();
            }
        });

        //权益登记日股本
        $('#regDateAmt').change(function () {
            //每股送转
            var unitCprice = $('#unitCprice').val();
            //每股派息
            var unitInt = $('#unitInt').val();
            //权益登记日股本
            var regDateAmt = $('#regDateAmt').val();
            if (isNotNull(unitCprice) && isNotNull(regDateAmt)) {
                //4舍5入取整
                $('#shareAmt').val(JoyinMath.doMul(regDateAmt, unitCprice, 0, JoyinMath.ROUND_HALF_UP));
            }
            if (isNotNull(unitInt) && isNotNull(regDateAmt)) {
                $('#fpriceAmt').val(JoyinMath.doMul(regDateAmt, unitInt, 2, JoyinMath.ROUND_HALF_UP));
            }
        });*/

        init();
    });
}());