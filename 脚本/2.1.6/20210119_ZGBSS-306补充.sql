declare
	sql03 clob:='select sum(O_MARKET_VALUE) O_MARKET_VALUE from (
select sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) O_MARKET_VALUE
from ptl_sec_valution where  CDATE= date''${valDate}''-1
and FINPROD_ID like ''F07_%'' and ${portfolioCode} = portfolio_id
union all
SELECT  
	case ps when ''T01'' then decode(''TRADE_STATUS'',''01'',trade_amt,-1* trade_amt)
	else decode(''TRADE_STATUS'',''01'',-1*trade_amt,trade_amt)
	end O_MARKET_VALUE
FROM TRD_PRODUCT_DEAL  tpd inner join TRD_PRODUCT_DEAL_ADD  tpda on tpd.trade_id= tpda.trade_id
where  tpd.finprod_id like ''F07_%'' and tpda.PORTFOLIO_ID=${portfolioCode} and tpd.trade_date = date''${valDate}''
union all
SELECT  
	case ps when ''T01'' then decode(''TRADE_STATUS'',''01'',trade_amt,-1* trade_amt)
	else decode(''TRADE_STATUS'',''01'',-1*trade_amt,trade_amt)
	end O_MARKET_VALUE
FROM WFL_TRD_PRODUCT_DEAL  tpd inner join WFL_TRD_PRODUCT_DEAL_ADD  tpda on tpd.trade_id= tpda.trade_id
where  tpd.finprod_id like ''F07_%'' and tpda.PORTFOLIO_ID=${portfolioCode} and tpd.trade_date = date''${valDate}'')';
begin
	update rsk_isup_indicator_para_dtl set content = sql03 where para_code = 'YGDQXYHCKGZ' and para_granularity='04';
end;
/