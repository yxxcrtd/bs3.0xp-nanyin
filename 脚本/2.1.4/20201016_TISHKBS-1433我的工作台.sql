


----------  TISHKBS-1433 CLONE - 用户工作台-我的工作
----  仅微服务版本需要执行

DELETE  Data_Auth_Mapper_Config WHERE sql_id = 'com.joyintech.system.main.dao.MainMapper.getAgentList';
        
insert into Data_Auth_Mapper_Config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.system.main.dao.MainMapper.getAgentList', '1', '01', null, 'SELECT * FROM ( %s ) BASE99 LEFT JOIN SYS_DEPARTMENT SD   ON SD.DEPT_CODE = BASE99.DEPT_CODE LEFT JOIN SYS_INSTITUTION SI ON SI.INSTITUTION_CODE = SD.INSTITUTION_CODE  WHERE SI.INSTITUTION_CODE IN %s', null, null, 'admin', null, SYSDATE, 'admin', SYSDATE, null, null, null);
