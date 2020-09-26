var moneyUnitSet = function(){
	return {
		/**
		 *提交按钮
		 */
		onSubmit : function(){
			var form = document.forms[0];
			var $select = $("select");
			var colArr = [];
			$($select).each(function(i, sel){
				var col = {};
				col.name = $(sel).attr("id");
				col.unit = $(sel).val();
				colArr.push(col);
			});
			updateGridUnit(parent.$("#" + $("#gridId").val()), colArr);
			var index = parent.layer.getFrameIndex(window.name);
        	parent.layer.close(index);
		},
		
		/**
		 * 取消按钮
		 */
		onCancel : function() {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}
	};
}();

$(document).ready(function(){
	$('#MoneyUnitSet_submit').bind('click', moneyUnitSet.onSubmit);
	$('#MoneyUnitSet_cancel').bind('click', moneyUnitSet.onCancel);
});