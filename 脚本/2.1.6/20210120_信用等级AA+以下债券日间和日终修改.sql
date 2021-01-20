declare
	sql01 clob:='with temp as(
SELECT D.finprod_id AS RKEY,A.GRADE_RESULT || ''||'' || C.ITEM_VALUE AS RVALUE
 from MST_BOND_CREDIT_RATING A JOIN
(select O_CODE,max(GRADE_DATE) GRADE_DATE,O_TYPE
from MST_BOND_CREDIT_RATING
left join fin_product on fin_product.finprod_market_id = MST_BOND_CREDIT_RATING.O_CODE
left join fin_product_type on fin_product.finprod_id =fin_product_type.finprod_id
where MST_BOND_CREDIT_RATING.O_TYPE = decode(fin_product_type.type_10,''12'',''02'',''01'')
GROUP BY O_CODE,O_TYPE) B
ON A.O_CODE = B.O_CODE AND A.GRADE_DATE = B.GRADE_DATE AND A.O_TYPE = B.O_TYPE
LEFT JOIN FIN_PRODUCT D ON D.finprod_market_id = A.o_code
JOIN
SYS_DATADICT_LIST C ON C.DICT_CODE=''MAIN_GRADE_RESULT'' and A.GRADE_RESULT = C.ITEM_CODE
WHERE D.finprod_id not in (select B.FINPROD_ID from FIN_PRODUCT_TYPE B
LEFT JOIN SYS_DATADICT_LIST C ON C.ITEM_CODE = B.TYPE_11
 where B.TYPE_11 IN  (''011'',''013'') AND C.dict_code = ''CUSTOM_ASSET_TYPE_FOUR''
UNION ALL
select B.FINPROD_ID from FIN_PRODUCT_TYPE B
LEFT JOIN SYS_DATADICT_LIST C ON C.ITEM_CODE = B.TYPE_8
 where B.TYPE_8 IN  (''F11'') AND C.dict_code = ''FINPROD_TYPE''
UNION ALL
select T1.FINPROD_ID from FIN_PRODUCT T1
LEFT JOIN FIN_CASH_INFO T2 ON T2.FINPROD_ID = T1.FINPROD_ID
LEFT JOIN FIN_RATE_INFO T3 ON T3.CASH_ID = T2.CASH_ID
AND T3.EFF_DATE = T1.VDATE WHERE T3.INT_TYPE = ''02''
)

UNION ALL

select B.FINPROD_ID RKEY,B.TYPE_11 || ''||'' || C.ITEM_VALUE RVALUE from FIN_PRODUCT_TYPE B
LEFT JOIN SYS_DATADICT_LIST C ON C.ITEM_CODE = B.TYPE_11
 where B.TYPE_11 IN  (''011'',''013'') AND C.dict_code = ''CUSTOM_ASSET_TYPE_FOUR''
UNION ALL
select B.FINPROD_ID RKEY, ''F10'' || ''||'' || C.ITEM_VALUE RVALUE from FIN_PRODUCT_TYPE B
LEFT JOIN SYS_DATADICT_LIST C ON C.ITEM_CODE = B.TYPE_8
 where B.TYPE_8 IN  (''F11'') AND C.dict_code = ''PINAN_ASSET_TYPE''
UNION ALL
select T1.FINPROD_ID RKEY , ''fd02'' || ''||'' || ''浮动利率债券'' RVALUE from FIN_PRODUCT T1
LEFT JOIN FIN_CASH_INFO T2 ON T2.FINPROD_ID = T1.FINPROD_ID
LEFT JOIN FIN_RATE_INFO T3 ON T3.CASH_ID = T2.CASH_ID
AND T3.EFF_DATE = T1.VDATE WHERE T3.INT_TYPE = ''02''),
psp as (
select distinct finprod_id from ptl_sec_position where cdate = date''${valDate}'' and amount >0 and portfolio_id in ${portfolioCode}
),
total as(
select * from temp
union all
select fin_product.finprod_id as RKEY,''00''||''||''||''无对应评级债券'' RVALUE
from fin_product left join temp on fin_product.finprod_id = temp.RKEY
where finprod_type2 = ''F01''  and temp.RVALUE is null
)
select total.RKEY,total.RVALUE
from total inner join psp on total.RKEY = psp.finprod_id
inner join fin_product_type fpt on fpt.finprod_id = psp.finprod_id
where fpt.type8 in (''F01'',''F11'')';
begin
	update rsk_isup_indicator_para_dtl  set content = sql01 where para_code = 'XYDJZAAYXDZQ' and para_granularity = '04';
	update rsk_isup_indicator_para_dtl  set content = sql01 where para_code = 'XYDJZAAYXDZQ' and para_granularity = '05';
end;
/