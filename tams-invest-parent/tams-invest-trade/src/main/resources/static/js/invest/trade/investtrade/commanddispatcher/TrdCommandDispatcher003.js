$(function(){
	if(distributeRule=='01'){
		$("#distributeRule_one").attr("checked", true);
		layui.form.render('radio');
	}else{
		$("#distributeRule_two").attr("checked", true);
		layui.form.render('radio');
	}
	if(distributeParameter){
		var split = distributeParameter.split(",");
		$.each(split,function (index,element) {
			if(element=='01'){
				$("#distributeParameter_one").attr("checked", true);
				layui.form.render('checkbox');
			}
			if(element=='02'){
				$("#distributeParameter_two").attr("checked", true);
				layui.form.render('checkbox');
			}
			if(element=='03'){
				$("#distributeParameter_three").attr("checked", true);
				layui.form.render('checkbox');
			}
		})
	}
	// 绑定按钮事件
	$('#TrdCommandDispatcher003_btn_submit').click(function(){
    	var vdate=$('#TrdCommandDispatcher003_autodispensetime').startDate();
    	var mdate=$('#TrdCommandDispatcher003_autodispensetime').endDate();
		var distributeRule  = $('input[name="distributeRule"]:checked').val();
		var distributeParameter = '';
		$('input[name="distributeParameter"]:checked').each(function(){
			if($(this).val()){
				distributeParameter+=$(this).val()+','
			}
		})
		if(distributeParameter){
			distributeParameter = distributeParameter.substr(0,distributeParameter.length-1);
		}
        if(vdate==null||vdate=='')
        {
            Joyin.error("请选择分发日期");
            return false;
        }

    	//自动分发事件
    	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001","分发"),function() {

			var object={finprodType2:finprodType2s,vdate:vdate,mdate:mdate,entrustId:entrustIds,
				distributeRule:distributeRule,distributeParameter:distributeParameter};
			Joyin.ajaxJson(JoyinUtil.getRealPath('invest/trade/investtrade/commanddispatcher/TrdCommandDispatcher003/addAutoTime'),object, function(data) {
				Joyin.alert("自动分发设置成功",null,function(){
					var index = window.parent.layer.getFrameIndex(window.name);
					window.parent.layer.close(index);
				});
             });
         });
	});
	//重置
	$('#TrdCommandDispatcher003_btn_reset').click(function(){
		$('#TrdCommandDispatcher003_autodispensetime').val('');
	})
});