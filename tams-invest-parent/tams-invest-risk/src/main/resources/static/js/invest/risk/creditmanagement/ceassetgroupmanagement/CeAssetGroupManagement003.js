$(function () {
    // var nodeId = '';
    //获取查询参数
    var getSearchParams = function () {
        var param = $.serializeToObject('#submitForm');
        param.workflowId = workflowId;
        if(param.finprodId) {
            param.finprodId = param.finprodId.join(',');
        }
        return param;
    };
    $('#CeAssetGroupManagement003_assetpoolName').prop('disabled',true);
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement003/findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        rowno: true,
        height : 200,
        loadonce: true,
        page:false,
        cols: [
            {field: 'finprodId', title:'CeAssetGroupManagement003.assetId'}
            , {field: 'finprodName', title: 'CeAssetGroupManagement003.assetName'}
        ]
    });
    
    function saveOrSubmit(operateType) {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
        }
        if ( !selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = getSearchParams();
        object.selectedItem = selArr;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement003/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType == 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement003/save', object, "CeAssetGroupManagement003_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement003/save', object, "CeAssetGroupManagement003_btn_submit");
                }
            });
        });
    }
    
    $(function () {
       /**
        * 画面加载完成
        */ 
        $('#CeAssetGroupManagement003_btn_save').click(function () {
            saveOrSubmit("save");
        });
        $("#CeAssetGroupManagement003_btn_submit").click(function () {
            saveOrSubmit('submit');
        });
        $("#CeAssetGroupManagement003_btn_search").click(function () {
            table.load();
        });
    });
    
}());