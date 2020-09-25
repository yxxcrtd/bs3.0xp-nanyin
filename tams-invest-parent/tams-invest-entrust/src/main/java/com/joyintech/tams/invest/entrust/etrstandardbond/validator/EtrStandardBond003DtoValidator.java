/*
 * 文件名：TrdRepoTrade002DtoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrstandardbond.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.EtrSecPriorityDetail;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond003Dto;
import com.joyintech.tams.invest.entrust.etrstandardbond.service.EtrStandardBond001Service;

/**
 * 回购资产交易新建画面：EtrStandardBond002DtoValidator
 *
 * @author lizhi
 * @version 1.0 2019年12月26日
 * @see EtrStandardBond003DtoValidator
 * @since 1.0
 */
@Component
public class EtrStandardBond003DtoValidator implements BeanValidator {

    @Autowired(required = false)
    private EtrStandardBond001Service etrStandardBond001Service;
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        EtrStandardBond003Dto dto = (EtrStandardBond003Dto) value;
        List<ValidMessage> errorList = new ArrayList<>();

        List<EtrSecPriorityDetail> detailList = dto.getDetailList();
        // 校验操作的角色是否是投资处长
        Map<String,Object> map = new HashMap<>();
        map.put("role_pos", DictCodeListExt2.角色岗位_投资处长.getValue());
        Long count = etrStandardBond001Service.selectUserRolePosList(map) ;
        if(count < 1){
            errorList.add(new ValidMessage( this.getMessage("EtrStandardBond003.role",
                DictCodeListExt2.角色岗位_投资处长.getCodeName())));
        }
        
        // 判断一级分类是否有空数据
        for (EtrSecPriorityDetail etrSecPriorityDetail : detailList) {
            if(StringUtil.isEmpty(etrSecPriorityDetail.getWindtypeone())){
                ValidMessage validMessage = new ValidMessage("",getMessage("EtrStandardBond003.windtypeone"));
                errorList.add(validMessage); 
            }
        }
        
        // 判断分类是否存在重复数据
        for(int i=0 ; i<detailList.size()-1 ; i++){       
            for(int j=detailList.size()-1 ; j>i; j--){    
                EtrSecPriorityDetail tmp01 = detailList.get(j);
                EtrSecPriorityDetail tmp02 = detailList.get(i);
                if(tmp01.getWindtypeone() == tmp02.getWindtypeone())
                    //若一级分类相同，则判断二级分类是否相同,二级分类若是空也是重复数据
                    if(tmp01.getWindtypetwo() == tmp01.getWindtypetwo()
                        ||StringUtil.isEmpty(tmp01.getWindtypetwo())
                        ||StringUtil.isEmpty(tmp02.getWindtypetwo())){       
                        ValidMessage validMessage = new ValidMessage("",getMessage("EtrStandardBond003.repea"));
                        errorList.add(validMessage);      
                }        
            }        
        }
        return errorList;
    }
}
