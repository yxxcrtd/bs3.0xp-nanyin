-- 批前检查任务配置信息 add by chenkang 20220127
delete from sys_prebatch_task_add where TASK_ID = '88600';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88600', '00', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add where TASK_ID = '88601';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88601', '00', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add where TASK_ID = '88602';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88602', '01', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add where TASK_ID = '88603';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88603', '00', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add where TASK_ID = '88604';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88604', '01', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add where TASK_ID = '88605';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88605', '01', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add where TASK_ID = '88606';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88606', '00', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add where TASK_ID = '88607';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88607', '00', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add where TASK_ID = '88608';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88608', '01', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add  where TASK_ID = '88610';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88610', '00', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add  where TASK_ID  = '88611';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88611' , '01', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';
delete from sys_prebatch_task_add  where TASK_ID  = '88612';
insert into sys_prebatch_task_add (GROUP_ID, TASK_ID, STOP_FLAG, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
select  a.seq_no,'88612' , '00', null, 'system', null, sysdate, 'system', sysdate from sys_batch_grp a where BATCH_NAME = '批前检查';