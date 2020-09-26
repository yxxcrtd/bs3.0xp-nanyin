CREATE OR REPLACE VIEW "VAL_VW_ATTACHMENT" ("PORTFOLIO_ID", "PORTFOLIO_NAME", "COMMON_BUSINESS_TYPE", "BUSINESS_ID", "USER_NAME", "REAL_NAME", "DEPT_INSTITUTION_CODE", "DEPT_INSTITUTION_NAME", "CREATE_TIME", "ATTACHMENT_FILE_NAME", "SEQNO") AS 
  SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'MST09'                                         AS COMMON_BUSINESS_TYPE,
    A.ACCT_NO                                       AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    STL_CAPACCT_INFO A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.ACCT_NO = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
-- 证券账户管理
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'MST10'                                         AS COMMON_BUSINESS_TYPE,
    A.SEC_ACCT_ID                                   AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    STL_SEC_ACCT_INFO A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.SEC_ACCT_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
-- 交易对手管理
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'MST15'                                         AS COMMON_BUSINESS_TYPE,
    A.COUNTER_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    MST_COUNTER_PARTY A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.COUNTER_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
-- 债券类资产
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET01'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F01'
-- 存款类资产
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET02'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F07'
-- 基金类资产
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET03'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F06'
-- 计划类资产净值型
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET04'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F17'
AND A.PROFIT_TYPE = '01'
-- 计划类资产利率型
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET05'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F17'
AND A.PROFIT_TYPE = '02'
-- 项目类资产
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET06'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F18'
-- 信息披露模板管理
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINPROD01'                                     AS COMMON_BUSINESS_TYPE,
    A.REVEAL_FILE_ID                                AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    PRD_FILE_TEMPLAT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.REVEAL_FILE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
-- 申报附件方案管理
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINPROD23'                                     AS COMMON_BUSINESS_TYPE,
    A.SEQNO                                         AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    PRD_DECLARE_SCHEME_DOC A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.SEQNO = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
-- 产品费用支付管理
UNION ALL
/**SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'FINPROD19'                                     AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    PA_TRD_PRODUCT_DEAL E
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = E.TRADE_ID
AND E.PA_BUSS_TYPE = '08'**/
--update by leipeng 20190824

SELECT
    P.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'FINPROD19'                                     AS COMMON_BUSINESS_TYPE,
    A.TOTLE_TRADE_ID                                AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM TRD_FIN_PRODUCT_DEAL_TOTAL A,
    PTL_TRADE P,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.TOTLE_TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.TOTLE_TRADE_ID = P.BUSI_NO
AND P.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.BUSI_TYPE = 'FINPROD19'
--AND E.PA_BUSS_TYPE = '08'


-- 债券投资委托
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR02'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 = 'F01'
-- 拆借投资委托
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR03'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F08',
                        'F09')
-- 存款投资委托
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR04'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F07')
-- 基金投资委托
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR05'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F06')
-- 计划类资产净值型投资委托
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR06'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    FIN_PRODUCT E
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F17')
AND A.FINPROD_ID = E.FINPROD_ID
AND E.PROFIT_TYPE = '01'
-- 计划类资产利率型投资委托
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR07'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    FIN_PRODUCT E
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F17')
AND A.FINPROD_ID = E.FINPROD_ID
AND E.PROFIT_TYPE = '02'
-- 回购投资委托
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR08'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F02',
                        'F03',
                        'F04',
                        'F05')
-- 项目类资产投资委托
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR09'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F18')
-- 债券投资交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD02'                                         AS COMMON_BUSINESS_TYPE,
    A.TOTLE_TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ( SELECT TFPDT.TOTLE_TRADE_ID, TPDA.TRADE_ID, TFPDT.PORTFOLIO_ID
  FROM TRD_FIN_PRODUCT_DEAL_TOTAL TFPDT
  LEFT JOIN TRD_PRODUCT_DEAL TPD
    ON TFPDT.TOTLE_TRADE_ID = NVL(TPD.TOTLE_TRADE_ID, TPD.TRADE_ID)
  LEFT JOIN TRD_PRODUCT_DEAL_ADD TPDA
    ON TPDA.TRADE_ID = TPD.TRADE_ID ) A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    PA_TRD_PRODUCT_DEAL E
WHERE
    A.TOTLE_TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = E.TRADE_ID
AND E.PA_BUSS_TYPE IN ('09',
                       '10',
                       '11')
-- 拆借投资交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD03'                                         AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    PA_TRD_PRODUCT_DEAL E
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = E.TRADE_ID
AND E.PA_BUSS_TYPE IN ('14',
                       '15',
                       '16')
-- 存款投资交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD04'                                         AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    PA_TRD_PRODUCT_DEAL E
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = E.TRADE_ID
AND E.PA_BUSS_TYPE IN ('12',
                       '13')
-- 基金类资产投资交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD05'                                         AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    PA_TRD_PRODUCT_DEAL E
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = E.TRADE_ID
AND E.PA_BUSS_TYPE IN ('21',
                       '22',
                       '23',
                       '24',
                       '25',
                       '26',
                       '27',
                       '28')
-- 计划类资产净值型投资交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD06'                                         AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    TRD_PRODUCT_DEAL E,
    FIN_PRODUCT F,
    PA_TRD_PRODUCT_DEAL G
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = G.TRADE_ID
AND G.PA_BUSS_TYPE IN ('29',
                       '30',
                       '31',
                       '32')
