delete from sys_menu where PAGE_ID in('AssetRatingAllocation001','Specialassets001','Specialassets002');
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_008_015', 'VAL', null, 'VALUATION_001_008', 'AssetRatingAllocation001', 15, '2', '资产评级来源配置', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');


delete from SYS_PAGE where PAGE_ID in('AssetRatingAllocation001','Specialassets001','Specialassets002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('AssetRatingAllocation001', 'RSK', '/model/impairment/assetratingallocation/AssetRatingAllocation001', 'lossmodel/assetratingallocation/AssetRatingAllocation001', 'VALUATION_001_011_001', null, null, '02', '01', '资产评级配置', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('Specialassets001', 'RSK', '/model/impairment/assetratingallocation/specialassets/Specialassets001', 'lossmodel/assetratingallocation/specialassets/Specialassets001', null, null, null, '02', '01', '特殊资产评级配置查询', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('Specialassets002', 'RSK', '/model/impairment/assetratingallocation/specialassets/Specialassets002', 'lossmodel/assetratingallocation/specialassets/Specialassets002', null, null, null, '02', '01', '特殊资产评级配置新建', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');
Insert into SYS_PAGE (PAGE_ID,SYSTEM_CODE,PAGE_URL,PAGE_FILE,MENU_ID,HEIGHT,WIDTH,IS_HP_SELECT,IS_NEED_WORKFLOW,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('Specialassets002','RSK','/model/impairment/assetratingallocation/specialassets/Specialassets002','model/impairment/assetratingallocation/specialassets/Specialassets002',null,null,null,'02','01','特殊资产评级配置新建','system',null,sysdate,'system',sysdate);


delete from SYS_PAGE where PAGE_ID in('LossGivenDefaultManage001','LossGivenDefaultMaintain001','LossGivenDefaultMaintain002');
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LossGivenDefaultManage001', 'RSK', '/model/impairment/lossgivendefaultmanage/LossGivenDefaultManage001', 'lossmodel/lossgivendefaultmanage/LossGivenDefaultManage001', 'VALUATION_001_011_005', null, null, '02', '01', '资产评级配置', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LossGivenDefaultMaintain001', 'RSK', '/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain001', 'lossmodel/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain001', null, null, null, '02', '01', '特殊资产评级配置查询', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');

insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('LossGivenDefaultMaintain002', 'RSK', '/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002', 'lossmodel/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002', null, null, null, '02', '01', '特殊资产评级配置新建', 'system', null, '05-AUG-21 03.07.29.000000 PM', 'system', '05-AUG-21 03.07.29.000000 PM');
commit;