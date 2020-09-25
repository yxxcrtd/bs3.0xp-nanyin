/*
 * 文件名：MstCaptialAccountValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstperformancebenchmark.validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.util.ArithmeticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.ComponentInfoDto;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.dto.MstPerformanceBenchMarkDto;
import com.joyintech.tams.invest.basicdata.mstperformancebenchmark.service.MstPerformanceBenchMarkService;

/**
 * 复合基准管理模块的check 〈功能详细描述〉
 * 
 * @author zhangjun
 * @version 1.0 2018年8月20日
 * @see MstPerformanceBenchMark001Validator
 * @since 1.0
 */
@Component
public class MstPerformanceBenchMark001Validator implements BeanValidator {

    /**
     * 基准信息管理service
     */
    @Autowired
    private MstPerformanceBenchMarkService mstPerformanceBenchMarkService;

    /**
     * 新建删除修改对数据进行校验
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        MstPerformanceBenchMarkDto dto = (MstPerformanceBenchMarkDto)value;
        List<ValidMessage> errorList = new ArrayList<>();
        // 点击【删除】按钮进行基准删除时，如果该基准已经挂在产品或资产上，则该基准不可删除，弹出提示框“该基准已应用产品中，不予删除！”
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            dto.setIndexId(dto.getSelectedItem().get(0).getIndexId());
            List<MstPerformanceBenchMarkDto> listDto = dto.getSelectedItem();
            if (listDto.size() > 1) {
                if (mstPerformanceBenchMarkService.checkAllCodeIsUse(listDto) > 0) {
                    errorList.add(new ValidMessage("indexName",
                        getMessage("MST_PARA_PERFORMANCE_BENCHMARKS_I_0001")));
                }
            }
            else {
                if (mstPerformanceBenchMarkService.checkCodeIsUse(dto) > 0) {
                    errorList.add(new ValidMessage("indexName",
                        getMessage("MST_PARA_PERFORMANCE_BENCHMARKS_I_0001")));
                }
            }
        }
        else {
            List<ComponentInfoDto> componentInfoList = new ArrayList<ComponentInfoDto>();
            componentInfoList = dto.getComponentInfo();
            // 判断传入的字段是否为空
            if (componentInfoList != null && componentInfoList.size() > 0) {
                int count0 = 0;
                for (ComponentInfoDto componentInfo : componentInfoList) {
                    if (StringUtil.isEmpty(componentInfo.getComponentId())
                        || StringUtil.isEmpty(componentInfo.getComponentType())
                        || componentInfo.getWeight() == null) {
                        count0++ ;
                    }
                }
                if (count0 > 0) {
                    errorList.add(
                        new ValidMessage("componentInfo", getMessage("COMMON_E_0057", "基准成分")));
                }
                else {
                    for (int i = 0; i < componentInfoList.size(); i++ ) {
                        for (int j = i + 1; j < componentInfoList.size(); j++ ) {
                            if (componentInfoList.size() > 1) {
                                // 判断多行基准成分不相同
                                if (componentInfoList.get(i).getComponentId().equals(
                                    componentInfoList.get(j).getComponentId())
                                    && componentInfoList.get(i).getComponentType().equals(
                                        componentInfoList.get(j).getComponentType())) {
                                    errorList.add(new ValidMessage("",
                                        getMessage("BUSS_COM_I_0017", "行情代码")));
                                }
                            }
                        }
                    }
                }
            }
            else {
                errorList.add(
                    new ValidMessage("componentInfo", getMessage("COMMON_E_0057", "基准成分")));
            }
            if (componentInfoList != null && componentInfoList.size() > 0) {
                BigDecimal cont = new BigDecimal(0);
                for (ComponentInfoDto entity : componentInfoList) {
                    if (entity.getWeight() != null) {
                        cont = ArithmeticUtil.round(entity.getWeight(),8, RoundingMode.HALF_UP).add(cont);
                    }
                    else {
                        errorList.add(new ValidMessage(getMessage("MST_PERFORMANCE_1")));
                        return errorList;
                    }
                }
//                BigDecimal a = cont.setScale(0, BigDecimal.ROUND_HALF_DOWN);
//                BigDecimal b = new BigDecimal(1);
//                b = b.setScale(0, BigDecimal.ROUND_HALF_DOWN);
                if (cont.compareTo(BigDecimal.valueOf(1))!=0) {
                    errorList.add(new ValidMessage(getMessage("MST_PERFORMANCE_2")));
                }
            }
            // 数据库中基准名称的数目
            int count = mstPerformanceBenchMarkService.selectByIndexName(dto);
            if (count > 0) {
                errorList.add(
                    new ValidMessage("indexName", getMessage("BUSS_COM_I_0017", "基准名称")));
            }
        }

        return errorList;
    }

}
