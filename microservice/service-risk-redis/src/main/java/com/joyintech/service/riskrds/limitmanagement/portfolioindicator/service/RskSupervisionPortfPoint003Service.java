package com.joyintech.service.riskrds.limitmanagement.portfolioindicator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dao.RskSupervisionPortfPoint003Mapper;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPackage003Dto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPackageDtlDto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPackageDtlImp;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPortfolioPoolConfigDto;
import com.joyintech.service.riskrds.limitmanagement.portfolioindicator.dto.RskPtlPortfolioDto;
import com.joyintech.tams.common.base.dao.PtlParamPoolConfigMapper;
import com.joyintech.tams.common.base.dao.RskPortfolioPackageDtlMapper;
import com.joyintech.tams.common.base.dao.RskPortfolioPackageMapper;
import com.joyintech.tams.common.base.entity.PtlParamPoolConfig;
import com.joyintech.tams.common.base.entity.RskPortfolioPackageDtl;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;

/**
 * 监控指标
 * 
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see RskSupervisionPortfPoint003Service
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service("backRskSupervisionPortfPoint003Service")
public class RskSupervisionPortfPoint003Service extends BaseService {

    @Autowired
    private RskSupervisionPortfPoint003Mapper rskSupervisionPortfPoint003Mapper;

    @Autowired
    private RskPortfolioPackageMapper rskPortfolioPackageMapper;

    @Autowired
    private RskPortfolioPackageDtlMapper rskPortfolioPackageDtlMapper;

    @Autowired
    private PtlParamPoolConfigMapper ptlParamPoolConfigMapper;

    /**
     * 画面查询方法 <功能详细描述>
     * 
     * @param page
     * @param length
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<RskPortfolioPoolConfigDto> selectForPage(int page, int length,
                                                         Map<String, Object> param)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskSupervisionPortfPoint003Mapper.selectForPage(param));
    }

    /**
     * 插入
     * 
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insert(RskPortfolioPackage003Dto entity) {
        if (null == entity) {
            return;
        }
        //wangyang1  风险二级法人改造 start  
        Map<String, Object> param = new HashMap<>(); 
        CommonUtil.setInstitutionMapByType_ms(param);
        if (StringUtil.isNotEmpty(param.get("institution"))) {
            entity.setInstitutionCode(param.get("institution").toString());
        }
        //wangyang1  风险二级法人改造 end 
        // 组合包
        rskPortfolioPackageMapper.insert(entity);
        if (null != entity.getRskPortfolioPackageDtlDto()) {
            // 组合包明细
            for (RskPortfolioPackageDtl portDto : entity.getRskPortfolioPackageDtlDto().getRskPortfolioPackageDtlList()) {
                portDto.setPtlBagId(entity.getPtlBagId());
                rskPortfolioPackageDtlMapper.insert(portDto);
            }
        }
        if (null != entity.getRskPortfolioPoolConfigDto()) {
            // 组合入池配置
            for (PtlParamPoolConfig portDto : entity.getRskPortfolioPoolConfigDto().getPtlParamPoolConfigList()) {
                portDto.setPtlBagId(entity.getPtlBagId());
                ptlParamPoolConfigMapper.insert(portDto);
            }
        }
    }

    /**
     * 更新
     * 
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(RskPortfolioPackage003Dto entity) {
        if (null == entity) {
            return;
        }
        // 组合包
        rskPortfolioPackageMapper.updateByPrimaryKeySelective(entity);
        if (null != entity.getRskPortfolioPackageDtlDto()) {
            rskSupervisionPortfPoint003Mapper.deleteBagDtlByBagId(entity.getPtlBagId());
            // 组合包明细
            for (RskPortfolioPackageDtl portDto : entity.getRskPortfolioPackageDtlDto().getRskPortfolioPackageDtlList()) {
                portDto.setPtlBagId(entity.getPtlBagId());
                rskPortfolioPackageDtlMapper.insert(portDto);
            }
        }
        if (null != entity.getRskPortfolioPoolConfigDto()) {
            rskSupervisionPortfPoint003Mapper.deletePoolByBagId(entity.getPtlBagId());
            // 组合入池配置
            for (PtlParamPoolConfig portDto : entity.getRskPortfolioPoolConfigDto().getPtlParamPoolConfigList()) {
                portDto.setPtlBagId(entity.getPtlBagId());
                ptlParamPoolConfigMapper.insert(portDto);
            }
        }
    }

    /**
     * 删除
     * 
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public void deleteBag(String bagId) {
        // 组合包
        rskPortfolioPackageMapper.deleteByPrimaryKey(bagId);
        // 组合包明细
        rskSupervisionPortfPoint003Mapper.deleteBagDtlByBagId(bagId);
        // 组合入池配置
        rskSupervisionPortfPoint003Mapper.deletePoolByBagId(bagId);
    }

    /**
     * 查询组合包基本信息
     * 
     * @param entity
     * @return
     */
    public RskPortfolioPackage003Dto queryPortfBagInfoByBagId(String bagId) {
        RskPortfolioPackage003Dto rskPortfolioPackage003Dto = new RskPortfolioPackage003Dto();
        List<RskPortfolioPackageDtlImp> portfDtlList = rskSupervisionPortfPoint003Mapper.selectPortfBagDtlByBagId(
            bagId);
        RskPortfolioPackageDtlDto rskPortfolioPackageDtlDto = new RskPortfolioPackageDtlDto();
        rskPortfolioPackageDtlDto.setRskPortfolioPackageDtlList(portfDtlList);
        List<PtlParamPoolConfig> portfPoolList = rskSupervisionPortfPoint003Mapper.selectPortfPoolByBagId(
            bagId);
        RskPortfolioPoolConfigDto rskPortfolioPoolConfigDto = new RskPortfolioPoolConfigDto();
        rskPortfolioPoolConfigDto.setPtlParamPoolConfigList(portfPoolList);
        rskPortfolioPackage003Dto.setRskPortfolioPackageDtlDto(rskPortfolioPackageDtlDto);
        rskPortfolioPackage003Dto.setRskPortfolioPoolConfigDto(rskPortfolioPoolConfigDto);
        return rskPortfolioPackage003Dto;
    }
    
    /**
     * 查询组合包组合明细
     * 
     * @param entity
     * @return
     */
    public List<String> queryPortfolioPackageDtlByBagId(String bagId) { 
        List<RskPortfolioPackageDtlImp> portfDtlList = rskSupervisionPortfPoint003Mapper.selectPortfBagDtlByBagId(
            bagId); 
        List<String> list= new ArrayList<>();
        if (StringUtil.isNotEmpty(portfDtlList)) {
           list= portfDtlList.stream().map(item->item.getPortfolioId()).collect(Collectors.toList());
        } 
        return list;
    }

    /**
     * 查询组合包关联的指标
     * 
     * @param entity
     * @return
     */
    public int selectReleteIndByBagId(String bagId) {
        return rskSupervisionPortfPoint003Mapper.selectReleteIndByBagId(bagId);
    }

    /**
     * 新建编辑场合时查询监督指标名称是否重复
     * 
     * @param name
     */
    public int searchRepeatPortfNameByPortName(String portName) {
        return rskSupervisionPortfPoint003Mapper.searchRepeatPortfNameByPortName(portName);
    }

    /**
     * 画面查询方法 <功能详细描述>
     * 
     * @param page
     * @param length
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<RskPtlPortfolioDto> selectPortElementForPage(int page, int length,
                                                             Map<String, Object> param)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskSupervisionPortfPoint003Mapper.selectPortElementForPage(param));
    }

}
