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
insert into VAL_INTERF_TEMPLATE values ('万得开放式基金','par_openfund_info','HqParFundInfo','100104','04','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('万得封闭式基金','par_Closefund_info','HqParFundInfo','100104','04','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('杭州-债券市场行情-上交所','mktdt00','HqBondMarketInfo','100101','02','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('杭州-债券市场行情-深交所','cashsecurityclosemd','HqBondMarketInfo','100101','01','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('杭州-基金市场行情-上交所','mktdt00','HqFundMarketInfo','100103','02','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('杭州-基金市场行情-深交所','cashsecurityclosemd','HqFundMarketInfo','100103','01','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('杭州-债券估值行情-中债','SRC_CBVLT','HqBondValuationInfo','100102','04','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('杭州-债券估值行情-中证','bond_valuation','HqBondValuationInfo','100102','02','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('杭州-利率行情行情','SRC_INTEREST_RATE','HqIntRateInfo','100105','02','ZX','system','',sysdate,'system',sysdate);

--模板字段校验信息配置表
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='par_openfund_info';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('万得开放式基金','par_openfund_info','公告日期','DATA_DATE','1','Y','8','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('万得开放式基金','par_openfund_info','单位资产净值','NET_VALUE','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('万得开放式基金','par_openfund_info','证券代码','ZQDM','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('万得开放式基金','par_openfund_info','累计净值','TOTAL_NET','1','N','32','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='par_Closefund_info';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('万得封闭式基金','par_Closefund_info','行情日期','HQRQ','1','Y','8','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('万得封闭式基金','par_Closefund_info','证券代码','ZQDM','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('万得封闭式基金','par_Closefund_info','证券净值','ZQJZ','1','Y','32','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='cashsecurityclosemd';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场行情-深交所','cashsecurityclosemd','证券代码','SecurityID','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场行情-深交所','cashsecurityclosemd','今日收盘价','ClosePx','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('市场行情-深交所','cashsecurityclosemd','证券类型','SecurityType','1','Y','2','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='MainTable';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','债券代码','BdId','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','估值日','ValtnDt','1','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','交易场所','ListgMktCd','1','Y','4','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','估值全价','ValtdDrtyP','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','估值净价','ValtdClnPr','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','估价收益率','ValtdYld','1','Y','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','久期','ValtdModfd','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','凸性','ValtdConex','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','估价基点价值','ValtdPVBP','1','N','32','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券估值-中债','MainTable','是否推荐','Relibity','1','N','32','','1');

--接口模板DTO匹配表
DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='par_openfund_info' AND DTO_NAME='HqParFundInfo';
insert into VAL_INTERF_DTO_MATCH values ('万得开放式基金','par_openfund_info','HqParFundInfo','ZQDM','证券代码','zqdm','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('万得开放式基金','par_openfund_info','HqParFundInfo','NET_VALUE','单位资产净值','netValue','单位资产净值','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('万得开放式基金','par_openfund_info','HqParFundInfo','DATA_DATE','公告日期','dataDate','公告日期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('万得开放式基金','par_openfund_info','HqParFundInfo','TOTAL_NET','累计净值','totalNet','累计净值','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('万得封闭式基金','par_Closefund_info','HqParFundInfo','ZQDM','证券代码','zqdm','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('万得封闭式基金','par_Closefund_info','HqParFundInfo','ZQJZ','证券净值','netValue','单位资产净值','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('万得封闭式基金','par_Closefund_info','HqParFundInfo','HQRQ','行情日期','dataDate','行情日期','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='cashsecurityclosemd' AND DTO_NAME='HqBondMarketInfo';
insert into VAL_INTERF_DTO_MATCH values ('杭州-债券市场行情-深交所','cashsecurityclosemd','HqBondMarketInfo','SecurityID','证券代码','secId','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('杭州-债券市场行情-深交所','cashsecurityclosemd','HqBondMarketInfo','ClosePx','今日收盘价','closePrice','今日收盘价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('杭州-债券市场行情-深交所','cashsecurityclosemd','HqBondMarketInfo','SecurityType','证券类型','secType','证券类型','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='cashsecurityclosemd' AND DTO_NAME='HqFundMarketInfo';
insert into VAL_INTERF_DTO_MATCH values ('杭州-基金市场行情-深交所','cashsecurityclosemd','HqFundMarketInfo','SecurityID','证券代码','fundCode','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('杭州-基金市场行情-深交所','cashsecurityclosemd','HqFundMarketInfo','ClosePx','今日收盘价','closePrice','今日收盘价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('杭州-基金市场行情-深交所','cashsecurityclosemd','HqFundMarketInfo','SecurityType','证券类型','secType','证券类型','system','',sysdate,'system',sysdate);


commit;