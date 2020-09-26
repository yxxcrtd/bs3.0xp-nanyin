--产品权益类资产规模
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='QYLZCGM' and PARA_GRANULARITY='04';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('QYLZCGM', '04', 'SQL', 'select sum(TRADE_AMT) as tradeAmt from	 
 (	 
select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as TRADE_AMT from PTL_SEC_VALUTION m	 
  INNER JOIN FIN_PRODUCT_ADD PFP	 
  on m.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''02''	 
where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}''-1 	AND m.PORTFOLIO_ID!=m.FINPROD_ID 
union ALL	 
  select	 
    nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  +nvl(sum(decode(n.PS,''T20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  +nvl(sum(decode(n.PS,''T46'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  +nvl(sum(decode(n.PS,''T47'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  +nvl(sum(decode(n.PS,''T48'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 	 
  -nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) 	 
  -nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	 
  -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT	 
  from TRD_PRODUCT_DEAL n	 
    INNER JOIN FIN_PRODUCT_ADD PFP	 
   on n.FINPROD_ID = PFP.FINPROD_ID and PFP.INVEST_NATURE =''02''	 
     LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n.TRADE_ID =n1.TRADE_ID	 
  where n1.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T19''	 
  ,''T20'',''T21'',''T38'',''T39'',''T46'',''T47'',''T48'',''T52'',''T54''))
  ', 'portfolioCode', 'N', 'jjpei', '2222222', TIMESTAMP '2020-05-07 17:02:32', 'jjpei', TIMESTAMP '2020-05-07 17:02:32');
 
--组合可投资资产限制
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE = 'ZHKTZZCXZ' and PARA_GRANULARITY = '05';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL (PARA_CODE, PARA_GRANULARITY, PARA_DISPLAY_TYPE, CONTENT, REDIS_FIELD, IS_FRESH_CAN_TRD, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME) VALUES ('ZHKTZZCXZ', '05', 'SQL', 'with tmp as (select 										
case when assetType=''F01'' then ''PINAN_ASSET_TYPE,WINDTYPEONE,WINDTYPETWO''										
when  assetType=''F02'' then ''PINAN_ASSET_TYPE,FUND_ASSET_TYPE,FUND_ASSET_SECOND_TYPE''										
when  assetType=''F06'' then ''PINAN_ASSET_TYPE,ASSET_TYPE_PROJECT,ASSET_TYPE_PROJECT_TWO''										
when  assetType=''F05'' then ''PINAN_ASSET_TYPE,DEPO_ASSET_TYPE,DEPO_TYPE''										
else ''PINAN_ASSET_TYPE'' end ITEMCODE,assetMxType as VALUE										
from (										
select DISTINCT assetType, case when assetMxType is null then assetType else assetMxType end as assetMxType from (										
select 										
  t4.TYPE_8 as assetType,										
			CASE WHEN TYPE_8 IN(''F01'',''F02'',''F06'') THEN TYPE_8||''_''||TYPE_6							
			     WHEN TYPE_8 =''F05'' THEN (							
			     CASE WHEN TYPE_7 IS NOT NULL THEN TYPE_8||''_''||TYPE_7							
			     ELSE TYPE_8||''_''||TYPE_6 END )							
			END AS assetMxType 							
from PTL_SEC_VALUTION t1										
inner join FIN_PRODUCT_TYPE t4 on t1.FINPROD_ID = t4.FINPROD_ID										
where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''	 
union all 										
   select 										
t4.TYPE_8 as assetType,CASE WHEN TYPE_8 IN(''F01'',''F02'',''F06'') THEN TYPE_8||''_''||TYPE_6							
			     WHEN TYPE_8 =''F05'' THEN (							
			     CASE WHEN TYPE_7 IS NOT NULL THEN TYPE_8||''_''||TYPE_7							
			     ELSE TYPE_8||''_''||TYPE_6 END )		
                 END AS assetMxType 							
from PTL_SEC_VALUTION t1										
inner join FIN_TRADE_PRODUCT t2	 on t1.FINPROD_ID = t2.FINPROD_ID									
inner join FIN_PRODUCT_TYPE t4 on t1.FINPROD_ID = t4.FINPROD_ID										
where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}'') 										
) t										
) 										
select m.VALUE RVALUE,n.ITEM_VALUE as RKEY from tmp m left join (										
				select DICT_CODE,''F01_''||ITEM_CODE AS ITEM_CODE,ITEM_VALUE  from SYS_DATADICT_LIST where DICT_CODE=''WINDTYPEONE''						
				union all						
				select DICT_CODE,''F02_''||ITEM_CODE ,ITEM_VALUE  from SYS_DATADICT_LIST where DICT_CODE=''FUND_ASSET_TYPE''						
				union all						
				select DICT_CODE,''F05_''||ITEM_CODE ,ITEM_VALUE  from SYS_DATADICT_LIST where DICT_CODE=''DEPO_TYPE''						
				union all						
				select DICT_CODE,''F05_''||ITEM_CODE ,ITEM_VALUE  from SYS_DATADICT_LIST where DICT_CODE=''DEPO_ASSET_TYPE''						
				union all						
				select DICT_CODE,''F06_''||ITEM_CODE ,ITEM_VALUE  from SYS_DATADICT_LIST where DICT_CODE=''ASSET_TYPE_PROJECT''						
				UNION ALL 						
				SELECT DICT_CODE,ITEM_CODE,ITEM_VALUE FROM SYS_DATADICT_LIST WHERE DICT_CODE  IN (''PINAN_ASSET_TYPE'',''WINDTYPETWO'',''FUND_ASSET_SECOND_TYPE'',''ASSET_TYPE_PROJECT_TWO'')						
	) n on m.VALUE=n.ITEM_CODE 	WHERE m.VALUE IS NOT NULL', 'portfolioCode', 'N', 'GY', '001', TIMESTAMP '2020-05-18 15:29:41', 'GY', TIMESTAMP '2020-05-18 15:29:41');