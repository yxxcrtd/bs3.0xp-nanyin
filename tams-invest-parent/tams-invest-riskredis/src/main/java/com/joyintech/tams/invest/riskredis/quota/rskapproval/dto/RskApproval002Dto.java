package com.joyintech.tams.invest.riskredis.quota.rskapproval.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 实占审批Dto
 * 
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RskApproval002Dto
 * @since 1.0
 */
@PageId("RskApproval002")
public class RskApproval002Dto extends RskOccupyInfo implements ProcessEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 3680516262295636527L;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 交易场所
     */
    private String market;

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

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

    /**
     * 获取 资产名称
     * 
     * @return 资产名称.
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * 
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * 获取 交易场所
     * 
     * @return 交易场所.
     */
    public String getMarket() {
        return market;
    }

    /**
     * 设置 交易场所
     * 
     * @param market 交易场所
     */
    public void setMarket(String market) {
        this.market = market;
    }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // @PrimaryKey(Field.PK_2)
    // public Date getEventDate() {
    // return super.getEventDate();
    // }

    /**
     * {@inheritDoc}
     */
    @PrimaryKey(Field.PK_2)
    public String getEventDateStr() {
        return DateUtil.format(super.getEventDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getEventId() {
        return super.getEventId();
    }

    /**
     * 将交易方向对应的数据字典代码转换为数据字典值
     * 
     * @return 数据字典值
     */
    public String getPsAbbr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易方向, this.getPs());
    }

    @Override
    public String getPs() {
        return super.getPs();
    }

    /**
     * 将交易场所对应的数据字典代码转换为数据字典值
     * 
     * @return 数据字典值
     */
    public String getMarketAbbr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易场所, this.getMarket());
    }

    public String getEventIdStr(){
    	if (StringUtil.contains(getEventId(), GlobalConstant.COLON)) {
    		return StringUtil.substring(getEventId(), StringUtil.indexOf(getEventId(), GlobalConstant.COLON) + 1);
    	}
    	return getEventId();
    }

    /**
     * 试算结论
     */
    private String conclusion;

    /**
     * {@inheritDoc}
     */
    public String getConclusion() {
        return conclusion;
    }

    /**
     * {@inheritDoc}
     */
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    /**
     * 画面元素ID
     */
    private String elementId;

    /**
     * {@inheritDoc}
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * {@inheritDoc}
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 画面ID
     */
    private String pageId;

    /**
     * {@inheritDoc}
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * {@inheritDoc}
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 系统标识
     */
    private String sysCode;

    /**
     * {@inheritDoc}
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * {@inheritDoc}
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}
