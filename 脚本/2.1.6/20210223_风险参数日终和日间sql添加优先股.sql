declare
	sql01 clob:='select nvl(sum(nvl(TRADE_AMT, 0)),0) as RVALUE ,s1.FINPROD_ID as RKEY,s1.FINPROD_NAME RKEYCN from
(
  select t1.FINPROD_ID ,n.FINPROD_NAME,nvl(sum(nvl(SHARE_AMT,0)),0) as  TRADE_AMT from PTL_SEC_VALUTION t1
  INNER  JOIN FIN_PRODUCT n on t1.FINPROD_ID=n.FINPROD_ID 
  inner join FIN_PRODUCT_TYPE fpt on n.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0 and (fpt.TYPE_8 =''F11''  OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B17''))
  where t1.PORTFOLIO_ID in ${portfolioCode}  and  t1.CDATE=date''${valDate}''-1
  group by t1.FINPROD_ID,n.FINPROD_NAME
union all
   select t2.FINPROD_ID ,m.FINPROD_NAME,
   nvl(sum(decode(t2.PS,''T46'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T47'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T48'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T01'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T04'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T52'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T02'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T03'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T06'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T08'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T09'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T11'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T19'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T21'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T22'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T23'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T38'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T39'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T54'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  from TRD_PRODUCT_DEAL_ADD t1
  inner join TRD_PRODUCT_DEAL t2 on t1.TRADE_ID=t2.TRADE_ID
  INNER  JOIN FIN_PRODUCT m on m.FINPROD_ID=t2.FINPROD_ID 
  inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0 and (fpt.TYPE_8 =''F11''  OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B17''))
  where t1.PORTFOLIO_ID in ${portfolioCode} and  t2.TRADE_DATE=date''${valDate}'' and t2.PS in (''T46'',''T47'',''T48'',''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'')
   group by t2.FINPROD_ID,m.FINPROD_NAME   union all
  select 	t2.FINPROD_ID ,s.FINPROD_NAME,
   nvl(sum(decode(t2.PS,''T46'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T47'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T48'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T01'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T04'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  +nvl(sum(decode(t2.PS,''T52'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T02'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T03'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T06'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T08'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T09'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T11'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T19'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T21'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T22'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T23'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T38'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T39'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  -nvl(sum(decode(t2.PS,''T54'',decode(TRADE_STATUS,''03'',t2.SHARE_AMT*-1,t2.SHARE_AMT))),0)
  from WFL_TRD_PRODUCT_DEAL_ADD t1
  inner join WFL_TRD_PRODUCT_DEAL t2 on t1.TRADE_ID=t2.TRADE_ID   and t2.TRADE_STATUS != ''03''
  INNER  JOIN FIN_PRODUCT s on t2.FINPROD_ID=s.FINPROD_ID 
  inner join FIN_PRODUCT_TYPE fpt on s.FINPROD_ID=fpt.FINPROD_ID AND fpt.BRANCH=0 and (fpt.TYPE_8 =''F11''  OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B17''))
  where t1.PORTFOLIO_ID in ${portfolioCode}  and t2.TRADE_DATE=date''${valDate}'' and t2.PS in (''T46'',''T47'',''T48'',''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'')
   group by t2.FINPROD_ID,s.FINPROD_NAME
) s1  group by s1.FINPROD_ID,s1.FINPROD_NAME';
begin
	--投资单一上市公司发行的股票
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code ='TZDYSSGSFXGP' and para_granularity = '04';
end;
/