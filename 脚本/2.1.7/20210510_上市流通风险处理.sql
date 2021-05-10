declare 
	sql01 clob:='select sum(RVALUE) RVALUE,RKEY
from(
select
    sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY
from PTL_SEC_VALUTION t1
left join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID
left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3 on t1.FINPROD_ID = t3.FINPROD_ID
where  t1.CDATE = date''${valDate}''-1  AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
    and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'', ''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME

union all
select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY
from (
    select t1.*
    from PTL_SEC_VALUTION t1 
    left join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID
    left join (SELECT * FROM FIN_TRADE_PRODUCT WHERE BRANCH=0) t3 on t1.FINPROD_ID = t3.FINPROD_ID
    where  t1.CDATE = date''${valDate}''-1   AND t1.PORTFOLIO_ID!=t1.FINPROD_ID
    and ((t3.FINPROD_TYPE2 is null and t2.FINPROD_TYPE2 is not null) or (t3.FINPROD_TYPE2 not in (''F02'',  ''F04'', ''F08'',''F11'',''F12'',''F13'',''F14'',''F15'',''F25'') AND t3.BRANCH=0))
) s1,
(
    select t1.PORTFOLIO_ID,t1.PTL_BAG_ID 
    from RSK_PORTFOLIO_PACKAGE_DTL t1 
    inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}''
)s2 
where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID
)
group by RKEY';
	sql02 clob:='SELECT nvl(sum(nvl(RVALUE, 0)),0) as RVALUE,s1.RKEY  FROM (	 
	select 
	sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, t1.PORTFOLIO_ID||'',''||t1.FINPROD_ID as RKEY 
	from PTL_SEC_VALUTION t1 
	inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  IN (''F01'',''F06'',''F10'') 
	where  t1.CDATE = date''${valDate}''-1 
	group by t1.PORTFOLIO_ID,t1.FINPROD_ID,t2.FINPROD_NAME 
    UNION ALL 
	select sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)) as RVALUE, s2.PTL_BAG_ID||'',''||s1.FINPROD_ID as RKEY 
	from (select t1.* 
	from PTL_SEC_VALUTION t1 
	inner join FIN_PRODUCT t2 on t1.FINPROD_ID = t2.FINPROD_ID and t2.FINPROD_TYPE2  IN (''F01'',''F06'',''F10'') 
	where  t1.CDATE = date''${valDate}''-1 
	) s1,( select t1.PORTFOLIO_ID,t1.PTL_BAG_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' ) 
	s2 where s1.PORTFOLIO_ID=s2.PORTFOLIO_ID  group by s2.PTL_BAG_ID,s1.FINPROD_ID 
	) s1 group by s1.RKEY ';
begin
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code= 'DYZCGM' and para_granularity in ('01','02','03');
	update rsk_isup_indicator_para_dtl set content = sql02 where para_code= 'GMZDDDZZQHJJ' and para_granularity in ('01','02','03');
end;
/