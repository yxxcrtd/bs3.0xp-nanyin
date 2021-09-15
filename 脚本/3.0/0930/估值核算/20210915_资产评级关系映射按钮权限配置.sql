-- 资产评级关系映射提交按钮权限配置
delete from  sys_page_element where  PAGE_ID='InsideOutsideGrade001' and ELEMENT_ID='ValInsideOutsideGrade002_btn_submit';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('InsideOutsideGrade001', 'ValInsideOutsideGrade002_btn_submit', '01', '01', null, '提交', 'system', null, sysdate, 'system', sysdate, null);

--资产减值结果管理 按钮权限配置
delete  from  sys_page_element where PAGE_ID='AssetImpairmentKpmgManagement001';
delete  from  sys_page_element where PAGE_ID='AssetImpairmentKpmgManagement002';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentKpmgManagement001', 'AssetImpairmentKpmgManagement001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentKpmgManagement001', 'AssetImpairmentKpmgManagement001_btn_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentKpmgManagement001', 'AssetImpairmentKpmgManagement001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentKpmgManagement001', 'AssetImpairmentKpmgManagement001_btn_reset', '01', '01', null, '重置', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('AssetImpairmentKpmgManagement001', 'AssetImpairmentKpmgManagement001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate, null);


