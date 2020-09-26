-------一级债新建，交易场所去掉 otc场外-------
DELETE SYS_PAGE_DICTLIST WHERE page_id='FinTenderBondTargetAsset002' AND DICT_ID = 'TRADE_PLACE' AND DICT_ITEM_VAL IN ('01','03','04','05','10');
insert into SYS_PAGE_DICTLIST values ('FinTenderBondTargetAsset002','999999','TRADE_PLACE','01','','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinTenderBondTargetAsset002','999999','TRADE_PLACE','03','','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinTenderBondTargetAsset002','999999','TRADE_PLACE','04','','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinTenderBondTargetAsset002','999999','TRADE_PLACE','05','','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
insert into SYS_PAGE_DICTLIST values ('FinTenderBondTargetAsset002','999999','TRADE_PLACE','10','','','admin','',to_date('2020/3/23', 'yyyy/mm/dd'), 'admin',to_date('2020/3/23', 'yyyy/mm/dd'));
COMMIT;
