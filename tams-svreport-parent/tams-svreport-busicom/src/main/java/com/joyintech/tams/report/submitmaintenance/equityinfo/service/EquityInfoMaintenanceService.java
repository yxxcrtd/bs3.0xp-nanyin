/*
 * 文件名：EquityInfoMaintenance.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月13日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.equityinfo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.common.base.dao.ChbTypeMappingExMapper;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * 权益人信息维护解析接口数据
 * 〈功能详细描述〉
 * @author wm
 * @version 1.0 2017年9月13日
 * @see EquityInfoMaintenance
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Component
@Service("equityInfoMaintenanceService")
@Transactional(rollbackFor = Exception.class)
public class EquityInfoMaintenanceService extends BaseService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(EquityInfoMaintenanceService.class);

    /**
     * 读取配置文件，并传入表名获取对应的value值
     * @param tableName
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unused")
	private static String readPropertiesByGetResourceAsStream(String tableName){
        InputStream inStream = EquityInfoMaintenanceService.class.getClassLoader().getResourceAsStream("table_setting.properties");
        Properties properties = new Properties();
        String tabs="";
        try {
            properties.load(inStream);
            tabs = properties.getProperty(tableName);
        }
        catch (IOException e) {
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally{
            if(inStream !=null){
                try {
                    inStream.close();
                }
                catch (IOException e) {
//                    e.printStackTrace();
                    logger.error(e.getMessage(),e);
                }
            }
        }
        return tabs;
    }
    /**
     * 将xml读取的Map<String,String>转化为Dto格式 全量转换
     * @param list Map<String,String>
     * @param EquityInfo001Dto
     * @see
     * @since 1.0
     */
    public static void maptoDto(Map<String,String> map,EquityInfo001Dto equityInfo001Dto){
        equityInfo001Dto.setIndentifyMark(map.get("INDENTIFY_MARK"));
        equityInfo001Dto.setOrgIndentifyMark(map.get("ORG_INDENTIFY_MARK"));
        equityInfo001Dto.setBankCode(map.get("REGIST_BANK_CODE"));
        equityInfo001Dto.setIsBelongBank(map.get("IS_BELONG_BANK"));
        equityInfo001Dto.setTheBankName(map.get("THE_BANK_NAME"));
        equityInfo001Dto.setTheBankCode(map.get("THE_BANK_CODE"));
        equityInfo001Dto.setHolderMark(map.get("HOLDER_MARK"));
        equityInfo001Dto.setHolderRegion(map.get("HOLDER_REGION"));
        equityInfo001Dto.setIdentifyNumber(map.get("IDENTIFY_NUMBER"));    
        equityInfo001Dto.setHolderType(map.get("HOLDER_TYPE"));
        equityInfo001Dto.setPersonalIdentifyType(map.get("PERSONAL_IDENTIFY_TYPE"));
        equityInfo001Dto.setInstitutionIdentifyType(map.get("INSTITUTION_IDENTIFY_TYPE"));
        equityInfo001Dto.setOtherIdentifyName(map.get("OTHER_IDENTIFY_NAME"));
        equityInfo001Dto.setSpvCapAccBank(map.get("SPV_CAP_ACC_BANK"));
        equityInfo001Dto.setOtherCapAccBank(map.get("OTHER_CAP_ACC_BANK"));
        equityInfo001Dto.setSpvRegistCode(map.get("SPV_REGIST_CODE"));
        equityInfo001Dto.setHolderDate(DateUtil.parse(map.get("HOLDER_DATE")));
        equityInfo001Dto.setHolderName(map.get("HOLDER_NAME"));
        equityInfo001Dto.setHolderSex(map.get("HOLDER_SEX"));
        equityInfo001Dto.setHolderRiskPrefer(map.get("HOLDER_RISK_PREFER"));
        if(StringUtil.isEmpty(map.get("HOLDER_CELLPHONE"))||StringUtil.equals(map.get("HOLDER_CELLPHONE"),ReportGlobalConstant.NAUGHT)){
            equityInfo001Dto.setHolderCellphone(ReportGlobalConstant.DEFAULTPHONE);
        }
        else{
            equityInfo001Dto.setHolderCellphone(map.get("HOLDER_CELLPHONE"));
        }
        if(StringUtil.isEmpty(map.get("HOLDER_TELEPHONE"))||StringUtil.equals(map.get("HOLDER_TELEPHONE"),ReportGlobalConstant.NAUGHT)){
            equityInfo001Dto.setHolderTelephone(ReportGlobalConstant.DEFAULTTEL);
        }
        else{
            equityInfo001Dto.setHolderTelephone(map.get("HOLDER_TELEPHONE"));
        }
        if(StringUtil.isEmpty(map.get("HOLDER_EMAIL"))||StringUtil.equals(map.get("HOLDER_EMAIL"),ReportGlobalConstant.NAUGHT)){
            equityInfo001Dto.setHolderEmail(ReportGlobalConstant.DEFAULTEMAIL);
        }
        else{
            equityInfo001Dto.setHolderEmail(map.get("HOLDER_EMAIL"));
        }
        equityInfo001Dto.setRemark(map.get("REMARK"));
        equityInfo001Dto.setOrgCode(map.get("ORG_CODE"));
        equityInfo001Dto.setAgent(map.get("AGENT"));
        equityInfo001Dto.setDeptCode(map.get("DEPT_CODE"));
    }
    /**
     * 通过配置及开关获取最新的dto,空值不覆盖
     * @param map 传入数据
     * @param equityInfo001Dto 目标返回数据
     * @param equityInfo001Map 维护表中数据
     * @param mapPrioty        配置项
     * @param interType        开关
     */
    public static void maptoDtoByPrpperties(Map<String,String> map,EquityInfo001Dto equityInfo001Dto,
    		Map<String, List<EquityInfo001Dto>> equityInfo001Map,
    		String[] result,String interType){
        List<EquityInfo001Dto> equityInfoCheckList = new ArrayList<EquityInfo001Dto>();
        /**
         *从map中获取维护表中的数据
         *1.当原识别标识不为空的时候
         * 1.1 用传入的原识别标识找数据 （即 传入数据 识别标识为B原识别标识A，库中识别标识A）
         * 1.2 若1.1为空，则用识别标识找数据（即 传入数据识别标识为B原识别标识A，库中是识别标识为B原识别标识A）
         *2.原识别标识为空，传入数据的识别标识为主键
         */
        String isisChangeId = DictCodeList.是否_中债_否.getValue();
    	if(StringUtil.isNotEmpty(map.get("ORG_INDENTIFY_MARK"))){
            equityInfoCheckList = equityInfo001Map.get(map.get(
                    "ORG_INDENTIFY_MARK") + GlobalConstant.UNDER_LINE +  map.get(
                            "REGIST_BANK_CODE"));
            if(StringUtil.isEmptyList(equityInfoCheckList)){
                equityInfoCheckList = equityInfo001Map.get(map.get(
                        "INDENTIFY_MARK") + GlobalConstant.UNDER_LINE +  map.get(
                                "REGIST_BANK_CODE"));
            }
            else{
            	isisChangeId = DictCodeList.是否_中债_是.getValue();
            }
    	}
    	else{
            equityInfoCheckList = equityInfo001Map.get(map.get(
                    "INDENTIFY_MARK") + GlobalConstant.UNDER_LINE +  map.get(
                            "REGIST_BANK_CODE"));
    	}
    	if(StringUtils.equals(DictCodeList.境内外_境内.getValue(), map.get("HOLDER_MARK"))
    			&&StringUtil.isNotEmpty(map.get("HOLDER_NAME"))){
    		map.put("HOLDER_NAME", map.get("HOLDER_NAME").replace(StringUtil.SPACE, StringUtil.EMPTY));
    	}
        EquityInfo001Dto equityInfoCheck = null;
        if (null != equityInfoCheckList && !equityInfoCheckList.isEmpty()) {
            equityInfoCheck = equityInfoCheckList.get(0);
        }
        //当维护表中数据为空，按照以前处理，不为空，则通过覆盖原则来赋参数
        if(equityInfoCheck!=null){
        	BeanUtil.copyPropertiesIgnoreNull(equityInfo001Dto, equityInfoCheck);
        	equityInfo001Dto.setIsNull(DictCodeList.是否_中债_否.getValue());
        	mapNotNulltoSetDto(map,equityInfo001Dto,result,isisChangeId);
        }
        else{
            equityInfo001Dto.setIndentifyMark(map.get("INDENTIFY_MARK"));
            equityInfo001Dto.setOrgIndentifyMark(map.get("ORG_INDENTIFY_MARK"));
            equityInfo001Dto.setBankCode(map.get("REGIST_BANK_CODE"));
            equityInfo001Dto.setIsBelongBank(map.get("IS_BELONG_BANK"));
            equityInfo001Dto.setTheBankName(map.get("THE_BANK_NAME"));
            equityInfo001Dto.setTheBankCode(map.get("THE_BANK_CODE"));
            equityInfo001Dto.setHolderMark(map.get("HOLDER_MARK"));
            equityInfo001Dto.setHolderRegion(map.get("HOLDER_REGION"));
            equityInfo001Dto.setIdentifyNumber(map.get("IDENTIFY_NUMBER"));    
            equityInfo001Dto.setHolderType(map.get("HOLDER_TYPE"));
            equityInfo001Dto.setPersonalIdentifyType(map.get("PERSONAL_IDENTIFY_TYPE"));
            equityInfo001Dto.setInstitutionIdentifyType(map.get("INSTITUTION_IDENTIFY_TYPE"));
            equityInfo001Dto.setOtherIdentifyName(map.get("OTHER_IDENTIFY_NAME"));
            equityInfo001Dto.setSpvCapAccBank(map.get("SPV_CAP_ACC_BANK"));
            equityInfo001Dto.setOtherCapAccBank(map.get("OTHER_CAP_ACC_BANK"));
            equityInfo001Dto.setSpvRegistCode(map.get("SPV_REGIST_CODE"));
            equityInfo001Dto.setHolderDate(DateUtil.parse(map.get("HOLDER_DATE")));
            equityInfo001Dto.setHolderName(map.get("HOLDER_NAME"));
            equityInfo001Dto.setHolderSex(map.get("HOLDER_SEX"));
            equityInfo001Dto.setHolderRiskPrefer(map.get("HOLDER_RISK_PREFER"));
        	equityInfo001Dto.setIsNull(DictCodeList.是否_中债_是.getValue());
            if(StringUtil.isEmpty(map.get("HOLDER_CELLPHONE"))||StringUtil.equals(map.get("HOLDER_CELLPHONE"),ReportGlobalConstant.NAUGHT)){
                equityInfo001Dto.setHolderCellphone(ReportGlobalConstant.DEFAULTPHONE);
            }
            else{
                equityInfo001Dto.setHolderCellphone(map.get("HOLDER_CELLPHONE"));
            }
            if(StringUtil.isEmpty(map.get("HOLDER_TELEPHONE"))||StringUtil.equals(map.get("HOLDER_TELEPHONE"),ReportGlobalConstant.NAUGHT)){
                equityInfo001Dto.setHolderTelephone(ReportGlobalConstant.DEFAULTTEL);
            }
            else{
                equityInfo001Dto.setHolderTelephone(map.get("HOLDER_TELEPHONE"));
            }
            if(StringUtil.isEmpty(map.get("HOLDER_EMAIL"))||StringUtil.equals(map.get("HOLDER_EMAIL"),ReportGlobalConstant.NAUGHT)){
                equityInfo001Dto.setHolderEmail(ReportGlobalConstant.DEFAULTEMAIL);
            }
            else{
                equityInfo001Dto.setHolderEmail(map.get("HOLDER_EMAIL"));
            }
            equityInfo001Dto.setRemark(map.get("REMARK"));
            equityInfo001Dto.setOrgCode(map.get("ORG_CODE"));
            equityInfo001Dto.setAgent(map.get("AGENT"));
            equityInfo001Dto.setDeptCode(map.get("DEPT_CODE"));
        }
        //add by lingxq on 2021-05-06 for 需求升级 ------->>>>>
        updateHoldType(equityInfo001Dto);
        //add by lingxq on 2021-05-06 for 需求升级 -------<<<<<
    }
    /**
     * 根据配置转换dto
     * @param map 接口中数据
     * @param equityInfo001Dto 转换后的
     * @param result 配置文件中的lie
     * @see
     * @since 1.0
     */
    public static void maptoSetDto(Map<String,String> map,EquityInfo001Dto equityInfo001Dto,String[] result){
        for(int i=1;i<result.length;i++){
            if("INDENTIFY_MARK".equals(result[i])){
                equityInfo001Dto.setIndentifyMark(map.get("INDENTIFY_MARK"));
            }
            else if("ORG_INDENTIFY_MARK".equals(result[i])){
                equityInfo001Dto.setOrgIndentifyMark(map.get("ORG_INDENTIFY_MARK"));
            }
            else if("REGIST_BANK_CODE".equals(result[i])){
                equityInfo001Dto.setBankCode(map.get("REGIST_BANK_CODE"));
            }
            else if("IS_BELONG_BANK".equals(result[i])){
                equityInfo001Dto.setIsBelongBank(map.get("IS_BELONG_BANK"));
            }
            else if("THE_BANK_NAME".equals(result[i])){
                equityInfo001Dto.setTheBankName(map.get("THE_BANK_NAME"));
            }
            else if("THE_BANK_CODE".equals(result[i])){
                equityInfo001Dto.setTheBankCode(map.get("THE_BANK_CODE"));
            }
            else if("HOLDER_MARK".equals(result[i])){
                equityInfo001Dto.setHolderMark(map.get("HOLDER_MARK"));
            }
            else if("HOLDER_REGION".equals(result[i])){
                equityInfo001Dto.setHolderRegion(map.get("HOLDER_REGION"));
            }
            else if("HOLDER_TYPE".equals(result[i])){
                equityInfo001Dto.setHolderType(map.get("HOLDER_TYPE"));
            }
            else if("PERSONAL_IDENTIFY_TYPE".equals(result[i])){
                equityInfo001Dto.setPersonalIdentifyType(map.get("PERSONAL_IDENTIFY_TYPE"));
            }
            else if("INSTITUTION_IDENTIFY_TYPE".equals(result[i])){
                equityInfo001Dto.setInstitutionIdentifyType(map.get("INSTITUTION_IDENTIFY_TYPE"));
            }
            else if("OTHER_IDENTIFY_NAME".equals(result[i])){
                equityInfo001Dto.setOtherIdentifyName(map.get("OTHER_IDENTIFY_NAME"));
            }
            else if("SPV_CAP_ACC_BANK".equals(result[i])){
                equityInfo001Dto.setSpvCapAccBank(map.get("SPV_CAP_ACC_BANK"));
            }
            else if("OTHER_CAP_ACC_BANK".equals(result[i])){
                equityInfo001Dto.setOtherCapAccBank(map.get("OTHER_CAP_ACC_BANK"));
            }
            else if("SPV_REGIST_CODE".equals(result[i])){
                equityInfo001Dto.setSpvRegistCode(map.get("SPV_REGIST_CODE"));
            }
            else if("HOLDER_DATE".equals(result[i])){
                equityInfo001Dto.setHolderDate(DateUtil.parse(map.get("HOLDER_DATE")));
            }
            else if("HOLDER_NAME".equals(result[i])){
                equityInfo001Dto.setHolderName(map.get("HOLDER_NAME"));
            }
            else if("HOLDER_SEX".equals(result[i])){
                equityInfo001Dto.setHolderSex(map.get("HOLDER_SEX"));
            }
            else if("HOLDER_RISK_PREFER".equals(result[i])){
                equityInfo001Dto.setHolderRiskPrefer(map.get("HOLDER_RISK_PREFER"));
            }
            else if("HOLDER_CELLPHONE".equals(result[i])){
                equityInfo001Dto.setHolderCellphone(map.get("HOLDER_CELLPHONE"));
            }
            else if("HOLDER_TELEPHONE".equals(result[i])){
                equityInfo001Dto.setHolderTelephone(map.get("HOLDER_TELEPHONE"));
            }
            else if("HOLDER_EMAIL".equals(result[i])){
                equityInfo001Dto.setHolderEmail(map.get("HOLDER_EMAIL"));
            }
            else if("REMARK".equals(result[i])){
                equityInfo001Dto.setRemark(map.get("REMARK"));
            }
            else if("IDENTIFY_NUMBER".equals(result[i])){
                equityInfo001Dto.setIdentifyNumber(map.get("IDENTIFY_NUMBER"));    
            }
            if(StringUtil.isNotEmpty(map.get("DEPT_CODE"))){
                equityInfo001Dto.setDeptCode(map.get("DEPT_CODE"));
            }
        }
    }
    /**
     * 
     * 根据配置转换dto
     * @param map 接口中数据
     * @param result  配置文件中的lie
     * @param interfType  数据来源
     * @param param       数据优先级
     * @param equityInfoCheck  源
     * @param type  数据类型
     * @return EquityInfo001Dto 
     * @see
     * @since 1.0
     */
    public static EquityInfo001Dto mapToSetProDto(Map<String,String> map,String interfType,
                                                  Map<String,Integer> param, String[] result,
                                                  EquityInfo001Dto equityInfoCheck,String type){
        EquityInfo001Dto equityInfo001Dto = new EquityInfo001Dto();
        //维护表中数据不为空
        boolean flag1 = equityInfoCheck!=null;
        //维护表中数据来源为中债子系统
        boolean flag2 = equityInfoCheck == null ? false : "01".equals(equityInfoCheck.getDataSource())||"02".equals(equityInfoCheck.getDataSource());
        //数据来源相同 
        boolean flag3 = equityInfoCheck == null ? false : interfType.equals(equityInfoCheck.getDataSource());
        //数据源不同，优先级大于维护表中
//        boolean flag4 = !interfType.equals(equityInfoCheck.getDataSource())
//            &&param.get(interfType).compareTo(param.get(equityInfoCheck.getDataSource()))>0;
        if((flag1&&(flag2||flag3))||equityInfoCheck==null){
            for(int i=0;i<result.length;i++){
                if("04".equals(type)){
                    if("HOLDER_RISK_PREFER".equals(result[i])){
                        equityInfo001Dto.setHolderRiskPrefer(map.get("HOLDER_RISK_PREFER"));
                    }
                    else if("HOLDER_CELLPHONE".equals(result[i])){
                        equityInfo001Dto.setHolderCellphone(map.get("HOLDER_CELLPHONE"));
                    }
                    else if("HOLDER_TELEPHONE".equals(result[i])){
                        equityInfo001Dto.setHolderTelephone(map.get("HOLDER_TELEPHONE"));
                    }
                    else if("HOLDER_EMAIL".equals(result[i])){
                        equityInfo001Dto.setHolderEmail(map.get("HOLDER_EMAIL"));
                    } 
                }
                if("REMARK".equals(result[i])){
                    equityInfo001Dto.setRemark(map.get("REMARK"));
                }
            }
        }
        return equityInfo001Dto;
    } 
    /**
     * 
     * 根据配置转换dto
     * @param map 接口中数据
     * @param isOpen 开关关闭开启
     * @param result  配置文件中的lie
     * @param equityInfoCheck  源
     * @param type  数据类型
     * @return EquityInfo001Dto 
     * @see
     * @since 1.0
     */
    public static EquityInfo001Dto maptoSetProDto(Map<String,String> map,String isOpen,
                                      String[] result,EquityInfo001Dto equityInfoCheck,String type){
        EquityInfo001Dto equityInfo001Dto = new EquityInfo001Dto();
        if("01".equals(isOpen)&&(equityInfoCheck!=null&&(
            "01".equals(equityInfoCheck.getDataSource())||"02".equals(equityInfoCheck.getDataSource())))){
            for(int i=0;i<result.length;i++){
                if("04".equals(type)){
                    if("HOLDER_RISK_PREFER".equals(result[i])){
                        equityInfo001Dto.setHolderRiskPrefer(map.get("HOLDER_RISK_PREFER"));
                    }
                    else if("HOLDER_CELLPHONE".equals(result[i])){
                        equityInfo001Dto.setHolderCellphone(map.get("HOLDER_CELLPHONE"));
                    }
                    else if("HOLDER_TELEPHONE".equals(result[i])){
                        equityInfo001Dto.setHolderTelephone(map.get("HOLDER_TELEPHONE"));
                    }
                    else if("HOLDER_EMAIL".equals(result[i])){
                        equityInfo001Dto.setHolderEmail(map.get("HOLDER_EMAIL"));
                    } 
                }
                if("REMARK".equals(result[i])){
                    equityInfo001Dto.setRemark(map.get("REMARK"));
                }
            }
        }
        else if(!("02".equals(isOpen)&&(equityInfoCheck!=null&&
            ("01".equals(equityInfoCheck.getDataSource())||"02".equals(equityInfoCheck.getDataSource()))))){
            if("04".equals(type)){
                equityInfo001Dto.setHolderRiskPrefer(map.get("HOLDER_RISK_PREFER"));
                equityInfo001Dto.setHolderCellphone(map.get("HOLDER_CELLPHONE"));
                equityInfo001Dto.setHolderTelephone(map.get("HOLDER_TELEPHONE"));
                equityInfo001Dto.setHolderEmail(map.get("HOLDER_EMAIL"));
            }
            equityInfo001Dto.setRemark(map.get("REMARK"));
        }
        return equityInfo001Dto;
    }
    /**
     * 根据配置转换dto
     * @param map 接口中数据
     * @param equityInfo001Dto 转换后的
     * @param result 配置文件中的列
     * @param isChangeId 是否识别标识变更
     * @see
     * @since 1.0
     */
    public static void mapNotNulltoSetDto(Map<String,String> map,EquityInfo001Dto equityInfo001Dto,
    		String[] result,String isChangeId){
    	if(DictCodeList.是否_中债_否.getValue().equals(isChangeId)){
    		if(DictCodeListExt2.数据来源_EXCEL导入.getValue().equals(equityInfo001Dto.getDataSource())||
    				DictCodeListExt2.数据来源_系统新建.getValue().equals(equityInfo001Dto.getDataSource())){
        		for(int i=1;i<result.length;i++){
                    if("INDENTIFY_MARK".equals(result[i])&&StringUtil.isNotEmpty(map.get("INDENTIFY_MARK"))){
                        equityInfo001Dto.setIndentifyMark(map.get("INDENTIFY_MARK"));
                    }
                    else if("ORG_INDENTIFY_MARK".equals(result[i])&&StringUtil.isNotEmpty(map.get("ORG_INDENTIFY_MARK"))){
                        equityInfo001Dto.setOrgIndentifyMark(map.get("ORG_INDENTIFY_MARK"));
                    }
                    else if("REGIST_BANK_CODE".equals(result[i])&&StringUtil.isNotEmpty(map.get("REGIST_BANK_CODE"))){
                        equityInfo001Dto.setBankCode(map.get("REGIST_BANK_CODE"));
                    }
                    else if("IS_BELONG_BANK".equals(result[i])&&StringUtil.isNotEmpty(map.get("IS_BELONG_BANK"))){
                        equityInfo001Dto.setIsBelongBank(map.get("IS_BELONG_BANK"));
                    }
                    else if("THE_BANK_NAME".equals(result[i])&&StringUtil.isNotEmpty(map.get("THE_BANK_NAME"))){
                        equityInfo001Dto.setTheBankName(map.get("THE_BANK_NAME"));
                    }
                    else if("THE_BANK_CODE".equals(result[i])&&StringUtil.isNotEmpty(map.get("THE_BANK_CODE"))){
                        equityInfo001Dto.setTheBankCode(map.get("THE_BANK_CODE"));
                    }
                    else if("HOLDER_MARK".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_MARK"))){
                        equityInfo001Dto.setHolderMark(map.get("HOLDER_MARK"));
                    }
                    else if("HOLDER_REGION".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_REGION"))){
                        equityInfo001Dto.setHolderRegion(map.get("HOLDER_REGION"));
                    }
                    else if("HOLDER_TYPE".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_TYPE"))){
                        equityInfo001Dto.setHolderType(map.get("HOLDER_TYPE"));
                    }
                    else if("PERSONAL_IDENTIFY_TYPE".equals(result[i])&&StringUtil.isNotEmpty(map.get("PERSONAL_IDENTIFY_TYPE"))){
                        equityInfo001Dto.setPersonalIdentifyType(map.get("PERSONAL_IDENTIFY_TYPE"));
                    }
                    else if("INSTITUTION_IDENTIFY_TYPE".equals(result[i])&&StringUtil.isNotEmpty(map.get("INSTITUTION_IDENTIFY_TYPE"))){
                        equityInfo001Dto.setInstitutionIdentifyType(map.get("INSTITUTION_IDENTIFY_TYPE"));
                    }
                    else if("OTHER_IDENTIFY_NAME".equals(result[i])&&StringUtil.isNotEmpty(map.get("OTHER_IDENTIFY_NAME"))){
                        equityInfo001Dto.setOtherIdentifyName(map.get("OTHER_IDENTIFY_NAME"));
                    }
                    else if("SPV_CAP_ACC_BANK".equals(result[i])&&StringUtil.isNotEmpty(map.get("SPV_CAP_ACC_BANK"))){
                        equityInfo001Dto.setSpvCapAccBank(map.get("SPV_CAP_ACC_BANK"));
                    }
                    else if("OTHER_CAP_ACC_BANK".equals(result[i])&&StringUtil.isNotEmpty(map.get("OTHER_CAP_ACC_BANK"))){
                        equityInfo001Dto.setOtherCapAccBank(map.get("OTHER_CAP_ACC_BANK"));
                    }
                    else if("SPV_REGIST_CODE".equals(result[i])&&StringUtil.isNotEmpty(map.get("SPV_REGIST_CODE"))){
                        equityInfo001Dto.setSpvRegistCode(map.get("SPV_REGIST_CODE"));
                    }
                    else if("HOLDER_DATE".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_DATE"))){
                        equityInfo001Dto.setHolderDate(DateUtil.parse(map.get("HOLDER_DATE")));
                    }
                    else if("HOLDER_NAME".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_NAME"))){
                        equityInfo001Dto.setHolderName(map.get("HOLDER_NAME"));
                    }
                    else if("HOLDER_SEX".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_SEX"))){
                        equityInfo001Dto.setHolderSex(map.get("HOLDER_SEX"));
                    }
                    else if("HOLDER_RISK_PREFER".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_RISK_PREFER"))){
                        equityInfo001Dto.setHolderRiskPrefer(map.get("HOLDER_RISK_PREFER"));
                    }
                    else if("HOLDER_CELLPHONE".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_CELLPHONE"))){
                        equityInfo001Dto.setHolderCellphone(map.get("HOLDER_CELLPHONE"));
                    }
                    else if("HOLDER_TELEPHONE".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_TELEPHONE"))){
                        equityInfo001Dto.setHolderTelephone(map.get("HOLDER_TELEPHONE"));
                    }
                    else if("HOLDER_EMAIL".equals(result[i])&&StringUtil.isNotEmpty(map.get("HOLDER_EMAIL"))){
                        equityInfo001Dto.setHolderEmail(map.get("HOLDER_EMAIL"));
                    }
                    else if("IDENTIFY_NUMBER".equals(result[i])&&StringUtil.isNotEmpty(map.get("IDENTIFY_NUMBER"))){
                        equityInfo001Dto.setIdentifyNumber(map.get("IDENTIFY_NUMBER"));    
                    }
                }
    		}
    		else{
    			if(StringUtil.isNotEmpty(map.get("INDENTIFY_MARK"))){
                    equityInfo001Dto.setIndentifyMark(map.get("INDENTIFY_MARK"));
                }
                if(StringUtil.isNotEmpty(map.get("ORG_INDENTIFY_MARK"))){
                    equityInfo001Dto.setOrgIndentifyMark(map.get("ORG_INDENTIFY_MARK"));
                }
                if(StringUtil.isNotEmpty(map.get("REGIST_BANK_CODE"))){
                    equityInfo001Dto.setBankCode(map.get("REGIST_BANK_CODE"));
                }
                if(StringUtil.isNotEmpty(map.get("IS_BELONG_BANK"))){
                    equityInfo001Dto.setIsBelongBank(map.get("IS_BELONG_BANK"));
                }
                if(StringUtil.isNotEmpty(map.get("THE_BANK_NAME"))){
                    equityInfo001Dto.setTheBankName(map.get("THE_BANK_NAME"));
                }
                if(StringUtil.isNotEmpty(map.get("THE_BANK_CODE"))){
                    equityInfo001Dto.setTheBankCode(map.get("THE_BANK_CODE"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_MARK"))){
                    equityInfo001Dto.setHolderMark(map.get("HOLDER_MARK"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_REGION"))){
                    equityInfo001Dto.setHolderRegion(map.get("HOLDER_REGION"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_TYPE"))){
                    equityInfo001Dto.setHolderType(map.get("HOLDER_TYPE"));
                }
                if(StringUtil.isNotEmpty(map.get("PERSONAL_IDENTIFY_TYPE"))){
                    equityInfo001Dto.setPersonalIdentifyType(map.get("PERSONAL_IDENTIFY_TYPE"));
                }
                if(StringUtil.isNotEmpty(map.get("INSTITUTION_IDENTIFY_TYPE"))){
                    equityInfo001Dto.setInstitutionIdentifyType(map.get("INSTITUTION_IDENTIFY_TYPE"));
                }
                if(StringUtil.isNotEmpty(map.get("OTHER_IDENTIFY_NAME"))){
                    equityInfo001Dto.setOtherIdentifyName(map.get("OTHER_IDENTIFY_NAME"));
                }
                if(StringUtil.isNotEmpty(map.get("SPV_CAP_ACC_BANK"))){
                    equityInfo001Dto.setSpvCapAccBank(map.get("SPV_CAP_ACC_BANK"));
                }
                if(StringUtil.isNotEmpty(map.get("OTHER_CAP_ACC_BANK"))){
                    equityInfo001Dto.setOtherCapAccBank(map.get("OTHER_CAP_ACC_BANK"));
                }
                if(StringUtil.isNotEmpty(map.get("SPV_REGIST_CODE"))){
                    equityInfo001Dto.setSpvRegistCode(map.get("SPV_REGIST_CODE"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_DATE"))){
                    equityInfo001Dto.setHolderDate(DateUtil.parse(map.get("HOLDER_DATE")));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_NAME"))){
                    equityInfo001Dto.setHolderName(map.get("HOLDER_NAME"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_SEX"))){
                    equityInfo001Dto.setHolderSex(map.get("HOLDER_SEX"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_RISK_PREFER"))){
                    equityInfo001Dto.setHolderRiskPrefer(map.get("HOLDER_RISK_PREFER"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_CELLPHONE"))){
                    equityInfo001Dto.setHolderCellphone(map.get("HOLDER_CELLPHONE"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_TELEPHONE"))){
                    equityInfo001Dto.setHolderTelephone(map.get("HOLDER_TELEPHONE"));
                }
                if(StringUtil.isNotEmpty(map.get("HOLDER_EMAIL"))){
                    equityInfo001Dto.setHolderEmail(map.get("HOLDER_EMAIL"));
                }
                if(StringUtil.isNotEmpty(map.get("IDENTIFY_NUMBER"))){
                    equityInfo001Dto.setIdentifyNumber(map.get("IDENTIFY_NUMBER"));    
                }
    		}
    	}
    	else {
    		 if(StringUtil.isNotEmpty(map.get("INDENTIFY_MARK"))){
                 equityInfo001Dto.setIndentifyMark(map.get("INDENTIFY_MARK"));
             }
             if(StringUtil.isNotEmpty(map.get("ORG_INDENTIFY_MARK"))){
                 equityInfo001Dto.setOrgIndentifyMark(map.get("ORG_INDENTIFY_MARK"));
             }
    	}
        if(StringUtil.isNotEmpty(map.get("REMARK"))){
            equityInfo001Dto.setRemark(map.get("REMARK"));
        }
		if(StringUtil.isNotEmpty(map.get("DEPT_CODE"))){
			equityInfo001Dto.setDeptCode(map.get("DEPT_CODE"));
		}
		equityInfo001Dto.setIsChangeId(isChangeId);
    }


    /**
     *
     * 投资者名称中带银行/信用社/信用合作社/资金互助社/信用合作联社字样的，投资者类别应该为“04 银行”
     * 投资者名称中带信托且有公司，投资者类别为“05 信托公司”
     * 投资者名称中带证券且有公司且无咨询/投资，投资者类别为“06 证券公司” ；
     * 投资者名称中带保险且有公司且无经纪/代理/咨询/销售/公估，投资者类别为“投资者类别为“08 保险公司” ；
     * 投资者名称中带期货且有公司，投资者类别为“投资者类别为“09 期货公司”；
     * 投资者名称中带财务有限公司，投资者类别为“10 其他金融机构”；
     * 投资者名称中带分公司、办事处、代表处，个体工商户、个体户、居民委员会、社区委员会、居委会，投资者类别为“26 其他非法人组织”
     *
     * @param equityInfo001Dto
     */
    public static void updateHoldType(EquityInfo001Dto equityInfo001Dto) {
        ChbTypeMappingExMapper chbTypeMappingExMapper = SpringContextUtil.getBean(ChbTypeMappingExMapper.class);
        List<ChbTypeMappingEx>  HoldTypeMappingList = chbTypeMappingExMapper.selectByMappingcode("HOLDTYPE");
        if (!CollectionUtils.isEmpty(HoldTypeMappingList)){
            boolean flag = false;
            for(ChbTypeMappingEx mapping: HoldTypeMappingList){
                String names[] = mapping.getSrcfield().split("\\|");
                for(int j=0;j<names.length;j++ ){
                    if(names[j].contains("&")){
                        String mutiNm[] = names[j].split("&");
                        //多条件同时满足
                        int index = 0;
                        for(int jj=0;jj < mutiNm.length;jj++ ){
                            if(mutiNm[jj].startsWith("!")){
                                if(!equityInfo001Dto.getHolderName().contains(mutiNm[jj].substring(1))){
                                    index ++;
                                }
                            }else if(equityInfo001Dto.getHolderName().contains(mutiNm[jj])){
                                index ++;
                            }else{
                                //nothing to do
                            }
                        }
                        if(index == mutiNm.length){
                            flag = true;
                        }
                    }else{
                        if(equityInfo001Dto.getHolderName().contains(names[j])){
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag){
                    equityInfo001Dto.setHolderType(mapping.getSrcfield1());
                    break;
                }
            }
        }
    }
}
