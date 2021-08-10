delete from RSK_VALTBL_SUBJECT where sub_code in ('300399');
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('300399', '01', '其它证券清算款', 'system', '',sysdate, 'system',sysdate);
delete from RSK_VALTBL_SUBJECT where sub_code in ('12030205','12030206');
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12030205', '01', '深交所_非货币基金红利', 'admin', null,sysdate, 'admin', sysdate);
insert into RSK_VALTBL_SUBJECT (SUB_CODE, SUB_TYPE, SUB_NAME, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('12030206', '01', '深交所_货币基金红利', 'admin', null,sysdate, 'admin', sysdate);

