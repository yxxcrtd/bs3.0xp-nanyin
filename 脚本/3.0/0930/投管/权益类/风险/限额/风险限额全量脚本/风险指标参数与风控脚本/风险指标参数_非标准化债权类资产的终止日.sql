/**风险指标参数:非标准化债权类资产的终止日**/
----风险监督指标参数

DELETE FROM RSK_ISUP_INDICATOR_PARA WHERE PARA_CODE ='FBZHZQLZCZZR';
INSERT INTO RSK_ISUP_INDICATOR_PARA VALUES('FBZHZQLZCZZR','非标准化债权类资产的终止日','N','','ysfu','C001',sysdate,'ysfu',sysdate,'','','N','String', '','', '');
---监督指标参数明细
DELETE FROM RSK_ISUP_INDICATOR_PARA_DTL WHERE PARA_CODE ='FBZHZQLZCZZR';
INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FBZHZQLZCZZR','01', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FBZHZQLZCZZR','02', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FBZHZQLZCZZR','03', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FBZHZQLZCZZR','04', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

INSERT INTO RSK_ISUP_INDICATOR_PARA_DTL VALUES('FBZHZQLZCZZR','05', 'SQL','SELECT 1 FROM DUAL', 'assetCode','N','ysfu','C001',sysdate,'ysfu',sysdate);

DECLARE
---- 实时、买入预占、卖出预占
V_CONTENT_01_02_03 CLOB := 'SELECT to_char(mdate,''yyyy-MM-dd'') AS RVALUE,
m.FINPROD_ID RKEY FROM  FIN_PRODUCT m
inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID 
and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))';

----事后(日间)
V_CONTENT_04 CLOB := 'select ceil(MDATE-date''${valDate}'') as RVALUE,FINPROD_ID as RKEY,FINPROD_NAME AS RKEYCN from
(
  select MDATE,FINPROD_ID,FINPROD_NAME, sum(TRADE_AMT) TRADE_AMT from(
  select nvl(sum(nvl(DSC_COST_AMT,0)+nvl(TDY_FLOAT_INGPL,0)+nvl(TDY_INTINCEXP,0)+ nvl(DELAY_PAY_AMT,0)),0) as TRADE_AMT,
         m.FINPROD_ID,n.MDATE,n.FINPROD_NAME
  from PTL_SEC_VALUTION m
  inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID  
  inner join FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID 
  and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))
  where m.PORTFOLIO_ID in ${portfolioCode}  and  m.CDATE =date''${valDate}''-1
  GROUP BY m.FINPROD_ID,n.MDATE,n.FINPROD_NAME
  union ALL select
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
 -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,
  n.FINPROD_ID,fp.MDATE,fp.FINPROD_NAME
  from TRD_PRODUCT_DEAL_ADD m
  inner join TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID
  inner join FIN_PRODUCT_TYPE fpt on n.FINPROD_ID=fpt.FINPROD_ID  and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))
  inner join FIN_PRODUCT fp on n.FINPROD_ID=fp.FINPROD_ID 
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in  (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'') 
  GROUP BY n.FINPROD_ID,fp.MDATE,fp.FINPROD_NAME
  union all  select
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
 -nvl(sum(decode(n.PS,''T54'',decode(TRADE_STATUS,''03'',n.TRADE_AMT*-1,n.TRADE_AMT))),0) as  TRADE_AMT,
 n.FINPROD_ID,fp.MDATE,fp.FINPROD_NAME
  from WFL_TRD_PRODUCT_DEAL_ADD m
  inner join WFL_TRD_PRODUCT_DEAL n on m.TRADE_ID=n.TRADE_ID and n.TRADE_STATUS != ''03''
  inner join FIN_PRODUCT_TYPE fpt on n.FINPROD_ID=fpt.FINPROD_ID  and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))
  inner join FIN_PRODUCT fp on n.FINPROD_ID=fp.FINPROD_ID 
  where m.PORTFOLIO_ID in ${portfolioCode} and n.TRADE_DATE=date''${valDate}'' and n.PS in (''T01'',''T02'',''T03'',''T04'',''T06'',''T08'',''T09'',''T11'',''T52'',''T19'',''T21'',''T22'',''T23'',''T38'',''T39'',''T54'')
  GROUP BY n.FINPROD_ID,fp.MDATE,fp.FINPROD_NAME) GROUP BY MDATE,FINPROD_ID,FINPROD_NAME
)where TRADE_AMT >0';

