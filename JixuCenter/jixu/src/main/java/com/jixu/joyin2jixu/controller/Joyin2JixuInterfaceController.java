package com.jixu.joyin2jixu.controller;

import com.jixu.common.apiconst.ApiPathConst;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.ResultInterface;
import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;
import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 几许对外开放接口，供估值调用
 * @auth 何阳阳
 * @date 2021/7/7
 */
@RestController
@RequestMapping(ApiPathConst.API_PATH)
public class Joyin2JixuInterfaceController {

    @Autowired
    private Joyin2JixuInterface joyin2JixuInterface;

    @PostMapping(ApiPathConst.EXECUTE_MESSAGE)
    public ResultContainer executeJixuMessage(@RequestParam(name = "withTx", required = false) boolean withTx, @RequestParam("jsonMsg") String jsonMsg) {
        if(withTx) {
            return joyin2JixuInterface.executeJixuMessage(jsonMsg);
        }
        return joyin2JixuInterface.executeJixuMessageNoTx(jsonMsg);
    }

    @PostMapping(ApiPathConst.CREATE_PORTFOLIOS)
    public ResultInterface createPortfolios(@RequestBody List<JJPortfolioDto> dtoList) {
        return joyin2JixuInterface.createPortfolios(dtoList);
    }

    @PostMapping(ApiPathConst.UPDATE_BK_CASH_ACCT)
    public ResultInterface updateBkCashAccts(@RequestBody List<JJBkCashAcctDto> dtoList) {
        return joyin2JixuInterface.updateBkCashAccts(dtoList);
    }

    @PostMapping(ApiPathConst.SECURITY_OPTIONS)
    public List<SelectOptionDto> getSecurityDtoByAssetType(@RequestParam("assetType[]") String[] assetType){
        return joyin2JixuInterface.getSecurityDtoByAssetTypes(assetType);
    }
}
