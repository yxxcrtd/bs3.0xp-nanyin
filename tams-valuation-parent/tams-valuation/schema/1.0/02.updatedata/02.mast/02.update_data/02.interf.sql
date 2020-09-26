/**ADD                                             2018-09-20                                             START**/
/**基金红利再投接口增加币种字段  add by jinmaodong  start**/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValBonusResTrade' AND COL_E_NAME = 'ccy';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('基金红利再投交易','ValBonusResTrade','币种','ccy','1','Y','50','1');
/**基金红利再投接口增加币种字段  add by jinmaodong end**/
/**ADD                                             2018-09-19                                              END**/

/**ADD                                             2018-09-21                                             START**/

/**资讯接口-中债估值：全价取值 日终估价全价 add by wujie start**/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME ='CBondAnalysisCNBD' 
AND COL_E_NAME IN ('bAnalPriceCnbd','bAnalDirtyCnbd');

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('中债登估值','CBondAnalysisCNBD','日终估价全价','bAnalPriceCnbd','3','Y','30','1');

DELETE FROM VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='CBondAnalysisCNBD'
AND TMP_COL_E_NAME IN ('bAnalPriceCnbd','bAnalDirtyCnbd');

insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalPriceCnbd',
'日终估价全价','price','市价全价','system','',sysdate,'system',sysdate);
/**资讯接口-中债估值：全价取值 日终估价全价 add by wujie  end**/

/**ADD                                             2018-09-25                                            START**/
--红利再投接口新增币种校验规则
delete VAL_TEMP_COL_CHECK_INFO a where TMP_E_NAME='ValBonusResTrade' and COL_E_NAME='ccy';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('基金红利再投交易','ValBonusResTrade','币种','ccy','1','Y','50','1');

