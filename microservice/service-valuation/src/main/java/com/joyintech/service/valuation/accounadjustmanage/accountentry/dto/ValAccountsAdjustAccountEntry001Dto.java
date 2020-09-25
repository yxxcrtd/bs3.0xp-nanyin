/*
 * 文件名：AccountsAdjustAccountEntry001Dto.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights
 * Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.accountentry.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.validate.EntryDeleteValidator;
/**
*
* 文件名：AccountsAdjustAccountEntry001Dto.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：会计分录管理画面用dto
* 系统名称：估值核算系统V1.0
*/
@ValidLogic(targetClass = EntryDeleteValidator.class)
public class ValAccountsAdjustAccountEntry001Dto extends BaseEntity implements GridRowDto{

	private static final long serialVersionUID = 1L;

	/**
     * 树key
     */
    private Integer index;
    
    /**
     * 节点场景分支代码
     */
    private String sceneNodeId;
    
    /**
     * 节点场景分支父代码
     */
    private String fsceneNodeid;

    /**
     * 影响记账元素
     */
    private String dictId;

    /**
     * 影响记账元素名称
     */
    private String tableName;

    /**
     * 元素值
     */
    private String nodeValue;

    /**
     * 元素值说明
     */
    private String dictName;

    /**
     * 是否包含分录
     */
    private Boolean isEntry;

    /**
     * 所属账套
     */
    private String booksetId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 父节点id
     */
    private Integer parent;

    /**
     * 是否是叶子
     */
    private boolean isLeaf;

    /**
     * 是否展开
     */
    private boolean expanded;

    /**
     * 级别
     */
    private int level;

    /**
     * 是否禁用编辑等按钮
     */
    private String disCbxCol;

    /**
     * 更新时间
     */
    private long updateTimeLong;

    /**
     * 获取画面传的更新时间
     * @return 画面传的更新时间
     * @see
     * @since 1.0
     */
    public long getUpdateTimeFromPage() {
        return updateTimeLong;
    }

    /**
     * 获取时间毫秒数
     * 
     * @return 毫秒数
     */
    public long getUpdateTimeLong() {
        return updateTime.getTime();
    }

    /**
     * 获取 dictId
     * 
     * @return dictId.
     */
    public String getDictId() {
        if (level == 0) {
            return StringUtil.EMPTY;
        }
        return dictId;
    }

    /**
     * 设置 dictId
     * 
     * @param dictId dictId
     */
    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    /**
     * 获取 tableName
     * 
     * @return tableName.
     */
    public String getTableName() {
        if (level == 0) {
            return StringUtil.EMPTY;
        }
        return tableName;
    }

    /**
     * 设置 tableName
     * 
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 获取 nodeValue
     * 
     * @return nodeValue.
     */
    public String getNodeValue() {
        if (level == 0) {
            return StringUtil.EMPTY;
        }
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
     * 获取 dictName
     * 
     * @return dictName.
     */
    public String getDictName() {
        if (level == 0) {
            return StringUtil.EMPTY;
        }
        return dictName;
    }

    /**
     * 设置 dictName
     * 
     * @param dictName dictName
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 获取 isEntry
     * 
     * @return isEntry.
     */
    @JsonProperty("isEntry")
    public Boolean getIsEntry() {
        return this.isEntry;
    }

    /**
     * 设置 isEntry
     * 
     * @param isEntry isEntry
     */
    public void setIsEntry(Boolean isEntry) {
        this.isEntry = isEntry;
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
     * 获取 updateTime
     * 
     * @return updateTime.
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 updateTime
     * 
     * @param updateTime updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取 isLeaf
     * 
     * @return isLeaf.
     */
    @JsonProperty("isLeaf")
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * 设置 isLeaf
     * 
     * @param isLeaf isLeaf
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 获取 expanded
     * 
     * @return expanded.
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * 设置 expanded
     * 
     * @param expanded expanded
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * 获取 level
     * 
     * @return level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置 level
     * 
     * @param level level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 获取 disCbxCol
     * 
     * @return disCbxCol.
     */
    public String getDisCbxCol() {
        return disCbxCol;
    }

    /**
     * 设置 disCbxCol
     * 
     * @param disCbxCol disCbxCol
     */
    public void setDisCbxCol(String disCbxCol) {
        this.disCbxCol = disCbxCol;
    }

    /**
     * 设置 updateTimeLong
     * 
     * @param updateTimeLong updateTimeLong
     */
    public void setUpdateTimeLong(Long updateTimeLong) {
        this.updateTimeLong = updateTimeLong;
    }

    /**
     * 获取 index
     * @return index.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 设置 index
     * @param index index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 获取 sceneNodeId
     * @return sceneNodeId.
     */
    public String getSceneNodeId() {
        return sceneNodeId;
    }

    /**
     * 设置 sceneNodeId
     * @param sceneNodeId sceneNodeId
     */
    public void setSceneNodeId(String sceneNodeId) {
        this.sceneNodeId = sceneNodeId;
    }

    /**
     * 获取 parent
     * @return parent.
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * 设置 parent
     * @param parent parent
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     * 获取 fsceneNodeid
     * @return fsceneNodeid.
     */
    public String getFsceneNodeid() {
        return fsceneNodeid;
    }

    /**
     * 设置 fsceneNodeid
     * @param fsceneNodeid fsceneNodeid
     */
    public void setFsceneNodeid(String fsceneNodeid) {
        this.fsceneNodeid = fsceneNodeid;
    }

    @Override
    public String getRowId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setRowId(String rowId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setOrder(int order) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getId() {
        return getSceneNodeId();
    }

    @Override
    public void setId(String id) {
        this.sceneNodeId = id;
    }

    @Override
    public String getParentId() {
        return getFsceneNodeid();
    }

    @Override
    public void setParentId(String parentId) {
        this.fsceneNodeid = parentId;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
    }
}
