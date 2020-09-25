/*
 * 文件名：TradeFundAccountImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.portfolio.impl;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.service.MapperDelegateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.dao.PtlCapacctInfoMapper;
import com.joyintech.tams.common.base.entity.PtlCapacctInfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.component.business.portfolio.FundAccountService;
import com.joyintech.tams.component.business.portfolio.dto.FundAccountInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;


/**
 * 资金账号信息实现类
 * @author fanshuanghu
 * @version 1.0 2018年9月13日
 * @see FundAccountImplService
 * @since 1.0
 */
@Service
public class FundAccountImplService extends BaseService<FundAccountInfoDto> implements FundAccountService {

    private static Logger logger = LoggerFactory.getLogger("FundAccountImplService");

    @Autowired
    private PtlCapacctInfoMapper ptlCapacctInfoMapper;

    @Autowired
    private MapperDelegateService mapperDelegateService;
    /**
     * 获取账号信息
     * @return 清算账户表信息
     * @see
     * @since 1.0
     */
    @Override
    public StlCapacctInfo getCctInfo(String acctNo){
        return restTemplate.getForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getCctInfo?acctNo="+acctNo,
            StlCapacctInfo.class);
    }
    /**
     * 获取资金账户信息
     * @return 
     * @see
     * @since 1.0
     */
    @Override
    public Page<SelectOptionDto> getAcctNo(Map<String, Object> param){
    	Page<SelectOptionDto> list =  restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getAcctNo",param,
                SelectOptionDto.class);
//        for(SelectOptionDto dto : list ){
//            dto.setId(dto.getId() + "_" + dto.getObject());
//        }
        return list;
    }
    
    @Override
    public void delete(String portfolioId) {
        mapperDelegateService.deleteBySpecificKey("PTL_SEC_ACCT", "PORTFOLIO_ID", portfolioId);
    }

    @Override
    public void insert(FundAccountInfoDto fundAccountInfoDto) {
        String[] temp = fundAccountInfoDto.getAcctNo().split("_");
        fundAccountInfoDto.setAcctNo(temp[0]);
        //增加判断 by gejiaao 2019/8/15
        if (temp.length > 1){
            fundAccountInfoDto.setCcy(temp[1]);
        }
        PtlCapacctInfo ptlCapacctInfo = new PtlCapacctInfo();
        BeanUtil.copyPropertiesIgnoreNull(ptlCapacctInfo, fundAccountInfoDto);
        ptlCapacctInfoMapper.insertSelective(ptlCapacctInfo);
    }

    @Override
    public void update(FundAccountInfoDto fundAccountInfoDto) {
        delete(fundAccountInfoDto.getPortfolioId());
        insert(fundAccountInfoDto);
    }

    @Override
    public void insertMulti(List<FundAccountInfoDto> fundAccountInfoDto, String portfolioId) {
        mapperDelegateService.deleteBySpecificKey("PTL_CAPACCT_INFO", "PORTFOLIO_ID", portfolioId);
        for (FundAccountInfoDto dto : fundAccountInfoDto) {
            dto.setPortfolioId(portfolioId);
            String[] temp = dto.getAcctNo().split("_");
            dto.setAcctNo(temp[0]);
            //增加判断 by gejiaao 2019/8/15
            if (temp.length > 1){
                dto.setCcy(temp[1]);
            }
            PtlCapacctInfo ptlCapacctInfo = new PtlCapacctInfo();
            BeanUtil.copyPropertiesIgnoreNull(ptlCapacctInfo, dto);
            logger.debug(dto.getPortfolioId() + "---" + dto.getAcctNo());
            ptlCapacctInfoMapper.insertSelective(ptlCapacctInfo);
        }
    }


    /**
     * 初始化下拉框
     * {@inheritDoc}
     */
    /*@Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.资金账户类型};
    }*/
}
