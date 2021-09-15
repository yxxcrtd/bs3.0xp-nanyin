

------ SVR_ORG_ASSET_TYPE2
delete sys_datadict_list where DICT_CODE = 'SVR_ORG_ASSET_TYPE2' and ITEM_CODE in ('F11','F13','F14','F15','F16');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_ORG_ASSET_TYPE2', 'F11', '股票', 11, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_ORG_ASSET_TYPE2', 'F13', '外汇即期', 13, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_ORG_ASSET_TYPE2', 'F14', '外汇远期', 14, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_ORG_ASSET_TYPE2', 'F15', '外汇掉期', 15, '0', 'system', null, sysdate, 'system', sysdate, null);

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('SVR_ORG_ASSET_TYPE2', 'F16', '利率互换', 16, '0', 'system', null, sysdate, 'system', sysdate, null);


delete sys_datadict_list_link where LINKED_DICT_CODE = 'SVR_ORG_ASSET_TYPE1' and DICT_CODE = 'SVR_ORG_ASSET_TYPE2';

---- 拆借
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', '01', 'SVR_ORG_ASSET_TYPE1', 'F08', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', '02', 'SVR_ORG_ASSET_TYPE1', 'F08', 'system', null, sysdate, 'system', sysdate);
--insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
--values ('SVR_ORG_ASSET_TYPE2', '', 'SVR_ORG_ASSET_TYPE1', 'F08', 'system', null, sysdate, 'system', sysdate);


--质押式
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', '03', 'SVR_ORG_ASSET_TYPE1', 'F07', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', '04', 'SVR_ORG_ASSET_TYPE1', 'F07', 'system', null, sysdate, 'system', sysdate);


---存款
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'D01', 'SVR_ORG_ASSET_TYPE1', 'F05', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'D02', 'SVR_ORG_ASSET_TYPE1', 'F05', 'system', null, sysdate, 'system', sysdate);

-------jijin
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F01', 'SVR_ORG_ASSET_TYPE1', 'F02', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F02', 'SVR_ORG_ASSET_TYPE1', 'F02', 'system', null, sysdate, 'system', sysdate);

--- 计划类净值型
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N01', 'SVR_ORG_ASSET_TYPE1', 'F04', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N02', 'SVR_ORG_ASSET_TYPE1', 'F04', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N03', 'SVR_ORG_ASSET_TYPE1', 'F04', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N04', 'SVR_ORG_ASSET_TYPE1', 'F04', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N05', 'SVR_ORG_ASSET_TYPE1', 'F04', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N06', 'SVR_ORG_ASSET_TYPE1', 'F04', 'system', null, sysdate, 'system', sysdate);

--- 利率
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N01', 'SVR_ORG_ASSET_TYPE1', 'F03', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N02', 'SVR_ORG_ASSET_TYPE1', 'F03', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N03', 'SVR_ORG_ASSET_TYPE1', 'F03', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N04', 'SVR_ORG_ASSET_TYPE1', 'F03', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N05', 'SVR_ORG_ASSET_TYPE1', 'F03', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N06', 'SVR_ORG_ASSET_TYPE1', 'F03', 'system', null, sysdate, 'system', sysdate);
   
---项目
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P01', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P02', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P03', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P04', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P05', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P06', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P07', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P08', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P09', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P10', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'P11', 'SVR_ORG_ASSET_TYPE1', 'F06', 'system', null, sysdate, 'system', sysdate);

--- 债券

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B01', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B02', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B03', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B04', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B05', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B06', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B07', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B08', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B09', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B10', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B11', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B12', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B13', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B14', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B15', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B16', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'B17', 'SVR_ORG_ASSET_TYPE1', 'F01', 'system', null, sysdate, 'system', sysdate);



---买断式

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', '03', 'SVR_ORG_ASSET_TYPE1', 'F10', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', '04', 'SVR_ORG_ASSET_TYPE1', 'F10', 'system', null, sysdate, 'system', sysdate);

---计划类 货币
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N01', 'SVR_ORG_ASSET_TYPE1', 'F09', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N02', 'SVR_ORG_ASSET_TYPE1', 'F09', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N03', 'SVR_ORG_ASSET_TYPE1', 'F09', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N04', 'SVR_ORG_ASSET_TYPE1', 'F09', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N05', 'SVR_ORG_ASSET_TYPE1', 'F09', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'N06', 'SVR_ORG_ASSET_TYPE1', 'F09', 'system', null, sysdate, 'system', sysdate);

---股票

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F11', 'SVR_ORG_ASSET_TYPE1', 'F11', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F13', 'SVR_ORG_ASSET_TYPE1', 'F13', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F14', 'SVR_ORG_ASSET_TYPE1', 'F14', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F15', 'SVR_ORG_ASSET_TYPE1', 'F15', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F16', 'SVR_ORG_ASSET_TYPE1', 'F16', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F29', 'SVR_ORG_ASSET_TYPE1', 'F12', 'system', null, sysdate, 'system', sysdate);
insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SVR_ORG_ASSET_TYPE2', 'F30', 'SVR_ORG_ASSET_TYPE1', 'F12', 'system', null, sysdate, 'system', sysdate);
