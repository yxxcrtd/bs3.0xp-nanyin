--浮动管理费报价调整新建费用品种下拉值进行数据隔离
delete from DATA_AUTH_MAPPER_CONFIG where sql_id = 'com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dao.FinFloatMgtExpQuotationMapper.getFeeInfo';
insert into 
DATA_AUTH_MAPPER_CONFIG(SQL_ID,FILTER_TYPE,IS_NEST,JOIN_COLUMN_NAME,FILTER_SQL,SYSTEM_CODE,PAGE_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,ADD_COLUMN,ADD_COLUMN_NAME,DATA_TYPE)
values
('com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dao.FinFloatMgtExpQuotationMapper.getFeeInfo','2','02','id',null,'MST','FinFloatMgtExpQuotation002','admin',null,sysdate,'admin',sysdate,null,null,'MST16');