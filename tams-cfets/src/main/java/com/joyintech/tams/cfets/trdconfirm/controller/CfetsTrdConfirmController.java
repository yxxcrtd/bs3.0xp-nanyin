/**
 * 场外指令分发
 * @author sjj
 * @version 1.0 2021年10月26日
 * @see CfetsEtrDispatcherController
 * @since 1.0
 */

package com.joyintech.tams.cfets.trdconfirm.controller;



import com.joyintech.framework.grid.Page;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.cfets.trdconfirm.dto.CfetsTrdConfirmRegInfoListRequest;
import com.joyintech.tams.cfets.trdconfirm.dto.CfetsTrdConfirmRegInfoListResponse;
import com.joyintech.tams.cfets.trdconfirm.service.CfetsTrdConfirmService;
import com.joyintech.tams.constant.DictCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.joyintech.tams.business.common.pojo.Message;

import java.util.*;


@Controller
@PageId("CfetsTrdConfirm")
@RequestMapping("/cfets/trdconfirm/CfetsTrdConfirm")
@Api(tags = "银行间交易成交确认")
public class CfetsTrdConfirmController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CfetsTrdConfirmController.class);

    @Autowired
    private CfetsTrdConfirmService cfetsTrdConfirmService;

    @ApiOperation(value = "查询页面初始化参数", notes = "查询页面初始化参数")
    @PostMapping("/getPageParams")
    @ResponseBody
    public Message<Map<String, Object>> getPageParams(){
        Map<String, Object> initPageParams = new HashMap<>();
        //数据字典初始化数据
        initPageParams.put("PINAN_ASSET_TYPE", this.getSelectDataFromDataDic(DictCode.平安资产类型, null));
        initPageParams.put("BUSI_TYPE_POSITION", this.getSelectDataFromDataDic(DictCode.头寸业务类型, null));
        initPageParams.put("TRADE_MARKET", this.getSelectDataFromDataDic(DictCode.交易流通场所, null));
        initPageParams.put("CCY", this.getSelectDataFromDataDic(DictCode.币种, null));
        initPageParams.put("ACCOUNT_SPEED", this.getSelectDataFromDataDic(DictCode.清算速度, null));
        initPageParams.put("PS", this.getSelectDataFromDataDic(DictCode.交易方向, null));
        initPageParams.put("SHORTSELLINGSUPPORTED01", CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));

        //pageId
        initPageParams.put("pageId", "CfetsTrdConfirm");
        return new Message<Map<String, Object>>(initPageParams).success();
    }
    /**
     * 交易登记列表分页查询
     *
     * @param dto
     * @return Page<CfetsTrdConfirmRegInfoListResponse>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ApiOperation(value = "交易登记列表分页查询", notes = "交易登记列表分页查询")
    @PostMapping("/findAllRegInfoList")
    @ResponseBody
    public Message<Page<CfetsTrdConfirmRegInfoListResponse>> findAllRegInfoList(@RequestBody CfetsTrdConfirmRegInfoListRequest dto)
            throws Exception {
        try {
            Page<CfetsTrdConfirmRegInfoListResponse> page = null;//cfetsTrdConfirmService.findAllRegInfoList(dto);
            return new Message<Page<CfetsTrdConfirmRegInfoListResponse>>().success().setData(page);
        } catch (Exception e) {
            logger.error("查询交易登记列表分页数据异常", e);
            return new Message<Page<CfetsTrdConfirmRegInfoListResponse>>().fail().setMessage("查询交易登记列表分页数据异常");
        }
    }

}
