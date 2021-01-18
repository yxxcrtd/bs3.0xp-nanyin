declare
	sql01 clob:='--五个交易日到期的非国债央票政策金融债资产
with prod as(
	select fp.finprod_id
	from FIN_PRODUCT fp 
	left join FIN_PRODUCT_TYPE FPT on FPT.finprod_id = fp.finprod_id and fpt.type_10 not in (''03'',''05'',''06'')
	inner join MST_HOLIDAY mh on NVL(fp.CALENDAR_ID,''01'') =  MH.CALENDAR_ID
        and mh.is_holiday = ''0''
        and mh.cdate > date''${valDate}''-1
        and fp.MDATE >= mh.cdate 
	group by fp.finprod_id
	having count(mh.cdate)<=5
),
psv as (
	select * from ptl_sec_valution where CDATE = date''${valDate}''-1
)
select 
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue,
psv.PORTFOLIO_ID
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
group by psv.PORTFOLIO_ID
';
	sql02 clob:='--五个交易日到期的非国债央票政策金融债资产
with prod as(
	select fp.finprod_id
	from FIN_PRODUCT fp 
	left join FIN_PRODUCT_TYPE FPT on FPT.finprod_id = fp.finprod_id and fpt.type_10 not in (''03'',''05'',''06'')
	inner join MST_HOLIDAY mh on NVL(fp.CALENDAR_ID,''01'') =  MH.CALENDAR_ID
        and mh.is_holiday = ''0''
        and mh.cdate > date''${valDate}''-1
        and fp.MDATE >= mh.cdate 
	group by fp.finprod_id
	having count(mh.cdate)<=5
),
psv as (
	select * from ptl_sec_valution where CDATE = date''${valDate}''-1
)
select 
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue,
psv.PORTFOLIO_ID
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
group by psv.PORTFOLIO_ID
';
	sql03 clob:='--五个交易日到期的非国债央票政策金融债资产
with prod as(
	select fp.finprod_id
	from FIN_PRODUCT fp 
	left join FIN_PRODUCT_TYPE FPT on FPT.finprod_id = fp.finprod_id and fpt.type_10 not in (''03'',''05'',''06'')
	inner join MST_HOLIDAY mh on NVL(fp.CALENDAR_ID,''01'') =  MH.CALENDAR_ID
        and mh.is_holiday = ''0''
        and mh.cdate > date''${valDate}''-1
        and fp.MDATE >= mh.cdate 
	group by fp.finprod_id
	having count(mh.cdate)<=5
),
psv as (
	select * from ptl_sec_valution where CDATE = date''${valDate}''-1
)
select 
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue,
psv.PORTFOLIO_ID
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
group by psv.PORTFOLIO_ID
';
	sql04 clob:='--五个交易日到期的非国债央票政策金融债资产
with prod as(
	select fp.finprod_id
	from FIN_PRODUCT fp
	left join FIN_PRODUCT_TYPE FPT on FPT.finprod_id = fp.finprod_id and fpt.type_10 not in (''03'',''05'',''06'')
	inner join MST_HOLIDAY mh on NVL(fp.CALENDAR_ID,''01'') =  MH.CALENDAR_ID
        and mh.is_holiday = ''0''
        and mh.cdate > date''${valDate}''-1
        and fp.MDATE >= mh.cdate
	group by fp.finprod_id
	having count(mh.cdate)<=5
),
psv as (
	select * from ptl_sec_valution where CDATE = date''${valDate}''-1 and portfolio_id IN ${portfolioCode}
)
select sum(TRADE_AMT) as RVALUE FROM(
select
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) TRADE_AMT
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
union all
SELECT
  nvl(sum(decode(trd.PS,''T01'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  +nvl(sum(decode(trd.PS,''T04'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  +nvl(sum(decode(trd.PS,''T20'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  +nvl(sum(decode(trd.PS,''T52'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T02'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T03'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T07'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T08'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T09'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T11'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T19'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T21'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T38'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T39'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T54'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T22'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T23'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)  as  TRADE_AMT
from TRD_PRODUCT_DEAL trd
inner join prod on trd.finprod_id = prod.finprod_id
inner join TRD_PRODUCT_DEAL_ADD trda on trd.trade_id = trda.trade_id
where trd.trade_date = date''${valDate}''  and trda.portfolio_id IN ${portfolioCode}
and trd.ps in (''T01'',''T02'',''T03'',''T04'',''T07'',''T08'',''T09'',''T11'',
  ''T19'',''T20'',''T21'',''T38'',''T39'',''T52'',''T54'',''T22'',''T23'')
union all
SELECT
  nvl(sum(decode(trd.PS,''T01'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  +nvl(sum(decode(trd.PS,''T04'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  +nvl(sum(decode(trd.PS,''T20'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  +nvl(sum(decode(trd.PS,''T52'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T02'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T03'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T07'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T08'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T09'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T11'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T19'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T21'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T38'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T39'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T54'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T22'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)
  -nvl(sum(decode(trd.PS,''T23'',decode(TRADE_STATUS,''03'',trd.TRADE_AMT*-1,trd.TRADE_AMT))),0)  as  TRADE_AMT
from WFL_TRD_PRODUCT_DEAL trd
inner join prod on trd.finprod_id = prod.finprod_id
inner join WFL_TRD_PRODUCT_DEAL_ADD trda on trd.trade_id = trda.trade_id
where trd.trade_date = date''${valDate}''  and trda.portfolio_id IN ${portfolioCode})
and trd.ps in (''T01'',''T02'',''T03'',''T04'',''T07'',''T08'',''T09'',''T11'',
  ''T19'',''T20'',''T21'',''T38'',''T39'',''T52'',''T54'',''T22'',''T23'')
';
	sql05 clob:='--五个交易日到期的非国债央票政策金融债资产
with prod as(
	select fp.finprod_id
	from FIN_PRODUCT fp 
	left join FIN_PRODUCT_TYPE FPT on FPT.finprod_id = fp.finprod_id and fpt.type_10 not in (''03'',''05'',''06'')
	inner join MST_HOLIDAY mh on NVL(fp.CALENDAR_ID,''01'') =  MH.CALENDAR_ID
        and mh.is_holiday = ''0''
        and mh.cdate > date''${valDate}''-1
        and fp.MDATE >= mh.cdate 
	group by fp.finprod_id
	having count(mh.cdate)<=5
),
psv as (
	select * from ptl_sec_valution where CDATE = date''${valDate}''
)
select 
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue,
psv.PORTFOLIO_ID
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
group by psv.PORTFOLIO_ID';
begin
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code = 'WGJYRDQJDQJTJRGJGZ' and para_granularity = '01';
	update rsk_isup_indicator_para_dtl set content = sql02 where para_code = 'WGJYRDQJDQJTJRGJGZ' and para_granularity = '02';
	update rsk_isup_indicator_para_dtl set content = sql03 where para_code = 'WGJYRDQJDQJTJRGJGZ' and para_granularity = '03';
	update rsk_isup_indicator_para_dtl set content = sql04 where para_code = 'WGJYRDQJDQJTJRGJGZ' and para_granularity = '04';
	update rsk_isup_indicator_para_dtl set content = sql05 where para_code = 'WGJYRDQJDQJTJRGJGZ' and para_granularity = '05';
end;
/