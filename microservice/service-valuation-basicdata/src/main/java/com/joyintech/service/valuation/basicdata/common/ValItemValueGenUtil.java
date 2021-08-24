package com.joyintech.service.valuation.basicdata.common;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basicdata.xmlrelation.XmlRelationInputParaDto;
import com.joyintech.service.valuation.basicdata.xmlrelation.XmlRelationOutputDto;
import com.joyintech.tams.common.base.dao.MstCustomerInfoAddMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ValItemValueGenUtil {

    /**
     * 金融产品类型 估值分类
     */
    public static final String KEY_FINPROD_TYPE = "FINPROD_TYPE";

    /**
     * 金融产品类型 投管分类
     */
    public static final String KEY_FINPROD_TYPE_TG = "FINPROD_TYPE_TG";

    /**
     * 资产一级分类
     */
    public static final String KEY_ASSET_FIRST_TYPE = "ASSET_FIRST_TYPE";

    /**
     * 资产二级分类
     */
    public static final String KEY_ASSET_SECOND_TYPE = "ASSET_SECOND_TYPE";

    /**
     * 交易场所
     */
    public static final String KEY_TRADE_PLACE = "TRADE_PLACE";

    /**
     * 是否境内外
     */
    public static final String KEY_YES_OR_NO = "YES_OR_NO";

    /**
     * 机构核算分类
     */
    public static final String KEY_ISSUER_HS_TYPE = "ISSUER_HS_TYPE";

    /**
     * 估值 资产类型
     */
    public static final String KEY_TYPE1 = "TYPE1";

    /**
     * 估值 核算分类
     */
    public static final String KEY_ACCOUNTING_TYPE = "ACCOUNTING_TYPE";

    /**
     * 估值 税务分类
     */
    public static final String KEY_TAX_ASSET_TYPE = "TAX_ASSET_TYPE";

    /**
     * 金融产品分类表的分类1-分类3
     *
     * @param finProduct 标的类金融产品
     * @param finProductType 金融产品分类表
     * @see
     * @since 1.0
     */
    public static void genTypeForFinProductType(FinProduct finProduct, FinProductType finProductType) {
        if (finProductType == null) {
            return;
        }

        // 金融产品类型（投管分类）
        String finprodType2 = finProduct.getFinprodType2();
        // 资产一级分类
        String assetFirstType = null;
        // 资产二级分类
        String assetSecondType = null;
        if (finProductType != null) {
            assetFirstType = finProductType.getType6();
            assetSecondType = finProductType.getType7();
        }
        // 交易场所
        String tradeMarket = finProduct.getTradeMarket();
        // 境内外
        String blnArea = finProduct.getBlnArea();

        XmlRelationOutputDto outputDto = genResultByItemValue(finprodType2, assetFirstType, assetSecondType, tradeMarket, blnArea);

        // 存款类资产需要机构核算分类维度，特殊处理
        if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_存款.getValue(), finprodType2)) {
            // 机构核算分类(默认01，根据存/借款机构配置的机构核算分类)
            String ISSUER_HS_TYPE = genIssuerHsTypeOrgCode(finProduct.getIssuer());
            outputDto = genResultByItemValue(finprodType2, assetFirstType, assetSecondType, tradeMarket, blnArea, ISSUER_HS_TYPE);
        }

        if (StringUtil.isBlank(finProductType.getType1())) {
            finProductType.setType1(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_TYPE1));
        }
        if (StringUtil.isBlank(finProductType.getType2())) {
            finProductType.setType2(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_ACCOUNTING_TYPE));
        }
        if (StringUtil.isBlank(finProductType.getType3())) {
            finProductType.setType3(XmlRelationUtil.getVALOutPutResultItemCode(outputDto, KEY_TAX_ASSET_TYPE));
        }
    }

    /**
     * 根据参数获取映射结果
     *
     * @param finprodType2 金融产品类型（投管分类）
     * @param assetFirstType 资产一级分类
     * @param assetSecondType 资产二级分类
     * @param tradeMarket 交易场所
     * @param blnArea 境内外
     * @param issuerHsTypeOrgCode 机构核算分类机构代码
     * @return 映射结果
     * @see
     * @since 1.0
     */
    private static XmlRelationOutputDto genResultByItemValue(String finprodType2, String assetFirstType, String assetSecondType, String tradeMarket, String blnArea, String issuerHsTypeOrgCode) {
        // 调用共通获取金融产品类型（估值核算）
        List<XmlRelationInputParaDto> inputParamList = genInputParamList(finprodType2, assetFirstType, assetSecondType, tradeMarket, blnArea);
        if (StringUtil.isNotBlank(issuerHsTypeOrgCode)) {
            // 机构核算分类机构代码
            XmlRelationInputParaDto inputParaDto = new XmlRelationInputParaDto();
            inputParaDto.setDictCode(KEY_ISSUER_HS_TYPE);
            inputParaDto.setItemCode(issuerHsTypeOrgCode);
            inputParamList.add(inputParaDto);
        }
        return XmlRelationUtil.getOutPut(inputParamList);
    }
    /**
     * 根据参数获取映射结果
     *
     * @param finprodType2 金融产品类型（投管分类）
     * @param assetFirstType 资产一级分类
     * @param assetSecondType 资产二级分类
     * @param tradeMarket 交易场所
     * @param blnArea 境内外
     * @return 映射结果
     * @see
     * @since 1.0
     */
    private static XmlRelationOutputDto genResultByItemValue(String finprodType2, String assetFirstType, String assetSecondType, String tradeMarket, String blnArea) {
        // 调用共通获取金融产品类型（估值核算）
        List<XmlRelationInputParaDto> inputParamList = genInputParamList(finprodType2, assetFirstType, assetSecondType, tradeMarket, blnArea);
        return XmlRelationUtil.getOutPut(inputParamList);
    }

    /**
     * 根据标的类金融产品表实体，获取金融产品类型（估值核算）
     * 并设置到标的类金融产品表
     *
     * @param finProduct 标的类金融产品表实体
     * @param finProductType 金融产品分类表
     * @see
     * @since 1.0
     */
    public static void genFinprodTypeForFinProduct(FinProduct finProduct, FinProductType finProductType) {
        if (finProduct == null) {
            return;
        }

        // 金融产品类型（投管分类）
        String finprodType2 = finProduct.getFinprodType2();
        // 资产一级分类
        String assetFirstType = null;
        // 资产二级分类
        String assetSecondType = null;
        if (finProductType != null) {
            assetFirstType = finProductType.getType6();
            assetSecondType = finProductType.getType7();
        }
        // 交易场所
        String tradeMarket = finProduct.getTradeMarket();
        // 境内外
        String blnArea = finProduct.getBlnArea();

        // 存款类资产需要机构核算分类维度，特殊处理
        if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_存款.getValue(), finprodType2)) {
            // 机构核算分类(默认01，根据存/借款机构配置的机构核算分类)
            String ISSUER_HS_TYPE = genIssuerHsTypeOrgCode(finProduct.getIssuer());
            String FINPROD_TYPE = genFinprodTypeByItemValue(finprodType2, assetFirstType, assetSecondType, tradeMarket, blnArea, ISSUER_HS_TYPE);
            if (StringUtil.isNotEmpty(FINPROD_TYPE)) {
                finProduct.setFinprodType(FINPROD_TYPE);
            }
            return;
        }

        String FINPROD_TYPE = genFinprodTypeByItemValue(finprodType2, assetFirstType, assetSecondType, tradeMarket, blnArea);
        if (StringUtil.isNotEmpty(FINPROD_TYPE)) {
            finProduct.setFinprodType(FINPROD_TYPE);
        }
    }

    /**
     * 根据存/借款机构获取对应机构核算分类机构代码
     *
     * @param issuer
     * @return
     * @see
     * @since 1.0
     */
    private static String genIssuerHsTypeOrgCode(String issuer) {
        String issuerHsTypeOrgCode = "01";

        MstCustomerInfoAddMapper mstCustomerInfoAddMapper = SpringContextUtil.getBean(MstCustomerInfoAddMapper.class);
        MstCustomerInfoAdd mstCustomerInfoAdd = mstCustomerInfoAddMapper.selectByPrimaryKey(issuer);
        if (mstCustomerInfoAdd != null) {
            issuerHsTypeOrgCode = mstCustomerInfoAdd.getHsType();
        }
        if (StringUtils.isEmpty(issuerHsTypeOrgCode)) {
            issuerHsTypeOrgCode = "01";
        }
        return issuerHsTypeOrgCode;
    }

    /**
     * 根据参数获取金融产品类型
     *
     * @param finprodType2 金融产品类型（投管分类）
     * @param assetFirstType 资产一级分类
     * @param assetSecondType 资产二级分类
     * @param tradeMarket 交易场所
     * @param blnArea 境内外
     * @param issuerHsTypeOrgCode 机构核算分类机构代码
     * @return 金融产品类型
     * @see
     * @since 1.0
     */
    private static String genFinprodTypeByItemValue(String finprodType2, String assetFirstType, String assetSecondType, String tradeMarket, String blnArea, String issuerHsTypeOrgCode) {
        // 调用共通获取金融产品类型（估值核算）
        List<XmlRelationInputParaDto> inputParamList = genInputParamList(finprodType2, assetFirstType, assetSecondType, tradeMarket, blnArea);
        if (StringUtil.isNotBlank(issuerHsTypeOrgCode)) {
            // 机构核算分类机构代码
            XmlRelationInputParaDto inputParaDto = new XmlRelationInputParaDto();
            inputParaDto.setDictCode(KEY_ISSUER_HS_TYPE);
            inputParaDto.setItemCode(issuerHsTypeOrgCode);
            inputParamList.add(inputParaDto);
        }
        String FINPROD_TYPE = XmlRelationUtil.getVALOutPutResultItemCode(inputParamList, KEY_FINPROD_TYPE);

        return FINPROD_TYPE;
    }

    /**
     * 根据参数获取金融产品类型
     *
     * @param finprodType2 金融产品类型（投管分类）
     * @param assetFirstType 资产一级分类
     * @param assetSecondType 资产二级分类
     * @param tradeMarket 交易场所
     * @param blnArea 境内外
     * @return 金融产品类型
     * @see
     * @since 1.0
     */
    private static String genFinprodTypeByItemValue(String finprodType2, String assetFirstType, String assetSecondType, String tradeMarket, String blnArea) {
        // 调用共通获取金融产品类型（估值核算）
        List<XmlRelationInputParaDto> inputParamList = genInputParamList(finprodType2, assetFirstType, assetSecondType, tradeMarket, blnArea);
        String FINPROD_TYPE = XmlRelationUtil.getVALOutPutResultItemCode(inputParamList, KEY_FINPROD_TYPE);

        return FINPROD_TYPE;
    }

    /**
     * 获取映射关系参数list
     *
     * @param finprodType2 金融产品类型（投管分类）
     * @param assetFirstType 资产一级分类
     * @param assetSecondType 资产二级分类
     * @param tradeMarket 交易场所
     * @param blnArea 境内外
     * @return 映射关系参数list
     * @see
     * @since 1.0
     */
    private static List<XmlRelationInputParaDto> genInputParamList(String finprodType2, String assetFirstType, String assetSecondType, String tradeMarket, String blnArea) {
        // 特殊场景处理
        if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_存款.getValue(), finprodType2)) {
            assetSecondType = null;
            tradeMarket = null;
            blnArea = null;
        }
        else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_拆出.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_拆入.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_特拆拆入.getValue(), finprodType2)) {
            assetFirstType = null;
            assetSecondType = null;
            tradeMarket = null;
            blnArea = null;
        }
        else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_基金.getValue(), finprodType2)) {
            //update by  zhangnian  20191127 start
            //tradeMarket = null;
            //update by  zhangnian  20191127 end
        }
        else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue(), finprodType2)) {
            assetSecondType = null;
            tradeMarket = null;
        }
        else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(), finprodType2)) {
            assetFirstType = null;
            assetSecondType = null;
            blnArea = null;
        }
        else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue(), finprodType2)) {
            assetSecondType = null;
            tradeMarket = null;
        }
        else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_固定管理费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_浮动管理费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_托管费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_投资顾问费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_销售费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_税额.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_代销费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.一次性费用类型_投管_交易手续费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.一次性费用类型_结算手续费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.一次性费用类型_账户维护费.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.一次性费用类型_其他费用.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_资金账户.getValue(), finprodType2)) {
            assetFirstType = null;
            assetSecondType = null;
            tradeMarket = null;
            blnArea = null;
        }
        else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(), finprodType2)
                && StringUtil.equals(DictCodeList.WIND一级分类_含债券一级分类等_同业存单.getValue(), assetFirstType)) {
            assetSecondType = null;
        }
        else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue(), finprodType2)
                || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue(), finprodType2)) {
            assetSecondType = null;
        }

        // 境内外转化
        if (StringUtil.equals(DictCodeList.境内外_境内.getValue(), blnArea)) {
            blnArea = DictCodeList.是否_是.getValue();
        }
        else if (StringUtil.equals(DictCodeList.境内外_境外.getValue(), blnArea)) {
            blnArea = DictCodeList.是否_否.getValue();
        }

        // 调用共通获取金融产品类型（估值核算）
        List<XmlRelationInputParaDto> inputParamList = new ArrayList<XmlRelationInputParaDto>();

        // 金融产品类型（投管分类）
        XmlRelationInputParaDto inputParaDto = new XmlRelationInputParaDto();
        inputParaDto.setDictCode(KEY_FINPROD_TYPE_TG);
        inputParaDto.setItemCode(finprodType2);
        inputParamList.add(inputParaDto);

        // 资产一级分类
        if (StringUtil.isNotBlank(assetFirstType)) {
            inputParaDto = new XmlRelationInputParaDto();
            inputParaDto.setDictCode(KEY_ASSET_FIRST_TYPE);
            inputParaDto.setItemCode(assetFirstType);
            inputParamList.add(inputParaDto);
        }

        // 资产二级分类
        if (StringUtil.isNotBlank(assetSecondType)) {
            inputParaDto = new XmlRelationInputParaDto();
            inputParaDto.setDictCode(KEY_ASSET_SECOND_TYPE);
            inputParaDto.setItemCode(assetSecondType);
            inputParamList.add(inputParaDto);
        }

        // 交易场所
        if (StringUtil.isNotBlank(tradeMarket)) {
            inputParaDto = new XmlRelationInputParaDto();
            inputParaDto.setDictCode(KEY_TRADE_PLACE);
            inputParaDto.setItemCode(tradeMarket);
            inputParamList.add(inputParaDto);
        }

        // 境内外
        if (StringUtil.isNotBlank(blnArea)) {
            inputParaDto = new XmlRelationInputParaDto();
            inputParaDto.setDictCode(KEY_YES_OR_NO);
            inputParaDto.setItemCode(blnArea);
            inputParamList.add(inputParaDto);
        }

        return inputParamList;
    }
}
