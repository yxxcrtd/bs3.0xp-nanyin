$(function() {
	var getSearchParams = function() {
//       var param = $.serializeToObject('#searchForm');
		return  $.serializeToObject('#searchForm');
	};
	function fakeClick(obj) {
		var ev = document.createEvent("MouseEvents");
		ev.initMouseEvent("click", true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
		obj.dispatchEvent(ev);
	};

	function download(name, data) {
		var urlObject = window.URL || window.webkitURL || window;
		var export_blob = new Blob([data]);
		var save_link = document.createElementNS("http://www.w3.org/1999/xhtml", "a")
		save_link.href = urlObject.createObjectURL(export_blob);
		save_link.download = name;
		fakeClick(save_link);
	} ;

	function downlo2ad(filename, text) {
		var element = document.createElement('a');
		element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
		element.setAttribute('download', filename);

		element.style.display = 'none';
		document.body.appendChild(element);

		element.click();

		document.body.removeChild(element);
	}



	var exportsql = function() {

		Joyin.ajax(JoyinUtil.getRealPath('/invest/risk/stresstestmanage/riskfactor/RiskFactorConfig/CellSql/'),getSearchParams(),
			function(result) {
				if(result){
					download("因子导入脚本.sql",result);

				}

			})};

	/**
	 * 画面加载完成
	 */
	$(function() {
		$('#RiskFactorConfig_btn_submit').click(function(){
			exportsql();

		});
		$("#type1").change(function(){
			var dicode="";
			if($('#type1').val()!=null&&$('#type1').val()!=''&&$('#type1').val()!='01'){
				dicode='FTR_RSK_TYPE_'+$('#type1').val();
			}
			Joyin.ajax(JoyinUtil.getRealPath('/invest/risk/stresstestmanage/riskfactor/RiskFactor001/selectRiskFactorType1/'),{dicode:dicode},
				function(result) {
					if(result){
						var sceneOptArray = result;
						var option = "";
						for(var i = 0 ; i<sceneOptArray.length;i++){
							option +='<option value ="'+sceneOptArray[i].id+'">'+sceneOptArray[i].text+'</option>';
						}
						$('#type2').empty();
						$("#type2").append('<option value =""></option>' + option);

					}

				})





		});
		$('#type1').trigger("change");


	});
	function clearBasicCondition(){
		$("#type2").val('').trigger('change');

		$("#type1").val('').trigger('change');
	}


}());
