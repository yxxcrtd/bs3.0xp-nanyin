--新增菜单
delete from sys_page where PAGE_ID ='CfetsEtrInfoQuery';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CfetsEtrInfoQuery', 'ETR', 'home.html#/interbank/instructionsearch?a=a', 'cfets/cfetsetrinfoquery/CfetsEtrInfoQuery', 'SETTLE_MANAGEMENT_001_002_008', '', '', '02', '01', '指令查询', 'system', '', sysdate, 'system', sysdate);

delete from sys_menu where MENU_ID ='SETTLE_MANAGEMENT_001_002_008';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SETTLE_MANAGEMENT_001_002_008', 'ETR', '', 'SETTLE_MANAGEMENT_001_002', 'CfetsEtrInfoQuery', 8, '02', '指令查询', 'system', '', sysdate, 'system', sysdate);

--自动刷新
delete from sys_para t where t.para_code='cfetsEtrInfoQuery_trade_refresh_period';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('STL', 'cfetsEtrInfoQuery_trade_refresh_period', '30000', '01', '', '银行间指令查询自动刷新时间（毫秒）', 'system', '', sysdate, 'system', sysdate, '', '01');


--银行间指令查询配置脚本
delete from cfg_datatable_show where PAGECODE = 'CfetsEtrInfoQuery';
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherEffectFlag', '有效状态', 'effectFlag','1', '1', '1', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherEntrustStatus', '指令状态', 'entrustStatus','2', '1', '1', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'exeProgress', '执行进度%', '','3', '1', '1', '1','100','{"colAlign": "left","supportSearch":"精确"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'dealProgress', '成交进度%', '','4', '1', '1', '1','100','{"colAlign": "left","supportSearch":"精确"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'entrustId', '指令代码', '','5', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','1');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'portfolioId', '组合代码', '','6', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'portfolioName', '组合名称', '','7', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherFinprodType2', '资产类型', 'finprodType2','8', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'finprodMarketId', '资产代码', '','9', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'finprodAbbr', '资产简称', '','10', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherBusiTypePosition', '业务类型', 'busiTypePosition','11', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherPs', '交易方向', 'ps','12', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherTradeMarket', '交易场所', 'tradeMarket','13', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherSettleSpeed', '清算速度', 'settleSpeed','14', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'secAcctId', '证券账户', '','15', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'entrustAmt', '券面总额/指令金额（元）', '','16', '1', '0', '1','100','{"colAlign": "left","dataType":"amount","precision":"2","supportSearch":"精确"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'etrPrice', '指令价格%', '','17', '1', '0', '1','100','{"colAlign": "left","dataType":"amount","precision":"4","supportSearch":"精确"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'termDays', '期限（天）', '','18', '1', '0', '1','100','{"colAlign": "left","supportSearch":"精确"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherCcy', '币种', 'ccy','19', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'otherEntrustDate', '指令日期', 'entrustDate','20', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'transmitTime', '下达时间', 'transmitTime','21', '1', '0', '1','100','{"colAlign": "left","supportSearch":"精确"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'invManagerId', '投资经理', '','22', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoQueryList','999999', 'remark', '备注', '','23', '1', '0', '1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0','','','0');

insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoOperateList','999999', 'seqNo', '序号', '','1', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoOperateList','999999', 'operDate', '操作日期', '','2', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoOperateList','999999', 'operTime', '操作时间', '','3', '1', '0', '0','100','{"colAlign": "left","dataType":"date"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoOperateList','999999', 'entrustId', '指令代码', '','4', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoOperateList','999999', 'operTypeStr', '操作类型', '','5', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoOperateList','999999', 'busiDes', '业务说明', '','6', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');
insert into cfg_datatable_show (PAGECODE, DTCODE,SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3,ISKEY) values('CfetsEtrInfoQuery', 'CfetsEtrInfoOperateList','999999', 'userId', '操作员', '','7', '1', '0', '0','100','{"colAlign": "left"}','0','COMM','1','0','','','0');


--交易方向
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID IN ('PS','BACK_LEND_DIRECTION');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PS', 'T01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PS', 'T02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PS', 'T03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '04', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BACK_LEND_DIRECTION', '06', '', '', 'system', '', sysdate, 'system', sysdate, '');

--资产类型
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID='PINAN_ASSET_TYPE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F07', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F10', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F12', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'PINAN_ASSET_TYPE', 'F16', '', '', 'system', '', sysdate, 'system', sysdate, '');


--业务类型
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID='BUSI_TYPE_POSITION';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '03', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '05', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '10', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '12', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'BUSI_TYPE_POSITION', '17', '', '', 'system', '', sysdate, 'system', sysdate, '');


--清算速度
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID='ACCOUNT_SPEED';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'ACCOUNT_SPEED', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'ACCOUNT_SPEED', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');

--交易场所
delete from sys_page_dictlist where PAGE_ID = 'CfetsEtrInfoQuery' and DICT_ID='TRADE_PLACE';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'TRADE_PLACE', '01', '', '', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('CfetsEtrInfoQuery', '999999', 'TRADE_PLACE', '02', '', '', 'system', '', sysdate, 'system', sysdate, '');