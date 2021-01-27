declare
	sql01 clob:='select 1 from dual';
	sql02 clob:='SELECT (nvl(sum((TRADE_AMT)*(LAST_PERIOD)),0)) AS lastPeriod FROM (
    select
	DISTINCT A1.FINPROD_ID,A1.TRADE_AMT ,
	CASE WHEN A1.FINPROD_TYPE =''F01'' THEN ((A1.mdate)-(date''${valDate}''))
	WHEN (A1.FINPROD_TYPE =''F06'' OR A1.FINPROD_TYPE =''XJ'') THEN (0)
	WHEN a1.FINPROD_ID =a1.PORTFOLIO_ID
	THEN (1)
    ELSE (((A1.mdate)-(date''${valDate}''))) END LAST_PERIOD
	from
	(
    select NULL AS FINPROD_ID,NULL AS PORTFOLIO_ID,nvl(sum(AMOUNT),0) as  TRADE_AMT,''XJ'' AS FINPROD_TYPE,NULL AS MDATE   from PTL_CASH_POSITION
 	where  PORTFOLIO_ID in ${portfolioCode} and HODING_TYPE=''03'' and CDATE =date''${valDate}''
    UNION ALL
		select
		t2.FINPROD_MARKET_ID FINPROD_ID,t1.PORTFOLIO_ID,nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0) as TRADE_AMT,t2.FINPROD_TYPE2,t2.MDATE
		from PTL_SEC_VALUTION t1
		inner join FIN_PRODUCT t2	on t1.FINPROD_ID = t2.FINPROD_ID
		where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''-1
		UNION ALL
		select
		t1.FINPROD_ID FINPROD_ID,t1.PORTFOLIO_ID,nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0) as TRADE_AMT,t3.FINPROD_TYPE2,t3.MDATE
		from PTL_SEC_VALUTION t1
		inner join FIN_TRADE_PRODUCT t3	 on t1.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0
		where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''-1
		and  t3.FINPROD_TYPE2 not in (''F02'',''F04'', ''F08'', ''F25'')
        UNION ALL
		select
		t1.FINPROD_ID FINPROD_ID,t1.PORTFOLIO_ID,-1*(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)) as TRADE_AMT,t3.FINPROD_TYPE2,t3.MDATE
		from PTL_SEC_VALUTION t1
		inner join FIN_TRADE_PRODUCT t3	 on t1.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0
		where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''-1
		and  t3.FINPROD_TYPE2 in (''F02'',''F04'', ''F08'', ''F25'')

        UNION ALL
		select NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID) FINPROD_ID ,n1.PORTFOLIO_ID,
		  nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		+nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		+nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_TYPE2,t2.MDATE
		  from TRD_PRODUCT_DEAL n
		  LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n1.TRADE_ID=n.TRADE_ID
		  left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
		  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0
		  where n1.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''
		  and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'',''F08'', ''F25'') AND t3.BRANCH=0))
		  and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') group by n1.PORTFOLIO_ID,NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID),t2.MDATE,n.FINPROD_TYPE2
          UNION ALL
	    select NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID) FINPROD_ID ,m.PORTFOLIO_ID,
	    nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		+nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		+nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,n.FINPROD_TYPE2,t2.MDATE
	  from WFL_TRD_PRODUCT_DEAL_ADD m
	  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
	  left JOIN FIN_PRODUCT T2 on T2.FINPROD_ID=n.FINPROD_ID
	  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0
      WHERE  m.PORTFOLIO_ID in ${portfolioCode}  AND n.TRADE_DATE=date''${valDate}''
      AND ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'',''F08'', ''F25'') AND t3.BRANCH=0 ))
      and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') group by m.PORTFOLIO_ID,NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID),t2.MDATE,n.FINPROD_TYPE2

       UNION ALL
		select NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID) FINPROD_ID ,n1.PORTFOLIO_ID,
		  -1*(nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		+nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		+nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)) as  TRADE_AMT,n.FINPROD_TYPE2,t2.MDATE
		  from TRD_PRODUCT_DEAL n
		  LEFT JOIN TRD_PRODUCT_DEAL_ADD n1 ON n1.TRADE_ID=n.TRADE_ID
		  left join FIN_PRODUCT t2  on n.FINPROD_ID = t2.FINPROD_ID
		  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0
		  where n1.PORTFOLIO_ID in ${portfolioCode} and  n.TRADE_DATE = date''${valDate}''
		  and (t3.FINPROD_TYPE2 in (''F02'', ''F04'',''F08'', ''F25'') AND t3.BRANCH=0)
		  and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') group by n1.PORTFOLIO_ID,NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID),t2.MDATE,n.FINPROD_TYPE2
          UNION ALL
	    select NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID) FINPROD_ID ,m.PORTFOLIO_ID,
	    -1*(nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		+nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		+nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T21'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T22'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T23'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T38'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T39'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
		-nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)) as  TRADE_AMT,n.FINPROD_TYPE2,t2.MDATE
	  from WFL_TRD_PRODUCT_DEAL_ADD m
	  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
	  left JOIN FIN_PRODUCT T2 on T2.FINPROD_ID=n.FINPROD_ID
	  left join FIN_TRADE_PRODUCT t3 on n.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0
      WHERE  m.PORTFOLIO_ID in ${portfolioCode}  AND n.TRADE_DATE=date''${valDate}''
      AND ( t3.FINPROD_TYPE2 in (''F02'', ''F04'',''F08'', ''F25'') AND t3.BRANCH=0 )
      and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') group by m.PORTFOLIO_ID,NVL(t2.FINPROD_MARKET_ID,n.FINPROD_ID),t2.MDATE,n.FINPROD_TYPE2

	) A1
)';
	sql03 clob:='SELECT (nvl(sum((TRADE_AMT)*(LAST_PERIOD)),0)) AS lastPeriod FROM (select
	A1.FINPROD_ID,
	A1.TRADE_AMT,
	A1.MDATE,
	CASE WHEN A1.FINPROD_TYPE =''F01'' THEN ((A1.mdate)-(date''${valDate}''))
	WHEN (A1.FINPROD_TYPE =''F06'' OR A1.FINPROD_TYPE =''XJ'') THEN (0)
	WHEN a1.FINPROD_ID =a1.PORTFOLIO_ID
	THEN (1)
    ELSE ((A1.mdate)-(date''${valDate}'')) END LAST_PERIOD
