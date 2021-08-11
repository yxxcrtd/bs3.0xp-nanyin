
/***ZGBSEI-166  【新人行升级】PIE产品终止信息查询导出手工处理、取消手工处理等按钮未配置角色权限**/
DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID = 'PbcPrdBaseinfoEndSubNew001';

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PbcPrdBaseinfoEndSubNew001', 'PbcPrdBaseinfoEndSubNew001_btn_cancelManual', '01', '01', null, '取消手工处理', 'system', null, SYSDATE, 'system', SYSDATE, 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PbcPrdBaseinfoEndSubNew001', 'PbcPrdBaseinfoEndSubNew001_btn_chbexport', '01', '01', null, '导出', 'system', null, SYSDATE, 'system', SYSDATE, 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PbcPrdBaseinfoEndSubNew001', 'PbcPrdBaseinfoEndSubNew001_btn_chbexportxls', '01', '01', null, '导出人行Excel', 'system', null, SYSDATE, 'system', SYSDATE, 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PbcPrdBaseinfoEndSubNew001', 'PbcPrdBaseinfoEndSubNew001_btn_manual', '01', '01', null, '手工处理', 'system', null, SYSDATE, 'system', SYSDATE, 'C');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PbcPrdBaseinfoEndSubNew001', 'PbcPrdBaseinfoEndSubNew001_btn_search', '01', '01', null, '查询', 'system', null, SYSDATE, 'system', SYSDATE, 'COM');

insert into SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('PbcPrdBaseinfoEndSubNew001', 'PbcPrdBaseinfoEndSubNew001_btn_submit', '01', '01', null, '数据检查', 'system', null, SYSDATE, 'system', SYSDATE, 'C');
