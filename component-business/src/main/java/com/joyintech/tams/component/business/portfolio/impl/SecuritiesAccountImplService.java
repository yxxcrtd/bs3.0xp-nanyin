/*
 * 文件名：SecuritiesAccountImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.portfolio.impl;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.service.MapperDelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.dao.PtlSecAcctMapper;
import com.joyintech.tams.common.base.entity.PtlSecAcct;
import com.joyintech.tams.component.business.portfolio.SecuritiesAccountService;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2018年9月20日
 * @see SecuritiesAccountImplService
 * @since 1.0
 */
@Service
public class SecuritiesAccountImplService extends BaseService<SecuritiesAccountInfoDto> implements SecuritiesAccountService{

    @Autowired
    private PtlSecAcctMapper ptlSecAcctMapper;

    @Autowired
    private MapperDelegateService mapperDelegateService;
    /**
     * {@inheritDoc}
     */
    @Override
    public SecuritiesAccountInfoDto getSecAcctInfo(String secAcctNo) {
        SecuritiesAccountInfoDto dto = restTemplate.getForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getSecAcctInfo?secAcctNo="+secAcctNo,
            SecuritiesAccountInfoDto.class);
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SelectOptionDto> getSecAcctId(Map<String, Object> param) {
        List<SelectOptionDto> list =  restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getSecAcctId", param,
                SelectOptionDto.class);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String portfolioId) {
        mapperDelegateService.deleteBySpecificKey("PTL_SEC_ACCT", "PORTFOLIO_ID", portfolioId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(SecuritiesAccountInfoDto securitiesAccountInfoDto) {
        PtlSecAcct ptlSecAcct = new PtlSecAcct();
        BeanUtil.copyPropertiesIgnoreNull(ptlSecAcct, securitiesAccountInfoDto);
        ptlSecAcct.setAcctNo(securitiesAccountInfoDto.getSecAcctId());
        ptlSecAcctMapper.insertSelective(ptlSecAcct);
    }

    @Override
    public void insertMulti(List<SecuritiesAccountInfoDto> securitiesAccountInfoDtos, String portfolioId) {
        delete(portfolioId);
        for(SecuritiesAccountInfoDto securitiesAccountInfoDto : securitiesAccountInfoDtos) {
            PtlSecAcct ptlSecAcct = new PtlSecAcct();
            BeanUtil.copyPropertiesIgnoreNull(ptlSecAcct, securitiesAccountInfoDto);
            ptlSecAcct.setAcctNo(securitiesAccountInfoDto.getSecAcctId());
            ptlSecAcct.setPortfolioId(portfolioId);
            ptlSecAcctMapper.insertSelective(ptlSecAcct);
        }
    }

}
