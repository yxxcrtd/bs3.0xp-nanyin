package com.joyintech.tams.svreport.pbc.prod.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.constant.GlobalConstant;

/**
 * 描述:PBC_MONTH_PRD_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-12
 */
public class PbcMonthPrdPostionDto {
    /**
     * 主键uuid
     */
    private String posuuid;

    /**
     * 产品代码
     */
    private String prodid;

    /**
     * 机构代码，产品所属的机构代码
     */
    private String orgCode;

    /**
     * 报表开始日期
     */
    private Date startdate;

    /**
     * 报表结束日期
     */
    private Date enddate;

    /**
     * 统计日期：数据生成日期
     */
    private Date bookdate;

    /**
     * 是否分级产品 01 是/02 否
     */
    private String isConstracte;

    /**
     * 转让类型：01 收益权转让/99 其他
     */
    private String transferType;

    /**
     * 销售区域
     */
    private String buyPlace;

    /**
     * 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     */
    private String operateMode;

    /**
     * 募集方式： 01公募/02私募
     */
    private String raisemode;

    /**
     * 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    private String ivstorientation;

    /**
     * 管理方式：01 集合/02 单一
     */
    private String manageType;

    /**
     * 受托职责：01 主动管理/02 被动管理
     */
    private String fiduciaryDuty;

    /**
     * 存续状态 01存续/02终止/03未起息
     */
    private String durationflag;

    /**
     * 货币市场基金：01是/02否
     */
    private String isMarketFund;

    /**
     * 起息日，结合存续状态获得新发产品（起息日在本期=03）
     */
    private Date vdate;

    /**
     * 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     */
    private String paystatus;

    /**
     * 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     */
    private BigDecimal term;

    /**
     * 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     */
    private String ccy;

    /**
     * 客户二级类型，枚举值参见CUSTOMER_TYPE2
     */
    private String customerType2;

    /**
     * 募集余额，截止月末的余额
     */
    private BigDecimal endamtsumOth;

    /**
     * 当期募集金额，本月内的募集金额
     */
    private BigDecimal dirtyamtsum;

    /**
     * 当期兑付金额，本月内兑付的金额
     */
    private BigDecimal redamt;

    /**
     * 期末份额
     */
    private BigDecimal endqtysum;

    /**
     * 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     */
    private BigDecimal endDays;
    /**
     * 资产池代码
     */
    private String assetPoolCode;
    
    /**
     * 当期到期本金
     */
    private BigDecimal redCost;
    
    /**
     * 剩余成本
     */
    private BigDecimal endCost;
    
    //  START add by leipeng 20200601 多法人改造--单产品报表生成 区分部门
    //创建部门 
    private String institutionCode;
    
    


    public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	//  END add by leipeng 20200601 多法人改造--单产品报表生成 区分部门
	/**  
	 * @Description 获取当期到期本金  
	 * @Author      高健  
	 * @Date        2019年12月20日 上午11:28:34  
	 * @return redCost  
	 */
	
	public BigDecimal getRedCost() {
		return redCost;
	}

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年12月20日 上午11:28:34  
	 * @param redCost redCost  
	 */
	
	public void setRedCost(BigDecimal redCost) {
		this.redCost = redCost;
	}

	/**  
	 * @Description 获取剩余成本  
	 * @Author      高健  
	 * @Date        2019年12月20日 上午11:28:34  
	 * @return endCost  
	 */
	
	public BigDecimal getEndCost() {
		return endCost;
	}

	/**  
	 * @Description TODO  
	 * @Author      高健  
	 * @Date        2019年12月20日 上午11:28:34  
	 * @param endCost endCost  
	 */
	
	public void setEndCost(BigDecimal endCost) {
		this.endCost = endCost;
	}

	/**
     * 主键uuid
     * @return POSUUID 主键uuid
     */
    public String getPosuuid() {
        return posuuid;
    }

