--接口增加所属机构字段 by tanxuan on 20210310
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValChannelBaseInfo' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValMngMnfBaseInfo' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdInfoC' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValBotAstInfo' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValDlTarget' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValMngAssetInfoN' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValMngAssetInfo' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdInfoN' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValOptTradeSubject' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValAccountInfo' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValRepDealSecSubject' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValSeclendInfo' and COL_E_NAME = 'belongInstitutionCode';
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValPrdBaseInfo' and COL_E_NAME = 'belongInstitutionCode';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('通道基础信息','ValChannelBaseInfo','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产(货币型)基础信息','ValMngMnfBaseInfo','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币型产品基础信息','ValPrdInfoC','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('存款类资产基础信息','ValBotAstInfo','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('拆借标的信息','ValDlTarget','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（净值型）基础信息','ValMngAssetInfoN','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产（利率型）基础信息','ValMngAssetInfo','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('净值型产品基础信息','ValPrdInfoN','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('期权交易标的','ValOptTradeSubject','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('资金账户信息','ValAccountInfo','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券回购标的','ValRepDealSecSubject','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券借贷标的信息','ValSeclendInfo','所属机构','belongInstitutionCode','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('预期收益型产品基础信息','ValPrdBaseInfo','所属机构','belongInstitutionCode','1','N','50','','1');


/*
接口-货币基金基础信息、接口-计划类资产(货币型)基础信息：
字段【结转频率】使用估值核算枚举值由【频率】改为【收益结转频率】
*/
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='ValFundMnfBaseInfo' AND COL_E_NAME='carryoverFreq';
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='ValMngMnfBaseInfo' AND COL_E_NAME='carryoverFreq';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币基金基础信息','ValFundMnfBaseInfo','结转频率','carryoverFreq','1','Y','50','RETURN_FREQUENCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('计划类资产(货币型)基础信息','ValMngMnfBaseInfo','结转频率','carryoverFreq','1','Y','50','RETURN_FREQUENCY','1');

--货币基金基础信息：新增字段：【场内基金类型】
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME ='ValFundMnfBaseInfo' AND COL_E_NAME='trmFundType';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币基金基础信息','ValFundMnfBaseInfo','场内基金类型','trmFundType','1','N','50','TRM_FUND_TYPE','1');

--净值型基金基础信息：新增字段：【场内基金类型】
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME ='ValFundNBaseInfoN' AND COL_E_NAME='trmFundType';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('净值型基金基础信息','ValFundNBaseInfoN','场内基金类型','trmFundType','1','N','50','TRM_FUND_TYPE','1');

--------------------------------------------基金类资产  货币基金付息规则  分红频率 start------------------------------
delete from VAL_TEMP_COL_CHECK_INFO where TMP_E_NAME = 'ValFundMnfPayRule' and COL_E_NAME = 'payCycle';

insert into VAL_TEMP_COL_CHECK_INFO 
(TMP_C_NAME,TMP_E_NAME,COL_C_NAME,
COL_E_NAME,COL_TYPE,IS_NULL,
MAX_LENGTH,DICT_CODE,IS_EXIST) 
values 
('货币基金付息规则','ValFundMnfPayRule','付息频率',
'payCycle','1','Y',
'50','RETURN_FREQUENCY','1');
--------------------------------------------基金类资产  货币基金付息规则  分红频率 start------------------------------