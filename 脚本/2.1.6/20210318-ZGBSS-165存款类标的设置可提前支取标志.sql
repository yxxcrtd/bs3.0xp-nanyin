--标的类金融产品附属表添加标的是否可提前支取列
declare cnt number;
begin
	select count(*) into cnt from cols where table_name = 'FIN_PRODUCT_ADD' and column_name = 'IS_INADVANCE';
	if cnt>0 then
		execute immediate 'ALTER TABLE FIN_PRODUCT_ADD DROP COLUMN IS_INADVANCE';
	end if;
end;
/
ALTER TABLE FIN_PRODUCT_ADD ADD IS_INADVANCE VARCHAR2(10);
COMMENT ON COLUMN FIN_PRODUCT_ADD.IS_INADVANCE IS '存款类标的否可提前支取，用于风险限额，不影响存续期操作';

--风险事项添加是否可延期变量配置
--添加：存款标的是否可提前支取
delete from SYS_DATADICT_LIST where dict_code='RISK_EVENT_CHARA_TYPE' and ITEM_CODE='isInAdvance';
INSERT INTO SYS_DATADICT_LIST (DICT_CODE, ITEM_CODE, ITEM_VALUE, ORDER_NO, DEFAULT_VALUE, CREATE_USER, CREATE_DEPT, CREATE_TIME, UPDATE_USER, UPDATE_TIME, INSTITUTION_CODE) VALUES ('RISK_EVENT_CHARA_TYPE', 'isInAdvance', '存款标的是否可提前支取', 19, '0', 'system', null, sysdate, 'system',sysdate, null);
--修改风险事项配置
update RSK_EVENT_ITEM_RULE set item_code='isInAdvance' where event_code like 'EVENT_ID_GDQXCKGZ_0%' and item_code = 'isCanDrawInAdvance';

--有固定期限银行估值指标参数sql调整
declare
	sql01 clob:='with fp as(
    select FINPROD_ID from FIN_PRODUCT_ADD
    where FINPROD_ID like ''F07_%'' and (IS_INADVANCE is null or IS_INADVANCE!=''Y'')
)
SELECT nvl(SUM(rvalue),0) AS rvalue ,rkey  FROM (
SELECT PP.PORTFOLIO_ID  AS rkey ,
       nvl(SUM(DECODE(wfl.ps,''T01'',WFL.PRIN_AMT,''T02'',WFL.PRIN_AMT*-1,WFL.PRIN_AMT)),0)  AS rvalue
  FROM TRD_PRODUCT_DEAL WFL
  LEFT JOIN PTL_TRADE PT
    ON NVL(WFL.P_TRADE_ID, WFL.TRADE_ID) = PT.BUSI_NO
  LEFT JOIN PTL_PORTFOLIO PP
    ON PT.PORTFOLIO_ID = PP.PORTFOLIO_ID
 WHERE PP.INVEST_YESORNO = ''Y''
   AND WFL.FINPROD_TYPE2 = ''F07''
   AND wfl.trade_date = date''${valDate}''
   AND WFL.PS IN (''T01'', ''T02'')
   AND WFL.TRADE_STATUS = ''01''
   AND WFL.IS_CANCEL != ''Y''
   AND pp.mdate>=date''${valDate}''
   GROUP BY pp.portfolio_id
UNION ALL
 select portfolio_id  rkey,
sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) rvalue
  from  ptl_sec_valution 
 where CDATE = date''${valDate}''-1
 and FINPROD_ID in (select FINPROD_ID from fp)
 group by portfolio_id
UNION ALL
SELECT t1.portfolio_id AS rkey,NVL(SUM(T1.FPRICE_AMT)*-1,0) AS rvalue

                   FROM TRD_FIN_PRODUCT_DEAL_TOTAL T1
                   LEFT JOIN PA_TRD_FIN_PRODUCT_DEAL_TOTAL T3  ON T1.TOTLE_TRADE_ID = T3.TOTLE_TRADE_ID
                   LEFT JOIN FIN_PRODUCT T4 ON T1.FINPROD_ID = T4.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT TC4 ON T1.CHL_ID = TC4.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT_TYPE FPROD_TYPE ON FPROD_TYPE.FINPROD_ID = T1.FINPROD_ID
                   LEFT JOIN FIN_PRODUCT_ADD FPA ON FPA.FINPROD_ID =  T1.FINPROD_ID
                  WHERE T1.TRADE_STATUS = ''01''
                    AND NVL(T1.IS_OVERDUE_DEAL, ''N'') = ''N''
                    AND T1.IS_CANCEL = ''N''
                    AND T1.FINPROD_TYPE2 = ''F07''
                    AND T1.P_TOTLE_TRADE_ID IS NULL
                    AND T3.PA_BUSS_TYPE = ''41''
                    AND t1.trade_date=date''${valDate}''
                    AND (FPA.IS_INADVANCE IS NULL OR FPA.IS_INADVANCE!=''Y'')
                  GROUP BY  t1.portfolio_id
                  UNION ALL
