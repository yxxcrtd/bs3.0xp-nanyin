--机构表增加机构简称

alter table SYS_INSTITUTION drop column institution_abbr;
alter table SYS_INSTITUTION add (institution_abbr  varchar2(200));