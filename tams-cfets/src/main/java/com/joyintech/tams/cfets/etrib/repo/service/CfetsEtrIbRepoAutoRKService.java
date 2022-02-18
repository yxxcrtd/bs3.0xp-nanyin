package com.joyintech.tams.cfets.etrib.repo.service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.cfets.etrib.repo.dao.CfetsEtrIbRepoinfoMapper;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoRkDto;
import com.joyintech.tams.common.base.entity.EtrSecStock;
import com.joyintech.tams.common.base.entity.EtrSecStockConfig;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CfetsEtrIbRepoAutoRKService {

    @Autowired
    CfetsEtrIbRepoinfoMapper cfetsEtrIbRepoinfoMapper;

    @Autowired
    CfetsEtrIbRepoRKService cfetsEtrIbRepoRKService;

    public void autoInLibrary(String userId, String finprodId, List<String> portfolioIds) {
        String repo_pledge_model = CacheUtil.getSysParam("repo_pledge_model");
        if (!StringUtil.equals(repo_pledge_model, "03")) {
            return;
        }
        this.createComRule(userId);
        List<CfetsEtrIbRepoRkDto> cfetsEtrIbRepoRkDtoList = new ArrayList<>();
        // 组合为空
        List<CfetsEtrIbRepoRkDto> cfetsEtrIbRepoRkDtoAllList = new ArrayList<>();
        // 组合不为空
        List<CfetsEtrIbRepoRkDto> cfetsEtrIbRepoRkDtoPortList = new ArrayList<>();
        // 不存在的对应配置的组合
        List<String> portfolioIdsNotIn = new ArrayList<>();
        // 配置信息拆分
        cfetsEtrIbRepoRkDtoList.forEach(item -> {
            if (StringUtil.isNotEmpty(item.getPortfolioId())) {
                cfetsEtrIbRepoRkDtoPortList.add(item);
            } else {
                cfetsEtrIbRepoRkDtoAllList.add(item);
            }
        });
        for(String portfolioId : portfolioIds) {
            for (CfetsEtrIbRepoRkDto portDto : cfetsEtrIbRepoRkDtoPortList) {

            }
        }
        if (cfetsEtrIbRepoRkDtoAllList == null || cfetsEtrIbRepoRkDtoAllList.isEmpty()) {
            return;
        }
    }

    /**
     * 创建 通用质押券库
     *
     * @param userId 用户id
     * @return boolean
     * @author jiangpeng
     * @date 2022/2/17 19:34
     */
    public boolean createComRule(String userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        List<EtrSecStock> etrSecStocks = cfetsEtrIbRepoinfoMapper.selectEtrSecStockByUser(param);
        // 存在质押券自动入库设置则不创建通用质押券库
        if (etrSecStocks != null && !etrSecStocks.isEmpty()) {
            return false;
        }
        String repo_pledge_model = CacheUtil.getSysParam("repo_pledge_model");
        if (!StringUtil.equals(repo_pledge_model, "03")) {
            return false;
        }
        param.put("pledgeStockId", "comConfig");
        List<EtrSecStockConfig> configs = cfetsEtrIbRepoinfoMapper.getAssetDetailBySeqno(param);
        CfetsEtrIbRepoRkDto dto = new CfetsEtrIbRepoRkDto();
        dto.setPledgeStockId(String.valueOf(new Date().getTime()));
        dto.setDefaultStock(dto.getPledgeStockId());
        dto.setPledgeStockName("通用质押券库");
        dto.setIsDefStock(DictCodeList.是否_是.getValue());
        dto.setCreateUser(userId);
        if (configs != null) {
            configs.forEach(item -> {
                item.setPledgeStockId(dto.getPledgeStockId());
                item.setConfigId(IDGenerator.generate());
            });
        }
        dto.setEtrSecStockConfig(configs);
        dto.setPtlSecStock(new ArrayList<>());
        cfetsEtrIbRepoRKService.insert(dto);
        cfetsEtrIbRepoRKService.inserttotal(dto);
        return true;
    }
}
