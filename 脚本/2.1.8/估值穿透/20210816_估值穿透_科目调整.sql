delete from RSK_VALTBL_SUBJECT where sub_code in ('10030101','10030201','10030301','10210601','10211301','10310601','12041801');
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10030101', '01', '存放同业', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10030201', '01', '拆放同业', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10030301', '01', '同业借款', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10210601', '01', '券商备付金', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10211301', '01', '期货备付金', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10310601', '01', '券商保证金', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12041801', '01', '其他应收利息', 'admin', null,sysdate, 'admin', sysdate);