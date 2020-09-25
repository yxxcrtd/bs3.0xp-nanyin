/*
 * 文件名：SubPtlQueue.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.valrift.dto;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  子级组合队列
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月25日
 * @see SubPtlQueue
 * @since 1.0
 */
public class SubPtlQueue {

    private Queue<SubPtlNode> subPtlQueue;
    
    public SubPtlQueue() {
        subPtlQueue = new LinkedList<SubPtlNode>();
    }
    
    public void push(SubPtlNode node) {
        subPtlQueue.add(node);
    }
    
    public SubPtlNode pop() {
        return subPtlQueue.poll();
    }
    
    public int size(){
        return subPtlQueue.size();
    }
    
    public Queue<SubPtlNode> getTaskList(){
        return subPtlQueue;
    }
    
    
}
