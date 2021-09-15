/**
 * @author wj
 * @version 1.0.0
 * @ClassName SysPrebatchResult002Service.java
 * @Description 批前检查002 页面对应
 * @createTime 2021年05月13日 10:57:00
 */
package com.joyintech.tams.invest.prebatch.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.Role;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.prebatch.dao.SysPrebatchResult002Mapper;
import com.joyintech.tams.invest.prebatch.dto.SysPrebatchResultDetail001Dto;
import com.joyintech.workflow.engine.service.WorkflowConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class SysPrebatchResult002Service extends CommonExportService<SysPrebatchResultDetail001Dto> {

    @Autowired
    private SysPrebatchResult002Mapper sysPrebatchResult002Mapper;

    @Autowired
    private WorkflowConfService workflowConfService;

    /**
     * @title 查询组合下拉框
     * @description 
     * @author wj 
     * @updateTime 2021/5/13 11:12 
     * @throws 
     */
    public Page<SelectOptionDto> queryPortfolioIdInfo(Map<String, Object> pageParam) {
        int page = Integer.valueOf(pageParam.get("page").toString());
        int length =Integer.valueOf(pageParam.get("length").toString());

        return  this.queryForPageForSelectReturn(page, length, ()-> sysPrebatchResult002Mapper.queryPortfolioIdInfo(pageParam));

    }

    /**
     * @title 查询资产下拉框
     * @description
     * @author wj
     * @updateTime 2021/5/13 11:12
     * @throws
     */
    public  Page<SelectOptionDto> queryFinprodIdInfo(Map<String, Object> pageParam) {
        int page = Integer.valueOf(pageParam.get("page").toString());
        int length =Integer.valueOf(pageParam.get("length").toString());

        return  this.queryForPageForSelectReturn(page, length, ()-> sysPrebatchResult002Mapper.queryFinprodIdInfo(pageParam));

    }

    /**
     * @title 查询页面
     * @description
     * @author wj
     * @updateTime 2021/5/13 11:12
     * @throws
     */
    public Page<SysPrebatchResultDetail001Dto> findPage(Map<String, Object> pageParam) {
        int page = Integer.valueOf(pageParam.get("page").toString());
        int length =Integer.valueOf(pageParam.get("length").toString());

        if (StringUtil.isNotEmpty(pageParam.get("portfolioId"))) {
            pageParam.put("portfolioId", StringUtil.splitToList(null, pageParam.get("portfolioId").toString()));
        }else{
            pageParam.put("portfolioId",new ArrayList<>());
        }

        if (StringUtil.isNotEmpty(pageParam.get("finprodId"))) {
            pageParam.put("finprodId", StringUtil.splitToList(null, pageParam.get("finprodId").toString()));
        }else{
            pageParam.put("finprodId",new ArrayList<>());
        }

        Page<SysPrebatchResultDetail001Dto> pageResult = this.queryForPageForSelectReturn(page, length, () -> sysPrebatchResult002Mapper.findPage(pageParam));
        Optional.ofNullable(pageResult.getRoot()).ifPresent(list -> list.forEach(item -> setNextUserName(item)));
        return pageResult;

    }

    /**
     * 将用户id 转为 name
     * 角色id转为角色name
     * @param item
     * @return
     */
    private SysPrebatchResultDetail001Dto setNextUserName(SysPrebatchResultDetail001Dto item) {
        StringBuilder sb = new StringBuilder();
        if(null != item && StringUtil.isNotEmpty(item.getNextUser())){
            String[] nowUserIdArr = item.getNextUser().split(GlobalConstant.COMMA);
            for(String id : nowUserIdArr){
                String realName = CacheUtil.getAppParamCache(GlobalConstant.SYS_USER_KEY, id);
                if(StringUtil.isEmpty(realName)) {
                    Role role = workflowConfService.queryRoleById(id);
                    if(null != role) {
                        realName = role.getRoleName();
                    }
                }
                if(sb.length() != 0) {
                    sb.append(GlobalConstant.COMMA);
                }
                sb.append(StringUtil.defaultString(realName, id));
            }
            item.setNextUser(sb.toString());
        }
        return item;
    }
}
