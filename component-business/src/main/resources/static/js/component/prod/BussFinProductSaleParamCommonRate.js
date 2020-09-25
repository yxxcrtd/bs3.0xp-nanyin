/**
 * 业务基准共通JS
 * @auth zhengyan
 */
(function(){
    
    var getElementById = function(id) {
        return $('#' + bussFinProductSaleParamDto + '_' + id);
    };
    
    var saleParamInitAction = function(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductSaleParam/querySaleParamInfoByFinprodId'), {finprodId : input_finprodId}, function(data){
    	 FinProductCommon.initPageDataAction(bussFinProductSaleParamDto, data);
    	})
    }
    
    window.BussFinProductSaleParam = {
    		saleParamInitAction : saleParamInitAction
    }

    var dealModeChange = function(){
      if ($('#' + dealMode).val() == '11' || $('#' + dealMode).val() == '12') {
            // 隐藏
            $('#saleChannelDiv').hide();// 销售渠道
            $('#investorTypeDiv').hide();// 目标客户类型
            getElementById('saleChannel').val('').trigger('change');
            getElementById('investorType').val('').trigger('change');
            $('#saleDepartmentDiv').hide();// 销售部门
            getElementById('saleDepartment').val('').trigger('change');
        } else {
            $('#saleChannelDiv').show();// 销售渠道
            $('#investorTypeDiv').show();// 目标客户类型
            $('#saleDepartmentDiv').show();// 销售部门
        }
    }

    
    //计划募集金额
    var repRaiseAmtPlanChange = function(){
    	var saleMax = JoyinMath.doDiv(getElementById('repRaiseAmtPlan').val(),10000,6)
    	getElementById('saleMax').val(saleMax);
    }; 
    
	var investorTypeAction = function(){
		var repInArr = getElementById('investorType').val();
		if(repInArr == null){
			$('#'+bussFinProductSaleParamDto + '_sameOrg').val('').prop('disabled',true);
			return;
		}
		for(var i = 0;i < repInArr.length ; i++){
			if(repInArr[i] == '04'){
				$('#'+bussFinProductSaleParamDto + '_sameOrg').prop('disabled',false);
				//客户类型仅有金融同业时，中债报送的 是否同业专属字段 才为是 必输 可编辑 yjm start 20190807 
				if (pageModel != PAGE_MODEL_REFERENCE) {
                    if (repInArr.length == 1){
                        getElementById('excFinSector').val('01').trigger('change');                        
                    } else {
                        getElementById('excFinSector').val('02').trigger('change');
                    }
				}               
				break;
			}else {
                getElementById('excFinSector').val('02').trigger('change');
			}
            //客户类型仅有金融同业时，中债报送的 是否同业专属字段 才为是 必输 可编辑 yjm end 20190807 
			if(i == repInArr.length - 1){
				$('#'+bussFinProductSaleParamDto + '_sameOrg').val('').prop('disabled',true);
			}
		}
	};
	
    $(function() {
    	dealModeChange();
    	$('#'+dealMode).change(dealModeChange);
    	
    	getElementById('investorType').on('change', function () {
			investorTypeAction();
	    });
    	//对募集开始日（从）（到）的校验
    	/*$('#bussFinProductSaleParamDto_firstSaleVdate').change(function(){
			FinProductCommon.firstSaleVdateChange();
		});*/
    });
}());
