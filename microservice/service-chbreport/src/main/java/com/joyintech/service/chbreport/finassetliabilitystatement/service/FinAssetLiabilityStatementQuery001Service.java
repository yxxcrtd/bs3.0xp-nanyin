package com.joyintech.service.chbreport.finassetliabilitystatement.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dao.FinAssetLiabilityStatementQuery001Mapper;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.FinAssetLiabilityStatementQuery001Dto;
import com.joyintech.tams.svreport.chbreport.finassetliabilitystatement.dto.PrdBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author WanShiWei
 * @version 1.0 2021年04月19日
 * @see FinAssetLiabilityStatementQuery001Service
 * @since 1.0
 */
@Service
public class FinAssetLiabilityStatementQuery001Service extends BaseService {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(FinAssetLiabilityStatementQuery001Service.class);

    @Autowired
    private FinAssetLiabilityStatementQuery001Mapper finAssetLiabilityStatementQuery001Mapper;

    /**
     * 查询产品代码和产品名称
     *
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPrdBaseinfo(Map<String, Object> map) {
        return finAssetLiabilityStatementQuery001Mapper.selectAllPrdBaseInfo(map);
    }

    /**
     * 根据条件获取表格信息
     *
     * @param pageParam 筛选条件
     * @return 表格信息
     * @see
     * @since 1.0
     */
    public Page<FinAssetLiabilityStatementQuery001Dto> selectForPage(Map<String, Object> pageParam) throws Exception {
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
            Page<FinAssetLiabilityStatementQuery001Dto> page = super.queryForPage(
                    pagenum, length, () -> finAssetLiabilityStatementQuery001Mapper.selectAllData(pageParam));
            return page;
        } else {
            Page<FinAssetLiabilityStatementQuery001Dto> page = queryForPage(FinAssetLiabilityStatementQuery001Mapper.class, "selectAllData", pageParam);
            return page;

        }
    }

    /**
     * 查询产品资产负债表导出数据
     *
     * @param pageParam 筛选条件
     * @return 列表信息
     * @see
     * @since 1.0
     */
    public List<PrdBalance> selectFinAssetLiabilityList(Map<String, Object> pageParam) throws Exception {
        return finAssetLiabilityStatementQuery001Mapper.selectFinAssetLiabilityData(pageParam);
    }
}
