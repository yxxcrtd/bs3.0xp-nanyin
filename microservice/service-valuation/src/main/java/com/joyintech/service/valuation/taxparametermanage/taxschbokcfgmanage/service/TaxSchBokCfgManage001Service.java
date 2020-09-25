/*
 * 文件名：TaxSchBokCfgManage001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dao.TaxSchBokCfgManage001Mapper;
import com.joyintech.service.valuation.taxparametermanage.taxschbokcfgmanage.dto.TaxSchBokCfgManage001Dto;

/**
 * 税务方案 - 主界面公用 
 * @author zhangwei
 * @version 1.0 2018年10月11日
 * @see TaxSchBokCfgManage001Service
 * @since 1.0
 */
@Service(value="serviceTaxSchBokCfgManage001Service")
@Transactional(rollbackFor = Exception.class)
public class TaxSchBokCfgManage001Service extends CommonExportService<TaxSchBokCfgManage001Dto>{
	
	@Autowired 
	private TaxSchBokCfgManage001Mapper taxSchBokCfgManage001Mapper;

    /**
     * 查询会计场景计税配置 - 分页
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<TaxSchBokCfgManage001Dto> findTaxSchaBokConfigByPage(int page, int length, Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page, length, ()-> taxSchBokCfgManage001Mapper.selectTaxSchaBokConfig(pageParam));
    }

    /**
     * 查询会计场景计税配置
     * @param pageParam
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<TaxSchBokCfgManage001Dto> findTaxSchaBokConfig(Map<String, Object> pageParam){
        return taxSchBokCfgManage001Mapper.selectTaxSchaBokConfig(pageParam);
    }

    /**
     * 统计配置使用数
     * @param configId
     * @return
     */
    public int countUsed(String configId) {
        return taxSchBokCfgManage001Mapper.countUsed(configId);
    }
}
