 $(function(){
    /**
     * 加载报表生成条件
     */
	Joyin.ajax("getReporSearchConditions",{tabNum:$('#tabNum').val()},function(result) {
		showSearch(result);
	});

    $('#PoiReportModelManage003_btn_export').click(function() {
    	reportExport();
    });
    
    $('#PoiReportModelManage003_btn_generate').click(function() {
    	reportGenerate();
    });
});

 /**
  * 导出报表
  * @returns
  */
function reportExport(){
	var object = $.serializeToObject('#submitForm', true);
	if(checkAllPrpos(object)){
		Joyin.exportExcel(basePath + 'disclosure/information/config/poireportmodelmanage/PoiReportModelManage003/reportExport', object);
	}
}
/**
 * 生成报表、生成信息披露确认（未确认）数据
 * @returns
 */
function reportGenerate() {
	var object = $.serializeToObject('#submitForm', true);
	if(checkAllPrpos(object)){
		Joyin.ajax('querySameCount', object, function() {
			Joyin.ajax("reportGenerate", object, function(result) {
				if (result) {
				}
			});
		});
	}
}
/**
 * 遍历指定对象所有的属性
 * @param obj 需要遍历的对象
 * @returns
 */
function checkAllPrpos(obj) {
    for(var p in obj){
        if(obj[p]==""){
        	layer.msg("请输入所有条件！");
        	return false;
        }
    }
    return true;
}
/**
 * 展示报表生成条件
 */
var showSearch = function(data){
	if (!data || data.length == 0 ) {
		$("#reporSearch").html('<h2>报表不需要导出生成条件，或者未配置生成条件。</h2>');
		return;
	}
	var defaultDateStyle = 'yyyy-MM-dd';
	var ids = new Array();
	//查询条件html
	var searchHtml = '';
	//隐藏报表主键
// 	searchHtml += '<input type="hidden" name="tabNum" id="tabNum" value="'+data[0].tabNum+'"/>';
	for (var i = 0; i < data.length; i++) {
		// 条件主键
		var disId = data[i].disId;
		var id = data[i].disKey;
		// 控件类型
		var ctltype = data[i].disType;
		var lablename = data[i].disName;
		// 每三个条件作为一行
		if(i%3 == 0){
			// 行div开始标签
			searchHtml += '<div class="layui-form-item">';
		}
		// 单个条件区域div开始标签
		searchHtml += '<div class="layui-inline layui-col-md4">';
		// 条件label、i标签
		searchHtml += '<label class="layui-form-label form-lable" style="width: 100px">'+lablename+'<i class="iconfont must" style="display: inline;">*</i></label>';
		// 条件控件div开始标签
		searchHtml += '<div class="layui-input-inline input-inline">';
		//日期
		if(ctltype=='Date'){
			searchHtml += '<input type="text" id="'+id+'" name="'+id+'" placeholder="请选择日期" class="layui-input form-input input-dateSelect"/>';
		//文本框
		}else if(ctltype=='Text'){
			searchHtml += '<input type="text" id="'+id+'" name="'+id+'" placeholder="请输入内容" class="layui-input form-input"/>';
		//下拉框
		}else if(ctltype=='Select'){
			if("PROD_ID" == id){
				// 产品下拉框单独处理
				searchHtml += '<select id="'+id+'" name="'+id+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getPrdInfoSelect"></select>'
			}else{
				searchHtml += '<select id="'+id+'" name="'+id+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getSelectOptions?disId='+disId+'"></select>'
			}
		}
		// 条件控件div结束标签
		searchHtml += '</div>';
		// 单个条件区域div结束标签
		searchHtml += '</div>';
		
		if(i%3 == 2){
			// 行div结束标签
			searchHtml += '</div>';
		}
		ids.push({key:id,type:ctltype});
	}
	// reporSearch区域内最后留一个空白行（美观）
	searchHtml += '<div class="layui-form-item"></div>';
	// 添加条件html到指定区域
	$('#reporSearch').html(searchHtml);
	
	for(var i in ids){
		//日期类型(单选)
		if(ids[i].type=='Date'){
			$('#'+ids[i].key+'').textDate(false, defaultDateStyle);
			$('#'+ids[i].key+'').val(cureDate);
	 	//文本框
		}else if(ids[i].type=='Text'){
	 		
		//下拉框
		}else if(ids[i].type=='Select'){
			$('#'+ids[i].key+'').textSelect();
		}
	}
}