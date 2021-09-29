delete from sys_element_control where page_id='FinDepositTarget002' and ELEMENT_ID='depositPostOrg';
insert into sys_element_control (PAGE_ID, ELEMENT_ID, ELEMENT_FIELD, ELEMENT_LABLE, IS_SHOW, IS_REQUIRED, IS_HOLD, REQUIRED_CONTROL)
values ('FinDepositTarget002', 'depositPostOrg', 'postOrg', '投后责任机构', '02', '02', '02', null);
delete from sys_element_control where page_id='RskSupervisionIndicatorParam003' and ELEMENT_ID='depositInstitutionCode';
insert into sys_element_control (PAGE_ID, ELEMENT_ID, ELEMENT_FIELD, ELEMENT_LABLE, IS_SHOW, IS_REQUIRED, IS_HOLD, REQUIRED_CONTROL)
values ('RskSupervisionIndicatorParam003', 'depositInstitutionCode', 'institutionId', '机构名称', '02', '02', '02', null);
delete from sys_element_control where page_id='FinPlanAssetC002' and ELEMENT_ID='bankTrustCodeDiv';
insert into sys_element_control (PAGE_ID, ELEMENT_ID, ELEMENT_FIELD, ELEMENT_LABLE, IS_SHOW, IS_REQUIRED, IS_HOLD, REQUIRED_CONTROL)
values ('FinPlanAssetC002', 'bankTrustCodeDiv', 'bankTrustCode', '人行信托代码', '02', '02', '02', null);
delete from sys_element_control where page_id='FinPlanProfitAsset002' and ELEMENT_ID='bankTrustCodeDiv';
insert into sys_element_control (PAGE_ID, ELEMENT_ID, ELEMENT_FIELD, ELEMENT_LABLE, IS_SHOW, IS_REQUIRED, IS_HOLD, REQUIRED_CONTROL)
values ('FinPlanProfitAsset002', 'bankTrustCodeDiv', 'bankTrustCode', '人行信托代码', '02', '02', '02', null);

update sys_para set param_value='02' where para_code='IS_QUERY_ASSETS_LOSS';