--计划类净值型终止日期非空校验 
delete from CHB_DATACHECK_RULE_INV where CHECKRULEID in ('ys214A49-6813-4C79-46B8-007F22700005');

Insert into CHB_DATACHECK_RULE_INV (CHECKRULEID,PAGE_ID,CHECKMETHOD,SRCPROPERTY,COMPARETO,OBJVALUE,NULLABLE,REMARK,STATUS,CHECKCLASS) values ('ys214A49-6813-4C79-46B8-007F22700005','FinPlanNetAsset002','isNotEmpty','mdate',null,null,'N','请输入终止日期。','E','com.joyintech.tams.invest.common.ChbBaseCheckLib');
