package com.jixu.joyin2jixu.service.impl;

import com.jixu.common.apiconst.ApiPathConst;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.ResultInterface;
import com.jixu.config.ServiceApplication;
import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 几许接口实现类<br>
 * 启动优化修改内容
 * @author 何阳阳
 * @date 2021-07-06
 */
@Lazy
@Service
public class Joyin2JixuInterfaceImpl implements Joyin2JixuInterface {

    @Autowired
    private JoyinRestTemplate restTemplate;

    @Override
    public ResultContainer executeJixuMessage(String jsonMsg) {
        return executeJixuMessage(true, jsonMsg);
    }

    @Override
    public ResultContainer executeJixuMessageNoTx(String jsonMsg) {
        return executeJixuMessage(false, jsonMsg);
    }

    @Override
    public ResultInterface createPortfolios(List<JJPortfolioDto> dtoList) {
        return restTemplate.postForObject(apiUrl(ApiPathConst.CREATE_PORTFOLIOS), dtoList, ResultInterface.class);
    }

    @Override
    public ResultInterface updateBkCashAccts(List<JJBkCashAcctDto> dtoList) {
        return restTemplate.postForObject(apiUrl(ApiPathConst.UPDATE_BK_CASH_ACCT), dtoList, ResultInterface.class);
    }

    @Override
    public List<SelectOptionDto> getSecurityDtoByAssetType(String... assetType) {
        return restTemplate.postForList(apiUrl(ApiPathConst.SECURITY_OPTIONS), assetType, SelectOptionDto.class);
    }

    private ResultContainer executeJixuMessage(boolean withTx, String jsonMsg) {
        Map<String, Object> request = new HashMap<>(1);
        request.put("jsonMsg", jsonMsg);
        request.put("withTx", withTx);
        return restTemplate.postForObject(apiUrl(ApiPathConst.API_PATH), request, ResultContainer.class);
    }

    private String apiUrl(String path) {
        return UriComponentsBuilder.fromHttpUrl(ServiceApplication.ADAPTER_SERVICE_NAME)
                .pathSegment(path).toString();
    }
}
