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
    
    $(function() {
    	
    	
    });
}());