package com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.controller;



import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

/**
 * @program: 质押券置换明细查询
 * @description: PtlRepoPledgeSearch003Controller
 * @author: nixiukuan
 * @create: 2019-11-12
 **/
@Controller
@PageId("PtlRepoPledgeSearch003")
@RequestMapping("/invest/portfolio/portfolioanalysis/ptlrepopledgesearch/PtlRepoPledgeSearch003")
public class PtlRepoPledgeSearch003Controller extends BaseController {

    /**
     * 质押明细查询界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("tradeId", map.get("businessCode"));
        return super.init(model);
    }
}
