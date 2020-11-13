


----------  TISHKBS-1436  fin_product_add表新增字段 REP_FINPROD_INS_ID VARCHAR2(100) 行内识别码；

alter table fin_product drop column REP_FINPROD_INS_ID;
alter  table fin_product_add  add (REP_FINPROD_INS_ID  varchar2(100));
comment on column FIN_PRODUCT_ADD.REP_FINPROD_INS_ID is '行内识别码';



UPDATE fin_product_add a SET REP_FINPROD_INS_ID = 
( SELECT finPROD_MARKET_ID FROM  fin_product b WHERE a.finprod_id = b.finprod_id)
WHERE EXISTS ( SELECT 1 FROM fin_product f WHERE a.finprod_id = f.finprod_id
AND f.finprod_type = 'F16' ) AND REP_FINPROD_INS_ID IS NULL;
