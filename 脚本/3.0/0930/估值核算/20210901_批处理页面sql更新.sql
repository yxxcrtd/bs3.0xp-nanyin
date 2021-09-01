--减值批处理页面更新
delete from  sys_page where page_id='ModImpaireBatchTask001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModImpaireBatchTask001', 'VAL', '/model/impairment/batch/batchtask/ModImpaireBatchTask001', 'lossmodel/batch/batchtask/ModImpaireBatchTask001', 'VALUATION_001_010_005', null, null, '02', '01', '减值批处理任务', 'system', null, '05-AUG-21 03.49.51.000000 PM', 'system', '05-AUG-21 03.49.51.000000 PM');

delete from  sys_page where page_id='ModImpaireBatchTask002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModImpaireBatchTask002', 'VAL', '/model/impairment/batch/batchtask/ModImpaireBatchTask002', 'lossmodel/batch/batchtask/ModImpaireBatchTask002', null, null, null, '02', '01', '减值批处理任务-详情', 'system', null, '05-AUG-21 03.49.51.000000 PM', 'system', '05-AUG-21 03.49.51.000000 PM');