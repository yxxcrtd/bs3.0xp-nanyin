$(function() {
    var getPageParams = function() {
        var ccyOriginal = $('#ccyOriginal').val();
        if (ccyOriginal) {
            ccyOriginal = ccyOriginal.join();
        }
        var ccyConvert = $('#ccyConvert').val();
        if (ccyConvert) {
            ccyConvert = ccyConvert.join();
        }
        return {
            // 币种
            ccyOriginal : ccyOriginal,
            // 折算币种
            ccyConvert : ccyConvert,
            // 公布日期
            publishDateStart : $('#publishDate').startDate(),
            publishDateEnd : $('#publishDate').endDate()
        };
    };
    var table = $('#exchangerate001').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function(d) {
                return getPageParams();
            }
        },
        searchOnInit: false, //初始化的时候就请求后台数据
        cols:[
          { title: "数据状态", field: 'statusName', sortField: 'status'},
          { title: "币种", field: 'ccyOriginalName', sortField: 'ccyOriginal'},
          { title: "折算币种", field: 'ccyConvertName', sortField: 'ccyConvert'},
          { title: "汇率", field: 'exchangePrice', type: 'number', digit:8, width: 200},
          { title: "公布日期", field: 'publishDate', type: 'date'},
          { field: 'ccyOriginal',visible: false},
          { field: 'ccyConvert',visible: false},
          { field: 'status',visible: false},
          {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    // 查询按钮点击事件
    $('#Exchangerate001_btn_search').click(function() {
        if($('#searchForm').joyinvalid()) {
            table.load();
        }
    });
    
    // 导入按钮点击事件
    $('#Exchangerate001_btn_import').click(function() {
        Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'),function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax('checkConfirmMsgForImportData', {fileName: fileName}, function() {
                    Joyin.ajax('importFile', {fileName: fileName});
                },function() {
//                    $('iframe').contents().find("#loading").hide();
                    $('iframe')[0].contentWindow.JoyinUtil.hideLoading();
                });
                
            },function() {
                table.reload();
            });
        });
    });
    
    // 导出按钮点击事件
    $('#Exchangerate001_btn_export').click(function() {
        var param = getPageParams();
        var rows = table.getAllCheckedRows();
        if(rows.length > 0) {
            var keyList = [];
            $.each(rows, function(i, row) {
                keyList.push(row.ccyOriginal +'|'+row.ccyConvert +'|'+Date.ofDate(row.publishDate).format()+'|'+row.status);
            });
            param.keys = keyList.join(',');
        }
        Joyin.exportExcel("export",param);
    });

    /**
     * 新建按钮点击事件
     * 跳转至新增页面
     */
    $('#Exchangerate001_btn_add').click(function() {
        var paramObj = {
                pageModel : PAGE_MODEL_NEW
            }
        var pageUrl = basePath + "svreport/submitmaintenance/exchangerate/Exchangerate002/init";
        Joyin.dialog("新建汇率行情", pageUrl, '60%', '60%', paramObj, function() {
            table.reload();
        });
    });

    //行内删除按钮点击事件
    table.bind('click','.delt', function(data, index) {
        var keyList = [];
//        $.each(rows, function(i, row) {
//            keyList.push(row.ccyOriginal +'|'+row.ccyConvert+'|'+Date.ofDate(row.publishDate).format());
//        });
        keyList.push(data.ccyOriginal +'|'+data.ccyConvert+'|'+Date.ofDate(data.publishDate).format());
        if(data.statusName != '有效'){
            return Joyin.error("只能删除有效数据，请重新选择");
        }
        Joyin.confirm("请确认是否要删除选中记录？", function() {
            Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
                table.reload();
            });
        });
    });
    
    // 删除按钮点击事件 删除数据
    $('#Exchangerate001_btn_delete').click(function(){
        var rows = table.getAllCheckedRows();
        if(rows.length === 0) {
            return Joyin.error("请至少选择一条记录");
        }
        for(var i=0;i<rows.length;i++){
            if(rows[i].statusName !="有效"){
                return Joyin.error("只能删除有效数据，请重新选择");
            }
        }
        var keyList = [];
        $.each(rows, function(i, row) {
            keyList.push(row.ccyOriginal +'|'+row.ccyConvert+'|'+Date.ofDate(row.publishDate).format());
        });
        Joyin.confirm("请确认是否要删除选中记录？",function() {
            Joyin.ajax('delete', {deleteKeys: keyList.join(',')}, function() {
                table.reload();
            });
        });
    });
    //编辑按钮点击事件
    table.bind('click','.edit', function(rows) {
        var row = rows;
        var date = Date.ofDate(row.publishDate);
        if(row.statusName != '有效'){
            return Joyin.error("只能编辑有效数据，请重新选择");
        }
        var paramObj = {
            ccyOriginal : row.ccyOriginal,
            ccyConvert : row.ccyConvert,
            publishDate : date.format(),
            status : row.status,
            pageModel : PAGE_MODEL_EDIT
        }
        Joyin.ajaxJson("checkCurrentRowData",{rowData:[row]},function(result) {
            if (result && result.statuCode == "200") {
                var pageUrl = basePath + "svreport/submitmaintenance/exchangerate/Exchangerate002/init";
                Joyin.dialog("编辑汇率行情", pageUrl, '60%', '60%', paramObj, function() {
                    table.reload();
                });
            }
        });
    });
});