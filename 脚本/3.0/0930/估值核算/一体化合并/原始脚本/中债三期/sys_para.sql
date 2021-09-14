update   sys_para  set   param_value='01'  where  para_code='export_excel_type';

delete from sys_para where SYSTEM_CODE = 'SVR' and PARA_CODE = 'CHB_ASSETS_BASIS_DATA_CHILD';
delete from sys_para where SYSTEM_CODE = 'SVR' and PARA_CODE = 'CHB_DEPT_CODE';
delete from sys_para where SYSTEM_CODE = 'CHB' and PARA_CODE = 'CHB_IS_SHOW_ZERO';
delete from sys_para where SYSTEM_CODE = 'SVR' and PARA_CODE = 'CHB_ORG_CODE';
delete from sys_para where SYSTEM_CODE = 'SVR' and PARA_CODE = 'CHB_PRD_DEAL_FILE_TYPE';
delete from sys_para where SYSTEM_CODE = 'SVR' and PARA_CODE = 'CHB_WARNING_CHECKRESULT_IS_SEND';

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR', 'CHB_ASSETS_BASIS_DATA_CHILD', 'Y', '01', null, '子资管计划资产基础指标数据表筛选开关: Y 开,N 关', 'system', null, sysdate, 'admin', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR', 'CHB_DEPT_CODE', 'ZCGLB', '01', null, '中债报送部门代码', 'system', null, sysdate, 'admin', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('CHB', 'CHB_IS_SHOW_ZERO', '02', '01', '01,02', '01－显示0，02-显示空', 'system', null, sysdate, 'xy', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR', 'CHB_ORG_CODE', 'Z70024', '01', null, '中债报送机构代码', 'system', null, sysdate, 'admin', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR', 'CHB_PRD_DEAL_FILE_TYPE', '02', '01', '01,02', '产品交易文件接口类型：01规范文件；02阜新恒生文件', 'system', null, sysdate, 'system', sysdate);

insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR', 'CHB_WARNING_CHECKRESULT_IS_SEND', '01', ' ', null, '中债一二期直连报送前检查预警信息是否发送接口', 'system', null, sysdate, 'system', sysdate);

