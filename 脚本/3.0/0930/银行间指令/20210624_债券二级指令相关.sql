--单组合指令页面调整
delete from sys_para where PARA_CODE = 'dan_etr_isshow';
insert into sys_para (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE, UPDATE_FLG)
values ('ETR', 'dan_etr_isshow', 'N', '01', null, '债券二级指令是否显示单组合指令页面 Y：是；N：否', 'system', null, sysdate, 'admin', sysdate, null, '01');

--价格模式添加市价指令
delete from sys_page_dictlist where PAGE_ID = 'EtrIbBondinfo002' and DICT_ID = 'PRICE_MODEL' and DICT_ITEM_VAL = 'PRM03';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('EtrIbBondinfo002', '999999', 'PRICE_MODEL', 'PRM03', null, null, 'system', null, sysdate, 'system', sysdate, null);

--投资目的
delete from sys_page_dictlist where PAGE_ID = 'EtrIbBondinfo002' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('EtrIbBondinfo002', '999999', 'INV_AIM', 'AC', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('EtrIbBondinfo002', '999999', 'INV_AIM', 'FVOCI', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('EtrIbBondinfo002', '999999', 'INV_AIM', 'FVTPL', null, null, 'system', null, sysdate, 'system', sysdate, null);

--增加计算方式字段
alter table ETR_ENTRUST_ADD ADD CALCULATE_FIELD VARCHAR(50);
COMMENT ON COLUMN ETR_ENTRUST_ADD.CALCULATE_FIELD
IS '计算方式';

--多组合页面调整
--价格模式添加市价指令
delete from sys_page_dictlist where PAGE_ID = 'EtribBondInfo003' and DICT_ID = 'PRICE_MODEL' and DICT_ITEM_VAL = 'PRM03';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('EtribBondInfo003', '999999', 'PRICE_MODEL', 'PRM03', null, null, 'system', null, sysdate, 'system', sysdate, null);


--投资目的
delete from sys_page_dictlist where PAGE_ID = 'EtribBondInfo003' and DICT_ID = 'INV_AIM';
insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('EtribBondInfo003', '999999', 'INV_AIM', 'AC', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('EtribBondInfo003', '999999', 'INV_AIM', 'FVOCI', null, null, 'system', null, sysdate, 'system', sysdate, null);

insert into sys_page_dictlist (PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('EtribBondInfo003', '999999', 'INV_AIM', 'FVTPL', null, null, 'system', null, sysdate, 'system', sysdate, null);






