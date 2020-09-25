/*
 * 文件名：NodeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 树节点实体类
 * @author fanshuanghu
 * @version 1.0 2019年1月3日
 * @see NodeDto
 * @since 1.0
 */
public class NodeDto extends BaseEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3857169624215895155L;
    private String code;
    private String name;
    private String upperCode;
    
    /**
     * 获取 code
     * @return code.
     */
    public String getCode() {
        return code;
    }
    
    /**
     * 设置 code
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * 获取 name
     * @return name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置 name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取 upperCode
     * @return upperCode.
     */
    public String getUpperCode() {
        return upperCode;
    }
    
    /**
     * 设置 upperCode
     * @param upperCode upperCode
     */
    public void setUpperCode(String upperCode) {
        this.upperCode = upperCode;
    }
    
}
