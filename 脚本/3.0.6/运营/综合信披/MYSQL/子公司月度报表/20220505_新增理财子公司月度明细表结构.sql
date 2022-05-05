create table chb_month_report_sub_detail
(
    DETAILUUID     char(36)        not null
        primary key,
    TERMID         varchar(20)     null comment '期数',
    REPORTTYPE     varchar(10)     null comment '报表类型：PROD-产品，ASSET-资产',
    PROPERTYCODE   varchar(20)     null comment '属性代码',
    BALANCE        decimal(24, 6)  null comment '余额',
    TERMS          decimal(30, 14) null comment '加权价格/剩余期限',
    BALANCE_ACC    decimal(24, 6)  null comment '符合《通知》余额',
    TERMS_ACC      decimal(30, 14) null comment '符合《通知》加权价格',
    BALANCE_INC    decimal(24, 6)  null comment '不符合《通知》余额',
    TERMS_INC      decimal(30, 14) null comment '不符合《通知》加权价格',
    BALANCE_BAN    decimal(24, 6)  null comment '母行余额',
    PRODCOUNTS_BAN int             null comment '母行只数',
    TERMS_BAN      decimal(30, 14) null comment '母行加权价格/剩余期限',
    BALANCE_SUB    decimal(24, 6)  null comment '子公司余额',
    PRODCOUNTS_SUB int             null comment '子公司只数',
    TERMS_SUB      decimal(30, 14) null comment '子公司加权价格/剩余期限',
    ORG_CODE       varchar(20)     null comment '机构代码',
    DEPT_CODE      varchar(20)     null comment '部门代码',
    REPORTUUID     char(36)        null comment '月报主表主键'
)
    comment '子公司月报明细表';