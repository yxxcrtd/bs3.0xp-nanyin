/*
 * 文件名：bussBenchMarkLayeredInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年6月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.component.business.prod.validator.BussBenchMarkLayeredInfoValidator;

/**
 * 分档业基准dto
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年6月22日
 * @see bussBenchMarkLayeredInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussBenchMarkLayeredInfoValidator.class)
public class BussBenchMarkLayeredInfoDto extends BussBenchMarkInfoDto {
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 产品款型
     */
    private String prodStyle;
    
    /**
     * 产品分档款型业基准
     */
    private List<BussBenchMarkLayeredInfoDto> bussBenchMarkLayeredInfoDtoList;
//    /**
//     * 费用列表(浮动管理费无，单多固定期限有误，无实际作用)
//     */
//    private List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList;
    /**
     * 投资周期
     */
    private String investmentCycle;
    
    private BigDecimal floatRate;    
    
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


//	public List<BussFinProductFeeInfoDto> getBussFinProductFeeInfoDtoList() {
//		return bussFinProductFeeInfoDtoList;
//	}
//
//
//	public void setBussFinProductFeeInfoDtoList(List<BussFinProductFeeInfoDto> bussFinProductFeeInfoDtoList) {
//		this.bussFinProductFeeInfoDtoList = bussFinProductFeeInfoDtoList;
//	}
    
    
}
