/*
 * 文件名：ValBondTargetAssetDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.assets.bond.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.hpsf.Decimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.valuation.component.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.service.valuation.component.element.dto.FinInterestFactorDto;
import com.joyintech.tams.common.base.entity.FinProductConvertible;
//import com.joyintech.tams.invest.common.dto.MstFileInputParameter;

/**
 * 债券标的信息DTO
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see ValBondTargetAssetDto
 * @since 1.0
 */
public class ValBondTargetAssetDto extends FinProduct{

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选择数据
     */
    private List<ValBondTargetAssetDto> selectedItem;

    /**
     * 要素id
     */
    private String elementId;
    
    /**
     * 债券代码
     */
    private String bondId;
    
    /**
     * 债券名称
     */
    private String bondName;
	/**
     * 息票品种
     */
    private String couponType;
    /**
     * 债券一级分类  add by zhangquan 20200114
     */
    private String bondType1;
    
    /**
     * 债券二级分类  add by zhangquan 20200114
     */
    private String bondType2;
    /**
     * 发行价格
     */
    private Decimal price;
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 分类4（wind一级类型）
     */
    private String type4;

    /**
     * 分类5（wind二级类型）
     */
    private String type5;
    /* add by zhangpuyu 2019/10/24 start */
    /**
	 * 交易对手类型---分行、通道
	 */
	private String counterType;
	public String getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
    /**
	 * 投后管理机构
	 */
	private String postOrg;
	public String getPostOrg() {
		return postOrg;
	}

	public void setPostOrg(String postOrg) {
		this.postOrg = postOrg;
	}
	/* add by zhangpuyu 2019/10/24 end */

    /**added by wangguiqi on 2019/7/19 -start**/
    /**
     * 分类9（资产二级分类）
     */
    private String type9;

    /**
     * 分类10（资产三级分类）
     */
    private String type10;

    /**
     * 分类11（资产四级分类）
     */
    private String type11;

    /**
     * 分类12（资产五级分类）
     */
    private String type12;
    /**added by wangguiqi on 2019/7/19 -end**/
    
    /**
     * 是否次级债
     */
    private String isSecBond;
    
    /**
     * 承销商
     */
    private String underWriterId;
    /**
     * 支付频率
     */
    private String payCycle;
    
    /**
     * 发行人
     */
    private String issuerName;
    /**
     * 交易日历名称
     */
    private String calendarName;

    /**
     * 是否允许覆盖
     */
    private String isAutoUpdate;
    
    /**
     * 利率类型
     */
    private String rateType;
    
    /**
     * 票面利率
     */
    private BigDecimal rate;
    
    /**
     * 计息基础
     */
    private String basis;
    
    /***
     * 系数
     */
    private BigDecimal coefficient;
    
    /**
     * 利差
     */
    private BigDecimal spreadRate;
    
    /**
     * 基准编号
     */
    private String indexId;
    
    /**
     * 机构类型(企业性质)
     */
    private String partyType2;
    /**
     * 授信额度代码
     */
    private String seqno;

    /**
     * 授信配置代码
     */
    private String creditMstId;

    /**
     * 授信客户编号（机构授信）/金融产品代码（资产授信）/资产系列代码（资产系列授信）
     */
    private String customerId;

    /**
     * 批复流水号
     */
    private String pflsh;
    
    /**
     * 已占用额度，初始值为0
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal hapCreditAmt;

    /**
     * 实际授信主体
     */
    private String actualCreditProvid;
    /**
     * 是否占用主体授信
     */
    private String creditFlag;
    /**
     * 行权日
     */
    private Date exerciseDate;

    /**
     * 转债信息
     */
    private FinProductConvertible finProductConvertible;

    /**
     * 正股市场代码
     */
    private String conFinprodMarketId;

    /**
     * 资产/负债类别
     */
    private String repAssetType;

