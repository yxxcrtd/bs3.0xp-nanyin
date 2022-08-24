$(function () {

	var tree=$("#typeDatatree");
	var docW= $(document).width(); 
	var docH= $(document).height(); 
	var tabHeadUlH=100;
	var defaultDateStyle = 'yyyy-MM-dd';
	var ids = new Array();

	//设置模板的调用参数
	var modelId;
	var modelUri;
	var repId;
	var repName;
	var proName;
	
	var modelListId;
	var modelListName;
	
	var currReportFile;
	
	var listSearch = [{screenType:'03',screenKey:'portnum',screenName:'产品名称'},
	                  {screenType:'01',screenKey:'startDate',screenName:'开始日期'},
	                  {screenType:'01',screenKey:'endDate',screenName:'结束日期'},
	                  {screenType:'06',screenKey:'prodSeriesNum',screenName:'产品系列'}]

	var previewFlag=false;

	var editFlag=true;

	var backParam;
 	var $container = $("#showtable");
 	
	var viewReport = function(){
				Joyin.ajax(basePath + 'disclosure/information/reveal/detailshow/RepView001/viewReport', null, function(result) {
					$container.html(result);
				});
	}
	    	
    /**
     * 画面加载完成
     */
    $(function() {
    	
    	
    	$('#showtable').width((docW-350)+"px");
    	$('#showtable').height((docH-tabHeadUlH)+"px");

    	// 绑定查询按钮事件
       	viewReport();

    });
});



