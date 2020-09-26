/*接口文件数据增加系统参数 add by yuluan  2019-04-25 begin*/
delete from SYS_PARA where SYSTEM_CODE='SVR' and PARA_CODE in ('SVR_PRD_COST_ALG','SVR_PRD_DEAL_DIMENSION');
insert into SYS_PARA values ('SVR','SVR_PRD_COST_ALG','01','01','01,02','产品成本算法：01子系统移动平均，02上游直接提供','system','',to_date('2019/4/15', 'yyyy/mm/dd'), 'system',to_date('2019/4/15', 'yyyy/mm/dd'));
insert into SYS_PARA values ('SVR','SVR_PRD_DEAL_DIMENSION','01','01','01,02','产品交易维度：01投资者交易明细维度，02客户组别维度','system','',to_date('2019/4/15', 'yyyy/mm/dd'), 'system',to_date('2019/4/15', 'yyyy/mm/dd'));
/*接口文件数据增加系统参数 add by yuluan  2019-04-25 end*/

/*延时报送检查增加系统参数 add by liuyu  2019-04-25 begin*/
DELETE FROM SYS_PARA WHERE SYSTEM_CODE = 'SVR' AND PARA_CODE IN ('COLOR_CONFIG_mid_list','COLOR_CONFIG_top_list');
INSERT INTO SYS_PARA VALUES('SVR','COLOR_CONFIG_mid_list','69,72,73,18,75,76,77,78,79,80,40,60','01',NULL,'延迟报送检查画面的配置参数(mids)','system',NULL,to_date('2019/04/12','yyyy/mm/dd'),'admin',to_date('2019/04/12','yyyy/mm/dd'));
insert into SYS_PARA values('SVR','COLOR_CONFIG_top_list','81,82,83,84,85,86,87,88,89,90,65,95','01',null,'延迟报送检查画面的配置参数(tops)','system',null,to_date('2019/04/12','yyyy/mm/dd'),'admin',to_date('2019/04/12','yyyy/mm/dd'));
/*延时报送检查增加系统参数 add by liuyu  2019-04-25 end*/



/*中债总体核查批处理 add by weiyuqiang 2019-4-19 start*/
delete from sys_batch_task where task_id = '3051';
INSERT INTO SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES ('3051','SVR','中债总体核查批处理',NULL,'chbTotalCheckCBService',3050,'system',TO_TIMESTAMP('2019-04-19 00.00.00.000000','yyyy-mm-dd hh24:mi:ss.ff6'),'system',TO_TIMESTAMP('2019-04-19 00.00.00.000000','yyyy-mm-dd hh24:mi:ss.ff6'));
/*中债总体核查批处理 add by weiyuqiang 2019-4-19 end*/


/*添加总体核查菜单栏 add by weiyuqiang 2019-4-23 start*/
delete from sys_menu where menu_id = 'REPORT_002003';
INSERT INTO sys_menu(menu_id,system_code,icon,parent_menu_id,page_id,order_no,is_spread,remark,create_user,create_dept,create_time,update_user,update_time) 
VALUES ('REPORT_002003','SVR',NULL,'REPORT_002','SvrTotalCheck001',3,NULL,'总体核查','system',NULL,to_timestamp('2019-04-23 00.00.00','yyyy-MM-dd HH24:MI:SS'),'system',to_timestamp('2019-04-23 00.00.00','yyyy-MM-dd HH24:MI:SS'));
delete from sys_page where page_id = 'SvrTotalCheck001';
INSERT INTO sys_page (page_id,system_code,page_url,page_file,menu_id,height,width,is_hp_select,is_need_workflow,remark,create_user,create_dept,create_time,update_user,update_time) 
VALUES ('SvrTotalCheck001','SVR','/chb/datacheck/totalcheck/TotalCheck001','report/chb/datacheck/totalcheck/TotalCheck001','REPORT_002003',NULL,NULL,'02','02','总体核查','system',NULL,to_timestamp('2019-04-23 00.00.00','yyyy-MM-dd HH24:MI:SS'),'system',to_timestamp('2019-04-23 00.00.00','yyyy-MM-dd HH24:MI:SS'));
/*添加总体核查菜单栏 add by weiyuqiang 2019-4-23 end*/
/*系统参数添加：总体核查合格率阈值 add by weiyuqiang 2019-4-23 start*/
delete from sys_para where system_code = 'SVR' and para_code = 'SVR_TOTALCHECK_COLOR_CONFIG_MID';
INSERT INTO SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('SVR','SVR_TOTALCHECK_COLOR_CONFIG_MID','50,50,50,50,50,50,50,50','01','核查合格率阈值-中间值','核查合格率阈值-中间值：8个阈值用","隔开','system',null,to_timestamp('2019-04-23 00:00:00','yyyy-MM-dd hh24:mi:ss'),'system',to_timestamp('2019-04-23 00:00:00','yyyy-MM-dd hh24:mi:ss'));
delete from sys_para where system_code = 'SVR' and para_code = 'SVR_TOTALCHECK_COLOR_CONFIG_TOP';
INSERT INTO SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('SVR','SVR_TOTALCHECK_COLOR_CONFIG_TOP','90,90,90,90,90,90,90,90','01','核查合格率阈值-顶值','核查合格率阈值-顶值：8个阈值用","隔开','system',null,to_timestamp('2019-04-23 00:00:00','yyyy-MM-dd hh24:mi:ss'),'system',to_timestamp('2019-04-23 00:00:00','yyyy-MM-dd hh24:mi:ss'));
/*系统参数添加：总体核查合格率阈值 add by weiyuqiang 2019-4-23 end*/


