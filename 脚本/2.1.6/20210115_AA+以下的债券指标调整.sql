declare
	enum_sql varchar2(2000):='SELECT ID,TEXT FROM (
select ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''FINPROD_TYPE'' and ITEM_CODE in (''F10'')
union all
select ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''CUSTOM_ASSET_TYPE_FOUR'' and ITEM_CODE in (''011'',''013'')
union all
select ITEM_CODE id,ITEM_VALUE text from SYS_DATADICT_LIST where DICT_CODE=''MAIN_GRADE_RESULT'' and ITEM_CODE not in (''01'',''02'',''03'',''04'')
union all
select ''fd02'' id,''浮动利率债券'' text from SYS_DATADICT_LIST where DICT_CODE=''INT_TYPE'' and ITEM_CODE = ''02''
union all
SELECT ''00'' id,''无对应评级债券'' text from dual
) ORDER BY ID';
	sql01 clob:='with temp as
(
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
AND T3.EFF_DATE = T1.VDATE WHERE T3.INT_TYPE = ''02'')
select * from temp
union all 
select fin_product.finprod_id as RKEY,''00''||''||''||''无对应评级债券''
from fin_product left join temp on fin_product.finprod_id = temp.RKEY
where finprod_type2 = ''F01''  and temp.RVALUE is null';
	sql02 clob:='with temp as 
(
SELECT D.finprod_id AS RKEY,A.GRADE_RESULT || ''||'' || C.ITEM_VALUE AS RVALUE
 from MST_BOND_CREDIT_RATING A JOIN
(select O_CODE,max(GRADE_DATE) GRADE_DATE,O_TYPE
from MST_BOND_CREDIT_RATING
left join fin_product on fin_product.finprod_market_id = MST_BOND_CREDIT_RATING.O_CODE
left join fin_product_type on fin_product.finprod_id =fin_product_type.finprod_id
where MST_BOND_CREDIT_RATING.O_TYPE = decode(fin_product_type.type_10,''12'',''02'',''01'')
GROUP BY O_CODE,O_TYPE
) B
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
AND T3.EFF_DATE = T1.VDATE WHERE T3.INT_TYPE = ''02'')
select * from temp
union all 
select fin_product.finprod_id as RKEY,''00''||''||''||''无对应评级债券''
from fin_product left join temp on fin_product.finprod_id = temp.RKEY
where finprod_type2 = ''F01''  and temp.RVALUE is null';
	sql03 clob:='with temp as 
(
SELECT D.finprod_id AS RKEY,A.GRADE_RESULT || ''||'' || C.ITEM_VALUE AS RVALUE
 from MST_BOND_CREDIT_RATING A JOIN
(select O_CODE,max(GRADE_DATE) GRADE_DATE,O_TYPE
from MST_BOND_CREDIT_RATING
left join fin_product on fin_product.finprod_market_id = MST_BOND_CREDIT_RATING.O_CODE
left join fin_product_type on fin_product.finprod_id =fin_product_type.finprod_id
where MST_BOND_CREDIT_RATING.O_TYPE = decode(fin_product_type.type_10,''12'',''02'',''01'')
GROUP BY O_CODE,O_TYPE
) B
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
AND T3.EFF_DATE = T1.VDATE WHERE T3.INT_TYPE = ''02'')
select * from temp
union all 
select fin_product.finprod_id as RKEY,''00''||''||''||''无对应评级债券''
from fin_product left join temp on fin_product.finprod_id = temp.RKEY
where finprod_type2 = ''F01''  and temp.RVALUE is null';
	sql04 clob:='with temp as 
(
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
AND T3.EFF_DATE = T1.VDATE WHERE T3.INT_TYPE = ''02'')
select RVALUE from(
select * from temp
union all 
select fin_product.finprod_id as RKEY,''00''||''||''||''无对应评级债券''
from fin_product left join temp on fin_product.finprod_id = temp.RKEY
where finprod_type2 = ''F01''  and temp.RVALUE is null
)where RKEY  IN ${assetCode}';
	sql05 clob:='with temp as 
(
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
AND T3.EFF_DATE = T1.VDATE WHERE T3.INT_TYPE = ''02'')
select RVALUE from(
select * from temp
union all 
select fin_product.finprod_id as RKEY,''00''||''||''||''无对应评级债券''
from fin_product left join temp on fin_product.finprod_id = temp.RKEY
where finprod_type2 = ''F01''  and temp.RVALUE is null
)where RKEY  IN ${assetCode}';
begin
	update rsk_isup_indicator_para set FORBID_ENUM = enum_sql where para_code = 'XYDJZAAYXDZQ';
	update rsk_isup_indicator set FOUL_COND = '{"threshold_1":{"value":"011,013,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,F10,fd02,00,","value_cn":"可交换债券,可转债,AA,AA-,A+,A,A-,A-1,A-2,A-3,BBB+,BBB,BBB-,BB+,BB,BB-,B+,B,B-,CCC+,CCC,CCC-,CC+,CC,CC-,C+,C,C-,D,股票,浮动利率债券,无对应评级债券,","fb_model":"Y"}}'
	where ind_name= '现金管理类产品不得投资于股票、可转换债券、可交换债券、浮动利率债券、信用等级在AA+以下的债券和资产支持证券';
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code = 'XYDJZAAYXDZQ' and para_granularity='01';
	update rsk_isup_indicator_para_dtl set content = sql02 where para_code = 'XYDJZAAYXDZQ' and para_granularity='02';
	update rsk_isup_indicator_para_dtl set content = sql03 where para_code = 'XYDJZAAYXDZQ' and para_granularity='03';
	update rsk_isup_indicator_para_dtl set content = sql04 where para_code = 'XYDJZAAYXDZQ' and para_granularity='04';
	update rsk_isup_indicator_para_dtl set content = sql05 where para_code = 'XYDJZAAYXDZQ' and para_granularity='05';
end;
/