package com.joyintech.service.trade.project.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.trade.project.dao.TrdProjectInvestTradeMapper;
import com.joyintech.tams.common.base.dao.WflPaTrdProductDealMapper;
import com.joyintech.tams.common.base.dao.WflTrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.WflTrdProductDealMapper;
import com.joyintech.tams.common.base.entity.WflPaTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.period.common.BaseTradeInfoDto;
import com.joyintech.tams.invest.common.dto.trade.project.TrdProjectInvestTradeDto;

/**
 * @program: tams-invest-parent
 * @description: 项目类资产投资交易
 * @author: 强成西
 * @create: 2018-12-04 14:45
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdProjectInvestTradeService extends BaseService<TrdProjectInvestTradeDto> {

    @Autowired
    private TrdProjectInvestTradeMapper trdProjectInvestTradeMapper;

    @Autowired
    private IPlatformDate platformDateImpl;

    public Page<SelectOptionDto> findEntrustId(int page, int length, Map<String, Object> param) {
        return super.queryForPageForSelectReturn(page, length, () -> trdProjectInvestTradeMapper.findEntrustId(param));
    }

    public Page<TrdProjectInvestTradeDto> findPage(int page, int length, Map<String, Object> param)
        throws Exception {
        setParams(param);
        return super.queryForPage(page, length, () -> trdProjectInvestTradeMapper.findTotalPage(param));
    }

    private void setParams(Map<String, Object> param) {
        param.put("TRADE_FLOW_STATUS01", DictCodeListExt1.交易流程状态_流程中.getValue());
        param.put("TRADE_FLOW_STATUS02", DictCodeListExt1.交易流程状态_有效.getValue());
        param.put("TRADE_FLOW_STATUS03", DictCodeListExt1.交易流程状态_撤销.getValue());
        param.put("F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        param.put("TRD09", DictCodeList.共通业务类型_项目类资产交易.getValue());
        param.put("YES", DictCodeList.是否_是.getValue());
        param.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("TRADE_STATUS03", DictCodeListExt1.交易状态_撤销.getValue());
        param.put("T01", DictCodeList.交易方向_买入.getValue());
        param.put("T02", DictCodeList.交易方向_卖出.getValue());
        // qiangchengxi 卖出到账流入核对 20190325 start
        param.put("T40", DictCodeList.交易方向_卖出到账.getValue());
        // qiangchengxi 卖出到账流入核对 20190325 end
    }

    public Page<SelectOptionDto> getFinprodMarketId(int page, int length, Map<String, Object> param) {
//        if (StringUtil.isNotEmpty(param)&&!StringUtil.isNotEmpty(param.get("tradeDate"))){
//            param.put("tradeDate", platformDateImpl.getSysDateStr());
//        }
        return queryForPageForSelectReturn(page, length, () -> trdProjectInvestTradeMapper.getFinprodMarketId(param));
    }

    public Map<String, Object> findInvestRate(Map<String, Object> param) {
        param.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        Map<String, Object> map=trdProjectInvestTradeMapper.findInvestRate(param);
        return map;
    }

    public TrdProjectInvestTradeDto findEffectiveData(Map<String, Object> param) {
        param.put("PAGE_DATA_STATUS01", DictCodeListExt1.画面数据状态_待处理.getValue());
         //交易表中入库的时候进行了转换，数值转换，导致查询的时候也要进行转换 yjm start 20190809
        param.put("01", DictCodeList.清算速度_T_0.getValue());
        param.put("02", DictCodeList.清算速度_T_1.getValue());
        //交易表中入库的时候进行了转换，数值转换，导致查询的时候也要进行转换 yjm start 20190809
        param.put("FEE_TYPE_B09", DictCodeListExt1.一次性费用类型_通道费.getValue());
        return trdProjectInvestTradeMapper.findEffectiveData(param);
    }

    @Autowired
    private WflTrdProductDealMapper trdProductDealMapper;

    @Autowired
    private WflTrdProductDealAddMapper trdProductDealAddMapper;

    @Autowired
    private WflPaTrdProductDealMapper paTrdProductDealMapper;

    @Autowired
    private  WflTrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    public String updateSetWflByTradeId(BaseTradeInfoDto tradeInfoDto) {
        WflTrdFinProductDealTotal wflDealTotel = new WflTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflDealTotel, tradeInfoDto.getTrdFinProductDealTotal());
        wflDealTotel.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(wflDealTotel);

        WflTrdProductDeal wflDeal = new WflTrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(wflDeal, tradeInfoDto.getTrdProductDeal());
        // qiangchengxi 卖出到账流入核对 20190325 start
        wflDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        // qiangchengxi 卖出到账流入核对 20190325 end
        trdProductDealMapper.updateByPrimaryKeySelective(wflDeal);

        WflTrdProductDealAdd wflDealAdd = new WflTrdProductDealAdd();
        BeanUtil.copyPropertiesIgnoreNull(wflDealAdd, tradeInfoDto.getTrdProductDealAdd());
        // qiangchengxi 卖出到账流入核对 20190325 start
        wflDealAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        // qiangchengxi 卖出到账流入核对 20190325 end
        trdProductDealAddMapper.updateByPrimaryKeySelective(wflDealAdd);

        WflPaTrdProductDeal wflPaDeal = new WflPaTrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(wflPaDeal, tradeInfoDto.getPaTrdProductDeal());
        // qiangchengxi 卖出到账流入核对 20190325 start
        wflPaDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        // qiangchengxi 卖出到账流入核对 20190325 end
        paTrdProductDealMapper.updateByPrimaryKeySelective(wflPaDeal);
        trdProjectInvestTradeMapper.updatePayOrders(tradeInfoDto.getStlPayOrders());
        return Boolean.TRUE.toString();
    }

    public int countFollowDeal(Map<String, Object> param) {
        return trdProjectInvestTradeMapper.countFollowDeal(param);
    }


    public Map<String, Object> findRepayFlag(Map<String, Object> param) {
        //param.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        String repayIntFlag=trdProjectInvestTradeMapper.getIsPayInt(param);
        Map<String, Object> map= new HashMap<>();
        if(repayIntFlag!=null && repayIntFlag.equals(DictCodeList.是否_否.getValue())) {
            map.put("repayIntFlag", repayIntFlag);//是否还本不带息
        }else{
            map.put("repayIntFlag", DictCodeList.是否_是.getValue());//默认是还本带息的
        }
        return map;
    }
    
    public BigDecimal getDoEntrustMoneyByEntrustId(Map<String, Object> param) {
		BigDecimal entrustAmt = trdProjectInvestTradeMapper.getEntrustAmtByEntrustId(param);
		List<BigDecimal> wflPrinAmts = trdProjectInvestTradeMapper.getFinPrintAmt(param);
    	BigDecimal wflPrinAmt = BigDecimal.ZERO;
    	for(BigDecimal temp: wflPrinAmts) {
    		wflPrinAmt = wflPrinAmt.add(temp);
    	}
		if(entrustAmt!=null){
			return entrustAmt.subtract(wflPrinAmt);
		}
		return BigDecimal.ZERO;
	}
    
    
    public int findRevokeDeal(Map<String, Object> param) {
    	return trdProjectInvestTradeMapper.findRevokeDeal(param);
	}
}
