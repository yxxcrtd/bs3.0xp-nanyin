--ZGBSS-19 产品登记编码新建，产品代码下拉列表值显示不对，显示了其他机构的产品(未进行数据隔离）
delete from DATA_AUTH_MAPPER_CONFIG where sql_id = 'com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper.getProdInfo';
insert into 
DATA_AUTH_MAPPER_CONFIG(SQL_ID,FILTER_TYPE,IS_NEST,JOIN_COLUMN_NAME,FILTER_SQL,SYSTEM_CODE,PAGE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADD_COLUMN,ADD_COLUMN_NAME,DATA_TYPE)
values
('com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper.getProdInfo','1','01',null,'select * from ( %s ) b LEFT JOIN (select f.finprod_market_id,di.institution_code from fin_product f left join DATA_AUTH_NOTIFY_INST di on f.finprod_id=di.business_id and di.model_type = ''02'' and DI.DATA_TYPE=''FINPROD04'') di on di.finprod_market_id = b.code where di.institution_code in %s','SVR','ProRegistCodeManage003','admin',null,sysdate,'admin',sysdate,null,null,null);