(function () {

    /**
     *  下限浮动利率联动事件
     */
    $('#benchmarkMin').change(function(){
        $("#benchmarkMinSource").val($('#benchmarkMin').select2('data')[0].object);
    })

    /**
     *  上限浮动利率联动事件
     */
    $('#benchmarkMax').change(function(){
        $("#benchmarkMaxSource").val($('#benchmarkMax').select2('data')[0].object);
    })

    /**
     *  利率类型联动事件
     */
    $('#rateType').change(function(){
        if($("#rateType").val() == "02"){
            //利率下限%
            $("#rateMix").val('').trigger('change').prop('disabled', true);
            //利率上限%
            $("#rateMax").val('').trigger('change').prop('disabled',true);
            //浮动利率下限%
            $("#benchmarkMin").val('').prop('disabled', false);
            $("#coefficientMin").val('1.00').trigger('change').prop('disabled', false);
            $("#spreadRateMin").val('').trigger('change').prop('disabled', false);
            //浮动利率上限%
           $("#benchmarkMax").val('').prop('disabled', false);
            $("#coefficientMax").val('1.00').trigger('change').prop('disabled', false);
            $("#spreadRateMax").val('').trigger('change').prop('disabled', false);
        }else{
            //利率下限%
            $("#rateMix").val('').trigger('change').prop('disabled', false);
            $("#benchmarkMin").val('').prop('disabled',true);
            $("#coefficientMin").val('').trigger('change').prop('disabled',true);
            $("#spreadRateMin").val('').trigger('change').prop('disabled', true);
            //利率上限%
            $("#rateMax").val('').trigger('change').prop('disabled',false);
            $("#benchmarkMax").val('').prop('disabled',true);
            $("#coefficientMax").val('').trigger('change').prop('disabled',true);
            $("#spreadRateMax").val('').trigger('change').prop('disabled',true);
        }
    });

    /**
     *  保存模拟交易信息
     */
    $("#Productsimulationtrial004_btn_save").click(function () {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#submitForm', true);
        Joyin.ajaxJson(basePath + 'invest/product/rddesign/productsimulationtrial/ProductSimulationTrial004/check', object, function(result) {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
            object.benchmarkMinStr = $('#benchmarkMin').select2('data')[0].text;
            object.benchmarkMaxStr = $('#benchmarkMax').select2('data')[0].text;
            window.parent.refreshTable(object);
            return;
        });
    })

}());