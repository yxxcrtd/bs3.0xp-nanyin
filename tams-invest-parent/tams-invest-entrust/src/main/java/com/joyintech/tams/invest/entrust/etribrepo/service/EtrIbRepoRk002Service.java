package com.joyintech.tams.invest.entrust.etribrepo.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoRk004Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2019年1月11日
 * @see EtrIbRepoRk002Service
 * @since 1.0
 */
@Service
public class EtrIbRepoRk002Service extends CommonExportService<EtrIbRepoRk004Dto> 
        implements BaseProcessService<EtrIbRepoRk004Dto>{

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(EtrIbRepoRk004Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbRepoRk004/" + action, entity,
            Integer.class);
        
    }

    /**
     * <一句话功能简述>
     * 查看明细
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public EtrIbRepoRk004Dto getBusinessInfoByPledgeStockId(String pledgeStockId) {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                          + "/EtrIbRepoRk004/getBusinessInfoByPledgeStockId",
                                          pledgeStockId, EtrIbRepoRk004Dto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param empty
     * @param assetpoolName
     * @return
     * @see
     * @since 1.0
     */
    public int checkIsExistByPledgeStockName(String seqno, String pledgeStockName) {
        Map<String, Object> map = new HashMap<>();
        map.put("seqno", seqno);
        map.put("pledgeStockName", pledgeStockName);
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                          + "/EtrIbRepoRk004/checkIsExistByPledgeStockName",
            map, Integer.class);
    }

    /**
     * <一句话功能简述>
     * 判断是否关联授信客户编号
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public int checkisExistId(String seqno) {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                          + "/EtrIbRepoRk004/checkisExistId",
                                          seqno, Integer.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param seqno
     * @return
     * @see
     * @since 1.0
     */
    public int checkIsExistDetail(String pledgeStockId) {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME
            + "/EtrIbRepoRk004/checkIsExistDetail",
            pledgeStockId, Integer.class);
    }

}
