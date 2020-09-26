delete from sys_datadict_list_link k where k.dict_code ='REMINDER_TYPE_SMALL' and k.linked_dict_code='REMINDER_TYPE_BIG'
 and trim(k.linked_item_code)='R3' and k.item_code in ('R3S14','R3S15');

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REMINDER_TYPE_SMALL', 'R3S14', 'REMINDER_TYPE_BIG', 'R3                                                ', 'system', null, sysdate, 'system', sysdate);

insert into sys_datadict_list_link (DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('REMINDER_TYPE_SMALL', 'R3S15', 'REMINDER_TYPE_BIG', 'R3                                                ', 'system', null, sysdate, 'system', sysdate);
