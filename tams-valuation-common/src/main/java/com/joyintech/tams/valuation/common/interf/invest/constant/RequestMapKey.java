package com.joyintech.tams.valuation.common.interf.invest.constant;


/**
 * @author jinmaodong
 * @since v1.0.0.0 20190902
 * 调用估值发布接口传参所使用的KEY，KEY的组织形式为"methodName_paramName"
 */
public interface RequestMapKey {
    /**
     * 对应方法：账套\产品信息初始化
     * 对应参数类型：ProdInfoDto-产品信息
     */
    public static final String PRODOTHERINFOINIT_PRODINFODTO = "PRODOTHERINFOINIT_PRODINFODTO";
    /**
     * 对应方法：账套\产品信息初始化
     * 对应参数类型：List<StlFinProductAcct>-产品对应标的账户信息
     */
    public static final String PRODOTHERINFOINIT_PRODUCTACCTLIST = "PRODOTHERINFOINIT_PRODUCTACCTLIST";
    /**
     * 对应方法：交易入账
     * 对应参数类型：String-金融产品代码
     */
    public static final String BOKTRADEBUILD_PRODID = "BOKTRADEBUILD_PRODID";
    /**
     * 对应方法：交易入账
     * 对应参数类型：String-金融产品类型
     */
    public static final String BOKTRADEBUILD_PRODFINPRODTYPE = "BOKTRADEBUILD_PRODFINPRODTYPE";
    /**
     * 对应方法：交易入账
     * 对应参数类型：String-分支序号
     */
    public static final String BOKTRADEBUILD_BRANCH = "BOKTRADEBUILD_BRANCH";
    /**
     * 对应方法：交易入账
     * 对应参数类型：TrdProductDeal-金融产品交易
     */
    public static final String BOKTRADEBUILD_TRDPRODUCTDEAL = "BOKTRADEBUILD_TRDPRODUCTDEAL";

    /**
     * 对应方法：同业借款基本信息初始化
     * 对应参数类型：FinTradeProduct-交易类金融产品
     */
    public static final String INTERBORROWBASEINFOINIT_FINTRADEPRODUCT = "INTERBORROWBASEINFOINIT_FINTRADEPRODUCT";
    /**
     * 对应方法：同业借款基本信息初始化
     * 对应参数类型：String-动作标志
     */
    public static final String INTERBORROWBASEINFOINIT_DATAACTIONSIGN = "INTERBORROWBASEINFOINIT_DATAACTIONSIGN";

