drop table SZD_TZXX cascade constraints;

/*==============================================================*/
/* Table: SZD_TZXX                                              */
/*==============================================================*/



create table SZD_TZXX
(
    SEQNO                VARCHAR(32)          not null,
    IMPORT_DATE          DATE,
    DATA_FILE            VARCHAR(256),
    SCDM                 VARCHAR(2),
    TZLB                 VARCHAR(3),
    TZRQ                 DATE,
    QSBH                 VARCHAR(8),
    ZQDM                 VARCHAR(6),
    ZQLB                 VARCHAR(2),
    LTLX                 VARCHAR(1),
    QYLB                 VARCHAR(2),
    GPNF                 VARCHAR(4),
    ZH1                  VARCHAR(25),
    ZH2                  VARCHAR(25),
    RQ1                  DATE,
    RQ2                  DATE,
    JE1                  NUMBER(17,2),
    JE2                  NUMBER(17,2),
    JE3                  NUMBER(17,2),
    JG1                  NUMBER(15,2),
    JG2             NUMBER(15,2),
    BL1                  NUMBER(15,2),
    BL2                  NUMBER(15,2),
    SL1                  NUMBER(15,2),
    SL2                  NUMBER(15,2),
    FZDM                 VARCHAR(10),
    BZ                   VARCHAR(80),
    "BY"                 VARCHAR(40),
    CREATE_USER          VARCHAR(20)          not null,
    CREATE_DEPT          VARCHAR(32),
    CREATE_TIME          TIMESTAMP            not null,
    UPDATE_USER          VARCHAR(20)          not null,
    UPDATE_TIME          TIMESTAMP            not null,
    constraint PK_SZD_TZXX primary key (SEQNO)
);

comment on table SZD_TZXX is
'上中登权益登记通知信息TZXX';

comment on column SZD_TZXX.SEQNO is
'流水号';

comment on column SZD_TZXX.IMPORT_DATE is
'导入日期';

comment on column SZD_TZXX.DATA_FILE is
'数据文件';

comment on column SZD_TZXX.SCDM is
'是否本次数据清算对象';

comment on column SZD_TZXX.TZLB is
'备付金账户';

comment on column SZD_TZXX.TZRQ is
'备付金账户';

comment on column SZD_TZXX.QSBH is
'数据类型';

comment on column SZD_TZXX.ZQDM is
'业务类别';

comment on column SZD_TZXX.ZQLB is
'证券代码';

comment on column SZD_TZXX.LTLX is
'交易单元';

comment on column SZD_TZXX.QYLB is
'托管单元';

comment on column SZD_TZXX.GPNF is
'证券账户号码';

comment on column SZD_TZXX.ZH1 is
'客户订单编号';

comment on column SZD_TZXX.ZH2 is
'营业部代码';

comment on column SZD_TZXX.RQ1 is
'执行编号';

comment on column SZD_TZXX.RQ2 is
'业务流水号';

comment on column SZD_TZXX.JE1 is
'成交数量';

comment on column SZD_TZXX.JE2 is
'清算数量';

comment on column SZD_TZXX.JE3 is
'交收数量';

comment on column SZD_TZXX.JG1 is
'成交价格';

comment on column SZD_TZXX.JG2 is
'清算价格';

comment on column SZD_TZXX.BL1 is
'信用交易标识';

comment on column SZD_TZXX.CREATE_USER is
'创建人';

comment on column SZD_TZXX.CREATE_DEPT is
'创建部门';

comment on column SZD_TZXX.CREATE_TIME is
'创建时间';

comment on column SZD_TZXX.UPDATE_USER is
'更新人';

comment on column SZD_TZXX.UPDATE_TIME is
'更新时间';


delete from SETTLE_DATA_RULE where SEQNO='191';
insert into SETTLE_DATA_RULE (SEQNO, TABLE_NAME, DATA_FILE, FINPROD_TYPE, INTERF_FIELD_CNNAME, TABLE_FIELD, FIELD_CODE, FIELD_VALUE, REMARK, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) values('191','TZXX','TZXX','06','通知类别','TZLB','008','A股','红利登记信息', 'system', '00001', sysdate, 'system', sysdate);
