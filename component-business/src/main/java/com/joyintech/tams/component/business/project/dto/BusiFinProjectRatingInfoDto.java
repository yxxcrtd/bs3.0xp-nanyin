/*
 * 文件名：BusiFinBondRatingInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.business.project.validator.BusiFinProjectRatingInfoValidator;

import java.util.List;

/**
 * 评级信息
 * 〈功能详细描述〉
 * @author sunhui
 * @version 1.0 2020年11月23日
 * @see BusiFinBondRatingInfoDto
 * @since 1.0
 */
@ValidLogic(targetClass = BusiFinProjectRatingInfoValidator.class,groups = {ValidateGroupEdit.class})
public class BusiFinProjectRatingInfoDto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    private List<MstBondCreditRating> mstBondCreditRatingList;

    
    /**
     * 获取 mstBondCreditRatingList
     * @return mstBondCreditRatingList.
     */
    public List<MstBondCreditRating> getMstBondCreditRatingList() {
        return mstBondCreditRatingList;
    }

    
    /**
     * 设置 mstBondCreditRatingList
     * @param mstBondCreditRatingList mstBondCreditRatingList
     */
    public void setMstBondCreditRatingList(List<MstBondCreditRating> mstBondCreditRatingList) {
        this.mstBondCreditRatingList = mstBondCreditRatingList;
    }
    
    

}
