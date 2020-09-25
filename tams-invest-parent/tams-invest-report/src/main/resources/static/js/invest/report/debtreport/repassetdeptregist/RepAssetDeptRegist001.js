var arr1 = ['1002','1003','1004','1005'];
var arr3 = ['1107','1108','1901','1902'];
var arr2 = ['1101','1109','1102','1111','1112','1113','1114','1115','1116','1117','2101','2501','2502'];
var arr4 = ['1202','1203','1204','1205','1206','1207','1208','1209','2201'];
var arr5 = ['1201','1210'];
var arr6 = ['1301','1302','1305'];
var arr7 = ['1401','1402','1403','1404','1405','1406','1499'];
var arr8 = ['1501'];
var arr9 = ['1504','1506'];
var arr10 = ['1502','1505','1507','1508','1509'];
var arr11 = ['1503'];
var arr13 = ['1601','1602','1699'];
var arr12 = ['1701','1702','1703','1704','1705','1706'];
var arr14 = ['1299','1399','1599','2299'];
var arr15 = ['2301','2302','2303','2304','2305','1603'];
var arr16 = ['1106','2401','1303','2403','2402','2499','2601','2602','2603','2604','2605','2606','2607','2610','2611','2612','2613','2614','2617','2618','2619','2620','2699'];
var arr17 = ['2801','2802','2803','2804','2805','2806','2899'];
var arr18 = ['2000'];
var searchTable = 1;
var flag = true;
(function() {
    /**
     * 获取查询参数
     */
    var searchform = $.serializeToObject('#searchForm');
    var getPageParams = function(){
        var liabilityTypeTemp = $('#liabilityType').val();
        var liabilityType = null;
        if(liabilityTypeTemp && liabilityTypeTemp != ''){
            liabilityType = liabilityTypeTemp.join(',');
        }
        if (!liabilityType) {
            if ($('#assetType').val() === '1') {
                liabilityType = '1002,1003,1004,1005';
            } else if ($('#assetType').val() === '3') {
                liabilityType = '1107,1108';
            } else if ($('#assetType').val() === '5') {
                liabilityType = '2101';
            } else if ($('#assetType').val() === '7') {
                liabilityType = '1201,1210,1202,1203,1204,1205,1206,1207,1208,1209,1299';
            } else if ($('#assetType').val() === '8') {
                liabilityType = '1399,1301';
            } else if ($('#assetType').val() === '8') {
                liabilityType = '1399,1301';
            } else if ($('#assetType').val() === '13') {
                liabilityType = '2401,1106,1303,2403,2402,2499';
            } else if ($('#assetType').val() === '14') {
                liabilityType = '2612';
            } else if ($('#assetType').val() === '15') {
                liabilityType = '1701,1703,1704,1702,1706,1705';
            } else if ($('#assetType').val() === '16') {
                liabilityType = '2801,2802,2803,2804,2806,2805,2899';
            } else if ($('#assetType').val() === '17') {
                liabilityType = '1902,1901';
            }
        }
        
        var assetCodeTemp = $('#assetCode').val();
        var assetCode = null;
        if(assetCodeTemp && assetCodeTemp != ''){
            assetCode = assetCodeTemp.join(',');
        }
        return {
            assetType:liabilityType,
            liabilityType:null,
            assetCode:assetCode,
            dataDateStart:$('#dataDate').startDate(),
            dataDateEnd:$('#dataDate').endDate()
        }
    };

        //本行/他行存款||大额存单                             
       var dataTable1 = $('#dataTable1').joyintable({
            ajax: {
                url: 'searchPage',
                type: 'POST',
                data: function() {
                    var param = getPageParams();
                    param.tableNum = "01";
                    return param;
                }
            },
            cols:[
                  {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
                  ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
                  ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
                  ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
                  ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
                  ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
                  ,{field:'repBankName', title: 'RepAssetDeptRegist001.repBankName', width:200,sortField:'REP_BANK_NAME'}
                  ,{field:'repPrinAmt', title: 'RepAssetDeptRegist001.repPrinAmt', width:200,type:'number',sortField:'REP_PRIN_AMT'}
                  ,{field:'repVdate', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VDATE'}
                  ,{field:'repMdate', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:'REP_MDATE'}
                  ,{field:'repRateYear', title: 'RepAssetDeptRegist001.repRateYear', width:200,type:'number',sortField:'REP_RATE_YEAR'}
                  ,{field:'repBasisStr', title: 'RepAssetDeptRegist001.repBasis', width:200,sortField:'REP_BASIS'}
                  ,{field:'repIsStructStr', title: 'RepAssetDeptRegist001.repIsStruct', width:200,sortField:'REP_IS_STRUCT'}
                  ,{field:'repTargetTypeStr', title: 'RepAssetDeptRegist001.repTargetType', width:200,sortField:'REP_TARGET_TYPE'}
                  ,{field:'repTarget', title: 'RepAssetDeptRegist001.repTarget', width:200,sortField:'REP_TARGET'}
                  ],
              done:function(){
            	  check();
              },
                  height:'400'
        });
       
        //债券类资产/理财直接融资工具/同业存单                                       
       var dataTable2 = $('#dataTable2').joyintable({
          ajax: {
              url: 'searchPage',
              type: 'POST',
              data: function() {
                  var param = getPageParams();
                  param.tableNum = "02";
                  return param;
              }
          },                            

          cols:[
                {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
                ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
                ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
                ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
                ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
                ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
                ,{field:'finprodMarketId2', title: 'RepAssetDeptRegist001.finprodMarketId', width:200,sortField:'FINPROD_MARKET_ID'}
                ,{field:'repFinprodName', title: 'RepAssetDeptRegist001.finprodMarketName', width:200,sortField:'REP_FINPROD_NAME'}
                ,{field:'repSectypeStr', title: 'RepAssetDeptRegist001.repSectype', width:200,sortField:'REP_SECTYPE'}
                ,{field:'repIssueModeStr', title: 'RepAssetDeptRegist001.repIssueMode', width:200,sortField:'REP_ISSUE_MODE'}
                ,{field:'repMainGradeStr', title: 'RepAssetDeptRegist001.repMainGrade', width:200,sortField:'REP_MAIN_GRADE'}
                ,{field:'repPartyType1Str', title: 'RepAssetDeptRegist001.repIssuePartyType1', width:200,sortField:'REP_PARTY_TYPE1'}
                ,{field:'repPartyType3Str', title: 'RepAssetDeptRegist001.repIssuePartyType3', width:200,sortField:'REP_PARTY_TYPE3'}
                ,{field:'repPartyType2Str', title: 'RepAssetDeptRegist001.repIssuePartyType2', width:200,sortField:'REP_PARTY_TYPE2'}
                ,{field:'repIndustryLevel2Str', title: 'RepAssetDeptRegist001.repIndustry', width:200,sortField:'REP_INDUSTRY_LEVEL2'}
                ,{field:'repRegistOrgStr', title: 'RepAssetDeptRegist001.repRegistOrg', width:200,sortField:'REP_REGIST_ORG'}
                ,{field:'repRegistorgComment', title: 'RepAssetDeptRegist001.repRegistorgComment', width:200,sortField:'REP_REGISTORG_COMMENT'}
                ],
                done:function(){
              	  check();
                },
                height:'400'
      });
       //拆放同业及买入返售/同业拆入及卖出回购                        

       var dataTable3 = $('#dataTable3').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "03";
                 return param;
             }
         },                   

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repVdate3', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VDATE'}
               ,{field:'repMdate3', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:'REP_MDATE'}
               ,{field:'repCounterparty', title: 'RepAssetDeptRegist001.repCounterparty', width:200,sortField:'REP_TRADE_TARGET'}
               ,{field:'repAnnualInterestRate', title: 'RepAssetDeptRegist001.repRateYear', width:200,type:'number',sortField:'REP_RATE_YEAR'}
               ,{field:'repBasisStr3', title: 'RepAssetDeptRegist001.repBasis', width:200,sortField:'REP_BASIS'}
               ,{field:'repTargetTypeStr', title: 'RepAssetDeptRegist001.repTargetType', width:200,sortField:'REP_TARGET_TYPE'}
               ,{field:'repBuyBackMoney', title: 'RepAssetDeptRegist001.repBuyBackMoney', width:200,type:'number',sortField:'REP_TARGET_AMT'}

               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //非标准化债权类资产/信贷资产流转项目                                                                                                                                             

       var dataTable4 = $('#dataTable4').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "04";
                 return param;
             }
         },              

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repProfitTypeStr', title: 'RepAssetDeptRegist001.repProfitType', width:200,sortField:'REP_PROFIT_TYPE'}
               ,{field:'repIsResaleStr', title: 'RepAssetDeptRegist001.repIsResale', width:200,sortField:'REP_IS_RESALE'}
               ,{field:'repFinprodName4', title: 'RepAssetDeptRegist001.finprodMarketName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repAmt', title: 'RepAssetDeptRegist001.repAmt', width:200,type:'number',sortField:'REP_AMT'}
               ,{field:'repVdate4', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VDATE'}
               ,{field:'repMdate4', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:'REP_MDATE'}
               ,{field:'repRateFlagStr', title: 'RepAssetDeptRegist001.repRateFlag', width:200,sortField:'REP_RATE_FLAG'}
               ,{field:'repProjYield', title: 'RepAssetDeptRegist001.repProjYield', width:200,type:'number',sortField:'REP_PROJ_YIELD'}
               ,{field:'repIntpayCycle', title: 'RepAssetDeptRegist001.repIntpayCycle', width:200,type:'number',sortField:'REP_INTPAY_CYCLE'}
               ,{field:'repRepayCmt', title: 'RepAssetDeptRegist001.repRepayCmt', width:200,sortField:'REP_REPAY_CMT'}
               ,{field:'repBasisType', title: 'RepAssetDeptRegist001.repBasisType', width:200,sortField:'REP_BASIS_TYPE'}
               ,{field:'repBasisStr4', title: 'RepAssetDeptRegist001.repBasis', width:200,sortField:'REP_BASIS'}
               ,{field:'repFloatFactor', title: 'RepAssetDeptRegist001.repFloatFactor', width:200,type:'number',sortField:'REP_FLOAT_FACTOR'}
               ,{field:'repSpreadRate', title: 'RepAssetDeptRegist001.repSpreadRate', width:200,type:'number',sortField:'REP_SPREAD_RATE'}
               ,{field:'repFinancier', title: 'RepAssetDeptRegist001.repFinancier', width:200,sortField:'REP_FINANCIER'}
               ,{field:'repFcrGradeIn', title: 'RepAssetDeptRegist001.repFcrGradeIn', width:200,sortField:'REP_FCR_GRADE_IN'}
               ,{field:'repFcrGradeOut', title: 'RepAssetDeptRegist001.repFcrGradeOut', width:200,sortField:'REP_FCR_GRADE_OUT'}
               ,{field:'repFinancierType1Str', title: 'RepAssetDeptRegist001.repFinancierType1', width:200,sortField:'REP_FINANCIER_TYPE1'}
               ,{field:'repFinancierType3Str', title: 'RepAssetDeptRegist001.repFinancierType3', width:200,sortField:'REP_FINANCIER_TYPE3'}
               ,{field:'repFinancierType2Str', title: 'RepAssetDeptRegist001.repFinancierType2', width:200,sortField:'REP_FINANCIER_TYPE2'}
               ,{field:'repFinanceProj', title: 'RepAssetDeptRegist001.repFinanceProj', width:200,sortField:'REP_FINANCE_PROJ'}
               ,{field:'repIndustry1', title: 'RepAssetDeptRegist001.repIndustry1', width:200,sortField:'REP_INDUSTRY1'}
               ,{field:'repIndustryFlagStr', title: 'RepAssetDeptRegist001.repIndustryFlag', width:200,sortField:'REP_INDUSTRY_FLAG'}
               ,{field:'repMonitTypeStr', title: 'RepAssetDeptRegist001.repMonitType', width:200,sortField:'REP_MONIT_TYPE'}
               ,{field:'repMonTypeComm', title: 'RepAssetDeptRegist001.repMonTypeComm', width:200,sortField:'REP_MON_TYPE_COMM'}
               ,{field:'repGuaType', title: 'RepAssetDeptRegist001.repGuaType', width:200,sortField:'REP_GUA_TYPE'}
               ,{field:'repGuaTypeRemark', title: 'RepAssetDeptRegist001.repGuaTypeRemark', width:200,sortField:'REP_GUA_TYPE_REMARK'}
               ,{field:'repFinancierGradeStr', title: 'RepAssetDeptRegist001.repFinancierGrade', width:200,sortField:'REP_FINANCIER_GRADE'}
               ,{field:'repInGradeStr', title: 'RepAssetDeptRegist001.repInGrade', width:200,sortField:'REP_IN_GRADE'}
               ,{field:'repOutGradeStr', title: 'RepAssetDeptRegist001.repOutGrade', width:200,sortField:'REP_OUT_GRADE'}
               ,{field:'repChose', title: 'RepAssetDeptRegist001.repChose', width:200,sortField:'REP_CHOSE'}
               ,{field:'repConditionCmm', title: 'RepAssetDeptRegist001.repConditionCmm', width:200,sortField:'REP_CONDITION_CMM'}
               ,{field:'repFinArea', title: 'RepAssetDeptRegist001.repFinArea', width:200,sortField:'REP_FIN_AREA'}
               ,{field:'repFinOrgCode', title: 'RepAssetDeptRegist001.repFinOrgCode', width:200,sortField:'REP_FIN_ORG_CODE'}
               ,{field:'repTotalFeeRate', title: 'RepAssetDeptRegist001.repTotalFeeRate', width:200,type:'number',sortField:'REP_TOTAL_FEE_RATE'}
               ,{field:'repProIndustry', title: 'RepAssetDeptRegist001.repProIndustry', width:200,sortField:'REP_PRO_INDUSTRY'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //票据类/信用证类资产                                         

       var dataTable5 = $('#dataTable5').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "05";
                 return param;
             }
         },                         

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repIsRightStr', title: 'RepAssetDeptRegist001.repIsRight', width:200,sortField:'REP_IS_RIGHT'}
               ,{field:'repIsResaleStr', title: 'RepAssetDeptRegist001.repIsResale', width:200,sortField:'REP_IS_RESALE'}
               ,{field:'repAstBillTypeStr', title: 'RepAssetDeptRegist001.repAstBillType', width:200,sortField:'REP_AST_BILL_TYPE'}
               ,{field:'repNum', title: 'RepAssetDeptRegist001.repNum', width:200,type:'number',sortField:'REP_NUM'}
               ,{field:'repAmtSum', title: 'RepAssetDeptRegist001.repAmtSum', width:200,type:'number',sortField:'REP_AMT_SUM'}
               ,{field:'repRemainTerm', title: 'RepAssetDeptRegist001.repRemainTerm', width:200,type:'number',sortField:'REP_REMAIN_TERM'}
               ,{field:'repMaxRemainTerm', title: 'RepAssetDeptRegist001.repMaxRemainTerm', width:200,type:'number',sortField:'REP_MAX_REMAIN_TERM'}
               ,{field:'repMinRemainTerm', title: 'RepAssetDeptRegist001.repMinRemainTerm', width:200,type:'number',sortField:'REP_MIN_REMAIN_TERM'}
               ,{field:'repVdate5', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VDATE'}
               ,{field:'repMdate5', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:''}
               ,{field:'repIndustry', title: 'RepAssetDeptRegist001.repIndustry', width:200,sortField:'REP_MDATE'}
               ,{field:'repDiscRate', title: 'RepAssetDeptRegist001.repDiscRate', width:200,type:'number',sortField:'REP_DISC_RATE'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //权益类资产                              

       var dataTable6 = $('#dataTable6').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "06";
                 return param;
             }
         },                        

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repSharesId', title: 'RepAssetDeptRegist001.repSharesId', width:200,sortField:'REP_SHARES_ID'}
               ,{field:'repFinprodName6', title: 'RepAssetDeptRegist001.stockName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repSharesTypeStr', title: 'RepAssetDeptRegist001.repSharesType', width:200,sortField:'REP_SHARES_TYPE'}
               ,{field:'repIndustry', title: 'RepAssetDeptRegist001.repIndustry', width:200,sortField:'REP_INDUSTRY'}
               ,{field:'repInvestStageStr', title: 'RepAssetDeptRegist001.repInvestStage', width:200,sortField:'REP_INVEST_STAGE'}
               ,{field:'repPartyType1_6Str', title: 'RepAssetDeptRegist001.repPartyType1', width:200,sortField:'REP_PARTY_TYPE1'}
               ,{field:'repPartyType3_6Str', title: 'RepAssetDeptRegist001.repPartyType3', width:200,sortField:'REP_PARTY_TYPE3'}
               ,{field:'repPartyType2_6Str', title: 'RepAssetDeptRegist001.repPartyType2', width:200,sortField:'REP_PARTY_TYPE2'}
               ,{field:'repIsPledgeStr', title: 'RepAssetDeptRegist001.repIsPledge', width:200,sortField:'REP_IS_PLEDGE'}
               
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //金融衍生品      

       var dataTable7 = $('#dataTable7').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "07";
                 return param;
             }
         },       

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repFinprodName7', title: 'RepAssetDeptRegist001.finprodMarketName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repNomCapi', title: 'RepAssetDeptRegist001.repNomCapi', width:200,type:'number',sortField:'REP_NOM_CAPI'}
               ,{field:'repDeriType', title: 'RepAssetDeptRegist001.repDeriType', width:200,sortField:'REP_DERI_TYPE'}
               ],
               height:'400'
     });
       //QDII债券资产                                                   

       var dataTable8 = $('#dataTable8').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "08";
                 return param;
             }
         },                               

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repCountryArea8Str', title: 'RepAssetDeptRegist001.repCountryArea', width:200,sortField:'REP_COUNTRY_AREA'}
               ,{field:'repFinprodName8', title: 'RepAssetDeptRegist001.secname', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'finprodMarketId8', title: 'RepAssetDeptRegist001.secid', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssueParty', title: 'RepAssetDeptRegist001.repIssueParty', width:200,sortField:'REP_ISSUE_PARTY'}
               ,{field:'repIndustry', title: 'RepAssetDeptRegist001.repIndustry', width:200,sortField:'REP_INDUSTRY'}
               ,{field:'repVdate8', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VDATE'}
               ,{field:'repMdate8', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:'REP_MDATE'}
               ,{field:'repTerm', title: 'RepAssetDeptRegist001.repTermMon', width:200,type:'number',sortField:'REP_TERM'}
               ,{field:'repPartyGrade', title: 'RepAssetDeptRegist001.repPartyGrade', width:200,sortField:'REP_PARTY_GRADE'}
               ,{field:'repSecGrade', title: 'RepAssetDeptRegist001.repSecGrade', width:200,sortField:'REP_SEC_GRADE'}
               ,{field:'repPaperRate', title: 'RepAssetDeptRegist001.repPaperRate', width:200,type:'number',sortField:'REP_PAPER_RATE'}
               ,{field:'repIntpayCycle', title: 'RepAssetDeptRegist001.repIntpayCycle', width:200,type:'number',sortField:'REP_INTPAY_CYCLE'}
               ,{field:'repGuaranteeCmt', title: 'RepAssetDeptRegist001.repGuaTypeRemark', width:200,sortField:'REP_GUARANTEE_CMT'}
               ,{field:'repSpecialCmt', title: 'RepAssetDeptRegist001.repSpecialCmt', width:200,sortField:'REP_SPECIAL_CMT'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //QDII拆出/逆回购资产                   

       var dataTable9 = $('#dataTable9').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "09";
                 return param;
             }
         },                  

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId',sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repCountryArea9Str', title: 'RepAssetDeptRegist001.repCountryArea', width:200,sortField:'REP_COUNTRY_AREA'}
               ,{field:'repVdate9', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VDATE'}
               ,{field:'repMdate9', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:'REP_MDATE'}
               ,{field:'repCounterparty', title: 'RepAssetDeptRegist001.repCounterparty', width:200,sortField:'REP_COUNTERPARTY'}
               ,{field:'repRateYear', title: 'RepAssetDeptRegist001.repRateYear', width:200,type:'number',sortField:'REP_RATE_YEAR'}
               ,{field:'repBasisStr9', title: 'RepAssetDeptRegist001.repBasis', width:200,sortField:'REP_BASIS'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //QDII股票/基金类资产               

       var dataTable10 = $('#dataTable10').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "010";
                 return param;
             }
         },              

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repCountryArea10Str', title: 'RepAssetDeptRegist001.repCountryArea', width:200,sortField:'REP_COUNTRY_AREA'}
               ,{field:'repShrsFundId', title: 'RepAssetDeptRegist001.repShrsFundId', width:200,sortField:'REP_SHRS_FUND_ID'}
               ,{field:'repFinprodName10', title: 'RepAssetDeptRegist001.funName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repIssueParty', title: 'RepAssetDeptRegist001.repIssueParty', width:200,sortField:'REP_ISSUE_PARTY'}
               ,{field:'repIndustryStr', title: 'RepAssetDeptRegist001.repIndustry', width:200,sortField:'REP_INDUSTRY'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //QDII结构性票据类资产                                                               

       var dataTable11 = $('#dataTable11').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "011";
                 return param;
             }
         },                                         

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repCountryArea11Str', title: 'RepAssetDeptRegist001.repCountryArea', width:200,sortField:'REP_COUNTRY_AREA'}
               ,{field:'repFinprodName11', title: 'RepAssetDeptRegist001.contractName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repVdate11', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VDATE'}
               ,{field:'repMdate11', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:'REP_MDATE'}
               ,{field:'repPaperRate', title: 'RepAssetDeptRegist001.repPaperRate', width:200,type:'number',sortField:'REP_PAPER_RATE'}
               ,{field:'repIntpayCycle', title: 'RepAssetDeptRegist001.repIntpayCycle', width:200,type:'number',sortField:'REP_INTPAY_CYCLE'}
               ,{field:'repIncomeRatio', title: 'RepAssetDeptRegist001.repIncomeRatio', width:200,type:'number',sortField:'REP_INCOME_RATIO'}
               ,{field:'repDerInstrRatio', title: 'RepAssetDeptRegist001.repDerInstrRatio', width:200,type:'number',sortField:'REP_DER_INSTR_RATIO'}
               ,{field:'repDerInstrInvType', title: 'RepAssetDeptRegist001.repDerInstrInvType', width:200,sortField:'REP_DER_INSTR_INV_TYPE'}
               ,{field:'repDerInstrTarget', title: 'RepAssetDeptRegist001.repDerInstrTarget', width:200,sortField:'REP_DER_INSTR_TARGET'}
               ,{field:'repAcctMode', title: 'RepAssetDeptRegist001.repAcctMode', width:200,sortField:'REP_ACCT_MODE'}
               ,{field:'repSpecialCmt', title: 'RepAssetDeptRegist001.repSpecialCmt', width:200,sortField:'REP_SPECIAL_CMT'}
               ,{field:'repMaxYield', title: 'RepAssetDeptRegist001.repMaxYield', width:200,type:'number',sortField:'REP_MAX_YIELD'}
               ,{field:'repMinYield', title: 'RepAssetDeptRegist001.repMinYield', width:200,type:'number',sortField:'REP_MIN_YIELD'}
               ,{field:'repBasicPrice', title: 'RepAssetDeptRegist001.repBasicPrice', width:200,type:'number',sortField:'REP_BASIC_PRICE'}
               ,{field:'repRegDatePrice', title: 'RepAssetDeptRegist001.repRegDatePrice', width:200,type:'number',sortField:'REP_REG_DATE_PRICE'}
               ,{field:'repTradeFee', title: 'RepAssetDeptRegist001.repTradeFee', width:200,sortField:'REP_TRADE_FEE'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //资产管理产品                                                                                     

       var dataTable12 = $('#dataTable12').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "012";
                 return param;
             }
         },                          

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repFinprodName12', title: 'RepAssetDeptRegist001.planName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repPartyCode', title: 'RepAssetDeptRegist001.repPartyCode', width:200,sortField:'REP_PARTY_CODE'}
               ,{field:'repRegistCode', title: 'RepAssetDeptRegist001.repRegistCode', width:200,sortField:'REP_REGIST_CODE'}
               ,{field:'repManagerId', title: 'RepAssetDeptRegist001.repManagerId', width:200,sortField:'REP_MANAGER_ID'}
               ,{field:'repTrusteeId', title: 'RepAssetDeptRegist001.repTrusteeId', width:200,sortField:'REP_TRUSTEE_ID'}
               ,{field:'repAmt', title: 'RepAssetDeptRegist001.repAmt', width:200,type:'number',sortField:'REP_AMT'}
               ,{field:'repCapiInvestStr', title: 'RepAssetDeptRegist001.repCapiInvest', width:200,sortField:'REP_CAPI_INVEST'}
               ,{field:'repCapUseWay', title: 'RepAssetDeptRegist001.repCapUseWay', width:200,sortField:'REP_CAP_USE_WAY'}
               ,{field:'repCapUseInduStr', title: 'RepAssetDeptRegist001.repCapUseIndu', width:200,sortField:'REP_CAP_USE_INDU'}
               ,{field:'repSetDate', title: 'RepAssetDeptRegist001.repSetDate', width:200,type:'date',sortField:'REP_SET_DATE'}
               ,{field:'repEndDate', title: 'RepAssetDeptRegist001.repEndDate', width:200,type:'date',sortField:'REP_END_DATE'}
               ,{field:'repPlanPropStr', title: 'RepAssetDeptRegist001.repPlanProp', width:200,sortField:'REP_PLAN_PROP'}
               ,{field:'repYieldFlagStr', title: 'RepAssetDeptRegist001.repRateFlag', width:200,sortField:'REP_YIELD_FLAG'}
               ,{field:'repMaxRate', title: 'RepAssetDeptRegist001.repMaxRate', width:200,type:'number',sortField:'REP_MAX_RATE'}
               ,{field:'repMinRate', title: 'RepAssetDeptRegist001.repMinRate', width:200,type:'number',sortField:'REP_MIN_RATE'}
               ,{field:'repBuyStructStr', title: 'RepAssetDeptRegist001.repBuyStruct', width:200,sortField:'REP_BUY_STRUCT'}
               ,{field:'repManageTypeStr', title: 'RepAssetDeptRegist001.repManageType', width:200,sortField:'REP_MANAGE_TYPE'}
               ,{field:'repIsCtrlBottomStr', title: 'RepAssetDeptRegist001.repIsCtrlBottom', width:200,sortField:'REP_IS_CTRL_BOTTOM'}
               ,{field:'repMgrFeeRate', title: 'RepAssetDeptRegist001.repMgrFeeRate', width:200,sortField:'REP_MGR_FEE_RATE'}
               ,{field:'repTruFeeRate', title: 'RepAssetDeptRegist001.repTruFeeRate', width:200,type:'number',sortField:'REP_TRU_FEE_RATE'}
               ,{field:'repTradeFeeRate', title: 'RepAssetDeptRegist001.repTradeFeeRate', width:200,type:'number',type:'number',sortField:'REP_TRADE_FEE_RATE'}
               ,{field:'repAgencyFeeRate', title: 'RepAssetDeptRegist001.repAgencyFeeRate', width:200,type:'number',sortField:'REP_AGENCY_FEE_RATE'}
               ,{field:'repOtherFeeRate', title: 'RepAssetDeptRegist001.repOtherFeeRate', width:200,type:'number',sortField:'REP_OTHER_FEE_RATE'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //贵金属/商品             

       var dataTable13 = $('#dataTable13').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "013";
                 return param;
             }
         },          

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate',type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode',sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId',sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType',sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy',sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket',sortField:'REP_TRADE_MARKET'}
               ,{field:'repCountryArea13Str', title: 'RepAssetDeptRegist001.repCountryArea',sortField:'REP_COUNTRY_AREA'}
               ,{field:'repFinprodName13', title: 'RepAssetDeptRegist001.finprodMarketName',sortField:'REP_FINPROD_NAME'}
               ,{field:'repTerm', title: 'RepAssetDeptRegist001.repTermDay',type:'number',sortField:'REP_TERM'}
               ,{field:'assetValue', title: 'RepAssetDeptRegist001.repAssetValue',type:'number',sortField:'ASSET_VALUE'}
               ,{field:'repAssetYield', title: 'RepAssetDeptRegist001.repAssetYield',type:'number',sortField:'REP_ASSET_YIELD'}
               ],
               height:'400'
     });
       //非标准化债权类/权益类/代客境外理财投资QDII/新增可投资资产                                                                                       

       var dataTable14 = $('#dataTable14').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "014";
                 return param;
             }
         },              

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repFinprodName14', title: 'RepAssetDeptRegist001.finprodMarketName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repBankAssetTypeStr', title: 'RepAssetDeptRegist001.repBankAssetType', width:200,sortField:'REP_BANK_ASSET_TYPE'}
               ,{field:'repBankAssetTyprExplain', title: 'RepAssetDeptRegist001.repBankAssetTyprExplain', width:200,sortField:'REP_BANK_ASSET_TYPR_EXPLAIN'}
               ,{field:'repMoney', title: 'RepAssetDeptRegist001.repMoney', width:200,type:'number',sortField:'REP_MONEY'}
               ,{field:'repValueDate', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VALUE_DATE'}
               ,{field:'repExpiringDate', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:'REP_EXPIRING_DATE'}
               ,{field:'repCountryArea14Str', title: 'RepAssetDeptRegist001.repCountryArea', width:200,sortField:'REP_COUNTRY_AREA'}
               ,{field:'repContainExpectedYield2Str', title: 'RepAssetDeptRegist001.repRateFlag', width:200,sortField:'REP_RATE_FLAG'}
               ,{field:'repProjectYieldRate', title: 'RepAssetDeptRegist001.repProjYield', width:200,type:'number',sortField:'REP_PROJECT_YIELD_RATE'}
               ,{field:'repIntpayCycle', title: 'RepAssetDeptRegist001.repIntpayCycle', width:200,type:'number',sortField:'REP_INTPAY_CYCLE'}
               ,{field:'repFinancier14', title: 'RepAssetDeptRegist001.repFinancier', width:200,sortField:'REP_FINANCIER'}
               ,{field:'repFinancierOrgCode', title: 'RepAssetDeptRegist001.repFinOrgCode', width:200,sortField:'REP_FINANCIER_ORG_CODE'}
               ,{field:'repFcrGradeOut14', title: 'RepAssetDeptRegist001.repFcrGradeOut', width:200,sortField:'REP_FCR_GRADE_OUT'}
               ,{field:'repFinancierType114Str', title: 'RepAssetDeptRegist001.repFinancierType1', width:200,sortField:'REP_FINANCIER_TYPE1'}
               ,{field:'repFinancierType314Str', title: 'RepAssetDeptRegist001.repFinancierType3', width:200,sortField:'REP_FINANCIER_TYPE3'}
               ,{field:'repFinancierType214Str', title: 'RepAssetDeptRegist001.repFinancierType2', width:200,sortField:'REP_FINANCIER_TYPE2'}
               ,{field:'repFinanceProj14', title: 'RepAssetDeptRegist001.repFinanceProj', width:200,sortField:'REP_FINANCE_PROJ'}
               ,{field:'repIndustryStr14', title: 'RepAssetDeptRegist001.repIndustry1', width:200,sortField:'REP_INDUSTRY1'}
               ,{field:'repProIndustryStr14', title: 'RepAssetDeptRegist001.repProIndustry', width:200,sortField:'REP_PRO_INDUSTRY'}
               ,{field:'repIndustryFlag14Str', title: 'RepAssetDeptRegist001.repIndustryFlag', width:200,sortField:'REP_INDUSTRY_FLAG'}
               ,{field:'repMonitType14Str', title: 'RepAssetDeptRegist001.repMonitType', width:200,sortField:'REP_MONIT_TYPE'}
               ,{field:'repMonTypeComm14', title: 'RepAssetDeptRegist001.repMonTypeComm', width:200,sortField:'REP_MON_TYPE_COMM'}
               ,{field:'repOutGrade14Str', title: 'RepAssetDeptRegist001.repOutGrade', width:200,sortField:'REP_OUT_GRADE'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //另类资产                   

       var dataTable15 = $('#dataTable15').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "015";
                 return param;
             }
         },  

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repCountryArea15Str', title: 'RepAssetDeptRegist001.repCountryArea', width:200,sortField:'REP_COUNTRY_AREA'}
               ,{field:'repFinprodName15', title: 'RepAssetDeptRegist001.finprodMarketName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repVdate15', title: 'RepAssetDeptRegist001.vdate', width:200,type:'date',sortField:'REP_VDATE'}
               ,{field:'repMdate15', title: 'RepAssetDeptRegist001.mdate', width:200,type:'date',sortField:'REP_MDATE'}
               ,{field:'repAssetValue', title: 'RepAssetDeptRegist001.repAssetValue', width:200,type:'number',sortField:'REP_ASSET_VALUE'}
               ,{field:'repAssetYield', title: 'RepAssetDeptRegist001.repAssetYield', width:200,type:'number',sortField:'REP_ASSET_YIELD'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //公募基金/私募基金                                                  

       var dataTable16 = $('#dataTable16').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "016";
                 return param;
             }
         },                             

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'finprodMarketId16', title: 'RepAssetDeptRegist001.funIds', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repFinprodName16', title: 'RepAssetDeptRegist001.funNames', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repIndustryStr16', title: 'RepAssetDeptRegist001.repIndustry', width:200,sortField:'REP_INDUSTRY'}
               ,{field:'repRegistOrgStr', title: 'RepAssetDeptRegist001.repRegistOrgFun', width:200,sortField:'REP_REGIST_ORG'}
               ,{field:'repIsGovInvestStr', title: 'RepAssetDeptRegist001.repIsGovInvest', width:200,sortField:'REP_IS_GOV_INVEST'}
               ,{field:'repGovInvestForStr', title: 'RepAssetDeptRegist001.repGovInvestFor', width:200,sortField:'REP_GOV_INVEST_FOR'}
               ,{field:'repFundCompName', title: 'RepAssetDeptRegist001.repFundCompName', width:200,sortField:'REP_FUND_COMP_NAME'}
               ,{field:'repMngPartyName', title: 'RepAssetDeptRegist001.repMngPartyName', width:200,sortField:'REP_MNG_PARTY_NAME'}
               ,{field:'repTrustPartyName', title: 'RepAssetDeptRegist001.repTrustPartyName', width:200,sortField:'REP_TRUST_PARTY_NAME'}
               ,{field:'repInvestStageStr', title: 'RepAssetDeptRegist001.repInvestStage', width:200,sortField:'REP_INVEST_STAGE'}
               ,{field:'repPartyType1_16Str', title: 'RepAssetDeptRegist001.repPartyType1', width:200,sortField:'REP_PARTY_TYPE1'}
               ,{field:'repPartyType3_16Str', title: 'RepAssetDeptRegist001.repPartyType3', width:200,sortField:'REP_PARTY_TYPE3'}
               ,{field:'repPartyType2_16Str', title: 'RepAssetDeptRegist001.repPartyType2', width:200,sortField:'REP_PARTY_TYPE2'}
               ,{field:'repFundInvestAsset', title: 'RepAssetDeptRegist001.repFundInvestAsset', width:200,sortField:'REP_FUND_INVEST_ASSET'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
     });
       //委外投资——协议方式                                                                         

       var dataTable17 = $('#dataTable17').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "017";
                 return param;
             }
         },                                                 
                      

         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repFinprodName17', title: 'RepAssetDeptRegist001.secName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'secId', title: 'RepAssetDeptRegist001.secId', width:200,sortField:'SEC_ID'}
               ,{field:'repTrustee', title: 'RepAssetDeptRegist001.repTrustee', width:200,sortField:'REP_TRUSTEE'}
               ,{field:'repActualManager', title: 'RepAssetDeptRegist001.repActualManager', width:200,sortField:'REP_ACTUAL_MANAGER'}
               ,{field:'repCustodian', title: 'RepAssetDeptRegist001.repTrusteeId', width:200,sortField:'REP_ENTRUST_INVEST_MONEY'}
               ,{field:'repEntrustInvestMoney', title: 'RepAssetDeptRegist001.repEntrustInvestMoney', width:200,type:'number',sortField:'REP_ENTRUST_INVEST_MONEY'}
               ,{field:'repCapiInvestStr', title: 'RepAssetDeptRegist001.repCapiInvest', width:200,sortField:'REP_CAPI_INVEST'}
               ,{field:'repApplicationMode', title: 'RepAssetDeptRegist001.repCapUseWay', width:200,sortField:'REP_CAP_USE_INDU'}
               ,{field:'repCapUseInduStr', title: 'RepAssetDeptRegist001.repCapUseIndu', width:200,sortField:'REP_CAP_USE_INDU'}
               ,{field:'repInvestStartdate', title: 'RepAssetDeptRegist001.repInvestStartdate', width:200,type:'date',sortField:'REP_INVEST_ENDDATE'}
               ,{field:'repInvestEnddate', title: 'RepAssetDeptRegist001.repInvestEnddate', width:200,type:'date',sortField:''}
               ,{field:'repTrustInvestAttributeStr', title: 'RepAssetDeptRegist001.repTrustInvestAttribute', width:200,sortField:'REP_TRUST_INVEST_ATTRIBUTE'}
               ,{field:'repRateFlagStr', title: 'RepAssetDeptRegist001.repRateFlag', width:200,sortField:'REP_RATE_FLAG'}
               ,{field:'repMaxSxpectedYield', title: 'RepAssetDeptRegist001.repMaxRate', width:200,type:'number',sortField:'REP_MAX_SXPECTED_YIELD'}
               ,{field:'repMinExpectedYield', title: 'RepAssetDeptRegist001.repMinRate', width:200,type:'number',sortField:'REP_MIN_EXPECTED_YIELD'}
               ,{field:'repManagementRate', title: 'RepAssetDeptRegist001.repMgrFeeRate', width:200,type:'number',sortField:'REP_MANAGEMENT_RATE'}
               ,{field:'repTrustRate', title: 'RepAssetDeptRegist001.repTruFeeRate', width:200,type:'number',sortField:'REP_TRUST_RATE'}
               ,{field:'repTradeSumRate', title: 'RepAssetDeptRegist001.repTradeFeeRate', width:200,type:'number',sortField:'REP_TRADE_SUM_RATE'}
               ,{field:'repIntermediarySumRate', title: 'RepAssetDeptRegist001.repAgencyFeeRate', width:200,type:'number',sortField:'REP_INTERMEDIARY_SUM_RATE'}
               ,{field:'repOtherSumRate', title: 'RepAssetDeptRegist001.repOtherFeeRate', width:200,type:'number',sortField:'REP_OTHER_SUM_RATE'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
       });
       //其他（负债）类资产              

       var dataTable18 = $('#dataTable18').joyintable({
         ajax: {
             url: 'searchPage',
             type: 'POST',
             data: function() {
                 var param = getPageParams();
                 param.tableNum = "018";
                 return param;
             }
         },           
                           
         cols:[
               {field:'repStatusDate', title: 'RepAssetDeptRegist001.repStatusDate', width:200,type:'date',sortField:'REP_STATUS_DATE'}
               ,{field:'finprodMarketId', title: 'RepAssetDeptRegist001.assetAndCode', width:200,sortField:'FINPROD_MARKET_ID'}
               ,{field:'repIssuePartyId', title: 'RepAssetDeptRegist001.repIssuePartyId', width:200,sortField:'REP_ISSUE_PARTY_ID'}
               ,{field:'repAssetTypeStr', title: 'RepAssetDeptRegist001.repAssetType', width:200,sortField:'REP_ASSET_TYPE'}
               ,{field:'ccyStr', title: 'RepAssetDeptRegist001.ccy', width:200,sortField:'REP_CCY'}
               ,{field:'repTradeMarketStr', title: 'RepAssetDeptRegist001.repTradeMarket', width:200,sortField:'REP_TRADE_MARKET'}
               ,{field:'repCountryArea18Str', title: 'RepAssetDeptRegist001.repCountryArea', width:200,sortField:'REP_COUNTRY_AREA'}
               ,{field:'repFinprodName18', title: 'RepAssetDeptRegist001.finprodMarketName', width:200,sortField:'REP_FINPROD_NAME'}
               ,{field:'repTerm', title: 'RepAssetDeptRegist001.repTermDay', width:200,type:'number',sortField:'REP_TERM'}
               ,{field:'repLiabilitiesMoney', title: 'RepAssetDeptRegist001.repLiabilitiesMoney', width:200,type:'number',sortField:'REP_LIABILITIES_MONEY'}
               ,{field:'repInterestRate', title: 'RepAssetDeptRegist001.repInterestRate', width:200,type:'number',sortField:'REP_INTEREST_RATE'}
               ],
               done:function(){
             	  check();
               },
               height:'400'
       });
       
       //导出
       var exportExcel = function(){       
           var table = null;
           table = $('#dataTable'+searchTable).data('joyintable');
           if(table!=null && table!=''){
               var selArr = table.getAllCheckedRows();
           }
           if (!selArr || selArr.length == 0) {
               Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
               return;
           }
           Joyin.exportExcel('export', getParamObj(searchTable));
       };
       
       var getParamObj = function(searchTable){
           var table = null;
           table = $('#dataTable'+searchTable).data('joyintable');
           if(table!=null && table!=''){
               var selArr = table.getAllCheckedRows();
           }
           var primaryKey = new Array();
           if (selArr || selArr.length > 0) {
               $.each(selArr, function(i, v) {  
                   if(v.repFinprodId != null && v.repFinprodId != ''){
                       primaryKey[i] = v.repFinprodId;
                   }
               });
           }
           return {
               primaryKey:primaryKey,
               tableNum:searchTable
           }
       }
       layui.use('element', function(){
           var element = layui.element;
           
           element.on('tab(docDemoTabBrief)', function(data){
               console.log(this); //当前Tab标题所在的原始DOM元素
               console.log(data.index); //得到当前Tab的所在下标
               console.log(data.elem); //得到当前的Tab大容器
               searchTable = data.index + 1;
             });
           });
       
       var load = function(){
   		dataTable1.load();
   	    dataTable2.load();
   	    dataTable3.load();
   	    dataTable4.load();
   	    dataTable5.load();
   	    dataTable6.load();
   	    dataTable7.load();
   	    dataTable8.load();
   	    dataTable9.load();
   	    dataTable10.load();
   	    dataTable11.load();
   	    dataTable12.load();
   	    dataTable13.load();
   	    dataTable14.load();
   	    dataTable15.load();
   	    dataTable16.load();
   	    dataTable17.load();
   	    dataTable18.load();
   	}
       
       $(function () {
           
//           $('span[lay-stope=tabmore]').css({'left':700+'px','top':'0px','height':$('ul.layui-tab-title').height()+'px'});
//           $('ul').css('width',document.body.clientWidth +'px');
           $("li").on('click',function(){
        	   if(flag){
	        	   for(var i=1; i<= 18;i++){
	                   $('#tab'+i).css('display','');
	               }
               
            	   var newLeft = null;
                   var oldLeft = $('#tab1').offset().left;
                   for(var i=1; i<= 18;i++){
                       if('tab'+i == this.id && i > 3 && i<15){
                           newLeft = $('#tab'+1).offset().left - $('#tab'+(i-3)).offset().left-2;
                           $('#tab1').css('margin-left',newLeft+'px');
                       }else if('tab'+i == this.id && i<=3){
                           newLeft = 0;
                           $('#tab1').css('margin-left',0+'px');
                       }else if('tab'+i == this.id && i >= 15){
                           newLeft = $('#tab'+1).offset().left - $('#tab'+(14-3)).offset().left-2;
                           $('#tab1').css('margin-left',newLeft+'px');
                       }
                   }
                   for(var i=18; i>= 1;i--){
                       if(newLeft - oldLeft + $('#tab'+i).offset().left + $('#tab'+i).width() > $('.condition-area').width()){
                           $('#tab'+i).css('display','none');
                       }
                   }
               }
               
           })
           
           // 绑定按钮事件
           $('#RepAssetDeptRegist001_btn_search').click(function() {
               /*if($('#assetType').val() != null && $('#assetType').val() != ''){
                   $('#tab'+$('#assetType').val()).trigger('click');
               }*/
               /*dataTable1.load();
               dataTable2.load();
               dataTable3.load();
               dataTable4.load();
               dataTable5.load();
               dataTable6.load();
               dataTable7.load();
               dataTable8.load();
               dataTable9.load();
               dataTable10.load();
               dataTable11.load();
               dataTable12.load();
               dataTable13.load();
               dataTable14.load();
               dataTable15.load();
               dataTable16.load();
               dataTable17.load();
               dataTable18.load();*/
               /*var timeOut = setTimeout(function(){
                   for(var j=1;j<=18;j++){
                       if($('#liabilityType').val() && $('#liabilityType').val() !='' 
                           && $('#assetType').val() && $('#assetType').val() !=''){
                           if($('#dataTable'+j).data().joyintable.getAllRows() && $('#dataTable'+j).data().joyintable.getAllRows().length>0){
                               $('#tab'+j).css('display','');
                           }else{
                               $('#tab'+j).css('display','none');
                           }
                           
                       }
                   }
                   clearTimeout(timeOut);
               },0);*/
        	   load();
        	   check1();
           });
           $('#RepAssetDeptRegist001_btn_export').click(function() {
               exportExcel();
           });
       });
}());
function check(){
	if($('#liabilityType').val() && $('#liabilityType').val() !=''){
		flag = false;
		for(var j=1;j<=18;j++){
			$('#tab'+j).css('display','none');
		}
		var arr = $('#liabilityType').val();
		$.each(arr,function(index,val){
			if($.inArray(val,arr1)!=-1){
				$('#tab1').css('display','');
				$('#tab1').trigger('click');
			}
			if($.inArray(val,arr2)!=-1){
				$('#tab2').css('display','');
				$('#tab2').trigger('click');
			}
			if($.inArray(val,arr3)!=-1){
				$('#tab3').css('display','');
				$('#tab3').trigger('click');
			}
			if($.inArray(val,arr4)!=-1){
				$('#tab4').css('display','');
				$('#tab4').trigger('click');
			}
			if($.inArray(val,arr5)!=-1){
				$('#tab5').css('display','');
				$('#tab5').trigger('click');
			}
			if($.inArray(val,arr6)!=-1){
				$('#tab6').css('display','');
				$('#tab6').trigger('click');
			}
			if($.inArray(val,arr7)!=-1){
				$('#tab7').css('display','');
				$('#tab7').trigger('click');
			}
			if($.inArray(val,arr8)!=-1){
				$('#tab8').css('display','');
				$('#tab8').trigger('click');
			}
			if($.inArray(val,arr9)!=-1){
				$('#tab9').css('display','');
				$('#tab9').trigger('click');
			}
			if($.inArray(val,arr10)!=-1){
				$('#tab10').css('display','');
				$('#tab10').trigger('click');
			}
			if($.inArray(val,arr11)!=-1){
				$('#tab11').css('display','');
				$('#tab11').trigger('click');
			}
			if($.inArray(val,arr12)!=-1){
				$('#tab12').css('display','');
				$('#tab12').trigger('click');
			}
			if($.inArray(val,arr13)!=-1){
				$('#tab13').css('display','');
				$('#tab13').trigger('click');
			}
			if($.inArray(val,arr14)!=-1){
				$('#tab14').css('display','');
				$('#tab14').trigger('click');
			}
			if($.inArray(val,arr15)!=-1){
				$('#tab15').css('display','');
				$('#tab15').trigger('click');
			}
			if($.inArray(val,arr16)!=-1){
				$('#tab16').css('display','');
				$('#tab16').trigger('click');
			}
			if($.inArray(val,arr17)!=-1){
				$('#tab17').css('display','');
				$('#tab17').trigger('click');
			}
			if($.inArray(val,arr18)!=-1){
				$('#tab18').css('display','');
				$('#tab18').trigger('click');
			}
		})
		/*for(var j=1;j<=18;j++){
				if($('#dataTable'+j).data().joyintable.getAllRows().length>0){
	                $('#tab'+j).css('display','');
	            }else{
	                $('#tab'+j).css('display','none');
	            }
        }*/
	}
	if($('#assetCode').val() != null && $('#assetCode').val() != ''){
		flag = false;
		for(var j=1;j<=18;j++){
            if($('#dataTable'+j).data().joyintable.getAllRows().length>0){
                $('#tab'+j).css('display','');
                $('#tab'+j).trigger('click');
            }else{
                $('#tab'+j).css('display','none');
            }
        }
	}
}
function check1(){
	if(($('#liabilityType').val() == null || $('#liabilityType').val() =='') 
	        && ($('#assetType').val() && $('#assetType').val() !='')
	        && ($('#assetCode').val() == null || $('#assetCode').val() == '')){
			flag = false;
			for(var j=1;j<=18;j++){
				$('#tab'+j).css('display','none');
			}
			if($('#assetType').val() == "1"){
				$('#tab1').css('display','');
				$('#tab1').trigger('click');
			}
			else if($('#assetType').val() == "2"){
				$('#tab2').css('display','');
				$('#tab2').trigger('click');
			}
			else if($('#assetType').val() == "3"){
				$('#tab3').css('display','');
				$('#tab3').trigger('click');
			}
			else if($('#assetType').val() == "4"){
				$('#tab2').css('display','');
				$('#tab2').trigger('click');
			}
			else if($('#assetType').val() == "5"){
				$('#tab2').css('display','');
				$('#tab2').trigger('click');
			}
			else if($('#assetType').val() == "6"){
				$('#tab4').css('display','');
				$('#tab14').css('display','');
				$('#tab4').trigger('click');
			}
			else if($('#assetType').val() == "7"){
				$('#tab4').css('display','');
				$('#tab5').css('display','');
				$('#tab14').css('display','');
				$('#tab4').trigger('click');
			}
			else if($('#assetType').val() == "8"){
				$('#tab6').css('display','');
				$('#tab14').css('display','');
				$('#tab6').trigger('click');
			}
			else if($('#assetType').val() == "9"){
				$('#tab7').css('display','');
				$('#tab7').trigger('click');
			}
			else if($('#assetType').val() == "10"){
				$('#tab8').css('display','');
				$('#tab9').css('display','');
				$('#tab10').css('display','');
				$('#tab11').css('display','');
				$('#tab14').css('display','');
				$('#tab8').trigger('click');
			}
			else if($('#assetType').val() == "11"){
				$('#tab13').css('display','');
				$('#tab13').trigger('click');
			}
			else if($('#assetType').val() == "12"){
				$('#tab15').css('display','');
				$('#tab15').trigger('click');
			}
			else if($('#assetType').val() == "13"){
				$('#tab16').css('display','');
				$('#tab16').trigger('click');
			}
			else if($('#assetType').val() == "14"){
				$('#tab16').css('display','');
				$('#tab16').trigger('click');
			}
			else if($('#assetType').val() == "15"){
				$('#tab12').css('display','');
				$('#tab12').trigger('click');
			}
			else if($('#assetType').val() == "16"){
				$('#tab17').css('display','');
				$('#tab17').trigger('click');
			}
			else if($('#assetType').val() == "17"){
				$('#tab3').css('display','');
				$('#tab3').trigger('click');
			}
			else if($('#assetType').val() == "18"){
				$('#tab18').css('display','');
				$('#tab18').trigger('click');
			}
		}
	if(($('#liabilityType').val() == null || $('#liabilityType').val() =='') 
	        && ($('#assetType').val() == null || $('#assetType').val() =='')
	        && ($('#assetCode').val() == null || $('#assetCode').val() == '')){
		flag = true;
		for(var i=1; i<= 18;i++){
            $('#tab'+i).css('display','');
        }
	}
}


