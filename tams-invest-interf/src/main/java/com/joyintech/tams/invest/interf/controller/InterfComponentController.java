/*
 * 文件名：InterfConponentController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.tams.invest.interf.dto.EsbReqRspDto;
import com.joyintech.tams.invest.interf.service.InterfComponentService;

/**
 * 接口组件Controller
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月24日
 * @see InterfComponentController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/interf")
public class InterfComponentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InterfComponentService interfComponentService;

    /**
     * UTF-8
     */
    private static final String UTF_8 = "UTF-8";

    @ResponseBody
    @RequestMapping("doEsbData")
    public String doEsbData(@RequestBody String request, HttpServletResponse rsp)
        throws IOException {
        try {
            logger.info("==========doEsbData start==========");

            if (StringUtil.isEmpty(request)) {
                throw new NullPointerException("请求报文为空");
            }

            String rspStr = null;

            // 1、读请求报文
            String reqStr = URLDecoder.decode(request, UTF_8);

            logger.info("==========doEsbData request==========\r\n{}", reqStr);

            // 详细处理
            EsbReqRspDto rspEntity = interfComponentService.sendEsbRequest(reqStr);
            if (null != rspEntity) {
                rspStr = rspEntity.getRspStr();
            }
            logger.info("==========doEsbData response==========\r\n{}", rspStr);

            // 5、写响应报文
            this.writeContent(rsp, rspStr);
            logger.info("==========doEsbData end==========");
            return rspStr;
        }
        catch (Exception e) {
            logger.info("==========doEsbData Exception==========\r\n {}", e.getMessage());
        }
        return null;
    }

    /**
     * 功能：写入响应报文
     * 
     * @param response 应答体
     * @param rspStr 应答字符串
     */
    private void writeContent(HttpServletResponse response, String rspStr)
        throws IOException {
        OutputStream stream = null;
        try {
            stream = response.getOutputStream();
            stream.write(rspStr.getBytes(UTF_8));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 功能 : 读取请求报文
     * 
     * @param request 请求
     * @return String 请求字符串
     * @throws IOException IO异常
     */
    // private String readReqData(HttpServletRequest request) {
    // String inputLine = null;
    // // 接收到的数据
    // StringBuilder recieveData = new StringBuilder();
    // try (BufferedReader in = new BufferedReader(
    // new InputStreamReader(request.getInputStream(), "UTF-8"));) {
    // while ((inputLine = in.readLine()) != null) {
    // recieveData.append(inputLine);
    // }
    // }
    // catch (IOException e) {
    // logger.error(e.getMessage());
    // }
    // return recieveData.toString();
    // }
}