/**add by wujie start remark:债券回购标的增加“登记托管机构”、“押品只数”**/
delete from VAL_TEMP_COL_CHECK_INFO where tmp_e_name='ValRepDealSecSubject'
and COL_E_NAME in ('registOrg','pledgeSecNum');

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('债券回购标的','ValRepDealSecSubject','登记托管机构','registOrg','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('债券回购标的','ValRepDealSecSubject','押品只数','pledgeSecNum','3','Y','10','1');

/**add by wujie end remark:债券回购标的增加“登记托管机构”、“押品只数”**/

/**add by wujie start remark:债券回购交易接口规范变更**/
delete from VAL_TEMP_COL_CHECK_INFO where tmp_e_name='ValRepDealSec';

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','产品代码','prodId','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','数据动作标志','dataActionSign','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','成交编号','tradeId','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','期限品种','termKind','1','N','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易场所','tradeMarket','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','业务类型','busiType','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易方向','ps','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易员','trader','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','通道代码','chlId','1','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易日','tradeDate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交割日','settleDate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','起息日','vdate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','到期日','mdate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','首次净价','cprice','3','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','首次全价','fprice','3','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','到期净价','mcprice','3','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','到期全价','mfprice','3','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','首次结算金额','firstAmt','3','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','到期结算金额','endAmt','3','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','利息金额','intAmt','3','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','币种','ccy','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','本金收益特征','capiProfFeature','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易对手','counterId','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易对手类型','counterType','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','登记托管机构','registOrg','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易费用','tradeFee','3','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','原交易编号','pTradeId','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','有无清算指令','isClean','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易费用是否随交易支付','isTradeFee','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','印花税','stampDuty','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','印花税是否随交易支付','isStampDuty','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','经手费','brokerage','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','经手费是否随交易支付','isBrokerage','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','证管费','certfFee','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','证管费是否随交易支付','isCertfFee','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','结算费','stlAmt','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','结算费是否随交易支付','isStlAmt','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','佣金','commission','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','佣金是否随交易支付','isCommission','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易手续费','dealFeeAmt','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','交易手续费是否随交易支付','isDealFeeAmt','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','结算手续费','stlFeeAmt','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','结算手续费是否随交易支付','isStlFeeAmt','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','通道费','chlFeeAmt','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券回购交易','ValRepDealSec','通道费是否随交易支付','isChlFeeAmt','1','Y','50','1');

/**add by wujie end remark:债券回购交易接口规范变更**/

/**add by wujie start remark:债券买卖交易接口规范变更**/

delete from VAL_TEMP_COL_CHECK_INFO where tmp_e_name='ValSecVestAssetDeal';

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','产品代码','prodId','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','资产代码','assetId','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','债券代码','secId','1','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','债券简称','secAbbr','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','数据动作标志','dataActionSign','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易场所','tradeMarket','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易日','dealDate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交割日','settdateDate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易方向','ps','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','结算方式','stlForm','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','成交数量','dealNum','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','单位面值','parValue','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','未随交易支付费用','feeAmtUnpay','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','到期收益率','yield','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易对手','counterId','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易对手类型','counterType','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易员','trader','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','证券托管户','secManageAcctId','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','通道代码','chlId','1','N','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','净价','cprice','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','全价','fprice','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','应收利息','unitInt','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','应收利息总额','intAmt','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','净价总额','cpriceAmt','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','全价总额','fpriceAmt','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','币种','ccy','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','成交编号','tradeId','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','投资目的','investAim','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','印花税','stampDuty','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','经手费','brokerage','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','证管费','certfFee','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','结算费','stlAmt','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','佣金','commission','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易手续费','dealFeeAmt','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','结算手续费','stlFeeAmt','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','通道费','chlFeeAmt','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易金额','tradeAmt','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','登记结算机构','registAgency','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','有无清算指令','isClean','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','行权收益率','exeYield','3','N','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','印花税是否随交易支付','isStampDuty','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','经手费是否随交易支付','isBrokerage','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','证管费是否随交易支付','isCertfFee','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','结算费是否随交易支付','isStlAmt','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','佣金是否随交易支付','isCommission','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','交易手续费是否随交易支付','isDealFeeAmt','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','结算手续费是否随交易支付','isStlFeeAmt','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('债券买卖交易','ValSecVestAssetDeal','通道费是否随交易支付','isChlFeeAmt','1','Y','50','1');

/**add by wujie end remark:债券买卖交易接口规范变更**/
/**ADD                                             2018-09-25                                              END**/

/**add                                              2018-09-28                            start**/

/**接口字段check更新 add by wujie start **/
delete from VAL_TEMP_COL_CHECK_INFO where tmp_e_name='CBondAnalysisCNBD' and COL_E_NAME='bAnalCredibility';

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('中债登估值','CBondAnalysisCNBD','可信度','bAnalCredibility','1','N','50','1');

/**接口字段check更新 add by wujie end **/

/**接口模板DTO匹配表-中债登估值  add by wujie start **/

delete from VAL_INTERF_DTO_MATCH where TMP_E_NAME='CBondAnalysisCNBD';
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','sInfoWindcode','Wind代码','secId','债券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','tradeDt','交易日期','valueDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalMatuCnbd','待偿期(年)','lastPeriod','剩余期限','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalPriceCnbd','日终估价全价','price','市价全价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalNetCnbd','估价净价','netprice','市价净价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalModiduraCnbd','估价修正久期','mDuration','市价久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalCnvxtyCnbd','估价凸性','mConvexity','市价凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalVobpCnbd','估价基点价值','bpvalue','基点价值','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalSprduraCnbd','估价利差久期','sduration','利差久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalSprcnxtCnbd','估价利差凸性','scnvxty','利差凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalInterestdurationCnbd','估价利率久期','interestDuration','利率久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalInterestcnvxtyCnbd','估价利率凸性','interestCnvxty','利率凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalYieldCnbd','估价收益率(%)','bpyield','市价收益率','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','bAnalCredibility','可信度','relibity','是否推荐','system','',sysdate,'system',sysdate);
/**接口模板DTO匹配表-中债登估值  add by wujie end **/

/**add                                         2018-09-28                            end**/
/**add                                         2018-09-29                            start**/

/**存款类资产基础信息：初始利率、挂钩利率字段类型调整 add by wujie start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValBotAstInfo' and COL_E_NAME in ('initRate','linkRate');

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('存款类资产基础信息','ValBotAstInfo','初始利率','initRate','3','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('存款类资产基础信息','ValBotAstInfo','挂钩利率','linkRate','1','N','30','1');
/**存款类资产基础信息：初始利率、挂钩利率字段类型调整 add by wujie end**/

/**货币型产品浮动管理费计提规则、净值型产品浮动管理费计提规则:计提频率值：由数值型改为字符型 add by wujie start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValPrdFloatFeeC' and COL_E_NAME='accrDayValue';

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST)
 values ('货币型产品浮动管理费计提规则','ValPrdFloatFeeC','计提频率值','accrDayValue','1','N','50','1');

delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValPrdFloatFeeN' and COL_E_NAME='accrDayValue';

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST)
 values ('净值型产品浮动管理费计提规则','ValPrdFloatFeeN','计提频率值','accrDayValue','1','N','50','1');

 /**货币型产品浮动管理费计提规则、净值型产品浮动管理费计提规则:计提频率值：由数值型改为字符型 add by wujie end**/

/**add                                         2018-09-29                            end**/
/**add                                         2018-10-08                            start**/
/**基金红利再投交易:增加分红截止日字段 add by wujie start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBonusResTrade' and COL_E_NAME='mdate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('基金红利再投交易','ValBonusResTrade','分红截止日','mdate','2','Y','10','','1');
/**基金红利再投交易:增加分红截止日字段 add by wujie end**/
/**add                                         2018-10-08                            end**/

/**add                                         2018-10-11                            start**/





/**add                                         2018-10-12                            start**/
/**add by leipeng  债券买断式回购派息交易 新增交易场所*/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValRepDividendDeal' AND COL_E_NAME='tradeMarket';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('债券买断式回购派息交易','ValRepDividendDeal','交易场所','tradeMarket','1','Y','50','1');

/**add                                         2018-10-12                            end**/

/**add                                         2018-10-15                            start**/
/**基金买入卖出脚本 add by leipeng start**/
/**check配置*/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValFundTrade';
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','产品代码','prodId','1','Y','32','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','数据动作标志','dataActionSign','1','Y','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','成交编号','tradeId','1','Y','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','业务类型','busiType','1','Y','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','交易方向','ps','1','Y','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','单位净价','unitCprice','3','Y','30','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','资产代码','assetId','1','Y','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','资产名称','assetName','1','Y','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','交易场所','tradeMarket','1','Y','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','交易日','tradeDate','2','Y','10','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','交易本金','prinAmt','3','Y','30','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','交易份额','shareAmt','3','Y','30','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','应收红利','intAmt','3','N','30','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','交易金额','tradeAmt','3','Y','30','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','币种','ccy','1','Y','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','交易对手','counterId','1','N','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','交易对手类型','counterType','1','N','50','1','','','','','','');
INSERT INTO VAL_TEMP_COL_CHECK_INFO VALUES ('基金买入卖出交易','ValFundTrade','有无清算指令','isClean','1','Y','50','1','','','','','','');

/**接口配置**/
DELETE FROM VAL_INTERF_TEMPLATE WHERE TMP_E_NAME = 'ValFundTrade';
insert into VAL_INTERF_TEMPLATE values ('基金买入卖出交易','ValFundTrade','ValFundTrade','100109','01','ZG','system','',sysdate,'system',sysdate);
DELETE FROM VAL_INTERF_DEFINE WHERE INTERF_ID = '100109';
insert into VAL_INTERF_DEFINE values ('100109','基金买入卖出交易','ValFundTrade','2071','system','',sysdate,'system',sysdate);
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID = 'ValFundTrade';
insert into BOK_BATCH_TASK_GRP values ('ValFundTrade','基金买入卖出交易','InterfDeal','2071','ValFundTrade',100109,'system',sysdate,'system',sysdate,'systeam');

/**基金买入卖出脚本 add by leipeng end**/

/**节假日接口check add by wujie start**/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValHolidayInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('节假日信息','ValHolidayInfo','交易场所','tradeMarket','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('节假日信息','ValHolidayInfo','日期','cdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('节假日信息','ValHolidayInfo','是否为节假日','isHoliday','3','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('节假日信息','ValHolidayInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
/**节假日接口check add by wujie end**/
/**add   
 *                                             2018-10-15                            end**/

/**add                                         2018-10-16                            start**/
/**基金现金分红交易增加“分红截止日” add by wujie  start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValCashBonusTrade' and COL_E_NAME='mdate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('基金现金分红交易','ValCashBonusTrade','分红截止日','mdate','2','Y','10','','');				
/**基金现金分红交易增加“分红截止日” add by wujie  end**/

/** 货币/净值费用信息添加字段check start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME ='ValPrdFeeInfoC' and COL_E_NAME='prodAbbr';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币型产品费用基础信息','ValPrdFeeInfoC','产品代码','prodAbbr','1','N','50','','1');
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME ='ValPrdFeeInfoN' and COL_E_NAME='prodAbbr';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('净值型产品费用基础信息','ValPrdFeeInfoN','产品代码','prodAbbr','1','N','50','','1');
/** 货币/净值费用信息添加字段check end**/

/**接口-节假日信息校验脚本 start**/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValHolidayInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('节假日信息','ValHolidayInfo','交易场所','tradeMarket','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('节假日信息','ValHolidayInfo','日期','cdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('节假日信息','ValHolidayInfo','是否为节假日','isHoliday','3','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('节假日信息','ValHolidayInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
/**接口-节假日信息校验脚本 end**/
/**add                                         2018-10-16                            start**/
/**add                                        2018-10-18                            begin**/
--added by huwei 2018-10-17 费用交易接口 start
--接口批次
delete from VAL_INTERF_TASK where task_id = '33';
insert into VAL_INTERF_TASK values ('33','feeDealRecRunner','费用交易-交易数据接口','2111','E','R','ZG','system','',sysdate,'system',sysdate);

--接口定义
delete from VAL_INTERF_DEFINE where INTERF_ID = '100110';
insert into VAL_INTERF_DEFINE values ('100110','费用交易','ValFeeDeal','2111','system','',sysdate,'system',sysdate);

--接口模板
delete from VAL_INTERF_TEMPLATE where TMP_E_NAME = 'ValFeeDeal';
insert into VAL_INTERF_TEMPLATE values ('费用交易','ValFeeDeal','ValFeeDeal','100110','01','ZG','system','',sysdate,'system',sysdate);

--接口字段check
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValFeeDeal';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','产品代码','prodId','1','Y','32','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','数据动作标志','dataActionSign','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','产品名称','prodName','1','Y','200','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','成交编号','tradeId','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','费用类型','feeType','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','交易方向','ps','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','交易日','tradeDate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','交割日','settdateDate','2','Y','10','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','交易币种','ccy','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','实付费用','dealAmt','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','有无清算指令','isClean','1','Y','50','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) values ('费用交易','ValFeeDeal','登记托管机构','registOrg','1','Y','50','1');


--菜单
delete from BOK_BATCH_TASK_GRP where task_grp_id = '2111' or P_TASK_GRP_ID = '2111';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('2111','费用交易','InterfDeal','','2111',11,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValFeeDeal','费用交易','InterfDeal','2111','ValFeeDeal',100099,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');


--added by huwei 2018-10-17 费用交易接口 end

/**add                                       2018-10-18                            end**/





/**add                                       2018-10-23                            start**/
/**add by wujie on 20181023 start remark:计划类资产（净值型）基础信息：起息日、到期日由必填项改为非必填项**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValMngAssetInfoN' and COL_E_NAME in ('vdate','mdate');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('计划类资产（净值型）基础信息','ValMngAssetInfoN','起息日','vdate','2','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('计划类资产（净值型）基础信息','ValMngAssetInfoN','到期日','mdate','2','N','10','','1');
/**add by wujie on 20181019 end**/


/**add by wujie on 20181023 start remark:机构信息接口：企业性质、核算分类:由必填项改为非必填**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValAgencyInfo' and COL_E_NAME in ('corporationType','valClas');

insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('市场机构信息','ValAgencyInfo','企业性质','corporationType','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('市场机构信息','ValAgencyInfo','核算分类','valClas','1','N','50','','1');
/**add by wujie on 20181023 end remark:机构信息接口：企业性质、核算分类:由必填项改为非必填**/

/**add                                       2018-10-23                            end**/

/**add                                       2018-10-24                            begin**/

--added by huwei 2018-10-24 委外行情-基础数据接口 start
--接口任务批次
delete from VAL_INTERF_TASK where task_id ='34';
insert into VAL_INTERF_TASK values ('34','hqOutsourceInfoRecRunner','资讯数据(委外)','3081','E','R','ZG','system','',sysdate,'system',sysdate);


--接口定义
delete from VAL_INTERF_DEFINE where INTERF_ID='100111';
insert into VAL_INTERF_DEFINE values ('100111','委外(非公募基金)行情','ValNfundRate','3081','system','',sysdate,'system',sysdate);

--接口模板
delete from VAL_INTERF_TEMPLATE where TMP_E_NAME='ValNfundRate';
insert into VAL_INTERF_TEMPLATE values ('委外(非公募基金)行情','ValNfundRate','ValNfundRate','100111','01','ZG','system','',sysdate,'system',sysdate);

--字段check
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValNfundRate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('委外(非公募基金)行情','ValNfundRate','金融产品代码','finprodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('委外(非公募基金)行情','ValNfundRate','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('委外(非公募基金)行情','ValNfundRate','公布日期','publishDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('委外(非公募基金)行情','ValNfundRate','估值日期','valDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('委外(非公募基金)行情','ValNfundRate','估值','valuation','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('委外(非公募基金)行情','ValNfundRate','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');

--菜单

delete from BOK_BATCH_TASK_GRP where task_grp_id = 'ValNfundRate' or task_grp_id='3081';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('3081','资讯行情(委外)','InterfData','','3081',100200,'admin',to_date('2018-11-01','yyyy-MM-dd'),'admin',to_date('2018-11-01','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValNfundRate','委外(非公募基金)行情','InterfData','3081','ValNfundRate',100101,'admin',to_date('2018-09-18','yyyy-MM-dd'),'admin',to_date('2018-09-18','yyyy-MM-dd'),'');

--added by huwei 2018-10-24 委外行情-基础数据接口 end

/**add                                       2018-10-25                            begin**/
/** add by wangqidong 20181025 存款类资产接口配置脚本 start **/
/**接口定义表增加存款类资产 add by wangqidong**/
DELETE FROM VAL_INTERF_DEFINE WHERE ORDERNO = '1071';
insert into VAL_INTERF_DEFINE values ('100112','存款类资产还本计划','ValBotAstCapPlan','1071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100113','存款类资产还本付息规则','ValBotAstRepayCapInt','1071','system','',sysdate,'system',sysdate);

-- 接口模板名称
DELETE FROM VAL_INTERF_TEMPLATE WHERE TMP_E_NAME = 'ValBotAstCapPlan' OR TMP_E_NAME = 'ValBotAstRepayCapInt';
insert into VAL_INTERF_TEMPLATE values ('存款类资产还本计划','ValBotAstCapPlan','ValBotAstCapPlan','100112','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('存款类资产还本付息规则','ValBotAstRepayCapInt','ValBotAstRepayCapInt','100113','01','ZG','system','',sysdate,'system',sysdate);

/**模板字段校验信息配置表增加存款类资产 add by wangqidong **/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValBotAstRepayCapInt';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','交易类型','dealType','1','Y','50','PS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','生效日','effDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','还本是否带息','isInt','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','付息频率','payCycle','1','N','50','FREQUENCY_UNIT','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','还本频率','capCycle','1','N','50','FREQUENCY_UNIT','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','营业日准则','tdayRule','1','Y','50','TDAY_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','推算方式','calcRule','1','Y','50','CALC_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','首次付息日','firstPayDate','2','N','10','','');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValBotAstCapPlan';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','现金流序号','orderNo','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','还本日','repayDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','支付日','payDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','还本金额','repayAmt','1','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','利息金额','intAmt','1','Y','30','','1');


/**前台树形结构配置增加存款类资产接口 add by wangqidong **/
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID = 'ValBotAstCapPlan' OR task_grp_id = 'ValBotAstRepayCapInt';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValBotAstCapPlan','还本计划','InterfData','1071','ValBotAstCapPlan',100206,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValBotAstRepayCapInt','还本付息规则','InterfData','1071','ValBotAstRepayCapInt',100207,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');

/** add by wangqidong 20181025 存款类资产接口配置脚本 end **/

/**add                                       2018-10-25                            end**/



/* 公共数据接入-增值税删除  add by dongjun on 20181029 start */
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID IN ('3001','ValAddValueTax');
/*公共数据接入-增值税删除  add by dongjun on 20181029 end */

/* 存款类资产接口接入 首次付息日非必填  add by dongjun on 20181029 start */
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValBotAstRatePlan' AND COL_E_NAME ='firstPayDate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','首次付息日','firstPayDate','2','N','10','','1');
/* 存款类资产接口接入 首次付息日非必填  add by dongjun on 20181029 end */

/* 净值型产品分红该交易增加数据动作标志校验 add by dongjun on 20181029 start */
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdBonusDealN' AND COL_E_NAME ='dataActionSign';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('净值型产品交易净值型产品分红','ValPrdBonusDealN','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
/* 净值型产品分红该交易增加数据动作标志校验 add by dongjun on 20181029 end */

/**add by wangqidong on 20181031 start remark:存款类资产付息计划增加计息本金，删除首次付息日CHECK**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstRatePlan' and COL_E_NAME='intCap';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstRatePlan' and COL_E_NAME='firstPayDate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','计息本金','intCap','3','Y','30','','1');
/**add by wangqidong on 20181031 end remark:存款类资产付息计划增加计息本金，删除首次付息日CHECK**/

/**add by wujie on 20181101 start remark:删除增值税接口的菜单**/
delete from BOK_BATCH_TASK_GRP where task_grp_id='3001' or p_task_grp_id='3001';
/**add by wujie on 20181101 end remark:删除增值税接口的菜单**/

/**add                                       2018-10-31                            start**/
/* 存款类资产基础信息模板check  add by wangqidong start */
delete from VAL_TEMP_COL_CHECK_INFO where tmp_e_name='ValBotAstInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产名称','assetName','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','存款机构','prinAgent','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','币种','ccy','1','Y','50','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产类型','assetClass','1','Y','50','DEPOSIT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','存款类型','prinType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','核算分类','valClas','1','Y','50','ORG_VALCLASS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','本金金额','capital','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','起息日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','到期日','mdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','境内外','inOut','1','Y','50','BLN_AREA','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','交易场所','tradeMarket','1','N','50','TRADE_MARKET','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','本金收益特征','capiProfFeature','1','Y','50','AMT_INCOME_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','计息基础','basis','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率类型','rateType','1','Y','50','INT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','初始利率','initRate','3','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','挂钩利率','linkRate','1','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','系数','ratio','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率上限','rateMax','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率下限','rateMin','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','浮动利率编号','intRateId','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利差','spreadRate','3','N','30','','1');

/* 存款类资产基础信息模板check  add by wangqidong end */
/**add                                       2018-10-31                            end**/
/**add                                       2018-11-06                            begin**/
/** 预期收益接口全量脚本   add by dongjun on 20181106 start **/
--前台树形结构配置_
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID ='1011' OR P_TASK_GRP_ID ='1011';
insert into BOK_BATCH_TASK_GRP values ('1011','收益型产品','InterfData','01','1011',103,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdBaseInfo','产品基础信息','InterfData','1011','ValPrdBaseInfo',100009,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdOpenPlan','产品开放计划','InterfData','1011','ValPrdOpenPlan',100012,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPayRule','产品付息规则','InterfData','1011','ValPrdPayRule',100013,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdAccrualPlan','产品付息计划','InterfData','1011','ValPrdAccrualPlan',100014,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFeeBaseinfo','产品费用信息','InterfData','1011','ValPrdFeeBaseinfo',100015,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPayPlan','产品费用支付计划','InterfData','1011','ValPrdPayPlan',100016,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdRateAdjustInfo','产品费率信息','InterfData','1011','ValPrdRateAdjustInfo',100017,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPriceAdjustInfo','产品报价信息','InterfData','1011','ValPrdPriceAdjustInfo',100018,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFloatFee','浮动管理费计提规则 ','InterfData','1011','ValPrdFloatFee',100107,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFloatFeeDetail','浮动管理费计算明细规则','InterfData','1011','ValPrdFloatFeeDetail',100108,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFeeCalPlan','产品费用计提计划','InterfData','1011','ValPrdFeeCalPlan',100106,'system',sysdate,'system',sysdate,'systeam');
DELETE FROM BOK_BATCH_TASK_GRP WHERE TASK_GRP_ID ='2021' OR P_TASK_GRP_ID ='2021';
insert into BOK_BATCH_TASK_GRP values ('2021','收益型TA交易','InterfDeal','(null)','2021',8,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdPeriodDeal','存续期交易','InterfDeal','2021','ValPrdPeriodDeal',100034,'system',sysdate,'system',sysdate,'systeam');
insert into BOK_BATCH_TASK_GRP values ('ValPrdFeeDeal','费用交易','InterfDeal','2021','ValPrdFeeDeal',100035,'system',sysdate,'system',sysdate,'systeam');
--接口任务批次
DELETE FROM VAL_INTERF_TASK WHERE ORDERNO IN ('1011','2021');
insert into VAL_INTERF_TASK values ('31','yPrdBaseInfoRecRunner','预期型产品-基础数据接口','1011','E','R','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TASK values ('32','expectedProdDealRecRunner','预期收益型产品-交易数据接口','2021','E','R','ZG','system','',sysdate,'system',sysdate);
--接口定义
DELETE FROM VAL_INTERF_DEFINE WHERE ORDERNO IN ('1011','2021');
insert into VAL_INTERF_DEFINE values ('100009','预期收益型产品基础信息','ValPrdBaseInfo','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100012','预期收益型产品开放计划','ValPrdOpenPlan','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100013','预期收益型产品付息规则','ValPrdPayRule','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100014','预期收益型产品付息计划','ValPrdAccrualPlan','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100015','预期收益型产品费用基础信息','ValPrdFeeBaseinfo','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100016','预期收益型产品付费计划','ValPrdPayPlan','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100017','预期收益型产品费率调整信息','ValPrdRateAdjustInfo','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100018','预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100106','预期收益型产品费用计提计划','ValPrdFeeCalPlan','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100107','预期收益型产品浮动管理费计提规则','ValPrdFloatFee','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100108','预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','1011','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100034','预期收益型产品交易存续期交易','ValPrdPeriodDeal','2021','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100035','预期收益型产品交易费用交易','ValPrdFeeDeal','2021','system','',sysdate,'system',sysdate);
--接口模版名称
DELETE FROM VAL_INTERF_TEMPLATE WHERE TMP_E_NAME IN ('ValPrdBaseInfo','ValPrdOpenPlan','ValPrdPayRule','ValPrdAccrualPlan','ValPrdFeeBaseinfo',
'ValPrdPayPlan','ValPrdRateAdjustInfo','ValPrdPriceAdjustInfo','ValPrdFeeCalPlan','ValPrdFloatFee','ValPrdFloatFeeDetail','ValPrdPeriodDeal','ValPrdFeeDeal');
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品基础信息','ValPrdBaseInfo','ValPrdBaseInfo','100009','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品开放计划','ValPrdOpenPlan','ValPrdOpenPlan','100012','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品付息规则','ValPrdPayRule','ValPrdPayRule','100013','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品付息计划','ValPrdAccrualPlan','ValPrdAccrualPlan','100014','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','ValPrdFeeBaseinfo','100015','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品付费计划','ValPrdPayPlan','ValPrdPayPlan','100016','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','ValPrdRateAdjustInfo','100017','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','ValPrdPriceAdjustInfo','100018','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','ValPrdFeeCalPlan','100106','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','ValPrdFloatFee','100107','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','ValPrdFloatFeeDetail','100108','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品交易存续期交易','ValPrdPeriodDeal','ValPrdPeriodDeal','100034','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('预期收益型产品交易费用交易','ValPrdFeeDeal','ValPrdFeeDeal','100035','01','ZG','system','',sysdate,'system',sysdate);
--模板字段校验信息配置表
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdBaseInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','产品名称','prodName','1','Y','100','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','产品简称','prodAbbr','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','产品估值方案','prdValSche','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','父产品代码','pmotherPrd','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','投资组合代码','portfolioId','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','是否核算','isAccount','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','是否分层核算','isLayerAccount','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','托管机构','truInstitut','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','产品收益类型','prdIncomeType','1','N','50','PRD_INCOME_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','是否允许提前终止','isEndFlag','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','是否允许客户赎回','isAllowFlag','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','认购币种','ccy','1','N','32','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','兑付本金币种','capiCcy','1','N','32','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','兑付收益币种','profCcy','1','N','32','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','起息日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','到期日','mdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','实际到期日','actMdate','2','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','产品期限','term','1','N','50','TERM_TYPE_PRODUCT','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','交易场所','tradeMarket','1','N','50','TRADE_MARKET','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','发行方式','issueType','1','N','50','ISSUE_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','是否周期型','isCycle','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','是否分层','isLayer','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','分层类型','layerType','1','N','50','CLASSIFICATION_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','托管活期利息计提','isCalc','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','是否计税','isTax','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','计息基础','basis','1','N','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','利率类型','rateType','1','N','50','INT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','预期收益率','proprate','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','浮动利率编号','intRateId','1','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','挂钩利率','linkRate','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','系数','ratio','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','利差','spreadRate','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','利率上限','rateMax','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','利率下限','rateMin','3','N','30','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdOpenPlan';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品开放计划','ValPrdOpenPlan','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品开放计划','ValPrdOpenPlan','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品开放计划','ValPrdOpenPlan','开放周期起始日','openDateStr','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品开放计划','ValPrdOpenPlan','开放周期结束日','openDateEnd','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品开放计划','ValPrdOpenPlan','开放周期起息日','openVdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品开放计划','ValPrdOpenPlan','开放周期到期日','openMdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品开放计划','ValPrdOpenPlan','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品开放计划','ValPrdOpenPlan','产品起息日','prodVdate','2','Y','10','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdPayRule';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','付息规则','payRule','1','Y','50','INTEREST_ROLE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','付息周期','payCycle','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','付息周期单位','payCycleUnit','1','N','50','PERIOD_UNIT','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','推算方法','calculation','1','Y','50','CALC_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','首次付息日','fstPayDate','2','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','营业日准则','tdayRule','1','Y','50','TDAY_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','生效日期','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息规则','ValPrdPayRule','产品起息日','prodVdate','2','Y','10','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdAccrualPlan';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','现金流序号','orderNo','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','计息区间起始日','intStrDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','计息区间结束日','intEndDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','支付日','payDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付息计划','ValPrdAccrualPlan','产品起息日','prodVdate','2','Y','10','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdFeeBaseinfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','费用ID','feeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','费用名称','feeName','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','初始费率','initRate','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','计费基础','chargeBase','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','计费基数','chargeBasic','1','Y','50','BASE_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','付费规则','payRule','1','Y','50','FREQUENCY_UNIT','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','营业准则','tdayRule','1','Y','50','TDAY_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','费用类型','feeType','1','Y','50','CASH_TYPE_2','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','计费基数日期类型','basisDate','1','Y','50','BASE_DATE_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','生效日期','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','产品起息日','prodVdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用基础信息','ValPrdFeeBaseinfo','产品简称','prodAbbr','1','N','50','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdFloatFee';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','费用名称','feeName','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','生效日','effDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','费用ID','feeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','利率生效规则','actRule','1','Y','50','ACT_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','计提类型','drawType','1','Y','50','DRAW_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','资产净值日','astVluDate','1','Y','50','BASE_DATE_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','计提频率','accrCycle','1','Y','50','FREQUENCY_UNIT','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','计提频率数值','accrCycleUnit','3','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','计提频率类型','accrDayType','1','N','50','DRAW_CYCLE_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','计提频率值','accrDayValue','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','营业日准则','tdayRule','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','首次计提日','fAccrDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','业绩报酬计算规则','calRule','1','Y','50','FLOAT_CALC_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计提规则','ValPrdFloatFee','产品起息日','prodVdate','2','Y','10','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdFloatFeeDetail';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','费用名称','feeName','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','生效日','effDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','费用ID','feeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','业绩比较基准层级','lev','3','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','基准利率','basicRate','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','调整系数','coefficient','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','利差','spreadRate','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','银行收取比例','chargeRatio','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','研发部分收取比例','saleRatio','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品浮动管理费计算明细规则','ValPrdFloatFeeDetail','产品起息日','prodVdate','2','Y','10','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdFeeCalPlan';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','费用名称','feeName','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','费用ID','feeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','区间开始日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','区间结束日','mdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','计提日','accrDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用计提计划','ValPrdFeeCalPlan','产品起息日','prodVdate','2','Y','10','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdPayPlan';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','现金流序号','orderNo','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','费用ID','feeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','支付日','payDate','2','Y','10','','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','费用名称','feeName','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品付费计划','ValPrdPayPlan','产品起息日','prodVdate','2','Y','10','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdRateAdjustInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','费用代码','feeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','计费基础','chargeBase','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','计费基数','chargeBasis','1','Y','50','BASE_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','计费基数日期类型','baseDateType','1','Y','50','BASE_DATE_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','费率','feeRate','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','生效日','effDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','产品起息日','prodVdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费率调整信息','ValPrdRateAdjustInfo','费用名称','feeName','1','N','50','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdPriceAdjustInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','计息基础','basis','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','利率类型','intType','1','Y','50','INT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','收益率','prtRate','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','生效日','effDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品产品报价调整信息','ValPrdPriceAdjustInfo','产品起息日','prodVdate','2','Y','10','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdFeeDeal';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','产品名称','prodName','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','成交编号','tradeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','费用ID','feeId','3','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','计提开始日','calStartDay','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','计提结束日','calEndDay','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','实付费用','actPayAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','是否结算','isClean2','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','支付日','payDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','币种','ccy','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','有无清算指令','isClean','1','Y','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','投资组合代码','portfolioId','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','产品起息日','prodVdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品费用交易表','ValPrdFeeDeal','费用名称','feeName','1','N','50','','1');
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME = 'ValPrdPeriodDeal';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','产品名称','prodName','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','成交编号','tradeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','交易类型','dealType','1','Y','50','PS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','业务日期','busiDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','交易日','dealDate','2','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','本金金额','capiAmt','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','利息金额','intAmt','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','交易金额','dealAmt','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','币种','ccy','1','Y','50','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','有无清算指令','isClean','1','Y','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','投资组合代码','portfolioId','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','产品起息日','prodVdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','付息截止日','calEndDay','2','N','10','','1');
/** 预期收益接口全量脚本   add by dongjun on 20181106 end **/
/**add                                       2018-11-06                            end**/
/**add                                       2018-11-09                            begin**/
--预期收益型存续期交易
/**add by 2018/11/08  chendijun**/
delete from VAL_TEMP_COL_CHECK_INFO t where t.tmp_e_name = 'ValPrdPeriodDeal' and t.col_e_name in ('isClean','isSettle');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','有无清算指令','isClean','1','Y','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品存续期交易表','ValPrdPeriodDeal','是否结清','isSettle','1','Y','50','YES_OR_NO','1');
/**add by 2018/11/08  chendijun**/

/** 债券借贷全量脚本   add by wujie on 20181109 start **/

--接口任务批次
delete from VAL_INTERF_TASK where TASK_ID ='35' and BEAN_NAME='secLendInfoRunner';
insert into VAL_INTERF_TASK values ('35','secLendInfoRunner','债券借贷-基础数据接口','1065','E','R','ZG','system','',sysdate,'system',sysdate);
delete from VAL_INTERF_TASK where TASK_ID ='36' and BEAN_NAME='secblDealInfoRecRunner';
insert into VAL_INTERF_TASK values ('36','secblDealInfoRecRunner','债券借贷-交易数据接口','2055','E','R','ZG','system','',sysdate,'system',sysdate);


--接口定义
delete from VAL_INTERF_DEFINE where INTERF_ID in ('100117','100118');
insert into VAL_INTERF_DEFINE values ('100117','债券借贷标的信息','ValSeclendInfo','1065','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100118','债券借贷质押债券信息','ValSeclendMortgage','1065','system','',sysdate,'system',sysdate);

delete from VAL_INTERF_DEFINE where INTERF_ID in ('100119','100120','100121');
insert into VAL_INTERF_DEFINE values ('100119','债券借贷交易信息','ValSeclendDeal','2055','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100120','债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','2055','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100121','债券借贷标的券卖出与购回','ValSeclendAssetDeal','2055','system','',sysdate,'system',sysdate);


--接口模版
delete from VAL_INTERF_TEMPLATE where TMP_E_NAME in ('ValSeclendInfo','ValSeclendMortgage');
insert into VAL_INTERF_TEMPLATE values ('债券借贷标的信息','ValSeclendInfo','ValSeclendInfo','100117','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('债券借贷质押债券信息','ValSeclendMortgage','ValSeclendMortgage','100118','01','ZG','system','',sysdate,'system',sysdate);

delete from VAL_INTERF_TEMPLATE where TMP_E_NAME in ('ValSeclendDeal','ValSeclendAssetPeriodDeal','ValSeclendAssetDeal');
insert into VAL_INTERF_TEMPLATE values ('债券借贷交易信息','ValSeclendDeal','ValSeclendDeal','100119','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','ValSecLendAssetPeriodDeal','100120','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','ValSeclendAssetDeal','100121','01','ZG','system','',sysdate,'system',sysdate);

--模板字段校验信息配置表 
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSeclendInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','标的代码','tradeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','起息日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','到期日','mdate','2','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','借贷期限','term','3','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','计息基础','basis','1','Y','32','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','费率','feeRate','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','交易场所','tradeMarket','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','币种','ccy','1','Y','32','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','首期结算方式','vSettMethod','1','Y','32','SETTLEMENT_WAY_LENDING','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','到期结算方式','mSettMethod','1','Y','32','SETTLEMENT_WAY_LENDING','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','标的债券代码','secId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','标的券面总额','prinAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','质押券面总额','mPrinAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','本金收益特征','capiIncomeFeature','1','Y','50','AMT_INCOME_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','登记托管机构','registOrg','1','N','50','REGIST_ORG','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','押品只数','pledgeSecNum','3','N','10','','1');

delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSeclendMortgage';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷质押债券信息','ValSeclendMortgage','成交编号','tradeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷质押债券信息','ValSeclendMortgage','金融产品代码','finprodId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷质押债券信息','ValSeclendMortgage','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷质押债券信息','ValSeclendMortgage','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷质押债券信息','ValSeclendMortgage','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷质押债券信息','ValSeclendMortgage','债券代码','secId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷质押债券信息','ValSeclendMortgage','债券名称','secName','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷质押债券信息','ValSeclendMortgage','券面总额','prinAmt','3','Y','30','','1');

DELETE from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSeclendDeal';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','成交编号','tradeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','金融产品代码','finprodId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','交易日','tradeDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','起息日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','到期日','mdate','2','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','借贷期限','term','3','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','交易方向','ps','1','Y','50','PS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','计息基础','basis','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','交易对手','counterId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','交易对手类型','counterType','1','Y','50','COUNTER_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','费率','feeRate','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','借贷费用','fee','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','交易场所','tradeMarket','1','Y','50','TRADE_MARKET','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','币种','ccy','1','Y','50','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','标的债券代码','secId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','标的券面总额','prinAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','质押券面总额','mPrinAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷交易信息','ValSeclendDeal','有无清算指令','isClean','1','Y','50','','1');


DELETE from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSeclendAssetPeriodDeal';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','金融产品代码','finprodId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','债券代码','secId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','交易方向','ps','1','Y','50','PS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','是否还本带息','isAmt','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','实收利息','actInt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','实收本金','actCapi','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','交易金额','tardeAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','计息开始日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','计息结束日','mdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','交割日','settleDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','交易日','tradeDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','是否结清','isClean2','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','币种','ccy','1','Y','50','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','成交编号','tradeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券存续期交易','ValSeclendAssetPeriodDeal','有无清算指令','isClean','1','Y','50','YES_OR_NO','1');

DELETE from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSeclendAssetDeal';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','金融产品代码','finprodId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','金融产品类型','finprodType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','债券代码','secId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交易场所','tradeMarket','1','Y','50','TRADE_MARKET','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交易日','dealDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交割日','settdateDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交易方向','ps','1','Y','50','PS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','结算方式','stlForm','1','Y','50','SETTLEMENT_WAY_LENDING','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','成交数量','dealNum','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','单位面值','parValue','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','未随交易支付费用','feeAmtUnpay','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交易对手','counterId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交易对手类型','counterType','1','Y','50','COUNTER_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','通道代码','chlId','1','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','净价','cprice','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','全价','fprice','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','应收利息','unitInt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','应收利息总额','intAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','净价总额','cpriceAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','全价总额','fpriceAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','币种','ccy','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','成交编号','tradeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','投资目的','investAim','1','Y','50','INV_AIM','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','印花税','stampDuty','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','印花税是否随交易支付','isStampDuty','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','经手费','brokerage','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','经手费是否随交易支付','isBrokerage','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','证管费','certfFee','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','证管费是否随交易支付','isCertfFee','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','结算费','stlAmt','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','结算费是否随交易支付','isStlAmt','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','佣金','commission','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','佣金是否随交易支付','isCommission','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交易手续费','dealFeeAmt','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交易手续费是否随交易支付','isDealFeeAmt','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','结算手续费','stlFeeAmt','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','结算手续费是否随交易支付','isStlFeeAmt','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','通道费','chlFeeAmt','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','通道费是否随交易支付','isChlFeeAmt','1','N','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','交易金额','tradeAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','登记结算机构','registAgency','1','Y','50','REGIST_ORG','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','有无清算指令','isClean','1','Y','50','YES_OR_NO','1');

--菜单
delete from BOK_BATCH_TASK_GRP where task_grp_id  in ('1065','ValSeclendInfo','ValSeclendMortgage') or p_task_grp_id='1065';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('1065','债券借贷','InterfData','','1065',204,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValSeclendInfo','标的信息','InterfData','1065','ValSeclendInfo',100047,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValSeclendMortgage','质押债券信息','InterfData','1065','ValSeclendMortgage',100048,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');

delete from BOK_BATCH_TASK_GRP where task_grp_id='2055' or p_task_grp_id='2055';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('2055','债券借贷交易','InterfDeal','','2055',6,'admin',to_date('2018-11-03','yyyy-MM-dd'),'admin',to_date('2018-11-03','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValSeclendDeal','债券借贷交易信息','InterfDeal','2055','ValSeclendDeal',100047,'admin',to_date('2018-11-03','yyyy-MM-dd'),'admin',to_date('2018-11-03','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValSeclendAssetPeriodDeal','债券借贷标的券存续期交易','InterfDeal','2055','ValSeclendAssetPeriodDeal',100047,'admin',to_date('2018-11-03','yyyy-MM-dd'),'admin',to_date('2018-11-03','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValSeclendAssetDeal','债券借贷标的券卖出与购回','InterfDeal','2055','ValSeclendAssetDeal',100047,'admin',to_date('2018-11-03','yyyy-MM-dd'),'admin',to_date('2018-11-03','yyyy-MM-dd'),'');

/** 债券借贷全量脚本   add by wujie on 20181109 end **/
/**add                                       2018-11-09                            end**/
/**add                                       2018-11-16                            begin**/
/** 模板字段校验信息配置表 :存款类资产基础信息    add by wujie on 20181116 start **/

delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产名称','assetName','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','存款机构','prinAgent','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','币种','ccy','1','Y','50','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产类型','assetClass','1','Y','50','DEPOSIT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','存款类型','prinType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','核算分类','valClas','1','Y','50','ORG_VALCLASS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','本金金额','capital','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','起息日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','到期日','mdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','境内外','inOut','1','Y','50','BLN_AREA','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','交易场所','tradeMarket','1','N','50','TRADE_MARKET','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','本金收益特征','capiProfFeature','1','Y','50','AMT_INCOME_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','计息基础','basis','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率类型','rateType','1','Y','50','INT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','初始利率','initRate','3','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','挂钩利率','linkRate','1','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','系数','ratio','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率上限','rateMax','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率下限','rateMin','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','浮动利率编号','intRateId','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利差','spreadRate','3','N','30','','1');

/** 模板字段校验信息配置表 :存款类资产基础信息    add by wujie on 20181116 end **/
/**add                                       2018-11-16                            end**/
/**add                                       2018-11-19                            end**/
/** 模板字段校验信息配置表 :债券借贷交易信息交易对手类型由必填项改为非必填    add by wujie on 20181119 start **/
DELETE from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSeclendDeal' and COL_E_NAME='counterType';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券借贷交易信息','ValSeclendDeal','交易对手类型','counterType','1','N','50','COUNTER_TYPE','1');
/** 模板字段校验信息配置表 :债券借贷交易信息交易对手类型由必填项改为非必填    add by wujie on 20181119 end **/


/** 模板字段校验信息配置表 :债券买卖交易新增流通市场    add by chendijun on 20181119 start **/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSecVestAssetDeal' and COL_E_NAME='sharesTradeMarket';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券买卖交易','ValSecVestAssetDeal','流通市场','sharesTradeMarket','1','Y','50','SHARES_TRADE_MARKET','1');
/** 模板字段校验信息配置表 :债券买卖交易新增流通市场    add by chendijun on 20181119 end **/

/**add                                       2018-11-19                           end**/

/**add                                       2018-11-20                            begin**/
/** 模板字段校验信息配置表 :净值型基金赎回确认交易账套代码由必填项改为非必填    add by wujie on 20181120 start **/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValFundNRedAffirmN' and COL_E_NAME='booksetId';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型基金赎回确认交易','ValFundNRedAffirmN','账套代码','booksetId','1','N','50','','1');
/** 模板字段校验信息配置表 :净值型基金赎回确认交易账套代码由必填项改为非必填    add by wujie on 20181120 end **/


/** 模板字段校验信息配置表 :资金账户信息[大额支付系统号]由必填项改为非必填    add by wujie on 20181120 start **/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValAccountInfo' and COL_E_NAME = 'largePayno';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('资金账户信息','ValAccountInfo','大额支付系统号','largePayno','1','N','50','','1');
/** 模板字段校验信息配置表 :资金账户信息[大额支付系统号]由必填项改为非必填    add by wujie on 20181120 end **/

/** 模板字段校验信息配置表 :债券回购标的[核算分类]由必填项改为非必填，数据字典校验取消    add by wujie on 20181120 start **/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValRepDealSecSubject' and COL_E_NAME='valClas';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券回购标的','ValRepDealSecSubject','核算分类','valClas','1','N','50','','1');
/** 模板字段校验信息配置表 :债券回购标的[核算分类]由必填项改为非必填，数据字典校验取消    add by wujie on 20181120 end **/


/** 模板字段校验信息配置表 :预期收益型产品基础信息[父产品代码、投资组合代码]长度由50改为32    add by wujie on 20181120 start **/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValPrdBaseInfo' and COL_E_NAME in ('pmotherPrd','portfolioId');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','父产品代码','pmotherPrd','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','投资组合代码','portfolioId','1','N','32','','1');
/** 模板字段校验信息配置表 :预期收益型产品基础信息[父产品代码、投资组合代码]长度由50改为32    add by wujie on 20181120 end **/

/**add                                       2018-11-20                            end**/

/**add                                       2018-11-21                            begin**/

/**模板字段校验信息配置表:节假日信息[交易场所]字段增加数据字典校验 add by wujie on 20181121 start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValHolidayInfo' and COL_E_NAME='tradeMarket';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('节假日信息','ValHolidayInfo','交易场所','tradeMarket','1','Y','32','HOLIDAY_TRADE_MARKET','1');
/**模板字段校验信息配置表:节假日信息[交易场所]字段增加数据字典校验 add by wujie on 20181121 end**/

/**模板字段校验信息配置表:货币基金基础信息、净值型基金基础信息[申购确认速度、赎回确认速度]字段取消数据字典校验 add by wujie on 20181121 start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValFundMnfBaseInfo' and COL_E_NAME in ('purSpeed','redSpeed');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币基金基础信息','ValFundMnfBaseInfo','申购确认速度','purSpeed','3','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币基金基础信息','ValFundMnfBaseInfo','赎回确认速度','redSpeed','3','Y','10','','1');

delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValFundNBaseInfoN' and COL_E_NAME in ('purSpeed','redSpeed');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('净值型基金基础信息','ValFundNBaseInfoN','申购确认速度','purSpeed','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('净值型基金基础信息','ValFundNBaseInfoN','赎回确认速度','redSpeed','1','Y','50','','1');
/**模板字段校验信息配置表:货币基金基础信息、净值型基金基础信息[申购确认速度、赎回确认速度]字段取消数据字典校验 add by wujie on 20181121 start**/

/**菜单：费用交易菜单名称改为投资端费用交易 add by wujie on 20181121 start**/
delete from BOK_BATCH_TASK_GRP where task_grp_id='2111';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) 
values ('2111','投资端费用交易','InterfDeal','','2111',11,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
/**菜单：费用交易菜单名称改为投资端费用交易 add by wujie on 20181121 end**/

/**add                                       2018-11-21                            end**/
/**add                                       2018-11-22                            begin**/


/**接口任务批次：利率型产品基础数据、交易数据接口的任务批次号调整  add by wujie on 20181122 start**/
delete VAL_INTERF_TASK where task_id in ('3','31');
insert into VAL_INTERF_TASK values ('3','yPrdBaseInfoRecRunner','利率型产品-基础数据接口','1011','E','R','ZG','system','',sysdate,'system',sysdate);

delete VAL_INTERF_TASK where task_id in ('16','32');
insert into VAL_INTERF_TASK values ('16','expectedProdDealRecRunner','利率型产品-交易数据接口','2021','E','R','ZG','system','',sysdate,'system',sysdate);

/**接口任务批次：利率型产品基础数据、交易数据接口的任务批次号调整  add by wujie on 20181122 end**/

/**交易数据导入：计划类(净值型)、基金交易的菜单调整 add by wujie on 20181122 start**/
delete from BOK_BATCH_TASK_GRP where task_grp_id='2091' or p_task_grp_id='2091';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) 
values ('2091','计划类(净值型)交易','InterfDeal','','2091',10,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');

delete from BOK_BATCH_TASK_GRP where task_grp_id='2071' or p_task_grp_id='2071';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('2071','基金'||chr(38)||'计划类(净值型)交易','InterfDeal','','2071',7,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValApplyPurTade','基金'||chr(38)||'计划类申购申请','InterfDeal','2071','ValApplyPurTade',100058,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValAffirmPurTrade','基金'||chr(38)||'计划类申购确认','InterfDeal','2071','ValAffirmPurTrade',100059,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValFundMnfRedAffirm','货币基金赎回确认','InterfDeal','2071','ValFundMnfRedAffirm',100060,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValFundNRedAffirmN','净值型基金'||chr(38)||'计划类赎回确认','InterfDeal','2071','ValFundNRedAffirmN',100061,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValRedAccoutTrade','基金'||chr(38)||'计划类赎回到账','InterfDeal','2071','ValRedAccoutTrade',100062,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValCashBonusTrade','基金'||chr(38)||'计划类现金分红','InterfDeal','2071','ValCashBonusTrade',100063,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValBonusResTrade','基金'||chr(38)||'计划类红利再投','InterfDeal','2071','ValBonusResTrade',100064,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');

/**交易数据导入：计划类(净值型)、基金交易的菜单调整 add by wujie on 20181122 end**/

/**删除计划类净值型资产接口配置脚本 add by wujie on 20181122 start**/
--接口任务批次
delete from VAL_INTERF_TASK where task_id ='23';

--接口定义
delete from VAL_INTERF_DEFINE where ORDERNO='2071';
insert into VAL_INTERF_DEFINE values ('100058','基金'||chr(38)||'计划类申购申请交易','ValApplyPurTade','2071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100059','基金'||chr(38)||'计划类申购确认交易','ValAffirmPurTrade','2071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100060','货币基金赎回确认交易','ValFundMnfRedAffirm','2071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100061','净值型基金'||chr(38)||'计划类赎回确认交易','ValFundNRedAffirmN','2071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100062','基金'||chr(38)||'计划类赎回到账交易','ValRedAccoutTrade','2071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100063','基金'||chr(38)||'计划类现金分红交易','ValCashBonusTrade','2071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100064','基金'||chr(38)||'计划类红利再投交易','ValBonusResTrade','2071','system','',sysdate,'system',sysdate);

--接口模版名称
UPDATE VAL_INTERF_TEMPLATE SET TMP_C_NAME='基金'||chr(38)||'计划类赎回到账交易' WHERE TMP_E_NAME='ValRedAccoutTrade';
UPDATE VAL_INTERF_TEMPLATE SET TMP_C_NAME='基金'||chr(38)||'计划类现金分红交易' WHERE TMP_E_NAME='ValCashBonusTrade';
UPDATE VAL_INTERF_TEMPLATE SET TMP_C_NAME='基金'||chr(38)||'计划类红利再投交易' WHERE TMP_E_NAME='ValBonusResTrade';
UPDATE VAL_INTERF_TEMPLATE SET TMP_C_NAME='基金'||chr(38)||'计划类申购确认交易' WHERE TMP_E_NAME='ValAffirmPurTrade';
UPDATE VAL_INTERF_TEMPLATE SET TMP_C_NAME='基金'||chr(38)||'计划类申购申请交易' WHERE TMP_E_NAME='ValApplyPurTade';
UPDATE VAL_INTERF_TEMPLATE SET TMP_C_NAME='净值型基金'||chr(38)||'计划类赎回确认交易' WHERE TMP_E_NAME='ValFundNRedAffirmN';

--模板字段校验信息配置表 
UPDATE VAL_TEMP_COL_CHECK_INFO SET TMP_C_NAME='基金'||chr(38)||'计划类赎回到账交易' WHERE TMP_E_NAME='ValRedAccoutTrade';
UPDATE VAL_TEMP_COL_CHECK_INFO SET TMP_C_NAME='基金'||chr(38)||'计划类现金分红交易' WHERE TMP_E_NAME='ValCashBonusTrade';
UPDATE VAL_TEMP_COL_CHECK_INFO SET TMP_C_NAME='基金'||chr(38)||'计划类红利再投交易' WHERE TMP_E_NAME='ValBonusResTrade';
UPDATE VAL_TEMP_COL_CHECK_INFO SET TMP_C_NAME='基金'||chr(38)||'计划类申购确认交易' WHERE TMP_E_NAME='ValAffirmPurTrade';
UPDATE VAL_TEMP_COL_CHECK_INFO SET TMP_C_NAME='基金'||chr(38)||'计划类申购申请交易' WHERE TMP_E_NAME='ValApplyPurTade';
UPDATE VAL_TEMP_COL_CHECK_INFO SET TMP_C_NAME='净值型基金'||chr(38)||'计划类赎回确认交易' WHERE TMP_E_NAME='ValFundNRedAffirmN';


/**删除计划类净值型资产接口配置脚本 add by wujie on 20181122 end**/


/**模板字段校验信息配置表 :ValSubjectInfo模板名称修改 add by wujie on 20181122 start**/
update VAL_TEMP_COL_CHECK_INFO set tmp_c_name='标的账户信息' where TMP_E_NAME='ValSubjectInfo';
/**模板字段校验信息配置表 :ValSubjectInfo模板名称修改 add by wujie on 20181122 end**/

/**add                                       2018-11-22                           end**/


/**add                                       2018-11-23                           start**/

/**模板字段校验信息配置表:债券借贷标的信息增加字段[借贷费用] add by wujie on 20181123 start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSeclendInfo' and COL_E_NAME='fee';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券借贷标的信息','ValSeclendInfo','借贷费用','fee','2','Y','30','','1');
/**模板字段校验信息配置表:债券借贷标的信息增加字段[借贷费用] add by wujie on 20181123 end**/


/**接口模版名称:债券借贷标的券存续期交易 修改 add by wujie on 20181123 start**/
update VAL_INTERF_TEMPLATE set dto_name='ValSeclendAssetPeriodDeal' where interf_id='100120';
/**接口模版名称:债券借贷标的券存续期交易 修改 add by wujie on 20181123 end**/


/**add                                       2018-11-23                           end**/

/**add                                       2018-11-26                           start**/


/**模板字段校验信息配置表 :计划类资产（利率型）利率调整信息增加[basis、intType] add by wujie on 20181126 start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValMngRateAdjInfo' and COL_E_NAME in ('intType','basis');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（利率型）利率调整信息','ValMngRateAdjInfo','计息基础','basis','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（利率型）利率调整信息','ValMngRateAdjInfo','利率类型','intType','1','Y','50','INT_TYPE','1');
/**模板字段校验信息配置表 :计划类资产（利率型）利率调整信息增加[basis、intType] add by wujie on 20181126 start**/


/**菜单：增加【基金&计划类买入卖出交易】及相关接口配置脚本更新 add by wujie on 20181126 start**/
delete from BOK_BATCH_TASK_GRP where task_grp_id='ValFundTrade' and p_task_grp_id='2071';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) 
values ('ValFundTrade','基金'||chr(38)||'计划类买入卖出交易','InterfDeal','2071','ValFundTrade',100065,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');

--接口定义
delete from VAL_INTERF_DEFINE where INTERF_ID='100109';
insert into VAL_INTERF_DEFINE values ('100109','基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','2071','system','',sysdate,'system',sysdate);

--接口模版名称
delete from VAL_INTERF_TEMPLATE where TMP_E_NAME='ValFundTrade';
insert into VAL_INTERF_TEMPLATE values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','ValFundTrade','100109','01','ZG','system','',sysdate,'system',sysdate);

--模板字段校验信息配置表 
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValFundTrade';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','产品代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','数据动作标志','dataActionSign','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','成交编号','tradeId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','业务类型','busiType','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','交易方向','ps','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','单位净价','unitCprice','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','资产代码','assetId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','资产名称','assetName','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','交易场所','tradeMarket','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','交易日','tradeDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','交易本金','prinAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','交易份额','shareAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','应收红利','intAmt','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','交易金额','tradeAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','币种','ccy','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','交易对手','counterId','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','交易对手类型','counterType','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金'||chr(38)||'计划类买入卖出交易','ValFundTrade','有无清算指令','isClean','1','Y','50','','1');

/**菜单：增加【基金&计划类买入卖出交易】 及相关接口配置脚本更新 add by wujie on 20181126 end**/



/**add                                       2018-11-26                           end**/

/**add                                       2018-11-27                           start**/


/**拆借利率调整接口配置脚本 add by wujie on 20181127 start**/
--菜单
delete from BOK_BATCH_TASK_GRP where task_grp_id ='ValDlTargetRateAdjust' and p_task_grp_id='1041';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValDlTargetRateAdjust','拆借利率调整','InterfData','1041','ValDlTargetRateAdjust',100101,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');


--接口定义
delete from VAL_INTERF_DEFINE where INTERF_ID ='100126';
insert into VAL_INTERF_DEFINE values ('100126','拆借利率调整','ValDlTargetRateAdjust','1041','system','',sysdate,'system',sysdate);

--接口模版名称
delete from VAL_INTERF_TEMPLATE where TMP_E_NAME ='ValDlTargetRateAdjust';
insert into VAL_INTERF_TEMPLATE values ('拆借利率调整','ValDlTargetRateAdjust','ValDlTargetRateAdjust','100126','01','ZG','system','',sysdate,'system',sysdate);

--模板字段校验信息配置表 
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValDlTargetRateAdjust';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('拆借利率调整','ValDlTargetRateAdjust','资产代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('拆借利率调整','ValDlTargetRateAdjust','业务类型','busiType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('拆借利率调整','ValDlTargetRateAdjust','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('拆借利率调整','ValDlTargetRateAdjust','生效日','effDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('拆借利率调整','ValDlTargetRateAdjust','利率类型','intType','1','Y','50','INT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('拆借利率调整','ValDlTargetRateAdjust','计息基础','basis','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('拆借利率调整','ValDlTargetRateAdjust','利率','rate','3','Y','30','','1');


/**拆借利率调整接口配置脚本 add by wujie on 20181127 end**/

/**存款类资产基本信息接口配置脚本 add by wujie on 20181127 start**/
--菜单
delete from BOK_BATCH_TASK_GRP where task_grp_id ='1071' or p_task_grp_id='1071';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('1071','存款资产','InterfData','','1071',204,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValBotAstInfo','基础信息','InterfData','1071','ValBotAstInfo',100049,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValBotAstRateAdjust','利率信息','InterfData','1071','ValBotAstRateAdjust',100050,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValBotAstRatePlan','付息计划','InterfData','1071','ValBotAstRatePlan',100051,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValBotAstCapPlan','还本计划','InterfData','1071','ValBotAstCapPlan',100206,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValBotAstRepayCapInt','还本付息规则','InterfData','1071','ValBotAstRepayCapInt',100207,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');


--接口定义
delete from VAL_INTERF_DEFINE where INTERF_ID in('100049','100050','100051','100112','100113');
insert into VAL_INTERF_DEFINE values ('100049','存款类资产基础信息','ValBotAstInfo','1071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100050','存款类资产利率调整','ValBotAstRateAdjust','1071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100051','存款类资产付息计划','ValBotAstRatePlan','1071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100112','存款类资产还本计划','ValBotAstCapPlan','1071','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100113','存款类资产还本付息规则','ValBotAstRepayCapInt','1071','system','',sysdate,'system',sysdate);


--接口模版名称
----存款类资产基础信息
delete from VAL_INTERF_TEMPLATE where TMP_E_NAME in ('ValBotAstInfo','ValBotAstRateAdjust','ValBotAstRatePlan','ValBotAstCapPlan','ValBotAstRepayCapInt');
insert into VAL_INTERF_TEMPLATE values ('存款类资产基础信息','ValBotAstInfo','ValBotAstInfo','100049','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('存款类资产利率调整','ValBotAstRateAdjust','ValBotAstRateAdjust','100050','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('存款类资产付息计划','ValBotAstRatePlan','ValBotAstRatePlan','100051','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('存款类资产还本计划','ValBotAstCapPlan','ValBotAstCapPlan','100112','01','ZG','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('存款类资产还本付息规则','ValBotAstRepayCapInt','ValBotAstRepayCapInt','100113','01','ZG','system','',sysdate,'system',sysdate);

--模板字段校验信息配置表 
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产名称','assetName','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','存款机构','prinAgent','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','币种','ccy','1','Y','50','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','资产类型','assetClass','1','Y','50','DEPOSIT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','存款类型','prinType','1','Y','50','FINPROD_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','核算分类','valClas','1','Y','50','ORG_VALCLASS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','本金金额','capital','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','起息日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','到期日','mdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','境内外','inOut','1','Y','50','BLN_AREA','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','交易场所','tradeMarket','1','N','50','TRADE_MARKET','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','本金收益特征','capiProfFeature','1','Y','50','AMT_INCOME_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','计息基础','basis','1','Y','50','BASIS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率类型','rateType','1','Y','50','INT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','初始利率','initRate','3','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','挂钩利率','linkRate','1','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','系数','ratio','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率上限','rateMax','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利率下限','rateMin','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','浮动利率编号','intRateId','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','利差','spreadRate','3','N','30','','1');


----存款类资产利率调整
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstRateAdjust';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产利率调整','ValBotAstRateAdjust','资产代码','prodId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产利率调整','ValBotAstRateAdjust','生效日','effDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产利率调整','ValBotAstRateAdjust','利率','rate','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产利率调整','ValBotAstRateAdjust','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产利率调整','ValBotAstRateAdjust','利率类型','intType','1','Y','50','INT_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产利率调整','ValBotAstRateAdjust','计息基础','basis','1','Y','50','BASIS','1');


----存款类资产付息计划
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstRatePlan';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','现金流序号','cashNum','3','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','计息开始日','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','计息结束日','mdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','支付日','payDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','应计利息','unitInt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产付息计划','ValBotAstRatePlan','计息本金','intCap','3','Y','30','','1');


----存款类资产还本付息规则
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstRepayCapInt';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','交易类型','dealType','1','Y','50','PS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','生效日','effDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','还本是否带息','isInt','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','付息频率','payCycle','1','N','50','FREQUENCY_UNIT','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','还本频率','capCycle','1','N','50','FREQUENCY_UNIT','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','营业日准则','tdayRule','1','Y','50','TDAY_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','推算方式','calcRule','1','Y','50','CALC_RULE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本付息规则','ValBotAstRepayCapInt','首次付息日','firstPayDate','1','N','50','','1');

----存款类资产还本计划
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstCapPlan';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','现金流序号','orderNo','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','还本日','repayDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','支付日','payDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','还本金额','repayAmt','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产还本计划','ValBotAstCapPlan','利息金额','intAmt','3','Y','30','','1');


/**存款类资产基本信息接口配置脚本 add by wujie on 20181127 end**/


/**模板字段校验信息配置表 ：费用交易增加【付费截止日】字段  add by wujie on 20181127 start**/

delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME ='ValFeeDeal' and COL_E_NAME='mdate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('费用交易','ValFeeDeal','付费截止日','mdate','2','Y','10','','1');

/**模板字段校验信息配置表 ：费用交易增加【付费截止日】字段  add by wujie on 20181127 end**/

/**add                                       2018-11-27                           end**/

/**add                                       2018-12-04                           start**/

/**模板字段校验信息配置表 ：净值型产品浮动管理费计算明细规则【费用名称字段由必填项改为非必填项】 add by wujie on 20181204 start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValPrdFloatFeeDetailN' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品浮动管理费计算明细规则','ValPrdFloatFeeDetailN','费用名称','feeName','1','N','50','','1');
/**模板字段校验信息配置表 ：净值型产品浮动管理费计算明细规则【费用名称字段由必填项改为非必填项】 add by wujie on 20181204 end**/

/**模板字段校验信息配置表 ：货币型产品浮动管理费计算明细规则【费用名称字段由必填项改为非必填项】 add by wujie on 20181204 start**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValPrdFloatFeeDetailC' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品浮动管理费计算明细规则','ValPrdFloatFeeDetailC','费用名称','feeName','1','N','50','','1');
/**模板字段校验信息配置表 ：货币型产品浮动管理费计算明细规则【费用名称字段由必填项改为非必填项】 add by wujie on 20181204 end**/
/**add                                       2018-12-04                           end**/


/**add                                       2018-12-10                           start**/
/** 提交人：璩建忠  日期：2018-12-07  remark：模板字段校验信息配置表:债券借贷标的券卖出与购回【投资目的】由必填项改为非必填 start**/ 
delete from VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='ValSeclendAssetDeal' and COL_E_NAME='investAim';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券借贷标的券卖出与购回','ValSeclendAssetDeal','投资目的','investAim','1','N','50','INV_AIM','1');
/** 提交人：璩建忠  日期：2018-12-07  remark：模板字段校验信息配置表:债券借贷标的券卖出与购回【投资目的】由必填项改为非必填 end**/ 

/**add                                       2018-12-10                           end**/


/**add                                       2018-12-18                           start**/
/**模板字段校验信息配置表：净值型产品分红方案登记部分字段check调整  add by wujie on 20181218 start**/
--净值型产品分红方案登记:【分红方式】由非必填项改为必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValPrdDelDealN' and COL_E_NAME='bonusMode';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品分红方案登记','ValPrdDelDealN','分红方式','bonusMode','1','N','50','PAY_TYPE','1');

--净值型产品分红方案登记:【分红方案】由非必填项改为必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValPrdDelDealN' and COL_E_NAME='bonusPlanId';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品分红方案登记','ValPrdDelDealN','分红方案','bonusPlanId','1','N','50','BONU_SCALC_FLAG','1');

--净值型产品分红方案登记:【分红日】由非必填项改为必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValPrdDelDealN' and COL_E_NAME='settDate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品分红方案登记','ValPrdDelDealN','分红日','settDate','2','N','10','','1');
/**模板字段校验信息配置表：净值型产品分红方案登记部分字段check调整  add by wujie on 20181218 end**/

/**模板字段校验信息配置表：债券基础信息部分字段check调整  add by wujie on 20181218 start**/
--债券基础信息:【初始利率】由非必填项改为必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSecBaseInfo' and COL_E_NAME='initRate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券基础信息','ValSecBaseInfo','初始利率','initRate','1','Y','50','','1');

--债券基础信息:【债券简称】长度由32改为200
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSecBaseInfo' and COL_E_NAME='secAbbr';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券基础信息','ValSecBaseInfo','债券简称','secAbbr','1','Y','200','','1');

--债券基础信息:【债券名称】长度由50改为200
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValSecBaseInfo' and COL_E_NAME='secName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券基础信息','ValSecBaseInfo','债券名称','secName','1','N','200','','1');
/**模板字段校验信息配置表：债券基础信息部分字段check调整  add by wujie on 20181218 end**/
/**add                                       2018-12-18                           end**/


/**add                                       2018-12-24                           start**/
/** 提交人：吴洁  2018-12-24  start**/
--净值型产品浮动管理费计算明细规则:【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFloatFeeDetailN' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品浮动管理费计算明细规则','ValPrdFloatFeeDetailN','费用名称','feeName','1','N','50','','1');


--货币型产品浮动管理费计算明细规则:【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFloatFeeDetailC' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品浮动管理费计算明细规则','ValPrdFloatFeeDetailC','费用名称','feeName','1','N','50','','1');

