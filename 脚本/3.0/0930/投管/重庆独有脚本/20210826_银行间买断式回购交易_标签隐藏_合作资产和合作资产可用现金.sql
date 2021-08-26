DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='TrdOutRightRepoTrade001' AND ELEMENT_ID = 'chlFinprodId';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdOutRightRepoTrade001','chlFinprodId','chlFinprodId','合作资产','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='TrdOutRightRepoTrade002' AND ELEMENT_ID = 'chlId';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdOutRightRepoTrade002','chlId','chlFinprodId','合作资产','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='TrdOutRightRepoTrade002' AND ELEMENT_ID = 'bussTrdOutRightRepoTrade_channelAvailableCash';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdOutRightRepoTrade002','bussTrdOutRightRepoTrade_channelAvailableCash','channelAvailableCash','合作资产可用现金','02','02','02',null);