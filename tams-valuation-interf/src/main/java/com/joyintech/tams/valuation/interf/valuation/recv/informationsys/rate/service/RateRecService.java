package com.joyintech.tams.valuation.interf.valuation.recv.informationsys.rate.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.interf.valuation.recv.informationsys.rate.dto.RateProcessorResult;

/**
 * /** 行情利率信息接口Service
 * 
 * @author dongxiangyang
 * @version 1.0 2018年7月06日
 * @see RateRecService
 * @since 1.0
 */

@Service
@Scope("prototype")
public class RateRecService {

    // /**
    // * 基础信息 模板处理Service
    // */
    // @Autowired
    // private InterestRateTemplateRecService interestRateTemplateRecService;

    public RateProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        RateProcessorResult rtnResult = new RateProcessorResult();
/*
        if (TempConstant.货币型产品存续期交易表.equals(acctEntity.getInterfName())) {
            // TODO
        }
        else if (TempConstant.货币型产品分红交易表.equals(acctEntity.getInterfName())) {
            // TODO
        }
        else if (TempConstant.货币型产品费用交易表.equals(acctEntity.getInterfName())) {
            // TODO
        }
        else {
            // TODO
        }*/
        // 设置启动源属性
        // TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        // templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        //
        // rtnResult=interestRateTemplateRecService.execute(templateSourceFlgDTO,acctEntity);

        return rtnResult;
    }

}
