$(function () {
        var init=function() {
            if(pageModel == PAGE_MODEL_EDIT){
                $("#assetType").attr("disabled", true);
                $("#assetDetailType").attr("disabled", true);
            }

            if($("#gradeOrgType").val() === '02'){
                $("#grade_org_id").show();
            }else{
                $("#grade_org_id").hide();
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

            Joyin.ajaxJson(basePath + 'model/impairment/assetratingallocation/assetcategories/AssetCategories002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
    		    	if(operateType === 'save'){
    		    		WorkFlow.doSave(basePath + 'model/impairment/assetratingallocation/assetcategories/AssetCategories002/doUpdate', object, "ValAssetCategories002_btn_save");
    				}else{// submit
    					WorkFlow.doSubmit(basePath + 'model/impairment/assetratingallocation/assetcategories/AssetCategories002/doUpdate', object, "ValAssetCategories002_btn_submit");
    				}
                });
            });
    }




    // 评级机构联动指定评级机构是否显示
    var gradePartyTypeChange = function() {

        if($("#gradeOrgType").val() === '02'){
            $("#grade_org_id").show();
        }else{
            $("#grade_org_id").hide();
        }

    };

    $(function() {
        initShow();// 根据操作类型显示
        init();
        /**
         * 绑定按钮事件
         */
        $('#ValAssetCategories002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $('#ValAssetCategories002_btn_save').click(function() {
            saveOrSubmit('save');
        });

    });


    // 评级机构类型onchange事件
    $("#grade_party_type").on('change',function(){
        gradePartyTypeChange();
    });

    // 资产类别onchange事件
    $("#assetType").on('change',function(){
        // assetTypeChange();
        if($("#assetType").val() == '07' || $("#assetType").val() == '09' ){
            $("#assetDetailType").prop("disabled",true);
        }
        else{
            $("#assetDetailType").prop("disabled",false);
        }

    });
});