package com.joyintech.tams.invest.common.risktrialrds.dto;

import com.joyintech.tams.invest.common.risktrialrds.dict.SupervisionStat;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
/**
 * 限制型内容解析
 * @author 裴家俊
 * @version 1.0 2018年11月30日
 * @see LimitPointThreshold
 * @since 1.0
 */
public class LimitPointThreshold {

	private PointThreshold violationThreshold1;
	private PointThreshold violationThreshold2;
	private PointThreshold approveThreshold1;
	private PointThreshold approveThreshold2;
	private PointThreshold warningThreshold1;
	private PointThreshold warningThreshold2;
	
	public PointThreshold getViolationThreshold1() {
		return violationThreshold1;
	}
	public void setViolationThreshold1(PointThreshold violationThreshold1) {
		this.violationThreshold1 = violationThreshold1;
	}
	public PointThreshold getViolationThreshold2() {
		return violationThreshold2;
	}
	public void setViolationThreshold2(PointThreshold violationThreshold2) {
		this.violationThreshold2 = violationThreshold2;
	}

	public PointThreshold getApproveThreshold1() {
		return approveThreshold1;
	}

	public void setApproveThreshold1(PointThreshold approveThreshold1) {
		this.approveThreshold1 = approveThreshold1;
	}

	public PointThreshold getApproveThreshold2() {
		return approveThreshold2;
	}

	public void setApproveThreshold2(PointThreshold approveThreshold2) {
		this.approveThreshold2 = approveThreshold2;
	}

	public PointThreshold getWarningThreshold1() {
		return warningThreshold1;
	}
	public void setWarningThreshold1(PointThreshold warningThreshold1) {
		this.warningThreshold1 = warningThreshold1;
	}
	public PointThreshold getWarningThreshold2() {
		return warningThreshold2;
	}
	public void setWarningThreshold2(PointThreshold warningThreshold2) {
		this.warningThreshold2 = warningThreshold2;
	}
	
	public String toVString() {
		StringBuffer sBuf = new StringBuffer();
		sBuf.append(violationThreshold1);
		if(violationThreshold2 != null) {
			sBuf.append(" ").append(violationThreshold2);
		}
		return sBuf.toString();
	}
	
	public String toWString() {
		StringBuffer sBuf = new StringBuffer();
		if(warningThreshold1 != null&&warningThreshold1.getValue()!=null) {
			sBuf.append(warningThreshold1);
			
			if(warningThreshold2 != null) {
				sBuf.append(" ").append(warningThreshold2);
			}
		}
		
		return sBuf.toString();
	}

	public String toAString() {
		StringBuffer sBuf = new StringBuffer();
		if(approveThreshold1 != null&&approveThreshold1.getValue()!=null) {
			sBuf.append(approveThreshold1);

			if(approveThreshold2 != null) {
				sBuf.append(" ").append(approveThreshold2);
			}
		}

		return sBuf.toString();
	}
	public SupervisionStat check(Double value) {
		if(violationThreshold1 != null&&violationThreshold1.getValue()!=null) {
			boolean isViolation = violationThreshold1.check(value);
			if (violationThreshold2 != null) {
				boolean isViolation2 = violationThreshold2.check(value);
				switch (violationThreshold2.getLnk()) {
					case RiskUtil.AND:
						isViolation = isViolation && isViolation2;
						break;
					case RiskUtil.OR:
						isViolation = isViolation || isViolation2;
						break;
				}
			}
			if (isViolation) return SupervisionStat.VIOLATION;
		}

		if(approveThreshold1 != null&&approveThreshold1.getValue()!=null) {
			boolean isApprove = approveThreshold1.check(value);
			if (approveThreshold2 != null) {
				boolean isViolation2 = approveThreshold2.check(value);
				switch (approveThreshold2.getLnk()) {
					case RiskUtil.AND:
						isApprove = isApprove && isViolation2;
						break;
					case RiskUtil.OR:
						isApprove = isApprove || isViolation2;
						break;
				}
			}
			if (isApprove) return SupervisionStat.APPROVE;
		}

		if(warningThreshold1 != null&&warningThreshold1.getValue()!=null) {
			boolean isWarning = warningThreshold1.check(value);
			if(warningThreshold2 != null) {
				boolean isWarning2 = warningThreshold2.check(value);
				switch(warningThreshold2.getLnk()) {
					case RiskUtil.AND: isWarning = isWarning && isWarning2; break;
					case RiskUtil.OR: isWarning = isWarning || isWarning2; break;
				}
			}
			if(isWarning) return SupervisionStat.WARNING;
		}
		
		return SupervisionStat.NORMAL;
	}
}

class PointThreshold {

	private String lnk;
	private String op;
	private Double value;//经过单位转换过后的值
	private String unit;
	
	public PointThreshold(String op, String value,String unit) {
		this.op = op;
		this.unit=unit;
		this.value =  (value==null||value.equals(""))?null:Double.valueOf(value);
	}
	
	public PointThreshold(String lnk, String op, String value,String unit) {
		this.lnk = lnk;
		this.op = op;
		this.unit=unit;
		this.value =  (value==null||value.equals(""))?null:Double.valueOf(value);
	}
	
	@Override
	public String toString() {
		StringBuffer sBuf = new StringBuffer();
		if(lnk != null) sBuf.append(lnk);
		return sBuf.append(" ").append(op)
				.append(" ").append(value)
				.append(" ").append(RiskUtil.convertValueToUnit(unit))
				.toString();
	}
	
	public boolean check(Double value) {
		switch(op) {
			case ">": return value > RiskUtil.convertUnitToValue(this.unit,this.value);
			case ">=": return value >= RiskUtil.convertUnitToValue(this.unit,this.value);
			case "<": return value < RiskUtil.convertUnitToValue(this.unit,this.value);
			case "<=": return value <= RiskUtil.convertUnitToValue(this.unit,this.value);
		}
		return false;
	}
	
	public String getLnk() {
		return lnk;
	}
	public void setLnk(String lnk) {
		this.lnk = lnk;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
