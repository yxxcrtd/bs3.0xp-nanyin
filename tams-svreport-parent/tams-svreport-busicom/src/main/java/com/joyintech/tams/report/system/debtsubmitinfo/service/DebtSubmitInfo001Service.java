/*
 * 文件名：SystemFeedback001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.system.debtsubmitinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.debtsubmitinfo.dao.DebtSubmitInfo001Mapper;
import com.joyintech.tams.report.system.debtsubmitinfo.dto.DebtSubmitInfo001Dto;
import com.joyintech.tams.constant.DictCode;

/**
 * 报送信息反馈的查询service
 * @author dingzhipeng
 * @version 1.0 2017年9月21日
 * @see DebtSubmitInfo001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DebtSubmitInfo001Service extends BaseService<DebtSubmitInfo001Dto>{
	@Autowired
	private DebtSubmitInfo001Mapper debtSubmitInfo001Mapper;
    /**
     * 根据传递的参数查询数据
     * <功能详细描述>
     * @param param 查询条件
     * @return 返回存储了查询信息的Dto
     * @see
     * @since 1.0
     */
    public Page<DebtSubmitInfo001Dto> searchInfo(Map<String, Object> param) {
        String repName = (String)param.get("repName");
        param.put("repNameList", StringUtil.splitToList(GlobalConstant.COMMA, repName));
        String[] str={"报送","条数据","中债通过","条","中债退回"};
        param.put("reportName",DictCode.报表名称.getValue());
//        Page<DebtSubmitInfo001Dto> result = super.queryForPage(DebtSubmitInfo001Mapper.class, "searchInfo", param);
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        Page<DebtSubmitInfo001Dto> result = super.queryForPage(
        		pagenum,legth,()->debtSubmitInfo001Mapper.searchInfoNew(param));
        result.getRoot().forEach(item -> {
             //反馈信息
//            StringBuffer sb = new StringBuffer(str[0]+item.getSubmitCount() + str[1]);
        	StringBuffer sb = new StringBuffer();
            if(StringUtil.isNotEmpty(item.getSubmitCount())){
                sb.append(str[0]+item.getSubmitCount() + str[1]);
            }
            else{
                sb.append(str[0]+item.getCheckTotalCount() + str[1]);
            }
            //发送成功 (直接返回sb)
            //报送通过 部分通过 部分通过
            if(!StringUtil.equals(item.getRepResult(), "01")){
                sb.append(GlobalConstant.COMMA+str[2]+item.getCheckSuccessCount()+str[3]+GlobalConstant.COMMA
                    +str[4]+item.getCheckFailedCount()+str[3]);
            }
            item.setRepFailedInfo(sb.toString());
            if(StringUtil.isNotEmpty(item.getRepName())){
                item.setRepName(SysDataUtil.getNameFromDataDict(DictCode.报表名称,
                        item.getRepName()));
            }
        });
        return result;
    }
    /**
     * 根据传递的参数查询数据
     * <功能详细描述>
     * @param param 查询条件
     * @return 返回存储了查询信息的Dto
     * @see
     * @since 1.0
     */
    public Page<DebtSubmitInfo001Dto> viewDetailPage(Map<String, Object> param) {
        String repResult = (String)param.get("repResult");
        param.put("reportName",DictCode.报表名称.getValue());
        param.put("repResultList", StringUtil.splitToList(GlobalConstant.COMMA, repResult));
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        Page<DebtSubmitInfo001Dto> result = super.queryForPage(
        		pagenum,legth,()->debtSubmitInfo001Mapper.viewDetailPage(param));
        formatSearchResult(result.getRoot());
        return result;
    }
    
    /**
     * 格式化查询结果
     *
     * @param resultList 查询结果
     * @see
     * @since 1.0
     */
    private void formatSearchResult(List<DebtSubmitInfo001Dto> resultList) {
        if (StringUtil.isEmptyList(resultList)) {
            return;
        }

        for (DebtSubmitInfo001Dto debtSubmitInfo001Dto : resultList) {
            debtSubmitInfo001Dto.setRepName(SysDataUtil.getNameFromDataDict(DictCode.报表名称,
                debtSubmitInfo001Dto.getRepName()));
        }
    }
}
