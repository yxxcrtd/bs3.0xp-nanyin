--1,系统参数增加 机构代码字段
ALTER TABLE SYS_PARA DROP COLUMN INSTITUTION_CODE; 
ALTER TABLE SYS_PARA ADD INSTITUTION_CODE VARCHAR2(50);
COMMENT ON COLUMN SYS_PARA.INSTITUTION_CODE IS '机构';

ALTER TABLE SYS_PARA DROP CONSTRAINT PK_SYS_PARA;
drop index PK_SYS_PARA;
drop index UI_SYS_PARA; 
CREATE UNIQUE index UI_SYS_PARA on SYS_PARA(SYSTEM_CODE, PARA_CODE, INSTITUTION_CODE);

-- 2，角色表增加字段：机构类型、管理权限
-- Add/modify columns 
ALTER TABLE SYS_ROLE DROP COLUMN LIANSHE_INSTITUTION_TYPE;
ALTER TABLE SYS_ROLE DROP COLUMN IS_ADMIN;
alter table SYS_ROLE add LIANSHE_INSTITUTION_TYPE VARCHAR2(20);
alter table SYS_ROLE add IS_ADMIN VARCHAR2(20);
-- Add comments to the columns 
comment on column SYS_ROLE.LIANSHE_INSTITUTION_TYPE is '机构类型';
comment on column SYS_ROLE.IS_ADMIN is '管理权限';

--3,字典列表增加 机构代码字段
ALTER TABLE SYS_DATADICT_LIST DROP COLUMN INSTITUTION_CODE;
ALTER TABLE SYS_DATADICT_LIST ADD INSTITUTION_CODE VARCHAR2(50);
COMMENT ON COLUMN SYS_DATADICT_LIST.INSTITUTION_CODE IS '所属机构';
ALTER TABLE SYS_DATADICT_LIST DROP CONSTRAINT PK_SYS_DATADICT_LIST ;
drop index PK_SYS_DATADICT_LIST;
drop index UI_SYS_DATADICT_LIST;
CREATE UNIQUE index UI_SYS_DATADICT_LIST ON SYS_DATADICT_LIST(DICT_CODE, ITEM_CODE, INSTITUTION_CODE);

--4,角色机构关联表
-- Create table
drop table SYS_ROLE_INST;
create table SYS_ROLE_INST
(
  UU_ID            VARCHAR2(32) not null,
  ROLE_ID          VARCHAR2(32) not null,
  INSTITUTION_CODE VARCHAR2(32) not null,
  CREATE_USER      VARCHAR2(20) not null,
  CREATE_DEPT      VARCHAR2(32),
  CREATE_TIME      TIMESTAMP(6) not null,
  UPDATE_USER      VARCHAR2(20) not null,
  UPDATE_TIME      TIMESTAMP(6) not null
);
-- Add comments to the table 
comment on table SYS_ROLE_INST
  is '角色机构关联表';
-- Add comments to the columns 
comment on column SYS_ROLE_INST.UU_ID
  is '主键';
comment on column SYS_ROLE_INST.ROLE_ID
  is '角色代码';
comment on column SYS_ROLE_INST.INSTITUTION_CODE
  is '机构代码';
comment on column SYS_ROLE_INST.CREATE_USER
  is '创建人';
comment on column SYS_ROLE_INST.CREATE_DEPT
  is '创建部门';
comment on column SYS_ROLE_INST.CREATE_TIME
  is '创建时间';
comment on column SYS_ROLE_INST.UPDATE_USER
  is '更新人';
comment on column SYS_ROLE_INST.UPDATE_TIME
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_ROLE_INST
  add constraint PK_SYS_ROLE_INST primary key (UU_ID);
  
--5，增加：联社机构类型（LIANSHE_INSTITUTION_TYPE）：01省级机构/02市级机构/03成员行社机构
delete from SYS_DATADICT where DICT_CODE ='LIANSHE_INSTITUTION_TYPE';
delete from SYS_DATADICT_LIST where DICT_CODE ='LIANSHE_INSTITUTION_TYPE';
INSERT INTO SYS_DATADICT (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('LIANSHE_INSTITUTION_TYPE', 'SYS', '联社机构类型', 'LIANSHE_INSTITUTION_TYPE', '02', 'system', '', sysdate, 'system', sysdate);
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME,INSTITUTION_CODE) VALUES ('LIANSHE_INSTITUTION_TYPE', '01', '省级机构', 1, '1', 'system', '', sysdate, 'system', sysdate,'');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME,INSTITUTION_CODE) VALUES ('LIANSHE_INSTITUTION_TYPE', '02', '市级机构', 2, '2', 'system', '', sysdate, 'system', sysdate,'');
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME,INSTITUTION_CODE) VALUES ('LIANSHE_INSTITUTION_TYPE', '03', '成员行社机构', 3, '3', 'system', '', sysdate, 'system', sysdate,'');

