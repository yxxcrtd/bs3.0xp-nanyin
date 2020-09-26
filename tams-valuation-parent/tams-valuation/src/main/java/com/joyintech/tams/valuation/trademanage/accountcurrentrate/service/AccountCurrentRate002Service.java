/*
 * 文件名：AccountCurrentRate002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.trademanage.accountcurrentrate.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRate002Dto;
import com.joyintech.tams.valuation.trademanage.accountcurrentrate.dto.AccountCurrentRateDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年11月13日
 * @see AccountCurrentRate002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountCurrentRate002Service extends CommonExportService<AccountCurrentRate002Dto> {

    /**
     * 帐号下拉框初始化
     * 
     * @param map Map
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctNo(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/AccountCurrentRate002/getAccNo",
            map, SelectOptionDto.class);
    }

    /**
     * 修改双击查看页面初始化数据
     * 
     * @param map Map<String, Object>
     * @return AccountCurrentRate002Dto
     * @see
     * @since 1.0
     */
    public AccountCurrentRate002Dto getfind002Dto(Map<String, Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/AccountCurrentRate002/getfind002Dto", map,
            AccountCurrentRate002Dto.class);
    }

    /**
     * 联想框初始化
     * 
     * @param map Map<String, Object>
     * @param param String
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAcctNo1(Map<String, Object> map, String param) {
        map.put("param", param);
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME + "/AccountCurrentRate002/getAccNo1",
            map, SuggestDto.class);
    }

    /**
     * 转换公共DTO
     * 
     * @param dto AccountCurrentRate002Dto
     * @return AccountCurrentRateDto
     * @see
     * @since 1.0
     */
    public AccountCurrentRateDto getAccountCurrentRateDto(AccountCurrentRate002Dto dto) {
        AccountCurrentRateDto result = new AccountCurrentRateDto();

        result.setAcctNo(dto.getAccountId());
        if (StringUtil.isNotEmpty(dto.getCashId())) {
            result.setCashId(dto.getCashId());
        }
        result.setEffDate(dto.getEffDate());
        result.setEffDate1(dto.getEffDate1());
        result.setAcctType(dto.getAccountType());
        result.setBasicRate(dto.getBasicRate());
        result.setBasis(dto.getBasis());
        result.setIntType(dto.getIntType());
        result.setRate(dto.getRate());
        result.setIntRateStructure(dto.getIntRateStructure());
        result.setOperateType(dto.getOperateType());
        return result;

    }
}
