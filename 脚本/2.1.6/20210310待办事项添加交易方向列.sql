--update
update SYS_MYTODOANDWORK_PSL  set seq_no ='006',order_no ='6' where seq_no ='005' and psl_field like'%processStateValue%';
update SYS_MYTODOANDWORK_PSL  set seq_no ='005',order_no ='5' where seq_no ='004' and psl_field like'%actionType%';
update SYS_MYTODOANDWORK_PSL  set seq_no ='106',order_no ='6' where seq_no ='105' and psl_field like'%processStateValue%';
update SYS_MYTODOANDWORK_PSL  set seq_no ='105',order_no ='5' where seq_no ='104' and psl_field like'%actionType%';
--delete 
delete from SYS_MYTODOANDWORK_PSL where seq_no in ('004','104') and psl_field like '%MainPage.ps%';
INSERT INTO SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('004', '01', '{field:''ps'', width:100, title: ''MainPage.ps''}', '4', 'system', null, TIMESTAMP '2021-03-01 00:00:00', 'system', TIMESTAMP '2021-03-01 00:00:00');
INSERT INTO SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('104', '02', '{field:''ps'', width:100, title: ''MainPage.ps''}', '4', 'system', null, TIMESTAMP '2021-03-01 00:00:00', 'system', TIMESTAMP '2021-03-01 00:00:00');

