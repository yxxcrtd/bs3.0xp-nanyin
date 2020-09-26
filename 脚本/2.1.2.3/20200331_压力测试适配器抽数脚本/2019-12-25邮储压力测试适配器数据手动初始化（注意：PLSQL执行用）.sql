delete from stt_object;
delete from stt_object_attr;
delete from stt_element;
delete from stt_element_attr;
delete from stt_stat_element;

--对象表初始化
insert into stt_object
  SELECT PORTFOLIO_ID   AS OBJECT_ID,
         PORTFOLIO_NAME AS OBJECT_NAME,
         PORTFOLIO_ID   AS SOURCE_ID,
         null,
         'system',
         'rsk',
         systimestamp,
         UPDATE_USER,
         UPDATE_TIME
    FROM PTL_PORTFOLIO;

--对象属性表初始化
insert into stt_object_attr
  select *
    from (SELECT B.PORTFOLIO_ID AS OBJECT_ID,
                 'PORTFOLIO_TYPE' AS PARAM_CODE,
                 '04' AS PARAM_TYPE,
                 B.PORTFOLIO_TYPE AS PARAM_VALUE,
                 'PORTFOLIO_TYPE' AS DICT_CODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATE_USER,
                 'system' AS CREATE_DEPT,
                 systimestamp,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM PTL_PORTFOLIO B
           WHERE B.PORTFOLIO_TYPE IS NOT NULL
          UNION ALL
          SELECT C.PORTFOLIO_ID AS OBJECT_ID,
                 'PROFIT_TYPE' AS PARAM_CODE,
                 '04' AS PARAM_TYPE,
                 C.PROFIT_TYPE AS PARAM_VALUE,
                 'PROFIT_TYPE' AS DICT_CODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATE_USER,
                 'system' AS CREATE_DEPT,
                 systimestamp,
                 C.UPDATE_USER,
                 C.UPDATE_TIME
            FROM PTL_PORTFOLIO C
           WHERE C.PROFIT_TYPE IS NOT NULL);

--元素表初始化
insert into stt_element
  SELECT A.FINPROD_ID AS ELEMENT_ID,
         A.OBJECTNAME AS OBJECT_NAME,
         A.SOURCEID AS SOURCE_ID,
         A.FINPROD_TYPE2 AS SOURCE_TYPE, --金融产品类型（投管分类）
         T1.TYPE_8 AS TYPE_1, --投管资产大类--投管资产一级分类
         T1.TYPE_6 AS TYPE_2, --投管一级类型--投管资产二级分类
         T1.TYPE_7 AS TYPE_3, --投管二级类型--投管资产三级分类
         NULL AS TYPE_4,
         NULL AS TYPE_5,
         'P_IMPORT_PROD_DATA' AS CREATE_USER,
         'system' AS CREATE_DEPT,
         systimestamp as create_Time,
         A.UPDATE_USER,
         A.UPDATE_TIME
    from ((select nvl(b.finprod_id, c.finprod_id) as finprod_id,
                  nvl(b.finprod_name, c.finprod_id) as objectName,
                  nvl(nvl(b.finprod_market_id,b.finprod_id), c.finprod_id) as sourceId,
                  NVL(B.FINPROD_TYPE2, C.FINPROD_TYPE2) AS FINPROD_TYPE2, --金融产品类型（投管分类）
                  nvl(b.update_user, c.update_user) as update_user,
                  nvl(b.update_time, c.update_time) as update_time,
                  c.branch
             from fin_product b
             full join (select * from fin_trade_product where branch = 0 or branch is null) c
               on b.finprod_id = c.finprod_id )) a
    left join fin_product_type t1
      on a.finprod_id = t1.finprod_id
     and (a.branch is null or a.branch = t1.branch);

