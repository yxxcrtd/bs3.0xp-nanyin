delete from RSK_VALTBL_SUBJECT where sub_code in ('110221','11022101');
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('110221', '01', '沪港通股票投资', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('11022101', '01', '沪港通股票投资_成本', 'admin', null,sysdate, 'admin', sysdate);

delete from RSK_VALTBL_SUBJECT where sub_code in ('110222','11022201');
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('110222', '01', '深港通股票投资', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('11022201', '01', '深港通股票投资_成本', 'admin', null,sysdate, 'admin', sysdate);

delete from RSK_VALTBL_SUBJECT where sub_code in ('110231','11023101');
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('110231', '01', '融资融券股票投资', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('11023101', '01', '融资融券股票投资成本', 'admin', null,sysdate, 'admin', sysdate);