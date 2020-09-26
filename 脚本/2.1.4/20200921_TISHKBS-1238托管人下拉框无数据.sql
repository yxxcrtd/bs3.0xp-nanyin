-- 计划类净值型标的查询界面中，托管人下拉框无数据
delete from DATA_AUTH_MAPPER_CONFIG t where t.SQL_ID='com.joyintech.service.asset.plan.planNet.dao.FinPlanNetAssetMapper.getTrusteeId';