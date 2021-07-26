
DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinProjectTargetAsset002'and ELEMENT_ID = 'projectAssetSource';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinProjectTargetAsset002','projectAssetSource','assetSource','资产来源','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinProjectTargetAsset002'and ELEMENT_ID = 'projectBussFinRecomment';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinProjectTargetAsset002','projectBussFinRecomment','bussFinRecommentDto','资产推荐方','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinProjectTargetAsset002'and ELEMENT_ID = 'projectBuyOutRate';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinProjectTargetAsset002','projectBuyOutRate','buyOutRate','买断利率','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinProjectTargetAsset002'and ELEMENT_ID = 'projectCapiIncomeFeature';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinProjectTargetAsset002','projectCapiIncomeFeature','capiIncomeFeature','保本标识','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinProjectTargetAsset002'and ELEMENT_ID = 'projectInvestAdviser';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinProjectTargetAsset002','projectInvestAdviser','investAdviser','投资顾问','02','02','02',null);

DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinProjectTargetAsset002'and ELEMENT_ID = 'projectSettleDays';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
alues ('FinProjectTargetAsset002','projectSettleDays','settleDays','清算时效（工作日）','02','02','02',null);
