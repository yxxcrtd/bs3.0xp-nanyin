DELETE FROM SYS_ELEMENT_CONTROL WHERE PAGE_ID ='TrdFundInvestTrade002' and ELEMENT_ID = 'trdFundSecManageAcctId';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdFundInvestTrade002','trdFundSecManageAcctId','secManageAcctId','证券账户','01','02','02',null);


