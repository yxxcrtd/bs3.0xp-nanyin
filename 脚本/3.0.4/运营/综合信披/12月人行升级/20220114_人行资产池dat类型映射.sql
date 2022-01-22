--人行资产池dat类型映射
delete from chb_type_mapping_ex where MAPPINGCODE='MAPPINGCODE_PBCASSETDATA' and SRCFIELD1 in('AMT_721100_JNCK','AMT_721200_DQHQTCK','AMT_722100_JNCK','AMT_722200_DQHQTCK','AMT_7K0000_ZCJZZB','AMT_824000_QTZHSY');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), 'MAPPINGCODE_PBCASSETDATA', '人行资产池dat类型映射', 'AMT_721100_JNCK', null, null, null, null, null, 'A2110', null, null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), 'MAPPINGCODE_PBCASSETDATA', '人行资产池dat类型映射', 'AMT_721200_DQHQTCK', null, null, null, null, null, 'A2120', null, null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), 'MAPPINGCODE_PBCASSETDATA', '人行资产池dat类型映射', 'AMT_722100_JNCK', null, null, null, null, null, 'A2210', null, null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), 'MAPPINGCODE_PBCASSETDATA', '人行资产池dat类型映射', 'AMT_722200_DQHQTCK', null, null, null, null, null, 'A2220', null, null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), 'MAPPINGCODE_PBCASSETDATA', '人行资产池dat类型映射', 'AMT_7K0000_ZCJZZB', null, null, null, null, null, 'AK000', null, null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), 'MAPPINGCODE_PBCASSETDATA', '人行资产池dat类型映射', 'AMT_824000_QTZHSY', null, null, null, null, null, 'C4000', null, null, null, 'E', null, null, 'system', sysdate, 'system', sysdate, null, null);
