/**风险指标参数:产品净资产**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='CPJZCJZ';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('CPJZCJZ','产品净资产','N','','cdj','C001',sysdate,'cdj',sysdate,'','','Y','String', '','', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='CPJZCJZ';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPJZCJZ','01', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPJZCJZ','02', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPJZCJZ','03', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPJZCJZ','04', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('CPJZCJZ','05', 'SQL','SELECT 1 FROM DUAL', 'portfolioCode','N','cdj','C001',sysdate,'cdj',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'WITH  temp  as(
  SELECT
       DISTINCT t2.PORTFOLIO_ID ,
       mujiData.mujiAmt  as AMOUNT
       from  PTL_PORTFOLIO t2
       left join
       (
       select t1.PORTFOLIO_ID, sum(t1.TRADE_AMT) as mujiAmt from TRD_FIN_PRODUCT_DEAL_TOTAL t1
       WHERE  t1.PS in (''C1'', ''C2'')  and t1.SETTLE_DATE <= date''${valDate}'' and t1.TRADE_STATUS=''01''
       group by t1.PORTFOLIO_ID
       ) mujiData
       on t2.PORTFOLIO_ID = mujiData.PORTFOLIO_ID
       where  t2.VDATE=date''${valDate}''
 UNION ALL
      SELECT
       DISTINCT t1.PORTFOLIO_ID RKEY,
        nvl(TDY_ASSET_NETVALUE,0) as RVALUE
       from PTL_PORTFOLIO_VALUATION t1
       where  t1.CDATE=date''${valDate}''-1
 )
 SELECT PORTFOLIO_ID RKEY,sum(AMOUNT) RVALUE FROM temp
 group by PORTFOLIO_ID
union all
   select m.PTL_BAG_ID as RKEY,
   (
        SELECT sum(AMOUNT) FROM (
          SELECT PORTFOLIO_ID,AMOUNT FROM temp
       ) t1 where t1.PORTFOLIO_ID in (select t1.PORTFOLIO_ID from RSK_PORTFOLIO_PACKAGE_DTL t1
       inner join PTL_PORTFOLIO t2 on t1.PORTFOLIO_ID=t2.PORTFOLIO_ID and t2.MDATE>date''${valDate}'' where t1.PTL_BAG_ID=m.PTL_BAG_ID)
   ) AS RVALUE
   from RSK_PORTFOLIO_PACKAGE m';

----事后(日间)
V_CONTENT_04 CLOB := 'select sum(amount) AS amount from
  (
      select sum(amount) as amount
     from
     (
      SELECT
       DISTINCT t1.PORTFOLIO_ID PORTFOLIO_ID,
      nvl(TDY_ASSET_NETVALUE,0) as AMOUNT
       from PTL_PORTFOLIO_VALUATION t1
       where  t1.CDATE=date''${valDate}''-1 and t1.PORTFOLIO_ID in ${portfolioCode}
     )
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
    -nvl(sum(decode(n.PS,''C14'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C16'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C18'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C15'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount
    from TRD_PRODUCT_DEAL_ADD m
    inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
    left join PTL_PORTFOLIO t2  on t2.portfolio_id =  m.portfolio_id
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' group by m.PORTFOLIO_ID
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
    -nvl(sum(decode(n.PS,''C14'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C16'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C17'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C18'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C19'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C15'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0)
    -nvl(sum(decode(n.PS,''C20'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as amount
    from WFL_TRD_PRODUCT_DEAL_ADD m
    inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
    INNER JOIN SYS_WFL_BUSINESS_LIST swbl on n.WORKFLOW_ID = swbl.SEQ_NO and swbl.PROCESS_STATE != ''BACK''
    where m.PORTFOLIO_ID in ${portfolioCode}  and n.TRADE_DATE=date''${valDate}'' group by m.PORTFOLIO_ID
  )';

----事后(日终)
V_CONTENT_05 CLOB := 'SELECT NVL(SUM(TDY_ASSET_NETVALUE),0) AS amount
  FROM PTL_PORTFOLIO_VALUATION
  WHERE PORTFOLIO_ID IN ${portfolioCode} AND CDATE = date''${valDate}''';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'CPJZCJZ' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'CPJZCJZ' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'CPJZCJZ' and PARA_GRANULARITY = '05';
END;
/



----产品净资产-买入预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPJZCJZ_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPJZCJZ_01','产品净资产-买入预占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_01';

----产品净资产-卖出预占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPJZCJZ_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPJZCJZ_02','产品净资产-卖出预占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_02';

----产品净资产-买入实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPJZCJZ_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPJZCJZ_03','产品净资产-买入实占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_03';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPJZCJZ_03','eventType','10e77f8bd98d47079b797a652ec6f1fe','','1,2','IN','','','','system','',SYSDATE,'system',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPJZCJZ_03','ps','10e77f8bd98d47079b797a652ec6f1fe','','C1,C2,C3','IN','','','','system','',SYSDATE,'system',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_03';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPJZCJZ','EVENT_ID_CPJZCJZ_03','01','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPJZCJZ','EVENT_ID_CPJZCJZ_03','02','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPJZCJZ','EVENT_ID_CPJZCJZ_03','03','+','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----产品净资产-卖出实占（不算指标）
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPJZCJZ_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPJZCJZ_04','产品净资产-卖出实占（不算指标）','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_04';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPJZCJZ_04','eventType','268d34b386904bbda524dff51509c407','','1,2','IN','','','','admin','C001',SYSDATE,'ysfu',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_CPJZCJZ_04','ps','268d34b386904bbda524dff51509c407','','C4,C5,C6,C7,C9,C14,C16,C17,C18,C19,C20,C15','IN','','','','admin','C001',SYSDATE,'ysfu',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_04';
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPJZCJZ','EVENT_ID_CPJZCJZ_04','01','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPJZCJZ','EVENT_ID_CPJZCJZ_04','02','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('CPJZCJZ','EVENT_ID_CPJZCJZ_04','03','-','amount','','system','',SYSDATE,'system',SYSDATE,'','');

----产品净资产-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPJZCJZ_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPJZCJZ_05','产品净资产-买入预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_05';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_05';

----产品净资产-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPJZCJZ_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPJZCJZ_06','产品净资产-卖出预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_06';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_06';

----产品净资产-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPJZCJZ_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPJZCJZ_07','产品净资产-买入实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_07';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_07';

----产品净资产-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_CPJZCJZ_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_CPJZCJZ_08','产品净资产-卖出实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_08';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_CPJZCJZ_08';



/**风险指标参数:产品净资产**/