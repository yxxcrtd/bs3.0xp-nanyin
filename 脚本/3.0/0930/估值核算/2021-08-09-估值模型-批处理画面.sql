
DELETE FROM SYS_MENU where PAGE_ID = 'ModValBatchTask001';
insert into sys_menu (MENU_ID, SYSTEM_CODE, ICON, PARENT_MENU_ID, PAGE_ID, ORDER_NO, IS_SPREAD, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('VALUATION_001_010_005', 'VAL', null, 'VALUATION_001_010', 'ModValBatchTask001', 5, '2', '估值模型批处理', 'system', null, '01-FEB-18 12.00.00.000000 AM', 'system', '01-FEB-18 12.00.00.000000 AM');



delete from sys_page where PAGE_ID = 'ModValBatchTask001';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModValBatchTask001', 'VAL', '/modelvaluation/batch/batchtask/ModValBatchTask001', 'valmodel/batch/batchtask/ModValBatchTask001', 'VALUATION_001_010_005', null, null, '02', '01', '估值模型批处理任务', 'system', null, '05-AUG-21 03.49.51.000000 PM', 'system', '05-AUG-21 03.49.51.000000 PM');
	   

delete from sys_page where PAGE_ID = 'ModValBatchTask002';
insert into sys_page (PAGE_ID, SYSTEM_CODE, PAGE_URL, PAGE_FILE, MENU_ID, HEIGHT, WIDTH, IS_HP_SELECT, IS_NEED_WORKFLOW, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME)
values ('ModValBatchTask002', 'VAL', '/modelvaluation/batch/batchtask/ModValBatchTask002', 'valmodel/batch/batchtask/ModValBatchTask002', null, null, null, '02', '01', '估值模型批处理任务-详情', 'system', null, '05-AUG-21 03.49.51.000000 PM', 'system', '05-AUG-21 03.49.51.000000 PM');

	   
	   


drop table MOD_VAL_BATCH_PARAMS purge;
create table MOD_VAL_BATCH_PARAMS
(
    SEQNO            VARCHAR(32)  not null
        constraint MOD_VAL_BATCH_PARAMS_PK
            unique,
    BATCH_EXEC_SEQNO VARCHAR(32)  not null,
    FINPROD_ID       VARCHAR(50)  not null,
    EXEC_DATE        TIMESTAMP(6) not null,
    CREATE_USER      VARCHAR(20)  not null,
    CREATE_DEPT      VARCHAR(32),
    CREATE_TIME      TIMESTAMP(6) not null,
    UPDATE_USER      VARCHAR(20)  not null,
    UPDATE_TIME      TIMESTAMP(6) not null
);

comment on column MOD_VAL_BATCH_PARAMS.SEQNO is '序号';

comment on column MOD_VAL_BATCH_PARAMS.BATCH_EXEC_SEQNO is '批处理对应序号';

comment on column MOD_VAL_BATCH_PARAMS.FINPROD_ID is '资产代码';

comment on column MOD_VAL_BATCH_PARAMS.EXEC_DATE is '执行日期';

comment on column MOD_VAL_BATCH_PARAMS.CREATE_USER is '创建人';

comment on column MOD_VAL_BATCH_PARAMS.CREATE_DEPT is '创建部门';

comment on column MOD_VAL_BATCH_PARAMS.CREATE_TIME is '创建时间';

comment on column MOD_VAL_BATCH_PARAMS.UPDATE_USER is '更新人';

comment on column MOD_VAL_BATCH_PARAMS.UPDATE_TIME is '更新时间';


drop table MOD_BATCH_LOGS purge;
create table MOD_BATCH_LOGS
(
    SEQNO            VARCHAR(32)  not null,
    BATCH_EXEC_SEQNO VARCHAR(32)  not null,
    FINPROD_ID       VARCHAR(50)  not null,
    TASK_ID          VARCHAR(32)  not null,
    EXEC_DETAIL      CLOB,
    EXEC_STATUS      VARCHAR(10),
    CREATE_USER      VARCHAR(20)  not null,
    CREATE_DEPT      VARCHAR(32),
    CREATE_TIME      TIMESTAMP(6) not null,
    UPDATE_USER      VARCHAR(20)  not null,
    UPDATE_TIME      TIMESTAMP(6) not null,

    constraint MOD_BATCH_LOGS_PK
        primary key (SEQNO)
);

comment on column MOD_BATCH_LOGS.SEQNO is '序号';

comment on column MOD_BATCH_LOGS.BATCH_EXEC_SEQNO is '批处理对应序号';

comment on column MOD_BATCH_LOGS.FINPROD_ID is '资产代码';

comment on column MOD_BATCH_LOGS.EXEC_DETAIL is '消息详情';

comment on column MOD_BATCH_LOGS.EXEC_STATUS is '执行状态';

comment on column MOD_BATCH_LOGS.CREATE_USER is '创建人';

comment on column MOD_BATCH_LOGS.CREATE_DEPT is '创建部门';

comment on column MOD_BATCH_LOGS.CREATE_TIME is '创建时间';

comment on column MOD_BATCH_LOGS.UPDATE_USER is '更新人';

comment on column MOD_BATCH_LOGS.UPDATE_TIME is '更新时间';

comment on column MOD_BATCH_LOGS.TASK_ID is '任务ID';

create index MOD_BATCH_LOGS1
    on MOD_BATCH_LOGS (BATCH_EXEC_SEQNO, TASK_ID, FINPROD_ID);




-- auto-generated definition
create table SYS_BATCH_SERVER_INFO
(
    SEQ_NO         VARCHAR2(32)      not null
        constraint PK_SYS_BATCH_SERVER_INFO
            primary key,
    PROGRAM_ID     NUMBER,
    SERVER_NAME    VARCHAR2(50)      not null,
    SERVER_ADDRESS VARCHAR2(32)      not null,
    PORT           NUMBER            not null,
    STATUS         VARCHAR2(10)      not null,
    SSH_PORT       NUMBER default 22 not null,
    SSH_USER       VARCHAR2(50),
    SSH_PASSWORD   VARCHAR2(200),
    COMMAND        VARCHAR2(200),
    START_TIME     TIMESTAMP(6),
    STOP_TIME      TIMESTAMP(6),
    RESTART_TIME   TIMESTAMP(6),
    VERSION        NUMBER default 1  not null
);

comment on table SYS_BATCH_SERVER_INFO is '批处理服务器信息配置';

comment on column SYS_BATCH_SERVER_INFO.SEQ_NO is '序列号';

comment on column SYS_BATCH_SERVER_INFO.PROGRAM_ID is 'PID';

comment on column SYS_BATCH_SERVER_INFO.SERVER_NAME is '批处理服务器名称';

comment on column SYS_BATCH_SERVER_INFO.SERVER_ADDRESS is '批处理服务器地址';

comment on column SYS_BATCH_SERVER_INFO.PORT is '批处理管理服务监听端口号';

comment on column SYS_BATCH_SERVER_INFO.STATUS is '批处理服务器状态';

comment on column SYS_BATCH_SERVER_INFO.SSH_PORT is 'SSH端口号';

comment on column SYS_BATCH_SERVER_INFO.SSH_USER is 'SSH用户名';

comment on column SYS_BATCH_SERVER_INFO.SSH_PASSWORD is 'SSH用户密码（支持ENC加密）';

comment on column SYS_BATCH_SERVER_INFO.COMMAND is '批处理控制脚本（可以是文件或者命令）';

comment on column SYS_BATCH_SERVER_INFO.START_TIME is '批处理服务器第一次启动时间';

comment on column SYS_BATCH_SERVER_INFO.STOP_TIME is '批处理服务器停止时间';

comment on column SYS_BATCH_SERVER_INFO.RESTART_TIME is '批处理服务器重启时间';

comment on column SYS_BATCH_SERVER_INFO.VERSION is '版本号';

