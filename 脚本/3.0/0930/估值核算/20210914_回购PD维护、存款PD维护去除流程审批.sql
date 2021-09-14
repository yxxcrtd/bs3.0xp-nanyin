-- 回购PD维护和存款PD维护去除流程审批
update sys_wfl_business_module set module_id='noneApproval' where page_id='ModCkGradePdConfig002';
update sys_wfl_business_module set module_id='noneApproval' where page_id='ModHgGradePdConfig002';