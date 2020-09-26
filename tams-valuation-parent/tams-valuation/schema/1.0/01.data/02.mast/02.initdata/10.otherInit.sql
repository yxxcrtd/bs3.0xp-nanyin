/*导入规则配置_begin*/
truncate table COM_EXCEL_IMP_TARGET;
insert into COM_EXCEL_IMP_TARGET
select rownum,'VAL',b.TABLE_NAME,b.comments,'','','','systeam',date'2018-08-30','systeam',date'2018-08-30'
  from (select *
          from (select t.table_name, t.comments, t.table_type
                  from user_tab_comments t, user_tables b
                 where t.table_name = b.table_name
                   and b.TABLE_NAME not like 'ACT%') b
         where b.TABLE_NAME not like 'BATCH_%') b
 where b.TABLE_NAME not like 'VAL_%'
 and b.comments is not null;
 /*产品_begin*/
delete  fin_product where finprod_type='F16' ;
insert into fin_product values ('DK3LK180003','F16','90天成长（净值型）','平安财富-90天成长（净值型）人民币理财产品','01','','','DK3LK180003','','','CNY','','03','SH','02','','',to_date('2018/5/9','yyyy-MM-dd'),to_date('2038/5/9','yyyy-MM-dd'),to_date('2038/5/9','yyyy-MM-dd'),'','','','','DK3LK180003','F16','','interf','',to_date('2018-09-11','yyyy-MM-dd'),'interf',to_date('2018-09-11','yyyy-MM-dd'));
insert into fin_product values ('DK4LK180004','F16','180天成长（净值型）','平安财富-180天成长（净值型）人民币理财产品','01','','','DK4LK180004','','','CNY','','03','SH','02','','',to_date('2018/5/9','yyyy-MM-dd'),to_date('2038/5/9','yyyy-MM-dd'),to_date('2038/5/9','yyyy-MM-dd'),'','','','','DK4LK180004','F16','','interf','',to_date('2018-09-11','yyyy-MM-dd'),'interf',to_date('2018-09-11','yyyy-MM-dd'));
insert into fin_product values ('DK7LK180007','F16','84天成长3号（净值型）','平安财富-84天成长3号（净值型）人民币理财产品','01','','','DK7LK180007','','','CNY','','03','SH','02','','',to_date('2018/7/19','yyyy-MM-dd'),to_date('2038/7/19','yyyy-MM-dd'),to_date('2038/7/19','yyyy-MM-dd'),'','','','','DK7LK180007','F16','','interf','',to_date('2018-09-11','yyyy-MM-dd'),'interf',to_date('2018-09-11','yyyy-MM-dd'));
insert into fin_product values ('DK5LK180005','F16','年成长（净值型）','平安财富-年成长（净值型）人民币理财产品','01','','','DK5LK180005','','','CNY','','03','SH','02','','',to_date('2018/6/14','yyyy-MM-dd'),to_date('2038/6/14','yyyy-MM-dd'),to_date('2038/6/14','yyyy-MM-dd'),'','','','','DK5LK180005','F16','','interf','',to_date('2018-09-11','yyyy-MM-dd'),'interf',to_date('2018-09-11','yyyy-MM-dd'));
insert into fin_product values ('T01LK180001','F16','天天成长A款','平安财富-天天成长现金人民币理财产品A款','03','','','T01LK180001','','','CNY','','01','IB','02','','',to_date('2018/4/25','yyyy-MM-dd'),to_date('2023/4/25','yyyy-MM-dd'),to_date('2023/4/25','yyyy-MM-dd'),'','','','','T01LK180001','F16','','interf','',to_date('2018-09-11','yyyy-MM-dd'),'interf',to_date('2018-09-11','yyyy-MM-dd'));
/*产品_end*/
/*账套_begin*/
delete bok_bookset a where a.bookset_id in('DK3LK180003','DK4LK180004','DK7LK180007','DK5LK180005','T01LK180001');
insert into bok_bookset values ('DK3LK180003','DK3LK180003账套','02','01','DK3LK180003','01','CNY','00000000000000000002','QY',to_date('2018/5/9','yyyy-MM-dd'),to_date('2038/5/9','yyyy-MM-dd'),'interf','',to_date('2019-09-11','yyyy-MM-dd'),'interf',to_date('2019-09-11','yyyy-MM-dd'));
insert into bok_bookset values ('DK4LK180004','DK4LK180004账套','02','01','DK4LK180004','01','CNY','00000000000000000002','QY',to_date('2018/5/9','yyyy-MM-dd'),to_date('2038/5/9','yyyy-MM-dd'),'interf','',to_date('2019-09-11','yyyy-MM-dd'),'interf',to_date('2019-09-11','yyyy-MM-dd'));
insert into bok_bookset values ('DK7LK180007','DK7LK180007账套','02','01','DK7LK180007','01','CNY','00000000000000000002','QY',to_date('2018/7/19','yyyy-MM-dd'),to_date('2038/7/19','yyyy-MM-dd'),'interf','',to_date('2019-09-11','yyyy-MM-dd'),'interf',to_date('2019-09-11','yyyy-MM-dd'));
insert into bok_bookset values ('DK5LK180005','DK5LK180005账套','02','01','DK5LK180005','01','CNY','00000000000000000002','QY',to_date('2018/6/14','yyyy-MM-dd'),to_date('2038/6/14','yyyy-MM-dd'),'interf','',to_date('2019-09-11','yyyy-MM-dd'),'interf',to_date('2019-09-11','yyyy-MM-dd'));
insert into bok_bookset values ('T01LK180001','T01LK180001账套','02','03','T01LK180001','01','CNY','00000000000000000002','QY',to_date('2018/4/25','yyyy-MM-dd'),to_date('2023/4/25','yyyy-MM-dd'),'interf','',to_date('2019-09-11','yyyy-MM-dd'),'interf',to_date('2019-09-11','yyyy-MM-dd'));
/*账套_end*/
delete bok_bookset_date a where a.bookset_id in('DK3LK180003','DK4LK180004','DK7LK180007','DK5LK180005','T01LK180001');
insert into bok_bookset_date values ('DK3LK180003',to_date('2018/6/6','yyyy-MM-dd'),'system','',to_date('2018-09-09','yyyy-MM-dd'),'system',to_date('2018-09-09','yyyy-MM-dd'));
insert into bok_bookset_date values ('DK4LK180004',to_date('2018/6/6','yyyy-MM-dd'),'system','',to_date('2018-09-09','yyyy-MM-dd'),'system',to_date('2018-09-09','yyyy-MM-dd'));
insert into bok_bookset_date values ('DK7LK180007',to_date('2018/7/22','yyyy-MM-dd'),'system','',to_date('2018-09-09','yyyy-MM-dd'),'system',to_date('2018-09-09','yyyy-MM-dd'));
insert into bok_bookset_date values ('DK5LK180005',to_date('2018/6/14','yyyy-MM-dd'),'system','',to_date('2018-09-09','yyyy-MM-dd'),'system',to_date('2018-09-09','yyyy-MM-dd'));
insert into bok_bookset_date values ('T01LK180001',to_date('2018/6/6','yyyy-MM-dd'),'system','',to_date('2018-09-09','yyyy-MM-dd'),'system',to_date('2018-09-09','yyyy-MM-dd'));
--DELETE
delete from bok_bookset_ext a where a.bookset_id!='00000000000000000002';
delete from BOK_SUBJECT where bookset_id !='00000000000000000002';
delete from BOK_SCENECONFIG where bookset_id !='00000000000000000002';
delete from BOK_TABLELIST where bookset_id !='00000000000000000002';
delete from BOK_TABLEDICT where bookset_id !='00000000000000000002';
delete from BOK_SCENESUBJECT where bookset_id !='00000000000000000002';
--BOK_BOOKSET_EXT
insert into bok_bookset_ext
  (bookset_id, param_code, param_value, param_name, remark, create_user, create_dept, create_time, update_user, update_time)
