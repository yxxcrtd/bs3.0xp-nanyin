-- 浙江农信估值 行为日志报错
UPDATE sys_page SET REMARK  = '指标参数查看' WHERE page_id = 'RskSupervisionIndicatorParam004' AND
remark IS NULL ;
UPDATE sys_page SET REMARK  = '负面信息详情' WHERE page_id = 'AgencyNegativeInf002'  AND
remark IS NULL ;

---- 查下 SELECT * FROM sys_page WHERE  REMARK IS NULL;  如果仍然有remark为null的 修改后 重新刷新缓存

