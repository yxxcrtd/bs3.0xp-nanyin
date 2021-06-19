delete from CHB_DATACHECK_RULE_INV where CHECKRULEID='LZ4827E5-DF35-D920-E050-007F01000196' and PAGE_ID='FinProjectTargetAsset002';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('LZ4827E5-DF35-D920-E050-007F01000196', 'FinProjectTargetAsset002', 'compareValueAsNumber', 'bussRepAssetCreditNonstdInfoDto.repExcessProfitRate', '>=', '0', 'Y', '超额收益分配比例必须大于等于0。', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

delete from CHB_DATACHECK_RULE_INV where CHECKRULEID='LZ4827E5-DF35-D920-E050-007F01000204' and PAGE_ID='FinProjectTargetAsset002';
insert into CHB_DATACHECK_RULE_INV (CHECKRULEID, PAGE_ID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, NULLABLE, REMARK, STATUS, CHECKCLASS)
values ('LZ4827E5-DF35-D920-E050-007F01000204', 'FinProjectTargetAsset002', 'isNumber2', 'bussRepAssetCreditNonstdInfoDto.repExcessProfitRate', '>=', '8,5', 'Y', '超额收益分配比例%以百分数形式表示时必须为n(8,5)格式。', 'E', 'com.joyintech.tams.invest.common.ChbBaseCheckLib');

delete from chb_datacheck_condition_inv where CHECKRULEID='LZ4827E5-DF35-D920-E050-007F01000196';
insert into chb_datacheck_condition_inv (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('LZ4827E5-DF35-D920-E050-006F01000110', 'LZ4827E5-DF35-D920-E050-007F01000196', 'inArray', 'repAssetType', '==', '1202,1203,1204,1205,1206,1207,1208,1209', null, 'E');

delete from chb_datacheck_condition_inv where CHECKRULEID='LZ4827E5-DF35-D920-E050-007F01000204';
insert into chb_datacheck_condition_inv (CHECKCONDITIONID, CHECKRULEID, CHECKMETHOD, SRCPROPERTY, COMPARETO, OBJVALUE, REMARK, STATUS)
values ('LZ4827E5-DF35-D920-E050-006F01000103', 'LZ4827E5-DF35-D920-E050-007F01000204', 'inArray', 'repAssetType', '==', '1202,1203,1204,1205,1206,1207,1208,1209', null, 'E');
