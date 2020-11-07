--Add By yangwenjun 20200814 估值信息查询 start


--添加菜单以及页面和页面按钮
delete from SYS_MENU where MENU_ID='BASICDATA_MANAGEMENT_001_002_025';
insert into SYS_MENU values('BASICDATA_MANAGEMENT_001_002_025','MST','','BASICDATA_MANAGEMENT_001_002','MstValuateInfo001',17,'02','估值信息查询','system','',sysdate,'system',sysdate);
delete from SYS_PAGE where PAGE_ID='MstValuateInfo001';
insert into SYS_PAGE values('MstValuateInfo001','MST','/invest/basicdata/mstvaluateinfo/MstValuateInfo001','invest/basicdata/mstvaluateinfo/MstValuateInfo001','BASICDATA_MANAGEMENT_001_002_025','','','02','01','估值信息查询','system','',sysdate,'system',sysdate);
delete from SYS_PAGE_ELEMENT where PAGE_ID='MstValuateInfo001' and ELEMENT_ID='MstValuateInfo001_btn_export';
insert into SYS_PAGE_ELEMENT values('MstValuateInfo001','MstValuateInfo001_btn_export','01','01','','导出','system','',sysdate,'system',sysdate,'01');
delete from SYS_PAGE_ELEMENT where PAGE_ID='MstValuateInfo001' and ELEMENT_ID='MstValuateInfo001_btn_search';
insert into SYS_PAGE_ELEMENT values('MstValuateInfo001','MstValuateInfo001_btn_search','01','01','','查询','system','',sysdate,'system',sysdate,'01');

--二级法人改造 添加拦截器 不同成员行社的数据屏蔽
delete from data_auth_mapper_config where data_auth_mapper_config.SQL_ID='com.joyintech.service.basicdata.mstvaluateinfo.dao.MstValuateInfoMapper.selectForPage';
insert into data_auth_mapper_config (SQL_ID, FILTER_TYPE, IS_NEST, JOIN_COLUMN_NAME, FILTER_SQL, SYSTEM_CODE, PAGE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADD_COLUMN, ADD_COLUMN_NAME, DATA_TYPE)
values ('com.joyintech.service.basicdata.mstvaluateinfo.dao.MstValuateInfoMapper.selectForPage', '1', '02', 'orgno', '', 'MST', 'MstValuateInfo001', 'admin', '', '13-8月 -20 03.15.00.000000 下午', 'admin', '13-8月 -20 03.15.00.000000 下午', '01', 'orgno belongInstitutionCode', '');

--Add By yangwenjun 20200814 添加菜单以及页面-估值信息查询 end