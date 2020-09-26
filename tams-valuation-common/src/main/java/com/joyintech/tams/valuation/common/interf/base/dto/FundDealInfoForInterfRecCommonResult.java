/*
 * 文件名：FundDealInfoForInterfRecCommonResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：HuangJian
 * 修改时间：2018年6月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.BokDealAccounting;
import com.joyintech.tams.common.base.entity.BokDealAccountingDetail;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.StlOrders;
import com.joyintech.tams.common.base.entity.StlOrdersExt;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.ValInterflog;
import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author HuangJian
 * @version 1.0 2018年6月27日
 * @see FundDealInfoForInterfRecCommonResult
 * @since 1.0
 */
public class FundDealInfoForInterfRecCommonResult extends BaseEntity{
    private static final long serialVersionUID = 1L;
    /**
     * 金融产品交易表
     */
    private List<TrdProductDeal> listValFinProductDeal = new ArrayList<TrdProductDeal>();;
    /**
     * 估值核算交易扩展
     */
    private List<TrdProductDealAdd> listValFinProductDealExt = new ArrayList<TrdProductDealAdd>();
    /**
     * 入账信息表
     */
    private List<BokDealAccounting> listValDealAccounting = new ArrayList<BokDealAccounting>();
    /**
     * 账务明细表
     */
    private List<BokDealAccountingDetail> listValDealAccountingDetail = new ArrayList<BokDealAccountingDetail>();
    /**
     * 交易费用明细表
     */
    private List<TrdTradeFeeDetail> listValTradeFeeDetail = new ArrayList<TrdTradeFeeDetail>();
    
    /**
     * 账务明细代码表
     */
    private List<BokDetailId> listValBokDetailId=new ArrayList<BokDetailId>();
    
    /**
     * 结算指令
     */
    private List<StlOrders> listValOrders = new ArrayList<StlOrders>();
    private List<StlOrdersExt> listValOrdersExt = new ArrayList<StlOrdersExt>();
    
    /**
     * 日志
     */
    private List<ValInterflog> listValInterflog = new ArrayList<ValInterflog>();
    
    /**
     * 交易入账
     */
    private List<InterfProductDealBookCommonDto> listInterfProductDealBookCommonDto = new ArrayList<InterfProductDealBookCommonDto>();

    /**
     * add by wujie on 20180809 start remark:非货币红利除权交易关联资管系统编号
     */
    private List<String> cqFtradeIds = new ArrayList<String>();
    
    /**
     * 读取数据数量
     */
    private int dataNum;
    
    public List<String> getCqFtradeIds() {
		return cqFtradeIds;
	}

	public void setCqFtradeIds(List<String> cqFtradeIds) {
		this.cqFtradeIds = cqFtradeIds;
	}
	/**add by wujie on 20180809 end**/


	private Map<String,Object> paramMap;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
    
