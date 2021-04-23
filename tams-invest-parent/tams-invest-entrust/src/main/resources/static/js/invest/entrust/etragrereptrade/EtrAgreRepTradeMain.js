/**
 * 主页面JS
 */   
  /**
   * 指令提交或者保存
   */

  function saveOrSubmit(operateType) {
      var s;
      var object = EtrAgreRepTrade001.getSearchParams();
      object.operateType = operateType;
      if(object.entrustId){
          pageModel = PAGE_MODEL_EDIT;
      }
      object.pageModel = pageModel;

      // var riskdata = EtrAgreRepTrade001.getriskdata(object);
      // if(BaseRiskMathComm && (object.ps == '03' || object.ps == '04' || object.ps == 'T56')) {
      //     s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
      //     if(!s && s != ''){
      //         return;
      //     }
      //     if (window.BaseRiskMathComm.isCalcConditionChanged(riskdata)){
      //         return;
      //     }
      // }
      // 验证组合是否绑定券商账户
      if(!object.acctNo){
          var portfolioId = object.portfolioId;
          if(portfolioId ) {
              var result = null;
              var param = {portfolioId:portfolioId};
              Joyin.ajaxSync(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade001/getPtlSecAcctInfo', param, function(list) {
                  result = list;
              });
              if(result.length == 0 ) {
                  Joyin.error("当前组合未绑定券商资金账号，无法下投资指令");
                  return;
              }
          }
      }
          Joyin.ajaxJson(basePath + '/invest/entrust/etragrereptrade/EtrAgreRepTrade001/check',object,
              function(result) {
                  Joyin.confirm(result.data,function() {
                      if (object.operateType === 'save') {
                          WorkFlow.doSaveList(basePath+'/invest/entrust/etragrereptrade/EtrAgreRepTrade001/save',object,"EtrAgreRepTrade001_btn_save","EtrAgreRepTrade001",function() {
                              if (EtrAgreRepTrade001) {
                            	  EtrAgreRepTrade001.doReset(true);

                                  doResetRiskMathFlag();
                              }
                              JoyinUtil.disableButton("EtrAgreRepTrade001_btn_save", false);
                              pageModel = PAGE_MODEL_NEW;
                          })
                      }else {
                          WorkFlow.doSubmitList(basePath+'/invest/entrust/etragrereptrade/EtrAgreRepTrade001/save',object,"EtrAgreRepTrade001_btn_submit","EtrAgreRepTrade001",function() {
                              if (EtrAgreRepTrade001) {
                            	  EtrAgreRepTrade001.doReset(true);

                                  doResetRiskMathFlag();
                              }
                              JoyinUtil.disableButton("EtrAgreRepTrade001_btn_submit", false);
                              pageModel = PAGE_MODEL_NEW;
                          })
                      }

                  });
              });
//      })
  }
  

  /**
   * 风险试算
   * @param url
   * @param object
   * @param riskdata
   * @returns
   */
  function doRiskcal() {
      var object = EtrAgreRepTrade001.getSearchParams();
      if(object) {
          // 交易所协议回购 指令环节，只有正回购、逆回购、到期续做等交易方向参与风险试算
          if(BaseRiskMathComm && (object.ps == '03' || object.ps == '04' || object.ps == 'T56')) {
              var riskdata = EtrAgreRepTrade001.getriskdata(object);
              var riskInterfUrl = EtrAgreRepTrade001.getRiskInterfUrl();
              object.fromEtrExchange = "Y";
              object.busiPs = object.repPs;
              object.assetType2 = "H01";
              if(riskdata && riskInterfUrl) {
                  window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object,riskdata,riskMathRedisCallback);
              }
          }
      }

  }
  
  function riskMathRedisCallback(object,cluflag) {
      var objectMap = new Map();
      var length = object.length
      // 根据指标Id 去重
      for(var i = 0; i < length; i++) {
          var item = objectMap.get(object[i].kriCode);
          if(item) {
              object[i].kriOldExpr = item.kriOldExpr;
              object[i].oldValue = item.oldValue;
          }
          objectMap.set(object[i].kriCode,object[i]);
      }
      var arr = new Array();
      objectMap.forEach(function (item) {
          arr.push(item)
      });
      EtrExchangeTrade001.riskResultdata = arr;
      EtrExchangeTrade001.cluflag = cluflag;
      Joyin.dialog(JoyinUtil.getLabel('EtrExchangeTradeRiskcal'), '/invest/entrust/etrexchangetrade/EtrExchangeTradeRiskcal/init', '60%', '75%',null);
  }
