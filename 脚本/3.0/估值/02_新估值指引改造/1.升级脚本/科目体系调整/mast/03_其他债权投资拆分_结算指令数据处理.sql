UPDATE STL_ORDERS  S
SET S.ORDER_TYPE = DECODE(S.ORDER_TYPE,'2705','2750','2706','2751'),
    S.UPDATE_USER = 'sjzl',S.UPDATE_TIME = SYSDATE
WHERE EXISTS (
  select 
    1
  from trd_product_deal a
  left join fin_product_add b on a.finprod_id = b.finprod_id
  where a.finprod_type='F17' and b.is_qtzq='Y'
  and exists (
    select 1 from bok_deal_accounting c where a.trade_id = c.busi_no
  )
  and s.trade_id = a.trade_id
)
AND S.ORDER_TYPE IN ('2705','2706');