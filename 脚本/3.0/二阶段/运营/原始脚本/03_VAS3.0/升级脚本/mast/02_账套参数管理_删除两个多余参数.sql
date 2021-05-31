--删除多余得账套参数：
--债券利息计算利率类型		01:票面利率,02:税后利率；此部分本来是在所得税需求中设计添加得，但是实际未开发，删除掉
--此部分参数非账套参数，而是系统参数
DELETE FROM BOK_BOOKSET_EXT  WHERE PARAM_CODE = 'BOND_INT_CALC_RATE_TYPE';
DELETE FROM BOK_BOOKSET_EXT  WHERE PARAM_CODE = 'PRICE_CAL_TYPE';
