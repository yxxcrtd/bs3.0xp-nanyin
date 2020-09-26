package com.joyintech.tams.report.queryanalysis.equitysharecheck.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

/**
 * 投资者份额检查dto
 *
 * @author zhengxiaolian
 * @version 1.0 2019年11月12日
 * @see EquityShareCheck001Dto
 * @since 1.0
 */
public class EquityShareCheck001Dto extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3421469398929270879L;

	/**
	 * 查询日期
	 */
	private String holdDate;

	/**
	 * 检查状态
	 */
	private String checkRes;

	/**
	 * 产品代码
	 */
	private String prodId;

	/**
	 * 产品登记编码
	 */
	private String prdRegistCode;

	/**
	 * 产品名称
	 */
	private String prodName;

	/**
	 * 币种
	 */
	private String ccy;

	/**
	 * 识别标识
	 */
	private String indentifyMark;

	/**
	 * 当日持有份额份额
	 */
	private BigDecimal qty;

	/**
	 * 前一日持有份额
	 */
	private BigDecimal lastQty;

	/**
	 * 当日明细变动份额
	 */
	private BigDecimal changeQty;

	/**
	 * 获取 holdDate
	 *
	 * @return holdDate.
	 */
	public String getholdDate() {
		return holdDate;
//		return DateUtil.format(holdDate);
	}

	/**
	 * 设置 holdDate
	 *
	 * @param holdDate
	 *            holdDate
	 */
	public void setholdDate(String holdDate) {
		this.holdDate = holdDate;
	}   

	/**
	 * 获取 checkRes
	 *
	 * @return checkRes.
	 */
	public String getcheckRes() {
		return checkRes;
	}

	/**
	 * 设置 checkRes
	 *
	 * @param checkRes
	 *            checkRes
	 */
	public void setcheckRes(String checkRes) {
		this.checkRes = checkRes;
	}

	/**
	 * 获取 prodId
	 *
	 * @return prodId.
	 */
	public String getProdId() {
		return prodId;
	}

	/**
	 * 设置 prodId
	 *
	 * @param prodId
	 *            prodId
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	/**
	 * 获取 prdRegistCode
	 *
	 * @return prdRegistCode.
	 */
	public String getPrdRegistCode() {
		return prdRegistCode;
	}

	/**
	 * 设置 prdRegistCode
	 *
	 * @param prdRegistCode
	 *            prdRegistCode
	 */
	public void setPrdRegistCode(String prdRegistCode) {
		this.prdRegistCode = prdRegistCode;
	}

	/**
	 * 获取 prodName
	 *
	 * @return prodName.
	 */
	public String getProdName() {
		return prodName;
	}

	/**
	 * 设置 prodName
	 *
	 * @param prodName
	 *            prodName
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	/**
	 * 获取 ccy
	 *
	 * @return ccy.
	 */
	public String getCcy() {
		return ccy;
	}

	/**
	 * 设置 ccy
	 *
	 * @param ccy
	 *            ccy
	 */
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	/**
	 * 获取 indentifyMark
	 *
	 * @return indentifyMark.
	 */
	public String getIndentifyMark() {
		return indentifyMark;
	}

	/**
	 * 设置 indentifyMark
	 *
	 * @param indentifyMark
	 *            indentifyMark
	 */
	public void setIndentifyMark(String indentifyMark) {
		this.indentifyMark = indentifyMark;
	}

	/**
	 * 获取 qty
	 *
	 * @return qty.
	 */
	public BigDecimal getQty() {
		return qty;
	}

	/**
	 * 设置 qty
	 *
	 * @param qty
	 *            qty
	 */
	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * 获取 lastQty
	 *
	 * @return lastQty.
	 */
	public BigDecimal getLastQty() {
		return lastQty;
	}

	/**
	 * 设置 lastQty
	 *
	 * @param lastQty
	 *            lastQty
	 */
	public void setLastQty(BigDecimal lastQty) {
		this.lastQty = lastQty;
	}

	/**
	 * 获取 changeQty
	 *
	 * @return changeQty.
	 */
	public BigDecimal getChangeQty() {
		return changeQty;
	}

	/**
	 * 设置 changeQty
	 *
	 * @param changeQty
	 *            changeQty
	 */
	public void setChangeQty(BigDecimal changeQty) {
		this.changeQty = changeQty;
	}
}
