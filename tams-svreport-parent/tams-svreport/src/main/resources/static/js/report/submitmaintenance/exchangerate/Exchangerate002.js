var exchangerate002 = function() {
        return{
            init : function(){
                if(pageModel==PAGE_MODEL_EDIT){
                    $('#build_ccyOriginal').prop("disabled",true);
                    $('#build_ccyConvert').prop("disabled",true);
                    $('#publishDate').prop("disabled",true);
                }
            },
             doSubmit : function(){
                 var param = {};
                 //币种
                 param.ccyOriginal=$("#build_ccyOriginal").val();
                 //折算币种
                 param.ccyConvert=$("#build_ccyConvert").val();
                 //汇率
                 param.exchangePrice=$("#build_exchangePrice").val();
                 //公布日期
                 param.publishDate=$("#publishDate").val();
                 param.pageModel = pageModel;
                 param.operateType = 'save';
                 Joyin.ajax("checkDate",param, function(result) {
                     if (result && result.statuCode == "200"){
                         Joyin.confirm(result.data, function(){
                             Joyin.ajax("saveDate",param, function(result) {
                                 exchangerate002.close();
                                 });
                         });
                     }
                 });
             },
             close : function(){
                 var index = parent.layer.getFrameIndex(window.name);
                 parent.layer.close(index);
             }
        };
}();
$(function() {
    exchangerate002.init();
    $("#Exchangerate002_btn_submit").on('click',exchangerate002.doSubmit);
});