    /**
     * 主键uuid
     * @param posuuid 主键uuid
     */
    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid == null ? null : posuuid.trim();
    }

    /**
     * 产品代码
     * @return PRODID 产品代码
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 产品代码
     * @param prodid 产品代码
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 机构代码，产品所属的机构代码
     * @return ORG_CODE 机构代码，产品所属的机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码，产品所属的机构代码
     * @param orgCode 机构代码，产品所属的机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 报表开始日期
     * @return STARTDATE 报表开始日期
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 报表开始日期
     * @param startdate 报表开始日期
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 报表结束日期
     * @return ENDDATE 报表结束日期
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 报表结束日期
     * @param enddate 报表结束日期
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 统计日期：数据生成日期
     * @return BOOKDATE 统计日期：数据生成日期
     */
    public Date getBookdate() {
        return bookdate;
    }

    /**
     * 统计日期：数据生成日期
     * @param bookdate 统计日期：数据生成日期
     */
    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    /**
     * 是否分级产品 01 是/02 否
     * @return IS_CONSTRACTE 是否分级产品 01 是/02 否
     */
    public String getIsConstracte() {
        return isConstracte;
    }

    /**
     * 是否分级产品 01 是/02 否
     * @param isConstracte 是否分级产品 01 是/02 否
     */
    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte == null ? null : isConstracte.trim();
    }

    /**
     * 转让类型：01 收益权转让/99 其他
     * @return TRANSFER_TYPE 转让类型：01 收益权转让/99 其他
     */
    public String getTransferType() {
        return transferType;
    }

    /**
     * 转让类型：01 收益权转让/99 其他
     * @param transferType 转让类型：01 收益权转让/99 其他
     */
    public void setTransferType(String transferType) {
        this.transferType = transferType == null ? null : transferType.trim();
    }

    /**
     * 销售区域
     * @return BUY_PLACE 销售区域
     */
    public String getBuyPlace() {
        return buyPlace;
    }

    /**
     * 销售区域
     * @param buyPlace 销售区域
     */
    public void setBuyPlace(String buyPlace) {
        this.buyPlace = buyPlace == null ? null : buyPlace.trim();
    }

    /**
     * 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     * @return OPERATE_MODE 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     * @param operateMode 运行方式：01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型 
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 募集方式： 01公募/02私募
     * @return RAISEMODE 募集方式： 01公募/02私募
     */
    public String getRaisemode() {
        return raisemode;
    }

    /**
     * 募集方式： 01公募/02私募
     * @param raisemode 募集方式： 01公募/02私募
     */
    public void setRaisemode(String raisemode) {
        this.raisemode = raisemode == null ? null : raisemode.trim();
    }

    /**
     * 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @return IVSTORIENTATION 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public String getIvstorientation() {
        return ivstorientation;
    }

    /**
     * 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     * @param ivstorientation 投资性质： 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation == null ? null : ivstorientation.trim();
    }

    /**
     * 管理方式：01 集合/02 单一
     * @return MANAGE_TYPE 管理方式：01 集合/02 单一
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 管理方式：01 集合/02 单一
     * @param manageType 管理方式：01 集合/02 单一
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 受托职责：01 主动管理/02 被动管理
     * @return FIDUCIARY_DUTY 受托职责：01 主动管理/02 被动管理
     */
    public String getFiduciaryDuty() {
        return fiduciaryDuty;
    }

    /**
     * 受托职责：01 主动管理/02 被动管理
     * @param fiduciaryDuty 受托职责：01 主动管理/02 被动管理
     */
    public void setFiduciaryDuty(String fiduciaryDuty) {
        this.fiduciaryDuty = fiduciaryDuty == null ? null : fiduciaryDuty.trim();
    }

    /**
     * 存续状态 01存续/02终止/03未起息
     * @return DURATIONFLAG 存续状态 01存续/02终止/03未起息
     */
    public String getDurationflag() {
        return durationflag;
    }

    /**
     * 存续状态 01存续/02终止/03未起息
     * @param durationflag 存续状态 01存续/02终止/03未起息
     */
    public void setDurationflag(String durationflag) {
        this.durationflag = durationflag == null ? null : durationflag.trim();
    }

    /**
     * 货币市场基金：01是/02否
     * @return IS_MARKET_FUND 货币市场基金：01是/02否
     */
    public String getIsMarketFund() {
        return isMarketFund;
    }

    /**
     * 货币市场基金：01是/02否
     * @param isMarketFund 货币市场基金：01是/02否
     */
    public void setIsMarketFund(String isMarketFund) {
        this.isMarketFund = isMarketFund == null ? null : isMarketFund.trim();
    }

    /**
     * 起息日，结合存续状态获得新发产品（起息日在本期=03）
     * @return VDATE 起息日，结合存续状态获得新发产品（起息日在本期=03）
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 起息日，结合存续状态获得新发产品（起息日在本期=03）
     * @param vdate 起息日，结合存续状态获得新发产品（起息日在本期=03）
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     * @return PAYSTATUS 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     */
    public String getPaystatus() {
        return paystatus;
    }

    /**
     * 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     * @param paystatus 兑付状态：01 正常兑付/02 提前兑付/03 延期兑付/04 到期未兑付
     */
    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    /**
     * 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     * @return TERM 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     */
    public BigDecimal getTerm() {
        return term;
    }

    /**
     * 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     * @param term 合同期限：0表示无固定期限，其他值根据期限所属区间映射
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     * @return CCY 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     * @param ccy 存续期交易的币种 CNY:人民币/USD:美元/EUR 欧元/GBP 英镑/JPY 日元/OTH 其他币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 客户二级类型，枚举值参见CUSTOMER_TYPE2
     * @return CUSTOMER_TYPE2 客户二级类型，枚举值参见CUSTOMER_TYPE2
     */
    public String getCustomerType2() {
        return customerType2;
    }

    /**
     * 客户二级类型，枚举值参见CUSTOMER_TYPE2
     * @param customerType2 客户二级类型，枚举值参见CUSTOMER_TYPE2
     */
    public void setCustomerType2(String customerType2) {
        this.customerType2 = customerType2 == null ? null : customerType2.trim();
    }

    /**
     * 募集余额，截止月末的余额
     * @return ENDAMTSUM_OTH 募集余额，截止月末的余额
     */
    public BigDecimal getEndamtsumOth() {
        return endamtsumOth;
    }

    /**
     * 募集余额，截止月末的余额
     * @param endamtsumOth 募集余额，截止月末的余额
     */
    public void setEndamtsumOth(BigDecimal endamtsumOth) {
        this.endamtsumOth = endamtsumOth;
    }

    /**
     * 当期募集金额，本月内的募集金额
     * @return DIRTYAMTSUM 当期募集金额，本月内的募集金额
     */
    public BigDecimal getDirtyamtsum() {
        return dirtyamtsum;
    }

    /**
     * 当期募集金额，本月内的募集金额
     * @param dirtyamtsum 当期募集金额，本月内的募集金额
     */
    public void setDirtyamtsum(BigDecimal dirtyamtsum) {
        this.dirtyamtsum = dirtyamtsum;
    }

    /**
     * 当期兑付金额，本月内兑付的金额
     * @return REDAMT 当期兑付金额，本月内兑付的金额
     */
    public BigDecimal getRedamt() {
        return redamt;
    }

    /**
     * 当期兑付金额，本月内兑付的金额
     * @param redamt 当期兑付金额，本月内兑付的金额
     */
    public void setRedamt(BigDecimal redamt) {
        this.redamt = redamt;
    }

    /**
     * 期末份额
     * @return ENDQTYSUM 期末份额
     */
    public BigDecimal getEndqtysum() {
        return endqtysum;
    }

    /**
     * 期末份额
     * @param endqtysum 期末份额
     */
    public void setEndqtysum(BigDecimal endqtysum) {
        this.endqtysum = endqtysum;
    }

    /**
     * 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     * @return END_DAYS 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     */
    public BigDecimal getEndDays() {
        return endDays;
    }

    /**
     * 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     * @param endDays 产品剩余期限，0表示无固定期限，其他值根据期限所属区间映射
     */
    public void setEndDays(BigDecimal endDays) {
        this.endDays = endDays;
    }

    public String getAssetPoolCode() {
        return assetPoolCode;
    }

    public void setAssetPoolCode(String assetPoolCode) {
        this.assetPoolCode = assetPoolCode;
    }

    /**
     * 产品代码+机构代码
     * @return  品代码+机构代码
     */
    public String getProdidAndOrgCode() {
        return prodid + GlobalConstant.VERTICAL_LINE + orgCode;
    }
    /**
     * 人行产品代码+机构代码
     * @return  品代码+机构代码
     */
    public String getPbcProdidAndOrgCode() {
        return assetPoolCode + GlobalConstant.VERTICAL_LINE + orgCode;
    }
}