--会计场景代码：新增数据项【8017、8018】
delete from SYS_DATADICT_LIST where DICT_CODE='TABLE_ID' and ITEM_CODE in ('8017','8018');
insert into SYS_DATADICT_LIST values ('TABLE_ID','8017','资金收款-净值型产品认申购','573','0','system','',to_date('2020/12/23', 'yyyy/mm/dd'), 'system',to_date('2020/12/23', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('TABLE_ID','8018','资金收款-货币型产品申购','574','0','system','',to_date('2020/12/23', 'yyyy/mm/dd'), 'system',to_date('2020/12/23', 'yyyy/mm/dd'));

--指令类型：新增数据项【8017、8018】
delete from SYS_DATADICT_LIST where DICT_CODE='ORDER_TYPE' and ITEM_CODE in ('8017','8018');
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','8017','资金收款-净值型产品认申购','192','0','system','',to_date('2020/12/23', 'yyyy/mm/dd'), 'system',to_date('2020/12/23', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ORDER_TYPE','8018','资金收款-货币型产品申购','193','0','system','',to_date('2020/12/23', 'yyyy/mm/dd'), 'system',to_date('2020/12/23', 'yyyy/mm/dd'));
