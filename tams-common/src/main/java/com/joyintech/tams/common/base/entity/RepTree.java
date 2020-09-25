package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepTree extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * ID
     */
    private String treeId;

    /**
     * 父ID
     */
    private String pId;

    /**
     * 名称
     */
    private String treeName;

    /**
     * null
     */
    private String flag;

    
    public String getTreeId() {
        return treeId;
    }

    
    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    
    public String getpId() {
        return pId;
    }

    
    public void setpId(String pId) {
        this.pId = pId;
    }

    
    public String getTreeName() {
        return treeName;
    }

    
    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    
    public String getFlag() {
        return flag;
    }

    
    public void setFlag(String flag) {
        this.flag = flag;
    }

    
    
}