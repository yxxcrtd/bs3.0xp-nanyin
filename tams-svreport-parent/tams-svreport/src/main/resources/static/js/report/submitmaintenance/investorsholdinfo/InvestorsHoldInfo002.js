$(function() {
    var editFlag = true;
    var init=function() {
        if(pageModel!=PAGE_MODEL_NEW){
            $("#indentifyMark").attr('disabled',true);
            $("#prdRegistCode").attr('disabled',true);
            $("#ccy").attr('disabled',true);
            $("#holdDate").attr('disabled',true);
            editFlag=false;
        }
        
    };
    var getSearchParams=function(){
//        return $.serializeToObject($("#submitForm"));
        var dto = {};
        dto.indentifyMark = $("#indentifyMark").val();
        dto.prdRegistCode = $("#prdRegistCode").val();
        dto.ccy = $("#ccy").val();
        dto.holdDate = $("#holdDate").val();
        dto.qty = $("#qty").val();
        dto.amount = $("#amount").val();
        dto.cnyAmt = $("#cnyAmt").val();
        dto.operateType = "save";
        dto.pageModel = pageModel;
        dto.registBankCode =$("#registBankCode").val();
        dto.orgCode =$("#InvestorsHoldInfo002_orgCode").val();
        dto.deptCode =$("#InvestorsHoldInfo002_deptCode").val();
        dto.createDept =$("#InvestorsHoldInfo002_createDept").val();
        dto.prodid =$("#InvestorsHoldInfo002_prodid").val();
        return dto;
    };
    //折合人民币金额字段取得
    var countCnyAmt = function(){
        if($("#ccy").val()=='CNY'||$("#holdDate").val()==""||$('#amount').val()==""){
            $("#cnyAmt").val($("#amount").val()).blur();
            return ;
        }
        else{
            var param = {};
            var holdDate=$("#holdDate").val();
            param.HOLDDATE= Date.ofDate(holdDate).format();
            param.amount=$('#amount').val();
            param.CCY=$('#ccy').val();
            if (param.HOLDDATE && param.CCY && param.amount) {
                Joyin.ajax('getCnyAmt',param,function(result){
                    if(result.data){
                        $("#cnyAmt").val(result.data).trigger('change').blur();
                    }
                });
            }
        }
    
    };
    $(function() {
        init();
        $("#ccy").on('change',function(){
            if(editFlag){
                countCnyAmt();
            }
        });
        $("#holdDate").on('change',function(){
            if(editFlag){
                countCnyAmt();
            }
        });
    	$("#amount").on('change',function(){
    		/*
        	//当持有金额为空格时，失去焦点后文本框显示NaN处理  zhujiabin add 20181018 start
        	if(isNaN(parseFloat($.trim($(this).val())))){
        		$(this).val('');
        		return;
        	}
        	//zhujiabin add 20181018 end
        	*/
    		if(editFlag){
                countCnyAmt();
            }
        });
        // zhujiabin add 20181018 start 
        $('#qty').on('blur',function(){
        	if(isNaN(parseFloat($.trim($(this).val())))){
        		$(this).val('');
        		return;
        	}
        });
        $('#cnyAmt').on('blur',function(){
        	if(isNaN(parseFloat($.trim($(this).val())))){
        		$(this).val('');
        		return;
        	}
        });
        // zhujiabin add 20181018 end 
        $('#InvestorsHoldInfo002_btn_save').click(function() {
            var param = getSearchParams();
            Joyin.ajaxJson("check",param,function(result){
                if(result){
                	layui.layer.open({type:3});
                        Joyin.ajaxJson("doSave",param,function(result){
                        	layui.layer.closeAll();
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                    });
                }
            })
        });
    }());
}());