package com.joyintech.service.riskrds.limitmanagement.rsksupervisionriskanalyze.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dao.RskSupervisionPortfPoint003Mapper;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPackageDtlImp;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionriskanalyze.dao.RskSupervisionRiskAnalyzeMapper;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionriskanalyze.dto.RskSupervisionRiskAnalyzeDto;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 监控值查询
 * @description: RskSupervisionRiskAnalyzeService
 * @author: 裴家俊
 * @create: 2019-10-01
 **/
@Service("backRskSupervisionRiskAnalyzeService")
public class RskSupervisionRiskAnalyzeService extends BaseService<RskSupervisionRiskAnalyzeDto> {

    /**
     * 指数参数管理mapper
     */
    @Autowired
    private RskSupervisionRiskAnalyzeMapper rskSupervisionRiskAnalyzeMapper;

    @Autowired
    private RskSupervisionPortfPoint003Mapper rskSupervisionPortfPoint003Mapper;
    
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<RskSupervisionRiskAnalyzeDto> selectForPage(int page, int length,
                                                            Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("PORTFOLIO_LEVEL_01", BussinessDictCodeListRds.组合层次_单组合.getValue());
        pageParam.put("PORTFOLIO_LEVEL_02", BussinessDictCodeListRds.组合层次_组合包.getValue());
        return super.queryForPage(page, length,
            () -> rskSupervisionRiskAnalyzeMapper.select(pageParam));
    }
    //进入界面初始化查询
    public Page<RskSupervisionRiskAnalyzeDto> selectForPageInit(int page, int length,
            Map<String, Object> pageParam)
		throws Exception {
		pageParam.put("PORTFOLIO_LEVEL_01", BussinessDictCodeListRds.组合层次_单组合.getValue());
		pageParam.put("PORTFOLIO_LEVEL_02", BussinessDictCodeListRds.组合层次_组合包.getValue());
		return super.queryForPage(page, length,
			() -> rskSupervisionRiskAnalyzeMapper.selectInit(pageParam));
	}
    public Page<RskSupervisionRiskAnalyzeDto> selectForPageMx(int page, int length,
                                                              Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskSupervisionRiskAnalyzeMapper.selectMx(pageParam));
    }

    public Page<RskSupervisionRiskAnalyzeDto> selectForPageNx(int page, int length,
                                                              Map<String, Object> pageParam)
        throws Exception {
        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) { 
            String portfolioId = pageParam.get("portfolioId").toString(); 
            List<RskPortfolioPackageDtlImp> portfDtlList = rskSupervisionPortfPoint003Mapper.selectPortfBagDtlByBagId(portfolioId); 
            List<String> portfolioIdList= new ArrayList<>();
            if (StringUtil.isNotEmpty(portfDtlList)) {
                portfolioIdList= portfDtlList.stream().map(item->item.getPortfolioId()).collect(Collectors.toList());
            }else {
                portfolioIdList.add(portfolioId);
            }
            pageParam.put("portfolioIdList", portfolioIdList);
        } 
        return super.queryForPage(page, length,
            () -> rskSupervisionRiskAnalyzeMapper.selectNx(pageParam));
    }

    public Page<RskSupervisionRiskAnalyzeDto> selectForPageFu(int page, int length,
                                                              Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskSupervisionRiskAnalyzeMapper.selectFu(pageParam));
    }

    public Page<SelectOptionDto> selectUserName(Map<String, Object> pageParam, int page, int length) {
        return this.queryForPageForSelectReturn(page, length, ()-> rskSupervisionRiskAnalyzeMapper.selectByUserName(pageParam));
    }
}