/**增加系统参数 产品要素接口类型add by yuluan 2019-04-28 begin**/
delete from sys_para where system_code = 'SVR' and para_code = 'SVR_PRD_BASEINFO_INTERF_TYPE';
INSERT INTO SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('SVR','SVR_PRD_BASEINFO_INTERF_TYPE','01','01','01,02','产品要素接口类型：01上游数据库；02文件','system',null,to_timestamp('2019-04-28 00:00:00','yyyy-MM-dd hh24:mi:ss'),'system',to_timestamp('2019-04-28 00:00:00','yyyy-MM-dd hh24:mi:ss'));
/**增加系统参数 产品要素接口类型add by yuluan 2019-04-28 end**/
/**增加系统参数 系统类别add by yuluan 2019-04-28 begin**/
delete from sys_para where system_code = 'SVR' and para_code = 'SVR_SYSTEM_TYPE';
INSERT INTO SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('SVR','SVR_SYSTEM_TYPE','01','01','01,02,03,04','系统类别：01中债一二期,02人行,03中债三期，04信批（可以填多个以，隔开）','system',null,to_timestamp('2019-04-28 00:00:00','yyyy-MM-dd hh24:mi:ss'),'system',to_timestamp('2019-04-28 00:00:00','yyyy-MM-dd hh24:mi:ss'));
/**增加系统参数 系统类别add by yuluan 2019-04-28 end**/

