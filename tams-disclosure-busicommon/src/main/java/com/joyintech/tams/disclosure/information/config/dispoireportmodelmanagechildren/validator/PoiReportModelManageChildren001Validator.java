package com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.validator;


import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dao.PoiReportModelManageChildren001Mapper;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto.PoiReportModelManageChildren001Dto;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.service.PoiReportModelManageChildren001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表子表校验
 *
 * @author kx
 */
@Component
public class PoiReportModelManageChildren001Validator implements BeanValidator {

    @Autowired
    private PoiReportModelManageChildren001Mapper poiReportModelManageChildren001Mapper;

	 @Autowired
	 private PoiReportModelManageChildren001Service poiReportModelManageChildren001Service;
	 
	 Map<String,Object> parameters = new HashMap<String,Object>();
/*	@Override
	public List<ValidMessage> valid(Object value) {
		PoiReportModelManageChildren001Dto dto = (PoiReportModelManageChildren001Dto) value;
       Map<String, Object> param = new HashMap<>();
       param.put("id",  dto.getId());
		// 验证信息
		List<ValidMessage> validList = new ArrayList<ValidMessage>();
		
        
       if (StringUtil.equals(dto.getPageModel() ,"0")) {
       	Page<PoiReportModelManageChildren001Dto> resultList = poiReportModelManageChildren001Service.searchPage(param);
       	if(resultList.getTotal() >=1) {
       		validList.add(new ValidMessage("子表编号不能重复！"));
       		return validList;
       	}
       } 
       return validList;
}*/

    @Override
    public List<ValidMessage> valid(Object value) {
        PoiReportModelManageChildren001Dto dto = (PoiReportModelManageChildren001Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();
        //if (StringUtil.equals(dto.getOperateType(), "submit") || StringUtil.equals(dto.getOperateType(), "edit")) {
        if (StringUtil.equals(dto.getPageModel(), "0")) {
            //必填验证
            dataCheck(dto, errorList);
            lengthCheck(dto, errorList);
            dataOnlyCheck(dto, errorList);
            chartConfCheck(dto,errorList);
            
        }else if(StringUtil.equals(dto.getPageModel(), "1")){
        	//必填验证
            dataCheck(dto, errorList);
            lengthCheck(dto, errorList);
            chartConfCheck(dto,errorList);
        }
        return errorList;
    }


    /**
     * 保存时必填项判断
     *
     * @param dto
     */
    private void dataCheck(PoiReportModelManageChildren001Dto dto, List<ValidMessage> errorList) {
        if (StringUtil.isBlank(dto.getId())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("PoiReportModelManageChildren002.id"))));
        }
        if (StringUtil.isBlank(dto.getSubName())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("PoiReportModelManageChildren002.subName"))));
        }
        if (StringUtil.isBlank(dto.getTabNum())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("PoiReportModelManageChildren002.tabNum"))));
        }
        if (StringUtil.isBlank(dto.getSubType())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("PoiReportModelManageChildren002.subType"))));
        }
        if (StringUtil.isBlank(dto.getSubFormType())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("PoiReportModelManageChildren002.subFormType"))));
        }
        if (StringUtil.isBlank(dto.getSubWidth())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("PoiReportModelManageChildren002.subWidth"))));
        }
        if (StringUtil.isBlank(dto.getSubTmpType())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("PoiReportModelManageChildren002.subTmpType"))));
        }
        if (StringUtil.equals(DictCodeListExt3.poi表格类型_图表.getValue(), dto.getSubFormType()) 
        		&& StringUtil.isBlank(dto.getSubChartType())) {
            errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0001",
                    this.getMessage("PoiReportModelManageChildren002.subChartType"))));
        }
    }

    /**
     * 字段长度判断
     *
     * @param dto
     */
    private void lengthCheck(PoiReportModelManageChildren001Dto dto, List<ValidMessage> errorList) {
        if (StringUtil.isNotBlank(dto.getId())) {
            if (dto.getId().length() > 32) {
                errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0002",
                        this.getMessage("PoiReportModelManageChildren002.id"), String.valueOf(32))));
            }
        }
        if (StringUtil.isNotBlank(dto.getSubName())) {
            if (dto.getSubName().length() > 200) {
                errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0002",
                        this.getMessage("PoiReportModelManageChildren002.subName"), String.valueOf(200))));
            }
        }
        if (StringUtil.isNotBlank(dto.getSubDefualt())) {
            if (dto.getSubDefualt().length() > 1000) {
                errorList.add(new ValidMessage(this.getMessage("REPORT_COMMON_E_0002",
                        this.getMessage("PoiReportModelManageChildren002.subName"), String.valueOf(200))));
            }
        }
    }

    /**
     * 保存时唯一判断
     *
     * @param dto
     */
    private void dataOnlyCheck(PoiReportModelManageChildren001Dto dto, List<ValidMessage> errorList) {
        if (StringUtil.isNotBlank(dto.getId())) {
            int countById = poiReportModelManageChildren001Mapper.countById(dto.getId());
            if (StringUtil.equals(dto.getStatus(), "D")) {
                errorList.add(new ValidMessage("删除状态的不允许修改"));
            }
            PoiReportModelManageChildren001Dto poiReportModelManageChildren001Dto = poiReportModelManageChildren001Mapper.selectById(dto.getId());
            // 因为是主键，查询不到直接视为新建
            /*if (poiReportModelManageChildren001Dto == null) {*/
            if (!StringUtils.isEmpty(poiReportModelManageChildren001Dto)) {
                if (countById != 0) {
                    errorList.add(new ValidMessage("子表编号已在数据库中存在，不能重复"));
                }
            }
        }
    }
    
    private void chartConfCheck(PoiReportModelManageChildren001Dto dto, List<ValidMessage> errorList){
        if(null!=dto.getSubChartConf()&&!"".equals(dto.getSubChartConf())){
            try {
                JsonUtils.json2Object(dto.getSubChartConf(), Map.class);
            }catch (Exception e){
                e.printStackTrace();
                errorList.add(new ValidMessage("图表配置文本格式错误，请检查！"));
            }
        }
    }


}