/**
 * 验证双击事件数据是否符合check规则
 */

var checkDbClickData = function(params){
	var tradePlat = params.tradePlat;//数据列表参数没有交易平台；
	var tradeMarket = params.tradeMarket;
	if(!tradePlat){//交易平台从参数左侧参数抽取
		var object = EtrExchangeTrade001.getSearchParams();
		if(object){
			tradePlat = object.tradePlat;
		}
	}
	if(!tradeMarket){//交易场所从参数左侧参数抽取
		var object = EtrExchangeTrade001.getSearchParams();
		if(object){
			tradeMarket = object.tradeMarket;
		}
	}
    var entrustType = params.entrustType;//只有右下角的指令列表有指令业务类型,其他列表没有指令业务类型，不可作为判断条件！
    var finprodType2 = params.finprodType2;
    if(entrustType && finprodType2){
        if(entrustType == "01" || entrustType == "02"
            || entrustType == "07" || entrustType == "09"
            || entrustType == "10" || entrustType == "11"
            || entrustType == "12" || entrustType == "13"){//竞价撮合平台  债券、股票、基金
            if(finprodType2 != FINPROD_TYPE_TG_F01 && finprodType2 != FINPROD_TYPE_TG_F06 && finprodType2 != FINPROD_TYPE_TG_F10 && finprodType2 != FINPROD_TYPE_TG_F02 && finprodType2 != FINPROD_TYPE_TG_F03 &&  finprodType2 != FINPROD_TYPE_TG_ZYSHG){
                Joyin.error("竞价交易仅支持债券、股票、基金、质押式回购交易，该资产类型不匹配!");
                return false;
            }
        }else if(entrustType == "03" || entrustType == "04" || entrustType == "05" || entrustType == "06"){//大宗交易平台 股票、基金、债券、质押式正回购、质押式逆回购
            if(finprodType2 != FINPROD_TYPE_TG_F06 && finprodType2 != FINPROD_TYPE_TG_F10 && finprodType2 != FINPROD_TYPE_TG_F01 && finprodType2 != FINPROD_TYPE_TG_F02 && finprodType2 != FINPROD_TYPE_TG_F03 &&  finprodType2 != FINPROD_TYPE_TG_ZYSHG){
                Joyin.error("大宗交易仅支持股票、基金、债券、质押式回购交易，该资产类型不匹配!");
                return false;
            }
        }
        else if(entrustType == "08") {
            if(finprodType2 != FINPROD_TYPE_TG_F01) {
                Joyin.error("固定收益仅支持债券交易，该资产类型不匹配!");
                return false;
            }
        }
    }else if(finprodType2){
    	if(tradePlat=="02" && finprodType2 != FINPROD_TYPE_TG_F01 && finprodType2 != FINPROD_TYPE_TG_F06 && finprodType2 != FINPROD_TYPE_TG_F10 && finprodType2 != FINPROD_TYPE_TG_F02 && finprodType2 != FINPROD_TYPE_TG_F03 &&  finprodType2 != FINPROD_TYPE_TG_ZYSHG){
            Joyin.error("竞价交易仅支持债券、股票、基金、质押式回购交易，该资产类型不匹配!");
            return false;
        }else if((tradePlat=="04" || tradePlat=="05") && finprodType2 != FINPROD_TYPE_TG_F06 && finprodType2 != FINPROD_TYPE_TG_F10 && finprodType2 != FINPROD_TYPE_TG_F01 && finprodType2 != FINPROD_TYPE_TG_F02 && finprodType2 != FINPROD_TYPE_TG_F03 && finprodType2 != FINPROD_TYPE_TG_ZYSHG){
            Joyin.error("大宗交易仅支持股票、基金、债券、质押式回购交易，该资产类型不匹配!");
            return false;
        }else if(tradePlat=="03" && finprodType2 != FINPROD_TYPE_TG_F01) {
            Joyin.error("选择的品种不支持固定收益平台!");
            return false;
        }else if(tradePlat=="03" && finprodType2 == FINPROD_TYPE_TG_F01){
		    if (tradeMarket == "04") {//04  深圳证券交易所
				Joyin.error("深圳证券交易所没有固定收益平台!");
				return false;
			}
        }
    }
    return true;
}

 //双击check
