/*
 * 文件名：PtlManagementInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.dao.PtlPortfolioAuthorityMapper;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.dao.PtlValParamMapper;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.portfolio.FundAccountService;
import com.joyintech.tams.component.business.portfolio.SecuritiesAccountService;
import com.joyintech.tams.component.business.prod.BussFinProductValuationService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfo004Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子组合信息Service
 *
 * @author gejiaao
 * @version 1.0 2019年8月14日
 * @see PtlManagementInfo004Service
 * @since 1.0
 */
@Service("PtlManagementInfo004Service")
@Transactional(rollbackFor = Exception.class)
public class PtlManagementInfo004Service extends CommonExportService<PtlManagementInfo004Dto>
        implements BaseProcessService<PtlManagementInfo004Dto> {
    /**
     * 注入资金账户业务组件Service
     */
    @Autowired(required = false)
    public FundAccountService fundAccountService;
    /**
     * 注入微服务代理类
     */
    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;
    /**
     * 注入组合权限表Mapper
     */
    @Autowired
    public PtlPortfolioAuthorityMapper ptlPortfolioAuthorityMapper;
    /**
     * 注入估值参数表Mapper
     */
    @Autowired
    public PtlValParamMapper ptlValParamMapper;
    /**
     * 注入估值信息的Service
     */
    @Autowired(required = false)
    public BussFinProductValuationService bussFinProductValuationService;
    /**
     * 注入证券账户业务组件Service
     */
    @Autowired(required = false)
    public SecuritiesAccountService securitiesAccountService;

    @Autowired
    private PtlManagementInfo001Service ptlManagementInfo001Service;

    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doComplete(PtlManagementInfo004Dto entity, OperationType type) {

        if (OperationType.NEW.equals(type)) {
            restTemplate.postForObject(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/doInsertAction", entity,
                    String.class);
        } else if (OperationType.DELETE.equals(type)) {
            PtlManagementInfoDto ptlManagementInfoDto = new PtlManagementInfoDto();
            try {
                BeanUtil.copyProperties(ptlManagementInfoDto, entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            ptlManagementInfo001Service.doComplete(ptlManagementInfoDto,type);
        } else {
            restTemplate.postForObject(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/doDeleteAction", entity,
                    String.class);
        }

        // 二级法人权限处理
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(entity.getCreateUser());
            entity.setCreateInst(createInst);

            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            if (!OperationType.DELETE.equals(type)) {
                //获取所有父级机构。  拥有查看权限
                searchOrgCodeList = com.joyintech.tams.systemtools.util.CommonUtil.getParentInstitution(createInst);
                // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                editOrgCodeList = Arrays.asList(entity.getCreateInst());
            }
            // 记录二级法人权限
            insertDataAuthTypeInst(entity.getChildPortfolioId(), searchOrgCodeList, editOrgCodeList, DictCodeList.共通业务类型_组合管理.getValue());
        }

    }


    public List<SelectOptionDto> getPortfolioId(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getPortfolioId", map,
                SelectOptionDto.class);
    }

    /**
     * 获取初始化界面信息
     *
     * @param map 请求集合
     * @return
     * @see组合实体类
     * @since 1.0
     */
    public PtlManagementInfo004Dto getInitInfo(Map<String, Object> map) {
        PtlManagementInfo004Dto dto = restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getInitInfo", map,
                PtlManagementInfo004Dto.class);

        if (null != dto.getParentPortfolioId()) {
            PtlPortfolio ptlPortfolio = bussCommonMapperDelegateService.selectByMultiPrimaryKey(PtlPortfolioMapper.class,
                    dto.getParentPortfolioId(), PtlPortfolio.class);
            dto.setChildPortfolioId(dto.getPortfolioId());
            dto.setChildPortfolioName(dto.getPortfolioName());
            dto.setPortfolioId(ptlPortfolio.getPortfolioId());
            dto.setPortfolioName(ptlPortfolio.getPortfolioName());
        }
        return dto;
    }

    /**
     * 创建并返回子组合代码
     *
     * @param portfolioId 组合代码
     * @return
     */
    public String createChildPortfolioId(String portfolioId) {
        Map<String, Object> map = new HashMap<>();
        map.put("portfolioId", portfolioId);
        return restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/createChildPortfolioId", map, String.class);
    }
}
