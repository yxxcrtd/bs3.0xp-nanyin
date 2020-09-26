$(function(){
	var initCheck=function(){
		var boxObj = $("input:checkbox[name='user']"); //获取所有的复选框值
		if(userNames !='' && userNames !=null){
			var express=userNames.split(',');
			$.each(express, function(index, expressId){
				boxObj.each(function () {
					if($(this).val() == expressId) {
						$(this).prop("checked",true);
						layui.form.render('checkbox');
					}
				});
			});
		}
		
	}
	//搜索框事件
	$('#TrdCommandDispatcher002_search').change(function(){
		var boxObj = $("input:checkbox[name='user']"); //获取所有的复选框值
		if(!$("#TrdCommandDispatcher002_search").val() == ''){
			var express=$("#TrdCommandDispatcher002_search").val();
			if(express.length > 0){
				$("input:checkbox[name='user']").prop("checked",false);
				layui.form.render('checkbox');
			}
			//循环为checkbox赋值
			$.each(express, function(index, expressId){
				boxObj.each(function () {
					if($(this).val() == expressId) {
						$(this).prop("checked",true);
						layui.form.render('checkbox');
					}
				});
			});
		}
		
	});
	 /**
     * 画面加载完成
     */
    $(function() {
    	initCheck();
    	// 绑定按钮事件
    	 $('#TrdCommandDispatcher002_btn_submit').click(function() {
           //获取checkbox[name='user']的值
             var arr = new Array();
             $("input:checkbox[name='user']:checked").each(function(i){
                 arr[i] = $(this).val();
             });
             //判断是否选中数据
             if(arr.length==0){
            	 Joyin.error(JoyinUtil.getMessage("TRD_W_0002"));
                 return;
             }
             var finprodType2Arr=new Array();
             finprodType2Arr=finprodType2s.split(",");
             for (var i = 0; i < finprodType2Arr.length; i++) {
                 if(finprodType2Arr[i] == PINAN_ASSET_TYPE_F17 && arr.length > 1){
                     Joyin.error(JoyinUtil.getMessage("一级投标债券只能分发给一个交易员"));
                     return;
                 }
             }
             var users="";
             users = arr.join(",");//将数组合并成字符串
             Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032",JoyinUtil.getLabel("TrdCommandDispatcher001.distribute")),function(){
                 Joyin.ajaxJson(JoyinUtil.getRealPath('invest/trade/investtrade/commanddispatcher/TrdCommandDispatcher002/check'), {dealers:users,entrustId:entrustId,finprodType2:finprodType2s,bussBidDate:bussBidDate,finprodMarketId:finprodMarketIds,entrustStatus:entrustStatus},function(result) {
                	 Joyin.alert(JoyinUtil.getMessage("TRD_I_0003"),'1',function(){
                		 WorkFlow.cancel();
                	 });
                 });
     		 });
         });
    });
	
	
});