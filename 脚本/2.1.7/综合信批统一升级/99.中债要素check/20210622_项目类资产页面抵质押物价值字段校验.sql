update chb_datacheck_rule_inv set status='E' where checkruleid='LZ4827E5-DF35-D920-E050-007F01000582' and page_id='FinProjectTargetAsset002';
update chb_datacheck_condition_inv set status='E' where checkruleid='LZ4827E5-DF35-D920-E050-007F01000582';
delete from chb_datacheck_condition_inv where CHECKRULEID='LZ4827E5-DF35-D920-E050-007F01000662';
insert into chb_datacheck_condition_inv (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('LZ4827E5-DF35-D920-E050-006F01000789', 'LZ4827E5-DF35-D920-E050-007F01000662', 'inArray', 'repAssetType', '==', '1202,1203,1204,1205,1206,1207,1208,1209,2201,2202', null, 'E');

insert into chb_datacheck_condition_inv (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('LZ4827E5-DF35-D920-E050-006F01000589', 'LZ4827E5-DF35-D920-E050-007F01000662', 'inArray', 'bussRepAssetCreditNonstdInfoDto.repGuaType', '==', '03,04', null, 'E');
