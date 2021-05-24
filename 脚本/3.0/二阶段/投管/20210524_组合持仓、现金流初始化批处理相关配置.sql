--删除 组合持仓、现金流初始化批处理 批处理任务组任务关联 

delete from SYS_BATCH_JOB
 where task_id in (select s.task_id
                     from SYS_BATCH_TASK s
                    where s.bean_name = 'ptlSecAndCashInitTask');
					
----删除 组合持仓、现金流初始化批处理					
delete from SYS_BATCH_TASK where bean_name = 'ptlSecAndCashInitTask';