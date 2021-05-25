package com.joyintech.service.chbreport.hxprofit.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.common.base.entity.HxProfit;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.hxprofit.dao.HXProfitQuery001Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see HXProfitQuery001Service
 * @since 1.0
 */
@Service
public class HXProfitQuery001Service extends BaseService {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(HXProfitQuery001Service.class);

    @Autowired
    private HXProfitQuery001Mapper hxProfitQuery001Mapper;

    /**
     * 根据条件获取表格信息
     *
     * @param pageParam 筛选条件
     * @return 表格信息
     * @see
     * @since 1.0
     */
    public Page<ChbreportPrdInfoDto> selectForPage(Map<String, Object> pageParam) throws Exception {
        Page<ChbreportPrdInfoDto> page;
        pageParam.put("reportDate", ((String) pageParam.get("reportDate")).replace("-", ""));
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null) {
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int length = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
            page = super.queryForPage(
                    pagenum, length, () -> hxProfitQuery001Mapper.selectAllData(pageParam));
        } else {
            page = queryForPage(HXProfitQuery001Mapper.class, "selectAllData", pageParam);
        }
        return page;
    }

    /**
     * 查询华夏利润表导出数据
     *
     * @param pageParam 筛选条件
     * @return 列表信息
     * @see
     * @since 1.0
     */
    public List<HxProfit> selectHXProfitList(Map<String, Object> pageParam) throws Exception {
        return hxProfitQuery001Mapper.selectHxProfitData(pageParam);
    }
}
