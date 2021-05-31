--01.提取待处理数据
create table stl_orders_cl as
select s.ORDER_ID,
       s.BUSI_TYPE,
       s.ORDER_TYPE,
       s.SETTLE_DATE,
       s.TRADE_ID,
       s.MY_ACCT_NO,
       s.PS,
       s.COUNTER_ACCT_NO,
       s.SETTLE_AMT,
       s.CCY，
       S.SETTLE_STATUS,
       S.ORDER_STATUS,
       b.bookset_id,
       e.bok_detail_id,
       g.acct_no qsk_acct_no
  from stl_orders s
  left join bok_deal_accounting b
    on s.trade_id = b.busi_no
  left join bok_detail_id  e on b.bookset_id = e.bookset_id and s.my_acct_no = e.busi_id and e.busi_detail_type='A01'
     and s.ccy = e.ccy
  left join bok_bookset_capacct_info g on b.bookset_id = g.bookset_id and g.acct_type='A03' and s.ccy = g.ccy
 where s.order_type ='N8011'
 and exists (
   select 1 from trd_product_deal c where s.trade_id = c.trade_id
   and c.trade_status='01'
 )
 and not exists
 (select 1
          from stl_orders a
         where s.trade_id = a.trade_id
           and a.order_type = '8017')          
UNION ALL
select s.ORDER_ID,
       s.BUSI_TYPE,
       s.ORDER_TYPE,
       s.SETTLE_DATE,
       s.TRADE_ID,
       s.MY_ACCT_NO,
       s.PS,
       s.COUNTER_ACCT_NO,
       s.SETTLE_AMT,
       s.CCY，
       S.SETTLE_STATUS,
       S.ORDER_STATUS,
       b.bookset_id,
       e.bok_detail_id,
       g.acct_no qsk_acct_no
  from stl_orders s
  left join bok_deal_accounting b
    on s.trade_id = b.busi_no
  left join bok_detail_id  e on b.bookset_id = e.bookset_id and s.my_acct_no = e.busi_id and e.busi_detail_type='A01'
      and s.ccy = e.ccy
  left join bok_bookset_capacct_info g on b.bookset_id = g.bookset_id and g.acct_type='A03' and s.ccy = g.ccy
 where s.order_type ='N8031'
 and exists (
   select 1 from trd_product_deal c where s.trade_id = c.trade_id
   and c.trade_status='01'
 )
 and not exists
 (select 1
          from stl_orders a
         where s.trade_id = a.trade_id
           and a.order_type = '8018');
  
--02.生成资金收款指令数据
DELETE FROM STL_ORDERS_CL WHERE ORDER_TYPE IN ('8017','8018');
INSERT INTO STL_ORDERS_CL
SELECT 
  DECODE(F.ORDER_TYPE,'N8011','8017','N8031','8018')||SUBSTR(F.ORDER_ID, 6),
  F.BUSI_TYPE,
  DECODE(F.ORDER_TYPE,'N8011','8017','N8031','8018'),
  F.SETTLE_DATE,
  F.TRADE_ID,
  F.MY_ACCT_NO,
  F.PS,
  F.QSK_ACCT_NO,
  F.SETTLE_AMT,
  F.CCY，
  F.SETTLE_STATUS,
  F.ORDER_STATUS,
  F.BOOKSET_ID,
  F.BOK_DETAIL_ID,
  F.QSK_ACCT_NO
FROM STL_ORDERS_CL F;

COMMIT;

--03:8017、8018结算指令表数据生成
DELETE FROM STL_ORDERS H WHERE EXISTS (
 SELECT 1 FROM STL_ORDERS_CL F WHERE H.ORDER_ID = F.ORDER_ID AND F.ORDER_TYPE IN ('8017','8018')
);
INSERT INTO STL_ORDERS(
ORDER_ID,BUSI_TYPE,ORDER_TYPE,ORDER_NUM,
SETTLE_DATE,TRADE_ID,MY_ACCT_NO,PS,COUNTER_ACCT_NO,SETTLE_AMT,CCY,
SETTLE_STATUS,ORDER_STATUS,
P_ORDER_ID,F_ORDER_ID,
CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME
)
SELECT 
 F.ORDER_ID,F.BUSI_TYPE,F.ORDER_TYPE,NULL,
 F.SETTLE_DATE,F.TRADE_ID,F.MY_ACCT_NO,F.PS,F.COUNTER_ACCT_NO,F.SETTLE_AMT,F.CCY,
 F.SETTLE_STATUS,F.ORDER_STATUS,
 NULL,NULL,
 'sjzl',NULL,SYSDATE,'sjzl',SYSDATE
