/** add by yanya on 20210309 金融产品交易附属表新增【行权状态】【放弃行权日】字段**/
alter table TRD_PRODUCT_DEAL_ADD add EXERCISE_STATUS VARCHAR2(50);
alter table TRD_PRODUCT_DEAL_ADD add CANCEL_EXERCISE_DATE DATE;
comment on column TRD_PRODUCT_DEAL_ADD.EXERCISE_STATUS is '行权状态';
comment on column TRD_PRODUCT_DEAL_ADD.CANCEL_EXERCISE_DATE is '放弃行权日';
