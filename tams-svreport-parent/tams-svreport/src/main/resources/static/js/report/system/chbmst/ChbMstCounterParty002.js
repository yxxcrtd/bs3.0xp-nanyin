
function change(value) {
    if(''!=value){
        var list=$("#counterType2").data().result;
        $("#counterType2").empty();
        $("#counterType2").append($('<option value>'));
        for(var i=0; i<list.length;i++){
            if(list[i].id.indexOf(value) != -1){
                $('<option>').attr('value', list[i].id).text(list[i].text).appendTo("#counterType2");
            }
        }
        $("#counterType2").trigger('change');
    }
}



(function() {
    function init(){
        $(".layui-fixbar").hide();
        if('update'==$("#type").val()){
            document.getElementById("counterId").readOnly="readonly";
        }
    };

    $(function() {
        init();

        //保存点击事件
        $('#ChbMstCounterParty002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            object.counterId = $("#counterId").val();
            object.uuid = $("#uuid").val();
            //object.cashName =$("#busiId").text();
            object.counterName = $("#counterName").val();
            object.counterType1 = $("#counterType1").val();
            object.counterType2 = $("#counterType2").val();
            object.type =$("#type").val();
            if(object.counterId==''){
                alert("交易对手代码不能为空");
            }
            if(object.counterName==''){
                alert("交易对手名称不能为空");
            }
            if(object.counterType1==''){
                alert("交易对手一级类型不能为空");
            }
            if(object.counterType2==''){
                alert("交易对手二级类型不能为空");
            }
            if(object.counterId!='' && object.counterName!='' && object.counterType1!=''&& object.counterType2!=''){
                Joyin.ajaxJson(basePath + 'svreport/system/chbmst/ChbMstCounterParty002/save', object, function(result) {
                    /*if(result){
                        Joyin.ajaxJson(basePath + 'svreport/system/prorate/DisPrdFeeRate002/checkAndSave',object,function(result){
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }*/
                });
            }
        });

    });
}());