--6,增加数据字典：【管理权限】管理权限、成员行社权限
delete from sys_datadict WHERE DICT_CODE ='ADMINPOWER';
insert into sys_datadict (DICT_CODE, SYSTEM_CODE, DICT_CNNAME, DICT_ENNAME, UPDATE_FLG, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ADMINPOWER', 'SYS', '管理权限', '', '02', 'system', '', sysdate, 'system', sysdate);

delete from sys_datadict_list WHERE DICT_CODE ='ADMINPOWER';
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ADMINPOWER', '01', '管理权限', 1, '1', 'system', '', sysdate, 'system', sysdate, '');
insert into sys_datadict_list (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)
values ('ADMINPOWER', '02', '成员行社权限', 2, '0', 'system', '', sysdate, 'system', sysdate, '');


DELETE  sys_para WHERE para_code IN ('data_authority','AUTHORITY_TYPE');

 insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYS', 'data_authority', '01', '01', '数据字典(是否）', '是否启用数据权限控制', 'system', null, sysdate, 'system', sysdate);

insert into SYS_PARA (SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SYS', 'AUTHORITY_TYPE', '02', null, null, '数据权限模式（01：银行模式，02：联社模式）', 'system', null, sysdate, 'system', sysdate);


create table DATA_AUTH_NOTIFY_INST
(
    BUSINESS_ID      VARCHAR(64)  not null,
    MODEL_TYPE       VARCHAR(50)  not null,
    INSTITUTION_CODE VARCHAR(32)  not null,
    CREATE_USER      VARCHAR(20)  not null,
    CREATE_DEPT      VARCHAR(32),
    CREATE_TIME      TIMESTAMP(6) not null,
    UPDATE_USER      VARCHAR(20)  not null,
    UPDATE_TIME      TIMESTAMP(6) not null,
    constraint PK_DATA_AUTH_NOTIFY_INST
        primary key (BUSINESS_ID, MODEL_TYPE, INSTITUTION_CODE)
);

comment on table DATA_AUTH_NOTIFY_INST is '数据权限授权机构表';

comment on column DATA_AUTH_NOTIFY_INST.BUSINESS_ID is '业务编号';

comment on column DATA_AUTH_NOTIFY_INST.MODEL_TYPE is '模式';

comment on column DATA_AUTH_NOTIFY_INST.INSTITUTION_CODE is '机构代码';

comment on column DATA_AUTH_NOTIFY_INST.CREATE_USER is '创建人';

comment on column DATA_AUTH_NOTIFY_INST.CREATE_DEPT is '创建部门';

comment on column DATA_AUTH_NOTIFY_INST.CREATE_TIME is '创建时间';

comment on column DATA_AUTH_NOTIFY_INST.UPDATE_USER is '更新人';

comment on column DATA_AUTH_NOTIFY_INST.UPDATE_TIME is '更新时间';

CREATE OR REPLACE VIEW VW_DATA_AUTH_INSTITUTION AS
WITH TEMP_INSTITUTION(INSTITUTION_CODE, UPPER_INSTITUTION_CODE, CODE) AS (
        SELECT
            si.INSTITUTION_CODE, si.UPPER_INSTITUTION_CODE, si.INSTITUTION_CODE code
        FROM
            SYS_INSTITUTION si
        UNION ALL
        SELECT
            si2.INSTITUTION_CODE, si2.UPPER_INSTITUTION_CODE, b.code code
        FROM
            SYS_INSTITUTION si2, TEMP_INSTITUTION b WHERE b.INSTITUTION_CODE = si2.UPPER_INSTITUTION_CODE)          
SELECT T.INSTITUTION_CODE CODE,T.CODE INSTITUTION_CODE, T.UPPER_INSTITUTION_CODE, 'inst' CODE_TYPE FROM TEMP_INSTITUTION T
UNION ALL
SELECT S.DEPT_CODE CODE,T.CODE INSTITUTION_CODE, T.UPPER_INSTITUTION_CODE, 'dept' CODE_TYPE FROM TEMP_INSTITUTION T INNER JOIN SYS_DEPARTMENT S ON T.INSTITUTION_CODE = S.INSTITUTION_CODE;

COMMENT ON COLUMN VW_DATA_AUTH_INSTITUTION.CODE IS '子机构或者部门代码';
COMMENT ON COLUMN VW_DATA_AUTH_INSTITUTION.INSTITUTION_CODE IS '机构代码';
COMMENT ON COLUMN VW_DATA_AUTH_INSTITUTION.UPPER_INSTITUTION_CODE IS '上级机构代码（部门时，为部门所属机构的上级）';
COMMENT ON COLUMN VW_DATA_AUTH_INSTITUTION.CODE_TYPE IS 'CODE类型(inst机构，dept部门)';



-- Create table
create table DATA_AUTH_MAPPER_CONFIG
(
  sql_id           VARCHAR2(512) not null,
  filter_type      VARCHAR2(10),
  is_nest          VARCHAR2(2),
  join_column_name VARCHAR2(100),
  filter_sql       VARCHAR2(2048),
  system_code      VARCHAR2(50),
  page_id          VARCHAR2(50),
  create_user      VARCHAR2(20),
  create_dept      VARCHAR2(20),
  create_time      TIMESTAMP(6),
  update_user      VARCHAR2(20),
  update_time      TIMESTAMP(6),
  add_column       VARCHAR2(50),
  add_column_name  VARCHAR2(50)
);
-- Add comments to the table 
comment on table DATA_AUTH_MAPPER_CONFIG
  is '二级法人权限过滤mapper配置表';
-- Add comments to the columns 
comment on column DATA_AUTH_MAPPER_CONFIG.sql_id
  is '查询id';
comment on column DATA_AUTH_MAPPER_CONFIG.filter_type
  is '控制类型，1 直接控制:原sql中可以过滤 2.间接控制:过滤data_auth_notify_inst表 3.特殊控制:配置sql过滤';
comment on column DATA_AUTH_MAPPER_CONFIG.is_nest
  is '是否嵌套 01：是 02：否 ';
comment on column DATA_AUTH_MAPPER_CONFIG.join_column_name
  is '关联列名称，当filter_type为1,2时，存储关联字段id ';
comment on column DATA_AUTH_MAPPER_CONFIG.filter_sql
  is '过滤sql，当filter_type为3时，存储过滤sql ';
comment on column DATA_AUTH_MAPPER_CONFIG.system_code
  is '系统代码';
comment on column DATA_AUTH_MAPPER_CONFIG.page_id
  is '画面代码（菜单对应的入口画面）';
comment on column DATA_AUTH_MAPPER_CONFIG.create_user
  is '创建人';
comment on column DATA_AUTH_MAPPER_CONFIG.create_dept
  is '创建部门';
comment on column DATA_AUTH_MAPPER_CONFIG.create_time
  is '创建时间';
comment on column DATA_AUTH_MAPPER_CONFIG.update_user
  is '更新人';
comment on column DATA_AUTH_MAPPER_CONFIG.update_time
  is '更新时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DATA_AUTH_MAPPER_CONFIG
  add constraint DATA_AUTH_MAPPER_CONFIG_PK primary key (SQL_ID)
 ;

 
 --流程控制，提交时可选择的审核
-- Add/modify columns 
alter table SYS_WFL_BUSINESS_MODULE add INSTITUTION_CODE VARCHAR2(50) default 'ALL' not null;
alter table SYS_WFL_BUSINESS_MODULE add IS_SEND_UORG VARCHAR2(50) default '02' not null;
-- Add comments to the columns 
comment on column SYS_WFL_BUSINESS_MODULE.INSTITUTION_CODE
  is '机构代码';
comment on column SYS_WFL_BUSINESS_MODULE.IS_SEND_UORG
  is '是否允许发送给上级机构';
  
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_WFL_BUSINESS_MODULE drop constraint PK_SYS_WFL_BUSINESS_MODULE cascade;
drop index PK_SYS_WFL_BUSINESS_MODULE;
alter table SYS_WFL_BUSINESS_MODULE
  add constraint PK_SYS_WFL_BUSINESS_MODULE primary key (PAGE_ID, ELEMENT_ID, INSTITUTION_CODE);


--画面数据字典明细关联新增机构代码
-- Add/modify columns 
alter table SYS_PAGE_DICTLIST add INSTITUTION_CODE VARCHAR2(50);
-- Add comments to the columns 
comment on column SYS_PAGE_DICTLIST.INSTITUTION_CODE
  is '所属机构';
  
-- Create/Recreate primary, unique and foreign key constraints 
alter table SYS_PAGE_DICTLIST drop constraint PK_SYS_PAGE_DICTLIST cascade;
drop index PK_SYS_PAGE_DICTLIST;

create unique index PK_SYS_PAGE_DICTLIST on SYS_PAGE_DICTLIST (PAGE_ID, DICT_ID, DICT_ITEM_VAL, SUB_PAGE_ID, INSTITUTION_CODE);


-----20200609 增加表字段
----------增加表字段 data_type
ALTER TABLE DATA_AUTH_MAPPER_CONFIG ADD ( data_type VARCHAR2(16) );

comment on column DATA_AUTH_MAPPER_CONFIG.data_type
  is '数据类型 '; 
  
  
 ----------增加表字段 data_type
ALTER TABLE DATA_AUTH_NOTIFY_INST ADD ( belong_institution_code VARCHAR2(50) );
ALTER TABLE DATA_AUTH_NOTIFY_INST ADD ( data_type VARCHAR2(16)  NOT NULL );


comment on column DATA_AUTH_NOTIFY_INST.data_type
  is '数据类型 ';  
comment on column DATA_AUTH_NOTIFY_INST.belong_institution_code
  is '所属机构 '; 

ALTER TABLE DATA_AUTH_NOTIFY_INST DROP CONSTRAINT PK_DATA_AUTH_NOTIFY_INST;
drop index PK_DATA_AUTH_NOTIFY_INST;
ALTER TABLE DATA_AUTH_NOTIFY_INST ADD CONSTRAINT PK_DATA_AUTH_NOTIFY_INST primary key(BUSINESS_ID, MODEL_TYPE, INSTITUTION_CODE,DATA_TYPE);

-- 20200613 caiqun 多级法人改造，存续期（公共资产）增加字段【机构代码】
--ALTER TABLE WFL_TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN INSTITUTION_CODE;
ALTER TABLE WFL_TRD_FIN_PRODUCT_DEAL_TOTAL ADD INSTITUTION_CODE  varchar2(32);
comment on column WFL_TRD_FIN_PRODUCT_DEAL_TOTAL.INSTITUTION_CODE is '机构代码';

--ALTER TABLE TRD_FIN_PRODUCT_DEAL_TOTAL DROP COLUMN INSTITUTION_CODE;
ALTER TABLE TRD_FIN_PRODUCT_DEAL_TOTAL ADD INSTITUTION_CODE  varchar2(32);
comment on column TRD_FIN_PRODUCT_DEAL_TOTAL.INSTITUTION_CODE is '机构代码';

--资金账户表新增流水号   add by caojin  2020/6/15
-- Add/modify columns 
alter table STL_CAPACCT_INFO add SEQ_NO VARCHAR2(32);
-- Add comments to the columns 
comment on column STL_CAPACCT_INFO.SEQ_NO is '流水号';

--股票标的新增按钮命名错误   add by kyf  2020/6/18 start
update sys_page_element t
   set t.element_id = 'FinStockInfo001_btn_create'
 where t.element_id = 'FinStockInfo001_btn_add';
--股票标的新增按钮命名错误   add by kyf  2020/6/18 end

--系统参数删除按钮   add by kyf  2020/6/18 start
delete from sys_page_element where PAGE_ID='SysParaSet001' and ELEMENT_ID='SysParaSet001_TBLROW_delete';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysParaSet001', 'SysParaSet001_TBLROW_delete', '01', '01', '', '删除', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
--系统参数删除按钮   add by kyf  2020/6/18 end


delete from sys_page where PAGE_ID='SysDataDict005';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('SysDataDict005', 'SYS', '/system/datadict/SysDataDict005', 'system/datadict/SysDataDict005', 'SYSTEM_001_011', '', '', '02', '02', '数据字典明细查看一览', 'system', '', sysdate, 'system', sysdate);

--主动提醒，数据修改 add by liupengfei 2020-06-18 18:00 start
DELETE FROM SYS_PARA WHERE PARA_CODE='R9S1_DAYS';
INSERT INTO SYS_PARA(SYSTEM_CODE, PARA_CODE, PARAM_VALUE, DATA_TYPE, DATA_SCOPE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('SYS', 'R9S1_DAYS', '30', '01', NULL, '主动提醒数据修改提醒天数', 'system', NULL, TIMESTAMP '2019-09-06 16:11:48.000000', 'system', TIMESTAMP '2019-09-06 16:11:48.000000', NULL);
DELETE FROM SYS_DATADICT_LIST_LINK WHERE DICT_CODE='REMINDER_TYPE_SMALL' and ITEM_CODE='R9S1' and LINKED_DICT_CODE='REMINDER_TYPE_BIG' and LINKED_ITEM_CODE='R9';
INSERT INTO SYS_DATADICT_LIST_LINK(DICT_CODE, ITEM_CODE, LINKED_DICT_CODE, LINKED_ITEM_CODE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('REMINDER_TYPE_SMALL', 'R9S1', 'REMINDER_TYPE_BIG', 'R9', 'system', NULL, TIMESTAMP '2020-02-24 16:55:12.000000', 'system', TIMESTAMP '2020-02-24 16:55:12.000000');
--主动提醒，数据修改 add by liupengfei 2020-06-18 18:00 end



--按钮脚本  add by kyf  2020/6/18 start
delete from sys_page_element where PAGE_ID='TrdConBond001' and ELEMENT_ID='TrdConBond001_btn_add';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdConBond001', 'TrdConBond001_btn_add', '01', '01', '', '新建', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');

update sys_page_element t set t.is_control='01'
where (t.page_id='TrdOptionTrade001') and t.element_id='TrdOptionTrade001_btn_revoke';
update sys_page_element t set t.is_control='01'
where (t.page_id='TrdOptionTrade001') and t.element_id='TrdOptionTrade001_btn_add';

delete from sys_page_element where PAGE_ID='TrdSecOverdueDuration001' and ELEMENT_ID='jixiyaosuweihu_btn';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdSecOverdueDuration001', 'TrdSecOverdueDuration001_btn_intst', '01', '01', '', '计息要素维护', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
delete from sys_page_element where PAGE_ID='TrdSecOverdueDuration001' and ELEMENT_ID='TrdSecOverdueDuration001_btn_huanbenshouxi';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdSecOverdueDuration001', 'TrdSecOverdueDuration001_btn_huanbenshouxi', '01', '01', '', '还本收息', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
delete from sys_page_element where PAGE_ID='TrdSecOverdueDuration001' and ELEMENT_ID='TrdSecOverdueDuration001_btn_zhuanrang';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdSecOverdueDuration001', 'TrdSecOverdueDuration001_btn_zhuanrang', '01', '01', '', '转让', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');

delete from sys_page_element where PAGE_ID='TrdSecOverTransfer001' and ELEMENT_ID='TrdSecOverTransfer001_btn_revoke';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdSecOverTransfer001', 'TrdSecOverTransfer001_btn_revoke', '01', '01', '', '撤销', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
--按钮脚本   add by kyf  2020/6/18 end

--按钮脚本  add by kyf  2020/6/19 start
delete from sys_page_element where PAGE_ID='TrdConBond001' and ELEMENT_ID='TrdConBond001_btn_search';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdConBond001', 'TrdConBond001_btn_search', '01', '01', '', '查询', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');

delete from sys_page_element where PAGE_ID='FinOptionTarget001' and ELEMENT_ID='FinOptionTarget001_btn_add';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinOptionTarget001', 'FinOptionTarget001_btn_add', '01', '01', '', '新建', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');

delete from sys_page_element where PAGE_ID='FinStockInfo001' and ELEMENT_ID='FinStockInfo001_btn_edit';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinStockInfo001', 'FinStockInfo001_btn_edit', '01', '01', '', '编辑', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');

