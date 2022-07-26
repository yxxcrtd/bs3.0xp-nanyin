(function() {
    /**
     * 获取表单参数
     */
    // var getPageParam = function () {
    //     //        var paramObj = $.serializeToObject('#submitForm');
    //     var paramObj = {};
    //
    //     // 模拟交易列表
    //     paramObj.uncliamList = unclaimTable.getAllRows();
    //
    //     return paramObj;
    // }




    //画面联动：输入产品名称，带出产品主代码
    var prodNameChange = function() {
        if (!$('#prodName').select2('data') || $('#prodName').select2('data').length == 0 || !$('#prodName').select2('data')[0]) {
            return;
        }
        var finprodId = $('#prodName').select2('data')[0].id;
        $('#prodId').val(finprodId);
    };

    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.uncliamList = unclaimTable.getAllRows();
        Joyin.ajaxJson(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/save', object, "UnClaimAssetDetail002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/save', object, "UnClaimAssetDetail002_btn_submit");
                }
            });
        });
    }

    // 非标资产明细列表
    var unclaimTable = $("#unclaimTable").joyintable({
        // ajax: {
        //     url: 'selectDetailList',
        //     type: 'POST',
        //     data: {nostdAssetId: $("#nostdAssetId").val()
        //     }
        // },
        data: uncliamList == null ? [] : uncliamList,
        searchOnInit: true,
        checkbox: false,
        page: false,
        height: '225px',
        cols: [
            { field: 'projectNo', edit: false, sortable: false, width: 120, title: '序号'}
            , { field: 'financier',sortable: false, width: 120, title: '融资客户',edit: true, type: 'text' }
            , { field: 'projectName',sortable: false, title: '项目名称', width: 150, edit: true, type: 'text'}
            , { field: 'remFinancingPeriod', sortable: false, title: '剩余融资期限', width: 150, edit: true, type: 'text'}
            , { field: 'incomeDistribute', edit: true, sortable: false, width: 120, title: '到期收益分配',type: 'text'}
            , { field: 'trdConstruct', sortable: false, title: '交易结构', width: 120, edit: true, type: 'text' }
            , { field: 'riskInfo', sortable: false, title: '风险状况', width: 120, edit: true, type: 'text'}
            , { field: 'operation', sortable: false, title: 'SysCommon.operation', type: 'action', buttons: ['delt'] }
        ],
        addbtn: true,
        done: function () {
            this.bind('click', '.delt', function (data, index) {
                var object = { selectedItem: [data], operateType: "delete" };
                unclaimTable.deleteRow(index);

            });
        }
    });

    function　init(){
        if(pageModel != PAGE_MODEL_NEW){
            $("#prodName").attr("disabled",true);
        }
        //修改时，如果生效日小于等于平台日，生效日不能修改
        if($('#effDate').val() != '' && $('#effDate').val() != null){
            if($('#effDate').val()<=platDate){
                $("#effDate").attr("disabled",true);
            }
        }

        //修改时，如果生效截止日小于等于平台日，生效日截止不能修改
        if($('#effEndDate').val() != '' && $('#effEndDate').val() != null){
            if($('#effEndDate').val()<=platDate){
                $("#effEndDate").attr("disabled",true);
            }
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#UnClaimAssetDetail002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#UnClaimAssetDetail002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        //产品名称联动
        $('#prodName').change(function() {
            prodNameChange();
        });

    });
}());