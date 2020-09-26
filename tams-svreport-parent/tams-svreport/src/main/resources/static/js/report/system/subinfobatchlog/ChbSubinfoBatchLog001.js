$(function() {
    var getPageParams = function() {
        return {
            taskId : $('#taskId').val(),
            busiConfirmDateStart:$('#busiConfirmDate').startDate(),
            busiConfirmDateEnd:$('#busiConfirmDate').endDate(),
            fileType : $('#fileType').val(),
            dealStatus : $('#dealStatus').val(),
            queryDateStart:$('#queryDate').startDate(),
            queryDateEnd:$('#queryDate').endDate(),
            operator : $('#operator').val()
        };
    };
    var table = $('#ChbSubinfoBatchLog001').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [ 
            { title: "业务确认日期", field:'busiConfirmDate',width:170},
            { title: "操作人", field:'operator',width:100},
            { title: "文件类别", field:'fileType',width:180},
            { title: "压缩文件编号", field:'compresFileId',width:200,visible:false},
            { title: "压缩文件名", field:'compresFileName',width:200,
//                render: function (data,type,row,meta) {
//                    return '<a href="' + JoyinUtil.getRealPath('/framework/download/' + row.compresFileId) + '">' + data+'</a>';
//                }
            },
            { title: "压缩文件路径", field:'compresFilePath',width:200,visible:false},
            { title: "文件个数", field:'fileCount',width:100},
            { title: "总记录数", field:'recordCount',width:90},
            { title: "处理状态", field:'dealStatus',width:100},
            { title: "耗时（s）", field:'consumeTime',width:100},
            { title: "开始时间", field:'startTime',type:'datetime',width:140},
            { title: "结束时间", field:'endTime',type:'datetime',width:140},
            { title: "生成条件", field:'genCondition',width:250, sortable:false}
        ]
    });

    // 查询按钮点击事件
    $('#ChbSubinfoBatchLog001_btn_search').click(function(){
        if($('#ChbSubinfoBatchLog001_searchForm').joyinvalid()) {
            table.load();
        }
    });
    
    // 导出按钮点击事件
    $('#ChbSubinfoBatchLog001_btn_export').click(function(){
        var selArr = table.getAllCheckedRows();
		if (!selArr || selArr.length == 0) {
			layer.alert('请选择需要下载的压缩包！');
			return;
        } else {
        	 var param = getPageParams();
        	 var keyList = [];
        	 param.keyList = JSON.stringify(selArr);
        	 Joyin.ajaxSync('checkFileExists', param, function(result) {
				if (result.data.length > 0) {
					layer.alert('以下文件已损坏或者不存在：' + result.data);
				} else {
					Joyin.exportExcel('download', param);
				}
			});
        }
    });

});