delete from sys_para where system_code = 'SVR' and para_code = 'CHB_PRD_DEAL_FILE_TYPE';
INSERT INTO SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('SVR','CHB_PRD_DEAL_FILE_TYPE','01','01','01,02','产品交易文件接口类型：01规范文件；02阜新恒生文件','system',null,to_timestamp('2019-05-09 00:00:00','yyyy-MM-dd hh24:mi:ss'),'system',to_timestamp('2019-05-09 00:00:00','yyyy-MM-dd hh24:mi:ss'));
/**增加交易文件接口 是01规范的还是02阜新恒生参数设置 add by yuluan 2019-04-09 end**/
/*重新整理批处理 add by weiyuqiang 2019-5-9 start*/
delete from SYS_BATCH_TASK where SYSTEM_CODE = 'REP' or SYSTEM_CODE = 'SVR';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3001','SVR','投资者身份报送',null,'equityInfoAutoSendCBService',3002,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3002','SVR','投资者持有报送',null,'investorsHoldInfoRegistAutoSendCBService',3003,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3003','SVR','投资者报送前检查',null,'checkReportAutoSendCheckService',3001,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3004','SVR','投资这报送数据生成',null,'generateInvestorsHoldInfoAutoSendCBService',3001,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3005','SVR','投资者报送数据归档',null,'reportDataBackupService',3004,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3010','SVR','交易对手接口数据接入',null,'svrThirdPartyInterfTaskService',3050,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3011','SVR','产品基本信息接口数据接入',null,'svrProdInfoInterfTaskService',3050,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3012','SVR','产品开放计划接口数据接入',null,'svrProdPlanInterfTaskService',3050,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3013','SVR','产品交易信息接入接入',null,'svrProdDealInterfTaskService',3100,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3014','SVR','产品费用交易接口数据接入',null,'svrProdFeeDealInterfTaskService',3100,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3015','SVR','产品收益率净值接口数据接入',null,'svrProdIncomeInterfTaskService',3050,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3016','SVR','资产要素信息接口数据接入',null,'svrAssetInfoInterfTaskService',3050,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3017','SVR','投资交易信息接口数据接入',null,'svrAssetDealInterfTaskService',3100,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3018','SVR','资管计划底层资产持仓接口数据接入',null,'svrBotAssetPosInterfTaskService',3100,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3019','SVR','资产基础指标数据接入',null,'svrAssetIndexInterfTaskService',3100,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3029','SVR','产品头寸批处理',null,'svrProdPositionTaskService',3200,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3030','SVR','资产头寸批处理',null,'svrAssetPositionTaskService',3200,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3031','SVR','产品发行申报登记报送',null,'svrProdIssueAndDeclareRegTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3032','SVR','产品存续期交易登记报送',null,'svrProdDurationRegTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3033','SVR','资产要素登记报送',null,'svrAssetInfoRegTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3034','SVR','投资交易登记报送',null,'svrAssetDealRegTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3035','SVR','产品募集总量登记报送',null,'svrProdRaiseRegTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3036','SVR','产品终止登记报送',null,'svrProdEndRegTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3037','SVR','底层持仓信息登记报送',null,'svrBotAssetPosRegTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3038','SVR','资产估值信息登记报送',null,'svrAssetValRegTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3039','SVR','银行月度统计表生成',null,'svrMonthReportTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3040','SVR','人行报表生成',null,'svrPbcReportTaskService',3250,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('3041','SVR','总体核查',null,'svrTotalCheckCBService',3300,'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'),'system',to_timestamp('2019-05-09 00.00.00','yyyy-MM-dd HH24.MI.SS'));


