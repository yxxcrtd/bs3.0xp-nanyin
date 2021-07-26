DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinDepositTarget002' and ELEMENT_ID = 'depositAssetSource';
insert into EXPORT_TABLE (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinDepositTarget002','depositAssetSource','assetSource','资产来源','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinDepositTarget002' and ELEMENT_ID = 'depositBussFinRecomment';
insert into EXPORT_TABLE (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinDepositTarget002','depositBussFinRecomment','bussFinRecommentDto','资产推荐方','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinDepositTarget002' and ELEMENT_ID = 'depositBuyOutRate';
insert into EXPORT_TABLE (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinDepositTarget002','depositBuyOutRate','buyOutRate','买断利率','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinDepositTarget002' and ELEMENT_ID = 'depositRegulatoryRating';
insert into EXPORT_TABLE (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinDepositTarget002','depositRegulatoryRating','regulatoryRating','监管评级','02','02','02',null);
