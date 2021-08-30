package com.joyintech.tams.invest.risk.assetinfodetail.util;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.invest.common.util.SysDataUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.AssetInfoDetailRisk001Dto;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussQDIISharesInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepEquityInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepFinanciallInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepQDIIBondInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
public class AssetInfoDetailRiskUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    static Map<String,Set<String>> map = null;

    static Map<String,String> dict = new HashMap<>();

    static Map<String,String> rule = new HashMap<>();

//    TODO
//    @Autowired
//    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;
//
//    @Autowired
//    private ChbDataCheckCommonMapper chbDataCheckCommonMapper;
    
    /**
	 * 资产要素service
	 */
	@Autowired
	private AssetInfoDetailRisk001Service assetInfoDetailRisk001Service;

    private static String ISNUMBER2 = "isNumber2";

    private static String ISVALIDDATE = "isValidDate";


    private static String SVRBUSSREPEQUITYINFODTO = "svrBussRepEquityInfoRiskDto";

    private static String SVRBUSSREPFINANCIALLINFODTO = "svrBussRepFinanciallInfoRiskDto";

    private static String SVRBUSSREPQDIIBONDINFODTO = "svrBussRepQDIIBondInfoRiskDto";

    /**
     * 获取资产及二级类型的关系
     * @return
     */
    public synchronized Map<String, Set<String>> getDataListLink(){

        if(map==null||map.isEmpty()){
            map = new HashMap<>();
            //界面配置,根据SYS_DATADICT_LIST_LINK
            Map<String,Object> params = new HashMap<>();
            params.put("linkDictCode", DictCode.资产_负债类别.getValue());
            List<SysDatadictListLink> listLink = assetInfoDetailRisk001Service.getDataListLink1(params);
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

    public AssetInfoDetailRisk001Dto getDtoByPageMessgae(AssetInfoDetailRisk001Dto dto)  {
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

    public synchronized List<ExcelValidDto> convertToBean(List<AssetInfoDetailRisk001Dto> entities , List<List<String>> dataList){
        List<ExcelValidDto> resultList = new ArrayList<>();
        Map<String,Object> params = new HashMap<>();
        params.put("linkDictCode", DictCode.资产_负债类别.getValue());
        List<SysDatadictListLink> listLink = assetInfoDetailRisk001Service.getDataListLink1(params);
        dict = new HashMap<>();
        listLink.forEach(sysDatadictListLink -> {
                dict.put(sysDatadictListLink.getItemCode(), sysDatadictListLink.getLinkedItemCode());
        });
        params.put("linkDictCode", DictCode.机构所属行业一级分类.getValue());
        List<SysDatadictListLink> industryListLink = assetInfoDetailRisk001Service.getDataListLink1(params);
        industryListLink.forEach(sysDatadictListLink -> {
            dict.put("industry" + sysDatadictListLink.getItemCode(),sysDatadictListLink.getLinkedItemCode());
        });
        params.put("linkDictCode", DictCode.人行资产一级分类.getValue());
        List<SysDatadictListLink> pbcAssetType1ListLink = assetInfoDetailRisk001Service.getDataListLink1(params);
        pbcAssetType1ListLink.forEach(sysDatadictListLink -> {
            dict.put("pbcAssetType1" + sysDatadictListLink.getItemCode(),sysDatadictListLink.getLinkedItemCode());
        });
        params.put("linkDictCode", DictCode.人行资产二级分类.getValue());
        List<SysDatadictListLink> pbcAssetType2ListLink = assetInfoDetailRisk001Service.getDataListLink1(params);
        pbcAssetType2ListLink.forEach(sysDatadictListLink -> {
            dict.put("pbcAssetType2" + sysDatadictListLink.getItemCode(),sysDatadictListLink.getLinkedItemCode());
        });
        rule = new HashMap<>();
//        TODO
//        List<ChbCheckRule> listRules = chbDataCheckCommonMapper.queryChbCheckRule("AssetInfoRegist002","1");
//        if (!StringUtil.isEmptyList(listRules)){
//            listRules.forEach(chbCheckRule -> {
//                rule.put(chbCheckRule.getSrcproperty() + GlobalConstant.UNDER_LINE + chbCheckRule.getCheckmethod(),chbCheckRule.getRemark());
//            });
//        }
        for (int i = 0; i < dataList.size(); i++ ) {
            if ( i < 2 ){
                continue;
            }
            List<String> list = dataList.get(i);
            AssetInfoDetailRisk001Dto entity = new AssetInfoDetailRisk001Dto();
            List<ValidMessage> messages = new ArrayList<>();
            List<String> headList = new ArrayList();
            getHeadList_gt(headList);
            if (StringUtil.isNotEmpty(list.get(headList.indexOf("*行内资产/负债编码")))) {
                entity.setAssetCode(list.get(headList.indexOf("*行内资产/负债编码")));
            }else {
                messages.add(new ValidMessage("行内资产/负债编码数据不能为空。"));
            }
            if (StringUtil.isNotEmpty(list.get(headList.indexOf("*发行机构代码")))) {
                entity.setIssuePartyId(list.get(headList.indexOf("*发行机构代码")));
            }else {
                messages.add(new ValidMessage("发行机构代码数据不能为空。"));
            }
            if (StringUtil.isNotEmpty(list.get(headList.indexOf("*资产/负债类别")))) {
                entity.setAssetType2(SysDataUtil.getZZDictCodeFromName(DictCode.资产_负债二级类别, list.get(headList.indexOf("*资产/负债类别"))));
            }else {
                messages.add(new ValidMessage("资产/负债类别数据不能为空。"));
            }
            entity.setAssetType(dict.get(entity.getAssetType2()));
            if (StringUtil.isNotEmpty(list.get(headList.indexOf("*交易流通场所")))) {
                entity.setTradeMarket(SysDataUtil.getZZDictCodeFromName(DictCode.交易流通场所_中债, list.get(headList.indexOf("*交易流通场所"))));
            }else {
                messages.add(new ValidMessage("交易流通场所数据不能为空。"));
            }
            if (StringUtil.isNotEmpty(list.get(headList.indexOf("*币种")))) {
                entity.setCcy(SysDataUtil.getZZDictCodeFromName(DictCode.币种_中债, list.get(headList.indexOf("*币种"))));
            }else {
                messages.add(new ValidMessage("币种数据不能为空。"));
            }
            if(StringUtil.isNotEmpty(list.get(headList.indexOf("*资产一级分类")))){
                entity.setOrgAssetType1(SysDataUtil.getZZDictCodeFromName(DictCode.中债_上游资产一级分类,
                        list.get(headList.indexOf("*资产一级分类"))));
            }else{
                messages.add(new ValidMessage("资产一级分类数据不能为空。"));
            }

           if (DictCodeListExt2.资产_负债类别_权益类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-权益类资产*/
//                if (dataList.get(1).size() != 24) {
//                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-权益类格式不正确！"));
//                    return resultList;
//                }
               headList.clear();
               getHeadList_06(headList);
               //资产名称
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*股票/企业名称")))) {
                   entity.setAssetName(list.get(headList.indexOf("*股票/企业名称")));
               }else {
                   messages.add(new ValidMessage("股票/企业名称数据不能为空。"));
               }
                entity.setSvrBussRepEquityInfoRiskDto(getSvrBussRepEquityInfoRiskDto(messages , list,headList));
            }else if (DictCodeListExt2.资产_负债类别_金融衍生品.getValue().equals(entity.getAssetType())){
                /**维护信息-金融衍生品*/
//                if (dataList.get(1).size() != 14) {
//                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-金融衍生品格式不正确！"));
//                    return resultList;
//                }
               headList.clear();
               getHeadList_07(headList);
               //资产名称
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*名称")))) {
                   entity.setAssetName(list.get(headList.indexOf("*名称")));
               }else {
                   messages.add(new ValidMessage("名称数据不能为空。"));
               }
               //起息日
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*起息日")))) {
                   if (CommonUtil.isEnDateStr(list.get(headList.indexOf("*起息日")))){
                       entity.setVdate(DateUtil.parse(list.get(headList.indexOf("*起息日"))));
                   }else {
                       entity.setVdate(new Date());
                       messages.add(new ValidMessage("起息日必须为日期格式（yyyy-MM-dd）"));
                   }
               }else {
                   messages.add(new ValidMessage("起息日数据不能为空。"));
               }
               //到期日
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*到期日")))) {
                   if (CommonUtil.isEnDateStr(list.get(headList.indexOf("*到期日")))){
                       entity.setMdate(DateUtil.parse(list.get(headList.indexOf("*到期日"))));
                   }else {
                       entity.setMdate(new Date());
                       messages.add(new ValidMessage("到期日必须为日期格式（yyyy-MM-dd）"));
                   }
               }else {
                   messages.add(new ValidMessage("到期日数据不能为空。"));
               }
                entity.setSvrBussRepFinanciallInfoRiskDto(getSvrBussRepFinanciallInfoRiskDto(messages , list,headList));
            }else if (DictCodeListExt2.资产_负债类别_QDII债券资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII债券资产*/
//                if (dataList.get(1).size() != 51) {
//                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-QDII品格式不正确！"));
//                    return resultList;
//                }
               headList.clear();
               getHeadList_08(headList);
               //资产名称
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*债券名称")))) {
                   entity.setAssetName(list.get(headList.indexOf("*债券名称")));
               }else {
                   messages.add(new ValidMessage("债券名称数据不能为空。"));
               }
               //起息日
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*起息日")))) {
                   if (CommonUtil.isEnDateStr(list.get(headList.indexOf("*起息日")))){
                       entity.setVdate(DateUtil.parse(list.get(headList.indexOf("*起息日"))));
                   }else {
                       entity.setVdate(new Date());
                       messages.add(new ValidMessage("起息日必须为日期格式（yyyy-MM-dd）"));
                   }
               }else {
                   messages.add(new ValidMessage("起息日数据不能为空。"));
               }
               //到期日
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*到期日")))) {
                   if (CommonUtil.isEnDateStr(list.get(headList.indexOf("*到期日")))){
                       entity.setMdate(DateUtil.parse(list.get(headList.indexOf("*到期日"))));
                   }else {
                       entity.setMdate(new Date());
                       messages.add(new ValidMessage("到期日必须为日期格式（yyyy-MM-dd）"));
                   }
               }else {
                   messages.add(new ValidMessage("到期日数据不能为空。"));
               }
                entity.setSvrBussRepQDIIBondInfoRiskDto(getSvrBussRepQDIIBondInfoRiskDto(messages , list,headList));
            }else if (DictCodeListExt2.资产_负债类别_QDII股票_基金类资产.getValue().equals(entity.getAssetType())){
                /**维护信息-QDII股票/基金类资产*/
//                if (dataList.get(1).size() != 51) {
//                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产要素表模板-QDII品格式不正确！"));
//                    return resultList;
//                }
               headList.clear();
               getHeadList_08(headList);
               //资产名称
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*股票/基金名称")))) {
                   entity.setAssetName(list.get(headList.indexOf("*股票/基金名称")));
               }else {
                   messages.add(new ValidMessage("股票/基金名称数据不能为空。"));
               }
               //起息日
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*起息日")))) {
                   if (CommonUtil.isEnDateStr(list.get(headList.indexOf("*起息日")))){
                       entity.setVdate(DateUtil.parse(list.get(headList.indexOf("*起息日"))));
                   }else {
                       entity.setVdate(new Date());
                       messages.add(new ValidMessage("起息日必须为日期格式（yyyy-MM-dd）"));
                   }
               }else {
                   messages.add(new ValidMessage("起息日数据不能为空。"));
               }
               //到期日
               if (StringUtil.isNotEmpty(list.get(headList.indexOf("*到期日")))) {
                   if (CommonUtil.isEnDateStr(list.get(headList.indexOf("*到期日")))){
                       entity.setMdate(DateUtil.parse(list.get(headList.indexOf("*到期日"))));
                   }else {
                       entity.setMdate(new Date());
                       messages.add(new ValidMessage("到期日必须为日期格式（yyyy-MM-dd）"));
                   }
               }else {
                   messages.add(new ValidMessage("到期日数据不能为空。"));
               }
                entity.setSvrBussQDIISharesInfoRiskDto(getSvrBussQDIISharesInfoRiskDto(messages , list,headList));
            }else {
                resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "资产/负债类别不在值域范围内"));
                return resultList;
            }
            List<String> message = new ArrayList<>();
            for (int j = i+1; j < dataList.size(); j++){
                if (list.get(0).equals(dataList.get(j).get(0))){
                    resultList.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i+1), "第"+(i+1)+"行资产代码与第"+(j+1)+"行资产代码重复"));
                }
            }