delete from sys_page_element where PAGE_ID='FinStockInfo001' and ELEMENT_ID='FinStockInfo001_btn_delete';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinStockInfo001', 'FinStockInfo001_btn_delete', '01', '01', '', '删除', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');


delete from sys_page_element where PAGE_ID='TrdFundDuration001' and ELEMENT_ID='TrdFundObversion001_btn_add';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdFundDuration001', 'TrdFundObversion001_btn_add', '01', '01', '', '折算-新建', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');

delete from sys_page_element where PAGE_ID='FinStockInfo001' and ELEMENT_ID='TrdFundObversion001_btn_revoke';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdFundDuration001', 'TrdFundObversion001_btn_revoke', '01', '01', '', '折算-撤销', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');


--按钮脚本  add by kyf  2020/6/19 end

--产品组配置新增是否手动生成脚本 add by shijingjing 20200619 start
-- Add/modify columns 
alter table SYS_BATCH_PRD_GRP add is_handwork VARCHAR2(32);
-- Add comments to the columns 
comment on column SYS_BATCH_PRD_GRP.is_handwork 
  is '账套组是否手工生成：01:是,02:否';
--产品组配置新增是否手动生成脚本 add by shijingjing 20200619 end

--批处理组配置新增是否手动生成脚本 add by shijingjing 20200619 start
-- Add/modify columns 
alter table SYS_BATCH_GRP add IS_HANDWORK VARCHAR2(32);
-- Add comments to the columns 
comment on column SYS_BATCH_GRP.IS_HANDWORK
  is '批处组是否手工生成：01:是,02:否';