select b.bookset_id, a.param_code, a.param_value, a.param_name, a.remark, a.create_user, a.create_dept, a.create_time, a.update_user, a.update_time
from bok_bookset_ext A, BOK_BOOKSET B where A.BOOKSET_ID = '00000000000000000002' AND B.BOOKSET_ID != A.BOOKSET_ID;
--BOK_SUBJECT
insert into BOK_SUBJECT(BOOKSET_ID,SUBJECT_NO,SUBJECT_NAME,FSUBJECT_ID,BASE_SUBJECT_NATURE,BAL_FLAG,SUBJECT_TYPE,SUBJECT_LEVEL,ACC_QUA_FLAG,ACC_INT_FLAG,OVERDRAWN_FLAG,GEN_DETSUB_FLAG,INV_AIM,SEC_MANAGE_ACCT_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
select B.BOOKSET_ID, A.SUBJECT_NO, A.SUBJECT_NAME, A.FSUBJECT_ID, A.BASE_SUBJECT_NATURE, A.BAL_FLAG, A.SUBJECT_TYPE, A.SUBJECT_LEVEL, A.ACC_QUA_FLAG, A.ACC_INT_FLAG, A.OVERDRAWN_FLAG, A.GEN_DETSUB_FLAG, A.INV_AIM, A.SEC_MANAGE_ACCT_ID, A.CREATE_USER, A.CREATE_DEPT, A.CREATE_TIME, A.UPDATE_USER, A.UPDATE_TIME 
from BOK_SUBJECT A, BOK_BOOKSET B where A.BOOKSET_ID = '00000000000000000002' AND B.BOOKSET_ID != A.BOOKSET_ID;
--BOK_SCENECONFIG
insert into BOK_SCENECONFIG (SCENE_NODE_ID, FSCENE_NODEID, BOOKSET_ID, NODE_ID, NODE_VALUE, "LEVEL", REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
select A.SCENE_NODE_ID, A.FSCENE_NODEID, B.BOOKSET_ID, A.NODE_ID, A.NODE_VALUE, A."LEVEL", A.REMARK, A.CREATE_USER, A.CREATE_DEPT, A.CREATE_TIME, A.UPDATE_USER, A.UPDATE_TIME 
from BOK_SCENECONFIG A, BOK_BOOKSET B where A.BOOKSET_ID = '00000000000000000002' AND B.BOOKSET_ID != A.BOOKSET_ID;
--BOK_SCENESUBJECT
insert into BOK_SCENESUBJECT (BOOKSET_ID, SCENE_NODE_ID, SUBJECT_NO, CD_FLAG, FORM_ELE,BOK_DETAIL_ID, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, ACCOUNT_NET) 
select B.BOOKSET_ID, A.SCENE_NODE_ID, A.SUBJECT_NO, A.CD_FLAG, A.FORM_ELE,A.BOK_DETAIL_ID, A.CREATE_USER, A.CREATE_DEPT, A.CREATE_TIME, A.UPDATE_USER, A.UPDATE_TIME, A.ACCOUNT_NET 
from BOK_SCENESUBJECT A, BOK_BOOKSET B where A.BOOKSET_ID = '00000000000000000002' AND B.BOOKSET_ID != A.BOOKSET_ID;
--BOK_TABLEDICT
insert into BOK_TABLEDICT (BOOKSET_ID,DICT_ID, DICT_CODE, TABLE_ID, DICT_NAME, DICT_TYPE, DICT_SOURCE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
select B.BOOKSET_ID, A.DICT_ID, A.DICT_CODE, A.TABLE_ID, A.DICT_NAME, A.DICT_TYPE, A.DICT_SOURCE, A.CREATE_USER, A.CREATE_DEPT, A.CREATE_TIME, A.UPDATE_USER, A.UPDATE_TIME 
from BOK_TABLEDICT A, BOK_BOOKSET B where A.BOOKSET_ID = '00000000000000000002' AND B.BOOKSET_ID != A.BOOKSET_ID;
--BOK_TABLELIST
insert into BOK_TABLELIST (BOOKSET_ID，TABLE_ID, TABLE_NAME, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) 
select B.BOOKSET_ID, A.TABLE_ID, A.TABLE_NAME, A.REMARK, A.CREATE_USER, A.CREATE_DEPT, A.CREATE_TIME, A.UPDATE_USER, A.UPDATE_TIME 
from BOK_TABLELIST A, BOK_BOOKSET B where A.BOOKSET_ID = '00000000000000000002' AND B.BOOKSET_ID != A.BOOKSET_ID;