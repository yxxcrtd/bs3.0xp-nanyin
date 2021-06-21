update chb_datacheck_rule_inv set status='E' where checkruleid='884827E5-DF35-D920-E050-007F110077FK' and page_id='FinProjectTargetAsset002';

delete from chb_datacheck_condition_inv where CHECKRULEID='884827E5-DF35-D920-E050-007F110077FK';
insert into chb_datacheck_condition_inv (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('LZ4827E5-DF35-D920-E123-007F01000205', '884827E5-DF35-D920-E050-007F110077FK', 'inArray', 'repAssetType', '==', '1399,1299,2299,1301,1201,1210,2201,2202,1202,1203,1204,1205,1206,1207,1208,1209,2617', null, 'E');
