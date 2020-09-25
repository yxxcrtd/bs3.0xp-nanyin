package com.joyintech.service.portfolio.ptlinveststrategypool.dto;

import com.joyintech.tams.common.base.entity.PtlInvStrategyDetail;

public class PtlInvStrategyDetailDto extends PtlInvStrategyDetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -533890955305506144L;

    /**
     * 联合分类
     */
    private String uniteType;

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

    /**
     * 获取 uniteType
     * 
     * @return uniteType.
     */
    public String getUniteType() {
        return uniteType;
    }

    /**
     * 设置 uniteType
     * 
     * @param uniteType uniteType
     */
    public void setUniteType(String uniteType) {
        this.uniteType = uniteType;
    }

    /**
     * 取得 资产二级分类
     * 
     * @return 资产二级分类
     */
    public String getType2() {
        return type2;
    }

    /**
     * 设置 资产二级分类
     * 
     * @param type2 资产二级分类
     */
    public void setType2(String type2) {
        this.type2 = type2 == null ? null : type2.trim();
    }

    /**
     * 取得 资产三级分类
     * 
     * @return 资产三级分类
     */
    public String getType3() {
        return type3;
    }

    /**
     * 设置 资产三级分类
     * 
     * @param type3 资产三级分类
     */
    public void setType3(String type3) {
        this.type3 = type3 == null ? null : type3.trim();
    }

    /**
     * 取得 资产四级分类
     * 
     * @return 资产四级分类
     */
    public String getType4() {
        return type4;
    }

    /**
     * 设置 资产四级分类
     * 
     * @param type4 资产四级分类
     */
    public void setType4(String type4) {
        this.type4 = type4 == null ? null : type4.trim();
    }

    /**
     * 取得 资产五级分类
     * 
     * @return 资产五级分类
     */
    public String getType5() {
        return type5;
    }

    /**
     * 设置 资产五级分类
     * 
     * @param type5 资产五级分类
     */
    public void setType5(String type5) {
        this.type5 = type5 == null ? null : type5.trim();
    }
}
