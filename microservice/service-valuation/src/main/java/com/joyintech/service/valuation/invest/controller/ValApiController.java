package com.joyintech.service.valuation.invest.controller;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.invest.service.ValApiCommonService;
import com.joyintech.service.valuation.invest.util.BeanUtils;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.common.dto.ApiTrdCommDto;
import com.joyintech.tams.valuation.common.interf.base.dto.ChannelProdDeal;
import com.joyintech.tams.valuation.common.interf.common.ResultObject;
import com.joyintech.tams.valuation.common.interf.invest.constant.RequestMapKey;
import com.joyintech.tams.valuation.common.interf.val.dto.ProdInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  估值系统对外发布的微服务接口，根路径为:/valApi
 *  接口列表如下:
 *  <ul>
 *      <li>1)账套\产品信息初始化,访问路径:/prodOtherInfoInit
 *      <li>2)交易入账,访问路径:/bokTradeBuild
 *      <li>3)同业借款基本信息初始化,访问路径:/interBorrowBaseInfoInit
 *      <li>4)结算指令交易入账,访问路径:/stlOrdersBokTradeBuild
 *      <li>5)删除交易入账信息,访问路径:/bokTradeBuldInfoDelete
 *      <li>6)拆入到期删除,访问路径:/lendMdatDelete
 *      <li>7)理财产品借通道投资交易,访问路径:/getChannelProdDeal
 *      <li>8)通道投资资产交易拆分,访问路径:/chlInvestAssetSep
 *      <li>9)产品端交易拆分,访问路径:/prdDealSep
 *      <li>10)核算到期日调整,访问路径:/updateProdMdate
 *      <li>11)按规则生成回购期限品种,访问路径:/getRepTermType
 *      <li>12)投管-几许接口,访问路径:/runValInvest2Jixu
 *  </ul>
 * @author jinmaodong
 * @since  v1.0.0 20190902
 */
@RestController(value="serviceValApiControllerController")
@RequestMapping("valApiController")
public class ValApiController {

    @Autowired
    private ValApiCommonService valApiCommonService;