--净值型产品付费计划：【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdPayPlanN' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品付费计划','ValPrdPayPlanN','费用名称','feeName','1','N','50','','1');


--净值型产品费用计提计划：【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFeeCalPlanN' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品费用计提计划','ValPrdFeeCalPlanN','费用名称','feeName','1','N','50','','1');


--货币型产品付费计划:【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdPayPlanC' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品付费计划','ValPrdPayPlanC','费用名称','feeName','1','N','50','','1');

--货币型产品费用计提计划:【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFeeCalPlanC' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品费用计提计划','ValPrdFeeCalPlanC','费用名称','feeName','1','N','50','','1');



--货币型产品费用基础信息：【计费基数日期类型】由日期型改为字符型
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFeeInfoC' and COL_E_NAME='basisDate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品费用基础信息','ValPrdFeeInfoC','计费基数日期类型','basisDate','1','Y','50','BASE_DATE_TYPE','1');

--货币型产品费用基础信息：【生效日】由字符型改为日期型
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFeeInfoC' and COL_E_NAME='effDate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品费用基础信息','ValPrdFeeInfoC','生效日','effDate','2','Y','10','','1');

--净值型产品浮动管理费计算明细规则:【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFloatFeeDetailN' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品浮动管理费计算明细规则','ValPrdFloatFeeDetailN','费用名称','feeName','1','N','50','','1');


