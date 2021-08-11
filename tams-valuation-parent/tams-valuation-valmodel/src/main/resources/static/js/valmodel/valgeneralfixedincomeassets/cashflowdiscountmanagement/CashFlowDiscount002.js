$(function () {
        var init=function() {
            if(pageModel == PAGE_MODEL_EDIT){
                $("#secTypeModel").attr("disabled", true);
                $("#secDetailTypeModel").attr("disabled", true);
                $("#finprodName").attr("disabled", true);
                $("#finprodId").attr("disabled", true);
            }
        };

	/**
	 * 根据操作类型显示
	 */
    var initShow = function () {
      var pageModel = $('#pageModel').val();
      var status = $('#status').val();
    };


    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
         // 获取seqNo
        if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
            object.seqNo = $('#seqNo').val();
        }

            Joyin.ajaxJson(basePath + 'valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
    		    	if(operateType === 'save'){
    		    		WorkFlow.doSave(basePath + 'valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002/doUpdate', object, "ValCashFlowDiscount002_btn_save");
    				}else{// submit
    					WorkFlow.doSubmit(basePath + 'valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002/doUpdate', object, "ValCashFlowDiscount002_btn_submit");
    				}
                });
            });
    }


   // 资产类别事件
   var assetTypeChange = function() {
        if ($("#secTypeModel").val().startsWith("F01")) {
            var itemFwd = [];
            var TEMP_MODEL_SEC_DETAIL_TYPE = JSON.parse(MODEL_SEC_DETAIL_TYPE);
            for ( var i in TEMP_MODEL_SEC_DETAIL_TYPE) {
                if (!TEMP_MODEL_SEC_DETAIL_TYPE.hasOwnProperty(i))
                    continue;
                if (TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "08"
                        && TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "09"
                        && TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "011"
                        && TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "013"
                        && TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "023"
                        && TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "024") {
                    itemFwd.push(TEMP_MODEL_SEC_DETAIL_TYPE[i].id);
                }
            }
            $('#secDetailTypeModel').updateChosenArr({
                item : itemFwd,
                disabled : false
            });

        } else if ($("#secTypeModel").val().startsWith("F18")) {
            var itemFwd = [];
            var TEMP_MODEL_SEC_DETAIL_TYPE = JSON.parse(MODEL_SEC_DETAIL_TYPE);
               for ( var i in TEMP_MODEL_SEC_DETAIL_TYPE) {
                   if (!TEMP_MODEL_SEC_DETAIL_TYPE.hasOwnProperty(i))
                       continue;
                   if (TEMP_MODEL_SEC_DETAIL_TYPE[i].id == "08"
                           || TEMP_MODEL_SEC_DETAIL_TYPE[i].id == "09") {
                        itemFwd.push(TEMP_MODEL_SEC_DETAIL_TYPE[i].id);
                   }
               }
            $('#secDetailTypeModel').updateChosenArr({
                item : itemFwd,
                disabled : false
            });
        }
    };

 var countCnyAmt = function() {
		$("#finprodId").val($('#finprodName').attr("data-id"));
		}

    var isCityBondChange = function(){
            var isCityBondChange = $('#isSmooth')[0].checked;
            if (isCityBondChange) {
                $("#smoothSectionDiv").show();
            }else {
                $("#smoothSectionDiv").hide();
            }
        };

       layui.form.on('switch(isSmooth)',function(){
            isCityBondChange();
        });


    $(function() {
        initShow();// 根据操作类型显示
        init();
        isCityBondChange();
        /**
         * 绑定按钮事件
         */
        $('#ValCashFlowDiscount002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $("#finprodName").on('change',function(){
                     countCnyAmt();
                });

//        $('#ValAssetCategories002_btn_save').click(function() {
//            saveOrSubmit('save');
//        });

         // 资产类别onchange事件
        $("#secTypeModel").on('change',function(){
                     assetTypeChange();
                });
    });
    
});