--批处理组配置新增是否手动生成脚本 add by shijingjing 20200619 end


-- 20200619 caiqun 多级法人改造，存续期（公共资产）增加字段【机构代码】 start
ALTER TABLE sys_activereminder_info ADD INSTITUTION_CODE  varchar2(32);
comment on column sys_activereminder_info.INSTITUTION_CODE is '机构代码';
-- 20200619 caiqun 多级法人改造，存续期（公共资产）增加字段【机构代码】 end

--按钮脚本add by caojin 20200619 start
/*银行间交易费用设置*/
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'MstBankTranFeeManage001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstBankTranFeeManage001','MstBankTranFeeManage001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstBankTranFeeManage001','MstBankTranFeeManage001_btn_clear','01','02',null,'重置','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstBankTranFeeManage001','MstBankTranFeeManage001_btn_delete','01','01',null,'删除','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstBankTranFeeManage001','MstBankTranFeeManage001_btn_edit','01','01',null,'修改','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstBankTranFeeManage001','MstBankTranFeeManage001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstBankTranFeeManage001','MstBankTranFeeManage001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
/*交易所费用设置*/
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'MstCommissionSet001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstCommissionSet001','MstCommissionSet001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstCommissionSet001','MstCommissionSet001_btn_clear','01','02',null,'重置','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstCommissionSet001','MstCommissionSet001_btn_delete','01','01',null,'删除','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstCommissionSet001','MstCommissionSet001_btn_edit','01','01',null,'修改','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstCommissionSet001','MstCommissionSet001_btn_export','01','01',null,'导出','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('MstCommissionSet001','MstCommissionSet001_btn_search','01','01',null,'查询','system',null,sysdate,'system',sysdate);
/*DVP指令管理*/
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'DvpOrdersManagement001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) 
values ('DvpOrdersManagement001','DvpOrdersManagement001_btn_creatOrder','01','01',null,'指令生成','system',null,sysdate,'system',sysdate);

