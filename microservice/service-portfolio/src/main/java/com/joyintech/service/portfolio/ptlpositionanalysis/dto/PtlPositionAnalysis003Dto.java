
package com.joyintech.service.portfolio.ptlpositionanalysis.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.tams.common.base.entity.AssetTargetDb;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组合持仓分析报表
 * 
 * @author nixiukuan
 * @version 1.0 2019年12月05日
 * @see PtlPositionAnalysis003Dto
 * @since 1.0
 */

public class PtlPositionAnalysis003Dto extends AssetTargetDb implements GridRowDto {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */

    private static final long serialVersionUID = 8496377411327582052L;
    /**
     * 分类名称
     */
    private  String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 修正久期扩展
     */
    private  BigDecimal mdurationAmend;

    public BigDecimal getMdurationAmend() {
        return mdurationAmend;
    }

    public void setMdurationAmend(BigDecimal mdurationAmend) {
        this.mdurationAmend = mdurationAmend;
    }

    /**
     * 父节点
     */
    private String parentInfo;

    /**
     * 子节点
     */
    private String childInfo;

    /**
     * 是否有子节点
     */
    private String hasChildren;

    public String getParentInfo() {
        return parentInfo;
    }

    public void setParentInfo(String parentInfo) {
        this.parentInfo = parentInfo;
    }

    public String getChildInfo() {
        return childInfo;
    }

    public void setChildInfo(String childInfo) {
        this.childInfo = childInfo;
    }

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    @Override
    public String getRowId() {
        return null;
    }

    @Override
    public void setRowId(String s) {

    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void setOrder(int i) {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String s) {

    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public void setParentId(String s) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public void setLeaf(boolean b) {

    }

    @Override
    public String getCreateUser() {
        return null;
    }

    @Override
    public void setCreateUser(String s) {

    }

    @Override
    public String getCreateDept() {
        return null;
    }

    @Override
    public void setCreateDept(String s) {

    }
    private String belongInstitutionNameNew;

    public String getBelongInstitutionNameNew() {
        return belongInstitutionNameNew;
    }

    public void setBelongInstitutionNameNew(String belongInstitutionNameNew) {
        this.belongInstitutionNameNew = belongInstitutionNameNew;
    }
}
