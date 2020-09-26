/*
 * 文件名：MstMarketInstitutionsValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.PaRskCustomerRating;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrMstMarketInstitutionsDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrPaRskCustomerRatingDto;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service.SvrMstMarketInstitutionsService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see SvrMstMarketInstitutionsValidator
 * @since 1.0
 */
@Component
public class SvrMstMarketInstitutionsValidator implements BeanValidator {

    /**
     * 市场机构管理service注入
     */
    @Autowired
    private SvrMstMarketInstitutionsService mstMarketInstitutionsService;

    @Override
    public List<ValidMessage> valid(Object value) {
        SvrMstMarketInstitutionsDto dto = (SvrMstMarketInstitutionsDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        if (WorkFlowUtil.isNotFromToDoList()) {
            if (StringUtil.equals(dto.getOperateType(), "delete")) {
                // 行删除按钮与删除按钮校验
                List<SvrMstMarketInstitutionsDto> listDto = dto.getSelectedItem();
                List<SvrMstMarketInstitutionsDto> templistDto = mstMarketInstitutionsService.checkAllCodeIsUse(
                    listDto);
                if (templistDto.size() > 0) {
                    for (SvrMstMarketInstitutionsDto mstMarketInstitutionsDto : templistDto) {
                        errorList.add(new ValidMessage("",
                            getMessage("COMMON_E_0030",
                                getMessage("MarketOrganManageInfo002.marketOrganCode"),
                                mstMarketInstitutionsDto.getCustomerId())));
                    }
                }
            }
            else {
            	//add by wangcheng 20190329 所属行业 必填校验
            	if (!StringUtil.isNotEmpty(dto.getIndustry())) {
            		errorList.add(new ValidMessage("industry", this.getMessage("MST_INFO_E_0004",
                            this.getMessage("MstMarketInstitutions002.industry"))));
            		}
            	//add by wangcheng 2019/03/27  机构所属行业为金融业，核算分类必填
            	if(StringUtil.equals(DictCodeList.机构所属行业一级分类_金融业.getValue(), dto.getIndustry())){
            		if (!StringUtil.isNotEmpty(dto.getHsType())) {
            		errorList.add(new ValidMessage("hsType", this.getMessage("MST_INFO_E_0004",
                            this.getMessage("MstMarketInstitutions002.hstype"))));
            		}
            	}
                // 市场机构名称唯一性check
                if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                    List<SvrMstMarketInstitutionsDto> list = mstMarketInstitutionsService.customerNameCheck();
                    for (SvrMstMarketInstitutionsDto mstMarketInstitutionsDto : list) {
                        if (dto.getCustomerName().equals(
                            mstMarketInstitutionsDto.getCustomerName())) {
                            errorList.add(new ValidMessage("", "市场机构名称已存在！"));
                        }
                    }
                    // 新建校验
                    List<SvrPaRskCustomerRatingDto> paRskCustomerRatingList = dto.getPaRskCustomer();
                    //判断传入的字段是否为空
                    for(PaRskCustomerRating paRskCustomerRating : paRskCustomerRatingList){
                        if(StringUtil.isEmpty(paRskCustomerRating.getRatingtype())){
                            errorList.add(new ValidMessage("", "评级类型不能为空！"));
                        } 
                        if(StringUtil.isEmpty(paRskCustomerRating.getRating())){
                            errorList.add(new ValidMessage("", "评级认定结果不能为空！"));
                        }
                        if(paRskCustomerRating.getRatingYear() == null){
                            errorList.add(new ValidMessage("", "评级报表年度不能为空！"));
                        }
                        if(StringUtil.isEmpty(paRskCustomerRating.getRatingMode())){                            errorList.add(new ValidMessage("", "使用模型类型不能为空！"));
                        }
                        if(paRskCustomerRating.getRatingYear() == null){
                            errorList.add(new ValidMessage("", "评级报表年度不能为空！"));
                        }                        if(paRskCustomerRating.getStartDate() == null){
                            errorList.add(new ValidMessage("", "评级生效日不能为空！"));
                        }
                        if (paRskCustomerRating.getEndDate() == null) {
                            errorList.add(new ValidMessage("", "评级到期日不能为空！"));
                        }
                        // 日期比较大小
                        if (DateUtil.compareTo(paRskCustomerRating.getStartDate(),
                            paRskCustomerRating.getEndDate()) > 0) {
                            errorList.add(new ValidMessage("", "评级生效日不能大于评级到期日！"));
                        }
                    }
                    // 比较客户评级表三个主键是否完全相等
                    for (int i = 0; i < paRskCustomerRatingList.size(); i++ ) {
                        for (int j = i + 1; j < paRskCustomerRatingList.size(); j++ ) {
                            if (paRskCustomerRatingList.get(i).getRatingtype().equals(
                                paRskCustomerRatingList.get(j).getRatingtype())
                                && paRskCustomerRatingList.get(i).getStartDate().equals(
                                    paRskCustomerRatingList.get(j).getStartDate())
                                && paRskCustomerRatingList.get(i).getEndDate().equals(
                                    paRskCustomerRatingList.get(j).getEndDate())) {
                                errorList.add(new ValidMessage("", "评级类型、评级生效日、评级到期日不能完全相同！"));
                            }
                        }
                    }
                }
                else if (StringUtil.equals(dto.getPageModel(),
                    GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                    List<SvrMstMarketInstitutionsDto> list = mstMarketInstitutionsService.customerNameCheck();
                    for (SvrMstMarketInstitutionsDto mstMarketInstitutionsDto : list) {
                        if (dto.getCustomerName().equals(
                            mstMarketInstitutionsDto.getCustomerName())) {
                            if (!StringUtil.equals(dto.getCustomerId(),
                                mstMarketInstitutionsDto.getCustomerId())) {
                                    errorList.add(new ValidMessage("", "市场机构名称已存在！"));
                                }
                            }
                        }
                    List<SvrPaRskCustomerRatingDto> paRskCustomerRatingList = dto.getPaRskCustomer();
                    // 判断传入的字段是否为空
                    for (PaRskCustomerRating paRskCustomerRating : paRskCustomerRatingList) {
                        if (StringUtil.isEmpty(paRskCustomerRating.getRatingtype())) {
                            errorList.add(new ValidMessage("", "评级类型不能为空！"));
                        }
                        if (StringUtil.isEmpty(paRskCustomerRating.getRating())) {
                            errorList.add(new ValidMessage("", "评级认定结果不能为空！"));
                        }
                        if (paRskCustomerRating.getRatingYear() == null) {
                            errorList.add(new ValidMessage("", "评级报表年度不能为空！"));
                        }
                        if(StringUtil.isEmpty(paRskCustomerRating.getRatingMode())){                            errorList.add(new ValidMessage("", "使用模型类型不能为空！"));
                        }
                        if(paRskCustomerRating.getRatingYear() == null){
                            errorList.add(new ValidMessage("", "评级报表年度不能为空！"));
                        }                        if(paRskCustomerRating.getStartDate() == null){
                            errorList.add(new ValidMessage("", "评级生效日不能为空！"));
                        }
                        if (paRskCustomerRating.getEndDate() == null) {
                            errorList.add(new ValidMessage("", "评级到期日不能为空！"));
                        }
                        // 日期比较大小
                        if (DateUtil.compareTo(paRskCustomerRating.getStartDate(),
                            paRskCustomerRating.getEndDate()) > 0) {
                            errorList.add(new ValidMessage("", "评级生效日不能大于评级到期日！"));
                        }
                    }
                    // 比较客户评级表三个主键是否完全相等
                    for (int i = 0; i < paRskCustomerRatingList.size(); i++ ) {
                        for (int j = i + 1; j < paRskCustomerRatingList.size(); j++ ) {
                            if (paRskCustomerRatingList.get(i).getRatingtype().equals(
                                paRskCustomerRatingList.get(j).getRatingtype())
                                && paRskCustomerRatingList.get(i).getStartDate().equals(
                                    paRskCustomerRatingList.get(j).getStartDate())
                                && paRskCustomerRatingList.get(i).getEndDate().equals(
                                    paRskCustomerRatingList.get(j).getEndDate())) {
                                errorList.add(new ValidMessage("", "评级类型、评级生效日、评级到期日不能完全相同！"));
                            }
                        }
                    }
                }
            }
        }
        else {
            // 市场机构名称唯一性check
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {
                List<SvrMstMarketInstitutionsDto> list = mstMarketInstitutionsService.customerNameCheck();
                for (SvrMstMarketInstitutionsDto mstMarketInstitutionsDto : list) {
                    if (dto.getCustomerName().equals(mstMarketInstitutionsDto.getCustomerName())) {
                        errorList.add(new ValidMessage("", "市场机构名称已存在！"));
                    }
                }
                List<SvrPaRskCustomerRatingDto> paRskCustomerRatingList = dto.getPaRskCustomer();
                // 判断传入的字段是否为空
                for (PaRskCustomerRating paRskCustomerRating : paRskCustomerRatingList) {
                    if (StringUtil.isEmpty(paRskCustomerRating.getRatingtype())) {
                        errorList.add(new ValidMessage("", "评级类型不能为空！"));
                    }
                    if (StringUtil.isEmpty(paRskCustomerRating.getRating())) {
                        errorList.add(new ValidMessage("", "评级认定结果不能为空！"));
                    }
                    if (paRskCustomerRating.getRatingYear() == null) {
                        errorList.add(new ValidMessage("", "评级报表年度不能为空！"));
                    }
                    if(StringUtil.isEmpty(paRskCustomerRating.getRatingMode())){                        errorList.add(new ValidMessage("", "使用模型类型不能为空！"));
                    }
                    if(paRskCustomerRating.getRatingYear() == null){
                        errorList.add(new ValidMessage("", "评级报表年度不能为空！"));
                    }                    if(paRskCustomerRating.getStartDate() == null){
                        errorList.add(new ValidMessage("", "评级生效日不能为空！"));
                    }
                    if (paRskCustomerRating.getEndDate() == null) {
                        errorList.add(new ValidMessage("", "评级到期日不能为空！"));
                    }
                    // 日期比较大小
                    if (DateUtil.compareTo(paRskCustomerRating.getStartDate(),
                        paRskCustomerRating.getEndDate()) > 0) {
                        errorList.add(new ValidMessage("", "评级生效日不能大于评级到期日！"));
                    }
                }
                // 比较客户评级表三个主键是否完全相等
                for (int i = 0; i < paRskCustomerRatingList.size(); i++ ) {
                    for (int j = i + 1; j < paRskCustomerRatingList.size(); j++ ) {
                        if (paRskCustomerRatingList.get(i).getRatingtype().equals(
                            paRskCustomerRatingList.get(j).getRatingtype())
                            && paRskCustomerRatingList.get(i).getStartDate().equals(
                                paRskCustomerRatingList.get(j).getStartDate())
                            && paRskCustomerRatingList.get(i).getEndDate().equals(
                                paRskCustomerRatingList.get(j).getEndDate())) {
                            errorList.add(new ValidMessage("", "评级类型、评级生效日、评级到期日不能完全相同！"));
                        }
                    }
                }
            }
            else if (StringUtil.equals(dto.getPageModel(),
                GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                List<SvrMstMarketInstitutionsDto> list = mstMarketInstitutionsService.customerNameCheck();
                int a = 0;
                for (SvrMstMarketInstitutionsDto mstMarketInstitutionsDto : list) {
                    if (dto.getCustomerName().equals(mstMarketInstitutionsDto.getCustomerName())) {
                        if (dto.getCustomerId().equals(mstMarketInstitutionsDto.getCustomerId())) {

                        }
                        else {
                            a++ ;
                            if (a > 0) {
                                errorList.add(new ValidMessage("", "市场机构名称已存在！"));
                            }
                        }
                    }
                }
                List<SvrPaRskCustomerRatingDto> paRskCustomerRatingList = dto.getPaRskCustomer();
                // 判断传入的字段是否为空
                for (PaRskCustomerRating paRskCustomerRating : paRskCustomerRatingList) {
                    if (StringUtil.isEmpty(paRskCustomerRating.getRatingtype())) {
                        errorList.add(new ValidMessage("", "评级类型不能为空！"));
                    }
                    if (StringUtil.isEmpty(paRskCustomerRating.getRating())) {
                        errorList.add(new ValidMessage("", "评级认定结果不能为空！"));
                    }
                    if (paRskCustomerRating.getRatingYear() == null) {
                        errorList.add(new ValidMessage("", "评级报表年度不能为空！"));
                    }
                    if(StringUtil.isEmpty(paRskCustomerRating.getRatingMode())){                        errorList.add(new ValidMessage("", "使用模型类型不能为空！"));
                    }
                    if(paRskCustomerRating.getRatingYear() == null){
                        errorList.add(new ValidMessage("", "评级报表年度不能为空！"));
                    }                    if(paRskCustomerRating.getStartDate() == null){
                        errorList.add(new ValidMessage("", "评级生效日不能为空！"));
                    }
                    if (paRskCustomerRating.getEndDate() == null) {
                        errorList.add(new ValidMessage("", "评级到期日不能为空！"));
                    }
                    // 日期比较大小
                    if (DateUtil.compareTo(paRskCustomerRating.getStartDate(),
                        paRskCustomerRating.getEndDate()) > 0) {
                        errorList.add(new ValidMessage("", "评级生效日不能大于评级到期日！"));
                    }
                }
                // 比较客户评级表三个主键是否完全相等
                for (int i = 0; i < paRskCustomerRatingList.size(); i++ ) {
                    for (int j = i + 1; j < paRskCustomerRatingList.size(); j++ ) {
                        if (paRskCustomerRatingList.get(i).getRatingtype().equals(
                            paRskCustomerRatingList.get(j).getRatingtype())
                            && paRskCustomerRatingList.get(i).getStartDate().equals(
                                paRskCustomerRatingList.get(j).getStartDate())
                            && paRskCustomerRatingList.get(i).getEndDate().equals(
                                paRskCustomerRatingList.get(j).getEndDate())) {
                            errorList.add(new ValidMessage("", "评级类型、评级生效日、评级到期日不能完全相同！"));
                        }
                    }
                }
            }
        }
        return errorList;
    }

}
