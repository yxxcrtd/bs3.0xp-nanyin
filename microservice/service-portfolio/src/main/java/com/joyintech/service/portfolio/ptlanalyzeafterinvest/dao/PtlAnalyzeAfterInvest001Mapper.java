/*
 * 文件名：PtlAnalyzeAfterInvest001Mapper
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：李淑萍
 * 修改时间：2019/10/23
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.service.portfolio.ptlanalyzeafterinvest.dao;

import com.joyintech.service.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest001Dto;
import com.joyintech.service.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest002Dto;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author 李淑萍
 * @version 1.0 2019/10/23
 * @see
 * @since 1.0
 */
public interface PtlAnalyzeAfterInvest001Mapper {
    List<PtlAnalyzeAfterInvest001Dto> selectForPage(Map<String,Object> map);

    List<PtlAnalyzeAfterInvest002Dto> getSourceData(Map<String, Object> param);
}
