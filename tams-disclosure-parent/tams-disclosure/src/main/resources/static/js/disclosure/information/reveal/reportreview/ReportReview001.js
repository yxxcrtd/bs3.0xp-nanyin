(function () {
    /**
     * 获取查询参数
     */

    var getSearchParams = function () {
        return {
            pProdId: $('#pProdId').val() == null ? null : $('#pProdId').val().join(","),
            prodId: $('#prodId').val() == null ? null : $('#prodId').val().join(","),
            reportType: $('#reportType').val() == null ? null : $('#reportType').val().join(","),
            reviewStatus: $('#reviewStatus').val() == null ? null : $('#reviewStatus').val().join(","),
            reviewTimeStart: $('#reviewTime').startDate(),
            reviewTimeEnd: $('#reviewTime').endDate(),
        };
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: basePath + 'disclosure/information/reveal/reportreview/ReportReview001/findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        checkbox: true,
        cols: [
            { field: 'id', title: 'id', visible: false },
            { field: 'reviewStatus', title: 'reviewStatus', sort: true, visible: false },
            { field: 'reviewStatusStr', title: '复核状态', width: 90, sort: true, sortField: "reviewStatus"},
            { field: 'reviewTimeStr', title: '复核时间', width: 150, sort: true },
            { field: 'reviewUser', title: '复核人', width: 100, sort: true },
            { field: 'prodid', title: '产品代码', width: 150, sort: true },
            { field: 'prodName', title: '产品名称', width: 350, sort: true },
            { field: 'reportCategory', title: 'reportCategory', sort: true, visible: false },
            { field: 'reportCategoryName', title: '报表类别', width: 100, sort: true, sortField: "reportCategory" },
            { field: 'reportName', title: '报表名称', width: 200, sort: true },
            { field: 'reportDate', title: '报告期', width: 170, sort: true },
            { field: 'revealEndDateStr', title: '披露截止日', width: 150, sort: true },
            {
                field: 'confirmFileName', title: '文件名称', width: 300, sort: true
                , render: function (data, type, row, meta) {
                    return '<a href="' + JoyinUtil.getRealPath('/disclosure/information/reveal/reportconfirm/ReportConfirmManage001/downLoad?' + 'sysFilepath=' + row.confirmFilePath + '&prodId=' + row.prodid + '&reportDate=' + row.reportDate + '&reportName=' + row.reportName) + '">' + data + '</a>';
                }
            },
            { field: 'createTimeStr', title: '生成时间', width: 150, sort: true },
            { field: 'confirmDateStr', title: '确认时间', width: 150, sort: true },
            { field: 'isConfirmName', title: '确认方式', width: 100, sort: true, sortField: "isConfirm" },

            { field: 'repId', title: '报表代码', sort: true, visible: false },
            {
                field: 'annex', title: '附件', width: 300, sort: true
                , render: function (data, type, row, meta) {
                    var str = '<div style="width:210px;height:28px; overflow:hidden;float:left">' + '<a style="width:100%" href="' + JoyinUtil.getRealPath('/disclosure/information/reveal/reportconfirm/ReportConfirmManage001/downLoad?' + 'sysFilepath=' + row.annexPath + '&prodId=' + row.prodid + '&reportDate=' + row.reportDate + '&reportName=' + row.reportName) + '">' + data + '</a>' + '</div>';
                    return str;
                }
            },
            {
                field: 'operation', title: '预览', fixed: 'right', width: 120,
                render: function (data, type, row, meta) {
                    let str = "";
                    str += '<i title="预览" class="iconfont icon-search view"></i>';
                    return str;
                }

            }

        ]
    });


    table.bind('click', '.view', function (data, index) {
        Joyin.dialog(JoyinUtil.getLabel("文件预览"),
            basePath + "disclosure/information/config/repreportcheck/ReportCheck003/init",
            "95%", "96%", {
                fileNo: data.fileNo
            });
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function () {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    /**
     * 画面加载完成
     */
    $(function () {
        // //导出
        // $('#ReportCheck001_btn_export').click(function () {
        //     exportExcel();
        // });
        //初始化查询
        $('#ReportReview001_btn_search').click(function () {
            table.load();
        });
        //
        // $('#ReportCheck001_btn_tips').click(function () {
        //     if (table != null && table != '') {
        //         var selArr = table.getAllCheckedRows();
        //     }
        //     if (!selArr || selArr.length == 0) {
        //         Joyin.error(JoyinUtil.getMessage("请勾选数据"));
        //         return;
        //     }
        //     var object = { selectedItem: selArr, operateType: "delete" };//请求参数
        //     Joyin.confirm("是否不再对勾选数据的错误信息进行提示", function () {
        //         Joyin.ajaxJson(basePath + 'disclosure/information/config/repreportcheck/ReportCheck001/delete', object,
        //             function (result) {
        //                 table.reload();
        //             });
        //     });
        // });


    });
}());
