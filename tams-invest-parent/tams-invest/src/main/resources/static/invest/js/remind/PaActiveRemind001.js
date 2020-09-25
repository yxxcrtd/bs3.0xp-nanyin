/**
 * 主动提醒页面
 */
(function() {
    /**
     * 获取查询参数
     */
    var searchformdata = function() {
        return {
            quaryDate : $('#quaryDate').val()
        }
    };

    $(function() {
        // 使主动提醒画面自适应高度
        $(window.parent.document).find("iframe").load(function(){
            var main = $(window.parent.document).find("iframe");
            var thisheight = $(window.parent.document).height() - 90;
            main.height(thisheight);
        });

        // 平台日更新
        SocketClient.subscribe('/sys/updateSysDate', function(msg) {
            $('#quaryDate').val(msg.responseMessage);
        });

    });

    /**
     * 查询按钮绑定
     */
    $('#PaActiveRemind001_btn_search').click(searchform);

    function searchform(){
        var object = searchformdata();
        if(!object.quaryDate || object.quaryDate === '' || object.quaryDate === null){
        	Joyin.error(JoyinUtil.getMessage("查询日期不能为空"));
        	return;
        }
        Joyin.ajaxJson(basePath + 'invest/remind/PaActiveRemind001/searchCount',
            object, function(result) {
                $.each($('.click'), function(m,n){
                    $(this).text(result[n.getAttribute('id')]);
                });
            });
    };

    /**
     * 明细点击绑定
     */
    $('.click').click(function() {
        var mainCategory = $(this).attr("one");
        var smallCategory = $(this).attr("two");
        var quaryDate = $('#quaryDate').val();
        var label = 'PaActiveRemind002';
        var path;        
        data={
            mainCategory:mainCategory,
            smallCategory:smallCategory,
            quaryDate:quaryDate
        };
        /*if('R5'== mainCategory){
            path = 'invest/risk/limitmanagement/rsksupervisionriskanalyze/RskSupervisionRiskAnalyze001/init';
        } else{
            path = 'invest/remind/PaActiveRemind002/init';
        }*/
        path = 'invest/remind/PaActiveRemind002/init';
        Joyin.dialog(JoyinUtil.getLabel(label), basePath
            + path, '98%', '95%',
            data, function() {
                searchform();
            });
    });

}());
