/*
 * 文件名：PtlAnalyzeAfterInvest001Service
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：李淑萍
 * 修改时间：2019/10/23
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.service.portfolio.ptlanalyzeafterinvest.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.portfolio.ptlanalyzeafterinvest.dao.PtlAnalyzeAfterInvest001Mapper;
import com.joyintech.service.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest001Dto;
import com.joyintech.service.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest002Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class PtlAnalyzeAfterInvest001Service extends BaseService<PtlAnalyzeAfterInvest001Dto> {
    @Autowired
    private PtlAnalyzeAfterInvest001Mapper ptlAnalyzeAfterInvest001Mapper;

    /**
     * 分页查询
     * @param page
     * @param length
     * @param map
     * @return
     * @throws Exception
     */
    public Page<PtlAnalyzeAfterInvest001Dto> selectForPage(int page, int length, Map<String,Object> map) throws Exception{
        map.put("PINAN_ASSET_TYPE_F06", DictCodeList.平安资产类型_项目类资产.getValue());
        map.put("PLEDGE_SHARES_STATUS_01", DictCodeListExt3.质押券状态_正常.getValue());
        map.put("PLEDGE_SHARES_STATUS_02", DictCodeListExt3.质押券状态_触发预警.getValue());
        map.put("PLEDGE_SHARES_STATUS_03", DictCodeListExt3.质押券状态_触发平仓.getValue());
        Page<PtlAnalyzeAfterInvest001Dto> pages = super.queryForPage(page, length,() -> ptlAnalyzeAfterInvest001Mapper.selectForPage(map));
        return pages;
    }

    /**
     *@title: getSourceData
     *@description: 获取行情波动图数据源
     *@author: 王贵奇
     *@date: 2019/11/05
     *@return: Page<PtlAnalyzeAfterInvest002Dto>
     *@throws:
     */
	public List<PtlAnalyzeAfterInvest002Dto> getSourceData(Map<String, Object> param) {
		 return ptlAnalyzeAfterInvest001Mapper.getSourceData(param);
    }
}
