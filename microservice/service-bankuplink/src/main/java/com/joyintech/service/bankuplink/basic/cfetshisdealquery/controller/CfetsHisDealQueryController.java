package com.joyintech.service.bankuplink.basic.cfetshisdealquery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.uplink.interbank.business.dto.BondQuoteExtDto;
import com.joyintech.background.component.uplink.interbank.business.dto.RepoQuoteExtDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.bankuplink.basic.cfetshisdealquery.service.CfetsHisDealQueryService;
import com.joyintech.service.bankuplink.ibu.dto.BondQuoteInfoDto;
import com.joyintech.service.bankuplink.ibu.dto.RepoQuoteInfoDto;

@RestController("backCfetsHisDealQueryController")
@RequestMapping("/CfetsHisDealQuery")
public class CfetsHisDealQueryController {

    /**
     * 现券买卖历史报价查询 微服务Service
     */
    @Autowired
    CfetsHisDealQueryService cfetsHisDealQueryService;

    /**
     * 现券买卖历史报价查询  list
     * @param param
     * @return
     */
    @PostMapping("queryHisDealList")
    public List<BondQuoteInfoDto> queryHisDealList(@RequestBody Map<String, Object> param){
        return cfetsHisDealQueryService.queryHisDealList(param);
    }

    /**
     * 现券买卖历史报价查询  page
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("/queryHisDealPage/{page}/{length}")
	public Page<BondQuoteExtDto> queryHisDealPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
	throws Exception {
	return cfetsHisDealQueryService.queryHisDealPage(page, length, param);
	}
    
    /**
     * 质押式回购历史报价查询  list
     * @param param
     * @return
     */
    @PostMapping("queryPledgeRepoList")
    public List<RepoQuoteInfoDto> queryPledgeRepoList(@RequestBody Map<String, Object> param){
        return cfetsHisDealQueryService.queryPledgeRepoList(param);
    }
    
    /**
     * 质押式回购历史报价查询  page
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     */
    @PostMapping("/queryPledgeRepoPage/{page}/{length}")
	public Page<RepoQuoteExtDto> queryPledgeRepoPage(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length)
	throws Exception {
	return cfetsHisDealQueryService.queryPledgeRepoPage(page, length, param);
	}

    /**
     * 查询报价编号list
     * @param param
     * @param page
     * @param length
     * @return
     */
    @PostMapping("/queryQuoteIdList")
    public List<SuggestDto> queryQuoteIdList(@RequestBody Map<String, Object> param){
        return cfetsHisDealQueryService.queryQuoteIdList(param);
    }

}
