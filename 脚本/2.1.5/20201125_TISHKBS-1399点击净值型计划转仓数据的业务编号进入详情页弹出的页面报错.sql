/*add yujinfa 2020/10/21 增加平安计划类净值型卖出业务类型 start */
delete from sys_datadict_list sd where sd.dict_code='PINAN_BUSINESS_TYPE' and sd.item_code='200';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PINAN_BUSINESS_TYPE', '200', '计划类资产（净值型）卖出', 200, '0', 'system', null, sysdate, 'system', sysdate);
/*add yujinfa 2020/10/21 增加平安计划类净值型卖出业务类型 end */