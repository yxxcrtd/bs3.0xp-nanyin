
package com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dao.ValAssetCategories001Mapper;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dto.ValAssetCategories001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 手工凭证查询service
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValAssetCategories001Service
 * @since 2.0
 */
@Service(value="serviceValAssetCategories001Service")
@Transactional(rollbackFor = Exception.class)
public class ValAssetCategories001Service extends BaseService<ValAssetCategories001Dto> {
	
    /**
     * ValAssetCategories001Mapper
     */
    @Autowired
	private ValAssetCategories001Mapper mapper;

    /**
     * 根据套帐类型与套帐名称获取数据
     *
     * @param map 资产类别
     * @return List<ValAssetCategories001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValAssetCategories001Dto> getAllAssetCategoriesPage(int page, int length, Map<String, Object> map) throws Exception {
        return super.queryForPage(page,length,()-> mapper.getAssetSet02(map));
    }

    /**
     * 导出页面数据
     * @param map
     * @return
     */
    public List<ValAssetCategories001Dto> selectForExport(Map<String, Object> map) {
        return mapper.getForExcel(map);
    }
}
