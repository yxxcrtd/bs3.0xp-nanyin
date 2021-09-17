delete  FROM SYS_PAGE_DICTLIST spd WHERE PAGE_ID IN ('EtrFund002','TrdFundInvestTrade002') AND DICT_ID = 'PS' AND DICT_ITEM_VAL = 'T65';
INSERT INTO SYS_PAGE_DICTLIST VALUES ('EtrFund002','999999','PS','T65','','','admin','',sysdate,'admin',sysdate,'');
INSERT INTO SYS_PAGE_DICTLIST VALUES ('TrdFundInvestTrade002','999999','PS','T65','','','admin','',sysdate,'admin',sysdate,'');
