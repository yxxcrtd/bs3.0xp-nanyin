---TISHKBS-2246 债券二级交易 券面入库除了100 导致小数点后有四位小数,库里字段只有两位导致精度问题
---金融产品交易汇总表 金融产品交易汇总业务流程 委托完成状况表 表结构变更
-- 1.先将字段重命名
alter table TRD_FIN_PRODUCT_DEAL_TOTAL rename column SHARE_AMT to SHARE_AMT_BAK;
alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL rename column SHARE_AMT to SHARE_AMT_BAK;
alter table ETR_FINISH_STATUS rename column FINISH_AMT to FINISH_AMT_BAK;
alter table ETR_FINISH_STATUS rename column ENT_FINISH_AMT to ENT_FINISH_AMT_BAK;
alter table ETR_FINISH_STATUS rename column ORDER_FINISH_AMT to ORDER_FINISH_AMT_BAK;
-- 2.在新增一列 money，并改成要修改的精度
alter table TRD_FIN_PRODUCT_DEAL_TOTAL add(SHARE_AMT number(30,4));
alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL add(SHARE_AMT number(30,4));
alter table ETR_FINISH_STATUS add(FINISH_AMT number(30,4));
alter table ETR_FINISH_STATUS add(ENT_FINISH_AMT number(30,4));
alter table ETR_FINISH_STATUS add(ORDER_FINISH_AMT number(30,4));
-- 3.将money_bak的值赋值给money列
update TRD_FIN_PRODUCT_DEAL_TOTAL set SHARE_AMT = trim(SHARE_AMT_BAK);
update WFL_TRD_FIN_PRODUCT_DEAL_TOTAL set SHARE_AMT = trim(SHARE_AMT_BAK);
update ETR_FINISH_STATUS set FINISH_AMT = trim(FINISH_AMT_BAK);
update ETR_FINISH_STATUS set ENT_FINISH_AMT = trim(ENT_FINISH_AMT_BAK);
update ETR_FINISH_STATUS set ORDER_FINISH_AMT = trim(ORDER_FINISH_AMT_BAK);
-- 4.删除money_bak列
alter table TRD_FIN_PRODUCT_DEAL_TOTAL drop column SHARE_AMT_BAK;
alter table WFL_TRD_FIN_PRODUCT_DEAL_TOTAL drop column SHARE_AMT_BAK;
alter table ETR_FINISH_STATUS drop column FINISH_AMT_BAK;
alter table ETR_FINISH_STATUS drop column ENT_FINISH_AMT_BAK;
alter table ETR_FINISH_STATUS drop column ORDER_FINISH_AMT_BAK;
-- 5.添加备注
comment on column TRD_FIN_PRODUCT_DEAL_TOTAL.SHARE_AMT is '交易份额';
comment on column WFL_TRD_FIN_PRODUCT_DEAL_TOTAL.SHARE_AMT is '交易份额';
comment on column ETR_FINISH_STATUS.FINISH_AMT is '交易完成额度';
comment on column ETR_FINISH_STATUS.ENT_FINISH_AMT is '委托完成额度';
comment on column ETR_FINISH_STATUS.ORDER_FINISH_AMT is '订单完成额度';