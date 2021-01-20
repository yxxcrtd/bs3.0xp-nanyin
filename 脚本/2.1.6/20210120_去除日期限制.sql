declare
	sql01 clob:='select y1.PORTFOLIO_ID as RKEY,sum(TRADE_AMT)  as RVALUE from
(
  select m.PORTFOLIO_ID,nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' 
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''
  where  m.CDATE=date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID group by m.PORTFOLIO_ID

	union all
   select t.PTL_BAG_ID as RKEY,
   ( select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' 
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''
  where  m.CDATE=date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID	AND
	m.PORTFOLIO_ID in (   select t1.PORTFOLIO_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' where t1.PTL_BAG_ID=t.PTL_BAG_ID)
   )
   from RSK_PORTFOLIO_PACKAGE t

) y1 group by y1.PORTFOLIO_ID';
	sql02 clob:='select sum(TRADE_AMT) as tradeAmt from
(
  select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=sysdate 
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''
  where  m.PORTFOLIO_ID in ${portfolioCode}  and m.CDATE=date''${valDate}'' -1

union ALL
  select
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
	-nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
  INNER JOIN FIN_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 =''F01'' and t2.MDATE>=sysdate 
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01'')) and t3.TYPE_8 =''F01''
  where  m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') union all
  select
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
	-nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
  INNER JOIN FIN_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 =''F01'' and t2.MDATE>=sysdate 
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01'')) and t3.TYPE_8 =''F01''
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'')
)';
	sql03 clob:='select sum(TRADE_AMT) as tradeAmt from
(
  select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' 
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''
  where  m.PORTFOLIO_ID in ${portfolioCode}  and m.CDATE=date''${valDate}''
)';
begin
	--到期日在一年以内的国债、中央银行票据和政策性金融债券规模 去除日期条件限制
	delete from RSK_EVENT_ITEM_RULE   where event_code like 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_0%' and item_code = 'mdate';
	--重命名
	update rsk_isup_indicator_para set para_name = '国债、中央银行票据和政策性金融债券规模' where para_name = '到期日在一年以内的国债、中央银行票据和政策性金融债券规模';
	update rsk_event set event_name = '国债、中央银行票据和政策性金融债券规模-买入预占（不算指标）' where event_code = 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_01';
	update rsk_event set event_name = '国债、中央银行票据和政策性金融债券规模-卖出预占（不算指标）' where event_code = 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_02';
	update rsk_event set event_name = '国债、中央银行票据和政策性金融债券规模-买入实占（不算指标）' where event_code = 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_03';
	update rsk_event set event_name = '国债、中央银行票据和政策性金融债券规模-卖出实占（不算指标）' where event_code = 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_04';
	update rsk_event set event_name = '国债、中央银行票据和政策性金融债券规模-买入预占' where event_code = 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_05';
	update rsk_event set event_name = '国债、中央银行票据和政策性金融债券规模-卖出预占' where event_code = 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_06';
	update rsk_event set event_name = '国债、中央银行票据和政策性金融债券规模-买入实占' where event_code = 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_07';
	update rsk_event set event_name = '国债、中央银行票据和政策性金融债券规模-卖出实占' where event_code = 'EVENT_ID_XJGZZYYHPJZCXJRZQGM_08';
	--update sql01
	update rsk_isup_indicator_para_dtl set content=sql01 where para_code= 'XJGZZYYHPJZCXJRZQGM' and para_granularity in ('01','02','03');
	update rsk_isup_indicator_para_dtl set content=sql02 where para_code= 'XJGZZYYHPJZCXJRZQGM' and para_granularity = '04';
	update rsk_isup_indicator_para_dtl set content=sql03 where para_code= 'XJGZZYYHPJZCXJRZQGM' and para_granularity = '05';
end;
/