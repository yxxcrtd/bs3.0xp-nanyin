var ValAccountsAdjustAccountEntry002 = function(){
	var ACCOUNTSET_TYPE02 = $("#ACCOUNTSET_TYPE02").val();
	var cdFlagNet = $("#cdFlagNet").val();
	
	return{
		init:function(){
			$("#ValAccountsAdjustAccountEntry002_btn_save").click(ValAccountsAdjustAccountEntry002.saveEnteryItem);
			$("#subjectId").change(function(){
                var thisValue = $(this).val();
                var data = ValAccountsAdjustAccountEntry002.getSimpleSelectObj("subjectId");
                $(data).each(function(i,v){
                	if(cdFlagNet==''){
                		if(v.id === thisValue) {
                        	ValAccountsAdjustAccountEntry002.setCheckBox(v.col);
                            return false;
                        }
                	}else{
                		ValAccountsAdjustAccountEntry002.setCheckBox(cdFlagNet);
                		cdFlagNet = null;
                        return false;
                	}
                })
            });
			$("#subjectId").trigger("change");
            
            var booksetData = ValAccountsAdjustAccountEntry002.getSimpleSelectObj("booksetId");
            var booksetType;
            $(booksetData).each(function(i, v){
                if (v.id == $("#booksetId").val()) {
                    booksetType = v.col;
                }
            });
            if (ACCOUNTSET_TYPE02 == booksetType) {
                $("#accountNet_div").hide();
            } else {
                $("#accountNet_div").show();
            }
		},
		setCheckBox:function(id){
			$("li.layui-chcekbox").each(function(){
				var initValue = $(this).attr("data-value");
				if(id == initValue){
					$(this).trigger("click");
				}
			});
		},
		getCheckBoxText:function(id){
			var text;
			var value = $("#"+id).val();
			$("li.layui-chcekbox").each(function(){
				var initValue = $(this).attr("data-value");
				if(value == initValue){
					text = $(this).html();
				}
			});
			return text;
		},
		/**
		 * 获取单选下拉框的OBJECT属性
		 */
		getSimpleSelectObj:function (id) {
		    var $option = $('#select2-' + id + '-results').find('li.select2-results__option--highlighted');
		    if($option.length === 0 || _.isUndefined($option.data('data'))){
		        $option = $("#" + id).find("option:selected");
		    }
		    var obj = $option.data('data');
		    if (!obj && typeof obj == 'undefined') {
		        obj = $option.data();
		    }
		    return obj;
		},
		saveEnteryItem:function(){
			var data = {};
            data.subjectName = $("#subjectId").find("option:selected").text();
            data.dictName = $("#formEle").find("option:selected").text();
            data.subjectId = $("#subjectId").val();
            data.formEle = $("#formEle").val();
            data.cdFlag = $("#cdFlag").val();
            if (!$("#accountNet_div").is(":hidden")) {
            	data.accountNet = $("#accountNet").val();
                data.accountNetName = $("#accountNet").find("option:selected").text();
            }
            data.cdFlagName = ValAccountsAdjustAccountEntry002.getCheckBoxText("cdFlag");
            Joyin.ajaxJson("saveCheck",data,function(result) {
            	 parent.layer.close(parent.layer.getFrameIndex(window.name));
            	 parent.ValAccountsAdjustAccountEntry003.eidtAccountEntryRow(data);
            });
		}
	}
}();

$(function () {
	ValAccountsAdjustAccountEntry002.init();
});