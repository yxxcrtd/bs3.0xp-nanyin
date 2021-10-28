package com.joyintech.tams.business.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.dao.JoyinbusinessCommonMapper;
import com.joyintech.tams.business.common.dto.EntrustIdRequestDto;
import com.joyintech.tams.business.common.dto.SecManageAcctIdRequestDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共方法service
 */
@Service("JoyinbusinessCommonService")
public class JoyinbusinessCommonService extends BaseService {

    @Autowired
    private JoyinbusinessCommonMapper joyinbusinessCommonMapper;

    /**
     * 根据条件查询证券账户
     *
     * @param secManageAcctIdRequestDto
     * @return List<SelectOptionDto>
     */
    public Page<SelectOptionDto> getSecManageAcctId(SecManageAcctIdRequestDto secManageAcctIdRequestDto) {
        Map<String,Object> pageParam = BeanUtil.object2Map(secManageAcctIdRequestDto);
        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(pageParam.get("portfolioId"))){
            List<String> portfolioIdList = StringUtil.splitToList(",",(String)pageParam.get("portfolioId"));
            pageParam.put("portfolioIdList", portfolioIdList);
        }

        String finprodMarketId = (String)pageParam.get("finprodMarketId");
        if(StringUtil.isNotEmpty(pageParam.get("finprodMarketId"))){
            List<String> finprodMarketIdList = StringUtil.splitToList(",",(String)pageParam.get("finprodMarketId"));
            pageParam.put("finprodMarketIdList", finprodMarketIdList);
        }
        return super.queryForPage(secManageAcctIdRequestDto.getPageMode().getPageNo(), secManageAcctIdRequestDto.getPageMode().getPageSize(),
                () -> joyinbusinessCommonMapper.getSecManageAcctId(pageParam));
    }

    /**
     * 委托代码的联想文本框的查询方法
     *
     * @param entrustIdRequestDto
     * @return List<SelectOptionDto>
     */
    public Page<SelectOptionDto> getEntrustId(EntrustIdRequestDto entrustIdRequestDto) {
        Map<String,Object> pageParam = new HashMap<String,Object>();
        pageParam.put("etrId", entrustIdRequestDto.getEtrId());
        pageParam.put("entrustAwait", DictCodeList.委托状态_待接收.getValue());
        pageParam.put("entrustDistribute", DictCodeListExt3.委托状态_待分发.getValue());
        pageParam.put("entrustReceive", DictCodeList.委托状态_已接收.getValue());
        pageParam.put("entrustUntread", DictCodeList.委托状态_已完成.getValue());
        pageParam.put("entrustClose", DictCodeList.委托状态_已关闭.getValue());
        pageParam.put("entrustReturn", DictCodeList.委托状态_已退回.getValue());
        pageParam.put("entrustRecall", DictCodeList.委托状态_已撤回.getValue());
        return super.queryForPage(entrustIdRequestDto.getPageMode().getPageNo(), entrustIdRequestDto.getPageMode().getPageSize(),
                () ->joyinbusinessCommonMapper.getEntrustId(pageParam));
    }
}
