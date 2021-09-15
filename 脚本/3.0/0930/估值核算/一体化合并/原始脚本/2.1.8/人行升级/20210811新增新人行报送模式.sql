--新增系统参数人行报送模式 01:新人行dat报送,老人行excel报送,02:新人行dat报送,老人行dat报送									
DELETE  FROM  SYS_PARA WHERE SYSTEM_CODE = 'SVR' AND PARA_CODE = 'PBC_REPORT_MODEL';
insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('SVR', 'PBC_REPORT_MODEL', '01', '', null, '新人行报送模式(01:新人行dat报送,老人行excel报送,02:新人行dat报送,老人行dat报送)', 'system', null, sysdate, 'system', sysdate, null, '02');

