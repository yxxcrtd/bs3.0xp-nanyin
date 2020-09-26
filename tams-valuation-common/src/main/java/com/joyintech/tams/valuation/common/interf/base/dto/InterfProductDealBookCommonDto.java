/*
 * 文件名：InterfProductDealBookCommonDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年7月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;

/**
 * 交易入帐信息公共处理
 * 〈功能详细描述〉
 * @author wangwei1
 * @version 1.0 2018年7月3日
 * @see InterfProductDealBookCommonDto
 * @since 1.0
 */
public class InterfProductDealBookCommonDto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 9172165806335381868L;
    /**
     * 入账信息Dto
     */
    private BokDealAccounting bokDealAccounting;
    /**
     * 入账账务明细Dto
     */
    private  BokDealAccountingDetail bokDealAccountingDetail;
    /**
     * 账务明细代码表Dto  
     */
    private  BokDetailId bokDetailId;
    
    /**
     * add by wujie on 20181017 start remark:交易入账返回账套信息List
     */
    /**
     * 账套信息List
     */
    private List<ValBooksetInfoDto> valBooksetInfoDtoList;
    
    /**
     * 入账信息List
     */
    private List<BokDealAccounting> bokDealAccountingList;
    /**
     * 入账账务明细List
     */
    private  List<BokDealAccountingDetail> bokDealAccountingDetailList;
    /**
     * 账务明细代码表List 
     */
    private  List<BokDetailId> bokDetailIdList;
    /**add by wujie on 20181017 end remark:交易入账返回账套信息List**/
    
    
    /**
     * 获取 bokDealAccounting
     * @return bokDealAccounting.
     */
    public BokDealAccounting getBokDealAccounting() {
        return bokDealAccounting;
    }
    
    /**
     * 设置 bokDealAccounting
     * @param bokDealAccounting bokDealAccounting
     */
    public void setBokDealAccounting(BokDealAccounting bokDealAccounting) {
        this.bokDealAccounting = bokDealAccounting;
    }
    
    /**
     * 获取 bokDealAccountingDetail
     * @return bokDealAccountingDetail.
     */
    public BokDealAccountingDetail getBokDealAccountingDetail() {
        return bokDealAccountingDetail;
    }
    
    /**
     * 设置 bokDealAccountingDetail
     * @param bokDealAccountingDetail bokDealAccountingDetail
     */
    public void setBokDealAccountingDetail(BokDealAccountingDetail bokDealAccountingDetail) {
        this.bokDealAccountingDetail = bokDealAccountingDetail;
    }
    
    /**
     * 获取 bokDetailId
     * @return bokDetailId.
     */
    public BokDetailId getBokDetailId() {
        return bokDetailId;
    }
    
    /**
     * 设置 bokDetailId
     * @param bokDetailId bokDetailId
     */
    public void setBokDetailId(BokDetailId bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

	/**
	 * @return the valBooksetInfoDtoList
	 */
	public List<ValBooksetInfoDto> getValBooksetInfoDtoList() {
		return valBooksetInfoDtoList;
	}

	/**
	 * @param valBooksetInfoDtoList the valBooksetInfoDtoList to set
	 */
	public void setValBooksetInfoDtoList(List<ValBooksetInfoDto> valBooksetInfoDtoList) {
		this.valBooksetInfoDtoList = valBooksetInfoDtoList;
	}

	/**
	 * @return the bokDealAccountingList
	 */
	public List<BokDealAccounting> getBokDealAccountingList() {
		return bokDealAccountingList;
	}

	/**
	 * @param bokDealAccountingList the bokDealAccountingList to set
	 */
	public void setBokDealAccountingList(List<BokDealAccounting> bokDealAccountingList) {
		this.bokDealAccountingList = bokDealAccountingList;
	}

	/**
	 * @return the bokDealAccountingDetailList
	 */
	public List<BokDealAccountingDetail> getBokDealAccountingDetailList() {
		return bokDealAccountingDetailList;
	}

	/**
	 * @param bokDealAccountingDetailList the bokDealAccountingDetailList to set
	 */
	public void setBokDealAccountingDetailList(List<BokDealAccountingDetail> bokDealAccountingDetailList) {
		this.bokDealAccountingDetailList = bokDealAccountingDetailList;
	}

	/**
	 * @return the bokDetailIdList
	 */
	public List<BokDetailId> getBokDetailIdList() {
		return bokDetailIdList;
	}

	/**
	 * @param bokDetailIdList the bokDetailIdList to set
	 */
	public void setBokDetailIdList(List<BokDetailId> bokDetailIdList) {
		this.bokDetailIdList = bokDetailIdList;
	}
    
	/**
	 * 核算主体类型
	 * add by wujie on 20190104
	 */
	private String acctSubjectType;

	/**
	 * 账套名称
	 * add by wujie no 20190104
	 */
	private String booksetName;

	/**
	 * @return the acctSubjectType
	 */
	public String getAcctSubjectType() {
		return acctSubjectType;
	}

	/**
	 * @param acctSubjectType the acctSubjectType to set
	 */
	public void setAcctSubjectType(String acctSubjectType) {
		this.acctSubjectType = acctSubjectType;
	}

	public String getBooksetName() {
		return booksetName;
	}

	public void setBooksetName(String booksetName) {
		this.booksetName = booksetName;
	}
}
