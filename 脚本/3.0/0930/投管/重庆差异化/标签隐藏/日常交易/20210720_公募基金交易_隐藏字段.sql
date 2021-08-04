
DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='TrdFundInvestTrade002';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdFundInvestTrade002','trdFundSecManageAcctId','secManageAcctId','证券托管户','02','02','02',null);
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdFundInvestTrade002','trdFundTradeMarket','tradeMarket','交易场所','02','02','02',null);
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdFundInvestTrade002','trdFundassetRecommandOrg','assetRecommandOrg','资产推荐方','02','02','02',null);
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdFundInvestTrade002','trmFundAbbr_div','trmFundAbbr','交易所基金简称','02','02','02',null);
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('TrdFundInvestTrade002','trmFundType_Div','trmFundType','交易所基金类型','02','02','02',null);
