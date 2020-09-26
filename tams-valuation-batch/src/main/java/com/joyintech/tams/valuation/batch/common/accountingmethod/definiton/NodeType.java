/*
 * 文件名：NodeType.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved. 描述：中信银行资管系统
 * 修改人：qiangchengxi 修改时间：2017年1月3日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.common.accountingmethod.definiton;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author qiangchengxi
 * @version 1.0 2017年1月3日
 * @see NodeType
 * @since 1.0
 */
public enum NodeType {
    /**
     * 根节点
     */
    根节点("1"),
    /**
     * 普通节点
     */
    普通节点("2");

    /**
     * 值
     */
    private String code;

    /**
     * 构造方法
     * 
     * @param code 值
     */
    NodeType(String code) {
        this.code = code;
    }

    /**
     * 获取 code
     * 
     * @return code.
     */
    public String getCode() {
        return code;
    }
}
