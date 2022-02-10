--
delete from chb_type_mapping_ex where MAPPINGCODE ='2_1' and OBJFIELD1 in('721100','721200','722100','722200','7c0000','824000','822000');
insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'cny', null, null, null, null, null, '721100', 'A', null, null, null, null, null, null, null, null, null, null, 'AMT_721100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'usdToCny', null, null, null, null, null, '721100', 'B', null, null, null, null, null, null, null, null, null, null, 'AMT_721100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'eurToCny', null, null, null, null, null, '721100', 'C', null, null, null, null, null, null, null, null, null, null, 'AMT_721100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'gbpToCny', null, null, null, null, null, '721100', 'D', null, null, null, null, null, null, null, null, null, null, 'AMT_721100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'jpyToCny', null, null, null, null, null, '721100', 'E', null, null, null, null, null, null, null, null, null, null, 'AMT_721100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'othToCny', null, null, null, null, null, '721100', 'F', null, null, null, null, null, null, null, null, null, null, 'AMT_721100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'total', null, null, null, null, null, '721100', 'G', null, null, null, null, null, null, null, null, null, null, 'AMT_721100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'cny', null, null, null, null, null, '721200', 'A', null, null, null, null, null, null, null, null, null, null, 'AMT_721200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'usdToCny', null, null, null, null, null, '721200', 'B', null, null, null, null, null, null, null, null, null, null, 'AMT_721200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'eurToCny', null, null, null, null, null, '721200', 'C', null, null, null, null, null, null, null, null, null, null, 'AMT_721200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'gbpToCny', null, null, null, null, null, '721200', 'D', null, null, null, null, null, null, null, null, null, null, 'AMT_721200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'jpyToCny', null, null, null, null, null, '721200', 'E', null, null, null, null, null, null, null, null, null, null, 'AMT_721200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'othToCny', null, null, null, null, null, '721200', 'F', null, null, null, null, null, null, null, null, null, null, 'AMT_721200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'total', null, null, null, null, null, '721200', 'G', null, null, null, null, null, null, null, null, null, null, 'AMT_721200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'cny', null, null, null, null, null, '722100', 'A', null, null, null, null, null, null, null, null, null, null, 'AMT_722100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'usdToCny', null, null, null, null, null, '722100', 'B', null, null, null, null, null, null, null, null, null, null, 'AMT_722100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'eurToCny', null, null, null, null, null, '722100', 'C', null, null, null, null, null, null, null, null, null, null, 'AMT_722100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'gbpToCny', null, null, null, null, null, '722100', 'D', null, null, null, null, null, null, null, null, null, null, 'AMT_722100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'jpyToCny', null, null, null, null, null, '722100', 'E', null, null, null, null, null, null, null, null, null, null, 'AMT_722100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'othToCny', null, null, null, null, null, '722100', 'F', null, null, null, null, null, null, null, null, null, null, 'AMT_722100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'total', null, null, null, null, null, '722100', 'G', null, null, null, null, null, null, null, null, null, null, 'AMT_722100_HQCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'cny', null, null, null, null, null, '722200', 'A', null, null, null, null, null, null, null, null, null, null, 'AMT_722200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'usdToCny', null, null, null, null, null, '722200', 'B', null, null, null, null, null, null, null, null, null, null, 'AMT_722200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'eurToCny', null, null, null, null, null, '722200', 'C', null, null, null, null, null, null, null, null, null, null, 'AMT_722200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'gbpToCny', null, null, null, null, null, '722200', 'D', null, null, null, null, null, null, null, null, null, null, 'AMT_722200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'jpyToCny', null, null, null, null, null, '722200', 'E', null, null, null, null, null, null, null, null, null, null, 'AMT_722200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'othToCny', null, null, null, null, null, '722200', 'F', null, null, null, null, null, null, null, null, null, null, 'AMT_722200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'total', null, null, null, null, null, '722200', 'G', null, null, null, null, null, null, null, null, null, null, 'AMT_722200_DQHQTCK');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'cny', null, null, null, null, null, '7c0000', 'A', null, null, null, null, null, null, null, null, null, null, 'AMT_7K0000_ZCJZZB');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'usdToCny', null, null, null, null, null, '7c0000', 'B', null, null, null, null, null, null, null, null, null, null, 'AMT_7K0000_ZCJZZB');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'eurToCny', null, null, null, null, null, '7c0000', 'C', null, null, null, null, null, null, null, null, null, null, 'AMT_7K0000_ZCJZZB');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'gbpToCny', null, null, null, null, null, '7c0000', 'D', null, null, null, null, null, null, null, null, null, null, 'AMT_7K0000_ZCJZZB');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'jpyToCny', null, null, null, null, null, '7c0000', 'E', null, null, null, null, null, null, null, null, null, null, 'AMT_7K0000_ZCJZZB');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'othToCny', null, null, null, null, null, '7c0000', 'F', null, null, null, null, null, null, null, null, null, null, 'AMT_7K0000_ZCJZZB');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'total', null, null, null, null, null, '7c0000', 'G', null, null, null, null, null, null, null, null, null, null, 'AMT_7K0000_ZCJZZB');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'cny', null, null, null, null, null, '824000', 'A', null, null, null, null, null, null, null, null, null, null, 'AMT_824000_QTZHSY');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'usdToCny', null, null, null, null, null, '824000', 'B', null, null, null, null, null, null, null, null, null, null, 'AMT_824000_QTZHSY');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'eurToCny', null, null, null, null, null, '824000', 'C', null, null, null, null, null, null, null, null, null, null, 'AMT_824000_QTZHSY');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'gbpToCny', null, null, null, null, null, '824000', 'D', null, null, null, null, null, null, null, null, null, null, 'AMT_824000_QTZHSY');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'jpyToCny', null, null, null, null, null, '824000', 'E', null, null, null, null, null, null, null, null, null, null, 'AMT_824000_QTZHSY');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'othToCny', null, null, null, null, null, '824000', 'F', null, null, null, null, null, null, null, null, null, null, 'AMT_824000_QTZHSY');

insert into chb_type_mapping_ex (MAPPINGID, MAPPINGCODE, MAPPINGNAME, SRCFIELD1, SRCFIELD2, SRCFIELD3, SRCFIELD4, SRCFIELD5, OBJFIELD, OBJFIELD1, OBJFIELD2, OBJFIELD3, OBJFIELD4, STATUS, WORKFLOW_ID, DEPT_CODE, UPDATE_USER, UPDATE_TIME, CREATE_USER, CREATE_TIME, ORG_CODE, SRCFIELD)
values (sys_guid(), '2_1', 'MI', 'total', null, null, null, null, null, '824000', 'G', null, null, null, null, null, null, null, null, null, null, 'AMT_824000_QTZHSY');