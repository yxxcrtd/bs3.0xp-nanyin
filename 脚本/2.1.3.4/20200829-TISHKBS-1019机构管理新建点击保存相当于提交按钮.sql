
------ add by wj 20200827 TISHKBS-1019  CLONE - UAT005706系统管理-机构管理-新建点击保存后相当于提交按钮，提交成功

UPDATE sys_page SET is_need_workflow = '01' WHERE page_id = 'SysInstitution002';
---- 暂时替换为一级流程， 之后配置 按照各自的项目需要的流程配置
UPDATE sys_wfl_business_module SET MODULE_ID = 'noneApproval' WHERE page_id = 'SysInstitution002' AND ELEMENT_ID = 'SysInstitution002_btn_save';


------ add by wj 20200829 TISHKBS-1019 
-----  配了流程，删除提交时 不弹出选择用户直接进待办
UPDATE sys_page SET is_need_workflow = '01' WHERE page_id = 'SysInstitution001';
