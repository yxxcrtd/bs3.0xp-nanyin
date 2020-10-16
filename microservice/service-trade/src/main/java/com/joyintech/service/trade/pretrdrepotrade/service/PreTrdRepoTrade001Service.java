
package com.joyintech.service.trade.pretrdrepotrade.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.pretrdrepotrade.dao.PreTrdRepoTrade001Mapper;
import com.joyintech.service.trade.pretrdrepotrade.dto.PreTrdRepoTrade001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

@Service("backPreTrdRepoTrade001Service")
public class PreTrdRepoTrade001Service extends BaseService<PreTrdRepoTrade001Dto> {
    /**
     * 投资委托基础组件
     */
    @Autowired(required = false)
    private PreTrdRepoTrade001Mapper preTrdRepoTrade001Mapper;
    /**
     * 回购预交易查询画面，查询回购预交易信息列表
     *
     * @param page      page
     * @param length    length
     * @param pageParam 参数
     * @return Page<PreTrdRepoTrade001Dto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PreTrdRepoTrade001Dto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        setParams(pageParam);
        if (StringUtil.isNotEmpty(pageParam.get("parentId")) && !StringUtil.equals(pageParam.get("parentId"), "root")) {
            String tradeId = String.valueOf(pageParam.get("parentId")).split("_")[0];
            if (tradeId.endsWith("R")){
                pageParam.put("tradeId",tradeId.substring(0,tradeId.length()-1));
            }else {
                pageParam.put("tradeId",tradeId);
            }
            String status = String.valueOf(pageParam.get("parentId")).split("_")[1];
            pageParam.put("status",status);
            //修改不支持清算速度条件查询，by 赵东东 20200325 start
            if(StringUtil.equals(pageParam.get("settleSpreed"),"01")) {
            	pageParam.put("settleSpreed","0");
            }else if(StringUtil.equals(pageParam.get("settleSpreed"),"02")){
            	pageParam.put("settleSpreed","1");
            }
            //修改不支持清算速度条件查询，by 赵东东 20200325 end
            List<PreTrdRepoTrade001Dto> resultList = preTrdRepoTrade001Mapper.selectForPage(pageParam);
            Page<PreTrdRepoTrade001Dto> pagelist = new Page<>();
            pagelist.setRoot(resultList);
            return pagelist;
        }
        //修改不支持清算速度条件查询，by 赵东东 20200325 start
        if(StringUtil.equals(pageParam.get("settleSpreed"),"01")) {
        	pageParam.put("settleSpreed","0");
        }else if(StringUtil.equals(pageParam.get("settleSpreed"),"02")){
        	pageParam.put("settleSpreed","1");
        }
        //修改不支持清算速度条件查询，by 赵东东 20200325 end
        Page<PreTrdRepoTrade001Dto> pageList= super.queryForPage(page, length, () -> preTrdRepoTrade001Mapper.selectForPage(pageParam));
        return pageList;
    }

    private void setParams(Map<String, Object> pageParam) {
        pageParam.put("status_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        pageParam.put("status_02", DictCodeListExt1.交易流程状态_有效.getValue());
        pageParam.put("status_03", DictCodeListExt1.交易流程状态_撤销.getValue());
        pageParam.put("FINPROD_TYPE_TG_F02", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        pageParam.put("FINPROD_TYPE_TG_F03", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        pageParam.put("COMMON_BUSINESS_TYPE_TRD70",DictCodeListExt1.共通业务类型_质押式回购交易预录入.getValue());
        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("TRADE_STATUS_03", DictCodeListExt1.交易状态_撤销.getValue());
        pageParam.put("IS_CANCEL_Y",DictCodeList.是否_是.getValue());
        pageParam.put("IS_CANCEL_N",DictCodeList.是否_否.getValue());

    }

    /**
     * 获取交易对手列表
     * @param map 请求参数
     * @return 交易对手列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterAcct(Map<String,Object> map){
        return preTrdRepoTrade001Mapper.getCounterAcct(map);
    }

    /**
     * 获取资金账户下拉框
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getAcct(Map<String, Object> params) {
        return preTrdRepoTrade001Mapper.getAcct( params);
    }

    /**
     * 获取托管账号下拉框
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public List<SelectOptionDto> getSecAcct(Map<String, Object> params) {
        return preTrdRepoTrade001Mapper.getSecAcct(params);
    }


    /**
     * 获取资金账户
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public PreTrdRepoTrade001Dto getAcctId(Map<String, Object> pageParam) {
        return preTrdRepoTrade001Mapper.getAcctId( pageParam);
    }

    /**
     * 获取托管
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public PreTrdRepoTrade001Dto getSecAcctDto(Map<String, Object> pageParam) {
        return preTrdRepoTrade001Mapper.getSecAcctDto( pageParam);
    }

    /**
     * 获取下行交易匹配情况
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<Map<String, Object>> findTrdMatchInfo(Map<String, Object> params) {
        return preTrdRepoTrade001Mapper.findTrdMatchInfo(params);
    }

    public PreTrdRepoTrade001Dto getPreTrdRepoTradeDto(Map<String, Object> params) {
        PreTrdRepoTrade001Dto preTrdRepoTrade001Dto = preTrdRepoTrade001Mapper.getPreTrdRepoTradeDto(params);
        return preTrdRepoTrade001Dto;
    }
}
