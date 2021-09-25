DELETE FROM  SYS_ELEMENT_CONTROL WHERE PAGE_ID ='FinPlanProfitAsset002' AND ELEMENT_ID = 'bussFinRepaymentDto_isPayInt_div';
insert into SYS_ELEMENT_CONTROL (PAGE_ID,ELEMENT_ID,ELEMENT_FIELD,ELEMENT_LABLE,IS_SHOW,IS_REQUIRED,IS_HOLD,REQUIRED_CONTROL)
values ('FinPlanProfitAsset002','bussFinRepaymentDto_isPayInt_div','bussFinRepaymentDto_isPayInt_div','是否还本不带息','02','02','02',null);