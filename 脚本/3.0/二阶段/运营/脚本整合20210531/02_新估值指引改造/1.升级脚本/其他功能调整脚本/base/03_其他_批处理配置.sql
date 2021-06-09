/** modify by yanya on 20210111 批处理任务组配置数据初始化 **/
delete from SYS_BATCH_GRP where seq_no in ('6f8803b3aa6fbaffe050007f01003cad','71d2d76a4f5d2ff2e050007f01000e70',
	'71d2edea97deace9e050007f01001581','86c8dff4efe26cd6e053d246a8c009bf','9eca09k0z3a044e2b39d467c12123cz4');
insert into SYS_BATCH_GRP (SEQ_NO, SYSTEM_CODE, BATCH_NAME, DETAIL, PRE_CONDITION, PRE_BATCH_GROUP, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 values ('6f8803b3aa6fbaffe050007f01003cad','VAL','默认批处理组','默认的批处理分组','','','system','',sysDate, 'system',sysDate);
insert into SYS_BATCH_GRP (SEQ_NO, SYSTEM_CODE, BATCH_NAME, DETAIL, PRE_CONDITION, PRE_BATCH_GROUP, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 values ('71d2d76a4f5d2ff2e050007f01000e70','VAL','账务明细删除任务组','账务明细删除任务','','','system','',sysDate, 'system',sysDate);
insert into SYS_BATCH_GRP (SEQ_NO, SYSTEM_CODE, BATCH_NAME, DETAIL, PRE_CONDITION, PRE_BATCH_GROUP, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 values ('71d2edea97deace9e050007f01001581','VAL','前置批处理组','前置的批处理分组','','','system','',sysDate, 'system',sysDate);
insert into SYS_BATCH_GRP (SEQ_NO, SYSTEM_CODE, BATCH_NAME, DETAIL, PRE_CONDITION, PRE_BATCH_GROUP, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 values ('86c8dff4efe26cd6e053d246a8c009bf','VAL','银行账批处理任务组','银行账批处理任务组','','','system','',sysDate, 'system',sysDate);
insert into SYS_BATCH_GRP (SEQ_NO, SYSTEM_CODE, BATCH_NAME, DETAIL, PRE_CONDITION, PRE_BATCH_GROUP, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
 values ('9eca09k0z3a044e2b39d467c12123cz4','VAL','后置批处理任务组','后置批处理任务组','','','system','',sysDate, 'system',sysDate);
