
 
package com.joyintech.service.portfolio.ptlpositionanalysis.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.CommonAlgorithmUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.portfolio.ptlpositionanalysis.dao.PtlPositionAnalysisMapper;
import com.joyintech.service.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis003Dto;

import com.joyintech.tams.constant.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 组合持仓分析
 * @author wanghb
 * @version 1.0 2019年1月25日
 * @see PtlPositionReportService
 * @since 1.0
 */
@Service
public class PtlPositionReportService extends BaseService<PtlPositionAnalysis003Dto>{

    @Autowired
    private PtlPositionAnalysisMapper ptlPositionAnalysisMapper;

    /**
     * 组合持仓分析报表查询
     * @param page 第几页
     * @param length 长度
     * @return 结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlPositionAnalysis003Dto> secReportPage(int page, int length, Map<String, Object> pageParam) {
        if(StringUtil.isNotEmpty(pageParam.get("portfolioId"))){
            pageParam.put("portfolioIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("portfolioId")));
        }
        if(StringUtil.isNotEmpty(pageParam.get("profitType"))){
            pageParam.put("profitTypeList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("profitType")));
        }
        if(StringUtil.isNotEmpty(pageParam.get("prodManager"))){
            pageParam.put("prodManagerList", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        }
        if(StringUtil.isNotEmpty(pageParam.get("finprodMarketId"))){
            pageParam.put("finprodMarketIdList", StringUtil.splitToList(null, pageParam.get("finprodMarketId").toString()));
        }
        if(StringUtil.isNotEmpty(pageParam.get("finprodMarketId"))){
            pageParam.put("finprodMarketIdList", StringUtil.splitToList(null, pageParam.get("finprodMarketId").toString()));
        }
        // 下钻层级
        if(StringUtil.isNotEmpty(pageParam.get("deepLevel"))){
            pageParam.put("deepLevel", pageParam.get("deepLevel").toString());
        }
        //展示方式01：单组合汇总；02 多组合汇总
        if(StringUtil.isNotEmpty(pageParam.get("displayType"))){
            pageParam.put("displayType", pageParam.get("displayType").toString());
        }
        // 多级法人改造 add by hxy 20200702
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //当前用户所属机构代码
            String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
            List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
            pageParam.put("institutionCode",createInst);
            pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        }
        // 多级法人改造 add by hxy 20200702
        if (StringUtil.isNotEmpty(pageParam.get("parentId")) && !StringUtil.equals(pageParam.get("parentId"), "root")) {
           List<PtlPositionAnalysis003Dto> resultList = ptlPositionAnalysisMapper.secReportPage(pageParam);
            resultList.forEach(list->{
                list.setTypeName("child");
            });
            Page<PtlPositionAnalysis003Dto> pagelist = new Page<>();
            pagelist.setRoot(resultList);
            return pagelist;
        }
        Page<PtlPositionAnalysis003Dto> list = super.queryForPage(page, length, () -> ptlPositionAnalysisMapper.secReportPage(pageParam));

        return list;
    }


}