/*报送信息登记菜单、报送时点管理相关调整 add by jiangxiao 2019-05-13 start*/
update sys_menu set ORDER_NO = 1 where MENU_ID = 'SVR_003_004_006';
update sys_menu set ORDER_NO = 2 where MENU_ID = 'SVR_003_004_002';
update sys_menu set ORDER_NO = 3 where MENU_ID = 'SVR_003_004_005';
update sys_menu set ORDER_NO = 4 where MENU_ID = 'SVR_003_004_004';
update sys_menu set ORDER_NO = 5 where MENU_ID = 'SVR_003_004_007';
update sys_menu set ORDER_NO = 1 where MENU_ID = 'SVR_003_005_001';
update sys_menu set ORDER_NO = 2,remark = '资产交易登记' where MENU_ID = 'SVR_003_005_004';
update sys_menu set ORDER_NO = 3 where MENU_ID = 'SVR_003_005_002';
update sys_menu set ORDER_NO = 4 where MENU_ID = 'SVR_003_005_003';
delete from SYS_MENU where MENU_ID in ('SVR_003_007_001','SVR_003_006_002');
Insert into SYS_MENU (SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,MENU_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR',null,'SVR_003_006','PbcMonthReport001','SVR_003_006_002',2,null,'人行月度报表登记','system',null,to_timestamp('2019-02-27 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-02-27 00:00:00','YYYY-MM-DD HH24:MI:SS'));
delete from sys_menu where menu_id = 'SVR_003_007';
update sys_menu set remark = '监管报送时点管理' where  menu_id = 'REPORT_004003';

/*报送信息登记菜单、报送时点管理相关调整 add by jiangxiao 2019-05-13 end*/

/*系统参数 ：评级取值规则 01:推荐评级 02:最大评级 03:最小评级  add by jiangxiao 2019-05-14*/
delete from SYS_PARA where PARA_CODE = 'SVR_SEC_RATING_FLAG';
Insert into SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR','SVR_SEC_RATING_FLAG','02','02',null,'评级取值规则(01:推荐评级02:最大评级03:最小评级)','system',null,to_timestamp('2018-02-04 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2018-02-04 00:00:00','YYYY-MM-DD HH24:MI:SS'));
/*系统参数 ：评级取值规则 01:推荐评级 02:最大评级 03:最小评级  add by jiangxiao 2019-05-14*/

/**报送检查-数据检查画面及菜单add by liuyu start*/
delete from sys_menu where menu_id = 'REPORT_002004';
INSERT INTO sys_menu(menu_id,system_code,icon,parent_menu_id,page_id,order_no,is_spread,remark,create_user,create_dept,create_time,update_user,update_time) 
VALUES ('REPORT_002004','SVR',NULL,'REPORT_002','ErrorMessage001',4,NULL,'数据检查','system',NULL,to_timestamp('2019-05-14 00.00.00','yyyy-MM-dd HH24:MI:SS'),'system',to_timestamp('2019-05-14 00.00.00','yyyy-MM-dd HH24:MI:SS'));
delete from sys_page where page_id = 'ErrorMessage001';
INSERT INTO sys_page (page_id,system_code,page_url,page_file,menu_id,height,width,is_hp_select,is_need_workflow,remark,create_user,create_dept,create_time,update_user,update_time) 
VALUES ('ErrorMessage001','REP','/chb/errormessage/ErrorMessage001','report/errormessage/ErrorMessage001','REPORT_002004',NULL,NULL,'02','02','数据检查','system',NULL,to_timestamp('2019-05-14 00.00.00','yyyy-MM-dd HH24:MI:SS'),'system',to_timestamp('2019-05-14 00.00.00','yyyy-MM-dd HH24:MI:SS'));
/**add by liuyu end*/

/*add by zhangzhongliang start 2019-05-15 G06理财业务月度统计表菜单*/
delete from SYS_MENU where MENU_ID = 'SVR_003_006_003';
delete from SYS_PAGE where PAGE_ID = 'SvrG06MonthReport001';
insert into SYS_MENU values ('SVR_003_006_003','SVR','','SVR_003_006','SvrG06MonthReport001','3','','G06理财业务月度统计表','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
insert into SYS_PAGE values ('SvrG06MonthReport001','SVR','/chb/monthreport/ChbG06MonthReport001','report/chb/monthreport/ChbG06MonthReport001','SVR_003_006_003','','','01','01','G06理财业务月度统计表','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
/*add by zhangzhongliang end 2019-05-15 G06理财业务月度统计表菜单*/
/**延时报送检查增加tab页配置参数 add by yuluan 2019-05-18 begin**/
delete from SYS_PARA where SYSTEM_CODE='SVR' and PARA_CODE ='SVR_DELAY_SUBMIT_TAB';
insert into SYS_PARA values ('SVR','SVR_DELAY_SUBMIT_TAB','ALL','01','CHB,PBC,ALL','延时报送检查显示tab页：ALL 全部，CHB中债,PBC 人行','system','',to_date('2019/5/18', 'yyyy/mm/dd'), 'system',to_date('2019/5/18', 'yyyy/mm/dd'));
/**延时报送检查增加tab页配置参数 add by yuluan 2019-05-18 end**/

/*报送首页、业务首页 add by jiangxiao 2019-05-20 start*/
delete from sys_page where page_id in ('SvreportHome001','SvreportHome002');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvreportHome001','SVR','/system/reportHome','report/system/reportHome',null,null,null,'01','01','报送首页','system',null,to_timestamp('2018-05-08 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2018-05-08 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SvreportHome002','SVR','/system/businessHome','report/system/businessHome',null,null,null,'01','01','业务首页','system',null,to_timestamp('2018-05-08 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2018-05-08 00:00:00','YYYY-MM-DD HH24:MI:SS'));
/*报送首页、业务首页 add by jiangxiao 2019-05-20 end*/

/*移除原节假日，汇率菜单 add by jiangxiao 2019-05-20 start*/
delete from sys_menu where page_id in ('Exchangerate001','Holidaymarket001');
delete from sys_page where page_id in ('Exchangerate001','Holidaymarket001');
/*移除原节假日，汇率菜单 add by jiangxiao 2019-05-20 end*/
--删除“其他信息”一级，二级菜单
delete from SYS_MENU  where MENU_ID in ('REPORT_001004');
delete from sys_menu where MENU_ID in ('REPORT_001004002','REPORT_001004003');

COMMIT;