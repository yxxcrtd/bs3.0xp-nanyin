/*
 * 文件名：AccountsAdjustAccountEntry001Dto.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights
 * Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.validate.SaveCheckValidate;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
*
* 文件名：AccountsAdjustAccountEntry001Controller.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：会计分录管理新建修改画面用dto
* 系统名称：估值核算系统V1.0
*/
@ValidLogic(targetClass = SaveCheckValidate.class)
public class ValAccountsAdjustAccountEntry003Dto extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 编辑模式
     */
    private final String editModel = GlobalConstant.DETAIL_PAGE_MODEL_EDIT;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 父节点
     */
    @NotEmpty(fieldName = "accountsadjust.accountentry.label.superNode")
    private String fsceneNodeid;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 影响记账元素
     */
    @NotEmpty(fieldName = "accountsadjust.accountentry.label.influAccountEle")
    private String nodeId;

    /**
     * 元素值域
     */
    @NotEmpty(fieldName = "accountsadjust.accountentry.label.eleValueEara")
    private String nodeValue;

    /**
     * 会计分录配置数据
     */
    private List<ValAccountsAdjustAccountEntry002Dto> rows;

    /**
     * 场景分支节点
     */
    private String sceneNodeId;
    
    /**
     * 级别
     */
    private Long level;

    /**
     * 更新时间
     */
    private Long updateTimeLong;
    
    /**
     * 其他的元素值域
     */
    private List<String> nodeValues;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 是否同时生成该节点的所有其他分录
     */
    private boolean isGen;

    /**
     * 获取 fsceneNodeid
     * 
     * @return fsceneNodeid.
     */
    public String getFsceneNodeid() {
        return fsceneNodeid;
    }

    /**
     * 设置 fsceneNodeid
     * 
     * @param fsceneNodeid fsceneNodeid
     */
    public void setFsceneNodeid(String fsceneNodeid) {
        this.fsceneNodeid = fsceneNodeid;
    }

    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 nodeId
     * 
     * @return nodeId.
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 设置 nodeId
     * 
     * @param nodeId nodeId
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 获取 nodeValue
     * 
     * @return nodeValue.
     */
    public String getNodeValue() {
        return nodeValue;
    }

    /**
     * 设置 nodeValue
     * 
     * @param nodeValue nodeValue
     */
    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    /**
     * 获取 rows
     * 
     * @return rows.
     */
    public List<ValAccountsAdjustAccountEntry002Dto> getRows() {
        return rows;
    }

    /**
     * 设置 rows
     * 
     * @param rows rows
     */
    public void setRows(List<ValAccountsAdjustAccountEntry002Dto> rows) {
        this.rows = rows;
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
     * 获取 editModel
     * 
     * @return editModel.
     */
    public String getEditModel() {
        return editModel;
    }

    /**
     * 获取 sceneNodeId
     * 
     * @return sceneNodeId.
     */
    public String getSceneNodeId() {
        return sceneNodeId;
    }

    /**
     * 设置 sceneNodeId
     * 
     * @param sceneNodeId sceneNodeId
     */
    public void setSceneNodeId(String sceneNodeId) {
        this.sceneNodeId = sceneNodeId;
    }

    /**
     * 获取 updateTimeLong
     * @return updateTimeLong.
     */
    public Long getUpdateTimeLong() {
        return updateTimeLong;
    }

    /**
     * 设置 updateTimeLong
     * @param updateTimeLong updateTimeLong
     */
    public void setUpdateTimeLong(Long updateTimeLong) {
        this.updateTimeLong = updateTimeLong;
    }

    /**
     * 获取 isGen
     * @return isGen.
     */
    public boolean isGen() {
        return isGen;
    }

    /**
     * 设置 isGen
     * @param isGen isGen
     */
    public void setGen(boolean isGen) {
        this.isGen = isGen;
    }

    /**
     * 获取 nodeValues
     * @return nodeValues.
     */
    public List<String> getNodeValues() {
        return nodeValues;
    }

    /**
     * 设置 nodeValues
     * @param nodeValues nodeValues
     */
    public void setNodeValues(List<String> nodeValues) {
        this.nodeValues = nodeValues;
    }

    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 level
     * @return level.
     */
    public Long getLevel() {
        return level;
    }

    /**
     * 设置 level
     * @param level level
     */
    public void setLevel(Long level) {
        this.level = level;
    }
}
