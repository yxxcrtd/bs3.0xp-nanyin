package com.joyintech.service.report.poc.portfolio.ptlmockoptionalassets.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.poc.portfolio.ptlmockoptionalassets.dao.PtlMockOptionAlassetsMapper;
import com.joyintech.service.report.poc.portfolio.ptlmockoptionalassets.dto.PtlMockOptionAlassetsDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 模拟试算可选资产
 * 
 * @author wl
 * @version 1.0 2020/04/19
 * @see PtlMockOptionAlassetsService
 * @since 1.0
 */
@Service
public class PtlMockOptionAlassetsService extends BaseService<PtlMockOptionAlassetsDto> {

    /**
     * PtlMockOptionAlassetsMapper
     */
    @Autowired
    private PtlMockOptionAlassetsMapper ptlmockoptionalassetsMapper;

    /**
     * IPlatformDate
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 画面查询方法
     * @param map 请求参数
     * @param page 页面
     * @param length 长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    public Page<PtlMockOptionAlassetsDto> selectForPage(int page, int length,
                                                        Map<String, Object> map)
        throws Exception {
        map.put("cdate", iPlatformDate.getSysDateStr());
        // 查询组合添加权限限制
        map.put("loginUserName", LoginUserUtil.getUserName());
        return super.queryForPage(page, length,
            () -> ptlmockoptionalassetsMapper.selectForPage(map));
    }

    /**
     * 资产代码下拉框列表
     * @param map 请求参数
     * @return 资产代码下拉框列表
     */
    public List<SelectOptionDto> getFinprodId(Map<String, Object> map) {
        map.put("finprodType2",
            DictCodeList.平安资产类型_债券.getValue() + "," + DictCodeList.平安资产类型_计划类资产_利率型.getValue()
                                + "," + DictCodeList.平安资产类型_项目类资产.getValue());
        map.put("finprodType2",
            StringUtil.splitToList(GlobalConstant.COMMA, (String)map.get("finprodType2")));
        return ptlmockoptionalassetsMapper.getFinprodId(map);
    }
}
