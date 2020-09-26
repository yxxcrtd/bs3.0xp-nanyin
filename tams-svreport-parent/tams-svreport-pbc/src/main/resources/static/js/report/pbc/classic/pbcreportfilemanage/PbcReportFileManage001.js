$(function(){
	var getYesterDayDate = basePath + "pbc/classic/pbcreportfilemanage/PbcReportFileManage001/getYesterDayDate";
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	// 文件标志码
        	fileCodeIds : $('#fileCode').val()==null?null:$('#fileCode').val().join(","),
        	// 查询起始日期
            startDate:$('#PbcReportFileCreateDate').startDate(),
            // 查询结束日期
            endDate:$('#PbcReportFileCreateDate').endDate()
        }
    };
	
	// 显示重置按钮
	document.getElementById("hiddenInput").style.visibility="hidden";
	// 初始化生产日期为平台日期前一日
	Joyin.ajaxSync(
			getYesterDayDate,null,function(result){
            if(result){
                $("#PbcReportFileCreateDate").val(result.data + " - " +result.data);
            }
        }
    );
	
	 /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        done : function(){
        
        },
        cols:[
            { title: "文件编号", field: 'fileid', visible:false, width:0},
            { title: '压缩包名称', field: 'zipName', sortable:false,  width:900},
            { title: "生成日期", field: 'cDateStr', sortable:false}
        ]
    });
    
    $(function() {
		// 查询按钮事件
		$('#PbcReportFileManage001_btn_search').click(function() {
			 table.load();
		});
		
		// 下载文件按钮事件
		$('#PbcReportFileManage001_btn_download').click(function() {
	        var selArr = table.getAllCheckedRows();
			if (!selArr || selArr.length == 0) {
				layer.alert('请选择需要下载的压缩包！');
				return;
	        } else {
	        	 var param = getSearchParams();
	        	 var keyList = [];
	        	 param.keyList = JSON.stringify(selArr);
	        	 Joyin.ajaxSync('checkFileExists', param, function(result) {
						if (result.data.length > 0) {
							layer.alert('以下文件已损坏或者不存在：' + result.data);
						} else {
							//检查已选zip是否已报送（有未报送的zip则提示是否要报送，全为已报送则直接下载）
							Joyin.ajaxSync('checkFileSend', param, function(result) {
								if(result.data){
									Joyin.confirmMulti("是否所选文件中相关数据修改为手工报送？", ['是','否'], function () {
										param.isManually = true; // 手工报送标识
										param.sendKeyList = JSON.stringify(result.data);// 需要手工处理的数据
										Joyin.exportExcel('download', param);
									}, function () {
										Joyin.exportExcel('download', param);
									})
								}else{
									Joyin.exportExcel('download', param);
								}
							});
						}
					});
	        }
		});
	});
    
    
});