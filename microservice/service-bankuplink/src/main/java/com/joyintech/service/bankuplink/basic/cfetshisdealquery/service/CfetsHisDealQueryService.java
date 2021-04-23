package com.joyintech.service.bankuplink.basic.cfetshisdealquery.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.background.component.common.pack.common.base.BaseService;
import com.joyintech.background.component.common.pack.common.grid.Page;
import com.joyintech.background.component.uplink.interbank.business.dto.BondQuoteExtDto;
import com.joyintech.background.component.uplink.interbank.business.dto.RepoQuoteExtDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.service.bankuplink.basic.cfetshisdealquery.dao.CfetsHisDealQueryMapper;
import com.joyintech.service.bankuplink.ibu.dto.BondQuoteInfoDto;
import com.joyintech.service.bankuplink.ibu.dto.RepoQuoteInfoDto;
import com.joyintech.service.bankuplink.ibu.service.IbuComponentCommonService;

@Service
public class CfetsHisDealQueryService extends BaseService{

    /**
     * 共通组件Service
     */
    @Autowired
    private IbuComponentCommonService ibuComponentCommonService;

    @Autowired
    CfetsHisDealQueryMapper cfetsHisDealQueryMapper;

    /**
     * 现券买卖历史报价查询  list
     * @param param
     * @return
     */
    public List<BondQuoteInfoDto> queryHisDealList(Map<String, Object> param){
        List<BondQuoteInfoDto> dtoList = new ArrayList<>();
        //查询条件转换
        List<BondQuoteExtDto> list = ibuComponentCommonService.queryHisDealList(param);
        list.forEach(x -> {
            BondQuoteInfoDto bondQuoteInfoDto = new BondQuoteInfoDto();
            bondQuoteInfoDto.setQuoteDate(x.getQuoteDate());//交易日期（报价日期）
            bondQuoteInfoDto.setQuoteRoleType(x.getQuoteRoleType());//本方角色
            bondQuoteInfoDto.setPs(x.getPs());//本方方向（交易方向）
            bondQuoteInfoDto.setFinprodMarketId(x.getFinprodMarketId());//债券代码
            bondQuoteInfoDto.setFinprodAbbr(x.getFinprodAbbr());//债券简称
            bondQuoteInfoDto.setQuoteType(x.getQuoteType());//报价类型
            bondQuoteInfoDto.setTradeStatus(x.getTradeStatus());//交易状态
            bondQuoteInfoDto.setSendStatus(x.getSendStatus());//发送状态
            bondQuoteInfoDto.setOuPartyId(x.getOuPartyId());//本方（本方银行间机构）
            bondQuoteInfoDto.setCpPartyId(x.getIbuQuoteBondDet() != null ? x.getIbuQuoteBondDet().getCpPartyId() : null);//交易对手（对手银行间机构）
            bondQuoteInfoDto.setCpTrader(x.getIbuQuoteBondDet() != null ? x.getIbuQuoteBondDet().getCpTrader() : null);//对手方交易员（对手银行间机构交易员）
            bondQuoteInfoDto.setShareAmt(x.getShareAmt());//回复量（交易份额）
            bondQuoteInfoDto.setSettleSpeed(x.getSettleSpeed());//清算速度
            bondQuoteInfoDto.setUnitCprice(x.getIbuQuoteBondDet() != null ? x.getIbuQuoteBondDet().getUnitCprice() : null);//净价（单位净价）
            bondQuoteInfoDto.setYield(x.getIbuQuoteBondDet() != null ? x.getIbuQuoteBondDet().getYield() : null);//到期(%)（到期收益率）
            bondQuoteInfoDto.setPrinAmt(x.getShareAmt() != null ? ArithmeticUtil.multiply(x.getShareAmt(), new BigDecimal(100)) : null);;//券面（面值总额）
            bondQuoteInfoDto.setAssetTypeOne(x.getType6());//债券类型
            bondQuoteInfoDto.setCfetsQuoteId(x.getCfetsQuoteId());//报价编号
            bondQuoteInfoDto.setfTradeId(x.getIbuQuoteBondDet() != null ? x.getIbuQuoteBondDet().getExecid() : null);//成交编号
            bondQuoteInfoDto.setPortfolioId(x.getPortfolioId());//组合代码
            bondQuoteInfoDto.setEntrustId(x.getEntrustId());//委托代码

            dtoList.add(bondQuoteInfoDto);
        });
        return dtoList;
    }

