$(function(){
	/**
	 * 生成按钮
	 */
	$("#ManuallyGeneratingPbcData001_btn_search").find("span").text("生成");
	$("#ManuallyGeneratingPbcData001_btn_search").find("i").removeClass("iconfont icon-search");
	$("#ManuallyGeneratingPbcData001_btn_search").find("i").addClass("iconfont layui-icon-star-fill");
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	// 文件标志码
        	prodIds : $('#pbcProdId').val()==null?null:$('#pbcProdId').val().join(","),
        	// 查询起始日期
            startDate:$('#queryDate').startDate(),
            // 查询结束日期
            endDate:$('#queryDate').endDate()
        }
    };
    
    // 初始化查询日期为平台日期前一日
	Joyin.ajaxSync(
			'getYesterDayDate',null,function(result){
            if(result){
                $("#queryDate").val(result.data);
            }
        }
    );
    
    /**
     * 生成按钮事件
     */
    $(function() {
    	$('#ManuallyGeneratingPbcData001_btn_search').click(function() {
			var startDate = new Date($('#queryDate').startDate());
			var endDate = new Date($('#queryDate').endDate());
			var betweenDate = (endDate - startDate)/(1000 * 60 * 60 *24);
			if (isNaN(betweenDate)) {
				// 时间区间必选
				layer.alert('请选择查询日期！', {
					  icon: 2,
					  skin: 'layer-ext-moon' 
					});
				return;
			} else {
				// 时间区间不能超过一个月
				if (betweenDate > 30) {
					layer.alert('只能选择一个月以内的数据，请重新选择！', {
						  icon: 2,
						  skin: 'layer-ext-moon' 
						});
					return;
				}
			}
			Joyin.confirm("是否确认生成报送文件？", function() {
				var param = getSearchParams();
				Joyin.ajaxSync('createZipFile', param, function(result) {
					if (result.length < 1) {
						layer.alert('所选产品已全部手工处理或没有在日期范围内！');
					} else {
						param.result = result;
						Joyin.exportExcel('download', param);
					}
				});
			});
			
		});
    });
});