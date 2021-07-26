DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinPlanProfitAsset002'and ELEMENT_ID = 'planProfitLoadBank';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinPlanProfitAsset002','planProfitLoadBank','loadBank','放款分行','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinPlanProfitAsset002'and ELEMENT_ID = 'planProfitCapiIncomeFeature';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinPlanProfitAsset002','planProfitCapiIncomeFeature','capiIncomeFeature','保本标识','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinPlanProfitAsset002'and ELEMENT_ID = 'planProfitTrustee';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinPlanProfitAsset002','planProfitTrustee','trusteeId','托管人','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinPlanProfitAsset002'and ELEMENT_ID = 'planProfitSettleDays';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinPlanProfitAsset002','planProfitSettleDays','settleDays','清算时效（工作日）','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinPlanProfitAsset002'and ELEMENT_ID = 'planProfitBuyOutRate';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinPlanProfitAsset002','planProfitBuyOutRate','buyOutRate','买断利率','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinPlanProfitAsset002'and ELEMENT_ID = 'planProfitAssetSource';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinPlanProfitAsset002','planProfitAssetSource','assetSource','资产来源','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinPlanProfitAsset002'and ELEMENT_ID = 'planProfitBussFinRecomment';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinPlanProfitAsset002','planProfitBussFinRecomment','bussFinRecommentDto','资产推荐方','02','02','02',null);
