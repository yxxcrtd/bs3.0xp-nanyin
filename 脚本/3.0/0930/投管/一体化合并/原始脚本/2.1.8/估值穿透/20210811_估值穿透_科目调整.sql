delete from RSK_VALTBL_SUBJECT where sub_code in ('12031801','12040101','12040201','12040301','12040401');
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12031801', '01', '其他应收红利', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12040101', '01', '应收银行活期存款利息', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12040201', '01', '应收备付金利息', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12040301', '01', '应收保证金利息', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12040401', '01', '应收存款利息', 'admin', null,sysdate, 'admin', sysdate);