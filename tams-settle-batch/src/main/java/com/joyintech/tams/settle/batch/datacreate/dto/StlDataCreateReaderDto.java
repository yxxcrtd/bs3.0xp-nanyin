package com.joyintech.tams.settle.batch.datacreate.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 金融产品交易临时表.交易方向是买入、卖出、正回购、逆回购的检索结果DTO
 * @author 陈卓
 * @date 2020/11/24
 * @see StlDataCreateReaderDto
 * @since 1.0
 */
public class StlDataCreateReaderDto extends NeedOperateEntity {

    /**
     * StlDataCreateDto List
     */
	private List<StlDataCreateDto> stlDataCreateDtoList;

    /**
     * 执行日期
     */
    private String tradeDate;

    public List<StlDataCreateDto> getStlDataCreateDtoList() {
		return stlDataCreateDtoList;
	}

	public void setStlDataCreateDtoList(List<StlDataCreateDto> stlDataCreateDtoList) {
		this.stlDataCreateDtoList = stlDataCreateDtoList;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
}
