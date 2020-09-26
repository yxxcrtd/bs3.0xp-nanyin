CREATE OR REPLACE VIEW VIEW_FIN_PRODUCT_RATING AS
SELECT
    CASE
        WHEN mbcr.o_type = '02' THEN
            mbcr.o_code
        ELSE
            NULL
    END AS marketid,
    CASE
        WHEN mbcr.o_type = '01' THEN
            mbcr.o_code
        ELSE
            NULL
    END AS orgid,
    mbcr.o_code AS ocode,
    mbcr.o_type             AS otype,
    NULL AS odistype,
    '01' AS innerouter,
    mbcr.grade_org_id       AS gradeorgid,
    mbcr.grade_date         AS gradedate,
    mbcr.notice_date AS noticeDate,
    mbcr.short_long_term    AS shortlongterm,
    mbcr.grade_result       AS mstRatingResult01,
    mrm.grade_result_st     AS mstRatingResult02,
    mbcr.input_type         AS inputtype,
    mbcr.GRADE_EXPECTATION AS gradeExpectation,
    mbcr.DIRECTION_CHANGE AS directionChange,
    mbcr.issuer_rate_type   AS issuerratetype
FROM
    mst_bond_credit_rating   mbcr
    LEFT JOIN mst_rating_match         mrm ON mbcr.grade_result = mrm.grade_result_or
UNION ALL
SELECT
    CASE
        WHEN micr.o_type = '02' THEN
            micr.o_code
        ELSE
            NULL
    END AS marketid,
    CASE
        WHEN micr.o_type = '01' THEN
            micr.o_code
        ELSE
            NULL
    END AS orgid,
    micr.o_code AS ocode,
    micr.o_type            AS otype,
    NULL AS odistype,
    '02' AS innerouter,
    micr.grade_org_id      AS gradeorgid,
    micr.grade_date        AS gradedate,
    NULL AS noticeDate,
    NULL AS shortlongterm,
    micr.grade_result      AS mstRatingResult01,
    mrm1.grade_result_st   AS mstRatingResult02,
    micr.input_type        AS inputtype,
    NULL AS gradeExpectation,
    NULL AS directionChange,
    NULL AS issuerratetype
FROM
    mst_inner_credit_rating   micr
    LEFT JOIN mst_rating_match          mrm1 ON micr.grade_result = mrm1.grade_result_or 
UNION ALL
SELECT
    distinct
    fpr.finprod_market_id            AS marketid,
    ztratinginfo.ocode            AS orgid,
    fpr.finprod_market_id            AS ocode,
    '91'            AS otype,
    '91' AS odistype,
    ztratinginfo.innerouter       AS innerouter,
    ztratinginfo.gradeorgid       AS gradeorgid,
    ztratinginfo.gradedate        AS gradedate,
    ztratinginfo.noticeDate             AS noticeDate,
    ztratinginfo.shortlongterm    AS shortlongterm,
    ztratinginfo.mstRatingResult01           AS mstRatingResult01,
    ztratinginfo.mstRatingResult02           AS mstRatingResult02,
    ztratinginfo.inputtype        AS inputtype,
    ztratinginfo.gradeExpectation             AS gradeExpectation,
    ztratinginfo.directionChange             AS directionChange,
    ztratinginfo.issuerratetype   AS issuerratetype
FROM
    fin_product fpr
    INNER JOIN (
        SELECT * FROM VIEW_FIN_PRODUCT_RATING_BREACH WHERE o_type1 = '01' or o_type2 = '01'
    ) ztratinginfo ON ztratinginfo.ocode = fpr.issuer 
UNION ALL
SELECT
    distinct
    fpr.finprod_market_id           AS marketid,
    ztratinginfo.ocode            AS orgid,
	fpr.finprod_market_id			AS ocode,
    '93'            AS otype,
    '93' AS odistype,
    ztratinginfo.innerouter       AS innerouter,
    ztratinginfo.gradeorgid       AS gradeorgid,
    ztratinginfo.gradedate        AS gradedate,
    ztratinginfo.noticeDate             AS noticeDate,
    ztratinginfo.shortlongterm    AS shortlongterm,
    ztratinginfo.mstRatingResult01           AS mstRatingResult01,
    ztratinginfo.mstRatingResult02           AS mstRatingResult02,
    ztratinginfo.inputtype        AS inputtype,
    ztratinginfo.gradeExpectation             AS gradeExpectation,
    ztratinginfo.directionChange             AS directionChange,
    ztratinginfo.issuerratetype   AS issuerratetype
FROM
    fin_product fpr
    INNER JOIN (
		SELECT * FROM VIEW_FIN_PRODUCT_RATING_BREACH WHERE o_type1 = '01' or o_type2 = '01'
	) ztratinginfo ON ztratinginfo.ocode = fpr.sponsor 
UNION ALL
SELECT
    distinct
    fpr.finprod_market_id            AS marketid,
    ztratinginfo.ocode            AS orgid,
    fpr.finprod_market_id            AS ocode,
    '92'            AS otype,
    '92' AS odistype,
    ztratinginfo.innerouter       AS innerouter,
    ztratinginfo.gradeorgid       AS gradeorgid,
    ztratinginfo.gradedate        AS gradedate,
    ztratinginfo.noticeDate             AS noticeDate,
    ztratinginfo.shortlongterm    AS shortlongterm,
    ztratinginfo.mstRatingResult01           AS mstRatingResult01,
    ztratinginfo.mstRatingResult02           AS mstRatingResult02,
    ztratinginfo.inputtype        AS inputtype,
    ztratinginfo.gradeExpectation             AS gradeExpectation,
    ztratinginfo.directionChange             AS directionChange,
    ztratinginfo.issuerratetype   AS issuerratetype
FROM
    fin_product fpr
    INNER JOIN (
		SELECT * FROM VIEW_FIN_PRODUCT_RATING_BREACH WHERE o_type1 = '01' or o_type2 = '01'
	) ztratinginfo ON ztratinginfo.ocode = fpr.financier;
