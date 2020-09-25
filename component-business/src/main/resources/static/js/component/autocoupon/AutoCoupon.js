/**
 *基金债券交易自动加券 
 */
var AutoCoupon = function(){
	return {
		/**
		 * 基金
		 */
		autoFundTradeCoupon : function(finprodMarketId) {
			var autoResult;
				Joyin.ajaxSync(basePath + 'component/common/CommonAutoCoupon/autoFundTradeCoupon?finprodMarketId=' 
						+ finprodMarketId,'',
						function(result) {
							autoResult = result;
						});
			return autoResult;
			},
			/**
			 * 债券
			 */
			autoBondTradeCoupon : function(finprodMarketId) {
				var autoResult;
					Joyin.ajaxSync(basePath + 'component/common/CommonAutoCoupon/autoBondTradeCoupon?finprodMarketId=' 
							+ finprodMarketId,'',
							function(result) {
								autoResult= result;
							});
				return autoResult;
				}
	
	}
}();
