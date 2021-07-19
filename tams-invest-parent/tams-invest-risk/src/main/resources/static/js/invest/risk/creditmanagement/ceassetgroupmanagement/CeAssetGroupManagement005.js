$(function () {

    var getSearchParams = function() {
        var object = $.serializeToObject('#searchForm',true);
        object.pflsh = $("#pflsh").val();
        return object;
    };


    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: pageModel === PAGE_MODEL_NEW ? {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        }: null,
        // searchOnInit: true,
        height: 200,
        data: pageModel === PAGE_MODEL_NEW ? [] : PAGELIST,
        cols: [
            {field: 'pflsh', title:'批复号'}
            ,{field: 'assetGroupName', title: '系列名称'}
            ,{field: 'creditAmt', title: '授信额度（亿元）'}
            ,{field: 'vdate', title: '批复生效日', visible:false}
            ,{field: 'seqno', visible:false}
        ]
    });

    /**
     * 保存或提交
     *
     * @param operateType
     *            操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {

        //校验数据  校验表格
        var tableData = table.getAllCheckedRows();
        if (tableData.length == 0) {
            Joyin.alert("请选择新批复号！", 2);
            return false;
        }else {
            let b = 0;
            for (var i = 1;i < tableData.length;i++){
                if(tableData[i].pflsh != tableData[0].pflsh){
                    b++;
                }
            }
            if(b > 0){
                Joyin.alert("仅能选择一个系列授信", 2);
                return false;
            }else {
                if(new Date(DATALIST[0].vdate) < new Date(tableData[0].vdate)){
                    Joyin.alert("新批复生效日必须大于等于原批复生效日", 2);
                    return false;
                }
            }

        }

        var object = $.serializeToObject('#submitForm');

        object.operateType = operateType;
        object.pageModel = pageModel;
        if (pageModel == PAGE_MODEL_NEW) {
            object.operateType = 'new';
        } else if (pageModel == PAGE_MODEL_EDIT) {
            object.operateType = 'update';
        }
        object.dataList = DATALIST;
        object.seqno = tableData[0].seqno;
        Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement005/check',
            object, function(result) {
                Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmitList(basePath  + 'invest/risk/creditmanagement/ceassetgroupmanagement/CeAssetGroupManagement005/save',
                        object, "CeAssetGroupManagement005_btn_submit");
                });
            });
    }

    var init = function(){
        debugger;
        var tableData = table.getAllCheckedRows();
        table.load();

    }

    /**
     * 画面加载完成
     */
    $(function() {
        init();

        $('#CeAssetGroupManagement005_btn_search').click(function() {
            table.reload();
        });

        // 绑定按钮事件
        $('#CeAssetGroupManagement005_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());