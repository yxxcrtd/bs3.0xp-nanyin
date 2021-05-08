package com.joyintech.tams.svreport.chb.assetinfodetail.util;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AssetInfoDetailUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    static Map<String,Set<String>> map = null;

    @Autowired
    private AssetInfoDetailMapper assetInfoDetailMapper;

    /**
     * 获取资产及二级类型的关系
     * @return
     */
    public Map<String, Set<String>> getDataListLink(){

        if(map==null||map.isEmpty()){
            map = new HashMap<>();
            //界面配置,根据SYS_DATADICT_LIST_LINK
            Map<String,Object> params = new HashMap<>();
            params.put("linkDictCode", DictCode.资产_负债类别.getValue());
            List<SysDatadictListLink> listLink = assetInfoDetailMapper.getDataListLinkData(params);
            for (SysDatadictListLink link:listLink) {
                if(null!=map.get(link.getLinkedItemCode())){
                    map.get(link.getLinkedItemCode()).add(link.getItemCode());
                }else{
                    map.put(link.getLinkedItemCode(),new HashSet<>(Arrays.asList(link.getItemCode())));
                }
            }
        }
        return map;
    }

    public AssetInfoDetailDto getDtoByPageMessgae(AssetInfoDetailDto dto)  {
        try {
            //遍历资产类型数据
            for (ServerMenu menu : ServerMenu.getAllAsset()) {
                //判断数据字典中是否有当前资产类型的实体类
                if (getDataListLink().get(menu.getAssetType()).contains(dto.getAssetType2())) {
                    //获取实体类中对应名称的资产方法
                    PropertyDescriptor pd = new PropertyDescriptor(menu.getServerId(),
                            dto.getClass());
                    Method getMethod =pd.getReadMethod();
                    //获取对应的实体类字段
                    Object obj1 = getMethod.invoke(dto);
                    //执行数据转换方法
                    DtoChangeUtil.SetChbToRep(obj1, dto, menu.getFlagStr(), true);
                    //资产类型赋值
                    dto.setAssetType(menu.getAssetType());
                }
            }
            return dto;
        }catch (Exception e){
            logger.info("实体转换错误,请检查数据类型",e);
        }finally {
            return null;
        }
    }

    public List<ExcelValidDto> convertToBean(List<AssetInfoDetailDto> entities , List<List<String>> dataList){
        List<ExcelValidDto> resultList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            if ( i < 2 ){
                continue;
            }
            List<String> list = dataList.get(i);
            AssetInfoDetailDto entity = new AssetInfoDetailDto();
            entity.setAssetCode(list.get(0));
            entity.setIssuePartyId(list.get(1));
            entity.setAssetType2(list.get(2));
            Map<String,Object> params = new HashMap<>();
            Map<String,String> dict = new HashMap<>();
            params.put("linkDictCode", DictCode.资产_负债类别.getValue());
            List<SysDatadictListLink> listLink = assetInfoDetailMapper.getDataListLinkData(params);
            listLink.forEach(sysDatadictListLink -> {
                dict.put(sysDatadictListLink.getLinkedItemCode(),sysDatadictListLink.getLinkedItemCode());
            });
            entity.setAssetType(dict.get(entity.getAssetType2()));
            entity.setTradeMarket(list.get(3));
            entity.setCcy(list.get(4));
            if (DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue().equals(entity.getAssetType())){
                /**维护信息-本行/他行存款||大额存单*/
                if (dataList.get(1).size() != 18) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-存款格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepDepositInfoDto(getSvrBussRepDepositInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue().equals(entity.getAssetType())){
                /**维护信息-拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）*/
                if (dataList.get(1).size() != 18) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-回购格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepPlacementInfoDto(getSvrBussRepPlacementInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue().equals(entity.getAssetType())){
                /**维护信息-债券类资产/理财直接融资工具/同业存单*/
                if (dataList.get(1).size() != 24) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-债券格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussFinBondRepInfoDto(getSvrBussFinBondRepInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_非标准化债权类资产_信贷资产流转项目.getValue().equals(entity.getAssetType())){
                /**维护信息-非标准化债权类资产/新增可投资资产*/
                if (dataList.get(1).size() != 78) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-非标格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepAssetCreditNonstdInfoDto(getSvrBussRepAssetCreditNonstdInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-票据类资产/信用证类资产*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepAssetAstBillInfoDto(getSvrBussRepAssetAstBillInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_权益类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-权益类资产*/
                if (dataList.get(1).size() != 24) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-权益类格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepEquityInfoDto(getSvrBussRepEquityInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_金融衍生品.getValue().equals(entity.getAssetType())){
                /**维护信息-金融衍生品*/
                if (dataList.get(1).size() != 14) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-金融衍生品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepFinanciallInfoDto(getSvrBussRepFinanciallInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_QDII债券资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII债券资产*/
                if (dataList.get(1).size() != 51) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-QDII品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepQDIIBondInfoDto(getSvrBussRepQDIIBondInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_QDII拆出_逆回购资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII拆出/逆回购资产*/
                if (dataList.get(1).size() != 51) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-QDII品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepQDIIdemolitionInfoDto(getSvrBussRepQDIIdemolitionInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_QDII股票_基金类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII股票/基金类资产*/
                if (dataList.get(1).size() != 51) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-QDII品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussQDIISharesInfoDto(getSvrBussQDIISharesInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_QDII结构性票据类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII结构性票据*/
                if (dataList.get(1).size() != 51) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-QDII品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepQDIIStructureInfoDto(getSvrBussRepQDIIStructureInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_资产管理产品.getValue().equals(entity.getAssetType())){
                /**维护信息-资产管理产品*/
                if (dataList.get(1).size() != 33) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-资产管理产品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepAssetMngProdInfoDto(getSvrBussRepAssetMngProdInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_商品类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-贵金属/商品类资产*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepNobleMetalInfoDto(getSvrBussRepNobleMetalInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_各类资产的其他项.getValue().equals(entity.getAssetType())){
                /**维护信息-各类资产的其他项*/
                if (dataList.get(1).size() != 39) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-各类资产其他项格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepOtherInvestTargetInfoDto(getSvrBussRepOtherInvestTargetInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_公募基金.getValue().equals(entity.getAssetType()) || DictCodeListExt2.资产_负债类别_私募基金.getValue().equals(entity.getAssetType())){
                /**维护信息-公募基金/私募基金*/
                if (dataList.get(1).size() != 25) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-基金格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepFundInfoDto(getSvrBussRepFundInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue().equals(entity.getAssetType())){
                /**维护信息-委外投资-协议方式*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepOutInvestAgrInfoDto(getSvrBussRepOutInvestAgrInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_其他_负债_类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-其他（负债）类资产*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepOtherInfoDto(getSvrBussRepOtherInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_另类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-另类资产*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepOthKindInfoDto(getSvrBussRepOthKindInfoDto(list));
            }
            else {
                resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "请上传正确的模板！"));
                return resultList;
            }
            getDtoByPageMessgae(entity);
            entity.setCreateUser(LoginUserUtil.getUserName());
            entity.setUpdateUser(LoginUserUtil.getUserName());
            entity.setAssetUuid(IDGenerator.generate(36));
            entity.setStatus(DictCodeListExt2.数据状态_有效.getValue());
            entities.add(entity);
        }
        return resultList;
    }

    /**
     * 维护信息-另类资产
     * @param list
     * @return
     */
    private SvrBussRepOthKindInfoDto getSvrBussRepOthKindInfoDto(List<String> list){
        SvrBussRepOthKindInfoDto dto = new SvrBussRepOthKindInfoDto();
        dto.setCountryArea(list.get(23));
        dto.setAssetName(list.get(24));
        if (StringUtil.isNotEmpty(list.get(25))) {
            dto.setVdate(DateUtil.parse(list.get(25)));
        }
        if (StringUtil.isNotEmpty(list.get(26))) {
            dto.setMdate(DateUtil.parse(list.get(26)));
        }
        if (StringUtil.isNotEmpty(list.get(27))){
            dto.setAssetValue(new BigDecimal(list.get(27)));
        }
        if (StringUtil.isNotEmpty(list.get(28))) {
            dto.setAssetYield(new BigDecimal(list.get(28)));
        }
        dto.setRemark(list.get(54));
        dto.setPbcAssetType1(list.get(55));
        dto.setPbcAssetType2(list.get(56));
        dto.setPbcAssetType3(list.get(57));
        return dto;
    }

    /**
     * 维护信息-其他（负债）类资产
     * @param list
     * @return
     */
    private SvrBussRepOtherInfoDto getSvrBussRepOtherInfoDto(List<String> list){
        SvrBussRepOtherInfoDto dto = new SvrBussRepOtherInfoDto();
        dto.setCountryArea(list.get(49));
        dto.setAssetName(list.get(50));
        if (StringUtil.isNotEmpty(list.get(51))) {
            dto.setTermDate(new BigDecimal(list.get(51)));
        }
        if (StringUtil.isNotEmpty(list.get(52))) {
            dto.setDebtScale(new BigDecimal(list.get(52)));
        }
        if (StringUtil.isNotEmpty(list.get(53))) {
            dto.setAssetYield(new BigDecimal(list.get(53)));
        }
        dto.setRemark(list.get(54));
        dto.setPbcAssetType1(list.get(55));
        dto.setPbcAssetType2(list.get(56));
        dto.setPbcAssetType3(list.get(57));
        return dto;
    }

    /**
     * 维护信息-委外投资-协议方式
     * @param list
     * @return
     */
    private SvrBussRepOutInvestAgrInfoDto getSvrBussRepOutInvestAgrInfoDto(List<String> list){
        SvrBussRepOutInvestAgrInfoDto dto = new SvrBussRepOutInvestAgrInfoDto();
        dto.setAssetName(list.get(29));
        dto.setAssetId(list.get(30));
        dto.setTrustee(list.get(31));
        dto.setManagerId(list.get(32));
        if (StringUtil.isNotEmpty(list.get(34))) {
            dto.setAmt(new BigDecimal(list.get(34)));
        }
        dto.setTruActType(list.get(35));
        dto.setInvestType(list.get(36));
        dto.setCapUseIndu(list.get(37));
        if (StringUtil.isNotEmpty(list.get(38))) {
            dto.setPlanSdate(DateUtil.parse(list.get(38)));
        }
        if (StringUtil.isNotEmpty(list.get(39))) {
            dto.setPlanEdate(DateUtil.parse(list.get(39)));
        }
        dto.setProProperty(list.get(40));
        dto.setRateFlag(list.get(41));
        if (StringUtil.isNotEmpty(list.get(42))) {
            dto.setMaxRate(new BigDecimal(list.get(42)));
        }
        if (StringUtil.isNotEmpty(list.get(43))) {
            dto.setMinRate(new BigDecimal(list.get(43)));
        }
        if (StringUtil.isNotEmpty(list.get(44))) {
            dto.setMgrFeeRate(new BigDecimal(list.get(44)));
        }
        if (StringUtil.isNotEmpty(list.get(45))) {
            dto.setTruFeeRate(new BigDecimal(list.get(45)));
        }
        if (StringUtil.isNotEmpty(list.get(46))) {
            dto.setTradeFeeRate(new BigDecimal(list.get(46)));
        }
        if (StringUtil.isNotEmpty(list.get(47))) {
            dto.setAgencyFeeRate(new BigDecimal(list.get(47)));
        }
        if (StringUtil.isNotEmpty(list.get(48))) {
            dto.setOtherFeeRate(new BigDecimal(list.get(48)));
        }
        dto.setRemark(list.get(54));
        dto.setPbcAssetType1(list.get(55));
        dto.setPbcAssetType2(list.get(56));
        dto.setPbcAssetType3(list.get(57));
        return dto;
    }

    /**
     * 维护信息-公募基金/私募基金
     * @param list
     * @return
     */
    private SvrBussRepFundInfoDto getSvrBussRepFundInfoDto(List<String> list){
        SvrBussRepFundInfoDto dto = new SvrBussRepFundInfoDto();
        dto.setAssetId(list.get(5));
        dto.setAssetName(list.get(6));
        dto.setIndustryShares(list.get(7));
        dto.setRegistOrg1(list.get(8));
        dto.setIsGovernFund(list.get(9));
        dto.setGovernInvestFund(list.get(10));
        dto.setFinancialComissue(list.get(11));
        dto.setFundEnpeName(list.get(12));
        dto.setMngOrgName(list.get(13));
        dto.setTruOrgName(list.get(14));
        dto.setInvestStage(list.get(15));
        dto.setSharesType1(list.get(16));
        dto.setSharesType3(list.get(17));
        dto.setSharesType2(list.get(18));
        dto.setFundInvestAsset(list.get(19));
        dto.setRemark(list.get(20));
        dto.setPrivatefundFlag(list.get(21));
        dto.setPbcAssetType1(list.get(22));
        dto.setPbcAssetType2(list.get(23));
        dto.setPbcAssetType3(list.get(24));
        return dto;
    }

    /**
     * 维护信息-各类资产的其他项
     * @param list
     * @return
     */
    private SvrBussRepOtherInvestTargetInfoDto getSvrBussRepOtherInvestTargetInfoDto(List<String> list){
        SvrBussRepOtherInvestTargetInfoDto dto = new SvrBussRepOtherInvestTargetInfoDto();
        dto.setAssetName(list.get(5));
        dto.setInAssetType(list.get(6));
        dto.setInAssetTypeCmm(list.get(7));
        if (StringUtil.isNotEmpty(list.get(8))) {
            dto.setAssetValue(new BigDecimal(list.get(8)));
        }
        if (StringUtil.isNotEmpty(list.get(9))) {
            dto.setVdate(DateUtil.parse(list.get(9)));
        }
        if (StringUtil.isNotEmpty(list.get(10))) {
            dto.setMdate(DateUtil.parse(list.get(10)));
        }
        dto.setCountryArea(list.get(11));
        dto.setRateFlag(list.get(12));
        if (StringUtil.isNotEmpty(list.get(13))) {
            dto.setAssetYield(new BigDecimal(list.get(13)));
        }
        if (StringUtil.isNotEmpty(list.get(14))) {
            dto.setIntpayCycle(Long.valueOf(list.get(14)));
        }
        dto.setFinancie(list.get(15));
        dto.setFinOrgCode(list.get(16));
        dto.setFcrGradeOut(list.get(17));
        dto.setFinancierType1(list.get(18));
        dto.setFinancierType2(list.get(19));
        dto.setFinancierType3(list.get(20));
        dto.setFinanceProj(list.get(21));
        dto.setIndustry1(list.get(22));
        dto.setProIndustry(list.get(23));
        dto.setIndustryFlag(list.get(24));
        dto.setMonitType(list.get(25));
        dto.setMonTypeComm(list.get(26));
        dto.setOutGrade(list.get(27));
        dto.setGuaType(list.get(28));
        dto.setPledgeType(list.get(29));
        if (StringUtil.isNotEmpty(list.get(30))) {
            dto.setPledgeValue(new BigDecimal(list.get(30)));
        }
        dto.setNatureGuarantee(list.get(31));
        dto.setRelationFinan(list.get(32));
        dto.setGuaTypeRemark(list.get(33));
        dto.setDeptEquity(list.get(34));
        dto.setRemark(list.get(35));
        dto.setPbcAssetType1(list.get(36));
        dto.setPbcAssetType2(list.get(37));
        dto.setPbcAssetType3(list.get(38));
        return dto;
    }

    /**
     * 维护信息-贵金属/商品类资产
     * @param list
     * @return
     */
    private SvrBussRepNobleMetalInfoDto getSvrBussRepNobleMetalInfoDto(List<String> list){
        SvrBussRepNobleMetalInfoDto dto = new SvrBussRepNobleMetalInfoDto();
        dto.setCountryArea(list.get(18));
        dto.setAssetName(list.get(19));
        if (StringUtil.isNotEmpty(list.get(20))) {
            dto.setTermDate(new BigDecimal(list.get(20)));
        }
        if (StringUtil.isNotEmpty(list.get(21))) {
            dto.setAssetValue(new BigDecimal(list.get(21)));
        }
        if (StringUtil.isNotEmpty(list.get(22))) {
            dto.setAssetYield(new BigDecimal(list.get(22)));
        }
        dto.setRemark(list.get(54));
        dto.setPbcAssetType1(list.get(55));
        dto.setPbcAssetType2(list.get(56));
        dto.setPbcAssetType3(list.get(57));
        return dto;
    }

    /**
     * 维护信息-资产管理产品
     * @param list
     * @return
     */
    private SvrBussRepAssetMngProdInfoDto getSvrBussRepAssetMngProdInfoDto(List<String> list){
        SvrBussRepAssetMngProdInfoDto dto = new SvrBussRepAssetMngProdInfoDto();
        dto.setAssetName(list.get(5));
        dto.setFinancialComissue(list.get(6));
        dto.setPartyCode(list.get(7));
        dto.setCheckCode(list.get(8));
        dto.setManagerId(list.get(9));
        dto.setTrusteeId(list.get(10));
        if (StringUtil.isNotEmpty(list.get(11))) {
            dto.setAmt(new BigDecimal(list.get(11)));
        }
        dto.setTruActType(list.get(12));
        dto.setInvestType(list.get(13));
        dto.setCapUseIndu(list.get(14));
        if (StringUtil.isNotEmpty(list.get(15))) {
            dto.setPlanSdate(DateUtil.parse(list.get(15)));
        }
        if (StringUtil.isNotEmpty(list.get(16))) {
            dto.setPlanEdate(DateUtil.parse(list.get(16)));
        }
        dto.setProProperty(list.get(17));
        dto.setRateFlag(list.get(18));
        if (StringUtil.isNotEmpty(list.get(19))) {
            dto.setMaxRate(new BigDecimal(list.get(19)));
        }
        if (StringUtil.isNotEmpty(list.get(20))) {
            dto.setMinRate(new BigDecimal(list.get(20)));
        }
        dto.setBuyStruct(list.get(21));
        dto.setManageType(list.get(22));
        if (StringUtil.isNotEmpty(list.get(23))) {
            dto.setMgrFeeRate(new BigDecimal(list.get(23)));
        }
        if (StringUtil.isNotEmpty(list.get(24))) {
            dto.setTruFeeRate(new BigDecimal(list.get(24)));
        }
        if (StringUtil.isNotEmpty(list.get(25))) {
            dto.setTradeFeeRate(new BigDecimal(list.get(25)));
        }
        if (StringUtil.isNotEmpty(list.get(26))) {
            dto.setAgencyFeeRate(new BigDecimal(list.get(26)));
        }
        if (StringUtil.isNotEmpty(list.get(27))) {
            dto.setOtherFeeRate(new BigDecimal(list.get(27)));
        }
        dto.setRemark(list.get(28));
        dto.setPrivateprdFlag(list.get(29));
        dto.setPbcAssetType1(list.get(30));
        dto.setPbcAssetType2(list.get(31));
        dto.setPbcAssetType3(list.get(32));
        return dto;
    }

    /**
     * 维护信息-QDII结构性票据
     * @param list
     * @return
     */
    private SvrBussRepQDIIStructureInfoDto getSvrBussRepQDIIStructureInfoDto(List<String> list){
        SvrBussRepQDIIStructureInfoDto dto = new SvrBussRepQDIIStructureInfoDto();
        dto.setCountryArea(list.get(30));
        dto.setContractName(list.get(31));
        if (StringUtil.isNotEmpty(list.get(32))) {
            dto.setVdate(DateUtil.parse(list.get(32)));
        }
        if (StringUtil.isNotEmpty(list.get(33))) {
            dto.setMdate(DateUtil.parse(list.get(33)));
        }
        if (StringUtil.isNotEmpty(list.get(34))) {
            dto.setAssetYield(new BigDecimal(list.get(34)));
        }
        if (StringUtil.isNotEmpty(list.get(35))) {
            dto.setIntpayCycle(Long.valueOf(list.get(35)));
        }
        if (StringUtil.isNotEmpty(list.get(36))) {
            dto.setIncomeRatio(new BigDecimal(list.get(36)));
        }
        if (StringUtil.isNotEmpty(list.get(37))) {
            dto.setDerInstrRatio(new BigDecimal(list.get(37)));
        }
        dto.setDerInstrInvType(list.get(38));
        dto.setDerInstrTarget(list.get(39));
        dto.setAcctMode(list.get(40));
        dto.setSpecialCmt1(list.get(41));
        if (StringUtil.isNotEmpty(list.get(42))) {
            dto.setMaxYield(new BigDecimal(list.get(42)));
        }
        if (StringUtil.isNotEmpty(list.get(43))) {
            dto.setMinYield(new BigDecimal(list.get(43)));
        }
        if (StringUtil.isNotEmpty(list.get(44))) {
            dto.setBasicPrice(new BigDecimal(list.get(44)));
        }
        if (StringUtil.isNotEmpty(list.get(45))) {
            dto.setRegDatePrice(new BigDecimal(list.get(45)));
        }
        dto.setTradeFee(list.get(46));
        dto.setRemark(list.get(47));
        dto.setPbcAssetType1(list.get(48));
        dto.setPbcAssetType2(list.get(49));
        dto.setPbcAssetType3(list.get(50));
        return dto;
    }

    /**
     * 维护信息-QDII股票/基金类资产
     * @param list
     * @return
     */
    private SvrBussQDIISharesInfoDto getSvrBussQDIISharesInfoDto(List<String> list){
        SvrBussQDIISharesInfoDto dto = new SvrBussQDIISharesInfoDto();
        dto.setCountryArea(list.get(25));
        dto.setAssetId(list.get(26));
        dto.setAssetName(list.get(27));
        dto.setIssuePartyName(list.get(28));
        dto.setIndustryShares(list.get(29));
        dto.setRemark(list.get(47));
        dto.setPbcAssetType1(list.get(48));
        dto.setPbcAssetType2(list.get(49));
        dto.setPbcAssetType3(list.get(50));
        return dto;
    }

    /**
     * 维护信息-QDII拆出/逆回购资产
     * @param list
     * @return
     */
    private SvrBussRepQDIIdemolitionInfoDto getSvrBussRepQDIIdemolitionInfoDto(List<String> list){
        SvrBussRepQDIIdemolitionInfoDto dto = new SvrBussRepQDIIdemolitionInfoDto();
        dto.setCountryArea(list.get(19));
        if (StringUtil.isNotEmpty(list.get(20))) {
            dto.setVdate(DateUtil.parse(list.get(20)));
        }
        if (StringUtil.isNotEmpty(list.get(21))) {
            dto.setMdate(DateUtil.parse(list.get(21)));
        }
        dto.setRivalParty(list.get(22));
        if (StringUtil.isNotEmpty(list.get(23))) {
            dto.setAssetYield(new BigDecimal(list.get(23)));
        }
        dto.setBasis(list.get(24));
        dto.setRemark(list.get(47));
        dto.setPbcAssetType1(list.get(48));
        dto.setPbcAssetType2(list.get(49));
        dto.setPbcAssetType3(list.get(50));
        return dto;
    }

    /**
     * 维护信息-QDII债券资产
     * @param list
     * @return
     */
    private SvrBussRepQDIIBondInfoDto getSvrBussRepQDIIBondInfoDto(List<String> list){
        SvrBussRepQDIIBondInfoDto dto = new SvrBussRepQDIIBondInfoDto();
        dto.setCountryArea(list.get(5));
        dto.setAssetName(list.get(6));
        dto.setAssetId(list.get(7));
        dto.setIssuePartyName(list.get(8));
        dto.setIndustry(list.get(9));
        if (StringUtil.isNotEmpty(list.get(10))) {
            dto.setVdate(DateUtil.parse(list.get(10)));
        }
        if (StringUtil.isNotEmpty(list.get(11))) {
            dto.setMdate(DateUtil.parse(list.get(11)));
        }
        if (StringUtil.isNotEmpty(list.get(12))) {
            dto.setTermMonth(new BigDecimal(list.get(12)));
        }
        dto.setPartyGrade(list.get(13));
        dto.setSecCreditGradeType(list.get(14));
        if (StringUtil.isNotEmpty(list.get(15))) {
            dto.setAssetYield(new BigDecimal(list.get(15)));
        }
        if (StringUtil.isNotEmpty(list.get(16))) {
            dto.setIntpayCycle(Long.valueOf(list.get(16)));
        }
        dto.setGuaranteeCmt(list.get(17));
        dto.setSpecialCmt(list.get(18));
        dto.setRemark(list.get(47));
        dto.setPbcAssetType1(list.get(48));
        dto.setPbcAssetType2(list.get(49));
        dto.setPbcAssetType3(list.get(50));
        return dto;
    }

    /**
     * 维护信息-金融衍生品
     * @param list
     * @return
     */
    private SvrBussRepFinanciallInfoDto getSvrBussRepFinanciallInfoDto(List<String> list){
        SvrBussRepFinanciallInfoDto dto = new SvrBussRepFinanciallInfoDto();
        dto.setAssetName(list.get(5));
        if (StringUtil.isNotEmpty(list.get(6))) {
            dto.setNomCapi(new BigDecimal(list.get(6)));
        }
        dto.setTargetType3(list.get(7));
        dto.setHoldPurpose(list.get(8));
        dto.setRemark(list.get(9));
        if (StringUtil.isNotEmpty(list.get(10))) {
            dto.setMdate(DateUtil.parse(list.get(10)));
        }
        dto.setPbcAssetType1(list.get(11));
        dto.setPbcAssetType2(list.get(12));
        dto.setPbcAssetType3(list.get(13));
        return dto;
    }

    /**
     * 维护信息-权益类资产
     * @param list
     * @return
     */
    private SvrBussRepEquityInfoDto getSvrBussRepEquityInfoDto(List<String> list){
        SvrBussRepEquityInfoDto dto = new SvrBussRepEquityInfoDto();
        dto.setAssetId(list.get(5));
        dto.setAssetName(list.get(6));
        dto.setSharesType(list.get(7));
        dto.setIndustryShares(list.get(8));
        dto.setInvestStage(list.get(9));
        if (StringUtil.isNotEmpty(list.get(10))) {
            dto.setEquityQuit(DateUtil.parse(list.get(10)));
        }
        dto.setSharesType1(list.get(11));
        dto.setSharesType3(list.get(12));
        dto.setSharesType2(list.get(13));
        dto.setIsPledge(list.get(14));
        dto.setDeptEquity(list.get(15));
        dto.setRemark(list.get(16));
        dto.setBankSecPrefer(list.get(17));
        if (StringUtil.isNotEmpty(list.get(18))) {
            dto.setMdate(DateUtil.parse(list.get(18)));
        }
        dto.setIsGovernFund(list.get(19));
        dto.setIsunlisted(list.get(20));
        dto.setPbcAssetType1(list.get(21));
        dto.setPbcAssetType2(list.get(22));
        dto.setPbcAssetType3(list.get(23));
        return dto;
    }

    /**
     * 维护信息-票据类资产/信用证类资产
     * @param list
     * @return
     */
    private SvrBussRepAssetAstBillInfoDto getSvrBussRepAssetAstBillInfoDto(List<String> list){
        SvrBussRepAssetAstBillInfoDto dto = new SvrBussRepAssetAstBillInfoDto();
        dto.setIsRight(list.get(5));
        dto.setIsResale(list.get(6));
        dto.setNotstandSecType(list.get(7));
        if (StringUtil.isNotEmpty(list.get(8))) {
            dto.setNotstandSecNum(Long.valueOf(list.get(8)));
        }
        if (StringUtil.isNotEmpty(list.get(9))) {
            dto.setAmt(new BigDecimal(list.get(9)));
        }
        if (StringUtil.isNotEmpty(list.get(10))) {
            dto.setRemainTerm(new BigDecimal(list.get(10)));
        }
        if (StringUtil.isNotEmpty(list.get(11))) {
            dto.setMaxRemainTerm(new BigDecimal(list.get(11)));
        }
        if (StringUtil.isNotEmpty(list.get(12))) {
            dto.setMinRemainTerm(new BigDecimal(list.get(12)));
        }
        if (StringUtil.isNotEmpty(list.get(13))) {
            dto.setRepVdate(DateUtil.parse(list.get(13)));
        }
        if (StringUtil.isNotEmpty(list.get(14))) {
            dto.setRepMdate(DateUtil.parse(list.get(14)));
        }
        dto.setRemark(list.get(54));
        dto.setPbcAssetType1(list.get(55));
        dto.setPbcAssetType2(list.get(56));
        dto.setPbcAssetType3(list.get(57));
        return dto;
    }

    /**
     * 维护信息-非标准化债权类资产/新增可投资资产
     * @param list
     * @return
     */
    private SvrBussRepAssetCreditNonstdInfoDto getSvrBussRepAssetCreditNonstdInfoDto(List<String> list){
        SvrBussRepAssetCreditNonstdInfoDto dto = new SvrBussRepAssetCreditNonstdInfoDto();
        dto.setRepProfitType(list.get(5));
        dto.setRepIsResale(list.get(6));
        dto.setAssetName(list.get(7));
        if (StringUtil.isNotEmpty(list.get(8))) {
            dto.setAmt(new BigDecimal(list.get(8)));
        }
        if (StringUtil.isNotEmpty(list.get(9))) {
            dto.setRepPrinBaseamt(new BigDecimal(list.get(9)));
        }
        if (StringUtil.isNotEmpty(list.get(10))) {
            dto.setRepVdate(DateUtil.parse(list.get(10)));
        }
        if (StringUtil.isNotEmpty(list.get(11))) {
            dto.setRepMdate(DateUtil.parse(list.get(11)));
        }
        if (StringUtil.isNotEmpty(list.get(12))) {
            dto.setRepFmdate(DateUtil.parse(list.get(12)));
        }
        dto.setRepRateFlag(list.get(13));
        if (StringUtil.isNotEmpty(list.get(14))) {
            dto.setAssetYield(new BigDecimal(list.get(14)));
        }
        dto.setRepCouponSpecies(list.get(15));
        dto.setInterestRuleFlag(list.get(16));
        if (StringUtil.isNotEmpty(list.get(17))) {
            dto.setRepIntpayCycle(Long.valueOf(list.get(17)));
        }
        dto.setRepIntpayRule(list.get(18));
        dto.setRepRepayCmt(list.get(19));
        dto.setBasis(list.get(20));
        dto.setBasisType(list.get(21));
        dto.setFloatFactorFlag(list.get(22));
        if (StringUtil.isNotEmpty(list.get(23))) {
            dto.setFloatFactor(new BigDecimal(list.get(23)));
        }
        if (StringUtil.isNotEmpty(list.get(24))) {
            dto.setSpreadRate(new BigDecimal(list.get(24)));
        }
        dto.setRepStructGrade(list.get(25));
        dto.setRepRepaymentType(list.get(26));
        dto.setRepPeriodRepaymentFlag(list.get(27));
        dto.setRepBasicAssetType(list.get(28));
        if (StringUtil.isNotEmpty(list.get(29))) {
            dto.setRepExcessProfitRate(new BigDecimal(list.get(29)));
        }
        dto.setRepFinancie(list.get(30));
        dto.setRepFcrGradeIn(list.get(31));
        dto.setRepFcrGradeOut(list.get(32));
        dto.setRepFinancierType1(list.get(33));
        dto.setRepFinancierType3(list.get(34));
        dto.setRepFinancierType2(list.get(35));
        dto.setRepFinanceProj(list.get(36));
        dto.setRepIndustry1(list.get(37));
        dto.setRepIndustryFlag(list.get(38));
        dto.setRepMonitType(list.get(39));
        dto.setRepMonTypeComm(list.get(40));
        dto.setRepGuaType(list.get(41));
        dto.setRepPledgeType(list.get(42));
        dto.setRepPledgeType(list.get(43));
        dto.setRepNatureGuarantee(list.get(44));
        dto.setRepRelationFinan(list.get(45));
        dto.setRepGuaTypeRemark(list.get(46));
        dto.setRepFinancierGrade(list.get(47));
        dto.setRepInGrade(list.get(48));
        dto.setRepOutGrade(list.get(49));
        dto.setRepContainType(list.get(50));
        dto.setRepExerciseType(list.get(51));
        if (StringUtil.isNotEmpty(list.get(52))) {
            dto.setRepExerciseDate(DateUtil.parse(list.get(52)));
        }
        if (StringUtil.isNotEmpty(list.get(53))) {
            dto.setRepFirstExerciseDate(DateUtil.parse(list.get(53)));
        }
        if (StringUtil.isNotEmpty(list.get(54))) {
            dto.setRepExercisePeriod(Integer.valueOf(list.get(54)));
        }
        if (StringUtil.isNotEmpty(list.get(55))) {
            dto.setRepExercisePrice(new BigDecimal(list.get(55)));
        }
        dto.setRepPerpetualClauseType(list.get(56));
        dto.setRepDeferredInterestType(list.get(57));
        dto.setRepDeferredInterestInt(list.get(58));
        if (StringUtil.isNotEmpty(list.get(59))) {
            dto.setRepFirstReprceDate(DateUtil.parse(list.get(59)));
        }
        if (StringUtil.isNotEmpty(list.get(60))) {
            dto.setRepReprceCycle(Integer.valueOf(list.get(60)));
        }
        dto.setRepPartRedemFlag(list.get(61));
        if (StringUtil.isNotEmpty(list.get(62))) {
            dto.setRepPartRedemRate(new BigDecimal(list.get(62)));
        }
        dto.setRepChose(list.get(63));
        dto.setRepConditionCmm(list.get(64));
        dto.setRepFinArea(list.get(65));
        dto.setRepFinOrgCode(list.get(66));
        dto.setCreditOrgCode(list.get(67));
        dto.setCreditOrgName(list.get(68));
        if (StringUtil.isNotEmpty(list.get(69))) {
            dto.setTotalFeeRate(new BigDecimal(list.get(69)));
        }
        dto.setRepProIndustry(list.get(70));
        dto.setRepRemark(list.get(71));
        dto.setRepPrivateprdFlag(list.get(72));
        dto.setRepPppflag(list.get(73));
        dto.setRepBondtostockFlag(list.get(74));
        dto.setRepPbcAssetType1(list.get(75));
        dto.setRepPbcAssetType2(list.get(76));
        dto.setRepPbcAssetType3(list.get(77));
        return dto;
    }

    /**
     * 维护信息-本行/他行存款||大额存单
     * @param list
     * @return
     */
    private SvrBussRepDepositInfoDto getSvrBussRepDepositInfoDto(List<String> list){
        SvrBussRepDepositInfoDto dto = new SvrBussRepDepositInfoDto();
        dto.setRepBankName(list.get(5));
        if (StringUtil.isNotEmpty(list.get(6))) {
            dto.setAmt(new BigDecimal(list.get(6)));
        }
        if (StringUtil.isNotEmpty(list.get(7))) {
            dto.setRepVdate(DateUtil.parse(list.get(7)));
        }
        if (StringUtil.isNotEmpty(list.get(8))) {
            dto.setRepMdate(DateUtil.parse(list.get(8)));
        }
        if (StringUtil.isNotEmpty(list.get(9))) {
            dto.setAssetYield(new BigDecimal(list.get(9)));
        }
        dto.setRepBasis(list.get(10));
        dto.setRepDepoType(list.get(11));
        dto.setRepTargetType(list.get(12));
        dto.setRepTarget(list.get(13));
        dto.setRepRemark(list.get(14));
        dto.setRepPbcAssetType1(list.get(15));
        dto.setRepPbcAssetType2(list.get(16));
        dto.setRepPbcAssetType3(list.get(17));
        return dto;
    }

    /**
     * 维护信息-拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）
     * @param list
     * @return
     */
    private SvrBussRepPlacementInfoDto getSvrBussRepPlacementInfoDto(List<String> list){
        SvrBussRepPlacementInfoDto dto = new SvrBussRepPlacementInfoDto();
        if (StringUtil.isNotEmpty(list.get(5))) {
            dto.setVdate(DateUtil.parse(list.get(5)));
        }
        if (StringUtil.isNotEmpty(list.get(6))) {
            dto.setMdate(DateUtil.parse(list.get(6)));
        }
        dto.setRivalParty(list.get(7));
        dto.setRivalPartyType(list.get(8));
        if (StringUtil.isNotEmpty(list.get(9))) {
            dto.setAssetYield(new BigDecimal(list.get(9)));
        }
        dto.setBasis(list.get(10));
        dto.setTargetType1(list.get(11));
        if (StringUtil.isNotEmpty(list.get(12))) {
            dto.setTargetAmt(new BigDecimal(list.get(12)));
        }
        dto.setRemark(list.get(13));
        dto.setCheckCode(list.get(14));
        dto.setPbcAssetType1(list.get(15));
        dto.setPbcAssetType2(list.get(16));
        dto.setPbcAssetType3(list.get(17));

        return dto;
    }

    /**
     * 维护信息-债券类资产/理财直接融资工具/同业存单
     * @param list
     * @return
     */
    private SvrBussFinBondRepInfoDto getSvrBussFinBondRepInfoDto(List<String> list){
        SvrBussFinBondRepInfoDto dto = new SvrBussFinBondRepInfoDto();
        dto.setAssetCode(list.get(5));
        dto.setRepAssetName(list.get(6));
        dto.setRepSectype(list.get(7));
        dto.setRepIssueMode(list.get(8));
        dto.setRepPartyGrade(list.get(9));
        dto.setRepPartyType1(list.get(10));
        dto.setRepPartyType3(list.get(11));
        dto.setRepPartyType2(list.get(12));
        dto.setRepIndustryLevel2(list.get(13));
        dto.setRepRegistOrg(list.get(14));
        dto.setRepRegistorgComment(list.get(15));
        dto.setRepRemark(list.get(16));
        if (StringUtil.isNotEmpty(list.get(17))) {
            dto.setRepMdate(DateUtil.parse(list.get(17)));
        }
        dto.setRepIsFbzb(list.get(19));
        dto.setRepBankCapital(list.get(20));
        dto.setBankSecZxzq(list.get(18));
//        dto.setIsPpn(list.get());
        dto.setRepPbcAssetType1(list.get(21));
        dto.setRepPbcAssetType2(list.get(22));
        dto.setRepPbcAssetType3(list.get(23));
        return dto;
    }
}
