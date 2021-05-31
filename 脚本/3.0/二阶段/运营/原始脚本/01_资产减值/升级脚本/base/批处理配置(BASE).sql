
--计划利率型资产：增加投资目的

---批处理任务
delete from SYS_BATCH_TASK where task_id='527';
insert into SYS_BATCH_TASK (task_id, system_code, task_name, ftask_id, bean_name, task_pri, create_user, create_time, update_user, update_time, create_dept) values ('527','VAL','计划类资产公允价值变动-账务明细批处理','','deltRightFairValeAlterationsTask','70','system',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'), '');

---批处理分组明细
delete from SYS_BATCH_JOB where task_id='527';
insert into SYS_BATCH_JOB values ('6f8803b3aa6fbaffe050007f01003cad','527','system','',to_date('2020/10/10', 'yyyy/mm/dd'), 'system',to_date('2020/10/10', 'yyyy/mm/dd'));

delete from SYS_BATCH_TASK t where t.TASK_ID = '203';
Insert into SYS_BATCH_TASK (TASK_ID,SYSTEM_CODE,TASK_NAME,FTASK_ID,BEAN_NAME,TASK_PRI,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('203','VAL','资产减值计提批处理',null,'assetImpairmentProvisionTask',75,'system',null,sysdate,'system',sysdate);
delete from SYS_BATCH_JOB t where t.TASK_ID = '203';
Insert into SYS_BATCH_JOB (BATCH_SEQ_NO,TASK_ID,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('6f8803b3aa6fbaffe050007f01003cad','203','system',null,sysdate,'system',sysdate);
