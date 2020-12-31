



----------------   TISHKBS-980
------ CLONE -  代办事项部分交易必须复核后才能提交的交易

alter table SYS_WFL_BUSINESS_LIST drop column PS;
ALTER TABLE SYS_WFL_BUSINESS_LIST ADD PS VARCHAR2(50);
COMMENT ON COLUMN SYS_WFL_BUSINESS_LIST.PS IS '交易方向';

