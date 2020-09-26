------------------组合基本信息表更改启动风险字段默认值为Y---------------
alter table PTL_PORTFOLIO modify RISK_ENABLED default 'Y';
UPDATE PTL_PORTFOLIO SET RISK_ENABLED = 'Y';