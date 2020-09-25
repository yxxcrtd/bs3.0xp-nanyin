package com.joyintech.service.riskrdsinf.process.entity;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;

/**
 * 实占审批Dto
 * 
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RskApproval002Dto
 * @since 1.0
 */
@PageId("RskApproval002")
public class RskApproval002Dto extends RskOccupyInfo implements RskApprovalDto {

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
     * 试算结论
     */
    private String conclusion;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConclusion() {
        return conclusion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
    @Override
    public String getElementId() {
        return elementId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
    @Override
    public String getPageId() {
        return pageId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
    @Override
    public String getSysCode() {
        return sysCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getEventIdStr(){
    	if (StringUtil.contains(getEventId(), GlobalConstant.COLON)) {
    		return StringUtil.substring(getEventId(), StringUtil.indexOf(getEventId(), GlobalConstant.COLON) + 1);
    	}
    	return getEventId();
    }
}
