
(function() {

    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        $('.must').each(function() {
            $(this).remove();
        })
        $('*[required]').each(function() {
            var $el = $(this);
            var $target = _findPrevLabel($el, 3);
            if ($target.find('> .must').length === 0) {
                $target.append($('<i class="must">*</i>'));
            }
        });
    }

    function init(){
        $("#curAssetType").change(function(){
            var  curAssetType=$("#curAssetType").val();
            if(curAssetType!='11' && curAssetType!='12'){
                $("#assetCode").attr('required', false).trigger('change');
                if (curAssetType!='09' && curAssetType!='10'){
                    $("#assetMethod").attr("required",true);
                    $("#assetMethod").attr("disabled",false);
                }else{
                    $("#assetMethod").attr("required",false);
                    document.getElementById("assetMethod").options.length=0;
                    $("#assetMethod").val("").attr("disabled",true);
                }
                if (curAssetType!='01' && curAssetType!='02'){
                    $("#unitPriceFull").attr("required",false);
                    $("#unitPriceNet").attr("required",false);
                }else{
                    $("#unitPriceFull").attr("required",true);
                    $("#unitPriceNet").attr("required",true);
                }
            }else{
                $("#assetCode").attr("required",true);
                $("#assetMethod").attr("required",false);
                $("#assetMethod").attr("disabled",true);
                $("#unitPriceFull").attr("required",true);
                $("#unitPriceNet").attr("required",true);

            }
            star();
        });

        $("#rivalPartyType").change(function(){
            var  rivalPartyType=$("#rivalPartyType").val();
            if(rivalPartyType!='99' ){
                $("#remark").attr("required",false);
            }else{
                $("#remark").attr("required",true);
            }
            star();
        });

        $("#tradeCounter").change(function(){
            var COUNTER_TYPE = COUNTER_TYPE;
            var rivalPartyType=document.getElementById("rivalPartyType"); //得到select的ID
            var data=rivalPartyType.getElementsByTagName("option");//得到数组option
            var  rivalPartyType=$('#tradeCounter').select2('data')[0].object;
            for(i=0;i<data.length;i++){//给select赋值
                if(rivalPartyType.indexOf(COUNTER_TYPE) >= 0){
                    data[i].selected=true
                }
            }
        });

        star();

    };

    $(function() {
        init();

        //保存点击事件
        $('#ChbMstCounterParty002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);

            if(object.counterId!='' && object.counterName!='' && object.counterType1!=''&& object.counterType2!=''){
                Joyin.ajaxJson(basePath + 'svreport/system/investmentdeal/InvestmentDealInfo002/save', object, function(result) {

                });
            }
        });

    });
}());