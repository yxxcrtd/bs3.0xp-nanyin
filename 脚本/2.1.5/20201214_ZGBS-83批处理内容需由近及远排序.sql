
------- ZGBS-83
DELETE Data_Auth_Mapper_Config WHERE sql_id LIKE 'com.joyintech.system.batch.batchmonitor.dao.SysBatchMonitor001Mapper%';


insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.system.batch.batchmonitor.dao.SysBatchMonitor001Mapper.findBatchExeDetail', '1', '01', null, 'SELECT base99.*, si.Institution_Code belongInstitutionCode
FROM (%s) base99
left join sys_user su
ON base99.CREATEUSER = su.user_name
left JOIN Sys_Department sd
ON sd.dept_code = su.dept_code
left JOIN Sys_Institution SI
ON Si.Institution_Code = sd.institution_code
WHERE SI.Institution_Code IN %s
or BASE99.CREATEUSER in (''System'',''system'') ORDER BY base99.createTime DESC ',
 'SYS', 'SysBatchMonitor001', 'admin', null, sysdate, 'admin', sysdate, null, null, null);

