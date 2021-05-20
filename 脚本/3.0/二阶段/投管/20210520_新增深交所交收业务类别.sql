----新增深交所交收业务类别
delete from sys_datadict_list where DICT_CODE = 'DELIVERY_BUS_TYPE' and ITEM_CODE IN ('03','04');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DELIVERY_BUS_TYPE', '03', '深交所综合协议平台债券买入', 3, '0', 'system', null, '26-NOV-20 02.07.50.000000 PM', 'system', '26-NOV-20 02.07.50.000000 PM');

insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('DELIVERY_BUS_TYPE', '04', '深交所综合协议平台债券卖出', 4, '0', 'system', null, '26-NOV-20 02.07.50.000000 PM', 'system', '26-NOV-20 02.07.50.000000 PM');

