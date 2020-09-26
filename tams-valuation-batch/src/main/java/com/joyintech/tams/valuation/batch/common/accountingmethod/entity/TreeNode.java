/*
 * 文件名：TreeNode.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved. 描述：中信银行资管系统
 * 修改人：qiangchengxi 修改时间：2017年1月3日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.common.accountingmethod.entity;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.StringUtil;

/**
 * 系统记账公共方法用树节点类
 * 
 * @author qiangchengxi
 * @version 1.0 2017年1月3日
 * @see TreeNode
 * @since 1.0
 */
public class TreeNode {

    /**
     * 节点序号
     */
    private String nodeNo;

    /**
     * 节点类型
     */
    private String nodeType;

    /**
     * 下级节点（孩子）
     */
    private List<TreeNode> childrenNodes;

    /**
     * 上级节点（父亲）
     */
    private TreeNode fatherNode;

    /**
     * 下级节点值域
     */
    private String childrenRange;

    /**
     * 节点值
     */
    private String nodeValue;

    /**
     * 绑定分录信息
     */
    private List<AccountEntry> accountEntrys;

    /**
     * 对应场景分支节点
     */
    private String sceneNodeId;

    /**
     * 增加子节点
     * 
     * @param treeNode TreeNode
     * @see
     * @since 1.0
     */
    public void addChild(TreeNode treeNode) {
        // 1 判断传入参数是否为空
        if (treeNode == null) {
            // 为空，直接返回（return）。
            return;
        }
        // 不为空，下记处理继续。
        // 2 获取下级节点list
        List<TreeNode> children = this.getChildrenNodes();
        if (null == children) {
            // 为空，则赋值一个空列表（new ArrayList<TreeNode>();)。
            children = new ArrayList<>();
            this.setChildrenNodes(children);
        }
        // 不为空，下记处理继续。

        // 3 根据节点值判断传入的参数是否已存在
        // 3.1 定义布尔类型变量 flag ，初始值为 FALSE 。
        boolean flag = false;

        // 3.2 定义字符串类型变量 nodeValue，赋值为传入参数节点的节点值（treeNode.getNodeValue()）。
        String nodeValue = treeNode.getNodeValue();
        // 3.3 遍历 list ,校验 步骤3.2 中定义的变量 nodeValue 在列表中是否存在，并做如下赋值。
        for (TreeNode node : children) {
            String nodeValue_var = node.getNodeValue();
            if (nodeValue.equals(nodeValue_var)) {
                flag = true;
                break;
            }
        }

        // 3.4 判断 步骤3.1中定义的变量 flag 的真假。
        // 为真，说明该节点已经存在，不用再添加，直接返回（return）。
        // 为假，说明该节点不存在，则添加节点，如下：
        if (!flag) {
            children.add(treeNode);
        }
    }

    /**
     * test1 测试 addChild 和 getChildByNodeValue 方法 <功能详细描述>
     * 
     * @param args
     * @see
     * @since 1.0
     */
//    public static void main(String[] args) {
//        TreeNode f = new TreeNode();
//        TreeNode s = new TreeNode();
//        s.setNodeValue("aaaa");
//        f.addChild(s);
//        TreeNode s1 = new TreeNode();
//        s1.setNodeValue("aaaa");
//        f.addChild(s1);
//        TreeNode s2 = new TreeNode();
//        s2.setNodeValue("bb");
//        f.addChild(s2);
//        System.out.println(f);
//    }

    /**
     * 根据节点值获取子节点
     * 
     * @param nodeValue 节点值
     * @return 子节点
     * @see
     * @since 1.0
     */
    public TreeNode getChildByNodeValue(String nodeValue) {
        // 1 判断参数 nodeValue 是否为空。
        // 为空，直接返回NULL。
        if (StringUtil.isEmpty(nodeValue)) {
            return null;
        }
        // 不为空，下记处理继续。

        // 2 获取获取孩子节点 list。
        List<TreeNode> list = getChildrenNodes();

        // 3 遍历 步骤2 获取的列表 list 。
        if (list != null) {
            for (TreeNode node : list) {
                if (nodeValue.equals(node.getNodeValue())) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * 获取 nodeNo
     * 
     * @return nodeNo.
     */
    public String getNodeNo() {
        return nodeNo;
    }

    /**
     * 设置 nodeNo
     * 
     * @param nodeNo nodeNo
     */
    public void setNodeNo(String nodeNo) {
        this.nodeNo = nodeNo;
    }

    /**
     * 获取 nodeType
     * 
     * @return nodeType.
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置 nodeType
     * 
     * @param nodeType nodeType
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    /**
     * 获取 childrenNodes
     * 
     * @return childrenNodes.
     */
    public List<TreeNode> getChildrenNodes() {
        return childrenNodes;
    }

    /**
     * 设置 childrenNodes
     * 
     * @param childrenNodes childrenNodes
     */
    public void setChildrenNodes(List<TreeNode> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }

    /**
     * 获取 fatherNode
     * 
     * @return fatherNode.
     */
    public TreeNode getFatherNode() {
        return fatherNode;
    }

    /**
     * 设置 fatherNode
     * 
     * @param fatherNode fatherNode
     */
    public void setFatherNode(TreeNode fatherNode) {
        this.fatherNode = fatherNode;
    }

    /**
     * 获取 childrenRange
     * 
     * @return childrenRange.
     */
    public String getChildrenRange() {
        return childrenRange;
    }

    /**
     * 设置 childrenRange
     * 
     * @param childrenRange childrenRange
     */
    public void setChildrenRange(String childrenRange) {
        this.childrenRange = childrenRange;
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
     * 获取 accountEntrys
     * 
     * @return accountEntrys.
     */
    public List<AccountEntry> getAccountEntrys() {
        return accountEntrys;
    }

    /**
     * 设置 accountEntrys
     * 
     * @param accountEntrys accountEntrys
     */
    public void setAccountEntrys(List<AccountEntry> accountEntrys) {
        this.accountEntrys = accountEntrys;
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
}
