(function() {
    var getPageParams = function() {
        var insideProdId = $('#ProPeoplebankcodeManagement001_insideProdId').val();
        if(insideProdId==='为空'){
            insideProdId = null;
        }
        if (insideProdId) {
            insideProdId = insideProdId.join();
        }
        var orgCode = $("#orgCode").val();
        if (orgCode) {
            orgCode = orgCode.join();
        }
        return {
            orgCode : orgCode,
            // 内部产品代码
            insideProdId : insideProdId,
            // 人行产品代码(系统)
            pbcProjectCode : $('#ProPeoplebankcodeManagement001_pbcProjectCode').val(),
            // 人行资产池代码(系统)
            pbcAssetPoolCode : $('#ProPeoplebankcodeManagement001_pbcAssetPoolCode').val()
        };
    };
    var table = $('#ProPeoplebankcodeManagement001_proPeoplebankcodeManagement001').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [
            { title: "内部产品代码", field:'insideProdId',width:180},
            { title: "人行产品代码(外部)", field:'pbcProjectCodeExternal'},
            { title: "人行资产池代码(外部)", field:'pbcAssetPoolCodeExternal'},
            { title: "人行产品代码(系统)", field:'pbcProjectCodeSystem'},
            { title: "人行资产池代码(系统)", field:'pbcAssetPoolCodeSystem'},
            { title: "隐藏列代码类型", field:'codeType',visible:false,width:10},
        ]
    });

    $(function() {
        // 查询按钮点击事件
        $('#ProPeoplebankcodeManagement001_btn_search').click(function() {
            if($('#searchForm').joyinvalid()) {
                table.load();
                initFlag=false;
            }
        });
        // 导入按钮点击事件
        $('#ProPeoplebankcodeManagement001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'svreport/system/peoplebankcodepoolmanagement/ProPeoplebankcodeManagement001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'svreport/system/peoplebankcodepoolmanagement/ProPeoplebankcodeManagement001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });

        // 导出按钮
        $('#ProPeoplebankcodeManagement001_btn_export').click(function(){
            var expArr=table.getAllCheckedRows();
            if(expArr != null && expArr.length >0){
                Joyin.confirm('确认导出选中信息？',function(){
                    exportExcel(1);
                })
            }else{
                Joyin.confirm('确认导出查询结果？',function(){
                    exportExcel(0);
                })
            }
        })

        /**
         * 导出
         * flag:0 全部导出，选中导出
         */
        var exportExcel = function(flag) {
            if(flag == 0){
                var param = getPageParams();
                Joyin.exportExcel("export",param);
            }else{
                var param = {};
                var expArr=table.getAllCheckedRows();
                var keyList = [];
                $.each(expArr, function(i, row) {
                    keyList.push(row.insideProdId);
                });
                if(expArr.length > 1){
                    param.keys = keyList.join(',');
                }else{
                    param.keys = keyList;
                }
                Joyin.exportExcel("export",param);
            }
        };

    });
}());