--货币型产品浮动管理费计算明细规则:【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFloatFeeDetailC' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品浮动管理费计算明细规则','ValPrdFloatFeeDetailC','费用名称','feeName','1','N','50','','1');

--净值型产品付费计划：【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdPayPlanN' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品付费计划','ValPrdPayPlanN','费用名称','feeName','1','N','50','','1');


--净值型产品费用计提计划：【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFeeCalPlanN' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品费用计提计划','ValPrdFeeCalPlanN','费用名称','feeName','1','N','50','','1');


--货币型产品付费计划:【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdPayPlanC' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品付费计划','ValPrdPayPlanC','费用名称','feeName','1','N','50','','1');

--货币型产品费用计提计划:【费用名称】由必填项改为非必填项
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFeeCalPlanC' and COL_E_NAME='feeName';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品费用计提计划','ValPrdFeeCalPlanC','费用名称','feeName','1','N','50','','1');



--货币型产品费用基础信息：【计费基数日期类型】由日期型改为字符型
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFeeInfoC' and COL_E_NAME='basisDate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品费用基础信息','ValPrdFeeInfoC','计费基数日期类型','basisDate','1','Y','50','BASE_DATE_TYPE','1');

--货币型产品费用基础信息：【生效日】由字符型改为日期型
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdFeeInfoC' and COL_E_NAME='effDate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品费用基础信息','ValPrdFeeInfoC','生效日','effDate','2','Y','10','','1');

