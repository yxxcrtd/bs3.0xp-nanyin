/**
 * 组合信息查看页面
 */
var showPtlManagement = function (portfolioId) {
    Joyin.dialog(JoyinUtil.getLabel("PtlManagementInfo002.see"), basePath
        + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
        "95%", "90%", {
            pageModel: PAGE_MODEL_REFERENCE,
            portfolioId: portfolioId,
        });
}
/**
 * 预交易查看页面
 */
var showDealPre = function (totleTradeId, finprodTypeStr) {
    if (finprodTypeStr == "债券") {
        Joyin.dialog(JoyinUtil.getLabel("TrdBondInvestTrade002.reference"),
                JoyinUtil.getRealPath("invest/trade/pretrdbondtrade/PreTrdBondInvestTrade002/init"),
                "99%", "99%", 
                {
                pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                tradeId:totleTradeId,
                }
        );
    } else {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                JoyinUtil.getRealPath("invest/trade/pretrdrepotrade/PreTrdRepoTrade002/init"),
                "99%", "99%", 
                {
                pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                tradeId:totleTradeId,
                }
        );
    }
}

/**
 * 指令查看页面
 */
var showEntrust = function (entrustId, finprodTypeStr) {
    if (finprodTypeStr == "债券") {
        label = 'EtrInvestBondInfo001.detail';
        pagePath = "invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/init";
        Joyin.dialog(JoyinUtil.getLabel(label),
            basePath + pagePath, "95%", "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                entrustId: entrustId,
            });
    } else {
        label = 'EtrRepo002.detail';
        pagePath = "invest/entrust/etrrepo/EtrRepo002/init";
        Joyin.dialog(JoyinUtil.getLabel(label),
            basePath + pagePath, "95%", "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                entrustId: entrustId,
            });
    }
}

