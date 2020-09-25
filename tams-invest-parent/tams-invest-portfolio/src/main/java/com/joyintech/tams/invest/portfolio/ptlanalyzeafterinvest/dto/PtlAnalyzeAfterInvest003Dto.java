package com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.dto;

import java.math.BigDecimal;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;

/**
 * 〈一句话功能简述〉
 *
 * @author 王贵奇
 * @version 1.0 2019/10/23
 * @since 1.0
 */
@PageId("PtlAnalyzeAfterInvest003")
public class PtlAnalyzeAfterInvest003Dto extends BaseEntity implements ITableRow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9109744570190349140L;

	private String triggerDate;
	
	private BigDecimal triggerPirce;
	
	private BigDecimal closeLine;
	
	private BigDecimal warningLine;
	
	private String statement;

	public String getTriggerDate() {
		return triggerDate;
	}

	public void setTriggerDate(String triggerDate) {
		this.triggerDate = triggerDate;
	}

	public BigDecimal getTriggerPirce() {
		return triggerPirce;
	}

	public void setTriggerPirce(BigDecimal triggerPirce) {
		this.triggerPirce = triggerPirce;
	}

	public BigDecimal getCloseLine() {
		return closeLine;
	}

	public void setCloseLine(BigDecimal closeLine) {
		this.closeLine = closeLine;
	}

	public BigDecimal getWarningLine() {
		return warningLine;
	}

	public void setWarningLine(BigDecimal warningLine) {
		this.warningLine = warningLine;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	
}
