package com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.dailyoperation.dailyaccountmake.valassetreclassification.validator.ValAssetReclassification001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 资产重分类
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see ValAssetReclassification001ValidDto
 * @since 1.0
 */
@PageId("ValAssetReclassification001")
@ValidLogic(targetClass = ValAssetReclassification001Validator.class)
public class ValAssetReclassification001ValidDto extends BaseEntity implements ProcessEntity{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产类型
     */
    private String assetType;
    
    /**
     * 账套
     */
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName = "ValAssetReclassification001ValidDto.booksetId")
    private String booksetId;
    
    /**
     * 资产代码
     */
    private String finprodId;
    
    /**
     * 资产分类集合
     */
    private List<ValAssetReclassification001Dto> valAssetReclassification001DtoList = new ArrayList<ValAssetReclassification001Dto>();

    
    public String getAssetType() {
        return assetType;
    }

    
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    
    public String getBooksetId() {
        return booksetId;
    }

    
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    
    public String getFinprodId() {
        return finprodId;
    }


    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    public List<ValAssetReclassification001Dto> getValAssetReclassification001DtoList() {
        return valAssetReclassification001DtoList;
    }

    
    public void setValAssetReclassification001DtoList(List<ValAssetReclassification001Dto> valAssetReclassification001DtoList) {
        this.valAssetReclassification001DtoList = valAssetReclassification001DtoList;
    }


}
