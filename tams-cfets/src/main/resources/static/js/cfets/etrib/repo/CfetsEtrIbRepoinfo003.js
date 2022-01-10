(function () {
    // 风险试算结果
    var riskResultdata;
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


    function getSearchParams() {
        var object = $.serializeToObject("#searchForm");
        return object;
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

        {
            /* 组合代码 */
            field: 'portfolioId', width: 200, title: 'EtribRepoInfo003.portfolioId', edit: true, sortable: false,
            editOption: {
                type: 'select',
                id: 'portfolioId',
                multiple: false,
                showField: 'text',
                searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
            }
        },

        {
            /* 质押券库代码 */
            field: 'zyqk', width: 200, title: 'EtrIbRepo001.zyqk',
            /* 光大质押券暂时隐藏 */
            visible: repo_pledge_model=='03'&&$("#ps").val()=='03'?true:false,
            edit: true,
            sortable: false,
            editOption: {
                type: 'select',
                id: 'zyqk',
                multiple: false,
                showField: 'text',
                parentIds : 'portfolioId',
                searchable: true,
                url: JoyinUtil.getRealPath('/invest/entrust/etribrepo/EtrIbRepoinfo002/getPledgeStockIdByPortfolioId')
            }
        } 
        , {
        /*指令金额*/
            field: 'entrustAmt',
            width: 200,
            title: '指令金额（元）',
            edit: false,
            sortable: false,
            type: 'number',
            digit: 2
        }, {
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
        data:entity_BussCombDistribtList||[],
        cols: cols,
        page: false,
        addbtn: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 190,
        addclick: function (row) {

        },beforeEditCell: function (preVal, field, rowData) {
            if(!initFlag){
                return true;
            }
            return false;
        }
    });
    var colsRiskTrialRet = [
        {field:'kriStatusCn',  title: 'BaseRiskMathComm.currStatus',sortable:false,
            render: function (data, type, row, meta) {
                if(row.kriStatus == 0){
                    return "<span>正常</span>";
                }
                if(row.kriStatus == 1){
                    return "<span style='color:orange'>预警</span>";
                }
                if(row.kriStatus == 2){
                    return "<span style='color:#F7580D'>审批</span>";
                }
                if(row.kriStatus == 3){
                    return "<span style='color:#F71A0D'>违规</span>";
                }
                if(row.kriStatus == 4){
                    return "<span style='color:#951F1F'>异常</span>";
                }
                return row.kriStatus;
            }
        }
        ,{
            /* 指标名称*/
            field: 'kriName', width: 200, title: 'BaseRiskMathComm.indName',sortable: false
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
        data:entity_RiskTrialRetList||[],
        cols: colsRiskTrialRet,
        page: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 290
    });


    var init = function () {
        initFlag=true
        if($('#ps').val()=='04'){
            $("#pledgeBondTotalTblDetailed_div").hide();
        }
    }
    var pledgeBondTotalTblDetailed ;
    if(repo_pledge_model=='02') {
        /**
         * 质押券汇总信息（Total）
         */
        pledgeBondTotalTblDetailed = $("#pledgeBondTotalListDetailed").joyintable({
            page: false,
            data: entity_PledgeBondTotalListDetailed || [],
            height: 200,
            searchOnInit: true,
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
                    width: 240
                }
                // 债券简称
                , {
                    field: 'finprodAbbr',
                    title: '债券简称',
                    type: 'text',
                    align: 'left',
                    sortable: true,
                    edit: false,
                    width: 240
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
                    width: 240
                }
                // 组合名称
                , {
                    field: 'portfolioName',
                    title: 'BussRepoSecPtl.portfolioName',
                    type: 'text',
                    edit: false,
                    sortable: false,
                    width: 240
                }
                // 质押本方券券面总额
//                , {field: 'pledgeBfPrin',title: '质押本方券券面总额',type: 'number',edit: true,sortable: false}
                // 质押融入券券面总额
//                , {field: 'pledgeRrPrin',title: '质押融入券券面总额',type: 'number',edit: true,sortable: false}
                // 可质押券面总额
                , {field: 'pledStrdPrinAmt', title: '可质押券面总额（万元）', type: 'number', edit: false, sortable: false, width: 240,
                    render: function (data, type, row, meta) {
                        return (data/10000).toFixed(2);
                    }
                }
                //质押券汇总券面总额
                , {field: 'pledgePrin', title: '质押券面总额（万元）', type: 'number', edit: false, sortable: false, width: 240,
                    render: function (data, type, row, meta) {
                        return (data/10000).toFixed(2);
                    }}
                // 首次净价
                , {
                    field: 'firstNetPrice',
                    width: 150,digit: 4,
                    title: '首次净价（元）',
                    sortable: false,
                    render: function (data, type, row, meta) {
                        return (data*1).toFixed(4);
                    }
                }
                //折算比例%
                , {field: 'scale', title: '折算比例(%)', type: 'number', edit: false, sortable: false,digit: 2, width: 100,
                    render: function (data, type, row, meta) {
                        return (data*100).toFixed(2);
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

            ]
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
        // param.etrInvestRepoInfoDtos = objects;
        // add by zhangrui1 start
        param.operateType = operateType;
        param.pageModel = pageModel;
        param.riskTrialRetDto = object.riskTrialRetDto;
        param.elementId = 'CfetsEtrIbRepoinfo003_btn_submit';
        res.param = param;
        // res.objects = objects;
        return res;
    }

    function saveOrSubmit(operateType) {
        var res =  getSubmitParam(operateType)
        // if(res.objects.length>0){
            if (operateType === "save") {
                WorkFlow.doSaveList(basePath+'cfets/etrib/cfetsEtrIbRepoinfo003/save',res.param,"cfetsEtrIbRepoinfo003_btn_save","cfetsEtrIbRepoinfo003");
            } else {
                WorkFlow.doSubmitList(basePath+'cfets/etrib/cfetsEtrIbRepoinfo003/save',res.param,"cfetsEtrIbRepoinfo003_btn_submit","cfetsEtrIbRepoinfo003");
            }
        // }

    }

    // 绑定提交按钮
    $('#CfetsEtrIbRepoinfo003_btn_submit').click(function () {
        saveOrSubmit('submit');
    });
    // 绑定保存按钮
    $('#CfetsEtrIbRepoinfo003_btn_save').click(function () {
        saveOrSubmit('save');
    });

}());