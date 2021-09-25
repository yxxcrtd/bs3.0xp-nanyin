-- 回购PD维护和存款PD维护去除流程审批
update sys_wfl_business_module set module_id='noneApproval' where page_id='ModCkGradePdConfig002';
update sys_wfl_business_module set module_id='noneApproval' where page_id='ModHgGradePdConfig002';

--股息增长率配置(提交 权限按钮配置)
delete from   sys_page_element t where element_id='ModStockDivitrateConfig002_btn_submit';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ADMIN_FLAG)
values ('ModStockDivitrateConfig001', 'ModStockDivitrateConfig002_btn_submit', '01', '01', null, '提交', 'system', null, sysdate, 'system', sysdate, null);


