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
psv.PORTFOLIO_ID RKEY
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
group by psv.PORTFOLIO_ID';
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
	select * from ptl_sec_valution where CDATE = date''${valDate}''
)
select
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue
from psv
inner join prod on psv.finprod_id = prod.finprod_id and  prod.finprod_id is not null
where psv.PORTFOLIO_ID in  ${portfolioCode}';
begin
	update rsk_isup_indicator_para_dtl set content = sql01 where para_code = 'WGJYRDQJDQJTJRGJGZ' and para_granularity in ('01','02','03'); 
	update rsk_isup_indicator_para_dtl set content = sql02 where para_code = 'WGJYRDQJDQJTJRGJGZ' and para_granularity ='05'; 
end;
/