    /**
     * 获取 creditFlag
     * @return creditFlag.
     */
    public String getCreditFlag() {
        return creditFlag;
    }

    
    /**
     * 设置 creditFlag
     * @param creditFlag creditFlag
     */
    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }
    
    // 一体化对应etliu
    /**
     * 面值
     */
    private BigDecimal faceValue;
    /**
     * 主体评级结果
     */
    private String ztGradeResult;
    /**
     * 主体评级机构
     */
    private String ztGradeOrgId;
    /**
     * 债项评级结果
     */
    private String zxGradeResult;
    /**
     * 债项评级结果
     */
    private String zxGradeOrgId;

    /**
     * 是否城投债
     */
    private String isCityBond;

    /**
     * 城投债级别
     */
    private String cityBondLev;

    private String industry;

    private String industry2;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry2() {
        return industry2;
    }

    public void setIndustry2(String industry2) {
        this.industry2 = industry2;
    }

    /**
     * 获取 ztGradeResult
     * @return ztGradeResult.
     */
    public String getZtGradeResult() {
        return ztGradeResult;
    }


    
    /**
     * 设置 ztGradeResult
     * @param ztGradeResult ztGradeResult
     */
    public void setZtGradeResult(String ztGradeResult) {
        this.ztGradeResult = ztGradeResult;
    }


    
    /**
     * 获取 ztGradeOrgId
     * @return ztGradeOrgId.
     */
    public String getZtGradeOrgId() {
        return ztGradeOrgId;
    }


    
    /**
     * 设置 ztGradeOrgId
     * @param ztGradeOrgId ztGradeOrgId
     */
    public void setZtGradeOrgId(String ztGradeOrgId) {
        this.ztGradeOrgId = ztGradeOrgId;
    }


    
    /**
     * 获取 zxGradeResult
     * @return zxGradeResult.
     */
    public String getZxGradeResult() {
        return zxGradeResult;
    }


    
    /**
     * 设置 zxGradeResult
     * @param zxGradeResult zxGradeResult
     */
    public void setZxGradeResult(String zxGradeResult) {
        this.zxGradeResult = zxGradeResult;
    }


    
    /**
     * 获取 zxGradeOrgId
     * @return zxGradeOrgId.
     */
    public String getZxGradeOrgId() {
        return zxGradeOrgId;
    }


    
    /**
     * 设置 zxGradeOrgId
     * @param zxGradeOrgId zxGradeOrgId
     */
    public void setZxGradeOrgId(String zxGradeOrgId) {
        this.zxGradeOrgId = zxGradeOrgId;
    }


    /**
     * 获取 faceValue
     * @return faceValue.
     */
    public BigDecimal getFaceValue() {
        return faceValue;
    }
    
    /**
     * 设置 faceValue
     * @param faceValue faceValue
     */
    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }
    
    /**
     * 基础组件-标的金融产品
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;
    
    /**
     * 计算规者扩展--单独针对平安
     * 计息要素DTO
     */
    private FinInterestFactorDto finInterestFactorDto;
    
    /**
     * 附件
     */
