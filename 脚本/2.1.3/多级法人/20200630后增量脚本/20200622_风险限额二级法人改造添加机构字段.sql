--固定值增加机构号并且为主键
alter table RSK_ISUP_FIX_RECODE add (INSTITUTION_CODE varchar2(50) default 'FIX' not null);
alter table RSK_ISUP_FIX_RECODE drop constraint PK_RSK_ISUP_FIX_RECODE; 
alter table RSK_ISUP_FIX_RECODE add constraint PK_RSK_ISUP_FIX_RECODE PRIMARY KEY (PARA_CODE, VALID_DATE, INSTITUTION_CODE);

--指标增加机构号  
alter table RSK_ISUP_INDICATOR add (INSTITUTION_CODE varchar2(50));

--组合包增加机构号 
alter table RSK_PORTFOLIO_PACKAGE add (INSTITUTION_CODE varchar2(50));

--组合自动规则信息增加机构号 
alter table RSK_RULE_INFO add (INSTITUTION_CODE varchar2(50));

--增加PARA_TYPE字段分
ALTER TABLE RSK_ISUP_INDICATOR_PARA ADD ( PARA_TYPE VARCHAR2(16));