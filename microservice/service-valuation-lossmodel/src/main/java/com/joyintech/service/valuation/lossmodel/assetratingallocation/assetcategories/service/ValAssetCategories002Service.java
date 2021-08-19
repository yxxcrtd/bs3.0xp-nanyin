
package com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dao.ValAssetCategories002Mapper;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.assetcategories.dto.ValAssetCategories002Dto;
import com.joyintech.tams.valuation.common.dto.modelDto.AssetCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 资产大类评级配置
 * 
 * @author 刘华明
 * @version 1.0 2021年6月29日
 * @see ValAssetCategories002Service
 * @since 1.0
 */
@Service(value="serviceValAssetCategories002Service")
@Transactional(rollbackFor = Exception.class)
public class ValAssetCategories002Service extends BaseService<ValAssetCategories002Dto> {

    /**
     * ValAssetBook002Mapper
     */
    @Autowired
    private ValAssetCategories002Mapper mapper;


    /**
     * 根据流水号获取数据
     * @param seqNo
     * @return
     */
    public ValAssetCategories002Dto getAssetCategoriesBySeqNo(String seqNo) {
        return mapper.getAssetCategoriesBySeqNo(seqNo);
    }

    public int delete(ValAssetCategories002Dto dto) {
        return mapper.deleteByPrimaryKey(dto.getSeqNo());
    }

    public void update(ValAssetCategories002Dto dto){
        Map<String, Object> map = new HashMap<>();
        map.put("assetType", dto.getAssetType());
        map.put("assetDetailType", dto.getAssetDetailType());
        map.put("seqNo",dto.getSeqNo());


        AssetCategories assetCategories = mapper.getAssetCategoriesSetByTypes(map);
        assetCategories.setGradeOrgId(dto.getGradeOrgId());
        assetCategories.setoType(dto.getoType());
        assetCategories.setGradeOrgType(dto.getGradeOrgType());
        mapper.updateByPrimaryKey(assetCategories);
    }

    public void insert(ValAssetCategories002Dto entity){
        AssetCategories assetCategories = new AssetCategories();
        // 资产类型
        assetCategories.setAssetType(entity.getAssetType());
        // 资产分类
        assetCategories.setAssetDetailType(entity.getAssetDetailType());
        // 流水号
        assetCategories.setSeqNo(entity.getSeqNo());
        // 评级机构
        assetCategories.setGradeOrgId(entity.getGradeOrgId());
        // 评级类型
        assetCategories.setoType(entity.getoType());
        // 评级机构类型
        assetCategories.setGradeOrgType(entity.getGradeOrgType());

        assetCategories.setCreateTime(new Date());
        assetCategories.setUpdateTime(new Date());
        assetCategories.setCreateUser(entity.getCreateUser());
        assetCategories.setUpdateUser(entity.getUpdateUser());

        // 保存
        mapper.insert(assetCategories);
    }

    public boolean check(ValAssetCategories002Dto dto){
        int count=mapper.checkAssetData(dto);
        if(count>0){
            return true;
        }
        return false;
    }
}
