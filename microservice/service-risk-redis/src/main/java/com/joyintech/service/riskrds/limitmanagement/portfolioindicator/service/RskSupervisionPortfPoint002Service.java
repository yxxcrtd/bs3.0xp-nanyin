package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dao.RskSupervisionPortfPoint002Mapper;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dao.RskSupervisionPortfPointMapper;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.tams.common.base.dao.RskPortfolioIndicatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 组合关联监控指标
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPoint002Service
 * @since 1.0
 */
@Service("backRskSupervisionPortfPoint002Service")
public class RskSupervisionPortfPoint002Service extends BaseService<RskSupervisionIndicator002Dto> {

    @Autowired
    private RskSupervisionPortfPoint002Mapper rskSupervisionPortfPoint002Mapper;

    @Autowired
    private RskPortfolioIndicatorMapper rskPortfolioIndicatorMapper;
    
    @Autowired
    private RskSupervisionPortfPointMapper rskSupervisionPortfPointMapper;


    /**
     * 组合关联查询监督指标
     * 
     * @param param
     * @return
     */
    public Page<RskSupervisionIndicator002Dto> queryRelatedIndList(int page, int length,
                                                                   Map<String, Object> param)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskSupervisionPortfPoint002Mapper.queryRelatedIndList(param));
    }

    /**
     * 组合关联查询组合成分
     * 
     * @param portfolioCode
     * @return
     */
    public String getPortfolioConst(String portfolioCode) {
        return rskSupervisionPortfPoint002Mapper.getPortfolioConst(portfolioCode);
    }

    /**
     * 组合关联查询当前组合已关联的监督信息
     * @param portfolioCode
     * @param indId
     * @return
     */
    public RskPortfolioIndicator002Dto queryPortfIndByPortfId(String portfolioCode, String indId) {
        return rskSupervisionPortfPoint002Mapper.queryPortfIndByPortfId(portfolioCode, indId);
        /*
         * RskPortfolioIndicator002Dto rskPortfolioIndicator002Dto=new
         * RskPortfolioIndicator002Dto(); if(rskPortfIndList.size()>0){
         * rskPortfolioIndicator002Dto.setPortfolioId(rskPortfIndList.get(0).getPortfolioId());
         * rskPortfolioIndicator002Dto.setPortfolioName(rskPortfIndList.get(0).getPortfolioName());
         * rskPortfolioIndicator002Dto.setPortfolioLevel(rskPortfIndList.get(0).getPortfolioLevel()
         * ); rskPortfolioIndicator002Dto.setStartDate(rskPortfIndList.get(0).getStartDate());
         * rskPortfolioIndicator002Dto.setEndDate(rskPortfIndList.get(0).getEndDate());
         * rskPortfolioIndicator002Dto.setSelectedItem(rskPortfIndList); } return
         * rskPortfolioIndicator002Dto;
         */
    }

    /**
     * 插入
     * 
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insert(RskPortfolioIndicator002Dto entity) {
        if (null != entity.getRskSupervisionIndicator002DtoList()) {
            // 组合关联指标
            for (RskSupervisionIndicator002Dto portDto : entity.getRskSupervisionIndicator002DtoList()) {
                RskPortfolioIndicator002Dto dto = new RskPortfolioIndicator002Dto();
                BeanUtil.copyPropertiesIgnoreNull(dto, entity);
                dto.setIndId(portDto.getIndId());
                rskPortfolioIndicatorMapper.deleteByPrimaryKey(dto);
                rskPortfolioIndicatorMapper.insertSelective(dto);
            }
        }
    }

    /**
     * 更新有效区间
     * 
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(RskPortfolioIndicator002Dto entity) {
        if (null != entity) {
            rskPortfolioIndicatorMapper.updateByPrimaryKey(entity);
        }
    }

    /**
     * 删除操作 <功能详细描述>
     * 
     * @param dto
     */
    public int delete(RskPortfolioIndicator002Dto dto) {
        //删除组合关联监督指标下的个性化联合设置
        rskSupervisionPortfPointMapper.deleteIndividUnite(dto);
        //---add by 裴家俊 2019-12-09---添加其它个性化参数的删除---start----
        rskSupervisionPortfPointMapper.deleteIndividCondition(dto);
        rskSupervisionPortfPointMapper.deleteSpecialStageMapper(dto);
        rskSupervisionPortfPointMapper.deleteIndividAssetDel(dto);
        //---add by 裴家俊 2019-12-09---添加其它个性化参数的删除---end----
        // 删除组合关联监督指标
        rskPortfolioIndicatorMapper.deleteByPrimaryKey(dto);
        return 0;
    }

    /**
     * 判断组合包中没有相同指标 <功能详细描述>
     * @param indId
     * @param portfolioId
     */
    public int searchRelatedIndByPortf(String indId, String portfolioId) {
        return rskSupervisionPortfPoint002Mapper.searchRelatedIndByPortf(indId, portfolioId);
    }

    /**
     * 判断单组合中没有相同指标 <功能详细描述>
     * @param indId
     * @param portfolioId
     */
    public int searchRelatedIndByPortfBagSingle(String indId, String portfolioId) {
        return rskSupervisionPortfPoint002Mapper.searchRelatedIndByPortfBagSingle(indId,
            portfolioId);
    }

    /**
     * 判断当前组合包中组合成分在其它组合包中没有相同指标 <功能详细描述>
     * @param indId
     * @param portfolioId
     */
    public int searchRelatedIndByPortfBag(String indId, String portfolioId) {
        return rskSupervisionPortfPoint002Mapper.searchRelatedIndByPortfBag(indId, portfolioId);
    }
}
