/**
 * 产品投向信息业务组件JS
 */
(function(){
	
	var getElementById = function(id) {
		return $('#' + investInfoEntityName + '_' + id);
	};
    
	
    var valInitAction = function(input_finprodId){
    	Joyin.ajax(JoyinUtil.getRealPath('component/prod/BussFinProductInvest/queryValParamByFinprodId'), {finprodId : input_finprodId}, function(data){
    		FinProductCommon.initPageDataAction(investInfoEntityName, data);
    	})
    }
    
    window.BussFinProductGoAl = {
		valInitAction : valInitAction
    }
	
	$(function() {
		
	});
}());