/*
 * 文件名：InfoAreaDto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月20日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.home.dto;


/**
 * 存储信息表区域查询的结果Dto(可批量导出模板区域复用)
 * @author dingzhipeng
 * @version 1.0 2017年9月20日
 * @see InfoAreaDto
 * @since 1.0
 */
public class InfoAreaDto {
    /**
     * 统计的数量
     */
    private String countNum;
    /**
     * 统计的数据的种类
     */
    private String countType;
    
    /**
     * 获取 countNum
     * @return countNum.
     */
    public String getCountNum() {
        return countNum;
    }
    
    /**
     * 设置 countNum
     * @param countNum countNum
     */
    public void setCountNum(String countNum) {
        this.countNum = countNum;
    }
    
    /**
     * 获取 countType
     * @return countType.
     */
    public String getCountType() {
        return countType;
    }
    
    /**
     * 设置 countType
     * @param countType countType
     */
    public void setCountType(String countType) {
        this.countType = countType;
    }
}
