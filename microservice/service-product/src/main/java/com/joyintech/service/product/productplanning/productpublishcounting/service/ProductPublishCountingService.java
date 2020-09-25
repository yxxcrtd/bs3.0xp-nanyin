package com.joyintech.service.product.productplanning.productpublishcounting.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.export.CommonExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productplanning.productpublishcounting.dao.ProductPublishCountingMapper;
import com.joyintech.service.product.productplanning.productpublishcounting.dto.ProductPublishCounting001Dto;

/**
 * 产品发行规划试算
 * @version 1.0 2018年12月18日
 * @see ProductPublishCountingService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductPublishCountingService extends CommonExportService<ProductPublishCounting001Dto> {
	
	@Autowired
    private ProductPublishCountingMapper productPublishCountingMapper;
	
	/**
     * 发行试算产品系列列表查询
     */
    public Page<ProductPublishCounting001Dto> findPrdPage(Map<String,Object> params, int page, int length){
        //BSTOT-325 guyunfei 20200110
    	//return super.queryForPage(page,length,() -> productPublishCountingMapper.getPrdPage(params));
        List<ProductPublishCounting001Dto> prdPage = productPublishCountingMapper.getPrdPage(params);
        return super.queryForTree(()->prdPage,
                    ProductPublishCounting001Dto::getChildInfo,
                    ProductPublishCounting001Dto::getParentInfo,
                    ProductPublishCounting001Dto::getPrdPlanSerisName,
                    (item)->true
                );
    }
	/**
     * 发行试算资产大类列表查询
     */
    public Page<ProductPublishCounting001Dto> findAssetPage(Map<String,Object> params, int page, int length){
        //BSTOT-325 guyunfei 20200110
    	//return super.queryForPage(page,length,() -> productPublishCountingMapper.getAssetPage(params));
        List<ProductPublishCounting001Dto> assetPage = productPublishCountingMapper.getAssetPage(params);
        return super.queryForTree(()->assetPage,
                ProductPublishCounting001Dto::getChildInfo,
                ProductPublishCounting001Dto::getParentInfo,
                ProductPublishCounting001Dto::getPrdPlanSerisName,
                (item)->true
        );
    }
	/**
     * 查询
     */
    public List<ProductPublishCounting001Dto> getAssetPlanList(Map<String,Object> params){
        return productPublishCountingMapper.getAssetPlanList(params);
    }
    /**
     * 查询策略明细
     */
    public List<ProductPublishCounting001Dto> getInvStrategyDetailList(String strategyId){
        return productPublishCountingMapper.getInvStrategyDetailList(strategyId);
    }
    /**
     * 发行规划信息查询
     */
    public ProductPublishCounting001Dto getPrdIssuePlanInfoByCode(String code){
        return productPublishCountingMapper.getPrdIssuePlanInfoByCode(code);
    }
    /**
     * 发行规划策略查询
     */
    public List<ProductPublishCounting001Dto> getPrdPlanStrategyListByCode(String code){
        return productPublishCountingMapper.getPrdPlanStrategyListByCode(code);
    }
    /**
     * 发行规划资产大类查询
     */
    public List<ProductPublishCounting001Dto> getPrdPlanAssetListByCode(String code){
        return productPublishCountingMapper.getPrdPlanAssetListByCode(code);
    }
    /**
     * 通过产品发行规划编号删除产品发行规划策略
     */
    public int deleteIssueplanStrategyByCode(String code){
        return productPublishCountingMapper.deleteIssueplanStrategyByCode(code);
    }
    /**
     * 通过产品发行规划编号删除产品发行规划策略明细
     */
    public int deleteStrategyDetailByCode(String code){
        return productPublishCountingMapper.deleteStrategyDetailByCode(code);
    }
    /**
     * 通过产品发行规划编号删除产品发行规划资产大类
     */
    public int deleteIssueplanAssetByCode(String code){
        return productPublishCountingMapper.deleteIssueplanAssetByCode(code);
    }
    /**
     * 通过产品发行规划编号删除产品发行规划资产大类
     */
    public Page<SelectOptionDto> getPrdIssueplanCode(Map<String, Object> map, int page, int length){
        return super.queryForPageForSelectReturn(page, length,
                () -> productPublishCountingMapper.getPrdIssueplanCode(map));
    }
}
