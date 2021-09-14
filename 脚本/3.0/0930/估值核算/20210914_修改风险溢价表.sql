-- Add/modify columns 修改风险溢价表得非空约束
alter table MOD_RISK_PREMIUM modify grade_source null;
alter table MOD_RISK_PREMIUM modify grade_result null;
alter table MOD_RISK_PREMIUM modify SPREAD null;
