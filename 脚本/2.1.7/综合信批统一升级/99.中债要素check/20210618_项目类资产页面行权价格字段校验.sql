delete from chb_datacheck_condition_inv where CHECKRULEID='LZ482123-DF35-D920-E050-321F01000788';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('LZ482123-DF35-D920-E050-321F01000788', 'FinProjectTargetAsset002', 'isNumber2', 'bussRepAssetCreditNonstdInfoDto.repExercisePrice', '>=', '17,4', 'Y', '行权价格必须为n(17,4)格式。', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

delete from chb_datacheck_condition_inv where CHECKRULEID='LZ482123-DF35-D920-E050-321F01000788';
insert into chb_datacheck_condition_inv (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('LZ482123-DF35-D920-E050-321F01000789', 'LZ482123-DF35-D920-E050-321F01000788', 'inArray', 'repAssetType', '==', '1202,1203,1204,1205,1206,1207,1208,1209', null, 'E');
