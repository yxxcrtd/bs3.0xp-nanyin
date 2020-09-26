delete from CHB_MGRTABLE_INFO where MGRTABLE_ID = 'SALE_CUST_SVRREPORT' and MGRTABLE_NAME = 'SALE_CUST_SVRREPORT';
Insert into CHB_MGRTABLE_INFO (MGRTABLE_ID,MGRTABLE_NAME,MGRTABLE_CHI_NAME,DATAOBJECT,STATUS) values ('SALE_CUST_SVRREPORT','SALE_CUST_SVRREPORT','监管客户组别区域报送数据','SALE_CUST_SVRREPORT','E');


delete from CHB_INFOMGR_CONFIG where BASEINFORMATIONID in ('06552a39-70ac-43e4-998c-f941d04983de', '9d21cd88-9e4b-44b9-b70d-fb86b757b99a');
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID, BASETABLEID, BASECOLUMNID, BASECOLUMNNAME, BASECOLUMNTYPE, BUSINESSPKFLAG, DICTCODE)
values ('06552a39-70ac-43e4-998c-f941d04983de', 'SALE_CUST_SVRREPORT', 'SENDDATE', '下发日期', 'D', 'Y', '');
insert into CHB_INFOMGR_CONFIG (BASEINFORMATIONID, BASETABLEID, BASECOLUMNID, BASECOLUMNNAME, BASECOLUMNTYPE, BUSINESSPKFLAG, DICTCODE)
values ('9d21cd88-9e4b-44b9-b70d-fb86b757b99a', 'SALE_CUST_SVRREPORT', 'FINPROD_ID', '产品代码', 'T', 'Y', '');

