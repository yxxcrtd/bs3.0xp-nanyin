/**
 * 主动提醒页面
 */
(function() {
    /**
     * 获取查询参数
     */
    var searchform = function() {
        return {
            quaryDate : $('#quaryDate').val()
        }
    };
    /**
     * 绑定按钮事件
     */
    $(function() {
    	// 使主动提醒画面自适应高度
    	$(window.parent.document).find("iframe").load(function(){
	    	var main = $(window.parent.document).find("iframe");
	    	var thisheight = $(document).height() - 90;
	    	main.height(thisheight);
    	});
    	
        $('#PaActiveRemind001_btn_search').click(
                function(obj) {
                    var object = searchform();
                    Joyin.ajaxJson(basePath + 'tams-invest/remind/PaActiveRemind001/searchCount',
                            object, function(result) {

                            });
                });
        $('.details').click(function() {
                    var mainCategory = $(this).attr("one");
                    var smallCategory = $(this).attr("two");
                    var quaryDate = $('#quaryDate').val();
                    data={mainCategory:mainCategory,smallCategory:smallCategory,quaryDate:quaryDate};
                    Joyin.dialog(JoyinUtil.getLabel('PaActiveRemind002'), basePath
                            + 'tams-invest/remind/PaActiveRemind002/init', '98%', '95%',
                            data, function() {
                                
                            });
                });
    });
}());
