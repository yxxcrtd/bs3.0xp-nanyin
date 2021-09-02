/** add by yanya on 20210830**/
/**股票标的**/
delete from BOK_BATCH_TASK_GRP where task_grp_id in ('1201','ValStockBaseInfo');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('1201','股票资产','InterfData','','1201',208,'admin',sysDate,'admin',sysDate,'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValStockBaseInfo','基础信息','InterfData','1201','ValStockBaseInfo',100201,'admin',sysDate,'admin',sysDate,'');

delete from VAL_INTERF_TASK where TASK_ID = '58';
insert into VAL_INTERF_TASK values ('58','valStockBaseRecRunner','股票资产-基础数据接口','1201','E','R','ZG','system','',sysdate,'system',sysdate);

delete from VAL_INTERF_DEFINE where INTERF_ID = '100201';
insert into VAL_INTERF_DEFINE values ('100201','股票资产基础信息','ValStockBaseInfo','1201','system','',sysdate,'system',sysdate);

delete from VAL_INTERF_TEMPLATE where TMP_E_NAME = 'ValStockBaseInfo';
insert into VAL_INTERF_TEMPLATE values ('股票资产基础信息','ValStockBaseInfo','ValStockBaseInfo','100201','01','ZG','system','',sysdate,'system',sysdate);

delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValStockBaseInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','资产代码','assetId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','股票代码','stockId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','股票名称','stockName','1','Y','200','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','数据动作标志','dataActionSign','1','Y','50','ACTION_STATUS','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','公司代码','customerId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','交易场所','tradePlace','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','上市板','board','1','Y','50','BOARD','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','上市日期','vdate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','退市日期','mdate','2','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','是否通道','isChl','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','通道代码','chlId','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','港股通市场','shOrZh','1','Y','50','SH_OR_ZH','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','是否融资融券标的','isMarginFinprod','1','Y','50','YES_OR_NO','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票资产基础信息','ValStockBaseInfo','币种','ccy','1','Y','50','CCY','1');


/**债券标的**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME ='ValSecBaseInfo' 
	and COL_E_NAME in ('bondRiskType','conFinprodId','conPrice','resalePrice','redeemPrice');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券基础信息','ValSecBaseInfo','风险五级分类','bondRiskType','1','Y','50','BOND_RISK_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券基础信息','ValSecBaseInfo','正股代码','conFinprodId','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券基础信息','ValSecBaseInfo','转股价格','conPrice','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券基础信息','ValSecBaseInfo','回售触发价','resalePrice','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券基础信息','ValSecBaseInfo','赎回触发价','redeemPrice','3','N','30','','1');

/**存款类资产**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME ='ValBotAstInfo' and COL_E_NAME = 'bondRiskType';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','风险五级分类','bondRiskType','1','Y','50','BOND_RISK_TYPE','1');

/**债券回购标的**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME ='ValRepDealSecSubject' and COL_E_NAME = 'counterId';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券回购标的','ValRepDealSecSubject','交易对手编码','counterId','1','Y','32','','1');

/**股票行情**/
delete from BOK_BATCH_TASK_GRP where task_grp_id = 'ValStockQuotationInfo';
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('ValStockQuotationInfo','股票行情','InterfData','3051','ValStockQuotationInfo',100206,'admin',sysDate,'admin',sysDate,'');
delete from VAL_INTERF_DEFINE where INTERF_ID = '100215';
insert into VAL_INTERF_DEFINE values ('100215','股票行情','ValStockQuotationInfo','3051','system','',sysdate,'system',sysdate);
delete from VAL_INTERF_TEMPLATE where TMP_E_NAME = 'ValStockQuotationInfo';
insert into VAL_INTERF_TEMPLATE values ('股票行情信息','ValStockQuotationInfo','ValStockQuotationInfo','100215','01','ZX','system','',sysdate,'system',sysdate);
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValStockQuotationInfo';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票行情信息','ValStockQuotationInfo','股票代码','sharesId','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票行情信息','ValStockQuotationInfo','交易日','dealDate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票行情信息','ValStockQuotationInfo','前收盘价','preClosePrice','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票行情信息','ValStockQuotationInfo','开盘价','openPrice','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票行情信息','ValStockQuotationInfo','最高价','highPrice','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票行情信息','ValStockQuotationInfo','最低价','lowPrice','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票行情信息','ValStockQuotationInfo','收盘价','closePrice','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('股票行情信息','ValStockQuotationInfo','交易状态','tradeStatus','1','Y','50','SHARES_TRADE_STATUS','1');

/**市场结构信息**/
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME ='ValAgencyInfo' 
	and COL_E_NAME in ('valClas','partyType3','partyType1','industry','industry2','insitituteCode','isGov',
'mainCapital','registerCapital','province','city','county','holdingType',
'customerObjType','isListedCompany','isGroupCustomer','parentCustomerName',
'isPrivate','isEscapeDept','creditType','isCreditRelatedTrd','structAdjustType',
'indusUpgrateType','busiScope','mainBusi','corporationType');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','企业性质','corporationType','1','Y','50','ENTERPRISE_PROPERTY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','核算分类','valClas','1','N','50','HS_TYPE','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','机构类型（技术领域）','partyType3','1','Y','50','SVR_PARTY_TYPE3','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','机构类型（规模）','partyType1','1','Y','50','MARKET_INSTI_TYPE_SCA','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','所属行业','industry','1','Y','50','INSTITUTION_INDUSTRY_FIRST_LEVEL','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','行业二级分类','industry2','1','Y','50','INSTITUTION_INDUSTRY_SECOND_LEVEL','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','统一社会信用代码','insitituteCode','1','Y','50','','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','是否政府融资平台','isGov','1','N','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','核心资本（万元）','mainCapital','3','N','30','','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','注册资本（万元）','registerCapital','3','N','50','','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','所属省','province','1','N','50','ADMINISTRATIVE_TYPE1','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','所属市','city','1','N','50','ADMINISTRATIVE_TYPE2','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','所属县','county','1','N','50','ADMINISTRATIVE_TYPE3','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','控股类型','holdingType','1','N','50','HOLDING_TYPE','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','客户主体类型','customerObjType','1','N','50','CUSTOMER_PRIN_TYPE','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','是否上市公司','isListedCompany','1','N','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','是否集团客户','isGroupCustomer','1','N','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','所属集团客户名称','parentCustomerName','1','N','200','','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','是否民营企业','isPrivate','1','N','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','是否为逃废债企业','isEscapeDept','1','N','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','授信策略','creditType','1','N','50','CREDIT_POLICY','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','是否授信类关联交易','isCreditRelatedTrd','1','N','50','YES_OR_NO','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','产业结构调整类型','structAdjustType','1','N','50','INDUSTY_STRUCT_ADJ_TYPE','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','工业转型升级标识','indusUpgrateType','1','N','50','INDUSTY_TRANSFORM_TYPE','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','经营范围','busiScope','1','N','1000','','');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场机构信息','ValAgencyInfo','主营业务','mainBusi','1','N','1000','','');

