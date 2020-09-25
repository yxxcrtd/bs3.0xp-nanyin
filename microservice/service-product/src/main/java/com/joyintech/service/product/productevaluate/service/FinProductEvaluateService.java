package com.joyintech.service.product.productevaluate.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productevaluate.dao.FinproductEvaluateMapper;
import com.joyintech.service.product.productevaluate.dto.FinAssetConfigDto;
import com.joyintech.service.product.productevaluate.dto.FinBokPositionPrdDto;
import com.joyintech.service.product.productevaluate.dto.FinProductVarDto;
import com.joyintech.service.product.productevaluate.dto.FinproductEvaluateDto;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 产品评价管理
 * @author guyunfei
 * @see
 * @since
 */
@Service
public class FinProductEvaluateService extends BaseService {

    @Autowired
    private FinproductEvaluateMapper finproductEvaluateMapper;

    /**
     * 存续产品查询
     * @param params
     * @return
     */
    public List<SelectOptionDto> getProNames(Map<String,Object> params){
        params.put("profitType",DictCodeList.收益类型_收益型.getValue());
        //params.put("productType",DictCodeListExt1.金融产品类型_理财产品.getValue());
        return finproductEvaluateMapper.getProNames(params);
    }

    /**
     * 存续产品的发行前信息查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    public Page<FinproductEvaluateDto> selectForPage(int page, int length, Map<String,Object> params){
        params.put("dataStatus",DictCodeListExt1.有效状态_有效.getValue());
        return super.queryForPage(page,length,()->finproductEvaluateMapper.queryBeforeIssue(params));
    }

    /**
     * 评价日资产配置情况查询
     * @param params
     * @return
     */
    public Page<FinAssetConfigDto> selectForPage2(int page, int length, Map<String,Object> params){
        String parentId = (String)params.get("parentId");
        if(!StringUtil.equals(parentId,"root")){
            List<FinAssetConfigDto> finAssetConfigDtos = finproductEvaluateMapper.queryAssetConfig(params);
            Page<FinAssetConfigDto> pagelist = new Page<>();
            pagelist.setRoot(finAssetConfigDtos);
            return pagelist;
        }else{
            return super.queryForPage(page, length,() -> finproductEvaluateMapper.queryAssetConfigHead(params));
        }
    }

    private Predicate<FinAssetConfigDto> getFilter(Map<String, Object> pageParam) {
        return (item -> {
            if (StringUtil.equals(pageParam.get("parentId"), "root")) {
                return true;
            }
            return true;
        });
    }

    /**
     * 产品净值表现查询
     * @param params
     * @return
     */
    public List<FinBokPositionPrdDto> selectForPage3(Map<String,Object> params){
        return finproductEvaluateMapper.queryProPerformance(params);
    }

    /**
     * 查询产品信息
     * @param params
     * @return
     */
    public FinProductVarDto queryProduct(Map<String,Object> params){
        return finproductEvaluateMapper.queryProduct(params);
    }

    /**
     * 组合份额查询
     * @param params
     * @return
     */
    public List<FinAssetConfigDto> queryCombinedShare(Map<String,Object> params){
        return finproductEvaluateMapper.queryCombinedShare(params);
    }

    /**
     * 根据产品代码查询子组合代码
     * @param params
     * @return
     */
    public List<PtlPortfolio> getsubCombinations(Map<String,Object> params){
        return finproductEvaluateMapper.subCombination(params);
    }

}
