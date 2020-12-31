DROP TABLE REP_BATCH_CONFIG;
-- CREATE TABLE  报表生成配置表
CREATE TABLE REP_BATCH_CONFIG (
	REP_CODE VARCHAR2(50) NOT NULL,
	REP_NAME VARCHAR2(50),
	IS_RUN VARCHAR2(2) DEFAULT 'Y',
	REMARK VARCHAR2(200),
	CREATE_USER VARCHAR2(20) DEFAULT 'system' NOT NULL ,
	CREATE_DEPT VARCHAR2(32),
	CREATE_TIME TIMESTAMP DEFAULT SYSDATE,
	UPDATE_USER VARCHAR2(20) DEFAULT 'system' NOT NULL ,
	UPDATE_TIME TIMESTAMP DEFAULT SYSDATE,
	PRIMARY KEY (REP_CODE)
);
 
-- Add comments to the table 
comment on table REP_BATCH_CONFIG
  is '报表生成配置表';
-- Add comments to the columns 
comment on column REP_BATCH_CONFIG.REP_CODE
  is '报表CODE ';
comment on column REP_BATCH_CONFIG.REP_NAME
  is '报表NAME';
comment on column REP_BATCH_CONFIG.IS_RUN
  is '是否生成';
comment on column REP_BATCH_CONFIG.REMARK
  is '备注';
comment on column REP_BATCH_CONFIG.CREATE_USER
  is '创建人';
comment on column REP_BATCH_CONFIG.CREATE_DEPT
  is '创建部门';
comment on column REP_BATCH_CONFIG.CREATE_TIME
  is '创建时间';
comment on column REP_BATCH_CONFIG.UPDATE_USER
  is '最后更新人';
comment on column REP_BATCH_CONFIG.UPDATE_TIME
  is '最后更新时间';

	