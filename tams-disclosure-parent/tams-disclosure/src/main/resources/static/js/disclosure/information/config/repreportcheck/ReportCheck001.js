(function () {
    /**
     * 获取查询参数
     */
    // var getSearchParams = function () {
    //     var data = $.serializeToObject('#searchForm');
    //     return data;
    // };

    var getSearchParams = function() {
         return {
             checkDateStart:$('#checkDate').startDate(),
             checkDateEnd:$('#checkDate').endDate(),
             finprodId:$('#finprodId').val() == null ? null : $('#finprodId').val().join(","),
             prodId:$('#prodId').val() == null ? null : $('#prodId').val().join(","),
             reportType:$('#reportType').val() == null ? null : $('#reportType').val().join(","),
             reportName:$('#reportName').val() == null ? null : $('#reportName').val().join(",")
         };
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },

        /**
         * 定义表头
         */
        cols: [
            {field:'reportCheckId',  visible: false,width:80}
            ,{field:'reportTypeName',  title: '报表类别 ',sortField:"reportType",width:200,sortable:false}
            ,{field:'reportName',  title: '报表名称',sortField:"reportName",width:200,sortable:false}
            , {field:'prodName',  title: '产品全称',sortField:"prodName",width:300,sortable:false}
            , {field:'pprodId',  title: '产品主代码',sortField:"pprodId",width:200,sortable:false}
            , {field:'prodId',  title: '子产品代码',sortField:"prodId",width:200,sortable:false}
            ,{field:'checkDate',  title: '检查日期',sortField:"checkDate",type:'date',width:120,sortable:false}
            ,{field:'errormsg',  title: '错误信息',sortField:"errormsg",width:400,sortable:false}
            ,{field:'fileNo',  visible: false}
            ,{
                title : '操作',
                field : 'operation',
                type : 'action',
                width : 120,
                buttons : [
                    {
                        text : '&#xe615;',
                        clas : 'view',
                        title : '预览'
                    } ]
            },
        ]
    });


    table.bind('click', '.view', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("文件预览"),
            basePath + "disclosure/information/config/repreportcheck/ReportCheck003/init",
            "95%", "96%", {
                fileNo:data.fileNo
            });
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    function addrule() {
        Joyin.dialog("产品选择", basePath + "disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager003/init", "80%", "80%",
            {}, function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if (!finprodIds||finprodIds.length == 0) {
                    return;
                }
                $('#finprodId').textSelect('setValue', finprodIds);
            });
        return;
    };
    function addrule1() {
        Joyin.dialog("产品选择", basePath + "disclosure/information/config/disprodplanmanage/DisProdPlanManage004/init", "80%", "80%",
            {}, function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if (!finprodIds||finprodIds.length == 0) {
                    return;
                }
                $('#prodId').textSelect('setValue', finprodIds);
            });
        return;
    };

    /**
     * 画面加载完成
     */
    $(function () {
        $('#ReportCheck001_btn_treeAdd').click(addrule);

        $('#ReportCheck001_btn_treeChildrenAdd').click(addrule1);
        //导出
        $('#ReportCheck001_btn_export').click(function () {
            exportExcel();
        });
        //初始化查询
        $('#ReportCheck001_btn_search').click(function () {
            table.load();
            //$('#ReportCheck001_btn_delete').show();
        });

        $('#ReportCheck001_btn_tips').click(function () {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("请勾选数据"));
                return;
            }
            var object = {selectedItem: selArr, operateType: "delete"};//请求参数
            Joyin.confirm("是否不再对勾选数据的错误信息进行提示", function () {
                Joyin.ajaxJson(basePath + 'disclosure/information/config/repreportcheck/ReportCheck001/delete', object,
                    function(result) {
                        table.reload();
                    });
            });
        });

        $('#ReportCheck001_btn_set').click(function () {
            Joyin.dialog(JoyinUtil.getLabel('指标阈值配置'), '/disclosure/information/config/repreportcheck/ReportCheck002/init', "50%", "90%",{pageModel:PAGE_MODEL_NEW},
                function(result) {
                }
            );
        });

    });
}());
