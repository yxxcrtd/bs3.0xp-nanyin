$(function () {
        var init=function() {
            if(pageModel == PAGE_MODEL_EDIT){
                $("#secTypeModel").attr("disabled", true);
                $("#secDetailTypeModel").attr("disabled", true);
                $("#secDetailTypeModel_plan").attr("disabled", true);
                /** modify by yanaya on 20210821 去除资产相关**/
                //$("#finprodName").attr("disabled", true);
                //$("#finprodId").attr("disabled", true);
            }

            var secTypeModel = $('#secTypeModel').val();
            if(secTypeModel && secTypeModel==='13'){
                $('#asset_detail_type_plan').show();
                $('#asset_detail_type').hide();
            }else{
                $('#asset_detail_type').show();
                $('#asset_detail_type_plan').hide();
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
       var secTypeModel = $('#secTypeModel').val();
       if(secTypeModel && secTypeModel==='13'){
           $('#asset_detail_type_plan').show();
           $('#asset_detail_type').hide();
           $('#secDetailTypeModel').val('');
       }else{
           $('#asset_detail_type').show();
           $('#asset_detail_type_plan').hide();
           $('#secDetailTypeModel_plan').val('');
       }
    };

 var countCnyAmt = function() {
		$("#finprodId").val($('#finprodName').attr("data-id"));
		}

    var isCityBondChange = function(){
            var isCityBondChange = $('#isSmooth')[0].checked;
            if (isCityBondChange) {
                //$("#smoothSectionDiv").show();
                $("#smoothSection").val(SMOOTHSECTION_02).trigger("change");
                $("#smoothSection").attr("disabled", false);
            }else {
                //$("#smoothSectionDiv").hide();
                $("#smoothSection").val('').trigger("change");
                $("#smoothSection").attr("disabled", true);
            }
        };

       layui.form.on('switch(isSmooth)',function(){
            isCityBondChange();
        });

    /** modify by yanaya on 20210821 增加是否兜底评级联动**/
    var isBottomGradeChange = function(){
        var isBottomGradeChangeVal = $('#isBottomGrade')[0].checked;
        if (isBottomGradeChangeVal) {
            $("#bottomGrade").attr("disabled", false);
        }else {
            $("#bottomGrade").val('').trigger("change");
            $("#bottomGrade").attr("disabled", true);
        }
    };

    layui.form.on('switch(isBottomGrade)',function(){
        isBottomGradeChange();
    });

    $(function() {
        initShow();// 根据操作类型显示
        init();
        isCityBondChange();
        /** modify by yanaya on 20210821 增加是否兜底评级联动**/
        isBottomGradeChange();
        /**
         * 绑定按钮事件
         */
        $('#ValCashFlowDiscount002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        /** modify by yanaya on 20210821 去除资产相关**/
        // $("#finprodName").on('change',function(){
        //              countCnyAmt();
        //         });

//        $('#ValAssetCategories002_btn_save').click(function() {
//            saveOrSubmit('save');
//        });

         // 资产类别onchange事件
        $("#secTypeModel").on('change',function(){
                     assetTypeChange();
                });
    });
    
});