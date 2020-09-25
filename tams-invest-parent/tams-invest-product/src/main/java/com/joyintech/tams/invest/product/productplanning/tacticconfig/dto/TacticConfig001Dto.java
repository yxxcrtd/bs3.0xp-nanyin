package com.joyintech.tams.invest.product.productplanning.tacticconfig.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.validator.TacticConfig001Validator;
import com.joyintech.tams.systemtools.util.SysDatadictUtil;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@PageId("TacticConfig001")
@ValidLogic(targetClass = TacticConfig001Validator.class,groups = {ValidateGroupEdit.class})
public class TacticConfig001Dto extends BaseEntity implements ProcessEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流程判断
	 */
	private String pkFlag = "PRDPLAN02";
	
	/**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    
    /**
     * 画面Id
     */
    private String pageId;
    
    /**
     * elementId
     */
    private String elementId;

	/**
     * 战术配置编号
     */
    private String tacticConfigCode;

    /**
     * 开始日
     */
    private Date vdate;

    /**
     * 结束日
     */
    private Date edate;

    /**
     * 投研支持观点
     */
    private String opinionRinv;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 理论配置比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theoConfigRate;

    /**
     * 样本平均收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal samleAverRate;

    /**
     * 波动率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vix;

    /**
     * 修正比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal corRate;

    /**
     * 修正场景
     */
    private String corScene;

    /**
     * 修正后占比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal corRatio;

    /**
     * 修正观点
     */
    private String corView;
    
    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 取得 战术配置编号
     * @return 战术配置编号
     */
    @PrimaryKey(Field.PK_1)
    public String getTacticConfigCode() {
        return tacticConfigCode;
    }

    /**
     * 设置 战术配置编号
     * @param tacticConfigCode 战术配置编号
     */
    public void setTacticConfigCode(String tacticConfigCode) {
        this.tacticConfigCode = tacticConfigCode == null ? null : tacticConfigCode.trim();
    }

    /**
     * 取得 开始日
     * @return 开始日
     */
    public Date getVdate() {
        return vdate;
    }
    
    public String getVdateStr() {
    	return DateUtil.format(this.getVdate());
    }

    /**
     * 设置 开始日
     * @param vdate 开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束日
     * @return 结束日
     */
    public Date getEdate() {
        return edate;
    }
    
    public String getEdateStr() {
    	return DateUtil.format(this.getEdate());
    }

    /**
     * 设置 结束日
     * @param edate 结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * 取得 投研支持观点
     * @return 投研支持观点
     */
    public String getOpinionRinv() {
        return opinionRinv;
    }

    /**
     * 设置 投研支持观点
     * @param opinionRinv 投研支持观点
     */
    public void setOpinionRinv(String opinionRinv) {
        this.opinionRinv = opinionRinv == null ? null : opinionRinv.trim();
    }
    
    /**
     * 取得 理论配置比例
     * @return 理论配置比例
     */
    public BigDecimal getTheoConfigRate() {
        return theoConfigRate;
    }
    
    public BigDecimal getTheoConfigRateStr() {
        return ArithmeticUtil.multiply(this.getTheoConfigRate(),new BigDecimal(100));
    }

    /**
     * 设置 理论配置比例
     * @param theoConfigRate 理论配置比例
     */
    public void setTheoConfigRate(BigDecimal theoConfigRate) {
        this.theoConfigRate = theoConfigRate;
    }

    /**
     * 取得 样本平均收益率
     * @return 样本平均收益率
     */
    public BigDecimal getSamleAverRate() {
        return samleAverRate;
    }
    
    public BigDecimal getSamleAverRateStr() {
        return ArithmeticUtil.multiply(this.getSamleAverRate(),new BigDecimal(100));
    }

    /**
     * 设置 样本平均收益率
     * @param samleAverRate 样本平均收益率
     */
    public void setSamleAverRate(BigDecimal samleAverRate) {
        this.samleAverRate = samleAverRate;
    }

    /**
     * 取得 波动率
     * @return 波动率
     */
    public BigDecimal getVix() {
        return vix;
    }
    
    public BigDecimal getVixStr() {
        return ArithmeticUtil.multiply(this.getVix(),new BigDecimal(100));
    }

    /**
     * 设置 波动率
     * @param vix 波动率
     */
    public void setVix(BigDecimal vix) {
        this.vix = vix;
    }

    /**
     * 取得 修正比例
     * @return 修正比例
     */
    public BigDecimal getCorRate() {
        return corRate;
    }
    
    public BigDecimal getCorRateStr() {
        return ArithmeticUtil.multiply(this.getCorRate(),new BigDecimal(100));
    }

    /**
     * 设置 修正比例
     * @param corRate 修正比例
     */
    public void setCorRate(BigDecimal corRate) {
        this.corRate = corRate;
    }

    /**
     * 取得 修正场景
     * @return 修正场景
     */
    public String getCorScene() {
        return corScene;
    }
    
    public String getCorSceneStr() {
        return SysDatadictUtil.getNameFromDataDict(DictCode.修正场景, this.getCorScene());
    }

    /**
     * 设置 修正场景
     * @param corScene 修正场景
     */
    public void setCorScene(String corScene) {
        this.corScene = corScene == null ? null : corScene.trim();
    }

    /**
     * 取得 修正后占比
     * @return 修正后占比
     */
    public BigDecimal getCorRatio() {
        return corRatio;
    }
    
    public BigDecimal getCorRatioStr() {
        return ArithmeticUtil.multiply(this.getCorRatio(),new BigDecimal(100));
    }

    /**
     * 设置 修正后占比
     * @param corRatio 修正后占比
     */
    public void setCorRatio(BigDecimal corRatio) {
        this.corRatio = corRatio;
    }

    /**
     * 取得 修正观点
     * @return 修正观点
     */
    public String getCorView() {
        return corView;
    }

    /**
     * 设置 修正观点
     * @param corView 修正观点
     */
    public void setCorView(String corView) {
        this.corView = corView == null ? null : corView.trim();
    }
    
    /**
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAssetType() {
        return assetType;
    }
    
    public String  getAssetTypeStr() {
    	return SysDatadictUtil.getNameFromDataDict(DictCode.资产大类, this.getAssetType());
    }

    /**
     * 设置 资产大类
     * @param assetType 资产大类
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }
    
    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	@PrimaryKey(Field.PK_2)
	public String getPkFlag() {
		return pkFlag;
	}

	public void setPkFlag(String pkFlag) {
		this.pkFlag = pkFlag;
	}
    
    
}
