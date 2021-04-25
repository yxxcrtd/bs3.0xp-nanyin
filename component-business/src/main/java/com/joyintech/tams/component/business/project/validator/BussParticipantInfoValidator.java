/*
 * 文件名：BussParticipantInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.validator;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 参与方信息--业务验证
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月7日
 * @see BussParticipantInfoValidator
 * @since 1.0
 */
@Component
public class BussParticipantInfoValidator implements BeanValidator{

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussParticipantInfoDto dto = (BussParticipantInfoDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if(!StringUtil.equals(dto.getType6(), DictCodeList.项目类一级分类_理财直接融资工具.getValue())){
            if(null!=dto.getBussParticipantInfoDtoList()&&!dto.getBussParticipantInfoDtoList().isEmpty()){
                for(BussParticipantInfoDto entity:dto.getBussParticipantInfoDtoList()){
                    boolean isBreak = false;
                    if(StringUtil.isEmpty(entity.getCounterId())){
                        isBreak= true;
                        resultList.add(new ValidMessage("counterId",
                            getMessage("COMMON_E_0057", getMessage("COMMON_E_0057_BAK",getMessage("invest.channelId")))));
                    }
                    /*if(null==entity.getClientYield()){
                        isBreak= true;
                        resultList.add(new ValidMessage("clientYield",
                            getMessage("COMMON_E_0057", getMessage("BussParticipantInfo.clientYield"))));
                    }
                    if(null==entity.getManagerYield()){
                        isBreak= true;
                        resultList.add(new ValidMessage("managerYield",
                            getMessage("COMMON_E_0057", getMessage("BussParticipantInfo.managerYield"))));
                    }
                    if(null==entity.getOtherYield()){
                        isBreak= true;
                        resultList.add(new ValidMessage("otherYield",
                            getMessage("COMMON_E_0057", getMessage("BussParticipantInfo.otherYield"))));
                    }*/
                    if(isBreak){
                        break;
                    }
                }
            }
        }
        //add by zhangnian 20191009 start
        //存款类通道信息只能录入一条
        if(StringUtil.equals(dto.getFinprodType2(),DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
            if(null!=dto.getBussParticipantInfoDtoList()&&!dto.getBussParticipantInfoDtoList().isEmpty()){
                if(dto.getBussParticipantInfoDtoList().size()>1){
                    resultList.add(new ValidMessage("",
                            getMessage("BUSS_COM_E_0059")));
                }
            }
        }
        //add by zhangnian 20191009 end
        //add by zhangnian  20191230  start
        //标的按买断利率或者净投资收益率计提 不能挂一次性划款
        String isCalLoadRate =  CacheUtil.getSysParam("IS_CAL_LOADRATE");
        if((StringUtil.equals(isCalLoadRate,"02")&&StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_存款.getValue()))
                ||StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())
                ||StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            if(null!=dto.getBussParticipantInfoDtoList()&&!dto.getBussParticipantInfoDtoList().isEmpty()){
                for(BussParticipantInfoDto item : dto.getBussParticipantInfoDtoList()){
                    if(StringUtil.equals(item.getPayType(), DictCodeListExt2.划款方式_一次性划款.getValue())){
                        resultList.add(new ValidMessage("",
                                getMessage("按买断利率或者净投资收益率计提不能挂一次性划款")));
                        break;
                    }
                }
            }
        }
        // add by etliu 20210421 权益类资产相关代码合并 start
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue())){
            if(null!=dto.getBussParticipantInfoDtoList()&&!dto.getBussParticipantInfoDtoList().isEmpty()){
                for(BussParticipantInfoDto item : dto.getBussParticipantInfoDtoList()){
                    if(StringUtil.equals(item.getPayType(), DictCodeListExt2.划款方式_一次性划款.getValue())){
                        resultList.add(new ValidMessage("",
                                getMessage("该资产不能挂一次性划款")));
                        break;
                    }
                }
            }
        }
        // add by etliu 20210421 权益类资产相关代码合并 end
        //add by zhangnian  20191230  start
        return resultList;
    }
}
