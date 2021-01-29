package com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dao.PtlRepoPledgeSearchMapper;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlPldgeSummaryDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PtlPldgeSummarySerchService extends BaseService<PtlPldgeSummaryDto> {
    /**
     * 定义债券质押查询mapper
     */
    @Autowired
    private PtlRepoPledgeSearchMapper ptlRepoPledgeSearchMapper;
    
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param page 页码
     * @param length 页面长度
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<PtlPldgeSummaryDto> pledgeSummaryFindPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        if(StringUtil.isNotEmpty(pageParam.get("counterId"))){
            pageParam.put("counterIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("counterId")));
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
        if(StringUtil.isNotEmpty(pageParam.get("prodManager"))){
            pageParam.put("prodManager", StringUtil.splitToList(null, pageParam.get("prodManager").toString()));
        } else {
            pageParam.put("prodManager", null);
        }
        // add by wangyang1 【吉林银行】 增加 产品经理  end  
        pageParam.put("BACK_LEND_DIRECTION_03", DictCodeList.回购拆借方向_正回购.getValue());
        pageParam.put("BACK_LEND_DIRECTION_05", DictCodeList.回购拆借方向_融入.getValue());
        pageParam.put("BACK_LEND_DIRECTION_06", DictCodeList.回购拆借方向_融出.getValue());
        /**add by 张年 20190902  去掉买断式正逆回购 start*/
        pageParam.put("FINPROD_TYPE_TG_F04", DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        pageParam.put("FINPROD_TYPE_TG_F05",DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        /**add by 张年 20190902  去掉买断式正逆回购 end*/
        return super.queryForPage(page, length, () -> ptlRepoPledgeSearchMapper.pledgeSummaryFindPage(pageParam));
    }
}