//不同资产的查看页面
var showDetailByFinprodId = function (finprodId, finprodType2) {
    var label = '';
    var pagePath = '';
    //债券标的信息查看
    if (finprodType2 == PINAN_ASSET_TYPE_F01
        || finprodType2 == PINAN_ASSET_TYPE_F10) {
        label = 'FinBondTargetAsset002.title';
        pagePath = "invest/asset/bond/FinBondTargetAsset002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//基金类标的信息查看
    } else if (finprodType2 == PINAN_ASSET_TYPE_F02) {
        label = 'FinFundTarget002.detail';
        pagePath = "invest/asset/fund/FinFundTarget002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//计划类利率型标的信息查看
    } else if (finprodType2 == PINAN_ASSET_TYPE_F03) {
        label = 'FinPlanProfitAsset002.detail';
        pagePath = "invest/asset/plan/planProfit/FinPlanProfitAsset002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//计划类净值型标的信息查看
    } else if (finprodType2 == PINAN_ASSET_TYPE_F04) {
        label = 'FinPlanNetAsset002.detail';
        pagePath = "invest/asset/plan/planNet/FinPlanNetAsset002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//存款标的信息查看
    } else if (finprodType2 == PINAN_ASSET_TYPE_F05) {
        label = 'FinDepositTarget002.title';
        pagePath = "invest/asset/depo/FinDepositTarget002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//项目类资产标的信息查看
    } else if (finprodType2 == PINAN_ASSET_TYPE_F06) {
        label = 'FinProjectTargetAsset002.title';
        pagePath = "invest/asset/project/FinProjectTargetAsset002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//回购标的信息查看
    } else if (finprodType2 == PINAN_ASSET_TYPE_F07) {
        label = '';
        pagePath = "";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//拆借标的信息查看
    } else if (finprodType2 == PINAN_ASSET_TYPE_F08) {
        label = '';
        pagePath = "";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//计划类货币
    } else if (finprodType2 == PINAN_ASSET_TYPE_F09) {
        label = 'SysCommon.refrence';
        pagePath = "invest/asset/plan/plancur/FinPlanAssetC002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });
    } else if (finprodType2 == PINAN_ASSET_TYPE_F11) {
        label = 'FinStockInfo002.refrence';
        pagePath = "invest/asset/stock/FinStockInfo002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath, "95%",
            "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });
    } else if (finprodType2 == PINAN_ASSET_TYPE_F12) {
        label = 'SysCommon.refrence';
        pagePath = "invest/asset/bond/FinBondTargetAsset002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
            "95%", "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//债券借贷指令查看
    } else if (finprodType2 == PINAN_ASSET_TYPE_F16) {
        label = 'SysCommon.refrence';
        pagePath = "invest/entrust/etrirs/EtrIRS002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
            "95%", "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });//一级投标债信息
    } else if (finprodType2 == PINAN_ASSET_TYPE_F17) {
        label = 'FinTenderBondTargetAsset002.title';
        pagePath = "invest/asset/tenderbond/FinTenderBondTargetAsset002/init";
        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
            "95%", "90%", {
                pageModel: PAGE_MODEL_REFERENCE,
                finprodId: finprodId
            });
    }
}
$(function(){
    /**
     * CSTP获取查询参数
     */
    var getPageParams = function(type) {
        var obj = {};
        var table = EntrustTable.getAllCheckedRows();
        var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId = null;
        if(portfolioIdTemp){
            obj.portfolioId = portfolioIdTemp.join(",");
        }else{
            obj.portfolioId ='';
        }
        var matchStatusTemp = $('#matchStatus').val();
        var matchStatus = null;
        if(matchStatusTemp != null)
            obj.matchStatus = matchStatusTemp.join(",");
        var execidTemp = $('#execid').val();
        var execid = null;
        if(execidTemp){
            obj.execid = execidTemp.join(",");
        }else{
            obj.execid='';
        }
        obj.isEntrust = false;
        obj.quoteId=$("#quoteId").val()
        if(table[0]&&type=='entrust'){
            obj.isEntrust = true;
            obj.entrustSecAcctId = table[0].secAcctId;
            obj.entrustPortfolioId = table[0].portfolioId;
            obj.entrustPriceModel =table[0].priceMode;
            obj.entrustMatchType = table[0].tradeType;
            obj.entrustTradeDateBegin = table[0].vdateStr;
            obj.entrustTradeDateEnd = table[0].mdateStr;
            if(table[0].settleSpeed=='0'||table[0].settleSpeed=='01'){
                obj.entrustSettleSpeed='1'
            }else if(table[0].settleSpeed=='1'||table[0].settleSpeed=='02'){
                obj.entrustSettleSpeed='2'
            }else if(table[0].settleSpeed=='1'||table[0].settleSpeed=='03'){
                obj.entrustSettleSpeed='3'
            }else{//空值
                obj.entrustSettleSpeed='5'
            }
            if(table[0].finprodType2&&table[0].finprodType2=='F01'){
                obj.entrustFinprodType = 'BOND'
                obj.entrustPrice = table[0].price;//净价
                obj.entrustUnderlyingyield2 = table[0].matureYield;//到期收益率
                obj.entrustStrikeyield = table[0].exerYield;//行权收益率
            }else if(table[0].finprodType2&&table[0].finprodType2=='F07'){
                obj.entrustFinprodType = 'REPO'
                obj.entrustPrice = table[0].price;//回购利率
                obj.entrustTradelimitdays =table[0].repoTerm;//回购期限
            }
            if(table[0].finprodMarketId){
                obj.entrustFinprodId = table[0].finprodMarketId;
            }
            if(table[0].ps){
                obj.entrustPs = table[0].ps;
            }
            if(table[0].tradeMarket){
                obj.entrustTradeMarket = table[0].tradeMarket;
            }
        }else{
            obj.finprodType = $('#finprodType').val();
            obj.finprodId = $('#finprodId').val();
            obj.ps = $('#ps').val();
            obj.tradeDateBegin = $('#tradeDate').startDate();
            obj.tradeDateEnd = $('#tradeDate').endDate();
            obj.tradeMarket = $('#tradeMarket').val();
            obj.repoTerm = $('#repoTerm').val();
            obj.repoTerm2 = $('#repoTerm2').val();
            obj.priceAmt = $('#priceAmt').val();
            obj.priceAmt2 = $('#priceAmt2').val();
            obj.priceAmtAll = $('#priceAmtAll').val();
        }
        return  obj;
    };
    var getEntrustPageParams = function (type) {
        var data = table.getAllCheckedRows();
        var obj ={};
        obj.busiCode=$("#TrdCommandReceive001_busiCode").val();
        obj.beginDate = $('#TrdCommandReceive001_createTime').startDate();
        obj.endDate = $('#TrdCommandReceive001_createTime').endDate();
        //obj.portfolioId =  $('#TrdCommandReceive001_portfolioId').val();
        if (!$('#TrdCommandReceive001_portfolioId').val() == '') {
            obj.portfolioId = $("#TrdCommandReceive001_portfolioId").val()
                .join(",");
        } else {
        	obj.portfolioId = null;
        }
        //obj.matchType = $('#TrdCommandReceive001_matchType').val();
        if (!$('#TrdCommandReceive001_matchType').val() == '') {
            obj.matchType = $("#TrdCommandReceive001_matchType").val()
                .join(",");
        } else {
        	obj.matchType = null;
        }
        obj.tradeMarket = $('#TrdCommandReceive001_tradeMarket').val();
        obj.repoTerm = $('#TrdCommandReceive001_repoTerm').val();
        obj.repoTerm2 = $('#TrdCommandReceive001_repoTerm2').val();
        obj.priceAmt = $('#TrdCommandReceive001_priceAmt').val();
        obj.priceAmt2 = $('#TrdCommandReceive001_priceAmt2').val();
        if (!$("#TrdCommandReceive001_finprodType2").val() == '') {
            obj.finprodType2 = $("#TrdCommandReceive001_finprodType2").val()
                .join(",");
        } else {
        	obj.finprodType2 = null;
        }
        obj.finprodMarketId = $('#TrdCommandReceive001_finprodMarketId').val();
        //利率互换交易方向特殊处理  后台转换
        if ($("#TrdCommandReceive001_ps").val()) {
            obj.ps = $("#TrdCommandReceive001_ps").val();
        }
        obj.isCstp = false;
        if(data[0]&&type){
            obj.isCstp = true;
            obj.cstpTradeDate  = data[0].tradeDateStr;
            if(data[0].businesstype=='BOND'){
                obj.cstpFinprodType2 ='F01';
                obj.cstpFfinprodMarketId =data[0].securityid;//资产代码
                if(data[0].price){
                    obj.cstpPrice = data[0].price;//净价
                }else{
                    obj.cstpPrice = null;//净价
                }

                if(data[0].underlyingyield2){
                    obj.cstpMatureYield = data[0].underlyingyield2;//到期收益率
                }else{
                    obj.cstpMatureYield=null;
                }
                if(data[0].strikeyield){
                    obj.cstpExerYield = data[0].strikeyield;//行权收益率
                }else{
                    obj.cstpExerYield = null;
                }
            }else if(data[0].businesstype=='REPO'){
                obj.cstpFinprodType2 ='F07';
                if(data[0].price){
                    obj.cstpPrice = data[0].price;//净价
                }else{
                    obj.cstpPrice = null;//净价
                }
                obj.cstpRepoTerm =data[0].tradelimitdays;//回购期限
            }
            obj.cstpTradeMarket = data[0].market;//交易场所
            obj.cstpPs = data[0].side;
            obj.cstpSecAcctId = data[0].ourdepositaccountnumber;
            if(data[0].settleSpeed=='1'||data[0].settleSpeed=='01'){
                obj.cstpSettleSpeed='0'
            }else if(data[0].settleSpeed=='2'||data[0].settleSpeed=='02'){
                obj.cstpSettleSpeed='1'
            }else if(data[0].settleSpeed=='3'||data[0].settleSpeed=='03'){
                obj.cstpSettleSpeed='2'
            }else{//空值
                obj.cstpSettleSpeed='5'
            }
        }
        return obj;
    };
    var TrdMatchInfoPageParams = function () {
        var obj = {};
         var trdMatchTypTemp = $('#trdMatchInfo_TrdMatchType').val();
        if(trdMatchTypTemp){
            obj.trdMatchTyp = trdMatchTypTemp.join(",");
        }else{
            obj.trdMatchTyp =null;
        }
        var portfolioIdTemp = $('#trdMatchInfo_portfolioId').val();
        if(portfolioIdTemp){
            obj.portfolioId = portfolioIdTemp.join(",");
        }else{
            obj.portfolioId=null;
        }
        var finprodType2 = $('#trdMatchInfo_finprodType2').val();
        if(finprodType2){
            obj.finprodType2 = finprodType2.join(",");
        }else{
            obj.finprodType2 =null;
        }
        var execid = $('#trdMatchInfo_execid').val();
        if(execid){
            obj.execid = execid.join(",");
        }else{
            obj.execid =null;
        }
        obj.ps = $('#trdMatchInfo_ps').val();
        obj.tradeMarket = $('#trdMatchInfo_tradeMarket').val();
        obj.finprodMarketId = $('#trdMatchInfo_finprodMarketId').val();
        obj.trdMatchInfoDateBegin = $('#trdMatchInfo_tradeDate').startDate();
        obj.trdMatchInfoDateEnd = $('#trdMatchInfo_tradeDate').endDate();
        obj.repoTerm = $('#trdMatchInfo_repoTerm').val();
        obj.repoTerm2 = $('#trdMatchInfo_repoTerm2').val();
        obj.priceAmt = $('#trdMatchInfo_priceAmt').val();
        obj.priceAmt2 = $('#trdMatchInfo_priceAmt2').val();
        return obj;
    }
//    var TrdMatchInfoSave = function (tableData,tableCstp) {
//        var object = {};
//        object.trdMatchInfos = [];
//        $.each(tableData,function (index,val) {
//            $.each(tableCstp,function (index1,val1) {
//                var trdMatchInfo = {};
//                trdMatchInfo.cstpTradeId=val1.execid;
//                if(val.tradeType=='02'){//投资指令
//                    trdMatchInfo.preTradeId=''
//                    trdMatchInfo.entrustId=val.entrustId;
//                }else{
//                    trdMatchInfo.preTradeId=val.entrustId;
//                    trdMatchInfo.entrustId='';
//                }
//                if(tableCstp.length==1&&tableData.length==1){
//                	var amt = 0;
//                	if(val1.businesstype=='BOND'){
//                		amt = Number(val1.prinamt);
//                	} else if(val1.businesstype=='REPO'){
//                		amt = Number(val1.repoamt);
//                    }
//                	if(val.tradeType=='02'){
//                		if (Number(val.entrustAmt) > amt) {
//                			trdMatchInfo.matchNum=amt;
//                		} else {
//                			trdMatchInfo.matchNum=val.entrustAmt;
//                		}
//                	} else {
//                        if(val1.businesstype=='BOND'){
//                            trdMatchInfo.matchNum=val1.prinamt;
//                        }else if(val1.businesstype=='REPO'){
//                            trdMatchInfo.matchNum=val1.repoamt;
//                        }
//                	}
//                }else{
//                    if(tableCstp.lengt>1){
//                        if(val1.businesstype=='BOND'){
//                            trdMatchInfo.matchNum=val1.prinamt;
//                        }else if(val1.businesstype=='REPO'){
//                            trdMatchInfo.matchNum=val1.repoamt;
//                        }
//                    }else if(tableData.length>1){
//                    	var amt = 0;
//                    	if(val1.businesstype=='BOND'){
//                    		amt = Number(val1.prinamt);
//                    	} else if(val1.businesstype=='REPO'){
//                    		amt = Number(val1.repoamt);
//                        }
//                    	if(val.tradeType=='02'){
//                    		if (Number(val.entrustAmt) > amt) {
//                    			trdMatchInfo.matchNum=amt;
//                    		} else {
//                    			trdMatchInfo.matchNum=val.entrustAmt;
//                    		}
//                    	} else {
//                            if(val1.businesstype=='BOND'){
//                                trdMatchInfo.matchNum=val1.prinamt;
//                            }else if(val1.businesstype=='REPO'){
//                                trdMatchInfo.matchNum=val1.repoamt;
//                            }
//                    	}
////                    	if(val.tradeType=='02'){
////                    		trdMatchInfo.matchNum=val.entrustAmt;
////                    	} else {
////                    		trdMatchInfo.matchNum=val.prinAmt;
////                    	}
//                    }
//                }
//                trdMatchInfo.matchType='1';//手工匹配
//                trdMatchInfo.tradeDate=new Date(val.createTimeValStr);
//                object.trdMatchInfos.push(trdMatchInfo);
//            })
//        })
//        return object;
//    }
    
    
    
//    var TrdMatchInfoSaveNew = function (tableData,tableCstp) {
//    	var object = {};
//        object.trdMatchInfos = [];
//    	if(tableData.length>1){//一条成交匹配多条委托
//    		if(val.tradeType=='01'){//预录入走原先逻辑
//    			return TrdMatchInfoSave(tableData,tableCstp)
//    		}else if(tableCstp[0].businesstype=='BOND'){//债券走原先逻辑
//    			return TrdMatchInfoSave(tableData,tableCstp)
//    		}else{//回购
//    			
//    		}
//    		
//    	}else{//多条成交匹配一条委托
//    		if(val.tradeType=='01'){//预录入走原先逻辑
//    			return TrdMatchInfoSave(tableData,tableCstp)
//    		}else if(tableCstp[0].businesstype=='BOND'){//债券走原先逻辑
//    			return TrdMatchInfoSave(tableData,tableCstp)
//    		}else{//回购
//    			
//    		}
//    	}
//        return object;
//    }
    
//    var MatchRepo = function (tableCstp,tableEntrust){
//    	
//    	var object = {};
//        object.trdMatchInfos = [];
//    	//获取当前cstp剩余质押券
//    	Joyin.ajaxSync('/invest/trade/bond/TrdBondInvestTrade002/getSecManageAcctId',tableCstp,function (data) {
//            if(data){
//            	$.each(data,function (index,val) {
//            		$.each(tableCstp,function (CstpIndex,cstp) {
//                		if(val.execid==cstp.execid){//成交编号
//                			cstp.interfTradRepoasstList = val
//                		}
//                	}
//            	}
//            }
//        })
//        //获取当前委托可用质押券
//        Joyin.ajaxSync('/invest/trade/bond/TrdBondInvestTrade002/getSecManageAcctId',tableEntrust,function (data) {
//            if(data){
//            	$.each(data,function (index,val) {
//            		$.each(tableEntrust,function (entrustIndex,entrust) {
//                		if(val.entrust==cstp.entrust){//委托编号
//                			entrust.trdPledgeList = val
//                		}
//                	}
//            	}
//            }
//        })
//        
//        //委托匹配多成交
//        if(tableCstp.length>1){
//        	var entrust = tableEntrust[0];
//        	entrust.ava =entrust.proAvailableCash;//初始化 委托可交易金额
//        	for(var i=0;i>=tableCstp.length;i++) {
//        		if(entrust.ava<=0){//无可用时，跳出
//        			break;
//        		}
//        		var cstpVal =tableCstp[i];//cstp下行数据
//        		var trdMatchInfo = {};//需要匹配数据
//        		var trdPledgeMatchInfoList =[]; //需要匹配的质押券信息
//                trdMatchInfo.cstpTradeId=cstpVal.execid;
//                trdMatchInfo.preTradeId=''
//                trdMatchInfo.entrustId=entrust.entrustId;
//                trdMatchInfo.matchType='1';//手工匹配
//                trdMatchInfo.tradeDate=new Date(entrust.createTimeValStr);
//                //成交对应的折算比例
//                var totaldeal = 0;
//                $.each(cstpVal.interfTradRepoasstList,function(index,val){
//                	totaldeal = JoyinMath.doAdd( totaldeal,val.dealamt)
//                })
//                //上请本金
//                cstpVal.scFpriceAmt
//                //中债本金
//                cstpVal.cbFpriceAmt
//                //trdMatchInfo
//                trdMatchInfo.matchNum =0;//匹配额度
//                //未匹配金额
//                var noMatchNum =JoyinMath.doMinus(cstp.tradecashamt,cstp.lastqtyready);
//                
//                //cstp质押券信息
//                for(var j=0;j>=cstpVal.interfTradRepoasstList.length;j++){
//                	for(var s=0;s>=entrust.trdPledgeList.length;s++){
//                		var repoasst = cstpVal.interfTradRepoasstList[j]//当前质押券
//                		var etrpledge = entrust.trdPledgeList[s] //当前委托可用质押券
//                		var repoasstSecId =repoasst.secid+".IB"
//                		if( repoasstSecId== etrpledge.bondCode){//资产代码匹配成功
//                			etrpledge.repRegistOrg//登记机构
//                			repoasst.facetotalamt//成交券面
//                			repoasst.convrate//折算比例
//                			repoasst.dealamt//成交金额
//                			etrpledge.avaPledgePrin//可质押券面
//                			if(etrpledge.avaPledgePrin<=0){//无可用质押   继续循环
//                				continue;
//                			}
//                			var trdPledgeMatchInfo = {};
//            				trdPledgeMatchInfo.cstpTradeId=cstpVal.execid;
//            				trdPledgeMatchInfo.preTradeId=''
//          					trdPledgeMatchInfo.entrustId=entrust.entrustId;
//            				trdPledgeMatchInfo.matchType='1';//手工匹配
//            				trdPledgeMatchInfo.tradeDate=new Date(entrust.createTimeValStr);
//            				
//                			if(repoasst.facetotalamt<etrpledge.avaPledgePrin){//成交券面小于剩余可用质押券
//                				if(repoasst.dealamt<entrust.ava){//成交质押券额度小于剩余可交易
//                					trdPledgeMatchInfo.matchNum = repoasst.facetotalamt
//                					var entrustminus = JoyinMath.doMul(noMatchNum,JoyinMath.doDiv( repoasst.dealamt,  totaldeal, 12),2)
//                					entrust.ava =JoyinMath.doMinus(entrust.ava,entrustminus )//减少剩余额度
//                					etrpledge.avaPledgePrin =JoyinMath.doMinus(etrpledge.avaPledgePrin,repoasst.facetotalamt )//减少可用券面
//                					trdMatchInfo.matchNum =JoyinMath.doAdd(trdMatchInfo.matchNum,entrustminus )//增加匹配额度
//                				}else{
//                					//成交质押券额度大于剩余可交易
//                					trdPledgeMatchInfo.matchNum = etrpledge.avaPledgePrin
//                					var entrustminus = JoyinMath.doMul(JoyinMath.doMul(etrpledge.avaPledgePrin, repoasst.convrate, 12), JoyinMath.doDiv( repoasst.dealamt,  totaldeal, 12), 2)
//                					entrust.ava =JoyinMath.doMinus(entrust.ava,entrustminus )//减少剩余额度
//                					etrpledge.avaPledgePrin =JoyinMath.doMinus(etrpledge.avaPledgePrin,etrpledge.avaPledgePrin )//减少可用券面
//                					trdMatchInfo.matchNum =JoyinMath.doAdd(trdMatchInfo.matchNum,entrustminus )//增加匹配额度
//                				}
//                			}else{//成交券面大于剩余可用质押券
//                				//折算本金
//                				var entrustminus = JoyinMath.doMul(JoyinMath.doMul(etrpledge.avaPledgePrin, repoasst.convrate, 12), JoyinMath.doDiv( repoasst.dealamt,  totaldeal, 12), 2)
//                				if(entrustminus <entrust.ava){//换算的成交信息小于剩余可委托
//                					trdPledgeMatchInfo.matchNum = etrpledge.avaPledgePrin
////                					var entrustminus = JoyinMath.doMul(JoyinMath.doMul(etrpledge.avaPledgePrin, repoasst.convrate, 12), JoyinMath.doDiv( repoasst.dealamt,  totaldeal, 12), 2)
//                					entrust.ava =JoyinMath.doMinus(entrust.ava,entrustminus )//减少剩余额度
//                					etrpledge.avaPledgePrin =JoyinMath.doMinus(etrpledge.avaPledgePrin,etrpledge.avaPledgePrin )//减少可用券面
//                					trdMatchInfo.matchNum =JoyinMath.doAdd(trdMatchInfo.matchNum,entrustminus )//增加匹配额度
//                				}else{
//                					//换算的成交信息大于剩余委托
//                					//通过委托换算可质押券
//                					var matchNum =JoyinMath.doMul(entrust.ava ,JoyinMath.doDiv( totaldeal,repoasst.dealamt,   12), 2);//匹配数量
//                					entrust.ava =JoyinMath.doMinus(entrust.ava,entrust.ava )//减少剩余额度
//                					etrpledge.avaPledgePrin =JoyinMath.doMinus(etrpledge.avaPledgePrin,matchNum )//减少可用券面
//                					trdMatchInfo.matchNum =JoyinMath.doAdd(trdMatchInfo.matchNum,matchNum )//增加匹配额度
//                				}
//                			}
//                			trdPledgeMatchInfoList.push(trdPledgeMatchInfo);
//                		}
//                	}
//                }
//                
//                trdMatchInfos.push(trdMatchInfo);
//                
//                return object;
//        		
//            });
//        }
//        
//        //成交匹配多委托
//        if(tableEntrust.length>1){
//        	var cstp = tableCstp[0]; //成交信息
//        	entrust.ava =entrust.proAvailableCash;//初始化 委托可交易金额
//        	for(var i=0;i>=tableCstp.length;i++) {
//        		if(entrust.ava<=0){//无可用时，跳出
//        			break;
//        		}
//        		var cstpVal =tableCstp[i];//cstp下行数据
//        		var trdMatchInfo = {};//需要匹配数据
//        		var trdPledgeMatchInfoList =[]; //需要匹配的质押券信息
//                trdMatchInfo.cstpTradeId=cstpVal.execid;
//                trdMatchInfo.preTradeId=''
//                trdMatchInfo.entrustId=entrust.entrustId;
//                trdMatchInfo.matchType='1';//手工匹配
//                trdMatchInfo.tradeDate=new Date(entrust.createTimeValStr);
//                //成交对应的折算比例
//                var totaldeal = 0;
//                $.each(cstpVal.interfTradRepoasstList,function(index,val){
//                	totaldeal = JoyinMath.doAdd( totaldeal,val.dealamt)
//                })
//                //上请本金
//                cstpVal.scFpriceAmt
//                //中债本金
//                cstpVal.cbFpriceAmt
//                //trdMatchInfo
//                trdMatchInfo.matchNum =0;//匹配额度
//                
//                //cstp质押券信息
//                for(var j=0;j>=cstpVal.interfTradRepoasstList.length;j++){
//                	for(var s=0;s>=entrust.trdPledgeList.length;s++){
//                		var repoasst = cstpVal.interfTradRepoasstList[j]//当前质押券
//                		var etrpledge = entrust.trdPledgeList[s] //当前委托可用质押券
//                		var repoasstSecId =repoasst.secid+".IB"
//                		if( repoasstSecId== etrpledge.bondCode){//资产代码匹配成功
//                			etrpledge.repRegistOrg//登记机构
//                			repoasst.facetotalamt//成交券面
//                			repoasst.convrate//折算比例
//                			repoasst.dealamt//成交金额
//                			etrpledge.avaPledgePrin//可质押券面
//                			if(etrpledge.avaPledgePrin<=0){//无可用质押   继续循环
//                				continue;
//                			}
//                			var trdPledgeMatchInfo = {};
//            				trdPledgeMatchInfo.cstpTradeId=cstpVal.execid;
//            				trdPledgeMatchInfo.preTradeId=''
//          					trdPledgeMatchInfo.entrustId=entrust.entrustId;
//            				trdPledgeMatchInfo.matchType='1';//手工匹配
//            				trdPledgeMatchInfo.tradeDate=new Date(entrust.createTimeValStr);
//            				
//                			if(repoasst.facetotalamt<etrpledge.avaPledgePrin){//成交券面小于剩余可用质押券
//                				if(repoasst.dealamt<entrust.ava){//成交质押券额度小于剩余可交易
//                					trdPledgeMatchInfo.matchNum = repoasst.facetotalamt
//                					entrust.ava =JoyinMath.doMinus(entrust.ava,repoasst.dealamt )//减少剩余额度
//                					etrpledge.avaPledgePrin =JoyinMath.doMinus(etrpledge.avaPledgePrin,repoasst.facetotalamt )//减少可用券面
//                					trdMatchInfo.matchNum =JoyinMath.doAdd(trdMatchInfo.matchNum,repoasst.dealamt )//增加匹配额度
//                				}else{
//                					//成交质押券额度大于剩余可交易
//                					trdPledgeMatchInfo.matchNum = etrpledge.avaPledgePrin
//                					var entrustminus = JoyinMath.doMul(JoyinMath.doMul(etrpledge.avaPledgePrin, repoasst.convrate, 12), JoyinMath.doDiv( repoasst.dealamt,  totaldeal, 12), 2)
//                					entrust.ava =JoyinMath.doMinus(entrust.ava,entrustminus )//减少剩余额度
//                					etrpledge.avaPledgePrin =JoyinMath.doMinus(etrpledge.avaPledgePrin,etrpledge.avaPledgePrin )//减少可用券面
//                					trdMatchInfo.matchNum =JoyinMath.doAdd(trdMatchInfo.matchNum,entrustminus )//增加匹配额度
//                				}
//                			}else{//成交券面大于剩余可用质押券
//                				//折算本金
//                				var entrustminus = JoyinMath.doMul(JoyinMath.doMul(etrpledge.avaPledgePrin, repoasst.convrate, 12), JoyinMath.doDiv( repoasst.dealamt,  totaldeal, 12), 2)
//                				if(entrustminus <entrust.ava){//换算的成交信息小于剩余可委托
//                					trdPledgeMatchInfo.matchNum = etrpledge.avaPledgePrin
////                            					var entrustminus = JoyinMath.doMul(JoyinMath.doMul(etrpledge.avaPledgePrin, repoasst.convrate, 12), JoyinMath.doDiv( repoasst.dealamt,  totaldeal, 12), 2)
//                					entrust.ava =JoyinMath.doMinus(entrust.ava,entrustminus )//减少剩余额度
//                					etrpledge.avaPledgePrin =JoyinMath.doMinus(etrpledge.avaPledgePrin,etrpledge.avaPledgePrin )//减少可用券面
//                					trdMatchInfo.matchNum =JoyinMath.doAdd(trdMatchInfo.matchNum,entrustminus )//增加匹配额度
//                				}else{
//                					//换算的成交信息大于剩余委托
//                					//通过委托换算可质押券
//                					var matchNum =JoyinMath.doMul(entrust.ava ,JoyinMath.doDiv( totaldeal,repoasst.dealamt,   12), 2);//匹配数量
//                					entrust.ava =JoyinMath.doMinus(entrust.ava,entrust.ava )//减少剩余额度
//                					etrpledge.avaPledgePrin =JoyinMath.doMinus(etrpledge.avaPledgePrin,matchNum )//减少可用券面
//                					trdMatchInfo.matchNum =JoyinMath.doAdd(trdMatchInfo.matchNum,matchNum )//增加匹配额度
//                				}
//                			}
//                			trdPledgeMatchInfoList.push(trdPledgeMatchInfo);
//                		}
//                	}
//                }
//                
//                trdMatchInfos.push(trdMatchInfo);
//                
//                return object;
//        		
//            });
//        }
//            		
//        
//        
//    }
    
    
    

    var TrdMatchInfoDelete = function (tableDate) {
        var object = {};
        object.trdMatchInfos = [];
        $.each(tableDate,function (index,val) {
            var trdMatchInfo = {};
            trdMatchInfo.cstpTradeId=val.cstpTradeId;
            trdMatchInfo.seqno=val.seqno;
            trdMatchInfo.entrustId = val.entrustId;
            trdMatchInfo.preTradeId = val.preTradeId;
            trdMatchInfo.tradeDate = val.tradeDate;
            trdMatchInfo.matchNum = val.prinAmt;
            object.trdMatchInfos.push(trdMatchInfo);
        })
        return object;
    }
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST'
        },
        cols:[
            // {field:'portfolioId', title: 'CSTPSelect001.portfolioId',width:'100'}
            // ,{field:'portfolioName', title: 'CSTPSelect001.portfolioName',width:'100'}
            {field:'matchStatusStr', title: 'CSTPSelect001.matchStatus',sortField:'matchStatus',width:'100',fixed:'left',
                render:function (data,type,row,meta) {
                    if(row.matchStatus == '01'){
                        return "<div class='layui-table-cell laytable-cell-4-0-1' style='color:blue'>"+row.matchStatusStr+"</div>";
                    }
                    else if(row.matchStatus == '02'){
                        return "<div class='layui-table-cell laytable-cell-4-0-1' style='color:red'>"+row.matchStatusStr+"</div>";
                    }
                    else if(row.matchStatus == '03'){
                        return "<div class='layui-table-cell laytable-cell-4-0-1' style='color:yellowgreen'>"+row.matchStatusStr+"</div>";
                    }
                }
            }
            ,{
                field: 'dealPercent',
                title: '匹配度',
                width: 100,
                fixed:'left',
                render: function (data, type, row, meta) {
                    return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"
                        + (row.dealPercent)
                        + "%'><span class='layui-progress-text'>"
                        + (row.dealPercent)
                        + "%</span></div></div>";
                },
                width: 100,
                sortField: 'percent'
            }
            ,{field:'execid', title: 'CSTPSelect001.execid',width:'100'}
            ,{field:'businesstypeStr', title: 'CSTPSelect001.finprodType',sortField:'businesstype',width:'100'}
            ,{field:'securityid', title: 'CSTPSelect001.finprodId',width:'100'}
            ,{field:'symbol',title: 'CSTPSelect001.finprodName',width:'100'}
            ,{field:'psStr', title: 'CSTPSelect001.ps',sortField:'side',width:'100'}
            ,{field:'marketStr', title: 'CSTPSelect001.market',sortField:'market',width:'100'}
            ,{field:'lastqty', title: 'CSTPSelect001.lastqty',width:'150',type:'number', digit:2}
            ,{field:'price', title: 'CSTPSelect001.price',width:'200',type:'number', digit:8}
            ,{field:'dirtyprice', title: 'CSTPSelect001.dirtyprice',width:'80',type:'number', digit:8}
            ,{field:'underlyingyield2', title: 'CSTPSelect001.underlyingyield2',width:'150',type:'number', digit:12}
            ,{field:'strikeyield', title: '行权收益率%',width:'150',type:'number', digit:12}
            ,{field:'tradeDateStr', title: 'CSTPSelect001.tradedate1',sortField:'tradedate',width:'120'}
            ,{field:'settleSpeedStr', title: '清算速度',sortField:'settleSpeed',width:'100'}
            ,{field:'accruedinterestamt',  title: 'CSTPSelect001.accruedinterestamt',width:'110',type:'number', digit:12}
            ,{field:'accruedinteresttotalamt',  title: 'CSTPSelect001.accruedinteresttotalamt',width:'150',type:'number', digit:2}
            ,{field:'tradecashamt', title: 'CSTPSelect001.tradecashamt',width:'110',type:'number', digit:2}
            ,{field:'cbFpriceAmt', title: '中债本金金额（元）',width:'150',type:'number', digit:2}
            ,{field:'scFpriceAmt', title: '上清本金金额（元）',width:'150',type:'number', digit:2}
            ,{field:'settlcurramt2', title: 'CSTPSelect001.settlcurramt2',width:'150',type:'number', digit:2}
            ,{field:'tradelimitdays', title: 'CSTPSelect001.tradelimitdays',width:'110'}
            ,{field:'cashholdingdays', title: 'CSTPSelect001.cashholdingdays',width:'150'}
            ,{field:'lastqtyready', title: '已匹配额度',width:'150',type:'number', digit:2}
            ,{field:'reference', title: '报价编号',width:'150'}
            ,{field:'ourdepositaccountname', title: '证券账户',sortField:'ourdepositaccountnumber',width:'100'}
            ,{field:'ourTradeName', title: 'CSTPSelect001.ourTradeName',width:'100'}
            ,{field:'market', visible: false}
            ,{field:'prinamt', visible: false}
            ,{field:'repoamt', visible: false}
            // , {
            //     field: 'market', width:100, sortField:'market', title: '匹配明细',
            //     render: function (data, type, row, meta) {
            //         return "<a class='portfolioId-link' style='cursor: pointer;'>匹配明细</a>";
            //     }
            // }
        ]
    });

    /**
     * 点击组合代码链接跳转到该组合信息查看页面
     */
    table.bind('click', '.portfolioId-link', function (row) {
        var param = {};
        if(row.execid){
            if(row.businesstype=='BOND'){
                param.fTradeId = row.execid;
                Joyin.dialog("CSTP下行交易债券匹配明细",
                    JoyinUtil.getRealPath("invest/trade/bond/TrdBondInvestTrade001/init"),
                    "99%", "99%", param/*,
                    function(){table.reload();}*/);
            }else{
                param.fTradeId = row.execid;
                Joyin.dialog("CSTP下行交易质押劵匹配明细",
                    JoyinUtil.getRealPath("invest/trade/trdrepotrade/TrdRepoTrade001/init"),
                    "99%", "99%", param
                    /*, function(){table.reload();}*/
                );
            }
        }

    });
    var EntrustTable = $("#EntrustDataTable").joyintable(
        {
            ajax: {
                url: 'selectForPageFromCstp',
                type: 'POST'
            },
            dblclick: function (table, data) {
                var entrustId = data.entrustId;
                //指令的
                if(data.tradeType=='02'){
                    //债券指令详情
                    if (data.finprodType2 == PINAN_ASSET_TYPE_F01) {
                        label = 'EtrInvestBondInfo001.detail';
                        pagePath = "invest/entrust/etrinvestbondinfo/EtrInvestBondInfo002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                            });//基金指令详情
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F02) {
                        label = 'EtrFund002.detail';
                        pagePath = "invest/entrust/etrfund/EtrFund002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                            });//计划类利率指令详情
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F03) {
                        label = 'EtrPlanAssetR002.detail';
                        pagePath = "invest/entrust/etrplanassetr/EtrPlanAssetR002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                            });//计划类净值指令详情
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F04) {
                        label = 'EtrPlanAssetN002.detail';
                        pagePath = "invest/entrust/etrplanassetn/EtrPlanAssetN002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                            });//存款指令详情
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F05) {
                        label = 'EtrBrowDepoInfo002.detail';
                        pagePath = "invest/entrust/etrbrowdepoinfo/EtrBrowDepoInfo002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                            });//项目类指令详情
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F06) {
                        label = 'EtrProject002.detail';
                        pagePath = "invest/entrust/etrproject/EtrProject002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                            });//回购指令详情
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F07) {
                        label = 'EtrRepo002.detail';
                        pagePath = "invest/entrust/etrrepo/EtrRepo002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                            });//拆借查看页面
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F08) {
                        label = 'EtrLendSpecInfo002.dblclick';
                        pagePath = "invest/entrust/etrlendspecinfo/EtrLendSpecInfo002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId
                            });
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F09) {
                        label = 'EtrPlanAssetC002.refence';
                        pagePath = "invest/entrust/etrplanassetc/EtrPlanAssetC002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId
                            });//计划类货币指令
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F10) {
                        label = 'EtrBoutRepo002.refrence';
                        pagePath = "invest/entrust/etrboutrepo/EtrBoutRepo002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId
                            });// 买断式回购指令
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F11) {
                        label = 'EtrStock002.refence';
                        pagePath = "invest/entrust/etrstock/EtrStock002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label),
                            basePath + pagePath, "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId
                            });// 股票交易指令
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F13) {
                        label = 'EtrWtSpotex.dblclick';
                        pagePath = "invest/entrust/etrwtspotex/EtrWtSpotex002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
                            "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId
                            });//外汇即期指令
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F14) {
                        label = 'EtrForwex002.refrence';
                        pagePath = "invest/entrust/etrforwex/EtrForwex002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
                            "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId
                            });//外汇远期指令
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F15) {
                        label = 'EtrWtFxswap002.refrence';
                        pagePath = "invest/entrust/etrwtfxswap/EtrWtFxswap002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
                            "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId
                            });//外汇掉期指令
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F12) {
                        label = 'EtrSecLend.refrence';
                        pagePath = "invest/entrust/etrseclend/EtrSecLend002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
                            "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                                targetFinprodId: data.finprodId,
                                portfolioId: data.portfolioId
                            });//债券借贷指令查看
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F16) {
                        label = 'EtrIRS.dblclick';
                        pagePath = "invest/entrust/etrirs/EtrIRS002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
                            "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId,
                                targetFinprodId: data.finprodId,
                                portfolioId: data.portfolioId
                            });//add by gejiaao start  一级投标债查看页面
                    } else if (data.finprodType2 == PINAN_ASSET_TYPE_F17) {
                        label = "EtrBondFirstLBidding002.dblclick";
                        pagePath = "invest/entrust/etrbondfirstlbidding/EtrBondFirstLBidding002/init";
                        Joyin.dialog(JoyinUtil.getLabel(label), basePath + pagePath,
                            "95%", "90%", {
                                pageModel: PAGE_MODEL_REFERENCE,
                                entrustId: entrustId
                            });
                    }//add by gejiaao end
                }else{//预录入的

                }
            },
