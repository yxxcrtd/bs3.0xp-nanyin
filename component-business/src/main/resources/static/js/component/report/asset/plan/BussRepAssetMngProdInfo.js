/**
 * 资产管理计划报送信息组件
 */
(function(){
    /**
     * 添加必填标志
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    var getElementById = function(id) {
        return $('#' + BussRepAssetMngProdInfo_entityName + '_' + id);
    };
    
    if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F18){
        getElementById('repPrivateprdFlag_div').show();
        getElementById('repBondtostockFlag_div').show();
        getElementById('project_other_div').show();
    }

    /**
     * 是否有预期收益率改变时执行的方法
     */
    function repYieldFlagChang() {
        var repYieldFlag = getElementById("repYieldFlag")[0].checked;
    	if(!repYieldFlag){
            $("#"+BussRepAssetMngProdInfo_entityName+"_repMaxRate").val("");
            $("#"+BussRepAssetMngProdInfo_entityName+"_repMinRate").val("");
    		$("#"+BussRepAssetMngProdInfo_entityName+"_repMaxRate").attr("disabled",true);
    		$("#"+BussRepAssetMngProdInfo_entityName+"_repMinRate").attr("disabled",true);
    		getElementById('repMaxRate').prop('required', false);
            getElementById('repMinRate').prop('required', false).trigger('change');
    	}
    	else{
    		$("#"+BussRepAssetMngProdInfo_entityName+"_repMaxRate").attr("disabled",false);
    		$("#"+BussRepAssetMngProdInfo_entityName+"_repMinRate").attr("disabled",false);
    		getElementById('repMaxRate').prop('required', true);
            getElementById('repMinRate').prop('required', true).trigger('change');
    	}
        star();
        layui.form.render();
    }

    /**
     * 交易流通市场改成其他交易流通场所时执行的方法
     */
    function repTradeMarketChang() {
        var repTradeMarket = getElementById('repTradeMarket').val();
        if(repTradeMarket && repTradeMarket == TRADE_MARKET_99){
            getElementById('repRemark').prop('required', true);
            star();
        }
        else {
            getElementById('repRemark').prop('required', false);
            star();
        }
        layui.form.render();
    }

    var init = function(){
        repTradeMarketChang();
    }

    $(function() {
        //初始化
        init();
        /*getElementById('repYieldFlag').on('change',function () {
            repYieldFlagChang();
        });*/
		$("#"+BussRepAssetMngProdInfo_entityName+"_repMaxRate").attr("disabled",true);
		$("#"+BussRepAssetMngProdInfo_entityName+"_repMinRate").attr("disabled",true);
        layui.form.on('switch(switchTest)', function(data){
        	repYieldFlagChang();
		  });
        getElementById('repTradeMarket').on('change', function () {
            repTradeMarketChang();
        });
        repYieldFlagChang();
        
        getElementById('repAssetType').change(function(){
        	
//       	    $("#bussRepAssetMngProdInfo_repFinancialComissue").attr("disabled",false);
//       	    $("#bussRepAssetMngProdInfo_repFinancialComissue").prop("required",false);

             //add by wj
             if(getElementById('repAssetType').val()=="1705"){
//             	$("#bussRepAssetMngProdInfo_repFinancialComissue_div").show();
             	$("#bussRepAssetMngProdInfo_repFinancialComissue").prop("required",true).trigger('change');
           	    $("#bussRepAssetMngProdInfo_repFinancialComissue").attr("disabled",false).trigger('change');

             }else{
            	 //默认显示否
             	 $('#bussRepAssetMngProdInfo_repFinancialComissue').prop('checked', false);
            	 $("#bussRepAssetMngProdInfo_repFinancialComissue").prop("required",false).trigger('change');
            	 $("#bussRepAssetMngProdInfo_repFinancialComissue").attr("disabled",true).trigger('change');
             }
             star();
             layui.form.render();
        });
        
    });

}());