--添加数据字典:指令创建来源
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE = 'ENTRUST_CREATE_SOURCE';
insert into SYS_DATADICT_LIST values ('ENTRUST_CREATE_SOURCE','01','场内簿记','1','0','system','',to_date('2021/9/18', 'yyyy/mm/dd'), 'system',to_date('2021/9/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ENTRUST_CREATE_SOURCE','02','场内实时','2','0','system','',to_date('2021/9/18', 'yyyy/mm/dd'), 'system',to_date('2021/9/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ENTRUST_CREATE_SOURCE','03','银行间簿记','3','0','system','',to_date('2021/9/18', 'yyyy/mm/dd'), 'system',to_date('2021/9/18', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('ENTRUST_CREATE_SOURCE','04','银行间实时','4','0','system','',to_date('2021/9/18', 'yyyy/mm/dd'), 'system',to_date('2021/9/18', 'yyyy/mm/dd'));
