/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2018/7/15 星期日 下午 6:31:18                     */
/*==============================================================*/
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('01','1002',1,'银行存款','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('02','1003',1,'存拆放款项','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('03','1102',1,'债券类资产','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('03','1103',1,'债券类资产','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('03','1104',1,'债券类资产','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('03','1105',1,'债券类资产','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('03','1103A',2,'债券类资产','02','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('04','1106',1,'项目类资产','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('05','1107',1,'计划类资产','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('06','1108',1,'股票','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('07','1103A',2,'基金','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('08','1202',1,'买入返售金融资产','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('09','9999C',1,'其他','01','admin',null,sysdate,'admin',sysdate);
insert into REP_BOOK_RELATION(DICT_CODE, SUBJECT_NO, SUBJECT_LEVEL, DICT_VALUE, FLAG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('10','9999T',1,'合计','01','admin',null,sysdate,'admin',sysdate);



ALTER TABLE SYS_PAGE_DICTLIST ADD SUB_PAGE_ID          VARCHAR(50)   ;
ALTER TABLE SYS_PAGE_DICTLIST ADD DICT_ITEM_VAL_CNNAME           VARCHAR(1000)   ;
ALTER TABLE SYS_PAGE_DICTLIST ADD DICT_ITEM_VAL_DEFAULT           VARCHAR(50)   ;