


----------  TISHKBS-1436

alter  table fin_product  add (REP_FINPROD_INS_ID  varchar2(100));

UPDATE fin_product SET REP_FINPROD_INS_ID = FINPROD_MARKET_ID WHERE finprod_type = 'F16';

