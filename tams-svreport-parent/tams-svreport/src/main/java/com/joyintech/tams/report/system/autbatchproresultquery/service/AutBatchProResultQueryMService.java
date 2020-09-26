package com.joyintech.tams.report.system.autbatchproresultquery.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.report.system.autbatchproresultquery.dto.AutBatchProResultQuery001Dto;

/**
 * 自动报送批处理
 * @author zhangzhongliang
 * @version 1.0 2019年4月19日
 * @see AutBatchProResultQueryMService
 * @since 1.0
 */
@Service
public class AutBatchProResultQueryMService extends BaseService<InvestorsHoldInfoRegist001Dto>{
    /**
     *
     * 查询自动批处理日志结果列表
     * @return 结果列表，分页显示
     */
    public Page<AutBatchProResultQuery001Dto> queryBatchProResult(@RequestBody Map<String, Object> param) {
    	return restTemplate.postForPage(
	       		SvrServiceApplication.COMMON_SERVICE_NAME + "/AutBatchProResultQuery/findPage", param,
	       		AutBatchProResultQuery001Dto.class);
    }
}
