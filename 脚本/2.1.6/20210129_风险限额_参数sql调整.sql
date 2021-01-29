declare
	sql01 clob:='SELECT  NVL(SUM(Market_Value),0) AS rvalue
        FROM BOK_VAL_TABLE_DATA
 WHERE 1=1  AND BOOKSET_ID IN (SELECT prod.finprod_id FROM fin_product prod WHERE prod.finprod_market_id= ${portfolioCode})
   AND VAL_DATE = date''${valDate}''
   AND SUBJECT_NO = ''1102''';
	sql02 clob:='SELECT
       NVL(SUM(A.SHADOW_PRICE_VALUE),0) AS RVALUE
  FROM BOK_VAL_TABLE_DATA A
 WHERE A.VAL_DATE = date''${valDate}''
   AND A.SUBJECT_NO = ''1102''
   AND a.bookset_id IN (SELECT prod.finprod_id FROM fin_product prod WHERE prod.finprod_market_id=${portfolioCode})';
begin
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code = 'TYCBFJSDZCJZ' and para_granularity ='05';
	update rsk_isup_indicator_para_dtl set content = sql02 where para_code = 'YZDJQDDCPJZC' and para_granularity ='05';
end;
/
