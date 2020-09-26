/*
 * 文件名：SysInstRegBankCode001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2018年5月18日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.system.instregbankcode.dto;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import com.joyintech.tams.report.system.instregbankcode.validator.SysInstRegBankCode002Validator;

/**
 * 机构登记银行代码新建修改画面用dto
 *
 * @author 刘二通
 * @version 1.0 2018年5月18日
 * @see SysInstRegBankCode002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = SysInstRegBankCode002Validator.class)
public class SysInstRegBankCode002Dto extends ChbOrgRegistBankCode {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -2160961274169645496L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面的模式
     */
    private String pageModel;
    
    /**
     * 总行机构代码
     */
    @NotEmpty(fieldName = "总行机构代码")
    private String pbcBankCode;
    
    /**
     * 是否理财子公司
     */
    @NotEmpty(fieldName = "是否理财子公司")
    private String isFinSubCompany;

    public String getPbcBankCode() {
		return pbcBankCode;
	}

	public void setPbcBankCode(String pbcBankCode) {
		this.pbcBankCode = pbcBankCode;
	}

    @NotEmpty(fieldName = "社会信用代码")
	private String insitituteCode;

    @Override
    public String getInsitituteCode() {
        return insitituteCode;
    }

    @Override
    public void setInsitituteCode(String insitituteCode) {
        this.insitituteCode = insitituteCode;
    }

    public String getIsFinSubCompany() {
		return isFinSubCompany;
	}

	public void setIsFinSubCompany(String isFinSubCompany) {
		this.isFinSubCompany = isFinSubCompany;
	}

	/**
     * 取得 机构代码
     * @return 机构代码
     */
    @Override
    @NotEmpty(fieldName = "机构代码")
    public String getOrgCode() {
        return super.getOrgCode();
    }

    /**
     * 取得 登记银行代码
     * @return 登记银行代码
     */
    @Override
    @NotEmpty(fieldName = "登记银行代码")
    public String getRegistBankCode() {
        return super.getRegistBankCode();
    }

    /**
     * 获取 operateType
     *
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 pageModel
     *
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

}
