$(function(){
    AssetCommon = {
            /**
             * 还本计划维护
             */
            finalShowRepayment:function(obj,callback){
                Joyin.dialog(JoyinUtil.getLabel('FinRepayPlan.title'), basePath +'invest/asset/common/FinRepayPlan/init', '90%', '90%',
                        obj,function(){
                            callback();//回调函数，表格刷新
                       });
            },
            /**
             * 付息计划维护
             */
            finalShowPayInterest:function(obj,callback){
                Joyin.dialog(JoyinUtil.getLabel('FinPayInterestPlan.title'), basePath +'invest/asset/common/FinPayInterestPlan/init', '90%', '90%',
                        obj,function(){
                            callback();//回调函数，表格刷新
                       });
            },
            /**
             * 最终展示利率调整
             */
           finalShowRateAdj:function(obj,callback){
                Joyin.dialog(JoyinUtil.getLabel('FinAssetRateAdj001.title'), basePath +'invest/asset/common/FinAssetRateAdj001/init', '80%', '80%',
                        obj,function(){
                            callback();//回调函数，表格刷新
                       });
            },
            /**
             * 最终展示利率调整
             */
           finalShowRateAdjTD:function(obj,callback){
                Joyin.dialog(JoyinUtil.getLabel('FinAssetRateAdj001.titleTD'), basePath +'invest/asset/common/FinAssetRateAdj001/init', '80%', '80%',
                        obj,function(){
                            callback();//回调函数，表格刷新
                       });
            },
            //计算投资收益率、资产推荐费
            loadRateChange:function(){
                //参与方信息--通道费率
                if ($("#parentPlan").prop('checked')) {
                    return;
                }
                var channelFee = 0;var investRate = 0; var referRate = 0;
                if(BussParticipantInfo){
                     var buyOutRate = $('#buyOutRate').val();
                     var bussParticipantInfoList = BussParticipantInfo.getAllRows();
                     $.each(bussParticipantInfoList,function(index,obj){
                         channelFee = JoyinMath.doAdd(channelFee,JoyinMath.doAdd(obj.otherYield,JoyinMath.doAdd(obj.clientYield,obj.managerYield,4),4),4);
                     });
                    //查看页面净投资收益率不进行计算 yjm start 20190830
                    if ( pageModel == PAGE_MODEL_REFERENCE ){
                         investRate  = $('#finInterestFactorDto_investRate').val();                         
                    }else {
                        // 通道增加“增值税税率，附加税率字段” add by renping 20191019 start
                    	var vatTaxRate = 0;
                    	var adtTaxRate = 0;
                    	if(bussParticipantInfoList && bussParticipantInfoList.length > 0){
                    	    if (bussParticipantInfoList[0].vatTaxRate) {
                    		    vatTaxRate = JoyinMath.doDiv(bussParticipantInfoList[0].vatTaxRate, 100, 6);
                    		}
                    	    if (bussParticipantInfoList[0].adtTaxRate) {
                    		    adtTaxRate = JoyinMath.doDiv(bussParticipantInfoList[0].adtTaxRate, 100, 6);
                    		}
                    	}
                    	// 净投资收益率=借款利率-[借款利率*增值税率/(1+增值税率)*（1+附加税率）]-通道费率
                    	var loadRate = $('#finInterestFactorDto_loadRate').val();
                    	if(!loadRate){
                    	    loadRate = 0;
                    	}
                    	// 借款利率*增值税率
                    	var mid1 = JoyinMath.doMul(loadRate, vatTaxRate, 12);
                    	// 借款利率*增值税率/(1+增值税率)
                    	var mid2 = JoyinMath.doDiv(mid1, JoyinMath.doAdd(1, vatTaxRate), 16);
                    	// 借款利率*增值税率/(1+增值税率)*（1+附加税率）
                    	var taxRate = JoyinMath.doMul(mid2, JoyinMath.doAdd(1,adtTaxRate), 4);
                        /*净投资收益率%=借款利率%-通道费率% panbin 2020-7-13 start*/
                        // investRate  = JoyinMath.doMinus(loadRate, taxRate, 4);
                        investRate  = JoyinMath.doMinus(loadRate,channelFee,4);
                        /*净投资收益率%=借款利率%-通道费率% panbin 2020-7-13 end*/
                        // 通道增加“增值税税率，附加税率字段” add by renping 20191019 end
                    }
                    //查看页面净投资收益率不进行计算 yjm end 20190830
                    referRate = JoyinMath.doMinus(investRate,buyOutRate,4);
                }
                $('#finInterestFactorDto_referRate').val(referRate).trigger('change');
                $('#finInterestFactorDto_investRate').val(investRate).trigger('change');
            },
            //起息日必须小于到期日
            checkDate:function(obj){
                var vdate = $('#vdate').val();
                var mdate = $('#mdate').val();
                if(vdate&&mdate){
                    if((new Date(vdate).DateDiff('d',mdate))<=0){
                        if(obj.type=='F01'||obj.type=='F07'||obj.type=='F18'){
                            Joyin.error("到期日必须大于起息日");
                            $('#'+obj.target.id).val('');
                        }else if(obj.type=='F17'||obj.type=='F28'){
                                Joyin.error("终止日期必须大于成立日期");
                                $('#'+obj.target.id).val('');
                            }
                        }
                }
            },
            //期次管理
            showStageManagement:function(obj,callback){
                Joyin.dialog(JoyinUtil.getLabel('FinStageManagement.title'), basePath +'invest/asset/common/FinStageManagement/init', '90%', '90%',
                        obj,function(){
                            callback();//回调函数，表格刷新
                       });
            },
            /**
        	 * 设置市场代码后缀
        	 */
        	setTradeMarketId : function(tradeMarket){
        		 if (tradeMarket == '01') {//银行间
        			 $('#tradeMarketId').val('.IB');
        		}else if (tradeMarket == '03') {//上海证券交易所
        			 $('#tradeMarketId').val('.SH');
        		}else if (tradeMarket == '04') {//深圳证券交易所
        			 $('#tradeMarketId').val('.SZ');
        		}else if (tradeMarket == '09') {//OTC场外
        			 $('#tradeMarketId').val('.OF');
        		}else{//机构间私募系统
        			 $('#tradeMarketId').val('.IOC');
        		}
        		 return $('#tradeMarketId').val();
        	},

        	getRepInfoByIssuer:function(obj){
                Joyin.ajax(JoyinUtil.getRealPath('/invest/asset/common/getRepInfoByIssuer'), {customerId : obj}, function(result){
                    //update by gejiaao 20200320
                    if (result.partyType1 == '01' || result.partyType1 == '02' || result.partyType1 == '03'){
                        $('#bussFinBondRepInfoDto_repPartyType1').val(result.partyType1).trigger('change');
                        $('#bussFinBondRepInfoDto_repInstitSize').val(result.partyType1).trigger('change');
                    }else if(result.partyType1 == '04'){
                        $('#bussFinBondRepInfoDto_repPartyType1').val('02').trigger('change');
                    }else{
                        $('#bussFinBondRepInfoDto_repPartyType1').val(result.partyType1).trigger('change');
                    }
                    $('#bussFinBondRepInfoDto_repPartyType2').val(result.partyType2).trigger('change');
                    $('#bussFinBondRepInfoDto_repPartyType3').val(result.partyType3).trigger('change');
                    $('#bussFinBondRepInfoDto_repIndustry').val(result.industry).trigger('change');
                    $('#bussFinBondRepInfoDto_repIndustryLevel2').val(result.industry2).trigger('change');
                });
            },
            
            /**
        	 * 设置市场代码后缀
        	 */
        	setTradeMarketId : function(tradeMarket){
        		 if (tradeMarket == '01') {//银行间
        			 $('#tradeMarketId').val('.IB');
        		}else if (tradeMarket == '03') {//上海证券交易所
        			 $('#tradeMarketId').val('.SH');
        		}else if (tradeMarket == '04') {//深圳证券交易所
        			 $('#tradeMarketId').val('.SZ');
        		}else if (tradeMarket == '09') {//OTC场外
        			 $('#tradeMarketId').val('.OF');
        		}else{//机构间私募系统
        			 $('#tradeMarketId').val('.IOC');
            }
        		 return $('#tradeMarketId').val();
    }
    }
}());