var EquityInfoRegist002 = function(){
    return {
        submit:function(){
           var param = {};
           param.remark = $("#remark").val();
           param.deleteKeys = $("#deleteKeys").val();
           if ($("input[type='checkbox']").is(':checked')) {
               param.flag="01";
           }else{
               param.flag="02";
           }
           Joyin.exportExcel("exportDel",param, function() {
	           var index = parent.layer.getFrameIndex(window.name);
	           parent.layer.close(index);
           });
        },
        
        cancel : function() {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    };
}();
$(document).ready(function(){
    EquityInfoRegist002.elements = {
            submitBtn:$("#EquityInfoRegist002_submit"),
            cancelBtn:$("#EquityInfoRegist002_cancel"),
    }
    EquityInfoRegist002.elements.submitBtn.on("click",EquityInfoRegist002.submit);
    EquityInfoRegist002.elements.cancelBtn.on("click",EquityInfoRegist002.cancel);
});