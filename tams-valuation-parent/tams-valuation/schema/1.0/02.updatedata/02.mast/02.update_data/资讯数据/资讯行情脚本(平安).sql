--文件下载服务器，每个行根据实际情况进行配置
delete from VAL_INTERF_SERVER_INFO where systemid='ZX';
insert into VAL_INTERF_SERVER_INFO(SERVERIP,SERVERPORT,USERNAME,PASSWORD,SERVERTYPE,STATUS,SYSTEMID,REMOTE_PATH,LOCAL_PATH,RS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('localhost','1521','tams_mast_0824','tams_mast_0824','05','Y','ZX','ORCL','','R','system','',sysdate,'system',sysdate);

--菜单
delete from BOK_BATCH_TASK_GRP  where task_grp_id = '3051' or p_task_grp_id='3051';

insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('3051','资讯行情','InterfData','','3051',100200,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('FundValuationRate','基金估值行情','InterfData','3051','FundValuationRate',100201,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('IntRate','利率行情','InterfData','3051','IntRate',100202,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('SecValuationRate','债券估值行情','InterfData','3051','SecValuationRate',100203,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('SecMarketRate','债券市场行情','InterfData','3051','SecMarketRate',100204,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('FundMarketRate','基金市场行情','InterfData','3051','FundMarketRate',100205,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');

--接口任务批次
delete from VAL_INTERF_TASK where TASK_ID='30';
insert into VAL_INTERF_TASK values ('30','hqDetailInfoRecRunner','资讯数据接口','3051','E','R','ZX','system','',sysdate,'system',sysdate);


--接口定义
DELETE FROM VAL_INTERF_DEFINE WHERE INTERF_ID IN ('100101','100102','100103','100104','100105');
insert into VAL_INTERF_DEFINE values ('100101','债券市场行情','SecMarketRate','3051','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100102','债券估值行情','SecValuationRate','3051','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100103','基金市场行情','FundMarketRate','3051','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100104','基金估值行情','FundValuationRate','3051','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100105','利率行情','IntRate','3051','system','',sysdate,'system',sysdate);

--接口模板
DELETE FROM VAL_INTERF_TEMPLATE WHERE INTERF_ID IN ('100101','100102','100103','100104','100105');
insert into VAL_INTERF_TEMPLATE values ('货币基金净值收益','CMoneyMarketFincome','HqParFundInfo','100104','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('中国共同基金净值','ChinaMutualFundNAV','HqParFundInfo','100104','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('Shibor行情','ShiborPrices','HqIntRateInfo','100105','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('法定存贷款利率','CBondBenchmark','HqIntRateInfo','100105','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('中债登估值','CBondAnalysisCNBD','HqBondValuationInfo','100102','05','ZX','system','',sysdate,'system',sysdate);

insert into VAL_INTERF_TEMPLATE values ('中国债券交易所债券行情','CBondEODPrices','HqBondMarketInfo','100101','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('中国债券行情-净价','Cbondpricesnet','HqBondMarketInfo','100101','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('中国债券行情-全价','Cbondprices','HqBondMarketInfo','100101','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','100102','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','100102','05','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('中国封闭式基金日行情','Chinaclosedfundeodprice','HqFundMarketInfo','100103','05','ZX','system','',sysdate,'system',sysdate);

--模板字段校验信息配置表
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='ChinaMutualFundNAV';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国共同基金净值','ChinaMutualFundNAV','Wind代码','fInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国共同基金净值','ChinaMutualFundNAV','单位净值','fNavUnit','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国共同基金净值','ChinaMutualFundNAV','截止日期','priceDate','2','Y','10','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='CMoneyMarketFincome';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币基金净值收益','CMoneyMarketFincome','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币基金净值收益','CMoneyMarketFincome','截止日期','fInfoEnddate','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('货币基金净值收益','CMoneyMarketFincome','净值','fInfoUnityield','3','Y','30','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='ShiborPrices';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('Shibor行情','ShiborPrices','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('Shibor行情','ShiborPrices','交易日期','tradeDt','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('Shibor行情','ShiborPrices','利率(%)','bInfoRate','3','Y','30','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='CBondBenchmark';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('法定存贷款利率','CBondBenchmark','存贷款利率类型','bInfoBenchmark','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('法定存贷款利率','CBondBenchmark','交易日期','tradeDt','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('法定存贷款利率','CBondBenchmark','利率(%)','bInfoRate','3','Y','30','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='CBondAnalysisCNBD';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','交易日期','tradeDt','2','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','待偿期(年)','bAnalMatuCnbd','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','日终估价全价','bAnalPriceCnbd','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价净价','bAnalNetCnbd','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价修正久期','bAnalModiduraCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价凸性','bAnalCnvxtyCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价基点价值','bAnalVobpCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价利差久期','bAnalSprduraCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价利差凸性','bAnalSprcnxtCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价利率久期','bAnalInterestdurationCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价利率凸性','bAnalInterestcnvxtyCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','估价收益率(%)','bAnalYieldCnbd','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中债登估值','CBondAnalysisCNBD','可信度','bAnalCredibility','1','N','50','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='CBondEODPrices';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','交易日期','tradeDt','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','货币代码','crncyCode','1','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','昨收盘价(元)','sDqPreclose','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','开盘价(元)','sDqOpen','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','最高价(元)','sDqHigh','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','最低价(元)','sDqLow','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','收盘价(元)','sDqClose','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','涨跌(元)','sDqChange','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','涨跌幅(%)','sDqPctchange','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','成交量(手)','sDqVolume','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','成交金额(千元)','sDqAmount','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','均价(VWAP)','sDqAvgprice','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券交易所债券行情','CBondEODPrices','交易状态','sDqTradestatus','1','N','10','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='Cbondpricesnet';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-净价','Cbondpricesnet','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-净价','Cbondpricesnet','交易日期','tradeDt','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-净价','Cbondpricesnet','开盘价(元)','bDqOpen','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-净价','Cbondpricesnet','最高价(元)','bDqHigh','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-净价','Cbondpricesnet','最低价(元)','bDqLow','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-净价','Cbondpricesnet','收盘价(元)','bDqOriginclose','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-净价','Cbondpricesnet','成交量(手)','bDqVolume','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-净价','Cbondpricesnet','成交金额(千元)','bDqAmount','3','N','30','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='Cbondprices';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-全价','Cbondprices','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-全价','Cbondprices','交易日期','tradeDt','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-全价','Cbondprices','开盘价(元)','bDqOpen','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-全价','Cbondprices','最高价(元)','bDqHigh','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-全价','Cbondprices','最低价(元)','bDqLow','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-全价','Cbondprices','收盘价(元)','bDqOriginclose','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-全价','Cbondprices','成交量(手)','bDqVolume','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券行情-全价','Cbondprices','成交金额(千元)','bDqAmount','3','N','30','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='Cbondanalysiscsi';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','交易日期','tradeDt','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','信用类型','bInfoType','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','发行市场数','bInfoListingmarketnumbers','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','计算价格','bAnalDirtyCsi','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','计算收益率(%)','bAnalYieldCsi','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','修正久期','bAnalModiduraCsi','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','凸性','bAnalCnvxtyCsi','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','净价','bAnalNetCsi','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中证估值','Cbondanalysiscsi','应计利息','bAnalAccrintCsi','3','N','30','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='Cbondanalysisshc';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','交易日期','tradeDt','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','待偿期(年)','bAnalMatuCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价全价','bAnalDirtyCnbd','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','日间应计利息','bAnalAccrintCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价净价','bAnalNetCnbd','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价收益率(%)','bAnalYieldCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价修正久期','bAnalModiduraCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价凸性','bAnalCnvxtyCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价基点价值','bAnalVobpCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价利差久期','bAnalSprduraCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价利差凸性','bAnalSprcnxtCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场全价','bAnalPrice','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场净价','bAnalNetprice','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场收益率(%)','bAnalYield','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场修正久期','bAnalModifiedduration','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场凸性','bAnalConvexity','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场基点价值','bAnalBpvalue','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场利差久期','bAnalSduration','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场利差凸性','bAnalScnvxty','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价利率久期','bAnalInterestdurationCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','估价利率凸性','bAnalInterestcnvxtyCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场利率久期','bAnalInterestduration','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','市场利率凸性','bAnalInterestcnvxty','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','日终估价全价','bAnalPriceCnbd','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','日终应计利息','bAnalBpyield','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','剩余本金','bAnalSurpluscapital','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','流通场所','bAnalExchange','1','N','40','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国债券上海清算所债券估值','Cbondanalysisshc','可信度','bAnalCredibility','1','N','40','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='Chinaclosedfundeodprice';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','Wind代码','sInfoWindcode','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','交易日期','tradeDt','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','货币代码','crncyCode','1','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','昨收盘价(元)','sDqPreclose','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','开盘价(元)','sDqOpen','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','最高价(元)','sDqHigh','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','最低价(元)','sDqLow','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','收盘价(元)','sDqClose','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','涨跌(元)','sDqChange','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','涨跌幅(%)','sDqPctchange','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','成交量(手)','sDqVolume','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','成交金额(千元)','sDqAmount','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','复权昨收盘价(元)','sDqAdjpreclose','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','复权开盘价(元)','sDqAdjopen','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','复权最高价(元)','sDqAdjhigh','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','复权最低价(元)','sDqAdjlow','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','复权收盘价(元)','sDqAdjclose','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','复权因子','sDqAdjfactor','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','成交笔数','tradesCount','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('中国封闭式基金日行情','Chinaclosedfundeodprice','贴水率（%）','discountRate','3','N','30','','1');

--接口模板DTO匹配表
DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='ChinaMutualFundNAV' AND DTO_NAME='HqParFundInfo';
insert into VAL_INTERF_DTO_MATCH values ('中国共同基金净值','ChinaMutualFundNAV','HqParFundInfo','fInfoWindcode','Wind代码','zqdm','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国共同基金净值','ChinaMutualFundNAV','HqParFundInfo','fNavUnit','单位净值','netValue','单位资产净值','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国共同基金净值','ChinaMutualFundNAV','HqParFundInfo','priceDate','截止日期','dataDate','估值日','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='CMoneyMarketFincome' AND DTO_NAME='HqParFundInfo';
insert into VAL_INTERF_DTO_MATCH values ('货币基金每天净值收益','CMoneyMarketFincome','HqParFundInfo','sInfoWindcode','Wind代码','zqdm','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('货币基金每天净值收益','CMoneyMarketFincome','HqParFundInfo','fInfoEnddate','截止日期','dataDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('货币基金每天净值收益','CMoneyMarketFincome','HqParFundInfo','fInfoUnityield','净值','netValue','证券净值','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='ShiborPrices' AND DTO_NAME='HqIntRateInfo';
insert into VAL_INTERF_DTO_MATCH values ('Shibor行情','ShiborPrices','HqIntRateInfo','sInfoWindcode','Wind代码','intRateId','行情代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('Shibor行情','ShiborPrices','HqIntRateInfo','tradeDt','交易日期','effDate','生效日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('Shibor行情','ShiborPrices','HqIntRateInfo','bInfoRate','利率(%)','intRate','利率','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='CBondBenchmark' AND DTO_NAME='HqIntRateInfo';
insert into VAL_INTERF_DTO_MATCH values ('法定存贷款利率','CBondBenchmark','HqIntRateInfo','bInfoBenchmark','存贷款利率类型','intRateId','行情代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('法定存贷款利率','CBondBenchmark','HqIntRateInfo','tradeDt','交易日期','effDate','生效日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('法定存贷款利率','CBondBenchmark','HqIntRateInfo','bInfoRate','利率(%)','intRate','利率','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='CBondAnalysisCNBD' AND DTO_NAME='HqBondValuationInfo';
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

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='CBondEODPrices' AND DTO_NAME='HqBondMarketInfo';
insert into VAL_INTERF_DTO_MATCH values ('中国债券交易所债券行情','CBondEODPrices','HqBondMarketInfo','sInfoWindcode','Wind代码','secId','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券交易所债券行情','CBondEODPrices','HqBondMarketInfo','tradeDt','交易日期','valueDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券交易所债券行情','CBondEODPrices','HqBondMarketInfo','sDqHigh','最高价(元)','maxPrice','最高价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券交易所债券行情','CBondEODPrices','HqBondMarketInfo','sDqLow','最低价(元)','minPrice','最低价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券交易所债券行情','CBondEODPrices','HqBondMarketInfo','sDqClose','收盘价(元)','closePrice','收盘价','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='Cbondpricesnet' AND DTO_NAME='HqBondMarketInfo';
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-净价','Cbondpricesnet','HqBondMarketInfo','sInfoWindcode','Wind代码','secId','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-净价','Cbondpricesnet','HqBondMarketInfo','tradeDt','交易日期','valueDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-净价','Cbondpricesnet','HqBondMarketInfo','bDqHigh','最高价(元)','maxPrice','最高价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-净价','Cbondpricesnet','HqBondMarketInfo','bDqLow','最低价(元)','minPrice','最低价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-净价','Cbondpricesnet','HqBondMarketInfo','bDqOriginclose','收盘价(元)','closePrice','收盘价','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='Cbondprices' AND DTO_NAME='HqBondMarketInfo';
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-全价','Cbondprices','HqBondMarketInfo','sInfoWindcode','Wind代码','secId','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-全价','Cbondprices','HqBondMarketInfo','tradeDt','交易日期','valueDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-全价','Cbondprices','HqBondMarketInfo','bDqHigh','最高价(元)','maxPrice','最高价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-全价','Cbondprices','HqBondMarketInfo','bDqLow','最低价(元)','minPrice','最低价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券行情-全价','Cbondprices','HqBondMarketInfo','bDqOriginclose','收盘价(元)','closePrice','收盘价','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='Cbondanalysiscsi' AND DTO_NAME='HqBondValuationInfo';
insert into VAL_INTERF_DTO_MATCH values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','sInfoWindcode','Wind代码','secId','债券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','tradeDt','交易日期','valueDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','bInfoType','信用类型','relibity','是否推荐','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','bAnalDirtyCsi','计算价格','price','市价全价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','bAnalYieldCsi','计算收益率(%)','bpyield','市价收益率','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','bAnalModiduraCsi','修正久期','mDuration','mDuration','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','bAnalCnvxtyCsi','凸性','mConvexity','市价凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中证估值','Cbondanalysiscsi','HqBondValuationInfo','bAnalNetCsi','净价','netprice','市价净价','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='Cbondanalysisshc' AND DTO_NAME='HqBondValuationInfo';
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','sInfoWindcode','Wind代码','secId','债券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','tradeDt','交易日期','valueDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalMatuCnbd','待偿期(年)','lastPeriod','剩余期限','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalDirtyCnbd','估价全价','price','市价全价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalNetCnbd','估价净价','netprice','市价净价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalYieldCnbd','估价收益率(%)','bpyield','市价收益率','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalModiduraCnbd','估价修正久期','mDuration','市价久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalCnvxtyCnbd','估价凸性','mConvexity','市价凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalVobpCnbd','估价基点价值','bpvalue','基点价值','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalSprduraCnbd','估价利差久期','sduration','利差久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalSprcnxtCnbd','估价利差凸性','scnvxty','利差凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalInterestdurationCnbd','估价利率久期','interestDuration','利率久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalInterestcnvxtyCnbd','估价利率凸性','interestCnvxty','利率凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国债券上海清算所债券估值','Cbondanalysisshc','HqBondValuationInfo','bAnalCredibility','可信度','relibity','是否推荐','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='Chinaclosedfundeodprice' AND DTO_NAME='HqFundMarketInfo';
insert into VAL_INTERF_DTO_MATCH values ('中国封闭式基金日行情','Chinaclosedfundeodprice','HqFundMarketInfo','sInfoWindcode','Wind代码','fundCode','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国封闭式基金日行情','Chinaclosedfundeodprice','HqFundMarketInfo','tradeDt','交易日期','valueDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国封闭式基金日行情','Chinaclosedfundeodprice','HqFundMarketInfo','sDqHigh','最高价(元)','maxPrice','最高价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国封闭式基金日行情','Chinaclosedfundeodprice','HqFundMarketInfo','sDqLow','最低价(元)','minPrice','最低价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('中国封闭式基金日行情','Chinaclosedfundeodprice','HqFundMarketInfo','sDqClose','收盘价(元)','closePrice','收盘价','system','',sysdate,'system',sysdate);


commit;