//        height:'300px',
            cols: [
                {
                    field: 'otherEntrustStatus',
                    title: 'TrdCommandReceive001.entrustStatus',
                    width: 100,
                    sortField: 'entrustStatus',
                    fixed:'left'
                },
                {
                    field: 'dealPercent',
                    title: 'TrdCommandReceive001.dealPercent',
                    width: 100,
                    fixed:'left',
                    render: function (data, type, row, meta) {
                        if(row.tradeType=='02'){
                            return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"
                                + (row.dealPercent)
                                + "%'><span class='layui-progress-text'>"
                                + (row.dealPercent)
                                + "%</span></div></div>";
                        }else{
                            return '';
                        }
                    },
                    width: 100,
                    sortField: 'percent'
                },
                {
                    field: 'tradeTypeName',
                    title: '交易类型',
                    width: 100,
                    sortField: 'tradeType'
                },
                {
                    field: 'createTimeValStr',
                    title: 'TrdCommandReceive001.createTime',
                    width: 150,
                    type: 'date',
                    sortField: 'createTimeVal'
                },
                {
                    field: 'portfolioId',
                    title: 'TrdCommandReceive001.portfolioId',
                    render: function (data, type, row, meta) {
                        return '<a onclick="showPtlManagement(\''
                            + row.portfolioId
                            + '\')" class="pro-link"  style="cursor: pointer;">'
                            + row.portfolioId + '</a>';
                    },
                    width: 130
                },
                {
                    field: 'portfolioName',
                    title: 'TrdCommandReceive001.portfolioName',
                    width: 150
                },
                {
                    field: 'otherFinprodType2',
                    title: 'TrdCommandReceive001.finprodType2',
                    width: 100,
                    sortField: 'finprodType2'
                },
                {
                    field: 'finprodMarketId',
                    title: 'TrdCommandReceive001.finprodMarketId',
                    render: function (data, type, row, meta) {
                        if (row.finprodMarketId != undefined) {
                            '"+A+"', '"+B+"'
                            // return '<a
                            // onclick="showDetailByFinprodId(\''
                            // + row.finprodId
                            // + '\')" class="pro-link"
                            // style="cursor:
                            // pointer;">'+row.finprodMarketId+'</a>';
                            return "<a onclick=\"showDetailByFinprodId('"
                                + row.finprodId
                                + "','"
                                + row.finprodType2
                                + "','"
                                + row.portfolioId
                                + "','"
                                + row.entrustId
                                + "','"
                                + row.tradeId
                                + "')\" + style='cursor: pointer;'>"
                                + row.finprodMarketId
                                + "</a>";
                        }
                    },
                    width: 150
                },
                {
                    field: 'finprodAbbr',
                    title: 'TrdCommandReceive001.finprodAbbr',
                    width: 160
                },
                {
                    field: 'otherPs',
                    title: 'TrdCommandReceive001.ps',
                    width: 100,
                    sortField: 'ps'
                },
                {
                    field: 'otherTradeMarket',
                    title: 'TrdCommandReceive001.tradeMarket',
                    width: 130,
                    sortField: 'tradeMarket'
                },
                {
                    field: 'settleSpeedStr',
                    title: '清算速度',
                    width: 130,
                    sortField: 'settleSpeed'
                },
                {field:'repoTerm', title: 'TrdCommandReceive001.repoTerm',width:'110'},
                {
                    field: 'entrustAmt',
                    title: 'TrdCommandReceive001.entrustAmt',
                    type: 'number',
                    width: 140
                },
                {
                    field: 'prinAmt',
                    title: '预录入额度',
                    type: 'number',
                    width: 140
                },
                {
                    field: 'tradeAmt',
                    title: '劵面总额',
                    type: 'number',
                    width: 140
                },
                {
                    field: 'prinAmt',
                    title: '交易金额',
                    type: 'number',
                    width: 140
                },
                {
                    field: 'proAvailableCash',
                    title: 'TrdCommandReceive001.proAvailableCash',
                    type: 'number',
                    width: 100
                },
                {
                    field: 'finshAmt',
                    title: 'TrdCommandReceive001.finishAmt',
                    type: 'number',
                    width: 140
                },
                {
                    field: 'otherCcy',
                    title: 'TrdCommandReceive001.ccy',
                    width: 120,
                    sortField: 'ccy'
                }, {
                    field: 'trader',
                    title: 'TrdCommandReceive001.trader',
                    width: 100
                }, {
                    field: 'busiCode',
                    title: '业务编号',
                    width: 100
                }, {
                    field: 'remark',
                    title: 'TrdCommandReceive001.remark',
                    width: 150
                }, {
                    field: 'entrustId',
                    visible: false
                }, {
                    field: 'finprodId',
                    visible: false
                }, {
                    field: 'days',
                    visible: false
                }, {
                    field: 'entrustStatus',
                    visible: false
                }, {
                    field: 'finprodType2',
                    visible: false
                }, {
                    field: 'tradeId',
                    visible: false
                }, {
                    field: 'ps',
                    visible: false
                },{
                    field:'chlId',
                    visible: false
                },{
                    field:'secAcctId',
                    visible: false
                },{
                    field:'termType',
                    visible: false
                },{
                    field:'assetType1',
                    visible: false
                },
                {  field:'SecAcctId',
                    visible: false}
                , {  field:'price',
                    visible: false}
                , {  field:'matureYield',
                    visible: false}
                , {  field:'priceMode',
                    visible: false}
                ,{  field:'exerYield',
                    visible: false}
                // ,{
                //     field: 'SecAcctId', width:100, sortField:'SecAcctId', title: '交易明细',
                //     render: function (data, type, row, meta) {
                //         return "<a class='portfolioId-link' style='cursor: pointer;'>交易明细</a>";
                //     }
                // }
            ],
            done: function (tables, allData) {
                $.each(tables, function (index, obj) {
                    //[相隔天数]在3天之内且[交易完成度]<100%，则列表行用红色标识
                    if (tables[index].days <= 3
                        && tables[index].percent < 1) {
                        // table.$element.next().find(".layui-table-body
                        // table.layui-table
                        // tr[data-index='"+index+"']").css({'background-color':'#FF5722','color':'white'})
                    }

                });

            }
        });

    EntrustTable.bind('click', '.portfolioId-link', function (row) {
        var param = {};
        if(row.finprodType2){
            if(row.finprodType2=='F01'){
                param.entrustId = row.entrustId;
                Joyin.dialog("债券指令匹配交易明细",
                    JoyinUtil.getRealPath("invest/trade/bond/TrdBondInvestTrade001/init"),
                    "99%", "99%", param/*,
                    function(){table.reload();}*/);
            }else{
                param.entrustId = row.entrustId;
                Joyin.dialog("质押式回购指令匹配交易明细",
                    JoyinUtil.getRealPath("invest/trade/trdrepotrade/TrdRepoTrade001/init"),
                    "99%", "99%", param
                    /*, function(){table.reload();}*/
                );
            }
        }

    });

    //下行交易确认table
    var TrdMatchInfo = $("#cstpTrdMatchInfo").joyintable(
        {
            ajax: {
                url: '/invest/trade/cstp/CSTPTrdMatchInfo/findPage',
                type: 'POST',
                data: function () {
                    return getEntrustPageParams();
                }
            },
            cols: [
                {
                    field: 'matchTypeStr',
                    title: '匹配方式',
                    width: 100,
                    sortField: 'matchType'
                },
                {
                    field: 'entrustId',
                    title: '指令编号',
                    width: 150,
                    render: function (data, type, row, meta) {
                    	if (row.entrustId) {
                            return '<a onclick="showEntrust(\''
                            + row.entrustId
                            + "','"
                            + row.finprodTypeStr
                            + '\')" class="pro-link"  style="cursor: pointer;">'
                            + row.entrustId + '</a>';
                    	}
                    },
                    sortField: 'entrustId'
                },
                {
                    field: 'invManagerId',
                    title: '投资经理',
                    width: 150,
                    sortField: 'invManagerId'
                },
                {
                    field: 'preTradeId',
                    title: '预录入交易编号',
                    width: 150,
                    render: function (data, type, row, meta) {
                    	if (row.preTradeId) {
                            return '<a onclick="showDealPre(\''
                            + row.totleTradeId
                            + "','"
                            + row.finprodTypeStr
                            + '\')" class="pro-link"  style="cursor: pointer;">'
                            + row.preTradeId + '</a>';
                    	}
                    },
                    sortField: 'preTradeId'	
                },
                {
                    field: 'portfolioId',
                    title: 'TrdCommandReceive001.portfolioId',
                    render: function (data, type, row, meta) {
                        return '<a onclick="showPtlManagement(\''
                            + row.portfolioId
                            + '\')" class="pro-link"  style="cursor: pointer;">'
                            + row.portfolioId + '</a>';
                    },
                    width: 130
                },
                {
                    field: 'portfolioName',
                    title: '组合名称',
                    width: 150,
                    sortField: 'portfolioName'
                },
                {
                    field: 'cstpTradeId',
                    title: '成交编号',
                    width: 150,
                    sortField: 'cstpTradeId'
                },
                {
                    field: 'finprodTypeStr',
                    title: '资产类型',
                    width: 150,
                    sortField: 'finprodType'
                },
                {
                    field: 'finprodMarketId',
                    title: '资产代码',
                    width: 150,
                    sortField: 'finprodMarketId'
                },
                {
                    field: 'finprodAbbr',
                    title: '资产名称',
                    width: 150,
                    sortField: 'finprodAbbr'
                },
                {
                    field: 'otherPs',
                    title: '交易方向',
                    width: 100,
                    sortField: 'otherPs'
                },
                {
                    field: 'psStr',
                    title: '交易方向',
                    visible: false,
                    sortField: 'ps'
                },
                {
                    field: 'invAimStr',
                    title: '投资目的',
                    width: 160
                },
                {
                    field: 'prinAmt',
                    title: '券面总额',
                    width: 100,
                    type: 'number',
                    digit:2,
                    sortField: 'prinAmt'

                },
                {
                    field: 'unitCprice',
                    title: '回购利率%/净价',
                    width: 130,
                    type: 'number',
                    digit:8,
                    sortField: 'unitCprice'
                },
                {
                    field: 'fpriceAmt',
                    title: '全价',
                    type: 'number',
                    digit:8,
                    width: 140
                },
                {
                    field: 'matureYield',
                    title: '到期收益率%',
                    type: 'number',
                    digit:12,
                    width: 100
                },
                {
                    field: 'exerYield',
                    title: '行权收益率',
                    type: 'number',
                    digit:12,
                    width: 140
                },
              {
                    field: 'tradeDate',
                    title: '成交日期',
                    width: 120,
                    type: 'date',
                    sortField: 'tradeDate'
                }, {
                    field: 'settleSpreedStr',
                    title: '清算速度',
                    width: 100
                }, {
                    field: 'unitInt',
                    title: '应计利息',
                    type:'number',
                    digit:12,
                    width: 150
                },  {
                    field: 'intAmt',
                    title: '应计利息总额',
                    type:'number',
                    digit:2,
                    width: 150
                },
                // {
                //     field: 'tradeAmt',
                //     title: '交易金额',
                //     type:'number',
                //     digit:2,
                //     width: 150
                // },
                {
                    field: 'secAcctName',
                    title: '证券账户',
                    width: 150
                },
                 {
                     field: 'tradeAmt',
                     title: '交易金额',
                     type:'number',
                     digit:2,
                     width: 150
                 },
                 {
                     field: 'mdateSettleAmt',
                     title: '到期结算金额',
                     type:'number',
                     digit:2,
                     width: 150
                 },
                {
                    field: 'repoTerm',
                    title: '回购期限',
                    width: 110
                },
                {
                    field: 'actCapDays',
                    title: '实际占款天数',
                    width: 110
                }
                ,{  field:'secAcctId', visible: false}
                ,{  field:'seqno', visible: false}
                ,{  field:'tradePlat', visible: false}
                ,{  field:'chlId', visible: false}
                ,{  field:'termType', visible: false}
                ,{  field:'assetType1', visible: false}
                ,{  field:'priceMode', visible: false}
                ,{  field:'totleTradeId', visible: false}
            ],
            done: function (tables, allData) {
                $.each(tables, function (index, obj) {
                    //[相隔天数]在3天之内且[交易完成度]<100%，则列表行用红色标识
                    if (tables[index].days <= 3
                        && tables[index].percent < 1) {
                        // table.$element.next().find(".layui-table-body
                        // table.layui-table
                        // tr[data-index='"+index+"']").css({'background-color':'#FF5722','color':'white'})
                    }

                });

            },
            click: function (table, data) {
                entrustId = data.entrustId;
                $('#detailTable_Div').attr("hidden", false);
//                detailTable ? detailTable.reload() : detailTable
//                    .load();
                // detailTable.table ? detailTable.reload() :
                // detailTable.load();
//        	showEtrInvest(data.entrustId);
                // table.$element.next().find(".layui-table-content
                // ").css({height:'200px'})
//                detailTable.$element.next().find(
//                    ".layui-tab-content").css({
//                    height: '200px'
//                });
            }
        });
    /**
     * 导出事件
     */
    var exportExcel = function() {
        var paramObj = getPageParams();
        Joyin.exportExcel('export', paramObj);
    };
    var exportExcelEntrust = function (type) {
        var paramObj = getEntrustPageParams(type);
         paramObj.tab = 0;
        Joyin.exportExcel('exportEntrust', paramObj);
    };

    var exportExcelQueDing = function() {
        var paramObj = getEntrustPageParams();
        Joyin.exportExcel('exportExcelQueDing', paramObj);
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        //导出
        $('#CSTP_btn_export').click(function() {
            exportExcel();
        });
        $('#Entrust_btn_enExport').click(function() {
            var data = table.getAllCheckedRows();
            if(data[0]){
                exportExcelEntrust('cstp');
            }else{
                exportExcelEntrust();
            }
        });
        $('#CSTPSelect001_btn_export').click(function() {
        	exportExcelQueDing();
        });
        //查询
        $('#CSTPSelect001_btn_search').click(function(){
            table.options.ajax.data=function(){
                return getPageParams('');
            }
            table.load();
            $('#priceAmtAll').val(0);
        });
        //查询
        $('#Entrust_btn_enSearch').click(function(){
            EntrustTable.options.ajax.data =null;
            EntrustTable.options.ajax.data = getEntrustPageParams('');
            EntrustTable.load();
            $('#TrdCommandReceive001_priceAmtAll').val(0);
        });
        $('#trdMatchInfo #CSTPSelect001_btn_search').click(function(){
            TrdMatchInfo.options.ajax.data=function(){
                return TrdMatchInfoPageParams();
            }
            TrdMatchInfo.load();
        });
        $('#CSTPSelect001_btn_canPiPei').click(function(){
            if(table.getAllCheckedRows().length==0||table.getAllCheckedRows().length>1){
                Joyin.error(JoyinUtil
                    .getMessage("请选择一条下行数据"));
                return;
            }
            EntrustTable.options.ajax.data=getEntrustPageParams('cstp');
            EntrustTable.load();
        });
        $('#Entrust_btn_canPiPei').click(function(){
            if(EntrustTable.getAllCheckedRows().length==0||EntrustTable.getAllCheckedRows().length>1){
                Joyin.error(JoyinUtil
                    .getMessage("请选择一条指令数据"));
                return;
            }
            table.options.ajax.data=function(){
                return getPageParams('entrust');
            }
            table.load();
        });
        $('#CSTPSelect001_cleanProdGroup').click(function () {
            $('#finprodType').val('').trigger('change');
            $('#finprodId').val('').trigger('change');
            $('#ps').val('').trigger('change');
             $('#tradeDate').val('').trigger('change');
            $('#tradeDate').val('').trigger('change');
            $('#execid').val('').trigger('change');
            $('#matchStatus').val('').trigger('change');
            $('#tradeMarket').val('').trigger('change');
            $('#repoTerm').val('').trigger('change');
            $('#repoTerm2').val('').trigger('change');
            $('#priceAmt').val('').trigger('change');
            $('#priceAmt2').val('').trigger('change');
            table.options.ajax.data=getPageParams('');
            table.load();
        });

        $('#Entrust_cleanProdGroup').click(function () {
            $('#TrdCommandReceive001_portfolioId').val('').trigger('change');
            $('#TrdCommandReceive001_matchType').val('').trigger('change');
            $('#TrdCommandReceive001_finprodType2').val('').trigger('change');
            $('#TrdCommandReceive001_finprodMarketId').val('').trigger('change');
            $('#TrdCommandReceive001_tradeMarket').val('').trigger('change');
            $('#TrdCommandReceive001_ps').val('').trigger('change');
            $('#TrdCommandReceive001_createTime').val('').trigger('change');
            $('#TrdCommandReceive001_repoTerm').val('').trigger('change');
            $('#TrdCommandReceive001_repoTerm2').val('').trigger('change');
            $('#TrdCommandReceive001_priceAmt').val('').trigger('change');
            $('#TrdCommandReceive001_priceAmt2').val('').trigger('change');
            EntrustTable.options.ajax.data=getEntrustPageParams('');
            EntrustTable.load();
        });
        function isAllEqual(array) {
            if (array.length > 0) {
                return !array.some(function (value, index) {
                    return value !== array[0];
                });
            } else {
                return true;
            }
        }

        function getSecAcctId(secAcctId,portfolioId,tradeMarket,tableCstp){
            if(!secAcctId){
                var secAcctIdFrom;
                var isTrue = false;
                var params  ={};
                params.portfolioId = portfolioId;
                params.tradeMarket = tradeMarket;
                Joyin.ajaxSync('/invest/trade/bond/TrdBondInvestTrade002/getSecManageAcctId',params,function (data) {
                    if(data){
                        secAcctIdFrom = data;
                    }
                })
                if(secAcctIdFrom&&secAcctIdFrom.length>0){
                    $.each(secAcctIdFrom,function (index,value) {
                        if(tableCstp.ourdepositaccountnumber==value.id){
                            isTrue = true;
                            return false;
                        }
                    })
                }
                if(!isTrue){
                    return "BUSS_CSTP_006";
                }
            }else{
                if(secAcctId!=tableCstp.ourdepositaccountnumber){//证券账户不一致
                    return "BUSS_CSTP_006";
                }
            }
        }


        function priceAndmatureYield(priceMode,tableCstp,ps,type,value){
            if(type=='price'){//净价
                if(priceMode&&priceMode=='PRM01'){//指定价格的时候
                    if(value&&tableCstp[0].price){
                        if(tableCstp[0].price&&parseFloat(JoyinMath.doAdd(tableCstp[0].price,0,2))!=parseFloat(JoyinMath.doAdd(value,0,2))){//净价不一致
                            return "BUSS_CSTP_011";
                        }
                    }else{
                        if(!value&&tableCstp[0].price){
                            return "BUSS_CSTP_011";
                        }else if(value&&!tableCstp[0].price){
                            return "BUSS_CSTP_011";
                        }
                    }
                }else if(priceMode&&priceMode=='PRM02'){//限价指令
                    if(ps=='T01'){//买入
                        if(tableCstp[0].price&&value){
                            if(tableCstp[0].price&&parseFloat(JoyinMath.doAdd(tableCstp[0].price,0,2))>parseFloat(JoyinMath.doAdd(value,0,2))){
                                return "BUSS_CSTP_011";
                            }
                        }else{
                            if(!tableCstp[0].price&&value){
                                return "BUSS_CSTP_011";
                            }else if(tableCstp[0].price&&!value){
                                return "BUSS_CSTP_011";
                            }
                        }

                    }else{
                        if(tableCstp[0].price&&value) {
                            if (tableCstp[0].price && parseFloat(JoyinMath.doAdd(tableCstp[0].price,0,2)) < parseFloat(JoyinMath.doAdd(value,0,2))) {
                                return "BUSS_CSTP_011";
                            }
                        }else{
                            if(!tableCstp[0].price&&value){
                                return "BUSS_CSTP_011";
                            }else if(tableCstp[0].price&&!value){
                                return "BUSS_CSTP_011";
                            }
                        }
                    }
                }
            }else if(type=='matureYield'){//到期收益率
                if(priceMode&&priceMode=='PRM01'){//指定价格的时候
                    if(tableCstp[0].underlyingyield2&&value){
                        if(tableCstp[0].underlyingyield2&&parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2))!=parseFloat(JoyinMath.doAdd(tableCstp[0].underlyingyield2,0,2))){//到期收益率不一致
                            return  "BUSS_CSTP_012";
                        }
                    }else {
                        if(!tableCstp[0].underlyingyield2&&value){
                            return  "BUSS_CSTP_012";
                        }else  if(tableCstp[0].underlyingyield2&&!value){
                            return  "BUSS_CSTP_012";
                        }
                    }

                }else if(priceMode&&priceMode=='PRM02') {//限价指令
                    if(ps=='T01'){//买入
                        if(tableCstp[0].underlyingyield2&&value) {
                            if (tableCstp[0].underlyingyield2 && parseFloat(JoyinMath.doAdd(tableCstp[0].underlyingyield2,0,2)) <parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2))) {
                                return "BUSS_CSTP_012";
                            }
                        }else {
                            if(!tableCstp[0].underlyingyield2&&value){
                                return  "BUSS_CSTP_012";
                            }else  if(tableCstp[0].underlyingyield2&&!value){
                                return  "BUSS_CSTP_012";
                            }
                        }
                    }else{
                        if(tableCstp[0].underlyingyield2&&value) {
                            if (tableCstp[0].underlyingyield2 &&parseFloat(JoyinMath.doAdd(tableCstp[0].underlyingyield2,0,2)) > parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2))) {
                                return "BUSS_CSTP_012";
                            }
                        }else {
                            if(!tableCstp[0].underlyingyield2&&value){
                                return  "BUSS_CSTP_012";
                            }else  if(tableCstp[0].underlyingyield2&&!value){
                                return  "BUSS_CSTP_012";
                            }
                        }
                    }
                }
            }else if(type=='exerYield'){//行权收益率
                if(priceMode&&priceMode=='PRM01'){//指定价格的时候
                    if(tableCstp[0].strikeyield&&value) {
                        if (tableCstp[0].strikeyield && parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2)) !=parseFloat(JoyinMath.doAdd(tableCstp[0].strikeyield,0,2))) {//净价不一致
                            return "BUSS_CSTP_013";
                        }
                    }else{
                        if(!tableCstp[0].strikeyield&&value){
                            return  "BUSS_CSTP_013";
                        }else  if(tableCstp[0].strikeyield&&!value){
                            return  "BUSS_CSTP_013";
                        }
                    }
                }else if(priceMode&&priceMode=='PRM02'){//限价指令
                    if(ps=='T01'){//买入
                        if(tableCstp[0].strikeyield&&value) {
                            if (tableCstp[0].strikeyield && parseFloat(JoyinMath.doAdd(tableCstp[0].strikeyield,0,2)) <parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2))) {
                                return "BUSS_CSTP_013";
                            }
                        }else{
                            if(!tableCstp[0].strikeyield&&value){
                                return  "BUSS_CSTP_013";
                            }else  if(tableCstp[0].strikeyield&&!value){
                                return  "BUSS_CSTP_013";
                            }
                        }
                    }else{
                        if(tableCstp[0].strikeyield&&value) {
                            if (tableCstp[0].strikeyield &&  parseFloat(JoyinMath.doAdd(tableCstp[0].strikeyield,0,2)) > parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2))) {
                                return "BUSS_CSTP_013";
                            }
                        }else{
                            if(!tableCstp[0].strikeyield&&value){
                                return  "BUSS_CSTP_013";
                            }else  if(tableCstp[0].strikeyield&&!value){
                                return  "BUSS_CSTP_013";
                            }
                        }
                    }
                }
            }else if(type=='zysPrice'){//质押式的回购利率
                if(priceMode&&priceMode=='PRM01'){//指定价格的时候
                    if(tableCstp[0].price&&value) {
                        if (tableCstp[0].price && parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2)) != parseFloat(JoyinMath.doAdd(tableCstp[0].price,0,2))) {//回购利率
                            return "BUSS_CSTP_014";
                        }
                    }else{
                        if(!value&&tableCstp[0].price){
                            return "BUSS_CSTP_014";
                        }else if(value&&!tableCstp[0].price){
                            return "BUSS_CSTP_014";
                        }
                    }
                }else if(priceMode&&priceMode=='PRM02') {//限价指令
                    if(ps=='03'){//正回购
                        if(tableCstp[0].price&&value) {
                            if (tableCstp[0].price && parseFloat(JoyinMath.doAdd(tableCstp[0].price,0,2)) > parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2))) {
                                return "BUSS_CSTP_014";
                            }
                        }else{
                            if(!value&&tableCstp[0].price){
                                return "BUSS_CSTP_014";
                            }else if(value&&!tableCstp[0].price){
                                return "BUSS_CSTP_014";
                            }
                        }
                    }else{
                        if(tableCstp[0].price&&value) {
                            if (tableCstp[0].price && parseFloat(JoyinMath.doAdd(tableCstp[0].price,0,2)) < parseFloat(JoyinMath.doAdd(JoyinMath.doMul(value,100),0,2))) {
                                return "BUSS_CSTP_014";
                            }
                        }else{
                            if(!value&&tableCstp[0].price){
                                return "BUSS_CSTP_014";
                            }else if(value&&!tableCstp[0].price){
                                return "BUSS_CSTP_014";
                            }
                        }
                    }
                }
            }

        }

        function isCanJIaoYi(tableData,tableCst){
            var tableCstp = [];
            //判断是否为对象
            if($.isPlainObject(tableCst)){
                tableCstp.push(tableCst);
            }
            //判断是否为数组
            if($.isArray(tableCst)){
                tableCstp = tableCst;
            }
            if(tableData.finprodType2==PINAN_ASSET_TYPE_F01){//债券
                if(tableCstp[0].businesstype!='BOND'){//资产类型不一致
                    return "BUSS_CSTP_004";
                }
                if(tableData.finprodMarketId!=tableCstp[0].securityid){//资产代码不一致
                    return "BUSS_CSTP_007";
                }
                if(tableData.ps!=tableCstp[0].side){//交易方向不一致
                    return "BUSS_CSTP_005";
                }
                var error = getSecAcctId(tableData.secAcctId,tableData.portfolioId,tableData.tradeMarket,tableCstp[0]);
                if(error){
                    return error;
                }
                if(tableCstp[0].settleSpeed&&Number(tableData.settleSpeed)!=tableCstp[0].settleSpeed){//清算速度不一致
                    return "BUSS_CSTP_009";
                }
                if(tableCstp[0].userName&&tableData.trader!=tableCstp[0].userName){//交易员不一致
                    return "CSTP_SELECT_001";
                }
                var price =  priceAndmatureYield(tableData.priceMode,tableCstp,tableData.ps,'price',tableData.price);
                if(price){
                    return price;
                }
                var matureYield = priceAndmatureYield(tableData.priceMode,tableCstp,tableData.ps,'matureYield',tableData.matureYield);
                if(matureYield){
                    return matureYield;
                }
                var exerYield = priceAndmatureYield(tableData.priceMode,tableCstp,tableData.ps,'exerYield',tableData.exerYield);
                if(exerYield){
                    return exerYield;
                }
            }else if(tableData.finprodType2==PINAN_ASSET_TYPE_F07){//回购式交易
                if(tableCstp[0].businesstype!='REPO'){//资产类型不一致
                    return "BUSS_CSTP_004";
                }
                // if(tableData.finprodMarketId!=tableCstp[0].securityid){//资产代码不一致
                //     return Joyin.error(JoyinUtil.getMessage("BUSS_CSTP_007"));
                // }
                if(tableData.ps!=tableCstp[0].side){//交易方向不一致
                    return "BUSS_CSTP_005";
                }
                if(tableCstp[0].side=='03'){//正回购  校验持仓
                	if(tableCstp[0].quoteId&&tableData.quoteId&&tableData.quoteId.indexOf(tableCstp[0].quoteId)!=-1){
                		//有报价 且对应委托是此报价的
                		//跳过
                	}else{
                		var positionParam ={}
                		positionParam.execid=tableCstp[0].execid
                		positionParam.portfolioId=tableData.portfolioId
                		var iserror = true;
                		Joyin.ajaxSync('/invest/trade/cstp/CSTPTrdMatchInfo/checkPosition',positionParam,function (data) {
                            if(data&&data.isContainPosition=='Y'){
                            	iserror=false;
                            }
                        })
                        if(iserror){
                        	return "所选业务编号为："+tableData.busiCode+"没有对应下行成交编号为："+tableCstp[0].execid+"所需质押券可用持仓";
                        }
                	}
                }
                var error = getSecAcctId(tableData.secAcctId,tableData.portfolioId,tableData.tradeMarket,tableCstp[0]);
                if(error){
                    return error;
                }
                if(tableData.tradeMarket!=tableCstp[0].market){//交易场所不一致不一致
                    return "下行数据的交易场所和指令的交易场所不一致";
                }
                if(tableCstp[0].settleSpeed&&tableData.settleSpeed!=tableCstp[0].settleSpeed){//清算速度不一致
                    return "BUSS_CSTP_009";
                }
                if(tableCstp[0].userName&&tableData.trader!=tableCstp[0].userName){//交易员不一致
                    return "CSTP_SELECT_001";
                }
                if(tableCstp[0].tradelimitdays&&tableData.repoTerm!=tableCstp[0].tradelimitdays){//回购期限不一致
                    return "BUSS_CSTP_010";
                }
                var price =  priceAndmatureYield(tableData.priceMode,tableCstp,tableData.ps,'zysPrice',tableData.price);//回购利率
                if(price){
                    return price;
                }
            }
            if(tableData.trader&&tableCstp[0].userName){
                if(tableData.trader!=tableCstp[0].userName){
                    return "BUSS_CSTP_050";
                }
            }else if(!tableData.trader&&tableCstp[0].userName){//交易员不一致
                return "BUSS_CSTP_050";
            }else if(tableData.trader&&!tableCstp[0].userName){
                return "BUSS_CSTP_050";
            }
        }
