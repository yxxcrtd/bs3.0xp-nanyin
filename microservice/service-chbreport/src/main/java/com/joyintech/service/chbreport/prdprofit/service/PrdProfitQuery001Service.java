package com.joyintech.service.chbreport.prdprofit.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PrdProfit;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.prdprofit.dao.PrdProfitQuery001Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see PrdProfitQuery001Service
 * @since 1.0
 */
@Service
public class PrdProfitQuery001Service extends BaseService {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(PrdProfitQuery001Service.class);

    @Autowired
    private PrdProfitQuery001Mapper prdProfitQuery001Mapper;

    /**
     * 根据条件获取表格信息
     *
     * @param pageParam 筛选条件
     * @return 表格信息
     * @see
     * @since 1.0
     */
    public Page<ChbreportPrdInfoDto> selectForPage(Map<String, Object> pageParam) throws Exception {
        // 查询参数条件
        // 将产品代码当做多选下拉框处理
        String prodId = (String) pageParam.get("prodId");
        if (StringUtil.isNotBlank(prodId)) {
            pageParam.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, prodId));
        }
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null) {
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int length = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
            Page<ChbreportPrdInfoDto> page = super.queryForPage(
                    pagenum, length, () -> prdProfitQuery001Mapper.selectAllData(pageParam));
            return page;
        } else {
            Page<ChbreportPrdInfoDto> page = queryForPage(PrdProfitQuery001Mapper.class, "selectAllData", pageParam);
            return page;

        }
    }

    /**
     * 查询产品利润表导出数据
     *
     * @param pageParam 筛选条件
     * @return 列表信息
     * @see
     * @since 1.0
     */
    public List<PrdProfit> selectPrdProfitList(Map<String, Object> pageParam) throws Exception {
        return prdProfitQuery001Mapper.selectPrdProfitList(pageParam);
    }
}
