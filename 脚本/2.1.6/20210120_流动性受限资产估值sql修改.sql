declare
	sql01 clob:='--到期日在10个交易日以上的债券买入返售、银行定期存款，以及流动性受限资产，合计不得超过该产品资产净值的10%
with prod as(
	SELECT FINPROD_ID FROM(
        select fp.FINPROD_ID,MH.CDATE
		from fin_product fp inner join MST_HOLIDAY mh 
        on nvl(fp.CALENDAR_ID,''01'') = mh.CALENDAR_ID  and fp.MDATE >= MH.CDATE and MH.IS_HOLIDAY = ''0''
        where MH.CDATE > date''${valDate}'' and fp.FINPROD_TYPE2 IN (''F01'', ''F07'')
		union 
		select fp.FINPROD_ID,MH.CDATE
		from FIN_TRADE_PRODUCT fp inner join MST_HOLIDAY mh 
        on nvl(fp.CALENDAR_ID,''01'') = mh.CALENDAR_ID  and fp.MDATE >= MH.CDATE and MH.IS_HOLIDAY = ''0''
        where MH.CDATE >= date''${valDate}'' and fp.FINPROD_TYPE2 IN (''F03'', ''F05'')
    )
	group by FINPROD_ID
    having count(CDATE)>10
 ),
 psv as (
	select * from ptl_sec_valution where CDATE = date''${valDate}''-1
)
select
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue,
psv.PORTFOLIO_ID RKEY
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
group by PORTFOLIO_ID';
	sql02 clob:='WITH PROD AS (
    SELECT FINPROD_ID FROM(
        select fp.FINPROD_ID,MH.CDATE
		from fin_product fp inner join MST_HOLIDAY mh 
        on nvl(fp.CALENDAR_ID,''01'') = mh.CALENDAR_ID  and fp.MDATE >= MH.CDATE and MH.IS_HOLIDAY = ''0''
        where MH.CDATE > date''${valDate}'' and fp.FINPROD_TYPE2 IN (''F01'', ''F07'')
		union 
		select fp.FINPROD_ID,MH.CDATE
		from FIN_TRADE_PRODUCT fp inner join MST_HOLIDAY mh 
        on nvl(fp.CALENDAR_ID,''01'') = mh.CALENDAR_ID  and fp.MDATE >= MH.CDATE and MH.IS_HOLIDAY = ''0''
        where MH.CDATE >= date''${valDate}'' and fp.FINPROD_TYPE2 IN (''F03'', ''F05'')
    )
	group by FINPROD_ID
    having count(CDATE)>10
),
psv as (
	select * from ptl_sec_valution where CDATE = date''${valDate}''-1
)
SELECT SUM(TRADE_AMT) RVALUE FROM (
    select
        sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue
    from psv
    inner join prod on psv.finprod_id = prod.finprod_id and prod.finprod_id is not null
    where psv.PORTFOLIO_ID in ${portfolioCode} 
    
    UNION ALL
    SELECT
        nvl(sum(decode(n.PS, ''T01'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T04'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T52'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T02'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T03'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T19'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T07'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T08'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T09'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T11'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T22'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T23'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T38'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T39'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0) AS TRADE_AMT
    FROM TRD_PRODUCT_DEAL_ADD m
    INNER JOIN TRD_PRODUCT_DEAL n ON m.TRADE_ID = n.TRADE_ID
    INNER JOIN PROD t2 ON t2.FINPROD_ID = n.FINPROD_ID 
    WHERE m.PORTFOLIO_ID IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T07'', ''T08'', ''T09'', ''T11'', ''T52'', ''T22'', ''T23'', ''T39'', ''T38'')
  UNION ALL
    SELECT
        nvl(sum(decode(n.PS, ''T01'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T04'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      + nvl(sum(decode(n.PS, ''T52'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T02'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T03'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0)
      - nvl(sum(decode(n.PS, ''T19'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0) AS TRADE_AMT
    FROM WFL_TRD_PRODUCT_DEAL_ADD m
    INNER JOIN WFL_TRD_PRODUCT_DEAL n ON m.TRADE_ID = n.TRADE_ID AND n.TRADE_STATUS != ''03''
    INNER JOIN PROD t2 ON t2.FINPROD_ID = n.FINPROD_ID 
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    WHERE m.PORTFOLIO_ID IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''
    AND n.PS IN (''T01'', ''T02'', ''T03'', ''T04'', ''T19'', ''T52'')
  UNION ALL
    SELECT
        nvl(sum(decode(n.PS, ''T02'', decode(TRADE_STATUS, ''03'', n.TRADE_AMT*-1, n.TRADE_AMT))), 0) AS TRADE_AMT
    FROM WFL_TRD_PRODUCT_DEAL_ADD m
    INNER JOIN WFL_TRD_PRODUCT_DEAL n ON m.TRADE_ID = n.TRADE_ID AND n.TRADE_STATUS != ''03''
    INNER JOIN PROD t2 ON t2.FINPROD_ID = n.FINPROD_ID
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    WHERE n.counter_id IN ${portfolioCode} AND n.TRADE_DATE = DATE''${valDate}''
    AND n.PS = ''T02''
    AND EXISTS (SELECT 1 FROM SYS_PARA WHERE PARA_CODE = ''is_auto_generate_negate_deal'' AND PARAM_VALUE = ''Y'')
)';
	sql03 clob:='--到期日在10个交易日以上的债券买入返售、银行定期存款，以及流动性受限资产，合计不得超过该产品资产净值的10%
with prod as(
	SELECT FINPROD_ID FROM(
        select fp.FINPROD_ID,MH.CDATE
		from fin_product fp inner join MST_HOLIDAY mh 
        on nvl(fp.CALENDAR_ID,''01'') = mh.CALENDAR_ID  and fp.MDATE >= MH.CDATE and MH.IS_HOLIDAY = ''0''
        where MH.CDATE > date''${valDate}'' and fp.FINPROD_TYPE2 IN (''F01'', ''F07'')
		union 
		select fp.FINPROD_ID,MH.CDATE
		from FIN_TRADE_PRODUCT fp inner join MST_HOLIDAY mh 
        on nvl(fp.CALENDAR_ID,''01'') = mh.CALENDAR_ID  and fp.MDATE >= MH.CDATE and MH.IS_HOLIDAY = ''0''
        where MH.CDATE >= date''${valDate}'' and fp.FINPROD_TYPE2 IN (''F03'', ''F05'')
    )
	group by FINPROD_ID
    having count(CDATE)>10
 ),
 psv as (
	select * from ptl_sec_valution where CDATE = date''${valDate}''
)
select
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
where psv.PORTFOLIO_ID in ${portfolioCode} ';
begin
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code = 'LDXSXZCGZ' and para_granularity in ('01','02','03');
	update rsk_isup_indicator_para_dtl set content = sql02 where para_code = 'LDXSXZCGZ' and para_granularity ='04';
	update rsk_isup_indicator_para_dtl set content = sql03 where para_code = 'LDXSXZCGZ' and para_granularity ='05';
end;
/