
package com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dto.ValAssetCategories001Dto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.service.ValAssetCategories001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 模板示例<br>
 * 资产大类评级配置查询
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValAssetCategories001Controller
 * @since 2.0
 */
@RestController("valAssetCategories001Ctl")
@RequestMapping("ValAssetCategories001")
public class ValAssetCategories001Controller {
    
    /**
     * ValManualVoucher001Service
     */
    @Autowired
    private ValAssetCategories001Service valAssetCategories001Service;

    /**
     * 根据套帐名称 套帐类型查询数据 <功能详细描述>
     *
     * @return List<ValAssetCategories001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/getAllAssetCategoriesPage/{page}/{length}")
    public Page<ValAssetCategories001Dto> getAllAssetCategoriesPage(@RequestBody Map<String,Object> params,
                                                                    @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return valAssetCategories001Service.getAllAssetCategoriesPage(page,length,params);
    }

    /**
     *
     * 导出数据查询
     *
     * @param map
     * @return
     */
    @PostMapping("selectForExport")
    public List<ValAssetCategories001Dto> selectForExport(@RequestBody Map<String, Object> map) {
        return valAssetCategories001Service.selectForExport(map);
    }
}
