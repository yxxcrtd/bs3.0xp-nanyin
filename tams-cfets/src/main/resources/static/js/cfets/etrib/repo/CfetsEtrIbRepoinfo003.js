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
            field: 'portfolioId', width: 100, title: 'EtribRepoInfo003.portfolioId', edit: false, sortable: false,
            editOption: {
                type: 'select',
                id: 'portfolioId',
                parentIds:'portfolioSeries',
//                childrenIds : 'portfolioId',
                multiple: false,
                showField: 'text',
                searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
            }
        },

        {
            /* 质押券库代码 */
            field: 'zyqk', width: 100, title: 'EtrIbRepo001.zyqk',
            /* 光大质押券暂时隐藏 */
            visible: repo_pledge_model=='03'?true:false,
            edit: false,
            sortable: false,
            editOption: {
                type: 'select',
                id: 'zyqk',
                multiple: false,
                showField: 'text',
                parentIds : 'portfolioId',
                searchable: true,
                url: JoyinUtil.getRealPath('/cfets/etrib/cfetsEtrIbRepoinfo/getPledgeStockIdByPortfolioIdOfSelectOptionDto')
            }
        } 
        , {
        /*指令金额*/
            field: 'entrustAmt',
            width: 50,
            title: 'EtrRepo002.entrustAmt',
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

        }
    });



    var init = function () {


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
            /**
             * 定义表头
             */
            cols: [
                // 委托代码
                /* {field: 'entrustId',title: 'BussRepoSecPtl.entrustId',type: 'text',align: 'left',sortable: false,edit: false},*/
                // 债券代码
                {
                    field: 'finprodId',
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
                , {field: 'pledStrdPrinAmt', title: '可质押券面总额', type: 'number', edit: false, sortable: false, width: 240}
                //质押券汇总券面总额
                , {field: 'pledgePrin', title: '质押券券面总额', type: 'number', edit: false, sortable: false, width: 240}
                // 首次净价
                , {
                    field: 'firstNetPrice',
                    width: 100,
                    title: '首次净价',
                    sortable: false
                }
                //折算比例%
                , {field: 'scale', title: '折算比例%', type: 'number', edit: false, sortable: false, width: 100}

                // 登记托管机构
                , {
                    field: 'repRegistOrgStr',
                    title: 'BussRepoSecPtl.repRegistOrg',
                    type: 'text',
                    align: 'left',
                    edit: false,
                    sortable: false,
                    width: 200
                }

            ],
            done: function () {
            },
            beforeEditCell: function (preVal, field, rowData) {
                return false;
            },
            checkbox: false
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
        debugger;
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