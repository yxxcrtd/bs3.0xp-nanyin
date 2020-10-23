/*
 * 文件名：ValBokClawbackInfoQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：李俊
 * 修改时间：2019年4月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.framework.util.JsonUtils;


/**
 * 回补信息查询
 * 
 * @author 李俊
 * @version 1.0 2019年4月22日
 * @see FinProductAnapleroticInfoDto
 * @since 1.0
 */
public class FinProductAnapleroticInfoDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 产品简称
     */
    private String finprodAbbr;
    /**
     * 回补id
     */
    private String anapleroticId;
    
    /**
     * 操作类型
     */
    private String operateType;
        
    /**
     * 产品代码
     */
    private String finprodId; 
    
    /**
     * 市场代码
     */
    private String finprodMarketId;
    /**
     * 生效日
     */
    private Date effDate;
    /**
     * 原生效日
     */
    private Date oriEffDate;
    
    /**
     * 回补形式（对应估值回补方式）
     */
    private String anapleroticType;
    
    /**
     * 回补方式（对应估值回补规则）
     */
    private String anapleroticRule;
    
    /**
     * 回补比例
     */
    private BigDecimal anapleroticRatio;

    /**
     * 计费基数
     */
    private String anapleroticBasic;
    
    /**
     * 资产净值日
     */
    private String astVluDateType;
    
    /**
     * 回补规则(计提频率)
     */
    private String anapleroticFrequency;
    
    /**
     * 首次回补日
     */
    private Date firstAnapleroticDate;

    /**
     * 周期营业日准则
     */
    private String cycleTdayRule;
   
    /**
     * 首次回补周期结束日
     */
    private Date firstAnapleroticCycleEndDate;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 回补计划列表
     */
    @Valid
    private List<FinProductAnapleroticPlanDto> finProductAnapleroticPlanList;
    /**
     * thisList
     */
    @Valid
    private List<FinProductAnapleroticInfoDto> finProductAnapleroticTabInfoDtoList;

    @Valid
    private FinProductAnapleroticInfoDto finProductAnapleroticInfoDto;
    /**
     * 回补结束日
     */
    private Date anapleroticEndDate;
    
    /**
     * 回补实际结束日
     */
    private Date mDate;
    
    /**
     * 交易日历id
     */
    private String calendarId;
    //以下为回补增加业绩基准要素 start 2019-12-02
    /**
     * 业绩基准组件
     */
    @Valid
    private BussBenchMarkInfoDto bussBenchMarkInfoDto;
    /**
     * 利率类型
     */
    private String rateType;

    /**
     * 初始利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 基准编号
     */
    private String indexId;

    /**
     * 基准来源，复合基准、利率行情、指数行情、曲线
     */
    private String indexSource;

    /**
     * 系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 重置规则，期初、期末等
     */
    private String resetType;
    //以下为回补增加业绩基准要素 start 2019-12-02
    //适用款型
    private String prodStyle;
    /**
     * 子产品代码
     */
    private String sFinprodId;
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 effDate
     * @return effDate.
     */
    public Date getEffDate() {
        return effDate;
    }

    
    /**
     * 设置 effDate
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    
    /**
     * 获取 anapleroticType
     * @return anapleroticType.
     */
    public String getAnapleroticType() {
        return anapleroticType;
    }
    /**
     * 获取 anapleroticTypeStr
     * @return anapleroticType.
     */
    public String getAnapleroticTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.回补方式,this.getAnapleroticType());
    }

    /**
     * 设置 anapleroticType
     * @param anapleroticType anapleroticType
     */
    public void setAnapleroticType(String anapleroticType) {
        this.anapleroticType = anapleroticType;
    }

    
    /**
     * 获取 anapleroticRule
     * @return anapleroticRule.
     */
    public String getAnapleroticRule() {
        return anapleroticRule;
    }
    
    /**
     * 获取 anapleroticRuleStr
     * @return anapleroticRule.
     */
    public String getAnapleroticRuleStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.回补规则,this.getAnapleroticRule());
    }

    
    /**
     * 设置 anapleroticRule
     * @param anapleroticRule anapleroticRule
     */
    public void setAnapleroticRule(String anapleroticRule) {
        this.anapleroticRule = anapleroticRule;
    }

    
    /**
     * 获取 anapleroticRatio
     * @return anapleroticRatio.
     */
    public BigDecimal getAnapleroticRatio() {
        return anapleroticRatio;
    }

    
    /**
     * 设置 anapleroticRatio
     * @param anapleroticRatio anapleroticRatio
     */
    public void setAnapleroticRatio(BigDecimal anapleroticRatio) {
        this.anapleroticRatio = anapleroticRatio;
    }

    
    /**
     * 获取 anapleroticBasic
     * @return anapleroticBasic.
     */
    public String getAnapleroticBasic() {
        return anapleroticBasic;
    }

    
    /**
     * 设置 anapleroticBasic
     * @param anapleroticBasic anapleroticBasic
     */
    public void setAnapleroticBasic(String anapleroticBasic) {
        this.anapleroticBasic = anapleroticBasic;
    }

    
    /**
     * 获取 astVluDateType
     * @return astVluDateType.
     */
    public String getAstVluDateType() {
        return astVluDateType;
    }

    
    /**
     * 设置 astVluDateType
     * @param astVluDateType astVluDateType
     */
    public void setAstVluDateType(String astVluDateType) {
        this.astVluDateType = astVluDateType;
    }

    
    /**
     * 获取 anapleroticFrequency
     * @return anapleroticFrequency.
     */
    public String getAnapleroticFrequency() {
        return anapleroticFrequency;
    }
    
    /**
     * 获取 anapleroticFrequencyStr
     * @return anapleroticFrequency.
     */
    public String getAnapleroticFrequencyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.频率,this.getAnapleroticFrequency());
    }

    
    /**
     * 设置 anapleroticFrequency
     * @param anapleroticFrequency anapleroticFrequency
     */
    public void setAnapleroticFrequency(String anapleroticFrequency) {
        this.anapleroticFrequency = anapleroticFrequency;
    }

    
    /**
     * 获取 firstAnapleroticDate
     * @return firstAnapleroticDate.
     */
    public Date getFirstAnapleroticDate() {
        return firstAnapleroticDate;
    }

    
    /**
     * 设置 firstAnapleroticDate
     * @param firstAnapleroticDate firstAnapleroticDate
     */
    public void setFirstAnapleroticDate(Date firstAnapleroticDate) {
        this.firstAnapleroticDate = firstAnapleroticDate;
    }

    
    /**
     * 获取 cycleTdayRule
     * @return cycleTdayRule.
     */
    public String getCycleTdayRule() {
        return cycleTdayRule;
    }
    
    /**
     * 获取 cycleTdayRuleStr
     * @return cycleTdayRule.
     */
    public String getCycleTdayRuleStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.营业日准则,this.getCycleTdayRule());
    }

    
    /**
     * 设置 cycleTdayRule
     * @param cycleTdayRule cycleTdayRule
     */
    public void setCycleTdayRule(String cycleTdayRule) {
        this.cycleTdayRule = cycleTdayRule;
    }

    
    /**
     * 获取 firstAnapleroticCycleEndDate
     * @return firstAnapleroticCycleEndDate.
     */
    public Date getFirstAnapleroticCycleEndDate() {
        return firstAnapleroticCycleEndDate;
    }

    
    /**
     * 设置 firstAnapleroticCycleEndDate
     * @param firstAnapleroticCycleEndDate firstAnapleroticCycleEndDate
     */
    public void setFirstAnapleroticCycleEndDate(Date firstAnapleroticCycleEndDate) {
        this.firstAnapleroticCycleEndDate = firstAnapleroticCycleEndDate;
    }

    
    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * 获取 anapleroticEndDate
     * @return anapleroticEndDate.
     */
    public Date getAnapleroticEndDate() {
        return anapleroticEndDate;
    }

    
    /**
     * 设置 anapleroticEndDate
     * @param anapleroticEndDate anapleroticEndDate
     */
    public void setAnapleroticEndDate(Date anapleroticEndDate) {
        this.anapleroticEndDate = anapleroticEndDate;
    }
    
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 mDate
     * @return mDate.
     */
    public Date getmDate() {
        return mDate;
    }

    /**
     * 设置 mDate
     * @param mDate mDate
     */
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
    

    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }
    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }


    
    /**
     * 获取 finProductAnapleroticPlanList
     * @return finProductAnapleroticPlanList.
     */
    public List<FinProductAnapleroticPlanDto> getFinProductAnapleroticPlanList() {
        return finProductAnapleroticPlanList;
    }


    
    /**
     * 设置 finProductAnapleroticPlanList
     * @param finProductAnapleroticPlanList finProductAnapleroticPlanList
     */
    public void setFinProductAnapleroticPlanList(List<FinProductAnapleroticPlanDto> finProductAnapleroticPlanList) {
        this.finProductAnapleroticPlanList = finProductAnapleroticPlanList;
    }

    
    /**
     * 获取 finProductAnapleroticTabInfoDtoList
     * @return finProductAnapleroticTabInfoDtoList.
     */
    public List<FinProductAnapleroticInfoDto> getFinProductAnapleroticTabInfoDtoList() {
        return finProductAnapleroticTabInfoDtoList;
    }


    
    /**
     * 设置 finProductAnapleroticTabInfoDtoList
     * @param finProductAnapleroticTabInfoDtoList finProductAnapleroticTabInfoDtoList
     */
    public void setFinProductAnapleroticTabInfoDtoList(List<FinProductAnapleroticInfoDto> finProductAnapleroticTabInfoDtoList) {
        this.finProductAnapleroticTabInfoDtoList = finProductAnapleroticTabInfoDtoList;
    }


    /**
     * 获取 anapleroticId
     * @return anapleroticId.
     */
    public String getAnapleroticId() {
        return anapleroticId;
    }


    
    /**
     * 设置 anapleroticId
     * @param anapleroticId anapleroticId
     */
    public void setAnapleroticId(String anapleroticId) {
        this.anapleroticId = anapleroticId;
    }


    
    /**
     * 获取 finProductAnapleroticInfoDto
     * @return finProductAnapleroticInfoDto.
     */
    public FinProductAnapleroticInfoDto getFinProductAnapleroticInfoDto() {
        return finProductAnapleroticInfoDto;
    }


    
    /**
     * 设置 finProductAnapleroticInfoDto
     * @param finProductAnapleroticInfoDto finProductAnapleroticInfoDto
     */
    public void setFinProductAnapleroticInfoDto(FinProductAnapleroticInfoDto finProductAnapleroticInfoDto) {
        this.finProductAnapleroticInfoDto = finProductAnapleroticInfoDto;
    }

    public String getFinProductAnapleroticInfoDtoStr(){
        if( null == this.getFinProductAnapleroticInfoDto()){
            return "";
        }
        return JsonUtils.object2Json(this.getFinProductAnapleroticInfoDto());
    }

    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }


    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }


    
    /**
     * 获取 oriEffDate
     * @return oriEffDate.
     */
    public Date getOriEffDate() {
        return oriEffDate;
    }

    /**
     * 设置 oriEffDate
     * @param oriEffDate oriEffDate
     */
    public void setOriEffDate(Date oriEffDate) {
        this.oriEffDate = oriEffDate;
    }
   /**
    * 是否展示生成计划
    */
    private String isList;

	public String getIsList() {
		return isList;
	}
	
	
	public void setIsList(String isList) {
		this.isList = isList;
	}
	 /**
     * 是否回补
     */
    private String isAnaplerotic;
    
    
    /**
     * 获取 isAnaplerotic
     * @return isAnaplerotic.
     */
    public String getIsAnaplerotic() {
        return isAnaplerotic;
    }

    /**
     * 设置 isAnaplerotic
     * @param isAnaplerotic isAnaplerotic
     */
    public void setIsAnaplerotic(String isAnaplerotic) {
        this.isAnaplerotic = isAnaplerotic;
    }
    
    /**
     * 是否生成回补计划
     */
    private String isAnapleroticList;

	public String getIsAnapleroticList() {
		return isAnapleroticList;
	}


	public void setIsAnapleroticList(String isAnapleroticList) {
		this.isAnapleroticList = isAnapleroticList;
	}
    /**
     * 首次回补周期开始日
     */
    private Date firstAnapleroticCycleBegDate;

	public Date getFirstAnapleroticCycleBegDate() {
		return firstAnapleroticCycleBegDate;
	}

	public void setFirstAnapleroticCycleBegDate(Date firstAnapleroticCycleBegDate) {
		this.firstAnapleroticCycleBegDate = firstAnapleroticCycleBegDate;
	}


	public BussBenchMarkInfoDto getBussBenchMarkInfoDto() {
		return bussBenchMarkInfoDto;
	}


	public void setBussBenchMarkInfoDto(BussBenchMarkInfoDto bussBenchMarkInfoDto) {
		this.bussBenchMarkInfoDto = bussBenchMarkInfoDto;
	}


	public String getRateType() {
		return rateType;
	}


	public void setRateType(String rateType) {
		this.rateType = rateType;
	}


	public BigDecimal getRate() {
		return rate;
	}


	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	public String getIndexId() {
		return indexId;
	}


	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}


	public String getIndexSource() {
		return indexSource;
	}


	public void setIndexSource(String indexSource) {
		this.indexSource = indexSource;
	}


	public BigDecimal getCoefficient() {
		return coefficient;
	}


	public void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}


	public BigDecimal getSpreadRate() {
		return spreadRate;
	}


	public void setSpreadRate(BigDecimal spreadRate) {
		this.spreadRate = spreadRate;
	}


	public String getResetType() {
		return resetType;
	}


	public void setResetType(String resetType) {
		this.resetType = resetType;
	}


	public String getProdStyle() {
		return prodStyle;
	}


	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
	}


	public String getsFinprodId() {
		return sFinprodId;
	}


	public void setsFinprodId(String sFinprodId) {
		this.sFinprodId = sFinprodId;
	}


	private Date vDate;

    /**
     * 产品款型
     */
    private String dealMode;
    /**
     * 发行方式
     */
    private String operationType;

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
