--前瞻因子增加字段：综合系数
ALTER TABLE MOD_PD_FACTOR_CONFIG ADD COMBINED_FACTOR NUMBER(30,6) ;
COMMENT ON COLUMN MOD_PD_FACTOR_CONFIG.COMBINED_FACTOR IS '综合系数' ;

--修改为非必填项
alter table MOD_PD_FACTOR_CONFIG modify VOUCH_QUARTER null;
alter table MOD_PD_FACTOR_CONFIG modify OPTIMISTIC_FACTOR null;
alter table MOD_PD_FACTOR_CONFIG modify BASIC_FACTOR null;
alter table MOD_PD_FACTOR_CONFIG modify PESSIMISM_FACTOR null;

--前瞻因子page
delete from  sys_page where PAGE_ID='PdFactorKpmgConfig002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorKpmgConfig002', 'RSK', '/model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002', 'lossmodel/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002', null, null, null, '02', '01', '前瞻因子维护新建', 'system', null, sysdate, 'system', sysdate);

delete from  sys_page where PAGE_ID='PdFactorKpmgConfig001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PdFactorKpmgConfig001', 'RSK', '/model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001', 'lossmodel/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001', null, null, null, '02', '01', '前瞻因子维护', 'system', null, sysdate, 'system', sysdate);


--前瞻因子工作流
delete from  sys_wfl_business_module where PAGE_ID='PdFactorKpmgConfig002';
insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('PdFactorKpmgConfig002', 'PdFactorKpmgConfig001_btn_delete', 'noneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('PdFactorKpmgConfig002', 'PdFactorKpmgConfig002_btn_save', 'noneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

insert into sys_wfl_business_module (PAGE_ID, ELEMENT_ID, MODULE_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, IS_SEND_UORG)
values ('PdFactorKpmgConfig002', 'PdFactorKpmgConfig002_btn_submit', 'noneApproval', 'system', null, sysdate, 'system', sysdate, 'ALL', '02');

