package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

/**
 * 名单管理流程--service
 * @author  zhangnian
 * @version 1.0 2019年07月22日
 * @see AssetNameManagement002Service
 * @since 1.0
 */
@Service
public class AssetNameManagement002Service extends CommonExportService<RskAccessListDto>
    implements BaseProcessService<RskAccessListDto> {

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(RskAccessListDto entity, OperationType type) {
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
            ServiceApplication.RISK_SERVICE_NAME + "/AssetNameManagement/" + action, entity,
            Integer.class);

    }



}
