package com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto;

import com.joyintech.tams.common.base.entity.PtlInvStrategyDetail;

public class PtlInvestStrategyPoolDetailDto extends PtlInvStrategyDetail{
	
	private String detailKey;
	 /**
     * 资产二级分类
     */
    private String type2;

    /**
     * 资产三级分类
     */
    private String type3;

    /**
     * 资产四级分类
     */
    private String type4;

    /**
     * 资产五级分类
     */
    private String type5;

	public String getDetailKey() {
		this.setDetailKey(this.getType2()+this.getType3()+this.getType4()+this.getType5());
        return this.detailKey;
	}

	public void setDetailKey(String detailKey) {
		this.detailKey = detailKey;
	}

    
    /**
     * 获取 type2
     * @return type2.
     */
    public String getType2() {
        return type2;
    }

    
    /**
     * 设置 type2
     * @param type2 type2
     */
    public void setType2(String type2) {
        this.type2 = type2;
    }

    
    /**
     * 获取 type3
     * @return type3.
     */
    public String getType3() {
        return type3;
    }

    
    /**
     * 设置 type3
     * @param type3 type3
     */
    public void setType3(String type3) {
        this.type3 = type3;
    }

    
    /**
     * 获取 type4
     * @return type4.
     */
    public String getType4() {
        return type4;
    }

    
    /**
     * 设置 type4
     * @param type4 type4
     */
    public void setType4(String type4) {
        this.type4 = type4;
    }

    
    /**
     * 获取 type5
     * @return type5.
     */
    public String getType5() {
        return type5;
    }

    
    /**
     * 设置 type5
     * @param type5 type5
     */
    public void setType5(String type5) {
        this.type5 = type5;
    }
	
}
