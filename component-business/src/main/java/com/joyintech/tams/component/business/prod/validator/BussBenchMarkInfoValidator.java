/*
 * 文件名：BussBenchMarkInfoValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 业绩基准业务组件验证类
 * 
 * @author zhangliang
 * @version 1.0 2018年10月17日
 * @see BussBenchMarkInfoValidator
 * @since 1.0
 */
@Component
public class BussBenchMarkInfoValidator implements BeanValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        BussBenchMarkInfoDto bussBenchMarkInfoDto = (BussBenchMarkInfoDto)value;

        List<ValidMessage> validList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(bussBenchMarkInfoDto.getPageId(), "FinProductBuild002")) {
            if (StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType())) {
                if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getResetType())) {
                    validList.add(new ValidMessage("resetType",
                        getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                }
            }
            if (StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType())) {
                if (StringUtil.equals(bussBenchMarkInfoDto.getRateType(),
                    DictCodeList.利率类型_固定利率.getValue())) {
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRate())) {
                        validList.add(new ValidMessage("rate",
                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rate"))));
                    }
                }
                else if (StringUtil.equals(bussBenchMarkInfoDto.getRateType(),
                    DictCodeList.利率类型_浮动利率.getValue())) {
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getIndexId())) {
                        validList.add(new ValidMessage("indexId", getMessage("BUSS_COM_E_0038",
                            getMessage("BussBenchMarkInfo.indexId"))));
                    }
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getCoefficient())) {
                        validList.add(new ValidMessage("coefficient", getMessage("BUSS_COM_E_0038",
                            getMessage("BussBenchMarkInfo.msg.coefficient"))));
                    }
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getSpreadRate())) {
                        validList.add(new ValidMessage("spreadRate", getMessage("BUSS_COM_E_0038",
                            getMessage("BussBenchMarkInfo.msg.spreadRate"))));
                    }
                }
            }
        }else if (StringUtil.equals(bussBenchMarkInfoDto.getPageId(), "FinNetValueProductBuild002")
        		|| StringUtil.equals(bussBenchMarkInfoDto.getPageId(), "FinProductBmQuotation002"))  {
            if (StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType())
            		&&StringUtil.equals(bussBenchMarkInfoDto.getRateType(), DictCodeList.利率类型_浮动利率.getValue())) {
                if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getResetType())) {
                    validList.add(new ValidMessage("resetType",
                        getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                }
            }
            if (StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateType())) {
            	
            	if (StringUtil.equals(bussBenchMarkInfoDto.getRateType(),
                    DictCodeList.利率类型_固定利率.getValue())) {
//            		if (!DictCodeListExt1.披露类型_单值披露.getValue().equals(bussBenchMarkInfoDto.getPublishType())) {
                		
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRate())) {
                        validList.add(new ValidMessage("rate",
                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rate"))));
                    }
                    //delete by wm 2019-10-16 对于产品披露时固定的业绩基准重置不需要填写 start
                    /** add by yanya on 20190930 净值化产品业绩基准选择固定利率时业绩基准重置规则改为必填 start**/
//                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getResetType())) {
//                        validList.add(new ValidMessage("resetType",
//                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
//                    }
                    /** add by yanya on 20190930 净值化产品业绩基准选择固定利率时业绩基准重置规则改为必填 end**/
                    //delete by wm 2019-10-16 对于产品披露时固定的业绩基准重置不需要填写 end
//                	}
//            		else {
//            			if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRate())) {
//                            validList.add(new ValidMessage("rate",
//                                getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rate"))));
//                        }
//            		}
                }
                else if (StringUtil.equals(bussBenchMarkInfoDto.getRateType(),
                    DictCodeList.利率类型_浮动利率.getValue())) {
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getIndexId())) {
                        validList.add(new ValidMessage("indexId", getMessage("BUSS_COM_E_0038",
                            getMessage("BussBenchMarkInfo.indexId"))));
                    }
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getCoefficient())) {
                        validList.add(new ValidMessage("coefficient", getMessage("BUSS_COM_E_0038",
                            getMessage("BussBenchMarkInfo.msg.coefficient"))));
                    }
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getSpreadRate())) {
                        validList.add(new ValidMessage("spreadRate", getMessage("BUSS_COM_E_0038",
                            getMessage("BussBenchMarkInfo.msg.spreadRate"))));
                    }
                    if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getResetType())) {
                        validList.add(new ValidMessage("resetType",
                            getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.resetType"))));
                    }
                }
            }
            
            if (DictCodeListExt1.披露类型_区间披露.getValue().equals(bussBenchMarkInfoDto.getPublishType())) {            	               		
                if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateLower())) {
                    validList.add(new ValidMessage("rateLower",
                        getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rateLower"))));
                }
                if (!StringUtil.isNotEmpty(bussBenchMarkInfoDto.getRateLimit())) {
                    validList.add(new ValidMessage("rateLimit",
                        getMessage("BUSS_COM_E_0038", getMessage("BussBenchMarkInfo.rateLimit"))));
                }
                	                
            }

        }

        return validList;
    }

}
