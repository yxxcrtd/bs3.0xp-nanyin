/*20200402_债券标的利率类型只显示固定利率和浮动利率*/
DELETE
  FROM SYS_PAGE_DICTLIST
 WHERE PAGE_ID = 'FinBondTargetAsset001'
   AND DICT_ID = 'INT_TYPE'
   AND DICT_ITEM_VAL IN ('01',  '02');

insert into SYS_PAGE_DICTLIST values ('FinBondTargetAsset001','999999','INT_TYPE','01','','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinBondTargetAsset001','999999','INT_TYPE','02','','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
