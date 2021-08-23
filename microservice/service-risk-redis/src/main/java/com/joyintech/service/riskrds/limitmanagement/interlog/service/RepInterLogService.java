package com.joyintech.service.riskrds.limitmanagement.interlog.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.interlog.dao.RepInterLogMapper;
import com.joyintech.service.riskrds.limitmanagement.interlog.dto.RepInterLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Map;

/**
 * 接口日志管理
 * @author 郑俊平
 * @version 1.0 2019年9月4日
 * @see RepInterLogService
 * @since 1.0
 */
@Service("backRepInterLogService")
public class RepInterLogService extends BaseService<RepInterLogDto> {
    /**
     * 组合估值收益率偏离mapper
     */
    @Autowired
    private RepInterLogMapper repInterLogMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<RepInterLogDto> selectForPage(int page, int length, Map<String, Object> pageParam)
    {
        return super.queryForPage(page, length, () -> repInterLogMapper.select(pageParam));
    }
    
    public RepInterLogDto selectUuid(String uuid){
        // SNSTZJY-1002 modify by zhangrui1 start
        /**
    	return repInterLogMapper.selectUuid(uuid);
        */
        RepInterLogDto dto = repInterLogMapper.selectUuid(uuid);
        if (!StringUtil.isEmpty(dto.getResponseData())) {
            Encoder encoder = Base64.getEncoder();
            dto.setResponseData(encoder.encodeToString(dto.getResponseData().getBytes()));
        }
        return dto;
        // SNSTZJY-1002 modify by zhangrui1 end
    }
}
