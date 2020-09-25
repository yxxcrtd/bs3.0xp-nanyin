package com.joyintech.tams.invest.common.risktrialrds.dto;

import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtl;

/**
 * 监督指标参数管理
 * @author 裴家俊
 * @date 2017年9月21日
 */
public class SupervisionParameterDto extends RskIsupIndicatorParaDtl{
    
	private static final long serialVersionUID = 6365500022221119309L;
	
	private String isMath;

	public String getIsMath() {
		return isMath;
	}

	public void setIsMath(String isMath) {
		this.isMath = isMath;
	}
	
	private String institutionCode;

    
    /**
     * 获取 institutionCode
     * @return institutionCode.
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    
    /**
     * 设置 institutionCode
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
  
}