//        function tradeAmtCanJIaoyi(tableCst,entrustAmt,type){
//            var tableCstp = [];
//            //判断是否为对象
//            if($.isPlainObject(tableCst)){
//                tableCstp.push(tableCst);
//            }
//            //判断是否为数组
//            if($.isArray(tableCst)){
//                tableCstp = tableCst;
//            }
//            var path = '';
//            if(tableCstp[0].execid&&entrustAmt){
//                var isTrue = false;
//                var params = {};
//                params.fTradeId = tableCstp[0].execid;
//                if(type==PINAN_ASSET_TYPE_F01){
//                    path = '/invest/trade/bond/TrdBondInvestTrade002/isAllTradeAmtByFTradeId';
//                    if(Number(entrustAmt)>Number(tableCstp[0].prinamt)){
//                        params.tradeAmt = tableCstp[0].prinamt;
//                    }else{
//                        params.tradeAmt =entrustAmt;
//                    }
//                }else if(type==PINAN_ASSET_TYPE_F07){
//                    path = '/invest/trade/trdrepotrade/TrdRepoTrade002/isAllTradeAmtByFTradeId';
//                    if(Number(entrustAmt)>Number(tableCstp[0].repoamt)){
//                        params.tradeAmt = tableCstp[0].repoamt;
//                    }else{
//                        params.tradeAmt =entrustAmt;
//                    }
//                    params.tradeType = 'PLEDGE';
//                    params.ps =tableCstp[0].ps;
//                }
//
//                params.workflowId = null;
//                Joyin.ajaxJson(JoyinUtil.getRealPath(path), params, function (result) {
//                    if(result&&!result.data){
//                        isTrue = true;
//                        // Joyin.error("成交编号获得的劵面总额不得大于已存在的劵面总额！");
//                    }
//                },false);
//                if(isTrue){
//                    return "成交编号获得的劵面总额不得大于已存在的劵面总额！";
//                }
//            }
//        }

        function preTradeAmtJiaoYi(tableCst,tableData,type){
            var tableCstp = [];
            //判断是否为对象
            if($.isPlainObject(tableCst)){
                tableCstp.push(tableCst);
            }
            //判断是否为数组
            if($.isArray(tableCst)){
                tableCstp = tableCst;
            }
            var path = '';
            if(tableCstp[0].execid&&tableData[0].entrustId){
                var isTrue = false;
                var params = {};
                params.tradeId = tableCstp[0].execid;
                params.preTradeId = tableData[0].entrustId;
                if(type==PINAN_ASSET_TYPE_F01){
                    path = '/invest/trade/bond/TrdBondInvestTrade002/isAllTradeAmtByPreTradeId';
                }else if(type==PINAN_ASSET_TYPE_F07){
                    path = '/invest/trade/trdrepotrade/TrdRepoTrade002/isAllTradeAmtByPreTradeId';
                }
                Joyin.ajaxJson(JoyinUtil.getRealPath(path), params, function (result) {
                    if(result&&result.data){
                        isTrue = true;
                        // Joyin.error("成交编号获得的劵面总额不得大于已存在的劵面总额！");
                    }
                },false);
                if(isTrue){
                    if(type==PINAN_ASSET_TYPE_F01){
                        return "预录入的劵面总额和下行数据的劵面总额不一致！";
                    }else if(type==PINAN_ASSET_TYPE_F07){
                        return "预录入的金额和下行数据的交易金额不一致！";
                    }
                }
            }
        }

        function saveTrdMatchInfo(tableData,tableCstp){
//              var object = TrdMatchInfoSave(tableData, tableCstp);
        	var object ={}
        	object.cstpSelectDtoList=tableCstp;
        	object.trdCommandReceive001DtoList =tableData;
            Joyin.confirm('请确认是否进行交易匹配？',function () {
                Joyin.ajaxJson(JoyinUtil.getRealPath('invest/trade/cstp/CSTPTrdMatchInfo/saveTrdMatchInfoByCstp'),object,function (result) {
                    table.table?table.reload():table.load();
                    EntrustTable.table?EntrustTable.reload():EntrustTable.load();
                },false);
            })
        }

        function deleteTrdMatchInfo(tableData){
            var object = TrdMatchInfoDelete(tableData);
            Joyin.confirm('请确认是否取消交易匹配？',function () {
                Joyin.ajaxJson(JoyinUtil.getRealPath('invest/trade/cstp/CSTPTrdMatchInfo/deleteTrdMatchInfoByCstp'),object,function (result) {
                    if(result.message){//失败
                        Joyin.error('取消交易匹配失败');
                    }else{
                        TrdMatchInfo.table?TrdMatchInfo.reload():TrdMatchInfo.load();
                    }
                },false);
            })
        }

        function extractedDuozuhe(rows, param) {
            var entrustIds = '';
            var preTradeIds = '';
            var portfolioId = '';
            for (var i = 0; i < rows.length; i++) {
                if (i == rows.length - 1) {
                    if (rows[i].entrustId) {
                        entrustIds = entrustIds + rows[i].entrustId;
                    } else if (rows[i].preTradeId) {
                        preTradeIds = preTradeIds + rows[i].preTradeId;
                    }
                    portfolioId = portfolioId + rows[i].portfolioId;
                } else {
                    if (rows[i].entrustId) {
                        entrustIds = entrustIds + rows[i].entrustId + ',';
                    } else if (rows[i].preTradeId) {
                        preTradeIds = preTradeIds + rows[i].preTradeId + ',';
                    }
                    portfolioId = portfolioId + rows[i].portfolioId + ",";
                }
            }
            param.entrustId = entrustIds;
            param.preTradeId = preTradeIds;
            param.portfolioId = portfolioId;
            param.ps = rows[0].ps;
        }

        function OpenTrade(rows) {
            var param = {
                entrustId: rows[0].entrustId,
                preTradeId: rows[0].preTradeId,
                fTradeId: rows[0].cstpTradeId,
                isCstp: 'true',
                isCstpSure: 'true',
                isSubmit:'true'
            };
            if (param.entrustId == undefined) {
            	param.entrustId = '';
            }
            if (param.preTradeId == undefined) {
            	param.preTradeId = '';
            }
            if (param.cstpTradeId == undefined) {
            	param.cstpTradeId = '';
            }
            //转到相应的投资交易录入界面
            var pagePath = "";
            var label = '';
            var finprodType2 = rows[0].finprodType;
            // 根据资产类型判断对应投资交易画面url
            if (finprodType2 == PINAN_ASSET_TYPE_F01 && rows.length == 1) {//债券
                label = 'TrdBondInvestTrade002';
                pagePath = "invest/trade/bond/TrdBondInvestTrade002/init";
            } else if (finprodType2 == PINAN_ASSET_TYPE_F07 && rows.length == 1) {//质押式回购
                label = 'TrdRepoTrade002';
                pagePath = "invest/trade/trdrepotrade/TrdRepoTrade002/init";
                param.portfolioId = rows[0].portfolioId;
                param.ps = rows[0].ps;
            } else if (finprodType2 == PINAN_ASSET_TYPE_F07 && rows.length > 1) {
                label = 'TrdRepoTrade003';
                pagePath = "invest/trade/trdrepotrade/TrdRepoTrade003/init";
                extractedDuozuhe(rows, param);
            } else if (finprodType2 == PINAN_ASSET_TYPE_F01 && rows.length > 1) {//债券交易
                label = '多组合债券交易新建';
                pagePath = "invest/trade/bond/TrdBondInvestTrade003/init";
                extractedDuozuhe(rows, param);
            } else if (finprodType2 == PINAN_ASSET_TYPE_F10) {//买断式回购
                // label = 'TrdOutRightRepoTrade002.new';
                // pagePath = "invest/trade/buyoutrepo/TrdOutRightRepoTrade002/init";
                label = 'TrdBondInvestTrade002';
                pagePath = "invest/trade/bond/TrdBondInvestTrade002/init";
                param.portfolioId = rows[0].portfolioId;
                param.ps = rows[0].ps;
            }
            pagePath = basePath + pagePath;
            Joyin.dialog(JoyinUtil
                    .getLabel(label),
                pagePath, "99%", "99%",
                param);
        }

        function openEntrustByTrdMatchInfo(rows){
            //多组合模式
            if (rows.length > 1) {
                //key容器
                var keyMap;
                if(rows[0].finprodType ==PINAN_ASSET_TYPE_F01){
                    //对于债券，在资产标的、交易方向、交易场所、交易平台、通道相同时，交易员可勾选多笔合并交易录单，某个字段为空时可认为是与其他相同；
                    keyMap ={
                        //金融产品类型
                        "finprodType":"",
                        //金融产品代码
                        "finprodId":"",
                        //交易方向
                        "ps":"",
                        //交易场所
                        "tradeMarket":"",
                        //    交易平台
                        "tradePlat":"",
                        //通道代码
                        "chlId":"",
                        //证券托管户
                        "secAcctId":""
                    };
                }
                else if(rows[0].finprodType ==PINAN_ASSET_TYPE_F07){
                    // 5.	对于正/逆回购，期限品种、回购类型、交易场所、交易方向相同时，也可以分别勾选多笔合并交易录单；
                    keyMap ={
                        "finprodType2":"",
                        //交易方向
                        "ps":"",
                        // 期限品种
                        "termType":"",
                        //回购类型
                        "assetType1":"",
                        //交易场所
                        "tradeMarket":"",
                        //价格模式
                        "priceModel":"",
                        //证券托管户
                        "secAcctId":""
                    };
                }
                var font="";
                var portifios = [];
                $.each(rows,function (index,value) {
                    portifios.push(value.portfolioId);
                })
                $.each(rows,function(i,v){
                    if(font){
                        return;
                    }
                    $.each(keyMap,function(key,val){
                        if(val){
                            if(v[key] && val != v[key]){
                                font = key;
                                return;
                            }
                        }
                        else{
                            keyMap[key] = v[key];
                        }
                    });
                });
                if(font && font!="finprodType"){
                    //债券
                    if(rows[0].finprodType ==PINAN_ASSET_TYPE_F01){
                        return JoyinUtil.getMessage("TRD_PEP_001");
                    }else if(rows[0].finprodType ==PINAN_ASSET_TYPE_F07){
                        return JoyinUtil.getMessage("TRD_PEP_002");
                    }
                }
                var isSomeType = rows.some(function (value, index) {
                    return value.cstpTradeId !== rows[0].cstpTradeId;
                });
                if(isSomeType){
                    return "请选择成交编号一样的数据进行多组合提交";
                }
                if(isAllEqual(portifios)){
                    return '请选择不同的组合进行交易录单';
                }
            }
        }

        function oneEntrustMoreCstp(tableData,tableCstp,isSome){
            if(tableCstp.length>1&&tableData.length==1){
                //交易类型不一致的话
                var isSomeType = tableCstp.some(function (value, index) {
                    return value.businesstype !== tableCstp[0].businesstype;
                });
                if(isSomeType){
                    return JoyinUtil.getMessage("TRD_PEP_003");
                }
                var keyMap;
                if(tableCstp[0].businesstype =='BOND'){
                    //对于债券，在资产标的、交易方向、交易场所、交易平台、通道相同时，交易员可勾选多笔合并交易录单，某个字段为空时可认为是与其他相同；
                    keyMap ={
                        //金融产品类型
                        "businesstype":"",
                        //金融产品代码
                        "securityid":"",
                        //交易方向
                        "ps":"",
                        //证券托管户
                        "ourdepositaccountnumber":"",
                        "tradeType":""
                    };
                }
                else if(tableCstp[0].businesstype =='REPO'){
                    // 5.	对于正/逆回购，期限品种、回购类型、交易场所、交易方向相同时，也可以分别勾选多笔合并交易录单；
                    keyMap ={
                        "businesstype":"",
                        //交易方向
                        "ps":"",
                        //证券托管户
                        "ourdepositaccountnumber":"",
                        "tradeType":""
                    };
                }
                var font="";
                var errorType ='';
                $.each(tableCstp,function(i,v){
                    var returnValue = isCanJIaoYi(tableData[0],v);
                    errorType = returnValue;
                    if(errorType&&errorType=='BUSS_CSTP_050'){
                        isSome.isSomeJiaoYiYuan = false;
                    }
                    if(errorType&&errorType=='BUSS_CSTP_011'){
                        isSome.isprice = false;
                    }
                    if(errorType&&errorType=='BUSS_CSTP_012'){
                        isSome.isunderlyingyield2 = false;
                    }
                    if(errorType&&errorType=='BUSS_CSTP_013'){
                        isSome.isstrikeyield = false;
                    }
                    if(errorType&&errorType=='BUSS_CSTP_014'){
                        isSome.ispriceHg = false;
                    }
                    if(errorType&&errorType!='BUSS_CSTP_050'){
                        return false;
                    }
                    if(font){
                        return;
                    }
                    $.each(keyMap,function(key,val){
                        if(val){
                            if(v[key] && val != v[key]){
                                font = key;
                                return;
                            }
                        }
                        else{
                            keyMap[key] = v[key];
                        }
                    });
                });
                if(errorType&&errorType!='BUSS_CSTP_050'&&errorType!='BUSS_CSTP_011'&&
                    errorType!='BUSS_CSTP_012'&&errorType!='BUSS_CSTP_013'&&errorType!='BUSS_CSTP_014'){
                    return JoyinUtil.getMessage(errorType);
                }

                if(font && font!="businesstype"){
                    //债券
                    if(tableCstp[0].businesstype =='BOND'){
                        return JoyinUtil.getMessage("TRD_PEP_001");
                    }else if(tableCstp[0].businesstype =='REPO'){
                        return JoyinUtil.getMessage("TRD_PEP_002");
                    }
                }
                var returnVal = "";
                var cstpAmtAll = 0;
                $.each(tableCstp,function (index,val) {
                    if(val.businesstype=='BOND'){
                        cstpAmtAll = JoyinMath.doAdd(cstpAmtAll,val.prinamt,2);
                    }else if(val.businesstype=='REPO'){
                        cstpAmtAll = JoyinMath.doAdd(cstpAmtAll,val.repoamt,2);
                    }
                });
                if(cstpAmtAll>Number(tableData[0].proAvailableCash)){
                    // return "下行数据的劵面总额或者本金金额不能大于指令的可交易额度！";
                }
                $.each(tableCstp,function (index,val) {
                    if(tableData[0].tradeType=='02'){
//                        returnVal = tradeAmtCanJIaoyi(val,tableData[0].entrustAmt,tableData[0].finprodType2);
                    }else if(tableData[0].tradeType=='01'){
                        returnVal = preTradeAmtJiaoYi(val,tableData,tableData[0].finprodType2);
                    }
                    if(returnVal){
                        return false;
                    }
                })
                if(returnVal){
                    return returnVal;
                }
            }else if(tableCstp.length==1&&tableData.length==1){
                var returnValue = isCanJIaoYi(tableData[0],tableCstp);
                if(returnValue&&returnValue=='BUSS_CSTP_050'){
                    isSome.isSomeJiaoYiYuan = false;
                }
                if(returnValue&&returnValue=='BUSS_CSTP_011'){
                    isSome.isprice = false;
                }
                if(returnValue&&returnValue=='BUSS_CSTP_012'){
                    isSome.isunderlyingyield2 = false;
                }
                if(returnValue&&returnValue=='BUSS_CSTP_013'){
                    isSome.isstrikeyield = false;
                }
                if(returnValue&&returnValue=='BUSS_CSTP_014'){
                    isSome.ispriceHg = false;
                }
                if(returnValue&&returnValue!='BUSS_CSTP_050'&&returnValue!='BUSS_CSTP_011'&&
                    returnValue!='BUSS_CSTP_012'&&returnValue!='BUSS_CSTP_013'&&returnValue!='BUSS_CSTP_014'){
                    return JoyinUtil.getMessage(returnValue);
                }
                if(tableCstp[0]&&tableCstp[0].businesstype=='BOND'){
                    if(tableData[0].tradeType=='02'){
//                        var returnVal = tradeAmtCanJIaoyi(tableCstp,tableData[0].entrustAmt,PINAN_ASSET_TYPE_F01);
                        if(returnVal){
                            return returnVal;
                        }
                    }else if(tableData[0].tradeType=='01'){
                        var returnVal =preTradeAmtJiaoYi(tableCstp,tableData,PINAN_ASSET_TYPE_F01);
                        if(returnVal){
                            return returnVal;
                        }
                    }
                }else if(tableCstp[0]&&tableCstp[0].businesstype=='REPO'){
                    if(tableData[0].tradeType=='02') {
//                        var returnVal = tradeAmtCanJIaoyi(tableCstp, tableData[0].entrustAmt, PINAN_ASSET_TYPE_F07);
                        if (returnVal) {
                            return returnVal;
                        }
                    }else if(tableData[0].tradeType=='01'){
                        var returnVal =preTradeAmtJiaoYi(tableCstp,tableData,PINAN_ASSET_TYPE_F07);
                        if(returnVal){
                            return returnVal;
                        }
                    }
                }
            }
        }

        function oneCstpMoreEntrust(tableData,tableCstp,isSome){
        	if(tableCstp[0].entrustId){//委托信息存在
        		var quoteIdFlag = true;
        		var entrustIdList = tableCstp[0].entrustId.split(',');
        		for(var i =0;i<tableData.length;i++){
        			if(tableData[i].entrustId){
        				if(entrustIdList.includes(tableData[i].entrustId)){
        					continue;
        				}
        			}
        			quoteIdFlag =false;
        			break;
        		}
        		if(!quoteIdFlag){
        			return "当前下行成交数据有对应上行报价委托信息，请选择对应委托！";
        		}
        	}
        	
            var portifios = [];
            if (tableData.length > 1&&tableCstp==1) {
//                if(!ISTRADERECOMMANDrECEIVE){//是否允许多组合录单
//                    return JoyinUtil.getMessage("BUSS_COM_E_0046")
//                }
                //交易类型不一致的话
                var isSomeType = tableData.some(function (value, index) {
                    return value.finprodType2 !== tableData[0].finprodType2;
                });
                if(isSomeType){
                    return JoyinUtil.getMessage("TRD_PEP_003");
                }
                //key容器
                var keyMap;
                if(tableData[0].finprodType2 ==PINAN_ASSET_TYPE_F01){
                    //对于债券，在资产标的、交易方向、交易场所、交易平台、通道相同时，交易员可勾选多笔合并交易录单，某个字段为空时可认为是与其他相同；
                    keyMap ={
                        //金融产品类型
                        "finprodType2":"",
                        //金融产品代码
                        "finprodId":"",
                        //交易方向
                        "ps":"",
                        //交易场所
                        "tradeMarket":"",
                        //    交易平台
                        "tradePlat":"",
                        //通道代码
                        "chlId":"",
                        //证券托管户
                        "secManageAcctId":"",
                        "tradeType":""
                    };
                }
                else if(tableData[0].finprodType2 ==PINAN_ASSET_TYPE_F07){
                    // 5.	对于正/逆回购，期限品种、回购类型、交易场所、交易方向相同时，也可以分别勾选多笔合并交易录单；
                    keyMap ={
                        "finprodType2":"",
                        //交易方向
                        "ps":"",
                        // 期限品种
                        "termType":"",
                        //回购类型
                        "assetType1":"",
                        //交易场所
                        "tradeMarket":"",
                        //价格模式
                        "priceModel":"",
                        //证券托管户
                        "securitiesCust":"",
                        "tradeType":""
                    };
                }
                var font="";
                var errorType ='';
                var entrustAmtAll = 0;
                $.each(tableData,function(i,v){
                    var returnValue = isCanJIaoYi(v,tableCstp);
                    errorType = returnValue;
                    if(errorType&&errorType=='BUSS_CSTP_050'){
                        isSome.isSomeJiaoYiYuan = false;
                    }
                    if(errorType&&errorType=='BUSS_CSTP_011'){
                        isSome.isprice = false;
                    }
                    if(errorType&&errorType=='BUSS_CSTP_012'){
                        isSome.isunderlyingyield2 = false;
                    }
                    if(errorType&&errorType=='BUSS_CSTP_013'){
                        isSome.isstrikeyield = false;
                    }
                    if(errorType&&errorType=='BUSS_CSTP_014'){
                        isSome.ispriceHg = false;
                    }
                    if(errorType&&errorType!='BUSS_CSTP_050'){
                        return false;
                    }
                    if(font){
                        return;
                    }
                    $.each(keyMap,function(key,val){
                        if(val){
                            if(v[key] && val != v[key]){
                                font = key;
                                return;
                            }
                        }
                        else{
                            keyMap[key] = v[key];
                        }
                    });
                });
                $.each(tableData,function (index,value) {
                    portifios.push(value.portfolioId);
                    if(value.entrustAmt){
                        entrustAmtAll = JoyinMath.doAdd(entrustAmtAll,value.entrustAmt,2);
                    }
                })
//                if(isAllEqual(portifios)){
//                    return '请选择不同的组合进行交易录单';
//                }
                if(errorType&&errorType!='BUSS_CSTP_050'&&errorType!='BUSS_CSTP_011'&&
                    errorType!='BUSS_CSTP_012'&&errorType!='BUSS_CSTP_013'&&errorType!='BUSS_CSTP_014'){
                    return JoyinUtil.getMessage(errorType);
                }

                if(font && font!="finprodType2"){
                    //债券
                    if(tableData[0].finprodType2 ==PINAN_ASSET_TYPE_F01){
                        return JoyinUtil.getMessage("TRD_PEP_001",JoyinUtil.getMessage("invest.channelId"));
                    }else if(tableData[0].finprodType2 ==PINAN_ASSET_TYPE_F07){
                        return JoyinUtil.getMessage("TRD_PEP_002")
                    }
                }
                if(tableCstp[0]&&tableCstp[0].businesstype=='BOND'){
                    if(tableData[0].tradeType=='02') {
//                        var returnVal = tradeAmtCanJIaoyi(tableCstp, entrustAmtAll, PINAN_ASSET_TYPE_F01);
                        if (returnVal) {
                            return returnVal;
                        }
                    }else if(tableData[0].tradeType=='01'){
                        var returnVal = preTradeAmtJiaoYi(tableCstp,tableData,PINAN_ASSET_TYPE_F01);
                        if (returnVal) {
                            return returnVal;
                        }
                    }
                }else if(tableCstp[0]&&tableCstp[0].businesstype=='REPO'){
                    if(tableData[0].tradeType=='02') {
//                        var returnVal = tradeAmtCanJIaoyi(tableCstp, entrustAmtAll, PINAN_ASSET_TYPE_F07);
                        if (returnVal) {
                            return returnVal;
                        }
                    }else if(tableData[0].tradeType=='01'){
                        var returnVal = preTradeAmtJiaoYi(tableCstp,tableData,PINAN_ASSET_TYPE_F07);
                        if (returnVal) {
                            return returnVal;
                        }
                    }
                }
            }else if(tableData.length == 1&&tableCstp==1){//非多组合
                var returnValue = isCanJIaoYi(tableData[0],tableCstp);
                if(returnValue&&returnValue=='BUSS_CSTP_050'){
                    isSome.isSomeJiaoYiYuan = false;
                }
                if(returnValue&&returnValue=='BUSS_CSTP_011'){
                    isSome.isprice = false;
                }
                if(returnValue&&returnValue=='BUSS_CSTP_012'){
                    isSome.isunderlyingyield2 = false;
                }
                if(returnValue&&returnValue=='BUSS_CSTP_013'){
                    isSome.isstrikeyield = false;
                }
                if(returnValue&&returnValue=='BUSS_CSTP_014'){
                    isSome.ispriceHg = false;
                }
                if(returnValue&&returnValue!='BUSS_CSTP_050'&&returnValue!='BUSS_CSTP_011'&&
                    returnValue!='BUSS_CSTP_012'&&returnValue!='BUSS_CSTP_013'&&returnValue!='BUSS_CSTP_014'){
                    return JoyinUtil.getMessage(returnValue);
                }
                if(tableCstp[0]&&tableCstp[0].businesstype=='BOND'){
                    if(tableData[0].tradeType=='02') {
//                        var returnVal = tradeAmtCanJIaoyi(tableCstp, tableData[0].entrustAmt, PINAN_ASSET_TYPE_F01);
                        if (returnVal) {
                            return returnVal;
                        }
                    }else if(tableData[0].tradeType=='01'){
                        var returnVal = preTradeAmtJiaoYi(tableCstp,tableData,PINAN_ASSET_TYPE_F01);
                        if (returnVal) {
                            return returnVal;
                        }
                    }
                }else if(tableCstp[0]&&tableCstp[0].businesstype=='REPO'){
                    if(tableData[0].tradeType=='02') {
//                        var returnVal = tradeAmtCanJIaoyi(tableCstp, tableData[0].entrustAmt, PINAN_ASSET_TYPE_F07);
                        if (returnVal) {
                            return returnVal;
                        }
                    }else if(tableData[0].tradeType=='01'){
                        var returnVal = preTradeAmtJiaoYi(tableCstp,tableData,PINAN_ASSET_TYPE_F07);
                        if (returnVal) {
                            return returnVal;
                        }
                    }
                }
            }
        }

        $('#dataTableDiv').on('click',function () {
            var allMoney = 0;
            var rows = table.getAllCheckedRows();
            if(rows&&rows.length>0){
                $.each(rows,function (index,element) {
                    if(element.tradecashamt){
                        allMoney =   JoyinMath.doAdd(allMoney,element.tradecashamt,2);
                    }
                })
            }
            $('#priceAmtAll').val(allMoney);
        });

        $('#EntrustDataTableDiv').on('click',function () {
            var allMoney = 0;
            var rows = EntrustTable.getAllCheckedRows();
            if(rows&&rows.length>0){
                $.each(rows,function (index,element) {
                    if(element.proAvailableCash){
                        allMoney =   JoyinMath.doAdd(allMoney,element.proAvailableCash,2);
                    }
                })
            }
            $('#TrdCommandReceive001_priceAmtAll').val(allMoney);
        })

        $('#TrdMatchInfo_btn_quit').on('click',function () {
            var rows = TrdMatchInfo.getAllCheckedRows();
            if(rows.length==0){
                Joyin.error("请选择一条数据进行取消匹配操作");
                return;
            }
            deleteTrdMatchInfo(rows);
        })
        $('#TrdMatchInfo_btn_submit').on('click',function () {
            var rows = TrdMatchInfo.getAllCheckedRows();
            var entrustIds = [];
            var preTradeIds=[];
            if(rows.length!=1){
                Joyin.error("请选择一条数据进行提交操作");
                return;
            }
            $.each(rows,function (index,val) {
                if(val.entrustId){
                    entrustIds.push(val.entrustId);
                }
                if(val.preTradeId){
                    preTradeIds.push(val.preTradeId);
                }
            });
            //判断是否是一样的交易匹配确认类型
            if(rows.length!=entrustIds.length&&rows.length!=preTradeIds.length){
                return Joyin.error('请选择相同的交易类型');
            }
            if(preTradeIds!=0&&preTradeIds.length>1){
                 return Joyin.error('请选择一条预录入数据');
            }

            var isSomeType = rows.some(function (value, index) {
                return value.finprodType !== rows[0].finprodType;
            });
            if(isSomeType){
                return Joyin.error(JoyinUtil.getMessage("TRD_PEP_003"));
            }
            
            var errors = openEntrustByTrdMatchInfo(rows);
            if(errors){
              return Joyin.error(errors);
            }
            //打开交易页面
            OpenTrade(rows);
        })

        $('#CSTPSelect001_btn_transaction')
            .click(
                function () {
                    var confirmContent = '';
                    var confirmContent1 = '';
                    var confirmContent2 = '';
                    var confirmContent3 = '';
                    var confirmContent4 = '';
                    var confirmContent5 = '';
                    var isReturn = false;
                    var isSome = {};//是否相同？
                    isSome.isSomeJiaoYiYuan = true;//交易员
                    isSome.isprice = true;//净价
                    isSome.isunderlyingyield2 = true;//到期收益率
                    isSome.isstrikeyield = true;//行权收益率
                    isSome.ispriceHg = true;//回购利率
                    if(table){//cstp下行table
                        var tableCstp = table.getAllCheckedRows();
                    }
                    if (EntrustTable != null && EntrustTable != '') {//委托列表table
                        var tableData = EntrustTable.getAllCheckedRows();
                    }
                    if(!tableCstp || tableCstp.length == 0){//未选cstptable
                        Joyin.error(JoyinUtil.getMessage("BUSS_CSTP_001"));
                        return;
                    }
                    if (!tableData || tableData.length == 0) {//未选委托table
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0026"));
                        return;
                    }
                    if(tableCstp.length>1 && tableData.length>1){
                        Joyin.error('下行交易数据和指令预交易数据不能同时大于2条数据');
                        return;
                    }
                    var isSomeType = tableData.some(function (value, index) {//委托列表交易类型是否相同
                        return value.tradeType !== tableData[0].tradeType;
                    });
                    if(isSomeType){
                        Joyin.error('请选择相同的交易类型进行交易匹配');
                        return;
                    }
                    if(tableData.length>1&&tableData[0].tradeType=='01'){
                        Joyin.error('只能选择一条预录入数据');
                        return;
                    }
                    var matchStatus = true;//全部匹配标识
                    $.each(tableCstp,function (index,val) {
                        if(!(val.matchStatus=='02'||val.matchStatus=='03')){
                            matchStatus = false;
                            return false;
                        }
                    });
                    if(!matchStatus){
                        Joyin.error(JoyinUtil.getMessage("BUSS_CSTP_003"));
                        return;
                    }
                    if(tableCstp.length>0 && tableData.length>0){
                        // var tradeDate = DateUtil.parse(tableCstp[0].tradeDateStr);
                        $.each(tableData,function (index,value) {
                            $.each(tableCstp,function (index1,val) {
                                if(!(DateUtil.parse(val.tradeDateStr)>=DateUtil.parse(value.vdateStr)&&
                                    DateUtil.parse(val.tradeDateStr)<=DateUtil.parse(value.mdateStr))){
                                    isReturn = true;
                                    return false;
                                }
                            })
                        })
                        if(isReturn){
                            Joyin.error('交易日期不在指令的有效区间内');
                            return;
                        }
                    }
                    var errorDate = false;
                    $.each(tableData,function (index,val) {
                        // 平台日期小于指令开始日报错
                        if (platDate < val.vdateStr) {
                            errorDate=true;
                            return false;
                        }
                    })
                    if(errorDate){
                        Joyin.error(JoyinUtil.getMessage("TRD_E_0003"));
                        return;
                    }

                    //一条cstp数据匹配多条指令预交易
                    var error1 = oneCstpMoreEntrust(tableData,tableCstp,isSome);
                    if(error1){
                        Joyin.error(error1);
                        return;
                    }
                    //一条指令匹配多条cstp数据
                    var error2 = oneEntrustMoreCstp(tableData,tableCstp,isSome);
                    if(error2){
                        Joyin.error(error2);
                        return;
                    }
                    var canOpen = [];
                    //交易录单的check
                    $.each(tableData,function (index,data) {
                        if(data.tradeType){
                            if(data.tradeType=='02'){//指令的
                                Joyin.ajaxJson(
                                    JoyinUtil
                                        .getRealPath('invest/trade/investtrade/commandreceive/TrdCommandReceive001/check'),
                                    {
                                        entrustStatus: data.entrustStatus,
                                        operateType: 'trdTrade',
                                        //存在流程中数据，不可以进入交易录单页面（回购，债券可以多次录入（提交会有check其流程中与已完成额度））
                                        entrustId: data.entrustId,
                                        finprodType2 : data.finprodType2
                                    }, function (result) {
                                        canOpen.push(true);
                                    },false);
                            }else{//预录入的
                                canOpen.push(true);
                            }

                        }
                    });
                    if(!isSome.isprice){
                        confirmContent1 = '债券指令的净价与债券成交单的净价不一致，';
                        confirmContent = confirmContent+confirmContent1;
                    }
                    if(!isSome.isunderlyingyield2){
                        confirmContent2 = '债券指令的到期收益率与债券成交单的到期收益率不一致，';
                        confirmContent = confirmContent+confirmContent2;
                    }
                    if(!isSome.isstrikeyield){
                        confirmContent3 = '债券指令的行权收益率与债券成交单的行权收益率不一致，';
                        confirmContent = confirmContent+confirmContent3;
                    }
                    if(!isSome.ispriceHg){
                        confirmContent4 = '质押式回购指令的回购利率与质押式成交单的回购利率不一致，';
                        confirmContent = confirmContent+confirmContent4;
                    }
                    if(!isSome.isSomeJiaoYiYuan){
                        confirmContent5 = '质押式回购指令的回购利率与质押式成交单的回购利率不一致，';
                        confirmContent = confirmContent+confirmContent5;
                    }

                    if(confirmContent){
                        Joyin.confirm(confirmContent+'确定提交？',function () {
                            if(canOpen.length==tableData.length){
                                saveTrdMatchInfo(tableData,tableCstp);
                            }
                        })
                    }else{
                      if(canOpen.length==tableData.length){
                            saveTrdMatchInfo(tableData,tableCstp);
                       }
                    }
                });
        //更多
        $('#CSTPSelect001_much').click(function () {
            if($('#CSTPSelect001_much').find('span').html()=="更多"){
                $('#CSTPSelect001_much_div1').show();
                $('#CSTPSelect001_much_div2').show();
                $('#CSTPSelect001_much').find('span').html("取消更多");
            }else{
                $('#CSTPSelect001_much_div1').hide();
                $('#CSTPSelect001_much_div2').hide();
                $('#CSTPSelect001_much').find('span').html("更多");
            }
        });
        $('#Entrust_much').click(function () {
            if($('#Entrust_much').find('span').html()=="更多"){
                $('#Entrust_much_div1').show();
                $('#Entrust_much_div2').show();
                $('#Entrust_much').find('span').html("取消更多");
            }else{
                $('#Entrust_much_div1').hide();
                $('#Entrust_much_div2').hide();
                $('#Entrust_much').find('span').html("更多");
            }
        });
    });
}());