package com.joyintech.service.riskrds.limitmanagement.interlog.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.interlog.dao.RepInterLogMapper;
import com.joyintech.service.riskrds.limitmanagement.interlog.dto.RepInterLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throws Exception {
        return super.queryForPage(page, length, () -> repInterLogMapper.select(pageParam));
    }
    
    public RepInterLogDto selectUuid(String uuid){
    	return repInterLogMapper.selectUuid(uuid);
    }
}
