DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='TrdBondInvestTrade002' AND ELEMENT_ID = 'chlId';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdBondInvestTrade002','chlId','chlFinprodId','合作资产','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='TrdBondInvestTrade002' AND ELEMENT_ID = 'bussTrdAvailableCashQuery_channelAvailableCash';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdBondInvestTrade002','bussTrdAvailableCashQuery_channelAvailableCash','channelAvailableCash','合作资产可用现金','02','02','02',null);