delete from sys_batch_job where batch_seq_no='inv3' and task_id in('10018','10019');
insert into sys_batch_job values('inv3','10018','unistart','',date'2020-03-10','unistart',date'2020-03-10');
insert into sys_batch_job values('inv3','10019','unistart','',date'2020-03-10','unistart',date'2020-03-10');
commit;