    /**
     * 现券买卖历史报价查询  page
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<BondQuoteExtDto> queryHisDealPage(int page, int length, Map<String, Object> param) {
    	return ibuComponentCommonService.queryHisDealPage(page,length,param);
	}

    /**
     * 质押式回购历史报价查询  list
     * @param param
     * @return
     */
    public List<RepoQuoteInfoDto> queryPledgeRepoList(Map<String, Object> param){
        List<RepoQuoteInfoDto> dtoList = new ArrayList<>();
        //查询条件转换
        List<RepoQuoteExtDto> list = ibuComponentCommonService.queryPledgeRepoList(param);
        list.forEach(x -> {
        	RepoQuoteInfoDto repoQuoteInfoDto = new RepoQuoteInfoDto();
        	repoQuoteInfoDto.setQuoteDate(x.getQuoteDate());//交易日期（报价日期）
        	repoQuoteInfoDto.setQuoteRoleType(x.getQuoteRoleType());//本方角色
        	repoQuoteInfoDto.setPs(x.getRepoPs());//本方方向（交易方向）
        	repoQuoteInfoDto.setQuoteType(x.getQuoteType());//报价类型
        	repoQuoteInfoDto.setTradeStatus(x.getTradeStatus());//交易状态
        	repoQuoteInfoDto.setSendStatus(x.getSendStatus());//发送状态
        	repoQuoteInfoDto.setOuPartyId(x.getOuPartyId());//本方（本方银行间机构）
        	repoQuoteInfoDto.setCpPartyId(x.getCpPartyId());//交易对手（对手银行间机构）
        	repoQuoteInfoDto.setCpTrader(x.getCpTraderId());//对手方交易员（对手银行间机构交易员）
        	repoQuoteInfoDto.setRate(x.getRate());//回购利率
        	repoQuoteInfoDto.setShareAmt(x.getShareAmt());//交易金额
        	repoQuoteInfoDto.setvSettleDate(x.getvSettleDate());//首次结算日
        	repoQuoteInfoDto.setmSettleDate(x.getmSettleDate());//到期结算日
        	repoQuoteInfoDto.setTerm(x.getTerm());//期限(天)
        	repoQuoteInfoDto.setSettleSpeed(x.getSettleSpeed());//清算速度
        	repoQuoteInfoDto.setCfetsQuoteId(x.getCfetsQuoteId());//报价编号
        	repoQuoteInfoDto.setfTradeId(x.getExecid());//成交编号
        	repoQuoteInfoDto.setPortfolioId(x.getPortfolioId());//组合代码
        	repoQuoteInfoDto.setEntrustId(x.getEntrustId());//委托代码
        	
            dtoList.add(repoQuoteInfoDto);
        });
        return dtoList;
    }

    /**
     * 质押式回购历史报价查询  page
     * @param page
     * @param length
     * @param param
     * @return
     */
    public Page<RepoQuoteExtDto> queryPledgeRepoPage(int page, int length, Map<String, Object> param) {
		return ibuComponentCommonService.queryPledgeRepoPage(page,length,param);
	}

    /**
     *
     * @param page
     * @param length
     * @param param
     * @return
     */
    public List<SuggestDto> queryQuoteIdList(Map<String, Object> param){
        return cfetsHisDealQueryMapper.queryQuoteIdList(param);
    }

}