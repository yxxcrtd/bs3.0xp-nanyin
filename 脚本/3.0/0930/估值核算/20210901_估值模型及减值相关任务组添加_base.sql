/** 添加估值模型及减值任务组 add by yanya on 20210901**/
delete SYS_BATCH_GRP where SEQ_NO in ('valmodel000000000000000000000001','valloss0000000000000000000000001');
insert into SYS_BATCH_GRP values ('valmodel000000000000000000000001','VAL','估值模型任务组','估值减值模型任务组','','','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'),'01');
insert into SYS_BATCH_GRP values ('valloss0000000000000000000000001','VAL','估值减值任务组','估值减值任务组','','','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'),'01');

delete from SYS_BATCH_JOB where BATCH_SEQ_NO = 'valmodel000000000000000000000001' 
	and TASK_ID in ('2021','2023','2024','2022');
delete from SYS_BATCH_JOB where BATCH_SEQ_NO = 'valloss0000000000000000000000001' 
	and TASK_ID in ('2043','2045','2044','2041','2026','2042');
insert into SYS_BATCH_JOB values ('valmodel000000000000000000000001','2021','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
insert into SYS_BATCH_JOB values ('valmodel000000000000000000000001','2023','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
--insert into SYS_BATCH_JOB values ('valmodel000000000000000000000001','2024','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
insert into SYS_BATCH_JOB values ('valmodel000000000000000000000001','2022','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
insert into SYS_BATCH_JOB values ('valloss0000000000000000000000001','2043','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
insert into SYS_BATCH_JOB values ('valloss0000000000000000000000001','2045','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
insert into SYS_BATCH_JOB values ('valloss0000000000000000000000001','2044','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
insert into SYS_BATCH_JOB values ('valloss0000000000000000000000001','2041','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
insert into SYS_BATCH_JOB values ('valloss0000000000000000000000001','2026','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));
insert into SYS_BATCH_JOB values ('valloss0000000000000000000000001','2042','system','',to_date('2021/9/1', 'yyyy/mm/dd'), 'system',to_date('2021/9/1', 'yyyy/mm/dd'));

