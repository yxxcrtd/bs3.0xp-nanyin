
update rsk_isup_indicator_para_dtl set content='
select sum(amount) AS VALUE from
(
   select sum(amount) as amount
   from
   (
       select 
       case when t2.PORTFOLIO_ID is null then nvl(TDY_ASSET_NETVALUE,0)
       else mujiData.mujiAmt end as amount
       from PTL_PORTFOLIO_VALUATION t1
       left join PTL_PORTFOLIO t2
        on t1.PORTFOLIO_ID = t2.PORTFOLIO_ID and t2.VDATE = date''${valDate}''
       left join 
       (
       select t1.PORTFOLIO_ID, sum(t1.TRADE_AMT) as mujiAmt from TRD_FIN_PRODUCT_DEAL_TOTAL t1
       WHERE
           t1.PS in (''C1'', ''C2'')
           and t1.PORTFOLIO_ID in ${portfolioCode}
           and t1.SETTLE_DATE <= date''${valDate}''
        and t1.trade_status=''01''
       group by t1.PORTFOLIO_ID
       ) mujiData
       on t1.PORTFOLIO_ID = mujiData.PORTFOLIO_ID
       where t1.PORTFOLIO_ID in ${portfolioCode} and t1.CDATE=date''${valDate}''-1
   ) zr			
   where zr.PORTFOLIO_ID in ${portfolioCode}	
union all
  select
    nvl(sum(decode(n.PS,''C1'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  +nvl(sum(decode(n.PS,''C2'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  +nvl(sum(decode(n.PS,''C3'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C4'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C5'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C6'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C7'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C9'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C11'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C12'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C13'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C14'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C15'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C16'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C17'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C18'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0)
  -nvl(sum(decode(n.PS,''C19'',case when TRADE_STATUS=''03'' and IS_CANCEL=''N'' then n.TRADE_AMT*-1 ELSE n.TRADE_AMT END)),0) as amount
  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
  where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' group by m.PORTFOLIO_ID
'
where para_code='CPJZC' and PARA_GRANULARITY='01';


update rsk_isup_indicator_para_dtl set content=content || '
union all
  select
   nvl(sum(decode(n.PS,''C1'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''C2'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  +nvl(sum(decode(n.PS,''C3'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C4'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C5'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C6'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C7'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C9'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C12'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C13'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C14'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C15'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C16'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C18'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
  -nvl(sum(decode(n.PS,''C19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount
  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
  where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' and m.WORKFLOW_ID != ${tradeCode} group by m.PORTFOLIO_ID  
)
'
where para_code='CPJZC' and PARA_GRANULARITY='04';
