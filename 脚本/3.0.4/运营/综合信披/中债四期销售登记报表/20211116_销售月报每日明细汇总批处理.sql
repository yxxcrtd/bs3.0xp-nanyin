DROP TABLE CHB_SALE_DEAL_SUM;
create table CHB_SALE_DEAL_SUM(
  PRD_REGIST_CODE VARCHAR2(20) NOT NULL
  ,PROD_ID	VARCHAR2(36)
  ,INSTITUTION_CODE	VARCHAR2(20) NOT NULL
  ,AREA_CODE	VARCHAR2(50) NOT NULL
  ,HOLDER_TYPE	CHAR(2 BYTE) NOT NULL
  ,CCY	CHAR(3 BYTE)
  ,ISAGENCY	CHAR(2 BYTE)
  ,DAY_SALE_AMOUNT_SUM	NUMBER(36,8)
  ,DAY_VALUE_AMOUNT_SUM	NUMBER(36,8)
  ,PROD_VALUE	NUMBER(36,8)
  ,DAY_SALE_QTY_SUM	NUMBER(36,8)
  ,DAY_VALUE_QTY_SUM	NUMBER(36,8)
  ,SALE_TOTAL_BY_QTY	NUMBER(36,8)
  ,VALUE_TOTAL_BY_QTY	NUMBER(30,8)
  ,HOLDER_NUM	NUMBER(18,5)
  ,HOLDER_BALANCE_AMT	NUMBER(36,8)
  ,REMARK	VARCHAR2(1000)
  ,CREATE_USER	VARCHAR2(20)
  ,ORG_CODE	VARCHAR2(20)
  ,CREATE_DEPT	VARCHAR2(20)
  ,CREATE_TIME	TIMESTAMP(6)
  ,UPDATE_USER	VARCHAR2(20)
  ,UPDATE_TIME	TIMESTAMP(6)
  ,EQUITY_CONF_DATE DATE
);
  
comment on table CHB_SALE_DEAL_SUM is '理财业务销售数据基础汇总表';
comment on column CHB_SALE_DEAL_SUM.PRD_REGIST_CODE is '产品登记编码 ';
comment on column CHB_SALE_DEAL_SUM.PROD_ID is '产品代码 ';
comment on column CHB_SALE_DEAL_SUM.INSTITUTION_CODE is '销售机构代码 ';
comment on column CHB_SALE_DEAL_SUM.AREA_CODE is '地区类型 ';
comment on column CHB_SALE_DEAL_SUM.HOLDER_TYPE is '投资者类型 ';
comment on column CHB_SALE_DEAL_SUM.CCY is '币种 ';
comment on column CHB_SALE_DEAL_SUM.ISAGENCY is '是否代销 ';
comment on column CHB_SALE_DEAL_SUM.DAY_SALE_AMOUNT_SUM is '汇总-总销售金额 ';
comment on column CHB_SALE_DEAL_SUM.DAY_VALUE_AMOUNT_SUM is '汇总-净销售金额 ';
comment on column CHB_SALE_DEAL_SUM.PROD_VALUE is '确认净值 ';
comment on column CHB_SALE_DEAL_SUM.DAY_SALE_QTY_SUM is '汇总-总销售份额 ';
comment on column CHB_SALE_DEAL_SUM.DAY_VALUE_QTY_SUM is '汇总-净销售份额 ';
comment on column CHB_SALE_DEAL_SUM.SALE_TOTAL_BY_QTY is '汇总-总销售金额（份额*净值） ';
comment on column CHB_SALE_DEAL_SUM.VALUE_TOTAL_BY_QTY is '汇总-净销售金额（份额*净值） ';
comment on column CHB_SALE_DEAL_SUM.HOLDER_NUM is '投资者数量 ';
comment on column CHB_SALE_DEAL_SUM.HOLDER_BALANCE_AMT is '持有余额 ';
comment on column CHB_SALE_DEAL_SUM.REMARK is '备注 ';
comment on column CHB_SALE_DEAL_SUM.CREATE_USER is '创建人 ';
comment on column CHB_SALE_DEAL_SUM.ORG_CODE is '机构代码 ';
comment on column CHB_SALE_DEAL_SUM.CREATE_DEPT is '创建部门 ';
comment on column CHB_SALE_DEAL_SUM.CREATE_TIME is '创建时间 ';
comment on column CHB_SALE_DEAL_SUM.UPDATE_USER is '更新人 ';
comment on column CHB_SALE_DEAL_SUM.UPDATE_TIME is '更新时间 ';
comment on column CHB_SALE_DEAL_SUM.EQUITY_CONF_DATE is '业务确认日期 ';

DELETE FROM SYS_BATCH_TASK WHERE TASK_ID = '3058';
insert into SYS_BATCH_TASK (TASK_ID, SYSTEM_CODE, TASK_NAME, FTASK_ID, BEAN_NAME, TASK_PRI, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, IS_BATCH_START_TARGET)
values ('3058', 'VAL', '销售月报每日明细汇总批处理', null, 'svrDayCollectInterfTaskService', 3030, 'system', null, sysdate, 'system', sysdate, null);
