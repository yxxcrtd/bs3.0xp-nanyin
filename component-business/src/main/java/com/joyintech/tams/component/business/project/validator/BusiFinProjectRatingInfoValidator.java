/*
 * 文件名：BusiFinBondRatingInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.project.validator;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.component.business.bond.dto.BusiFinBondRatingInfoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  评级信息--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月14日
 * @see BusiFinProjectRatingInfoValidator
 * @since 1.0
 */
@Component
public class BusiFinProjectRatingInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        BusiFinBondRatingInfoDto dto = (BusiFinBondRatingInfoDto)value;
        List<MstBondCreditRating> mstBondCreditRatingList = dto.getMstBondCreditRatingList();
//        校验重复的评级信息
//        Set<MstBondCreditRating> set = new  HashSet<MstBondCreditRating>();
        Set<String> keySet = new HashSet<>();
        if(null!=mstBondCreditRatingList&&!mstBondCreditRatingList.isEmpty()){
            for(MstBondCreditRating entity:mstBondCreditRatingList){
                //评级类型不可为空
                if(StringUtil.isEmpty(entity.getoType())){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.gradeType"))));
                    break;
                }
                //长期短期不可为空
                if(StringUtil.isEmpty(entity.getShortLongTerm())){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.shortLongTerm"))));
                    break;
                }
                //评级结果不可为空
                if(StringUtil.isEmpty(entity.getGradeResult())){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.gradeResult"))));
                    break;
                }
                if(null==entity.getGradeDate()){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.gradeDate"))));
                    break;
                }
                if(StringUtil.isEmpty(entity.getGradeOrgId())){
                    resultList.add(new ValidMessage("",
                        getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.gradeOrgId"))));
                    break;
                }
//                MstBondCreditRating newEntity = new MstBondCreditRating();
//                newEntity.setGradeOrgId(entity.getGradeOrgId());
//                newEntity.setGradeDate(entity.getGradeDate());
//                newEntity.setGradeType(entity.getGradeType());
//                增加check 同一评级机构、评级日期、评级类型只能有一条数据，不能重复。
//                原检验逻辑 未重写hsahcode 方法导致校验失败
                StringBuffer sb = new StringBuffer();
                sb.append(entity.getGradeOrgId()).append(GlobalConstant.VERTICAL_LINE).append(DateUtil.format(entity.getGradeDate())).append(GlobalConstant.VERTICAL_LINE).append(entity.getoType());
                if(!keySet.add(sb.toString())){
                    resultList.add(new ValidMessage("",
                            getMessage("FIN_ASSET_E_0018")));
                    break;
                }

//                if(!set.add(newEntity)){
//                    resultList.add(new ValidMessage("",
//                        getMessage("FIN_ASSET_E_0018")));
//                }
            }
        }/*else{
            resultList.add(new ValidMessage("",
                getMessage("COMMON_E_0057", getMessage("BusiFinBondRatingInfo.title"))));
        }*/
        return resultList;
    }

}