/*指令新建*/
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'OrderNewBuild001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('OrderNewBuild001','OrderNewBuild001_btn_add','01','01',null,'新建','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('OrderNewBuild001','OrderNewBuild001_btn_revoke','01','01',null,'撤销','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('OrderNewBuild001','OrderNewBuild001_btn_resend','01','01',null,'指令发送','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('OrderNewBuild001','OrderNewBuild001_btn_verify','01','01',null,'指令验证','system',null,sysdate,'system',sysdate);

/*指令接口管理*/
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'StlOrderManagement001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('StlOrderManagement001','DvpOrderManagement001_btn_resend','01','01',null,'指令发送','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('StlOrderManagement001','DvpOrderManagement001_btn_refresh','01','01',null,'指令查证','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('StlOrderManagement001','DvpOrderManagement001_btn_invalid','01','01',null,'指令作废','system',null,sysdate,'system',sysdate);

--按钮脚本add by caojin 20200619 end

/* add by songyawei 2020/06/19 start*/
delete from sys_page_element where PAGE_ID='TrdDepositInvestTrade001' and ELEMENT_ID='TrdDepositInvestTrade001_btn_protfolio';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdDepositInvestTrade001', 'TrdDepositInvestTrade001_btn_protfolio', '01', '01', '', '多组合交易', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
/* add by songyawei 2020/06/19 end */

--主动提醒，数据修改 add by liupengfei 2020-06-20 17:00 start
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE='REMINDER_TYPE_BIG' AND ITEM_CODE='R9';
INSERT INTO SYS_DATADICT_LIST(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('REMINDER_TYPE_BIG', 'R9', '公共数据变更', 9, '0', 'system', NULL, TIMESTAMP '2020-06-08 00:00:00.000000', 'system', TIMESTAMP '2020-06-08 00:00:00.000000', NULL);
DELETE FROM SYS_DATADICT_LIST WHERE DICT_CODE='REMINDER_TYPE_SMALL' AND ITEM_CODE='R9S1';
INSERT INTO SYS_DATADICT_LIST(DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('REMINDER_TYPE_SMALL', 'R9S1', '数据修改', 1, '0', 'system', NULL, TIMESTAMP '2020-06-08 00:00:00.000000', 'system', TIMESTAMP '2020-06-08 00:00:00.000000', NULL);
--主动提醒，数据修改 add by liupengfei 2020-06-20 17:00 end


--按钮脚本  add by kyf  2020/6/20 end
delete from sys_page_element where PAGE_ID='TrdFundInvestTrade001' and ELEMENT_ID='TrdFundInvestTrade001_TBLROW_edit';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdFundInvestTrade001', 'TrdFundInvestTrade001_TBLROW_edit', '01', '01', '', '确认', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
--按钮脚本  add by kyf  2020/6/20 end

--按钮脚本add by caojin 20200620 start
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PtlActivityPeriod001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PtlActivityPeriod001','PtlActivityPeriod001_btn_add','01','01',null,'新建','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PtlActivityPeriod001','PtlActivityPeriod001_btn_delete','01','01',null,'撤销','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PtlActivityPeriod001','PtlActivityPeriod001_btn_export','01','01',null,'导出','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PtlActivityPeriod001','PtlActivityPeriod001_btn_reset','01','02',null,'重置','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PtlActivityPeriod001','PtlActivityPeriod001_btn_search','01','01',null,'查询','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
--按钮脚本add by caojin 20200620 end

--授信额度管理、资产系列增加机构代码字段 add by jingqiongqiong 20200622 start
--机构授信、资产授信
alter table CREDIT_LINES add INSTITUTION_CODE VARCHAR2(50);
comment on column CREDIT_LINES.INSTITUTION_CODE is '机构代码';
--资产系列管理
alter table CREDIT_ASSETPOOL add INSTITUTION_CODE VARCHAR2(50);
comment on column CREDIT_ASSETPOOL.INSTITUTION_CODE is '机构代码';
--授信额度管理、资产系列增加机构代码字段 add by jingqiongqiong 20200622 end

--按钮脚本  add by kyf  2020/6/22 end
delete from sys_page_element where PAGE_ID='FinProductDurDeal001' and ELEMENT_ID='TrdFundInvestTrade001_TBLROW_dealRevoke';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('FinProductDurDeal001', 'TrdFundInvestTrade001_TBLROW_dealRevoke', '01', '01', '', '撤销', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');

delete from sys_page_element where PAGE_ID='PeriodicManagement001' and ELEMENT_ID='PeriodicManagement001_btn_edit';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('PeriodicManagement001', 'PeriodicManagement001_btn_edit', '01', '01', '', '编辑', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
--按钮脚本  add by kyf  2020/6/22 end

--默认批处理组脚本  add by shijingjing 2020/6/22 start
update sys_batch_grp t set t.is_handwork='01' where t.seq_no in ('cd1724f1ed21472b8d6c2c5e9bcf6599','rep','71d2d76a4f5d2ff2e050007f01000e70',
'dis2','dis1','inv2','inv3','dateChange','71d2edea97deace9e050007f01001581','unistart','6f8803b3aa6fbaffe050007f01003cad','inv1','val');
--默认批处理组脚本  add by shijingjing 2020/6/22 end

--按钮脚本add by jingqiongqiong 20200622 start
--银证转账
DELETE FROM SYS_PAGE_ELEMENT WHERE SYS_PAGE_ELEMENT.PAGE_ID ='PtlFundAllocation001'
 AND SYS_PAGE_ELEMENT.ELEMENT_ID IN('PtlFundAllocation001_btn_add','PtlFundAllocation001_btn_export','PtlFundAllocation001_btn_reset','PtlFundAllocation001_btn_revoke','PtlFundAllocation001_btn_search');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation001', 'PtlFundAllocation001_btn_add', '01', '01', null, '新建', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation001', 'PtlFundAllocation001_btn_revoke', '01', '01', null, '撤销', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation001', 'PtlFundAllocation001_btn_reset', '01', '02', null, '重置', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation001', 'PtlFundAllocation001_btn_search', '01', '01', null, '查询', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlFundAllocation001', 'PtlFundAllocation001_btn_export', '01', '01', null, '导出', 'system', null, SYSDATE, 'system', SYSDATE);


-- DVP资金调拨
DELETE FROM SYS_PAGE_ELEMENT WHERE SYS_PAGE_ELEMENT.PAGE_ID ='PtlDvpFundAllocation001'
 AND SYS_PAGE_ELEMENT.ELEMENT_ID IN('PtlDvpFundAllocation001_btn_add','PtlDvpFundAllocation001_btn_revoke','PtlDvpFundAllocation001_btn_reset','PtlDvpFundAllocation001_btn_search','PtlDvpFundAllocation001_btn_export');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlDvpFundAllocation001', 'PtlDvpFundAllocation001_btn_add', '01', '01', null, '新建', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlDvpFundAllocation001', 'PtlDvpFundAllocation001_btn_revoke', '01', '01', null, '撤销', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlDvpFundAllocation001', 'PtlDvpFundAllocation001_btn_reset', '01', '02', null, '重置', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlDvpFundAllocation001', 'PtlDvpFundAllocation001_btn_search', '01', '01', null, '查询', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlDvpFundAllocation001', 'PtlDvpFundAllocation001_btn_export', '01', '01', null, '导出', 'system', null, SYSDATE, 'system', SYSDATE);
	
--通道资金划拨管理
DELETE FROM SYS_PAGE_ELEMENT WHERE SYS_PAGE_ELEMENT.PAGE_ID ='PtlChlFundAllocation001'
 AND SYS_PAGE_ELEMENT.ELEMENT_ID IN('PtlChlFundAllocation001_btn_add','PtlChlFundAllocation001_btn_revoke','PtlChlFundAllocation001_btn_reset','PtlChlFundAllocation001_btn_search','PtlChlFundAllocation001_btn_export');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlChlFundAllocation001', 'PtlChlFundAllocation001_btn_add', '01', '01', null, '新建', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlChlFundAllocation001', 'PtlChlFundAllocation001_btn_revoke', '01', '01', null, '撤销', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlChlFundAllocation001', 'PtlChlFundAllocation001_btn_reset', '01', '02', null, '重置', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlChlFundAllocation001', 'PtlChlFundAllocation001_btn_search', '01', '01', null, '查询', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlChlFundAllocation001', 'PtlChlFundAllocation001_btn_export', '01', '01', null, '导出', 'system', null, SYSDATE, 'system', SYSDATE);


--实时资金头寸查询
DELETE FROM SYS_PAGE_ELEMENT WHERE SYS_PAGE_ELEMENT.PAGE_ID ='ActualTimeCapital001'
 AND SYS_PAGE_ELEMENT.ELEMENT_ID IN('ActualTimeAsset001_btn_adjamt','ActualTimeCapital001_btn_search','ActualTimeCapital001_btn_export');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ActualTimeCapital001', 'ActualTimeAsset001_btn_adjamt', '01', '01', null, '手工调整', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ActualTimeCapital001', 'ActualTimeCapital001_btn_search', '01', '01', null, '查询', 'system', null, SYSDATE, 'system', SYSDATE);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ActualTimeCapital001', 'ActualTimeCapital001_btn_export', '01', '01', null, '导出', 'system', null, SYSDATE, 'system', SYSDATE);
--按钮脚本add by jingqiongqiong 20200622 end



/* add by songyawei 表RSK_ACCESS_LIST_DETAIL添加所属机构  20200622 start */
ALTER TABLE RSK_ACCESS_LIST_DETAIL ADD INSTITUTION_CODE VARCHAR2(32);
COMMENT ON COLUMN RSK_ACCESS_LIST_DETAIL.INSTITUTION_CODE IS '所属机构';
/* add by songyawei 表RSK_ACCESS_LIST_DETAIL添加所属机构 20200622 end */

/* add by songyawei 表WFL_RSK_ACCESS_LIST_DETAIL添加所属机构 20200622 start */
ALTER TABLE WFL_RSK_ACCESS_LIST_DETAIL ADD INSTITUTION_CODE VARCHAR2(32);
COMMENT ON COLUMN WFL_RSK_ACCESS_LIST_DETAIL.INSTITUTION_CODE IS '所属机构';
/* add by songyawei 表WFL_RSK_ACCESS_LIST_DETAIL添加所属机构  20200622 end */

/* add by songyawei 表RSK_ACCESS_LIST_LOG添加所属机构 20200622 start */
ALTER TABLE RSK_ACCESS_LIST_LOG ADD INSTITUTION_CODE VARCHAR2(32);
COMMENT ON COLUMN RSK_ACCESS_LIST_LOG.INSTITUTION_CODE IS '所属机构';
/* add by songyawei 表RSK_ACCESS_LIST_LOG添加所属机构 20200622 end */

/* add by songyawei 表WFL_RSK_ACCESS_LIST_LOG添加所属机构 20200622 start */
ALTER TABLE WFL_RSK_ACCESS_LIST_LOG ADD INSTITUTION_CODE VARCHAR2(32);
COMMENT ON COLUMN WFL_RSK_ACCESS_LIST_LOG.INSTITUTION_CODE IS '所属机构';
/* add by songyawei 表WFL_RSK_ACCESS_LIST_LOG添加所属机构 20200622 end */

--add by liupengfei for 存款存续期element配置S 2020-06-22 22:00:00
DELETE FROM SYS_PAGE_ELEMENT WHERE SYS_PAGE_ELEMENT.PAGE_ID ='TrdDepoDuration001' and  ELEMENT_ID ='TrdDepoDuration001_btn_demand';
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdDepoDuration001', 'TrdDepoDuration001_btn_demand', '01', '01', NULL, '活期存放结息', 'system', NULL, SYSDATE, 'system', SYSDATE);
--add by liupengfei for 存款存续期element配置E

--add by wujian 20200623 脚本位置移动 start
----- 机构登记银行表增加字段
ALTER TABLE CHB_ORG_REGIST_BANK_CODE ADD (INSITITUTE_CODE VARCHAR2(50));
--add by wujian 20200623 脚本位置移动 end


--add by wujian 20200623  配置data_type改为in配置，分隔符使用英文逗号
alter table Data_Auth_Mapper_Config modify (data_type VARCHAR2(512));

--按钮脚本 估值表关联导入映射 add by caojin 20200624 start
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'RskValRelateImport001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('RskValRelateImport001','RskValRelateImport001_btn_add','01','01',null,'新建','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('RskValRelateImport001','RskValRelateImport001_btn_delete','01','01',null,'删除','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('RskValRelateImport001','RskValRelateImport001_btn_export','01','01',null,'导出','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('RskValRelateImport001','RskValRelateImport001_btn_search','01','01',null,'查询','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('RskValRelateImport001','RskValRelateImport001_btn_edit','01','01',null,'编辑','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('RskValRelateImport001','RskValRelateImport001_btn_delt','01','01',null,'删除','system',null,to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'),'system',to_timestamp('24-5月 -19 12.00.00.000000000 上午','DD-MON-RR HH.MI.SSXFF AM'));
--按钮脚本 估值表关联导入映射 add by caojin 20200624 end

--按钮脚本add by jingqiongqiong 20200628 start
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlManagementInfo001', 'PtlManagementInfo001_btn_allocation', '01', '01', null, '用户分配', 'system', null, sysdate, 'system', sysdate);
--按钮脚本add by jingqiongqiong 20200628 end

--add  by wangyang1 产品端角色权限配置 start
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'ProductReportRegister001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ProductReportRegister001', 'ProductReportRegister001_btn_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ProductReportRegister001', 'ProductReportRegister001_btn_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ProductReportRegister001', 'ProductReportRegister001_btn_edit', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ProductReportRegister001', 'ProductReportRegister001_btn_export', '01', '01', null, '导出', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system', TIMESTAMP '2019-05-24 00:00:00');


delete from SYS_PAGE_ELEMENT where UPDATE_USER ='system1';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductAnapleroticInfo001', 'FinProductAnapleroticInfo001_TBLROW_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductBmQuotation001', 'FinProductBmQuotation001_TBLROW_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinMdateAdjustment001', 'FinMdateAdjustment001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinFloatMgtExpQuotation001', 'FinFloatMgtExpQuotation001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductAnapleroticInfo001', 'FinProductAnapleroticInfo001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductBmQuotation001', 'FinProductBmQuotation001_btn_edit', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductEstablish001', 'FinProductEstablish001_btn_add', '01', '01', null, '新建', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductAddReduce001', 'FinProductAddReduce001_btn_addreduce', '01', '01', null, '提前赎回', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductPayInt001', 'FinProductPayInt001_btn_add', '01', '01', null, '新建', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductExpire001', 'FinProductExpire001_btn_add', '01', '01', null, '新建', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinNetProductBonusScheme001', 'FinNetProductBonusScheme001_btn_bonusScheRegister', '01', '01', null, '分红方案登记', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinNetProductBonusScheme001', 'FinNetProductBonusScheme001_btn_bonusAmtConfirm', '01', '01', null, '分红金额确认', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinNetProductBonusScheme001', 'FinNetProductBonusScheme001_btn_bonusCash', '01', '01', null, '现金红利下发', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinNetProductBonusScheme001', 'FinNetProductBonusScheme001_btn_bonusShareConfirm', '01', '01', null, '再投份额确认', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinResultsCovering001', 'FinResultsCovering001_btn_add', '01', '01', null, '新建', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinResultsCovering001', 'FinResultsCovering001_btn_revoke', '01', '01', null, '撤销', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductQuotation001', 'FinProductQuotation001_btn_del', '01', '01', null, '删除', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductQuotation001', 'FinProductQuotation001_btn_sendSale', '01', '01', null, '发送', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinProductBmQuotation001', 'FinProductBmQuotation001_btn_sendSale', '01', '01', null, '发送', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('FinPayPlanAdjustment001', 'FinPayPlanAdjustment001_btn_rule', '01', '01', null, '付费规则调整', 'system1', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
update SYS_PAGE_ELEMENT set IS_CONTROL = '01' where ELEMENT_ID IN ('FinOpenPlanMaintenance001_btn_submit','FinPayPlanAdjustment001_btn_submit','FinIntPayDateAdjustment001_btn_submit');

--add  by wangyang1 产品端角色权限配置 end
--add by liupengfei for 角色权限配置存续期 start 2020/06/29-11:00
--买断式回购
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'TrdRepoBuyoutDuration001';
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdRepoBuyoutDuration001', 'TrdRepoBuyoutDuration001_TBLROW_edit', '01', '01', NULL, '确认', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdRepoBuyoutDuration001', 'TrdRepoBuyoutDuration001_btn_export', '01', '01', NULL, '导出', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdRepoBuyoutDuration001', 'TrdRepoBuyoutDuration001_btn_reset', '01', '02', NULL, '重置', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdRepoBuyoutDuration001', 'TrdRepoBuyoutDuration001_btn_revoke', '01', '01', NULL, '撤销', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdRepoBuyoutDuration001', 'TrdRepoBuyoutDuration001_btn_search', '01', '01', NULL, '查询', 'system', NULL, sysdate, 'system', sysdate);
--债券借贷
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'TrdSecLendDuration001';
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdSecLendDuration001', 'TrdSecLendDuration001_TBLROW_edit', '01', '01', NULL, '确认', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdSecLendDuration001', 'TrdSecLendDuration001_btn_export', '01', '01', NULL, '导出', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdSecLendDuration001', 'TrdSecLendDuration001_btn_reset', '01', '02', NULL, '重置', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdSecLendDuration001', 'TrdSecLendDuration001_btn_revoke', '01', '01', NULL, '撤销', 'system', NULL, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdSecLendDuration001', 'TrdSecLendDuration001_btn_search', '01', '01', NULL, '查询', 'system', NULL, sysdate, 'system', sysdate);
--利率互换
delete from SYS_PAGE_ELEMENT where ELEMENT_ID = 'TrdIRSDuration001_TBLROW_edit';
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdIRSDuration001', 'TrdIRSDuration001_TBLROW_edit', '01', '01', NULL, '确认', 'system', NULL, sysdate, 'system', sysdate);
--融入/出券
delete from SYS_PAGE_ELEMENT where ELEMENT_ID = 'TrdBondLendTargetDuration001_TBLROW_edit';
INSERT INTO SYS_PAGE_ELEMENT(PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)VALUES('TrdBondLendTargetDuration001', 'TrdBondLendTargetDuration001_TBLROW_edit', '01', '01', NULL, '确认', 'system', NULL, sysdate, 'system', sysdate);
--add by liupengfei for 角色权限配置 end

--add by jingqiongqiong for 角色权限配置机构授信 start 220200629
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'CeOrg001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CeOrg001', 'CeOrg001_TBLROW_delete', '01', '01', null, '删除', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CeOrg001', 'CeOrg001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CeOrg001', 'CeOrg001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CeOrg001', 'CeOrg001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CeOrg001', 'CeOrg001_btn_filter', '01', '01', null, '筛选', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('CeOrg001', 'CeOrg001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate);
--add by jingqiongqiong for 角色权限配置机构授信 end 220200629


-- add by  caiqun 20200629 存款类/拆借存续期角色权限配置  start
delete from sys_page_element where PAGE_ID='TrdDepoDuration001' and ELEMENT_ID in ('TrdDepoDuration001_btn_demand','TrdDepoDuration001_btn_edit');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdDepoDuration001', 'TrdDepoDuration001_btn_demand', '01', '01', '', '活期存放结息', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdDepoDuration001', 'TrdDepoDuration001_btn_edit', '01', '01', '', '编辑', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');


delete from sys_page_element where PAGE_ID='TrdLendDuration001' and ELEMENT_ID='TrdLendDuration001_btn_edit';
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('TrdLendDuration001', 'TrdLendDuration001_btn_edit', '01', '01', '', '编辑', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
-- add by  caiqun 20200629 存款类/拆借存续期角色权限配置  end


--按钮脚本add by songyawei 20200629 start
insert into sys_page_element (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('RskValtblPrtBotasset001', 'RskValtblPrtBotasset001_btn_edit', '01', '01', '', '操作', 'system', '', '24-5月 -19 12.00.00.000000 上午', 'system', '24-5月 -19 12.00.00.000000 上午');
--按钮脚本add by songyawei 20200629 end


------  ADD BY  WJ 20200629 计划类货币型标的、与公募基金标的里的货币型未生成委外组合
DELETE ASSET_TYPE_CONFIG WHERE TYPE_6 = 'N01' AND TYPE_7 = '01' AND TYPE_8 = 'F09';
DELETE ASSET_TYPE_CONFIG WHERE TYPE_6 = 'F01' AND TYPE_7 IS NULL AND TYPE_8 = 'F02';

insert into ASSET_TYPE_CONFIG (TYPE_6, TYPE_7, TYPE_8, IS_CREATE_PORTFOLIO)
values ('N01', '01', 'F09', 'Y');

insert into ASSET_TYPE_CONFIG (TYPE_6, TYPE_7, TYPE_8, IS_CREATE_PORTFOLIO)
values ('F01', null, 'F02', 'Y');
------------------

--按钮脚本  add by caojin 20200629 start

--产品基本信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdBaseinfoSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_submit','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoSub','PbcPrdBaseinfoSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
--产品起始募集信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdInitRaiseSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdInitRaiseSub','PbcPrdInitRaiseSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdInitRaiseSub','PbcPrdInitRaiseSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
--产品终止信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdBaseinfoEndSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdBaseinfoEndSub','PbcPrdBaseinfoEndSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
--产品资金募集信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdCashRaiseSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdCashRaiseSub','PbcPrdCashRaiseSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
--资产池基本信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdAccountSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountSub','PbcPrdAccountSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountSub','PbcPrdAccountSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
--资产池终止信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdAccountEndSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountEndSub','PbcPrdAccountEndSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAccountEndSub','PbcPrdAccountEndSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
--资产负债信息
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcPrdAssetDebtSub';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAssetDebtSub','PbcPrdAssetDebtSub_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAssetDebtSub','PbcPrdAssetDebtSub_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcPrdAssetDebtSub','PbcPrdAssetDebtSub_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
--人行资管产品月度统计表
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PbcMonthReport001';
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_checkData','01','01',null,'数据检查','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_manual','01','01',null,'手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_cancelManual','01','01',null,'取消手工处理','system',null,sysdate,'system',sysdate);
Insert into SYS_PAGE_ELEMENT (PAGE_ID,ELEMENT_ID,ELEMENT_TYPE,IS_CONTROL,TRANSFER_PAGE_ID,REMARK,CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME) values ('PbcMonthReport001','ChbMonthReport001_btn_recal','01','01',null,'重新计算','system',null,sysdate,'system',sysdate);

--按钮脚本  add by caojin 20200629 end


--add  by wangyang1 风险限额角色权限配置 start
delete from SYS_PAGE_ELEMENT where UPDATE_USER ='system2';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionIndicatorParam001', 'RskSupervisionIndicatorParam001_btn_yycs_create', '01', '01', null, '语言参数', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionIndicatorParam001', 'RskSupervisionIndicatorParam001_btn_zdy_create', '01', '01', null, '自定义参数', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionIndicatorParam001', 'RskSupervisionIndicatorParam001_btn_gdz_create', '01', '01', null, '固定值参数', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_btn_creat', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_btn_add', '01', '01', null, '添加', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_btn_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_btn_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_btn_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_btn_portBagAddAicon', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_TBLROW_editPortfBag', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_TBLROW_delPortfBag', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionPortfPoint001_TBLROW_edit', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_btn_delt', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_TBLROW_node_modify', '01', '01', null, '编辑', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskPortAutoRelatePoint001', 'RskPortAutoRelatePoint001_TBLROW_node_delete', '01', '01', null, '删除', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system1', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionIndividuation001_btn_tjsz_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionIndividuation001_btn_tsjd_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('RskSupervisionPortfPointMain', 'RskSupervisionIndividuation001_btn_tszqsz_add', '01', '01', null, '新建', 'system', null, TIMESTAMP '2019-05-24 00:00:00', 'system2', TIMESTAMP '2019-05-24 00:00:00');
--add  by wangyang1 风险限额角色权限配置 end

--add  by jingqiongqiong 子组合间资金管理 start
delete from SYS_PAGE_ELEMENT where PAGE_ID = 'PtlSubFundsMange001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlSubFundsMange001', 'PtlSubFundsAllocate001_btn_add', '01', '01', null, '资金调配新建', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlSubFundsMange001', 'PtlSubFundsAllocate001_btn_export', '01', '01', null, '子组合资金调配导出', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlSubFundsMange001', 'PtlSubFundsAllocate001_btn_search', '01', '01', null, '子组合资金调配查询', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlSubFundsMange001', 'PtlSubFundsMange001_btn_add', '01', '01', null, '资金分配', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlSubFundsMange001', 'PtlSubFundsMange001_btn_export', '01', '01', null, '产品交易资金分配导出', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlSubFundsMange001', 'PtlSubFundsMange001_btn_reset', '01', '01', null, '产品交易资金分配重置', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('PtlSubFundsMange001', 'PtlSubFundsMange001_btn_search', '01', '01', null, '产品交易资金分配查询', 'system', null, sysdate, 'system', sysdate);
 --add  by jingqiongqiong 子组合间资金管理 end

--add  by jingqiongqiong 投资费用支付 start
 DELETE FROM SYS_PAGE_ELEMENT WHERE PAGE_ID='InvestFeePay001';
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('InvestFeePay001', 'InvestFeePay001_btn_add', '01', '01', null, '新建', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('InvestFeePay001', 'InvestFeePay001_btn_export', '01', '01', null, '导出', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('InvestFeePay001', 'InvestFeePay001_btn_reset', '01', '01', null, '重置', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('InvestFeePay001', 'InvestFeePay001_btn_revoke', '01', '01', null, '撤销', 'system', null, sysdate, 'system', sysdate);
INSERT INTO SYS_PAGE_ELEMENT (PAGE_ID, ELEMENT_ID, ELEMENT_TYPE, IS_CONTROL, TRANSFER_PAGE_ID, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('InvestFeePay001', 'InvestFeePay001_btn_search', '01', '01', null, '查询', 'system', null, sysdate, 'system', sysdate);
  --add  by jingqiongqiong 投资费用支付 end
  
--add by liupengfei  授权管理交易方向数据字典 S 2020/06/30
DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID='RskAuthorityManage002';
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T01', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T02', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T03', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T04', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T05', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T06', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T18', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T19', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T24', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T39', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T52', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T_B/S', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage002', '999999', 'PS', 'T_S/B', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
DELETE FROM SYS_PAGE_DICTLIST WHERE PAGE_ID='RskAuthorityManage001';
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T01', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T02', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T03', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T04', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T05', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T06', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T18', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T19', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T24', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T39', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T52', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T_B/S', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
INSERT INTO SYS_PAGE_DICTLIST(PAGE_ID, SUB_PAGE_ID, DICT_ID, DICT_ITEM_VAL, DICT_ITEM_VAL_CNNAME, DICT_ITEM_VAL_DEFAULT, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE)VALUES('RskAuthorityManage001', '999999', 'PS', 'T_S/B', NULL, NULL, 'system', NULL, sysdate, 'system', sysdate, NULL);
--add by liupengfei  授权管理交易方向数据字典 E 2020/06/30
 
-- add by caiqun 脚本整理，初始化脚本默认到银行模式，后续提供联社版本单独再执行：多级法人开关--联社模式才执行.sql
-- Add/modify columns 
alter table SYS_PARA drop column UPDATE_FLG;
alter table SYS_PARA add UPDATE_FLG VARCHAR2(50) DEFAULT'01' not null;
-- Add comments to the columns 
comment on column SYS_PARA.UPDATE_FLG is '用户可更改标识';

--默认银行模式
update sys_para t set t.param_value='02' where t.system_code='SYS' and t.para_code='data_authority';
update sys_para t set t.param_value='01' where t.system_code='SYS' and t.para_code='AUTHORITY_TYPE';
update sys_para t set t.param_value='01' where t.system_code='SYS' and t.para_code='is_show_admin_flag';
-- add by caiqun 脚本整理，初始化脚本默认到银行模式，后续提供联社版本单独再执行：多级法人开关--联社模式才执行.sql