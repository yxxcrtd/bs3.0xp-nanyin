(function () {
    var dataTable = $('#dataTable').joyintable({
        data: dataList == null ? [] : dataList,
        searchOnInit: true,
        checkbox: false,
        loadonce: true,
        page: false,
        cols: [
            { field: 'checkId', visible: false }
            , { field: 'checkDesc', width: 500, sortable: false, title: '指标名称' }
            , {
                field: 'checkType', width: 250, sortable: false, edit: true, title: '比较符',
                editOption: {
                    type: 'select', multiple: false, searchable: true, result: REP_CHECK_TYPE
                }
            }
            , { field: 'paramValue1', sortable: false, edit: true, title: '阈值' }
        ],
        done: function () {
        }
    });

    var sava = function() {
        let paramObj = {};
        paramObj.dataList = JSON.stringify(dataTable.getAllRows()) ;
        Joyin.ajaxJson(basePath + 'disclosure/information/config/repreportcheck/ReportCheck002/check', paramObj, function(result) {
            // Joyin.confirm(result.data, function() {
            //     Joyin.ajax(basePath + 'disclosure/information/config/repreportcheck/ReportCheck002/insert',
            //         {
            //             pageModel: pageModel,
            //             object: JSON.stringify(object),
            //             addParam: JSON.stringify(addParam),
            //             liparams: object.liparams
            //         },
            //         function (result) {
            //             var index = parent.layer.getFrameIndex(window.name);
            //             parent.layer.close(index);
            //         });
            // });
        });
    }

    $(function () {
        $('#ReportCheck002_btn_save').click(function () {
            sava();
        });
    });
}());