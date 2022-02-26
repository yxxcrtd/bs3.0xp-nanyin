-- 修改查询 操作记录表头： 业务编码 --> 指令代码
UPDATE CFG_DATATABLE_SHOW SET COLNAME = '指令代码' WHERE PAGECODE = 'CfetsEtrInfoQuery' AND DTCODE = 'CfetsEtrInfoOperateList' AND SPCDCODE IN ( '999999' ) AND EFFERANGE = 0 AND RANGEID = 'COMM' AND STATUS = 1 AND COLCODE = 'entrustId';

-- 修改 指令查询添加币种列 
Insert into CFG_DATATABLE_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,DISPLAYED,FROZEN,SORT,MAXLEN,EXTENDCFG,EFFERANGE,RANGEID,STATUS,REMARK1,REMARK2,REMARK3,ISKEY) VALUES ('FieldEtrInfoQuery','FieldEtrInfoQueryList','999999','ccyStr','币种','ccy','24','1','0','1','100','{"colAlign": "left","supportSearch":"模糊"}','0','COMM','1','0',NULL,NULL,'0');

-- 指令查询详情 添加 资金账户
INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE, DTCODE, SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, EXTENDCFG, STATUS, REMARK1, REMARK2, REMARK3) VALUES ('FieldEtrDispatcher', 'FieldEtrDispatcherInfo', 'EX004', 'secAcctId', '资金账户', NULL, '18', '{"colAlign": "left","dataType":"amount","precision":"4"}', '1', '0', NULL, NULL);

-- 指令查询 删除以下字段
-- 1债转股
INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE, DTCODE, SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, EXTENDCFG, STATUS, REMARK1, REMARK2, REMARK3) VALUES ('FieldEtrDispatcher', 'FieldEtrDispatcherInfo', 'EX004', 'expConValue', '转股价值', NULL, '16', '{"colAlign": "left","dataType":"amount","precision":"4"}', '1', '0', NULL, NULL);
delete from CFG_READONLY_FORM_SHOW where PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'EX004'  and COLCODE = 'expConValue';
-- 2配债
INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) VALUES ('FieldEtrDispatcher', 'FieldEtrDispatcherInfo', 'EX006', 'expConValue', '转股价值', NULL, '17', '{"colAlign": "left","dataType":"amount","precision":"4"}', '1', '0', NULL, NULL);
INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) VALUES ('FieldEtrDispatcher', 'FieldEtrDispatcherInfo', 'EX006', 'preb', '转股溢价率', NULL, '19', '{"colAlign": "left","dataType":"amount","precision":"4"}', '1', '0', NULL, NULL);
INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) VALUES ('FieldEtrDispatcher', 'FieldEtrDispatcherInfo', 'EX006', 'prec', '纯债溢价率', NULL, '20', '{"colAlign": "left","dataType":"amount","precision":"4"}', '1', '0', NULL, NULL);
delete from CFG_READONLY_FORM_SHOW where PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'EX006'  and COLCODE = 'expConValue';
delete from CFG_READONLY_FORM_SHOW where PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'EX006'  and COLCODE = 'preb';
delete from CFG_READONLY_FORM_SHOW where PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'EX006'  and COLCODE = 'prec';
-- 3可转债申购
INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) VALUES ('FieldEtrDispatcher', 'FieldEtrDispatcherInfo', 'EX007', 'expConValue', '转股价值', NULL, '17', '{"colAlign": "left","dataType":"amount","precision":"4"}', '1', '0', NULL, NULL);
INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) VALUES ('FieldEtrDispatcher', 'FieldEtrDispatcherInfo', 'EX007', 'preb', '转股溢价率', NULL, '19', '{"colAlign": "left","dataType":"amount","precision":"4"}', '1', '0', NULL, NULL);
INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE,DTCODE,SPCDCODE,COLCODE,COLNAME,DICTCODE,SEQNO,EXTENDCFG,STATUS,REMARK1,REMARK2,REMARK3) VALUES ('FieldEtrDispatcher', 'FieldEtrDispatcherInfo', 'EX007', 'prec', '纯债溢价率', NULL, '20', '{"colAlign": "left","dataType":"amount","precision":"4"}', '1', '0', NULL, NULL);
delete from CFG_READONLY_FORM_SHOW where PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'EX007' and COLCODE = 'expConValue';
delete from CFG_READONLY_FORM_SHOW where PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'EX007' and COLCODE = 'preb';
delete from CFG_READONLY_FORM_SHOW where PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'EX007' and COLCODE = 'prec';


-- 指令查询 大宗交易 -> 修改详情显示
-- 修改投资目的 busiModel -> otherBusiModel
UPDATE CFG_READONLY_FORM_SHOW SET COLCODE = 'otherBusiModel' WHERE PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'F01EX002' AND COLCODE = 'busiModel' AND COLNAME = '投资目的';
-- 修改对方席位 对方席位 -> 对手席位
UPDATE CFG_READONLY_FORM_SHOW SET COLNAME = '对手席位' WHERE PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'F01EX002' AND COLCODE = 'counterSeat' AND COLNAME = '对方席位' ;
-- 修改指令金额、指令数量 小数位两位
UPDATE CFG_READONLY_FORM_SHOW SET EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"2"}' WHERE PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'F01EX002' AND COLCODE = 'entrustNum' AND COLNAME = '指令数量' ;
UPDATE CFG_READONLY_FORM_SHOW SET EXTENDCFG = '{"colAlign": "left","dataType":"amount","precision":"2"}' WHERE PAGECODE = 'FieldEtrDispatcher' AND DTCODE = 'FieldEtrDispatcherInfo' AND SPCDCODE = 'F01EX002' AND COLCODE = 'entrustAmt' AND COLNAME = '指令金额' ;
