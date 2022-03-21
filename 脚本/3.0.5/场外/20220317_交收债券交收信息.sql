-- 删除 清算速度\全价\本方券券面总额\融入券券面总额
delete from CFG_READONLY_FORM_SHOW where PAGECODE='CfetsEtrDispatcher' and DTCODE='CfetsEtrDispatcherInfo' and SPCDCODE='03' and COLCODE='otherSettleSpeed';
delete from CFG_READONLY_FORM_SHOW where PAGECODE='CfetsEtrDispatcher' and DTCODE='CfetsEtrDispatcherInfo' and SPCDCODE='03' and COLCODE='allPrice';
delete from CFG_READONLY_FORM_SHOW where PAGECODE='CfetsEtrDispatcher' and DTCODE='CfetsEtrDispatcherInfo' and SPCDCODE='03,02' and COLCODE='entrustOwnNum';
delete from CFG_READONLY_FORM_SHOW where PAGECODE='CfetsEtrDispatcher' and DTCODE='CfetsEtrDispatcherInfo' and SPCDCODE='03,02' and COLCODE='integrateSecAmount';
update CFG_READONLY_FORM_SHOW set COLNAME='行权净价' where PAGECODE='CfetsEtrDispatcher' and DTCODE='CfetsEtrDispatcherInfo' and SPCDCODE='03' and COLCODE='unitPrice';

-- 删除 清算速度\全价\本方券券面总额\融入券券面总额
delete from CFG_READONLY_FORM_SHOW where PAGECODE='IbuQuoteBond' and DTCODE='ETRDETINFO' and SPCDCODE='02' and COLCODE='settleSpreedStr';
delete from CFG_READONLY_FORM_SHOW where PAGECODE='IbuQuoteBond' and DTCODE='ETRDETINFO' and SPCDCODE='02' and COLCODE='unitFprice';
delete from CFG_READONLY_FORM_SHOW where PAGECODE='IbuQuoteBond' and DTCODE='ETRDETINFO' and SPCDCODE='02' and COLCODE='ourEntNum';
delete from CFG_READONLY_FORM_SHOW where PAGECODE='IbuQuoteBond' and DTCODE='ETRDETINFO' and SPCDCODE='02' and COLCODE='lendinEntNum';
update CFG_READONLY_FORM_SHOW set COLNAME='行权净价' where PAGECODE='IbuQuoteBond' and DTCODE='ETRDETINFO' and SPCDCODE='02' and COLCODE='price';

-- 回退脚本
-- INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE, DTCODE, SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, EXTENDCFG, STATUS, REMARK1, REMARK2, REMARK3) VALUES ('CfetsEtrDispatcher', 'CfetsEtrDispatcherInfo', '03', 'allPrice', '全价', null, 17, '{"colAlign": "left","dataType":"amount","precision":"4","unit":"yuan"}', 1, 0, null, null);
-- INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE, DTCODE, SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, EXTENDCFG, STATUS, REMARK1, REMARK2, REMARK3) VALUES ('CfetsEtrDispatcher', 'CfetsEtrDispatcherInfo', '03', 'otherSettleSpeed', '清算速度', null, 9, '{"colAlign": "left"}', 1, 0, null, null);
-- INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE, DTCODE, SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, EXTENDCFG, STATUS, REMARK1, REMARK2, REMARK3) VALUES ('CfetsEtrDispatcher', 'CfetsEtrDispatcherInfo', '03,02', 'entrustOwnNum', '本方券券面总额', null, 14, '{"colAlign": "left","dataType":"amount","precision":"2","unit":"wanYuan"}', 1, 0, null, null);
-- INSERT INTO CFG_READONLY_FORM_SHOW (PAGECODE, DTCODE, SPCDCODE, COLCODE, COLNAME, DICTCODE, SEQNO, EXTENDCFG, STATUS, REMARK1, REMARK2, REMARK3) VALUES ('CfetsEtrDispatcher', 'CfetsEtrDispatcherInfo', '03,02', 'integrateSecAmount', '融入券券面总额', null, 15, '{"colAlign": "left","dataType":"amount","precision":"2","unit":"wanYuan"}', 1, 0, null, null);
-- update CFG_READONLY_FORM_SHOW set COLNAME='净价' where PAGECODE='CfetsEtrDispatcher' and DTCODE='CfetsEtrDispatcherInfo' and SPCDCODE='03' and COLCODE='unitPrice';
