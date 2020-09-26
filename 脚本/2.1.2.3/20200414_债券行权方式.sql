delete from  sys_datadict_list where dict_code ='EXEC_MODE' and item_value in ('欧式','美式');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_MODE', '06', '欧式', 1, '0', 'system', null, '30-12月-19 12.00.00.000000 上午', 'system', '30-12月-19 12.00.00.000000 上午');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('EXEC_MODE', '07', '美式', 2, '0', 'system', null, '30-12月-19 12.00.00.000000 上午', 'system', '30-12月-19 12.00.00.000000 上午');
DELETE
  FROM SYS_PAGE_DICTLIST
 WHERE PAGE_ID = 'FinBondTargetAsset002'
   AND DICT_ID = 'EXEC_MODE'
   AND DICT_ITEM_VAL IN ('01',  '02','03','04','05');
insert into SYS_PAGE_DICTLIST values ('FinBondTargetAsset002','BusiFinExercisePlan','EXEC_MODE','01','赎回','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinBondTargetAsset002','BusiFinExercisePlan','EXEC_MODE','02','回售','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinBondTargetAsset002','BusiFinExercisePlan','EXEC_MODE','03','发行人上调票面利率选择权','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinBondTargetAsset002','BusiFinExercisePlan','EXEC_MODE','04','发行人/投资人递延兑付选择权','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinBondTargetAsset002','BusiFinExercisePlan','EXEC_MODE','05','其他选择权','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
commit;