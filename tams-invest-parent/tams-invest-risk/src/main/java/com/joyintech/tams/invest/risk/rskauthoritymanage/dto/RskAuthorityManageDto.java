/*
 * 文件名：RskAuthorityManageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.rskauthoritymanage.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.RskAuthorizeMng;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.rskauthoritymanage.valid.RskAuthorityManageValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年11月30日
 * @see RskAuthorityManageDto
 * @since 1.0
 */
@PageId("RskAuthorityManage002")
@ValidLogic(targetClass = RskAuthorityManageValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class RskAuthorityManageDto extends RskAuthorizeMng implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    /**
     * 用于判断在代办页面是新建还是修改
     */
    private String wflOperType;
    /**
     * 流程中做pk1存json串
     */
    private String allData;
    
    /**
     * 选择数据
     */
    private List<RskAuthorityManageDto> selectedItem;

    /**
     * 流水号
     * {@inheritDoc}
     */
    @Override
    public String getSeqNo() {
        return super.getSeqNo();
    }
    /**
     * 取得 角色岗位
     * @return 角色岗位
     */
    @Override
    @PrimaryKey(value = Field.PK_2)
    @NotEmpty(fieldName = "RskAuthorityManage001.rolePos", groups = {ValidateGroupEdit.class})
    public String getRolePos() {
        return super.getRolePos();
    }

    @Override
    @NotEmpty(fieldName = "RskAuthorityManage001.userName", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value = Field.PK_3)
    public String getUserName(){
        return super.getUserName();
    }

    public String getAllData() {
        return allData;
    }
    
    public void setAllData(String allData) {
        this.allData = allData;
    }

    /**
     * 流水号
     * {@inheritDoc}
     */
    @PrimaryKey(value = Field.PK_1)
    public String getSeqNoStr() {
        return StringUtil.isNotEmpty(super.getSeqNo())?super.getSeqNo():IDGenerator.generate();
    }
    
    public String getPageModel() {
        return pageModel;
    }

    
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    public String getOperateType() {
        return operateType;
    }

    
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    public String getElementId() {
        return elementId;
    }

    
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    
    public String getPageId() {
        return pageId;
    }

    
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    
    public List<RskAuthorityManageDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RskAuthorityManageDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    public String getRolePosStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.角色岗位, super.getRolePos());
    }
    // 资产类型改为授权类型 modify by renping 2019-08-06  start
//    public String getAssetTypeStr() {
//        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, super.getAssetType());
//    }
    public String getAssetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.授权类型一级, super.getAssetType());
    }
    // 资产类型改为授权类型 modify by renping 2019-08-06  end
    
    public String getTradeMarketStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }
    
    public String getAuthorizeTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.授权类型, super.getAuthorizeType());
    }
    @Override
    public BigDecimal getAuthorizeLimitAmt(){
        return super.getAuthorizeLimitAmt();
    }

    // 资产类型改为授权类型 modify by renping 2019-08-06  start
    public String getAssetSecTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.授权类型二级, super.getAssetSecType());
//        if(StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_债券.getValue())){
//            return SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等, super.getAssetSecType());
//        }
//        else if(StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_基金.getValue())){
//            return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型, super.getAssetSecType());
//        }
//        else if(StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_存款.getValue())){
//            return SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型, super.getAssetSecType());
//        }
//        else if(StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_计划类资产_净值型.getValue())
//            || StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_计划类资产_利率型.getValue())){
//            return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, super.getAssetSecType());
//        }
//        else if(StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_项目类资产.getValue())){
//            return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, super.getAssetSecType());
//        }
//        else if(StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_质押式回购.getValue())){
//            return SysDataUtil.getNameFromDataDict(DictCode.回购类型, super.getAssetSecType());
//        }
//        else if(StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_拆借.getValue())){
//            return SysDataUtil.getNameFromDataDict(DictCode.拆借类型, super.getAssetSecType());
//        }
//        return null;
    }
    // 资产类型改为授权类型 modify by renping 2019-08-06  end
    
    public String getTradeDirectionStr(){
           String tradeDirectionStr = SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getTradeDirection()); 
           if (!StringUtil.isNotEmpty(tradeDirectionStr) && StringUtil.equals(super.getAssetType() , "17")) {
               tradeDirectionStr = SysDataUtil.getNameFromDataDict(DictCode.互换方式, super.getTradeDirection());
           }
           if (!StringUtil.isNotEmpty(tradeDirectionStr)) {
               tradeDirectionStr = SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getTradeDirection());
           }
           return tradeDirectionStr;
//        if(StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_拆借.getValue())
//            || StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_质押式回购.getValue())
//            || StringUtil.equals(super.getAssetType(), DictCodeList.平安资产类型_买断式回购.getValue()) ){
//            return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getTradeDirection());
//        }
//        else{
//            return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getTradeDirection());
//        }
    }
    
    public String getWflOperType() {
        return wflOperType;
    }
    
    public void setWflOperType(String wflOperType) {
        this.wflOperType = wflOperType;
    }
    
    
}
