/**增加人行报表系统目录及菜单 addd by yuluan 2019-03-06 begin **/
delete from SYS_MENU where MENU_ID in ('SVR_003_007','SVR_003_007_001');
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_003_007','SVR',null,'SVR_003',null,7,null,'人行报送信息登记','system',null,to_timestamp('2019-02-27 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-02-27 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_MENU (MENU_ID,SYSTEM_CODE,ICON,PARENT_MENU_ID,PAGE_ID,ORDER_NO,IS_SPREAD,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('SVR_003_007_001','SVR',null,'SVR_003_007','PbcMonthReport001',1,null,'人行资管产品月度统计报表','system',null,to_timestamp('2019-02-27 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-02-27 00:00:00','YYYY-MM-DD HH24:MI:SS'));
delete from SYS_PAGE where PAGE_ID ='PbcMonthReport001';
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','SVR','/svreport/pbc/PbcMonthReport001','report/pbc/PbcMonthReport001','SVR_003_007_001',null,null,'01','01','人行资管产品月度统计报表','system',null,to_timestamp('2019-02-27 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-02-27 00:00:00','YYYY-MM-DD HH24:MI:SS'));
/**增加人行报表系统目录及菜单 addd by yuluan 2019-03-06 end **/
/**增加人行报表报表名称下拉选项 addd by yuluan 2019-03-06 begin **/
delete from SYS_DATADICT where DICT_CODE='PBC_REPORT_TYPE';
Insert into SYS_DATADICT (DICT_CODE,SYSTEM_CODE,DICT_CNNAME,DICT_ENNAME,UPDATE_FLG,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','PBC','人行报表名称',null,'1','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
delete from SYS_DATADICT_LIST where DICT_CODE='PBC_REPORT_TYPE';
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','1_1','资管产品募集余额统计表',1,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','1_2','资管产品募集及兑付发生额统计表',2,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','1_3','资管产品只数情况统计表',3,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','1_4','资管产品境内募集余额分地区统计表',4,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','1_5','资管产品提前及延期兑付统计表',5,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','1_6','资管产品到期未兑付统计表',6,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','2_1','资管产品资产负债统计表',7,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','2_2','除回购和拆借外贷款分行业、企业规模统计表',8,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','2_3','除回购和拆借外贷款分地区统计表',9,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','2_4','资产收益权按基础资产投向分类统计表',10,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','2_5','企业债券分行业、企业规模统计表',11,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','3_1','存续产品分合同期限募集余额统计表',12,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','3_2','新发产品分合同期限募集金额统计表',13,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
Insert into SYS_DATADICT_LIST (DICT_CODE,ITEM_CODE,ITEM_VALUE,ORDER_NO,DEFAULT_VALUE,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PBC_REPORT_TYPE','3_3','资管产品资产负债剩余期限统计表',14,'0','system',null,to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'),'system',to_timestamp('2019-03-12 00:00:00','YYYY-MM-DD HH24:MI:SS'));
/**增加人行报表报表名称下拉选项 addd by yuluan 2019-03-06 end **/
/*添加产品品种枚举值 add by weiyuqiang 2019-3-7 start*/

/*增加人行报表通道是否穿透开关 add by yuluan 2019-3-11 begin*/
delete from SYS_PARA where system_code ='SVR' and para_code='PBC_IS_CHL_BTM';
insert into SYS_PARA values ('SVR','PBC_IS_CHL_BTM','01','01','数据字典(是否)','人行通道投资资产管理产品是否穿透报送(01：是，02：否)','system','',to_date('2019/2/27', 'yyyy/mm/dd'), 'system',to_date('2019/2/27', 'yyyy/mm/dd'));
/*增加人行报表通道是否穿透开关 add by yuluan 2019-3-11 end*/


/*增加人行汇率取值参数 add by jiangxiao 2019-3-13 start*/
delete from SYS_PARA where PARA_CODE='SVR_EXCHANGE_SOURCE_PBC';
insert into SYS_PARA values ('SVR','SVR_EXCHANGE_SOURCE_PBC','05','02','01,02,03,04,05','人行汇率取值来源(01:开盘价，02:收盘价，03:最高价，04:最低价，05:中间价)','system','',to_date('2019/3/13', 'yyyy/mm/dd'), 'system',to_date('2019/3/13', 'yyyy/mm/dd'));
/*增加人行汇率取值参数 add by jiangxiao 2019-3-13 end*/



/*系统参数添加填报机构名称、金融机构编码、社会信用代码  add by weiyuqiang 2019-2-20 start*/
delete from sys_para where system_code = 'SVR' and para_code = 'SVR_SOCIAL_CREDIT_CODE';
INSERT INTO SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME)
values ('SVR','SVR_SOCIAL_CREDIT_CODE','HSBANK_LCJLB01','01','','社会信用代码','system',null,to_timestamp('12-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('12-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
/*系统参数添加填报机构名称、金融机构编码、社会信用代码  add by weiyuqiang 2019-2-20 end*/



/*人行报表资产头寸批处理 add by weiyuqiang 2019-03-12 start*/
delete from SYS_BATCH_TASK where TASK_ID IN('3100','3101');
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('3100','SVR','人行产品头寸批处理',null,'pbcMonthProdPositionCBService',3020,'system',to_timestamp('14-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('14-3月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
INSERT INTO SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
VALUES ('3101','SVR','人行资产头寸批处理',NULL,'pbcMonthAssetPositionCBService',3021,'system',TO_TIMESTAMP('27-2月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',TO_TIMESTAMP('27-2月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
/*人行报表资产头寸批处理 add by weiyuqiang 2019-03-12 end*/

/*人行报表设置报送时点 add by weiyuqiang 2019-3-22 start*/
DELETE FROM SYS_PARA  WHERE SYSTEM_CODE = 'SVR' AND PARA_CODE = 'SVR_SUBMIT_TIME_PBC';
INSERT INTO SYS_PARA (SYSTEM_CODE,PARA_CODE,PARAM_VALUE,DATA_TYPE,DATA_SCOPE,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) VALUES ('SVR','SVR_SUBMIT_TIME_PBC','16:00:00','0 ',' ','人行报送时点时间配置','system',NULL,TO_DATE('2019-03-22','YYYY-MM-DD'),'system',TO_DATE('2019-03-22','YYYY-MM-DD'));
/*人行报表设置报送时点 add by weiyuqiang 2019-3-22 start*/

/**放到最后，不要删除***/
commit;