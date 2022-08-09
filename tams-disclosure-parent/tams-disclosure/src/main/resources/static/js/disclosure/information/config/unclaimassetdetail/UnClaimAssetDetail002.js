(function() {
    /**
     * 获取表单参数
     */
    //画面联动：输入产品名称，带出产品主代码
    var prodNameChange = function() {
        if (!$('#prodId').select2('data') || $('#prodId').select2('data').length == 0 || !$('#prodId').select2('data')[0]) {
            return;
        }
        var finprodId = $('#prodId').select2('data')[0].id;
        $('#prodName').val(finprodId);
    };

    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.unclaimList = unclaimTable.getAllRows();
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
        data: unclaimList == null ? null: unclaimList,
        searchOnInit: true,
        checkbox: false,
        page: false,
        height: '225px',
        cols: [
            //列表中的序号，为当前非标明细表中所查到所有非标的数量加上当前列表的行数
            { field: 'projectNo', edit: false,editoption:{
                type:'select',id:'projectNo',showField:'id',formatoption:true,
                url: JoyinUtil.getRealPath('/invest/basicdata/msttradeopponent/MstTradeOpponent002/getAccount')},
                sortable: false, width: 120, title: '序号'}
            , { field: 'financier',sortable: false, width: 120, title: '融资客户',edit: true, type: 'text' }
            , { field: 'projectName',sortable: false, title: '项目名称', width: 150, edit: true, type: 'text'}
            , { field: 'remFinancingPeriod', sortable: false, title: '剩余融资期限', width: 150, edit: true, type: 'text'}
            , { field: 'incomeDistribute', edit: true, sortable: false, width: 120, title: '到期收益分配',type: 'text'}
            , { field: 'trdConstruct', sortable: false, title: '交易结构', width: 120, edit: true, type: 'text' }
            , { field: 'riskInfo', sortable: false, title: '风险状况', width: 120, edit: true, type: 'text'}
            , { field: 'operation', sortable: false, title: 'SysCommon.operation', type: 'action', buttons: ['delt'] }
        ],
        addbtn: pageModel == PAGE_MODEL_REFERENCE ? false:true,
        addclick: function(row) {
            var _row = unclaimTable.getRow(0);
            if (_row.projectNo==null){
                Joyin.ajaxJson(basePath + 'disclosure/information/config/unclaimassetdetail/UnClaimAssetDetail002/selectCount', '', function (result) {
                    row.projectNo=result['count'];
                    unclaimTable.renderRow(unclaimTable.getAllRows().length-1, row);
                });
            }else {
                var row2 = unclaimTable.getRow(unclaimTable.getAllRows().length-2);
                row.projectNo=row2.projectNo+1;
            }
        },
        editCell : function(value, data, field, cell, column){
               if (value == '无'){
                   var RowData = {};
                   RowData.financier='无';
                   RowData.projectName='无';
                   RowData.remFinancingPeriod='无';
                   RowData.incomeDistribute='无';
                   RowData.trdConstruct='无';
                   RowData.riskInfo='无';
                   unclaimTable.clear();
                   unclaimTable.addRow(RowData);
                   $('.btn-table-add').hide();
               }
            unclaimTable.renderRow(data.LAY_TABLE_INDEX, RowData);
        },
        done: function () {
            this.bind('click', '.delt', function (data, index) {
                var object = { selectedItem: [data], operateType: "delete" };
                unclaimTable.deleteRow(index);

            });
        }
    });


    function　init(){
        if(pageModel != PAGE_MODEL_NEW){
            $("#prodId").attr("disabled",true);
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
        $('#prodId').change(function() {
            prodNameChange();
        });

    });
}());