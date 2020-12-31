--  ZGBS-110  对部门进行删除操作，流程流转错误
UPDATE sys_page SET is_need_workflow = '01' WHERE page_id = 'SysDepartment001';