//            TODO
//            messages.addAll(chbDataCheckCommonUtil.chbCheckCommon(resultList, entity, "AssetInfoRegist002", entity.getAssetType2(), ValidMessage.class));
            if (!StringUtil.isEmptyList(messages)){
                messages.forEach(validMessage -> {
                    if (StringUtil.isNotEmpty(validMessage.getMessage())){
                        message.add(validMessage.getMessage());
                    }
                });
            }
            getDtoByPageMessgae(entity);
           /* if (StringUtil.isNotEmpty(entity.getPbcAssetType1()) && StringUtil.isNotEmpty(entity.getPbcAssetType2())){
                if (!StringUtil.equals(entity.getPbcAssetType1(),dict.get("pbcAssetType1"+entity.getPbcAssetType2()))){
                    message.add("所选新人行二级分类值不在所选新人行一级分类下");
                }
            }
            if (StringUtil.isNotEmpty(entity.getPbcAssetType2()) && StringUtil.isNotEmpty(entity.getPbcAssetType3())){
                if (!StringUtil.equals(entity.getPbcAssetType2(),dict.get("pbcAssetType2"+entity.getPbcAssetType3()))){
                    message.add("所选新人行三级分类值不在所选新人行二级分类下");
                }
            }*/
            entity.setCreateUser(LoginUserUtil.getUserName());
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            entity.setUpdateUser(LoginUserUtil.getUserName());
            entity.setAssetUuid(IDGenerator.generate(36));
            entity.setStatus(DictCodeListExt2.数据状态_有效.getValue());
            if (CollectionUtils.isNotEmpty(message)) {
                resultList.add(new ExcelValidDto(entity.getAssetCode(), String.valueOf(i+1),
                        StringUtil.joinString(message, "<br>")));
            }
            else {
                entities.add(entity);
            }
        }
        return resultList;
    }

    /**
     * 维护信息-QDII股票/基金类资产
     * @param list
     * @return
     */
    private SvrBussQDIISharesInfoRiskDto getSvrBussQDIISharesInfoRiskDto(List<ValidMessage> messages,List<String> list,List<String> headList){
        SvrBussQDIISharesInfoRiskDto dto = new SvrBussQDIISharesInfoRiskDto();
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*所属国家或地区")))) {
            dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(headList.indexOf("*所属国家或地区"))));
        }else {
            messages.add(new ValidMessage("所属国家或地区数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*股票/基金代码")))) {
            dto.setAssetId(list.get(headList.indexOf("*股票/基金代码")));
        }else {
            messages.add(new ValidMessage("股票/基金代码数据不能为空。"));
        }
        dto.setAssetName(list.get(headList.indexOf("*股票/基金名称")));
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*发行机构")))) {
            dto.setIssuePartyName(list.get(headList.indexOf("*发行机构")));
        }else {
            messages.add(new ValidMessage("发行机构数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*行业")))) {
            dto.setIndustryShares2(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业二级分类, list.get(headList.indexOf("*行业"))));
        }else {
            messages.add(new ValidMessage("行业数据不能为空。"));
        }
        dto.setIndustryShares(dict.get("industry"+dto.getIndustryShares2()));
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*资产投资性质")))) {
            dto.setInvestNature(SysDataUtil.getZZDictCodeFromName(DictCode.投资性质, list.get(headList.indexOf("*资产投资性质"))));
        }else {
            messages.add(new ValidMessage("资产投资性质数据不能为空。"));
        }
        dto.setRemark(list.get(headList.indexOf("备注")));
        return dto;
    }

    /**
     * 维护信息-QDII债券资产
     * @param list
     * @return
     */
    private SvrBussRepQDIIBondInfoRiskDto getSvrBussRepQDIIBondInfoRiskDto(List<ValidMessage> messages,List<String> list,List<String> headList){
        SvrBussRepQDIIBondInfoRiskDto dto = new SvrBussRepQDIIBondInfoRiskDto();
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*所属国家或地区")))) {
            dto.setCountryArea(SysDataUtil.getZZDictCodeFromName(DictCode.所属国家或地区, list.get(headList.indexOf("*所属国家或地区"))));
        }else {
            messages.add(new ValidMessage("所属国家或地区数据不能为空。"));
        }
        dto.setAssetName(list.get(headList.indexOf("*债券名称")));
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*债券代码")))) {
            dto.setAssetId(list.get(headList.indexOf("*债券代码")));
        }else {
            messages.add(new ValidMessage("债券代码数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*发行机构")))) {
            dto.setIssuePartyName(list.get(headList.indexOf("*发行机构")));
        }else {
            messages.add(new ValidMessage("发行机构数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*发行机构所属行业")))) {
            dto.setIndustryLevel2(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业二级分类, list.get(headList.indexOf("*发行机构所属行业"))));
        }else {
            messages.add(new ValidMessage("发行机构所属行业数据不能为空。"));
        }
        dto.setIndustry(dict.get("industry"+dto.getIndustryLevel2()));
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*起息日")))) {
            if (CommonUtil.isEnDateStr(list.get(headList.indexOf("*起息日")))){
                dto.setVdate(DateUtil.parse(list.get(headList.indexOf("*起息日"))));
            }else {
                dto.setVdate(new Date());
                messages.add(new ValidMessage(rule.get(SVRBUSSREPQDIIBONDINFODTO + GlobalConstant.POINT + "vdate" + GlobalConstant.UNDER_LINE + ISVALIDDATE )));
            }
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*到期日")))) {
            if (CommonUtil.isEnDateStr(list.get(headList.indexOf("*到期日")))){
                dto.setMdate(DateUtil.parse(list.get(headList.indexOf("*到期日"))));
            }else {
                dto.setMdate(new Date());
                messages.add(new ValidMessage(rule.get(SVRBUSSREPQDIIBONDINFODTO + GlobalConstant.POINT + "mdate" + GlobalConstant.UNDER_LINE + ISVALIDDATE )));
            }
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*期限（月）")))) {
            if (CommonUtil.isFloatNumeric(list.get(headList.indexOf("*期限（月）")))){
                dto.setTermMonth(new BigDecimal(list.get(headList.indexOf("*期限（月）"))));
            }else {
                dto.setTermMonth(new BigDecimal(0));
                messages.add(new ValidMessage(rule.get(SVRBUSSREPQDIIBONDINFODTO + GlobalConstant.POINT + "termMonth" + GlobalConstant.UNDER_LINE + ISNUMBER2 )));
            }
        }else {
            messages.add(new ValidMessage("期限（月）数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*发行机构主体信用评级")))) {
            dto.setPartyGrade(list.get(headList.indexOf("*发行机构主体信用评级")));
        }else {
            messages.add(new ValidMessage("发行机构主体信用评级数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*债券信用评级")))) {
            dto.setSecCreditGradeType(list.get(headList.indexOf("*债券信用评级")));
        }else {
            messages.add(new ValidMessage("债券信用评级数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*票面利率%")))) {
            if (CommonUtil.isFloatNumeric(list.get(headList.indexOf("*票面利率%")))){
                dto.setAssetYield(new BigDecimal(list.get(headList.indexOf("*票面利率%"))));
            }else {
                dto.setAssetYield(new BigDecimal(0));
                messages.add(new ValidMessage(rule.get(SVRBUSSREPQDIIBONDINFODTO + GlobalConstant.POINT + "assetYield" + GlobalConstant.UNDER_LINE + ISNUMBER2 )));
            }
        }else {
            messages.add(new ValidMessage("票面利率%数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*付息频率（个月/次）")))) {
            try {
                dto.setIntpayCycle(Long.valueOf(list.get(headList.indexOf("*付息频率（个月/次）"))));
            }catch (Exception e){
                dto.setIntpayCycle(0L);
                messages.add(new ValidMessage(rule.get(SVRBUSSREPQDIIBONDINFODTO + GlobalConstant.POINT + "intpayCycle" + GlobalConstant.UNDER_LINE + "isNumber" )));
            }
        }else {
            messages.add(new ValidMessage("付息频率（个月/次）数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*担保情况说明")))) {
            dto.setGuaranteeCmt(list.get(headList.indexOf("*担保情况说明")));
            dto.setGuaTypeRemark(list.get(headList.indexOf("*担保情况说明")));
        }else {
            messages.add(new ValidMessage("担保情况说明数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*是否含权等特殊条款情况说明")))) {
            dto.setSpecialCmt(list.get(headList.indexOf("*是否含权等特殊条款情况说明")));
        }else {
            messages.add(new ValidMessage("是否含权等特殊条款情况说明数据不能为空。"));
        }
        dto.setRemark(list.get(headList.indexOf("备注")));
        return dto;
    }

    /**
     * 维护信息-金融衍生品
     * @param list
     * @return
     */
    private SvrBussRepFinanciallInfoRiskDto getSvrBussRepFinanciallInfoRiskDto(List<ValidMessage> messages,List<String> list,List<String> headList){
        SvrBussRepFinanciallInfoRiskDto dto = new SvrBussRepFinanciallInfoRiskDto();
        dto.setAssetName(list.get(headList.indexOf("*名称")));
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*名义本金")))) {
            if (CommonUtil.isFloatNumeric(list.get(headList.indexOf("*名义本金")))){
                dto.setNomCapi(new BigDecimal(list.get(headList.indexOf("*名义本金"))));
            }else {
                dto.setNomCapi(new BigDecimal(0));
                messages.add(new ValidMessage(rule.get(SVRBUSSREPFINANCIALLINFODTO + GlobalConstant.POINT + "nomCapi" + GlobalConstant.UNDER_LINE + ISNUMBER2 )));
            }
        }else {
            messages.add(new ValidMessage("名义本金数据不能为空。"));
        }
        if (StringUtil.isNotEmpty( list.get(headList.indexOf("*标的类别")))){
            String[] targetType3s = list.get(headList.indexOf("*标的类别")).split(GlobalConstant.COMMA);
            String targetType3 = "";
            for (int i=0;i<targetType3s.length;i++){
                targetType3 += SysDataUtil.getZZDictCodeFromName(DictCode.标的类别, targetType3s[i]) + GlobalConstant.COMMA;
            }
            dto.setTargetType3(targetType3.substring(0,targetType3.length()-1));
        }else {
            messages.add(new ValidMessage("标的类别数据不能为空。"));
        }
        if (StringUtil.isNotEmpty( list.get(headList.indexOf("*持有目的")))) {
            dto.setHoldPurpose(SysDataUtil.getZZDictCodeFromName(DictCode.持有目的, list.get(headList.indexOf("*持有目的"))));
        }else {
            messages.add(new ValidMessage("持有目的数据不能为空。"));
        }
        dto.setRemark(list.get(headList.indexOf("备注")));
        if(StringUtil.equals(dto.getTargetType3(),DictCodeListExt2.标的类别_其他.getValue())
                ||StringUtil.equals(dto.getHoldPurpose(),DictCodeListExt1.持有目的_其他.getValue()) ) {
            if (!StringUtil.isNotEmpty( list.get(headList.indexOf("备注")))) {
                messages.add(new ValidMessage("备注数据不能为空。"));
            }
        }
        return dto;
    }

    /**
     * 维护信息-权益类资产
     * @param list
     * @return
     */
    private SvrBussRepEquityInfoRiskDto getSvrBussRepEquityInfoRiskDto(List<ValidMessage> messages,List<String> list,List<String> headList){
        SvrBussRepEquityInfoRiskDto dto = new SvrBussRepEquityInfoRiskDto();
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("股票代码")))) {
            dto.setAssetId(list.get(headList.indexOf("股票代码")));
        }else {
            messages.add(new ValidMessage("股票代码数据不能为空。"));
        }
        dto.setAssetName(list.get(headList.indexOf("*股票/企业名称")));
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("股票类型")))) {
            dto.setSharesType(SysDataUtil.getZZDictCodeFromName(DictCode.股票类型, list.get(headList.indexOf("股票类型"))));
        }else {
            messages.add(new ValidMessage("股票类型数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*行业")))) {
            dto.setIndustryShares2(SysDataUtil.getZZDictCodeFromName(DictCode.机构所属行业二级分类, list.get(headList.indexOf("*行业"))));
        }else {
            messages.add(new ValidMessage("行业数据不能为空。"));
        }
        dto.setIndustryShares(dict.get("industry"+dto.getIndustryShares2()));
        dto.setInvestStage(SysDataUtil.getZZDictCodeFromName(DictCode.投资阶段, list.get(headList.indexOf("投资阶段"))));
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*企业类型（按规模划分）")))) {
            dto.setSharesType1(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按规模划分, list.get(headList.indexOf("*企业类型（按规模划分）"))));
        }else {
            messages.add(new ValidMessage("企业类型（按规模划分）数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*企业类型（按技术领域划分）")))) {
            dto.setSharesType3(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按技术领域划分, list.get(headList.indexOf("*企业类型（按技术领域划分）"))));
        }else {
            messages.add(new ValidMessage("企业类型（按技术领域划分）数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*企业类型（按经济类型划分）")))) {
            dto.setSharesType2(SysDataUtil.getZZDictCodeFromName(DictCode.发行机构类型_按经济类型划分, list.get(headList.indexOf("*企业类型（按经济类型划分）"))));
        }else {
            messages.add(new ValidMessage("企业类型（按经济类型划分）数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*是否为质押融资")))) {
            dto.setIsPledge(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(headList.indexOf("*是否为质押融资"))));
        }else {
            messages.add(new ValidMessage("是否为质押融资数据不能为空。"));
        }
        if (StringUtil.isNotEmpty(list.get(headList.indexOf("*是否为债转股")))) {
            dto.setDeptEquity(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(headList.indexOf("*是否为债转股"))));
        }else {
            messages.add(new ValidMessage("是否为债转股数据不能为空。"));
        }
        dto.setBankSecPrefer(SysDataUtil.getZZDictCodeFromName(DictCode.是否_中债, list.get(headList.indexOf("是否商业银行优先股"))));
        dto.setRemark(list.get(headList.indexOf("备注")));
        if(StringUtil.equals(dto.getSharesType1(),DictCodeListExt1.企业性质_其他企业.getValue())
                || StringUtil.equals(dto.getSharesType2(), DictCodeListExt1.企业性质_其他企业.getValue())
                || StringUtil.equals(dto.getSharesType3(),DictCodeListExt1.企业性质_其他企业.getValue())
                || StringUtil.equals(dto.getSharesType(),DictCodeListExt1.企业性质_其他企业.getValue())) {
            if (!StringUtil.isNotEmpty(list.get(headList.indexOf("备注")))) {
                messages.add(new ValidMessage("备注数据不能为空。"));
            }
        }
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
        if (excelTypeList.contains("权益类资产")){
            getHeadList_06(headList);
        }else if (excelTypeList.contains("金融衍生品")){
            getHeadList_07(headList);
        }else if (!Collections.disjoint(excelTypeList,Arrays.asList("QDII债券资产","QDII股票/基金类资产"))){
            getHeadList_08(headList);
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

    private void getHeadList_gt(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*资产一级分类");
    }

    private void getHeadList_06(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*资产一级分类");
        headList.add("股票代码");
        headList.add("*股票/企业名称");
        headList.add("股票类型");
        headList.add("*行业");
        headList.add("投资阶段");
        headList.add("*企业类型（按规模划分）");
        headList.add("*企业类型（按技术领域划分）");
        headList.add("*企业类型（按经济类型划分）");
        headList.add("*是否为质押融资");
        headList.add("*是否为债转股");
        headList.add("是否商业银行优先股");
        headList.add("备注");
    }

    private void getHeadList_07(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*资产一级分类");
        headList.add("*名称");
        headList.add("*名义本金");
        headList.add("*标的类别");
        headList.add("*持有目的");
        headList.add("备注");
        headList.add("*起息日");
        headList.add("*到期日");
    }

    private void getHeadList_08(List<String> headList){
        headList.add("*行内资产/负债编码");
        headList.add("*发行机构代码");
        headList.add("*资产/负债类别");
        headList.add("*交易流通场所");
        headList.add("*币种");
        headList.add("*资产一级分类");
        headList.add("*所属国家或地区");
        headList.add("*债券名称");
        headList.add("*债券代码");
        headList.add("*发行机构");
        headList.add("*发行机构所属行业");
        headList.add("*期限（月）");
        headList.add("*发行机构主体信用评级");
        headList.add("*债券信用评级");
        headList.add("*票面利率%");
        headList.add("*付息频率（个月/次）");
        headList.add("*担保情况说明");
        headList.add("*是否含权等特殊条款情况说明");
        headList.add("*所属国家或地区");
        headList.add("*股票/基金代码");
        headList.add("*股票/基金名称");
        headList.add("*发行机构");
        headList.add("*行业");
        headList.add("*资产投资性质");
        headList.add("*起息日");
        headList.add("*到期日");
        headList.add("备注");
    }

}
