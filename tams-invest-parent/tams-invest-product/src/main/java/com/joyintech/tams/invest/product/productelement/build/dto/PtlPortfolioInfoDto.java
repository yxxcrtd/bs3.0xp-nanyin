/*
 * 文件名：PtlManagementInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.build.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlValParam;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.component.business.portfolio.dto.FundAccountInfoDto;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 组合管理实体类
 * 
 * @author fanshuanghu
 * @version 1.0 2018年9月13日
 * @see PtlPortfolioInfoDto
 * @since 1.0
 */
public class PtlPortfolioInfoDto extends PtlPortfolio implements ITableRow, ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -286288564346458527L;

    /**
     * 是否保本
     */
    private String isGuarantee;

    /**
     * 开放类型，开放式、封闭式
     */
    private String openType;

    /**
     * 募集方式，公募、私募
     */
    private String raiseType;

    /**
     * 投资性质，固定收益类、权益类、商品及金融衍生品类、混合类
     */
    private String investNature;

    /**
     * 选择的行
     */
    private List<PtlPortfolioInfoDto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private List<String> userId;

    /**
     * 获取 userId
     * 
     * @return userId.
     */
    public List<String> getUserId() {
        return userId;
    }

    /**
     * 设置 userId
     * 
     * @param userId userId
     */
    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 资金账户信息实体类
     */
    private List<FundAccountInfoDto> fundAccountInfoDto;

    /**
     * 证券账户实体类
     */
    private List<SecuritiesAccountInfoDto> securitiesAccountInfoDto;

    /**
     * 证券账户实体类
     */

    private PtlValParam ptlValParam;

    public String getInvestNatureStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资性质, this.investNature);
    }

    public String getRaiseTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.募集方式, this.raiseType);
    }

    public String getOpenTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.开放类型, this.openType);
    }

    /**
     * 获取 openType
     * 
     * @return openType.
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * 设置 openType
     * 
     * @param openType openType
     */
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * 获取 raiseType
     * 
     * @return raiseType.
     */
    public String getRaiseType() {
        return raiseType;
    }

    /**
     * 设置 raiseType
     * 
     * @param raiseType raiseType
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    /**
     * 获取 investNature
     * 
     * @return investNature.
     */
    public String getInvestNature() {
        return investNature;
    }

    /**
     * 设置 investNature
     * 
     * @param investNature investNature
     */
    public void setInvestNature(String investNature) {
        this.investNature = investNature;
    }

    /**
     * 获取 isGuaranteeStr
     * 
     * @return isGuaranteeStr.
     */
    public String getIsGuaranteeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, isGuarantee);
    }

    /**
     * 设置主键和限制 {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getPortfolioId() {
        return super.getPortfolioId();
    }

    /**
     * 获取 fundAccountInfoDto
     * 
     * @return fundAccountInfoDto.
     */
    public List<FundAccountInfoDto> getFundAccountInfoDto() {
        return fundAccountInfoDto;
    }

    /**
     * 设置 fundAccountInfoDto
     * 
     * @param fundAccountInfoDto fundAccountInfoDto
     */
    public void setFundAccountInfoDto(List<FundAccountInfoDto> fundAccountInfoDto) {
        this.fundAccountInfoDto = fundAccountInfoDto;
    }

    /**
     * 获取 securitiesAccountInfoDto
     * 
     * @return securitiesAccountInfoDto.
     */
    public List<SecuritiesAccountInfoDto> getSecuritiesAccountInfoDto() {
        return securitiesAccountInfoDto;
    }

    /**
     * 设置 securitiesAccountInfoDto
     * 
     * @param securitiesAccountInfoDto securitiesAccountInfoDto
     */
    public void setSecuritiesAccountInfoDto(List<SecuritiesAccountInfoDto> securitiesAccountInfoDto) {
        this.securitiesAccountInfoDto = securitiesAccountInfoDto;
    }

    /**
     * 获取 ptlValParam
     * 
     * @return ptlValParam.
     */
    public PtlValParam getPtlValParam() {
        return ptlValParam;
    }

    /**
     * 设置 ptlValParam
     * 
     * @param ptlValParam ptlValParam
     */
    public void setPtlValParam(PtlValParam ptlValParam) {
        this.ptlValParam = ptlValParam;
    }

    /**
     * 有效状态
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.有效状态,
            DictCodeListExt1.有效状态_有效.getValue());
    }

    /**
     * 获取 profitTypeStr
     * 
     * @return profitTypeStr.
     */
    public String getProfitTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.收益类型, super.getProfitType());
    }

    /**
     * 获取 portfolioTypeStr
     * 
     * @return portfolioTypeStr.
     */
    public String getPortfolioTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.组合类型, super.getPortfolioType());
    }

    /**
     * 获取 isGuarantee
     * 
     * @return isGuarantee.
     */
    public String getIsGuarantee() {
        return isGuarantee;
    }

    /**
     * 设置 isGuarantee
     * 
     * @param isGuarantee isGuarantee
     */
    public void setIsGuarantee(String isGuarantee) {
        this.isGuarantee = isGuarantee;
    }

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<PtlPortfolioInfoDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<PtlPortfolioInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     * 
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     * 
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
}
