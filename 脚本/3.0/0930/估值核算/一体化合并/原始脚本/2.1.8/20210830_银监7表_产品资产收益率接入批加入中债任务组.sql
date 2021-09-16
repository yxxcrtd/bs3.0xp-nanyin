delete from sys_batch_job where batch_seq_no='rep' and task_id in('4100','4101');
insert into sys_batch_job values('rep','4100','unistart','',date'2020-03-10','unistart',date'2020-03-10');
insert into sys_batch_job values('rep','4101','unistart','',date'2020-03-10','unistart',date'2020-03-10');