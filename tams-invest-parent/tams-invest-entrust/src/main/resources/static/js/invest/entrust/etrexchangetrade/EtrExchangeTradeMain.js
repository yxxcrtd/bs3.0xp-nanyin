/**
 * 主页面JS
 */   

var correctData;

  /**
   * 指令提交或者保存
   */

  function saveOrSubmit(operateType) {
      var s;
      var object = EtrExchangeTrade001.getSearchParams();
      if(object.entrustPrice&&object.entrustPrice<0){
          checkMessage(object.entrustPriceLabel);
          return;
      }
      if(object.entrustNum&&object.entrustNum<0){
          checkMessage(object.entrustNumLabel);
          return;
      }
      if(object.entrustAmt&&object.entrustAmt<0){
          checkMessage(object.entrustAmtLabel);
          return;
      }
      if(object.toShareAvailableNum&&object.toShareAvailableNum<0){
          checkMessage(object.toShareAvailableNumLabel);
          return;
      }
      object.operateType = operateType;
      //object.pageModel = pageModel;
            
      // var riskdata = EtrExchangeTrade001.getriskdata(object);
      // if(BaseRiskMathComm && EtrExchangeTrade001.isNeedRiskcal()) {
      //     s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
      //     if(!s && s != ''){
      //         return;
      //     }
      //     if (window.BaseRiskMathComm.isCalcConditionChanged(riskdata)){
      //         return;
      //     }
      // }
      //验证
      if(!checkDbClickData(object)){
          return;
      }
      //TODO 风险和授信
//      Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
          Joyin.ajaxJson(basePath + '/invest/entrust/etrexchangetrade/EtrExchangeTrade001/check',object,
              function(result) {
                  Joyin.confirm(result.data,function() {
                      if (object.operateType === 'save') {
                          WorkFlow.doSaveList(basePath+'/invest/entrust/etrexchangetrade/EtrExchangeTrade001/save',object,"EtrExchangeTrade001_btn_save",null,function() {
                              if (EtrExchangeTrade001) {
                                  EtrExchangeTrade001.doReset(true);
                              }
                              //报错后风险试算标识置为01
                              // if(BaseRiskMathComm) {
                              //     BaseRiskMathComm.resetRiskMathFlag();
                              // }
                              JoyinUtil.disableButton("EtrExchangeTrade001_btn_save", false);
                          })
                      }else {
                          WorkFlow.doSubmitList(basePath+'/invest/entrust/etrexchangetrade/EtrExchangeTrade001/save',object,"EtrExchangeTrade001_btn_submit",null,function() {
                              if (EtrExchangeTrade001) {
                                  EtrExchangeTrade001.doReset(true);
                              }
                              //提交后风险试算标识置为01
                              // if(BaseRiskMathComm) {
                              //     BaseRiskMathComm.resetRiskMathFlag();
                              // }
                              JoyinUtil.disableButton("EtrExchangeTrade001_btn_submit", false);
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
      var object = EtrExchangeTrade001.getSearchParams();
      if(object && EtrExchangeTrade001.isNeedRiskcal()) {
          var riskdata = EtrExchangeTrade001.getriskdata(object);
          var riskInterfUrl = EtrExchangeTrade001.getRiskInterfUrl();
          if(riskdata && riskInterfUrl) {
              window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object,riskdata,riskMathRedisCallback);
          }
      }

  }
  /**
   * 风险试算的回调函数
   * @param object
   * @param cluflag
   * @returns
   */
  function riskMathRedisCallback(object,cluflag) {
      EtrExchangeTrade001.riskResultdata = object;
      EtrExchangeTrade001.cluflag = cluflag;
      Joyin.dialog(JoyinUtil.getLabel('EtrExchangeTradeRiskcal'), '/invest/entrust/etrexchangetrade/EtrExchangeTradeRiskcal/init', '60%', '75%',null); 
  }

  /**
   * 双击校验后，将风险试算标识重置
   * @returns
   */
  function dbclickRefreshRisk(){
      // if(BaseRiskMathComm) {
      //     BaseRiskMathComm.resetRiskMathFlag();
      // }
  }
  
  /**
   * 验证剩余持仓符合零股是否全部卖出
   */
  var checkSalesOfSmallShares = function(params) {
	Joyin.confirm("剩余可用数量存在零股，是否卖出零股？", function() {
		EtrExchangeTrade001.setTotalSharesNum(params);
	});
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
        for(var i=0; i<selArr.length; i++){
            if(selArr[i].entrustStatus == '02'){
                Joyin.error('不能对待接收的数据进行关闭操作');
                return;
            }
            if(selArr[i].entrustStatus == '04'){
                Joyin.error('不能对已完成的数据进行关闭操作');
                return;
            }
            if(selArr[i].entrustStatus == '06'){
                Joyin.error('已关闭状态的数据不允许再次关闭');
                return;
            }

            if(selArr[i].entrustStatus == '08'){
                Joyin.error("流程中的指令不允许关闭");
                return ;
            }
        }
        var object = {selectedItem: selArr, operateType:"close"};
        Joyin.ajaxJson(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade003/closeCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                Joyin.ajaxJson(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade003/entrustClose', object, function(result) {
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
	if (EtrExchangeTrade001) {
        Joyin.confirm('重置后会清空页面，确认重置？', function () {
            EtrExchangeTrade001.doReset();
            //重置风险试算标识置为01
            // if(BaseRiskMathComm) {
            //     BaseRiskMathComm.resetRiskMathFlag();
            // }
        });
	}
}


//撤销委托
var revokeEtr = function(){
    if(EtrExchangeTrade003){
        var selArr = EtrExchangeTrade003.getAllCheckedRows();
    }

    if (!selArr || selArr.length == 0) {
        Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
        return;
    }else {
        for(var i=0; i<selArr.length; i++) {
            if (selArr[i].entrustStatus == '03') {
                Joyin.error('不能对已接收的数据进行撤回操作');
                return;
            }
            if (selArr[i].entrustStatus == '04') {
                Joyin.error('不能对已完成的数据进行撤回操作');
                return;
            }
            if (selArr[i].entrustStatus == '05') {
                Joyin.error('不能对已退回的数据进行撤回操作');
                return;
            }
            if (selArr[i].entrustStatus == '06') {
                Joyin.error('不能对已关闭的数据进行撤回操作');
                return;
            }
            if (selArr[i].entrustStatus == '07') {
                Joyin.error('已撤回的指令不允许再次撤回');
                return;
            }

            if (selArr[i].entrustStatus == '08') {
                Joyin.error("流程中的指令不允许撤回");
                return;
            }
        }
    }
        var object = {selectedItem: selArr, operateType:"revoke"};
        Joyin.ajaxJson(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade003/revokeCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤回"), function(){
                Joyin.ajaxJson(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade003/entrustRevoke', object, function(result) {
                    EtrExchangeTrade003.myrefresh();
                });
            });
        });
}

function hideLeftTopPanel(){
    $("div.left_top_panel").each(function(i,v){
        $(v).css("display","none");
    });
    $(".layui-form-label").css('width','20%');
    $(".layui-elem-field").css('min-width','800px');
    $(".left_bottom_panel").css('height','600px');
}

function showLeftTopPanel(){
    $("div.left_top_panel").each(function(i,v){
        $(v).css("display","inline");
    });
}


// 指令导入
var importEtr = function() {
	Joyin.uploadFile({pageId : 'EtrExchangeImport'}, function(fileName) {
        Joyin.ajax(basePath + 'invest/entrust/etrexchangetrade/EtrExchangeTrade001/checkImportData', {fileName: fileName}, function(checkResult) {
            setTimeout( function () {
                if(checkResult.data.errorMsg){
                    Joyin.error(checkResult.data.errorMsg);
                    return;
                }
                Joyin.confirm(checkResult.data.confirmMsg,function() {
                    correctData = checkResult.data.correctData;
                    if(typeof correctData === 'string') {
                    	correctData = JSON.parse(correctData);
                    	if(correctData != null && correctData.length > 0){
                    		var data = [];
                    		for(var i in correctData){
                    			data.push(JSON.parse(correctData[i]));
                    		}
                    		correctData = data;
                    	}
                    }
                    Joyin.dialog('场内竞价交易指令导入'/*JoyinUtil.getLabel("EtrBrowDepoInfo003")*/,
                        JoyinUtil.getRealPath("/invest/entrust/etrexchangetrade/EtrExchangeImport/init"),
                        "98%", "95%", {},
                        function(){EtrExchangeTrade003.myrefresh();});
                });
            },1000);
        });
    },function() {
        table.reload();
    });
}

$(function() {

	var init = function() {
		if (pageModel==PAGE_MODEL_REFERENCE || pageModel==PAGE_MODEL_EDIT) {
			$('.right_panel').css('display','none');
			$('#EtrExchangeTrade001_btn_reset').css('display','none');
		} else {
            $('#left_top_div').show();
            Split(['#left_panel_div', '#right_panel_div'], {
                sizes: [25, 75],
                minSize: [300,800],
                direction: 'horizontal',
            });

            Split(['#left_top_div', '#left_bottom_panel_div'], {
                sizes: [48, 44],
                minSize: 100,
                direction: 'vertical',
            });

            Split(['#right_top_panel_div', '#right_bottom_panel_div'], {
                sizes: [48, 49],
                minSize: 100,
                direction: 'vertical',
            });
        }
		//窗口容器的高度，设置为页面左右容器的高度
		var height = window.innerHeight;
        $('.right_panel').css('height', height);
        $('.left_panel').css('height', height);
	}
	
	init();
	
	 // 绑定提交按钮
    $('#EtrExchangeTrade001_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
    
    // 绑定保存按钮
    $('#EtrExchangeTrade001_btn_save').click(function() {
        saveOrSubmit('save');
    });
    
    // 绑定取消按钮
    $('#EtrExchangeTrade001_btn_reset').click(function() {
        doReset();
    });
    
    // 绑定风险按钮
    $('#EtrExchangeTrade001_btn_riskcal').click(function() {
        doRiskcal();
    });
}());
