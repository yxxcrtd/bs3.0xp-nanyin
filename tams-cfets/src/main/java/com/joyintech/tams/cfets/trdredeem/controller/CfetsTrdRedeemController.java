/**
 * 银行间成交确认
 * @author
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherController
 * @since 1.0
 */

package com.joyintech.tams.cfets.trdredeem.controller;




import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.business.common.pojo.Message;
import com.joyintech.tams.cfets.trdquery.controller.CfetsTrdQueryController;
import com.joyintech.tams.cfets.trdquery.dto.CfetsTrdQueryRegInfoListRequest;
import com.joyintech.tams.cfets.trdquery.dto.CfetsTrdQueryRegInfoListResponse;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@PageId("CfetsTrdRedeem")
@RequestMapping("/cfets/trdredeem/CfetsTrdRedeem")
@Api(tags = "银行间赎回登记")
public class CfetsTrdRedeemController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CfetsTrdRedeemController.class);

    @ApiOperation(value = "查询页面初始化参数", notes = "查询页面初始化参数")
    @PostMapping("/getPageParams")
    @ResponseBody
    public Message<Map<String, Object>> getPageParams(){
        Map<String, Object> initPageParams = new HashMap<>();
        //是否支持CSTP下行接口
        initPageParams.put("IS_SUPPORT_CSTP_FLAG", CacheUtil.getSysParam("IS_SUPPORT_CSTP_FLAG"));


        //自动刷新时间
        initPageParams.put("cfetsTrdQuery_regList_refresh_period", CacheUtil.getSysParam("cfetsTrdConfirm_regList_refresh_period"));
        initPageParams.put("cfetsTrdQuery_ibuList_refresh_period", CacheUtil.getSysParam("cfetsTrdConfirm_cstpList_refresh_period"));
        initPageParams.put("cfetsTrdQuery_cstpList_refresh_period", CacheUtil.getSysParam("cfetsTrdConfirm_matchList_refresh_period"));


        //数据字典初始化数据
        initPageParams.put("REG_ASSET_TYPE", this.getSelectDataFromDataDic(DictCode.登记资产类型, null));
        initPageParams.put("PS", this.getSelectDataFromDataDic(DictCode.交易方向, null));
        initPageParams.put("TRADE_MARKET", this.getSelectDataFromDataDic(DictCode.交易场所, null));
        initPageParams.put("ACCOUNT_SPEED", this.getSelectDataFromDataDic(DictCode.清算速度, null));
        initPageParams.put("QUOTE_TYPE", this.getSelectDataFromDataDic(BussinessDictCode.报价方式, null));
        initPageParams.put("STATUS_VALID", this.getSelectDataFromDataDic(DictCode.有效状态, null));
        initPageParams.put("MATCH_STATUS", this.getSelectDataFromDataDic(DictCode.匹配状态, null));
        initPageParams.put("REG_STATUS", this.getSelectDataFromDataDic(DictCode.登记状态, null));
        initPageParams.put("DEAL_TYPE_IBU", this.getSelectDataFromDataDic(BussinessDictCode.银行间成交状态, null));
        initPageParams.put("CSTP_MATCH_STATUS", this.getSelectDataFromDataDic(DictCode.CSTP匹配状态, null));




        initPageParams.put("TRD_MATCH_TYPE", this.getSelectDataFromDataDic(DictCode.下行交易匹配类型, null));
        initPageParams.put("CSTP_BUSINESS_TYPE", this.getSelectDataFromDataDic(DictCode.CSTP资产类型, null));
        initPageParams.put("QUOTE_STYLE", this.getSelectDataFromDataDic(BussinessDictCode.报价方式_登记, null));


        //pageId
        initPageParams.put("pageId", "CfetsTrdQuery");
        //当前平台日
        initPageParams.put("platDate", DateUtil.platDateFromCache());
        return new Message<Map<String, Object>>(initPageParams).success();
    }
    @ApiOperation(value = "赎回登记列表分页查询", notes = "赎回登记列表分页查询")
    @PostMapping("/queryAllRedeemRegInfoList")
    @ResponseBody
    public Message<Page<CfetsTrdQueryRegInfoListResponse>> queryAllRegInfoList(@RequestBody CfetsTrdQueryRegInfoListRequest dto)
            throws Exception {
        try {
            Page<CfetsTrdQueryRegInfoListResponse> page =null;//cfetsTrdQueryService.queryAllRegInfoList(dto);
            return new Message<Page<CfetsTrdQueryRegInfoListResponse>>().success().setData(page);
        } catch (Exception e) {
            logger.error("查询交易登记列表分页数据异常", e);
            return new Message<Page<CfetsTrdQueryRegInfoListResponse>>().fail().setMessage("查询交易登记列表分页数据异常");
        }
    }


}
