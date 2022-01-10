--新增菜单   PAGE_URL字段联调时根据前端调整
delete from sys_page where PAGE_ID ='FieldEtrInfoQuery';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FieldEtrInfoQuery', 'ETR', 'home.html#/instructions/instructionsearch?a=a', 'exchange/entrust/fieldetrinfoquerymain/fieldetrinfoquery/FieldEtrInfoQuery', 'SH_DEAL_MANAGEMENT_001_001_099', '', '', '02', '01', '指令查询', 'system', '', sysdate, 'system', sysdate);

delete from sys_menu where MENU_ID ='SETTLE_MANAGEMENT_001_003_010';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SH_DEAL_MANAGEMENT_001_001_099', 'ETR', '', 'SH_DEAL_MANAGEMENT_001_001', 'FieldEtrInfoQuery', 10, '02', '指令查询', 'system', '', sysdate, 'system', sysdate);


--银行间指令查询配置脚本
delete from cfg_datatable_show where PAGECODE = 'FieldEtrInfoQuery';

insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('FieldEtrInfoQuery', 'FieldEtrInfoOperateList','999999', 'seqNo', '序号', '','1', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('FieldEtrInfoQuery', 'FieldEtrInfoOperateList','999999', 'operDate', '操作日期', '','2', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('FieldEtrInfoQuery', 'FieldEtrInfoOperateList','999999', 'operTime', '操作时间', '','3', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('FieldEtrInfoQuery', 'FieldEtrInfoOperateList','999999', 'entrustId', '指令代码', '','4', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('FieldEtrInfoQuery', 'FieldEtrInfoOperateList','999999', 'operTypeStr', '操作类型', '','5', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('FieldEtrInfoQuery', 'FieldEtrInfoOperateList','999999', 'busiDes', '业务说明', '','6', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('FieldEtrInfoQuery', 'FieldEtrInfoOperateList','999999', 'userId', '操作员', '','7', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');


--交易方向
delete from sys_page_dictlist where PAGE_ID = 'FieldEtrInfoQuery' and DICT_ID IN ('PS','BACK_LEND_DIRECTION');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T04', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T49', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T68', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T69', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T97', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PS', 'T46', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '04', '', '', 'system', '', sysdate, 'system', sysdate, '');

--资产类型     
delete from sys_page_dictlist where PAGE_ID = 'FieldEtrInfoQuery' and DICT_ID='PINAN_ASSET_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F07', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('FieldEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F11', '', '', 'system', '', sysdate, 'system', sysdate, '');

DELETE FROM CFG_READONLY_FORM_SHOW where PAGECODE = 'FieldEtrDispatcher' and dtcode = 'FieldEtrDispatcherInfo'; 
--指令信息
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','entrustDate','指令日期',null,1,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','portfolioId','组合代码',null,2,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','portfolioName','组合名称',null,3,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','busiTypePosition','业务类型',null,4,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','finprodMarketId','资产代码',null,5,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','finprodAbbr','资产简称',null,6,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','otherTradeMarket','交易场所',null,7,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','otherPs','委托方向',null,8,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01','assetType','资产类型',null,9,'{"colAlign": "left"}',1,0,null,null);


--债券竞价交易
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','unitPrice','净价（元）',null,10,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','allPrice','全价（元）',null,11,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','matureYield','到期收益率（%）',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','otherCcy','币种',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','busiModel','投资目的',null,16,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','invManagerId','投资经理',null,17,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','targetType','目标类型',null,18,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,02','secType','债券分类',null,19,'{"colAlign": "left"}',1,0,null,null);

--债券大宗交易
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','unitPrice','净价（元）',null,10,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','allPrice','全价（元）',null,11,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','matureYield','到期收益率（%）',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','otherCcy','币种',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','busiModel','投资目的',null,16,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','invManagerId','投资经理',null,17,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','targetType','目标类型',null,18,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','counterId','交易对手',null,19,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','counterSeat','对方席位',null,20,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','counterConNo','约定号',null,21,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,03','secType','债券分类',null,22,'{"colAlign": "left"}',1,0,null,null);

--债券协议现券
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','unitPrice','净价（元）',null,10,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','allPrice','全价（元）',null,11,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','matureYield','到期收益率（%）',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','otherCcy','币种',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','busiModel','投资目的',null,16,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','invManagerId','投资经理',null,17,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','targetType','目标类型',null,18,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','counterId','交易对手',null,19,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','counterSeat','对方席位',null,20,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','counterConNo','约定号',null,21,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','otherSettleSpeed','清算速度',null,22,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','trader','本方交易员',null,23,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','counterTrader','对方交易员',null,24,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,04','secType','债券分类',null,25,'{"colAlign": "left"}',1,0,null,null);

--债券行权
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,05','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,05','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,05','unitPrice','指令价格',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,05','secType','债券分类',null,15,'{"colAlign": "left"}',1,0,null,null);

--债券债转股
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,06','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,06','unitPrice','指令价格',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,06','conId','股票代码',null,14,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,06','conName','股票名称',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,06','expConValue','转股价值',null,16,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,06','expConNum','可转股数量',null,17,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);



--债券配债/可转债申购 一致

Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','unitPrice','指令价格',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','conId','股票代码',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','conName','股票名称',null,16,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','expConValue','转股价值',null,17,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','expConNum','可转股数量',null,18,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','preb','转股溢价率',null,19,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','prec','纯债溢价率',null,20,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,07','secType','债券分类',null,21,'{"colAlign": "left"}',1,0,null,null);



--股票竞价交易
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,08','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,08','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,08','unitPrice','指令价格',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,08','priceModel','价格模式',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,08','targetType','目标类型',null,16,'{"colAlign": "left"}',1,0,null,null);


--股票大宗交易
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,09','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,09','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,09','unitPrice','指令价格',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,09','priceModel','价格模式',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,09','targetType','目标类型',null,16,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,09','counterId','交易对手',null,17,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,09','counterSeat','对方席位',null,18,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,09','counterConNo','约定号',null,19,'{"colAlign": "left"}',1,0,null,null);


--股票配股/新股申购
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,10','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,10','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,10','unitPrice','指令价格',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,10','priceModel','价格模式',null,15,'{"colAlign": "left"}',1,0,null,null);


