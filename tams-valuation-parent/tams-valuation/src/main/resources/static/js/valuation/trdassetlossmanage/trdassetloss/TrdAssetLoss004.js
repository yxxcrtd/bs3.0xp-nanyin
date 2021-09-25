/**
 *
 */
(function () {

    //初始化委托列表
    /**
     * 定义数据表格
     */
    var cols = [
        {field: 'valDate', title: '减值行情日', type: 'date', sortable:false,width: '10%'}
        , {field: 'finprodId', title: '金融产品代码', sortable:false,width: '10%',visible: false}
        , {field: 'finprodAbbr', title: '金融产品简称',sortable:false, width: '10%'}
        , {field: 'assetType1', title: '资产类型', width: '10%', sortable:false,visible: false}
        , {field: 'assetType1Str', title: '资产类型', width: '10%' ,sortable:false}
        , {field: 'vDate', title: '资产起息日', type: 'date', sortable:false,width: '10%' }
        , {field: 'mDate', title: '资产到期日', type: 'date', sortable:false,width: '10%' }
        , {field: 'assetExerciseDate', title: '资产行权日', sortable:false,type: 'date', width: '10%'}
        , {field: 'strDate', title: '初始确认起始日', sortable:false,type: 'date', width: '10%'}
        , {field: 'endDate', title: '初始确认截止日', sortable:false,type: 'date', width: '10%'}
        , {field: 'lossTestType', title: '减值测算方式',sortable:false, width: '10%',visible: false}
        , {field: 'lossTestTypeStr', title: '减值测算方式',sortable:false, width: '10%'}
        , {field: 'stage', title: '减值阶段',sortable:false, width: '10%',visible: false}
        , {field: 'stageStr', title: '减值阶段', sortable:false,width: '10%'}
        , {field: 'overDays', title: '逾期天数', sortable:false,width: '10%'}
        , {field: 'pdValue', title: 'PD违约概率%', sortable:false,width: '10%'}
        , {field: 'lgdValue', title: 'LGD违约损失率%', sortable:false,width: '10%'}
        , {field: 'eclVaule', title: 'ECL预期信用损失比例%', sortable:false,width: '10%'}
        , {field: 'peroid', title: '待偿期（年）',sortable:false, width: '10%'}
        , {field: 'lossInputPattern', title: '减值录入方式', width: '10%',sortable:false,visible: false}
        , {field: 'lossInputPatternStr', title: '减值录入方式', sortable:false,width: '10%'}
        , {field: 'lossRatio', title: '减值比例%', sortable:false,width: '10%'}
        , {
            field: 'checkStatusStr', title: '检查结果',width:'10%',sortable:false,render: function (data, type, row, meta) {
                if (data == "不通过") {
                    return '<span style="color: red">'+data+'</span>';
                }else{
                    return data;
                }
            }
        }
        , {field: 'tradeId', title: '交易编号', width: '10%', sortable:false, visible: false}
        , {field: 'remark', title: '备注', width: '10%', sortable:false}
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