----事后(日终)
V_CONTENT_05 CLOB := 'select ceil(n.mdate-date''${valDate}'') as RVALUE,m.FINPROD_ID as RKEY,n.FINPROD_NAME AS RKEYCN   from PTL_SEC_VALUTION m
INNER JOIN FIN_PRODUCT n on m.FINPROD_ID=n.FINPROD_ID and n.FINPROD_TYPE2 IN (''F18'',''F01'')
inner join FIN_PRODUCT_TYPE fpt on m.FINPROD_ID=fpt.FINPROD_ID and ((fpt.TYPE_8 =''F06'' and fpt.TYPE_6 IN(''P01'',''P11'')) OR (fpt.TYPE_8 =''F01'' and fpt.TYPE_6 =''B18''))
where  m.PORTFOLIO_ID in ${portfolioCode} and m.CDATE = date''${valDate}''';
BEGIN
    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_01_02_03 WHERE PARA_CODE = 'FBZHZQLZCZZR' and PARA_GRANULARITY in('01','02','03');

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_04 WHERE PARA_CODE = 'FBZHZQLZCZZR' and PARA_GRANULARITY ='04';

    UPDATE RSK_ISUP_INDICATOR_PARA_DTL SET CONTENT = V_CONTENT_05 WHERE PARA_CODE = 'FBZHZQLZCZZR' and PARA_GRANULARITY = '05';
END;
/



----非标准化债权类资产的终止日-买入预占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FBZHZQLZCZZR_01';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FBZHZQLZCZZR_01','非标准化债权类资产的终止日-买入预占(不算指标)','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_01';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_01';

----非标准化债权类资产的终止日-卖出预占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FBZHZQLZCZZR_02';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FBZHZQLZCZZR_02','非标准化债权类资产的终止日-卖出预占(不算指标)','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_02';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_02';

----非标准化债权类资产的终止日-买入实占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FBZHZQLZCZZR_03';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FBZHZQLZCZZR_03','非标准化债权类资产的终止日-买入实占(不算指标)','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_03';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_03';

----非标准化债权类资产的终止日-卖出实占(不算指标)
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FBZHZQLZCZZR_04';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FBZHZQLZCZZR_04','非标准化债权类资产的终止日-卖出实占(不算指标)','N','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_04';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_04';

----非标准化债权类资产的终止日-买入预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FBZHZQLZCZZR_05';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FBZHZQLZCZZR_05','非标准化债权类资产的终止日-买入预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_05';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FBZHZQLZCZZR_05','assetType','c7a767b47888489bba4e71c3fbe76ef5','','F06','=','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FBZHZQLZCZZR_05','assetType2','c7a767b47888489bba4e71c3fbe76ef5','','P01,P11','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FBZHZQLZCZZR_05','eventType','c7a767b47888489bba4e71c3fbe76ef5','','0','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FBZHZQLZCZZR_05','ps','c7a767b47888489bba4e71c3fbe76ef5','','T01','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_05';
INSERT INTO RSK_EVENT_PARA_REL VALUES('FBZHZQLZCZZR','EVENT_ID_FBZHZQLZCZZR_05','02','+','custom','','system','',SYSDATE,'system',SYSDATE,'0','');

----非标准化债权类资产的终止日-卖出预占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FBZHZQLZCZZR_06';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FBZHZQLZCZZR_06','非标准化债权类资产的终止日-卖出预占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_06';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_06';

----非标准化债权类资产的终止日-买入实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FBZHZQLZCZZR_07';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FBZHZQLZCZZR_07','非标准化债权类资产的终止日-买入实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_07';
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FBZHZQLZCZZR_07','assetType','3b99d6682f674b93992e4098852ba441','','F06','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FBZHZQLZCZZR_07','assetType2','3b99d6682f674b93992e4098852ba441','','P01,P11','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FBZHZQLZCZZR_07','eventType','3b99d6682f674b93992e4098852ba441','','1,2','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);
INSERT INTO RSK_EVENT_ITEM_RULE VALUES('EVENT_ID_FBZHZQLZCZZR_07','ps','3b99d6682f674b93992e4098852ba441','','T01','IN','','','','admin','C001',SYSDATE,'jx',SYSDATE);

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_07';
INSERT INTO RSK_EVENT_PARA_REL VALUES('FBZHZQLZCZZR','EVENT_ID_FBZHZQLZCZZR_07','01','+','custom','','system','',SYSDATE,'system',SYSDATE,'0','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('FBZHZQLZCZZR','EVENT_ID_FBZHZQLZCZZR_07','02','+','custom','','system','',SYSDATE,'system',SYSDATE,'0','');
INSERT INTO RSK_EVENT_PARA_REL VALUES('FBZHZQLZCZZR','EVENT_ID_FBZHZQLZCZZR_07','03','+','custom','','system','',SYSDATE,'system',SYSDATE,'0','');

----非标准化债权类资产的终止日-卖出实占
DELETE FROM RSK_EVENT WHERE EVENT_CODE ='EVENT_ID_FBZHZQLZCZZR_08';
INSERT INTO RSK_EVENT VALUES ('EVENT_ID_FBZHZQLZCZZR_08','非标准化债权类资产的终止日-卖出实占','Y','','system','',sysdate,'system',sysdate);
         
 
DELETE FROM RSK_EVENT_ITEM_RULE WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_08';

DELETE FROM RSK_EVENT_PARA_REL WHERE EVENT_CODE = 'EVENT_ID_FBZHZQLZCZZR_08';



/**风险指标参数:非标准化债权类资产的终止日**/