AND A.TRADE_ID = E.TRADE_ID
AND E.FINPROD_ID = F.FINPROD_ID
AND F.PROFIT_TYPE = '01'
-- 计划类资产利率型投资交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD07'                                         AS COMMON_BUSINESS_TYPE,
    A.TOTLE_TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ( SELECT TFPDT.TOTLE_TRADE_ID, TPDA.TRADE_ID, TFPDT.PORTFOLIO_ID
  FROM TRD_FIN_PRODUCT_DEAL_TOTAL TFPDT
  LEFT JOIN TRD_PRODUCT_DEAL TPD
    ON TFPDT.TOTLE_TRADE_ID = NVL(TPD.TOTLE_TRADE_ID, TPD.TRADE_ID)
  LEFT JOIN TRD_PRODUCT_DEAL_ADD TPDA
    ON TPDA.TRADE_ID = TPD.TRADE_ID ) A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    TRD_PRODUCT_DEAL E,
    FIN_PRODUCT F,
    PA_TRD_PRODUCT_DEAL G
WHERE
    A.TOTLE_TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = G.TRADE_ID
AND G.PA_BUSS_TYPE IN ('33',
                       '34')
AND A.TRADE_ID = E.TRADE_ID
AND E.FINPROD_ID = F.FINPROD_ID
AND F.PROFIT_TYPE = '02'
-- 回购投资交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD08'                                         AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    PA_TRD_PRODUCT_DEAL E
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = E.TRADE_ID
AND E.PA_BUSS_TYPE IN ('19',
                       '20')
-- 项目类资产投资交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD09'                                         AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    PA_TRD_PRODUCT_DEAL E
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = E.TRADE_ID
AND E.PA_BUSS_TYPE IN ('17',
                       '18')
--产品要素管理
UNION ALL
SELECT
    A.FINPROD_MARKET_ID                             AS PORTFOLIO_ID,
    A.FINPROD_ABBR                                  AS PORTFOLIO_NAME,
    'FINPROD04'                                     AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F16'

UNION ALL

SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET11'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F17'
AND A.PROFIT_TYPE = '03'
-- 计划类资产货币型

UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET12'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F28'
--通道管理
UNION ALL
SELECT
    NULL                                            AS PORTFOLIO_ID,
    NULL                                            AS PORTFOLIO_NAME,
    'FINASSET14'                                    AS COMMON_BUSINESS_TYPE,
    A.FINPROD_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    FIN_PRODUCT A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C
WHERE
    A.FINPROD_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.FINPROD_TYPE2 = 'F17'
--通道费用支付??

UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR10'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    FIN_PRODUCT E
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F17')
AND A.FINPROD_ID = E.FINPROD_ID
AND E.PROFIT_TYPE = '03'
-- 计划类资产（货币型）投资委托

UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR11'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F04',
                        'F05')
--买断式回购投资委托(这个要修改回购投资委托的取值?)
/*UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'ETR08'                                         AS COMMON_BUSINESS_TYPE,
    A.ENTRUST_ID                                    AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    ETR_ENTRUST A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D
WHERE
    A.ENTRUST_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.FINPROD_TYPE2 IN ('F02',
                        'F03')*/

UNION ALL
--计划类资产（货币型）交易
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD47'                                         AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    TRD_PRODUCT_DEAL E,
    FIN_PRODUCT F,
    PA_TRD_PRODUCT_DEAL G
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = G.TRADE_ID
AND G.PA_BUSS_TYPE IN ('70',
                       '71',
                       '72',
                       '73')
AND A.TRADE_ID = E.TRADE_ID
AND E.FINPROD_ID = F.FINPROD_ID
AND F.PROFIT_TYPE = '03'

--买断式回购交易
UNION ALL
SELECT
    A.PORTFOLIO_ID                                  AS PORTFOLIO_ID,
    D.PORTFOLIO_NAME                                AS PORTFOLIO_NAME,
    'TRD48'                                         AS COMMON_BUSINESS_TYPE,
    A.TRADE_ID                                      AS BUSINESS_ID,
    B.CREATE_USER                                   AS USER_NAME,
    C.REAL_NAME                                     AS REAL_NAME,
    C.DEPT_INSTITUTION_CODE                         AS DEPT_INSTITUTION_CODE,
    C.DEPT_INSTITUTION_NAME                         AS DEPT_INSTITUTION_NAME,
    TO_CHAR(B.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME,
    B.FILE_NAME                                     AS ATTACHMENT_FILE_NAME,
    B.SEQ_NO                                        AS SEQNO
FROM
    TRD_PRODUCT_DEAL_ADD A,
    sys_upload_file_info B,
    VAL_VW_USERDEPT C,
    PTL_PORTFOLIO D,
    TRD_PRODUCT_DEAL E,
    PA_TRD_PRODUCT_DEAL G
WHERE
    A.TRADE_ID = B.BUSINESS_ID(+)
AND B.CREATE_USER = C.USER_NAME(+)
AND A.PORTFOLIO_ID = D.PORTFOLIO_ID
AND A.TRADE_ID = G.TRADE_ID
AND G.PA_BUSS_TYPE IN ('74',
                       '75')
AND A.TRADE_ID = E.TRADE_ID;