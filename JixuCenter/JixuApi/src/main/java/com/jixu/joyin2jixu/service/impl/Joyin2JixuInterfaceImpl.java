package com.jixu.joyin2jixu.service.impl;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.type.ResultInterface;
import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;
import com.joyintech.framework.JoyinRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResultContainer executeJixuMessageNoTx(String jsonMsg) {
        //TODO 通过restTemplate调用几许服务
//        ServiceApplication.JIXU_SERVICE_NAME;
        return null;
    }

    @Override
    public ResultInterface createPortfolios(List<JJPortfolioDto> dtoList) {
        //TODO 通过restTemplate调用几许服务
        return null;
    }

    @Override
    public ResultInterface updateBkCashAccts(List<JJBkCashAcctDto> dtoList) {
        //TODO 通过restTemplate调用几许服务
        return null;
    }
}
