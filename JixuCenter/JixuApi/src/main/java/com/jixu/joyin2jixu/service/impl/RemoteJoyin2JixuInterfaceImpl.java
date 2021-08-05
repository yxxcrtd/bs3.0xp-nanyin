package com.jixu.joyin2jixu.service.impl;

import com.jixu.api.util.RestUrlUtil;
import com.jixu.common.apiconst.ApiPathConst;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.ResultInterface;
import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
public class RemoteJoyin2JixuInterfaceImpl implements Joyin2JixuInterface {

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
        return restTemplate.postForObject(RestUrlUtil.apiUrl(ApiPathConst.CREATE_PORTFOLIOS), dtoList, ResultInterface.class);
    }

    @Override
    public ResultInterface updateBkCashAccts(List<JJBkCashAcctDto> dtoList) {
        return restTemplate.postForObject(RestUrlUtil.apiUrl(ApiPathConst.UPDATE_BK_CASH_ACCT), dtoList, ResultInterface.class);
    }

    @Override
    public List<SelectOptionDto> getSecurityDtoByAssetType(String... assetType) {
        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>();
        for (String s : assetType) {
            valueMap.add("assetType[]", s);
        }
        return restTemplate.postForList(RestUrlUtil.apiUrl(ApiPathConst.SECURITY_OPTIONS), valueMap, SelectOptionDto.class);
    }

    private ResultContainer executeJixuMessage(boolean withTx, String jsonMsg) {
        Map<String, Object> request = new HashMap<>(1);
        request.put("jsonMsg", jsonMsg);
        request.put("withTx", withTx);
        return restTemplate.postForObject(RestUrlUtil.apiUrl(ApiPathConst.EXECUTE_MESSAGE), request, ResultContainer.class);
    }
}
