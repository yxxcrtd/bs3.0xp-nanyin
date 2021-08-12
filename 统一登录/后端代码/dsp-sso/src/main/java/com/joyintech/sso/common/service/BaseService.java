package com.joyintech.sso.common.service;

import com.joyintech.sso.common.dto.TreeNode;

import java.util.*;

public class BaseService {
    /**
     * list去重
     * @param treeNodes
     * @return
     */
    public List<TreeNode> removeDupliById(List<TreeNode> treeNodes) {
        Set<TreeNode> treeNodeSet = new TreeSet<TreeNode>((o1, o2) -> o1.getId().compareTo(o2.getId()));
        treeNodeSet.addAll(treeNodes);
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>(treeNodeSet);

        Collections.sort(treeNodeList, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                int i = o1.getSortnum() - o2.getSortnum();
                if(i == 0){
                    return 1;
                }
                return i;
            }
        });

        return treeNodeList;
    }

    /**
     * 设置父节点
     *
     */
    public void setParentNode(List<TreeNode> parentList,TreeNode node,Map<String,TreeNode> nodeMap) {
        for(int i=0;i<parentList.size();i++) {
            TreeNode parentNode = parentList.get(i);
            if(parentNode.getId().equals(node.getPid())) {
                nodeMap.put(parentNode.getId(), parentNode);
                parentList.remove(parentNode);
                i--;
                setParentNode(parentList,parentNode,nodeMap);
            }
        }
    }

    /**
     * 设置子节点
     *c
     */
    public void setChildrenNode(List<TreeNode> childrenList,TreeNode node,Map<String,TreeNode> nodeMap) {
        for(int i=0;i<childrenList.size();i++) {
            TreeNode childrenNode = childrenList.get(i);
            if(null!=childrenNode.getPid()&&childrenNode.getPid().equals(node.getId())) {
                nodeMap.put(childrenNode.getId(), childrenNode);
                childrenList.remove(childrenNode);
                i--;
                setParentNode(childrenList,childrenNode,nodeMap);
            }
        }
    }
}
