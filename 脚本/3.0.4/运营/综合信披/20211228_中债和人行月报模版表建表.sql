DROP TABLE PBC_REPORT_TEMPLATEDETAIL;
/***包含2-1  2-4**/
create table PBC_REPORT_TEMPLATEDETAIL
(

  propertycode VARCHAR2(50),
  propername   VARCHAR2(100),
  orderno      NUMBER,
  reporttype   VARCHAR2(20),
  codeparent VARCHAR2(20),
  proplevel    VARCHAR2(2),
  status       VARCHAR2(2)
);
-- Add comments to the table 
comment on table PBC_REPORT_TEMPLATEDETAIL
  is '月报模版表';
-- Add comments to the columns 

comment on column PBC_REPORT_TEMPLATEDETAIL.propertycode
  is '属性代码';
comment on column PBC_REPORT_TEMPLATEDETAIL.propername
  is '属性名称';
comment on column PBC_REPORT_TEMPLATEDETAIL.orderno
  is '排序';
comment on column PBC_REPORT_TEMPLATEDETAIL.reporttype
  is '报表类型';
comment on column PBC_REPORT_TEMPLATEDETAIL.codeparent
  is '上级代码';
comment on column PBC_REPORT_TEMPLATEDETAIL.proplevel
  is '等级  1-6';


DROP TABLE CHB_REPORT_TEMPLATEDETAIL;

-- Create table
create table CHB_REPORT_TEMPLATEDETAIL
(
  propertycode VARCHAR2(20),
  propername   VARCHAR2(100),
  orderno      NUMBER,
  reporttype   VARCHAR2(10),
  codeparent VARCHAR2(20),
  proplevel    VARCHAR2(2),
  status       VARCHAR2(2)
);
-- Add comments to the table 
comment on table CHB_REPORT_TEMPLATEDETAIL
  is '月报模版表';
-- Add comments to the columns 

comment on column CHB_REPORT_TEMPLATEDETAIL.propertycode
  is '属性代码';
comment on column CHB_REPORT_TEMPLATEDETAIL.propername
  is '属性名称';
comment on column CHB_REPORT_TEMPLATEDETAIL.orderno
  is '排序';
comment on column CHB_REPORT_TEMPLATEDETAIL.codeparent
  is '上级代码';
comment on column CHB_REPORT_TEMPLATEDETAIL.proplevel
  is '等级  1-4';
