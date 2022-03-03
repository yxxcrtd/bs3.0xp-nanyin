(function () {
    // 风险试算结果
    var riskResultResponse = entity_RiskcalResponse;
    var initFlag = false;
    var cluflag;
    var businessId;

    /**
     * 画星星
     */
    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
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
            && (pageModel !== PAGE_MODEL_REFERENCE)) {
            $('.must').each(function () {
                $(this).remove();
            })
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    /**
     * 获取风险试算参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSearchParams() {
        var object = $.serializeToObject("#searchForm");
        return object;
    }

    var cols = [
        {field:'operation', width:20, title: 'SysCommon.operation', sortable:false, type: 'action', buttons: ['delt']},
        {
            /* 组合代码 */
            field: 'portfolioId',
            width: 200,
            title: 'EtribRepoInfo003.portfolioId',
            edit: (repo_pledge_model == '02' && $("#ps").val() == '03') ? false : true,
            sortable: false,
            editOption: {
                type: 'select',
                id: 'portfolioId',
                multiple: false,
                showField: 'text',
                childrenIds : 'zyqk',
                searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
            }
        },

        {
            /* 质押券库代码 */
            field: 'zyqk', width: 200, title: 'EtrIbRepo001.zyqk',
            visible: (repo_pledge_model == '03' && $("#ps").val() == '03') ? true : false,
            edit: true,
            sortable: false,
            editOption: {
                type: 'select',
                id: 'zyqk',
                multiple: false,
                showField: 'text',
                parentIds : 'portfolioId',
                searchable: true,
                url: JoyinUtil.getRealPath('/cfets/etrib/cfetsEtrIbRepoinfo/getPledgeStockId')
            }
        }
        , {
            /*指令金额*/
            field: 'entrustAmt',
            width: 200,
            title: '指令金额（元）',
            edit: true,
            sortable: false,
            type: 'number',
            digit: 2
        },
        {
            /*可质押券面总额*/
            field: 'avaPledgePrin',
            width: 200,
            title: '可质押券面总额（万元）',
            visible: ((repo_pledge_model == '01' || repo_pledge_model == '03') && $("#ps").val() == '03') ? true : false,
            sortable: false,
            type: 'number',
            digit: 2
        },

        {
            /*组合t日可用*/
            field: 'availableCashT',
            width: 200,
            title: 'T日可用现金（元）',
            edit: false,
            sortable: false,
            type: 'number',
            digit: 2
        }, {
            /*组合t+1可用*/
            field: 'availableCashT1',
            width: 200,
            title: 'T+1日可用现金（元）',
            edit: false,
            sortable: false,
            type: 'number',
            digit: 2
        }, {
            /*账户现金*/
            field: 'accountCash',
            width: 200,
            title: '账户现金（元）',
            edit: false,
            sortable: false,
            type: 'number',
            digit: 2
        }
    ]
    var table = $("#etrInvestRepoInfoDtos").joyintable({
        data: entity_BussCombDistribtList || [{}],
        cols: cols,
        page: false,
        addbtn: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 190,
        done:function(){
            this.bind('click', '.delt', function(data, index) {
                var allRow = table.getAllRows();
                if(allRow.length > 1) {
                    table.deleteRow(index);
                }else {
                    Joyin.error("至少要保留一个组合");
                }
            });
        },
         beforeEditCell: function (preVal, field, rowData) {
            return true;
        }, editCell: function (value, data, field, cell, column) {
            if (field == 'entrustAmt') {
                $("#entrustAmt").val(value);
            }
            if (field == 'portfolioId') {
                var allRow = table.getAllRows();
                var exist = 0;
                $.each(allRow,function (index,item) {
                    if(value == item.portfolioId) {
                        exist = exist + 1;
                    }
                })
                if(exist > 1) {
                    Joyin.error(value+"组合已经存在,不可以重复选择");
                    data.portfolioId = null;
                    table.renderRow(data.LAY_TABLE_INDEX, data);
                    return;
                }
                var arr = [];
                var param = {}
                arr.push(value);
                param.portfolioIdList = arr;
                Joyin.ajaxJson(basePath + 'cfets/etrib/cfetsEtrIbBondinfo/queryPositionInfo', param, function (result) {
                    if(result.data) {
                        data.availableCashT = result.data[0].amount;
                        data.availableCashT1 = result.data[1].amount;
                        data.accountCash = result.data[2].amount;
                        table.renderRow(data.LAY_TABLE_INDEX, data);
                    }

                });
                // table.renderRow(data.LAY_TABLE_INDEX, data);
                if(repo_pledge_model == '01' || repo_pledge_model == '03') {
                    var param = {};
                    param.portfolioId = value;
                    param.pledgeStockId = "999999";
                    if(data.zyqk) {
                        param.pledgeStockId = data.zyqk;
                    }
                    Joyin.ajaxJson(basePath + 'cfets/etrib/cfetsEtrIbRepoinfo/getPledgeable', param, function (result) {
                        if(result.data) {
                            data.avaPledgePrin = result.data;
                            table.renderRow(data.LAY_TABLE_INDEX, data);
                        }
                    });
                }
            }
            if (field == 'zyqk') {
                var param = {};
                param.portfolioId = data.portfolioId;
                param.pledgeStockId = value;
                Joyin.ajaxJson(basePath + 'cfets/etrib/cfetsEtrIbRepoinfo/getPledgeable', param, function (result) {
                    if(result.data) {
                        data.avaPledgePrin = result.data;
                        table.renderRow(data.LAY_TABLE_INDEX, data);
                    }
                });
            }
            return true;
        }

    });
    var colsRiskTrialRet = [
        {
            field: 'kriStatusCn', title: 'BaseRiskMathComm.currStatus', sortable: false,
            render: function (data, type, row, meta) {
                if (row.kriStatus == 0) {
                    return "<span>正常</span>";
                }
                if (row.kriStatus == 1) {
                    return "<span style='color:orange'>预警</span>";
                }
                if (row.kriStatus == 2) {
                    return "<span style='color:#F7580D'>审批</span>";
                }
                if (row.kriStatus == 3) {
                    return "<span style='color:#F71A0D'>违规</span>";
                }
                if (row.kriStatus == 4) {
                    return "<span style='color:#951F1F'>异常</span>";
                }
                return row.kriStatus;
            }
        }
        , {
            /* 指标名称*/
            field: 'kriName', width: 200, title: 'BaseRiskMathComm.indName', sortable: false
        }
        , {/*指标类型*/
            field: 'kriTypeCn',
            width: 100,
            title: 'BaseRiskMathComm.indType',
            sortable: false
        }
        , {
            /*组合名称*/
            field: 'portfolioName',
            width: 150,
            title: 'BaseRiskMathComm.portfolioName',
            sortable: false
        }
        , {
            /*预警阈值*/
            field: 'warningLine',
            width: 150,
            title: 'BaseRiskMathComm.wthreshold',
            sortable: false
        }
        , {
            /*审批线*/
            field: 'approveLine',
            width: 150,
            title: 'BaseRiskMathComm.approveold',
            sortable: false
        }
        , {
            /*当前实际值*/
            field: 'kriValue',
            width: 200,
            title: 'BaseRiskMathComm.currValue',
            sortable: false
        }
        , {
            /*原值*/
            field: 'oldValue',
            width: 200,
            title: 'BaseRiskMathComm.preValue',
            sortable: false
        }
        , {
            /*趋势*/
            field: 'kriTrend',
            width: 200,
            title: 'BaseRiskMathComm.kriTrend',
            sortable: false
        }

    ]

    var tableRisk = $("#riskTrialRetTable").joyintable({
        data: entity_RiskTrialRetList || [],
        cols: colsRiskTrialRet,
        page: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 290
    });


    var init = function () {
        if ($('#ps').val() == '04') {
            $("#pledgeBondTotalTblDetailed_div").hide();
        }
    }
    var pledgeBondTotalTblDetailed;
    if (repo_pledge_model == '02') {
        /**
         * 质押券汇总信息（Total）
         */
        pledgeBondTotalTblDetailed = $("#pledgeBondTotalListDetailed").joyintable({
            page: false,
            data: entity_PledgeBondTotalListDetailed || [],
            height: 200,
            searchOnInit: true,
            checkbox: false,
            /**
             * 定义表头
             */
            cols: [
                // 委托代码
                /* {field: 'entrustId',title: 'BussRepoSecPtl.entrustId',type: 'text',align: 'left',sortable: false,edit: false},*/
                // 债券代码
                {
                    field: 'finprodMarketId',
                    title: 'BussRepoSecPtl.secId',
                    type: 'text',
                    align: 'left',
                    sortable: false,
                    edit: false,
                    width: 200
                }
                // 债券简称
                , {
                    field: 'finprodAbbr',
                    title: '债券简称',
                    type: 'text',
                    align: 'left',
                    sortable: true,
                    edit: false,
                    width: 200
                }
                // 债券类型
                /*,{field: 'secTypeStr',title: 'BussRepoSecPtl.secType',type: 'text',align: 'left',edit: false,sortable: false}*/
                // 组合代码
                , {
                    field: 'portfolioId',
                    title: 'BussRepoSecPtl.portfolioId',
                    type: 'text',
                    sortable: false,
                    edit: false,
                    width: 150
                }
                // 组合名称
                , {
                    field: 'portfolioName',
                    title: 'BussRepoSecPtl.portfolioName',
                    type: 'text',
                    edit: false,
                    sortable: false,
                    width: 200
                }
                // 质押本方券券面总额
//                , {field: 'pledgeBfPrin',title: '质押本方券券面总额',type: 'number',edit: true,sortable: false}
                // 质押融入券券面总额
//                , {field: 'pledgeRrPrin',title: '质押融入券券面总额',type: 'number',edit: true,sortable: false}
                // 可质押券面总额
                , {
                    field: 'pledStrdPrinAmt',
                    title: '可质押券面总额（万元）',
                    type: 'number',
                    edit: false,
                    sortable: false,
                    width: 200,
                    render: function (data, type, row, meta) {
                        return (data / 10000).toFixed(2);
                    }
                }
                //质押券汇总券面总额
                , {
                    field: 'pledgePrin', title: '质押券面总额（万元）', type: 'number', edit: true, sortable: false, width: 240,
                    render: function (data, type, row, meta) {
                        return (data / 10000).toFixed(2);
                    }
                }
                // 首次净价
                , {
                    field: 'firstNetPrice',
                    width: 150, digit: 4,
                    title: '首次净价（元）',
                    sortable: false,
                    render: function (data, type, row, meta) {
                        return (data * 1).toFixed(4);
                    }
                }
                //折算比例%
                , {
                    field: 'scale',
                    title: '折算比例(%)',
                    type: 'number',
                    edit: true,
                    sortable: false,
                    digit: 2,
                    width: 100,
                    render: function (data, type, row, meta) {
                        return (data * 100).toFixed(2);
                    }
                }

                // 登记托管机构
                , {
                    field: 'registOrgStr',
                    title: 'BussRepoSecPtl.repRegistOrg',
                    type: 'text',
                    align: 'left',
                    edit: false,
                    sortable: false,
                    width: 200
                }

            ], editCell: function (value, data, field, cell, column) {
                if (field == 'pledgePrin') {
                    if(value > data.pledStrdPrinAmt) {
                        Joyin.error("质押券面总额不能大于可质押券面总额");
                        data.pledgePrin = data.pledStrdPrinAmt;
                        pledgeBondTotalTblDetailed.renderRow(data.LAY_TABLE_INDEX, data);
                        return false;
                    }
                }
                if(field == 'scale') {
                    var rows = pledgeBondTotalTblDetailed.getAllRows();
                    var rowData = null;
                    $.each(rows, function(i, obj){
                        if(obj.finprodMarketId == data.finprodMarketId) {
                            rowData = obj;
                            rowData.scale = value;
                            pledgeBondTotalTblDetailed.renderRow(obj.LAY_TABLE_INDEX, rowData);
                        }
                    })
                }
                return true;
            }
        });

    }
    init();

    /**
     * 获取提交的数据
     * @param operateType
     * @returns {{}}
     */
    function getSubmitParam(operateType) {
        var res = {}
        var object = $.serializeToObject('#searchForm');

        var param = {};
        param = JSON.parse(JSON.stringify(object));
        param.entrustAmt = object.entrustAmt;
        param.operateType = operateType;
        param.pageModel = pageModel;
        param.cfetsEtribdoRiskcalResponse = riskResultResponse;
        param.elementId = 'CfetsEtrIbRepoinfo003_btn_submit';
        param.pageId = "CfetsEtrIbRepoinfo003";

        res.param = param;
        return res;
    }

    function paramCheck() {
        var object = $.serializeToObject('#searchForm');
        if (!object.entrustAmt || !object.settleSpreed || !object.priceModel || !object.price || !object.repoTerm || !object.termType || !object.counterId) {
            Joyin.error("有*必填项未填写,请填写完整")
            return false;
        }
        if(!riskResultResponse) {
            Joyin.error("请先完成风险试算")
            return false;
        }
        return true;
    }


    function saveOrSubmit(operateType) {
        var res = getSubmitParam(operateType)
        // if(res.objects.length>0){
        if (operateType === "save") {
            WorkFlow.doSaveList(basePath + 'cfets/etrib/cfetsEtrIbRepoinfo003/save', res.param, "cfetsEtrIbRepoinfo003_btn_save", "cfetsEtrIbRepoinfo003");
        } else {
            WorkFlow.doSubmitList(basePath + 'cfets/etrib/cfetsEtrIbRepoinfo003/save', res.param, "cfetsEtrIbRepoinfo003_btn_submit", "cfetsEtrIbRepoinfo003");
        }
        // }

    }

    // 绑定提交按钮
    $('#CfetsEtrIbRepoinfo003_btn_submit').click(function () {
        if (paramCheck()) {
            saveOrSubmit('submit');
        }
    });
    // 绑定保存按钮
    $('#CfetsEtrIbRepoinfo003_btn_save').click(function () {
        saveOrSubmit('save');
    });

    $('#CfetsEtrIbRepoinfo003_btn_riskcal').click(function () {
        var object = $.serializeToObject('#searchForm');
        var param = JSON.parse(JSON.stringify(object))
        Joyin.ajaxJson(basePath + 'cfets/etrib/cfetsEtrIbRepoinfo/doRiskcal', param, function (data) {
            $("#riskTrialSpan").html("&nbsp;&nbsp;" + data.data.data.riskTrialSpan);
            riskResultResponse = data.data.data;
            entity_RiskTrialRetList = data.data.data.riskcalReturnList;
            tableRisk.clear();
            tableRisk.addRow(data.data.data.riskcalReturnList);
        });
    });
    $("#riskTrialSpan").html("&nbsp;&nbsp;" + entity_riskTrialSpan);
    //cfets/etrib/cfetsEtrIbRepoinfo/getTermType
    $("#repoTerm").blur(function () {
        var repoTerm = $("#repoTerm").val();
        if (!repoTerm) return;
        Joyin.ajax(basePath + 'cfets/etrib/cfetsEtrIbRepoinfo/getTermTypeByRepoTerm?repoTerm=' + repoTerm, {}, function (data) {
            if(data.data) {
                $("#termType").textSelect('setValue', data.data.data);
            }
        });
    })

}());