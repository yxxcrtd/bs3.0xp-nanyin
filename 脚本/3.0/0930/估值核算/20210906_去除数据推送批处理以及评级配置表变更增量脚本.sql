--删除数据推送批处理
DELETE FROM sys_batch_task t WHERE T.TASK_ID IN ('2024','2026');

--资产评级配置表 字段：GRADE_ORG_ID 设为可为空
alter table MOD_ASSET_GRADE_CONFIG modify (GRADE_ORG_ID VARCHAR2(50) NULL);

--特殊资产评级配置表 字段：GRADE_ORG_ID 设为可为空
alter table MOD_ASSET_GRADE_SPECIAL_CONFIG modify (GRADE_ORG_ID VARCHAR2(50) NULL);