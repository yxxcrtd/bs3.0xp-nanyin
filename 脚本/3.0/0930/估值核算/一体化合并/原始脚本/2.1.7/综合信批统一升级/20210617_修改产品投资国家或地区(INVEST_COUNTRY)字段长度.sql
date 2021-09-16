--20210617_修改产品投资国家或地区(INVEST_COUNTRY)字段长度
alter table CHB_PRD_DECLARE_SUB_TMP modify (INVEST_COUNTRY varchar(600));
alter table CHB_PRD_DECLARE_SUB_HIS modify (INVEST_COUNTRY varchar(600));
alter table CHB_PRD_DECLARE_SUB modify (INVEST_COUNTRY varchar(600));
alter table CHB_PRD_DECLARE_INFO modify (INVEST_COUNTRY varchar(600));
alter table CHB_PRD_ORG_ISSUE_SUB modify (INVEST_COUNTRY varchar(600));