SELECT
    VAL.PORTFOLIO_ID RKEY,NVL(SUM(NVL(VAL.MARKET_VALUE,0)+nvl(VAL.TDY_INTINCEXP, 0)),0) RVALUE
FROM PTL_SEC_PIERCE_VALUTION VAL
INNER JOIN FIN_PRODUCT PRD ON VAL.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN FIN_PRODUCT_ADD FPA ON FPA.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN FIN_PRODUCT_TYPE FPT ON FPT.FINPROD_ID = PRD.FINPROD_ID
INNER JOIN PA_FIN_PRODUCT PFP ON PFP.FINPROD_ID = PRD.FINPROD_ID
WHERE VAL.CDATE = date''${valDate}'' - 1 AND PRD.FINPROD_TYPE2 = ''F07''
-- 这里是是否可提前支取的条件
and (FPA.IS_INADVANCE IS NULL OR FPA.IS_INADVANCE!=''Y'')
GROUP BY VAL.PORTFOLIO_ID
 )GROUP BY rkey';
	sql02 clob:='with fp as(
    select FINPROD_ID from FIN_PRODUCT_ADD
    where FINPROD_ID like ''F07_%'' and (IS_INADVANCE is null or IS_INADVANCE!=''Y'')
)
select sum(O_MARKET_VALUE) RVALUE from (
select sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) O_MARKET_VALUE
from ptl_sec_valution where  CDATE= date''${valDate}''-1
and FINPROD_ID in (select FINPROD_ID from fp) 
and ${portfolioCode} = portfolio_id
union all
SELECT
	case ps when ''T01'' then decode(''TRADE_STATUS'',''01'',trade_amt,-1* trade_amt)
	else decode(''TRADE_STATUS'',''01'',-1*trade_amt,trade_amt)
	end O_MARKET_VALUE
FROM TRD_PRODUCT_DEAL  tpd inner join TRD_PRODUCT_DEAL_ADD  tpda on tpd.trade_id= tpda.trade_id
where  tpd.finprod_id in (select FINPROD_ID from fp) 
and tpda.PORTFOLIO_ID=${portfolioCode} and tpd.trade_date = date''${valDate}''
union all
SELECT
	case ps when ''T01'' then decode(''TRADE_STATUS'',''01'',trade_amt,-1* trade_amt)
	else decode(''TRADE_STATUS'',''01'',-1*trade_amt,trade_amt)
	end O_MARKET_VALUE
FROM WFL_TRD_PRODUCT_DEAL  tpd inner join WFL_TRD_PRODUCT_DEAL_ADD  tpda on tpd.trade_id= tpda.trade_id
where  tpd.finprod_id in (select FINPROD_ID from fp) 
and tpda.PORTFOLIO_ID=${portfolioCode} and tpd.trade_date = date''${valDate}'')';
	sql03 clob:='select sum(decode(TDY_FLOAT_INGPL,null,0,TDY_FLOAT_INGPL))+sum(decode(DSC_COST_AMT,null,0,DSC_COST_AMT))+sum(decode(TDY_INTINCEXP,null,0,TDY_INTINCEXP)) RVALUE
  from ptl_sec_valution
 where CDATE= date''${valDate}''
 and FINPROD_ID in (
    select FINPROD_ID from FIN_PRODUCT_ADD
    where FINPROD_ID like ''F07_%'' and (IS_INADVANCE is null or IS_INADVANCE!=''Y'')
 )
 and ${portfolioCode} = portfolio_id';
begin
	update rsk_isup_indicator_para_dtl set content=sql01 where para_code = 'YGDQXYHCKGZ' and para_granularity in('01','02','03');
	update rsk_isup_indicator_para_dtl set content=sql02 where para_code = 'YGDQXYHCKGZ' and para_granularity ='04';
	update rsk_isup_indicator_para_dtl set content=sql03 where para_code = 'YGDQXYHCKGZ' and para_granularity ='05';
end;
/