    /**
     * 对应方法：结算指令交易入账
     * 对应参数类型：String-金融产品代码
     */
    public static final String STLORDERSBOKTRADEBUILD_PRODID = "STLORDERSBOKTRADEBUILD_PRODID";
    /**
     * 对应方法：结算指令交易入账
     * 对应参数类型：String-金融产品类型
     */
    public static final String STLORDERSBOKTRADEBUILD_PRODFINPRODTYPE = "STLORDERSBOKTRADEBUILD_PRODFINPRODTYPE";
    /**
     * 对应方法：结算指令交易入账
     * 对应参数类型：String-子产品起息日(要求按照yyyy-MM-dd格式配置)
     */
    public static final String STLORDERSBOKTRADEBUILD_PRODVDATE = "STLORDERSBOKTRADEBUILD_PRODVDATE";
    /**
     * 对应方法：结算指令交易入账
     * 对应参数类型：StlOrders-结算指令
     */
    public static final String STLORDERSBOKTRADEBUILD_STLORDERS = "STLORDERSBOKTRADEBUILD_STLORDERS";
    /**
     * 对应方法：理财产品借通道投资交易
     * 对应参数类型：ChannelProdDeal-理财产品借通道投资交易
     */
    public static final String GETCHANNELPRODDEAL_DEAL = "GETCHANNELPRODDEAL_DEAL";
    /**
     * 对应方法：理财产品借通道投资交易
     * 对应参数类型：String-金融产品类型
     */
    public static final String GETCHANNELPRODDEAL_PRODUCTTYPE = "GETCHANNELPRODDEAL_PRODUCTTYPE";
    /**
     * 对应方法：通道投资资产交易拆分
     * 对应参数类型：String-金融产品代码
     */
    public static final String CHLINVESTASSETSEP_PRODID = "CHLINVESTASSETSEP_PRODID";
    /**
     * 对应方法：通道投资资产交易拆分
     * 对应参数类型：String-金融产品类型
     */
    public static final String CHLINVESTASSETSEP_PRODFINRODTYPE = "CHLINVESTASSETSEP_PRODFINRODTYPE";
    /**
     * 对应方法：通道投资资产交易拆分
     * 对应参数类型：TrdProductDeal-金融产品交易
     */
    public static final String CHLINVESTASSETSEP_TRDPRODUCTDEAL = "CHLINVESTASSETSEP_TRDPRODUCTDEAL";
    /**
     * 对应方法：通道投资资产交易拆分
     * 对应参数类型：TrdProductDeal-金融产品交易扩展信息
     */
    public static final String CHLINVESTASSETSEP_TRDPRODUCTDEALADD = "CHLINVESTASSETSEP_TRDPRODUCTDEALADD";
    /**
     * 对应方法：通道投资资产交易拆分
     * 对应参数类型：TrdTradeFeeDetail-交易费用明细信息
     */
    public static final String CHLINVESTASSETSEP_TRDTRADEFEEDETAIL = "CHLINVESTASSETSEP_TRDTRADEFEEDETAIL";
    /**
     * 对应方法：产品端交易拆分
     * 对应参数类型：TrdProductDeal-金融产品交易
     */
    public static final String PRDDEALSEP_TRDPRODUCTDEAL = "PRDDEALSEP_TRDPRODUCTDEAL";
    /**
     * 对应方法：产品端交易拆分
     * 对应参数类型：TrdTradeFeeDetail-交易费用明细
     */
    public static final String PRDDEALSEP_TRDTRADEFEEDETAILLIST = "PRDDEALSEP_TRDTRADEFEEDETAILLIST";
    /**
     * 对应方法：产品端交易拆分
     * 对应参数类型：String-金融产品代码
     */
    public static final String PRDDEALSEP_PRODID = "PRDDEALSEP_PRODID";
    /**
     * 对应方法：产品端交易拆分
     * 对应参数类型：String-通道代码
     */
    public static final String PRDDEALSEP_CHANNELID = "PRDDEALSEP_CHANNELID";
    /**
     * 对应方法：产品端交易拆分
     * 对应参数类型：String-金融产品类型
     */
    public static final String PRDDEALSEP_PRODTYPE = "PRDDEALSEP_PRODTYPE";
    /**
     * 对应方法：产品端交易拆分
     * 对应参数类型：Long-分支序号
     */
    public static final String PRDDEALSEP_BRANCH = "PRDDEALSEP_BRANCH";
    /**
     * 对应方法：产品端交易拆分
     * 对应参数类型：String-拆分标识
     */
    public static final String PRDDEALSEP_DEALSEPFLAG = "PRDDEALSEP_DEALSEPFLAG";
    /**
     * 对应方法：核算到期日调整
     * 对应参数类型：String-金融产品代码
     */
    public static final String UPDATEPRODMDATE_PRODID = "UPDATEPRODMDATE_PRODID";
    /**
     * 对应方法：核算到期日调整
     * 对应参数类型：String-金融产品类型
     */
    public static final String UPDATEPRODMDATE_PRODTYPE = "UPDATEPRODMDATE_PRODTYPE";
    /**
     * 对应方法：核算到期日调整
     * 对应参数类型：String-分支序号
     */
    public static final String UPDATEPRODMDATE_BRANCH = "UPDATEPRODMDATE_BRANCH";
    /**
     * 对应方法：核算到期日调整
     * 对应参数类型：String-目标到期日
     */
    public static final String UPDATEPRODMDATE_MDATE = "UPDATEPRODMDATE_MDATE";
    /**
     * 对应方法：按规则生成回购期限品种
     * 对应参数类型：String-金融产品类型
     */
    public static final String GETREPTERMTYPE_FINPRODTYPE = "GETREPTERMTYPE_FINPRODTYPE";
    /**
     * 对应方法：按规则生成回购期限品种
     * 对应参数类型：Long-期限天数
     */
    public static final String GETREPTERMTYPE_TERMDAY = "GETREPTERMTYPE_TERMDAY";


}