/** 提交人：吴洁  2018-12-24  end**/
/**add                                       2018-12-24                           end**/

/** add                                   20181225                                start**/
--模板字段校验信息配置：预期收益型产品、货币型产品、净值型产品，增加【估值表模板】 
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdBaseInfo' and COL_E_NAME='valuationTemplate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('预期收益型产品基础信息','ValPrdBaseInfo','估值表模板','valuationTemplate','1','N','50','VALUATION_TEMPLATE','1');

delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdInfoC' and COL_E_NAME='valuationTemplate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('货币型产品基础信息','ValPrdInfoC','估值表模板','valuationTemplate','1','N','50','VALUATION_TEMPLATE','1');

delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdInfoN' and COL_E_NAME='valuationTemplate';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('净值型产品基础信息','ValPrdInfoN','估值表模板','valuationTemplate','1','N','50','VALUATION_TEMPLATE','1');
/**提交人：吴洁  end**/
/** add                                   20181225                                end**/

/**add                                       2018-12-27                           start**/
/**模板字段校验信息配置：债券回购标的，增加【交易场所】 add by wujie on 20181226 start **/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValRepDealSecSubject' and COL_E_NAME='tradeMarket';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) 
values ('债券回购标的','ValRepDealSecSubject','交易场所','tradeMarket','1','Y','50','TRADE_MARKET','1');
/**模板字段校验信息配置：债券回购标的，增加【交易场所】 add by wujie on 20181226 end**/

/**add by wujie on 20181226 start remark:存款类资产基础信息：初始利率、挂钩利率字段类型调整**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME='ValBotAstInfo' and COL_E_NAME in ('initRate','linkRate');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('存款类资产基础信息','ValBotAstInfo','初始利率','initRate','3','Y','30','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,IS_EXIST) 
values ('存款类资产基础信息','ValBotAstInfo','挂钩利率','linkRate','1','N','50','1');
/**add by wujie on 20181226 end remark:存款类资产基础信息：初始利率、挂钩利率字段类型调整**/
/**add                                       2018-12-27                           end**/
commit;