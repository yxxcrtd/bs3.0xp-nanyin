(function () {
    function query(ele) {
        return $("#CfetsQuoteRepo003_bodydiv " + ele);
    }

    /**
     * 保存
     */
    var saveOrSubmitAction = function (operatorType) {
        var object = $.serializeToObject('#submitForm', true);
        object.operatorType = operatorType;
        object.isDzh = 'Y';
        object.pageId = "CfetsQuoteRepo003";
        object.ptlAllocationList = ptlAllocations;
        object.countPledgeBondList = repoEntrustPledgeDtos;
        Joyin.confirm("是否确认提交?",
            function () {
                //保存 走流程
                if (operatorType === 'save') {
                    WorkFlow.doSaveList(basePath + 'cfets/bankuplink/repo/IbuQuoteRepo003/save', object, "CfetsQuoteRepo003_btn_save", null,
                        function () {
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        })
                }
                //提交 走流程
                else if (operatorType === 'submit') {
                    WorkFlow.doSubmitList(basePath + 'cfets/bankuplink/repo/IbuQuoteRepo003/save', object, "CfetsQuoteRepo003_btn_submit", null,
                        function () {
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        })
                }
            });
    };
    /**
     * 组合分配
     */
    var colsCombDistribt = [
        {
            /* 指令代码 */
            field: 'entrustId', width: 200, title: '指令代码', sortable: false, disabled: true
        }
        , {/*组合名称*/
            field: 'portfolioName',
            width: 120,
            title: '组合名称',
            sortable: false
            , disabled: true
        }
        , {
            field: 'avaNum',
            width: 150,
            title: '剩余指令金额(元)',
            sortable: false,
            type: 'money',
            digit: 2
            , disabled: true
        }
        , {
            field: 'fpriceAmt',
            width: 120,
            title: '交易金额(元)',
            sortable: false,
            type: 'money',
            digit: 2
            , disabled: true
        }
        , {
            field: 'dealFeeAmt',
            width: 120,
            title: '交易手续费(元)',
            sortable: false,
            type: 'money',
            digit: 2
            , disabled: true
        }
        , {
            field: 'settFeeAmt',
            width: 100,
            title: '结算服务费(元)',
            sortable: false,
            type: 'money',
            digit: 2
            , disabled: true
        }
        , {
            field: 'intAmt',
            width: 100,
            title: '回购利息(元)',
            sortable: false,
            type: 'money',
            digit: 2
            , disabled: true
        }
        , {
            field: 'mtradeAmt',
            width: 100,
            title: '到期结算金额(元)',
            sortable: false,
            type: 'money',
            digit: 2
            , disabled: true
        }
        , {
            field: 'priceModelStr',
            width: 100,
            title: '价格模式',
            sortable: false
        }
        , {
            field: 'price',
            width: 100,
            title: '回购利率(%)',
            sortable: false,
            type: 'number',
            digit: 4
            , disabled: true
        }
    ]
    /**
     * 质押券信息
     */
    var pledgeTableCols = [
        {
            /* 指令代码 */
            field: 'bondCode', width: 100, title: '债券代码', sortable: false, disabled: true
        }
        , {/*组合名称*/
            field: 'bondAbbrName',
            width: 150,
            title: '债券简称',
            sortable: false
            , disabled: true
        }
        , {
            field: 'portfolioId',
            width: 120,
            title: '组合代码',
            sortable: false
            , disabled: true
        }
        , {
            field: 'portfolioName',
            width: 120,
            title: '组合名称',
            sortable: false
            , disabled: true
        }
        , {
            field: 'avaPledgePrinStr',
            width: 150,
            title: '可质押券面总额(万元)',
            sortable: false,
            type: 'number',
            digit: 2
            , disabled: true
        }
        , {
            field: 'pledgePrinStr',
            width: 150,
            title: '质押券面总额(万元)',
            sortable: false,
            type: 'number',
            digit: 2
            , disabled: true
        }
        , {
            field: 'firstNetPrice',
            width: 100,
            title: '首次净价(元)',
            sortable: false,
            type: 'money',
            digit: 4
            , disabled: true
        }
        , {
            field: 'pledgeRate',
            width: 100,
            title: '折算比例(%)',
            sortable: false,
            type: 'number',
            digit: 2
            , disabled: true
        }
        , {
            field: 'repRegistOrgStr',
            width: 200,
            title: '登记托管机构',
            sortable: false
            , disabled: true
        }
    ]
    var table = query("#bussCombDistribtTable").joyintable({
        data: ptlAllocations == null ? [] : ptlAllocations,
        cols: colsCombDistribt,
        searchOnInit:true,
        checkbox : false,
        height : 140,
        page:false
    });
    var pledgeTable = query("#pledgeTable").joyintable({
        data: repoEntrustPledgeDtos == null ? [] : repoEntrustPledgeDtos,
        cols: pledgeTableCols,
        searchOnInit:true,
        checkbox : false,
        height : 140,
        page:false
    });


    //流程中数据不可编辑
    hideRepoPage = function () {
        if (workflowId) {
            $('#submitForm').find('input,text,textarea,select').attr('disabled', 'disabled');
        }

    }

    $(function () {
        hideRepoPage();
        /**
         * 保存按钮按下处理
         */
        $('#CfetsQuoteRepo003_btn_save').click(function () {
            saveOrSubmitAction('save');
        });

        /**
         * 提交按钮按下处理
         */
        $('#CfetsQuoteRepo003_btn_submit').click(function () {
            saveOrSubmitAction('submit');
        });

    });

}());