    /**
     * 账套\产品信息初始化
     *
     * @param params
     *  参数以MAP形式传输，应包含元素(类型-：
     *  <ul>
     *      <li>ProdInfoDto：金融产品信息；</li>
     *      <li></li>
     *      <li></li>
     *      <li></li>
     *      <li></li>
     *      <li></li>
     *      <li></li>
     *
     *  </ul>
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/prodOtherInfoInit")
    public ResultObject prodOtherInfoInit(@RequestBody Map<String, Object> params) {
        ProdInfoDto prodInfoDto = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.PRODOTHERINFOINIT_PRODINFODTO), ProdInfoDto.class);
        List<StlFinProductAcct> productAcctList = BeanUtils.mapToObject((List<Map<String, Object>>) params.get(RequestMapKey.PRODOTHERINFOINIT_PRODUCTACCTLIST), StlFinProductAcct.class);
        return valApiCommonService.prodOtherInfoInit(prodInfoDto, productAcctList);
    }

    /**
     * 交易入账
     *
     * @param params
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/bokTradeBuild")
    public ResultObject bokTradeBuild(@RequestBody Map<String, Object> params) {
        String prodId = (String) params.get(RequestMapKey.BOKTRADEBUILD_PRODID);
        String prodFinprodType = (String) params.get(RequestMapKey.BOKTRADEBUILD_PRODFINPRODTYPE);
        Long branch = Long.valueOf(String.valueOf(RequestMapKey.BOKTRADEBUILD_BRANCH));
        TrdProductDeal trdProductDeal = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.BOKTRADEBUILD_TRDPRODUCTDEAL), TrdProductDeal.class);
        return valApiCommonService.bokTradeBuild(prodId, prodFinprodType, branch, trdProductDeal);
    }

    /**
     * 同业借款基本信息初始化
     *
     * @param params
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/interBorrowBaseInfoInit")
    public ResultObject interBorrowBaseInfoInit(@RequestBody Map<String, Object> params) {
        FinTradeProduct finTradeProduct = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.INTERBORROWBASEINFOINIT_FINTRADEPRODUCT), FinTradeProduct.class);
        String dataActionSign = (String) params.get(RequestMapKey.INTERBORROWBASEINFOINIT_DATAACTIONSIGN);
        return valApiCommonService.interBorrowBaseInfoInit(finTradeProduct, dataActionSign);
    }

    /**
     * 结算指令交易入账
     *
     * @param params
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/stlOrdersBokTradeBuild")
    public ResultObject stlOrdersBokTradeBuild(@RequestBody Map<String, Object> params) {
        String prodId = (String) params.get(RequestMapKey.STLORDERSBOKTRADEBUILD_PRODID);
        String prodFinprodType = (String) params.get(RequestMapKey.STLORDERSBOKTRADEBUILD_PRODFINPRODTYPE);
        Date prodVdate = DateUtil.parse((String) params.get(RequestMapKey.STLORDERSBOKTRADEBUILD_PRODVDATE));
        StlOrders stlOrders = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.STLORDERSBOKTRADEBUILD_STLORDERS), StlOrders.class);
        return valApiCommonService.stlOrdersBokTradeBuild(prodId, prodFinprodType, prodVdate, stlOrders);
    }

    /**
     * 删除交易入账信息
     * @param tradeId
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/bokTradeBuldInfoDelete")
    public ResultObject bokTradeBuldInfoDelete(@RequestBody String tradeId) {
        return valApiCommonService.bokTradeBuldInfoDelete(tradeId);
    }


    /**
     * 拆入到期删除
     * @param totleTradeId
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/lendMdatDelete")
    public ResultObject lendMdatDelete(@RequestBody String totleTradeId) {
        return valApiCommonService.lendMdatDelete(totleTradeId);
    }

    /**
     * 理财产品借通道投资交易
     *
     * @param params
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/getChannelProdDeal")
    public ResultObject getChannelProdDeal(@RequestBody Map<String, Object> params) {
        ChannelProdDeal deal = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.GETCHANNELPRODDEAL_DEAL), ChannelProdDeal.class);
        String productType = (String) params.get(RequestMapKey.GETCHANNELPRODDEAL_PRODUCTTYPE);
        return valApiCommonService.getChannelProdDeal(deal, productType);
    }

    /**
     * 通道投资资产交易拆分
     *
     * @param params
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/chlInvestAssetSep")
    public ResultObject chlInvestAssetSep(@RequestBody Map<String, Object> params) {
        String prodId = (String) params.get(RequestMapKey.CHLINVESTASSETSEP_PRODID);
        String prodFinrodType = (String) params.get(RequestMapKey.CHLINVESTASSETSEP_PRODFINRODTYPE);
        TrdProductDeal trdProductDeal = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.CHLINVESTASSETSEP_TRDPRODUCTDEAL), TrdProductDeal.class);
        TrdProductDealAdd trdProductDealAdd = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.CHLINVESTASSETSEP_TRDPRODUCTDEALADD), TrdProductDealAdd.class);
        TrdTradeFeeDetail trdTradeFeeDetail = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.CHLINVESTASSETSEP_TRDTRADEFEEDETAIL), TrdTradeFeeDetail.class);
        //TODO 债转股 金融产品实物交易信息调用接口时需要
        return valApiCommonService.chlInvestAssetSep(prodId, prodFinrodType, trdProductDeal, trdProductDealAdd, trdTradeFeeDetail,null);
    }

    /**
     * 产品端交易拆分
     *
     * @param params
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/prdDealSep")
    public ResultObject prdDealSep(@RequestBody Map<String, Object> params) {
        TrdProductDeal trdProductDeal = BeanUtil.mapToObject((Map<String, Object>) params.get(RequestMapKey.PRDDEALSEP_TRDPRODUCTDEAL), TrdProductDeal.class);
        List<TrdTradeFeeDetail> trdTradeFeeDetailList =  BeanUtils.mapToObject((List<Map<String, Object>>) params.get(RequestMapKey.PRDDEALSEP_TRDTRADEFEEDETAILLIST), TrdTradeFeeDetail.class);
        String prodId = (String) params.get(RequestMapKey.PRDDEALSEP_PRODID);
        String channelId = (String) params.get(RequestMapKey.PRDDEALSEP_CHANNELID);
        String prodType = (String) params.get(RequestMapKey.PRDDEALSEP_PRODTYPE);
        Long branch = Long.valueOf(String.valueOf(params.get(RequestMapKey.PRDDEALSEP_BRANCH)));
        String dealSepFlag = (String) params.get(RequestMapKey.PRDDEALSEP_DEALSEPFLAG);
        return valApiCommonService.prdDealSep(trdProductDeal, trdTradeFeeDetailList, prodId, channelId, prodType, branch, dealSepFlag);
    }



    /**
     * 核算到期日调整
     *
     * @param params
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/updateProdMdate")
    public ResultObject updateProdMdate(@RequestBody Map<String, Object> params) {
        String prodId = (String) params.get(RequestMapKey.UPDATEPRODMDATE_PRODID);
        String prodType = (String) params.get(RequestMapKey.UPDATEPRODMDATE_PRODTYPE);
        Long branch = Long.valueOf(String.valueOf(params.get(RequestMapKey.UPDATEPRODMDATE_BRANCH)));
        Date mDate = DateUtil.parse((String) params.get(RequestMapKey.UPDATEPRODMDATE_MDATE));
        return valApiCommonService.updateProdMdate(prodId, prodType, branch, mDate);
    }


    /**
     * 按规则生成回购期限品种
     *
     * @param params
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/queryRepTermType")
    public ResultObject getRepTermType(@RequestBody Map<String, Object> params) {
        String finprodType = (String) params.get(RequestMapKey.GETREPTERMTYPE_FINPRODTYPE);
        Long termDay = Long.valueOf(String.valueOf(params.get(RequestMapKey.GETREPTERMTYPE_TERMDAY)));
        return valApiCommonService.getRepTermType(finprodType, termDay);
    }

    /**
     * 投管-几许接口
     * @param apiTrdCommDto
     * @return  返回共通{@link com.joyintech.service.api.invest.dto.ResultObject}
     */
    @PostMapping("/runValInvest2Jixu")
    public ResultObject runValInvest2Jixu(@RequestBody ApiTrdCommDto apiTrdCommDto) {
        return valApiCommonService.runValInvest2Jixu(apiTrdCommDto);
    }
    
    @SuppressWarnings("unchecked")
    @PostMapping("/getAddTaxType")
    public List<String> getAddTaxType(@RequestBody Map<String, Object> params){
        List<String> listProdId = (List<String>)params.get("listProdId"); 
        String prodType = (String)params.get("prodType");
        Date queryDate = DateUtil.parse((String)params.get("queryDate"));
        //OPEX-650【产品缴纳】缴纳查询界面，选择纳税日期查询条件进行查询，报数据接口异常 guyunfei 20191227 start
        Date queryDateBegin = DateUtil.parse((String)params.get("queryDateBegin"));
        Date queryDateEnd = DateUtil.parse((String)params.get("queryDateEnd"));
        return valApiCommonService.getAddTaxType(listProdId, prodType, queryDate,queryDateBegin,queryDateEnd);
        //OPEX-650【产品缴纳】缴纳查询界面，选择纳税日期查询条件进行查询，报数据接口异常 guyunfei 20191227 end
    }
}
