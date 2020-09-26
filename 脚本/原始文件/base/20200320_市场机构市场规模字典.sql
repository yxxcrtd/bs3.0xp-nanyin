delete from SYS_DATADICT where dict_code = 'MARKET_INSTI_TYPE_SCA';
insert into SYS_DATADICT values ('MARKET_INSTI_TYPE_SCA','VAL','市场机构机构类型（规模）','','02','system','',to_date('2020/03/19', 'yyyy/mm/dd'), 'system',to_date('2020/03/19', 'yyyy/mm/dd'));
delete from SYS_DATADICT_LIST where dict_code = 'MARKET_INSTI_TYPE_SCA';
insert into SYS_DATADICT_LIST values ('MARKET_INSTI_TYPE_SCA','01','中型企业','1','0','system','',to_date('2020/03/19', 'yyyy/mm/dd'), 'system',to_date('2020/03/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MARKET_INSTI_TYPE_SCA','02','小型企业','2','0','system','',to_date('2020/03/19', 'yyyy/mm/dd'), 'system',to_date('2020/03/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MARKET_INSTI_TYPE_SCA','03','微型企业','3','0','system','',to_date('2020/03/19', 'yyyy/mm/dd'), 'system',to_date('2020/03/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MARKET_INSTI_TYPE_SCA','04','大型企业','4','0','system','',to_date('2020/03/19', 'yyyy/mm/dd'), 'system',to_date('2020/03/19', 'yyyy/mm/dd'));
insert into SYS_DATADICT_LIST values ('MARKET_INSTI_TYPE_SCA','05','其他','5','0','system','',to_date('2020/03/19', 'yyyy/mm/dd'), 'system',to_date('2020/03/19', 'yyyy/mm/dd'));
