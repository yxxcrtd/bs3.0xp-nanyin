alter table FIN_PRODUCT_ADD add is_qtzq varchar2(50);
comment on column FIN_PRODUCT_ADD.is_qtzq
  is '是否其他债权，其他资产都为空，只有项目类资产为：Y；';
   
UPDATE FIN_PRODUCT_ADD A SET 
  A.IS_QTZQ = 'Y'
WHERE EXISTS (
  SELECT * FROM FIN_PRODUCT B WHERE A.FINPROD_ID = B.FINPROD_ID 
  AND B.FINPROD_TYPE='F17' AND B.FINPROD_TYPE2='F18'
);