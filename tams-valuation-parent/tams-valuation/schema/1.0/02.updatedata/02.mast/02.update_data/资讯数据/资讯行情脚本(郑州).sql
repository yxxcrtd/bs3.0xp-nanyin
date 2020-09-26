--文件下载服务器，每个行根据实际情况进行配置
delete from VAL_INTERF_SERVER_INFO where systemid='ZX';
insert into VAL_INTERF_SERVER_INFO(SERVERIP,SERVERPORT,USERNAME,PASSWORD,SERVERTYPE,STATUS,SYSTEMID,REMOTE_PATH,LOCAL_PATH,RS,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('192.168.70.220','22','tams_user','tams_user','02','Y','ZX','INPUT/ZG','D:\interf','R','system','',sysdate,'system',sysdate);

--菜单
delete from BOK_BATCH_TASK_GRP  where task_grp_id = '3051' or p_task_grp_id='3051';

insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('3051','资讯行情','InterfData','','3051',100200,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('FundValuationRate','基金估值行情','InterfData','3051','FundValuationRate',100201,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('IntRate','利率行情','InterfData','3051','IntRate',100202,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');
insert into BOK_BATCH_TASK_GRP  (task_grp_id, task_grp_name, task_grp_type, p_task_grp_id, task_id, order_no, create_user, create_time, update_user, update_time, create_dept) values ('SecValuationRate','债券估值行情','InterfData','3051','SecValuationRate',100203,'admin',to_date('2018-09-17','yyyy-MM-dd'),'admin',to_date('2018-09-17','yyyy-MM-dd'),'');

--接口任务批次
delete from VAL_INTERF_TASK where TASK_ID='30';
insert into VAL_INTERF_TASK values ('30','hqDetailInfoRecRunner','资讯数据接口','3051','E','R','ZX','system','',sysdate,'system',sysdate);


--接口定义
DELETE FROM VAL_INTERF_DEFINE WHERE INTERF_ID IN ('100101','100102','100103','100104','100105');
insert into VAL_INTERF_DEFINE values ('100102','债券估值行情','SecValuationRate','3051','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100104','基金估值行情','FundValuationRate','3051','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DEFINE values ('100105','利率行情','IntRate','3051','system','',sysdate,'system',sysdate);

--接口模板
DELETE FROM VAL_INTERF_TEMPLATE WHERE INTERF_ID IN ('100101','100102','100103','100104','100105');
insert into VAL_INTERF_TEMPLATE values ('基金市场估值','SRC_FUNDVALUE','HqParFundInfo','100104','06','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','100102','06','ZX','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_TEMPLATE values ('基础利率重置','SRC_INTEREST_RATE','HqIntRateInfo','100105','06','ZX','system','',sysdate,'system',sysdate);

--模板字段校验信息配置表
DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='SRC_FUNDVALUE';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金市场估值','SRC_FUNDVALUE','基金ID','FUNDID','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金市场估值','SRC_FUNDVALUE','基金代码','FUNDCODE','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金市场估值','SRC_FUNDVALUE','基金类型','FUNDTYPE','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金市场估值','SRC_FUNDVALUE','估值日期','FUNDDATE','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基金市场估值','SRC_FUNDVALUE','估值','FUNDVALUE','3','Y','30','','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='SRC_CBVLT';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','债券id','SECID','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','估值日','CDATE','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','剩余期限','ENCASH_YEARS','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','市价全价','MDPRICE','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','市价净价','MCPRICE','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','市价久期','M_MDURATION','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','市价凸性','M_CNVXTY','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','基点价值','MBASISVALUE','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','利差久期','MSPRD_D','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','利差凸性','MSPRD_CNVXTY','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','利率久期','MYIELD_D','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','利率凸性','MYIELD_CNVXTY','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','市价收益率','MYIELD','3','N','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','账户','ACCOUNT','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('债券市价估值','SRC_CBVLT','估值来源','GZLY','1','Y','50','VALUE_SOURCE_ZX','1');

DELETE FROM VAL_TEMP_COL_CHECK_INFO WHERE TMP_E_NAME='SRC_INTEREST_RATE';
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','发布日','PUBLISHDATE','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','利率类型','INTERESTRATEKIND','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','利率code','CURRENCYCODE','1','Y','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','币种','CURRENCY','1','Y','50','CCY','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','利率值','INTERESTRATE','3','Y','30','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','生效日','EFFECTIVEDATE','2','Y','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','ISEFFECTORNOT','ISEFFECTORNOT','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','INNAME','INNAME','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','RELATDATA','RELATDATA','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','PUBDATE','PUBDATE','2','N','10','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','CHANGEHIS','CHANGEHIS','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','DATASRC','DATASRC','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','变化百分比','UPORDOWN','1','N','50','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','期限','TERM','1','N','12','','1');
insert into VAL_TEMP_COL_CHECK_INFO (TMP_C_NAME,TMP_E_NAME,COL_C_NAME,COL_E_NAME,COL_TYPE,IS_NULL,MAX_LENGTH,DICT_CODE,IS_EXIST) values ('基础利率重置','SRC_INTEREST_RATE','是否下发','NEEDREFRESH','1','Y','50','YES_OR_NO','1');

--接口模板DTO匹配表
DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='SRC_FUNDVALUE' AND DTO_NAME='HqParFundInfo';
insert into VAL_INTERF_DTO_MATCH values ('基金市场估值','SRC_FUNDVALUE','HqParFundInfo','FUNDCODE','基金代码','zqdm','证券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('基金市场估值','SRC_FUNDVALUE','HqParFundInfo','FUNDDATE','估值日期','valDate','估值日期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('基金市场估值','SRC_FUNDVALUE','HqParFundInfo','FUNDVALUE','估值','netValue','单位资产净值','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='SRC_CBVLT' AND DTO_NAME='HqBondValuationInfo';
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','SECID','债券id','secId','债券代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','CDATE','估值日','valueDate','估值日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','ENCASH_YEARS','剩余期限','lastPeriod','剩余期限','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','MDPRICE','市价全价','price','市价全价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','MCPRICE','市价净价','netprice','市价净价','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','M_MDURATION','市价久期','mDuration','市价久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','M_CNVXTY','市价凸性','mConvexity','市价凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','MBASISVALUE','基点价值','bpvalue','基点价值','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','MSPRD_D','利差久期','sduration','利差久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','MSPRD_CNVXTY','利差凸性','scnvxty','利差凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','MYIELD_D','利率久期','interestDuration','利率久期','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','MYIELD_CNVXTY','利率凸性','interestCnvxty','利率凸性','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','MYIELD','市价收益率','bpyield','市价收益率','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('债券市价估值','SRC_CBVLT','HqBondValuationInfo','GZLY','估值来源','valueource','估值来源','system','',sysdate,'system',sysdate);

DELETE FROM  VAL_INTERF_DTO_MATCH WHERE TMP_E_NAME='SRC_INTEREST_RATE' AND DTO_NAME='HqIntRateInfo';
insert into VAL_INTERF_DTO_MATCH values ('基础利率重置','SRC_INTEREST_RATE','HqIntRateInfo','PUBLISHDATE','发布日','relaeseDate','发布日','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('基础利率重置','SRC_INTEREST_RATE','HqIntRateInfo','CURRENCYCODE','利率code','intRateId','行情代码','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('基础利率重置','SRC_INTEREST_RATE','HqIntRateInfo','INTERESTRATE','利率值','intRate','利率','system','',sysdate,'system',sysdate);
insert into VAL_INTERF_DTO_MATCH values ('基础利率重置','SRC_INTEREST_RATE','HqIntRateInfo','EFFECTIVEDATE','生效日','effDate','生效日','system','',sysdate,'system',sysdate);

/**base用户下执行 START**/
--系统参数
DELETE FROM SYS_PARA WHERE PARA_CODE IN ('VALUE_SOURCE_KG','VALUE_SOURCE_DEFAULT');
insert into SYS_PARA values ('VAL','VALUE_SOURCE_KG','OFF','01','(null)','资讯接口(标准TXT):估值来源取值开关 ON:开 OFF:关','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));
insert into SYS_PARA values ('VAL','VALUE_SOURCE_DEFAULT','CBC','01','(null)','资讯接口(标准TXT):债券估值无估值来源时设置的默认值，中债:CBC、中证:CSD、上清:SCH','system','',to_date('2018/11/5', 'yyyy/mm/dd'), 'system',to_date('2018/11/5', 'yyyy/mm/dd'));

--数据字典
delete from SYS_DATADICT where dict_code='VALUE_SOURCE_ZX';
insert into SYS_DATADICT values ('VALUE_SOURCE_ZX','VAL','估值来源_资讯','','02','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));

delete from SYS_DATADICT_LIST where dict_code='VALUE_SOURCE_ZX';
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','中债','CBC','1','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','中证','CSD','2','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('VALUE_SOURCE_ZX','上清','SCH','3','01','system','',to_date('2018/11/3', 'yyyy/mm/dd'), 'system',to_date('2018/11/3', 'yyyy/mm/dd'));

/**base用户下执行 END**/

commit;