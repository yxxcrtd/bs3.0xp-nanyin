
package com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.dto.AssetCategories002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 新建资产大类评级配置
 * 
 * @author 刘华明
 * @version 1.0 2021年6月29日
 * @see AssetCategories002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AssetCategories002Service extends CommonExportService<AssetCategories002Dto> implements BaseProcessService<AssetCategories002Dto> {
    /**
     * 检查资产类别和资产分类的唯一性
     * 
     * @param dto 资产大类评级配置
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean checkAssetTypeAndAssetDetailType(AssetCategories002Dto dto) {
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ValAssetCategories002/checkAssetCategories",dto, boolean.class);
    }
    
    /**
     * getBookSetById
     * @param seqNo
     * @return AssetCategories002Dto
     * @see
     * @since 1.0
     */
    public AssetCategories002Dto getAssetCategoriesBySeqNo(String seqNo) {
        return restTemplate.postForObject(ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/ValAssetCategories002/getAssetCategoriesBySeqNo", seqNo, AssetCategories002Dto.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(AssetCategories002Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
            action = "insert";
            entity.setCreateUser(SecurityUtil.getLoginUser().getUserName());
            entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        } else {
            action = "update";
            entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        }
        restTemplate.postForObject(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ValAssetCategories002/" + action, entity,
                Integer.class);
    }

}
