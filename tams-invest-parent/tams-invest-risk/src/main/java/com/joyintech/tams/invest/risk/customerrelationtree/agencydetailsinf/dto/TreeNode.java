/*
 * 文件名：TreeNode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.dto;

import java.io.Serializable;
import java.util.List;

public class TreeNode implements Serializable{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private String id;

    /**
     * 父节点ID
     */
    private String pId;

    /**
     * 节点显示名
     */
    private String name;
    
    /**
     * 标记的图形。  例如图片：image://https://gallerybox.echartsjs.com/asset/get/s/data-1561621393298-zQTG-TuDS.png
     */
    private String symbol; 
    
    /**
     * 删除的主键
     */
    private String seqNo;
    
    /**
     * 子节点
     */
    private List<TreeNode> children;
    
    /**
     * 左子节点
     */
    private List<TreeNode> leftChi;
    
    
    /**
     * 右节点
     */
    private List<TreeNode> rightChi;
    
    
    /**
     * more中的属性
     */
    private List<TreeNode> moreList;
    
    /**
     * 子节点总数
     */
    private int nodeCount;
    
    
    /**
     * 是否有关联机构
     */
    private boolean hasEndAgency;
    

    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getpId() {
        return pId;
    }

    
    public void setpId(String pId) {
        this.pId = pId;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getSymbol() {
        return symbol;
    }

    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    
    public List<TreeNode> getChildren() {
        return children;
    }


    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public List<TreeNode> getMoreList() {
        return moreList;
    }

    
    public void setMoreList(List<TreeNode> moreList) {
        this.moreList = moreList;
    }


    
    public List<TreeNode> getLeftChi() {
        return leftChi;
    }


    
    public void setLeftChi(List<TreeNode> leftChi) {
        this.leftChi = leftChi;
    }


    
    public List<TreeNode> getRightChi() {
        return rightChi;
    }


    
    public void setRightChi(List<TreeNode> rightChi) {
        this.rightChi = rightChi;
    }


    
    public int getNodeCount() {
        return nodeCount;
    }


    
    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }


    
    public boolean isHasEndAgency() {
        return hasEndAgency;
    }


    
    public void setHasEndAgency(boolean hasEndAgency) {
        this.hasEndAgency = hasEndAgency;
    }


	public String getSeqNo() {
		return seqNo;
	}


	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

    
    
    
}
