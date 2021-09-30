--股票标的信息去除港股通市场字段必填校验
UPDATE VAL_TEMP_COL_CHECK_INFO T SET T.IS_NULL='N' WHERE T.TMP_E_NAME ='ValStockBaseInfo' AND t.col_e_name='shOrZh';