--不卡批
update sys_prebatch_task_add set stop_flag = '00' where task_id in('88602','88604','88605','88608');
--卡批
update sys_prebatch_task_add set stop_flag = '01' where task_id in('88601','88603','88606','88607');