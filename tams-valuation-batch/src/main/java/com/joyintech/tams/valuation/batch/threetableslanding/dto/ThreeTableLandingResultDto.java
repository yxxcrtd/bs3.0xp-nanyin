/*
 * 文件名：AssetLiabilityStatementQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 资产负债表
 * 
 * @author zhangwei
 * @version 1.0 2018年9月18日
 * @see ThreeTableLandingResultDto
 * @since 1.0
 */
public class ThreeTableLandingResultDto extends NeedToWriteEntity {

    /**
     * 资产负债表List
     */
    private List<AssetLiabilityStatementDto> listAssetLiability;

    /**
     * 利润表
     */
    private List<ValProfitDto> listValProfitDto;

    /**
     * 所有者权益表
     */
    private List<OwnerEquityChangeDto> listOwnerEquityChangeDto;

    /**
     * 获取 资产负债表List
     * 
     * @return 资产负债表List
     */
    public List<AssetLiabilityStatementDto> getListAssetLiability() {
        return listAssetLiability;
    }

    /**
     * 设置 资产负债表List
     * 
     * @param listAssetLiability 资产负债表List
     */
    public void setListAssetLiability(List<AssetLiabilityStatementDto> listAssetLiability) {
        this.listAssetLiability = listAssetLiability;
    }

    /**
     * 获取 利润表
     * 
     * @return 利润表
     */
    public List<ValProfitDto> getListValProfitDto() {
        return listValProfitDto;
    }

    /**
     * 设置 利润表
     * 
     * @param listValProfitDto 利润表
     */
    public void setListValProfitDto(List<ValProfitDto> listValProfitDto) {
        this.listValProfitDto = listValProfitDto;
    }

    /**
     * 获取 所有者权益表
     * 
     * @return 所有者权益表
     */
    public List<OwnerEquityChangeDto> getListOwnerEquityChangeDto() {
        return listOwnerEquityChangeDto;
    }

    /**
     * 设置 所有者权益表
     * 
     * @param listOwnerEquityChangeDto 所有者权益表
     */
    public void setListOwnerEquityChangeDto(List<OwnerEquityChangeDto> listOwnerEquityChangeDto) {
        this.listOwnerEquityChangeDto = listOwnerEquityChangeDto;
    }

}
