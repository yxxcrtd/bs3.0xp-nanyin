package com.jixu.common.apiconst;

/**
 * 对外开放接口路径
 * @auth 何阳阳
 * @date 2021/7/7
 */
public class ApiPathConst {
    public static final String API_NAME = "openapi";

    public static final String VERSION = "v1";

    public static final String API_PATH = "/" + API_NAME + "/" + VERSION;

    public static final String EXECUTE_MESSAGE = "executeJixuMessageNoTx";

    public static final String CREATE_PORTFOLIOS = "createPortfolios";

    public static final String UPDATE_BK_CASH_ACCT = "updateBkCashAccts";

    public static final String SECURITY_OPTIONS = "getSecurityDtoByAssetType";
}
