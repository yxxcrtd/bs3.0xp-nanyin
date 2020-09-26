-----------------------更改产品权益类资产的风控事项---------------------------------
delete from RSK_EVENT_ITEM_RULE where EVENT_CODE='EVENT_ID_QYLZCGM_08' and ITEM_CODE in ('eventType','investType','ps');
INSERT INTO "RSK_EVENT_ITEM_RULE" VALUES ('EVENT_ID_QYLZCGM_08', 'eventType', '42f0e31c2e95485bb6e5079e560cb0a7', NULL, '0,1,2', 'IN', NULL, NULL, NULL, 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_EVENT_ITEM_RULE" VALUES ('EVENT_ID_QYLZCGM_08', 'investType', '42f0e31c2e95485bb6e5079e560cb0a7', NULL, '02', '=', NULL, NULL, NULL, 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_EVENT_ITEM_RULE" VALUES ('EVENT_ID_QYLZCGM_08', 'ps', '42f0e31c2e95485bb6e5079e560cb0a7', NULL, 'T02,T03,T06,T19,T21', 'IN', NULL, NULL, NULL, 'jjpei', '2222222', sysdate, 'jjpei', sysdate);

-------------------更改到期日在一年以内的国债、中央银行票据和政策性金融债券规模 指标参数SQL--------------
delete from RSK_ISUP_INDICATOR_PARA_DTL where PARA_CODE='XJGZZYYHPJZCXJRZQGM';
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('XJGZZYYHPJZCXJRZQGM', '01', 'SQL', 'select y1.PORTFOLIO_ID as RKEY,sum(TRADE_AMT)  as RVALUE from	
(	
  select m.PORTFOLIO_ID,nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' and n.MDATE<=add_months(date''${valDate}'', 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''	
  where  m.CDATE=date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID group by m.PORTFOLIO_ID	
	
	union all
   select t.PTL_BAG_ID as RKEY,	
   ( select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' and n.MDATE<=add_months(date''${valDate}'', 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''	
  where  m.CDATE=date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID	AND 
	m.PORTFOLIO_ID in (   select t1.PORTFOLIO_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' where t1.PTL_BAG_ID=t.PTL_BAG_ID)
   )	
   from RSK_PORTFOLIO_PACKAGE t	
   	
) y1 group by y1.PORTFOLIO_ID', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('XJGZZYYHPJZCXJRZQGM', '02', 'SQL', 'select y1.PORTFOLIO_ID as RKEY,sum(TRADE_AMT)  as RVALUE from	
(	
  select m.PORTFOLIO_ID,nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' and n.MDATE<=add_months(date''${valDate}'', 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''	
  where  m.CDATE=date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID group by m.PORTFOLIO_ID	
	
	union all
   select t.PTL_BAG_ID as RKEY,	
   ( select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' and n.MDATE<=add_months(date''${valDate}'', 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''	
  where  m.CDATE=date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID	AND 
	m.PORTFOLIO_ID in (   select t1.PORTFOLIO_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' where t1.PTL_BAG_ID=t.PTL_BAG_ID)
   )	
   from RSK_PORTFOLIO_PACKAGE t	
   	
) y1 group by y1.PORTFOLIO_ID', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('XJGZZYYHPJZCXJRZQGM', '03', 'SQL', 'select y1.PORTFOLIO_ID as RKEY,sum(TRADE_AMT)  as RVALUE from	
(	
  select m.PORTFOLIO_ID,nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' and n.MDATE<=add_months(date''${valDate}'', 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''	
  where  m.CDATE=date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID group by m.PORTFOLIO_ID	
	
	union all
   select t.PTL_BAG_ID as RKEY,	
   ( select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' and n.MDATE<=add_months(date''${valDate}'', 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''	
  where  m.CDATE=date''${valDate}''-1  AND m.PORTFOLIO_ID!=m.FINPROD_ID	AND 
	m.PORTFOLIO_ID in (   select t1.PORTFOLIO_ID from RSK_PORTFOLIO_PACKAGE_DTL t1  inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' where t1.PTL_BAG_ID=t.PTL_BAG_ID)
   )	
   from RSK_PORTFOLIO_PACKAGE t	
   	
) y1 group by y1.PORTFOLIO_ID', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);
INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('XJGZZYYHPJZCXJRZQGM', '04', 'SQL', 'select sum(TRADE_AMT) as tradeAmt from	
(	
  select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=sysdate and n.MDATE<=add_months(sysdate, 12)	
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
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT  from TRD_PRODUCT_DEAL_ADD m	
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID	
  INNER JOIN FIN_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 =''F01'' and t2.MDATE>=sysdate and t2.MDATE<=add_months(sysdate, 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01'')) and t3.TYPE_8 =''F01''	
  where  m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'')	
	
union ALL	
  select	
   nvl(sum(decode(n.PS,''T01'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T04'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  +nvl(sum(decode(n.PS,''T52'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T02'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T03'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T06'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T07'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T08'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T09'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)	
  -nvl(sum(decode(n.PS,''T11'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT  from WFL_TRD_PRODUCT_DEAL_ADD m	
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''	
  INNER JOIN FIN_PRODUCT t2 on t2.FINPROD_ID=n.FINPROD_ID and t2.FINPROD_TYPE2 =''F01'' and t2.MDATE>=sysdate and t2.MDATE<=add_months(sysdate, 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01'')) and t3.TYPE_8 =''F01''	
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T07'',''T08'',''T09'',''T11'',''T52'')	
	
)', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);

INSERT INTO "RSK_ISUP_INDICATOR_PARA_DTL" VALUES ('XJGZZYYHPJZCXJRZQGM', '05', 'SQL', 'select sum(TRADE_AMT) as tradeAmt from	
(	
  select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP, 0)),0) as  TRADE_AMT from PTL_SEC_VALUTION m	
  INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 =''F01'' and n.MDATE>=date''${valDate}'' and n.MDATE<=add_months(date''${valDate}'', 12)	
  inner join FIN_PRODUCT_TYPE t3 on n.FINPROD_ID=t3.FINPROD_ID and (t3.TYPE_6 in (''B01'',''B03'') or (t3.TYPE_6=''B05'' AND t3.TYPE_7=''01''))  and t3.TYPE_8 =''F01''	
  where  m.PORTFOLIO_ID in ${portfolioCode}  and m.CDATE=date''${valDate}''	
)', 'portfolioCode', 'N', 'jjpei', '2222222', sysdate, 'jjpei', sysdate);