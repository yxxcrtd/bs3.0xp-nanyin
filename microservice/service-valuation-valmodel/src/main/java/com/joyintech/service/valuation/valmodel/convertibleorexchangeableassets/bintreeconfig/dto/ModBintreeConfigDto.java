package com.joyintech.service.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:MOD_BINTREE_CONFIG表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-07-01
 */
public class ModBintreeConfigDto extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 流水号
     */
    private String seqno;

    /**
     * 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 标的公司上市状态
     */
    private String stockBordsttus;

    /**
     * 标的股票代码
     */
    private String stockId;

    /**
     * 可比公司
     */
    private String compOrg;

    /**
     * 可比公司名称
     */
    private String compOrgname;
    /**
     * 市场乘数方法，数据字典.市场乘数方法
     */
    private String marketMulti;

    /**
     * 可比公司股票代码
     */
    private String compStockid;
    /**
     * 可比公司市净率
     */
    private BigDecimal compPB;
    /**
     * 可比公司市盈率
     */
    private BigDecimal compPE;

    /**
     * 标的公司每股收益
     */
    private BigDecimal finprodProit;

    /**
     * 标的公司每股净资产
     */
    private BigDecimal finprodNetasset;

    /**
     * 连续股息率
     */
    private BigDecimal smoothsection;

    /**
     * 二叉树步数
     */
    private BigDecimal bintreeStep;

    /**
     * 无风险曲线代码
     */
    private String curveCode;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面元素
     */
    private String elementId;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * 选中的数据
     */
    private transient List<ModBintreeConfigDto> selectedItem;

    /**
     * 流水号
     * @return SEQNO 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @return FINPROD_ID 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @param finprodId 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 金融产品名称
     * @return FINPROD_NAME 金融产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 金融产品名称
     * @param finprodName 金融产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 标的公司上市状态
     * @return STOCK_BORDSTTUS 标的公司上市状态
     */
    public String getStockBordsttus() {
        return stockBordsttus;
    }

    /**
     * 标的公司上市状态
     * @param stockBordsttus 标的公司上市状态
     */
    public void setStockBordsttus(String stockBordsttus) {
        this.stockBordsttus = stockBordsttus == null ? null : stockBordsttus.trim();
    }

    /**
     * 标的股票代码
     * @return STOCK_ID 标的股票代码
     */
    public String getStockId() {
        return stockId;
    }

    /**
     * 标的股票代码
     * @param stockId 标的股票代码
     */
    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    /**
     * 可比公司
     * @return COMP_ORG 可比公司
     */
    public String getCompOrg() {
        return compOrg;
    }

    /**
     * 可比公司
     * @param compOrg 可比公司
     */
    public void setCompOrg(String compOrg) {
        this.compOrg = compOrg == null ? null : compOrg.trim();
    }

    /**
     * 可比公司名称
     * @return COMP_ORGNAME 可比公司名称
     */
    public String getCompOrgname() {
        return compOrgname;
    }

    /**
     * 可比公司名称
     * @param compOrgname 可比公司名称
     */
    public void setCompOrgname(String compOrgname) {
        this.compOrgname = compOrgname == null ? null : compOrgname.trim();
    }
    /**
     * 市场乘数方法，数据字典.市场乘数方法
     * @return marketMulti 市场乘数方法，数据字典.市场乘数方法
     */
    public String getMarketMulti() {
		return marketMulti;
	}
    /**
     * 市场乘数方法，数据字典.市场乘数方法
     * @param marketMulti 市场乘数方法，数据字典.市场乘数方法
     */
	public void setMarketMulti(String marketMulti) {
		this.marketMulti = marketMulti;
	}

	/**
     * 可比公司股票代码
     * @return COMP_STOCKID 可比公司股票代码
     */
    public String getCompStockid() {
        return compStockid;
    }

    /**
     * 可比公司股票代码
     * @param compStockid 可比公司股票代码
     */
    public void setCompStockid(String compStockid) {
        this.compStockid = compStockid == null ? null : compStockid.trim();
    }
    /**
     * 可比公司市净率
     * @return compPB 可比公司市净率
     */
    public BigDecimal getCompPB() {
		return compPB;
	}
    /**
     * 可比公司市净率
     * @param compPB 可比公司市净率
     */
	public void setCompPB(BigDecimal compPB) {
		this.compPB = compPB;
	}
	/**
     * 可比公司市盈率
     * @return compPE 可比公司市盈率
     */
	public BigDecimal getCompPE() {
		return compPE;
	}
	/**
     * 可比公司市盈率
     * @param compPE 可比公司市盈率
     */
	public void setCompPE(BigDecimal compPE) {
		this.compPE = compPE;
	}

	/**
     * 标的公司每股收益
     * @return FINPROD_PROIT 标的公司每股收益
     */
    public BigDecimal getFinprodProit() {
        return finprodProit;
    }

    /**
     * 标的公司每股收益
     * @param finprodProit 标的公司每股收益
     */
    public void setFinprodProit(BigDecimal finprodProit) {
        this.finprodProit = finprodProit;
    }

    /**
     * 标的公司每股净资产
     * @return FINPROD_NETASSET 标的公司每股净资产
     */
    public BigDecimal getFinprodNetasset() {
        return finprodNetasset;
    }

    /**
     * 标的公司每股净资产
     * @param finprodNetasset 标的公司每股净资产
     */
    public void setFinprodNetasset(BigDecimal finprodNetasset) {
        this.finprodNetasset = finprodNetasset;
    }

    /**
     * 连续股息率
     * @return SMOOTHSECTION 连续股息率
     */
    public BigDecimal getSmoothsection() {
        return smoothsection;
    }

    /**
     * 连续股息率
     * @param smoothsection 连续股息率
     */
    public void setSmoothsection(BigDecimal smoothsection) {
        this.smoothsection = smoothsection;
    }

    /**
     * 二叉树步数
     * @return BINTREE_STEP 二叉树步数
     */
    public BigDecimal getBintreeStep() {
        return bintreeStep;
    }

    /**
     * 二叉树步数
     * @param bintreeStep 二叉树步数
     */
    public void setBintreeStep(BigDecimal bintreeStep) {
        this.bintreeStep = bintreeStep;
    }

    /**
     * 无风险曲线代码
     * @return CURVE_CODE 无风险曲线代码
     */
    public String getCurveCode() {
        return curveCode;
    }

    /**
     * 无风险曲线代码
     * @param curveCode 无风险曲线代码
     */
    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode;
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    @Override
	public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    @Override
	public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    @Override
	public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    @Override
	public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    @Override
	public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    @Override
	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    @Override
	public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    @Override
	public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    @Override
	public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    @Override
	public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public List<ModBintreeConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ModBintreeConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}
}