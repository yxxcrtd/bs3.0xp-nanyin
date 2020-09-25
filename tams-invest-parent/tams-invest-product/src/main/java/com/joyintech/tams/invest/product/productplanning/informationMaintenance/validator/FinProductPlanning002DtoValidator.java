/*
 * 文件名：ProdDevelopManageValidator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.informationMaintenance.validator;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning002DetailDto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning002Dto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto.FinProductPlanning003Dto;
import com.joyintech.tams.invest.product.productplanning.informationMaintenance.service.FinProductPlanningEstablish002Service;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 新建时候的检测
 * @author daiguofu
 * @version 1.0 2019年12月28日
 * @see
 * @since 1.0
 */
@Component
public class FinProductPlanning002DtoValidator extends BaseService implements BeanValidator {

    @Autowired
    private FinProductPlanningEstablish002Service finProductPlanningEstablish002Service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        ArrayList<ValidMessage> errorList = new ArrayList<>();
        // （规划开始日和规划结束日、规划基准日需相同）
        FinProductPlanning002Dto dto = (FinProductPlanning002Dto) value;
        Map<String,Object> params = new HashMap<>();
        params.put("planningNo",dto.getPlanningNo());
        if(StringUtil.isEmpty(DateUtil.format(dto.getPlanningStartDate(),DateUtil.DEFAULT_DATE_FORMAT_EN))){
            errorList.add(new ValidMessage("pageId", "请输入规划开始日"));
        }
        if(StringUtil.isEmpty(DateUtil.format(dto.getPlanningEndDate(),DateUtil.DEFAULT_DATE_FORMAT_EN))){
            errorList.add(new ValidMessage("pageId", "请输入规划结束日"));
        }
        if(StringUtil.isEmpty(DateUtil.format(dto.getBaseDate(),DateUtil.DEFAULT_DATE_FORMAT_EN))){
            errorList.add(new ValidMessage("pageId", "请输入规划基准日"));
        }
        params.put("planningStartDate",DateUtil.format(dto.getPlanningStartDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
        params.put("planningEndDate",DateUtil.format(dto.getPlanningEndDate(),DateUtil.DEFAULT_DATE_FORMAT_EN));
        if(StringUtil.isEmpty(dto.getPlanningNo())) {
            Integer count = finProductPlanningEstablish002Service.getConflictCount(params);
            //如果开始日、结束日和Db中资产规划信息维护表相同，则提示数据库中已有改记录，请修改！
            if (count > 0) {
                errorList.add(new ValidMessage("pageId", "数据库中已有该记录，请修改"));
            }
        }
        //规划计划（规划开始日、规划结束日）区间不重叠
        if(StringUtil.isEmpty(dto.getPlanningNo())){
            Integer integer = finProductPlanningEstablish002Service.getOverlapCount(params);
            if(integer > 0){
                errorList.add(new ValidMessage("pageId","规划计划区间重叠,请修改"));
            }
        }
        List<FinProductPlanning003Dto> targets = finProductPlanningEstablish002Service.getTargetSize(params);
        if(CollectionUtils.isNotEmpty(targets)){
            FinProductPlanning003Dto defend = targets.get(0);
            BigDecimal sum = new BigDecimal("0");
            //获取规划目标值的和
            for (FinProductPlanning002DetailDto detailDto : dto.getDetailDtoList()) {
                if (detailDto.getPlanningTargetValue() != null) {
                    sum = sum.add(detailDto.getPlanningTargetValue());
                }
            }
            //判断规划目标值与各个产品目标规模大小
            if(sum != null && defend.getTargetScale() != null){
                if(ArithmeticUtil.multiply(sum,BigDecimal.valueOf(10000)).compareTo(defend.getTargetScale()) != -1){
                    errorList.add(new ValidMessage(getMessage("FIN_PRODUCT_PLANNING_F_002","")));
                }
            }
            //判断资产规划信息的基准日必须和产品规划信息的基准日是否相同
            if(defend.getBenchDate().compareTo(dto.getBaseDate()) != 0){
                errorList.add(new ValidMessage(getMessage("FIN_PRODUCT_PLANNING_F_003","")));
            }
        }
        return errorList;
    }

}
