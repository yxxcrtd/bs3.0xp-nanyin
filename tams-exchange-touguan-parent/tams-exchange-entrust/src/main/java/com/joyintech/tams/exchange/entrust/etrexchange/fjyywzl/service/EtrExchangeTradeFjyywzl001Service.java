package com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.exchange.entrust.etrexchange.fjyywzl.dto.EtrExchangeTradeFjyywzlZqzyDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EtrExchangeTradeFjyywzl001Service extends CommonExportService<EtrExchangeTradeFjyywzlZqzyDto> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Page<EtrExchangeTradeFjyywzlZqzyDto> findZqzyPositionsPage(Map<String, Object> param) {
        try {
            String userId = SecurityUtil.getLoginUser().getUserName();
            if (StringUtils.isNotEmpty(userId)) {
                logger.info("请求用户【{}】", "userId = " + userId);
                param.put("userId", SecurityUtil.getLoginUser().getUserName());
                return restTemplate.postForPage(
                        ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/findZqzyPositionsPage", param,
                        EtrExchangeTradeFjyywzlZqzyDto.class);
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return null;
    }

    /**
     * 查询上交所和深交所标准券数量
     *
     * @param pageParam 页面参数
     * @return 上交所和深交所标准券数量
     * @see
     * @since 1.0
     */
    public Map<String, String> loadSecNum(Map<String, Object> pageParam) {
        Map<String, String> map = (Map<String, String>)restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/loadSecNum",
                pageParam, Map.class);
        return map;
    }

}