--元素属性表初始化
insert into stt_element_attr
  select *
    from (SELECT B.FINPROD_ID AS ELEMENT_ID,
                 'P_FINPROD_ID' AS PARAM_CODE,
                 '02' AS PARAM_TYPE,
                 C.P_FINPROD_ID AS PARAM_VALUE,
                 'P_FINPROD_ID' AS DICT_CODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATE_USER,
                 'system' AS CREATE_DEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT_TYPE B
          --不能用左连接，因为PARAMVALUE必须不为NULL
           INNER JOIN FIN_PRODUCT C
              ON B.FINPROD_ID = C.FINPROD_ID
             AND C.P_FINPROD_ID IS NOT NULL
          UNION ALL
        SELECT NVL(X.FINPROD_ID,Y.FINPROD_ID)  AS ELEMENT_ID,
				   'PROFIT_TYPE' AS PARAMCODE,
				   '04' AS PARAMTYPE,
				   NVL(X.PARAMVALUE,Y.PARAMVALUE) AS PARAMVALUE,
				   'PROFIT_TYPE' AS DICTCODE,
				   NULL AS REMARK,
				   'P_IMPORT_PROD_DATA' AS CREATEUSER,
				   'system' AS CREATEDEPT,
				   SYSTIMESTAMP AS CREATETIME,
				   NVL(X.UPDATE_USER,Y.UPDATE_USER) as UPDATE_USER,
				   NVL(X.UPDATE_TIME,Y.UPDATE_TIME) as UPDATE_TIME
		FROM (SELECT C.FINPROD_ID,
				   'PROFIT_TYPE' AS PARAMCODE,
				   '04' AS PARAMTYPE,
				   C.PROFIT_TYPE AS PARAMVALUE,
				   'PROFIT_TYPE' AS DICTCODE,
				   NULL AS REMARK,
				   'P_IMPORT_PROD_DATA' AS CREATEUSER,
				   'system' AS CREATEDEPT,
				   SYSTIMESTAMP AS CREATETIME,
				   C.UPDATE_USER,
				   C.UPDATE_TIME
			 FROM FIN_PRODUCT C
			 WHERE C.PROFIT_TYPE IS NOT NULL) X
		FULL JOIN
			(SELECT C.FINPROD_ID,
				   'PROFIT_TYPE' AS PARAMCODE,
				   '04' AS PARAMTYPE,
				   C.PROFIT_TYPE AS PARAMVALUE,
				   'PROFIT_TYPE' AS DICTCODE,
				   NULL AS REMARK,
				   'P_IMPORT_PROD_DATA' AS CREATEUSER,
				   'system' AS CREATEDEPT,
				   SYSTIMESTAMP AS CREATETIME,
				   C.UPDATE_USER,
				   C.UPDATE_TIME
			 FROM FIN_TRADE_PRODUCT C
			   WHERE C.BRANCH = 0
			   AND C.PROFIT_TYPE IS NOT NULL) Y
		ON X.FINPROD_ID = Y.FINPROD_ID
          UNION ALL
          SELECT B.FINPROD_ID,
                 'FINPROD_TYPE2' AS PARAMCODE,
                 '04' AS PARAMTYPE,
                 C.FINPROD_TYPE2 AS PARAMVALUE,
                 'FINPROD_TYPE_TG' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT_TYPE B
           INNER JOIN FIN_TRADE_PRODUCT C
              ON B.FINPROD_ID = C.FINPROD_ID
             AND C.BRANCH = 0
             AND C.FINPROD_TYPE2 IS NOT NULL
          UNION ALL
          SELECT NVL(X.FINPROD_ID, Y.FINPROD_ID) AS FINPROD_ID,
                 'MDATE' AS PARAMCODE,
                 '03' AS PARAMTYPE,
                 NVL(X.PARAMVALUE, Y.PARAMVALUE) AS PARAMVALUE,
                 'MDATE' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 NVL(X.UPDATE_USER, Y.UPDATE_USER) AS UPDATE_USER,
                 NVL(X.UPDATE_TIME, Y.UPDATE_TIME) AS UPDATE_TIME
            FROM (SELECT B.FINPROD_ID,
                         TO_CHAR(C.MDATE, 'YYYY-MM-DD') AS PARAMVALUE,
                         B.UPDATE_USER,
                         B.UPDATE_TIME
                    FROM FIN_PRODUCT_TYPE B
                   INNER JOIN FIN_TRADE_PRODUCT C ---交易类
                      ON B.FINPROD_ID = C.FINPROD_ID
                     AND C.BRANCH = 0
                     AND C.MDATE IS NOT NULL) X
            FULL JOIN (SELECT B.FINPROD_ID,
                             TO_CHAR(C.MDATE, 'YYYY-MM-DD') AS PARAMVALUE,
                             B.UPDATE_USER,
                             B.UPDATE_TIME
                        FROM FIN_PRODUCT_TYPE B
                       INNER JOIN FIN_PRODUCT C ---------标的类
                          ON B.FINPROD_ID = C.FINPROD_ID
                         AND C.MDATE IS NOT NULL) Y
              ON X.FINPROD_ID = Y.FINPROD_ID
          UNION ALL
          SELECT B.FINPROD_ID,
                 'M_TRADE_AMT' AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 TO_CHAR(C.M_TRADE_AMT) AS PARAMVALUE,
                 'M_TRADE_AMT' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT_TYPE B
           INNER JOIN FIN_TRADE_PRODUCT C
              ON B.FINPROD_ID = C.FINPROD_ID
             AND C.BRANCH = 0
             AND C.M_TRADE_AMT IS NOT NULL
          UNION ALL
          SELECT B.FINPROD_ID,
                 'TYPE_8' AS PARAMCODE,
                 '04' AS PARAMTYPE,
                 B.TYPE_8 AS PARAMVALUE,
                 'PINAN_ASSET_TYPE' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT_TYPE B
           WHERE B.TYPE_8 IS NOT NULL
          UNION ALL
          SELECT B.FINPROD_ID,
                 'TYPE_9' AS PARAMCODE,
                 '04' AS PARAMTYPE,
                 B.TYPE_9 AS PARAMVALUE,
                 'CUSTOM_ASSET_TYPE_TWO' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT_TYPE B
           WHERE B.TYPE_9 IS NOT NULL
          UNION ALL
          SELECT B.FINPROD_ID,
                 'TYPE_10' AS PARAMCODE,
                 '04' AS PARAMTYPE,
                 B.TYPE_10 AS PARAMVALUE,
                 'CUSTOM_ASSET_TYPE_THREE' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT_TYPE B
           WHERE B.TYPE_10 IS NOT NULL
          UNION ALL
          SELECT B.FINPROD_ID,
                 'TYPE_11' AS PARAMCODE,
                 '04' AS PARAMTYPE,
                 B.TYPE_11 AS PARAMVALUE,
                 'CUSTOM_ASSET_TYPE_FOUR' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT_TYPE B
           WHERE B.TYPE_11 IS NOT NULL
          UNION ALL
          SELECT B.FINPROD_ID,
                 'TYPE_12' AS PARAMCODE,
                 '04' AS PARAMTYPE,
                 B.TYPE_12 AS PARAMVALUE,
                 'CUSTOM_ASSET_TYPE_FIVE' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT_TYPE B
           WHERE B.TYPE_12 IS NOT NULL
          UNION ALL
          SELECT C.FINPROD_ID,
			   'CCY' AS PARAMCODE,
			   '04' AS PARAMTYPE,
			   C.CCY AS PARAMVALUE,
			   'CCY' AS DICTCODE,
			   NULL AS REMARK,
			   'P_IMPORT_PROD_DATA' AS CREATEUSER,
			   'system' AS CREATEDEPT,
			   SYSTIMESTAMP AS CREATETIME,
			   C.UPDATE_USER,
			   C.UPDATE_TIME
		  FROM  (select nvl(a.finprod_id,b.finprod_id) finprod_id, 
                    nvl(a.ccy,b.ccy) ccy,
                    nvl(a.update_user,b.update_user) update_user,
                    nvl(a.update_time,b.update_time) update_time
          from fin_product a
        full join (select * from fin_trade_product where BRANCH = 0) b  on a.finprod_id=b.finprod_id) C
      where C.CCY IS NOT NULL
          UNION ALL
          SELECT B.FINPROD_ID AS ELEMENT_ID,
                 'BENCHMARK_ID' AS PARAM_CODE,
                 '04' AS PARAM_TYPE,
                 B.BENCHMARK_ID AS PARAM_VALUE,
                 'SECRATE_DATA' AS DICT_CODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATE_USER,
                 'system' AS CREATE_DEPT,
                 SYSTIMESTAMP AS CREATE_TIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_RATE_INFO B, FIN_PRODUCT_TYPE C
           WHERE B.BENCHMARK_ID IS NOT NULL
             AND B.FINPROD_ID = C.FINPROD_ID
             AND C.TYPE_8 = 'F16'
          UNION ALL
          SELECT B.FINPROD_ID AS ELEMENT_ID,
                 'CUR_PAIR' AS PARAM_CODE,
                 '04' AS PARAM_TYPE,
                 replace(B.CUR_PAIR, '/', '') AS PARAMVALUE,
                 'CYY_PAIR' AS DICT_CODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATE_USER,
                 'system' AS CREATE_DEPT,
                 SYSTIMESTAMP AS CREATE_TIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_TRADE_PRODUCT B, FIN_PRODUCT_TYPE C
           WHERE B.CUR_PAIR IS NOT NULL
             AND B.FINPROD_ID = C.FINPROD_ID
             AND C.TYPE_8 in ('F13', 'F14', 'F15')
			 AND B.BRANCH = 0
          UNION ALL
          SELECT B.FINPROD_ID AS ELEMENT_ID,
                 'IS_RIGHT' AS PARAM_CODE,
                 '04' AS PARAM_TYPE,
                 B.IS_RIGHT AS PARAM_VALUE,
                 'IS_RIGHT' AS DICT_CODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATE_USER,
                 'system' AS CREATE_DEPT,
                 SYSTIMESTAMP AS CREATE_TIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT B
           WHERE B.IS_RIGHT IS NOT NULL
             AND B.FINPROD_TYPE2 = 'F01'
          UNION ALL
          SELECT B.FINPROD_ID AS ELEMENT_ID,
                 'INT_TYPE' AS PARAM_CODE,
                 '04' AS PARAM_TYPE,
                 T6.INT_TYPE AS PARAM_VALUE,
                 'INT_TYPE' AS DICT_CODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATE_USER,
                 'system' AS CREATE_DEPT,
                 SYSTIMESTAMP AS CREATE_TIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_PRODUCT B
            left join FIN_CASH_INFO T5
              ON T5.FINPROD_ID = B.FINPROD_ID
            LEFT JOIN FIN_RATE_INFO T6
              ON T6.CASH_ID = T5.CASH_ID
           where T6.INT_TYPE is not null
             and B.Finprod_Type2 = 'F01'
          UNION ALL
          SELECT B.FINPROD_ID,
                 'IRS_TYPE' AS PARAMCODE,
                 '04' AS PARAMTYPE,
                 B.IRS_TYPE AS PARAMVALUE,
                 'IRS_TYPE' AS DICTCODE,
                 NULL AS REMARK,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 SYSTIMESTAMP AS CREATETIME,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM FIN_TRADE_PRODUCT B
           where B.Finprod_Type2 = 'F14'
		   and B.BRANCH = 0 );

