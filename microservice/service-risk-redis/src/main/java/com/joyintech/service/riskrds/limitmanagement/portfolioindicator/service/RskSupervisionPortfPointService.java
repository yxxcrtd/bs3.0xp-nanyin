package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dao.RskSupervisionPortfPointMapper;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dao.RskSupervisionIndicatorMapper;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskPortfolioIndicatorDto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicator002Dto;
import com.joyintech.service.riskrds.limitmanagement.superviseindicator.dto.RskSupervisionIndicatorDto;
import com.joyintech.service.riskrds.limitmanagement.supervisionindividuation.dto.RskIndividUniteDto;
import com.joyintech.tams.common.base.dao.PtlPortfolioMapper;
import com.joyintech.tams.common.base.dao.RskPortfolioIndicatorMapper;
import com.joyintech.tams.common.base.entity.PtlPortfolio;

/**
 * 监控指标
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPointService
 * @since 1.0
 */
@Service("backRskSupervisionPortfPointService")
public class RskSupervisionPortfPointService extends BaseService<RskPortfolioIndicator002Dto> {

    @Autowired
    private RskSupervisionPortfPointMapper rskSupervisionPortfPointMapper;

    @Autowired
    private RskSupervisionIndicatorMapper rskSupervisionIndicatorMapper;

    @Autowired
    private RskPortfolioIndicatorMapper rskPortfolioIndicatorMapper;

    @Autowired
    private PtlPortfolioMapper ptlPortfolioMapper;

    /**
     * 组合树Tab画面查询方法
     * 
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<RskPortfolioIndicator002Dto> selectForPage(int page, int length,
                                                           Map<String, Object> param)
        throws Exception {
        if (null != param.get("portfolioLevel")) {
            if (BussinessDictCodeListRds.组合层次_组合包.getValue().equals(param.get("portfolioLevel"))) {
                return super.queryForPage(page, length,
                    () -> rskSupervisionPortfPointMapper.selectForBagPage(param));
            }
        }
        return super.queryForPage(page, length,
            () -> rskSupervisionPortfPointMapper.selectForPage(param));
    }

    /**
     * 指标树Tab画面查询方法
     * 
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<RskPortfolioIndicator002Dto> selectTableIndForPage(int page, int length,
                                                                   Map<String, Object> param)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskSupervisionPortfPointMapper.selectTableIndForPage(param));
    }

    /**
     * 根据指标类型查询指标树
     * 
     * @param indType
     * @return
     * @since 1.0
     */
    public List<TreeNode<RskSupervisionIndicatorDto>> getTreeIndicatorByIndType(String indType) {
        return rskSupervisionPortfPointMapper.getTreeIndicatorByIndType(indType);
    }

    /**
     * 插入
     * 
     * @param entity
     * @return
     * @since 1.0
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insert(RskSupervisionIndicator002Dto entity) {
        if (null != entity.getSelectedPortfIndItem()) {
            // 组合关联指标
            for (RskPortfolioIndicatorDto portDto : entity.getSelectedPortfIndItem()) {
                portDto.setIndId(entity.getRskPortfolioIndicatorDto().getIndId());
                portDto.setPortfolioLevel(
                    entity.getRskPortfolioIndicatorDto().getPortfolioLevel());
                portDto.setStartDate(entity.getRskPortfolioIndicatorDto().getStartDate());
                portDto.setEndDate(entity.getRskPortfolioIndicatorDto().getEndDate());
                RskPortfolioIndicator002Dto rskPortDto = new RskPortfolioIndicator002Dto();
                rskPortDto.setPortfolioId(portDto.getPortfolioId());
                rskPortDto.setIndId(portDto.getIndId());
                rskPortfolioIndicatorMapper.deleteByPrimaryKey(rskPortDto);
                rskSupervisionIndicatorMapper.insertPortIndicator(portDto);
            }
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public int playPortfolio(Map<String, Object> param) {
        PtlPortfolio record = new PtlPortfolio();
        record.setPortfolioId((String)param.get("portfolioId"));
        record.setRiskEnabled("Y");
        ptlPortfolioMapper.updateByPrimaryKeySelective(record);
        return 1;
    }

    // add by zhuzhen 20191128 组合关联指标删除时校验联合设置 start
    /**
     * 根据组合代码，指标代码，查询个性化联合设置数据是否存在
     * @param param 参数
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public boolean selectIndividUnite(Map<String, Object> param) {
        List<RskIndividUniteDto> dtoList=rskSupervisionPortfPointMapper.selectIndividUnite(param);
        if(!StringUtil.isEmptyList(dtoList)){
            return true;
        }
        return false;
    }
    // add by zhuzhen 20191128 组合关联指标删除时校验联合设置 end
}
