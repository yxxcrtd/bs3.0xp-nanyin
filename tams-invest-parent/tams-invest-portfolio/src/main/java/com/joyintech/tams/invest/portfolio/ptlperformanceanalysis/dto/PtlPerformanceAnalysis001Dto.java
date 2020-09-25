package com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PlTargetDb;
import com.joyintech.tams.constant.DictCodeListExt1;

public class PtlPerformanceAnalysis001Dto  extends PlTargetDb implements GridRowDto {
	
	private String id;
	
	private String parentId;
	
	private String rowId;
	
	private boolean leaf;
	
	private String profitTypeName;
	
	//绝对回报
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal absoIncome;
    
    //绝对回报
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal relaIncome;
    
    //最大涨幅
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxIncrease;
	
	//累计年化收益率
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal totalyYield;
	
	//总资产增长率（区间）
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetGrP;
    
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netassetGrP;
	
    //净值增长率（区间）
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrP;
    
    //累计净值增长率（区间）
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netunitGrP2;
    
    //区间年化收益率
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldP;
    
    //区间年化收益率
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal absoluteYieldP;
    
    //区间Beta
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaP;
    
    
    /**
     * Beta（本周）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaW;

    /**
     * Beta（本月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaM;

    /**
     * Beta（本季）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaS;

    /**
     * Beta（本年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaY;

    /**
     * Beta（成立至今）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaR;

    /**
     * Beta（最近1个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaLoneM;

    /**
     * Beta（最近3个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaLthreeM;

    /**
     * Beta（最近半年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaLHy;

    /**
     * Beta（最近1年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal betaLY;

    /**
     * treynor 区间
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorP;
    
    /**
     * treynor（本周）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorW;

    /**
     * treynor（本月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorM;

    /**
     * treynor（本季）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorS;

    /**
     * treynor（本年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorY;

    /**
     * treynor（成立至今）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorR;

    /**
     * treynor（最近1个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorLoneM;

    /**
     * treynor（最近3个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorLthreeM;

    /**
     * treynor（最近半年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorLHy;

    /**
     * treynor（最近1年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal treynorLY;
    
    
    /**
     * IR（区间）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irP;
	
	/**
     * IR（本周）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irW;

    /**
     * IR（本月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irM;

    /**
     * IR（本季）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irS;

    /**
     * IR（本年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irY;

    /**
     * IR（成立至今）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irR;

    /**
     * IR（最近1个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irLoneM;

    /**
     * IR（最近3个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irLthreeM;

    /**
     * IR（最近半年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irLHy;

    /**
     * IR（最近1年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal irLY;
    
    
    /**
     * 跟踪误差（区间）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackP;
	
	/**
     * 跟踪误差（本周）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackW;

    /**
     * 跟踪误差（本月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackM;

    /**
     * 跟踪误差（本季）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackS;

    /**
     * 跟踪误差（本年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackY;

    /**
     * 跟踪误差（成立至今）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackR;

    /**
     * 跟踪误差（最近1个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackLoneM;

    /**
     * 跟踪误差（最近3个月）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackLthreeM;

    /**
     * 跟踪误差（最近半年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackLHy;

    /**
     * 跟踪误差（最近1年）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal trackLY;
    
    private BigDecimal vixP;
    
    private BigDecimal sharpeP;
    
    private BigDecimal sortinoP;
    
    private BigDecimal maxDrP;
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1395460513283042730L;


	public BigDecimal getAbsoIncome() {
		return absoIncome;
	}

	public void setAbsoIncome(BigDecimal absoIncome) {
		this.absoIncome = absoIncome;
	}

	public BigDecimal getRelaIncome() {
		return relaIncome;
	}

	public void setRelaIncome(BigDecimal relaIncome) {
		this.relaIncome = relaIncome;
	}

	public BigDecimal getMaxIncrease() {
		return maxIncrease;
	}

	public void setMaxIncrease(BigDecimal maxIncrease) {
		this.maxIncrease = maxIncrease;
	}

	public BigDecimal getTotalyYield() {
		return totalyYield;
	}

	public void setTotalyYield(BigDecimal totalyYield) {
		this.totalyYield = totalyYield;
	}

	public BigDecimal getAssetGrP() {
		return assetGrP;
	}

	public void setAssetGrP(BigDecimal assetGrP) {
		this.assetGrP = assetGrP;
	}

	public BigDecimal getNetassetGrP() {
		return netassetGrP;
	}

	public void setNetassetGrP(BigDecimal netassetGrP) {
		this.netassetGrP = netassetGrP;
	}

	public BigDecimal getNetunitGrP() {
		return netunitGrP;
	}

	public void setNetunitGrP(BigDecimal netunitGrP) {
		this.netunitGrP = netunitGrP;
	}

	public BigDecimal getNetunitGrP2() {
		return netunitGrP2;
	}

	public void setNetunitGrP2(BigDecimal netunitGrP2) {
		this.netunitGrP2 = netunitGrP2;
	}

	public BigDecimal getYieldP() {
		return yieldP;
	}

	public void setYieldP(BigDecimal yieldP) {
		this.yieldP = yieldP;
	}

	public BigDecimal getAbsoluteYieldP() {
		return absoluteYieldP;
	}

	public void setAbsoluteYieldP(BigDecimal absoluteYieldP) {
		this.absoluteYieldP = absoluteYieldP;
	}

	public String getProfitTypeName() {
		return profitTypeName;
	}

	public void setProfitTypeName(String profitTypeName) {
		this.profitTypeName = profitTypeName;
	}

	public BigDecimal getBetaP() {
		return betaP;
	}

	public void setBetaP(BigDecimal betaP) {
		this.betaP = betaP;
	}

	public BigDecimal getBetaW() {
		return betaW;
	}

	public void setBetaW(BigDecimal betaW) {
		this.betaW = betaW;
	}

	public BigDecimal getBetaM() {
		return betaM;
	}

	public void setBetaM(BigDecimal betaM) {
		this.betaM = betaM;
	}

	public BigDecimal getBetaS() {
		return betaS;
	}

	public void setBetaS(BigDecimal betaS) {
		this.betaS = betaS;
	}

	public BigDecimal getBetaY() {
		return betaY;
	}

	public void setBetaY(BigDecimal betaY) {
		this.betaY = betaY;
	}

	public BigDecimal getBetaR() {
		return betaR;
	}

	public void setBetaR(BigDecimal betaR) {
		this.betaR = betaR;
	}

	public BigDecimal getBetaLoneM() {
		return betaLoneM;
	}

	public void setBetaLoneM(BigDecimal betaLoneM) {
		this.betaLoneM = betaLoneM;
	}

	public BigDecimal getBetaLthreeM() {
		return betaLthreeM;
	}

	public void setBetaLthreeM(BigDecimal betaLthreeM) {
		this.betaLthreeM = betaLthreeM;
	}

	public BigDecimal getBetaLHy() {
		return betaLHy;
	}

	public void setBetaLHy(BigDecimal betaLHy) {
		this.betaLHy = betaLHy;
	}

	public BigDecimal getBetaLY() {
		return betaLY;
	}

	public void setBetaLY(BigDecimal betaLY) {
		this.betaLY = betaLY;
	}

	public BigDecimal getTreynorP() {
		return treynorP;
	}

	public void setTreynorP(BigDecimal treynorP) {
		this.treynorP = treynorP;
	}

	public BigDecimal getTreynorW() {
		return treynorW;
	}

	public void setTreynorW(BigDecimal treynorW) {
		this.treynorW = treynorW;
	}

	public BigDecimal getTreynorM() {
		return treynorM;
	}

	public void setTreynorM(BigDecimal treynorM) {
		this.treynorM = treynorM;
	}

	public BigDecimal getTreynorS() {
		return treynorS;
	}

	public void setTreynorS(BigDecimal treynorS) {
		this.treynorS = treynorS;
	}

	public BigDecimal getTreynorY() {
		return treynorY;
	}

	public void setTreynorY(BigDecimal treynorY) {
		this.treynorY = treynorY;
	}

	public BigDecimal getTreynorR() {
		return treynorR;
	}

	public void setTreynorR(BigDecimal treynorR) {
		this.treynorR = treynorR;
	}

	public BigDecimal getTreynorLoneM() {
		return treynorLoneM;
	}

	public void setTreynorLoneM(BigDecimal treynorLoneM) {
		this.treynorLoneM = treynorLoneM;
	}

	public BigDecimal getTreynorLthreeM() {
		return treynorLthreeM;
	}

	public void setTreynorLthreeM(BigDecimal treynorLthreeM) {
		this.treynorLthreeM = treynorLthreeM;
	}

	public BigDecimal getTreynorLHy() {
		return treynorLHy;
	}

	public void setTreynorLHy(BigDecimal treynorLHy) {
		this.treynorLHy = treynorLHy;
	}

	public BigDecimal getTreynorLY() {
		return treynorLY;
	}

	public void setTreynorLY(BigDecimal treynorLY) {
		this.treynorLY = treynorLY;
	}

	public BigDecimal getIrP() {
		return irP;
	}

	public void setIrP(BigDecimal irP) {
		this.irP = irP;
	}

	public BigDecimal getIrW() {
		return irW;
	}

	public void setIrW(BigDecimal irW) {
		this.irW = irW;
	}

	public BigDecimal getIrM() {
		return irM;
	}

	public void setIrM(BigDecimal irM) {
		this.irM = irM;
	}

	public BigDecimal getIrS() {
		return irS;
	}

	public void setIrS(BigDecimal irS) {
		this.irS = irS;
	}

	public BigDecimal getIrY() {
		return irY;
	}

	public void setIrY(BigDecimal irY) {
		this.irY = irY;
	}

	public BigDecimal getIrR() {
		return irR;
	}

	public void setIrR(BigDecimal irR) {
		this.irR = irR;
	}

	public BigDecimal getIrLoneM() {
		return irLoneM;
	}

	public void setIrLoneM(BigDecimal irLoneM) {
		this.irLoneM = irLoneM;
	}

	public BigDecimal getIrLthreeM() {
		return irLthreeM;
	}

	public void setIrLthreeM(BigDecimal irLthreeM) {
		this.irLthreeM = irLthreeM;
	}

	public BigDecimal getIrLHy() {
		return irLHy;
	}

	public void setIrLHy(BigDecimal irLHy) {
		this.irLHy = irLHy;
	}

	public BigDecimal getIrLY() {
		return irLY;
	}

	public void setIrLY(BigDecimal irLY) {
		this.irLY = irLY;
	}

	public BigDecimal getTrackP() {
		return trackP;
	}

	public void setTrackP(BigDecimal trackP) {
		this.trackP = trackP;
	}

	public BigDecimal getTrackW() {
		return trackW;
	}

	public void setTrackW(BigDecimal trackW) {
		this.trackW = trackW;
	}

	public BigDecimal getTrackM() {
		return trackM;
	}

	public void setTrackM(BigDecimal trackM) {
		this.trackM = trackM;
	}

	public BigDecimal getTrackS() {
		return trackS;
	}

	public void setTrackS(BigDecimal trackS) {
		this.trackS = trackS;
	}

	public BigDecimal getTrackY() {
		return trackY;
	}

	public void setTrackY(BigDecimal trackY) {
		this.trackY = trackY;
	}

	public BigDecimal getTrackR() {
		return trackR;
	}

	public void setTrackR(BigDecimal trackR) {
		this.trackR = trackR;
	}

	public BigDecimal getTrackLoneM() {
		return trackLoneM;
	}

	public void setTrackLoneM(BigDecimal trackLoneM) {
		this.trackLoneM = trackLoneM;
	}

	public BigDecimal getTrackLthreeM() {
		return trackLthreeM;
	}

	public void setTrackLthreeM(BigDecimal trackLthreeM) {
		this.trackLthreeM = trackLthreeM;
	}

	public BigDecimal getTrackLHy() {
		return trackLHy;
	}

	public void setTrackLHy(BigDecimal trackLHy) {
		this.trackLHy = trackLHy;
	}

	public BigDecimal getTrackLY() {
		return trackLY;
	}

	public void setTrackLY(BigDecimal trackLY) {
		this.trackLY = trackLY;
	}

	public BigDecimal getVixP() {
		return vixP;
	}

	public void setVixP(BigDecimal vixP) {
		this.vixP = vixP;
	}

	public BigDecimal getSharpeP() {
		return sharpeP;
	}

	public void setSharpeP(BigDecimal sharpeP) {
		this.sharpeP = sharpeP;
	}

	public BigDecimal getSortinoP() {
		return sortinoP;
	}

	public void setSortinoP(BigDecimal sortinoP) {
		this.sortinoP = sortinoP;
	}

	public BigDecimal getMaxDrP() {
		return maxDrP;
	}

	public void setMaxDrP(BigDecimal maxDrP) {
		this.maxDrP = maxDrP;
	}

	@Override
	public String getCreateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateUser(String createUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCreateDept() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateDept(String createDept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRowId() {
		// TODO Auto-generated method stub
        return rowId;
	}

	@Override
	public void setRowId(String rowId) {
        this.rowId = rowId;
		
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setOrder(int order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getParentId() {
		// TODO Auto-generated method stub
        return this.parentId;
	}

	@Override
	public void setParentId(String parentId) {
        this.parentId = parentId;
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return this.leaf;
	}

	@Override
	public void setLeaf(boolean leaf) {
		// TODO Auto-generated method stub
		this.leaf = leaf;
		
	}

}