//    private MstFileInputParameter mstFileInputParameter;
    
    /**
     * 获取 finInterestFactorDto
     * @return finInterestFactorDto.
     */
    public FinInterestFactorDto getFinInterestFactorDto() {
        return finInterestFactorDto;
    }





    
    /**
     * 设置 finInterestFactorDto
     * @param finInterestFactorDto finInterestFactorDto
     */
    public void setFinInterestFactorDto(FinInterestFactorDto finInterestFactorDto) {
        this.finInterestFactorDto = finInterestFactorDto;
    }





    /**
     * 获取 baseFinProductBaseInfoList
     * @return baseFinProductBaseInfoList.
     */
    public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
        return baseFinProductBaseInfoList;
    }




    
    /**
     * 设置 baseFinProductBaseInfoList
     * @param baseFinProductBaseInfoList baseFinProductBaseInfoList
     */
    public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
        this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
    }




    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }



    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }



    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<ValBondTargetAssetDto> getSelectedItem() {
        return selectedItem;
    }



    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<ValBondTargetAssetDto> selectedItem) {
        this.selectedItem = selectedItem;
    }



    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }



    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    

    
    /**
     * 获取 isAutoUpdate
     * @return isAutoUpdate.
     */
    public String getIsAutoUpdate() {
        return isAutoUpdate;
    }






    
    /**
     * 设置 isAutoUpdate
     * @param isAutoUpdate isAutoUpdate
     */
    public void setIsAutoUpdate(String isAutoUpdate) {
        this.isAutoUpdate = isAutoUpdate;
    }


    /**
     * 获取 type4
     * @return type4.
     */
    public String getType4() {
        return type4;
    }

    
    /**
     * 设置 type4
     * @param type4 type4
     */
    public void setType4(String type4) {
        this.type4 = type4;
    }

    
    /**
     * 获取 type5
     * @return type5.
     */
    public String getType5() {
        return type5;
    }

    
    /**
     * 设置 type5
     * @param type5 type5
     */
    public void setType5(String type5) {
        this.type5 = type5;
    }

    /**added by wangguiqi on 2019/7/19 -start**/
    /**
     * 获取 type9
     * @return type9.
     */
    public String getType9() {
		return type9;
	}


    /**
     * 设置 type9
     * @param type9
     */
	public void setType9(String type9) {
		this.type9 = type9;
	}

	/**
     * 获取 type10
     * @return type10.
     */
	public String getType10() {
		return type10;
	}


    /**
     * 设置 type10
     * @param type10
     */
	public void setType10(String type10) {
		this.type10 = type10;
	}

	/**
     * 获取 type11
     * @return type11.
     */
	public String getType11() {
		return type11;
	}


    /**
     * 设置 type11
     * @param type11
     */
	public void setType11(String type11) {
		this.type11 = type11;
	}


    /**
     * 获取 type12
     * @return type12.
     */
	public String getType12() {
		return type12;
	}

	/**
     * 设置 type12
     * @param type12
     */
	public void setType12(String type12) {
		this.type12 = type12;
	}
	/**added by wangguiqi on 2019/7/19 -end**/

    /**
     * 获取 isSecBond
     * @return isSecBond.
     */
    public String getIsSecBond() {
        return isSecBond;
    }

    
    /**
     * 设置 isSecBond
     * @param isSecBond isSecBond
     */
    public void setIsSecBond(String isSecBond) {
        this.isSecBond = isSecBond;
    }

    
    /**
     * 获取 underWriterId
     * @return underWriterId.
     */
    public String getUnderWriterId() {
        return underWriterId;
    }

    
    /**
     * 设置 underWriterId
     * @param underWriterId underWriterId
     */
    public void setUnderWriterId(String underWriterId) {
        this.underWriterId = underWriterId;
    }
    
    /**
     * 金融产品代码
     */
    @Override
    public String getFinprodId(){
        return super.getFinprodId();
    }

    
    /**
     * 获取 calendarName
     * @return calendarName.
     */
    public String getCalendarName() {
        return calendarName;
    }






    
    /**
     * 设置 calendarName
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }






    
    /**
     * 获取 issuerName
     * @return issuerName.
     */
    public String getIssuerName() {
        return issuerName;
    }






    
    /**
     * 设置 issuerName
     * @param issuerName issuerName
     */
    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }






    
 /*   *//**
     * 获取 mstFileInputParameter
     * @return mstFileInputParameter.
     *//*
    public MstFileInputParameter getMstFileInputParameter() {
        return mstFileInputParameter;
    }






    
    *//**
     * 设置 mstFileInputParameter
     * @param mstFileInputParameter mstFileInputParameter
     *//*
    public void setMstFileInputParameter(MstFileInputParameter mstFileInputParameter) {
        this.mstFileInputParameter = mstFileInputParameter;
    }*/







    
    /**
     * 获取 rateType
     * @return rateType.
     */
    public String getRateType() {
        return rateType;
    }







    
    /**
     * 设置 rateType
     * @param rateType rateType
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }







    
    /**
     * 获取 rate
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }







    
    /**
     * 设置 rate
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }







    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }







    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }







    
    /**
     * 获取 coefficient
     * @return coefficient.
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }







    
    /**
     * 设置 coefficient
     * @param coefficient coefficient
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }







    
    /**
     * 获取 spreadRate
     * @return spreadRate.
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }







    
    /**
     * 设置 spreadRate
     * @param spreadRate spreadRate
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }







    
    /**
     * 获取 partyType2
     * @return partyType2.
     */
    public String getPartyType2() {
        return partyType2;
    }







    
    /**
     * 设置 partyType2
     * @param partyType2 partyType2
     */
    public void setPartyType2(String partyType2) {
        this.partyType2 = partyType2;
    }







    
    /**
     * 获取 indexId
     * @return indexId.
     */
    public String getIndexId() {
        return indexId;
    }







    
    /**
     * 设置 indexId
     * @param indexId indexId
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }


    
    /**
     * 获取 payCycle
     * @return payCycle.
     */
    public String getPayCycle() {
        return payCycle;
    }







    
    /**
     * 设置 payCycle
     * @param payCycle payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }







    
    /**
     * 获取 seqno
     * @return seqno.
     */
    public String getSeqno() {
        return seqno;
    }







    
    /**
     * 设置 seqno
     * @param seqno seqno
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }







    
    /**
     * 获取 creditMstId
     * @return creditMstId.
     */
    public String getCreditMstId() {
        return creditMstId;
    }







    
    /**
     * 设置 creditMstId
     * @param creditMstId creditMstId
     */
    public void setCreditMstId(String creditMstId) {
        this.creditMstId = creditMstId;
    }







    
    /**
     * 获取 customerId
     * @return customerId.
     */
    public String getCustomerId() {
        return customerId;
    }







    
    /**
     * 设置 customerId
     * @param customerId customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }







    
    /**
     * 获取 pflsh
     * @return pflsh.
     */
    public String getPflsh() {
        return pflsh;
    }







    
    /**
     * 设置 pflsh
     * @param pflsh pflsh
     */
    public void setPflsh(String pflsh) {
        this.pflsh = pflsh;
    }







    
    /**
     * 获取 hapCreditAmt
     * @return hapCreditAmt.
     */
    public BigDecimal getHapCreditAmt() {
        return hapCreditAmt;
    }







    
    /**
     * 设置 hapCreditAmt
     * @param hapCreditAmt hapCreditAmt
     */
    public void setHapCreditAmt(BigDecimal hapCreditAmt) {
        this.hapCreditAmt = hapCreditAmt;
    }







    
    /**
     * 获取 actualCreditProvid
     * @return actualCreditProvid.
     */
    public String getActualCreditProvid() {
        return actualCreditProvid;
    }







    
    /**
     * 设置 actualCreditProvid
     * @param actualCreditProvid actualCreditProvid
     */
    public void setActualCreditProvid(String actualCreditProvid) {
        this.actualCreditProvid = actualCreditProvid;
    }


    
    /**
     * 获取 exerciseDate
     * @return exerciseDate.
     */
    public Date getExerciseDate() {
        return exerciseDate;
    }


    
    /**
     * 设置 exerciseDate
     * @param exerciseDate exerciseDate
     */
    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getIsCityBond() {
        return isCityBond;
    }

    public void setIsCityBond(String isCityBond) {
        this.isCityBond = isCityBond;
    }

    public String getCityBondLev() {
        return cityBondLev;
    }

    public void setCityBondLev(String cityBondLev) {
        this.cityBondLev = cityBondLev;
    }

    public FinProductConvertible getFinProductConvertible() {
        return finProductConvertible;
    }

    public void setFinProductConvertible(FinProductConvertible finProductConvertible) {
        this.finProductConvertible = finProductConvertible;
    }

    public String getConFinprodMarketId() {
        return conFinprodMarketId;
    }

    public void setConFinprodMarketId(String conFinprodMarketId) {
        this.conFinprodMarketId = conFinprodMarketId;
    }

    public String getRepAssetType() {
        return repAssetType;
    }

    public void setRepAssetType(String repAssetType) {
        this.repAssetType = repAssetType;
    }
    
    public String getBondId() {
		return bondId;
	}

	public void setBondId(String bondId) {
		this.bondId = bondId;
	}

	public String getBondName() {
		return bondName;
	}

	public void setBondName(String bondName) {
		this.bondName = bondName;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getBondType1() {
		return bondType1;
	}

	public void setBondType1(String bondType1) {
		this.bondType1 = bondType1;
	}

	public String getBondType2() {
		return bondType2;
	}

	public void setBondType2(String bondType2) {
		this.bondType2 = bondType2;
	}

	public Decimal getPrice() {
		return price;
	}

	public void setPrice(Decimal price) {
		this.price = price;
	}
}