    /**
     * 获取 listValFinProductDeal
     * @return listValFinProductDeal.
     */
    public List<TrdProductDeal> getListValFinProductDeal() {
        return listValFinProductDeal;
    }

    
    /**
     * 设置 listValFinProductDeal
     * @param listValFinProductDeal listValFinProductDeal
     */
    public void setListValFinProductDeal(List<TrdProductDeal> listValFinProductDeal) {
        this.listValFinProductDeal = listValFinProductDeal;
    }

    
    /**
     * 获取 listValFinProductDealExt
     * @return listValFinProductDealExt.
     */
    public List<TrdProductDealAdd> getListValFinProductDealExt() {
        return listValFinProductDealExt;
    }

    
    /**
     * 设置 listValFinProductDealExt
     * @param listValFinProductDealExt listValFinProductDealExt
     */
    public void setListValFinProductDealExt(List<TrdProductDealAdd> listValFinProductDealExt) {
        this.listValFinProductDealExt = listValFinProductDealExt;
    }

    
    /**
     * 获取 listValDealAccounting
     * @return listValDealAccounting.
     */
    public List<BokDealAccounting> getListValDealAccounting() {
        return listValDealAccounting;
    }

    
    /**
     * 设置 listValDealAccounting
     * @param listValDealAccounting listValDealAccounting
     */
    public void setListValDealAccounting(List<BokDealAccounting> listValDealAccounting) {
        this.listValDealAccounting = listValDealAccounting;
    }

    
    /**
     * 获取 listValDealAccountingDetail
     * @return listValDealAccountingDetail.
     */
    public List<BokDealAccountingDetail> getListValDealAccountingDetail() {
        return listValDealAccountingDetail;
    }

    
    /**
     * 设置 listValDealAccountingDetail
     * @param listValDealAccountingDetail listValDealAccountingDetail
     */
    public void setListValDealAccountingDetail(List<BokDealAccountingDetail> listValDealAccountingDetail) {
        this.listValDealAccountingDetail = listValDealAccountingDetail;
    }

    
    /**
     * 获取 listValTradeFeeDetail
     * @return listValTradeFeeDetail.
     */
    public List<TrdTradeFeeDetail> getListValTradeFeeDetail() {
        return listValTradeFeeDetail;
    }

    
    /**
     * 设置 listValTradeFeeDetail
     * @param listValTradeFeeDetail listValTradeFeeDetail
     */
    public void setListValTradeFeeDetail(List<TrdTradeFeeDetail> listValTradeFeeDetail) {
        this.listValTradeFeeDetail = listValTradeFeeDetail;
    }

    
    /**
     * 获取 listValBokDetailId
     * @return listValBokDetailId.
     */
    public List<BokDetailId> getListValBokDetailId() {
        return listValBokDetailId;
    }

    
    /**
     * 设置 listValBokDetailId
     * @param listValBokDetailId listValBokDetailId
     */
    public void setListValBokDetailId(List<BokDetailId> listValBokDetailId) {
        this.listValBokDetailId = listValBokDetailId;
    }

    
    /**
     * 获取 listValOrders
     * @return listValOrders.
     */
    public List<StlOrders> getListValOrders() {
        return listValOrders;
    }

    
    /**
     * 设置 listValOrders
     * @param listValOrders listValOrders
     */
    public void setListValOrders(List<StlOrders> listValOrders) {
        this.listValOrders = listValOrders;
    }

    
    /**
     * 获取 listValOrdersExt
     * @return listValOrdersExt.
     */
    public List<StlOrdersExt> getListValOrdersExt() {
        return listValOrdersExt;
    }

    
    /**
     * 设置 listValOrdersExt
     * @param listValOrdersExt listValOrdersExt
     */
    public void setListValOrdersExt(List<StlOrdersExt> listValOrdersExt) {
        this.listValOrdersExt = listValOrdersExt;
    }

    
    /**
     * 获取 listValInterflog
     * @return listValInterflog.
     */
    public List<ValInterflog> getListValInterflog() {
        return listValInterflog;
    }

    
    /**
     * 设置 listValInterflog
     * @param listValInterflog listValInterflog
     */
    public void setListValInterflog(List<ValInterflog> listValInterflog) {
        this.listValInterflog = listValInterflog;
    }

    
    /**
     * 获取 listInterfProductDealBookCommonDto
     * @return listInterfProductDealBookCommonDto.
     */
    public List<InterfProductDealBookCommonDto> getListInterfProductDealBookCommonDto() {
        return listInterfProductDealBookCommonDto;
    }

    
    /**
     * 设置 listInterfProductDealBookCommonDto
     * @param listInterfProductDealBookCommonDto listInterfProductDealBookCommonDto
     */
    public void setListInterfProductDealBookCommonDto(List<InterfProductDealBookCommonDto> listInterfProductDealBookCommonDto) {
        this.listInterfProductDealBookCommonDto = listInterfProductDealBookCommonDto;
    }

    
    /**
     * 获取 serialversionuid
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取 dataNum
     * @return dataNum.
     */
    public int getDataNum() {
        return dataNum;
    }

    /**
     * 设置 dataNum
     * @param dataNum dataNum
     */
    public void setDataNum(int dataNum) {
        this.dataNum = dataNum;
    }

    
}
