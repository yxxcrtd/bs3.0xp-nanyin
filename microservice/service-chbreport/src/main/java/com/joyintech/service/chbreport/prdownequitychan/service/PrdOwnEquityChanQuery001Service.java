package com.joyintech.service.chbreport.prdownequitychan.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PrdOwnEquityChan;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import com.joyintech.tams.svreport.chbreport.prdownequitychan.dao.PrdOwnEquityChanQuery001Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see PrdOwnEquityChanQuery001Service
 * @since 1.0
 */
@Service
public class PrdOwnEquityChanQuery001Service extends BaseService {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(PrdOwnEquityChanQuery001Service.class);

    @Autowired
    private PrdOwnEquityChanQuery001Mapper PrdOwnEquityChanQuery001Mapper;

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
                    pagenum, length, () -> PrdOwnEquityChanQuery001Mapper.selectAllData(pageParam));
            return page;
        } else {
            Page<ChbreportPrdInfoDto> page = queryForPage(PrdOwnEquityChanQuery001Mapper.class, "selectAllData", pageParam);
            return page;

        }
    }

    /**
     * 查询产品所有者权益变动表导出数据
     *
     * @param pageParam 筛选条件
     * @return 列表信息
     * @see
     * @since 1.0
     */
    public List<PrdOwnEquityChan> selectPrdOwnEquityChanList(Map<String, Object> pageParam) throws Exception {
        return PrdOwnEquityChanQuery001Mapper.selectPrdOwnEquityChanList(pageParam);
    }
}