--元素统计项表初始化
insert into stt_stat_element
  select to_char(SEQ_STT_STAT_ELEMENT.NEXTVAL),
         abs.OBJECTID,
         abs.ELEMENTID,
         abs.CDATE,
         abs.INVAIM,
         abs.SECACCIID,
         abs.PARAMCODE,
         abs.PARAMTYPE,
         abs.PARAMVALUE,
         abs.DICTCODE,
         abs.CREATEUSER,
         abs.CREATEDEPT,
         systimestamp,
         abs.UPDATE_USER,
         abs.UPDATE_TIME
    from (SELECT *
            FROM (SELECT F.PORTFOLIO_ID AS OBJECTID,
                         F.FINPROD_ID AS ELEMENTID,
                         F.CDATE,
                         F.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'SHARE_AMT' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         TO_CHAR(NVL(F.finally_amt, 0)) AS PARAMVALUE,
                         'SHARE_AMT' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         F.UPDATE_USER,
                         F.UPDATE_TIME
                    FROM (SELECT RESULT1.PORTFOLIO_ID,
                                 RESULT1.FINPROD_ID,
                                 RESULT1.CDATE,
                                 RESULT1.INV_AIM,
                                 NVL(RESULT1.SHARE_AMT, 0) +
                                 NVL(RESULT2.no_amt, 0) as finally_amt,
                                 NVL(RESULT1.DSC_COST_AMT, 0) +
                                 NVL(RESULT2.no_cost, 0) as finally_cost,
                                 NVL(RESULT1.TDY_FLOAT_INGPL, 0) *
                                 (NVL(RESULT1.SHARE_AMT, 0) +
                                  NVL(RESULT2.no_amt, 0)) /
                                 DECODE(RESULT1.SHARE_AMT,
                                        0,
                                        null,
                                        RESULT1.SHARE_AMT) as finally_GY,
                                 NVL(RESULT1.TDY_INTINCEXP, 0) +
                                 NVL(RESULT2.no_lixi, 0) as finally_LX,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            FROM (SELECT F.PORTFOLIO_ID,
                                         F.FINPROD_ID,
                                         F.CDATE,
                                         F.INV_AIM,
                                         F.SHARE_AMT,
                                         F.TDY_FLOAT_INGPL,
                                         F.DSC_COST_AMT,
                                         F.TDY_INTINCEXP
                                    FROM PTL_SEC_VALUTION F
                                    left join fin_product fp
                                      on fp.finprod_id = F.FINPROD_ID
                                   WHERE F.CDATE <= TRUNC(SYSDATE - 1)
                                     AND F.SHARE_AMT IS NOT NULL
                                     AND fp.finprod_type2 = 'F01') RESULT1
                            LEFT JOIN (select tpda.portfolio_id,
                                             tpd.finprod_id,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.share_amt,
                                                        tpd.share_amt)) as no_amt,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.cprice_amt,
                                                        tpd.cprice_amt)) as no_cost,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.int_amt,
                                                        tpd.int_amt)) as no_lixi,
                                             tpd.inv_aim
                                        from TRD_PRODUCT_DEAL     tpd,
                                             TRD_PRODUCT_DEAL_ADD tpda
                                       where tpd.trade_id = tpda.trade_id
                                         and tpd.finprod_type2 = 'F01'
                                         and tpd.trade_date <=
                                             TRUNC(sysdate - 1)
                                         and TRUNC(sysdate - 1) <
                                             tpd.settle_date
                                       group by tpda.portfolio_id,
                                                tpd.finprod_id,
                                                tpd.inv_aim) RESULT2
                              ON RESULT1.PORTFOLIO_ID = RESULT2.PORTFOLIO_ID
                             AND RESULT1.FINPROD_ID = RESULT2.FINPROD_ID
                             AND RESULT1.INV_AIM = RESULT2.INV_AIM) F
                  UNION ALL
                  SELECT F.PORTFOLIO_ID AS OBJECTID,
                         F.FINPROD_ID AS ELEMENTID,
                         F.CDATE,
                         F.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'SHARE_AMT' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         TO_CHAR(F.share_amt) AS PARAMVALUE,
                         'SHARE_AMT' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         F.UPDATE_USER,
                         F.UPDATE_TIME
                    FROM (select psv.cdate,
                                 psv.portfolio_id,
                                 psv.finprod_id,
                                 psv.inv_aim,
                                 psv.share_amt,
                                 psv.tdy_float_ingpl,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            from ptl_sec_valution psv
                            left join fin_trade_product ftp
                              on psv.finprod_id = ftp.finprod_id
                           where nvl(ftp.finprod_type2, 'notFound') in
                                 ('F12', 'F13', 'F14')
                             and psv.cdate <= trunc(sysdate - 1)) F
                  UNION ALL
                  SELECT F.PORTFOLIO_ID AS OBJECTID,
                         F.FINPROD_ID AS ELEMENTID,
                         F.CDATE,
                         F.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'SHARE_AMT' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         TO_CHAR(nvl(F.share_amt, 0)) AS PARAMVALUE,
                         'SHARE_AMT' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         F.UPDATE_USER,
                         F.UPDATE_TIME
                    FROM (select psv.cdate,
                                 psv.portfolio_id,
                                 psv.finprod_id,
                                 psv.inv_aim,
                                 psv.share_amt,
                                 psv.tdy_float_ingpl,
                                 psv.dsc_cost_amt,
                                 psv.tdy_intincexp,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            from ptl_sec_valution psv
                            left join fin_trade_product ftp
                              on psv.finprod_id = ftp.finprod_id
                            left join fin_product fp
                              on psv.finprod_id = fp.finprod_id
                           where nvl(nvl(ftp.finprod_type2, fp.finprod_type2),
                                     'notFound') not in
                                 ('F01', 'F12', 'F13', 'F14', 'notFound')
                             and psv.cdate <= trunc(sysdate - 1)) F
                  UNION ALL
                  SELECT G.PORTFOLIO_ID AS OBJECTID,
                         G.FINPROD_ID AS ELEMENTID,
                         G.CDATE,
                         G.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'MARKET_VALUE' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         TO_CHAR(NVL(G.finally_cost, 0) +
                                 NVL(G.finally_GY, 0) + NVL(G.finally_LX, 0)) AS PARAMVALUE,
                         'MARKET_VALUE' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         G.UPDATE_USER,
                         G.UPDATE_TIME
                    FROM (SELECT RESULT1.PORTFOLIO_ID,
                                 RESULT1.FINPROD_ID,
                                 RESULT1.CDATE,
                                 RESULT1.INV_AIM,
                                 NVL(RESULT1.SHARE_AMT, 0) +
                                 NVL(RESULT2.no_amt, 0) as finally_amt,
                                 NVL(RESULT1.DSC_COST_AMT, 0) +
                                 NVL(RESULT2.no_cost, 0) as finally_cost,
                                 NVL(RESULT1.TDY_FLOAT_INGPL, 0) *
                                 (NVL(RESULT1.SHARE_AMT, 0) +
                                  NVL(RESULT2.no_amt, 0)) /
                                 DECODE(RESULT1.SHARE_AMT,
                                        0,
                                        null,
                                        RESULT1.SHARE_AMT) as finally_GY,
                                 NVL(RESULT1.TDY_INTINCEXP, 0) +
                                 NVL(RESULT2.no_lixi, 0) as finally_LX,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            FROM (SELECT F.PORTFOLIO_ID,
                                         F.FINPROD_ID,
                                         F.CDATE,
                                         F.INV_AIM,
                                         F.SHARE_AMT,
                                         F.TDY_FLOAT_INGPL,
                                         F.DSC_COST_AMT,
                                         F.TDY_INTINCEXP
                                    FROM PTL_SEC_VALUTION F
                                    left join fin_product fp
                                      on fp.finprod_id = F.FINPROD_ID
                                   WHERE F.CDATE <= TRUNC(SYSDATE - 1)
                                     AND F.SHARE_AMT IS NOT NULL
                                     AND fp.finprod_type2 = 'F01') RESULT1
                            LEFT JOIN (select tpda.portfolio_id,
                                             tpd.finprod_id,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.share_amt,
                                                        tpd.share_amt)) as no_amt,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.cprice_amt,
                                                        tpd.cprice_amt)) as no_cost,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.int_amt,
                                                        tpd.int_amt)) as no_lixi,
                                             tpd.inv_aim
                                        from TRD_PRODUCT_DEAL     tpd,
                                             TRD_PRODUCT_DEAL_ADD tpda
                                       where tpd.trade_id = tpda.trade_id
                                         and tpd.finprod_type2 = 'F01'
                                         and tpd.trade_date <=
                                             TRUNC(sysdate - 1)
                                         and TRUNC(sysdate - 1) <
                                             tpd.settle_date
                                       group by tpda.portfolio_id,
                                                tpd.finprod_id,
                                                tpd.inv_aim) RESULT2
                              ON RESULT1.PORTFOLIO_ID = RESULT2.PORTFOLIO_ID
                             AND RESULT1.FINPROD_ID = RESULT2.FINPROD_ID
                             AND RESULT1.INV_AIM = RESULT2.INV_AIM) G
                  UNION ALL
                  SELECT G.PORTFOLIO_ID AS OBJECTID,
                         G.FINPROD_ID AS ELEMENTID,
                         G.CDATE,
                         G.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'MARKET_VALUE' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         TO_CHAR(NVL(G.tdy_float_ingpl, 0)) AS PARAMVALUE,
                         'MARKET_VALUE' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         G.UPDATE_USER,
                         G.UPDATE_TIME
                    FROM (select psv.cdate,
                                 psv.portfolio_id,
                                 psv.finprod_id,
                                 psv.inv_aim,
                                 psv.share_amt,
                                 psv.tdy_float_ingpl,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            from ptl_sec_valution psv
                            left join fin_trade_product ftp
                              on psv.finprod_id = ftp.finprod_id
                           where nvl(ftp.finprod_type2, 'notFound') in
                                 ('F12', 'F13', 'F14')
                             and psv.cdate <= trunc(sysdate - 1)) G
                  UNION ALL
                  SELECT G.PORTFOLIO_ID AS OBJECTID,
                         G.FINPROD_ID AS ELEMENTID,
                         G.CDATE,
                         G.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'MARKET_VALUE' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         TO_CHAR(NVL(G.tdy_float_ingpl, 0) +
                                 NVL(G.dsc_cost_amt, 0) +
                                 NVL(G.tdy_intincexp, 0)) AS PARAMVALUE,
                         'MARKET_VALUE' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         G.UPDATE_USER,
                         G.UPDATE_TIME
                    FROM (select psv.cdate,
                                 psv.portfolio_id,
                                 psv.finprod_id,
                                 psv.inv_aim,
                                 psv.share_amt,
                                 psv.tdy_float_ingpl,
                                 psv.dsc_cost_amt,
                                 psv.tdy_intincexp,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            from ptl_sec_valution psv
                            left join fin_trade_product ftp
                              on psv.finprod_id = ftp.finprod_id
                            left join fin_product fp
                              on psv.finprod_id = fp.finprod_id
                           where nvl(nvl(ftp.finprod_type2, fp.finprod_type2),
                                     'notFound') not in
                                 ('F01', 'F12', 'F13', 'F14', 'notFound')
                             and psv.cdate <= trunc(sysdate - 1)) G
                  UNION ALL
                  SELECT G.PORTFOLIO_ID AS OBJECTID,
                         G.FINPROD_ID AS ELEMENTID,
                         G.CDATE,
                         G.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'CURRENT_PRICE' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         to_char((NVL(G.finally_cost, 0) +
                                 NVL(G.finally_GY, 0) + NVL(G.finally_LX, 0)) /
                                 decode(G.finally_amt, 0, null, G.finally_amt)) AS PARAMVALUE,
                         'CURRENT_PRICE' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         G.UPDATE_USER,
                         G.UPDATE_TIME
                    FROM (SELECT RESULT1.PORTFOLIO_ID,
                                 RESULT1.FINPROD_ID,
                                 RESULT1.CDATE,
                                 RESULT1.INV_AIM,
                                 NVL(RESULT1.SHARE_AMT, 0) +
                                 NVL(RESULT2.no_amt, 0) as finally_amt,
                                 NVL(RESULT1.DSC_COST_AMT, 0) +
                                 NVL(RESULT2.no_cost, 0) as finally_cost,
                                 NVL(RESULT1.TDY_FLOAT_INGPL, 0) *
                                 (NVL(RESULT1.SHARE_AMT, 0) +
                                  NVL(RESULT2.no_amt, 0)) /
                                 DECODE(RESULT1.SHARE_AMT,
                                        0,
                                        null,
                                        RESULT1.SHARE_AMT) as finally_GY,
                                 NVL(RESULT1.TDY_INTINCEXP, 0) +
                                 NVL(RESULT2.no_lixi, 0) as finally_LX,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            FROM (SELECT F.PORTFOLIO_ID,
                                         F.FINPROD_ID,
                                         F.CDATE,
                                         F.INV_AIM,
                                         F.SHARE_AMT,
                                         F.TDY_FLOAT_INGPL,
                                         F.DSC_COST_AMT,
                                         F.TDY_INTINCEXP
                                    FROM PTL_SEC_VALUTION F
                                    left join fin_product fp
                                      on fp.finprod_id = F.FINPROD_ID
                                   WHERE F.CDATE <= TRUNC(SYSDATE - 1)
                                     AND F.SHARE_AMT IS NOT NULL
                                     AND fp.finprod_type2 = 'F01') RESULT1
                            LEFT JOIN (select tpda.portfolio_id,
                                             tpd.finprod_id,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.share_amt,
                                                        tpd.share_amt)) as no_amt,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.cprice_amt,
                                                        tpd.cprice_amt)) as no_cost,
                                             sum(decode(tpd.ps,
                                                        'T01',
                                                        -1 * tpd.int_amt,
                                                        tpd.int_amt)) as no_lixi,
                                             tpd.inv_aim
                                        from TRD_PRODUCT_DEAL     tpd,
                                             TRD_PRODUCT_DEAL_ADD tpda
                                       where tpd.trade_id = tpda.trade_id
                                         and tpd.finprod_type2 = 'F01'
                                         and tpd.trade_date <=
                                             TRUNC(sysdate - 1)
                                         and TRUNC(sysdate - 1) <
                                             tpd.settle_date
                                       group by tpda.portfolio_id,
                                                tpd.finprod_id,
                                                tpd.inv_aim) RESULT2
                              ON RESULT1.PORTFOLIO_ID = RESULT2.PORTFOLIO_ID
                             AND RESULT1.FINPROD_ID = RESULT2.FINPROD_ID
                             AND RESULT1.INV_AIM = RESULT2.INV_AIM) G
                  UNION ALL
                  SELECT G.PORTFOLIO_ID AS OBJECTID,
                         G.FINPROD_ID AS ELEMENTID,
                         G.CDATE,
                         G.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'CURRENT_PRICE' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         to_char(NVL(G.tdy_float_ingpl, null)) AS PARAMVALUE,
                         'CURRENT_PRICE' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         G.UPDATE_USER,
                         G.UPDATE_TIME
                    FROM (select psv.cdate,
                                 psv.portfolio_id,
                                 psv.finprod_id,
                                 psv.inv_aim,
                                 psv.share_amt,
                                 psv.tdy_float_ingpl,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            from ptl_sec_valution psv
                            left join fin_trade_product ftp
                              on psv.finprod_id = ftp.finprod_id
                           where nvl(ftp.finprod_type2, 'notFound') in
                                 ('F12', 'F13', 'F14')
                             and psv.cdate <= trunc(sysdate - 1)) G
                  UNION ALL
                  SELECT G.PORTFOLIO_ID AS OBJECTID,
                         G.FINPROD_ID AS ELEMENTID,
                         G.CDATE,
                         G.INV_AIM AS INVAIM,
                         NULL AS SECACCIID,
                         'CURRENT_PRICE' AS PARAMCODE,
                         '01' AS PARAMTYPE,
                         to_char((NVL(G.tdy_float_ingpl, 0) +
                                 NVL(G.dsc_cost_amt, 0) +
                                 NVL(G.tdy_intincexp, 0)) /
                                 decode(G.share_amt, 0, null, G.share_amt)) AS PARAMVALUE,
                         'CURRENT_PRICE' AS DICTCODE,
                         'P_IMPORT_PROD_DATA' AS CREATEUSER,
                         'system' AS CREATEDEPT,
                         G.UPDATE_USER,
                         G.UPDATE_TIME
                    FROM (select psv.cdate,
                                 psv.portfolio_id,
                                 psv.finprod_id,
                                 psv.inv_aim,
                                 psv.share_amt,
                                 psv.tdy_float_ingpl,
                                 psv.dsc_cost_amt,
                                 psv.tdy_intincexp,
                                 'system' as UPDATE_USER,
                                 sysdate as UPDATE_TIME
                            from ptl_sec_valution psv
                            left join fin_trade_product ftp
                              on psv.finprod_id = ftp.finprod_id
                            left join fin_product fp
                              on psv.finprod_id = fp.finprod_id
                           where nvl(nvl(ftp.finprod_type2, fp.finprod_type2),
                                     'notFound') not in
                                 ('F01', 'F12', 'F13', 'F14', 'notFound')
                             and psv.cdate <= trunc(sysdate - 1)) G) RESULTS
           WHERE RESULTS.PARAMVALUE IS NOT NULL) abs;

