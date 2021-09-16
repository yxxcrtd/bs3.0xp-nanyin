-- Add comments to the table
comment on table MOD_BATCH_LOGS
  is '模型计算日志表';
-- Add comments to the columns
comment on column MOD_BATCH_LOGS.batch_exec_seqno
  is '批处理对应序号，对应SYS_BATCHGRP_EXEC_INFO表的SEQ_NO字段';
