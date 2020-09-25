package com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dao.PtlRepoPledgeSearchMapper;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlSubstitutionRepoPledgeDto;
import com.joyintech.tams.common.base.entity.FinPledgeDisplaceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class PtlSubstitutionRepoPledgeService extends BaseService<PtlSubstitutionRepoPledgeDto> {
    @Autowired
    private PtlRepoPledgeSearchMapper ptlRepoPledgeSearchMapper;
    /**
     * 画面查询方法->质押券置换查询
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    public Page<PtlSubstitutionRepoPledgeDto> substitutionFindPage(int page, int length, Map<String, Object> param) {
        if(StringUtil.isNotEmpty(param.get("portfolioId"))){
            param.put("portfolioIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)param.get("portfolioId")));
        }
        return super.queryForPage(page, length, () -> ptlRepoPledgeSearchMapper.substitutionFindPage(param));
    }


}
