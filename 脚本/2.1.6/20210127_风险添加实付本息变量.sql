--添加实付本息变量
delete from SYS_DATADICT_LIST where DICT_CODE = 'EVENT_REL_ITEM' and ITEM_CODE = 'tradeAmtReal';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('EVENT_REL_ITEM', 'tradeAmtReal', '实付本息', 9, '0', 'system', null, TIMESTAMP '2019-08-14 00:00:00', 'system', TIMESTAMP '2019-08-14 00:00:00', null);
--现金持仓规模 风险事项指向 tradeAmtReal
update rsk_event_para_rel set rel_item = 'tradeAmtReal' where para_code = 'XJGM';