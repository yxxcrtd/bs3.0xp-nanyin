/** ===   add by lby on 20201229 新增批：科目余额重新汇总批处理 start  === **/
delete from SYS_BATCH_TASK sbt where BEAN_NAME = 'subjectBalanceAgainCollectTask';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('1081', 'VAL', '科目余额重新汇总批处理', null, 'subjectBalanceAgainCollectTask', 140, 'system', null, sysdate, 'system', sysdate);
/** ===   add by lby on 20201229 新增批：科目余额重新汇总批处理 end  === **/