from
(
  select NULL AS FINPROD_ID,NULL AS PORTFOLIO_ID,nvl(sum(AMOUNT),0) as  TRADE_AMT,''XJ'' AS FINPROD_TYPE,NULL AS MDATE   from PTL_CASH_POSITION
 	 where  PORTFOLIO_ID in ${portfolioCode} and HODING_TYPE=''03'' and CDATE =date''${valDate}''
    UNION ALL
	select
	t2.FINPROD_MARKET_ID FINPROD_ID,t1.PORTFOLIO_ID,nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0) as TRADE_AMT,t2.FINPROD_TYPE2,t2.MDATE
	from PTL_SEC_VALUTION t1
	 inner join FIN_PRODUCT t2	on t1.FINPROD_ID = t2.FINPROD_ID
	where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''
    UNION ALL
    select
	NVL(t2.FINPROD_MARKET_ID,t1.FINPROD_ID) FINPROD_ID,t1.PORTFOLIO_ID,nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0) as TRADE_AMT,t3.FINPROD_TYPE2,t3.MDATE
	from PTL_SEC_VALUTION t1
	inner join FIN_TRADE_PRODUCT t3	 on t1.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0
     left join FIN_PRODUCT t2 on t3.FINPROD_ID = t2.FINPROD_ID
	where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''
	and  t3.FINPROD_TYPE2 not in (''F02'', ''F04'',''F08'', ''F25'')
    union ALL
    select
	NVL(t2.FINPROD_MARKET_ID,t1.FINPROD_ID) FINPROD_ID,t1.PORTFOLIO_ID,-1*(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as TRADE_AMT,t3.FINPROD_TYPE2,t3.MDATE
	from PTL_SEC_VALUTION t1
	inner join FIN_TRADE_PRODUCT t3	 on t1.FINPROD_ID = t3.FINPROD_ID AND t3.BRANCH=0
     left join FIN_PRODUCT t2 on t3.FINPROD_ID = t2.FINPROD_ID
	where t1.PORTFOLIO_ID in ${portfolioCode} and  t1.CDATE = date''${valDate}''
	and  t3.FINPROD_TYPE2 in (''F02'', ''F04'',''F08'', ''F25'')
) A1
)';
begin
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code = 'ZCSYCXQX' and para_granularity in ('01','02','03');
	update rsk_isup_indicator_para_dtl set content = sql02 where para_code = 'ZCSYCXQX' and para_granularity ='04';
	update rsk_isup_indicator_para_dtl set content = sql03 where para_code = 'ZCSYCXQX' and para_granularity ='05';
end;
/