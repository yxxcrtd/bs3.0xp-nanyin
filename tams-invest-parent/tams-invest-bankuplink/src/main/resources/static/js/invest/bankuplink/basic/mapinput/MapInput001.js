(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
//            partyName: $('#partyName').val(),//交易成员
//            partyId:$('#partyId').val()//交易成员ID
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        $('#MapInput001_btn_submit').click(function() {
        	var content = $('#mapJson').val();

            //var object = {selectedItem: selArr, effStatus: status, operateType: "01" == status ? "effective" : "neffective"};
        	var mapJson = $.parseJSON(content);
            Joyin.ajaxJson(basePath + 'invest/bankuplink/basic/mapinput/MapInput001/bodyMap', mapJson, function(result) {
            	//alert(result.message);
            });

        });
    });
}());