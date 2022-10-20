(function () {
    /**
     * 获取查询参数
     */

    var getSearchParams = function () {
        return {
            pProdId: $('#pProdId').val() == null ? null : $('#pProdId').val().join(","),
            prodId: $('#prodId').val() == null ? null : $('#prodId').val().join(","),
            reportName: $('#reportName').val(),
            reportType: $('#reportType').val() == null ? null : $('#reportType').val().join(","),
            reviewTgStatus: $('#reviewTgStatus').val() == null ? null : $('#reviewTgStatus').val().join(","),
            reviewTgUser: $('#reviewTgUser').val() == null ? null : $('#reviewTgUser').val().join(","),
            reviewTgTimeStart: $('#reviewTgTime').startDate(),
            reviewTgTimeEnd: $('#reviewTgTime').endDate(),
            reportDateStart : $('#reportDate').startDate(),
            reportDateEnd : $('#reportDate').endDate()
        };
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: basePath + 'disclosure/information/reveal/reportreviewtg/ReportReviewTg001/findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        checkbox: true,
        cols: [
            { field: 'id', title: 'id', visible: false },
            { field: 'reviewTgStatus', title: 'reviewTgStatus', sort: true, visible: false },
            { field: 'reviewTgStatusStr', title: '托管复核状态', width: 120, sort: true, sortField: "reviewTgStatus"
                ,render: function (data, type, row, meta) {
                    if(row.reviewTgStatus==='02'){
                        return '<span class="layui-badge-rim layui-bg-blue">'+row.reviewTgStatusStr+'</span>';
                    }else if(row.reviewTgStatus==='01'){
                        return '<span class="layui-badge-rim layui-bg-red">'+row.reviewTgStatusStr+'</span>';
                    }else if(row.reviewTgStatus==='03'){
                        return '<span class="layui-badge-rim layui-bg-orange">'+row.reviewTgStatusStr+'</span>';
                    }else{
                        return '';
                    }
                }},
            { field: 'reviewTgTimeStr', title: '托管复核时间', width: 150, sort: true , sortField: "reviewTgTime"},
            { field: 'reviewTgUser', title: '托管复核人', width: 100, sort: true },
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
            { field: 'createTimeStr', title: '生成时间', width: 150, sort: true, sortField: "createTime" },
            { field: 'confirmDateStr', title: '确认时间', width: 150, sort: true },
            { field: 'isConfirmName', title: '确认方式', width: 100, sort: true, sortField: "isConfirm" },
            { field: 'reviewTimeStr', title: '报表复核时间', width: 150, sort: true,sortField: "reviewTime" },
            { field: 'reviewUser', title: '复核人', width: 100, sort: true },
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
                fileNo: data.confirmFilePath
            });
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function () {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    //下载模板
    var download = function(){
        var object =null;
        var rowArr = table.getAllCheckedRows();// 所有选中行数据
        var ids = [];

        //// 不点击查询，直接点击导出所有报表文件;点击查询，未勾选，导出所有报表文件；点击查询，已勾选，导出勾选 报表文件 zhangyf 20190109
        if(rowArr == null || rowArr.length ==0){
            layer.msg("请勾选一个数据！");
        }
        else {
            for(var i = 0 ; i<rowArr.length ; i++){
                ids[i] = rowArr[i].id;
            }
            Joyin.exportExcel('downloadReport', {id:JSON.stringify(ids),data:JSON.stringify(rowArr)});
        }
    };

    /**
     * 退回数据
     */
    var returnData = function () {
        var object ={};
        var idList = [];
        if (table != null && table !== '') {
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length === 0) {
            Joyin.error(JoyinUtil.getMessage("未选择数据"));
            return;
        }
        if(selArr.length === 1){
            for(let i = 0 ; i<selArr.length ; i++){
                if(selArr[i].reviewTgStatus==='02'){
                    Joyin.error("数据已经复核，不能执行退回操作！");
                    break;
                }
                else {
                    idList[i] = selArr[i].id;
                }
            }
        }else {
            for(let i = 0 ; i<selArr.length ; i++){
                if(selArr[i].reviewTgStatus==='02'){
                    Joyin.error("您所勾选的数据包含已复核的数据，不能执行退回操作!");
                    break;
                }
                else {
                    idList[i] = selArr[i].id;
                }
            }
        }

        if(idList.length !== 0 && idList.length === selArr.length){
            Joyin.confirm("所选数据，审核不通过，现退回？", function(){
                object.id = JSON.stringify(idList);
                object.data = JSON.stringify(selArr)
                Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportreviewtg/ReportReviewTg001/pushRevealCheck', object, function(result) {
                    Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportreviewtg/ReportReviewTg001/returnData', object, function(result) {
                        table.reload();
                    });
                });

            });
        }
    };

    /**
     * 退回数据
     */
    var pushReveal = function () {
        var object ={};
        var idList = [];
        if (table != null && table !== '') {
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length === 0) {
            Joyin.error(JoyinUtil.getMessage("未选择数据！"));
            return;
        }
        if(selArr.length === 1){
            for(let i = 0 ; i<selArr.length ; i++){
                if(selArr[i].reviewTgStatus==='02' ){
                    Joyin.error("数据已经复核");
                    break;
                }
                else {
                    idList[i] = selArr[i].id;
                }
            }
        }else{
            for(let i = 0 ; i<selArr.length ; i++){
                if(selArr[i].reviewTgStatus==='02' ){
                    Joyin.error("您所勾选的数据包含已复核的数据，不能执行推送待披露操作");
                    break;
                }
                else {
                    idList[i] = selArr[i].id;
                }
            }
        }

        if(idList.length !== 0 && idList.length === selArr.length){
            Joyin.confirm("所选数据，您已经审核通过，现直接推送到待披露？", function(){
                object.id = JSON.stringify(idList);
                object.data = JSON.stringify(selArr)
                Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportreviewtg/ReportReviewTg001/pushRevealCheck', object, function(result) {
                    Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportreviewtg/ReportReviewTg001/pushReveal', object, function(result) {
                        table.reload();
                    });
                });
            });
        }
    };
    function addrule() {
        Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage003/init","80%","80%",
            {},function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if(finprodIds.length ==0){
                    return;
                }
                $('#prodId').textSelect('setValue',finprodIds);
            });
        return;
    };
    function addrule1() {
        Joyin.dialog("产品选择", basePath + "disclosure/information/config/disprodplanmanage/DisProdPlanManage004/init", "80%", "80%",
            {}, function () {
                var finprodIds = $('#finprodIds').data('finprodIds');
                if (finprodIds.length == 0) {
                    return;
                }
                $('#prodName').textSelect('setValue', finprodIds);
            });
        return;
    };


    /**
     * 画面加载完成
     */
    $(function () {
        $('#ReportReviewTg001_btn_treeAdd').click(addrule);
        $('#ReportReviewTg001_btn_treeChildrenAdd').click(addrule1);
        //初始化查询
        $('#ReportReviewTg001_btn_search').click(function () {
            table.load();
        });

        $('#ReportReviewTg001_btn_back').click(function () {
            returnData();
        });
        $('#ReportReviewTg001_btn_push_reveal').click(function () {
            pushReveal();
        });
        //导出
        $('#ReportReviewTg001_btn_export').click(function () {
            exportExcel();
        });
        $('#ReportReviewTg001_btn_export_file').click(function () {
            download();
        });
    });
}());
