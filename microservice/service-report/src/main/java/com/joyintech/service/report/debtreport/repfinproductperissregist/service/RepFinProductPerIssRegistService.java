/*
 * 文件名：RepFinProductPerIssRegistService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repfinproductperissregist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.debtreport.repfinproductperissregist.dao.RepFinProductPerIssRegistMapper;
import com.joyintech.service.report.debtreport.repfinproductperissregist.dto.RepFinProductPerIssRegistDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年11月20日
 * @see RepFinProductPerIssRegistService
 * @since 1.0
 */
@Service
public class RepFinProductPerIssRegistService extends BaseService<RepFinProductPerIssRegistDto> {
    @Autowired
    private RepFinProductPerIssRegistMapper repFinProductPerIssRegistMapper;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepFinprodName(Map<String, Object> param) {
        param.put("SALETARGET_DECLARE_05", DictCodeList.目标客户类型_一般个人客户.getValue());
        param.put("SALETARGET_DECLARE_06", DictCodeList.目标客户类型_高资产净值客户.getValue());
        return repFinProductPerIssRegistMapper.getRepFinprodName(param);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<RepFinProductPerIssRegistDto> selectForPage(int page, int length,
                                                            Map<String, Object> pageParam) throws Exception {
        pageParam.put("recruitmentSDate", pageParam.get("recruitmentSDate"));
        pageParam.put("recruitmentEDate", pageParam.get("recruitmentEDate"));
        pageParam.put("dateSDate", pageParam.get("dateSDate"));
        pageParam.put("dateEDate", pageParam.get("dateEDate"));
        String repFinprodName = (String)pageParam.get("repFinprodName");
        if(StringUtil.isNotEmpty(repFinprodName)){
            pageParam.put("repFinprodName", StringUtil.splitToList(GlobalConstant.COMMA,repFinprodName));
        }else{
            pageParam.put("repFinprodName",null);
        }
        pageParam.put("SALETARGET_DECLARE_05", DictCodeList.目标客户类型_一般个人客户.getValue());
        pageParam.put("SALETARGET_DECLARE_06", DictCodeList.目标客户类型_高资产净值客户.getValue());
        pageParam.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        return super.queryForPage(page, length, () -> repFinProductPerIssRegistMapper.select(pageParam));
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<RepFinProductPerIssRegistDto> selectForExport(int page, int length,
                                                              Map<String, Object> param) throws Exception {
        String repFinprodId = (String)param.get("repFinprodId");
        if(StringUtil.isNotEmpty(repFinprodId)){
            param.put("repFinprodId", StringUtil.splitToList(GlobalConstant.COMMA,repFinprodId));
        }else{
            param.put("repFinprodId",null);
        }
        param.put("YES_OR_NO_N", DictCodeList.是否_否.getValue());
        return super.queryForPage(page, length, () -> repFinProductPerIssRegistMapper.selectForExport(param));
    }

}
