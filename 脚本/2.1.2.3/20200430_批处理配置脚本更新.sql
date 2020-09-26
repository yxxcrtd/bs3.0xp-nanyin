/** add by 吴洁 on 20200430 账务明细发生日余额更新批处理-交易前：批执行顺序由30 改为 38 start **/

update sys_batch_task set task_pri=38 where task_id='112';

/** add by 吴洁 on 20200430 账务明细发生日余额更新批处理-交易前：批执行顺序由30 改为 38 end **/