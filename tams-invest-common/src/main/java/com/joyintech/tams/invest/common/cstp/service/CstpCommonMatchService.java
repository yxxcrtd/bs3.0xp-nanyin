package com.joyintech.tams.invest.common.cstp.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.TrdMatchInfoMapper;
import com.joyintech.tams.common.base.dao.TrdPledgeMatchInfoMapper;
import com.joyintech.tams.common.base.entity.InterfOpicsTradRepo;
import com.joyintech.tams.common.base.entity.InterfTradSecdeal;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.common.base.entity.TrdPledgeMatchInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.cstp.dao.CstpCommonMatchMapper;
import com.joyintech.tams.invest.common.dto.cstp.EtrEntrustExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @auther zhangnian
 * @date 2021/3/25
 */
@Service
public class CstpCommonMatchService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("loadBalancedRestTemplate")
    protected JoyinRestTemplate restTemplate;

    @Autowired
    private CstpCommonMatchBondImplService cstpCommonMatchBondImplService;

    @Autowired
    private CstpCommonMatchRepoImplService cstpCommonMatchRepoImplService;

    @Autowired
    private CstpCommonMatchMapper cstpCommonMatchMapper;
    @Autowired
    private TrdMatchInfoMapper trdMatchInfoMapper;

    @Autowired
    private TrdPledgeMatchInfoMapper trdPledgeMatchInfoMapper;

    /**
     *  匹配委托信息
     * @param map
     * @return
     */
    public Map<String,Object> dealMatchEntrust(Map<String, Object> map) {
        log.info("下行成交匹配开始,成交编号"+(String)map.get("execId"));
        List<EtrEntrustExt> etrEntrustExts = getMatchEntrust(map);
        try{
            if(!StringUtil.isEmptyList(etrEntrustExts)){
                map.put("etrEntrustExts",etrEntrustExts);
                String finprodType2 = (String) map.get("finprodType2");
                if(StringUtil.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                    log.info("下行成交债券委托信息匹配中");
                    cstpCommonMatchBondImplService.dealMatchEntrust(map);
                }else if(StringUtil.equals(finprodType2,DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())||StringUtil.equals(finprodType2,DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
                    log.info("下行成交质押式回购委托信息匹配中");
                    cstpCommonMatchRepoImplService.dealMatchEntrust(map);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("下行成交匹配异常!"+e.getMessage());
        }
        log.info("下行成交匹配结束");
        return map;
    }

    /**
     *  通过成交编号获取可以匹配的委托信息
     * @param map
     * @return
     */
    public List<EtrEntrustExt> getMatchEntrust(Map<String, Object> map) {
        String execId = map.containsKey("execId")?(String)map.get("execId"): null;
        String matchType = map.containsKey("matchType")?(String)map.get("matchType"):null;
        if(StringUtil.isEmpty(execId)||StringUtil.isEmpty(matchType)){
            log.info("成交编号或者匹配方式不可为空");
            return new ArrayList<>();
        }
        convertMapByExecId(map);
        //手工匹配 按无报价无委托处理
        if(StringUtil.equals(matchType, DictCodeListExt3.下行交易匹配类型_手动匹配.getValue())){
            map.put("quoteIdFlag","03");
            Map<String,Object> map2 = new HashMap<>();
            map2.put("entrustIds", Arrays.asList(((String)map.get("entrustIds")).split(",")));
            return cstpCommonMatchMapper.getQualifiedEtrEntrust(map2);
        }
        EtrEntrustExt etrEntrustExt = cstpCommonMatchMapper.getQuoteInfoByexecId(execId);
        //有报价有委托01 有报价无委托02  无报价无委托03
        if(null==etrEntrustExt){
            map.put("quoteIdFlag","03");
        }else{
            map.put("quoteIdFlag",etrEntrustExt.getQuoteIdFlag());
            map.put("quoteId",etrEntrustExt.getQuoteId());
            map.put("isDzh",etrEntrustExt.getIsDzh());
            if(StringUtil.equals(etrEntrustExt.getQuoteIdFlag(),"02")){
                map.put("portfolioId",etrEntrustExt.getPortfolioId());
            }
            if(StringUtil.equals(etrEntrustExt.getQuoteIdFlag(),"01")){
                //委托信息固定
                Map<String,Object> map2 = new HashMap<>();
                map.put("entrustId",etrEntrustExt.getEntrustId());
                map2.put("entrustIds", Arrays.asList(etrEntrustExt.getEntrustId().split(",")));
                return cstpCommonMatchMapper.getQualifiedEtrEntrust(map2);
            }
        }
        return cstpCommonMatchMapper.getQualifiedEtrEntrust(map);
    }





    /**
     * 组装参数map
     * @param execId
     * @return
     */
    private Map<String,Object> convertMapByExecId(Map<String, Object> map){
        //通过成交编号查询成交信息  先查债券  再查回购
        String execId = (String)map.get("execId");
        InterfTradSecdeal interfTradSecdeal = cstpCommonMatchMapper.getInterfTradSecdealByExecId(execId);
        if(null==interfTradSecdeal){
            InterfOpicsTradRepo interfOpicsTradRepo = cstpCommonMatchMapper.getInterfOpicsTradRepoByExecId(execId);
            if(null!=interfOpicsTradRepo){
                String ps = interfOpicsTradRepo.getPs();
                if(StringUtil.equals(ps, DictCodeList.回购拆借方向_正回购.getValue())){
                    map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
                }else{
                    map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());

                }
                map.put("ps",ps);
                map.put("price",interfOpicsTradRepo.getReporate());
                map.put("dealDate", DateUtil.format(interfOpicsTradRepo.getDealdate()));
                List<String> acctNoList = Arrays.asList(interfOpicsTradRepo.getOurdepositaccountnumber().split(","));
                if(!StringUtil.isEmptyList(acctNoList)&&acctNoList.size()>1){
                    map.put("isktg","Y");
                }else{
                    map.put("isktg","N");
                }
                map.put("acctNoList",acctNoList);
                map.put("interfOpicsTradRepo",interfOpicsTradRepo);
            }
        }else{
            map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            map.put("ps",interfTradSecdeal.getPs());
            map.put("price",interfTradSecdeal.getPrice8());
            map.put("dealDate", DateUtil.format(interfTradSecdeal.getDealdate()));
            map.put("finprodId","F01_"+interfTradSecdeal.getSecid());
            map.put("acctNoList",Arrays.asList(interfTradSecdeal.getOurdepositaccountnumber().split(",")));
            map.put("interfTradSecdeal",interfTradSecdeal);
        }
        return map;
    }

    /**
     * 组合可用现金及持仓 position
     * @param parameters
     * @return
     */
    public Map<String, Object> getAvailableCash(Map<String, Object> parameters) {
            return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdAvailableCashQuery/getAvailableCash",
                parameters, Map.class);
    }

    /**
     * 组合可用持仓
     * @param map
     * @return
     */
    public Map<String, Object>  getFinAvaPosition(Map<String, Object> map){
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdAvailableCashQuery/getFinAvaPosition",
                map, Map.class);
    }

    /**
     * 匹配信息存储
     * @param map
     */
    public void saveMatchInfo(Map<String,Object> map){
        if(map.containsKey("trdMatchInfos")){
            List<TrdMatchInfo> trdMatchInfos = (List<TrdMatchInfo>)map.get("trdMatchInfos");
            trdMatchInfos.forEach(item ->{
                item.setSeqno(cstpCommonMatchMapper.selectSeqNo(DateUtil.getSysDate()));
                trdMatchInfoMapper.insert(item);
            });
        }
        if(map.containsKey("trdPledgeMatchInfos")){
            List<TrdPledgeMatchInfo> trdPledgeMatchInfos = (List<TrdPledgeMatchInfo>)map.get("trdPledgeMatchInfos");
            trdPledgeMatchInfos.forEach(item ->{
                item.setSeqno(cstpCommonMatchMapper.selectSeqNo(DateUtil.getSysDate()));
                trdPledgeMatchInfoMapper.insert(item);
            });
        }
    }
}

