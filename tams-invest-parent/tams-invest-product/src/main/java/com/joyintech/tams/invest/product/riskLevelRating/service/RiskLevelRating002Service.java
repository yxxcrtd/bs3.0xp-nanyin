package com.joyintech.tams.invest.product.riskLevelRating.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.riskLevelRating.dto.RiskLevelRating001Dto;
import com.joyintech.tams.invest.product.riskLevelRating.dto.RiskLevelRating002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * @program: RiskLevelRating002
 * @description: 产品风险评定新建
 * @author: jyl
 * @create: 2020-01-04 11:43
 **/
@Service
public class RiskLevelRating002Service extends BaseService<RiskLevelRating001Dto> implements BaseProcessService<RiskLevelRating001Dto> {
    /**
     * 资产评定列表查询
     *
     * @param param
     * @return RiskLevelRating002Dto
     */
    public RiskLevelRating002Dto findList(Map<String, Object> param) {
        RiskLevelRating002Dto dto = restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/findList",
                param, RiskLevelRating002Dto.class);
        return dto;
    }

    /**
     * 校验单产品某一评定日期是否存在数据
     *
     * @param param
     * @return String
     */
    public String onlyOne(Map<String, Object> param) {
        int count = restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME +
                "/RiskLevelRating/onlyOne", param, Integer.class);
        if (count > 0) {
            return "Y";
        }
        return "N";
    }

    @Override
    public void doComplete(RiskLevelRating001Dto dto, OperationType operationType) {
        if (operationType.name() == "NEW") {
            restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/insertRisk", dto, Integer.class);

        } else if (operationType.name() == "MODIFY") {
            restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/updateRiskLevel", dto, Integer.class);

        } else {
            restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/deleteRiskLevel?seqNo=" + dto.getSeqNo(), Integer.class);

        }

    }

    /**
     * 获取流水号
     * @param param
     * @return String
     */
    public String selectNo(Map<String, Object> param) {
        String seNo = restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/selectNo", param, String.class);
        return seNo;
    }
    /**
     * 获取调整后资产风险等级列表
     * @param seqNo
     * @return List
     */
     public List<RiskLevelRating002Dto> selectAjustRisk(String seqNo){
         return restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/selectAjustRisk?seqNo="+seqNo,RiskLevelRating002Dto.class);
     }
    /**
     * 点击产品风险等级评定，产品风险等级=∑（调整后）资产风险等级*每个资产的集中度。
     *
     * @param dto
     * @return String
     */
    public String prodRisk(RiskLevelRating002Dto dto){
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/RiskLevelRating/prodRisk",dto,String.class);
    }
}
