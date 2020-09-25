/*
 * 文件名：bussBenchMarkLayeredInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年6月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年6月22日
 * @see bussBenchMarkLayeredInfoDto
 * @since 1.0
 */
public class BussBenchMarkLayeredInfoDto extends BussBenchMarkInfoDto {
    
    /**
     * 产品款型
     */
    private String prodStyle;
    /**
     * 投资周期
     */
    private String investmentCycle;
    
    private BigDecimal floatRate;    
    /**
     * 产品分档款型业基准
     */
    private List<BussBenchMarkLayeredInfoDto> bussBenchMarkLayeredInfoDtoList;
    
    
    public BigDecimal getFloatRate() {
		return floatRate;
	}


	public void setFloatRate(BigDecimal floatRate) {
		this.floatRate = floatRate;
	}


	/**
     * 获取 prodStyle
     * @return prodStyle.
     */
    public String getProdStyle() {
        return prodStyle;
    }

    
    /**
     * 设置 prodStyle
     * @param prodStyle prodStyle
     */
    public void setProdStyle(String prodStyle) {
        this.prodStyle = prodStyle;
    }


    
    /**
     * 获取 bussBenchMarkLayeredInfoDtoList
     * @return bussBenchMarkLayeredInfoDtoList.
     */
    public List<BussBenchMarkLayeredInfoDto> getBussBenchMarkLayeredInfoDtoList() {
        return bussBenchMarkLayeredInfoDtoList;
    }


    
    /**
     * 设置 bussBenchMarkLayeredInfoDtoList
     * @param bussBenchMarkLayeredInfoDtoList bussBenchMarkLayeredInfoDtoList
     */
    public void setBussBenchMarkLayeredInfoDtoList(List<BussBenchMarkLayeredInfoDto> bussBenchMarkLayeredInfoDtoList) {
        this.bussBenchMarkLayeredInfoDtoList = bussBenchMarkLayeredInfoDtoList;
    }


	public String getInvestmentCycle() {
		return investmentCycle;
	}


	public void setInvestmentCycle(String investmentCycle) {
		this.investmentCycle = investmentCycle;
	}
    
    
}