--元素统计项
insert into stt_stat_element
  select to_char(SEQ_STT_STAT_ELEMENT.NEXTVAL),
         st.OBJECTID,
         st.ELEMENTID,
         st.CDATE,
         st.INVAIM,
         st.SECACCIID,
         st.PARAMCODE,
         st.PARAMTYPE,
         st.PARAMVALUE,
         st.DICTCODE,
         st.CREATEUSER,
         st.CREATEDEPT,
         systimestamp,
         st.UPDATE_USER,
         st.UPDATE_TIME
    from (SELECT B.PORTFOLIO_ID AS OBJECTID,
                 NULL AS ELEMENTID,
                 B.CDATE,
                 NULL AS INVAIM,
                 NULL AS SECACCIID,
                 'TDY_ASSET_NETVALUE' AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(B.TDY_ASSET_NETVALUE, B.CCY, B.CDATE, 'JIXU')) AS PARAMVALUE,
                 'TDY_ASSET_NETVALUE' AS DICTCODE,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 B.UPDATE_USER,
                 B.UPDATE_TIME
            FROM PTL_PORTFOLIO_VALUATION B
           WHERE B.TDY_ASSET_NETVALUE IS NOT NULL
             AND B.CDATE IS NOT NULL
             AND B.CDATE <= TRUNC(SYSDATE) - 1
          UNION ALL
          SELECT C.PORTFOLIO_ID AS OBJECTID,
                 NULL AS ELEMENTID,
                 C.CDATE,
                 NULL AS INVAIM,
                 NULL AS SECACCIID,
                 'TDY_ASSET_VALUE' AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(C.TDY_ASSET_VALUE, C.CCY, C.CDATE, 'JIXU')) AS PARAMVALUE,
                 'TDY_ASSET_VALUE' AS DICTCODE,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 C.UPDATE_USER,
                 C.UPDATE_TIME
            FROM PTL_PORTFOLIO_VALUATION C
           WHERE C.TDY_ASSET_VALUE IS NOT NULL
             AND C.CDATE IS NOT NULL
             AND C.CDATE <= TRUNC(SYSDATE) - 1
          UNION ALL
          SELECT nvl(fp.FINPROD_MARKET_ID, D.FINPROD_ID) AS OBJECTID,
                 NULL AS ELEMENTID,
                 D.CDATE,
                 NULL AS INVAIM,
                 NULL AS SECACCIID,
                 'TDY_SHARE_AMT' AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 TO_CHAR(D.TDY_SHARE_AMT) AS PARAMVALUE,
                 'TDY_SHARE_AMT' AS DICTCODE,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 D.UPDATE_USER,
                 D.UPDATE_TIME
            FROM BOK_POSITION_PRD D
            left join fin_product fp
              on fp.finprod_id = D.FINPROD_ID
           WHERE D.TDY_SHARE_AMT IS NOT NULL
             AND D.CDATE IS NOT NULL
             AND D.CDATE <= TRUNC(SYSDATE) - 1
          UNION ALL
          SELECT nvl(fp.FINPROD_MARKET_ID, E.FINPROD_ID) AS OBJECTID,
                 NULL AS ELEMENTID,
                 E.CDATE,
                 NULL AS INVAIM,
                 NULL AS SECACCIID,
                 'TDY_UNIT_VALUE' AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 TO_CHAR(E.TDY_UNIT_VALUE) AS PARAMVALUE,
                 'TDY_UNIT_VALUE' AS DICTCODE,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 E.UPDATE_USER,
                 E.UPDATE_TIME
            FROM BOK_POSITION_PRD E
            left join fin_product fp
              on fp.finprod_id = E.FINPROD_ID
           WHERE E.TDY_UNIT_VALUE IS NOT NULL
             AND E.CDATE IS NOT NULL
             AND E.CDATE <= TRUNC(SYSDATE) - 1
          UNION ALL
          SELECT H.PORTFOLIO_ID AS OBJECTID,
                 H.CCY AS ELEMENTID,
                 H.CDATE,
                 NULL AS INVAIM,
                 NULL AS SECACCIID,
                 'AMOUNT' AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 TO_CHAR(H.AMOUNT) AS PARAMVALUE,
                 'AMOUNT' AS DICTCODE,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 H.UPDATE_USER,
                 H.UPDATE_TIME
            FROM PTL_CASH_POSITION H
           WHERE H.AMOUNT IS NOT NULL
             AND H.CDATE IS NOT NULL
             AND H.CDATE <= TRUNC(SYSDATE) - 1
          UNION ALL
          SELECT I.PORTFOLIO_ID AS OBJECTID,
                 I.CCY AS ELEMENTID,
                 I.CDATE,
                 NULL AS INVAIM,
                 NULL AS SECACCIID,
                 'VALUE' AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 TO_CHAR(PKG_FLOW_STRESS_TEST.FUNC_CNY_TRANS(I.AMOUNT, I.CCY, I.CDATE, 'JIXU')) AS PARAMVALUE,
                 'VALUE' AS DICTCODE,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 I.UPDATE_USER,
                 I.UPDATE_TIME
            FROM PTL_CASH_POSITION I
           WHERE I.AMOUNT IS NOT NULL
             AND I.CDATE IS NOT NULL
             AND I.CDATE <= TRUNC(SYSDATE) - 1
          UNION ALL
          SELECT J.PORTFOLIO_ID AS OBJECTID,
                 J.FINPROD_ID AS ELEMENTID,
                 J.CDATE,
                 J.INV_AIM AS INVAIM,
                 J.SEC_ACCT_ID AS SECACCIID,
                 J.HODING_TYPE AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 TO_CHAR(J.AMOUNT) AS PARAMVALUE,
                 'POSITION_TYPE' AS DICTCODE,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 J.UPDATE_USER,
                 J.UPDATE_TIME
            FROM PTL_SEC_POSITION J
           WHERE J.BRANCH = 0
             AND J.AMOUNT IS NOT NULL
             AND J.CDATE IS NOT NULL
             AND J.CDATE <= TRUNC(SYSDATE) - 1
          union all
          select fp.finprod_id AS OBJECTID,
                 fp.finprod_id AS ELEMENTID,
                 pj.CDATE as CDATE,
                 null AS INVAIM,
                 NULL AS SECACCIID,
                 'GRADE_ORDER' AS PARAMCODE,
                 '01' AS PARAMTYPE,
                 sdl2.item_value AS PARAMVALUE,
                 'GRADE_ORDER' AS DICTCODE,
                 'P_IMPORT_PROD_DATA' AS CREATEUSER,
                 'system' AS CREATEDEPT,
                 pj.UPDATE_USER,
                 pj.UPDATE_TIME
            from FIN_PRODUCT fp
            left join (select t1.*
                        from (select t.*,
                                     t2.CDATE,
                                     row_number() over(partition by t.o_code, t2.CDATE order by t.grade_date desc, t.grade_result desc) rownumber
                                from MST_BOND_CREDIT_RATING t
                               right join (select startDate + level - 1 as CDATE
                                            from (select min(t.grade_date) as startDate
                                                    from MST_BOND_CREDIT_RATING t)
                                          connect by level <= trunc(sysdate) -
                                                     startDate) t2
                                  on t.grade_date <= CDATE) t1
                       where t1.rownumber = 1) pj
              on pj.o_code =
                 substr(fp.finprod_id, instr(fp.finprod_id, '_') + 1)
            left join sys_datadict_list sdl
              on sdl.dict_code = 'MAIN_GRADE_RESULT'
             and sdl.item_code = pj.grade_result
            left join sys_datadict_list sdl2
              on sdl2.dict_code = 'ST_BOND_GRADE'
             and sdl.item_value = sdl2.item_code
           where fp.finprod_type2 = 'F01'
             and pj.o_type = '02') st;

commit;