FROM STL_ORDERS_CL F
WHERE F.ORDER_TYPE IN ('8017','8018');

COMMIT;

--04：入账信息表数据生成
DELETE FROM BOK_DEAL_ACCOUNTING I
WHERE EXISTS (
 SELECT 1 FROM STL_ORDERS_CL F WHERE F.ORDER_TYPE IN ('8017','8018')
 AND I.BOOKSET_ID = F.BOOKSET_ID AND I.BUSI_NO = F.ORDER_ID  
);

INSERT INTO BOK_DEAL_ACCOUNTING(
BOOKSET_ID,BUSI_NO,BUSI_TABLE,
BOOKSET_DATE,BOOK_DATE,HAPPEN_DATE,
IS_ACCOUNTED,BOOK_ID,
CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME,
INV_AIM,INV_AIM_METHOD
)
SELECT 
  F.BOOKSET_ID,F.ORDER_ID,'01',
  F.SETTLE_DATE,F.SETTLE_DATE,F.SETTLE_DATE,
  'Y', NULL,
  'sjzl',NULL,SYSDATE,'sjzl',SYSDATE,
  NULL,NULL
FROM STL_ORDERS_CL F
WHERE F.ORDER_TYPE IN ('8017','8018');

COMMIT;


--05：入账信息明细表数据生成

DELETE FROM BOK_DEAL_ACCOUNTING_DETAIL I
WHERE EXISTS (
 SELECT 1 FROM STL_ORDERS_CL F WHERE F.ORDER_TYPE IN ('8017','8018')
 AND I.BOOKSET_ID = F.BOOKSET_ID AND I.BUSI_NO = F.ORDER_ID  
);

INSERT INTO BOK_DEAL_ACCOUNTING_DETAIL(
BOOKSET_ID,BUSI_NO,BUSI_TABLE,BUSI_TYPE,BOK_DETAIL_ID,
CREATE_USER,CREATE_DEPT,CREATE_TIME,UPDATE_USER,UPDATE_TIME
)
SELECT 
  F.BOOKSET_ID,
  F.ORDER_ID,
  '01',
  F.BUSI_TYPE,
  F.BOK_DETAIL_ID,
  'sjzl',NULL,SYSDATE,'sjzl',SYSDATE
FROM STL_ORDERS_CL F
WHERE F.ORDER_TYPE IN ('8017','8018');

COMMIT;

--06:8017、8018 结算指令扩展表数据初始化
INSERT INTO STL_ORDERS_EXT
  (ORDER_ID,
   IS_TRADE,
   CREATE_USER,
   CREATE_DEPT,
   CREATE_TIME,
   UPDATE_USER,
   UPDATE_TIME)
  SELECT SL.ORDER_ID, 'N' IS_TRADE, 'sjzl', null, sysdate, 'sjzl', sysdate
    FROM STL_ORDERS_CL SL
   WHERE NOT EXISTS
   (SELECT 1 FROM STL_ORDERS_EXT E WHERE SL.ORDER_ID = E.ORDER_ID)
     AND SL.ORDER_TYPE IN ('8017', '8018');

--07:N8011、N8031 结算指令数据更新
UPDATE STL_ORDERS S 
  SET S.MY_ACCT_NO = (SELECT F1.QSK_ACCT_NO FROM STL_ORDERS_CL F1 WHERE S.ORDER_ID = F1.ORDER_ID)
WHERE EXISTS (
  SELECT 1 FROM STL_ORDERS_CL F WHERE S.ORDER_ID = F.ORDER_ID
  AND F.ORDER_TYPE IN ('N8011','N8031')
);

COMMIT;

--07：删除中间表
DROP TABLE STL_ORDERS_CL;










 