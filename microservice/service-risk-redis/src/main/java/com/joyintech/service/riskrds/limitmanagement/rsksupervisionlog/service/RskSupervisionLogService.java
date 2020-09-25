package com.joyintech.service.riskrds.limitmanagement.rsksupervisionlog.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionlog.dao.RskSupervisionLogMapper;
import com.joyintech.service.riskrds.limitmanagement.rsksupervisionlog.dto.RskSupervisionLogDto;

/**
 * 指数参数管理Service
 *
 * @author 裴家俊
 * @version 1.0 2018年10月23日
 * @see RskSupervisionLogService
 * @since 1.0
 */
@Service("backRskSupervisionLogService")
public class RskSupervisionLogService extends BaseService<RskSupervisionLogDto> {

    /**
     * 指数日志管理mapper
     */
    @Autowired
    private RskSupervisionLogMapper rskSupervisionLogMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * 
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<RskSupervisionLogDto> selectForPage(int page, int length,
                                                    Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskSupervisionLogMapper.selectLog(pageParam));
    }

}