delete from CHB_INFOMGR_RESULT_CONFIG WHERE MGRTABLE_ID='SALE_CUST_SVRREPORT';
insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('a8412617-2075-4e1a-9cb2-2aa1284bc9f1', 'SALE_CUST_SVRREPORT', 'SENDDATE', '下发日期', '', 1, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1bd7829f-d3f9-4822-bb19-a04c137f789d', 'SALE_CUST_SVRREPORT', 'FINPROD_ID', '产品代码', '', 2, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('6db04271-89de-4c79-bcda-1cb3a4635559', 'SALE_CUST_SVRREPORT', 'ISSUEMEMBER', '法人行', '', 3, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('74939069-6e82-4d02-bf91-960ccec709c8', 'SALE_CUST_SVRREPORT', 'INORABOARD', '境内境外', '', 4, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('2531c1b4-f206-4182-ab0b-b8140e874ee6', 'SALE_CUST_SVRREPORT', 'CUS_CODE', '客户组别代码', '', 5, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('2487ee44-8b5f-4fbd-969b-2b705e456ec1', 'SALE_CUST_SVRREPORT', 'AREA_CODE', '行政区域代码', '', 6, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('1b74b16c-34c2-4334-a4ea-110f97d66e14', 'SALE_CUST_SVRREPORT', 'CCY', '币种', '', 7, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('b8d15b77-21ca-47fc-8b8d-1ab68b2de1a7', 'SALE_CUST_SVRREPORT', 'PRINAMT', '认购金额', '', 8, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f9d396ff-ecdf-4576-8061-4c6ce20d490e', 'SALE_CUST_SVRREPORT', 'PRINAMT_RMB', '认购金额转人民币', '', 9, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('8ce69c86-fe12-4c98-b353-86038905795e', 'SALE_CUST_SVRREPORT', 'PRINAMT_CONFIRM', '认购确认份额', '', 10, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('f4b4af01-c4ea-4643-9329-a40fdbeb703d', 'SALE_CUST_SVRREPORT', 'SG_AMT', '申购金额', '', 11, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c5a5e36', 'SALE_CUST_SVRREPORT', 'SG_AMT_RMB', '申购金额转人民币', '', 12, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500001', 'SALE_CUST_SVRREPORT', 'SG_CONFIRM_AMT', '申购确认份额', '', 13, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500002', 'SALE_CUST_SVRREPORT', 'SH_AMT', '赎回兑付金额', '', 14, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500003', 'SALE_CUST_SVRREPORT', 'SH_AMT_RMB', '赎回兑付金额转人民币', '', 15, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500004', 'SALE_CUST_SVRREPORT', 'SH_PRINCIPAL', '赎回兑付本金', '', 16, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500005', 'SALE_CUST_SVRREPORT', 'SH_PRINCIPAL_RMB', '赎回兑付本金转人民币', '', 17, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500006', 'SALE_CUST_SVRREPORT', 'SH_QTY', '赎回兑付份额', '', 18, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500007', 'SALE_CUST_SVRREPORT', 'EXPIRE_AMT', '到期兑付金额', '', 19, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500008', 'SALE_CUST_SVRREPORT', 'EXPIRE_AMT_RMB', '到期兑付金额转人民币', '', 20, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500009', 'SALE_CUST_SVRREPORT', 'EXPIRE_AMT_PRINCIPAL', '到期兑付本金', '', 21, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500010', 'SALE_CUST_SVRREPORT', 'EXPIRE_AMT_PRINCIPAL_RMB', '到期兑付本金转人民币', '', 22, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500011', 'SALE_CUST_SVRREPORT', 'EXPIRE_AMT_QTY', '到期兑付份额', '', 23, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500012', 'SALE_CUST_SVRREPORT', 'REBONUS_AMT', '红利再投资金额', '', 24, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500013', 'SALE_CUST_SVRREPORT', 'REBONUS_AMT_RMB', '红利再投资金额转人民币', '', 25, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500014', 'SALE_CUST_SVRREPORT', 'REBONUS_AMT_QTY', '红利再投资份额', '', 26, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500015', 'SALE_CUST_SVRREPORT', 'BONUS_AMT', '现金分红金额', '', 27, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500016', 'SALE_CUST_SVRREPORT', 'BONUS_AMT_RMB', '现金分红金额转人民币', '', 28, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500017', 'SALE_CUST_SVRREPORT', 'QTY_ADD_AMT', '份额强增金额', '', 29, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500018', 'SALE_CUST_SVRREPORT', 'QTY_ADD_AMT_RMB', '份额强增金额转人民币', '', 30, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500019', 'SALE_CUST_SVRREPORT', 'QTY_ADD', '强增份额', '', 31, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500020', 'SALE_CUST_SVRREPORT', 'QTY_MINUS_PRINCIPAL', '强减本金', '', 32, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500021', 'SALE_CUST_SVRREPORT', 'QTY_MINUS_PRINCIPAL_RMB', '强减本金转人民币', '', 33, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500022', 'SALE_CUST_SVRREPORT', 'QTY_MINUS', '强减份额', '', 34, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500023', 'SALE_CUST_SVRREPORT', 'SAVE_AMT', '保有量金额', '', 35, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500024', 'SALE_CUST_SVRREPORT', 'SAVE_CUS_NUM', '保有量客户数', '', 36, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500025', 'SALE_CUST_SVRREPORT', 'SAVE_AMT_RMB', '保有量金额转人民币', '', 37, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500026', 'SALE_CUST_SVRREPORT', 'SAVE_QTY', '保有量份额', '', 38, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500027', 'SALE_CUST_SVRREPORT', 'EXCHANGE_RATE', '汇率', '', 39, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500028', 'SALE_CUST_SVRREPORT', 'CUS_TYPE', '客户类型', '', 40, 'Y');

insert into chb_infomgr_result_config (RESULT_ID, MGRTABLE_ID, COLUMN_ID, COLUMN_NAME, DICTCODE, ORDER_NO, SHOWFLAG)
values ('176cdbf0-20db-43d1-b9f9-60ac5c500029', 'SALE_CUST_SVRREPORT', 'UPDATETIME', '更新时间', '', 41, 'Y');


update chb_interf_config set content_src='T' where interf_id='CHB_PRD_DEAL';