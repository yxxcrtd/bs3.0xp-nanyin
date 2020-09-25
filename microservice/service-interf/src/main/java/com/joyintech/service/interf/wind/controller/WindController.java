/*
 * 文件名：WindController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.cache.InterfCacheUtil;
import com.joyintech.service.interf.core.util.ServiceMapperUtil;
import com.joyintech.service.interf.dobatch.dto.BatchResultDto;
import com.joyintech.service.interf.wind.service.WindService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * wind数据接入Conroller
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月13日
 * @see WindController
 * @since 1.0
 */
@RestController
public class WindController {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * wind数据接入量控制参数
     */
    private static final String WIND_SEL_NUM = "WIND_SEL_NUM";

    /**
     * wind数据接入入口
     * 
     * @param dto 参数
     * @return 处理结果
     * @see
     * @since 1.0
     */
    @RequestMapping("/batchConsumer/windBatch")
    public List<BatchResultDto> windBatch(@RequestBody BatchResultDto dto) {
        logger.info("{}处理开始...", dto.getBatchName());
        List<BatchResultDto> resultList = new ArrayList<>();
        WindService<?> service = getService(dto.getBatchName());
        if (service == null) {
            dto.setResult(false);
            dto.setErrMsg(dto.getBatchName() + "对应的Service不存在");
            resultList.add(dto);
            return resultList;
        }
        String uuid = IDGenerator.generate();
        service.init(uuid);
        long selNum = getSelNum();
        long start = 0;
        long end = start + selNum;
        String sysDate = service.getSysDate();
        while (true) {
            List<?> dataList = service.getDataList(sysDate, start, end);
            BatchResultDto result = service.executeService(dto, dataList);
            resultList.add(result);
            start = end;
            end = start + selNum;
            // 如果行数不足指定的量则表示后续没有数据，跳出循环
            if (dataList.size() < selNum) {
                break;
            }
        }
        service.addInterfLog(dto.getBatchId(), dto.getBatchId(),
            DictCodeList.是否_否.getValue(), StringUtil.EMPTY, StringUtil.EMPTY);
        logger.info("{}处理结束...", dto.getBatchName());
        return resultList;
    }

    /**
     * 取得wind数据接入量控制参数
     * 
     * @return wind数据接入量控制参数
     */
    private long getSelNum() {
        String selNumStr = InterfCacheUtil.getSysParam(WIND_SEL_NUM);
        if (StringUtil.isEmpty(selNumStr)) {
            return 10000;
        }
        try {
            return Long.parseLong(selNumStr);
        }
        catch (NumberFormatException e) {
            return 10000;
        }
    }

    /**
     * 取得 接口处理Service
     * 
     * @param key batch名
     * @return 接口处理Service
     * @see
     * @since 1.0
     */
    public WindService<?> getService(String key) {
        return ServiceMapperUtil.getWindService(key);
    }
}
