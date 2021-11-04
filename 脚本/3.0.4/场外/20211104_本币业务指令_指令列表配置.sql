DELETE FROM CFG_DATATABLE_SHOW  WHERE PAGECODE ='CFETS_ETR' AND DTCODE ='ETRINFOS' AND EFFERANGE='0';
insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'effectiveStatusName', '有效状态', 'effectiveStatus', 1, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'entrustId', '指令代码', null, 2, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'entrustStatusName', '指令状态', 'entrustStatus', 3, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'execProcess', '执行进度%', null, 4, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"精确"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'transProcess', '成交进度%', null, 5, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"精确"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'invManagerId', '投资经理', null, 6, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'portfolioId', '组合代码', null, 7, 1, 1, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'portfolioName', '组合名称', null, 8, 1, 1, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'finprodMarketId', '资产代码', null, 9, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'finprodAbbr', '资产简称', null, 10, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'secType1Name', '债券一级分类', 'secType1', 11, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'psName', '交易方向', 'ps', 12, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'shareNum', '券面总额（万元）', null, 13, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"精确"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'priceModelName', '价格模式', 'priceModel', 14, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'unitPrice', '净价（元）', null, 15, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"精确"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'allPrice', '全价（元）', null, 16, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"精确"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'matureYield', '到期收益率%', null, 17, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"精确"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'exerYield', '行权收益率%', null, 18, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"精确"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'settleSpreedName', '清算速度', 'settleSpreed', 19, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'etrDate', '指令日期', null, 20, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"精确"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'approver', '审批人', null, 21, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'busiModelStr', '业务模式', 'busiModel', 22, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

insert into cfg_datatable_show (PAGECODE, DTCODE, COLCODE, COLNAME, DICTCODE, SEQNO, DISPLAYED, FROZEN, SORT, MAXLEN, EXTENDCFG, EFFERANGE, RANGEID, STATUS, REMARK1, REMARK2, REMARK3)
values ('CFETS_ETR', 'ETRINFOS', 'remark', '备注', null, 23, 1, 0, 1, 100, '{"colAlign": "left","supportSearch":"模糊"}', 0, 'COMM', 1, 0, null, null);

