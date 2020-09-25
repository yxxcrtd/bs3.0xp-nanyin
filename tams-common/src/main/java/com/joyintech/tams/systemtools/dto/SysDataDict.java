/*
 * 文件名：SysDataDict.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.systemtools.dto;

/**
 * 数据字典项
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月8日
 * @see SysDataDict
 * @since 1.0
 */
public class SysDataDict {

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 数据代码
     */
    private String itemCode;

    /**
     * 参数值
     */
    private String itemValue;

    /**
     * 排序号
     */
    private String orderNo;

    /**
     * 默认值
     */
    private String defaultValue;

    private String linkedItemCode;



    /**
	 * @Description TODO
	 * @Author      高健
	 * @Date        2019年12月25日 下午2:06:36
	 * @return linkedItemCode
	 */

	public String getLinkedItemCode() {
		return linkedItemCode;
	}

	/**
	 * @Description TODO
	 * @Author      高健
	 * @Date        2019年12月25日 下午2:06:36
	 * @param linkedItemCode linkedItemCode
	 */

	public void setLinkedItemCode(String linkedItemCode) {
		this.linkedItemCode = linkedItemCode;
	}

	/**
     * 获取 字典代码
     * 
     * @return 字典代码.
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 字典代码
     * 
     * @param dictCode 字典代码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取 数据代码
     * 
     * @return 数据代码.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 数据代码
     * 
     * @param itemCode 数据代码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 获取 参数值
     * 
     * @return 参数值.
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 设置 参数值
     * 
     * @param itemValue 参数值
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    /**
     * 获取 orderNo
     *
     * @return orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 orderNo
     *
     * @param orderNo orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取 defaultValue
     *
     * @return defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置 defaultValue
     *
     * @param defaultValue defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
