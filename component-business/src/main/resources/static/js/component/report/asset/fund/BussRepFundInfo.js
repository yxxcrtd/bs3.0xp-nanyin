/**
 * 分红计划组件
 */
(function(){
	/**
     * 画星星
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
		return $('#' + BussRepFundInfoentityName + '_' + id);
	};

	if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F18){
		getElementById('repPrivateprdFlag_div').show();
		getElementById('repBondtostockFlag_div').show();
		getElementById('project_other_div').show();
	}

    /**
     * 利率类型改变时执行的方法
     * @param rateType
     * @returns
     */
    function repGovInvestForChang(data) {
    	if(!data.checked){
    		getElementById('repGovInvestFor').prop('required', false).trigger('change');
    	}
    	else{
    		getElementById('repGovInvestFor').prop('required', true).trigger('change');
    	}
    	star();
    	layui.form.render();
    }
	
    function repAssetTypeChange(){
    	var fundType = $("#"+fundTypeElement).val();
    	if(fundType == h_01){
    		getElementById('repAssetType').prop('disabled', true).trigger('change');
    		getElementById('repAssetType').val(z_02).trigger('change');
    		
    	}
    	else{
    		var secondType = $("#"+secondTypeElement).val();
    		if(secondType == null || secondType == ''){
    			getElementById('repAssetType').val('').trigger('change');
    		}
    		else{
    			
    			if(secondType == FUND_ASSET_SECOND_TYPE_01){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_01).trigger('change');
				}
				if(secondType == FUND_ASSET_SECOND_TYPE_02){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_02).trigger('change');
				}
				if(secondType == FUND_ASSET_SECOND_TYPE_03){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_03).trigger('change');
				}
				if(secondType == FUND_ASSET_SECOND_TYPE_04){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_04).trigger('change');
				}
				//保本基金 类型删除 20190416 start
				// if(fundType == FUND_ASSET_SECOND_TYPE_05){
				// 	getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_05).trigger('change');
				// }
				//保本基金 类型删除 20190416 start
				if(secondType == FUND_ASSET_SECOND_TYPE_06){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_06).trigger('change');
				}
				if(secondType == FUND_ASSET_SECOND_TYPE_07){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_07).trigger('change');
				}
    		}
    	}
    }
	$(function() {
		$("#"+fundTypeElement).on('change',function(){
			repAssetTypeChange();
		});
        // 基金类资产新建，是否属于政府投资基金，默认是，应改为否 by zhuyongkang
		getElementById('repGovInvestFor').prop('required', false).trigger('change');
		layui.form.on('switch(switchTest)', function(data){
			repGovInvestForChang(this);
		  });
//		$('#' + BussRepFundInfoentityName + '_' + 'repIsGovInvest').on('change', function () {
//			
//			repGovInvestForChang();
//	    });
		$("#"+secondTypeElement).on('change',function(){
			var fundType = $("#"+fundTypeElement).val();
			var secondType = $("#"+secondTypeElement).val();
			if(fundType != h_01){
				if(secondType == FUND_ASSET_SECOND_TYPE_01){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_01).trigger('change');
				}
				if(secondType == FUND_ASSET_SECOND_TYPE_02){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_02).trigger('change');
				}
				if(secondType == FUND_ASSET_SECOND_TYPE_03){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_03).trigger('change');
				}
				if(secondType == FUND_ASSET_SECOND_TYPE_04){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_04).trigger('change');
				}
				//保本基金 类型删除 20190416 start
				// if(secondType == FUND_ASSET_SECOND_TYPE_05){
				// 	getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_06).trigger('change');
				// }
				//保本基金 类型删除 20190416 end
				if(secondType == FUND_ASSET_SECOND_TYPE_06){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_06).trigger('change');
				}
				if(secondType == FUND_ASSET_SECOND_TYPE_07){
					getElementById('repAssetType').val(CHB_FUND_ASSET_SECOND_TYPE_07).trigger('change');
				}
			}
		});
		$('#' + BussRepFundInfoentityName + '_' + 'repIndustry').on('change', function () {
			$('#' + BussRepFundInfoentityName + '_' + 'repIndustryLevel2').val('');
	    });
		$('#' + BussRepFundInfoentityName + '_' + 'repTradeMarket').on('change', function () {
			var repTradeMarket = $('#' + BussRepFundInfoentityName + '_' + 'repTradeMarket').val();
			if(repTradeMarket == TRADE_MARKET_99){
				getElementById('repRemark').prop('required', true).trigger('change');
			}
			else{
				getElementById('repRemark').prop('required', false).trigger('change');
			}
			star();
			layui.form.render();
	    });
		$('#' + BussRepFundInfoentityName + '_' + 'repPartyType1').on('change', function () {
			if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F18&&$('#' + BussRepFundInfoentityName + '_' + 'repPartyType1').val()==SVR_PARTY_TYPE1_01){
				$('#' + BussRepFundInfoentityName + '_' + 'repInstitSize_div').show();
			}else{
				$('#' + BussRepFundInfoentityName + '_' + 'repInstitSize_div').val('').trigger("change");
				$('#' + BussRepFundInfoentityName + '_' + 'repInstitSize_div').hide();
			}
		});
		// 投资资产种类及比例
        getElementById('repFundInvestAsset_showRule').click(function(){
            Joyin.alert(JoyinUtil.getLabel("BussRepFinProductInfo.repInvTypeRat.showRule"));
        });
		/*getElementById('repAssetType').change(function(){
			if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F18){
				if(getElementById('repAssetType').val()) {
					getElementById('content_one_div').nextAll().show();
					getElementById('repAssetType_div').nextAll().show();
				}else{
					getElementById('content_one_div').nextAll().hide();
					getElementById('repAssetType_div').nextAll().hide();
				}
			}
		});*/
	});

}());