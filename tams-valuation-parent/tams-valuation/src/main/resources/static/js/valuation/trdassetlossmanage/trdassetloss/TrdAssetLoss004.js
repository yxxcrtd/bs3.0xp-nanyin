/**
 *
 */
(function () {

    //初始化委托列表
    /**
     * 定义数据表格
     */
    var cols = [
        {field: 'valDate', title: '减值行情日', type: 'date', width: '10%', sortField: "valDate"}
        , {field: 'finprodId', title: '金融产品代码', width: '10%', sortField: "finprodId",visible: false}
        , {field: 'finprodAbbr', title: '金融产品简称', width: '10%', sortField: "finprodAbbr"}
        , {field: 'assetType1', title: '资产类型', width: '10%', sortField: "assetType1",visible: false}
        , {field: 'assetType1Str', title: '资产类型', width: '10%', sortField: "assetType1Str"}
        , {field: 'vDate', title: '资产起息日', type: 'date', width: '10%', sortField: "vDate"}
        , {field: 'mDate', title: '资产到期日', type: 'date', width: '10%', sortField: "mDate"}
        , {field: 'assetExerciseDate', title: '资产行权日', type: 'date', width: '10%', sortField: "assetExerciseDate"}
        , {field: 'strDate', title: '初始确认起始日', type: 'date', width: '10%', sortField: "strDate"}
        , {field: 'endDate', title: '初始确认截止日', type: 'date', width: '10%', sortField: "endDate"}
        , {field: 'lossTestType', title: '减值测算方式', width: '10%', sortField: "lossTestType",visible: false}
        , {field: 'lossTestTypeStr', title: '减值测算方式', width: '10%', sortField: "lossTestTypeStr"}
        , {field: 'stage', title: '减值阶段', width: '10%', sortField: "stage",visible: false}
        , {field: 'stageStr', title: '减值阶段', width: '10%', sortField: "stageStr"}
        , {field: 'overDays', title: '逾期天数', width: '10%', sortField: "overDays"}
        , {field: 'pdValue', title: 'PD违约概率%', width: '10%', sortField: "pdValue"}
        , {field: 'lgdValue', title: 'LGD违约损失率%', width: '10%', sortField: "lgdValue"}
        , {field: 'eclVaule', title: 'ECL预期信用损失比例%', width: '10%', sortField: "eclVaule"}
        , {field: 'peroid', title: '待偿期（年）', width: '10%', sortField: "peroid"}
        , {field: 'lossInputPattern', title: '减值录入方式', width: '10%', sortField: "lossInputPattern",visible: false}
        , {field: 'lossInputPatternStr', title: '减值录入方式', width: '10%', sortField: "lossInputPatternStr"}
        , {field: 'lossRatio', title: '减值比例%', width: '10%', sortField: "lossRatio"}
        , {field: 'checkStatusStr', title: '检查结果', width: '10%', sortField: "checkStatusStr"}
        , {field: 'tradeId', title: '交易编号', width: '10%', sortField: "tradeId", visible: false}
        , {field: 'remark', title: '备注', width: '10%', sortField: "remark"}
    ];
    var table = $("#dataTable").joyintable({
        data: trdAssetLossList == null || trdAssetLossList == '' ? null : JSON.parse(trdAssetLossList),
        cols: cols,
        height: 250,
        checkbox: false,
        filter: false,
        page: false,
        addbtn:true
    });

    //绑定上传事件
    table.bind('click', '.uploadFile', function (data) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {};
        object.rowData = JSON.stringify(data);
        object.businessId = data.businessId;
        Joyin.dialog("上传附件",
            JoyinUtil.getRealPath("component/entrust/EtrListUploadFile/init"),
            "88%", "85%", object,
            function(result){
                var fileArrayName = '';
                if (result && result.fileArray && _.isArray(result.fileArray) && result.fileArray.length>0) {
                    for (var index in result.fileArray) {
                        fileArrayName += result.fileArray[index].srcName +'，';
                    }
                    result.fileArrayName = fileArrayName.substr(0,fileArrayName.length-1);
                }
                table.renderRow(data.LAY_TABLE_INDEX, result);
            });
    });

    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        //校验数据  校验表格
        var tableData = table.getAllRows();
        if (tableData.length == 0) {
            Joyin.alert("委托列表至少有一条数据！", 2);
            return false;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        var objects = object.selectedItem;
        for (var index in objects) {
            var temp = objects[index];
            temp.operateType = operateType;
            temp.pageModel = pageModel;
            if (operateType === 'save') {
                temp.elementId = 'TrdAssetLoss002_btn_save';
            } else {
                temp.elementId = 'TrdAssetLoss002_btn_submit';
            }
        }
        var allDto = {};
        allDto.importFlow = true;
        allDto.selectedItem = objects;
        Joyin.ajaxJson(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004/check', objects,
            function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004/save', allDto, "TrdAssetLoss002_btn_save", "TrdAssetLoss002");
                    } else {
                        WorkFlow.doSubmitList(basePath + 'valuation/trdassetlossmanage/trdassetloss/TrdAssetLoss004/save', allDto, "TrdAssetLoss002_btn_submit", "TrdAssetLoss002");
                    }
                });
            });
    }

    $(function () {
        if (trdAssetLossList) {
            table.reload({data: JSON.parse(trdAssetLossList)});
        }
        /**
         * 提交
         */
        $('#TrdAssetLoss004_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        /**
         * 保存
         */
        $('#TrdAssetLoss004_btn_save').click(function () {
            saveOrSubmit('save');
        });

    })
}());