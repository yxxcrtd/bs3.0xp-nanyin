
package com.joyintech.tams.invest.interf.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.interf.dto.InterfStatus;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 第三方接口接收和发送日志查询画面
 * @author xkni
 * @version 1.0 2020年2月18日
 * @see InterfaceLogService
 * @since 1.0
 */
@Service
public class InterfaceLogService extends BaseService {

    /**
     * 投管接口共通组件
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;

    /**
     * 根据条件查询日志结果画面
     *
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<InterfStatus> selectForPage(Map<String, Object> pageParam) {

        return restTemplate.postForPage(ServiceApplication.INVEST_INTERF_SERVICE_NAME + "/invest/interf/selectForPage", pageParam,
                InterfStatus.class);

    }

    /**
     * 再次发送
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public void restartSend(Map<String, Object> pageParam) {
         restTemplate.postForObject(ServiceApplication.INVEST_INTERF_SERVICE_NAME + "/invest/interf/reSendUtility", pageParam,
                Map.class);
    }
}
