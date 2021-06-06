
DELETE  SYS_MYTODOANDWORK_PSL 
WHERE seq_no IN ('001','002','003','004','005','006','101','102','103','104','105','106');

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('001', '01', '{field:''crtDate'', width:100, title: ''MainPage.date'', align: ''left'', type: ''date''}', '1', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('002', '01', '{field:''businessCode'', width:210, title: ''MainPage.businessCode''}', '2', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('003', '01', '{field:''businessType'', width:150, title: ''MainPage.businessType'', sortField: ''businessTypeValue''}', '3', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('004', '01', '{field:''ps'', width:100, title: ''MainPage.ps''}', '4', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('005', '01', '{field:''actionType'', width:100, title: ''MainPage.actionType''}', '5', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('006', '01', '{field:''processStateValue'', width:100, title: ''MainPage.processState'', sortField: ''actionType''}', '6', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('101', '02', '{field:''crtDate'', width:70, title: ''MainPage.date'', align: ''left'', type: ''date''}', '1', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('102', '02', '{field:''businessCode'', width:200, title: ''MainPage.businessCode''}', '2', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('103', '02', '{field:''businessType'', width:140, title: ''MainPage.businessType'', sortField: ''businessTypeValue''}', '3', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('104', '02', '{field:''actionType'', width:100, title: ''MainPage.actionType''}', '5', 'system', null, sysdate, 'system', sysdate);

insert into SYS_MYTODOANDWORK_PSL (SEQ_NO, PSL_TYPE, PSL_FIELD, ORDER_NO, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('105', '02', '{field:''processStateValue'', width:100, title: ''MainPage.processState'', sortField: ''actionType''}', '6', 'system', null, sysdate, 'system', sysdate);
