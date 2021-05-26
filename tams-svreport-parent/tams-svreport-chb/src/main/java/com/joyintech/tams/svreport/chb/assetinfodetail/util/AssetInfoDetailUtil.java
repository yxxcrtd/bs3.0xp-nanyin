package com.joyintech.tams.svreport.chb.assetinfodetail.util;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.*;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AssetInfoDetailUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    static Map<String,Set<String>> map = null;

    @Autowired
    private AssetInfoDetailMapper assetInfoDetailMapper;

    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;

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
            entity.setAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.资产_负债二级类别, list.get(2)));
            Map<String,Object> params = new HashMap<>();
            Map<String,String> dict = new HashMap<>();
            params.put("linkDictCode", DictCode.资产_负债类别.getValue());
            List<SysDatadictListLink> listLink = assetInfoDetailMapper.getDataListLinkData(params);
            listLink.forEach(sysDatadictListLink -> {
                dict.put(sysDatadictListLink.getLinkedItemCode(),sysDatadictListLink.getLinkedItemCode());
            });
            entity.setAssetType(dict.get(entity.getAssetType2()));
            entity.setTradeMarket(SysDataUtil.getZZDictCodeFromName(DictCode.交易流通场所_中债, list.get(3)));
            entity.setCcy(SysDataUtil.getZZDictCodeFromName(DictCode.币种_中债, list.get(4)));
            if (DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue().equals(entity.getAssetType())){
                /**维护信息-本行/他行存款||大额存单*/
                if (dataList.get(1).size() != 18) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-存款格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepDepositInfoDto(getSvrBussRepDepositInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue().equals(entity.getAssetType())){
                /**维护信息-拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）*/
                if (dataList.get(1).size() != 18) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-回购格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepPlacementInfoDto(getSvrBussRepPlacementInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue().equals(entity.getAssetType())){
                /**维护信息-债券类资产/理财直接融资工具/同业存单*/
                if (dataList.get(1).size() != 24) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-债券格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussFinBondRepInfoDto(getSvrBussFinBondRepInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_非标准化债权类资产_信贷资产流转项目.getValue().equals(entity.getAssetType())){
                /**维护信息-非标准化债权类资产/新增可投资资产*/
                if (dataList.get(1).size() != 78) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-非标格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepAssetCreditNonstdInfoDto(getSvrBussRepAssetCreditNonstdInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-票据类资产/信用证类资产*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepAssetAstBillInfoDto(getSvrBussRepAssetAstBillInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_权益类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-权益类资产*/
                if (dataList.get(1).size() != 24) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-权益类格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepEquityInfoDto(getSvrBussRepEquityInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_金融衍生品.getValue().equals(entity.getAssetType())){
                /**维护信息-金融衍生品*/
                if (dataList.get(1).size() != 14) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-金融衍生品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepFinanciallInfoDto(getSvrBussRepFinanciallInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_QDII债券资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII债券资产*/
                if (dataList.get(1).size() != 51) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-QDII品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepQDIIBondInfoDto(getSvrBussRepQDIIBondInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_QDII拆出_逆回购资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII拆出/逆回购资产*/
                if (dataList.get(1).size() != 51) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-QDII品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepQDIIdemolitionInfoDto(getSvrBussRepQDIIdemolitionInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_QDII股票_基金类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII股票/基金类资产*/
                if (dataList.get(1).size() != 51) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-QDII品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussQDIISharesInfoDto(getSvrBussQDIISharesInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_QDII结构性票据.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII结构性票据*/
                if (dataList.get(1).size() != 51) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-QDII品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepQDIIStructureInfoDto(getSvrBussRepQDIIStructureInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_资产管理产品.getValue().equals(entity.getAssetType())){
                /**维护信息-资产管理产品*/
                if (dataList.get(1).size() != 33) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-资产管理产品格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepAssetMngProdInfoDto(getSvrBussRepAssetMngProdInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_商品类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-贵金属/商品类资产*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepNobleMetalInfoDto(getSvrBussRepNobleMetalInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_各类资产的其他项.getValue().equals(entity.getAssetType())){
                /**维护信息-各类资产的其他项*/
                if (dataList.get(1).size() != 39) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-各类资产其他项格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepOtherInvestTargetInfoDto(getSvrBussRepOtherInvestTargetInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_公募基金.getValue().equals(entity.getAssetType()) || DictCodeListExt2.资产_负债类别_私募基金.getValue().equals(entity.getAssetType())){
                /**维护信息-公募基金/私募基金*/
                if (dataList.get(1).size() != 25) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-基金格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepFundInfoDto(getSvrBussRepFundInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue().equals(entity.getAssetType())){
                /**维护信息-委外投资-协议方式*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepOutInvestAgrInfoDto(getSvrBussRepOutInvestAgrInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_其他_负债_类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-其他（负债）类资产*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepOtherInfoDto(getSvrBussRepOtherInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_另类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-另类资产*/
                if (dataList.get(1).size() != 58) {
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-票据及其他格式不正确！"));
                    return resultList;
                }
                entity.setSvrBussRepOthKindInfoDto(getSvrBussRepOthKindInfoDto(list));
            }
            else {
                resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产/负债类别不在值域范围内"));
                return resultList;
            }
            List<String> message = new ArrayList<>();
            List<ValidMessage> messages = chbDataCheckCommonUtil.chbCheckCommon(resultList, entity, "AssetInfoRegist002", entity.getAssetType2(), ValidMessage.class);
            if (!StringUtil.isEmptyList(resultList)){
                messages.forEach(validMessage -> {
                    message.add(validMessage.getMessage());
                });
            }
            getDtoByPageMessgae(entity);
            entity.setCreateUser(LoginUserUtil.getUserName());
            entity.setUpdateUser(LoginUserUtil.getUserName());
            entity.setAssetUuid(IDGenerator.generate(36));
            entity.setStatus(DictCodeListExt2.数据状态_有效.getValue());
            if (CollectionUtils.isNotEmpty(message)) {
                resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+2),
                        StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(entity);
            }
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
        dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(23)));
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
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(55)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(56)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(57)));
        return dto;
    }

    /**
     * 维护信息-其他（负债）类资产
     * @param list
     * @return
     */
    private SvrBussRepOtherInfoDto getSvrBussRepOtherInfoDto(List<String> list){
        SvrBussRepOtherInfoDto dto = new SvrBussRepOtherInfoDto();
        dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(49)));
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
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(55)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(56)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(57)));
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
        dto.setTruActType(SysDataUtil.getZZDictCodeFromName(DictCode.资金实际投向, list.get(35)));
        dto.setInvestType(list.get(36));
        dto.setCapUseIndu(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(37)));
        if (StringUtil.isNotEmpty(list.get(38))) {
            dto.setPlanSdate(DateUtil.parse(list.get(38)));
        }
        if (StringUtil.isNotEmpty(list.get(39))) {
            dto.setPlanEdate(DateUtil.parse(list.get(39)));
        }
        dto.setProProperty(SysDataUtil.getZZDictCodeFromName(DictCode.管理方式_发行登记, list.get(40)));
        dto.setRateFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(41)));
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
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(55)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(56)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(57)));
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
        dto.setIndustryShares(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(7)));
        dto.setRegistOrg1(SysDataUtil.getZZDictCodeFromName(DictCode.登记备案机构, list.get(8)));
        dto.setIsGovernFund(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(9)));
        dto.setGovernInvestFund(SysDataUtil.getZZDictCodeFromName(DictCode.政府投资基金投向, list.get(10)));
        dto.setFinancialComissue(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(11)));
        dto.setFundEnpeName(list.get(12));
        dto.setMngOrgName(list.get(13));
        dto.setTruOrgName(list.get(14));
        dto.setInvestStage(SysDataUtil.getZZDictCodeFromName(DictCode.投资阶段, list.get(15)));
        dto.setSharesType1(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按规模划分, list.get(16)));
        dto.setSharesType3(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按技术领域划分, list.get(17)));
        dto.setSharesType2(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按经济类型划分, list.get(18)));
        dto.setFundInvestAsset(list.get(19));
        dto.setRemark(list.get(20));
        dto.setPrivatefundFlag(list.get(21));
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(22)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(23)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(24)));
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
        dto.setInAssetType(SysDataUtil.getZZDictCodeFromName(DictCode.行内资产类别, list.get(6)));
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
        dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(11)));
        dto.setRateFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(12)));
        if (StringUtil.isNotEmpty(list.get(13))) {
            dto.setAssetYield(new BigDecimal(list.get(13)));
        }
        if (StringUtil.isNotEmpty(list.get(14))) {
            dto.setIntpayCycle(Long.valueOf(list.get(14)));
        }
        dto.setFinancie(list.get(15));
        dto.setFinOrgCode(list.get(16));
        dto.setFcrGradeOut(list.get(17));
        dto.setFinancierType1(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按规模划分, list.get(18)));
        dto.setFinancierType2(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按经济类型划分, list.get(19)));
        dto.setFinancierType3(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按技术领域划分, list.get(20)));
        dto.setFinanceProj(list.get(21));
        dto.setIndustry1(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(22)));
        dto.setProIndustry(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(23)));
        dto.setIndustryFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(24)));
        dto.setMonitType(SysDataUtil.getZZDictCodeFromName(DictCode.重点监控行业和领域类别, list.get(25)));
        dto.setMonTypeComm(list.get(26));
        dto.setOutGrade(SysDataUtil.getZZDictCodeFromName(DictCode.资产评级, list.get(27)));
        dto.setGuaType(SysDataUtil.getZZDictCodeFromName(DictCode.担保方式, list.get(28)));
        dto.setPledgeType(SysDataUtil.getZZDictCodeFromName(DictCode.抵质押物类型, list.get(29)));
        if (StringUtil.isNotEmpty(list.get(30))) {
            dto.setPledgeValue(new BigDecimal(list.get(30)));
        }
        dto.setNatureGuarantee(SysDataUtil.getZZDictCodeFromName(DictCode.担保性质, list.get(31)));
        dto.setRelationFinan(SysDataUtil.getZZDictCodeFromName(DictCode.担保人与融资人关系, list.get(32)));
        dto.setGuaTypeRemark(list.get(33));
        dto.setDeptEquity(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(34)));
        dto.setRemark(list.get(35));
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(36)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(37)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(38)));
        return dto;
    }

    /**
     * 维护信息-贵金属/商品类资产
     * @param list
     * @return
     */
    private SvrBussRepNobleMetalInfoDto getSvrBussRepNobleMetalInfoDto(List<String> list){
        SvrBussRepNobleMetalInfoDto dto = new SvrBussRepNobleMetalInfoDto();
        dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(18)));
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
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(55)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(56)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(57)));
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
        dto.setFinancialComissue(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(6)));
        dto.setPartyCode(list.get(7));
        dto.setCheckCode(list.get(8));
        dto.setManagerId(list.get(9));
        dto.setTrusteeId(list.get(10));
        if (StringUtil.isNotEmpty(list.get(11))) {
            dto.setAmt(new BigDecimal(list.get(11)));
        }
        dto.setTruActType(SysDataUtil.getZZDictCodeFromName(DictCode.资金实际投向, list.get(12)));
        dto.setInvestType(list.get(13));
        dto.setCapUseIndu(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(14)));
        if (StringUtil.isNotEmpty(list.get(15))) {
            dto.setPlanSdate(DateUtil.parse(list.get(15)));
        }
        if (StringUtil.isNotEmpty(list.get(16))) {
            dto.setPlanEdate(DateUtil.parse(list.get(16)));
        }
        dto.setProProperty(SysDataUtil.getZZDictCodeFromName(DictCode.资管计划属性, list.get(17)));
        dto.setRateFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(18)));
        if (StringUtil.isNotEmpty(list.get(19))) {
            dto.setMaxRate(new BigDecimal(list.get(19)));
        }
        if (StringUtil.isNotEmpty(list.get(20))) {
            dto.setMinRate(new BigDecimal(list.get(20)));
        }
        dto.setBuyStruct(SysDataUtil.getZZDictCodeFromName(DictCode.购买结构, list.get(21)));
        dto.setManageType(SysDataUtil.getZZDictCodeFromName(DictCode.管理方式, list.get(22)));
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
        dto.setPrivateprdFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(29)));
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(30)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(31)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(32)));
        return dto;
    }

    /**
     * 维护信息-QDII结构性票据
     * @param list
     * @return
     */
    private SvrBussRepQDIIStructureInfoDto getSvrBussRepQDIIStructureInfoDto(List<String> list){
        SvrBussRepQDIIStructureInfoDto dto = new SvrBussRepQDIIStructureInfoDto();
        dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(30)));
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
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(48)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(49)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(50)));
        return dto;
    }

    /**
     * 维护信息-QDII股票/基金类资产
     * @param list
     * @return
     */
    private SvrBussQDIISharesInfoDto getSvrBussQDIISharesInfoDto(List<String> list){
        SvrBussQDIISharesInfoDto dto = new SvrBussQDIISharesInfoDto();
        dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(25)));
        dto.setAssetId(list.get(26));
        dto.setAssetName(list.get(27));
        dto.setIssuePartyName(list.get(28));
        dto.setIndustryShares(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(25)));
        dto.setRemark(list.get(47));
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(48)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(49)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(50)));
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
        dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(19)));
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
        dto.setBasis(SysDataUtil.getZZDictCodeFromName(DictCode.计息基础_中债, list.get(19)));
        dto.setRemark(list.get(47));
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(48)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(49)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(50)));
        return dto;
    }

    /**
     * 维护信息-QDII债券资产
     * @param list
     * @return
     */
    private SvrBussRepQDIIBondInfoDto getSvrBussRepQDIIBondInfoDto(List<String> list){
        SvrBussRepQDIIBondInfoDto dto = new SvrBussRepQDIIBondInfoDto();
        dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(18)));
        dto.setAssetName(list.get(6));
        dto.setAssetId(list.get(7));
        dto.setIssuePartyName(list.get(8));
        dto.setIndustry(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(9)));
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
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(48)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(49)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(50)));
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
        dto.setTargetType3(SysDataUtil.getZZDictCodeFromName(DictCode.标的类别, list.get(7)));
        dto.setHoldPurpose(SysDataUtil.getZZDictCodeFromName(DictCode.持有目的, list.get(8)));
        dto.setRemark(list.get(9));
        if (StringUtil.isNotEmpty(list.get(10))) {
            dto.setMdate(DateUtil.parse(list.get(10)));
        }
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(11)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(12)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(13)));
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
        dto.setSharesType(SysDataUtil.getZZDictCodeFromName(DictCode.股票类型, list.get(7)));
        dto.setIndustryShares(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(8)));
        dto.setInvestStage(SysDataUtil.getZZDictCodeFromName(DictCode.投资阶段, list.get(9)));
        if (StringUtil.isNotEmpty(list.get(10))) {
            dto.setEquityQuit(DateUtil.parse(list.get(10)));
        }
        dto.setSharesType1(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按规模划分, list.get(11)));
        dto.setSharesType3(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按技术领域划分, list.get(12)));
        dto.setSharesType2(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按经济类型划分, list.get(13)));
        dto.setIsPledge(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(14)));
        dto.setDeptEquity(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(15)));
        dto.setRemark(list.get(16));
        dto.setBankSecPrefer(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(17)));
        if (StringUtil.isNotEmpty(list.get(18))) {
            dto.setMdate(DateUtil.parse(list.get(18)));
        }
        dto.setIsGovernFund(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(19)));
        dto.setIsunlisted(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(20)));
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(21)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(22)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(23)));
        return dto;
    }

    /**
     * 维护信息-票据类资产/信用证类资产
     * @param list
     * @return
     */
    private SvrBussRepAssetAstBillInfoDto getSvrBussRepAssetAstBillInfoDto(List<String> list){
        SvrBussRepAssetAstBillInfoDto dto = new SvrBussRepAssetAstBillInfoDto();
        dto.setIsRight(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(5)));
        dto.setIsResale(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(6)));
        dto.setNotstandSecType(SysDataUtil.getZZDictCodeFromName(DictCode.票据类信用证类资产类型, list.get(7)));
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
        dto.setIndustryShares(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(15)));
        if (StringUtil.isNotEmpty(list.get(16))) {
            dto.setAssetYield(new BigDecimal(list.get(16)));
        }
        dto.setIsBzhpj(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(17)));
        dto.setRemark(list.get(54));
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(55)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(56)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(57)));
        return dto;
    }

    /**
     * 维护信息-非标准化债权类资产/新增可投资资产
     * @param list
     * @return
     */
    private SvrBussRepAssetCreditNonstdInfoDto getSvrBussRepAssetCreditNonstdInfoDto(List<String> list){
        SvrBussRepAssetCreditNonstdInfoDto dto = new SvrBussRepAssetCreditNonstdInfoDto();
        dto.setRepProfitType(SysDataUtil.getZZDictCodeFromName(DictCode.收受益权类型, list.get(5)));
        dto.setRepIsResale(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(6)));
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
        dto.setRepRateFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(13)));
        if (StringUtil.isNotEmpty(list.get(14))) {
            dto.setAssetYield(new BigDecimal(list.get(14)));
        }
        dto.setRepCouponSpecies(SysDataUtil.getZZDictCodeFromName(DictCode.计息类型, list.get(15)));
        dto.setInterestRuleFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(16)));
        if (StringUtil.isNotEmpty(list.get(17))) {
            dto.setRepIntpayCycle(Long.valueOf(list.get(17)));
        }
        dto.setRepIntpayRule(SysDataUtil.getZZDictCodeFromName(DictCode.利息分布方式, list.get(18)));
        dto.setRepRepayCmt(list.get(19));
        dto.setBasis(SysDataUtil.getZZDictCodeFromName(DictCode.计息基础_中债, list.get(20)));
        dto.setBasisType(SysDataUtil.getZZDictCodeFromName(DictCode.基准利率, list.get(21)));
        dto.setFloatFactorFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(22)));
        if (StringUtil.isNotEmpty(list.get(23))) {
            dto.setFloatFactor(new BigDecimal(list.get(23)));
        }
        if (StringUtil.isNotEmpty(list.get(24))) {
            dto.setSpreadRate(new BigDecimal(list.get(24)));
        }
        dto.setRepStructGrade(SysDataUtil.getZZDictCodeFromName(DictCode.结构档次, list.get(25)));
        dto.setRepRepaymentType(SysDataUtil.getZZDictCodeFromName(DictCode.还本方式, list.get(26)));
        dto.setRepPeriodRepaymentFlag(SysDataUtil.getZZDictCodeFromName(DictCode.分期还本条款标识, list.get(27)));
        dto.setRepBasicAssetType(list.get(28));
        if (StringUtil.isNotEmpty(list.get(29))) {
            dto.setRepExcessProfitRate(new BigDecimal(list.get(29)));
        }
        dto.setRepFinancie(list.get(30));
        dto.setRepFcrGradeIn(list.get(31));
        dto.setRepFcrGradeOut(list.get(32));
        dto.setRepFinancierType1(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按规模划分, list.get(33)));
        dto.setRepFinancierType3(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按经济类型划分, list.get(34)));
        dto.setRepFinancierType2(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按技术领域划分, list.get(35)));
        dto.setRepFinanceProj(list.get(36));
        dto.setRepIndustry1(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(37)));
        dto.setRepIndustryFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(38)));
        dto.setRepMonitType(SysDataUtil.getZZDictCodeFromName(DictCode.重点监控行业和领域类别, list.get(39)));
        dto.setRepMonTypeComm(list.get(40));
        dto.setRepGuaType(SysDataUtil.getZZDictCodeFromName(DictCode.担保方式, list.get(41)));
        dto.setRepPledgeType(SysDataUtil.getZZDictCodeFromName(DictCode.抵质押物类型, list.get(42)));
        if (StringUtil.isNotEmpty(list.get(43))){
            dto.setRepPledgeValue(new BigDecimal(list.get(43)));
        }
        dto.setRepNatureGuarantee(SysDataUtil.getZZDictCodeFromName(DictCode.担保性质, list.get(44)));
        dto.setRepRelationFinan(SysDataUtil.getZZDictCodeFromName(DictCode.担保人与融资人关系, list.get(45)));
        dto.setRepGuaTypeRemark(list.get(46));
        dto.setRepFinancierGrade(SysDataUtil.getZZDictCodeFromName(DictCode.资产评级, list.get(47)));
        dto.setRepInGrade(list.get(48));
        dto.setRepOutGrade(SysDataUtil.getZZDictCodeFromName(DictCode.资产评级, list.get(49)));
        dto.setRepContainType(SysDataUtil.getZZDictCodeFromName(DictCode.含权类型, list.get(50)));
        dto.setRepExerciseType(SysDataUtil.getZZDictCodeFromName(DictCode.资产行权方式, list.get(51)));
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
        dto.setRepPerpetualClauseType(SysDataUtil.getZZDictCodeFromName(DictCode.永续条款类型, list.get(56)));
        dto.setRepDeferredInterestType(SysDataUtil.getZZDictCodeFromName(DictCode.利息递延条款类型, list.get(57)));
        dto.setRepDeferredInterestInt(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(58)));
        if (StringUtil.isNotEmpty(list.get(59))) {
            dto.setRepFirstReprceDate(DateUtil.parse(list.get(59)));
        }
        if (StringUtil.isNotEmpty(list.get(60))) {
            dto.setRepReprceCycle(Integer.valueOf(list.get(60)));
        }
        dto.setRepPartRedemFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(61)));
        if (StringUtil.isNotEmpty(list.get(62))) {
            dto.setRepPartRedemRate(new BigDecimal(list.get(62)));
        }
        dto.setRepChose(list.get(63));
        dto.setRepConditionCmm(list.get(64));
        dto.setRepFinArea(SysDataUtil.getZZDictCodeFromName(DictCode.行政区划_市, list.get(65)));
        dto.setRepFinOrgCode(list.get(66));
        dto.setCreditOrgCode(list.get(67));
        dto.setCreditOrgName(list.get(68));
        if (StringUtil.isNotEmpty(list.get(69))) {
            dto.setTotalFeeRate(new BigDecimal(list.get(69)));
        }
        dto.setRepProIndustry(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(70)));
        dto.setRepRemark(list.get(71));
        dto.setRepPrivateprdFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(72)));
        dto.setRepPppflag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(73)));
        dto.setRepBondtostockFlag(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(74)));
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
        dto.setRepBasis(SysDataUtil.getZZDictCodeFromName(DictCode.计息基础_中债, list.get(10)));
        dto.setRepDepoType(SysDataUtil.getZZDictCodeFromName(DictCode.存款类型, list.get(11)));
        dto.setRepTargetType(SysDataUtil.getZZDictCodeFromName(DictCode.标的类别, list.get(12)));
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
        dto.setRivalPartyType(SysDataUtil.getZZDictCodeFromName(DictCode.对手方类型, list.get(8)));
        if (StringUtil.isNotEmpty(list.get(9))) {
            dto.setAssetYield(new BigDecimal(list.get(9)));
        }
        dto.setBasis(SysDataUtil.getZZDictCodeFromName(DictCode.计息基础_中债, list.get(10)));
        dto.setTargetType1(SysDataUtil.getZZDictCodeFromName(DictCode.回购标的类别, list.get(11)));
        if (StringUtil.isNotEmpty(list.get(12))) {
            dto.setTargetAmt(new BigDecimal(list.get(12)));
        }
        dto.setRemark(list.get(13));
        dto.setCheckCode(list.get(14));
        dto.setPbcAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产一级分类, list.get(15)));
        dto.setPbcAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产二级分类, list.get(16)));
        dto.setPbcAssetType3(SysDataUtil.getZZDictCodeFromName(DictCode.人行资产三级分类, list.get(17)));

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
        dto.setRepSectype(SysDataUtil.getZZDictCodeFromName(DictCode.具体类别, list.get(7)));
        dto.setRepIssueMode(SysDataUtil.getZZDictCodeFromName(DictCode.发行方式, list.get(8)));
        dto.setRepPartyGrade(SysDataUtil.getZZDictCodeFromName(DictCode.主体评级_中债, list.get(9)));
        dto.setRepPartyType1(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按规模划分, list.get(10)));
        dto.setRepPartyType3(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按技术领域划分, list.get(11)));
        dto.setRepPartyType2(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按经济类型划分, list.get(12)));
        dto.setRepIndustry(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业一级分类, list.get(13)));
        dto.setRepRegistOrg(SysDataUtil.getZZDictCodeFromName(DictCode.登记托管机构_中债, list.get(14)));
        dto.setRepRegistorgComment(SysDataUtil.getZZDictCodeFromName(DictCode.登记托管机构_监管报送, list.get(15)));
        dto.setRepRemark(list.get(16));
        if (StringUtil.isNotEmpty(list.get(17))) {
            dto.setRepMdate(DateUtil.parse(list.get(17)));
        }
        dto.setBankSecZxzq(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(10)));
        dto.setRepIsFbzb(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(10)));
        dto.setRepBankCapital(list.get(20));
        dto.setRepPbcAssetType1(list.get(21));
        dto.setRepPbcAssetType2(list.get(22));
        dto.setRepPbcAssetType3(list.get(23));
        return dto;
    }

    public List<ExcelValidDto> checkExcelTemp(String fileName){
        List<ExcelValidDto> tempCheckResult = new ArrayList<ExcelValidDto>();
        String errorMsg = "导入模板不一致，请重新选择文件！";
        ExcelValidDto excelValidDto = new ExcelValidDto();
        excelValidDto.setErrorInfo(errorMsg);
        tempCheckResult.add(excelValidDto);
        List<List<String>> tempDataList;
        try {
            tempDataList =  ExcelUtil.readToList(
                    new File(fileName), 0);
        }
        catch (FileNotFoundException e) {
            logger.error(e.getMessage(),e);
            // Excel解析异常
            return tempCheckResult;
        }
        if (StringUtil.isEmptyList(tempDataList)) {
            // Excel没有有效数据
            return tempCheckResult;
        }
        List<String> excelTypeList = tempDataList.get(0);
        List<String> excelHeadList = tempDataList.get(1);
        List<String> headList = new ArrayList();
        if (excelTypeList.contains("本行/他行存款||大额存单")){
            getHeadList_01(headList);
        }else if (excelTypeList.contains("拆放同业及买入返售/同业拆入及卖出回购")){
            getHeadList_02(headList);
        }else if (excelTypeList.contains("债券类资产/理财直接融资工具/同业存单")){
            getHeadList_03(headList);
        }else if (excelTypeList.contains("非标准化债权类资产/新增可投资资产")){
            getHeadList_04(headList);
        }else if (!Collections.disjoint(excelTypeList,Arrays.asList("票据类/信用证类资产","贵金属/商品","另类资产","委外投资——协议方式","其他（负债）类资产"))){
            getHeadList_05(headList);
        }else if (excelTypeList.contains("权益类资产")){
            getHeadList_06(headList);
        }else if (excelTypeList.contains("金融衍生品")){
            getHeadList_07(headList);
        }else if (!Collections.disjoint(excelTypeList,Arrays.asList("QDII债券资产","QDII拆出/逆回购资产","QDII股票/基金类资产","QDII结构性票据类资产"))){
            getHeadList_08(headList);
        }else if (excelTypeList.contains("资产管理产品")){
            getHeadList_12(headList);
        }else if (excelTypeList.contains("其他非标准化债权类/权益类/代客境外理财投资QDII/新增可投资资产")){
            getHeadList_14(headList);
        }else if (excelTypeList.contains("公募基金/私募基金")){
            getHeadList_L01(headList);
        }else {
            return tempCheckResult;
        }
        if (excelHeadList.size() < headList.size()) {
            // Excel中列数少于要求列数
            return tempCheckResult;
        }

        for (int i = 0; i < headList.size(); i++ ) {
            if (!excelHeadList.get(i).contains(headList.get(i))) {
                // Excel中列不一致
                return tempCheckResult;
            }
        }
        return null;
    }

    private void getHeadList_01(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("资金存入银行");
        headList.add("*存款金额");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("*年利率%");
        headList.add("*计息基础");
        headList.add("存款类型");
        headList.add("结构性存款挂钩标的类别");
        headList.add("结构性存款挂钩标的");
        headList.add("备注");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_02(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("*对手方");
        headList.add("*对手方类型");
        headList.add("*年利率%");
        headList.add("*计息基础");
        headList.add("回购标的类别");
        headList.add("回购标的金额");
        headList.add("备注");
        headList.add("对应资管计划代码");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_03(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*代码");
        headList.add("*名称");
        headList.add("具体类别");
        headList.add("*发行方式");
        headList.add("*主体评级");
        headList.add("*发行机构类型（按规模划分）");
        headList.add("*发行机构类型（按技术领域划分）");
        headList.add("*发行机构类型（按经济类型划分）");
        headList.add("*发行机构所属行业");
        headList.add("*登记托管机构");
        headList.add("登记托管机构说明");
        headList.add("备注");
        headList.add("到期日");
        headList.add("是否专项债券");
        headList.add("是否非标转标资产");
        headList.add("银行资本补充工具");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_04(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("收/受益权类型");
        headList.add("*是否属于买入返售");
        headList.add("*名称");
        headList.add("*金额");
        headList.add("份额面值");
        headList.add(" *起息日");
        headList.add("*到期日");
        headList.add("法定到期日");
        headList.add("*是否有预期收益率");
        headList.add("项目收益率(利率)%");
        headList.add("计息类型");
        headList.add("规则付息标识");
        headList.add("*付息频率（个月/次）");
        headList.add("利息分布方式");
        headList.add("*还本付息情况说明");
        headList.add("*计息基础");
        headList.add(" 基准利率种类");
        headList.add("是否有浮动因子");
        headList.add("浮动因子（%）");
        headList.add("利差(BP)");
        headList.add("结构档次");
        headList.add("还本方式");
        headList.add("*分期还本条款标识");
        headList.add("基础资产类型");
        headList.add("超额收益分配比例（%）");
        headList.add("*融资人");
        headList.add("*融资人内部信用评级");
        headList.add("外部评级机构名称及对融资人评级结果");
        headList.add("*融资人类型（按规模划分）");
        headList.add("*融资人类型（按技术领域划分）");
        headList.add("*融资人类型（按经济类型划分）");
        headList.add("*融资项目");
        headList.add("*融资人所属行业");
        headList.add("*项目是否属于重点监控行业和领域");
        headList.add("重点监控行业和领域类别");
        headList.add("重点监控行业和领域类别说明");
        headList.add("担保方式");
        headList.add("抵质押物类型");
        headList.add("抵质押物价值（元）");
        headList.add("担保性质");
        headList.add("担保人与融资人关系");
        headList.add("担保情况说明");
        headList.add("*融资人主体评级");
        headList.add("*资产内部评级");
        headList.add("*资产外部评级");
        headList.add("含权类型");
        headList.add("行权方式");
        headList.add("固定行权日");
        headList.add("首次行权日期");
        headList.add("行权周期");
        headList.add("行权价格");
        headList.add("永续条款类型");
        headList.add("利息递延条款类型");
        headList.add("递延利息是否计息");
        headList.add("首次重定价日期");
        headList.add("重定价周期");
        headList.add("部分赎回标识");
        headList.add("部分赎回比例（%）");
        headList.add("*选择权");
        headList.add("*行权条件说明");
        headList.add("*融资人所属地区");
        headList.add("*融资人组织机构（社会信用）代码");
        headList.add("增信机构代码");
        headList.add("增信机构名称");
        headList.add("*融资总费率%");
        headList.add("*融资项目所属行业");
        headList.add("备注");
        headList.add("地方政府融资平台");
        headList.add("公共PPP");
        headList.add("是否投向市场化债转股相关产品的部分");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_05(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*是否为收/受益权");
        headList.add("*是否属于买入返售");
        headList.add("*类型");
        headList.add("*数量");
        headList.add("*合计金额");
        headList.add("*加权剩余期限（天）");
        headList.add("*最长剩余期限（天）");
        headList.add("*最短剩余期限（天）");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("*行业");
        headList.add("*贴现利率（%）");
        headList.add("是否标准化票据");
        headList.add("*所属国家或地区");
        headList.add("*名称");
        headList.add("期限（天）");
        headList.add("*资产价值");
        headList.add(" 资产收益率%");
        headList.add("*所属国家或地区");
        headList.add("*名称");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("*资产价值");
        headList.add("资产收益率%");
        headList.add("委外投资协议名称");
        headList.add("委外投资协议编号");
        headList.add("受托人");
        headList.add("实际管理人");
        headList.add("托管人");
        headList.add("委托投资金额");
        headList.add("资金实际投向");
        headList.add("资金运用方式");
        headList.add("资金运用行业");
        headList.add("投资运作起始日期");
        headList.add("投资运作终止日期");
        headList.add("委外投资属性");
        headList.add("是否有预期收益率");
        headList.add("预期最高收益率");
        headList.add("预期最低收益率");
        headList.add("管理费率%");
        headList.add("托管费率%");
        headList.add("交易相关合计费率%");
        headList.add("中介服务机构合计费率%");
        headList.add("其他合计费率%");
        headList.add("*所属国家或地区");
        headList.add("*名称");
        headList.add("期限（天）");
        headList.add("*负债规模");
        headList.add(" 利率%");
        headList.add("备注");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_06(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("股票代码");
        headList.add(" *股票/企业名称");
        headList.add("股票类型");
        headList.add("*行业");
        headList.add("投资阶段");
        headList.add("股权退出安排");
        headList.add("*企业类型（按规模划分）");
        headList.add("*企业类型（按技术领域划分）");
        headList.add("*企业类型（按经济类型划分）");
        headList.add("*是否为质押融资");
        headList.add("*是否为债转股");
        headList.add("备注");
        headList.add("是否商业银行优先股");
        headList.add("到期日");
        headList.add("是否创业投资基金");
        headList.add("是否政府出资产业投资基金");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_07(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*名称");
        headList.add("*名义本金");
        headList.add("*标的类别");
        headList.add("*持有目的");
        headList.add("备注");
        headList.add("到期日");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_08(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*所属国家或地区");
        headList.add("*债券名称");
        headList.add("*债券代码");
        headList.add("*发行机构");
        headList.add("*发行机构所属行业");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("*期限（月）");
        headList.add("*发行机构主体信用评级");
        headList.add("*债券信用评级");
        headList.add("*票面利率%");
        headList.add("*付息频率（个月/次）");
        headList.add("*担保情况说明");
        headList.add("*是否含权等特殊条款情况说明");
        headList.add("*所属国家或地区");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("*对手方");
        headList.add("*年利率%");
        headList.add("*计息基础");
        headList.add("*所属国家或地区");
        headList.add("*股票/基金代码");
        headList.add("*股票/基金名称");
        headList.add("*发行机构");
        headList.add("*行业");
        headList.add("*所属国家或地区");
        headList.add("*合约名称");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("票面利率%");
        headList.add("付息频率（个月/次）");
        headList.add("*固定收益部分所占比例(%)");
        headList.add("*衍生金融工具所占比例(%)");
        headList.add("*衍生金融工具具体投资方式");
        headList.add("*衍生金融工具挂钩的标的资产");
        headList.add("*利息结算方式");
        headList.add("*含权情况说明");
        headList.add("*结构性票据最高收益率%");
        headList.add("结构性票据最低收益率%");
        headList.add("挂钩标的资产基准价格");
        headList.add("*挂钩标的资产登记日价格");
        headList.add("*交易费");
        headList.add("备注");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_12(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add(" *资管计划名称");
        headList.add("是否由金融资产投资公司发行");
        headList.add("*资管计划发起人机构编码");
        headList.add("资管计划登记编码");
        headList.add("*管理人");
        headList.add("*托管人");
        headList.add("*金额");
        headList.add("*资金实际投向");
        headList.add(" *资金运用方式");
        headList.add(" *资金运用行业");
        headList.add("*资管计划成立日期");
        headList.add("*资管计划终止日期");
        headList.add("*资管计划属性");
        headList.add("*是否有预期收益率");
        headList.add("预期最高收益率%");
        headList.add("预期最低收益率%");
        headList.add("*购买结构");
        headList.add("*管理方式");
        headList.add("*管理费率%");
        headList.add("*托管费率%");
        headList.add("*交易相关合计费率%");
        headList.add("*中介服务机构合计费率%");
        headList.add(" *其他合计费率%");
        headList.add("备注");
        headList.add("是否投向金融资产投资公司发行的私募资产管理产品");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_14(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*名称");
        headList.add("*行内资产类别");
        headList.add("行内资产类别说明");
        headList.add("*金额");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("*所属国家或地区");
        headList.add("*是否有预期收益率");
        headList.add("项目收益率（利率）%");
        headList.add("*付息频率（个月/次）");
        headList.add("*融资人");
        headList.add("*融资人组织机构（社会信用）代码");
        headList.add("外部评级机构名称及对融资人评级结果");
        headList.add("*融资人类型（按规模划分）");
        headList.add("*融资人类型（按技术领域划分）");
        headList.add("*融资人类型（按经济类型划分）");
        headList.add("*融资项目");
        headList.add("*融资人所属行业");
        headList.add("*融资项目所属行业");
        headList.add("*项目是否属于重点监控行业和领域");
        headList.add("重点监控行业和领域类别");
        headList.add("重点监控行业和领域类别说明");
        headList.add("对应资产外部评级");
        headList.add("担保方式");
        headList.add("抵质押物类型");
        headList.add("抵质押物价值（元）");
        headList.add("担保性质");
        headList.add("担保人与融资人关系");
        headList.add("担保情况说明");
        headList.add("是否为债转股");
        headList.add("备注");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }

    private void getHeadList_L01(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*基金代码");
        headList.add("*基金名称");
        headList.add("*行业");
        headList.add("*登记备案机构");
        headList.add("是否属于政府投资基金");
        headList.add("政府投资基金投向");
        headList.add("是否由金融资产投资公司发行");
        headList.add("基金公司名称");
        headList.add("*基金管理机构名称");
        headList.add("*基金托管机构名称");
        headList.add("投资阶段");
        headList.add("投资企业类型（按规模划分）");
        headList.add("投资企业类型（按技术领域划分）");
        headList.add("投资企业类型（按经济类型划分）");
        headList.add("*基金投资资产");
        headList.add("备注");
        headList.add("是否投向金融资产投资公司或其附属机构发行的私募股权投资基金");
        headList.add("新人行一级分类");
        headList.add("新人行二级分类");
        headList.add("新人行三级分类");
    }



}