var dbclickCheck = function(data){
    if(data.entrustStatus != ENTRUST_STATUS_05&&data.entrustStatus != ENTRUST_STATUS_07){
        Joyin.error(data.entrustStatusString+"的指令不允许修改!");
        return false;
    }
    //验证数据
    if(!checkDbClickData(data)){
        return false;
    }
    return true;
}
//关闭委托
var closeEtr = function(){
    if(EtrExchangeTrade003){
        var selArr = EtrExchangeTrade003.getAllCheckedRows();;
    }
    if (!selArr || selArr.length == 0) {
        Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        return;
    }else {
        var object = {selectedItem: selArr, operateType:"close",fromEtrExchange:"Y"};
        Joyin.ajaxJson(basePath + 'invest/entrust/etragrereptrade/EtrAgreRepTrade003/closeCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                Joyin.ajaxJson(basePath + 'invest/entrust/etragrereptrade/EtrAgreRepTrade003/entrustClose', object, function(result) {
                    EtrExchangeTrade003.myrefresh();
                });
            });
        });
    }
}

/**
 * 
 * @returns
 */
function doReset() {
	if (EtrAgreRepTrade001) {
        Joyin.confirm('重置后会清空页面，确认重置？', function () {
        	EtrAgreRepTrade001.doReset(false);
            doResetRiskMathFlag();
        });
	}
}

function doResetRiskMathFlag (){
    if(BaseRiskMathComm) {
        window.BaseRiskMathComm.resetRiskMathFlag();
    }
}


//撤销委托
var revokeEtr = function(){
    if(EtrExchangeTrade003){
        var selArr = EtrExchangeTrade003.getAllCheckedRows();;
    }
    if (!selArr || selArr.length == 0) {
        Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        return;
    }else {
        var object = {selectedItem: selArr, operateType:"revoke",fromEtrExchange:"Y"};
        Joyin.ajaxJson(basePath + 'invest/entrust/etragrereptrade/EtrAgreRepTrade003/revokeCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤回"), function(){
                Joyin.ajaxJson(basePath + 'invest/entrust/etragrereptrade/EtrAgreRepTrade003/entrustRevoke', object, function(result) {
                    EtrExchangeTrade003.myrefresh();
                });
            });
        });
    }
}

// 指令导入
var importEtr = function() {
	Joyin.dialog(JoyinUtil.getLabel("AssetDeclarationCodeMaintenance001.import"),
					JoyinUtil.getRealPath("invest/entrust/etrexchangetrade/EtrExchangeImport/init"),"90%", "90%", null, function() {
						EtrExchangeTrade003.myrefresh();
		});
}

$(function() {
	
	Split(['#left_bottom_panel', '#right_bottom_panel'], {
        sizes: [42, 58],
        minSize: 100,
        direction: 'horizontal',
    });

    Split(['#top_panel', '#bottom_panel'], {
        sizes: [50, 50],
        minSize: 100,
        direction: 'vertical',
    });

    /*Split(['#left_bottom_panel', '#right_bottom_panel'], {
        sizes: [48, 49],
        minSize: 100,
        direction: 'horizontal',
    });*/
	 // 绑定提交按钮
    $('#EtrAgreRepTrade001_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
    
    // 绑定保存按钮
    $('#EtrAgreRepTrade001_btn_save').click(function() {
        saveOrSubmit('save');
    });
    
    // 绑定取消按钮
    $('#EtrAgreRepTrade001_btn_reset').click(function() {
        doReset(false);
        doResetRiskMathFlag();
    });
    
    // 绑定风险按钮
    $('#EtrAgreRepTrade001_btn_riskcal').click(function() {
        doRiskcal();
    });
}());