--基金竞价交易
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,11','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,11','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,11','unitPrice','指令价格',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,11','priceModel','价格模式',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,11','targetType','目标类型',null,16,'{"colAlign": "left"}',1,0,null,null);



--基金大宗交易
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,12','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,12','entrustAmt','指令金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,12','unitPrice','指令价格',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,12','priceModel','价格模式',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,12','targetType','目标类型',null,16,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,12','counterId','交易对手',null,17,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,12','counterSeat','对方席位',null,18,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,12','counterConNo','约定号',null,19,'{"colAlign": "left"}',1,0,null,null);



--质押式回购
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,13','pledgeStandardNumber','标准券数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,13','entrustAmt','回购金额',null,13,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,13','unitPrice','回购利率',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,13','priceModel','价格模式',null,15,'{"colAlign": "left"}',1,0,null,null);

--债券质押券出入库
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,14','entrustNum','指令数量',null,12,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,14','secType','债券分类',null,13,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,14','pledgeRatio','折算率',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','01,14','pledgeStandardNumber','标准券数量',null,15,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);



--协议回购
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','entrustDate','指令日期',null,1,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','portfolioId','组合代码',null,2,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','portfolioName','组合名称',null,3,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','busiTypePosition','业务类型',null,4,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','otherTradeMarket','交易场所',null,5,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','otherPs','委托方向',null,6,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','dealCurAcct','资金账号',null,7,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','entrustAmt','回购金额',null,8,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','requestId','请求编号',null,9,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','counterId','交易对手',null,10,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','trader','本方交易员',null,11,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02','counterTrader','对方交易员',null,12,'{"colAlign": "left"}',1,0,null,null);


--协议回购首期
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','repoTerm','回购期限',null,13,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','unitPrice','回购利率',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','capDays','占款天数',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','vpayDate','首期结算日',null,16,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','mpayDate','到期结算日',null,17,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','mIntAmt','回购利息',null,18,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','mTradeAmt','到期结算金额',null,19,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','finprodMarketId','质押券',null,19,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','pledgeRatio','折算率',null,19,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','pledgeNumber','质押数量',null,19,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,01','pledStrdPrinAmt','券面金额',null,19,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);


--协议回购 提前购回 到期购回 到期续做
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','repoTerm','回购期限',null,13,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','unitPrice','回购利率',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','capDays','占款天数',null,15,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','vpayDate','首期结算日',null,16,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','mpayDate','到期结算日',null,17,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','mIntAmt','回购利息',null,18,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','mTradeAmt','到期结算金额',null,19,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','finprodMarketId','质押券',null,20,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','pledgeRatio','折算率',null,21,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','pledgeNumber','质押数量',null,22,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','pledStrdPrinAmt','券面金额',null,23,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,02','tradeId','初始交易编号',null,24,'{"colAlign": "left"}',1,0,null,null);


--解除质押
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,03','finprodMarketId','质押券',null,13,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,03','pledgeNumber','质押数量',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,03','pledStrdPrinAmt','券面金额',null,15,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,03','tradeId','初始交易编号',null,16,'{"colAlign": "left"}',1,0,null,null);


--中途换券
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','finprodMarketId','新质押券',null,13,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','pledgeRatio','新折算率',null,14,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','pledgeNumber','新质押数量',null,15,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','pledStrdPrinAmt','新券面金额',null,16,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','tradeId','初始交易编号',null,17,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','oldFinprodMarketId','原质押券',null,18,'{"colAlign": "left"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','oldPledgeRatio','原折算率',null,19,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','oldPledgeNumber','原质押数量',null,20,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);
Insert into CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) values ('FieldEtrDispatcher','FieldEtrDispatcherInfo','02,04','oldPledStrdPrinAmt','原券面金额',null,21,'{"colAlign": "left","dataType":"amount","precision":"4"}',1,0,null,null);




